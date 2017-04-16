package AbstractSyntaxTree;

import Environment.ScopeTable.Scope;
import AbstractSyntaxTree.Statement.VariableDeclarationStatement;
import AbstractSyntaxTree.Type.ClassType.ClassType;
import Utility.Error.CompilationError;
import Utility.Utility;

import java.util.ArrayList;
import java.util.List;

public class Program implements Node, Scope {

    public List<Function> functions;
    public List<VariableDeclarationStatement> globalVariables;
    private List<ClassType> classTypes;

    private Program() {
        functions = new ArrayList<>();
        globalVariables = new ArrayList();
        classTypes = new ArrayList<>();
    }

    public static Program getProgram() {
        return new Program();
    }

    public void addClassType(ClassType classType) {
        classTypes.add(classType);
    }

    public void addFunction(Function function) {
        functions.add(function);
    }

    public void addGlobalVariable(VariableDeclarationStatement globalVarible) {
        globalVariables.add(globalVarible);
    }

    public void MainFunctionExistence(){
        for (int i = 0; i < functions.size(); ++i) {
            if (functions.get(i).name.equals("main")) return;
        }
        throw new CompilationError("A \"main\" function is expected in the program");
    }

    @Override
    public String toString() {
        return "[Program]";
    }

    @Override
    public String toString(int indents) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Utility.getIndent(indents)).append(toString()).append("\n");

        classTypes.forEach(classType -> {
            stringBuilder.append(classType.toString(indents + 1));
        });


        globalVariables.forEach(globalVariableDeclaration -> {
            stringBuilder.append(globalVariableDeclaration.toString(indents + 1));
        });

        functions.forEach(function -> {
            stringBuilder.append(function.toString(indents + 1));
        });

        return stringBuilder.toString();
    }
}
