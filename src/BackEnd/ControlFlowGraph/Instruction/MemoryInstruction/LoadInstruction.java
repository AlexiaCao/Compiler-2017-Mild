package BackEnd.ControlFlowGraph.Instruction.MemoryInstruction;

import BackEnd.ControlFlowGraph.Instruction.Instruction;
import BackEnd.ControlFlowGraph.Operand.Address;
import BackEnd.ControlFlowGraph.Operand.Operand;
import BackEnd.ControlFlowGraph.Operand.VirtualRegister.VirtualRegister;
import Utility.Error.InternalError;

import java.util.Collections;
import java.util.List;

public class LoadInstruction extends MemoryInstruction {
    public VirtualRegister destination;
    public Address address;

    private LoadInstruction(VirtualRegister destination, Address address) {
        this.destination = destination;
        this.address = address;
    }

    public static Instruction getInstruction(Operand target, Operand address) {
        if (target instanceof VirtualRegister && address instanceof Address) {
            return new LoadInstruction((VirtualRegister)target, (Address)address);
        }
        throw new InternalError("LoadInstruction:getInstruction.");
    }

    @Override
    public List<Operand> getDefinedOperands() {
        return Collections.singletonList(destination);
    }

    @Override
    public List<Operand> getUsedOperands() {
        return Collections.singletonList(address.base);
    }

    @Override
    public void setDefinedRegister(VirtualRegister from, VirtualRegister to) {
        if (destination == from) {
            destination = to;
        }
    }

    @Override
    public void setUsedRegister(VirtualRegister from, Operand to) {
        if (address.base == from) {
            address = new Address((VirtualRegister)to, address.offset, address.size);
        }
    }

    public String NASMName() {
        if (address.size == 1) {
            return "lb";
        } else if (address.size == 4) {
            return "lw";
        }
        throw new InternalError("LoadInstruction:NASMName.");
    }

    @Override
    public String toString() {
        return destination + " = load " + address.size + " " + address.base + " " + address.offset;
    }
}
