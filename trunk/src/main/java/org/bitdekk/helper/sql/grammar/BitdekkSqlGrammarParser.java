// $ANTLR 3.3 Nov 30, 2010 12:45:30 C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g 2013-02-11 10:20:09

package org.bitdekk.helper.sql.grammar;

import org.antlr.runtime.BitSet;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;

public class BitdekkSqlGrammarParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "IDENTIFIER", "POS_INT", "ADD_SUB", "MUL_DIV", "OPERATOR", "Digit", "Letter", "WS", "'SELECT'", "'FROM'", "'WHERE'", "'ORDER'", "'BY'", "','", "'asc'", "'desc'", "'LIMIT'", "'AS'", "'AND'", "'='", "'\"'", "'in'", "'('", "')'", "'SUM'", "'AVG'", "'.'"
    };
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
    public static final int IDENTIFIER=4;
    public static final int POS_INT=5;
    public static final int ADD_SUB=6;
    public static final int MUL_DIV=7;
    public static final int OPERATOR=8;
    public static final int Digit=9;
    public static final int Letter=10;
    public static final int WS=11;

    // delegates
    // delegators


        public BitdekkSqlGrammarParser(TokenStream input, State state) {
            this(input, new RecognizerSharedState());
            this.state1 = state;
        }
        public BitdekkSqlGrammarParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return BitdekkSqlGrammarParser.tokenNames; }
    public String getGrammarFileName() { return "C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g"; }


    	private State state1;



    // $ANTLR start "stat"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:18:1: stat : selectStatement ;
    public final void stat() throws RecognitionException {
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:18:6: ( selectStatement )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:18:8: selectStatement
            {
            pushFollow(FOLLOW_selectStatement_in_stat28);
            selectStatement();

            state._fsp--;


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


    // $ANTLR start "selectStatement"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:20:1: selectStatement : 'SELECT' columns 'FROM' IDENTIFIER ( 'WHERE' whereExpressions )? ( 'ORDER' 'BY' orderByColumns )? ( limitClause )? ;
    public final void selectStatement() throws RecognitionException {
        Token IDENTIFIER1=null;

        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:21:2: ( 'SELECT' columns 'FROM' IDENTIFIER ( 'WHERE' whereExpressions )? ( 'ORDER' 'BY' orderByColumns )? ( limitClause )? )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:21:4: 'SELECT' columns 'FROM' IDENTIFIER ( 'WHERE' whereExpressions )? ( 'ORDER' 'BY' orderByColumns )? ( limitClause )?
            {
            match(input,12,FOLLOW_12_in_selectStatement37); 
            pushFollow(FOLLOW_columns_in_selectStatement39);
            columns();

            state._fsp--;

            match(input,13,FOLLOW_13_in_selectStatement41); 
            IDENTIFIER1=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_selectStatement43); 
            state1.setTableName((IDENTIFIER1!=null?IDENTIFIER1.getText():null));
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:21:79: ( 'WHERE' whereExpressions )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==14) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:21:80: 'WHERE' whereExpressions
                    {
                    match(input,14,FOLLOW_14_in_selectStatement47); 
                    pushFollow(FOLLOW_whereExpressions_in_selectStatement49);
                    whereExpressions();

                    state._fsp--;


                    }
                    break;

            }

            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:22:2: ( 'ORDER' 'BY' orderByColumns )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==15) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:22:3: 'ORDER' 'BY' orderByColumns
                    {
                    match(input,15,FOLLOW_15_in_selectStatement56); 
                    match(input,16,FOLLOW_16_in_selectStatement58); 
                    pushFollow(FOLLOW_orderByColumns_in_selectStatement60);
                    orderByColumns();

                    state._fsp--;


                    }
                    break;

            }

            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:22:33: ( limitClause )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==20) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:22:33: limitClause
                    {
                    pushFollow(FOLLOW_limitClause_in_selectStatement64);
                    limitClause();

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
    // $ANTLR end "selectStatement"


    // $ANTLR start "columns"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:23:1: columns : column ( ',' column )* ;
    public final void columns() throws RecognitionException {
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:23:9: ( column ( ',' column )* )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:23:11: column ( ',' column )*
            {
            pushFollow(FOLLOW_column_in_columns72);
            column();

            state._fsp--;

            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:23:19: ( ',' column )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==17) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:23:20: ',' column
            	    {
            	    match(input,17,FOLLOW_17_in_columns76); 
            	    pushFollow(FOLLOW_column_in_columns78);
            	    column();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


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
    // $ANTLR end "columns"


    // $ANTLR start "orderByColumns"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:24:1: orderByColumns : a= IDENTIFIER (c= ( 'asc' | 'desc' ) )? ( ',' b= IDENTIFIER (d= ( 'asc' | 'desc' ) )? )* ;
    public final void orderByColumns() throws RecognitionException {
        Token a=null;
        Token c=null;
        Token b=null;
        Token d=null;

        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:25:2: (a= IDENTIFIER (c= ( 'asc' | 'desc' ) )? ( ',' b= IDENTIFIER (d= ( 'asc' | 'desc' ) )? )* )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:25:4: a= IDENTIFIER (c= ( 'asc' | 'desc' ) )? ( ',' b= IDENTIFIER (d= ( 'asc' | 'desc' ) )? )*
            {
            a=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_orderByColumns90); 
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:25:17: (c= ( 'asc' | 'desc' ) )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( ((LA5_0>=18 && LA5_0<=19)) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:25:18: c= ( 'asc' | 'desc' )
                    {
                    c=(Token)input.LT(1);
                    if ( (input.LA(1)>=18 && input.LA(1)<=19) ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    }
                    break;

            }

            state1.addOrderByColumn((a!=null?a.getText():null), (c!=null?c.getText():null) == null || (c!=null?c.getText():null).equals("asc"));
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:26:3: ( ',' b= IDENTIFIER (d= ( 'asc' | 'desc' ) )? )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==17) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:26:4: ',' b= IDENTIFIER (d= ( 'asc' | 'desc' ) )?
            	    {
            	    match(input,17,FOLLOW_17_in_orderByColumns109); 
            	    b=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_orderByColumns112); 
            	    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:26:20: (d= ( 'asc' | 'desc' ) )?
            	    int alt6=2;
            	    int LA6_0 = input.LA(1);

            	    if ( ((LA6_0>=18 && LA6_0<=19)) ) {
            	        alt6=1;
            	    }
            	    switch (alt6) {
            	        case 1 :
            	            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:26:21: d= ( 'asc' | 'desc' )
            	            {
            	            d=(Token)input.LT(1);
            	            if ( (input.LA(1)>=18 && input.LA(1)<=19) ) {
            	                input.consume();
            	                state.errorRecovery=false;
            	            }
            	            else {
            	                MismatchedSetException mse = new MismatchedSetException(null,input);
            	                throw mse;
            	            }


            	            }
            	            break;

            	    }

            	    state1.addOrderByColumn((b!=null?b.getText():null), (d!=null?d.getText():null) == null || (d!=null?d.getText():null).equals("asc"));

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


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
    // $ANTLR end "orderByColumns"


    // $ANTLR start "limitClause"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:27:1: limitClause : ( 'LIMIT' a= POS_INT ',' b= POS_INT ) ;
    public final void limitClause() throws RecognitionException {
        Token a=null;
        Token b=null;

        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:28:2: ( ( 'LIMIT' a= POS_INT ',' b= POS_INT ) )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:28:4: ( 'LIMIT' a= POS_INT ',' b= POS_INT )
            {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:28:4: ( 'LIMIT' a= POS_INT ',' b= POS_INT )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:28:5: 'LIMIT' a= POS_INT ',' b= POS_INT
            {
            match(input,20,FOLLOW_20_in_limitClause136); 
            a=(Token)match(input,POS_INT,FOLLOW_POS_INT_in_limitClause140); 
            state1.setFromRowNumber(Integer.parseInt((a!=null?a.getText():null)));
            match(input,17,FOLLOW_17_in_limitClause146); 
            b=(Token)match(input,POS_INT,FOLLOW_POS_INT_in_limitClause150); 
            state1.setToRowNumber(Integer.parseInt((b!=null?b.getText():null)));

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
    // $ANTLR end "limitClause"


    // $ANTLR start "column"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:30:1: column : (a= dimension | b= aggregateMeasure );
    public final void column() throws RecognitionException {
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:30:8: (a= dimension | b= aggregateMeasure )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==IDENTIFIER) ) {
                alt8=1;
            }
            else if ( ((LA8_0>=POS_INT && LA8_0<=ADD_SUB)||LA8_0==26||(LA8_0>=28 && LA8_0<=29)) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:30:10: a= dimension
                    {
                    pushFollow(FOLLOW_dimension_in_column162);
                    dimension();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:30:24: b= aggregateMeasure
                    {
                    pushFollow(FOLLOW_aggregateMeasure_in_column168);
                    aggregateMeasure();

                    state._fsp--;


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
    // $ANTLR end "column"


    // $ANTLR start "dimension"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:31:1: dimension : a= IDENTIFIER ( 'AS' b= IDENTIFIER )? ;
    public final void dimension() throws RecognitionException {
        Token a=null;
        Token b=null;

        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:32:2: (a= IDENTIFIER ( 'AS' b= IDENTIFIER )? )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:32:4: a= IDENTIFIER ( 'AS' b= IDENTIFIER )?
            {
            a=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_dimension178); 
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:32:17: ( 'AS' b= IDENTIFIER )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==21) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:32:18: 'AS' b= IDENTIFIER
                    {
                    match(input,21,FOLLOW_21_in_dimension181); 
                    b=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_dimension185); 

                    }
                    break;

            }

            state1.addColumn(new Dimension((a!=null?a.getText():null), b == null ? (a!=null?a.getText():null) : (b!=null?b.getText():null)));

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
    // $ANTLR end "dimension"


    // $ANTLR start "aggregateMeasure"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:33:1: aggregateMeasure : a= groupedExpression ( 'AS' b= IDENTIFIER )? ;
    public final void aggregateMeasure() throws RecognitionException {
        Token b=null;
        BitdekkSqlGrammarParser.groupedExpression_return a = null;


        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:34:2: (a= groupedExpression ( 'AS' b= IDENTIFIER )? )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:34:4: a= groupedExpression ( 'AS' b= IDENTIFIER )?
            {
            pushFollow(FOLLOW_groupedExpression_in_aggregateMeasure199);
            a=groupedExpression();

            state._fsp--;

            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:34:24: ( 'AS' b= IDENTIFIER )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==21) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:34:25: 'AS' b= IDENTIFIER
                    {
                    match(input,21,FOLLOW_21_in_aggregateMeasure202); 
                    b=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_aggregateMeasure206); 

                    }
                    break;

            }

            state1.addColumn(new Measure((a!=null?input.toString(a.start,a.stop):null), b == null ? (a!=null?input.toString(a.start,a.stop):null) : (b!=null?b.getText():null)));

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
    // $ANTLR end "aggregateMeasure"


    // $ANTLR start "whereExpressions"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:36:1: whereExpressions : whereExpression ( 'AND' whereExpression )* ;
    public final void whereExpressions() throws RecognitionException {
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:37:2: ( whereExpression ( 'AND' whereExpression )* )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:37:4: whereExpression ( 'AND' whereExpression )*
            {
            pushFollow(FOLLOW_whereExpression_in_whereExpressions221);
            whereExpression();

            state._fsp--;

            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:37:20: ( 'AND' whereExpression )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==22) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:37:21: 'AND' whereExpression
            	    {
            	    match(input,22,FOLLOW_22_in_whereExpressions224); 
            	    pushFollow(FOLLOW_whereExpression_in_whereExpressions226);
            	    whereExpression();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);


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
    // $ANTLR end "whereExpressions"


    // $ANTLR start "whereExpression"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:38:1: whereExpression : dimensionCondition ;
    public final void whereExpression() throws RecognitionException {
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:39:2: ( dimensionCondition )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:39:4: dimensionCondition
            {
            pushFollow(FOLLOW_dimensionCondition_in_whereExpression236);
            dimensionCondition();

            state._fsp--;


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
    // $ANTLR end "whereExpression"


    // $ANTLR start "dimensionCondition"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:41:1: dimensionCondition : ( (a= IDENTIFIER '=' '\"' b= IDENTIFIER '\"' ) | (c= IDENTIFIER 'in' ( '(' '\"' d= IDENTIFIER '\"' ( ',' '\"' d= IDENTIFIER '\"' )* ')' ) ) );
    public final void dimensionCondition() throws RecognitionException {
        Token a=null;
        Token b=null;
        Token c=null;
        Token d=null;

        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:42:2: ( (a= IDENTIFIER '=' '\"' b= IDENTIFIER '\"' ) | (c= IDENTIFIER 'in' ( '(' '\"' d= IDENTIFIER '\"' ( ',' '\"' d= IDENTIFIER '\"' )* ')' ) ) )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==IDENTIFIER) ) {
                int LA13_1 = input.LA(2);

                if ( (LA13_1==23) ) {
                    alt13=1;
                }
                else if ( (LA13_1==25) ) {
                    alt13=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:42:4: (a= IDENTIFIER '=' '\"' b= IDENTIFIER '\"' )
                    {
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:42:4: (a= IDENTIFIER '=' '\"' b= IDENTIFIER '\"' )
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:42:5: a= IDENTIFIER '=' '\"' b= IDENTIFIER '\"'
                    {
                    a=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_dimensionCondition249); 
                    match(input,23,FOLLOW_23_in_dimensionCondition251); 
                    match(input,24,FOLLOW_24_in_dimensionCondition253); 
                    b=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_dimensionCondition257); 
                    state1.addDimensionValue((a!=null?a.getText():null), (b!=null?b.getText():null));
                    match(input,24,FOLLOW_24_in_dimensionCondition261); 

                    }


                    }
                    break;
                case 2 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:43:5: (c= IDENTIFIER 'in' ( '(' '\"' d= IDENTIFIER '\"' ( ',' '\"' d= IDENTIFIER '\"' )* ')' ) )
                    {
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:43:5: (c= IDENTIFIER 'in' ( '(' '\"' d= IDENTIFIER '\"' ( ',' '\"' d= IDENTIFIER '\"' )* ')' ) )
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:43:6: c= IDENTIFIER 'in' ( '(' '\"' d= IDENTIFIER '\"' ( ',' '\"' d= IDENTIFIER '\"' )* ')' )
                    {
                    c=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_dimensionCondition271); 
                    match(input,25,FOLLOW_25_in_dimensionCondition273); 
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:43:24: ( '(' '\"' d= IDENTIFIER '\"' ( ',' '\"' d= IDENTIFIER '\"' )* ')' )
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:43:25: '(' '\"' d= IDENTIFIER '\"' ( ',' '\"' d= IDENTIFIER '\"' )* ')'
                    {
                    match(input,26,FOLLOW_26_in_dimensionCondition276); 
                    match(input,24,FOLLOW_24_in_dimensionCondition278); 
                    d=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_dimensionCondition282); 
                    state1.addDimensionValue((b!=null?b.getText():null), (d!=null?d.getText():null));
                    match(input,24,FOLLOW_24_in_dimensionCondition286); 
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:44:4: ( ',' '\"' d= IDENTIFIER '\"' )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==17) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:44:5: ',' '\"' d= IDENTIFIER '\"'
                    	    {
                    	    match(input,17,FOLLOW_17_in_dimensionCondition293); 
                    	    match(input,24,FOLLOW_24_in_dimensionCondition295); 
                    	    d=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_dimensionCondition299); 
                    	    state1.addDimensionValue((c!=null?c.getText():null), (d!=null?d.getText():null));
                    	    match(input,24,FOLLOW_24_in_dimensionCondition303); 

                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);

                    match(input,27,FOLLOW_27_in_dimensionCondition307); 

                    }


                    }


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
    // $ANTLR end "dimensionCondition"

    public static class groupedExpression_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "groupedExpression"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:55:1: groupedExpression : a= groupedAddExpression ( ADD_SUB b= groupedAddExpression )* ;
    public final BitdekkSqlGrammarParser.groupedExpression_return groupedExpression() throws RecognitionException {
        BitdekkSqlGrammarParser.groupedExpression_return retval = new BitdekkSqlGrammarParser.groupedExpression_return();
        retval.start = input.LT(1);

        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:56:2: (a= groupedAddExpression ( ADD_SUB b= groupedAddExpression )* )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:56:4: a= groupedAddExpression ( ADD_SUB b= groupedAddExpression )*
            {
            pushFollow(FOLLOW_groupedAddExpression_in_groupedExpression321);
            groupedAddExpression();

            state._fsp--;

            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:56:27: ( ADD_SUB b= groupedAddExpression )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==ADD_SUB) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:56:28: ADD_SUB b= groupedAddExpression
            	    {
            	    match(input,ADD_SUB,FOLLOW_ADD_SUB_in_groupedExpression324); 
            	    pushFollow(FOLLOW_groupedAddExpression_in_groupedExpression328);
            	    groupedAddExpression();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


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
    // $ANTLR end "groupedExpression"


    // $ANTLR start "groupedAddExpression"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:57:1: groupedAddExpression : a= groupedMulExpression ( MUL_DIV b= groupedMulExpression )* ;
    public final void groupedAddExpression() throws RecognitionException {
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:58:2: (a= groupedMulExpression ( MUL_DIV b= groupedMulExpression )* )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:58:4: a= groupedMulExpression ( MUL_DIV b= groupedMulExpression )*
            {
            pushFollow(FOLLOW_groupedMulExpression_in_groupedAddExpression340);
            groupedMulExpression();

            state._fsp--;

            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:58:27: ( MUL_DIV b= groupedMulExpression )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==MUL_DIV) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:58:28: MUL_DIV b= groupedMulExpression
            	    {
            	    match(input,MUL_DIV,FOLLOW_MUL_DIV_in_groupedAddExpression343); 
            	    pushFollow(FOLLOW_groupedMulExpression_in_groupedAddExpression347);
            	    groupedMulExpression();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


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
    // $ANTLR end "groupedAddExpression"


    // $ANTLR start "groupedMulExpression"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:59:1: groupedMulExpression : ( number | function '(' measureExpression ')' | '(' groupedExpression ')' );
    public final void groupedMulExpression() throws RecognitionException {
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:60:2: ( number | function '(' measureExpression ')' | '(' groupedExpression ')' )
            int alt16=3;
            switch ( input.LA(1) ) {
            case POS_INT:
            case ADD_SUB:
                {
                alt16=1;
                }
                break;
            case 28:
            case 29:
                {
                alt16=2;
                }
                break;
            case 26:
                {
                alt16=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }

            switch (alt16) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:60:4: number
                    {
                    pushFollow(FOLLOW_number_in_groupedMulExpression357);
                    number();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:61:4: function '(' measureExpression ')'
                    {
                    pushFollow(FOLLOW_function_in_groupedMulExpression363);
                    function();

                    state._fsp--;

                    match(input,26,FOLLOW_26_in_groupedMulExpression365); 
                    pushFollow(FOLLOW_measureExpression_in_groupedMulExpression367);
                    measureExpression();

                    state._fsp--;

                    match(input,27,FOLLOW_27_in_groupedMulExpression370); 

                    }
                    break;
                case 3 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:62:4: '(' groupedExpression ')'
                    {
                    match(input,26,FOLLOW_26_in_groupedMulExpression375); 
                    pushFollow(FOLLOW_groupedExpression_in_groupedMulExpression376);
                    groupedExpression();

                    state._fsp--;

                    match(input,27,FOLLOW_27_in_groupedMulExpression377); 

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
    // $ANTLR end "groupedMulExpression"


    // $ANTLR start "measureExpression"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:63:1: measureExpression : a= addExpression ( MUL_DIV b= addExpression )* ;
    public final void measureExpression() throws RecognitionException {
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:64:2: (a= addExpression ( MUL_DIV b= addExpression )* )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:64:4: a= addExpression ( MUL_DIV b= addExpression )*
            {
            pushFollow(FOLLOW_addExpression_in_measureExpression388);
            addExpression();

            state._fsp--;

            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:64:20: ( MUL_DIV b= addExpression )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==MUL_DIV) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:64:21: MUL_DIV b= addExpression
            	    {
            	    match(input,MUL_DIV,FOLLOW_MUL_DIV_in_measureExpression391); 
            	    pushFollow(FOLLOW_addExpression_in_measureExpression395);
            	    addExpression();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);


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
    // $ANTLR end "measureExpression"


    // $ANTLR start "addExpression"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:65:1: addExpression : a= mulExpression ( ADD_SUB b= mulExpression )* ;
    public final void addExpression() throws RecognitionException {
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:66:2: (a= mulExpression ( ADD_SUB b= mulExpression )* )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:66:4: a= mulExpression ( ADD_SUB b= mulExpression )*
            {
            pushFollow(FOLLOW_mulExpression_in_addExpression407);
            mulExpression();

            state._fsp--;

            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:66:20: ( ADD_SUB b= mulExpression )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==ADD_SUB) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:66:21: ADD_SUB b= mulExpression
            	    {
            	    match(input,ADD_SUB,FOLLOW_ADD_SUB_in_addExpression410); 
            	    pushFollow(FOLLOW_mulExpression_in_addExpression414);
            	    mulExpression();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);


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
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:67:1: mulExpression : ( IDENTIFIER | number | '(' measureExpression ')' );
    public final void mulExpression() throws RecognitionException {
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:68:2: ( IDENTIFIER | number | '(' measureExpression ')' )
            int alt19=3;
            switch ( input.LA(1) ) {
            case IDENTIFIER:
                {
                alt19=1;
                }
                break;
            case POS_INT:
            case ADD_SUB:
                {
                alt19=2;
                }
                break;
            case 26:
                {
                alt19=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }

            switch (alt19) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:68:4: IDENTIFIER
                    {
                    match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_mulExpression424); 

                    }
                    break;
                case 2 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:69:5: number
                    {
                    pushFollow(FOLLOW_number_in_mulExpression432);
                    number();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:70:5: '(' measureExpression ')'
                    {
                    match(input,26,FOLLOW_26_in_mulExpression440); 
                    pushFollow(FOLLOW_measureExpression_in_mulExpression441);
                    measureExpression();

                    state._fsp--;

                    match(input,27,FOLLOW_27_in_mulExpression442); 

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


    // $ANTLR start "function"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:71:1: function : ( 'SUM' | 'AVG' );
    public final void function() throws RecognitionException {
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:72:2: ( 'SUM' | 'AVG' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:
            {
            if ( (input.LA(1)>=28 && input.LA(1)<=29) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
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
    // $ANTLR end "function"


    // $ANTLR start "number"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:74:1: number : ( ADD_SUB )? POS_INT ( '.' POS_INT )? ;
    public final void number() throws RecognitionException {
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:74:8: ( ( ADD_SUB )? POS_INT ( '.' POS_INT )? )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:74:10: ( ADD_SUB )? POS_INT ( '.' POS_INT )?
            {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:74:10: ( ADD_SUB )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==ADD_SUB) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:74:10: ADD_SUB
                    {
                    match(input,ADD_SUB,FOLLOW_ADD_SUB_in_number465); 

                    }
                    break;

            }

            match(input,POS_INT,FOLLOW_POS_INT_in_number468); 
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:74:26: ( '.' POS_INT )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==30) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:74:27: '.' POS_INT
                    {
                    match(input,30,FOLLOW_30_in_number470); 
                    match(input,POS_INT,FOLLOW_POS_INT_in_number471); 

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
    // $ANTLR end "number"

    // Delegated rules


 

    public static final BitSet FOLLOW_selectStatement_in_stat28 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_selectStatement37 = new BitSet(new long[]{0x0000000034000070L});
    public static final BitSet FOLLOW_columns_in_selectStatement39 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_selectStatement41 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_selectStatement43 = new BitSet(new long[]{0x000000000010C002L});
    public static final BitSet FOLLOW_14_in_selectStatement47 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_whereExpressions_in_selectStatement49 = new BitSet(new long[]{0x0000000000108002L});
    public static final BitSet FOLLOW_15_in_selectStatement56 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_selectStatement58 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_orderByColumns_in_selectStatement60 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_limitClause_in_selectStatement64 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_column_in_columns72 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_17_in_columns76 = new BitSet(new long[]{0x0000000034000070L});
    public static final BitSet FOLLOW_column_in_columns78 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_orderByColumns90 = new BitSet(new long[]{0x00000000000E0002L});
    public static final BitSet FOLLOW_set_in_orderByColumns95 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_17_in_orderByColumns109 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_orderByColumns112 = new BitSet(new long[]{0x00000000000E0002L});
    public static final BitSet FOLLOW_set_in_orderByColumns117 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_20_in_limitClause136 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_POS_INT_in_limitClause140 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_limitClause146 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_POS_INT_in_limitClause150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dimension_in_column162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_aggregateMeasure_in_column168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_dimension178 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_21_in_dimension181 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_dimension185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_groupedExpression_in_aggregateMeasure199 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_21_in_aggregateMeasure202 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_aggregateMeasure206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_whereExpression_in_whereExpressions221 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_22_in_whereExpressions224 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_whereExpression_in_whereExpressions226 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_dimensionCondition_in_whereExpression236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_dimensionCondition249 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_dimensionCondition251 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_dimensionCondition253 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_dimensionCondition257 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_dimensionCondition261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_dimensionCondition271 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_dimensionCondition273 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_dimensionCondition276 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_dimensionCondition278 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_dimensionCondition282 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_dimensionCondition286 = new BitSet(new long[]{0x0000000008020000L});
    public static final BitSet FOLLOW_17_in_dimensionCondition293 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_dimensionCondition295 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_dimensionCondition299 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_dimensionCondition303 = new BitSet(new long[]{0x0000000008020000L});
    public static final BitSet FOLLOW_27_in_dimensionCondition307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_groupedAddExpression_in_groupedExpression321 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_ADD_SUB_in_groupedExpression324 = new BitSet(new long[]{0x0000000034000070L});
    public static final BitSet FOLLOW_groupedAddExpression_in_groupedExpression328 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_groupedMulExpression_in_groupedAddExpression340 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_MUL_DIV_in_groupedAddExpression343 = new BitSet(new long[]{0x0000000034000070L});
    public static final BitSet FOLLOW_groupedMulExpression_in_groupedAddExpression347 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_number_in_groupedMulExpression357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_in_groupedMulExpression363 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_groupedMulExpression365 = new BitSet(new long[]{0x0000000004000070L});
    public static final BitSet FOLLOW_measureExpression_in_groupedMulExpression367 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_groupedMulExpression370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_groupedMulExpression375 = new BitSet(new long[]{0x0000000034000070L});
    public static final BitSet FOLLOW_groupedExpression_in_groupedMulExpression376 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_groupedMulExpression377 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_addExpression_in_measureExpression388 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_MUL_DIV_in_measureExpression391 = new BitSet(new long[]{0x0000000004000070L});
    public static final BitSet FOLLOW_addExpression_in_measureExpression395 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_mulExpression_in_addExpression407 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_ADD_SUB_in_addExpression410 = new BitSet(new long[]{0x0000000004000070L});
    public static final BitSet FOLLOW_mulExpression_in_addExpression414 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_IDENTIFIER_in_mulExpression424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_number_in_mulExpression432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_mulExpression440 = new BitSet(new long[]{0x0000000004000070L});
    public static final BitSet FOLLOW_measureExpression_in_mulExpression441 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_mulExpression442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_function0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ADD_SUB_in_number465 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_POS_INT_in_number468 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_30_in_number470 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_POS_INT_in_number471 = new BitSet(new long[]{0x0000000000000002L});

}