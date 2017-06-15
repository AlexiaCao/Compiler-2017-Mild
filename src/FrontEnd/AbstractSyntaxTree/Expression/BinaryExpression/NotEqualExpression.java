package FrontEnd.AbstractSyntaxTree.Expression.BinaryExpression;

import BackEnd.ControlFlowGraph.Instruction.Instruction;
import BackEnd.ControlFlowGraph.Instruction.ArithmeticInstruction.BinaryInstruction.NotEqualInstruction;
import Environment.Environment;
import FrontEnd.AbstractSyntaxTree.Expression.ConstantExpression.BoolConstant;
import FrontEnd.AbstractSyntaxTree.Expression.ConstantExpression.IntConstant;
import FrontEnd.AbstractSyntaxTree.Expression.ConstantExpression.NullConstant;
import FrontEnd.AbstractSyntaxTree.Expression.ConstantExpression.StringConstant;
import FrontEnd.AbstractSyntaxTree.Expression.Expression;
import FrontEnd.AbstractSyntaxTree.Expression.FunctionCallExpression;
import FrontEnd.AbstractSyntaxTree.Function;
import FrontEnd.AbstractSyntaxTree.Type.BasicType.BoolType;
import FrontEnd.AbstractSyntaxTree.Type.BasicType.StringType;
import FrontEnd.AbstractSyntaxTree.Type.Type;
import Environment.Environment;
import Utility.Error.CompilationError;

import java.util.ArrayList;
import java.util.List;

public class NotEqualExpression extends BinaryExpression {
	private NotEqualExpression(Type type, boolean isLeftValue, Expression left, Expression right) {
		super(type, isLeftValue, left, right);
	}

	public static Expression getExpression(Expression left, Expression right) {
		if (!left.type.Compatible(right.type)) {
			throw new CompilationError("two expressions with the same type are expected in the not-equal-to expression");
		}
		if (left instanceof NullConstant && right instanceof NullConstant) {
			return BoolConstant.getConstant(false);
		} else if (left instanceof BoolConstant && right instanceof BoolConstant) {
			boolean literal1 = ((BoolConstant)left).literal;
			boolean literal2 = ((BoolConstant)right).literal;
			return BoolConstant.getConstant(literal1 != literal2);
		} else if (left instanceof IntConstant && right instanceof IntConstant) {
			int literal1 = ((IntConstant)left).literal;
			int literal2 = ((IntConstant)right).literal;
			return BoolConstant.getConstant(literal1 != literal2);
		} else if (left instanceof StringConstant && right instanceof StringConstant) {
			String literal1 = ((StringConstant)left).literal;
			String literal2 = ((StringConstant)right).literal;
			return BoolConstant.getConstant(!literal1.equals(literal2));
		}
		if (left.type instanceof StringType && right.type instanceof StringType) {
			return FunctionCallExpression.getExpression(
					(Function)Environment.symbolTable.get("____builtin_string____not_equal_to").type,
					new ArrayList<Expression>() {{
						add(left);
						add(right);
					}}
			);
		}
		return new NotEqualExpression(BoolType.getType(), false, left, right);
	}

	@Override
	public String toString() {
		return "[expression: not-equal-to]";
	}

	@Override
	public void emit(List<Instruction> instructions) {
		left.emit(instructions);
		left.load(instructions);
		right.emit(instructions);
		right.load(instructions);
		operand = Environment.registerTable.addTemporaryRegister();
		instructions.add(NotEqualInstruction.getInstruction(operand, left.operand, right.operand));
	}
}
