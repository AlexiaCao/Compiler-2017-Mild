package AbstractSyntaxTree.Statement.LoopStatement;

import AbstractSyntaxTree.Expression.Expression;
import AbstractSyntaxTree.Statement.Statement;
import AbstractSyntaxTree.Type.BasicType.BoolType;
import Utility.Error.CompilationError;
import Utility.Utility;

import java.util.List;
public class WhileStatement extends LoopStatement {

    public Expression condition;
    public Statement statement;

    public static Statement getStatement() {
        return new WhileStatement();
    }

    public void addCondition(Expression condition) {
        if (!(condition.type instanceof BoolType)) {
            throw new CompilationError("a bool-type condition is expected in the while statement");
        }
        this.condition = condition;
    }

    public void addStatement(Statement statement) {
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "[statement: while]";
    }

    @Override
    public String toString(int indents) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Utility.getIndent(indents)).append(toString()).append("\n");
        stringBuilder.append(condition.toString(indents + 1));
        stringBuilder.append(statement.toString(indents + 1));
        return stringBuilder.toString();
    }
}
