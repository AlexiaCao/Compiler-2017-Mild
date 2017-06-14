package FrontEnd.AbstractSyntaxTree.Expression.UnaryExpression;

import BackEnd.ControlFlowGraph.Instruction.Instruction;
import BackEnd.ControlFlowGraph.Instruction.ArithmeticInstruction.UnaryInstruction.BitNotInstruction;
import Environment.Environment;
import FrontEnd.AbstractSyntaxTree.Expression.ConstantExpression.IntConstant;
import FrontEnd.AbstractSyntaxTree.Expression.Expression;
import FrontEnd.AbstractSyntaxTree.Type.BasicType.IntType;
import FrontEnd.AbstractSyntaxTree.Type.Type;
import Utility.Error.CompilationError;

import java.util.List;

public class BitNotExpression extends UnaryExpression {
	private BitNotExpression(Type type, boolean isLeftValue, Expression expression) {
		super(type, isLeftValue, expression);
	}

	public static Expression getExpression(Expression expression) {
		if (expression.type instanceof IntType) {
			if (expression instanceof IntConstant) {
				int literal = ((IntConstant)expression).literal;
				return IntConstant.getConstant(~literal);
			}
			return new BitNotExpression(IntType.getType(), false, expression);
		}
		throw new CompilationError("an int-type expression is expected in the bitwise-not expression");
	}

	@Override
	public String toString() {
		return "[expression: bitwise-not]";
	}

	@Override
	public void emit(List<Instruction> instructions) {
		expression.emit(instructions);
		expression.load(instructions);
		operand = Environment.registerTable.addTemporaryRegister();
		instructions.add(BitNotInstruction.getInstruction(operand, expression.operand));
	}
}
