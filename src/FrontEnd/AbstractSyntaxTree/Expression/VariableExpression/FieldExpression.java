package FrontEnd.AbstractSyntaxTree.Expression.VariableExpression;

import BackEnd.ControlFlowGraph.Instruction.Instruction;
import BackEnd.ControlFlowGraph.Instruction.ArithmeticInstruction.BinaryInstruction.AdditionInstruction;
import BackEnd.ControlFlowGraph.Instruction.ArithmeticInstruction.BinaryInstruction.MultiplicationInstruction;
import BackEnd.ControlFlowGraph.Instruction.MemoryInstruction.LoadInstruction;
import BackEnd.ControlFlowGraph.Operand.VirtualRegister.VirtualRegister;
import BackEnd.ControlFlowGraph.Operand.Address;
import BackEnd.ControlFlowGraph.Operand.ImmediateValue;
import FrontEnd.AbstractSyntaxTree.Expression.Expression;
import FrontEnd.AbstractSyntaxTree.Type.ArrayType;
import FrontEnd.AbstractSyntaxTree.Type.BasicType.StringType;
import FrontEnd.AbstractSyntaxTree.Type.ClassType.ClassType;
import FrontEnd.AbstractSyntaxTree.Type.ClassType.Member.Member;
import FrontEnd.AbstractSyntaxTree.Type.ClassType.Member.MemberFunction;
import FrontEnd.AbstractSyntaxTree.Type.ClassType.Member.MemberVariable;
import FrontEnd.AbstractSyntaxTree.Type.Type;
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
						Environment.symbolTable.get("__builtin_getArraySize").type,
						expression.isLeftValue, expression, name
				);
			}
		} else if (expression.type instanceof StringType) {
			if (name.equals("length")) {
				return new FieldExpression(
						Environment.symbolTable.get("__builtin_getStringLength").type,
						expression.isLeftValue, expression, name
				);
			} else if (name.equals("ord")) {
				return new FieldExpression(
						Environment.symbolTable.get("__builtin_ord").type,
						expression.isLeftValue, expression, name
				);
			} else if (name.equals("substring")) {
				return new FieldExpression(
						Environment.symbolTable.get("__builtin_getSubstring").type,
						expression.isLeftValue, expression, name
				);
			} else if (name.equals("parseInt")) {
				return new FieldExpression(
						Environment.symbolTable.get("__builtin_parseInt").type,
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


	@Override
	public void emit(List<Instruction> instructions) {
		if (expression.type instanceof ClassType) {
			ClassType classType = (ClassType)expression.type;
			Member member = classType.getMember(field);
			if (member instanceof MemberVariable) {
				MemberVariable memberVariable = (MemberVariable)member;
				expression.emit(instructions);
				expression.load(instructions);
				VirtualRegister address = (VirtualRegister)expression.operand;
				ImmediateValue delta = new ImmediateValue(memberVariable.offset);
				operand = new Address(address, delta, memberVariable.type.size());
			}
		}
	}

	@Override
	public void load(List<Instruction> instructions) {
		if (operand instanceof Address) {
			Address address = (Address)operand;
			operand = Environment.registerTable.addTemporaryRegister();
			instructions.add(LoadInstruction.getInstruction(operand, address));
		}
	}
}
