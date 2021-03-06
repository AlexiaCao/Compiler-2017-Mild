package BackEnd.ControlFlowGraph.Instruction.FunctionInstruction;

import BackEnd.ControlFlowGraph.Instruction.Instruction;
import BackEnd.ControlFlowGraph.Operand.Operand;
import BackEnd.ControlFlowGraph.Operand.VirtualRegister.VirtualRegister;
import Environment.Environment;
import FrontEnd.AbstractSyntaxTree.Function;
import FrontEnd.AbstractSyntaxTree.Statement.VariableDeclarationStatement;
import Utility.Error.InternalError;

import java.util.ArrayList;
import java.util.List;

public class CallInstruction extends FunctionInstruction {
    public VirtualRegister destination;
    public Function function;
    public List<Operand> parameters;

    private CallInstruction(VirtualRegister destination, Function function, List<Operand> parameters) {
        this.destination = destination;
        this.function = function;
        this.parameters = parameters;
    }

    public static Instruction getInstruction(Operand destination, Function function, List<Operand> parameters) {
        if (destination == null) {
            return new CallInstruction(null, function, parameters);
        } else if (destination instanceof VirtualRegister) {
            return new CallInstruction((VirtualRegister)destination, function, parameters);
        }
        throw new InternalError("CallInstruction:getInstruction.");
    }

    @Override
    public List<Operand> getDefinedOperands() {
        return new ArrayList<Operand>() {{
            if (destination != null) {
                add(destination);
            }
            if (!function.name.startsWith("____builtin")) {
                for (VariableDeclarationStatement variable : Environment.program.globalVariables) {
                    add(variable.symbol.register);
                }
            }
        }};
    }

    @Override
    public List<Operand> getUsedOperands() {
        return new ArrayList<Operand>() {{
            addAll(parameters);
            if (!function.name.startsWith("____builtin")) {
                for (VariableDeclarationStatement variable : Environment.program.globalVariables) {
                    add(variable.symbol.register);
                }
            }
        }};
    }

    @Override
    public void setDefinedRegister(VirtualRegister from, VirtualRegister to) {
        if (destination == from) {
            destination = to;
        }
    }

    @Override
    public void setUsedRegister(VirtualRegister from, Operand to) {
        for (int i = 0; i < parameters.size(); ++i) {
            if (parameters.get(i) == from) {
                parameters.set(i, to);
            }
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (destination != null) {
            stringBuilder.append(destination).append(" = ");
        }
        stringBuilder.append("call ").append(function.name);
        parameters.forEach(parameter -> stringBuilder.append(" ").append(parameter));
        return stringBuilder.toString();
    }
}
