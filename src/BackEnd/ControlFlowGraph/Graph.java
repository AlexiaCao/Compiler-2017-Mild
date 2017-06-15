package BackEnd.ControlFlowGraph;

import BackEnd.ControlFlowGraph.Instruction.ControlFlowInstruction.BranchInstruction;
import BackEnd.ControlFlowGraph.Instruction.ControlFlowInstruction.ControlFlowInstruction;
import BackEnd.ControlFlowGraph.Instruction.ControlFlowInstruction.JumpInstruction;
import BackEnd.ControlFlowGraph.Instruction.FunctionInstruction.CallInstruction;
import BackEnd.ControlFlowGraph.Instruction.Instruction;
import BackEnd.ControlFlowGraph.Instruction.LabelInstruction;
import BackEnd.ControlFlowGraph.Operand.Operand;
import BackEnd.ControlFlowGraph.Operand.VirtualRegister.VariableRegister.TemporaryRegister;
import BackEnd.ControlFlowGraph.Operand.VirtualRegister.VirtualRegister;
import BackEnd.Translator.NASM.NASMRegister;
import Environment.Environment;
import Environment.SymbolTable.Symbol;
import FrontEnd.AbstractSyntaxTree.Function;
import FrontEnd.AbstractSyntaxTree.Statement.VariableDeclarationStatement;
import FrontEnd.AbstractSyntaxTree.Type.BasicType.VoidType;
import Utility.Error.InternalError;
import Utility.Utility;

import java.util.*;

public class Graph {
    public Function function;
    public List<Block> blocks;
    public Block enter, entry, exit;
    public Frame frame;

    public Graph(Function function) {
        this.function = function;
        this.buildGraph();
        //this.optimize();
    }

    private void buildGraph() {
        List<Instruction> instructions = new ArrayList<>();
        function.enter = LabelInstruction.getInstruction("enter");
        function.entry = LabelInstruction.getInstruction("entry");
        function.exit = LabelInstruction.getInstruction("exit");
        instructions.add(function.enter);
        if (function.name.equals("main")) {
            for (VariableDeclarationStatement declaration : Environment.program.globalVariables) {
                declaration.emit(instructions);
            }
        }
        instructions.add(JumpInstruction.getInstruction(function.entry));
        instructions.add(function.entry);
        function.statements.emit(instructions);
        instructions.add(JumpInstruction.getInstruction(function.exit));
        instructions.add(function.exit);

        blocks = new ArrayList<>();
        for (int i = 0, j; i < instructions.size(); i = j) {
            if (!(instructions.get(i) instanceof LabelInstruction)) {
                j = i + 1;
            } else {
                LabelInstruction label = (LabelInstruction)instructions.get(i);
                Block block = label.block = addBlock(label.name, label);
                for (j = i + 1; j < instructions.size(); ++j) {
                    if (instructions.get(j) instanceof LabelInstruction) {
                        break;
                    }
                    block.instructions.add(instructions.get(j));
                    if (instructions.get(j) instanceof ControlFlowInstruction) {
                        //	eliminate unreachable codes after this instruction
                        break;
                    }
                }
            }
        }
        for (Block block : blocks) {
            if (block.name.equals("enter")) {
                enter = block;
            } else if (block.name.equals("entry")) {
                entry = block;
            } else if (block.name.equals("exit")) {
                exit = block;
            }
        }
        refresh();
    }

    public Graph refresh() {
        refreshGraph();
        analysisLiveliness();
        analysisFrame();
        return this;
    }

    private void refreshGraph() {
        for (Block block : blocks) {
            block.successors = new ArrayList<>();
            block.predecessors = new ArrayList<>();
        }
        for (Block block : blocks) {
            if (!block.instructions.isEmpty()) {
                Instruction instruction = block.instructions.get(block.instructions.size() - 1);
                if (instruction instanceof JumpInstruction) {
                    JumpInstruction i = (JumpInstruction)instruction;
                    block.successors.add(i.to.block);
                } else if (instruction instanceof BranchInstruction) {
                    BranchInstruction i = (BranchInstruction)instruction;
                    block.successors.add(i.trueTo.block);
                    block.successors.add(i.falseTo.block);
                }
            }
        }
        blocks = depthFirstSearch(enter, new HashSet<>());
        for (Block block : blocks) {
            for (Block successor : block.successors) {
                successor.predecessors.add(block);
            }
        }
    }

    private List<Block> depthFirstSearch(Block block, Set<Block> visit) {
        visit.add(block);
        List<Block> list = new ArrayList<Block>() {{
            add(block);
        }};
        for (Block successor : block.successors) {
            if (visit.contains(successor)) {
                continue;
            }
            if (successor != exit) {
                visit.add(successor);
                list.addAll(depthFirstSearch(successor, visit));
            }
        }
        if (block == enter) {
            list.add(exit);
        }
        return list;
    }

    private void analysisLiveliness() {
        for (Block block : blocks) {
            block.liveliness.used = new ArrayList<>();
            block.liveliness.defined = new ArrayList<>();
            for (Instruction instruction : block.instructions) {
                for (VirtualRegister register : instruction.getUsedRegisters()) {
                    if (!block.liveliness.defined.contains(register)) {
                        block.liveliness.used.add(register);
                    }
                }
                for (VirtualRegister register : instruction.getDefinedRegisters()) {
                    block.liveliness.defined.add(register);
                }
            }
        }
        for (Block block : blocks) {
            block.liveliness.liveIn = new HashSet<>();
            block.liveliness.liveOut = new HashSet<>();
        }
        while (true) {
            for (Block block : blocks) {
                block.liveliness.liveIn = new HashSet<VirtualRegister>() {{
                    block.liveliness.liveOut.forEach(this::add);
                    block.liveliness.defined.forEach(this::remove);
                    block.liveliness.used.forEach(this::add);
                }};
            }
            boolean modified = false;
            for (Block block : blocks) {
                Set<VirtualRegister> origin = block.liveliness.liveOut;
                block.liveliness.liveOut = new HashSet<VirtualRegister>() {{
                    for (Block successor : block.successors) {
                        addAll(successor.liveliness.liveIn);
                    }
                }};
                if (!block.liveliness.liveOut.equals(origin)) {
                    modified = true;
                }
            }
            if (!modified) {
                break;
            }
        }
    }

    private void analysisFrame() {
        Set<VirtualRegister> registers = new HashSet<VirtualRegister>() {{
            for (Block block : blocks) {
                for (Instruction instruction : block.instructions) {
                    for (VirtualRegister register : instruction.getUsedRegisters()) {
                        if (register instanceof TemporaryRegister) {
                            add(register);
                        }
                    }
                    for (VirtualRegister register : instruction.getDefinedRegisters()) {
                        if (register instanceof TemporaryRegister) {
                            add(register);
                        }
                    }
                }
            }
        }};
        frame = new Frame();
        frame.size += NASMRegister.size() * 8;
        for (VirtualRegister register : registers) {
            frame.temporary.put(register, -frame.size);
            frame.size += NASMRegister.size();
        }
        for (int i = 0; i < function.parameters.size(); i++) {
            Symbol parameter = function.parameters.get(i);
            if (i < 6) {
                frame.parameter.put(parameter.register, -(i + 1) * NASMRegister.size());
            } else {
                frame.parameter.put(parameter.register, (i - 6) * NASMRegister.size() + 16);
            }
        }
    }

    private Block addBlock(String name, LabelInstruction label) {
        Block block = new Block(function, name, blocks.size(), label);
        blocks.add(block);
        return block;
    }

    public Set<VirtualRegister> getAllRegisters() {
        return new HashSet<VirtualRegister>() {{
            for (Block block : blocks) {
                addAll(block.getAllRegisters());
            }
        }};
    }

    public boolean containsCall() {
        for (Block block : blocks) {
            for (Instruction instruction : block.instructions) {
                if (instruction instanceof CallInstruction) {
                    return true;
                }
            }
        }
        return false;
    }

    public String toString(int indents) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Utility.getIndent(indents));
        if (function.type instanceof VoidType) {
            stringBuilder.append("void");
        } else {
            stringBuilder.append("func");
        }
        stringBuilder.append(" ").append(function.name);
        function.parameters.forEach(parameter -> stringBuilder.append(" ").append(parameter.register));
        stringBuilder.append(" {");
        blocks.forEach(block -> stringBuilder.append("\n").append(block.toString(indents + 1)));
        stringBuilder.append(Utility.getIndent(indents)).append("}").append("\n");
        return stringBuilder.toString();
    }

    public class Frame {
        public int size;
        public Map<VirtualRegister, Integer> temporary, parameter;

        public Frame() {
            size = 0;
            temporary = new HashMap<>();
            parameter = new HashMap<>();
        }

        public int getOffset(Operand operand) {
            if (operand instanceof VirtualRegister) {
                if (temporary.containsKey(operand)) {
                    return temporary.get(operand);
                }
                if (parameter.containsKey(operand)) {
                    return parameter.get(operand);
                }
            }
            throw new InternalError("Frame:getOffset.");
        }
    }
}
