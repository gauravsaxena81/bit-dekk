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
package org.bitdekk.helper.expression.model;

import java.util.ArrayList;

import org.bitdekk.aggregation.IAggregation;
import org.bitdekk.util.BitDekkUtil.OPERATORS;

/**
 * It helps store the information captured from GROUP functions like SUM, COUNT etc. in grammar
 * @author gaurav.saxena
 *
 */
public class GroupedMeasureExpression {
	private OPERATORS[] operators;
	private ArrayList<Object> groupedTokens;
	private Object[] groupedTokenArray;
	
	public Object[] getGroupedTokenArray() {
		return groupedTokenArray;
	}
	public OPERATORS[] getOperators() {
		return operators;
	}
	public ArrayList<Object> getGroupedTokens() {
		return groupedTokens;
	}

	public void setGroupedTokens(ArrayList<Object> groupedTokens) {
		this.groupedTokens = groupedTokens;
		this.groupedTokenArray = groupedTokens.toArray();
		this.operators = new OPERATORS[groupedTokens.size()];
		int j = 0;
		for(Object i : groupedTokens) {
			if(i.equals("+")) {
				this.operators[j++] = OPERATORS.PLUS;
			} else if(i.equals("-")) {
				this.operators[j++] = OPERATORS.MINUS;
			} else if(i.equals("*"))  {
				this.operators[j++] = OPERATORS.MULTIPLY;
			} else if(i.equals("/")) {
				this.operators[j++] = OPERATORS.DIVIDE;
			} else if(i instanceof Double)
				this.operators[j++] = OPERATORS.NUMBER;
			else if(i instanceof IAggregation)
				this.operators[j++] = OPERATORS.AGGREGATION;
			else
				this.operators[j++] = OPERATORS.CELL_VALUE;
		}
	}
}
 