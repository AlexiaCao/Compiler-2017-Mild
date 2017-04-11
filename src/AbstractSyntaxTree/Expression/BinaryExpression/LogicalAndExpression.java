package AbstractSyntaxTree.Expression.BinaryExpression;

import AbstractSyntaxTree.Expression.ConstantExpression.BoolConstant;
import AbstractSyntaxTree.Expression.Expression;
import AbstractSyntaxTree.Type.BasicType.BoolType;
import AbstractSyntaxTree.Type.Type;
import Utility.Error.CompilationError;

import java.util.List;

public class LogicalAndExpression extends BinaryExpression {
	private LogicalAndExpression(Type type, boolean isLeftValue, Expression left, Expression right) {
		super(type, isLeftValue, left, right);
	}

	public static Expression getExpression(Expression left, Expression right) {
		if (left.type instanceof BoolType && right.type instanceof BoolType) {
			if (left instanceof BoolConstant && right instanceof BoolConstant) {
				boolean literal1 = ((BoolConstant)left).literal;
				boolean literal2 = ((BoolConstant)right).literal;
				return BoolConstant.getConstant(literal1 && literal2);
			}
			return new LogicalAndExpression(BoolType.getType(), false, left, right);
		}
		throw new CompilationError("two bool-type expressions are expected in the logical-and expression");
	}

	@Override
	public String toString() {
		return "[expression: logical-and]";
	}
}
