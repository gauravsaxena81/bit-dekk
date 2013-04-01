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
package org.bitdekk.api;

import java.util.ArrayList;

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognitionException;
import org.bitdekk.helper.Position;
import org.bitdekk.helper.expression.model.GroupedMeasureExpression;
import org.bitdekk.model.DataRow;

public interface IEvaluation {
	double getMeasureExpressionValue(ArrayList<Object> measureExpressionTokens,	Position pos, DataRow row, String tableName, IBitSet viewBitSet, IBitSet filterBitSet);

	Lexer getLexer(String measureExpression);

	void parse(CommonTokenStream tokens, GroupedMeasureExpression gme) throws RecognitionException;
}
