package FrontEnd.ConcreteSyntaxTree.Listener;

import Environment.Environment;
import FrontEnd.AbstractSyntaxTree.Type.ClassType.ClassType;
import FrontEnd.ConcreteSyntaxTree.Parser.MildParser;

public class ClassFetcherListener extends BaseListener {
    @Override
    public void exitClassDeclaration(MildParser.ClassDeclarationContext ctx) {
        String name = ctx.IDENTIFIER(0).getText();
        ClassType classType = (ClassType)ClassType.getType(name);
        //	put the class into class table
        Environment.classTable.put(name, classType);
        //	put the class into program
        Environment.program.addClassType(classType);
        returnNode.put(ctx, classType);
    }
}
