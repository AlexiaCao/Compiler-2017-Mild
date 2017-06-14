package FrontEnd.ConcreteSyntaxTree.Listener;

import FrontEnd.AbstractSyntaxTree.Node;
import FrontEnd.ConcreteSyntaxTree.Parser.MildBaseListener;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public abstract class BaseListener extends MildBaseListener {
    public static int row, column;
    static ParseTreeProperty<Node> returnNode = new ParseTreeProperty<>();

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        row = ctx.getStart().getLine();
        column = ctx.getStart().getCharPositionInLine();
    }
}

