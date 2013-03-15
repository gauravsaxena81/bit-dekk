package org.bitdekk.helper.sql.grammar;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;

public class ErrorHandlingLexer extends BitdekkSqlGrammarLexer {
	public ErrorHandlingLexer(ANTLRStringStream antlrStringStream) {
		super(antlrStringStream);
	}

	@Override
	public Token nextToken() {
        while (true) {
            state.token = null;
            state.channel = Token.DEFAULT_CHANNEL;
            state.tokenStartCharIndex = input.index();
            state.tokenStartCharPositionInLine = input.getCharPositionInLine();
            state.tokenStartLine = input.getLine();
            state.text = null;
            if ( input.LA(1)==CharStream.EOF ) {
                return super.getEOFToken();
            }
            try {
                mTokens();
                if ( state.token==null ) {
                    emit();
                }
                else if ( state.token==Token.SKIP_TOKEN ) {
                    continue;
                }
                return state.token;
            }
            catch (RecognitionException re) {
                reportError(re);
                if(state.token != null)
                	throw new InvalidGrammarException("Bad grammar near " + state.token.getText()); // or throw Error
                else
                	throw new InvalidGrammarException("Bad grammar after " + input.substring(0, input.getCharPositionInLine())); // or throw Error
                	
            }
        }
    }
}