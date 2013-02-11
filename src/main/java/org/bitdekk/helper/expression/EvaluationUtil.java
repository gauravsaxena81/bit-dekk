package org.bitdekk.helper.expression;

import java.util.ArrayList;

import org.bitdekk.aggregation.IAggregation;
import org.bitdekk.model.DataRow;
import org.bitdekk.model.Table;
import org.bitdekk.util.OpenBitSet;

public class EvaluationUtil {
	public static double getMeasureExpressionValue(ArrayList<Object> measureExpressionTokens, int pos, DataRow row, Table table, OpenBitSet viewBitSet
			, OpenBitSet filterBitSet) {
		if(measureExpressionTokens.get(pos).equals("+"))
			return add(measureExpressionTokens, pos + 1, row, table, viewBitSet, filterBitSet);
		else if(measureExpressionTokens.get(pos).equals("-"))
			return subtract(measureExpressionTokens, ++pos, row, table, viewBitSet, filterBitSet);
		else if(measureExpressionTokens.get(pos).equals("*"))
			return multiply(measureExpressionTokens, ++pos, row, table, viewBitSet, filterBitSet);
		else if(measureExpressionTokens.get(pos).equals("/"))
			return divide(measureExpressionTokens, ++pos, row, table, viewBitSet, filterBitSet);
		else if(measureExpressionTokens.get(pos) instanceof Double)
			return (Double)measureExpressionTokens.get(pos);
		else if(measureExpressionTokens.get(pos) instanceof IAggregation)
			return aggregate(((IAggregation)measureExpressionTokens.get(pos)), ((IAggregation)measureExpressionTokens.get(pos)).getMeasureExpression(), table, viewBitSet, filterBitSet);
		else
			return row.getMeasureValues()[table.getMeasureIndexMap().get(measureExpressionTokens.get(pos))];
	}
	private static double aggregate(IAggregation aggregation, MeasureExpression measureExpression, Table table, OpenBitSet viewBitSet, OpenBitSet filterBitSet) {
		aggregation.initialize();
		for(DataRow i : table.getRows()) {
			if(filterBitSet.contains(i.getMeasureQuery()) && i.getMeasureQuery().contains(viewBitSet)) {
				aggregation.aggregate(getMeasureExpressionValue(measureExpression.getTokens(), 0, i, table, viewBitSet, filterBitSet));
			}
		}
		return aggregation.getValue();
	}
	private static double divide(ArrayList<Object> measureExpressionTokens, int pos, DataRow row, Table table, OpenBitSet viewBitSet, OpenBitSet filterBitSet) {
		return getMeasureExpressionValue(measureExpressionTokens, pos, row, table, viewBitSet, filterBitSet) 
				/ getMeasureExpressionValue(measureExpressionTokens, pos + 1, row, table, viewBitSet, filterBitSet);
	}
	private static double multiply(ArrayList<Object> measureExpressionTokens, int pos, DataRow row, Table table, OpenBitSet viewBitSet, OpenBitSet filterBitSet) {
		return getMeasureExpressionValue(measureExpressionTokens, pos, row, table, viewBitSet, filterBitSet) 
				* getMeasureExpressionValue(measureExpressionTokens, pos + 1, row, table, viewBitSet, filterBitSet);
	}
	private static double subtract(ArrayList<Object> measureExpressionTokens, int pos, DataRow row, Table table, OpenBitSet viewBitSet, OpenBitSet filterBitSet) {
		return getMeasureExpressionValue(measureExpressionTokens, pos, row, table, viewBitSet, filterBitSet) 
				- getMeasureExpressionValue(measureExpressionTokens, pos + 1, row, table, viewBitSet, filterBitSet);
	}
	private static double add(ArrayList<Object> measureExpressionTokens, int pos, DataRow row, Table table, OpenBitSet viewBitSet, OpenBitSet filterBitSet) {
		return getMeasureExpressionValue(measureExpressionTokens, pos, row, table, viewBitSet, filterBitSet) 
				+ getMeasureExpressionValue(measureExpressionTokens, pos + 1, row, table, viewBitSet, filterBitSet);
	}
	/*public static boolean measureCondition(ArrayList<ArrayList<Object>> measureConditionExpressions, DataRow row, HashMap<String, Integer> measureIndexMap) {
		boolean returnValue = true;
		for(ArrayList<Object> measureConditionExpression : measureConditionExpressions) {
			if(measureConditionExpression.get(0).equals("="))
				returnValue &= row.getMeasureValues()[measureIndexMap.get(measureConditionExpression.get(1))] 
						== getMeasureValue(measureConditionExpression, 2, row, measureIndexMap);
			else if(measureConditionExpression.get(0).equals("<>"))
				returnValue &= row.getMeasureValues()[measureIndexMap.get(measureConditionExpression.get(1))] 
						!= getMeasureValue(measureConditionExpression, 2, row, measureIndexMap);
			else if(measureConditionExpression.get(0).equals(">="))
				returnValue &= row.getMeasureValues()[measureIndexMap.get(measureConditionExpression.get(1))] 
						>= getMeasureValue(measureConditionExpression, 2, row, measureIndexMap);
			else if(measureConditionExpression.get(0).equals("<="))
				returnValue &= row.getMeasureValues()[measureIndexMap.get(measureConditionExpression.get(1))] 
						<= getMeasureValue(measureConditionExpression, 2, row, measureIndexMap);
			else if(measureConditionExpression.get(0).equals("<"))
				returnValue &= row.getMeasureValues()[measureIndexMap.get(measureConditionExpression.get(1))] 
						< getMeasureValue(measureConditionExpression, 2, row, measureIndexMap);
			else if(measureConditionExpression.get(0).equals(">"))
				returnValue &= row.getMeasureValues()[measureIndexMap.get(measureConditionExpression.get(1))] 
						> getMeasureValue(measureConditionExpression, 2, row, measureIndexMap);
			else if(measureConditionExpression.get(0).equals("<>"))
				returnValue &= row.getMeasureValues()[measureIndexMap.get(measureConditionExpression.get(1))] 
						> getMeasureValue(measureConditionExpression, 2, row, measureIndexMap);
			else
				throw new IllegalArgumentException("Unidentified operator " + measureConditionExpressions.get(0));
		}
		return returnValue;
	}*/
}
