package FrontEnd.AbstractSyntaxTree.Expression.BinaryExpression;

import BackEnd.ControlFlowGraph.Instruction.ArithmeticInstruction.BinaryInstruction.BitXorInstruction;
import BackEnd.ControlFlowGraph.Instruction.Instruction;
import FrontEnd.AbstractSyntaxTree.Expression.ConstantExpression.IntConstant;
import FrontEnd.AbstractSyntaxTree.Expression.Expression;
import FrontEnd.AbstractSyntaxTree.Type.BasicType.IntType;
import FrontEnd.AbstractSyntaxTree.Type.Type;
import Environment.Environment;
import Utility.Error.CompilationError;

import java.util.List;

public class BitXorExpression extends BinaryExpression {
	private BitXorExpression(Type type, boolean isLeftValue, Expression left, Expression right) {
		super(type, isLeftValue, left, right);
	}

	public static Expression getExpression(Expression left, Expression right) {
		if (left.type instanceof IntType && right.type instanceof IntType) {
			if (left instanceof IntConstant && right instanceof IntConstant) {
				int literal1 = ((IntConstant)left).literal;
				int literal2 = ((IntConstant)right).literal;
				return IntConstant.getConstant(literal1 ^ literal2);
			}
			return new BitXorExpression(IntType.getType(), false, left, right);
		}
		throw new CompilationError("two int-type expressions are expected in the bitwise-xor expression");
	}

	@Override
	public String toString() {
		return "[expression: bitwise-xor]";
	}

	@Override
	public void emit(List<Instruction> instructions) {
		left.emit(instructions);
		left.load(instructions);
		right.emit(instructions);
		right.load(instructions);
		operand = Environment.registerTable.addTemporaryRegister();
		instructions.add(BitXorInstruction.getInstruction(operand, left.operand, right.operand));
	}
}
