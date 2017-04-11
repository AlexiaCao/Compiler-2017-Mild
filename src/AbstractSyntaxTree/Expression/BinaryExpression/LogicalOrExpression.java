package AbstractSyntaxTree.Expression.BinaryExpression;

import AbstractSyntaxTree.Expression.ConstantExpression.BoolConstant;
import AbstractSyntaxTree.Expression.Expression;
import AbstractSyntaxTree.Type.BasicType.BoolType;
import AbstractSyntaxTree.Type.Type;
import Utility.Error.CompilationError;

import java.util.List;

public class LogicalOrExpression extends BinaryExpression {
	private LogicalOrExpression(Type type, boolean isLeftValue, Expression left, Expression right) {
		super(type, isLeftValue, left, right);
	}

	public static Expression getExpression(Expression left, Expression right) {
		if (left.type instanceof BoolType && right.type instanceof BoolType) {
			if (left instanceof BoolConstant && right instanceof BoolConstant) {
				boolean literal1 = ((BoolConstant)left).literal;
				boolean literal2 = ((BoolConstant)right).literal;
				return BoolConstant.getConstant(literal1 || literal2);
			}
			return new LogicalOrExpression(BoolType.getType(), false, left, right);
		}
		throw new CompilationError("two bool-type expressions are expected in the logical-or expression");
	}

	@Override
	public String toString() {
		return "[expression: logical-or]";
	}
}
