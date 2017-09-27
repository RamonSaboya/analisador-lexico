grammar Minijava;

goal: mainClass classDeclaration* EOF;

mainClass : 'class' identifier '{' 'public' 'static' 'void' 'main' '(' 'String' '[' ']' identifier ')' '{' statement '}' '}';

classDeclaration: ( classSimpleDeclaration | classExtendsDeclaration );

classSimpleDeclaration: 'class' identifier '{' ( varDeclaration )* ( methodDeclaration )* '}';

classExtendsDeclaration: 'class' identifier 'extends' identifier '{' ( varDeclaration )* ( methodDeclaration )* '}';

varDeclaration: type identifier ';';

methodDeclaration: 'public' type identifier '(' ( type identifier ( ',' type identifier )* )? ')' '{' ( varDeclaration )* ( statement )* 'return' expression ';' '}';

type: intArray | boolean | int | identifier;
intArray: 'int' '[' ']';
boolean: 'boolean';
int: 'int';
	
statement: block | if | while | print | assign | arrayAssign;
block: '{' ( statement )* '}';
if: 'if' '(' expression ')' statement 'else' statement;
while: 'while' '(' expression ')' statement;
print: 'System.out.println' '(' expression ')' ';';
assign: identifier '=' expression ';';
arrayAssign: identifier '[' expression ']' '=' expression ';';
	
expression: and2 | less | plus | minus | times | arrayLookup | arrayLength | call | integerLiteral | true | false | identifier | this | newArray | newObject | not | identifierExpression;
and: expression '&&' expression;
less: expression '<' expression;
plus: expression '+' expression;
minus: expression '-' expression;
times: expression '*' expression;
arrayLookup: expression '[' expression ']';
arrayLength: expression '.' 'length';
call: expression '.' identifier '(' ( expression ( ',' expression )* )? ')';
integerLiteral: INTEGER_LITERAL;
true: 'true';
false: 'false';
identifier: IDENTIFIER;
this: 'this';
newArray: 'new' 'int' '[' expression ']';
newObject: 'new' identifier '(' ')';
not: '!' expression;
identifierExpression: '(' expression ')';
and2: and;


COMMENT: '/*' .*? '*/' -> skip;
LINE_COMMENT: '//' ~[\r\n]* -> skip;

WHITESPACE: [ \t\r\n]+ -> skip;

INTEGER_LITERAL: ([1-9][0-9]* | '0');
IDENTIFIER: [_a-zA-Z][a-zA-Z0-9_]*;