package AbstractSyntaxTree.Expression.UnaryExpression;

import AbstractSyntaxTree.Expression.Expression;
import AbstractSyntaxTree.Type.BasicType.IntType;
import AbstractSyntaxTree.Type.Type;
import Utility.Error.CompilationError;

import java.util.List;

public class PostfixDecrementExpression extends UnaryExpression {
	private PostfixDecrementExpression(Type type, boolean isLeftValue, Expression expression) {
		super(type, isLeftValue, expression);
	}

	public static Expression getExpression(Expression expression) {
		if (!expression.isLeftValue) {
			throw new CompilationError("a left-value expression is expected in the postfix-decrement expression");
		}
		if (expression.type instanceof IntType) {
			return new PostfixDecrementExpression(IntType.getType(), false, expression);
		}
		throw new CompilationError("an int-type expression is expected in the postfix-decrement expression");
	}

	@Override
	public String toString() {
		return "[expression: postfix-decrement]";
	}
}
