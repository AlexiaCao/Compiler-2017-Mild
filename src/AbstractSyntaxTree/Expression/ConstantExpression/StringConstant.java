package AbstractSyntaxTree.Expression.ConstantExpression;


import Environment.Environment;
import AbstractSyntaxTree.Type.BasicType.StringType;

public class StringConstant extends Constant {
    public String literal;

    private StringConstant(String literal) {
        super(StringType.getType());
        this.literal = literal;
    }

    public static Constant getConstant(String literal) {
        return new StringConstant(literal);
    }

    @Override
    public String toString() {
        return "[constant: string, value = " + literal + "]";
    }
}
