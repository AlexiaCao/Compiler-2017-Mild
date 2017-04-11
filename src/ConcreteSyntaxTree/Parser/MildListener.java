package ConcreteSyntaxTree.Parser;

// Generated from Mild.g4 by ANTLR 4.5.3
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
	 * Enter a parse tree produced by the {@code whileStatement}
	 * labeled alternative in {@link MildParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(MildParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileStatement}
	 * labeled alternative in {@link MildParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(MildParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forStatement}
	 * labeled alternative in {@link MildParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(MildParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forStatement}
	 * labeled alternative in {@link MildParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(MildParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code continueStatement}
	 * labeled alternative in {@link MildParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStatement(MildParser.ContinueStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code continueStatement}
	 * labeled alternative in {@link MildParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStatement(MildParser.ContinueStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code breakStatement}
	 * labeled alternative in {@link MildParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(MildParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code breakStatement}
	 * labeled alternative in {@link MildParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(MildParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnStatement}
	 * labeled alternative in {@link MildParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(MildParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnStatement}
	 * labeled alternative in {@link MildParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(MildParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code constantExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterConstantExpression(MildParser.ConstantExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code constantExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitConstantExpression(MildParser.ConstantExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code shiftExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterShiftExpression(MildParser.ShiftExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code shiftExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitShiftExpression(MildParser.ShiftExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code additiveExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(MildParser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code additiveExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(MildParser.AdditiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subscriptExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSubscriptExpression(MildParser.SubscriptExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subscriptExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSubscriptExpression(MildParser.SubscriptExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code relationalExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpression(MildParser.RelationalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code relationalExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpression(MildParser.RelationalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code inclusiveOrExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterInclusiveOrExpression(MildParser.InclusiveOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code inclusiveOrExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitInclusiveOrExpression(MildParser.InclusiveOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNewExpression(MildParser.NewExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNewExpression(MildParser.NewExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignmentExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentExpression(MildParser.AssignmentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignmentExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentExpression(MildParser.AssignmentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multiplicativeExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(MildParser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multiplicativeExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(MildParser.MultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalOrExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOrExpression(MildParser.LogicalOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalOrExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOrExpression(MildParser.LogicalOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variableExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterVariableExpression(MildParser.VariableExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variableExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitVariableExpression(MildParser.VariableExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(MildParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(MildParser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exclusiveOrExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExclusiveOrExpression(MildParser.ExclusiveOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exclusiveOrExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExclusiveOrExpression(MildParser.ExclusiveOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code equalityExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpression(MildParser.EqualityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equalityExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpression(MildParser.EqualityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalAndExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAndExpression(MildParser.LogicalAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalAndExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAndExpression(MildParser.LogicalAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fieldExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFieldExpression(MildParser.FieldExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fieldExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFieldExpression(MildParser.FieldExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallExpression(MildParser.FunctionCallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallExpression(MildParser.FunctionCallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(MildParser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(MildParser.UnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSubExpression(MildParser.SubExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSubExpression(MildParser.SubExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code postfixExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPostfixExpression(MildParser.PostfixExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code postfixExpression}
	 * labeled alternative in {@link MildParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPostfixExpression(MildParser.PostfixExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayType}
	 * labeled alternative in {@link MildParser#type}.
	 * @param ctx the parse tree
	 */
	void enterArrayType(MildParser.ArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayType}
	 * labeled alternative in {@link MildParser#type}.
	 * @param ctx the parse tree
	 */
	void exitArrayType(MildParser.ArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intType}
	 * labeled alternative in {@link MildParser#type}.
	 * @param ctx the parse tree
	 */
	void enterIntType(MildParser.IntTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intType}
	 * labeled alternative in {@link MildParser#type}.
	 * @param ctx the parse tree
	 */
	void exitIntType(MildParser.IntTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringType}
	 * labeled alternative in {@link MildParser#type}.
	 * @param ctx the parse tree
	 */
	void enterStringType(MildParser.StringTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringType}
	 * labeled alternative in {@link MildParser#type}.
	 * @param ctx the parse tree
	 */
	void exitStringType(MildParser.StringTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code voidType}
	 * labeled alternative in {@link MildParser#type}.
	 * @param ctx the parse tree
	 */
	void enterVoidType(MildParser.VoidTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code voidType}
	 * labeled alternative in {@link MildParser#type}.
	 * @param ctx the parse tree
	 */
	void exitVoidType(MildParser.VoidTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link MildParser#type}.
	 * @param ctx the parse tree
	 */
	void enterBoolType(MildParser.BoolTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link MildParser#type}.
	 * @param ctx the parse tree
	 */
	void exitBoolType(MildParser.BoolTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code classType}
	 * labeled alternative in {@link MildParser#type}.
	 * @param ctx the parse tree
	 */
	void enterClassType(MildParser.ClassTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code classType}
	 * labeled alternative in {@link MildParser#type}.
	 * @param ctx the parse tree
	 */
	void exitClassType(MildParser.ClassTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolConstant}
	 * labeled alternative in {@link MildParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterBoolConstant(MildParser.BoolConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolConstant}
	 * labeled alternative in {@link MildParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitBoolConstant(MildParser.BoolConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intConstant}
	 * labeled alternative in {@link MildParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterIntConstant(MildParser.IntConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intConstant}
	 * labeled alternative in {@link MildParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitIntConstant(MildParser.IntConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringConstant}
	 * labeled alternative in {@link MildParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterStringConstant(MildParser.StringConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringConstant}
	 * labeled alternative in {@link MildParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitStringConstant(MildParser.StringConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nullConstant}
	 * labeled alternative in {@link MildParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterNullConstant(MildParser.NullConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nullConstant}
	 * labeled alternative in {@link MildParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitNullConstant(MildParser.NullConstantContext ctx);
}