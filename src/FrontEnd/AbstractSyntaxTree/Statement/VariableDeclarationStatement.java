package FrontEnd.AbstractSyntaxTree.Statement;


import BackEnd.ControlFlowGraph.Instruction.Instruction;
import BackEnd.ControlFlowGraph.Instruction.MemoryInstruction.MoveInstruction;
import Environment.SymbolTable.Symbol;
import FrontEnd.AbstractSyntaxTree.Expression.Expression;
import FrontEnd.AbstractSyntaxTree.Type.BasicType.VoidType;
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
        if (symbol.name.equals("this")) {
            throw new CompilationError("\"this\" cannot be the name of variable-declaration");
        }
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

    @Override
    public void emit(List<Instruction> instructions) {
        if (expression != null) {
            expression.emit(instructions);
            expression.load(instructions);
            instructions.add(MoveInstruction.getInstruction(symbol.register, expression.operand));
        }
    }
}
