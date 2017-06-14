package FrontEnd.AbstractSyntaxTree.Expression.BinaryExpression;

import BackEnd.ControlFlowGraph.Instruction.Instruction;
import BackEnd.ControlFlowGraph.Instruction.ArithmeticInstruction.BinaryInstruction.BitRightShiftInstruction;
import Environment.Environment;
import FrontEnd.AbstractSyntaxTree.Expression.ConstantExpression.IntConstant;
import FrontEnd.AbstractSyntaxTree.Expression.Expression;
import FrontEnd.AbstractSyntaxTree.Type.BasicType.IntType;
import FrontEnd.AbstractSyntaxTree.Type.Type;
import Utility.Error.CompilationError;

import java.util.List;

public class BitRightShiftExpression extends BinaryExpression {
	private BitRightShiftExpression(Type type, boolean isLeftValue, Expression left, Expression right) {
		super(type, isLeftValue, left, right);
	}

	public static Expression getExpression(Expression left, Expression right) {
		if (left.type instanceof IntType && right.type instanceof IntType) {
			if (left instanceof IntConstant && right instanceof IntConstant) {
				int literal1 = ((IntConstant)left).literal;
				int literal2 = ((IntConstant)right).literal;
				return IntConstant.getConstant(literal1 >> literal2);
			}
			return new BitRightShiftExpression(IntType.getType(), false, left, right);
		}
		throw new CompilationError("two int-type expressions are expected in the bitwise-right-shift expression");
	}

	@Override
	public String toString() {
		return "[expression: bitwise-right-shift]";
	}

	@Override
	public void emit(List<Instruction> instructions) {
		left.emit(instructions);
		left.load(instructions);
		right.emit(instructions);
		right.load(instructions);
		operand = Environment.registerTable.addTemporaryRegister();
		instructions.add(BitRightShiftInstruction.getInstruction(operand, left.operand, right.operand));
	}
}
