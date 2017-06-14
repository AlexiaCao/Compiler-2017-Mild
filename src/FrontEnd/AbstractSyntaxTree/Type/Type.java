package FrontEnd.AbstractSyntaxTree.Type;

import FrontEnd.AbstractSyntaxTree.Node;
import Utility.Utility;

public abstract class Type implements Node {
    public int size() {
        return 8;
    }

    public abstract boolean Compatible (Type other);

    public abstract boolean Castable (Type other);

    @Override
    public String toString (int indents){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Utility.getIndent(indents)).append(toString()).append("\n");
        return stringBuilder.toString();
    }

}