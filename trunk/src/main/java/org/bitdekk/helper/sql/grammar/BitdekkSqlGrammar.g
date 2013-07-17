grammar BitdekkSqlGrammar;
@header
{
package org.bitdekk.helper.sql.grammar;

import org.bitdekk.helper.sql.grammar.State;
import org.bitdekk.helper.sql.grammar.model.Measure;
import org.bitdekk.helper.sql.grammar.model.Dimension;
import org.bitdekk.helper.sql.grammar.model.OrderColumn;
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
	:	 a=stringName  (c=('ASC'|'DESC'))? {state1.addOrderByColumn($a.name,  $c.text == null || $c.text.equals("ASC"));} 
		(','  b=stringName  (d=('ASC'|'DESC'))? {state1.addOrderByColumn($b.name, $d.text == null || $d.text.equals("ASC"));})*;
limitClause
	:	('LIMIT' a=POS_INT {state1.setFromRowNumber(Integer.parseInt($a.text));} 
	',' b=POS_INT {state1.setToRowNumber(Integer.parseInt($b.text));});
column	:	(a=dimension ('AS'  b=stringName  )?) {state1.addColumn(new Dimension($a.name, b == null ? $a.name : $b.name));}
	| (c=aggregateMeasure ('AS' d=stringName)?) {
		if(c.expression != null)
			state1.addColumn(new Measure($c.text, d == null ? $c.text : $d.name));
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
	:	(a=IDENTIFIER '=' b=stringName {state1.addDimensionValue($a.text, $b.name);} )
	| 	(c=IDENTIFIER 'IN' ('('  d=stringName  {state1.addDimensionValue($c.text, $d.name);} 
			(','  e=stringName  {state1.addDimensionValue($c.text, $e.name);})* ')'));
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
stringName returns[String name] @init{StringBuffer buf = new StringBuffer();}
	:	'"' (a=(ESC_SEQ | ~('\\'|'"')){buf.append($a.text);})* '"' {$name=buf.toString();};
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
fragment
HEX_DIGIT : ('0'..'9'|'a'..'f'|'A'..'F') ;
fragment
ESC_SEQ
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   UNICODE_ESC
    |   OCTAL_ESC
    ;
fragment
OCTAL_ESC
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;

fragment
UNICODE_ESC
    :   '\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
    ;