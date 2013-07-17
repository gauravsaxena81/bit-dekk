package org.bitdekk.helper.sql.grammar;

import java.util.ArrayList;
import java.util.HashMap;

import org.bitdekk.helper.sql.grammar.model.Dimension;
import org.bitdekk.helper.sql.grammar.model.IColumn;
import org.bitdekk.helper.sql.grammar.model.Measure;
import org.bitdekk.helper.sql.grammar.model.OrderColumn;

public class State {
	private String tableName;
	private HashMap<String, ArrayList<String>> dimensionConditions = new HashMap<String, ArrayList<String>>();
	private ArrayList<IColumn> columns = new ArrayList<IColumn>();
	private ArrayList<ArrayList<Object>> measureConditions = new ArrayList<ArrayList<Object>>();
	private ArrayList<OrderColumn> orderByColumns = new ArrayList<OrderColumn>();
	private int fromRowNumber = -1;
	private int toRowNumber = -1;
	private ArrayList<HavingExpression> havingExpressions = new ArrayList<HavingExpression>();
	
	public ArrayList<HavingExpression> getHavingExpressions() {
		return havingExpressions;
	}

	public int getFromRowNumber() {
		return fromRowNumber;
	}

	public int getToRowNumber() {
		return toRowNumber;
	}

	public ArrayList<ArrayList<Object>> getMeasureConditions() {
		return measureConditions;
	}

	public ArrayList<IColumn> getColumns() {
		return columns;
	}
	public HashMap<String, ArrayList<String>> getDimensionConditions() {
		return dimensionConditions;
	}
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void addDimensionValue(String dimension, String dimensionValue) {
		ArrayList<String> list = dimensionConditions.get(dimension);
		if(list == null) {
			list = new ArrayList<String>();
			dimensionConditions.put(dimension, list);
		}
		list.add(dimensionValue);
	}

	public void addColumn(IColumn column) {
		columns.add(column);
	}

	public void addMeasureCondition(ArrayList<Object> measureTokens) {
		measureConditions.add(measureTokens);
	}

	public void addOrderByColumn(String columnName, boolean asc) {
		for(int i = 0; i < columns.size(); i++) {
			if(columnName.equals(columns.get(i).getLabel())
				|| ((columns.get(i) instanceof Dimension && (columnName.equals(((Dimension)columns.get(i)).getName()))))
				|| ((columns.get(i) instanceof Measure && columnName.equals(((Measure)columns.get(i)).getMeasureExpression())))) {
				orderByColumns.add(new OrderColumn(i, asc));
				return;
			}
		}
		throw new IllegalArgumentException(columnName + " not found in select clause");
	}

	public ArrayList<OrderColumn> getOrderByColumns() {
		return orderByColumns;
	}

	public void setFromRowNumber(int fromRowNumber) {
		this.fromRowNumber = fromRowNumber;
	}

	public void setToRowNumber(int toRowNumber) {
		if(toRowNumber < fromRowNumber)
			throw new IllegalArgumentException("in LIMIT clause toRowNumber should be grater than fromRowNumber");
		this.toRowNumber = toRowNumber;
	}

	public void groupedExpressionNotFound(String expression) {
		throw new InvalidGrammarException("No aggregating function found with measure expression: " + expression);
	}

	public void addHavingExpression(HavingExpression havingExpression) {
		havingExpressions.add(havingExpression);
	}
}