package FrontEnd.AbstractSyntaxTree.Type.ClassType.Member;

import FrontEnd.AbstractSyntaxTree.Expression.Expression;
import FrontEnd.AbstractSyntaxTree.Type.ClassType.ClassType;
import FrontEnd.AbstractSyntaxTree.Type.Type;
import Utility.Utility;

public class MemberVariable extends Member {
    public Type type;
    public int offset;
    public Expression expression;

    public MemberVariable(ClassType classType, String name, Type type){
        super(name);
        this.type = type;
        this.offset = classType.allocateSize;
        classType.allocateSize += Utility.getAligned(type.size());
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
