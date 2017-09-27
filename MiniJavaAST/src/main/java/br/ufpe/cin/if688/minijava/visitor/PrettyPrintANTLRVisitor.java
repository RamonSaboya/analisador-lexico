package br.ufpe.cin.if688.minijava.visitor;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import br.ufpe.cin.if688.minijava.antlr.MinijavaParser.ClassDeclarationContext;
import br.ufpe.cin.if688.minijava.antlr.MinijavaParser.ClassDeclarationListContext;
import br.ufpe.cin.if688.minijava.antlr.MinijavaParser.ClassExtendsDeclarationContext;
import br.ufpe.cin.if688.minijava.antlr.MinijavaParser.ClassSimpleDeclarationContext;
import br.ufpe.cin.if688.minijava.antlr.MinijavaParser.ExpressionContext;
import br.ufpe.cin.if688.minijava.antlr.MinijavaParser.GoalContext;
import br.ufpe.cin.if688.minijava.antlr.MinijavaParser.MainClassContext;
import br.ufpe.cin.if688.minijava.antlr.MinijavaParser.MethodDeclarationContext;
import br.ufpe.cin.if688.minijava.antlr.MinijavaParser.StatementContext;
import br.ufpe.cin.if688.minijava.antlr.MinijavaParser.TypeContext;
import br.ufpe.cin.if688.minijava.antlr.MinijavaParser.VarDeclarationContext;
import br.ufpe.cin.if688.minijava.antlr.MinijavaVisitor;

public class PrettyPrintANTLRVisitor implements MinijavaVisitor<Void> {

	@Override
	public Void visit(ParseTree arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visitChildren(RuleNode arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visitErrorNode(ErrorNode arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visitTerminal(TerminalNode arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visitGoal(GoalContext ctx) {
		ctx.mainClass().accept(this);
		for(ClassDeclarationContext cdc : ctx.classDeclaration()) {
			System.out.println();
			cdc.accept(this);
		}
		return null;
	}

	@Override
	public Void visitMainClass(MainClassContext ctx) {
		System.out.print("class ");
		ctx.identifier(0).accept(this);
		System.out.println(" {");
		System.out.print("  public static void main (String [] ");
		ctx.identifier(1).accept(this);
		System.out.println(") {");
		System.out.print("    ");
		ctx.statement().accept(this);
		System.out.println("");
		System.out.println("  }");
		System.out.println("}");
		return null;
	}

	@Override
	public Void visitClassDeclaration(ClassDeclarationContext ctx) {
		ctx.classSimpleDeclaration().accept(this);
		ctx.classExtendsDeclaration().accept(this);
		return null;
	}

	@Override
	public Void visitClassSimpleDeclaration(ClassSimpleDeclarationContext ctx) {
		System.out.print("class ");
		ctx.identifier().accept(this);
		System.out.println(" { ");
		for (VarDeclarationContext vdc : ctx.varDeclaration()) {
			System.out.print("  ");
			vdc.accept(this);
			if (ctx.varDeclaration().indexOf(vdc) <= ctx.varDeclaration().size() - 1) {
				System.out.println();
			}
		}
		for (MethodDeclarationContext mdc : ctx.methodDeclaration()) {
			System.out.println();
			mdc.accept(this);
		}
		System.out.println();
		System.out.println("}");
		return null;
	}

	@Override
	public Void visitClassExtendsDeclaration(ClassExtendsDeclarationContext ctx) {
		System.out.print("class ");
		ctx.identifier(0).accept(this);
		System.out.print(" extends ");
		ctx.identifier(1).accept(this);
		System.out.println(" { ");
		for (VarDeclarationContext vdc : ctx.varDeclaration()) {
			System.out.print("  ");
			vdc.accept(this);
			if (ctx.varDeclaration().indexOf(vdc) <= ctx.varDeclaration().size() - 1) {
				System.out.println();
			}
		}
		for (MethodDeclarationContext mdc : ctx.methodDeclaration()) {
			System.out.println();
			mdc.accept(this);
		}
		System.out.println();
		System.out.println("}");
		return null;
	}

	@Override
	public Void visitVarDeclaration(VarDeclarationContext ctx) {
		ctx.type().accept(this);
		System.out.print(" ");
		ctx.identifier().accept(this);
		System.out.print(";");
		return null;
	}

	@Override
	public Void visitMethodDeclaration(MethodDeclarationContext ctx) {
		System.out.print("  public ");
		ctx.type(0).accept(this);
		System.out.print(" ");
		ctx.identifier(0).accept(this);
		System.out.print(" (");
		for (int i = 1; i < ctx.type().size(); i++) {
			ctx.type(i).accept(this);
			if (i + 1 < ctx.type().size()) {
				System.out.print(", ");
			}
		}
		System.out.println(") { ");
		for (VarDeclarationContext vdc : ctx.varDeclaration()) {
			System.out.print("    ");
			vdc.accept(this);
			System.out.println("");
		}
		for (StatementContext sc : ctx.statement()) {
			System.out.print("    ");
			sc.accept(this);
			if (ctx.statement().indexOf(sc) <= ctx.statement().size() - 1) {
				System.out.println();
			}
		}
		System.out.print("    return ");
		ctx.expression().accept(this);
		System.out.println(";");
		System.out.print("  }");
		return null;
	}

	@Override
	public Void visitType(TypeContext ctx) {
		ctx.identifier().accept(this);
		return null;
	}

	@Override
	public Void visitStatement(StatementContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visitExpression(ExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

}