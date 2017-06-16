package BackEnd.ControlFlowGraph;

import BackEnd.ControlFlowGraph.Instruction.Instruction;
import BackEnd.ControlFlowGraph.Instruction.LabelInstruction;
import BackEnd.ControlFlowGraph.Operand.VirtualRegister.VirtualRegister;
import FrontEnd.AbstractSyntaxTree.Function;
import Utility.Utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Block {
    public Function function;
    public String name;
    public int identity;
    public LabelInstruction label;
    public List<Instruction> instructions, phiFunctions;
    public List<Block> successors, predecessors;
    public Liveliness liveliness;

    public Block(Function function, String name, int identity, LabelInstruction label) {
        this.function = function;
        this.name = name;
        this.identity = identity;
        this.label = label;
        this.instructions = new ArrayList<>();
        this.phiFunctions = new ArrayList<>();
        this.successors = new ArrayList<>();
        this.predecessors = new ArrayList<>();
        this.liveliness = new Liveliness();
    }

    public Set<VirtualRegister> getAllRegisters() {
        return new HashSet<VirtualRegister>() {{
            for (Instruction instruction : phiFunctions) {
                addAll(instruction.getDefinedRegisters());
                addAll(instruction.getUsedRegisters());
            }
            for (Instruction instruction : instructions) {
                addAll(instruction.getDefinedRegisters());
                addAll(instruction.getUsedRegisters());
            }
        }};
    }

    @Override
    public String toString() {
        return "%" + function.name + "." + identity + "." + name;
    }

    public String toString(int indents) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Utility.getIndent(indents)).append(this).append(":").append("\n");
        phiFunctions.forEach(instruction -> stringBuilder.append(instruction.toString(indents + 1)));
        instructions.forEach(instruction -> stringBuilder.append(instruction.toString(indents + 1)));
        return stringBuilder.toString();
    }

    public class Liveliness {
        public List<VirtualRegister> used, defined;
        public Set<VirtualRegister> liveIn, liveOut;

        public Liveliness() {
            this.used = new ArrayList<>();
            this.defined = new ArrayList<>();
            this.liveIn = new HashSet<>();
            this.liveOut = new HashSet<>();
        }
    }
}