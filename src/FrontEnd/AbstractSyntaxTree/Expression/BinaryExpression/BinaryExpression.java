package FrontEnd.AbstractSyntaxTree.Expression.BinaryExpression;

import FrontEnd.AbstractSyntaxTree.Expression.Expression;
import FrontEnd.AbstractSyntaxTree.Type.Type;
import Utility.Utility;


public abstract class BinaryExpression extends Expression {
	public Expression left, right;

	protected BinaryExpression(Type type, boolean isLeftValue, Expression left, Expression right) {
		super(type, isLeftValue);
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString(int indents) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Utility.getIndent(indents)).append(toString()).append("\n");
		stringBuilder.append(left.toString(indents + 1));
		stringBuilder.append(right.toString(indents + 1));
		return stringBuilder.toString();
	}
}
