package AbstractSyntaxTree.Expression.BinaryExpression;

import AbstractSyntaxTree.Expression.ConstantExpression.IntConstant;
import AbstractSyntaxTree.Expression.Expression;
import AbstractSyntaxTree.Type.BasicType.IntType;
import AbstractSyntaxTree.Type.Type;
import Utility.Error.CompilationError;

import java.util.List;

public class BitwiseOrExpression extends BinaryExpression {
	private BitwiseOrExpression(Type type, boolean isLeftValue, Expression left, Expression right) {
		super(type, isLeftValue, left, right);
	}

	public static Expression getExpression(Expression left, Expression right) {
		if (left.type instanceof IntType && right.type instanceof IntType) {
			if (left instanceof IntConstant && right instanceof IntConstant) {
				int literal1 = ((IntConstant)left).literal;
				int literal2 = ((IntConstant)right).literal;
				return IntConstant.getConstant(literal1 | literal2);
			}
			return new BitwiseOrExpression(IntType.getType(), false, left, right);
		}
		throw new CompilationError("two int-type expressions are expected in the bitwise-or expression");
	}

	@Override
	public String toString() {
		return "[expression: bitwise-or]";
	}
}
