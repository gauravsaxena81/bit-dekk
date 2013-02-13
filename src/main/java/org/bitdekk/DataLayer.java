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
	
	public void initializeDimensions(HashMap<String, Integer> dimensionMap) {
		dimensionHelper.initialize(dimensionMap);
	}
	public void initializeTable(String dataTableName, DataTable dataTable) {
		measureHelper.intializeTable(dataTableName, dataTable);
	}
	public double aggregate(String tableName, OpenBitSet viewBitSet, OpenBitSet filterBitSet, String measureExpression) throws InvalidBitDekkExpressionException {
		return aggregationHelper.aggregate(measureHelper.getTable(tableName), viewBitSet, filterBitSet, measureExpression);
	}
	public double aggregate(String tableName, String[] viewDimensionValues, String[] filterDimensionValues, String measureExpression) throws InvalidBitDekkExpressionException {
		return aggregationHelper.aggregate(measureHelper.getTable(tableName), getBitSet(viewDimensionValues), getBitSet(filterDimensionValues), measureExpression);
	}
	public OpenBitSet getBitSet(String[] dimensions) {
		return dimensionHelper.getBitSet(dimensions);
	}
	public double aggregate(IAggregation aggregation, String tableName, OpenBitSet viewBitSet, OpenBitSet filterBitSet, String[] measureNames) throws InvalidBitDekkExpressionException {
		return aggregationHelper.aggregate(aggregation, measureHelper.getTable(tableName), viewBitSet, filterBitSet, measureNames);
	}
	public double aggregate(IAggregation aggregation, String tableName, String[] viewDimensionValues, String[] filterDimensionValues, String[] measureNames) throws InvalidBitDekkExpressionException {
		return aggregationHelper.aggregate(aggregation, measureHelper.getTable(tableName), getBitSet(viewDimensionValues), getBitSet(filterDimensionValues), measureNames);
	}
	public int getDimensionId(String dimensionName) {
		return dimensionHelper.getId(dimensionName);
	}
	public Set<Integer> getDimensionIds() {
		return dimensionHelper.getDimensionIds();
	}
	public String getDimensionValue(int id) {
		return dimensionHelper.getDimensionValue(id);
	}
}