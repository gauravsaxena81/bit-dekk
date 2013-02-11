grammar BitDekkMeasureExpressionTest;
//Test:
//SUM(Volume)
//SUM(Volume + 2)
//SUM((Volume * 5) + 2)
//SUM((Volume * 5) + (2 + (3 * Volume)))
//SUM(Volume) + AVG(Volume)
//(SUM(Volume) * AVG(Volume)) + (5 * SUM(Volume)) + AVG(Volume))
//2 + SUM(Volume) + AVG(Volume)
//2 * SUM(Volume) * AVG(Volume)

stat	:	groupedExpression ;
groupedExpression
	:	a=groupedAddExpression (ADD_SUB b=groupedAddExpression)*;
groupedAddExpression
	:	a=groupedMulExpression (MUL_DIV b=groupedMulExpression)*;
groupedMulExpression
	:	 NUMBER 
	|	function '(' measureExpression  ')'
	|	'('groupedExpression')' ;
measureExpression
	:	a=addExpression (MUL_DIV b=addExpression)*;
addExpression
	:	a=mulExpression (ADD_SUB b=mulExpression)*;
mulExpression
	:	IDENTIFIER  
	| 	NUMBER  
	| 	'('measureExpression')' ;
function
	:	'SUM' 
	|	'AVG' ;
//Lexer
OPERATOR:	'=' | '<' | '>' | '<>' | '<=' | '>=';
NUMBER	:	Digit+('.'Digit+)?;
MUL_DIV	:	'*' | '/';
ADD_SUB	:	'+' | '-';
IDENTIFIER   :  Letter (Letter | Digit)*;
fragment     
Letter  : 'a'..'z' | '_' | 'A'..'Z';
fragment     
Digit  :  '0'..'9';    
WS  	:   ( ' ' | '\t' | '\r' | '\n') {$channel=HIDDEN;};



