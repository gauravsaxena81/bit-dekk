// $ANTLR 3.3 Nov 30, 2010 12:45:30 C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g 2013-03-03 13:38:16

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
import org.bitdekk.aggregation.impl.CountAggregation;
import org.bitdekk.aggregation.impl.MaxAggregation;
import org.bitdekk.aggregation.impl.MinAggregation;
import org.bitdekk.aggregation.impl.SumAggregation;
import org.bitdekk.helper.expression.model.GroupedMeasureExpression;
import org.bitdekk.helper.expression.model.MeasureExpression;

public class BitdekkMeasureExpressionParser extends Parser {
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
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:23:1: stat : groupedExpression EOF ;
    public final void stat() throws RecognitionException {
        ArrayList<Object> groupedExpression1 = null;


        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:23:6: ( groupedExpression EOF )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:23:8: groupedExpression EOF
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
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:24:1: groupedExpression returns [ArrayList<Object> groupedTokens] : a= groupedAddExpression ( ADD_SUB b= groupedAddExpression )? ;
    public final ArrayList<Object> groupedExpression() throws RecognitionException {
        ArrayList<Object> groupedTokens = null;

        Token ADD_SUB2=null;
        ArrayList<Object> a = null;

        ArrayList<Object> b = null;


        groupedTokens = new ArrayList<Object>();
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:25:2: (a= groupedAddExpression ( ADD_SUB b= groupedAddExpression )? )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:25:4: a= groupedAddExpression ( ADD_SUB b= groupedAddExpression )?
            {
            pushFollow(FOLLOW_groupedAddExpression_in_groupedExpression49);
            a=groupedAddExpression();

            state._fsp--;

            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:25:27: ( ADD_SUB b= groupedAddExpression )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==ADD_SUB) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:25:28: ADD_SUB b= groupedAddExpression
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
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:33:1: groupedAddExpression returns [ArrayList<Object> groupedTokens] : a= groupedMulExpression ( MUL_DIV b= groupedMulExpression )? ;
    public final ArrayList<Object> groupedAddExpression() throws RecognitionException {
        ArrayList<Object> groupedTokens = null;

        Token MUL_DIV3=null;
        ArrayList<Object> a = null;

        ArrayList<Object> b = null;


        groupedTokens = new ArrayList<Object>();
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:34:2: (a= groupedMulExpression ( MUL_DIV b= groupedMulExpression )? )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:34:4: a= groupedMulExpression ( MUL_DIV b= groupedMulExpression )?
            {
            pushFollow(FOLLOW_groupedMulExpression_in_groupedAddExpression78);
            a=groupedMulExpression();

            state._fsp--;

            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:34:27: ( MUL_DIV b= groupedMulExpression )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==MUL_DIV) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:34:28: MUL_DIV b= groupedMulExpression
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
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:42:1: groupedMulExpression returns [ArrayList<Object> groupedTokens] : ( NUMBER | function '(' measureExpression ')' | '(' groupedExpression ')' );
    public final ArrayList<Object> groupedMulExpression() throws RecognitionException {
        ArrayList<Object> groupedTokens = null;

        Token NUMBER4=null;
        IAggregation function5 = null;

        MeasureExpression measureExpression6 = null;

        ArrayList<Object> groupedExpression7 = null;


        groupedTokens = new ArrayList<Object>();
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:43:2: ( NUMBER | function '(' measureExpression ')' | '(' groupedExpression ')' )
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
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:43:5: NUMBER
                    {
                    NUMBER4=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_groupedMulExpression106); 
                    groupedTokens.add(Double.parseDouble((NUMBER4!=null?NUMBER4.getText():null)));

                    }
                    break;
                case 2 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:44:4: function '(' measureExpression ')'
                    {
                    pushFollow(FOLLOW_function_in_groupedMulExpression113);
                    function5=function();

                    state._fsp--;

                    groupedTokens.add(function5);
                    match(input,12,FOLLOW_12_in_groupedMulExpression117); 
                    pushFollow(FOLLOW_measureExpression_in_groupedMulExpression119);
                    measureExpression6=measureExpression();

                    state._fsp--;

                    function5.setMeasureExpression(measureExpression6);
                    match(input,13,FOLLOW_13_in_groupedMulExpression123); 

                    }
                    break;
                case 3 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:45:4: '(' groupedExpression ')'
                    {
                    match(input,12,FOLLOW_12_in_groupedMulExpression128); 
                    pushFollow(FOLLOW_groupedExpression_in_groupedMulExpression129);
                    groupedExpression7=groupedExpression();

                    state._fsp--;

                    match(input,13,FOLLOW_13_in_groupedMulExpression130); 
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
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:46:1: measureExpression returns [MeasureExpression me] : (a= addExpression ( ADD_SUB b= addExpression )? ) ;
    public final MeasureExpression measureExpression() throws RecognitionException {
        MeasureExpression me = null;

        Token ADD_SUB8=null;
        ArrayList<Object> a = null;

        ArrayList<Object> b = null;


        ArrayList<Object> tokens = new ArrayList<Object>();
        							me = new MeasureExpression();
        						
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:49:2: ( (a= addExpression ( ADD_SUB b= addExpression )? ) )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:49:4: (a= addExpression ( ADD_SUB b= addExpression )? )
            {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:49:4: (a= addExpression ( ADD_SUB b= addExpression )? )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:49:5: a= addExpression ( ADD_SUB b= addExpression )?
            {
            pushFollow(FOLLOW_addExpression_in_measureExpression150);
            a=addExpression();

            state._fsp--;

            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:49:21: ( ADD_SUB b= addExpression )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==ADD_SUB) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:49:22: ADD_SUB b= addExpression
                    {
                    ADD_SUB8=(Token)match(input,ADD_SUB,FOLLOW_ADD_SUB_in_measureExpression153); 
                    pushFollow(FOLLOW_addExpression_in_measureExpression157);
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
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:58:1: addExpression returns [ArrayList<Object> tokens] : (a= mulExpression ( MUL_DIV b= mulExpression )? ) ;
    public final ArrayList<Object> addExpression() throws RecognitionException {
        ArrayList<Object> tokens = null;

        Token MUL_DIV9=null;
        ArrayList<Object> a = null;

        ArrayList<Object> b = null;


        tokens = new ArrayList<Object>();
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:59:2: ( (a= mulExpression ( MUL_DIV b= mulExpression )? ) )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:59:4: (a= mulExpression ( MUL_DIV b= mulExpression )? )
            {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:59:4: (a= mulExpression ( MUL_DIV b= mulExpression )? )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:59:5: a= mulExpression ( MUL_DIV b= mulExpression )?
            {
            pushFollow(FOLLOW_mulExpression_in_addExpression181);
            a=mulExpression();

            state._fsp--;

            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:59:21: ( MUL_DIV b= mulExpression )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==MUL_DIV) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:59:22: MUL_DIV b= mulExpression
                    {
                    MUL_DIV9=(Token)match(input,MUL_DIV,FOLLOW_MUL_DIV_in_addExpression184); 
                    pushFollow(FOLLOW_mulExpression_in_addExpression188);
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
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:67:1: mulExpression returns [ArrayList<Object> tokens] : ( IDENTIFIER | NUMBER | '(' measureExpression ')' );
    public final ArrayList<Object> mulExpression() throws RecognitionException {
        ArrayList<Object> tokens = null;

        Token IDENTIFIER10=null;
        Token NUMBER11=null;
        MeasureExpression measureExpression12 = null;


        tokens = new ArrayList<Object>();
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:68:2: ( IDENTIFIER | NUMBER | '(' measureExpression ')' )
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
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:68:4: IDENTIFIER
                    {
                    IDENTIFIER10=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_mulExpression208); 
                    tokens.add((IDENTIFIER10!=null?IDENTIFIER10.getText():null));

                    }
                    break;
                case 2 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:69:5: NUMBER
                    {
                    NUMBER11=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_mulExpression217); 
                    tokens.add(Double.parseDouble((NUMBER11!=null?NUMBER11.getText():null)));

                    }
                    break;
                case 3 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:70:5: '(' measureExpression ')'
                    {
                    match(input,12,FOLLOW_12_in_mulExpression226); 
                    pushFollow(FOLLOW_measureExpression_in_mulExpression227);
                    measureExpression12=measureExpression();

                    state._fsp--;

                    match(input,13,FOLLOW_13_in_mulExpression228); 
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
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:71:1: function returns [IAggregation func] : ( 'SUM' | 'AVG' | 'COUNT' | 'MAX' | 'MIN' );
    public final IAggregation function() throws RecognitionException {
        IAggregation func = null;

        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:72:2: ( 'SUM' | 'AVG' | 'COUNT' | 'MAX' | 'MIN' )
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
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:72:4: 'SUM'
                    {
                    match(input,14,FOLLOW_14_in_function242); 
                    func = new SumAggregation();

                    }
                    break;
                case 2 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:73:4: 'AVG'
                    {
                    match(input,15,FOLLOW_15_in_function249); 
                    func = new AvgAggregation();

                    }
                    break;
                case 3 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:74:4: 'COUNT'
                    {
                    match(input,16,FOLLOW_16_in_function256); 
                    func = new CountAggregation();

                    }
                    break;
                case 4 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:75:4: 'MAX'
                    {
                    match(input,17,FOLLOW_17_in_function263); 
                    func = new MaxAggregation();

                    }
                    break;
                case 5 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\expression\\BitdekkMeasureExpression.g:76:4: 'MIN'
                    {
                    match(input,18,FOLLOW_18_in_function270); 
                    func = new MinAggregation();

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


 

    public static final BitSet FOLLOW_groupedExpression_in_stat27 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_stat31 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_groupedAddExpression_in_groupedExpression49 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_ADD_SUB_in_groupedExpression52 = new BitSet(new long[]{0x000000000007D040L});
    public static final BitSet FOLLOW_groupedAddExpression_in_groupedExpression56 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_groupedMulExpression_in_groupedAddExpression78 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_MUL_DIV_in_groupedAddExpression81 = new BitSet(new long[]{0x000000000007D040L});
    public static final BitSet FOLLOW_groupedMulExpression_in_groupedAddExpression85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_groupedMulExpression106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_in_groupedMulExpression113 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_groupedMulExpression117 = new BitSet(new long[]{0x00000000000010C0L});
    public static final BitSet FOLLOW_measureExpression_in_groupedMulExpression119 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_groupedMulExpression123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_groupedMulExpression128 = new BitSet(new long[]{0x000000000007D040L});
    public static final BitSet FOLLOW_groupedExpression_in_groupedMulExpression129 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_groupedMulExpression130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_addExpression_in_measureExpression150 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_ADD_SUB_in_measureExpression153 = new BitSet(new long[]{0x00000000000010C0L});
    public static final BitSet FOLLOW_addExpression_in_measureExpression157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_mulExpression_in_addExpression181 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_MUL_DIV_in_addExpression184 = new BitSet(new long[]{0x00000000000010C0L});
    public static final BitSet FOLLOW_mulExpression_in_addExpression188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_mulExpression208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_mulExpression217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_mulExpression226 = new BitSet(new long[]{0x00000000000010C0L});
    public static final BitSet FOLLOW_measureExpression_in_mulExpression227 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_mulExpression228 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_function242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_function249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_function256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_function263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_function270 = new BitSet(new long[]{0x0000000000000002L});

}