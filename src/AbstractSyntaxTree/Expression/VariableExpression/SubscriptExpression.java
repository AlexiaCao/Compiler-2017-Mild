package AbstractSyntaxTree.Expression.VariableExpression;

import AbstractSyntaxTree.Expression.Expression;
import AbstractSyntaxTree.Type.ArrayType;
import AbstractSyntaxTree.Type.BasicType.IntType;
import AbstractSyntaxTree.Type.Type;
import Utility.Error.CompilationError;
import Utility.Utility;

import java.util.List;

public class SubscriptExpression extends Expression {
	public Expression expression, subscript;

	private SubscriptExpression(Type type, boolean isLeftValue, Expression expression, Expression subscript) {
		super(type, isLeftValue);
		this.expression = expression;
		this.subscript = subscript;
	}

	public static Expression getExpression(Expression expression, Expression subscript) {
		if (!(expression.type instanceof ArrayType)) {
			throw new CompilationError("an array-type expression is expected in the left-hand-side of the subscript expression");
		}
		if (!(subscript.type instanceof IntType)) {
			throw new CompilationError("an int-type expression is expected in the right-hand-side of the subscript expression");
		}
		ArrayType arrayType = (ArrayType)expression.type;
		return new SubscriptExpression(arrayType.reduce(), expression.isLeftValue, expression, subscript);
	}

	@Override
	public String toString() {
		return "[expression: subscript]";
	}

	@Override
	public String toString(int indents) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Utility.getIndent(indents)).append(this).append("\n");
		stringBuilder.append(expression.toString(indents + 1));
		stringBuilder.append(subscript.toString(indents + 1));
		return stringBuilder.toString();
	}
}
