package Utility.Error;

import ConcreteSyntaxTree.Listener.BaseListener;

public class CompilationError extends Error {
    public CompilationError(String errorMessage) {
        super("Compilation error:" + BaseListener.row + ":" + BaseListener.column + ": " + errorMessage + "!");
    }
}
