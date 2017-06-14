package FrontEnd.AbstractSyntaxTree.Statement;

import BackEnd.ControlFlowGraph.Instruction.Instruction;
import FrontEnd.AbstractSyntaxTree.Node;

import java.util.List;

public abstract class Statement implements Node {
    public abstract void emit(List<Instruction> instructions);
}
