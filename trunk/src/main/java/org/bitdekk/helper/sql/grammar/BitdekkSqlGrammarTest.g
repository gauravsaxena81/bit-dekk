grammar BitdekkSqlGrammarTest;

stat	:	selectStatement EOF;

selectStatement
	:	'SELECT' columns 'FROM' IDENTIFIER ('WHERE' whereExpressions)? ('ORDER' 'BY' orderByColumns)? limitClause?;
orderByColumns
	:	a=IDENTIFIER (c=('ASC'|'DESC'))? 
		(','b=IDENTIFIER (d=('ASC'|'DESC'))?)*;
limitClause
	:	'LIMIT' POS_INT ',' POS_INT;
columns	:	column  (',' column)*;
column	:	a=dimension | b=aggregateMeasure;
dimension
	:	a=IDENTIFIER ('AS' b=IDENTIFIER)? ;
aggregateMeasure
	:	a=groupedExpression ('AS' b=IDENTIFIER)?;
whereExpressions
	:	whereExpression ('AND' whereExpression)*;
whereExpression
	:	dimensionCondition;
dimensionCondition
	:	(a=IDENTIFIER '=' '"' b=IDENTIFIER '"')
	| 	(c=IDENTIFIER 'IN' ('(' '"' d=IDENTIFIER '"' (',' '"' d=IDENTIFIER '"')*')'));
/*measureExpression 
	:	(a=addExpression (ADD_SUB b=addExpression)?) ;
addExpression
	:	(a=mulExpression (MUL_DIV b=mulExpression)?);
mulExpression
	:	function'('IDENTIFIER')'
	| 	number
	| 	'('measureExpression')';*/
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
Letter  : 'a'..'z' | '_' | 'A'..'Z';
fragment     
Digit  :  '0'..'9';    
WS  	:   ( ' ' | '\t' | '\r' | '\n') {$channel=HIDDEN;};

