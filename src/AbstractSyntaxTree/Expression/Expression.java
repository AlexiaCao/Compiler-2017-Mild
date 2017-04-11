package AbstractSyntaxTree.Expression;

import AbstractSyntaxTree.Node;
import AbstractSyntaxTree.Type.Type;

public abstract class Expression implements Node {
    public Type type;
    public boolean isLeftValue;

    protected Expression(Type type, boolean isLeftValue) {
        this.type = type;
        this.isLeftValue = isLeftValue;
    }
}
