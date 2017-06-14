package FrontEnd.AbstractSyntaxTree.Statement;

import BackEnd.ControlFlowGraph.Instruction.ControlFlowInstruction.BranchInstruction;
import BackEnd.ControlFlowGraph.Instruction.ControlFlowInstruction.JumpInstruction;
import BackEnd.ControlFlowGraph.Instruction.Instruction;
import BackEnd.ControlFlowGraph.Instruction.LabelInstruction;
import Environment.ScopeTable.Scope;
import FrontEnd.AbstractSyntaxTree.Expression.ConstantExpression.BoolConstant;
import FrontEnd.AbstractSyntaxTree.Expression.Expression;
import FrontEnd.AbstractSyntaxTree.Type.BasicType.BoolType;
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

    @Override
    public void emit(List<Instruction> instructions) {
        LabelInstruction trueLabel = LabelInstruction.getInstruction("if_true");
        LabelInstruction falseLabel = LabelInstruction.getInstruction("if_false");
        LabelInstruction mergeLabel = LabelInstruction.getInstruction("if_merge");

        condition.emit(instructions);
        condition.load(instructions);
        instructions.add(BranchInstruction.getInstruction(condition.operand, trueLabel, falseLabel));
        instructions.add(trueLabel);
        if (trueStatement != null) {
            trueStatement.emit(instructions);
        }
        instructions.add(JumpInstruction.getInstruction(mergeLabel));
        instructions.add(falseLabel);
        if (falseStatement != null) {
            falseStatement.emit(instructions);
        }
        instructions.add(JumpInstruction.getInstruction(mergeLabel));
        instructions.add(mergeLabel);
    }
}

