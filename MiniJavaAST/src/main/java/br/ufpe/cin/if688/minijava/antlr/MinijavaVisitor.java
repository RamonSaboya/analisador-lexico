package br.ufpe.cin.if688.minijava.antlr;
// Generated from Minijava.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MinijavaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MinijavaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MinijavaParser#goal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoal(MinijavaParser.GoalContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinijavaParser#mainClass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMainClass(MinijavaParser.MainClassContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinijavaParser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(MinijavaParser.ClassDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinijavaParser#classSimpleDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassSimpleDeclaration(MinijavaParser.ClassSimpleDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinijavaParser#classExtendsDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassExtendsDeclaration(MinijavaParser.ClassExtendsDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinijavaParser#varDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclaration(MinijavaParser.VarDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinijavaParser#methodDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDeclaration(MinijavaParser.MethodDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinijavaParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(MinijavaParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinijavaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(MinijavaParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinijavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(MinijavaParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinijavaParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(MinijavaParser.IdentifierContext ctx);
}