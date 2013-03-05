package org.bitdekk.helper.sql.grammar;

import org.antlr.runtime.BitSet;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;

public class ErrorHandlingParser extends BitdekkSqlGrammarParser {
	public ErrorHandlingParser(TokenStream input, State state) {
		super(input, state);
	}
	@Override
	public Object recoverFromMismatchedToken(IntStream arg0, int arg1, BitSet arg2) {
		throw new InvalidGrammarException("Bad Grammar near " + ((TokenStream)input).LT(1).getText());
	}
	@Override
	public void reportError(RecognitionException re) {
		throw new InvalidGrammarException("Bad Grammar near " + ((TokenStream)input).LT(1).getText());
	}
}
