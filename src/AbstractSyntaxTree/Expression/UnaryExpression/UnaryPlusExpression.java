package AbstractSyntaxTree.Expression.UnaryExpression;

import AbstractSyntaxTree.Expression.ConstantExpression.IntConstant;
import AbstractSyntaxTree.Expression.Expression;
import AbstractSyntaxTree.Type.BasicType.IntType;
import AbstractSyntaxTree.Type.Type;
import Utility.Error.CompilationError;

import java.util.List;

public class UnaryPlusExpression extends UnaryExpression {
	private UnaryPlusExpression(Type type, boolean isLeftValue, Expression expression) {
		super(type, isLeftValue, expression);
	}

	public static Expression getExpression(Expression expression) {
		if (expression.type instanceof IntType) {
			if (expression instanceof IntConstant) {
				int literal = ((IntConstant)expression).literal;
				return IntConstant.getConstant(+literal);
			}
			return new UnaryPlusExpression(IntType.getType(), false, expression);
		}
		throw new CompilationError("an int-type expression is expected in the unary-plus expression");
	}

	@Override
	public String toString() {
		return "[expression: unary-plus]";
	}
}
