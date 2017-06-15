package BackEnd.ControlFlowGraph.Instruction.MemoryInstruction;

import BackEnd.ControlFlowGraph.Instruction.Instruction;
import BackEnd.ControlFlowGraph.Operand.Address;
import BackEnd.ControlFlowGraph.Operand.Operand;
import BackEnd.ControlFlowGraph.Operand.VirtualRegister.VirtualRegister;
import Utility.Error.InternalError;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StoreInstruction extends MemoryInstruction {
    public Operand source;
    public Address address;

    private StoreInstruction(Operand source, Address address) {
        this.source = source;
        this.address = address;
    }

    public static Instruction getInstruction(Operand source, Operand address) {
        if (address instanceof Address) {
            return new StoreInstruction(source, (Address)address);
        }
        throw new InternalError("StoreInstruction:getInstruction.");
    }

    @Override
    public List<Operand> getDefinedOperands() {
        return Collections.emptyList();
    }

    @Override
    public List<Operand> getUsedOperands() {
        return Arrays.asList(source, address.base);
    }

    @Override
    public void setDefinedRegister(VirtualRegister from, VirtualRegister to) {
    }

    @Override
    public void setUsedRegister(VirtualRegister from, Operand to) {
        if (source == from) {
            source = to;
        }
        if (address.base == from) {
            address = new Address((VirtualRegister)to, address.offset, address.size);
        }
    }

    public String NASMName() {
        if (address.size == 1) {
            return "sb";
        } else if (address.size == 4) {
            return "sw";
        }
        throw new InternalError("StoreInstruction:NASMName.");
    }

    @Override
    public String toString() {
        return "store " + address.size + " " + address.base + " " + source + " " + address.offset;
    }
}
