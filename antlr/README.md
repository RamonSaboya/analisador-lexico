# mavenWithImp

O arquivo .g4 está na pasta src/main/antlr4 ao finalizar sua implementação, basta salvar, botão direito no projeto -> run as -> maven generate-sources.

Os arquivos gerados estarão na pasta target/generated-sources/antlr4. Basta copiá-los para dentro de um package, por exemplo, src/test/java/mavenWithImp 
e utilizá-los. No caso, dentro desse package, está um teste simples que le um arquivo de entrada com os exemplos de programa definidos no artigo e verificar se há erros sintáticos.