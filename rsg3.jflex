%%

/* Não altere as configurações a seguir */

%line
%column
%unicode
//%debug
%public
%standalone
%class Minijava
%eofclose

/* Padrôes nomeados */

whitespace = [ \n\t\r\f]
line_comment = \/\/[^\n]*
comment = \/\*[\w\W]*\*\/
literals = ([1-9][0-9]*|0)
identifier = [\w_][\w\d_]*


/* Insira os padrões nomeados no epaço acima */

/* Insira as regras léxicas abaixo */

%%
boolean             { System.out.println("Token BOOLEAN"); }
class               { System.out.println("Token CLASS"); }
public              { System.out.println("Token PUBLIC"); }
extends             { System.out.println("Token EXTENDS"); }
static              { System.out.println("Token STATIC"); }
void                { System.out.println("Token VOID"); }
main                { System.out.println("Token MAIN"); }
String              { System.out.println("Token STRING"); }
int                 { System.out.println("Token INT"); }
while               { System.out.println("Token WHILE"); }
if                  { System.out.println("Token IF"); }
else                { System.out.println("Token ELSE"); }
return              { System.out.println("Token RETURN"); }
length              { System.out.println("Token LENGTH"); }
true                { System.out.println("Token TRUE"); }
false               { System.out.println("Token FALSE"); }
this                { System.out.println("Token THIS"); }
new                 { System.out.println("Token NEW"); }
System.out.println  { System.out.println("Token SYSO"); }
"&&"                { System.out.println("Token AND"); }
"<"                 { System.out.println("Token LESS_THEN"); }
"=="                { System.out.println("Token EQUALS"); }
"!="                { System.out.println("Token NOT_EQUALS"); }
"+"                 { System.out.println("Token SUM"); }
"-"                 { System.out.println("Token MINUS"); }
"*"                 { System.out.println("Token PRODUCT"); }
"!"                 { System.out.println("Token NEG"); }
";"                 { System.out.println("Token SEMI_COMMA"); }
"."                 { System.out.println("Token DOT"); }
","                 { System.out.println("Token COMMA"); }
"="                 { System.out.println("Token ASSIGN"); }
"("                 { System.out.println("Token LEFT_PARENTHESES"); }
")"                 { System.out.println("Token RIGHT_PARENTHESES"); }
"{"                 { System.out.println("Token LEFT_BRACKET"); }
"}"                 { System.out.println("Token RIGHT_BRACKET"); }
"["                 { System.out.println("Token LEFT_BRACES"); }
"]"                 { System.out.println("Token RIGHT_BRACES"); }
{whitespace}        {  }
{comment}           {  }
{line_comment}      {  }
{literals}          { System.out.println("Literal: \"" + yytext() + "\""); }
{identifier}        { System.out.println("Identifier: \"" + yytext() + "\""); }

    
/* Insira as regras léxicas no espaço acima */     
     
. { throw new RuntimeException("Caractere ilegal! '" + yytext() + "' na linha: " + yyline + ", coluna: " + yycolumn); }
