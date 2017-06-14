package FrontEnd.AbstractSyntaxTree.Statement.LoopStatement;

import BackEnd.ControlFlowGraph.Instruction.LabelInstruction;
import Environment.ScopeTable.Scope;
import FrontEnd.AbstractSyntaxTree.Statement.Statement;

public abstract class LoopStatement extends Statement implements Scope{
    public LabelInstruction loop, merge;
}
