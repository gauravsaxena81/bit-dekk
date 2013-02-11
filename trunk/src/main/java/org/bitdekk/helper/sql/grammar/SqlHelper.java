package org.bitdekk.helper.sql.grammar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.bitdekk.DataLayer;
import org.bitdekk.helper.InvalidBitDekkExpressionException;
import org.bitdekk.util.OpenBitSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.ValueType;

@Component
public class SqlHelper {
	@Autowired
	private DataLayer dataLayer;
	private HashMap<String, ArrayList<String>> dimensionToDimensionValuesMap;
	
	public DataTable result(String sql) throws InvalidBitDekkExpressionException {
		ErrorHandlingLexer lexer = new ErrorHandlingLexer(new ANTLRStringStream(sql));
		CommonTokenStream tokens = new CommonTokenStream();
		tokens.setTokenSource(lexer);
		State state = new State();
		ErrorHandlingParser parser = new ErrorHandlingParser(tokens, state);
		DataTable dataTable = null;
		try {
			parser.stat();
			dataTable = new DataTable();
			int dimensionColumnNumber = 0;
			for(IColumn i : state.getColumns()) {
				if(i instanceof Dimension) {
					dataTable.addColumn(new ColumnDescription(i.getLabel(), ValueType.TEXT, i.getLabel()));
					dimensionColumnNumber++;
				} else
					dataTable.addColumn(new ColumnDescription(i.getLabel(), ValueType.NUMBER, i.getLabel()));
			}
			OpenBitSet filterBitSet = getFilterOpenBitSet(state.getDimensionConditions(), dimensionToDimensionValuesMap);
			int[] counters = new int[dimensionColumnNumber];
			OpenBitSet viewBitSet;
			ArrayList<TableRow> rowList = new ArrayList<TableRow>();
			while((viewBitSet = nextBitSet(dimensionToDimensionValuesMap, state.getColumns(), counters)) != null) {
				TableRow row = new TableRow();
				boolean isAllMeasuresNull = false;
				boolean isAnyDimensionPresent = false;
				int k = -1;
				for(IColumn j : state.getColumns()) {
					if(j instanceof Dimension) {
						k = viewBitSet.nextSetBit(k + 1);
						row.addCell(dataLayer.getDimensionValue(k));
						isAnyDimensionPresent = true;
					} else {
						double value = dataLayer.aggregate(state.getTableName(), viewBitSet, filterBitSet, ((Measure)j).getMeasureExpression());
						row.addCell(value);
						isAllMeasuresNull |= Double.isNaN(value);
					}
				}
				if(!isAllMeasuresNull)
					rowList.add(row);
				if(!isAnyDimensionPresent)
					break;
			}
			if(!state.getOrderByColumns().isEmpty())
				Collections.sort(rowList, new TableRowComparator(state.getOrderByColumns()));
			if(state.getFromRowNumber() != -1 && state.getToRowNumber() != -1)
				for(int i = state.getFromRowNumber(); i < rowList.size() && i < state.getToRowNumber(); i++)
					dataTable.addRow(rowList.get(i));
			else
				for(TableRow i : rowList)
					dataTable.addRow(i);
		} catch (RecognitionException e) {
			e.printStackTrace();
			return null;
		} catch (TypeMismatchException e) {
			e.printStackTrace();
		}
		return dataTable;
	}
	private OpenBitSet nextBitSet(HashMap<String, ArrayList<String>> dimensionToDimensionValuesMap, ArrayList<IColumn> columns, int[] counters) {
		OpenBitSet bitSet = new OpenBitSet();
		boolean containsAtLeastOneDimension = false;
		for(int i = 0, j = 0; i < columns.size(); i++) {
			if(columns.get(i) instanceof Dimension) {
				containsAtLeastOneDimension = true;
				if(j == 0 && counters[j] >= dimensionToDimensionValuesMap.get(((Dimension) columns.get(i)).getName()).size())
					return null;
				bitSet.set(dataLayer.getDimensionId(dimensionToDimensionValuesMap.get(((Dimension) columns.get(i)).getName()).get(counters[j])));
				j++;
			}
		}
		if(containsAtLeastOneDimension) {
			counters[counters.length - 1]++;
			for(int j = counters.length - 1, i = columns.size() - 1; j > 0; i--) {
				if(columns.get(i) instanceof Dimension) {
					if(counters[j] == dimensionToDimensionValuesMap.get(((Dimension) columns.get(i)).getName()).size()) {
						counters[j] = 0;
						counters[j - 1]++;
						j--;
					} else
						break;
				}
			}
		} /*else {
			for(ArrayList<String> i : dimensionToDimensionValuesMap.values())
				for(String j : i)
					bitSet.set(dataLayer.getDimensionId(j));
		}*/
		return bitSet;
	}

	private OpenBitSet getFilterOpenBitSet(HashMap<String, ArrayList<String>> hashMap, HashMap<String, ArrayList<String>> dimensionToDimensionValuesMap) {
		OpenBitSet openBitSet = new OpenBitSet();
		for(Integer i : dataLayer.getDimensionIds())
			openBitSet.set(i);
		if(!hashMap.isEmpty()) {
			for(String i : hashMap.keySet()) {
				for(String j : dimensionToDimensionValuesMap.get(i))
					openBitSet.flip(dataLayer.getDimensionId(j));
				for(String j : hashMap.get(i))
					openBitSet.set(dataLayer.getDimensionId(j));
			}
		}
		return openBitSet;
	}
	public void initialize(HashMap<String, ArrayList<String>> dimensionToDimensionValuesMap, HashMap<String, Integer> hashMap) {
		this.dimensionToDimensionValuesMap = dimensionToDimensionValuesMap;
		dataLayer.initializeDimensions(hashMap);
	}
	public void initializeTable(String name, DataTable dataTable) {
		dataLayer.initializeTable(name, dataTable);
	}
}