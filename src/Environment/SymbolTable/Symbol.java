package Environment.SymbolTable;

import Environment.Environment;
import Environment.ScopeTable.Scope;
import AbstractSyntaxTree.Type.Type;

public class Symbol {
    public String name;
    public Type type;
    public Scope scope;

    public Symbol(String name, Type type) {
        this.name = name;
        this.type = type;
        this.scope = Environment.scopeTable.getScope();
    }
}
