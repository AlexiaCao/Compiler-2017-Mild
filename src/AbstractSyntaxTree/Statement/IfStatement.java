package AbstractSyntaxTree.Statement;

import Environment.ScopeTable.Scope;
import AbstractSyntaxTree.Expression.ConstantExpression.BoolConstant;
import AbstractSyntaxTree.Expression.Expression;
import AbstractSyntaxTree.Type.BasicType.BoolType;
import Utility.Error.CompilationError;
import Utility.Utility;

import java.util.List;

public class IfStatement extends Statement implements Scope {

    public Expression condition;
    public Statement trueStatement, falseStatement;

    private IfStatement(Expression condition, Statement trueStatement, Statement falseStatement) {
        this.condition = condition;
        this.trueStatement = trueStatement;
        this.falseStatement = falseStatement;
    }

    public static Statement getStatement(Expression condition, Statement trueStatement, Statement falseStatement) {
        if (!(condition.type instanceof BoolType)) {
            throw new CompilationError("a bool-type condition is expected in the if statement");
        }
        if (condition instanceof BoolConstant) {
            if (((BoolConstant)condition).literal) {
                return trueStatement;
            } else {
                return falseStatement;
            }
        }
        return new IfStatement(condition, trueStatement, falseStatement);
    }

    @Override
    public String toString() {
        return "[statement: if]";
    }

    @Override
    public String toString(int indents) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Utility.getIndent(indents)).append(toString()).append("\n");
        stringBuilder.append(condition.toString(indents + 1));
        if (trueStatement != null) {
            stringBuilder.append(trueStatement.toString(indents + 1));
        }
        if (falseStatement != null) {
            stringBuilder.append(falseStatement.toString(indents + 1));
        }
        return stringBuilder.toString();
    }
}
