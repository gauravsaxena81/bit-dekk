package org.bitdekk;

import java.util.HashMap;
import java.util.Set;

import org.bitdekk.aggregation.IAggregation;
import org.bitdekk.helper.AggregationHelper;
import org.bitdekk.helper.DimensionHelper;
import org.bitdekk.helper.InvalidBitDekkExpressionException;
import org.bitdekk.helper.MeasureHelper;
import org.bitdekk.helper.sql.grammar.SqlHelper;
import org.bitdekk.util.OpenBitSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.visualization.datasource.datatable.DataTable;

@Component
public class DataLayer {
	@Autowired
	private DimensionHelper dimensionHelper;
	@Autowired
	private MeasureHelper measureHelper;
	@Autowired
	private AggregationHelper aggregationHelper;
	@Autowired
	private SqlHelper sqlHelper;
	
	/**
	 * @param dimensionMap Map of dimension name and its id
	 */
	public void initializeDimensions(HashMap<String, Integer> dimensionMap) {
		dimensionHelper.initialize(dimensionMap);
	}
	/**
	 * @param tableName a string to uniquely identify this table
	 * @param dataTable <a href='http://gwt-google-apis.googlecode.com/svn/javadoc/visualization/1.1/com/google/gwt/visualization/client/DataTable.html'>Google DataTable</a> 
	 */
	public void initializeTable(String tableName, DataTable dataTable) {
		measureHelper.intializeTable(tableName, dataTable);
	}
	/**
	 * @param tableName Name of the table which will be queried
	 * @param viewBitSet A {@link OpenBitSet} implementation having those bits set who's position matches with the ids of dimension values for which 
	 * list of matching measure values will be grouped and aggregated
	 * @param filterBitSet A {@link OpenBitSet} implementation having those bits set position of which matches with the ids of dimension values filtered in.
	 * @param measureExpression Accepts any well formed mathematical expression. It may also contain SQL like functions e.g. SUM(MeasureName)
	 * @return aggregated value
	 * @throws InvalidBitDekkExpressionException if there is a parsing error
	 */
	public double aggregate(String tableName, OpenBitSet viewBitSet, OpenBitSet filterBitSet, String measureExpression) throws InvalidBitDekkExpressionException {
		return aggregationHelper.aggregate(measureHelper.getTable(tableName), viewBitSet, filterBitSet, measureExpression);
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
		return aggregationHelper.aggregate(measureHelper.getTable(tableName), getBitSet(viewDimensionValues), getBitSet(filterDimensionValues), measureExpression);
	}
	/**
	 * @param dimensionValues Array of dimension values
	 * @return A {@link OpenBitSet} object having those bits set position of which matches with the ids of dimension values 
	 */
	public OpenBitSet getBitSet(String[] dimensionValues) {
		return dimensionHelper.getBitSet(dimensionValues);
	}
	/**
	 * @param aggregation custom aggregation
	 * @param viewBitSet A {@link OpenBitSet} implementation having those bits set who's position matches with the ids of dimension values for which 
	 * list of matching measure values will be grouped and aggregated
	 * @param filterBitSet A {@link OpenBitSet} implementation having those bits set position of which matches with the ids of dimension values filtered in.
	 * @param measureNames array of measureNames
	 * @return aggregated value
	 * @throws InvalidBitDekkExpressionException
	 */
	public double aggregate(IAggregation aggregation, String tableName, OpenBitSet viewBitSet, OpenBitSet filterBitSet, String[] measureNames) throws InvalidBitDekkExpressionException {
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
	public double aggregate(IAggregation aggregation, String tableName, String[] viewDimensionValues, String[] filterDimensionValues, String[] measureNames) throws InvalidBitDekkExpressionException {
		return aggregationHelper.aggregate(aggregation, measureHelper.getTable(tableName), getBitSet(viewDimensionValues), getBitSet(filterDimensionValues), measureNames);
	}
	/**
	 * @param dimensionValue
	 * @return id of the dimension value
	 */
	public int getDimensionId(String dimensionValue) {
		return dimensionHelper.getId(dimensionValue);
	}
	/**
	 * @return set of all the ids
	 */
	public Set<Integer> getDimensionIds() {
		return dimensionHelper.getDimensionIds();
	}
	/**
	 * @param id of a dimension value
	 * @return Dimension value
	 */
	public String getDimensionValue(int id) {
		return dimensionHelper.getDimensionValue(id);
	}
}