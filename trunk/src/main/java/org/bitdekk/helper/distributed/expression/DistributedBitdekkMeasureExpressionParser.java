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

package org.bitdekk.helper.distributed.expression;

import java.util.ArrayList;

import org.antlr.runtime.BitSet;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.bitdekk.aggregation.IAggregation;
import org.bitdekk.aggregation.impl.AvgAggregation;
import org.bitdekk.aggregation.impl.CountAggregation;
import org.bitdekk.aggregation.impl.MaxAggregation;
import org.bitdekk.aggregation.impl.MinAggregation;
import org.bitdekk.aggregation.impl.SumAggregation;
import org.bitdekk.helper.distributed.expression.model.FunctionExpression;
import org.bitdekk.helper.expression.model.GroupedMeasureExpression;

public class DistributedBitdekkMeasureExpressionParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ADD_SUB", "MUL_DIV", "NUMBER", "IDENTIFIER", "OPERATOR", "Digit", "Letter", "WS", "'('", "')'", "'SUM'", "'AVG'", "'COUNT'", "'MAX'", "'MIN'"
    };
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


        public DistributedBitdekkMeasureExpressionParser(TokenStream input, GroupedMeasureExpression gme) {
            this(input, new RecognizerSharedState());
            this.gme = gme;
        }
        public DistributedBitdekkMeasureExpressionParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return DistributedBitdekkMeasureExpressionParser.tokenNames; }
    public String getGrammarFileName() { return "C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g"; }


    	private GroupedMeasureExpression gme;



    // $ANTLR start "stat"
    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:24:1: stat : groupedExpression EOF ;
    public final void stat() throws RecognitionException {
        ArrayList<Object> groupedExpression1 = null;


        try {
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:24:6: ( groupedExpression EOF )
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:24:8: groupedExpression EOF
            {
            pushFollow(FOLLOW_groupedExpression_in_stat27);
            groupedExpression1=groupedExpression();

            state._fsp--;

            gme.setGroupedTokens(groupedExpression1);
            match(input,EOF,FOLLOW_EOF_in_stat31); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "stat"


    // $ANTLR start "groupedExpression"
    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:25:1: groupedExpression returns [ArrayList<Object> groupedTokens] : a= groupedAddExpression ( ADD_SUB b= groupedAddExpression )? ;
    public final ArrayList<Object> groupedExpression() throws RecognitionException {
        ArrayList<Object> groupedTokens = null;

        Token ADD_SUB2=null;
        ArrayList<Object> a = null;

        ArrayList<Object> b = null;


        groupedTokens = new ArrayList<Object>();
        try {
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:26:2: (a= groupedAddExpression ( ADD_SUB b= groupedAddExpression )? )
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:26:4: a= groupedAddExpression ( ADD_SUB b= groupedAddExpression )?
            {
            pushFollow(FOLLOW_groupedAddExpression_in_groupedExpression49);
            a=groupedAddExpression();

            state._fsp--;

            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:26:27: ( ADD_SUB b= groupedAddExpression )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==ADD_SUB) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:26:28: ADD_SUB b= groupedAddExpression
                    {
                    ADD_SUB2=(Token)match(input,ADD_SUB,FOLLOW_ADD_SUB_in_groupedExpression52); 
                    pushFollow(FOLLOW_groupedAddExpression_in_groupedExpression56);
                    b=groupedAddExpression();

                    state._fsp--;


                    }
                    break;

            }


            		if(b != null) {
            			groupedTokens.add((ADD_SUB2!=null?ADD_SUB2.getText():null)); 
            			groupedTokens.addAll(a); 
            			groupedTokens.addAll(b);
            		} else
            			groupedTokens.addAll(a); 
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return groupedTokens;
    }
    // $ANTLR end "groupedExpression"


    // $ANTLR start "groupedAddExpression"
    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:34:1: groupedAddExpression returns [ArrayList<Object> groupedTokens] : a= groupedMulExpression ( MUL_DIV b= groupedMulExpression )? ;
    public final ArrayList<Object> groupedAddExpression() throws RecognitionException {
        ArrayList<Object> groupedTokens = null;

        Token MUL_DIV3=null;
        ArrayList<Object> a = null;

        ArrayList<Object> b = null;


        groupedTokens = new ArrayList<Object>();
        try {
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:35:2: (a= groupedMulExpression ( MUL_DIV b= groupedMulExpression )? )
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:35:4: a= groupedMulExpression ( MUL_DIV b= groupedMulExpression )?
            {
            pushFollow(FOLLOW_groupedMulExpression_in_groupedAddExpression78);
            a=groupedMulExpression();

            state._fsp--;

            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:35:27: ( MUL_DIV b= groupedMulExpression )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==MUL_DIV) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:35:28: MUL_DIV b= groupedMulExpression
                    {
                    MUL_DIV3=(Token)match(input,MUL_DIV,FOLLOW_MUL_DIV_in_groupedAddExpression81); 
                    pushFollow(FOLLOW_groupedMulExpression_in_groupedAddExpression85);
                    b=groupedMulExpression();

                    state._fsp--;


                    }
                    break;

            }


            		if(b != null) {
            			groupedTokens.add((MUL_DIV3!=null?MUL_DIV3.getText():null));
            			groupedTokens.addAll(a);
            			groupedTokens.addAll(b);
            		} else
            			groupedTokens.addAll(a);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return groupedTokens;
    }
    // $ANTLR end "groupedAddExpression"


    // $ANTLR start "groupedMulExpression"
    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:43:1: groupedMulExpression returns [ArrayList<Object> groupedTokens] : ( NUMBER | function '(' measureExpression ')' | '(' groupedExpression ')' );
    public final ArrayList<Object> groupedMulExpression() throws RecognitionException {
        ArrayList<Object> groupedTokens = null;

        Token NUMBER4=null;
        DistributedBitdekkMeasureExpressionParser.function_return function5 = null;

        DistributedBitdekkMeasureExpressionParser.measureExpression_return measureExpression6 = null;

        ArrayList<Object> groupedExpression7 = null;


        groupedTokens = new ArrayList<Object>();
        try {
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:44:2: ( NUMBER | function '(' measureExpression ')' | '(' groupedExpression ')' )
            int alt3=3;
            switch ( input.LA(1) ) {
            case NUMBER:
                {
                alt3=1;
                }
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                {
                alt3=2;
                }
                break;
            case 12:
                {
                alt3=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:44:5: NUMBER
                    {
                    NUMBER4=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_groupedMulExpression106); 
                    groupedTokens.add(Double.parseDouble((NUMBER4!=null?NUMBER4.getText():null)));

                    }
                    break;
                case 2 :
                    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:45:5: function '(' measureExpression ')'
                    {
                    pushFollow(FOLLOW_function_in_groupedMulExpression114);
                    function5=function();

                    state._fsp--;

                    match(input,12,FOLLOW_12_in_groupedMulExpression116); 
                    pushFollow(FOLLOW_measureExpression_in_groupedMulExpression118);
                    measureExpression6=measureExpression();

                    state._fsp--;

                    match(input,13,FOLLOW_13_in_groupedMulExpression120); 
                    groupedTokens.add(new FunctionExpression((function5!=null?function5.func:null), (function5!=null?input.toString(function5.start,function5.stop):null) + "(" + (measureExpression6!=null?input.toString(measureExpression6.start,measureExpression6.stop):null) + ")"));

                    }
                    break;
                case 3 :
                    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:46:4: '(' groupedExpression ')'
                    {
                    match(input,12,FOLLOW_12_in_groupedMulExpression127); 
                    pushFollow(FOLLOW_groupedExpression_in_groupedMulExpression128);
                    groupedExpression7=groupedExpression();

                    state._fsp--;

                    match(input,13,FOLLOW_13_in_groupedMulExpression129); 
                    groupedTokens.addAll(groupedExpression7);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return groupedTokens;
    }
    // $ANTLR end "groupedMulExpression"

    public static class measureExpression_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "measureExpression"
    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:47:1: measureExpression : a= addExpression ( ADD_SUB b= addExpression )? ;
    public final DistributedBitdekkMeasureExpressionParser.measureExpression_return measureExpression() throws RecognitionException {
        DistributedBitdekkMeasureExpressionParser.measureExpression_return retval = new DistributedBitdekkMeasureExpressionParser.measureExpression_return();
        retval.start = input.LT(1);

        try {
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:48:2: (a= addExpression ( ADD_SUB b= addExpression )? )
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:48:4: a= addExpression ( ADD_SUB b= addExpression )?
            {
            pushFollow(FOLLOW_addExpression_in_measureExpression142);
            addExpression();

            state._fsp--;

            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:48:20: ( ADD_SUB b= addExpression )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==ADD_SUB) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:48:21: ADD_SUB b= addExpression
                    {
                    match(input,ADD_SUB,FOLLOW_ADD_SUB_in_measureExpression145); 
                    pushFollow(FOLLOW_addExpression_in_measureExpression149);
                    addExpression();

                    state._fsp--;


                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "measureExpression"


    // $ANTLR start "addExpression"
    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:49:1: addExpression : a= mulExpression ( MUL_DIV b= mulExpression )? ;
    public final void addExpression() throws RecognitionException {
        try {
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:50:2: (a= mulExpression ( MUL_DIV b= mulExpression )? )
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:50:4: a= mulExpression ( MUL_DIV b= mulExpression )?
            {
            pushFollow(FOLLOW_mulExpression_in_addExpression161);
            mulExpression();

            state._fsp--;

            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:50:20: ( MUL_DIV b= mulExpression )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==MUL_DIV) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:50:21: MUL_DIV b= mulExpression
                    {
                    match(input,MUL_DIV,FOLLOW_MUL_DIV_in_addExpression164); 
                    pushFollow(FOLLOW_mulExpression_in_addExpression168);
                    mulExpression();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "addExpression"


    // $ANTLR start "mulExpression"
    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:51:1: mulExpression : ( IDENTIFIER | NUMBER | '(' measureExpression ')' );
    public final void mulExpression() throws RecognitionException {
        try {
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:52:2: ( IDENTIFIER | NUMBER | '(' measureExpression ')' )
            int alt6=3;
            switch ( input.LA(1) ) {
            case IDENTIFIER:
                {
                alt6=1;
                }
                break;
            case NUMBER:
                {
                alt6=2;
                }
                break;
            case 12:
                {
                alt6=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:52:4: IDENTIFIER
                    {
                    match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_mulExpression178); 

                    }
                    break;
                case 2 :
                    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:53:5: NUMBER
                    {
                    match(input,NUMBER,FOLLOW_NUMBER_in_mulExpression184); 

                    }
                    break;
                case 3 :
                    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:54:5: '(' measureExpression ')'
                    {
                    match(input,12,FOLLOW_12_in_mulExpression190); 
                    pushFollow(FOLLOW_measureExpression_in_mulExpression191);
                    measureExpression();

                    state._fsp--;

                    match(input,13,FOLLOW_13_in_mulExpression192); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "mulExpression"

    public static class function_return extends ParserRuleReturnScope {
        public IAggregation func;
    };

    // $ANTLR start "function"
    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:55:1: function returns [IAggregation func] : ( 'SUM' | 'AVG' | 'COUNT' | 'MAX' | 'MIN' );
    public final DistributedBitdekkMeasureExpressionParser.function_return function() throws RecognitionException {
        DistributedBitdekkMeasureExpressionParser.function_return retval = new DistributedBitdekkMeasureExpressionParser.function_return();
        retval.start = input.LT(1);

        try {
            // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:56:2: ( 'SUM' | 'AVG' | 'COUNT' | 'MAX' | 'MIN' )
            int alt7=5;
            switch ( input.LA(1) ) {
            case 14:
                {
                alt7=1;
                }
                break;
            case 15:
                {
                alt7=2;
                }
                break;
            case 16:
                {
                alt7=3;
                }
                break;
            case 17:
                {
                alt7=4;
                }
                break;
            case 18:
                {
                alt7=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:56:4: 'SUM'
                    {
                    match(input,14,FOLLOW_14_in_function204); 
                    retval.func = new SumAggregation();

                    }
                    break;
                case 2 :
                    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:57:4: 'AVG'
                    {
                    match(input,15,FOLLOW_15_in_function211); 
                    retval.func = new AvgAggregation();

                    }
                    break;
                case 3 :
                    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:58:4: 'COUNT'
                    {
                    match(input,16,FOLLOW_16_in_function218); 
                    retval.func = new CountAggregation();

                    }
                    break;
                case 4 :
                    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:59:4: 'MAX'
                    {
                    match(input,17,FOLLOW_17_in_function225); 
                    retval.func = new MaxAggregation();

                    }
                    break;
                case 5 :
                    // C:\\Work\\Personal Workspace\\BitDekkServer\\src\\org\\bitdekk\\helper\\distributed\\expression\\DistributedBitdekkMeasureExpression.g:60:4: 'MIN'
                    {
                    match(input,18,FOLLOW_18_in_function232); 
                    retval.func = new MinAggregation();

                    }
                    break;

            }
            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "function"

    // Delegated rules


 

    public static final BitSet FOLLOW_groupedExpression_in_stat27 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_stat31 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_groupedAddExpression_in_groupedExpression49 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_ADD_SUB_in_groupedExpression52 = new BitSet(new long[]{0x000000000007D040L});
    public static final BitSet FOLLOW_groupedAddExpression_in_groupedExpression56 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_groupedMulExpression_in_groupedAddExpression78 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_MUL_DIV_in_groupedAddExpression81 = new BitSet(new long[]{0x000000000007D040L});
    public static final BitSet FOLLOW_groupedMulExpression_in_groupedAddExpression85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_groupedMulExpression106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_in_groupedMulExpression114 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_groupedMulExpression116 = new BitSet(new long[]{0x00000000000010C0L});
    public static final BitSet FOLLOW_measureExpression_in_groupedMulExpression118 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_groupedMulExpression120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_groupedMulExpression127 = new BitSet(new long[]{0x000000000007D040L});
    public static final BitSet FOLLOW_groupedExpression_in_groupedMulExpression128 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_groupedMulExpression129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_addExpression_in_measureExpression142 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_ADD_SUB_in_measureExpression145 = new BitSet(new long[]{0x00000000000010C0L});
    public static final BitSet FOLLOW_addExpression_in_measureExpression149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_mulExpression_in_addExpression161 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_MUL_DIV_in_addExpression164 = new BitSet(new long[]{0x00000000000010C0L});
    public static final BitSet FOLLOW_mulExpression_in_addExpression168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_mulExpression178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_mulExpression184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_mulExpression190 = new BitSet(new long[]{0x00000000000010C0L});
    public static final BitSet FOLLOW_measureExpression_in_mulExpression191 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_mulExpression192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_function204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_function211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_function218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_function225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_function232 = new BitSet(new long[]{0x0000000000000002L});

}