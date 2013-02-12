grammar BitdekkMeasureExpression;
@header
{
package org.bitdekk.helper.expression;

import org.bitdekk.helper.expression.MeasureExpression;
import org.bitdekk.aggregation.IAggregation;
import org.bitdekk.aggregation.impl.SumAggregation;
import org.bitdekk.aggregation.impl.AvgAggregation;
import org.bitdekk.aggregation.impl.CountAggregation;
import org.bitdekk.aggregation.impl.MaxAggregation;
import org.bitdekk.aggregation.impl.MinAggregation;
}
@lexer::header
{
package org.bitdekk.helper.expression;
}
@members
{
	private GroupedMeasureExpression gme;
}
stat	:	groupedExpression {gme.setGroupedTokens($groupedExpression.groupedTokens);} EOF;
groupedExpression returns [ArrayList<Object> groupedTokens] @init{groupedTokens = new ArrayList<Object>();}
	:	a=groupedAddExpression (ADD_SUB b=groupedAddExpression)? {
		if(b != null) {
			$groupedTokens.add($ADD_SUB.text); 
			$groupedTokens.addAll($a.groupedTokens); 
			$groupedTokens.addAll($b.groupedTokens);
		} else
			$groupedTokens.addAll($a.groupedTokens); 
	};
groupedAddExpression returns [ArrayList<Object> groupedTokens] @init{groupedTokens = new ArrayList<Object>();}
	:	a=groupedMulExpression (MUL_DIV b=groupedMulExpression)? {
		if(b != null) {
			$groupedTokens.add($MUL_DIV.text);
			$groupedTokens.addAll($a.groupedTokens);
			$groupedTokens.addAll($b.groupedTokens);
		} else
			$groupedTokens.addAll($a.groupedTokens);
	};
groupedMulExpression returns [ArrayList<Object> groupedTokens] @init{groupedTokens = new ArrayList<Object>();}
	:	 NUMBER {groupedTokens.add(Double.parseDouble($NUMBER.text));}
	|	function {groupedTokens.add($function.func);} '(' measureExpression {$function.func.setMeasureExpression($measureExpression.me);} ')'
	|	'('groupedExpression')' {groupedTokens.addAll($groupedExpression.groupedTokens);};
measureExpression returns[MeasureExpression me] @init{ArrayList<Object> tokens = new ArrayList<Object>();
							me = new MeasureExpression();
						}
	:	(a=addExpression (ADD_SUB b=addExpression)?) {
			if(b != null) {
				tokens.add($ADD_SUB.text); 
				tokens.addAll($a.tokens); 
				tokens.addAll($b.tokens);
			} else
				tokens.addAll($a.tokens);
			me.setTokens(tokens);
		};
addExpression returns [ArrayList<Object> tokens] @init{tokens = new ArrayList<Object>();}
	:	(a=mulExpression (MUL_DIV b=mulExpression)?{
			if(b != null) {
				$tokens.add($MUL_DIV.text);
				$tokens.addAll($a.tokens);
				$tokens.addAll($b.tokens);
			} else
				$tokens.addAll($a.tokens);
		});
mulExpression returns [ArrayList<Object> tokens] @init{tokens = new ArrayList<Object>();}
	:	IDENTIFIER {$tokens.add($IDENTIFIER.text);} 
	| 	NUMBER {$tokens.add(Double.parseDouble($NUMBER.text));} 
	| 	'('measureExpression')' {$tokens.addAll($measureExpression.me.getTokens());};
function returns [IAggregation func]
	:	'SUM' {$func = new SumAggregation();}
	|	'AVG' {$func = new AvgAggregation();}
	|	'COUNT' {$func = new CountAggregation();}
	|	'MAX' {$func = new MaxAggregation();}
	|	'MIN' {$func = new MinAggregation();};
//Lexer
OPERATOR:	'=' | '<' | '>' | '<>' | '<=' | '>=';
NUMBER	:	ADD_SUB? Digit+('.'Digit+)?;
MUL_DIV	:	'*' | '/';
ADD_SUB	:	'+' | '-';
IDENTIFIER   :  Letter (Letter | Digit)*;
fragment     
Letter  : 'a'..'z' | '_' | 'A'..'Z';
fragment     
Digit  :  '0'..'9';    
WS  	:   ( ' ' | '\t' | '\r' | '\n') {$channel=HIDDEN;};

