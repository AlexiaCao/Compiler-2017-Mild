package AbstractSyntaxTree.Statement;

import AbstractSyntaxTree.Expression.Expression;

import java.util.List;

public class ExpressionStatement extends Statement{
    public Expression expression;

    private ExpressionStatement(Expression expression) {
        this.expression = expression;
    }

    public static Statement getStatement(Expression expression) {
        return new ExpressionStatement(expression);
    }

    @Override
    public String toString() {
        return "[statement: expression]";
    }

    @Override
    public String toString(int indents) {
        if (expression == null) {
            return null;
        }
        return expression.toString(indents);
    }
}
