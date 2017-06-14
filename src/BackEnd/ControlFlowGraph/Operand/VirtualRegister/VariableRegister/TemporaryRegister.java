package BackEnd.ControlFlowGraph.Operand.VirtualRegister.VariableRegister;

import Environment.SymbolTable.Symbol;

public class TemporaryRegister extends VariableRegister {
    public TemporaryRegister(Symbol symbol) {
        super(symbol);
    }

    @Override
    public String toString() {
        return String.format("$t%s", identity);
    }
}
