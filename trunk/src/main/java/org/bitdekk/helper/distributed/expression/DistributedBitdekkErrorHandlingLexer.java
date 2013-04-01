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
package org.bitdekk.helper.distributed.expression;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.bitdekk.exception.InvalidBitDekkExpressionException;

public class DistributedBitdekkErrorHandlingLexer extends DistributedBitdekkMeasureExpressionLexer {
	public DistributedBitdekkErrorHandlingLexer(ANTLRStringStream antlrStringStream) {
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
                throw new InvalidBitDekkExpressionException("Bad grammar near " + state.token.getText()); // or throw Error
            }
        }
    }
}