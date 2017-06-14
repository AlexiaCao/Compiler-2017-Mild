package BackEnd.ControlFlowGraph.Instruction.ArithmeticInstruction.UnaryInstruction;

import BackEnd.ControlFlowGraph.Instruction.Instruction;
import BackEnd.ControlFlowGraph.Instruction.MemoryInstruction.MoveInstruction;
import BackEnd.ControlFlowGraph.Operand.ImmediateValue;
import BackEnd.ControlFlowGraph.Operand.Operand;
import BackEnd.ControlFlowGraph.Operand.VirtualRegister.VirtualRegister;

public class BitNotInstruction extends UnaryInstruction {
    private BitNotInstruction(VirtualRegister destination, Operand source) {
        super(destination, source);
    }

    public static Instruction getInstruction(Operand destination, Operand source) {
        return new BitNotInstruction((VirtualRegister)destination, source).rebuild();
    }

    @Override
    public Instruction rebuild() {
        if (source instanceof ImmediateValue) {
            int literal = ((ImmediateValue)source).literal;
            return MoveInstruction.getInstruction(destination, new ImmediateValue(~literal));
        }
        return this;
    }

    @Override
    public String NASMName() {
        return "not";
    }

    @Override
    public String toString() {
        return String.format("%s = not %s", destination, source);
    }
}
