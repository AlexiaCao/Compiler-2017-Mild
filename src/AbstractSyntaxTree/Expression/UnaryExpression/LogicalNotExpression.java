package AbstractSyntaxTree.Expression.UnaryExpression;

import AbstractSyntaxTree.Expression.ConstantExpression.BoolConstant;
import AbstractSyntaxTree.Expression.Expression;
import AbstractSyntaxTree.Type.BasicType.BoolType;
import AbstractSyntaxTree.Type.Type;
import Utility.Error.CompilationError;

import java.util.List;

public class LogicalNotExpression extends UnaryExpression {
	private LogicalNotExpression(Type type, boolean isLeftValue, Expression expression) {
		super(type, isLeftValue, expression);
	}

	public static Expression getExpression(Expression expression) {
		if (expression.type instanceof BoolType) {
			if (expression instanceof BoolConstant) {
				boolean literal = ((BoolConstant)expression).literal;
				return BoolConstant.getConstant(!literal);
			}
			return new LogicalNotExpression(BoolType.getType(), false, expression);
		}
		throw new CompilationError("a bool-type expression is expected in the logical-not expression");
	}

	@Override
	public String toString() {
		return "[expression: logical-not]";
	}
}