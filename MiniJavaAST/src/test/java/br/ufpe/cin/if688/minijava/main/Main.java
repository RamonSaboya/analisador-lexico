package br.ufpe.cin.if688.minijava.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import br.ufpe.cin.if688.minijava.antlr.MiniJavaASTVisitor;
import br.ufpe.cin.if688.minijava.antlr.MiniJavaLexer;
import br.ufpe.cin.if688.minijava.antlr.MiniJavaParser;
import br.ufpe.cin.if688.minijava.ast.Program;
import br.ufpe.cin.if688.minijava.visitor.PrettyPrintVisitor;

@SuppressWarnings("deprecation")
public class Main {

	private MiniJavaParser fromFile(String file) throws IOException {
		InputStream stream = new FileInputStream("src/test/resources/" + file);
		ANTLRInputStream input = new ANTLRInputStream(stream);
		MiniJavaLexer lexer = new MiniJavaLexer(input);
		CommonTokenStream token = new CommonTokenStream(lexer);
		return new MiniJavaParser(token);
	}

	@Test
	public void test1() throws IOException {
		Program prog = (Program) new MiniJavaASTVisitor().visit(fromFile("BinarySearch.java").goal());

		PrettyPrintVisitor ppv = new PrettyPrintVisitor();
		ppv.visit(prog);
	}

	@Test
	public void test2() throws IOException {
		Program prog = (Program) new MiniJavaASTVisitor().visit(fromFile("BinaryTree.java").goal());

		PrettyPrintVisitor ppv = new PrettyPrintVisitor();
		ppv.visit(prog);
	}

	@Test
	public void test3() throws IOException {
		Program prog = (Program) new MiniJavaASTVisitor().visit(fromFile("BubbleSort.java").goal());

		PrettyPrintVisitor ppv = new PrettyPrintVisitor();
		ppv.visit(prog);
	}

	@Test
	public void test4() throws IOException {
		Program prog = (Program) new MiniJavaASTVisitor().visit(fromFile("LinearSearch.java").goal());

		PrettyPrintVisitor ppv = new PrettyPrintVisitor();
		ppv.visit(prog);
	}

	@Test
	public void test5() throws IOException {
		Program prog = (Program) new MiniJavaASTVisitor().visit(fromFile("LinkedList.java").goal());

		PrettyPrintVisitor ppv = new PrettyPrintVisitor();
		ppv.visit(prog);
	}

	@Test
	public void test6() throws IOException {
		Program prog = (Program) new MiniJavaASTVisitor().visit(fromFile("QuickSort.java").goal());

		PrettyPrintVisitor ppv = new PrettyPrintVisitor();
		ppv.visit(prog);
	}

	@Test
	public void test7() throws IOException {
		Program prog = (Program) new MiniJavaASTVisitor().visit(fromFile("Simple.java").goal());

		PrettyPrintVisitor ppv = new PrettyPrintVisitor();
		ppv.visit(prog);
	}

}
