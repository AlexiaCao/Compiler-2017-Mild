package Environment.SymbolTable;

import Environment.Environment;
import Environment.ScopeTable.Scope;
import FrontEnd.AbstractSyntaxTree.Type.Type;
import BackEnd.ControlFlowGraph.Operand.VirtualRegister.VirtualRegister;


public class Symbol {
    public String name;
    public Type type;
    public Scope scope;
    public VirtualRegister register;

    public Symbol(String name, Type type) {
        this.name = name;
        this.type = type;
        this.scope = Environment.scopeTable.getScope();
        this.register = null;
    }
}
