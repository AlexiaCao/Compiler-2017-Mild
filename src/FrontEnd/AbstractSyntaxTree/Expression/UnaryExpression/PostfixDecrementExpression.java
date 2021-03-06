package FrontEnd.AbstractSyntaxTree.Expression.UnaryExpression;

import BackEnd.ControlFlowGraph.Instruction.Instruction;
import BackEnd.ControlFlowGraph.Instruction.ArithmeticInstruction.BinaryInstruction.SubtractionInstruction;
import BackEnd.ControlFlowGraph.Instruction.MemoryInstruction.MoveInstruction;
import BackEnd.ControlFlowGraph.Instruction.MemoryInstruction.StoreInstruction;
import BackEnd.ControlFlowGraph.Operand.Address;
import BackEnd.ControlFlowGraph.Operand.ImmediateValue;
import Environment.Environment;
import FrontEnd.AbstractSyntaxTree.Expression.Expression;
import FrontEnd.AbstractSyntaxTree.Type.BasicType.IntType;
import FrontEnd.AbstractSyntaxTree.Type.Type;
import Utility.Error.CompilationError;

import java.util.List;

public class PostfixDecrementExpression extends UnaryExpression {
	private PostfixDecrementExpression(Type type, boolean isLeftValue, Expression expression) {
		super(type, isLeftValue, expression);
	}

	public static Expression getExpression(Expression expression) {
		if (!expression.isLeftValue) {
			throw new CompilationError("a left-value expression is expected in the postfix-decrement expression");
		}
		if (expression.type instanceof IntType) {
			return new PostfixDecrementExpression(IntType.getType(), false, expression);
		}
		throw new CompilationError("an int-type expression is expected in the postfix-decrement expression");
	}

	@Override
	public String toString() {
		return "[expression: postfix-decrement]";
	}

	@Override
	public void emit(List<Instruction> instructions) {
		expression.emit(instructions);
		operand = Environment.registerTable.addTemporaryRegister();
		if (expression.operand instanceof Address) {
			Address address = (Address)expression.operand;
			address = new Address(address.base, address.offset, address.size);
			expression.load(instructions);
			instructions.add(MoveInstruction.getInstruction(operand, expression.operand));
			instructions.add(SubtractionInstruction.getInstruction(expression.operand, expression.operand, new ImmediateValue(1)));
			instructions.add(StoreInstruction.getInstruction(expression.operand, address));
		} else {
			expression.load(instructions);
			instructions.add(MoveInstruction.getInstruction(operand, expression.operand));
			instructions.add(SubtractionInstruction.getInstruction(expression.operand, expression.operand, new ImmediateValue(1)));
		}
	}
}
