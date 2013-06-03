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
// $ANTLR 3.3 Nov 30, 2010 12:45:30 C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g 2013-03-04 15:05:29

package org.bitdekk.distributed.expression;


import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;

public class DistributedBitdekkMeasureExpressionLexer extends Lexer {
    public static final int EOF=-1;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int ADD_SUB=4;
    public static final int MUL_DIV=5;
    public static final int NUMBER=6;
    public static final int IDENTIFIER=7;
    public static final int OPERATOR=8;
    public static final int Digit=9;
    public static final int Letter=10;
    public static final int WS=11;

    // delegates
    // delegators

    public DistributedBitdekkMeasureExpressionLexer() {;} 
    public DistributedBitdekkMeasureExpressionLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public DistributedBitdekkMeasureExpressionLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g"; }

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:7:7: ( '(' )
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:7:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:8:7: ( ')' )
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:8:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:9:7: ( 'SUM' )
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:9:9: 'SUM'
            {
            match("SUM"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:10:7: ( 'AVG' )
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:10:9: 'AVG'
            {
            match("AVG"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:11:7: ( 'COUNT' )
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:11:9: 'COUNT'
            {
            match("COUNT"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:12:7: ( 'MAX' )
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:12:9: 'MAX'
            {
            match("MAX"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:13:7: ( 'MIN' )
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:13:9: 'MIN'
            {
            match("MIN"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "OPERATOR"
    public final void mOPERATOR() throws RecognitionException {
        try {
            int _type = OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:62:9: ( '=' | '<' | '>' | '<>' | '<=' | '>=' )
            int alt1=6;
            switch ( input.LA(1) ) {
            case '=':
                {
                alt1=1;
                }
                break;
            case '<':
                {
                switch ( input.LA(2) ) {
                case '>':
                    {
                    alt1=4;
                    }
                    break;
                case '=':
                    {
                    alt1=5;
                    }
                    break;
                default:
                    alt1=2;}

                }
                break;
            case '>':
                {
                int LA1_3 = input.LA(2);

                if ( (LA1_3=='=') ) {
                    alt1=6;
                }
                else {
                    alt1=3;}
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:62:11: '='
                    {
                    match('='); 

                    }
                    break;
                case 2 :
                    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:62:17: '<'
                    {
                    match('<'); 

                    }
                    break;
                case 3 :
                    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:62:23: '>'
                    {
                    match('>'); 

                    }
                    break;
                case 4 :
                    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:62:29: '<>'
                    {
                    match("<>"); 


                    }
                    break;
                case 5 :
                    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:62:36: '<='
                    {
                    match("<="); 


                    }
                    break;
                case 6 :
                    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:62:43: '>='
                    {
                    match(">="); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OPERATOR"

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
        try {
            int _type = NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:63:8: ( ( ADD_SUB )? ( Digit )+ ( '.' ( Digit )+ )? )
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:63:10: ( ADD_SUB )? ( Digit )+ ( '.' ( Digit )+ )?
            {
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:63:10: ( ADD_SUB )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='+'||LA2_0=='-') ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:63:10: ADD_SUB
                    {
                    mADD_SUB(); 

                    }
                    break;

            }

            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:63:19: ( Digit )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:63:19: Digit
            	    {
            	    mDigit(); 

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);

            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:63:25: ( '.' ( Digit )+ )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='.') ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:63:26: '.' ( Digit )+
                    {
                    match('.'); 
                    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:63:29: ( Digit )+
                    int cnt4=0;
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:63:29: Digit
                    	    {
                    	    mDigit(); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt4 >= 1 ) break loop4;
                                EarlyExitException eee =
                                    new EarlyExitException(4, input);
                                throw eee;
                        }
                        cnt4++;
                    } while (true);


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NUMBER"

    // $ANTLR start "MUL_DIV"
    public final void mMUL_DIV() throws RecognitionException {
        try {
            int _type = MUL_DIV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:64:9: ( '*' | '/' )
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:
            {
            if ( input.LA(1)=='*'||input.LA(1)=='/' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MUL_DIV"

    // $ANTLR start "ADD_SUB"
    public final void mADD_SUB() throws RecognitionException {
        try {
            int _type = ADD_SUB;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:65:9: ( '+' | '-' )
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:
            {
            if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ADD_SUB"

    // $ANTLR start "IDENTIFIER"
    public final void mIDENTIFIER() throws RecognitionException {
        try {
            int _type = IDENTIFIER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:66:14: ( Letter ( Letter | Digit )* )
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:66:17: Letter ( Letter | Digit )*
            {
            mLetter(); 
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:66:24: ( Letter | Digit )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>='0' && LA6_0<='9')||(LA6_0>='A' && LA6_0<='Z')||LA6_0=='_'||(LA6_0>='a' && LA6_0<='z')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IDENTIFIER"

    // $ANTLR start "Letter"
    public final void mLetter() throws RecognitionException {
        try {
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:68:9: ( 'a' .. 'z' | '_' | 'A' .. 'Z' )
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "Letter"

    // $ANTLR start "Digit"
    public final void mDigit() throws RecognitionException {
        try {
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:70:8: ( '0' .. '9' )
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:70:11: '0' .. '9'
            {
            matchRange('0','9'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "Digit"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:71:6: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:71:10: ( ' ' | '\\t' | '\\r' | '\\n' )
            {
            if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:1:8: ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | OPERATOR | NUMBER | MUL_DIV | ADD_SUB | IDENTIFIER | WS )
        int alt7=13;
        alt7 = dfa7.predict(input);
        switch (alt7) {
            case 1 :
                // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:1:10: T__12
                {
                mT__12(); 

                }
                break;
            case 2 :
                // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:1:16: T__13
                {
                mT__13(); 

                }
                break;
            case 3 :
                // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:1:22: T__14
                {
                mT__14(); 

                }
                break;
            case 4 :
                // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:1:28: T__15
                {
                mT__15(); 

                }
                break;
            case 5 :
                // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:1:34: T__16
                {
                mT__16(); 

                }
                break;
            case 6 :
                // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:1:40: T__17
                {
                mT__17(); 

                }
                break;
            case 7 :
                // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:1:46: T__18
                {
                mT__18(); 

                }
                break;
            case 8 :
                // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:1:52: OPERATOR
                {
                mOPERATOR(); 

                }
                break;
            case 9 :
                // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:1:61: NUMBER
                {
                mNUMBER(); 

                }
                break;
            case 10 :
                // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:1:68: MUL_DIV
                {
                mMUL_DIV(); 

                }
                break;
            case 11 :
                // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:1:76: ADD_SUB
                {
                mADD_SUB(); 

                }
                break;
            case 12 :
                // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:1:84: IDENTIFIER
                {
                mIDENTIFIER(); 

                }
                break;
            case 13 :
                // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:1:95: WS
                {
                mWS(); 

                }
                break;

        }

    }


    protected DFA7 dfa7 = new DFA7(this);
    static final String DFA7_eotS =
        "\3\uffff\4\13\1\uffff\1\22\4\uffff\5\13\1\uffff\1\30\1\31\1\13"+
        "\1\33\1\34\2\uffff\1\13\2\uffff\1\36\1\uffff";
    static final String DFA7_eofS =
        "\37\uffff";
    static final String DFA7_minS =
        "\1\11\2\uffff\1\125\1\126\1\117\1\101\1\uffff\1\60\4\uffff\1\115"+
        "\1\107\1\125\1\130\1\116\1\uffff\2\60\1\116\2\60\2\uffff\1\124\2"+
        "\uffff\1\60\1\uffff";
    static final String DFA7_maxS =
        "\1\172\2\uffff\1\125\1\126\1\117\1\111\1\uffff\1\71\4\uffff\1\115"+
        "\1\107\1\125\1\130\1\116\1\uffff\2\172\1\116\2\172\2\uffff\1\124"+
        "\2\uffff\1\172\1\uffff";
    static final String DFA7_acceptS =
        "\1\uffff\1\1\1\2\4\uffff\1\10\1\uffff\1\11\1\12\1\14\1\15\5\uffff"+
        "\1\13\5\uffff\1\3\1\4\1\uffff\1\6\1\7\1\uffff\1\5";
    static final String DFA7_specialS =
        "\37\uffff}>";
    static final String[] DFA7_transitionS = {
            "\2\14\2\uffff\1\14\22\uffff\1\14\7\uffff\1\1\1\2\1\12\1\10"+
            "\1\uffff\1\10\1\uffff\1\12\12\11\2\uffff\3\7\2\uffff\1\4\1\13"+
            "\1\5\11\13\1\6\5\13\1\3\7\13\4\uffff\1\13\1\uffff\32\13",
            "",
            "",
            "\1\15",
            "\1\16",
            "\1\17",
            "\1\20\7\uffff\1\21",
            "",
            "\12\11",
            "",
            "",
            "",
            "",
            "\1\23",
            "\1\24",
            "\1\25",
            "\1\26",
            "\1\27",
            "",
            "\12\13\7\uffff\32\13\4\uffff\1\13\1\uffff\32\13",
            "\12\13\7\uffff\32\13\4\uffff\1\13\1\uffff\32\13",
            "\1\32",
            "\12\13\7\uffff\32\13\4\uffff\1\13\1\uffff\32\13",
            "\12\13\7\uffff\32\13\4\uffff\1\13\1\uffff\32\13",
            "",
            "",
            "\1\35",
            "",
            "",
            "\12\13\7\uffff\32\13\4\uffff\1\13\1\uffff\32\13",
            ""
    };

    static final short[] DFA7_eot = DFA.unpackEncodedString(DFA7_eotS);
    static final short[] DFA7_eof = DFA.unpackEncodedString(DFA7_eofS);
    static final char[] DFA7_min = DFA.unpackEncodedStringToUnsignedChars(DFA7_minS);
    static final char[] DFA7_max = DFA.unpackEncodedStringToUnsignedChars(DFA7_maxS);
    static final short[] DFA7_accept = DFA.unpackEncodedString(DFA7_acceptS);
    static final short[] DFA7_special = DFA.unpackEncodedString(DFA7_specialS);
    static final short[][] DFA7_transition;

    static {
        int numStates = DFA7_transitionS.length;
        DFA7_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA7_transition[i] = DFA.unpackEncodedString(DFA7_transitionS[i]);
        }
    }

    class DFA7 extends DFA {

        public DFA7(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 7;
            this.eot = DFA7_eot;
            this.eof = DFA7_eof;
            this.min = DFA7_min;
            this.max = DFA7_max;
            this.accept = DFA7_accept;
            this.special = DFA7_special;
            this.transition = DFA7_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | OPERATOR | NUMBER | MUL_DIV | ADD_SUB | IDENTIFIER | WS );";
        }
    }
 

}