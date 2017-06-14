package Environment;

import Environment.ScopeTable.Scope;
import Environment.ScopeTable.ScopeTable;
import Environment.SymbolTable.Symbol;
import Environment.SymbolTable.SymbolTable;
import FrontEnd.AbstractSyntaxTree.Function;
import FrontEnd.AbstractSyntaxTree.Program;
import FrontEnd.AbstractSyntaxTree.Type.BasicType.BoolType;
import FrontEnd.AbstractSyntaxTree.Type.BasicType.IntType;
import FrontEnd.AbstractSyntaxTree.Type.BasicType.StringType;
import FrontEnd.AbstractSyntaxTree.Type.BasicType.VoidType;

import java.util.ArrayList;

public class Environment {
    public static Program program;
    public static ScopeTable scopeTable;
    public static SymbolTable symbolTable;
    public static ClassTable classTable;
    public static RegisterTable registerTable;

    public static void Initialize() {
        scopeTable = new ScopeTable();
        symbolTable = new SymbolTable();
        classTable = new ClassTable();
        registerTable = new RegisterTable();
        enterScope(program = Program.getProgram());
        loadLibraryFunctions();
    }

    public static void enterScope(Scope scope) {
        scopeTable.enterScope(scope);
        symbolTable.enterScope();
    }

    public static void exitScope(){
        scopeTable.exitScope();
        symbolTable.exitScope();
    }

    private static void loadLibraryFunctions() {

        symbolTable.add("print", Function.getFunction(
                "____builtin____print",
                VoidType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("str", StringType.getType()));
                }}
        ));

        symbolTable.add("println", Function.getFunction(
                "____builtin____println",
                VoidType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("str", StringType.getType()));
                }}
        ));

        symbolTable.add("getString", Function.getFunction(
                "____builtin____get_string",
                StringType.getType(),
                new ArrayList<>()
        ));

        symbolTable.add("getInt", Function.getFunction(
                "____builtin____get_int",
                IntType.getType(),
                new ArrayList<>()
        ));

        symbolTable.add("toString", Function.getFunction(
                "____builtin____to_string",
                StringType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("int", IntType.getType()));
                }}
        ));

        symbolTable.add("____builtin_array____size", Function.getFunction(
                "____builtin_array____size",
                IntType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("this", VoidType.getType()));
                }}
        ));

        symbolTable.add("____builtin_string____length", Function.getFunction(
                "____builtin_string____length",
                IntType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("this", StringType.getType()));
                }}
        ));

        symbolTable.add("____builtin_string____ord", Function.getFunction(
                "____builtin_string____ord",
                IntType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("this", StringType.getType()));
                    add(new Symbol("pos", IntType.getType()));
                }}
        ));

        symbolTable.add("____builtin_string____substring", Function.getFunction(
                "____builtin_string____substring",
                StringType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("this", StringType.getType()));
                    add(new Symbol("lhs", IntType.getType()));
                    add(new Symbol("rhs", IntType.getType()));
                }}
        ));

        symbolTable.add("____builtin_string____parse_int", Function.getFunction(
                "____builtin_string____parse_int",
                IntType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("this", StringType.getType()));
                }}
        ));

        symbolTable.add("____builtin_string____concatenate", Function.getFunction(
                "____builtin_string____concatenate",
                StringType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("lhs", StringType.getType()));
                    add(new Symbol("rhs", StringType.getType()));
                }}
        ));

        symbolTable.add("____builtin_string____equal_to", Function.getFunction(
                "____builtin_string____equal_to",
                BoolType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("lhs", StringType.getType()));
                    add(new Symbol("rhs", StringType.getType()));
                }}
        ));

        symbolTable.add("____builtin_string____greater_than", Function.getFunction(
                "____builtin_string____greater_than",
                BoolType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("lhs", StringType.getType()));
                    add(new Symbol("rhs", StringType.getType()));
                }}
        ));

        symbolTable.add("____builtin_string____greater_than_or_equal_to", Function.getFunction(
                "____builtin_string____greater_than_or_equal_to",
                BoolType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("lhs", StringType.getType()));
                    add(new Symbol("rhs", StringType.getType()));
                }}
        ));
        symbolTable.add("____builtin_string____less_than", Function.getFunction(
                "____builtin_string____less_than",
                BoolType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("lhs", StringType.getType()));
                    add(new Symbol("rhs", StringType.getType()));
                }}
        ));
        symbolTable.add("____builtin_string____less_than_or_equal_to", Function.getFunction(
                "____builtin_string____less_than_or_equal_to",
                BoolType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("lhs", StringType.getType()));
                    add(new Symbol("rhs", StringType.getType()));
                }}
        ));
        symbolTable.add("____builtin_string____not_equal_to", Function.getFunction(
                "____builtin_string____not_equal_to",
                BoolType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("lhs", StringType.getType()));
                    add(new Symbol("rhs", StringType.getType()));
                }}
        ));
    }
}