package BackEnd.ControlFlowGraph.Operand.VirtualRegister.VariableRegister;

import BackEnd.ControlFlowGraph.Operand.VirtualRegister.VirtualRegister;
import Environment.SymbolTable.Symbol;

public class VariableRegister extends VirtualRegister {
    public Symbol symbol;

    protected VariableRegister(Symbol symbol) {
        this.symbol = symbol;
    }
}
