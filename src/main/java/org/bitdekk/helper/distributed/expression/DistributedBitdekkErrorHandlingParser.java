package org.bitdekk.helper.distributed.expression;

import org.antlr.runtime.BitSet;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.TokenStream;
import org.bitdekk.exception.InvalidBitDekkExpressionException;
import org.bitdekk.helper.expression.model.GroupedMeasureExpression;

public class DistributedBitdekkErrorHandlingParser extends DistributedBitdekkMeasureExpressionParser {
	public DistributedBitdekkErrorHandlingParser(TokenStream input, GroupedMeasureExpression gme) {
		super(input, gme);
	}
	@Override
	public Object recoverFromMismatchedToken(IntStream arg0, int arg1, BitSet arg2) {
		throw new InvalidBitDekkExpressionException("Bad Grammar near " + ((TokenStream)input).LT(1).getText());
	}
}
