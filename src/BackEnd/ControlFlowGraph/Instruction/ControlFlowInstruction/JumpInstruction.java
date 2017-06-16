package BackEnd.ControlFlowGraph.Instruction.ControlFlowInstruction;

import BackEnd.ControlFlowGraph.Instruction.Instruction;
import BackEnd.ControlFlowGraph.Instruction.LabelInstruction;
import BackEnd.ControlFlowGraph.Operand.Operand;
import BackEnd.ControlFlowGraph.Operand.VirtualRegister.VirtualRegister;

import java.util.Collections;
import java.util.List;

public class JumpInstruction extends ControlFlowInstruction {

    public LabelInstruction to;

    private JumpInstruction(LabelInstruction to) {
        this.to = to;
    }

    public static Instruction getInstruction(LabelInstruction toLabel) {
        return new JumpInstruction(toLabel);
    }

    @Override
    public List<Operand> getDefinedOperands() {
        return Collections.emptyList();
    }

    @Override
    public List<Operand> getUsedOperands() {
        return Collections.emptyList();
    }

    @Override
    public void setDefinedRegister(VirtualRegister from, VirtualRegister to) {
    }

    @Override
    public void setUsedRegister(VirtualRegister from, Operand to) {
    }

    @Override
    public String toString() {
        //System.err.println(to.block.toString());
        return "jump " + to.block.toString();
    }
}
