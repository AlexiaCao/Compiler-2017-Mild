package BackEnd.Translator.NASM.NASMTranslator;

import BackEnd.Allocator.PhysicalRegister;
import BackEnd.ControlFlowGraph.Block;
import BackEnd.ControlFlowGraph.Graph;
import BackEnd.ControlFlowGraph.Instruction.ArithmeticInstruction.ArithmeticInstruction;
import BackEnd.ControlFlowGraph.Instruction.ArithmeticInstruction.BinaryInstruction.*;
import BackEnd.ControlFlowGraph.Instruction.ArithmeticInstruction.UnaryInstruction.BitNotInstruction;
import BackEnd.ControlFlowGraph.Instruction.ArithmeticInstruction.UnaryInstruction.UnaryInstruction;
import BackEnd.ControlFlowGraph.Instruction.ArithmeticInstruction.UnaryInstruction.UnaryMinusInstruction;
import BackEnd.ControlFlowGraph.Instruction.ControlFlowInstruction.BranchInstruction;
import BackEnd.ControlFlowGraph.Instruction.ControlFlowInstruction.ControlFlowInstruction;
import BackEnd.ControlFlowGraph.Instruction.ControlFlowInstruction.JumpInstruction;
import BackEnd.ControlFlowGraph.Instruction.FunctionInstruction.CallInstruction;
import BackEnd.ControlFlowGraph.Instruction.FunctionInstruction.FunctionInstruction;
import BackEnd.ControlFlowGraph.Instruction.FunctionInstruction.ReturnInstruction;
import BackEnd.ControlFlowGraph.Instruction.Instruction;
import BackEnd.ControlFlowGraph.Instruction.LabelInstruction;
import BackEnd.ControlFlowGraph.Instruction.MemoryInstruction.*;
import BackEnd.ControlFlowGraph.Operand.Operand;
import BackEnd.ControlFlowGraph.Operand.Address;
import BackEnd.ControlFlowGraph.Operand.VirtualRegister.VirtualRegister;
import BackEnd.Translator.NASM.NASMRegister;
import BackEnd.Translator.NASM.NASMTranslator.NASMTranslator;
import FrontEnd.AbstractSyntaxTree.Function;;
import FrontEnd.AbstractSyntaxTree.Type.BasicType.VoidType;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;


public class NASMNaiveTranslator extends NASMTranslator {
	public NASMNaiveTranslator(PrintStream output) {
		super(output);
	}
	
	@Override
	public void translate(Graph graph){
		this.graph = graph;
		output.printf("%s:\n", getFunctionName(graph.function));

		output.printf("\tpush	rbp\n");
		output.printf("\tmov	rbp, rsp\n");
		output.printf("\tsub	rsp, %d\n", graph.frame.size);
		output.printf("\tmov	qword [rbp-8], rdi\n");
		output.printf("\tmov	qword [rbp-16], rsi\n");
		output.printf("\tmov	qword [rbp-24], rdx\n");
		output.printf("\tmov	qword [rbp-32], rcx\n");
		output.printf("\tmov	qword [rbp-40], r8\n");
		output.printf("\tmov	qword [rbp-48], r9\n");

		for (Block block :graph.blocks) {
			output.printf("%s:\n", getBlockName(block));
			for (Instruction instruction : block.instructions) {
				//output.printf("\t%s")
				if (instruction instanceof  LabelInstruction) {
					output.printf("%s:\n", getBlockName(((LabelInstruction) instruction).block));
				}else if (instruction instanceof ArithmeticInstruction) {
					if (instruction instanceof BinaryInstruction) {
						VirtualRegister destination = ((BinaryInstruction) instruction).destination;
						Operand operand1 = ((BinaryInstruction) instruction).source1;
						Operand operand2 = ((BinaryInstruction) instruction).source2;
						output.printf("\tmov    %s, %s\n", NASMRegister.r11, getPhysicalMemoryName(operand1));
						if (instruction instanceof AdditionInstruction) {
							output.printf("\tadd    %s, %s\n", NASMRegister.r11, getPhysicalMemoryName(operand2));
						} else if (instruction instanceof BitAndInstruction) {
							output.printf("\tand    %s, %s\n", NASMRegister.r11, getPhysicalMemoryName(operand2));
						} else if (instruction instanceof BitLeftShiftInstruction) {
							output.printf("\tmov    %s, %s\n", NASMRegister.rcx, getPhysicalMemoryName(operand2));
							output.printf("\tsal    %s, cl\n", NASMRegister.r11);
						} else if (instruction instanceof BitOrInstruction) {
							output.printf("\tor    %s, %s\n", NASMRegister.r11, getPhysicalMemoryName(operand2));
						} else if (instruction instanceof BitRightShiftInstruction) {
							output.printf("\tmov    %s, %s\n", NASMRegister.rcx, getPhysicalMemoryName(operand2));
							output.printf("\tsar    %s, cl\n", NASMRegister.r11);
						} else if (instruction instanceof BitXorInstruction) {
							output.printf("\txor    %s, %s\n", NASMRegister.r11, getPhysicalMemoryName(operand2));
						} else if (instruction instanceof DivisionInstruction) {
							output.printf("\tmov    %s, %s\n", NASMRegister.rax, getPhysicalMemoryName(operand1));
							output.printf("\tcqo\n");
							output.printf("\tmov    %s, %s\n", NASMRegister.r11, getPhysicalMemoryName(operand2));
							output.printf("\tidiv   %s\n", NASMRegister.r11);
							output.printf("\tmov    %s, %s\n", NASMRegister.r11, NASMRegister.rax);
						} else if (instruction instanceof EqualInstruction) {
							output.printf("\tcmp    %s, %s\n", NASMRegister.r11, getPhysicalMemoryName(operand2));
							output.printf("\tsete   al\n");
							output.printf("\tmovzx  %s, al\n", NASMRegister.r11);
						} else if (instruction instanceof GreaterThanInstruction) {
							output.printf("\tcmp    %s, %s\n", NASMRegister.r11, getPhysicalMemoryName(operand2));
							output.printf("\tsetg   al\n");
							output.printf("\tmovzx  %s, al\n", NASMRegister.r11);
						} else if (instruction instanceof GreaterThanOrEqualInstruction) {
							output.printf("\tcmp    %s, %s\n", NASMRegister.r11, getPhysicalMemoryName(operand2));
							output.printf("\tsetge   al\n");
							output.printf("\tmovzx  %s, al\n", NASMRegister.r11);
						} else if (instruction instanceof LessThanInstruction) {
							output.printf("\tcmp    %s, %s\n", NASMRegister.r11, getPhysicalMemoryName(operand2));
							output.printf("\tsetl   al\n");
							output.printf("\tmovzx  %s, al\n", NASMRegister.r11);
						} else if (instruction instanceof LessThanOrEqualInstruction) {
							output.printf("\tcmp    %s, %s\n", NASMRegister.r11, getPhysicalMemoryName(operand2));
							output.printf("\tsetle  al\n");
							output.printf("\tmovzx  %s, al\n", NASMRegister.r11);
						} else if (instruction instanceof ModuloInstruction) {
							output.printf("\tmov    %s, %s\n", NASMRegister.rax, getPhysicalMemoryName(operand1));
							output.printf("\tcqo\n");
							output.printf("\tmov    %s, %s\n", NASMRegister.r11, getPhysicalMemoryName(operand2));
							output.printf("\tidiv   %s\n", NASMRegister.r11);
							output.printf("\tmov    %s, %s\n", NASMRegister.r11, NASMRegister.rdx);
						} else if (instruction instanceof MultiplicationInstruction) {
							output.printf("\timul   %s, %s\n", NASMRegister.r11, getPhysicalMemoryName(operand2));
						} else if (instruction instanceof NotEqualInstruction) {
							output.printf("\tcmp    %s, %s\n", NASMRegister.r11, getPhysicalMemoryName(operand2));
							output.printf("\tsetne  al\n");
							output.printf("\tmovzx  %s, al\n", NASMRegister.r11);
						} else if (instruction instanceof SubtractionInstruction) {
							output.printf("\tsub    %s, %s\n", NASMRegister.r11, getPhysicalMemoryName(operand2));
						}
						output.printf("\tmov    %s, %s\n", getPhysicalMemoryName(destination), NASMRegister.r11);
					} else if (instruction instanceof UnaryInstruction) {
						VirtualRegister destination = ((UnaryInstruction) instruction).destination;
						Operand operand = ((UnaryInstruction) instruction).source;
						output.printf("\tmov    %s, %s\n", NASMRegister.r11, getPhysicalMemoryName(operand));
						if (instruction instanceof BitNotInstruction) {
							output.printf("\tnot    %s\n", NASMRegister.r11);
						} else if (instruction instanceof UnaryMinusInstruction) {
							output.printf("\tneg    %s\n", NASMRegister.r11);
						}
						output.printf("\tmov    %s, %s\n", getPhysicalMemoryName(destination), NASMRegister.r11);
					}
				}else if (instruction instanceof ControlFlowInstruction) {
					if (instruction instanceof JumpInstruction) {
						output.printf("\tjmp    %s\n", getBlockName(((JumpInstruction) instruction).to.block));
					} else if (instruction instanceof BranchInstruction) {
						Operand condition = ((BranchInstruction) instruction).condition;
						LabelInstruction trueTo = ((BranchInstruction) instruction).trueTo;
						LabelInstruction falseTo = ((BranchInstruction) instruction).falseTo;
						output.printf("\tcmp    %s, 0\n", getPhysicalMemoryName(condition));
						output.printf("\tjnz    %s\n", getBlockName(trueTo.block));
						output.printf("\tjz     %s\n", getBlockName(falseTo.block));
					}
				}else if (instruction instanceof FunctionInstruction) {
					if (instruction instanceof ReturnInstruction) {
						if (!(graph.function.type instanceof VoidType)) {
							output.printf("\tmov    %s, %s\n", NASMRegister.rax, getPhysicalMemoryName(((ReturnInstruction) instruction).source));
						}
						output.printf("\tleave\n");
						output.printf("\tret\n");
					} else if (instruction instanceof CallInstruction) {
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
							output.printf("\tmov    %s, %s\n", order.get(i), getPhysicalMemoryName(parameters.get(i)));
						}
						if (parameters.size() > 6) {
							for (int i = parameters.size() - 1; i >= 6; i--) {
								output.printf("\tpush   %s\n", getPhysicalMemoryName(parameters.get(i)));
							}
						}
						output.printf("\tcall   %s\n", getFunctionName(function));
						if (destination != null) {
							output.printf("\tmov    %s, %s\n", getPhysicalMemoryName(destination), NASMRegister.rax);
						}
					}
				}else if (instruction instanceof MemoryInstruction) {
					if (instruction instanceof AllocateInstruction){
						VirtualRegister destination = ((AllocateInstruction) instruction).destination;
						Operand size = ((AllocateInstruction) instruction).size;
						output.printf("\tmov    %s, %s\n", NASMRegister.rdi, getPhysicalMemoryName(size));
						output.printf("\tcall   malloc\n");
						output.printf("\tmov    %s, %s\n", getPhysicalMemoryName(destination), NASMRegister.rax);
					}else if (instruction instanceof LoadInstruction) {
						VirtualRegister destination = ((LoadInstruction) instruction).destination;
						Address address = ((LoadInstruction) instruction).address;
						output.printf("\tmov    %s, %s\n", NASMRegister.r11, getPhysicalMemoryName(address.base));
						output.printf("\tadd    %s, %s\n", NASMRegister.r11, address.offset);
						output.printf("\tmov    %s, qword [%s]\n", NASMRegister.rax, NASMRegister.r11);
						output.printf("\tmov    %s, %s\n", getPhysicalMemoryName(destination), NASMRegister.rax);
					}else if (instruction instanceof MoveInstruction){
						VirtualRegister destination = ((MoveInstruction) instruction).destination;
						Operand operand = ((MoveInstruction) instruction).source;
						output.printf("\tmov    %s, %s\n", NASMRegister.r11, getPhysicalMemoryName(operand));
						output.printf("\tmov    %s, %s\n", getPhysicalMemoryName(destination), NASMRegister.r11);
					}else if (instruction instanceof StoreInstruction){
						Operand operand = ((StoreInstruction) instruction).source;
						Address address = ((StoreInstruction) instruction).address;
						output.printf("\tmov    %s, %s\n", NASMRegister.r11, getPhysicalMemoryName(address.base));
						output.printf("\tadd    %s, %s\n", NASMRegister.r11, address.offset);
						output.printf("\tmov    %s, %s\n", NASMRegister.rax, getPhysicalMemoryName(operand));
						output.printf("\tmov    qword [%s], %s\n", NASMRegister.r11, NASMRegister.rax);
					}
				}
			}

		}
		output.printf("\tleave\n");
		output.printf("\tret\n");
	}
}