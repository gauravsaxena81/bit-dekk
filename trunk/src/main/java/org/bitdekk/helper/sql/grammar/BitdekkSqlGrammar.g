grammar BitdekkSqlGrammar;
@header
{
package org.bitdekk.helper.sql.grammar;

import org.bitdekk.helper.sql.grammar.State;
import org.bitdekk.helper.sql.grammar.Measure;
}
@lexer::header
{
package org.bitdekk.helper.sql.grammar;
}
@members
{
	private State state1;
	private boolean groupedExpressionFound = false;
}

stat	:	selectStatement EOF;

selectStatement
	:	'SELECT' columns 'FROM' IDENTIFIER{state1.setTableName($IDENTIFIER.text);} ('WHERE' whereExpressions)? 
	('HAVING' havingExpression ('AND' havingExpression)*)?  ('ORDER' 'BY' orderByColumns)? limitClause?;
havingExpression
	:	(a=aggregateMeasure b=LOGICAL_OPERATORS c=aggregateMeasure) {
		state1.addHavingExpression(new HavingExpression($a.text, $b.text, $c.text));
	};
columns	:	column  (',' column)*;
orderByColumns
	:	a=IDENTIFIER (c=('ASC'|'DESC'))? {state1.addOrderByColumn($a.text, $c.text == null || $c.text.equals("ASC"));} 
		(','b=IDENTIFIER (d=('ASC'|'DESC'))? {state1.addOrderByColumn($b.text, $d.text == null || $d.text.equals("ASC"));})*;
limitClause
	:	('LIMIT' a=POS_INT {state1.setFromRowNumber(Integer.parseInt($a.text));} 
	',' b=POS_INT {state1.setToRowNumber(Integer.parseInt($b.text));});
column	:	(a=dimension ('AS' b=IDENTIFIER)?) {state1.addColumn(new Dimension($a.name, $b == null ? $a.name : $b.text));}
	| (c=aggregateMeasure ('AS' d=IDENTIFIER)?) {
		if(c.expression != null)
			state1.addColumn(new Measure($c.text, $d == null ? $c.text : $d.text));
		else
			state1.groupedExpressionNotFound($c.text);
	};
dimension returns[String name]
	:	a=IDENTIFIER {$name = $a.text;};
aggregateMeasure returns[String expression] @init{groupedExpressionFound = false;}
	:	a=groupedExpression {
		if(groupedExpressionFound)
			$expression = $groupedExpression.text;
		else
			$expression = null;
	};
whereExpressions
	:	whereExpression ('AND' whereExpression)*;
whereExpression
	:	dimensionCondition
	;
dimensionCondition
	:	(a=IDENTIFIER '=' '"' b=IDENTIFIER {state1.addDimensionValue($a.text, $b.text);} '"')
	| 	(c=IDENTIFIER 'IN' ('(' '"' d=IDENTIFIER {state1.addDimensionValue($c.text, $d.text);} '"' 
			(',' '"' d=IDENTIFIER {state1.addDimensionValue($c.text, $d.text);} '"')* ')'));
groupedExpression
	:	a=groupedAddExpression (ADD_SUB b=groupedAddExpression)*;
groupedAddExpression
	:	a=groupedMulExpression (MUL_DIV b=groupedMulExpression)*;
groupedMulExpression
	:	number 
	|	(function '(' measureExpression  ')') {groupedExpressionFound = true;}
	|	'('groupedExpression')' ;
measureExpression
	:	a=addExpression (MUL_DIV b=addExpression)*;
addExpression
	:	a=mulExpression (ADD_SUB b=mulExpression)*;
mulExpression
	:	IDENTIFIER  
	| 	number  
	| 	'('measureExpression')' ;
function
	:	'SUM' 
	|	'AVG' 
	|	'COUNT'
	|	'MAX'
	|	'MIN';
number	:	ADD_SUB? POS_INT('.'POS_INT)?;
//Lexer
LOGICAL_OPERATORS
	:	'=' | '<' | '>' | '<>' | '<=' | '>=';
POS_INT	:	Digit+;
MUL_DIV	:	'*' | '/';
ADD_SUB	:	'+' | '-';
IDENTIFIER   :  Letter (Letter | Digit)*;
fragment     
Letter  : 'a'..'z' | '_' | 'A'..'Z';
fragment     
Digit  :  '0'..'9';    
WS  	:   ( ' ' | '\t' | '\r' | '\n') {$channel=HIDDEN;};