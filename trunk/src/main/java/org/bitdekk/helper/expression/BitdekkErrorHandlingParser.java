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
package org.bitdekk.helper.expression;

import org.antlr.runtime.BitSet;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.TokenStream;
import org.bitdekk.exception.InvalidBitDekkExpressionException;
import org.bitdekk.helper.expression.model.GroupedMeasureExpression;

public class BitdekkErrorHandlingParser extends BitdekkMeasureExpressionParser {
	public BitdekkErrorHandlingParser(TokenStream input, GroupedMeasureExpression gme) {
		super(input, gme);
	}
	@Override
	public Object recoverFromMismatchedToken(IntStream arg0, int arg1, BitSet arg2) {
		throw new InvalidBitDekkExpressionException("Bad Grammar near " + ((TokenStream)input).LT(1).getText());
	}
}
