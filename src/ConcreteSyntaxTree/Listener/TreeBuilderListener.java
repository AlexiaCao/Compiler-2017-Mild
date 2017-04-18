package ConcreteSyntaxTree.Listener;

import AbstractSyntaxTree.Type.ArrayType;
import Environment.Environment;
import Environment.SymbolTable.Symbol;
import AbstractSyntaxTree.Expression.BinaryExpression.*;
import AbstractSyntaxTree.Expression.ConstantExpression.BoolConstant;
import AbstractSyntaxTree.Expression.ConstantExpression.IntConstant;
import AbstractSyntaxTree.Expression.ConstantExpression.NullConstant;
import AbstractSyntaxTree.Expression.ConstantExpression.StringConstant;
import AbstractSyntaxTree.Expression.Expression;
import AbstractSyntaxTree.Expression.FunctionCallExpression;
import AbstractSyntaxTree.Expression.NewExpression;
import AbstractSyntaxTree.Expression.UnaryExpression.*;
import AbstractSyntaxTree.Expression.VariableExpression.FieldExpression;
import AbstractSyntaxTree.Expression.VariableExpression.IdentifierExpression;
import AbstractSyntaxTree.Expression.VariableExpression.SubscriptExpression;
import AbstractSyntaxTree.Function;
import AbstractSyntaxTree.Statement.*;
import AbstractSyntaxTree.Statement.LoopStatement.ForStatement;
import AbstractSyntaxTree.Statement.LoopStatement.WhileStatement;
import AbstractSyntaxTree.Type.ClassType.ClassType;
import AbstractSyntaxTree.Type.ClassType.Member.Member;
import AbstractSyntaxTree.Type.ClassType.Member.MemberVariable;
import AbstractSyntaxTree.Type.Type;
import ConcreteSyntaxTree.Parser.MildParser;
import Utility.Error.CompilationError;
import Utility.Error.InternalError;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class TreeBuilderListener extends BaseListener {
    @Override
    public void exitProgram(MildParser.ProgramContext ctx) {
        ctx.variableDeclarationStatement().forEach(statementContext -> {
            VariableDeclarationStatement variableDeclaration = (VariableDeclarationStatement)returnNode.get(statementContext);
            Environment.program.addGlobalVariable(variableDeclaration);
        });
    }

    @Override
    public void enterFunctionDeclaration(MildParser.FunctionDeclarationContext ctx) {
        Function function = (Function)returnNode.get(ctx);
        Environment.enterScope(function);
    }

    @Override
    public void exitFunctionDeclaration(MildParser.FunctionDeclarationContext ctx) {
        Function function = (Function)returnNode.get(ctx);
        function.addStatements((BlockStatement)returnNode.get(ctx.blockStatement()));
        Environment.exitScope();
    }

    @Override
    public void enterClassDeclaration(MildParser.ClassDeclarationContext ctx) {
        ClassType classType = (ClassType)returnNode.get(ctx);
        Environment.enterScope(classType);
        classType.memberVariables.forEach((name, member) -> Environment.symbolTable.add(name, member.type));
        classType.memberFunctions.forEach((name, member) -> Environment.symbolTable.add(name, member.function));
    }

    @Override
    public void exitClassDeclaration(MildParser.ClassDeclarationContext ctx) {
        ClassType classType = (ClassType)returnNode.get(ctx);
        ctx.variableDeclarationStatement().forEach(statementContext -> {
            String name = statementContext.IDENTIFIER().getText();
            if (statementContext.expression() != null) {
                Member member = classType.getMember(name);
                if (member instanceof MemberVariable) {
                    MemberVariable memberVariable = (MemberVariable)member;
                    memberVariable.expression = (Expression)returnNode.get(statementContext.expression());
                }
            }
        });
        Environment.exitScope();
    }

    public void enterStatement(MildParser.StatementContext ctx) {
        if (ctx.parent instanceof MildParser.SelectionStatementContext) {
            //	handle : if (*) int *;
            Environment.enterScope(null);
        }
    }

    @Override
    public void exitStatement(MildParser.StatementContext ctx) {
        if (ctx.parent instanceof MildParser.SelectionStatementContext) {
            //	handle : if (*) int *;
            Environment.exitScope();
        }
        returnNode.put(ctx, returnNode.get(ctx.getChild(0)));
    }

    @Override
    public void enterBlockStatement(MildParser.BlockStatementContext ctx) {
        BlockStatement blockStatement = (BlockStatement)BlockStatement.getStatement();
        Environment.enterScope(blockStatement);
        if (ctx.parent instanceof MildParser.FunctionDeclarationContext) {
            Function function = (Function)returnNode.get(ctx.parent);
            for (int i = 0; i < function.parameters.size(); ++i) {
                Symbol parameter = function.parameters.get(i);
                function.parameters.set(i, Environment.symbolTable.addParameterVariable(parameter.name, parameter.type));
            }
        }
        returnNode.put(ctx, blockStatement);
    }

    @Override
    public void exitBlockStatement(MildParser.BlockStatementContext ctx) {
        ctx.statement().forEach(statementContext -> {
            ((BlockStatement)returnNode.get(ctx)).addStatement(
                    (Statement)returnNode.get(statementContext)
            );
        });
        Environment.exitScope();
    }

    @Override
    public void exitExpressionStatement(MildParser.ExpressionStatementContext ctx) {
        returnNode.put(ctx, ExpressionStatement.getStatement(
                (Expression)returnNode.get(ctx.expression())
        ));
    }

    @Override
    public void exitSelectionStatement(MildParser.SelectionStatementContext ctx) {
        returnNode.put(ctx, IfStatement.getStatement(
                (Expression)returnNode.get(ctx.expression()),
                (Statement)returnNode.get(ctx.statement(0)),
                (Statement)returnNode.get(ctx.statement(1))
        ));
    }

    @Override
    public void enterWhileStatement(MildParser.WhileStatementContext ctx) {
        WhileStatement whileStatement = (WhileStatement)WhileStatement.getStatement();
        Environment.enterScope(whileStatement);
        returnNode.put(ctx, whileStatement);
    }

    @Override
    public void exitWhileStatement(MildParser.WhileStatementContext ctx) {
        WhileStatement whileStatement = (WhileStatement)returnNode.get(ctx);
        whileStatement.addCondition((Expression)returnNode.get(ctx.expression()));
        whileStatement.addStatement((Statement)returnNode.get(ctx.statement()));
        Environment.exitScope();
    }

    @Override
    public void enterForStatement(MildParser.ForStatementContext ctx) {
        ForStatement forStatement = (ForStatement)ForStatement.getStatement();
        Environment.enterScope(forStatement);
        returnNode.put(ctx, forStatement);
    }

    @Override
    public void exitForStatement(MildParser.ForStatementContext ctx) {
        ForStatement forStatement = (ForStatement)returnNode.get(ctx);
        forStatement.addStatement((Statement)returnNode.get(ctx.statement()));
        //	handle : for (*; *; *)
        int semicolons = 0;
        for (ParseTree parseTree : ctx.children) {
            if (parseTree.getText().equals(";")) {
                semicolons++;
            }
            if (parseTree instanceof MildParser.ExpressionContext) {
                Expression expression = (Expression)returnNode.get(parseTree);
                if (semicolons == 0) {
                    forStatement.addInitialization(expression);
                } else if (semicolons == 1) {
                    forStatement.addCondition(expression);
                } else if (semicolons == 2) {
                    forStatement.addIncrement(expression);
                } else {
                    throw new InternalError();
                }
            }
        }
        //	exit loop scope
        Environment.exitScope();
    }

    @Override
    public void exitContinueStatement(MildParser.ContinueStatementContext ctx) {
        returnNode.put(ctx, ContinueStatement.getStatement());
    }

    @Override
    public void exitBreakStatement(MildParser.BreakStatementContext ctx) {
        returnNode.put(ctx, BreakStatement.getStatement());
    }

    @Override
    public void exitReturnStatement(MildParser.ReturnStatementContext ctx) {
        Expression returnExpression = (Expression)returnNode.get(ctx.expression());
        returnNode.put(ctx, ReturnStatement.getStatement(returnExpression));
    }

    @Override
    public void exitVariableDeclarationStatement(MildParser.VariableDeclarationStatementContext ctx) {
        if (!(ctx.parent instanceof MildParser.ClassDeclarationContext)) {
            Type type = (Type)returnNode.get(ctx.type());
            String name = ctx.IDENTIFIER().getText();
            Symbol symbol;
            if (Environment.scopeTable.getScope() == Environment.program) {
                symbol = Environment.symbolTable.addGlobalVariable(name, type);
            } else {
                symbol = Environment.symbolTable.addTemporaryVariable(name, type);
            }
            Expression expression = (Expression)returnNode.get(ctx.expression());
            returnNode.put(ctx, VariableDeclarationStatement.getStatement(symbol, expression));
        }
    }

    @Override
    public void exitConstantExpression(MildParser.ConstantExpressionContext ctx) {
        returnNode.put(ctx, returnNode.get(ctx.constant()));
    }

    @Override
    public void exitVariableExpression(MildParser.VariableExpressionContext ctx) {
        returnNode.put(ctx, IdentifierExpression.getExpression(ctx.getText()));
    }

    @Override
    public void exitFieldExpression(MildParser.FieldExpressionContext ctx) {
        returnNode.put(ctx, FieldExpression.getExpression(
                (Expression)returnNode.get(ctx.expression()),
                ctx.IDENTIFIER().getText()
        ));
    }

    @Override
    public void exitSubscriptExpression(MildParser.SubscriptExpressionContext ctx) {
        if (ctx.expression(0) instanceof MildParser.NewExpressionContext) {
            throw new CompilationError("wrong ArrayType variable declaration statement");
        }
        returnNode.put(ctx, SubscriptExpression.getExpression(
                (Expression)returnNode.get(ctx.expression(0)),
                (Expression)returnNode.get(ctx.expression(1))
        ));
    }

    @Override
    public void exitSubExpression(MildParser.SubExpressionContext ctx) {
        returnNode.put(ctx, returnNode.get(ctx.expression()));
    }

    @Override
    public void exitPostfixExpression(MildParser.PostfixExpressionContext ctx) {
        Expression expression = (Expression)returnNode.get(ctx.expression());
        if (ctx.operator.getText().equals("++")) {
            returnNode.put(ctx, PostfixIncrementExpression.getExpression(expression));
        } else if (ctx.operator.getText().equals("--")) {
            returnNode.put(ctx, PostfixDecrementExpression.getExpression(expression));
        } else {
            throw new InternalError();
        }
    }

    @Override
    public void exitUnaryExpression(MildParser.UnaryExpressionContext ctx) {
        Expression expression = (Expression)returnNode.get(ctx.expression());
        if (ctx.operator.getText().equals("+")) {
            returnNode.put(ctx, UnaryPlusExpression.getExpression(expression));
        } else if (ctx.operator.getText().equals("-")) {
            returnNode.put(ctx, UnaryMinusExpression.getExpression(expression));
        } else if (ctx.operator.getText().equals("!")) {
            returnNode.put(ctx, LogicalNotExpression.getExpression(expression));
        } else if (ctx.operator.getText().equals("~")) {
            returnNode.put(ctx, BitwiseNotExpression.getExpression(expression));
        } else if (ctx.operator.getText().equals("++")) {
            returnNode.put(ctx, PrefixIncrementExpression.getExpression(expression));
        } else if (ctx.operator.getText().equals("--")) {
            returnNode.put(ctx, PrefixDecrementExpression.getExpression(expression));
        } else {
            throw new InternalError();
        }
    }

    @Override
    public void exitFunctionCallExpression(MildParser.FunctionCallExpressionContext ctx) {
        Expression function = (Expression)returnNode.get(ctx.expression(0));
        List<Expression> parameters = new ArrayList<>();
        for (int i = 1; i < ctx.expression().size(); ++i) {
            Expression parameter = (Expression)returnNode.get(ctx.expression(i));
            parameters.add(parameter);
        }
        returnNode.put(ctx, FunctionCallExpression.getExpression(function, parameters));
    }

    @Override
    public void exitNewExpression(MildParser.NewExpressionContext ctx) {
        String pre = null;
        List<Expression> dimensionExpressions = new ArrayList<>();
        ctx.expression().forEach(expressionContext -> {
            Expression dimensionExpression = (Expression)returnNode.get(expressionContext);
            dimensionExpressions.add(dimensionExpression);
        });
        for (ParseTree each : ctx.children){
            if (each instanceof TerminalNode) {
                if (each.getText().equals("]") && pre.equals("[")){
                    dimensionExpressions.add(null);
                }
            }
            pre = each.getText();
        }
        Type baseType = (Type)returnNode.get(ctx.type());
        returnNode.put(ctx, NewExpression.getExpression(baseType, dimensionExpressions));
    }

    @Override
    public void exitMultiplicativeExpression(MildParser.MultiplicativeExpressionContext ctx) {
        Expression lhs = (Expression)returnNode.get(ctx.expression(0));
        Expression rhs = (Expression)returnNode.get(ctx.expression(1));
        if (ctx.operator.getText().equals("*")) {
            returnNode.put(ctx, MultiplicationExpression.getExpression(lhs, rhs));
        } else if (ctx.operator.getText().equals("/")) {
            returnNode.put(ctx, DivisionExpression.getExpression(lhs, rhs));
        } else if (ctx.operator.getText().equals("%")) {
            returnNode.put(ctx, ModuloExpression.getExpression(lhs, rhs));
        } else {
            throw new InternalError();
        }
    }

    @Override
    public void exitAdditiveExpression(MildParser.AdditiveExpressionContext ctx) {
        Expression lhs = (Expression)returnNode.get(ctx.expression(0));
        Expression rhs = (Expression)returnNode.get(ctx.expression(1));
        if (ctx.operator.getText().equals("+")) {
            returnNode.put(ctx, AdditionExpression.getExpression(lhs, rhs));
        } else if (ctx.operator.getText().equals("-")) {
            returnNode.put(ctx, SubtractionExpression.getExpression(lhs, rhs));
        } else {
            throw new InternalError();
        }
    }

    @Override
    public void exitShiftExpression(MildParser.ShiftExpressionContext ctx) {
        Expression lhs = (Expression)returnNode.get(ctx.expression(0));
        Expression rhs = (Expression)returnNode.get(ctx.expression(1));
        if (ctx.operator.getText().equals("<<")) {
            returnNode.put(ctx, BitwiseLeftShiftExpression.getExpression(lhs, rhs));
        } else if (ctx.operator.getText().equals(">>")) {
            returnNode.put(ctx, BitwiseRightShiftExpression.getExpression(lhs, rhs));
        } else {
            throw new InternalError();
        }
    }

    @Override
    public void exitRelationalExpression(MildParser.RelationalExpressionContext ctx) {
        Expression lhs = (Expression)returnNode.get(ctx.expression(0));
        Expression rhs = (Expression)returnNode.get(ctx.expression(1));
        if (ctx.operator.getText().equals("<")) {
            returnNode.put(ctx, LessThanExpression.getExpression(lhs, rhs));
        } else if (ctx.operator.getText().equals(">")) {
            returnNode.put(ctx, GreaterThanExpression.getExpression(lhs, rhs));
        } else if (ctx.operator.getText().equals("<=")) {
            returnNode.put(ctx, LessThanOrEqualToExpression.getExpression(lhs, rhs));
        } else if (ctx.operator.getText().equals(">=")) {
            returnNode.put(ctx, GreaterThanOrEqualToExpression.getExpression(lhs, rhs));
        } else {
            throw new InternalError();
        }
    }

    @Override
    public void exitEqualityExpression(MildParser.EqualityExpressionContext ctx) {
        Expression lhs = (Expression)returnNode.get(ctx.expression(0));
        Expression rhs = (Expression)returnNode.get(ctx.expression(1));
        if (ctx.operator.getText().equals("==")) {
            returnNode.put(ctx, EqualToExpression.getExpression(lhs, rhs));
        } else if (ctx.operator.getText().equals("!=")) {
            returnNode.put(ctx, NotEqualToExpression.getExpression(lhs, rhs));
        } else {
            throw new InternalError();
        }
    }

    @Override
    public void exitAndExpression(MildParser.AndExpressionContext ctx) {
        Expression lhs = (Expression)returnNode.get(ctx.expression(0));
        Expression rhs = (Expression)returnNode.get(ctx.expression(1));
        returnNode.put(ctx, BitwiseAndExpression.getExpression(lhs, rhs));
    }

    @Override
    public void exitExclusiveOrExpression(MildParser.ExclusiveOrExpressionContext ctx) {
        Expression lhs = (Expression)returnNode.get(ctx.expression(0));
        Expression rhs = (Expression)returnNode.get(ctx.expression(1));
        returnNode.put(ctx, BitwiseXorExpression.getExpression(lhs, rhs));
    }

    @Override
    public void exitInclusiveOrExpression(MildParser.InclusiveOrExpressionContext ctx) {
        Expression lhs = (Expression)returnNode.get(ctx.expression(0));
        Expression rhs = (Expression)returnNode.get(ctx.expression(1));
        returnNode.put(ctx, BitwiseOrExpression.getExpression(lhs, rhs));
    }

    @Override
    public void exitLogicalAndExpression(MildParser.LogicalAndExpressionContext ctx) {
        Expression lhs = (Expression)returnNode.get(ctx.expression(0));
        Expression rhs = (Expression)returnNode.get(ctx.expression(1));
        returnNode.put(ctx, LogicalAndExpression.getExpression(lhs, rhs));
    }

    @Override
    public void exitLogicalOrExpression(MildParser.LogicalOrExpressionContext ctx) {
        Expression lhs = (Expression)returnNode.get(ctx.expression(0));
        Expression rhs = (Expression)returnNode.get(ctx.expression(1));
        returnNode.put(ctx, LogicalOrExpression.getExpression(lhs, rhs));
    }

    @Override
    public void exitAssignmentExpression(MildParser.AssignmentExpressionContext ctx) {
        Expression lhs = (Expression)returnNode.get(ctx.expression(0));
        Expression rhs = (Expression)returnNode.get(ctx.expression(1));
        returnNode.put(ctx, AssignmentExpression.getExpression(lhs, rhs));
    }

    @Override
    public void exitBoolConstant(MildParser.BoolConstantContext ctx) {
        returnNode.put(ctx, BoolConstant.getConstant(Boolean.valueOf(ctx.getText())));
    }

    @Override
    public void exitIntConstant(MildParser.IntConstantContext ctx) {
        try {
            returnNode.put(ctx, IntConstant.getConstant(Integer.valueOf(ctx.getText())));
        } catch (NumberFormatException e) {
            throw new CompilationError("the number format is wrong");
        }
    }

    @Override
    public void exitStringConstant(MildParser.StringConstantContext ctx) {
        returnNode.put(ctx, StringConstant.getConstant(ctx.getText().substring(1, ctx.getText().length() - 1)));
    }

    @Override
    public void exitNullConstant(MildParser.NullConstantContext ctx) {
        returnNode.put(ctx, NullConstant.getConstant());
    }
}
