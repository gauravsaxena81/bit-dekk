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
// $ANTLR 3.3 Nov 30, 2010 12:45:30 C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g 2013-02-22 09:37:46

package org.bitdekk.helper.sql.grammar;


import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;

public class BitdekkSqlGrammarLexer extends Lexer {
    public static final int EOF=-1;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__19=19;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int IDENTIFIER=4;
    public static final int LOGICAL_OPERATORS=5;
    public static final int POS_INT=6;
    public static final int ADD_SUB=7;
    public static final int MUL_DIV=8;
    public static final int Digit=9;
    public static final int Letter=10;
    public static final int WS=11;

    // delegates
    // delegators

    public BitdekkSqlGrammarLexer() {;} 
    public BitdekkSqlGrammarLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public BitdekkSqlGrammarLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g"; }

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:7:7: ( 'SELECT' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:7:9: 'SELECT'
            {
            match("SELECT"); 


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
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:8:7: ( 'FROM' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:8:9: 'FROM'
            {
            match("FROM"); 


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
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:9:7: ( 'WHERE' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:9:9: 'WHERE'
            {
            match("WHERE"); 


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
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:10:7: ( 'HAVING' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:10:9: 'HAVING'
            {
            match("HAVING"); 


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
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:11:7: ( 'AND' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:11:9: 'AND'
            {
            match("AND"); 


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
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:12:7: ( 'ORDER' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:12:9: 'ORDER'
            {
            match("ORDER"); 


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
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:13:7: ( 'BY' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:13:9: 'BY'
            {
            match("BY"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:14:7: ( ',' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:14:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:15:7: ( 'ASC' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:15:9: 'ASC'
            {
            match("ASC"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:16:7: ( 'DESC' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:16:9: 'DESC'
            {
            match("DESC"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:17:7: ( 'LIMIT' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:17:9: 'LIMIT'
            {
            match("LIMIT"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:18:7: ( 'AS' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:18:9: 'AS'
            {
            match("AS"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:19:7: ( '=' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:19:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:20:7: ( '\"' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:20:9: '\"'
            {
            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:21:7: ( 'IN' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:21:9: 'IN'
            {
            match("IN"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:22:7: ( '(' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:22:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:23:7: ( ')' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:23:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:24:7: ( 'SUM' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:24:9: 'SUM'
            {
            match("SUM"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:25:7: ( 'AVG' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:25:9: 'AVG'
            {
            match("AVG"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:26:7: ( 'COUNT' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:26:9: 'COUNT'
            {
            match("COUNT"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:27:7: ( 'MAX' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:27:9: 'MAX'
            {
            match("MAX"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:28:7: ( 'MIN' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:28:9: 'MIN'
            {
            match("MIN"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:29:7: ( '.' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:29:9: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "LOGICAL_OPERATORS"
    public final void mLOGICAL_OPERATORS() throws RecognitionException {
        try {
            int _type = LOGICAL_OPERATORS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:85:2: ( '=' | '<' | '>' | '<>' | '<=' | '>=' )
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
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:85:4: '='
                    {
                    match('='); 

                    }
                    break;
                case 2 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:85:10: '<'
                    {
                    match('<'); 

                    }
                    break;
                case 3 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:85:16: '>'
                    {
                    match('>'); 

                    }
                    break;
                case 4 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:85:22: '<>'
                    {
                    match("<>"); 


                    }
                    break;
                case 5 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:85:29: '<='
                    {
                    match("<="); 


                    }
                    break;
                case 6 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:85:36: '>='
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
    // $ANTLR end "LOGICAL_OPERATORS"

    // $ANTLR start "POS_INT"
    public final void mPOS_INT() throws RecognitionException {
        try {
            int _type = POS_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:86:9: ( ( Digit )+ )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:86:11: ( Digit )+
            {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:86:11: ( Digit )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:86:11: Digit
            	    {
            	    mDigit(); 

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "POS_INT"

    // $ANTLR start "MUL_DIV"
    public final void mMUL_DIV() throws RecognitionException {
        try {
            int _type = MUL_DIV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:87:9: ( '*' | '/' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:
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
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:88:9: ( '+' | '-' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:
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
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:89:14: ( Letter ( Letter | Digit )* )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:89:17: Letter ( Letter | Digit )*
            {
            mLetter(); 
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:89:24: ( Letter | Digit )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')||(LA3_0>='A' && LA3_0<='Z')||LA3_0=='_'||(LA3_0>='a' && LA3_0<='z')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:
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
            	    break loop3;
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
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:91:9: ( 'a' .. 'z' | '_' | 'A' .. 'Z' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:
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
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:93:8: ( '0' .. '9' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:93:11: '0' .. '9'
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
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:94:6: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:94:10: ( ' ' | '\\t' | '\\r' | '\\n' )
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
        // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:8: ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | LOGICAL_OPERATORS | POS_INT | MUL_DIV | ADD_SUB | IDENTIFIER | WS )
        int alt4=29;
        alt4 = dfa4.predict(input);
        switch (alt4) {
            case 1 :
                // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:10: T__12
                {
                mT__12(); 

                }
                break;
            case 2 :
                // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:16: T__13
                {
                mT__13(); 

                }
                break;
            case 3 :
                // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:22: T__14
                {
                mT__14(); 

                }
                break;
            case 4 :
                // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:28: T__15
                {
                mT__15(); 

                }
                break;
            case 5 :
                // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:34: T__16
                {
                mT__16(); 

                }
                break;
            case 6 :
                // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:40: T__17
                {
                mT__17(); 

                }
                break;
            case 7 :
                // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:46: T__18
                {
                mT__18(); 

                }
                break;
            case 8 :
                // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:52: T__19
                {
                mT__19(); 

                }
                break;
            case 9 :
                // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:58: T__20
                {
                mT__20(); 

                }
                break;
            case 10 :
                // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:64: T__21
                {
                mT__21(); 

                }
                break;
            case 11 :
                // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:70: T__22
                {
                mT__22(); 

                }
                break;
            case 12 :
                // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:76: T__23
                {
                mT__23(); 

                }
                break;
            case 13 :
                // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:82: T__24
                {
                mT__24(); 

                }
                break;
            case 14 :
                // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:88: T__25
                {
                mT__25(); 

                }
                break;
            case 15 :
                // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:94: T__26
                {
                mT__26(); 

                }
                break;
            case 16 :
                // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:100: T__27
                {
                mT__27(); 

                }
                break;
            case 17 :
                // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:106: T__28
                {
                mT__28(); 

                }
                break;
            case 18 :
                // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:112: T__29
                {
                mT__29(); 

                }
                break;
            case 19 :
                // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:118: T__30
                {
                mT__30(); 

                }
                break;
            case 20 :
                // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:124: T__31
                {
                mT__31(); 

                }
                break;
            case 21 :
                // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:130: T__32
                {
                mT__32(); 

                }
                break;
            case 22 :
                // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:136: T__33
                {
                mT__33(); 

                }
                break;
            case 23 :
                // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:142: T__34
                {
                mT__34(); 

                }
                break;
            case 24 :
                // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:148: LOGICAL_OPERATORS
                {
                mLOGICAL_OPERATORS(); 

                }
                break;
            case 25 :
                // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:166: POS_INT
                {
                mPOS_INT(); 

                }
                break;
            case 26 :
                // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:174: MUL_DIV
                {
                mMUL_DIV(); 

                }
                break;
            case 27 :
                // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:182: ADD_SUB
                {
                mADD_SUB(); 

                }
                break;
            case 28 :
                // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:190: IDENTIFIER
                {
                mIDENTIFIER(); 

                }
                break;
            case 29 :
                // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:1:201: WS
                {
                mWS(); 

                }
                break;

        }

    }


    protected DFA4 dfa4 = new DFA4(this);
    static final String DFA4_eotS =
        "\1\uffff\7\27\1\uffff\2\27\2\uffff\1\27\2\uffff\2\27\7\uffff\6"+
        "\27\1\61\2\27\1\64\2\27\1\uffff\1\67\4\27\1\74\3\27\1\100\1\101"+
        "\1\uffff\1\102\1\27\1\uffff\2\27\1\uffff\1\27\1\107\1\110\1\27\1"+
        "\uffff\1\112\2\27\3\uffff\1\27\1\116\2\27\2\uffff\1\27\1\uffff\1"+
        "\122\1\27\1\124\1\uffff\1\125\1\126\1\127\1\uffff\1\130\5\uffff";
    static final String DFA4_eofS =
        "\131\uffff";
    static final String DFA4_minS =
        "\1\11\1\105\1\122\1\110\1\101\1\116\1\122\1\131\1\uffff\1\105\1"+
        "\111\2\uffff\1\116\2\uffff\1\117\1\101\7\uffff\1\114\1\115\1\117"+
        "\1\105\1\126\1\104\1\60\1\107\1\104\1\60\1\123\1\115\1\uffff\1\60"+
        "\1\125\1\130\1\116\1\105\1\60\1\115\1\122\1\111\2\60\1\uffff\1\60"+
        "\1\105\1\uffff\1\103\1\111\1\uffff\1\116\2\60\1\103\1\uffff\1\60"+
        "\1\105\1\116\3\uffff\1\122\1\60\2\124\2\uffff\1\124\1\uffff\1\60"+
        "\1\107\1\60\1\uffff\3\60\1\uffff\1\60\5\uffff";
    static final String DFA4_maxS =
        "\1\172\1\125\1\122\1\110\1\101\1\126\1\122\1\131\1\uffff\1\105"+
        "\1\111\2\uffff\1\116\2\uffff\1\117\1\111\7\uffff\1\114\1\115\1\117"+
        "\1\105\1\126\1\104\1\172\1\107\1\104\1\172\1\123\1\115\1\uffff\1"+
        "\172\1\125\1\130\1\116\1\105\1\172\1\115\1\122\1\111\2\172\1\uffff"+
        "\1\172\1\105\1\uffff\1\103\1\111\1\uffff\1\116\2\172\1\103\1\uffff"+
        "\1\172\1\105\1\116\3\uffff\1\122\1\172\2\124\2\uffff\1\124\1\uffff"+
        "\1\172\1\107\1\172\1\uffff\3\172\1\uffff\1\172\5\uffff";
    static final String DFA4_acceptS =
        "\10\uffff\1\10\2\uffff\1\15\1\16\1\uffff\1\20\1\21\2\uffff\1\27"+
        "\1\30\1\31\1\32\1\33\1\34\1\35\14\uffff\1\15\13\uffff\1\14\2\uffff"+
        "\1\7\2\uffff\1\17\4\uffff\1\22\3\uffff\1\5\1\11\1\23\4\uffff\1\25"+
        "\1\26\1\uffff\1\2\3\uffff\1\12\3\uffff\1\3\1\uffff\1\6\1\13\1\24"+
        "\1\1\1\4";
    static final String DFA4_specialS =
        "\131\uffff}>";
    static final String[] DFA4_transitionS = {
            "\2\30\2\uffff\1\30\22\uffff\1\30\1\uffff\1\14\5\uffff\1\16"+
            "\1\17\1\25\1\26\1\10\1\26\1\22\1\25\12\24\2\uffff\1\23\1\13"+
            "\1\23\2\uffff\1\5\1\7\1\20\1\11\1\27\1\2\1\27\1\4\1\15\2\27"+
            "\1\12\1\21\1\27\1\6\3\27\1\1\3\27\1\3\3\27\4\uffff\1\27\1\uffff"+
            "\32\27",
            "\1\31\17\uffff\1\32",
            "\1\33",
            "\1\34",
            "\1\35",
            "\1\36\4\uffff\1\37\2\uffff\1\40",
            "\1\41",
            "\1\42",
            "",
            "\1\43",
            "\1\44",
            "",
            "",
            "\1\46",
            "",
            "",
            "\1\47",
            "\1\50\7\uffff\1\51",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\52",
            "\1\53",
            "\1\54",
            "\1\55",
            "\1\56",
            "\1\57",
            "\12\27\7\uffff\2\27\1\60\27\27\4\uffff\1\27\1\uffff\32\27",
            "\1\62",
            "\1\63",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\65",
            "\1\66",
            "",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\70",
            "\1\71",
            "\1\72",
            "\1\73",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\75",
            "\1\76",
            "\1\77",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\103",
            "",
            "\1\104",
            "\1\105",
            "",
            "\1\106",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\111",
            "",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\113",
            "\1\114",
            "",
            "",
            "",
            "\1\115",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\117",
            "\1\120",
            "",
            "",
            "\1\121",
            "",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\123",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA4_eot = DFA.unpackEncodedString(DFA4_eotS);
    static final short[] DFA4_eof = DFA.unpackEncodedString(DFA4_eofS);
    static final char[] DFA4_min = DFA.unpackEncodedStringToUnsignedChars(DFA4_minS);
    static final char[] DFA4_max = DFA.unpackEncodedStringToUnsignedChars(DFA4_maxS);
    static final short[] DFA4_accept = DFA.unpackEncodedString(DFA4_acceptS);
    static final short[] DFA4_special = DFA.unpackEncodedString(DFA4_specialS);
    static final short[][] DFA4_transition;

    static {
        int numStates = DFA4_transitionS.length;
        DFA4_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA4_transition[i] = DFA.unpackEncodedString(DFA4_transitionS[i]);
        }
    }

    class DFA4 extends DFA {

        public DFA4(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 4;
            this.eot = DFA4_eot;
            this.eof = DFA4_eof;
            this.min = DFA4_min;
            this.max = DFA4_max;
            this.accept = DFA4_accept;
            this.special = DFA4_special;
            this.transition = DFA4_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | LOGICAL_OPERATORS | POS_INT | MUL_DIV | ADD_SUB | IDENTIFIER | WS );";
        }
    }
 

}