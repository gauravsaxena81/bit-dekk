// $ANTLR 3.3 Nov 30, 2010 12:45:30 C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g 2013-06-05 15:06:17

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
import org.bitdekk.helper.sql.grammar.model.Dimension;
import org.bitdekk.helper.sql.grammar.model.Measure;

public class BitdekkSqlGrammarParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "IDENTIFIER", "LOGICAL_OPERATORS", "POS_INT", "ADD_SUB", "MUL_DIV", "ESC_SEQ", "Digit", "Letter", "WS", "HEX_DIGIT", "UNICODE_ESC", "OCTAL_ESC", "'SELECT'", "'FROM'", "'WHERE'", "'HAVING'", "'AND'", "'ORDER'", "'BY'", "','", "'ASC'", "'DESC'", "'LIMIT'", "'AS'", "'='", "'IN'", "'('", "')'", "'SUM'", "'AVG'", "'COUNT'", "'MAX'", "'MIN'", "'.'", "'\"'", "'\\\\'"
    };
    public static final int EOF=-1;
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
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int IDENTIFIER=4;
    public static final int LOGICAL_OPERATORS=5;
    public static final int POS_INT=6;
    public static final int ADD_SUB=7;
    public static final int MUL_DIV=8;
    public static final int ESC_SEQ=9;
    public static final int Digit=10;
    public static final int Letter=11;
    public static final int WS=12;
    public static final int HEX_DIGIT=13;
    public static final int UNICODE_ESC=14;
    public static final int OCTAL_ESC=15;

    // delegates
    // delegators


        public BitdekkSqlGrammarParser(TokenStream input, State state1) {
            this(input, new RecognizerSharedState());
            this.state1 = state1;
        }
        public BitdekkSqlGrammarParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return BitdekkSqlGrammarParser.tokenNames; }
    public String getGrammarFileName() { return "C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g"; }


    	private State state1;
    	private boolean groupedExpressionFound = false;



    // $ANTLR start "stat"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:19:1: stat : selectStatement EOF ;
    public final void stat() throws RecognitionException {
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:19:6: ( selectStatement EOF )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:19:8: selectStatement EOF
            {
            pushFollow(FOLLOW_selectStatement_in_stat28);
            selectStatement();

            state._fsp--;

            match(input,EOF,FOLLOW_EOF_in_stat30); 

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
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:21:1: selectStatement : 'SELECT' columns 'FROM' IDENTIFIER ( 'WHERE' whereExpressions )? ( 'HAVING' havingExpression ( 'AND' havingExpression )* )? ( 'ORDER' 'BY' orderByColumns )? ( limitClause )? ;
    public final void selectStatement() throws RecognitionException {
        Token IDENTIFIER1=null;

        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:22:2: ( 'SELECT' columns 'FROM' IDENTIFIER ( 'WHERE' whereExpressions )? ( 'HAVING' havingExpression ( 'AND' havingExpression )* )? ( 'ORDER' 'BY' orderByColumns )? ( limitClause )? )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:22:4: 'SELECT' columns 'FROM' IDENTIFIER ( 'WHERE' whereExpressions )? ( 'HAVING' havingExpression ( 'AND' havingExpression )* )? ( 'ORDER' 'BY' orderByColumns )? ( limitClause )?
            {
            match(input,16,FOLLOW_16_in_selectStatement39); 
            pushFollow(FOLLOW_columns_in_selectStatement41);
            columns();

            state._fsp--;

            match(input,17,FOLLOW_17_in_selectStatement43); 
            IDENTIFIER1=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_selectStatement45); 
            state1.setTableName((IDENTIFIER1!=null?IDENTIFIER1.getText():null));
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:22:79: ( 'WHERE' whereExpressions )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==18) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:22:80: 'WHERE' whereExpressions
                    {
                    match(input,18,FOLLOW_18_in_selectStatement49); 
                    pushFollow(FOLLOW_whereExpressions_in_selectStatement51);
                    whereExpressions();

                    state._fsp--;


                    }
                    break;

            }

            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:23:2: ( 'HAVING' havingExpression ( 'AND' havingExpression )* )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==19) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:23:3: 'HAVING' havingExpression ( 'AND' havingExpression )*
                    {
                    match(input,19,FOLLOW_19_in_selectStatement58); 
                    pushFollow(FOLLOW_havingExpression_in_selectStatement60);
                    havingExpression();

                    state._fsp--;

                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:23:29: ( 'AND' havingExpression )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==20) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:23:30: 'AND' havingExpression
                    	    {
                    	    match(input,20,FOLLOW_20_in_selectStatement63); 
                    	    pushFollow(FOLLOW_havingExpression_in_selectStatement65);
                    	    havingExpression();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);


                    }
                    break;

            }

            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:23:58: ( 'ORDER' 'BY' orderByColumns )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==21) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:23:59: 'ORDER' 'BY' orderByColumns
                    {
                    match(input,21,FOLLOW_21_in_selectStatement73); 
                    match(input,22,FOLLOW_22_in_selectStatement75); 
                    pushFollow(FOLLOW_orderByColumns_in_selectStatement77);
                    orderByColumns();

                    state._fsp--;


                    }
                    break;

            }

            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:23:89: ( limitClause )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==26) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:23:89: limitClause
                    {
                    pushFollow(FOLLOW_limitClause_in_selectStatement81);
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


    // $ANTLR start "havingExpression"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:24:1: havingExpression : (a= aggregateMeasure b= LOGICAL_OPERATORS c= aggregateMeasure ) ;
    public final void havingExpression() throws RecognitionException {
        Token b=null;
        BitdekkSqlGrammarParser.aggregateMeasure_return a = null;

        BitdekkSqlGrammarParser.aggregateMeasure_return c = null;


        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:25:2: ( (a= aggregateMeasure b= LOGICAL_OPERATORS c= aggregateMeasure ) )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:25:4: (a= aggregateMeasure b= LOGICAL_OPERATORS c= aggregateMeasure )
            {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:25:4: (a= aggregateMeasure b= LOGICAL_OPERATORS c= aggregateMeasure )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:25:5: a= aggregateMeasure b= LOGICAL_OPERATORS c= aggregateMeasure
            {
            pushFollow(FOLLOW_aggregateMeasure_in_havingExpression93);
            a=aggregateMeasure();

            state._fsp--;

            b=(Token)match(input,LOGICAL_OPERATORS,FOLLOW_LOGICAL_OPERATORS_in_havingExpression97); 
            pushFollow(FOLLOW_aggregateMeasure_in_havingExpression101);
            c=aggregateMeasure();

            state._fsp--;


            }


            		state1.addHavingExpression(new HavingExpression((a!=null?input.toString(a.start,a.stop):null), (b!=null?b.getText():null), (c!=null?input.toString(c.start,c.stop):null)));
            	

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
    // $ANTLR end "havingExpression"


    // $ANTLR start "columns"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:28:1: columns : column ( ',' column )* ;
    public final void columns() throws RecognitionException {
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:28:9: ( column ( ',' column )* )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:28:11: column ( ',' column )*
            {
            pushFollow(FOLLOW_column_in_columns111);
            column();

            state._fsp--;

            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:28:19: ( ',' column )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==23) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:28:20: ',' column
            	    {
            	    match(input,23,FOLLOW_23_in_columns115); 
            	    pushFollow(FOLLOW_column_in_columns117);
            	    column();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop6;
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
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:29:1: orderByColumns : a= stringName (c= ( 'ASC' | 'DESC' ) )? ( ',' b= stringName (d= ( 'ASC' | 'DESC' ) )? )* ;
    public final void orderByColumns() throws RecognitionException {
        Token c=null;
        Token d=null;
        String a = null;

        String b = null;


        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:30:2: (a= stringName (c= ( 'ASC' | 'DESC' ) )? ( ',' b= stringName (d= ( 'ASC' | 'DESC' ) )? )* )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:30:5: a= stringName (c= ( 'ASC' | 'DESC' ) )? ( ',' b= stringName (d= ( 'ASC' | 'DESC' ) )? )*
            {
            pushFollow(FOLLOW_stringName_in_orderByColumns130);
            a=stringName();

            state._fsp--;

            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:30:19: (c= ( 'ASC' | 'DESC' ) )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( ((LA7_0>=24 && LA7_0<=25)) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:30:20: c= ( 'ASC' | 'DESC' )
                    {
                    c=(Token)input.LT(1);
                    if ( (input.LA(1)>=24 && input.LA(1)<=25) ) {
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

            state1.addOrderByColumn(a,  (c!=null?c.getText():null) == null || (c!=null?c.getText():null).equals("ASC"));
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:31:3: ( ',' b= stringName (d= ( 'ASC' | 'DESC' ) )? )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==23) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:31:4: ',' b= stringName (d= ( 'ASC' | 'DESC' ) )?
            	    {
            	    match(input,23,FOLLOW_23_in_orderByColumns150); 
            	    pushFollow(FOLLOW_stringName_in_orderByColumns155);
            	    b=stringName();

            	    state._fsp--;

            	    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:31:23: (d= ( 'ASC' | 'DESC' ) )?
            	    int alt8=2;
            	    int LA8_0 = input.LA(1);

            	    if ( ((LA8_0>=24 && LA8_0<=25)) ) {
            	        alt8=1;
            	    }
            	    switch (alt8) {
            	        case 1 :
            	            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:31:24: d= ( 'ASC' | 'DESC' )
            	            {
            	            d=(Token)input.LT(1);
            	            if ( (input.LA(1)>=24 && input.LA(1)<=25) ) {
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

            	    state1.addOrderByColumn(b, (d!=null?d.getText():null) == null || (d!=null?d.getText():null).equals("ASC"));

            	    }
            	    break;

            	default :
            	    break loop9;
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
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:32:1: limitClause : ( 'LIMIT' a= POS_INT ',' b= POS_INT ) ;
    public final void limitClause() throws RecognitionException {
        Token a=null;
        Token b=null;

        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:33:2: ( ( 'LIMIT' a= POS_INT ',' b= POS_INT ) )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:33:4: ( 'LIMIT' a= POS_INT ',' b= POS_INT )
            {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:33:4: ( 'LIMIT' a= POS_INT ',' b= POS_INT )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:33:5: 'LIMIT' a= POS_INT ',' b= POS_INT
            {
            match(input,26,FOLLOW_26_in_limitClause180); 
            a=(Token)match(input,POS_INT,FOLLOW_POS_INT_in_limitClause184); 
            state1.setFromRowNumber(Integer.parseInt((a!=null?a.getText():null)));
            match(input,23,FOLLOW_23_in_limitClause190); 
            b=(Token)match(input,POS_INT,FOLLOW_POS_INT_in_limitClause194); 
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
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:35:1: column : ( (a= dimension ( 'AS' b= stringName )? ) | (c= aggregateMeasure ( 'AS' d= stringName )? ) );
    public final void column() throws RecognitionException {
        String a = null;

        String b = null;

        BitdekkSqlGrammarParser.aggregateMeasure_return c = null;

        String d = null;


        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:35:8: ( (a= dimension ( 'AS' b= stringName )? ) | (c= aggregateMeasure ( 'AS' d= stringName )? ) )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==IDENTIFIER) ) {
                alt12=1;
            }
            else if ( ((LA12_0>=POS_INT && LA12_0<=ADD_SUB)||LA12_0==30||(LA12_0>=32 && LA12_0<=36)) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:35:10: (a= dimension ( 'AS' b= stringName )? )
                    {
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:35:10: (a= dimension ( 'AS' b= stringName )? )
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:35:11: a= dimension ( 'AS' b= stringName )?
                    {
                    pushFollow(FOLLOW_dimension_in_column207);
                    a=dimension();

                    state._fsp--;

                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:35:23: ( 'AS' b= stringName )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==27) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:35:24: 'AS' b= stringName
                            {
                            match(input,27,FOLLOW_27_in_column210); 
                            pushFollow(FOLLOW_stringName_in_column215);
                            b=stringName();

                            state._fsp--;


                            }
                            break;

                    }


                    }

                    state1.addColumn(new Dimension(a, b == null ? a : b));

                    }
                    break;
                case 2 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:36:4: (c= aggregateMeasure ( 'AS' d= stringName )? )
                    {
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:36:4: (c= aggregateMeasure ( 'AS' d= stringName )? )
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:36:5: c= aggregateMeasure ( 'AS' d= stringName )?
                    {
                    pushFollow(FOLLOW_aggregateMeasure_in_column230);
                    c=aggregateMeasure();

                    state._fsp--;

                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:36:24: ( 'AS' d= stringName )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==27) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:36:25: 'AS' d= stringName
                            {
                            match(input,27,FOLLOW_27_in_column233); 
                            pushFollow(FOLLOW_stringName_in_column237);
                            d=stringName();

                            state._fsp--;


                            }
                            break;

                    }


                    }


                    		if(c.expression != null)
                    			state1.addColumn(new Measure((c!=null?input.toString(c.start,c.stop):null), d == null ? (c!=null?input.toString(c.start,c.stop):null) : d));
                    		else
                    			state1.groupedExpressionNotFound((c!=null?input.toString(c.start,c.stop):null));
                    	

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
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:42:1: dimension returns [String name] : a= IDENTIFIER ;
    public final String dimension() throws RecognitionException {
        String name = null;

        Token a=null;

        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:43:2: (a= IDENTIFIER )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:43:4: a= IDENTIFIER
            {
            a=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_dimension255); 
            name = (a!=null?a.getText():null);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return name;
    }
    // $ANTLR end "dimension"

    public static class aggregateMeasure_return extends ParserRuleReturnScope {
        public String expression;
    };

    // $ANTLR start "aggregateMeasure"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:44:1: aggregateMeasure returns [String expression] : a= groupedExpression ;
    public final BitdekkSqlGrammarParser.aggregateMeasure_return aggregateMeasure() throws RecognitionException {
        BitdekkSqlGrammarParser.aggregateMeasure_return retval = new BitdekkSqlGrammarParser.aggregateMeasure_return();
        retval.start = input.LT(1);

        BitdekkSqlGrammarParser.groupedExpression_return a = null;


        groupedExpressionFound = false;
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:45:2: (a= groupedExpression )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:45:4: a= groupedExpression
            {
            pushFollow(FOLLOW_groupedExpression_in_aggregateMeasure274);
            a=groupedExpression();

            state._fsp--;


            		if(groupedExpressionFound)
            			retval.expression = (a!=null?input.toString(a.start,a.stop):null);
            		else
            			retval.expression = null;
            	

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
    // $ANTLR end "aggregateMeasure"


    // $ANTLR start "whereExpressions"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:51:1: whereExpressions : whereExpression ( 'AND' whereExpression )* ;
    public final void whereExpressions() throws RecognitionException {
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:52:2: ( whereExpression ( 'AND' whereExpression )* )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:52:4: whereExpression ( 'AND' whereExpression )*
            {
            pushFollow(FOLLOW_whereExpression_in_whereExpressions284);
            whereExpression();

            state._fsp--;

            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:52:20: ( 'AND' whereExpression )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==20) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:52:21: 'AND' whereExpression
            	    {
            	    match(input,20,FOLLOW_20_in_whereExpressions287); 
            	    pushFollow(FOLLOW_whereExpression_in_whereExpressions289);
            	    whereExpression();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop13;
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
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:53:1: whereExpression : dimensionCondition ;
    public final void whereExpression() throws RecognitionException {
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:54:2: ( dimensionCondition )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:54:4: dimensionCondition
            {
            pushFollow(FOLLOW_dimensionCondition_in_whereExpression299);
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
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:56:1: dimensionCondition : ( (a= IDENTIFIER '=' b= stringName ) | (c= IDENTIFIER 'IN' ( '(' d= stringName ( ',' e= stringName )* ')' ) ) );
    public final void dimensionCondition() throws RecognitionException {
        Token a=null;
        Token c=null;
        String b = null;

        String d = null;

        String e = null;


        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:57:2: ( (a= IDENTIFIER '=' b= stringName ) | (c= IDENTIFIER 'IN' ( '(' d= stringName ( ',' e= stringName )* ')' ) ) )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==IDENTIFIER) ) {
                int LA15_1 = input.LA(2);

                if ( (LA15_1==28) ) {
                    alt15=1;
                }
                else if ( (LA15_1==29) ) {
                    alt15=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 15, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:57:4: (a= IDENTIFIER '=' b= stringName )
                    {
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:57:4: (a= IDENTIFIER '=' b= stringName )
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:57:5: a= IDENTIFIER '=' b= stringName
                    {
                    a=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_dimensionCondition312); 
                    match(input,28,FOLLOW_28_in_dimensionCondition314); 
                    pushFollow(FOLLOW_stringName_in_dimensionCondition318);
                    b=stringName();

                    state._fsp--;

                    state1.addDimensionValue((a!=null?a.getText():null), b);

                    }


                    }
                    break;
                case 2 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:58:5: (c= IDENTIFIER 'IN' ( '(' d= stringName ( ',' e= stringName )* ')' ) )
                    {
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:58:5: (c= IDENTIFIER 'IN' ( '(' d= stringName ( ',' e= stringName )* ')' ) )
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:58:6: c= IDENTIFIER 'IN' ( '(' d= stringName ( ',' e= stringName )* ')' )
                    {
                    c=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_dimensionCondition331); 
                    match(input,29,FOLLOW_29_in_dimensionCondition333); 
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:58:24: ( '(' d= stringName ( ',' e= stringName )* ')' )
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:58:25: '(' d= stringName ( ',' e= stringName )* ')'
                    {
                    match(input,30,FOLLOW_30_in_dimensionCondition336); 
                    pushFollow(FOLLOW_stringName_in_dimensionCondition341);
                    d=stringName();

                    state._fsp--;

                    state1.addDimensionValue((c!=null?c.getText():null), d);
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:59:4: ( ',' e= stringName )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==23) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:59:5: ',' e= stringName
                    	    {
                    	    match(input,23,FOLLOW_23_in_dimensionCondition351); 
                    	    pushFollow(FOLLOW_stringName_in_dimensionCondition356);
                    	    e=stringName();

                    	    state._fsp--;

                    	    state1.addDimensionValue((c!=null?c.getText():null), e);

                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);

                    match(input,31,FOLLOW_31_in_dimensionCondition363); 

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
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:60:1: groupedExpression : a= groupedAddExpression ( ADD_SUB b= groupedAddExpression )* ;
    public final BitdekkSqlGrammarParser.groupedExpression_return groupedExpression() throws RecognitionException {
        BitdekkSqlGrammarParser.groupedExpression_return retval = new BitdekkSqlGrammarParser.groupedExpression_return();
        retval.start = input.LT(1);

        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:61:2: (a= groupedAddExpression ( ADD_SUB b= groupedAddExpression )* )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:61:4: a= groupedAddExpression ( ADD_SUB b= groupedAddExpression )*
            {
            pushFollow(FOLLOW_groupedAddExpression_in_groupedExpression375);
            groupedAddExpression();

            state._fsp--;

            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:61:27: ( ADD_SUB b= groupedAddExpression )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==ADD_SUB) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:61:28: ADD_SUB b= groupedAddExpression
            	    {
            	    match(input,ADD_SUB,FOLLOW_ADD_SUB_in_groupedExpression378); 
            	    pushFollow(FOLLOW_groupedAddExpression_in_groupedExpression382);
            	    groupedAddExpression();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop16;
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
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:62:1: groupedAddExpression : a= groupedMulExpression ( MUL_DIV b= groupedMulExpression )* ;
    public final void groupedAddExpression() throws RecognitionException {
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:63:2: (a= groupedMulExpression ( MUL_DIV b= groupedMulExpression )* )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:63:4: a= groupedMulExpression ( MUL_DIV b= groupedMulExpression )*
            {
            pushFollow(FOLLOW_groupedMulExpression_in_groupedAddExpression394);
            groupedMulExpression();

            state._fsp--;

            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:63:27: ( MUL_DIV b= groupedMulExpression )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==MUL_DIV) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:63:28: MUL_DIV b= groupedMulExpression
            	    {
            	    match(input,MUL_DIV,FOLLOW_MUL_DIV_in_groupedAddExpression397); 
            	    pushFollow(FOLLOW_groupedMulExpression_in_groupedAddExpression401);
            	    groupedMulExpression();

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
    // $ANTLR end "groupedAddExpression"


    // $ANTLR start "groupedMulExpression"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:64:1: groupedMulExpression : ( number | ( function '(' measureExpression ')' ) | '(' groupedExpression ')' );
    public final void groupedMulExpression() throws RecognitionException {
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:65:2: ( number | ( function '(' measureExpression ')' ) | '(' groupedExpression ')' )
            int alt18=3;
            switch ( input.LA(1) ) {
            case POS_INT:
            case ADD_SUB:
                {
                alt18=1;
                }
                break;
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
                {
                alt18=2;
                }
                break;
            case 30:
                {
                alt18=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }

            switch (alt18) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:65:4: number
                    {
                    pushFollow(FOLLOW_number_in_groupedMulExpression411);
                    number();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:66:4: ( function '(' measureExpression ')' )
                    {
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:66:4: ( function '(' measureExpression ')' )
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:66:5: function '(' measureExpression ')'
                    {
                    pushFollow(FOLLOW_function_in_groupedMulExpression418);
                    function();

                    state._fsp--;

                    match(input,30,FOLLOW_30_in_groupedMulExpression420); 
                    pushFollow(FOLLOW_measureExpression_in_groupedMulExpression422);
                    measureExpression();

                    state._fsp--;

                    match(input,31,FOLLOW_31_in_groupedMulExpression425); 

                    }

                    groupedExpressionFound = true;

                    }
                    break;
                case 3 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:67:4: '(' groupedExpression ')'
                    {
                    match(input,30,FOLLOW_30_in_groupedMulExpression433); 
                    pushFollow(FOLLOW_groupedExpression_in_groupedMulExpression434);
                    groupedExpression();

                    state._fsp--;

                    match(input,31,FOLLOW_31_in_groupedMulExpression435); 

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
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:68:1: measureExpression : a= addExpression ( MUL_DIV b= addExpression )* ;
    public final void measureExpression() throws RecognitionException {
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:69:2: (a= addExpression ( MUL_DIV b= addExpression )* )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:69:4: a= addExpression ( MUL_DIV b= addExpression )*
            {
            pushFollow(FOLLOW_addExpression_in_measureExpression446);
            addExpression();

            state._fsp--;

            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:69:20: ( MUL_DIV b= addExpression )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==MUL_DIV) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:69:21: MUL_DIV b= addExpression
            	    {
            	    match(input,MUL_DIV,FOLLOW_MUL_DIV_in_measureExpression449); 
            	    pushFollow(FOLLOW_addExpression_in_measureExpression453);
            	    addExpression();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop19;
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
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:70:1: addExpression : a= mulExpression ( ADD_SUB b= mulExpression )* ;
    public final void addExpression() throws RecognitionException {
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:71:2: (a= mulExpression ( ADD_SUB b= mulExpression )* )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:71:4: a= mulExpression ( ADD_SUB b= mulExpression )*
            {
            pushFollow(FOLLOW_mulExpression_in_addExpression465);
            mulExpression();

            state._fsp--;

            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:71:20: ( ADD_SUB b= mulExpression )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==ADD_SUB) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:71:21: ADD_SUB b= mulExpression
            	    {
            	    match(input,ADD_SUB,FOLLOW_ADD_SUB_in_addExpression468); 
            	    pushFollow(FOLLOW_mulExpression_in_addExpression472);
            	    mulExpression();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop20;
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
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:72:1: mulExpression : ( IDENTIFIER | number | '(' measureExpression ')' );
    public final void mulExpression() throws RecognitionException {
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:73:2: ( IDENTIFIER | number | '(' measureExpression ')' )
            int alt21=3;
            switch ( input.LA(1) ) {
            case IDENTIFIER:
                {
                alt21=1;
                }
                break;
            case POS_INT:
            case ADD_SUB:
                {
                alt21=2;
                }
                break;
            case 30:
                {
                alt21=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }

            switch (alt21) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:73:4: IDENTIFIER
                    {
                    match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_mulExpression482); 

                    }
                    break;
                case 2 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:74:5: number
                    {
                    pushFollow(FOLLOW_number_in_mulExpression490);
                    number();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:75:5: '(' measureExpression ')'
                    {
                    match(input,30,FOLLOW_30_in_mulExpression498); 
                    pushFollow(FOLLOW_measureExpression_in_mulExpression499);
                    measureExpression();

                    state._fsp--;

                    match(input,31,FOLLOW_31_in_mulExpression500); 

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
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:76:1: function : ( 'SUM' | 'AVG' | 'COUNT' | 'MAX' | 'MIN' );
    public final void function() throws RecognitionException {
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:77:2: ( 'SUM' | 'AVG' | 'COUNT' | 'MAX' | 'MIN' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:
            {
            if ( (input.LA(1)>=32 && input.LA(1)<=36) ) {
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
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:82:1: number : ( ADD_SUB )? POS_INT ( '.' POS_INT )? ;
    public final void number() throws RecognitionException {
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:82:8: ( ( ADD_SUB )? POS_INT ( '.' POS_INT )? )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:82:10: ( ADD_SUB )? POS_INT ( '.' POS_INT )?
            {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:82:10: ( ADD_SUB )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==ADD_SUB) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:82:10: ADD_SUB
                    {
                    match(input,ADD_SUB,FOLLOW_ADD_SUB_in_number538); 

                    }
                    break;

            }

            match(input,POS_INT,FOLLOW_POS_INT_in_number541); 
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:82:26: ( '.' POS_INT )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==37) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:82:27: '.' POS_INT
                    {
                    match(input,37,FOLLOW_37_in_number543); 
                    match(input,POS_INT,FOLLOW_POS_INT_in_number544); 

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


    // $ANTLR start "stringName"
    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:83:1: stringName returns [String name] : '\"' (a= ( ESC_SEQ | ~ ( '\\\\' | '\"' ) ) )* '\"' ;
    public final String stringName() throws RecognitionException {
        String name = null;

        Token a=null;

        StringBuffer buf = new StringBuffer();
        try {
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:84:2: ( '\"' (a= ( ESC_SEQ | ~ ( '\\\\' | '\"' ) ) )* '\"' )
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:84:4: '\"' (a= ( ESC_SEQ | ~ ( '\\\\' | '\"' ) ) )* '\"'
            {
            match(input,38,FOLLOW_38_in_stringName561); 
            // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:84:8: (a= ( ESC_SEQ | ~ ( '\\\\' | '\"' ) ) )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( ((LA24_0>=IDENTIFIER && LA24_0<=37)) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // C:\\Work\\Personal Workspace\\bitdekk\\src\\main\\java\\org\\bitdekk\\helper\\sql\\grammar\\BitdekkSqlGrammar.g:84:9: a= ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )
            	    {
            	    a=(Token)input.LT(1);
            	    if ( (input.LA(1)>=IDENTIFIER && input.LA(1)<=37) ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    buf.append((a!=null?a.getText():null));

            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

            match(input,38,FOLLOW_38_in_stringName582); 
            name =buf.toString();

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return name;
    }
    // $ANTLR end "stringName"

    // Delegated rules


 

    public static final BitSet FOLLOW_selectStatement_in_stat28 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_stat30 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_selectStatement39 = new BitSet(new long[]{0x0000001F400000D0L});
    public static final BitSet FOLLOW_columns_in_selectStatement41 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_selectStatement43 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_selectStatement45 = new BitSet(new long[]{0x00000000042C0002L});
    public static final BitSet FOLLOW_18_in_selectStatement49 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_whereExpressions_in_selectStatement51 = new BitSet(new long[]{0x0000000004280002L});
    public static final BitSet FOLLOW_19_in_selectStatement58 = new BitSet(new long[]{0x0000001F400000D0L});
    public static final BitSet FOLLOW_havingExpression_in_selectStatement60 = new BitSet(new long[]{0x0000000004300002L});
    public static final BitSet FOLLOW_20_in_selectStatement63 = new BitSet(new long[]{0x0000001F400000D0L});
    public static final BitSet FOLLOW_havingExpression_in_selectStatement65 = new BitSet(new long[]{0x0000000004300002L});
    public static final BitSet FOLLOW_21_in_selectStatement73 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_selectStatement75 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_orderByColumns_in_selectStatement77 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_limitClause_in_selectStatement81 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_aggregateMeasure_in_havingExpression93 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_LOGICAL_OPERATORS_in_havingExpression97 = new BitSet(new long[]{0x0000001F400000D0L});
    public static final BitSet FOLLOW_aggregateMeasure_in_havingExpression101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_column_in_columns111 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_23_in_columns115 = new BitSet(new long[]{0x0000001F400000D0L});
    public static final BitSet FOLLOW_column_in_columns117 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_stringName_in_orderByColumns130 = new BitSet(new long[]{0x0000000003800002L});
    public static final BitSet FOLLOW_set_in_orderByColumns136 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_23_in_orderByColumns150 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_stringName_in_orderByColumns155 = new BitSet(new long[]{0x0000000003800002L});
    public static final BitSet FOLLOW_set_in_orderByColumns161 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_26_in_limitClause180 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_POS_INT_in_limitClause184 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_limitClause190 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_POS_INT_in_limitClause194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dimension_in_column207 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_27_in_column210 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_stringName_in_column215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_aggregateMeasure_in_column230 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_27_in_column233 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_stringName_in_column237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_dimension255 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_groupedExpression_in_aggregateMeasure274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_whereExpression_in_whereExpressions284 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_20_in_whereExpressions287 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_whereExpression_in_whereExpressions289 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_dimensionCondition_in_whereExpression299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_dimensionCondition312 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_dimensionCondition314 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_stringName_in_dimensionCondition318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_dimensionCondition331 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_dimensionCondition333 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_dimensionCondition336 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_stringName_in_dimensionCondition341 = new BitSet(new long[]{0x0000000080800000L});
    public static final BitSet FOLLOW_23_in_dimensionCondition351 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_stringName_in_dimensionCondition356 = new BitSet(new long[]{0x0000000080800000L});
    public static final BitSet FOLLOW_31_in_dimensionCondition363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_groupedAddExpression_in_groupedExpression375 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_ADD_SUB_in_groupedExpression378 = new BitSet(new long[]{0x0000001F400000D0L});
    public static final BitSet FOLLOW_groupedAddExpression_in_groupedExpression382 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_groupedMulExpression_in_groupedAddExpression394 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_MUL_DIV_in_groupedAddExpression397 = new BitSet(new long[]{0x0000001F400000D0L});
    public static final BitSet FOLLOW_groupedMulExpression_in_groupedAddExpression401 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_number_in_groupedMulExpression411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_in_groupedMulExpression418 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_groupedMulExpression420 = new BitSet(new long[]{0x00000000400000D0L});
    public static final BitSet FOLLOW_measureExpression_in_groupedMulExpression422 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_groupedMulExpression425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_groupedMulExpression433 = new BitSet(new long[]{0x0000001F400000D0L});
    public static final BitSet FOLLOW_groupedExpression_in_groupedMulExpression434 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_groupedMulExpression435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_addExpression_in_measureExpression446 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_MUL_DIV_in_measureExpression449 = new BitSet(new long[]{0x00000000400000D0L});
    public static final BitSet FOLLOW_addExpression_in_measureExpression453 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_mulExpression_in_addExpression465 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_ADD_SUB_in_addExpression468 = new BitSet(new long[]{0x00000000400000D0L});
    public static final BitSet FOLLOW_mulExpression_in_addExpression472 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_IDENTIFIER_in_mulExpression482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_number_in_mulExpression490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_mulExpression498 = new BitSet(new long[]{0x00000000400000D0L});
    public static final BitSet FOLLOW_measureExpression_in_mulExpression499 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_mulExpression500 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_function0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ADD_SUB_in_number538 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_POS_INT_in_number541 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_37_in_number543 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_POS_INT_in_number544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_stringName561 = new BitSet(new long[]{0x0000007FFFFFFFF0L});
    public static final BitSet FOLLOW_set_in_stringName566 = new BitSet(new long[]{0x0000007FFFFFFFF0L});
    public static final BitSet FOLLOW_38_in_stringName582 = new BitSet(new long[]{0x0000000000000002L});

}