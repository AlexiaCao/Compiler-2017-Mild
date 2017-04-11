import Environment.Environment;
import AbstractSyntaxTree.Function;
import ConcreteSyntaxTree.Listener.ClassFetcherListener;
import ConcreteSyntaxTree.Listener.DeclarationFetcherListener;
import ConcreteSyntaxTree.Listener.SyntaxErrorListener;
import ConcreteSyntaxTree.Listener.TreeBuilderListener;
import ConcreteSyntaxTree.Parser.MildLexer;
import ConcreteSyntaxTree.Parser.MildParser;
import Utility.Error.CompilationError;
import Utility.Error.InternalError;
import Utility.Utility;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Utility.arguments = new HashSet<>(Arrays.asList(args));
        try {
            new Main().compile(System.in, System.out);
        } catch (CompilationError e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (InternalError e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void load(InputStream file) throws Exception {

        ANTLRInputStream input = new ANTLRInputStream(file);
        MildLexer lexer = new MildLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MildParser parser = new MildParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new SyntaxErrorListener());
        ParseTree tree = parser.program();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new ClassFetcherListener(), tree);
        walker.walk(new DeclarationFetcherListener(), tree);
        Environment.classTable.analysis();
        walker.walk(new TreeBuilderListener(), tree);
    }

    void compile(InputStream input, OutputStream output) throws Exception {
        Environment.Initialize();
        load(input);
        if (Utility.arguments.contains("-ast")) {
            System.err.println(Environment.program.toString(0));
        }
    }

}
