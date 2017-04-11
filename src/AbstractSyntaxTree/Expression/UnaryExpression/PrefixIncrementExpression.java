package AbstractSyntaxTree.Expression.UnaryExpression;

import AbstractSyntaxTree.Expression.Expression;
import AbstractSyntaxTree.Type.BasicType.IntType;
import AbstractSyntaxTree.Type.Type;
import Utility.Error.CompilationError;

import java.util.List;

public class PrefixIncrementExpression extends UnaryExpression {
	private PrefixIncrementExpression(Type type, boolean isLeftValue, Expression expression) {
		super(type, isLeftValue, expression);
	}

	public static Expression getExpression(Expression expression) {
		if (!expression.isLeftValue) {
			throw new CompilationError("a left-value expression is expected in the prefix-increment expression");
		}
		if (expression.type instanceof IntType) {
			return new PrefixIncrementExpression(IntType.getType(), false, expression);
		}
		throw new CompilationError("an int-type expression is expected in the prefix-increment expression");
	}

	@Override
	public String toString() {
		return "[expression: prefix-increment]";
	}
}
