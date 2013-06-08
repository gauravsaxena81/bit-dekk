package org.bitdekk.helper.sql.grammar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.bitdekk.DataLayer;
import org.bitdekk.api.IBitSet;
import org.bitdekk.exception.InvalidBitDekkExpressionException;
import org.bitdekk.helper.MeasureHelper;
import org.bitdekk.model.DataRow;
import org.bitdekk.model.DimensionValue;
import org.bitdekk.model.Table;
import org.bitdekk.util.BitDekkUtil;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableCell;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.ValueType;


public class SqlHelper {
	
	private DataLayer dataLayer;
	private HashMap<String, ArrayList<String>> dimensionToDimensionValuesMap = new HashMap<String, ArrayList<String>>();
	private HashMap<String, String> dimensionValuesToDimensionMap = new HashMap<String, String>();
	private MeasureHelper measureHelper;
	
	public DataLayer getDataLayer() {
		return dataLayer;
	}
	public MeasureHelper getMeasureHelper() {
		return measureHelper;
	}
	public void setMeasureHelper(MeasureHelper measureHelper) {
		this.measureHelper = measureHelper;
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
					, getFilterBitSet(state.getDimensionConditions(), dimensionToDimensionValuesMap), state.getTableName(), state.getHavingExpressions());
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
		ArrayList<TableRow> rowList = new ArrayList<TableRow>();
		//while((viewBitSet = nextBitSet(dimensionToDimensionValuesMap, columns, counters)) != null) {
		for(ArrayList<DimensionValue> list : createBuckets(tableName, columns, filterBitSet)) {
			TableRow row = new TableRow();
			boolean isAllMeasuresNull = false;
			boolean isAnyDimensionPresent = false;
			int k = 0;
			IBitSet viewBitSet = dataLayer.getBitSet(list.toArray(new DimensionValue[0]));
			for(IColumn j : columns) {
				if(j instanceof Dimension) {
					row.addCell(list.get(k++).getDimensionValue());
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
	private ArrayList<ArrayList<DimensionValue>> createBuckets(String tableName, ArrayList<IColumn> columns, IBitSet filterBitSet) {
		Table table = measureHelper.getTable(tableName);
		Set<IBitSet> bucketBitSets = new HashSet<IBitSet>();
		ArrayList<ArrayList<DimensionValue>> buckets = new ArrayList<ArrayList<DimensionValue>>();
		for(DataRow i : table.getRows()) {
			if(filterBitSet.contains(i.getMeasureQuery())) {
				IBitSet bits = BitDekkUtil.newBitSet();
				ArrayList<DimensionValue> list = new ArrayList<DimensionValue>();
				boolean isDimensionPresent = false;
				for(IColumn k : columns) {
					if(k instanceof Dimension) {
						isDimensionPresent = true;
						//for(int j = i.getMeasureQuery().nextSetBit(0); j > -1; j = i.getMeasureQuery().nextSetBit(j + 1)) {
						for(Integer j : i.getMeasureQuery()) {
							if(dataLayer.getDimension(j).equals(((Dimension) k).getName())) {
								bits.set(j);
								list.add(dataLayer.getDimensionValue(j));
								break;
							}
						}
					}
				}
				if(isDimensionPresent && bucketBitSets.add(bits))
					buckets.add(list);
			}
		}
		if(buckets.isEmpty())
			buckets.add(new ArrayList<DimensionValue>());
		return buckets;
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
	/*private IBitSet nextBitSet(HashMap<String, ArrayList<String>> dimensionToDimensionValuesMap, ArrayList<IColumn> columns, int[] counters) {
		IBitSet bitSet = BitDekkUtil.newBitSet();
		boolean containsAtLeastOneDimension = false;
		for(int i = 0, j = 0; i < columns.size(); i++) {
			if(columns.get(i) instanceof Dimension) {
				containsAtLeastOneDimension = true;
				if(j == 0 && counters[j] >= dimensionToDimensionValuesMap.get(((Dimension) columns.get(i)).getName()).size())
					return null;
				bitSet.set(dataLayer.getDimensionId(((Dimension) columns.get(i)).getName()
						, dimensionToDimensionValuesMap.get(((Dimension) columns.get(i)).getName()).get(counters[j])));
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
		}
		return bitSet;
	}*/

	private IBitSet getFilterBitSet(HashMap<String, ArrayList<String>> hashMap, HashMap<String, ArrayList<String>> dimensionToDimensionValuesMap) {
		IBitSet bitSet = BitDekkUtil.newBitSet();
		for(Integer i : dataLayer.getDimensionValueIds())
			bitSet.set(i);
		if(!hashMap.isEmpty()) {
			for(String i : hashMap.keySet()) {
				for(String j : dimensionToDimensionValuesMap.get(i))
					bitSet.clear(dataLayer.getDimensionId(i, j));
				for(String j : hashMap.get(i))
					bitSet.set(dataLayer.getDimensionId(i, j));
			}
		}
		return bitSet;
	}
	public void initialize(HashMap<String, ArrayList<String>> dimensionToDimensionValuesMap) {
		this.dimensionToDimensionValuesMap = dimensionToDimensionValuesMap;
	}
	public void initializeTable(String name, DataTable dataTable) {
		dataLayer.initializeTable(name, dataTable);
		Comparator<TableCell> comparator = new Comparator<TableCell>() {
			@Override
			public int compare(TableCell o1, TableCell o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		};
		for(int i = 0; i < dataTable.getNumberOfColumns(); i++) {
			for(TableCell j : dataTable.getColumnDistinctCellsSorted(i, comparator)) {
				if(!j.getType().equals(ValueType.NUMBER)) {
					String label = dataTable.getColumnDescription(i).getLabel();
					String dimensionValue = j.getValue().toString();
					dimensionValuesToDimensionMap.put(dimensionValue, label);
					ArrayList<String> list = dimensionToDimensionValuesMap.get(label);
					if(list == null) {
						list = new ArrayList<String>();
						dimensionToDimensionValuesMap.put(label, list);
					}
					list.add(dimensionValue);
				}
			}
		}
	}
}