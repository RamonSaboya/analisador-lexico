package mavenWithImp;

import java.io.FileInputStream;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

public class Teste {
	@Test
	public void Teste() throws Exception {
		InputStream stream = new FileInputStream("src/test/resources/BinaryTree.java");
		ANTLRInputStream input = new ANTLRInputStream(stream);
		MinijavaLexer lexer = new MinijavaLexer(input);
		CommonTokenStream token = new CommonTokenStream(lexer);
		MinijavaParser parser = new MinijavaParser(token);
		parser.goal();
		assert parser.getNumberOfSyntaxErrors() == 0;
		
	}
}
