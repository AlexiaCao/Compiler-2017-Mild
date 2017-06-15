package BackEnd.ControlFlowGraph.Instruction.ArithmeticInstruction.BinaryInstruction;

import BackEnd.ControlFlowGraph.Instruction.Instruction;
import BackEnd.ControlFlowGraph.Instruction.MemoryInstruction.MoveInstruction;
import BackEnd.ControlFlowGraph.Operand.ImmediateValue;
import BackEnd.ControlFlowGraph.Operand.Operand;
import BackEnd.ControlFlowGraph.Operand.VirtualRegister.VirtualRegister;
import Utility.Error.InternalError;

public class ModuloInstruction extends BinaryInstruction {
    private ModuloInstruction(VirtualRegister target, Operand source1, Operand source2) {
        super(target, source1, source2);
    }

    public static Instruction getInstruction(Operand target, Operand source1, Operand source2) {
        if (target instanceof VirtualRegister) {
            return new ModuloInstruction((VirtualRegister)target, source1, source2).rebuild();
        }
        throw new InternalError("ModuloInstruction:getInstruction.");
    }

    @Override
    public Instruction rebuild() {
        if (source1 instanceof ImmediateValue && source2 instanceof ImmediateValue) {
            int literal1 = ((ImmediateValue)source1).literal;
            int literal2 = ((ImmediateValue)source2).literal;
            if (literal2 != 0) {
                return MoveInstruction.getInstruction(destination, new ImmediateValue(literal1 % literal2));
            }
        }
        return this;
    }

    @Override
    public String NASMName() {
        return "rem";
    }

    @Override
    public String toString() {
        return String.format("%s = rem %s %s", destination, source1, source2);
    }
}
