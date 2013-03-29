package org.bitdekk.scenario.helper;

import java.util.ArrayList;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.bitdekk.aggregation.IAggregation;
import org.bitdekk.api.IBitSet;
import org.bitdekk.api.IEvaluation;
import org.bitdekk.helper.MeasureHelper;
import org.bitdekk.helper.expression.BitdekkErrorHandlingLexer;
import org.bitdekk.helper.expression.BitdekkErrorHandlingParser;
import org.bitdekk.helper.expression.model.GroupedMeasureExpression;
import org.bitdekk.helper.expression.model.MeasureExpression;
import org.bitdekk.model.DataRow;
import org.bitdekk.scenario.ScenarioUtil;

public class ScenarioEvaluationHelper implements IEvaluation {
	private MeasureHelper measureHelper;
	private ScenarioDataHelper scenarioDataHelper;
	private DimensionHelper dimensionHelper;
	public DimensionHelper getDimensionHelper() {
		return dimensionHelper;
	}
	public void setDimensionHelper(DimensionHelper dimensionHelper) {
		this.dimensionHelper = dimensionHelper;
	}
	public ScenarioDataHelper getScenarioDataHelper() {
		return scenarioDataHelper;
	}
	public void setScenarioDataHelper(ScenarioDataHelper scenarioDataHelper) {
		this.scenarioDataHelper = scenarioDataHelper;
	}
	public MeasureHelper getMeasureHelper() {
		return measureHelper;
	}
	public void setMeasureHelper(MeasureHelper measureHelper) {
		this.measureHelper = measureHelper;
	}
	@Override
	public double getMeasureExpressionValue(ArrayList<Object> measureExpressionTokens, int pos, DataRow row, String tableName, IBitSet viewBitSet
			, IBitSet filterBitSet) {
		if(measureExpressionTokens.get(pos).equals("+"))
			return add(measureExpressionTokens, pos + 1, row, tableName, viewBitSet, filterBitSet);
		else if(measureExpressionTokens.get(pos).equals("-"))
			return subtract(measureExpressionTokens, ++pos, row, tableName, viewBitSet, filterBitSet);
		else if(measureExpressionTokens.get(pos).equals("*"))
			return multiply(measureExpressionTokens, ++pos, row, tableName, viewBitSet, filterBitSet);
		else if(measureExpressionTokens.get(pos).equals("/"))
			return divide(measureExpressionTokens, ++pos, row, tableName, viewBitSet, filterBitSet);
		else if(measureExpressionTokens.get(pos) instanceof Double)
			return (Double)measureExpressionTokens.get(pos);
		else if(measureExpressionTokens.get(pos) instanceof IAggregation)
			return aggregate(((IAggregation)measureExpressionTokens.get(pos)), ((IAggregation)measureExpressionTokens.get(pos)).getMeasureExpression(), tableName, viewBitSet, filterBitSet);
		else
			return row.getMeasureValues()[measureHelper.getTable(tableName).getMeasureIndexMap().get(measureExpressionTokens.get(pos))];
	}
	private double aggregate(IAggregation aggregation, MeasureExpression measureExpression, String tableName, IBitSet viewBitSet, IBitSet filterBitSet) {
		ArrayList<IBitSet> list = new ArrayList<IBitSet>();
		for(Integer i : ScenarioUtil.pi(viewBitSet, scenarioDataHelper)) {
			for(IBitSet key : scenarioDataHelper.getQueryMap(i).keySet()) {
				IBitSet clone = viewBitSet.clone();
				clone.and(key);
				if(!ScenarioUtil.containsAllDimensions(clone, dimensionHelper, scenarioDataHelper))
					list.addAll(scenarioDataHelper.getQueryMap(i).get(key));
			}
		}
		
		for(DataRow i : measureHelper.getTable(tableName).getRows()) {
			if(filterBitSet.contains(i.getMeasureQuery()) && i.getMeasureQuery().contains(viewBitSet))
				aggregation.aggregate(getMeasureExpressionValue(measureExpression.getTokens(), 0, i, tableName, viewBitSet, filterBitSet));
			for(int j = 0; j < list.size(); j++)
				if(list.get(j).contains(i.getMeasureQuery()))
					aggregation.aggregate(getMeasureExpressionValue(measureExpression.getTokens(), 0, i, tableName, viewBitSet, filterBitSet));
		}
		return aggregation.getValue();
	}
	private double divide(ArrayList<Object> measureExpressionTokens, int pos, DataRow row, String tableName, IBitSet viewBitSet, IBitSet filterBitSet) {
		return getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet) 
				/ getMeasureExpressionValue(measureExpressionTokens, pos + 1, row, tableName, viewBitSet, filterBitSet);
	}
	private double multiply(ArrayList<Object> measureExpressionTokens, int pos, DataRow row, String tableName, IBitSet viewBitSet, IBitSet filterBitSet) {
		return getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet) 
				* getMeasureExpressionValue(measureExpressionTokens, pos + 1, row, tableName, viewBitSet, filterBitSet);
	}
	private double subtract(ArrayList<Object> measureExpressionTokens, int pos, DataRow row, String tableName, IBitSet viewBitSet, IBitSet filterBitSet) {
		return getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet) 
				- getMeasureExpressionValue(measureExpressionTokens, pos + 1, row, tableName, viewBitSet, filterBitSet);
	}
	private double add(ArrayList<Object> measureExpressionTokens, int pos, DataRow row, String tableName, IBitSet viewBitSet, IBitSet filterBitSet) {
		return getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet) 
				+ getMeasureExpressionValue(measureExpressionTokens, pos + 1, row, tableName, viewBitSet, filterBitSet);
	}
	@Override
	public BitdekkErrorHandlingLexer getLexer(String measureExpression) {
		return new BitdekkErrorHandlingLexer(new ANTLRStringStream(measureExpression));
	}
	@Override
	public void parse(CommonTokenStream tokens, GroupedMeasureExpression gme) throws RecognitionException {
		new BitdekkErrorHandlingParser(tokens, gme).stat();
	}
}