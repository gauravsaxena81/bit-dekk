package org.bitdekk.helper.sql.grammar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.bitdekk.DataLayer;
import org.bitdekk.api.IBitSet;
import org.bitdekk.exception.InvalidBitDekkExpressionException;
import org.bitdekk.util.OpenBitSet;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.ValueType;


public class SqlHelper {
	
	private DataLayer dataLayer;
	private HashMap<String, ArrayList<String>> dimensionToDimensionValuesMap;
	
	public DataLayer getDataLayer() {
		return dataLayer;
	}
	public void setDataLayer(DataLayer dataLayer) {
		this.dataLayer = dataLayer;
	}
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
			int dimensionColumnNumber = applyProjection(state.getColumns(), dataTable);
			ArrayList<TableRow> rowList = getRowList(state.getColumns(), new int[dimensionColumnNumber]
					, getFilterOpenBitSet(state.getDimensionConditions(), dimensionToDimensionValuesMap), state.getTableName(), state.getHavingExpressions());
			//rowList = applyHaving(state.getHavingExpressions(), rowList, dataTable.getColumnDescriptions());
			applyOrderBy(state.getOrderByColumns(), rowList);
			applyLimit(state.getFromRowNumber(), state.getToRowNumber(), rowList, dataTable);
		} catch (RecognitionException e) {
			throw new InvalidGrammarException("Bad Grammar near " + ((TokenStream)parser.input).LT(1).getText());
		} catch (TypeMismatchException e) {
			e.printStackTrace();
		}
		return dataTable;
	}
	private int applyProjection(ArrayList<IColumn> columns, DataTable dataTable) {
		int dimensionColumnNumber = 0;
		for(IColumn i : columns) {
			if(i instanceof Dimension) {
				dataTable.addColumn(new ColumnDescription(i.getLabel(), ValueType.TEXT, i.getLabel()));
				dimensionColumnNumber++;
			} else
				dataTable.addColumn(new ColumnDescription(i.getLabel(), ValueType.NUMBER, i.getLabel()));
		}
		return dimensionColumnNumber;
	}
	private ArrayList<TableRow> getRowList(ArrayList<IColumn> columns, int[] counters, IBitSet filterBitSet, String tableName
			, ArrayList<HavingExpression> havingExpressions) {
		OpenBitSet viewBitSet;
		ArrayList<TableRow> rowList = new ArrayList<TableRow>();
		while((viewBitSet = nextBitSet(dimensionToDimensionValuesMap, columns, counters)) != null) {
			TableRow row = new TableRow();
			boolean isAllMeasuresNull = false;
			boolean isAnyDimensionPresent = false;
			int k = -1;
			for(IColumn j : columns) {
				if(j instanceof Dimension) {
					k = viewBitSet.nextSetBit(k + 1);
					row.addCell(dataLayer.getDimensionValue(k));
					isAnyDimensionPresent = true;
				} else {
					double value = dataLayer.aggregate(tableName, viewBitSet, filterBitSet, ((Measure)j).getMeasureExpression());
					row.addCell(value);
					isAllMeasuresNull |= Double.isNaN(value);
				}
			}
			if(!isAllMeasuresNull) {
				boolean isHavingTrue = true;
				for(HavingExpression j : havingExpressions) {
					if(!logicalOperation(dataLayer.aggregate(tableName, viewBitSet, filterBitSet, j.getLhs()), j.getLogicOperator(), 
						dataLayer.aggregate(tableName, viewBitSet, filterBitSet, j.getRhs()))) {
						isHavingTrue = false;
						break;
					}
				}
				if(isHavingTrue)
					rowList.add(row);
			}
			if(!isAnyDimensionPresent)
				break;
		}
		return rowList;
	}
	private boolean logicalOperation(double lhsAggregate, String logicOperator, double rhsAggregate) {
		if(logicOperator.equals("="))
			return lhsAggregate == rhsAggregate;
		else if(logicOperator.equals("<"))
			return lhsAggregate < rhsAggregate;
		else if(logicOperator.equals(">"))
			return lhsAggregate > rhsAggregate;
		else if(logicOperator.equals(">="))
			return lhsAggregate >= rhsAggregate;
		else if(logicOperator.equals("<="))
			return lhsAggregate <= rhsAggregate;
		else if(logicOperator.equals("<>"))
			return lhsAggregate != rhsAggregate;
		else
			return false;
	}
	private void applyLimit(int fromRowNumber, int toRowNumber, ArrayList<TableRow> rowList, DataTable dataTable) throws TypeMismatchException {
		if(fromRowNumber != -1 && toRowNumber != -1)
			for(int i = fromRowNumber; i < rowList.size() && i < toRowNumber; i++)
				dataTable.addRow(rowList.get(i));
		else
			for(TableRow i : rowList)
				dataTable.addRow(i);		
	}
	private void applyOrderBy(ArrayList<OrderColumn> orderByColumns, ArrayList<TableRow> rowList) {
		if(!orderByColumns.isEmpty())
			Collections.sort(rowList, new TableRowComparator(orderByColumns));		
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
		dataLayer.initializeDimensionValues(hashMap);
	}
	public void initializeTable(String name, DataTable dataTable) {
		dataLayer.initializeTable(name, dataTable);
	}
}