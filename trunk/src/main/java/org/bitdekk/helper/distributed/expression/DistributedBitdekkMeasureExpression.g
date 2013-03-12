grammar DistributedBitdekkMeasureExpression;
@header
{
package org.bitdekk.helper.distributed.expression;

import org.bitdekk.helper.expression.model.MeasureExpression;
import org.bitdekk.helper.expression.model.GroupedMeasureExpression;
import org.bitdekk.aggregation.IAggregation;
import org.bitdekk.aggregation.impl.SumAggregation;
import org.bitdekk.aggregation.impl.AvgAggregation;
import org.bitdekk.aggregation.impl.CountAggregation;
import org.bitdekk.aggregation.impl.MaxAggregation;
import org.bitdekk.aggregation.impl.MinAggregation;
import org.bitdekk.helper.distributed.expression.model.FunctionExpression;
}
@lexer::header
{
package org.bitdekk.helper.distributed.expression;
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
	|	 function '(' measureExpression ')' {groupedTokens.add(new FunctionExpression($function.func, $function.text + "(" + $measureExpression.text + ")"));}
	|	'('groupedExpression')' {groupedTokens.addAll($groupedExpression.groupedTokens);};
measureExpression 
	:	a=addExpression (ADD_SUB b=addExpression)?;
addExpression
	:	a=mulExpression (MUL_DIV b=mulExpression)?;
mulExpression
	:	IDENTIFIER
	| 	NUMBER
	| 	'('measureExpression')';
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

