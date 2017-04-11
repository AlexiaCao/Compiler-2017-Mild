package AbstractSyntaxTree.Statement.LoopStatement;

import AbstractSyntaxTree.Expression.ConstantExpression.BoolConstant;
import AbstractSyntaxTree.Expression.Expression;
import AbstractSyntaxTree.Statement.Statement;
import AbstractSyntaxTree.Type.BasicType.BoolType;
import Utility.Error.CompilationError;
import Utility.Utility;

public class ForStatement extends LoopStatement {
    public Expression initialization, condition, increment;
    public Statement statement;

    public static Statement getStatement() {
        return new ForStatement();
    }

    public void addInitialization(Expression initialization) {
        this.initialization = initialization;
    }

    public void addCondition(Expression condition) {
        if (condition == null) {
            this.condition = BoolConstant.getConstant(true);
            return;
        }
        if (condition.type instanceof BoolType) {

            this.condition = condition;
            return;
        }
        throw new CompilationError("A bool-type condition is expected in the for statement");
    }

    public void addIncrement(Expression increment) {
        this.increment = increment;
    }

    public void addStatement(Statement statement) {
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "[statement: for";
    }

    @Override
    public String toString(int indents) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Utility.getIndent(indents)).append(toString()).append("\n");
        if (initialization != null) {
            stringBuilder.append(initialization.toString(indents + 1));
        }

        stringBuilder.append(condition.toString(indents + 1));
        if (increment != null) {
            stringBuilder.append(increment.toString(indents + 1));
        }

        stringBuilder.append(statement.toString(indents + 1));
        return stringBuilder.toString();
    }
}
