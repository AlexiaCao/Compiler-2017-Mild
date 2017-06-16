package BackEnd.Translator.NASM.NASMTranslator;

import BackEnd.Allocator.Allocator;
import BackEnd.Allocator.PhysicalRegister;
import BackEnd.ControlFlowGraph.Block;
import BackEnd.ControlFlowGraph.Graph;
import BackEnd.ControlFlowGraph.Instruction.ArithmeticInstruction.*;
import BackEnd.ControlFlowGraph.Instruction.ArithmeticInstruction.BinaryInstruction.*;
import BackEnd.ControlFlowGraph.Instruction.ArithmeticInstruction.UnaryInstruction.*;
import BackEnd.ControlFlowGraph.Instruction.ControlFlowInstruction.BranchInstruction;
import BackEnd.ControlFlowGraph.Instruction.ControlFlowInstruction.ControlFlowInstruction;
import BackEnd.ControlFlowGraph.Instruction.ControlFlowInstruction.JumpInstruction;
import BackEnd.ControlFlowGraph.Instruction.FunctionInstruction.CallInstruction;
import BackEnd.ControlFlowGraph.Instruction.FunctionInstruction.FunctionInstruction;
import BackEnd.ControlFlowGraph.Instruction.FunctionInstruction.ReturnInstruction;
import BackEnd.ControlFlowGraph.Instruction.Instruction;
import BackEnd.ControlFlowGraph.Instruction.MemoryInstruction.*;
import BackEnd.ControlFlowGraph.Operand.Operand;
import BackEnd.ControlFlowGraph.Operand.VirtualRegister.VariableRegister.TemporaryRegister;
import BackEnd.ControlFlowGraph.Operand.VirtualRegister.VirtualRegister;
import BackEnd.Translator.NASM.NASMRegister;
import FrontEnd.AbstractSyntaxTree.Function;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class NASMBasicTranslator extends NASMTranslator{
    public Allocator allocator;

    public NASMBasicTranslator(PrintStream output) {
        super(output);
    }

    private PhysicalRegister loadToRead(Operand from, PhysicalRegister to) {
        if ((from instanceof TemporaryRegister) && allocator.mapping.containsKey(from)){
            return allocator.mapping.get(from);
        }
        else {
            output.printf("\tmov    %s, %s\n", to, getPhysicalMemoryName(from));
            return to;
        }
    }

    private PhysicalRegister loadToWrite(Operand from, PhysicalRegister to) {
        if ((from instanceof TemporaryRegister) && allocator.mapping.containsKey(from)) {
            return allocator.mapping.get(from);
        }
        return to;
    }

    private void store(PhysicalRegister from, VirtualRegister to) {
        if (allocator.mapping.containsKey(to)) {
            return;
        } else{
            output.printf("\tmov    %s, %s\n", getPhysicalMemoryName(to), from);
        }
    }

    private void move(PhysicalRegister from, VirtualRegister to) {
        if (allocator.mapping.containsKey(to)) {
            output.printf("\tmov    %s, %s\n", allocator.mapping.get(to), from);
        } else{
            output.printf("\tmov    %s, %s\n", getPhysicalMemoryName(to), from);
        }
    }

    public void moveFilter(PhysicalRegister to, PhysicalRegister from) {
        if (to == from) {
            return;
        } else {
            output.printf("\tmov    %s, %s\n", to, from);
        }
    }

    public void protectScene() {
        for (PhysicalRegister physicalRegister : allocator.getUsedPhysicalRegisters()) {
            if (!physicalRegister.callPreserved) {
                output.printf("\tmov    qword [rbp + (%d)], %s\n",
                        -graph.frame.size - NASMRegister.size() * (physicalRegister.identity + 1),
                        physicalRegister.name);
            }
        }
    }

    public void restoreScene() {
        for (PhysicalRegister physicalRegister : allocator.getUsedPhysicalRegisters()) {
            if (!physicalRegister.callPreserved) {
                output.printf("\tmov    %s, qword [rbp + (%d)]\n",
                        physicalRegister.name,
                        -graph.frame.size - NASMRegister.size() * (physicalRegister.identity + 1));
            }
        }
    }

    @Override
    public void translate(Graph graph) {
        this.graph = graph;
        this.allocator = graph.function.allocator;

        /*for (VirtualRegister key : allocator.mapping.keySet()) {
            System.err.println(key  + " = " + allocator.mapping.get(key));
        }*/

        output.printf("%s:\n", getFunctionName(graph.function));
        output.printf("\tpush   rbp\n");
        output.printf("\tmov    rbp, rsp\n");
        output.printf("\tsub    rsp, %d\n", graph.frame.size + NASMRegister.size() * 20);
        if (graph.function.parameters.size() >= 1) {
            output.printf("\tmov    qword [rbp-8], rdi\n");
        }
        if (graph.function.parameters.size() >= 2) {
            output.printf("\tmov    qword [rbp-16], rsi\n");
        }
        if (graph.function.parameters.size() >= 3) {
            output.printf("\tmov    qword [rbp-24], rdx\n");
        }
        if (graph.function.parameters.size() >= 4) {
            output.printf("\tmov    qword [rbp-32], rcx\n");
        }
        if (graph.function.parameters.size() >= 5) {
            output.printf("\tmov    qword [rbp-40], r8\n");
        }
        if (graph.function.parameters.size() >= 6) {
            output.printf("\tmov    qword [rbp-48], r9\n");
        }
        
        for (PhysicalRegister physicalRegister : allocator.getUsedPhysicalRegisters()) {
            if (physicalRegister.callPreserved) {
                output.printf("\tmov    qword [rbp + (%d)], %s\n",
                        -graph.frame.size - (physicalRegister.identity + 1) * NASMRegister.size(),
                        physicalRegister);
            }
        }

        for (Block block : graph.blocks) {
            output.printf("%s:\n", getBlockName(block));
            for (Instruction instruction : block.instructions) {
                output.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t\t;%s\n", instruction);
                if (instruction instanceof ArithmeticInstruction) {
                    if (instruction instanceof UnaryInstruction) {
                        PhysicalRegister a = loadToRead(((UnaryInstruction) instruction).source, NASMRegister.rax);
                        PhysicalRegister b = loadToWrite(((UnaryInstruction) instruction).destination, NASMRegister.rax);

                        moveFilter(b, a);
                        if (instruction instanceof BitNotInstruction) {
                            output.printf("\tnot    %s\n", b);
                        } else if (instruction instanceof UnaryMinusInstruction) {
                            output.printf("\tneg    %s\n", b);
                        }
                        store(b, ((UnaryInstruction) instruction).destination);
                    } else if (instruction instanceof BinaryInstruction) {
                        PhysicalRegister a = loadToRead(((BinaryInstruction) instruction).source1, NASMRegister.r10);
                        PhysicalRegister b = loadToRead(((BinaryInstruction) instruction).source2, NASMRegister.r11);
                        PhysicalRegister c = loadToWrite(((BinaryInstruction) instruction).destination, NASMRegister.rax);
                        if (!(instruction instanceof AdditionInstruction)) {
                            moveFilter(c, a);
                        }
                        if (instruction instanceof AdditionInstruction) {
                            output.printf("\tlea    %s, [%s + %s]\n", c, b, a);
                        } else if (instruction instanceof BitAndInstruction) {
                            output.printf("\tand    %s, %s\n", c, b);
                        } else if (instruction instanceof BitLeftShiftInstruction) {
                            output.printf("\tmov    %s, %s\n", NASMRegister.rcx, b);
                            output.printf("\tsal    %s, cl\n", c);
                        } else if (instruction instanceof BitOrInstruction) {
                            output.printf("\tor     %s, %s\n", c, b);
                        } else if (instruction instanceof BitRightShiftInstruction) {
                            output.printf("\tmov    %s, %s\n", NASMRegister.rcx, b);
                            output.printf("\tsar    %s, cl\n", c);
                        } else if (instruction instanceof BitXorInstruction) {
                            output.printf("\txor     %s, %s\n", c, b);
                        } else if (instruction instanceof DivisionInstruction) {
                            moveFilter(NASMRegister.rax, c);
                            output.printf("\tcqo\n");
                            output.printf("\tidiv   %s\n", b);
                            moveFilter(c, NASMRegister.rax);
                        } else if (instruction instanceof EqualInstruction) {
                            output.printf("\tcmp    %s, %s\n", c, b);
                            output.printf("\tsete   al\n");
                            output.printf("\tmovzx    %s, al\n", c);
                        } else if (instruction instanceof GreaterThanInstruction) {
                            output.printf("\tcmp    %s, %s\n", c, b);
                            output.printf("\tsetg   al\n");
                            output.printf("\tmovzx    %s, al\n", c);
                        } else if (instruction instanceof GreaterThanOrEqualInstruction) {
                            output.printf("\tcmp    %s, %s\n", c, b);
                            output.printf("\tsetge   al\n");
                            output.printf("\tmovzx    %s, al\n", c);
                        } else if (instruction instanceof LessThanInstruction) {
                            output.printf("\tcmp    %s, %s\n", c, b);
                            output.printf("\tsetl   al\n");
                            output.printf("\tmovzx    %s, al\n", c);
                        } else if (instruction instanceof LessThanOrEqualInstruction) {
                            output.printf("\tcmp    %s, %s\n", c, b);
                            output.printf("\tsetle   al\n");
                            output.printf("\tmovzx    %s, al\n", c);
                        } else if (instruction instanceof ModuloInstruction) {
                            moveFilter(NASMRegister.rax, c);
                            output.printf("\tcqo\n");
                            output.printf("\tidiv   %s\n", b);
                            output.printf("\tmov    %s, %s\n", c, NASMRegister.rdx);
                        } else if (instruction instanceof MultiplicationInstruction) {
                            output.printf("\timul    %s, %s\n", c, b);
                        } else if (instruction instanceof NotEqualInstruction) {
                            output.printf("\tcmp    %s, %s\n", c, b);
                            output.printf("\tsetne   al\n");
                            output.printf("\tmovzx    %s, al\n", c);
                        } else if (instruction instanceof SubtractionInstruction) {
                            output.printf("\tsub    %s, %s\n", c, b);
                        }
                        store(c, ((BinaryInstruction) instruction).destination);
                    }
                } else if (instruction instanceof MemoryInstruction) {
                    if (instruction instanceof MoveInstruction) {
                        PhysicalRegister a = loadToRead(((MoveInstruction) instruction).source, NASMRegister.rax);
                        move(a, ((MoveInstruction) instruction).destination);
                    } else if (instruction instanceof AllocateInstruction) {
                        PhysicalRegister sizeR = loadToRead(((AllocateInstruction) instruction).size, NASMRegister.rax);
                        protectScene();
                        moveFilter(NASMRegister.rdi, sizeR);
                        output.printf("\tcall   malloc\n");
                        restoreScene();
                        move(NASMRegister.rax, ((AllocateInstruction) instruction).destination);
                    } else if (instruction instanceof LoadInstruction) {
                        PhysicalRegister baseR = loadToRead(((LoadInstruction) instruction).address.base, NASMRegister.rax);
                        PhysicalRegister destR = loadToWrite(((LoadInstruction) instruction).destination, NASMRegister.r10);
                        moveFilter(NASMRegister.r11, baseR);
                        if (((LoadInstruction) instruction).address.offset.literal != 0) {
                            output.printf("\tadd    %s, %s\n", NASMRegister.r11, ((LoadInstruction) instruction).address.offset);
                        }
                        output.printf("\tmov    %s, qword [%s]\n", destR, NASMRegister.r11);
                        store(destR, ((LoadInstruction) instruction).destination);
                    } else if (instruction instanceof StoreInstruction) {
                        PhysicalRegister baseR = loadToRead(((StoreInstruction) instruction).address.base, NASMRegister.r10);
                        PhysicalRegister a = loadToRead(((StoreInstruction) instruction).source, NASMRegister.rax);
                        moveFilter(NASMRegister.r11, baseR);
                        if (((StoreInstruction) instruction).address.offset.literal != 0) {
                            output.printf("\tadd    %s, %s\n", NASMRegister.r11, ((StoreInstruction) instruction).address.offset);
                        }
                        output.printf("\tmov    qword [%s], %s\n", NASMRegister.r11, a);
                    }
                } else if (instruction instanceof ControlFlowInstruction) {
                    if (instruction instanceof JumpInstruction) {
                        output.printf("\tjmp    %s\n", getBlockName(((JumpInstruction) instruction).to.block));
                    } else if (instruction instanceof BranchInstruction) {
                        PhysicalRegister condR = loadToRead(((BranchInstruction) instruction).condition, NASMRegister.rax);
                        output.printf("\tcmp    %s, 0\n", condR);
                        output.printf("\tjnz    %s\n", getBlockName(((BranchInstruction) instruction).trueTo.block));
                        output.printf("\tjz     %s\n", getBlockName(((BranchInstruction) instruction).falseTo.block));
                    }
                } else if (instruction instanceof FunctionInstruction) {
                    if (instruction instanceof ReturnInstruction) {
                        PhysicalRegister a = loadToRead(((ReturnInstruction) instruction).source, NASMRegister.rax);
                        moveFilter(NASMRegister.rax, a);
                        output.printf("\tjmp    %s\n", getBlockName(graph.exit));
                    } else if (instruction instanceof CallInstruction) {
                        protectScene();
                        VirtualRegister destination = ((CallInstruction) instruction).destination;
                        Function function = ((CallInstruction) instruction).function;
                        List<Operand> parameters = ((CallInstruction) instruction).parameters;
                        List<PhysicalRegister> order = new ArrayList<PhysicalRegister>() {{
                            add(NASMRegister.rdi);
                            add(NASMRegister.rsi);
                            add(NASMRegister.rdx);
                            add(NASMRegister.rcx);
                            add(NASMRegister.r8);
                            add(NASMRegister.r9);
                        }};
                        for (int i = 0; i < 6 && i < parameters.size(); i++) {
                            //rdi, rsi, rdx, rcx, r8, r9
                            //System.err.print(cur.name);
                            PhysicalRegister cur = loadToRead(parameters.get(i), NASMRegister.rax);
                            if (order.contains(cur) && !cur.callPreserved) {
                                output.printf("\tmov    %s, qword[rbp + (%d)]\n", order.get(i),
                                        -graph.frame.size - (cur.identity + 1) * NASMRegister.size());
                            } else {
                                output.printf("\tmov    %s, %s\n", order.get(i), cur);
                            }
                        }
                        if (parameters.size() > 6) {
                            for (int i = parameters.size() - 1; i >= 6; i--) {
                                PhysicalRegister cur = loadToRead(parameters.get(i), NASMRegister.rax);
                                output.printf("\tpush   %s\n", cur);
                            }
                        }
                        output.printf("\tcall   %s\n", getFunctionName(function));
                        restoreScene();
                        if (destination != null) {
                            PhysicalRegister destR = loadToWrite(destination, NASMRegister.r11);
                            //output.printf("\tmov    %s, %s\n", destR, NASMRegister.rax);
                            moveFilter(destR, NASMRegister.rax);
                            store(destR, destination);
                        }
                    }
                }
            }
        }
        
        for (PhysicalRegister physicalRegister : allocator.getUsedPhysicalRegisters()) {
            if (physicalRegister.callPreserved) {
                output.printf("\tmov    %s, qword [rbp + (%d)]\n",
                        physicalRegister,
                        -graph.frame.size - (physicalRegister.identity + 1) * NASMRegister.size());
            }
        }
        output.printf("\tleave\n");
        output.printf("\tret\n");
    }

}
