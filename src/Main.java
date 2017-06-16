import Environment.Environment;
import BackEnd.Allocator.GlobalRegisterAllocator.GlobalRegisterAllocator;
import BackEnd.ControlFlowGraph.Graph;
import BackEnd.Translator.NASM.NASMTranslator.NASMNaiveTranslator;
import FrontEnd.AbstractSyntaxTree.Function;
import FrontEnd.ConcreteSyntaxTree.Listener.ClassFetcherListener;
import FrontEnd.ConcreteSyntaxTree.Listener.DeclarationFetcherListener;
import FrontEnd.ConcreteSyntaxTree.Listener.SyntaxErrorListener;
import FrontEnd.ConcreteSyntaxTree.Listener.TreeBuilderListener;
import FrontEnd.ConcreteSyntaxTree.Parser.MildLexer;
import FrontEnd.ConcreteSyntaxTree.Parser.MildParser;
import Utility.Error.CompilationError;
import Utility.Error.InternalError;
import Utility.Utility;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws Exception {
        Utility.arguments = new HashSet<>(Arrays.asList(args));
        //InputStream is = new FileInputStream("program.txt");
        try {
            new Main().compile(System.in, System.out);
            //new Main().compile(is, System.out);
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
        Environment.program.MainFunctionExistence();
    }

    void compile(InputStream input, OutputStream output) throws Exception {
        Environment.Initialize();
        load(input);

        for (Function function : Environment.program.functions) {
          //  System.out.println(function.name);
            function.graph = new Graph(function);
            //function.allocator = new GlobalRegisterAllocator(function);
        }

        new NASMNaiveTranslator(new PrintStream(output)).translate();
    }

}
