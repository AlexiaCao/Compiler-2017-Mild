package AbstractSyntaxTree.Statement;

import Environment.Environment;
import AbstractSyntaxTree.Statement.LoopStatement.LoopStatement;
import Utility.Error.CompilationError;
import Utility.Utility;

import java.util.List;

public class BreakStatement extends Statement {
    public LoopStatement to;

    private BreakStatement(LoopStatement to) {
        this.to = to;
    }

    public static Statement getStatement() {
        if (Environment.scopeTable.getLoopScope() == null) {
            throw new CompilationError("The break is not placed in a loop");
        }
        return new BreakStatement(Environment.scopeTable.getLoopScope());
    }

    @Override
    public String toString() {
        return "[statement: break]";
    }

    @Override
    public String toString(int indents) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Utility.getIndent(indents)).append(toString()).append("\n");
        return stringBuilder.toString();
    }
}
