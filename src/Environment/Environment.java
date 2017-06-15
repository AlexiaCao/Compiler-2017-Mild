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
                "__builtin_print",
                VoidType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("str", StringType.getType()));
                }}
        ));

        symbolTable.add("println", Function.getFunction(
                "__builtin_println",
                VoidType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("str", StringType.getType()));
                }}
        ));

        symbolTable.add("getString", Function.getFunction(
                "__builtin_getString",
                StringType.getType(),
                new ArrayList<>()
        ));

        symbolTable.add("getInt", Function.getFunction(
                "__builtin_getInt",
                IntType.getType(),
                new ArrayList<>()
        ));

        symbolTable.add("toString", Function.getFunction(
                "__builtin_toString",
                StringType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("int", IntType.getType()));
                }}
        ));

        symbolTable.add("__builtin_getArraySize", Function.getFunction(
                "__builtin_getArraySize",
                IntType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("this", VoidType.getType()));
                }}
        ));

        symbolTable.add("__builtin_getStringLength", Function.getFunction(
                "__builtin_getStringLength",
                IntType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("this", StringType.getType()));
                }}
        ));

        symbolTable.add("__builtin_ord", Function.getFunction(
                "__builtin_ord",
                IntType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("this", StringType.getType()));
                    add(new Symbol("pos", IntType.getType()));
                }}
        ));

        symbolTable.add("__builtin_getSubstring", Function.getFunction(
                "__builtin_getSubstring",
                StringType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("this", StringType.getType()));
                    add(new Symbol("lhs", IntType.getType()));
                    add(new Symbol("rhs", IntType.getType()));
                }}
        ));

        symbolTable.add("__builtin_string__parse_int", Function.getFunction(
                "__builtin_parseInt",
                IntType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("this", StringType.getType()));
                }}
        ));

        symbolTable.add("__builtin_string_concat", Function.getFunction(
                "__builtin_string_concat",
                StringType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("lhs", StringType.getType()));
                    add(new Symbol("rhs", StringType.getType()));
                }}
        ));

        symbolTable.add("__builtin_string_equalTo", Function.getFunction(
                "__builtin_string_equalTo",
                BoolType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("lhs", StringType.getType()));
                    add(new Symbol("rhs", StringType.getType()));
                }}
        ));

        symbolTable.add("__builtin_string_greaterThan", Function.getFunction(
                "__builtin_string_greaterThan",
                BoolType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("lhs", StringType.getType()));
                    add(new Symbol("rhs", StringType.getType()));
                }}
        ));

        symbolTable.add("__builtin_string_greaterThanOrEqualTo", Function.getFunction(
                "__builtin_string_greaterThanOrEqualTo",
                BoolType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("lhs", StringType.getType()));
                    add(new Symbol("rhs", StringType.getType()));
                }}
        ));
        symbolTable.add("__builtin_string_lessThan", Function.getFunction(
                "__builtin_string_lessThan",
                BoolType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("lhs", StringType.getType()));
                    add(new Symbol("rhs", StringType.getType()));
                }}
        ));
        symbolTable.add("__builtin_string_lessThanOrEqualTo", Function.getFunction(
                "__builtin_string_lessThanOrEqualTo",
                BoolType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("lhs", StringType.getType()));
                    add(new Symbol("rhs", StringType.getType()));
                }}
        ));
        /*symbolTable.add("____builtin_string____not_equal_to", Function.getFunction(
                "____builtin_string____not_equal_to",
                BoolType.getType(),
                new ArrayList<Symbol>() {{
                    add(new Symbol("lhs", StringType.getType()));
                    add(new Symbol("rhs", StringType.getType()));
                }}
        ));*/
    }
}