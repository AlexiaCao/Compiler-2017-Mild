package BackEnd.ControlFlowGraph.Instruction.ArithmeticInstruction.BinaryInstruction;

import BackEnd.ControlFlowGraph.Instruction.Instruction;
import BackEnd.ControlFlowGraph.Operand.Operand;
import BackEnd.ControlFlowGraph.Operand.VirtualRegister.VirtualRegister;
import Utility.Error.InternalError;

public class BitOrInstruction extends BinaryInstruction {
    private BitOrInstruction(VirtualRegister target, Operand source1, Operand source2) {
        super(target, source1, source2);
    }

    public static Instruction getInstruction(Operand target, Operand source1, Operand source2) {
        if (target instanceof VirtualRegister) {
            return new BitOrInstruction((VirtualRegister)target, source1, source2).rebuild();
        }
        throw new InternalError("BitOrInstruction:getInstruction.");
    }

    @Override
    public Instruction rebuild() {
        return this;
    }

    @Override
    public String NASMName() {
        return "or";
    }

    @Override
    public String toString() {
        return String.format("%s = or %s %s", destination, source1, source2);
    }
}
