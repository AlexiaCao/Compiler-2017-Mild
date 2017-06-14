package FrontEnd.AbstractSyntaxTree.Statement.LoopStatement;

import BackEnd.ControlFlowGraph.Instruction.ControlFlowInstruction.BranchInstruction;
import BackEnd.ControlFlowGraph.Instruction.ControlFlowInstruction.JumpInstruction;
import BackEnd.ControlFlowGraph.Instruction.Instruction;
import BackEnd.ControlFlowGraph.Instruction.LabelInstruction;
import FrontEnd.AbstractSyntaxTree.Expression.Expression;
import FrontEnd.AbstractSyntaxTree.Statement.Statement;
import FrontEnd.AbstractSyntaxTree.Type.BasicType.BoolType;
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


    @Override
    public void emit(List<Instruction> instructions) {
        LabelInstruction bodyLabel = LabelInstruction.getInstruction("while_body");
        loop = LabelInstruction.getInstruction("while_loop");
        merge = LabelInstruction.getInstruction("while_merge");

        instructions.add(JumpInstruction.getInstruction(loop));
        instructions.add(loop);
        condition.emit(instructions);
        instructions.add(BranchInstruction.getInstruction(condition.operand, bodyLabel, merge));
        instructions.add(bodyLabel);
        statement.emit(instructions);
        instructions.add(JumpInstruction.getInstruction(loop));
        instructions.add(merge);
    }
}
