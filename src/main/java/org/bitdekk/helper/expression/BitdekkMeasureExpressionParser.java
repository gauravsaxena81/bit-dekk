// $ANTLR 3.3 Nov 30, 2010 12:45:30 C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g 2013-02-11 10:04:31

package org.bitdekk.helper.expression;

import java.util.ArrayList;

import org.antlr.runtime.BitSet;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.bitdekk.aggregation.IAggregation;
import org.bitdekk.aggregation.impl.AvgAggregation;
import org.bitdekk.aggregation.impl.SumAggregation;

public class BitdekkMeasureExpressionParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ADD_SUB", "MUL_DIV", "NUMBER", "IDENTIFIER", "OPERATOR", "Digit", "Letter", "WS", "'('", "')'", "'SUM'", "'AVG'"
    };
    public static final int EOF=-1;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int T__15=15;
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


        public BitdekkMeasureExpressionParser(TokenStream input, GroupedMeasureExpression gme) {
            this(input, new RecognizerSharedState());
            this.gme = gme;
        }
        public BitdekkMeasureExpressionParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return BitdekkMeasureExpressionParser.tokenNames; }
    public String getGrammarFileName() { return "C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g"; }


    	private GroupedMeasureExpression gme;



    // $ANTLR start "stat"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:19:1: stat : groupedExpression ;
    public final void stat() throws RecognitionException {
        ArrayList<Object> groupedExpression1 = null;


        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:19:6: ( groupedExpression )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:19:8: groupedExpression
            {
            pushFollow(FOLLOW_groupedExpression_in_stat27);
            groupedExpression1=groupedExpression();

            state._fsp--;

            gme.setGroupedTokens(groupedExpression1);

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
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:20:1: groupedExpression returns [ArrayList<Object> groupedTokens] : a= groupedAddExpression ( ADD_SUB b= groupedAddExpression )? ;
    public final ArrayList<Object> groupedExpression() throws RecognitionException {
        ArrayList<Object> groupedTokens = null;

        Token ADD_SUB2=null;
        ArrayList<Object> a = null;

        ArrayList<Object> b = null;


        groupedTokens = new ArrayList<Object>();
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:21:2: (a= groupedAddExpression ( ADD_SUB b= groupedAddExpression )? )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:21:4: a= groupedAddExpression ( ADD_SUB b= groupedAddExpression )?
            {
            pushFollow(FOLLOW_groupedAddExpression_in_groupedExpression47);
            a=groupedAddExpression();

            state._fsp--;

            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:21:27: ( ADD_SUB b= groupedAddExpression )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==ADD_SUB) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:21:28: ADD_SUB b= groupedAddExpression
                    {
                    ADD_SUB2=(Token)match(input,ADD_SUB,FOLLOW_ADD_SUB_in_groupedExpression50); 
                    pushFollow(FOLLOW_groupedAddExpression_in_groupedExpression54);
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
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:29:1: groupedAddExpression returns [ArrayList<Object> groupedTokens] : a= groupedMulExpression ( MUL_DIV b= groupedMulExpression )? ;
    public final ArrayList<Object> groupedAddExpression() throws RecognitionException {
        ArrayList<Object> groupedTokens = null;

        Token MUL_DIV3=null;
        ArrayList<Object> a = null;

        ArrayList<Object> b = null;


        groupedTokens = new ArrayList<Object>();
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:30:2: (a= groupedMulExpression ( MUL_DIV b= groupedMulExpression )? )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:30:4: a= groupedMulExpression ( MUL_DIV b= groupedMulExpression )?
            {
            pushFollow(FOLLOW_groupedMulExpression_in_groupedAddExpression76);
            a=groupedMulExpression();

            state._fsp--;

            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:30:27: ( MUL_DIV b= groupedMulExpression )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==MUL_DIV) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:30:28: MUL_DIV b= groupedMulExpression
                    {
                    MUL_DIV3=(Token)match(input,MUL_DIV,FOLLOW_MUL_DIV_in_groupedAddExpression79); 
                    pushFollow(FOLLOW_groupedMulExpression_in_groupedAddExpression83);
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
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:38:1: groupedMulExpression returns [ArrayList<Object> groupedTokens] : ( NUMBER | function '(' measureExpression ')' | '(' groupedExpression ')' );
    public final ArrayList<Object> groupedMulExpression() throws RecognitionException {
        ArrayList<Object> groupedTokens = null;

        Token NUMBER4=null;
        IAggregation function5 = null;

        MeasureExpression measureExpression6 = null;

        ArrayList<Object> groupedExpression7 = null;


        groupedTokens = new ArrayList<Object>();
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:39:2: ( NUMBER | function '(' measureExpression ')' | '(' groupedExpression ')' )
            int alt3=3;
            switch ( input.LA(1) ) {
            case NUMBER:
                {
                alt3=1;
                }
                break;
            case 14:
            case 15:
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
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:39:5: NUMBER
                    {
                    NUMBER4=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_groupedMulExpression104); 
                    groupedTokens.add(Double.parseDouble((NUMBER4!=null?NUMBER4.getText():null)));

                    }
                    break;
                case 2 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:40:4: function '(' measureExpression ')'
                    {
                    pushFollow(FOLLOW_function_in_groupedMulExpression111);
                    function5=function();

                    state._fsp--;

                    groupedTokens.add(function5);
                    match(input,12,FOLLOW_12_in_groupedMulExpression115); 
                    pushFollow(FOLLOW_measureExpression_in_groupedMulExpression117);
                    measureExpression6=measureExpression();

                    state._fsp--;

                    function5.setMeasureExpression(measureExpression6);
                    match(input,13,FOLLOW_13_in_groupedMulExpression121); 

                    }
                    break;
                case 3 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:41:4: '(' groupedExpression ')'
                    {
                    match(input,12,FOLLOW_12_in_groupedMulExpression126); 
                    pushFollow(FOLLOW_groupedExpression_in_groupedMulExpression127);
                    groupedExpression7=groupedExpression();

                    state._fsp--;

                    match(input,13,FOLLOW_13_in_groupedMulExpression128); 
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


    // $ANTLR start "measureExpression"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:42:1: measureExpression returns [MeasureExpression me] : (a= addExpression ( ADD_SUB b= addExpression )? ) ;
    public final MeasureExpression measureExpression() throws RecognitionException {
        MeasureExpression me = null;

        Token ADD_SUB8=null;
        ArrayList<Object> a = null;

        ArrayList<Object> b = null;


        ArrayList<Object> tokens = new ArrayList<Object>();
        							me = new MeasureExpression();
        						
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:45:2: ( (a= addExpression ( ADD_SUB b= addExpression )? ) )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:45:4: (a= addExpression ( ADD_SUB b= addExpression )? )
            {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:45:4: (a= addExpression ( ADD_SUB b= addExpression )? )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:45:5: a= addExpression ( ADD_SUB b= addExpression )?
            {
            pushFollow(FOLLOW_addExpression_in_measureExpression148);
            a=addExpression();

            state._fsp--;

            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:45:21: ( ADD_SUB b= addExpression )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==ADD_SUB) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:45:22: ADD_SUB b= addExpression
                    {
                    ADD_SUB8=(Token)match(input,ADD_SUB,FOLLOW_ADD_SUB_in_measureExpression151); 
                    pushFollow(FOLLOW_addExpression_in_measureExpression155);
                    b=addExpression();

                    state._fsp--;


                    }
                    break;

            }


            }


            			if(b != null) {
            				tokens.add((ADD_SUB8!=null?ADD_SUB8.getText():null)); 
            				tokens.addAll(a); 
            				tokens.addAll(b);
            			} else
            				tokens.addAll(a);
            			me.setTokens(tokens);
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return me;
    }
    // $ANTLR end "measureExpression"


    // $ANTLR start "addExpression"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:54:1: addExpression returns [ArrayList<Object> tokens] : (a= mulExpression ( MUL_DIV b= mulExpression )? ) ;
    public final ArrayList<Object> addExpression() throws RecognitionException {
        ArrayList<Object> tokens = null;

        Token MUL_DIV9=null;
        ArrayList<Object> a = null;

        ArrayList<Object> b = null;


        tokens = new ArrayList<Object>();
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:55:2: ( (a= mulExpression ( MUL_DIV b= mulExpression )? ) )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:55:4: (a= mulExpression ( MUL_DIV b= mulExpression )? )
            {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:55:4: (a= mulExpression ( MUL_DIV b= mulExpression )? )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:55:5: a= mulExpression ( MUL_DIV b= mulExpression )?
            {
            pushFollow(FOLLOW_mulExpression_in_addExpression179);
            a=mulExpression();

            state._fsp--;

            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:55:21: ( MUL_DIV b= mulExpression )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==MUL_DIV) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:55:22: MUL_DIV b= mulExpression
                    {
                    MUL_DIV9=(Token)match(input,MUL_DIV,FOLLOW_MUL_DIV_in_addExpression182); 
                    pushFollow(FOLLOW_mulExpression_in_addExpression186);
                    b=mulExpression();

                    state._fsp--;


                    }
                    break;

            }


            			if(b != null) {
            				tokens.add((MUL_DIV9!=null?MUL_DIV9.getText():null));
            				tokens.addAll(a);
            				tokens.addAll(b);
            			} else
            				tokens.addAll(a);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return tokens;
    }
    // $ANTLR end "addExpression"


    // $ANTLR start "mulExpression"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:63:1: mulExpression returns [ArrayList<Object> tokens] : ( IDENTIFIER | NUMBER | '(' measureExpression ')' );
    public final ArrayList<Object> mulExpression() throws RecognitionException {
        ArrayList<Object> tokens = null;

        Token IDENTIFIER10=null;
        Token NUMBER11=null;
        MeasureExpression measureExpression12 = null;


        tokens = new ArrayList<Object>();
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:64:2: ( IDENTIFIER | NUMBER | '(' measureExpression ')' )
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
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:64:4: IDENTIFIER
                    {
                    IDENTIFIER10=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_mulExpression206); 
                    tokens.add((IDENTIFIER10!=null?IDENTIFIER10.getText():null));

                    }
                    break;
                case 2 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:65:5: NUMBER
                    {
                    NUMBER11=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_mulExpression215); 
                    tokens.add(Double.parseDouble((NUMBER11!=null?NUMBER11.getText():null)));

                    }
                    break;
                case 3 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:66:5: '(' measureExpression ')'
                    {
                    match(input,12,FOLLOW_12_in_mulExpression224); 
                    pushFollow(FOLLOW_measureExpression_in_mulExpression225);
                    measureExpression12=measureExpression();

                    state._fsp--;

                    match(input,13,FOLLOW_13_in_mulExpression226); 
                    tokens.addAll(measureExpression12.getTokens());

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
        return tokens;
    }
    // $ANTLR end "mulExpression"


    // $ANTLR start "function"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:67:1: function returns [IAggregation func] : ( 'SUM' | 'AVG' );
    public final IAggregation function() throws RecognitionException {
        IAggregation func = null;

        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:68:2: ( 'SUM' | 'AVG' )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==14) ) {
                alt7=1;
            }
            else if ( (LA7_0==15) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:68:4: 'SUM'
                    {
                    match(input,14,FOLLOW_14_in_function240); 
                    func = new SumAggregation();

                    }
                    break;
                case 2 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:69:4: 'AVG'
                    {
                    match(input,15,FOLLOW_15_in_function247); 
                    func = new AvgAggregation();

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
        return func;
    }
    // $ANTLR end "function"

    // Delegated rules


 

    public static final BitSet FOLLOW_groupedExpression_in_stat27 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_groupedAddExpression_in_groupedExpression47 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_ADD_SUB_in_groupedExpression50 = new BitSet(new long[]{0x000000000000D040L});
    public static final BitSet FOLLOW_groupedAddExpression_in_groupedExpression54 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_groupedMulExpression_in_groupedAddExpression76 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_MUL_DIV_in_groupedAddExpression79 = new BitSet(new long[]{0x000000000000D040L});
    public static final BitSet FOLLOW_groupedMulExpression_in_groupedAddExpression83 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_groupedMulExpression104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_in_groupedMulExpression111 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_groupedMulExpression115 = new BitSet(new long[]{0x00000000000010C0L});
    public static final BitSet FOLLOW_measureExpression_in_groupedMulExpression117 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_groupedMulExpression121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_groupedMulExpression126 = new BitSet(new long[]{0x000000000000D040L});
    public static final BitSet FOLLOW_groupedExpression_in_groupedMulExpression127 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_groupedMulExpression128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_addExpression_in_measureExpression148 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_ADD_SUB_in_measureExpression151 = new BitSet(new long[]{0x00000000000010C0L});
    public static final BitSet FOLLOW_addExpression_in_measureExpression155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_mulExpression_in_addExpression179 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_MUL_DIV_in_addExpression182 = new BitSet(new long[]{0x00000000000010C0L});
    public static final BitSet FOLLOW_mulExpression_in_addExpression186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_mulExpression206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_mulExpression215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_mulExpression224 = new BitSet(new long[]{0x00000000000010C0L});
    public static final BitSet FOLLOW_measureExpression_in_mulExpression225 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_mulExpression226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_function240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_function247 = new BitSet(new long[]{0x0000000000000002L});

}