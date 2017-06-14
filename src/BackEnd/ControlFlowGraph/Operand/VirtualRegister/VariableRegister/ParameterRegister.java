package BackEnd.ControlFlowGraph.Operand.VirtualRegister.VariableRegister;

import Environment.SymbolTable.Symbol;

public class ParameterRegister extends VariableRegister {
    public ParameterRegister(Symbol symbol) {
        super(symbol);
    }

    @Override
    public String toString() {
        return String.format("$p%s", identity);
    }
}
