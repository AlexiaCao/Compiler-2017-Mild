package AbstractSyntaxTree.Statement;

import Environment.SymbolTable.Symbol;
import AbstractSyntaxTree.Expression.Expression;
import AbstractSyntaxTree.Type.BasicType.VoidType;
import Utility.Error.CompilationError;
import Utility.Utility;

import java.util.List;

public class VariableDeclarationStatement extends Statement {
    public Symbol symbol;
    public Expression expression;

    private VariableDeclarationStatement(Symbol symbol, Expression expression) {
        this.symbol = symbol;
        this.expression = expression;
    }

    public static Statement getStatement(Symbol symbol, Expression expression) {
        if (symbol.type instanceof VoidType) {
            throw new CompilationError("VoidType can not be in the left-side of the variable-declaration statement");
        }
        if (expression == null || symbol.type.Compatible(expression.type)) {
            return new VariableDeclarationStatement(symbol, expression);
        }
        throw new CompilationError("The type of two expressions are not compatible in the the variable-declaration statement");

    }

    @Override
    public String toString() {
        return "[statment: variable-declaration, name = " + symbol.name + ",type = " + symbol.type + "]";
    }

    @Override
    public String toString(int indents) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Utility.getIndent(indents)).append(this).append("\n");
        if (expression != null) {
            stringBuilder.append(expression.toString(indents + 1));
        }
        return stringBuilder.toString();
    }
}
