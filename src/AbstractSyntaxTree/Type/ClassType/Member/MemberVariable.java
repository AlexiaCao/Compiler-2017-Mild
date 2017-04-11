package AbstractSyntaxTree.Type.ClassType.Member;

import AbstractSyntaxTree.Expression.Expression;
import AbstractSyntaxTree.Type.ClassType.ClassType;
import AbstractSyntaxTree.Type.Type;
import Utility.Utility;

public class MemberVariable extends Member {
    public Type type;
    public Expression expression;

    public MemberVariable(ClassType classType, String name, Type type){
        super(name);
        this.type = type;
    }

    @Override
    public String toString() {
        return "[" + name + ", type = " + type + "]";
    }

    public String toStringTree(int indents) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Utility.getIndent(indents)).append(this).append("\n");
        if (expression != null) {
            stringBuilder.append(expression.toString(indents + 1));
        }
        return stringBuilder.toString();
    }
}
