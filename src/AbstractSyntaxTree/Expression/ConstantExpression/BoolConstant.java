package AbstractSyntaxTree.Expression.ConstantExpression;

import AbstractSyntaxTree.Type.BasicType.BoolType;

import java.util.List;


public class BoolConstant extends Constant {
    public boolean literal;

    private BoolConstant(boolean literal) {
        super(BoolType.getType());
        this.literal = literal;
    }

    public static Constant getConstant(boolean literal) {
        return new BoolConstant(literal);
    }

    @Override
    public String toString() {
        return "[constant: bool, value = " + literal + "]";
    }
}
