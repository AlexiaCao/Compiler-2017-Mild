package BackEnd.ControlFlowGraph.Instruction.ArithmeticInstruction.BinaryInstruction;

import BackEnd.ControlFlowGraph.Instruction.Instruction;
import BackEnd.ControlFlowGraph.Instruction.MemoryInstruction.MoveInstruction;
import BackEnd.ControlFlowGraph.Operand.ImmediateValue;
import BackEnd.ControlFlowGraph.Operand.Operand;
import BackEnd.ControlFlowGraph.Operand.VirtualRegister.VirtualRegister;
import Utility.Error.InternalError;

public class SubtractionInstruction extends BinaryInstruction {
    private SubtractionInstruction(VirtualRegister destination, Operand source1, Operand source2) {
        super(destination, source1, source2);
    }

    public static Instruction getInstruction(Operand destination, Operand source1, Operand source2) {
        if (destination instanceof VirtualRegister) {
            return new SubtractionInstruction((VirtualRegister)destination, source1, source2).rebuild();
        }
        throw new InternalError("SubtractionInstruction:getInstruction.");
    }

    @Override
    public Instruction rebuild() {
        if (source1 instanceof ImmediateValue && source2 instanceof ImmediateValue) {
            int literal1 = ((ImmediateValue)source1).literal;
            int literal2 = ((ImmediateValue)source2).literal;
            return MoveInstruction.getInstruction(destination, new ImmediateValue(literal1 - literal2));
        }
        return this;
    }

    @Override
    public String NASMName() {
        return "sub";
    }

    @Override
    public String toString() {
        return String.format("%s = sub %s %s", destination, source1, source2);
    }
}
