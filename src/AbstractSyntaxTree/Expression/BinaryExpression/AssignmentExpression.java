package AbstractSyntaxTree.Expression.BinaryExpression;

import AbstractSyntaxTree.Expression.Expression;
import AbstractSyntaxTree.Type.Type;
import Utility.Error.CompilationError;

import java.util.List;

public class AssignmentExpression extends BinaryExpression {
	private AssignmentExpression(Type type, boolean isLeftValue, Expression left, Expression right) {
		super(type, isLeftValue, left, right);
	}

	public static Expression getExpression(Expression left, Expression right) {
		if (!left.isLeftValue) {
			throw new CompilationError("a left-value expression is expected in the left-hand-side of the assignment expression");
		}
		if (left.type.Compatible(right.type)) {
			return new AssignmentExpression(left.type, true, left, right);
		}
		throw new CompilationError("two expressions with compatible types are expected in the assignment expression");
	}

	@Override
	public String toString() {
		return "[expression: assignment]";
	}
}
