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
package org.bitdekk.distributed.scenario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.bitdekk.aggregation.IAggregation;
import org.bitdekk.api.IBitSet;
import org.bitdekk.distributed.scenario.helper.DistributedScenarioDimensionValueHelper;
import org.bitdekk.distributed.scenario.helper.DistributedScenarioHelper;
import org.bitdekk.exception.InvalidBitDekkExpressionException;
import org.bitdekk.model.DimensionValue;
import org.bitdekk.scenario.ScenarioDataLayer;

import com.google.visualization.datasource.datatable.DataTable;

public class DistributedScenarioDataLayer {
	private ScenarioDataLayer scenarioDataLayer;
	private DistributedScenarioDimensionValueHelper distributedScenarioDimensionValueHelper;
	private DistributedScenarioHelper distributedScenarioHelper;
	public DistributedScenarioHelper getDistributedScenarioHelper() {
		return distributedScenarioHelper;
	}
	public void setDistributedScenarioHelper(
			DistributedScenarioHelper distributedScenarioHelper) {
		this.distributedScenarioHelper = distributedScenarioHelper;
	}
	public DistributedScenarioDimensionValueHelper getDistributedScenarioDimensionValueHelper() {
		return distributedScenarioDimensionValueHelper;
	}
	public void setDistributedScenarioDimensionValueHelper(
			DistributedScenarioDimensionValueHelper distributedScenarioDimensionValueHelper) {
		this.distributedScenarioDimensionValueHelper = distributedScenarioDimensionValueHelper;
	}
	public ScenarioDataLayer getScenarioDataLayer() {
		return scenarioDataLayer;
	}
	public void setScenarioDataLayer(ScenarioDataLayer scenarioDataLayer) {
		this.scenarioDataLayer = scenarioDataLayer;
	}
	/**
	 * @param tableName a string to uniquely identify this table
	 * @param dataTable <a href='http://gwt-google-apis.googlecode.com/svn/javadoc/visualization/1.1/com/google/gwt/visualization/client/DataTable.html'>Google DataTable</a> 
	 */
	public void initializeTable(String tableName, DataTable dataTable) {
		scenarioDataLayer.initializeTable(tableName, dataTable);
	}
	public void initializeTable(String tableName, ResultSet resultSet) throws SQLException {
		scenarioDataLayer.initializeTable(tableName, resultSet);
	}
	/**
	 * 
	 * @param tableName Name of the table which will be queried
	 * @param viewBitSet A {@link IBitSet} implementation having those bits set who's position matches with the ids of dimension values for which 
	 * list of matching measure values will be grouped and aggregated
	 * @param filterBitSet A {@link IBitSet} implementation having those bits set position of which matches with the ids of dimension values filtered in.
	 * @param measureExpression Accepts any well formed mathematical expression. It may also contain SQL like functions e.g. SUM(MeasureName)
	 * @return aggregated value
	 * @throws InvalidBitDekkExpressionException if there is a parsing error
	 */
	public double aggregate(String tableName, IBitSet viewBitSet, IBitSet filterBitSet, String measureExpression) throws InvalidBitDekkExpressionException {
		return scenarioDataLayer.aggregate(tableName, viewBitSet, filterBitSet, measureExpression);
	}
	/**
	 * @param tableName Name of the table which will be queried
	 * @param viewDimensionValues Array of dimension values for which list of matching measure values will be grouped and aggregated
	 * @param filterDimensionValues Array of dimension values which are filtered in
	 * @param measureExpression any well formed mathematical expression. It may also contain SQL like functions e.g. SUM(MeasureName)
	 * @return aggregated value
	 * @throws InvalidBitDekkExpressionException if there is a parsing error
	 */
	public double aggregate(String tableName, DimensionValue[] viewDimensionValues, DimensionValue[] filterDimensionValues, String measureExpression) throws InvalidBitDekkExpressionException {
		return scenarioDataLayer.aggregate(tableName, getBitSet(viewDimensionValues), getBitSet(filterDimensionValues), measureExpression);
	}
	/**
	 * @param dimensionValues Array of dimension values
	 * @return A {@link IBitSet} object having those bits set position of which matches with the ids of dimension values 
	 */
	public IBitSet getBitSet(DimensionValue[] dimensionValues) {
		return scenarioDataLayer.getBitSet(dimensionValues);
	}
	/**
	 * @param aggregation custom aggregation
	 * @param viewBitSet A {@link IBitSet} implementation having those bits set who's position matches with the ids of dimension values for which 
	 * list of matching measure values will be grouped and aggregated
	 * @param filterBitSet A {@link IBitSet} implementation having those bits set position of which matches with the ids of dimension values filtered in.
	 * @param measureNames array of measureNames
	 * @return aggregated value
	 * @throws InvalidBitDekkExpressionException
	 */
	public double aggregate(IAggregation aggregation, String tableName, IBitSet viewBitSet, IBitSet filterBitSet, String[] measureNames) throws InvalidBitDekkExpressionException {
		return scenarioDataLayer.aggregate(aggregation, tableName, viewBitSet, filterBitSet, measureNames);
	}
	/**
	 * @param aggregation
	  * @param tableName Name of the table which will be queried
	 * @param viewDimensionValues Array of dimension values for which list of matching measure values will be grouped and aggregated
	 * @param filterDimensionValues Array of dimension values which are filtered in
	 * @param measureNames array of measureNames
	 * @return aggregated value
	 * @throws InvalidBitDekkExpressionException
	 */
	public double aggregate(IAggregation aggregation, String tableName, DimensionValue[] viewDimensionValues, DimensionValue[] filterDimensionValues, String[] measureNames) throws InvalidBitDekkExpressionException {
		return scenarioDataLayer.aggregate(aggregation, tableName, getBitSet(viewDimensionValues), getBitSet(filterDimensionValues), measureNames);
	}
	/**
	 * @param dimensionValue
	 * @return id of the dimension value
	 */
	public int getDimensionId(String dimension, String dimensionValue) {
		return scenarioDataLayer.getDimensionId(dimension, dimensionValue);
	}
	/**
	 * @return set of all the ids
	 */
	public Set<Integer> getDimensionValueIds() {
		return scenarioDataLayer.getDimensionValueIds();
	}
	/**
	 * @param id of a dimension value
	 * @return Dimension value
	 */
	public List<Integer> getDimensionValueIds(String dimension) {
		return scenarioDataLayer.getDimensionValueIds(dimension);
	}
	public DimensionValue getDimensionValue(int id) {
		return scenarioDataLayer.getDimensionValue(id);
	}
	public void createDimensionValue(String dimension, String dimensionValue) {
		distributedScenarioDimensionValueHelper.createDimensionValue(dimension, dimensionValue);
	}
	public boolean deleteDimensionValue(String dimension, String dimensionValue) {
		return distributedScenarioDimensionValueHelper.deleteDimensionValue(dimension, dimensionValue);
	}
	public void associateRule(String dimension, String scenarioDimensionvalue, IBitSet ruleBitSet, double[] factor) {
		distributedScenarioHelper.associateRule(dimension, scenarioDimensionvalue, ruleBitSet, factor);
	}
	/**
	 * @param dimension value id
	 * @param ruleBitSet a query which represents a set of rows
	 * @param factor by which the measure values are to be multiplied
	 * @return true if ruleBitSet was found and deleted, false if it was not found
	 */
	public boolean deleteRule(String dimension, String dimensionValue, IBitSet ruleBitSet) {
		return distributedScenarioHelper.deleteRule(dimension, dimensionValue, getDimensionId(dimension, dimensionValue), ruleBitSet);
	}
}
