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
package org.bitdekk;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import org.bitdekk.aggregation.IAggregation;
import org.bitdekk.api.IBitSet;
import org.bitdekk.exception.InvalidBitDekkExpressionException;
import org.bitdekk.helper.AggregationHelper;
import org.bitdekk.helper.DimensionValueHelper;
import org.bitdekk.helper.MeasureHelper;
import org.bitdekk.helper.SelectHelper;
import org.bitdekk.model.DimensionValue;

import com.google.visualization.datasource.datatable.DataTable;


public class DataLayer {
	private DimensionValueHelper dimensionValueHelper;
	private MeasureHelper measureHelper;
	private AggregationHelper aggregationHelper;
	private SelectHelper selectHelper;
	
	public SelectHelper getSelectHelper() {
		return selectHelper;
	}
	public void setSelectHelper(SelectHelper selectHelper) {
		this.selectHelper = selectHelper;
	}
	public DimensionValueHelper getDimensionValueHelper() {
		return dimensionValueHelper;
	}
	public void setDimensionValueHelper(DimensionValueHelper dimensionValueHelper) {
		this.dimensionValueHelper = dimensionValueHelper;
	}
	public MeasureHelper getMeasureHelper() {
		return measureHelper;
	}
	public void setMeasureHelper(MeasureHelper measureHelper) {
		this.measureHelper = measureHelper;
	}
	public AggregationHelper getAggregationHelper() {
		return aggregationHelper;
	}
	public void setAggregationHelper(AggregationHelper aggregationHelper) {
		this.aggregationHelper = aggregationHelper;
	}
	/**
	 * @param tableName a string to uniquely identify this table
	 * @param dataTable <a href='http://gwt-google-apis.googlecode.com/svn/javadoc/visualization/1.1/com/google/gwt/visualization/client/DataTable.html'>Google DataTable</a> 
	 */
	public void initializeTable(String tableName, DataTable dataTable) {
		dimensionValueHelper.initializeDimensionValues(dataTable);
		measureHelper.intializeTable(tableName, dataTable);
	}
	public void initializeTable(String tableName, ResultSet resultSet) throws SQLException {
		measureHelper.intializeTable(tableName, resultSet);
	}
	public Set<String> getDimensions(String tableName) {
		return measureHelper.getTable(tableName).getMeasureIndexMap().keySet();
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
		return aggregationHelper.aggregate(tableName, viewBitSet, filterBitSet, measureExpression);
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
		return aggregationHelper.aggregate(tableName, getBitSet(viewDimensionValues), getBitSet(filterDimensionValues), measureExpression);
	}
	/**
	 * @param dimensionValues Array of dimension values
	 * @return A {@link IBitSet} object having those bits set position of which matches with the ids of dimension values 
	 */
	public IBitSet getBitSet(DimensionValue[] dimensionValues) {
		return dimensionValueHelper.getBitSet(dimensionValues);
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
		return aggregationHelper.aggregate(aggregation, measureHelper.getTable(tableName), viewBitSet, filterBitSet, measureNames);
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
		return aggregationHelper.aggregate(aggregation, measureHelper.getTable(tableName), getBitSet(viewDimensionValues), getBitSet(filterDimensionValues), measureNames);
	}
	/**
	 * @param dimensionValue
	 * @return id of the dimension value
	 */
	public int getDimensionId(String dimension, String dimensionValue) {
		return dimensionValueHelper.getId(dimension, dimensionValue);
	}
	/**
	 * @param id of a dimension value
	 * @return Dimension value
	 */
	public DimensionValue getDimensionValue(int id) {
		return dimensionValueHelper.getDimensionValue(id);
	}
	public DataTable select(String tableName, IBitSet filterbitSet, String... columnNames) {
		return selectHelper.select(tableName, filterbitSet, columnNames);
	}
	public DataTable select(String tableName, DimensionValue[] filterDimensionValues, String... columnNames) {
		return selectHelper.select(tableName, getBitSet(filterDimensionValues), columnNames);
	}
	public Set<Integer> getDimensionValueIds() {
		return dimensionValueHelper.getDimensionValueIds();
	}
}