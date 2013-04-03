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
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.bitdekk.DataLayer;
import org.bitdekk.aggregation.IAggregation;
import org.bitdekk.api.IBitSet;
import org.bitdekk.distributed.scenario.helper.DistributedScenarioDimensionValueHelper;
import org.bitdekk.distributed.scenario.helper.DistributedScenarioHelper;
import org.bitdekk.exception.InvalidBitDekkExpressionException;
import org.bitdekk.scenario.ScenarioDataLayer;

import com.google.visualization.datasource.datatable.DataTable;

public class DistributedScenarioDataLayer {
	private ScenarioDataLayer scenarioDataLayer;
	private DataLayer dataLayer;
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
	public DataLayer getDataLayer() {
		return dataLayer;
	}
	public void setDataLayer(DataLayer dataLayer) {
		this.dataLayer = dataLayer;
	}
	/**
	 * @param dimensionMap Map of dimension name and its id
	 */
	public void initializeDimensionValues(HashMap<String, Integer> dimensionMap) {
		dataLayer.initializeDimensionValues(dimensionMap);
	}
	/**
	 * @param tableName a string to uniquely identify this table
	 * @param dataTable <a href='http://gwt-google-apis.googlecode.com/svn/javadoc/visualization/1.1/com/google/gwt/visualization/client/DataTable.html'>Google DataTable</a> 
	 */
	public void initializeTable(String tableName, DataTable dataTable) {
		dataLayer.initializeTable(tableName, dataTable);
	}
	public void initializeTable(String tableName, ResultSet resultSet) throws SQLException {
		dataLayer.initializeTable(tableName, resultSet);
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
		return dataLayer.aggregate(tableName, viewBitSet, filterBitSet, measureExpression);
	}
	/**
	 * @param tableName Name of the table which will be queried
	 * @param viewDimensionValues Array of dimension values for which list of matching measure values will be grouped and aggregated
	 * @param filterDimensionValues Array of dimension values which are filtered in
	 * @param measureExpression any well formed mathematical expression. It may also contain SQL like functions e.g. SUM(MeasureName)
	 * @return aggregated value
	 * @throws InvalidBitDekkExpressionException if there is a parsing error
	 */
	public double aggregate(String tableName, String[] viewDimensionValues, String[] filterDimensionValues, String measureExpression) throws InvalidBitDekkExpressionException {
		return dataLayer.aggregate(tableName, getBitSet(viewDimensionValues), getBitSet(filterDimensionValues), measureExpression);
	}
	/**
	 * @param dimensionValues Array of dimension values
	 * @return A {@link IBitSet} object having those bits set position of which matches with the ids of dimension values 
	 */
	public IBitSet getBitSet(String[] dimensionValues) {
		return dataLayer.getBitSet(dimensionValues);
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
		return dataLayer.aggregate(aggregation, tableName, viewBitSet, filterBitSet, measureNames);
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
	public double aggregate(IAggregation aggregation, String tableName, String[] viewDimensionValues, String[] filterDimensionValues, String[] measureNames) throws InvalidBitDekkExpressionException {
		return dataLayer.aggregate(aggregation, tableName, getBitSet(viewDimensionValues), getBitSet(filterDimensionValues), measureNames);
	}
	/**
	 * @param dimensionValue
	 * @return id of the dimension value
	 */
	public int getDimensionId(String dimensionValue) {
		return dataLayer.getDimensionId(dimensionValue);
	}
	/**
	 * @return set of all the ids
	 */
	public Set<Integer> getDimensionValueIds() {
		return dataLayer.getDimensionValueIds();
	}
	/**
	 * @param id of a dimension value
	 * @return Dimension value
	 */
	public List<Integer> getDimensionValueIds(String dimension) {
		return scenarioDataLayer.getDimensionValueIds(dimension);
	}
	public String getDimensionValue(int id) {
		return dataLayer.getDimensionValue(id);
	}
	public void initializeDimensions(HashMap<String, List<Integer>> dimensionToDimensionValueIdMap) {
		scenarioDataLayer.initializeDimensions(dimensionToDimensionValueIdMap);
	}
	public void createDimensionValue(String dimension, String dimensionValue, int id) {
		distributedScenarioDimensionValueHelper.createDimensionValue(dimension, dimensionValue, id);
	}
	public void deleteDimensionValue(String dimension, String dimensionValue, int id) {
		distributedScenarioDimensionValueHelper.deleteDimensionValue(dimension, dimensionValue, id);
	}
	public void associateRule(int id, IBitSet ruleBitSet, double[] factor) {
		distributedScenarioHelper.associateRule(id, ruleBitSet, factor);
	}
	/**
	 * @param dimension value id
	 * @param ruleBitSet a query which represents a set of rows
	 * @param factor by which the measure values are to be multiplied
	 * @return true if ruleBitSet was found and deleted, false if it was not found
	 */
	public boolean deleteRule(int id, IBitSet ruleBitSet, double[] factor) {
		return distributedScenarioHelper.deleteRule(id, ruleBitSet);
	}
}
