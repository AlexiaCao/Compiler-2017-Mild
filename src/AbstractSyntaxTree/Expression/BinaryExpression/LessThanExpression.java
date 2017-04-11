package AbstractSyntaxTree.Expression.BinaryExpression;

import AbstractSyntaxTree.Expression.ConstantExpression.BoolConstant;
import AbstractSyntaxTree.Expression.ConstantExpression.IntConstant;
import AbstractSyntaxTree.Expression.ConstantExpression.StringConstant;
import AbstractSyntaxTree.Expression.Expression;
import AbstractSyntaxTree.Expression.FunctionCallExpression;
import AbstractSyntaxTree.Function;
import AbstractSyntaxTree.Type.BasicType.BoolType;
import AbstractSyntaxTree.Type.BasicType.IntType;
import AbstractSyntaxTree.Type.BasicType.StringType;
import AbstractSyntaxTree.Type.Type;
import Environment.Environment;
import Utility.Error.CompilationError;

import java.util.ArrayList;
import java.util.List;

public class LessThanExpression extends BinaryExpression {
	private LessThanExpression(Type type, boolean isLeftValue, Expression left, Expression right) {
		super(type, isLeftValue, left, right);
	}

	public static Expression getExpression(Expression left, Expression right) {
		if (left.type instanceof IntType && right.type instanceof IntType) {
			if (left instanceof IntConstant && right instanceof IntConstant) {
				int literal1 = ((IntConstant)left).literal;
				int literal2 = ((IntConstant)right).literal;
				return BoolConstant.getConstant(literal1 < literal2);
			} else {
				return new LessThanExpression(BoolType.getType(), false, left, right);
			}
		} else if (left.type instanceof StringType && right.type instanceof StringType) {
			if (left instanceof StringConstant && right instanceof StringConstant) {
				String literal1 = ((StringConstant)left).literal;
				String literal2 = ((StringConstant)right).literal;
				return BoolConstant.getConstant(literal1.compareTo(literal2) < 0);
			}
			return FunctionCallExpression.getExpression(
					(Function)Environment.symbolTable.get("____builtin_string____less_than").type,
					new ArrayList<Expression>() {{
						add(left);
						add(right);
					}}
			);
		}
		throw new CompilationError("two int-type or string-type expressions are expected in the less-than expression");
	}

	@Override
	public String toString() {
		return "[expression: less-than]";
	}
}
