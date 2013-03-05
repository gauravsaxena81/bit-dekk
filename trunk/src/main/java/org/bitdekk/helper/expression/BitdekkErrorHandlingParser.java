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
