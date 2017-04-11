package AbstractSyntaxTree.Expression.ConstantExpression;

import AbstractSyntaxTree.Type.BasicType.IntType;

public class IntConstant extends Constant {
    public int literal;

    private IntConstant(int literal) {
        super(IntType.getType());
        this.literal = literal;
    }

    public static Constant getConstant(int literal) {
        return new IntConstant(literal);
    }

    @Override
    public String toString() {
        return "[constant: int, value = " + literal + "]";
    }
}
