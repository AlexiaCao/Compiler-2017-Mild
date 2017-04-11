package AbstractSyntaxTree.Expression.ConstantExpression;

import AbstractSyntaxTree.Type.BasicType.NullType;

public class NullConstant extends Constant {
    private NullConstant() {
        super(NullType.getType());
    }

    public static Constant getConstant() {
        return new NullConstant();
    }

    @Override
    public String toString() {
        return "[constant: null]";
    }
}
