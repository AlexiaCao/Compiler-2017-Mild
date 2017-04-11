// Generated from /home/alexia/Compiler-2017-Mild/src/ConcreteSyntaxTree/Parser/Mild.g4 by ANTLR 4.6
package ConcreteSyntaxTree.Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MildParser}.
 */
public interface MildListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MildParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MildParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MildParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MildParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MildParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(MildParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MildParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(MildParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MildParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclaration(MildParser.FunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MildParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclaration(MildParser.FunctionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MildParser#variableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarationStatement(MildParser.VariableDeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MildParser#variableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarationStatement(MildParser.VariableDeclarationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MildParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MildParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MildParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MildParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MildParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement(MildParser.BlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MildParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement(MildParser.BlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MildParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(MildParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MildParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(MildParser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MildParser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectionStatement(MildParser.SelectionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MildParser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectionStatement(MildParser.SelectionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MildParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterIterationStatement(MildParser.IterationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MildParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitIterationStatement(MildParser.IterationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MildParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterJumpStatement(MildParser.JumpStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MildParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitJumpStatement(MildParser.JumpStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(MildParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(MildParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MildParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(MildParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MildParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(MildParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MildParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(MildParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link MildParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(MildParser.ConstantContext ctx);
}