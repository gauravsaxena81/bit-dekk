/*
 * Copyright 2013 Contributors of bit-dekk
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
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

stat	:	groupedExpression EOF;
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
	|	'AVG' 
	|	'COUNT'
	|	'MAX'
	|	'MIN';
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



