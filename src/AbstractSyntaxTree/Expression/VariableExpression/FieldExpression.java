package AbstractSyntaxTree.Expression.VariableExpression;

import AbstractSyntaxTree.Expression.Expression;
import AbstractSyntaxTree.Type.ArrayType;
import AbstractSyntaxTree.Type.BasicType.StringType;
import AbstractSyntaxTree.Type.ClassType.ClassType;
import AbstractSyntaxTree.Type.ClassType.Member.Member;
import AbstractSyntaxTree.Type.ClassType.Member.MemberFunction;
import AbstractSyntaxTree.Type.ClassType.Member.MemberVariable;
import AbstractSyntaxTree.Type.Type;
import Environment.Environment;
import Utility.Utility;
import Utility.Error.CompilationError;

import java.util.List;

public class FieldExpression extends Expression {
	public Expression expression;
	public String field;

	private FieldExpression(Type type, boolean isLeftValue, Expression expression, String field) {
		super(type, isLeftValue);
		this.expression = expression;
		this.field = field;
	}

	public static Expression getExpression(Expression expression, String name) {
		if (expression.type instanceof ClassType) {
			ClassType classType = (ClassType)expression.type;
			Member member = classType.getMember(name);
			if (member instanceof MemberVariable) {
				return new FieldExpression(((MemberVariable)member).type, expression.isLeftValue, expression, name);
			} else if (member instanceof MemberFunction) {
				return new FieldExpression(((MemberFunction)member).function, expression.isLeftValue, expression, name);
			}
			throw new InternalError();
		} else if (expression.type instanceof ArrayType) {
			if (name.equals("size")) {
				return new FieldExpression(
						Environment.symbolTable.get("____builtin_array____size").type,
						expression.isLeftValue, expression, name
				);
			}
		} else if (expression.type instanceof StringType) {
			if (name.equals("length")) {
				return new FieldExpression(
						Environment.symbolTable.get("____builtin_string____length").type,
						expression.isLeftValue, expression, name
				);
			} else if (name.equals("ord")) {
				return new FieldExpression(
						Environment.symbolTable.get("____builtin_string____ord").type,
						expression.isLeftValue, expression, name
				);
			} else if (name.equals("substring")) {
				return new FieldExpression(
						Environment.symbolTable.get("____builtin_string____substring").type,
						expression.isLeftValue, expression, name
				);
			} else if (name.equals("parseInt")) {
				return new FieldExpression(
						Environment.symbolTable.get("____builtin_string____parse_int").type,
						expression.isLeftValue, expression, name
				);
			}
		}
		throw new CompilationError("a class/string/array-type expression is expected in the left-hand-side of the field expression");
	}

	@Override
	public String toString() {
		return "[expression: field]";
	}

	@Override
	public String toString(int indents) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Utility.getIndent(indents)).append(toString()).append("\n");
		stringBuilder.append(expression.toString(indents + 1));
		stringBuilder.append(Utility.getIndent(indents + 1)).append("[field: name = ").append(field).append(", type = ").append(type.toString()).append("]").append("\n");
		return stringBuilder.toString();
	}
}
