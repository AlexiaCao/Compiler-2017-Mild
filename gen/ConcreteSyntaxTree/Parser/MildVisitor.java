// Generated from /home/alexia/Compiler-2017-Mild/src/ConcreteSyntaxTree/Parser/Mild.g4 by ANTLR 4.6
package ConcreteSyntaxTree.Parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MildParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MildVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MildParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MildParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MildParser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(MildParser.ClassDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MildParser#functionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclaration(MildParser.FunctionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MildParser#variableDeclarationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarationStatement(MildParser.VariableDeclarationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MildParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(MildParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MildParser#blockStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatement(MildParser.BlockStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MildParser#expressionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionStatement(MildParser.ExpressionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MildParser#selectionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectionStatement(MildParser.SelectionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MildParser#iterationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIterationStatement(MildParser.IterationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MildParser#jumpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJumpStatement(MildParser.JumpStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MildParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(MildParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MildParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(MildParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MildParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(MildParser.ConstantContext ctx);
}