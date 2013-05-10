/*
 * Copyright 2013 Contributors of bit-dekk
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.bitdekk.scenario.helper;

import java.util.ArrayList;
import java.util.Set;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.bitdekk.aggregation.IAggregation;
import org.bitdekk.api.IBitSet;
import org.bitdekk.api.IEvaluation;
import org.bitdekk.helper.MeasureHelper;
import org.bitdekk.helper.Position;
import org.bitdekk.helper.expression.BitdekkErrorHandlingLexer;
import org.bitdekk.helper.expression.BitdekkErrorHandlingParser;
import org.bitdekk.helper.expression.model.GroupedMeasureExpression;
import org.bitdekk.helper.expression.model.MeasureExpression;
import org.bitdekk.model.DataRow;
import org.bitdekk.scenario.ScenarioUtil;
import org.bitdekk.scenario.model.ScenarioRowQuery;

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
	public double getMeasureExpressionValue(ArrayList<Object> measureExpressionTokens, Position pos, DataRow row, String tableName, IBitSet viewBitSet
			, IBitSet filterBitSet) {
		return getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet, null);
	}
	private double getMeasureExpressionValue(ArrayList<Object> measureExpressionTokens, Position pos, DataRow row, String tableName, IBitSet viewBitSet
			, IBitSet filterBitSet, double[] factors) {
		if(measureExpressionTokens.get(pos.pos).equals("+")) {
			++pos.pos;
			return add(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet, factors);
		} else if(measureExpressionTokens.get(pos.pos).equals("-")) {
			++pos.pos;
			return subtract(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet, factors);
		} else if(measureExpressionTokens.get(pos.pos).equals("*"))  {
			++pos.pos;
			return multiply(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet, factors);
		} else if(measureExpressionTokens.get(pos.pos).equals("/")) {
			++pos.pos;
			return divide(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet, factors);
		} else if(measureExpressionTokens.get(pos.pos) instanceof Double)
			return (Double)measureExpressionTokens.get(pos.pos);
		else if(measureExpressionTokens.get(pos.pos) instanceof IAggregation)
			return aggregate(((IAggregation)measureExpressionTokens.get(pos.pos)), ((IAggregation)measureExpressionTokens.get(pos.pos)).getMeasureExpression(), tableName
				, viewBitSet, filterBitSet, factors);
		else if (factors != null){
			Integer measureNumber = measureHelper.getTable(tableName).getMeasureIndexMap().get(measureExpressionTokens.get(pos.pos));
			return row.getMeasureValues()[measureNumber] * factors[measureNumber];
		} else
			return row.getMeasureValues()[measureHelper.getTable(tableName).getMeasureIndexMap().get(measureExpressionTokens.get(pos.pos))];
	}
	private double aggregate(IAggregation aggregation, MeasureExpression measureExpression, String tableName, IBitSet viewBitSet, IBitSet filterBitSet, double[] factors) {
		class D {
			IBitSet key;
			ArrayList<ScenarioRowQuery> list = new ArrayList<ScenarioRowQuery>();
			public D(IBitSet key, ArrayList<ScenarioRowQuery> list) {
				this.key = key;
				this.list = list;
			}
		}
		IBitSet viewBitSetClone = viewBitSet.clone();
		ArrayList<D> list  = new ArrayList<D>();
		if(filterBitSet.contains(viewBitSet)) {
			Set<Integer> scenarioSet = ScenarioUtil.pi(viewBitSet, scenarioDataHelper);
			for(Integer i : scenarioSet) {
				IBitSet clone = viewBitSet.clone();
				clone.clear(i);
				viewBitSetClone.clear(i);
				for(IBitSet key : scenarioDataHelper.getQueryMap(i).keySet()) {
					if(filterBitSet.contains(key) && key.contains(clone)) {
						IBitSet intersectedFilter = filterBitSet.clone();
						intersectedFilter.and(key);
						getRealQuery(scenarioDataHelper.getScenrios(), intersectedFilter);
						list.add(new D(intersectedFilter, scenarioDataHelper.getQueryMap(i).get(key)));
					}
				}
			}
		}
		for(DataRow i : measureHelper.getTable(tableName).getRows()) {
			if(filterBitSet.contains(i.getMeasureQuery()) && i.getMeasureQuery().contains(viewBitSet))
				aggregation.aggregate(getMeasureExpressionValue(measureExpression.getTokens(), new Position(), i, tableName, viewBitSet, filterBitSet));
			for(int j = 0; j < list.size(); j++)
				if(list.get(j).key.contains(i.getMeasureQuery()))
					for(ScenarioRowQuery k : list.get(j).list)
						if(k.getQuery().contains(i.getMeasureQuery()) && i.getMeasureQuery().contains(viewBitSetClone))
							aggregation.aggregate(getMeasureExpressionValue(measureExpression.getTokens(), new Position(), i, tableName, viewBitSet, filterBitSet
									, k.getFactor()));
		}
		return aggregation.getValue();
	}
	private double divide(ArrayList<Object> measureExpressionTokens, Position pos, DataRow row, String tableName, IBitSet viewBitSet, IBitSet filterBitSet, double[] factors) {
		double v1 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet, factors);
		++pos.pos;
		double v2 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet, factors);
		return v1 / v2;
	}
	private double multiply(ArrayList<Object> measureExpressionTokens, Position pos, DataRow row, String tableName, IBitSet viewBitSet, IBitSet filterBitSet, double[] factors) {
		double v1 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet, factors);
		++pos.pos;
		double v2 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet, factors);
		return v1 * v2;
	}
	private double subtract(ArrayList<Object> measureExpressionTokens, Position pos, DataRow row, String tableName, IBitSet viewBitSet, IBitSet filterBitSet, double[] factors) {
		double v1 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet, factors);
		++pos.pos;
		double v2 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet, factors);
		return v1 - v2;
	}
	private double add(ArrayList<Object> measureExpressionTokens, Position pos, DataRow row, String tableName, IBitSet viewBitSet, IBitSet filterBitSet, double[] factors) {
		double v1 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet, factors);
		++pos.pos;
		double v2 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, viewBitSet, filterBitSet, factors);
		return v1 + v2;
	}
	private void getRealQuery(Set<Integer> scenarioSet, IBitSet q) {
		for(Integer i : scenarioSet)
			if(q.get(i))
				q.or(dimensionHelper.getDimensionValuesBitSet(dimensionHelper.getDimension(i)));
		for(Integer i : scenarioSet)
			q.clear(i);
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