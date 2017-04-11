package AbstractSyntaxTree.Expression.UnaryExpression;

import AbstractSyntaxTree.Expression.Expression;
import AbstractSyntaxTree.Type.Type;
import Utility.Utility;

public abstract class UnaryExpression extends Expression {
	public Expression expression;

	protected UnaryExpression(Type type, boolean isLeftValue, Expression expression) {
		super(type, isLeftValue);
		this.expression = expression;
	}

	@Override
	public String toString(int indents) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Utility.getIndent(indents)).append(toString()).append("\n");
		stringBuilder.append(expression.toString(indents + 1));
		return stringBuilder.toString();
	}
}
