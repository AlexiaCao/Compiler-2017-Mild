package BackEnd.Translator.NASM.NASMTranslator;

import BackEnd.ControlFlowGraph.Graph;
import BackEnd.ControlFlowGraph.Block;
import BackEnd.ControlFlowGraph.Operand.ImmediateValue;
import BackEnd.ControlFlowGraph.Operand.Operand;
import BackEnd.ControlFlowGraph.Operand.VirtualRegister.StringRegister;
import BackEnd.ControlFlowGraph.Operand.VirtualRegister.VariableRegister.GlobalRegister;
import BackEnd.ControlFlowGraph.Operand.VirtualRegister.VariableRegister.ParameterRegister;
import BackEnd.ControlFlowGraph.Operand.VirtualRegister.VariableRegister.TemporaryRegister;
import BackEnd.ControlFlowGraph.Operand.VirtualRegister.VariableRegister.VariableRegister;
import BackEnd.ControlFlowGraph.Operand.VirtualRegister.VirtualRegister;
import BackEnd.Translator.Translator;
import BackEnd.Translator.Template;
import FrontEnd.AbstractSyntaxTree.Function;
import Environment.Environment;
import Utility.Utility;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public abstract class NASMTranslator extends Translator {
	public Graph graph;

	public NASMTranslator(PrintStream output){
		super(output);
	}

	public String getFunctionName(Function function) {
		if (function.name.equals("main") || function.name.startsWith("__builtin")) {
			return function.name;
		} else {
			return String.format("__%s_function", function.name);
		}
	}

	public String getBlockName(Block block){
		return String.format("__%s_%d__%s", block.function.name, block.identity, block.name);
	}

	public String getPhysicalMemoryName(Operand register) {
		if (register instanceof GlobalRegister) {
			return "qword [rel " + "GlobalVariable_" + ((GlobalRegister) register).symbol.name + "]";
		}else if (register instanceof ParameterRegister){
			int offset = graph.frame.parameter.get(register);
			return String.format("qword [rbp+(%d)]", offset);
		}else if (register instanceof TemporaryRegister){
			int offset = graph.frame.temporary.get(register);
			return String.format("qword [rbp+(%d)]", offset);
		}else if (register instanceof StringRegister) {
			return String.format("__const_string_%d", ((StringRegister) register).identity);
		}else if (register instanceof ImmediateValue){
			return String.valueOf(((ImmediateValue) register).literal);
		}
		return "ERROR!!!";
	}

	@Override
	public void translate() throws Exception {
		Template template = new Template();
		output.print(template.templateStr);
		for (Function function : Environment.program.functions) {
			translate(function.graph);
		}
		output.println("\tsection .data");
		for (VirtualRegister register : Environment.registerTable.registers){
			if (register instanceof GlobalRegister) {
				output.printf("%s:\n\tdq 0\n", "GlobalVariable_" + ((GlobalRegister)register).symbol.name);
			}
			else if (register instanceof StringRegister){
				output.printf("__const_string_%d:\n\tdb ", register.identity);
				String str = ((StringRegister) register).literal;
				String tmp = "";
				for (int i = 0; i < str.length(); i++) {
					if (i + 1 < str.length() && str.substring(i).startsWith("\\n")){
						output.printf("%d, ", 10);
						i++;
					}
					else if (i + 1 < str.length() && str.substring(i).startsWith("\\\\")) {
						output.printf("%d, ", 92);
						i++;
					} else if (i + 1 < str.length() && str.substring(i).startsWith("\\\"")) {
						output.printf("%d, ", 34);
						i++;
					} else {
						int t = str.charAt(i);
						output.printf("%d, ", t);
						/*output.printf("\"");
						for (; x < str.length(); x++) {
							if (x + 1 < str.length() && (str.substring(x).startsWith("\\n") || str.substring(x).startsWith("\\\\") || str.substring(x).startsWith("\\\""))){
								break;
							}
							output.printf("%c, ", str.charAt(x));
						}
						output.printf("\", ");*/
					}
				}
				output.println("0\n");
			}
		}
		output.printf("STRING_FORMAT:\n\tdb \"%%s\", 0\n");
		output.printf("INTEGER_FORMAT_NEXT_LINE:\n\tdb \"%%lld\", 10, 0\n");
		output.printf("INT_FORMAT_NEXT_LINE:\n\tdb \"%%d\", 10, 0\n");
		output.printf("INTEGER_FORMAT:\n\tdb \"%%lld\", 0\n");
		output.printf("CHAR_FORMAT:\n\tdb \"%%c\", 0\n");
		output.printf("NEXT_LINE:\n\tdb 10, 0\n");
	}
}