package AbstractSyntaxTree;

import Environment.Environment;
import Environment.ScopeTable.Scope;
import Environment.ScopeTable.ScopeTable;
import Environment.SymbolTable.Symbol;
import AbstractSyntaxTree.Statement.BlockStatement;
import AbstractSyntaxTree.Type.BasicType.IntType;
import AbstractSyntaxTree.Type.Type;
import Utility.Error.CompilationError;
import Utility.Utility;

import java.util.ArrayList;
import java.util.List;

public class Function extends Type implements Node, Scope {
    public String name;
    public Type type;
    public List<Symbol> parameters;
    public BlockStatement statements;

    private Function(String name, Type type, List<Symbol> parameters) {
        this.name = name;
        this.type = type;
        this.parameters = parameters;
    }

    public static Function getFunction(String name, Type returnType, List<Symbol> parameters) {
        if (Environment.scopeTable.getClassScope() == null) {
            if (Environment.symbolTable.contains(name)) {
                throw new CompilationError("The program cannot have two functions named \"" + name + "\"");
            }
        }

        if (name.equals("this")) {
            throw new CompilationError("The program cannot have a function named \"this\"");
        }
        if (name.equals("main")) {
            if (!(returnType instanceof IntType)) {
                throw new CompilationError("The function \"main()\" should be an IntType function");
            }
            if (parameters.size() != 0) {
                throw new CompilationError("The function \"main()\" cannot have any parameters");
            }
        }

        for (int i = 0; i < parameters.size(); ++i) {
            for (int j = i + 1; j < parameters.size(); ++j) {
                if (parameters.get(i).name.equals(parameters.get(j).name)) {
                    throw new CompilationError("The function \"" + name + "\" cannot have two parameters named \"" + parameters.get(i).name + "\"");
                }
            }
        }

        return new Function(name, returnType, parameters);
    }

    public void addStatements(BlockStatement statements) {
        this.statements = statements;
    }

    public  List<Type> getParameterTypes() {
        List<Type> parameterTypes = new ArrayList<>();
        parameters.forEach(parameter -> parameterTypes.add(parameter.type));
        return parameterTypes;
    }

    @Override
    public boolean Compatible(Type other) {
        return false;
    }

    @Override
    public boolean Castable(Type other) {
        return false;
    }

    @Override
    public String toString() {
        return "[function: name = " + name + ", type = " + type + "]";
    }

    @Override
    public String toString(int indents) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Utility.getIndent(indents)).append(toString()).append("\n");
        parameters.forEach(parameter -> {
            stringBuilder.append(Utility.getIndent(indents + 1));
            stringBuilder.append("[parameter: name = ").append(parameter.name).append(", type = ").append(parameter.type).append("]").append("\n");
        });
        if (statements != null) {
            stringBuilder.append(statements.toString(indents + 1));
        }
        return stringBuilder.toString();
    }
}
