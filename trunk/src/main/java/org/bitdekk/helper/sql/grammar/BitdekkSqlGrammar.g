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
}

stat	:	selectStatement;

selectStatement
	:	'SELECT' columns 'FROM' IDENTIFIER{state1.setTableName($IDENTIFIER.text);} ('WHERE' whereExpressions)? 
	('ORDER' 'BY' orderByColumns)? limitClause?;
columns	:	column  (',' column)*;
orderByColumns
	:	a=IDENTIFIER (c=('asc'|'desc'))? {state1.addOrderByColumn($a.text, $c.text == null || $c.text.equals("asc"));} 
		(','b=IDENTIFIER (d=('asc'|'desc'))? {state1.addOrderByColumn($b.text, $d.text == null || $d.text.equals("asc"));})*;
limitClause
	:	('LIMIT' a=POS_INT {state1.setFromRowNumber(Integer.parseInt($a.text));} 
	',' b=POS_INT {state1.setToRowNumber(Integer.parseInt($b.text));});
column	:	a=dimension | b=aggregateMeasure;
dimension
	:	a=IDENTIFIER ('AS' b=IDENTIFIER)? {state1.addColumn(new Dimension($a.text, $b == null ? $a.text : $b.text));};
aggregateMeasure
	:	a=groupedExpression ('AS' b=IDENTIFIER)? 
		{state1.addColumn(new Measure($groupedExpression.text, $b == null ? $a.text : $b.text));};
whereExpressions
	:	whereExpression ('AND' whereExpression)*;
whereExpression
	:	dimensionCondition
	;
dimensionCondition
	:	(a=IDENTIFIER '=' '"' b=IDENTIFIER {state1.addDimensionValue($a.text, $b.text);} '"')
	| 	(c=IDENTIFIER 'in' ('(' '"' d=IDENTIFIER {state1.addDimensionValue($b.text, $d.text);} '"' 
			(',' '"' d=IDENTIFIER {state1.addDimensionValue($c.text, $d.text);} '"')* ')'));
/*measureExpression
	:	mulExpression (MUL_DIV mulExpression)?;
mulExpression
	:	addExpression (ADD_SUB addExpression)?;
addExpression
	:	IDENTIFIER
	| 	number
	|	function?'('measureExpression')';
function:	'SUM'
	|	'AVG';*/
groupedExpression
	:	a=groupedAddExpression (ADD_SUB b=groupedAddExpression)*;
groupedAddExpression
	:	a=groupedMulExpression (MUL_DIV b=groupedMulExpression)*;
groupedMulExpression
	:	number 
	|	function '(' measureExpression  ')'
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
	|	'AVG' ;
number	:	ADD_SUB? POS_INT('.'POS_INT)?;
//Lexer
OPERATOR:	'=' | '<' | '>' | '<>' | '<=' | '>=';
POS_INT	:	Digit+;
MUL_DIV	:	'*' | '/';
ADD_SUB	:	'+' | '-';
IDENTIFIER   :  Letter (Letter | Digit)*;
fragment     
Letter  : 'a'..'z' | '_' | 'A'..'Z';
fragment     
Digit  :  '0'..'9';    
WS  	:   ( ' ' | '\t' | '\r' | '\n') {$channel=HIDDEN;};