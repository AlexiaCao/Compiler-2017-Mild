package FrontEnd.AbstractSyntaxTree.Expression;

import BackEnd.ControlFlowGraph.Instruction.Instruction;
import BackEnd.ControlFlowGraph.Operand.Operand;
import FrontEnd.AbstractSyntaxTree.Node;
import FrontEnd.AbstractSyntaxTree.Type.Type;

import java.util.List;

public abstract class Expression implements Node {
    public Type type;
    public boolean isLeftValue;
    public Operand operand;

    protected Expression(Type type, boolean isLeftValue) {
        this.type = type;
        this.isLeftValue = isLeftValue;
    }

    public abstract void emit(List<Instruction> instructions);

    public void load(List<Instruction> instructions) {
    }
}
