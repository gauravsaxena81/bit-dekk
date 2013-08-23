package org.bitdekk.helper.sql.grammar.model;

import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.bitdekk.api.IBitSet;
import org.bitdekk.exception.InvalidBitDekkExpressionException;
import org.bitdekk.helper.Position;
import org.bitdekk.helper.expression.model.GroupedMeasureExpression;
import org.bitdekk.helper.sql.grammar.BucketMeasureEvaluator;
import org.bitdekk.model.DimensionValue;
import org.bitdekk.util.BitDekkUtil.OPERATORS;

public class AggregationTreeNode implements ITreeNode<double[]> {

	/*private String[] aggregationExpressions;*/
	private HashMap<String, Integer> measureIndexMap;//Map of measure name to its index in table
	private ArrayList<DimensionValue> rowDimensionValues;
	private Object[][] groupedTokenArray;
	private OPERATORS[][] operatorsArray;
	private BucketMeasureEvaluator bucketMeasureEvaluator = new BucketMeasureEvaluator();
	private double values[];

	public AggregationTreeNode(HashMap<String, Integer> measureIndexMap, String[] aggregationExpressions) {
		this.measureIndexMap = measureIndexMap;
		this.groupedTokenArray = new Object[aggregationExpressions.length][];
		this.operatorsArray= new OPERATORS[aggregationExpressions.length][];
		this.values = new double[aggregationExpressions.length];
		
		for(int i = 0; i < aggregationExpressions.length; i++) {
			GroupedMeasureExpression groupedMeasureExpression = new GroupedMeasureExpression();
			CommonTokenStream tokens = new CommonTokenStream();
			tokens.setTokenSource(bucketMeasureEvaluator.getLexer(aggregationExpressions[i]));
			try {
				bucketMeasureEvaluator.parse(tokens, groupedMeasureExpression);
				groupedTokenArray[i] = groupedMeasureExpression.getGroupedTokenArray();
				operatorsArray[i] = groupedMeasureExpression.getOperators();
			} catch (RecognitionException e) {
				e.printStackTrace();
				throw new InvalidBitDekkExpressionException("Invalid token " + e.token + " found at " + e.line + "," + e.charPositionInLine);
			}
		}
	}
	@Override
	public IBitSet childrenBitset() {
		return null;
	}
	public void aggregate(double[] rowMeasureValues) {
		for(int i = 0; i < groupedTokenArray.length; i++)
			values[i] = bucketMeasureEvaluator.getMeasureExpressionValue(operatorsArray[i], groupedTokenArray[i], new Position(), rowMeasureValues, measureIndexMap);
		//rows.add(rowMeasureValues);
	}
	@Override
	public double[] getNodeObject() {
		/*BucketMeasureEvaluator bucketMeasureEvaluator = new BucketMeasureEvaluator();
		double[] values = new double[aggregationExpressions.length];
		for(int i = 0; i < aggregationExpressions.length; i++)
			values[i] = getExpressionValue(bucketMeasureEvaluator, aggregationExpressions[i]);*/
		return values;
	}
	/*private double getExpressionValue(BucketMeasureEvaluator bucketMeasureEvaluator, String expression) {
		GroupedMeasureExpression gme = new GroupedMeasureExpression();
		CommonTokenStream tokens = new CommonTokenStream();
		tokens.setTokenSource(bucketMeasureEvaluator.getLexer(expression));
		try {
			bucketMeasureEvaluator.parse(tokens, gme);
			return bucketMeasureEvaluator.getMeasureExpressionValue(gme.getGroupedTokens(), new Position(), null, measureIndexMap);
		} catch (RecognitionException e) {
			e.printStackTrace();
			throw new InvalidBitDekkExpressionException("Invalid token " + e.token + " found at " + e.line + "," + e.charPositionInLine);
		}		
	}*/
	/*@Override
	public int hashCode() {
		return rows;
	}
	@Override
	public boolean equals(Object o) {
		if(o != null && o instanceof DimensionValueTreeNode)
			return ((DimensionValueTreeNode)o).dimensionValueId == this.dimensionValueId;
		else
			return false;
	}*/
	public ArrayList<DimensionValue> getRowDimensionValues() {
		return rowDimensionValues;
	}
	public void setRowDimensionValues(ArrayList<DimensionValue> rowDimensionValues) {
		this.rowDimensionValues = rowDimensionValues;
	}
}
