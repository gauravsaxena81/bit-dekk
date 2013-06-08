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
import org.bitdekk.helper.DataHelper;
import org.bitdekk.helper.DimensionHelper;
import org.bitdekk.helper.MeasureHelper;
import org.bitdekk.helper.Position;
import org.bitdekk.helper.expression.BitdekkErrorHandlingLexer;
import org.bitdekk.helper.expression.BitdekkErrorHandlingParser;
import org.bitdekk.helper.expression.model.GroupedMeasureExpression;
import org.bitdekk.helper.expression.model.MeasureExpression;
import org.bitdekk.model.DataRow;
import org.bitdekk.scenario.ScenarioUtil;
import org.bitdekk.scenario.model.ScenarioRowQuery;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.ValueType;

public class ScenarioEvaluationHelper implements IEvaluation {
	private MeasureHelper measureHelper;
	private ScenarioDataHelper scenarioDataHelper;
	private DataHelper dataHelper;
	private DimensionHelper dimensionHelper;
	private ScenarioDimensionValueHelper scenarioDimensionValueHelper;
	public DataHelper getDataHelper() {
		return dataHelper;
	}
	public void setDataHelper(DataHelper dataHelper) {
		this.dataHelper = dataHelper;
	}
	public ScenarioDimensionValueHelper getScenarioDimensionValueHelper() {
		return scenarioDimensionValueHelper;
	}
	public void setScenarioDimensionValueHelper(ScenarioDimensionValueHelper scenarioDimensionValueHelper) {
		this.scenarioDimensionValueHelper = scenarioDimensionValueHelper;
	}
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
	public double getMeasureExpressionValue(ArrayList<Object> measureExpressionTokens, Position pos, DataRow row, String tableName, IBitSet unifiedQuery) {
		return getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, unifiedQuery, null);
	}
	private double getMeasureExpressionValue(ArrayList<Object> measureExpressionTokens, Position pos, DataRow row, String tableName, IBitSet unifiedQuery, double[] factors) {
		if(measureExpressionTokens.get(pos.pos).equals("+")) {
			++pos.pos;
			return add(measureExpressionTokens, pos, row, tableName, unifiedQuery, factors);
		} else if(measureExpressionTokens.get(pos.pos).equals("-")) {
			++pos.pos;
			return subtract(measureExpressionTokens, pos, row, tableName, unifiedQuery, factors);
		} else if(measureExpressionTokens.get(pos.pos).equals("*"))  {
			++pos.pos;
			return multiply(measureExpressionTokens, pos, row, tableName, unifiedQuery, factors);
		} else if(measureExpressionTokens.get(pos.pos).equals("/")) {
			++pos.pos;
			return divide(measureExpressionTokens, pos, row, tableName, unifiedQuery, factors);
		} else if(measureExpressionTokens.get(pos.pos) instanceof Double)
			return (Double)measureExpressionTokens.get(pos.pos);
		else if(measureExpressionTokens.get(pos.pos) instanceof IAggregation)
			return aggregate(((IAggregation)measureExpressionTokens.get(pos.pos)), ((IAggregation)measureExpressionTokens.get(pos.pos)).getMeasureExpression(), tableName
				, unifiedQuery, factors);
		else if (factors != null){
			Integer measureNumber = measureHelper.getTable(tableName).getMeasureIndexMap().get(measureExpressionTokens.get(pos.pos));
			return row.getMeasureValues()[measureNumber] * factors[measureNumber];
		} else
			return row.getMeasureValues()[measureHelper.getTable(tableName).getMeasureIndexMap().get(measureExpressionTokens.get(pos.pos))];
	}
	/*private double aggregate1(IAggregation aggregation, MeasureExpression measureExpression, String tableName, IBitSet unifiedQuery, double[] factors) {
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
						getRealQuery(scenarioDataHelper.getScenarios(), intersectedFilter);
						list.add(new D(intersectedFilter, scenarioDataHelper.getQueryMap(i).get(key)));
					}
				}
			}
		}
		for(DataRow i : measureHelper.getTable(tableName).getRows()) {
			if(filterBitSet.contains(i.getMeasureQuery()) && i.getMeasureQuery().contains(viewBitSet))
				aggregation.aggregate(getMeasureExpressionValue(measureExpression.getTokens(), new Position(), i, tableName, unifiedQuery));
			for(int j = 0; j < list.size(); j++)
				if(list.get(j).key.contains(i.getMeasureQuery()))
					for(ScenarioRowQuery k : list.get(j).list)
						if(k.getQuery().contains(i.getMeasureQuery()) && i.getMeasureQuery().contains(viewBitSetClone))
							aggregation.aggregate(getMeasureExpressionValue(measureExpression.getTokens(), new Position(), i, tableName, unifiedQuery
									, k.getFactor()));
		}
		return aggregation.getValue();
	}*/
	private double aggregate(IAggregation aggregation, MeasureExpression measureExpression, String tableName, IBitSet unifiedQuery, double[] factors) {
		Set<Integer> scenarioSet = ScenarioUtil.pi(unifiedQuery, scenarioDataHelper);
		if(scenarioSet.isEmpty()) {
			for(DataRow j : measureHelper.getTable(tableName).getRows())
				if(unifiedQuery.contains(j.getMeasureQuery()))
					aggregation.aggregate(getMeasureExpressionValue(measureExpression.getTokens(), new Position(), j, tableName, unifiedQuery));
		} else {
			for(Integer i : scenarioSet) {
				for(IBitSet key : scenarioDataHelper.getQueryMap(i).keySet()) {
					IBitSet intersectedFilter = unifiedQuery.clone();
					intersectedFilter.and(key);
					getRealQuery(scenarioDataHelper.getScenarios(), intersectedFilter);
					if(ScenarioUtil.containsAllDimensions(intersectedFilter, dimensionHelper, dataHelper)) {
						for(DataRow j : measureHelper.getTable(tableName).getRows()) {
							if(unifiedQuery.contains(j.getMeasureQuery()))
								aggregation.aggregate(getMeasureExpressionValue(measureExpression.getTokens(), new Position(), j, tableName, unifiedQuery));
							for(ScenarioRowQuery k : scenarioDataHelper.getQueryMap(i).get(key))
								if(intersectedFilter.contains(j.getMeasureQuery()) && k.getQuery().contains(j.getMeasureQuery()))
									aggregation.aggregate(getMeasureExpressionValue(measureExpression.getTokens(), new Position(), j, tableName, unifiedQuery
											, k.getFactor()));
						}
					}
				}
			}
		}
		return aggregation.getValue();
	}
	private double divide(ArrayList<Object> measureExpressionTokens, Position pos, DataRow row, String tableName, IBitSet unifiedQuery, double[] factors) {
		double v1 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, unifiedQuery, factors);
		++pos.pos;
		double v2 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, unifiedQuery, factors);
		return v1 / v2;
	}
	private double multiply(ArrayList<Object> measureExpressionTokens, Position pos, DataRow row, String tableName, IBitSet unifiedQuery, double[] factors) {
		double v1 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, unifiedQuery, factors);
		++pos.pos;
		double v2 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, unifiedQuery, factors);
		return v1 * v2;
	}
	private double subtract(ArrayList<Object> measureExpressionTokens, Position pos, DataRow row, String tableName, IBitSet unifiedQuery, double[] factors) {
		double v1 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, unifiedQuery, factors);
		++pos.pos;
		double v2 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, unifiedQuery, factors);
		return v1 - v2;
	}
	private double add(ArrayList<Object> measureExpressionTokens, Position pos, DataRow row, String tableName, IBitSet unifiedQuery, double[] factors) {
		double v1 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, unifiedQuery, factors);
		++pos.pos;
		double v2 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, unifiedQuery, factors);
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
	@Override
	public DataTable select(String tableName, IBitSet filterBitSet, String... columnNames) throws TypeMismatchException {
		class D {
			IBitSet key;
			ArrayList<ScenarioRowQuery> list = new ArrayList<ScenarioRowQuery>();
			int scenarioDimensionValue;
			private IBitSet origkey;
			public D(int scenarioDimensionValue, IBitSet origkey, IBitSet key, ArrayList<ScenarioRowQuery> list) {
				this.scenarioDimensionValue = scenarioDimensionValue;
				this.origkey = origkey;
				this.key = key;
				this.list = list;
			}
		}
		DataTable dataTable = new DataTable();
		for(String i : columnNames)
			if(measureHelper.getTable(tableName).getDimensionIndexMap().get(i) != null)
				dataTable.addColumn(new ColumnDescription(i, ValueType.TEXT, i));
			else if(measureHelper.getTable(tableName).getMeasureIndexMap().get(i) != null)
				dataTable.addColumn(new ColumnDescription(i, ValueType.NUMBER, i));
			else
				throw new IllegalArgumentException("Column Name " + i + "not found in table " + tableName);
			
		ArrayList<D> list  = new ArrayList<D>();
		for(Integer i : ScenarioUtil.pi(filterBitSet, scenarioDataHelper)) {
			for(IBitSet key : scenarioDataHelper.getQueryMap(i).keySet()) {
				if(filterBitSet.contains(key)) {
					IBitSet intersectedFilter = filterBitSet.clone();
					intersectedFilter.and(key);
					getRealQuery(scenarioDataHelper.getScenarios(), intersectedFilter);
					list.add(new D(i, key, intersectedFilter, scenarioDataHelper.getQueryMap(i).get(key)));
				}
			}
		}
		for(DataRow i : measureHelper.getTable(tableName).getRows()) {
			if(filterBitSet.contains(i.getMeasureQuery()))
				accumulator(dataTable, i.getMeasureQuery(), i.getMeasureValues(), tableName, columnNames);
			for(int j = 0; j < list.size(); j++) {
				if(list.get(j).key.contains(i.getMeasureQuery())) {
					for(ScenarioRowQuery k : list.get(j).list) {
						IBitSet clone = k.getQuery().clone();
						if(k.getQuery().contains(i.getMeasureQuery())) {
							for(Integer w : ScenarioUtil.pi(list.get(j).origkey, scenarioDataHelper))
								clone = f(clone, w);
							accumulator(dataTable, f(clone, list.get(j).scenarioDimensionValue), p(i.getMeasureValues(), k.getFactor()), tableName, columnNames);
						}
					}
				}
			}
		}
		return dataTable;
	}
	private void accumulator(DataTable dataTable, IBitSet measureQuery, double[] measureValues, String tableName, String[] columnNames) throws TypeMismatchException {
		TableRow row = new TableRow();
		for(ColumnDescription i : dataTable.getColumnDescriptions()) {
			if(i.getType().equals(ValueType.TEXT)) {
				IBitSet dimensionValuesBitSet = dimensionHelper.getDimensionValuesBitSet(i.getId());
				dimensionValuesBitSet.and(measureQuery);
				row.addCell(scenarioDimensionValueHelper.getDimensionValue(dimensionValuesBitSet.iterator().next()).getDimensionValue());
			} else
				row.addCell(measureValues[measureHelper.getTable(tableName).getMeasureIndexMap().get(i.getId())]);
		}
		dataTable.addRow(row);
	}
	private IBitSet f(IBitSet query, Integer w) {
		IBitSet clone = query.clone();
		clone.andNot(dimensionHelper.getDimensionValuesBitSet(dimensionHelper.getDimension(w)));
		clone.set(w);
		return clone;
	}
	private double[] p(double[] measureValues, double[] factor) {
		double[] r = new double[measureValues.length];
		for(int i = 0; i < r.length; i++)
			r[i] = measureValues[i] * factor[i];
		return r;
	}
}