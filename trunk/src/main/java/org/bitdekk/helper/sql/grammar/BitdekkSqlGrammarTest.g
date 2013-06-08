grammar BitdekkSqlGrammarTest;

stat	:	selectStatement EOF;

selectStatement
	:	'SELECT' columns 'FROM' IDENTIFIER ('WHERE' whereExpressions)? ('ORDER' 'BY' orderByColumns)? limitClause?;
orderByColumns
	:	a=stringName (c=('ASC'|'DESC'))? 
		(',' b=stringName (d=('ASC'|'DESC'))?)*;
limitClause
	:	'LIMIT' POS_INT ',' POS_INT;
columns	:	column  (',' column)*;
column	:	(a=dimension | b=aggregateMeasure) ('AS' stringName )?;
dimension
	:	a=IDENTIFIER;
aggregateMeasure
	:	a=groupedExpression;
whereExpressions
	:	whereExpression ('AND' whereExpression)*;
whereExpression
	:	dimensionCondition;
dimensionCondition
	:	(a=IDENTIFIER '=' b=IDENTIFIER )
	| 	(c=IDENTIFIER 'IN' ('(' d=stringName (',' d=stringName )*')'));
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
	|	'AVG' 
	|	'COUNT'
	|	'MAX'
	|	'MIN';
number	:	ADD_SUB? POS_INT('.'POS_INT)?;
//Lexer
OPERATOR:	'=' | '<' | '>' | '<>' | '<=' | '>=';
POS_INT	:	Digit+;
MUL_DIV	:	'*' | '/';
ADD_SUB	:	'+' | '-';
IDENTIFIER   :  Letter (Letter | Digit)*;
fragment     
Letter  :	'a'..'z' | '_' | 'A'..'Z';
fragment     
Digit   :  	'0'..'9';    
WS  	:   	( ' ' | '\t' | '\r' | '\n') {$channel=HIDDEN;};
stringName
	:	'"' (a=(ESC_SEQ | ~('\\'|'"')))* '"';
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

