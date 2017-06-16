package BackEnd.Allocator.GlobalRegisterAllocator;

import BackEnd.Allocator.Allocator;
import BackEnd.Allocator.GlobalRegisterAllocator.GraphColoring.ChaitinGraphColoring;
import BackEnd.ControlFlowGraph.Block;
import BackEnd.ControlFlowGraph.Instruction.ArithmeticInstruction.BinaryInstruction.BinaryInstruction;
import BackEnd.ControlFlowGraph.Instruction.Instruction;
import BackEnd.ControlFlowGraph.Instruction.MemoryInstruction.MoveInstruction;
import BackEnd.ControlFlowGraph.Operand.VirtualRegister.VirtualRegister;
import FrontEnd.AbstractSyntaxTree.Function;

import java.util.HashSet;
import java.util.Set;

public class RegisterAllocator extends Allocator {
    public RegisterAllocator(Function function) throws Exception {
        super(function);
        //function.graph.refresh();
        InterferenceGraph graph = new InterferenceGraph();
        for (Block block : function.graph.blocks) {
            for (Instruction instruction : block.instructions) {
                for (VirtualRegister register : instruction.getDefinedRegisters()) {
                    graph.add(register);
                }
                for (VirtualRegister register : instruction.getUsedRegisters()) {
                    graph.add(register);
                }
            }
        }
        for (Block block : function.graph.blocks) {
            Set<VirtualRegister> live = new HashSet<VirtualRegister>() {{
                block.liveliness.liveOut.forEach(this::add);
            }};
            for (int i = block.instructions.size() - 1; i >= 0; i--) {
                Instruction instruction = block.instructions.get(i);
                if (instruction instanceof BinaryInstruction) {
                    for (VirtualRegister liveRegister : live) {
                        graph.forbid(((BinaryInstruction) instruction).destination, liveRegister);
                    }
                    live.remove(((BinaryInstruction) instruction).destination);
                    if (((BinaryInstruction) instruction).source2 instanceof  VirtualRegister) {
                        live.add((VirtualRegister)((BinaryInstruction) instruction).source2);
                    }

                    for (VirtualRegister liveRegister : live) {
                        graph.forbid(((BinaryInstruction) instruction).destination, liveRegister);
                    }
                    live.remove(((BinaryInstruction) instruction).destination);
                    if (((BinaryInstruction) instruction).source1 instanceof  VirtualRegister) {
                        live.add((VirtualRegister)((BinaryInstruction) instruction).source1);
                    }
                }
                else{
                    for (VirtualRegister register : instruction.getDefinedRegisters()) {
                        for (VirtualRegister liveRegister : live) {
                            graph.forbid(register, liveRegister);
                        }
                    }
                    for (VirtualRegister register : instruction.getDefinedRegisters()){
                        live.remove(register);
                    }
                    for (VirtualRegister register : instruction.getUsedRegisters()){
                        live.add(register);
                    }
                }
            }
        }
        for (Block block : function.graph.blocks) {
            for (Instruction instruction : block.instructions) {
                if (instruction instanceof MoveInstruction) {
                    MoveInstruction i = (MoveInstruction)instruction;
                    if (i.source instanceof VirtualRegister) {
                        graph.recommend(i.destination, (VirtualRegister)i.source);
                    }
                }
            }
        }
        mapping = new ChaitinGraphColoring(graph).analysis();

    }
}
