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
package org.bitdekk.aggregation;

import org.bitdekk.helper.expression.model.MeasureExpression;

/**
 * Interface used in grammar to create aggrgation types for SUM, COUNT etc.
 * @author gaurav.saxena
 *
 */
public interface IAggregation {
	public void aggregate(double[] measureValues);
	public double getValue();
	public void aggregate(double measureValue);
	public void setMeasureExpression(MeasureExpression me);
	public MeasureExpression getMeasureExpression();
}