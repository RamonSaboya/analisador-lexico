package br.ufpe.cin.if688.minijava.antlr;
// Generated from Minijava.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MinijavaParser}.
 */
public interface MinijavaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MinijavaParser#goal}.
	 * @param ctx the parse tree
	 */
	void enterGoal(MinijavaParser.GoalContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinijavaParser#goal}.
	 * @param ctx the parse tree
	 */
	void exitGoal(MinijavaParser.GoalContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinijavaParser#mainClass}.
	 * @param ctx the parse tree
	 */
	void enterMainClass(MinijavaParser.MainClassContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinijavaParser#mainClass}.
	 * @param ctx the parse tree
	 */
	void exitMainClass(MinijavaParser.MainClassContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinijavaParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(MinijavaParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinijavaParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(MinijavaParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinijavaParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(MinijavaParser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinijavaParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(MinijavaParser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinijavaParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclaration(MinijavaParser.MethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinijavaParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclaration(MinijavaParser.MethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinijavaParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(MinijavaParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinijavaParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(MinijavaParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinijavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MinijavaParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinijavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MinijavaParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(MinijavaParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(MinijavaParser.ExpressionContext ctx);
}