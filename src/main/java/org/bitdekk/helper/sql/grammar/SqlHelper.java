package org.bitdekk.helper.sql.grammar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.bitdekk.DataLayer;
import org.bitdekk.api.IBitSet;
import org.bitdekk.exception.InvalidBitDekkExpressionException;
import org.bitdekk.helper.MeasureHelper;
import org.bitdekk.helper.sql.grammar.model.AggregationTreeNode;
import org.bitdekk.helper.sql.grammar.model.Dimension;
import org.bitdekk.helper.sql.grammar.model.IColumn;
import org.bitdekk.helper.sql.grammar.model.Measure;
import org.bitdekk.helper.sql.grammar.model.OrderColumn;
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
	private HashMap<String, ArrayList<String>> dimensionToDimensionValuesMap = new HashMap<String, ArrayList<String>>();//Map of dimension name to list of dimension values string
	private HashMap<String, String> dimensionValuesToDimensionMap = new HashMap<String, String>();//Map of dimension value string to dimension name
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
		ArrayList<String> measureExpressions = new ArrayList<String>();
		for(IColumn j : columns)
			if(j instanceof Measure)
				measureExpressions.add(((Measure)j).getMeasureExpression());
		for(HavingExpression i : havingExpressions) {
			measureExpressions.add(i.getLhs());
			measureExpressions.add(i.getRhs());
		}
		for(AggregationTreeNode i : createBucketsO1Bucketing(tableName, columns, filterBitSet, measureExpressions.toArray(new String[0]))) {
			TableRow row = new TableRow();
			boolean isAllMeasuresNull = false;
			boolean isAnyDimensionPresent = false;
			int k = 0;
			double[] aggregatedValues = i.getNodeObject();
			int measureIndex = 0;
			for(IColumn j : columns) {
				if(j instanceof Dimension) {
					row.addCell(i.getRowDimensionValues().get(k++).getDimensionValue());
					isAnyDimensionPresent = true;
				} else {
					double value = aggregatedValues[measureIndex++];
					row.addCell(value);
					isAllMeasuresNull |= Double.isNaN(value);
				}
			}
			if(!isAllMeasuresNull) {
				boolean isHavingTrue = true;
				for(HavingExpression j : havingExpressions) {
					if(!logicalOperation(aggregatedValues[measureIndex++], j.getLogicOperator(), aggregatedValues[measureIndex++])) {
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
	/*private ArrayList<TableRow> getRowList(ArrayList<IColumn> columns, int[] counters, IBitSet filterBitSet, String tableName
			, ArrayList<HavingExpression> havingExpressions) {
		ArrayList<TableRow> rowList = new ArrayList<TableRow>();
		ArrayList<String> measureExpressions = new ArrayList<String>();
		int measureSize = 0;
		for(IColumn j : columns) {
			if(j instanceof Measure) {
				measureExpressions.add(((Measure)j).getMeasureExpression());
				measureSize++;
			}
		}
		for(HavingExpression i : havingExpressions) {
			measureExpressions.add(i.getLhs());
			measureExpressions.add(i.getRhs());
		}
		ArrayList<TableCell> cells = new ArrayList<TableCell>();
		MapIndexedArrayListTree<ITreeNode<?>> bucketTree = null;
		try {
			bucketTree = createBucketTree(tableName, columns, filterBitSet, measureExpressions.toArray(new String[0]));
			for(ITreeNode<?> leaf : bucketTree.leaves()) {
				cells.clear();
				ITreeNode<?> parent = bucketTree.parent(leaf);
				TableRow row = new TableRow();
				boolean isAllMeasuresNull = false;
				boolean isAnyDimensionPresent = false;
				int k = measureSize - 1;
				double[] aggregatedValues = (double[]) leaf.getNodeObject();
				for(int i = columns.size() - 1; i >= 0; i--) {
					if(columns.get(i) instanceof Dimension) {
						cells.add(0, new TableCell(dataLayer.getDimensionValue((Integer) parent.getNodeObject()).getDimensionValue()));
						parent = bucketTree.parent(parent);
						isAnyDimensionPresent = true;
					} else {
						double value = aggregatedValues[k--];
						cells.add(0, new TableCell(value));
						isAllMeasuresNull |= Double.isNaN(value);
					}
				}
				if(!isAllMeasuresNull) {
					boolean isHavingTrue = true;
					for(int j = measureSize, havingIndex = 0; havingIndex < havingExpressions.size(); j += 2, havingIndex++) {
						if(!logicalOperation(aggregatedValues[j], havingExpressions.get(havingIndex).getLogicOperator(), aggregatedValues[j + 1])) {
							isHavingTrue = false;
							break;
						}
					}
					if(isHavingTrue) {
						for(TableCell i : cells)
							row.addCell(i);
						rowList.add(row);
					}
				}
				if(!isAnyDimensionPresent)
					break;
			}
		} catch (NodeNotFoundException e) {
			e.printStackTrace();
		}
		return rowList;
	}*/
	/*private MapIndexedArrayListTree<ITreeNode<?>> createBucketTree(String tableName, ArrayList<IColumn> columns, IBitSet filterBitSet, String[] measureExpressions) throws NodeNotFoundException {
		Table table = measureHelper.getTable(tableName);
		MapIndexedArrayListTree<ITreeNode<?>> bucketTree = new MapIndexedArrayListTree<ITreeNode<?>>();
		bucketTree.add(new RootTreeNode(filterBitSet));
		HashMap<String, IBitSet> dimensionBitSMap = new HashMap<String, IBitSet>();
		for(IColumn j : columns)
			if(j instanceof Dimension)
				dimensionBitSMap.put(((Dimension) j).getName(), dataLayer.getDimensionBitSet(((Dimension) j).getName()));
		for(DataRow i : table.getRows()) {
			IBitSet measureQuery = i.getMeasureQuery();
			if(filterBitSet.contains(measureQuery)) {
				ITreeNode<?> parent = bucketTree.root();
				for(IColumn j : columns) {
					if(j instanceof Dimension) {
						IBitSet childrenBitset = parent.childrenBitset();
						if(childrenBitset.intersects(measureQuery)) {
							for(ITreeNode<?> k : bucketTree.children(parent)) {
								if(measureQuery.get((Integer) k.getNodeObject())) {
									parent = k;
									break;
								}
							}
						} else {
							IBitSet dimensionBitSet = dimensionBitSMap.get(((Dimension) j).getName()).clone();
							dimensionBitSet.and(measureQuery);
							DimensionValueTreeNode dimensionValueTreeNode = new DimensionValueTreeNode(dimensionBitSet.nextSetBit(0));
							bucketTree.add(parent, dimensionValueTreeNode);
							parent.childrenBitset().or(dimensionBitSet);
							parent = dimensionValueTreeNode;
						}
					}
				}
				List<ITreeNode<?>> children = bucketTree.children(parent);
				if(children.isEmpty()) {
					AggregationTreeNode aggregationTreeNode = new AggregationTreeNode(measureHelper.getTable(tableName).getMeasureIndexMap()
							, measureExpressions);
					bucketTree.add(parent, aggregationTreeNode);
					if(measureExpressions.length > 0)
						aggregationTreeNode.aggregate(i.getMeasureValues());
				} else if(measureExpressions.length > 0)
					((AggregationTreeNode) children.get(0)).aggregate(i.getMeasureValues());
			}
		}
		return bucketTree;
	}*/
	/*private ArrayList<AggregationTreeNode> createBuckets(String tableName, ArrayList<IColumn> columns, IBitSet filterBitSet, String[] aggregationExpressions) {
		Table table = measureHelper.getTable(tableName);
		TreeMap<IBitSet, AggregationTreeNode> bucketBitSets = new TreeMap<IBitSet, AggregationTreeNode>();
		ArrayList<AggregationTreeNode> buckets = new ArrayList<AggregationTreeNode>();
		
		boolean isDimensionPresent = false;
		boolean isMeasuresPresent = false;
		IBitSet dimensionsPresentInSelectBitSet = BitDekkUtil.newBitSet();
		for(IColumn k : columns) {
			if(k instanceof Dimension) {
				isDimensionPresent = true;
				dimensionsPresentInSelectBitSet.or(dataLayer.getDimensionBitSet(((Dimension) k).getName()));
			} else
				isMeasuresPresent = true;
		}
		OpenBitSet filterOpenBitSet = (OpenBitSet)filterBitSet;
		filterOpenBitSet.compress();
		if(isDimensionPresent && isMeasuresPresent) {
			IBitSet bits = null;
			for(DataRow i : table.getRows()) {
				if(filterOpenBitSet.contains(i.getMeasureQuery())) {
					if(bits == null || !i.getMeasureQuery().contains(bits)) {
						bits = dimensionsPresentInSelectBitSet.clone();
						bits.and(i.getMeasureQuery());
					}
					AggregationTreeNode aggregationTreeNode = bucketBitSets.get(bits);
					if(aggregationTreeNode == null) {
						ArrayList<DimensionValue> list = new ArrayList<DimensionValue>();
						for(Integer j : bits)
							list.add(dataLayer.getDimensionValue(j));
						aggregationTreeNode= new AggregationTreeNode(table.getMeasureIndexMap(), aggregationExpressions);
						buckets.add(aggregationTreeNode);
						aggregationTreeNode.setRowDimensionValues(list);
						bucketBitSets.put(bits, aggregationTreeNode);
					}
					aggregationTreeNode.aggregate(i.getMeasureValues());
				}
			}
		} else if(isDimensionPresent) {
			for(DataRow i : table.getRows()) {
				if(filterOpenBitSet.contains(i.getMeasureQuery())) {
					IBitSet bits = dimensionsPresentInSelectBitSet.clone();
					bits.and(i.getMeasureQuery());
					AggregationTreeNode aggregationTreeNode = bucketBitSets.get(bits);
					if(aggregationTreeNode == null) {
						ArrayList<DimensionValue> list = new ArrayList<DimensionValue>();
						for(Integer j : bits)
							list.add(dataLayer.getDimensionValue(j));
						aggregationTreeNode= new AggregationTreeNode(table.getMeasureIndexMap(), aggregationExpressions);
						buckets.add(aggregationTreeNode);
						aggregationTreeNode.setRowDimensionValues(list);
						bucketBitSets.put(bits, aggregationTreeNode);
					}
				}
			}
		} else if(isMeasuresPresent) {
			AggregationTreeNode aggregationTreeNode = new AggregationTreeNode(table.getMeasureIndexMap(), aggregationExpressions);
			buckets.add(aggregationTreeNode);
			aggregationTreeNode.setRowDimensionValues(new ArrayList<DimensionValue>());
			for(DataRow i : table.getRows())
				aggregationTreeNode.aggregate(i.getMeasureValues());
		}
		return buckets;
	}*/
	private ArrayList<AggregationTreeNode> createBucketsO1Bucketing(String tableName, ArrayList<IColumn> columns, IBitSet filterBitSet, String[] aggregationExpressions) {
		Table table = measureHelper.getTable(tableName);
		ArrayList<AggregationTreeNode> buckets = new ArrayList<AggregationTreeNode>();
		
		boolean isDimensionPresent = false;
		boolean isMeasuresPresent = false;
		IBitSet dimensionsPresentInSelectBitSet = BitDekkUtil.newBitSet();
		for(IColumn k : columns) {
			if(k instanceof Dimension) {
				isDimensionPresent = true;
				dimensionsPresentInSelectBitSet.or(dataLayer.getDimensionBitSet(((Dimension) k).getName()));
			} else
				isMeasuresPresent = true;
		}
		IBitSet filterOpenBitSet = filterBitSet;
		IBitSet lastBucket = null;
		AggregationTreeNode lastAggregationTreeNode = null;
		if(isDimensionPresent && isMeasuresPresent) {
			for(DataRow i : table.getRows()) {
				if(filterOpenBitSet.contains(i.getMeasureQuery())) {
					if(lastBucket == null || !i.getMeasureQuery().contains(lastBucket)) {
						lastBucket = dimensionsPresentInSelectBitSet.clone();
						lastBucket.and(i.getMeasureQuery());
						ArrayList<DimensionValue> list = new ArrayList<DimensionValue>();
						for(Integer j : lastBucket)
							list.add(dataLayer.getDimensionValue(j));
						lastAggregationTreeNode = new AggregationTreeNode(table.getMeasureIndexMap(), aggregationExpressions);
						buckets.add(lastAggregationTreeNode);
						lastAggregationTreeNode.setRowDimensionValues(list);
					}
					lastAggregationTreeNode.aggregate(i.getMeasureValues());
				}
			}
		} else if(isDimensionPresent) {
			for(DataRow i : table.getRows()) {
				if(filterOpenBitSet.contains(i.getMeasureQuery())) {
					if(!i.getMeasureQuery().contains(lastBucket)) {
						lastBucket = dimensionsPresentInSelectBitSet.clone();
						lastBucket.and(i.getMeasureQuery());
						ArrayList<DimensionValue> list = new ArrayList<DimensionValue>();
						for(Integer j : lastBucket)
							list.add(dataLayer.getDimensionValue(j));
						lastAggregationTreeNode = new AggregationTreeNode(table.getMeasureIndexMap(), aggregationExpressions);
						buckets.add(lastAggregationTreeNode);
						lastAggregationTreeNode.setRowDimensionValues(list);
					}
				}
			}
		} else if(isMeasuresPresent) {
			AggregationTreeNode aggregationTreeNode = new AggregationTreeNode(table.getMeasureIndexMap(), aggregationExpressions);
			buckets.add(aggregationTreeNode);
			aggregationTreeNode.setRowDimensionValues(new ArrayList<DimensionValue>());
			for(DataRow i : table.getRows())
				aggregationTreeNode.aggregate(i.getMeasureValues());
		}
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