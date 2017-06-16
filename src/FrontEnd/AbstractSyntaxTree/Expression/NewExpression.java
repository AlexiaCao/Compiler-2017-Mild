package FrontEnd.AbstractSyntaxTree.Expression;

import BackEnd.ControlFlowGraph.Instruction.ArithmeticInstruction.BinaryInstruction.AdditionInstruction;
import BackEnd.ControlFlowGraph.Instruction.ArithmeticInstruction.BinaryInstruction.LessThanInstruction;
import BackEnd.ControlFlowGraph.Instruction.ArithmeticInstruction.BinaryInstruction.MultiplicationInstruction;
import BackEnd.ControlFlowGraph.Instruction.ControlFlowInstruction.BranchInstruction;
import BackEnd.ControlFlowGraph.Instruction.ControlFlowInstruction.JumpInstruction;
import BackEnd.ControlFlowGraph.Instruction.FunctionInstruction.CallInstruction;
import BackEnd.ControlFlowGraph.Instruction.Instruction;
import BackEnd.ControlFlowGraph.Instruction.LabelInstruction;
import BackEnd.ControlFlowGraph.Instruction.MemoryInstruction.AllocateInstruction;
import BackEnd.ControlFlowGraph.Instruction.MemoryInstruction.MoveInstruction;
import BackEnd.ControlFlowGraph.Instruction.MemoryInstruction.StoreInstruction;
import BackEnd.ControlFlowGraph.Operand.Address;
import BackEnd.ControlFlowGraph.Operand.ImmediateValue;
import BackEnd.ControlFlowGraph.Operand.Operand;
import BackEnd.ControlFlowGraph.Operand.VirtualRegister.VirtualRegister;
import Environment.Environment;
import FrontEnd.AbstractSyntaxTree.Function;
import FrontEnd.AbstractSyntaxTree.Type.ArrayType;
import FrontEnd.AbstractSyntaxTree.Type.BasicType.IntType;
import FrontEnd.AbstractSyntaxTree.Type.ClassType.ClassType;
import FrontEnd.AbstractSyntaxTree.Type.Type;
import Utility.Error.CompilationError;
import Utility.Utility;
import com.sun.media.jfxmedia.control.VideoRenderControl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class NewExpression extends Expression {
    public List<Expression> expressions;
    public Function constructor;
    public List<Expression> parameters;

    private NewExpression(Type type, boolean isLeftValue, List<Expression> expressions) {
        super(type, isLeftValue);
        this.expressions = expressions;
        this.constructor = null;
        this.parameters = new ArrayList<>();
    }

    public static NewExpression getExpression(Type type, List<Expression> dimensionExpressions) {
        if (dimensionExpressions.isEmpty()) {
            if (type instanceof ClassType) {
                return new NewExpression(type, false, dimensionExpressions);
            }
            throw new CompilationError("an array/class type is expected in the new expression");
        } else {
            Type arrayType = ArrayType.getType(type, dimensionExpressions.size());
            return new NewExpression(arrayType, false, dimensionExpressions);
        }
    }

    @Override
    public String toString() {
        return "[expression: new, type = " + type + "]";
    }

    @Override
    public String toString(int indents) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Utility.getIndent(indents)).append(this).append("\n");
        expressions.stream()
                .filter(expression -> expression != null)
                .forEach(expression -> stringBuilder.append(expression.toString(indents + 1)));
        if (constructor != null) {
            stringBuilder.append(Utility.getIndent(indents)).append("[constructor]").append("\n");
            parameters.forEach(expression -> stringBuilder.append(expression.toString(indents + 1)));
        }
        return stringBuilder.toString();
    }


    @Override
    public void emit(List<Instruction> instructions) {
        expressions.stream().filter(expression -> expression != null).forEach(expression -> {
            expression.emit(instructions);
            expression.load(instructions);
        });
        operand = Environment.registerTable.addTemporaryRegister();
        if (type instanceof ClassType) {
            ClassType classType = (ClassType)type;
            instructions.add(AllocateInstruction.getInstruction(operand, new ImmediateValue(classType.allocateSize)));
            //	handle the class members' initial values
            classType.memberVariables.forEach((name, member) -> {
                Address address = new Address((VirtualRegister)operand, new ImmediateValue(member.offset), member.type.size());
                if (member.expression != null) {
                    member.expression.emit(instructions);
                    member.expression.load(instructions);
                    instructions.add(StoreInstruction.getInstruction(member.expression.operand, address));
                }
            });
            if (constructor == null) {
                //	match the class constructor
                if (!classType.constructors.isEmpty()) {
                    List<Type> parameterTypes = new ArrayList<Type>() {{
                        add(classType);
                    }};
                    if (!classType.constructors.containsKey(parameterTypes)) {
                        throw new CompilationError("the class \"" + classType.name + "\" has no matching constructor");
                    }
                    constructor = classType.constructors.get(parameterTypes);
                }
            }
            if (constructor != null) {
                //	handle the class constructor
                List<Operand> operands = new ArrayList<Operand>() {{
                    add(operand);
                    parameters.forEach(parameter -> {
                        parameter.emit(instructions);
                        parameter.load(instructions);
                        add(parameter.operand);
                    });
                }};
                instructions.add(CallInstruction.getInstruction(null, constructor, operands));
            }
        } else if (type instanceof ArrayType) {
            ArrayType arrayType = (ArrayType)type;
            VirtualRegister size = Environment.registerTable.addTemporaryRegister();
            instructions.add(MultiplicationInstruction.getInstruction(size, expressions.get(0).operand, new ImmediateValue(arrayType.reduce().size())));
            instructions.add(AdditionInstruction.getInstruction(size, size, new ImmediateValue(IntType.getType().size())));
            instructions.add(AllocateInstruction.getInstruction(operand, size));
            instructions.add(StoreInstruction.getInstruction(expressions.get(0).operand, new Address((VirtualRegister)operand, IntType.getType().size())));
            instructions.add(AdditionInstruction.getInstruction(operand, operand, new ImmediateValue(IntType.getType().size())));
            if (expressions.size() == 1 && arrayType.baseType instanceof ClassType || expressions.size() > 1 && expressions.get(1) != null) {
                LabelInstruction conditionLabel = LabelInstruction.getInstruction("new_arrayType_condition");
                LabelInstruction bodyLabel = LabelInstruction.getInstruction("new_arrayType_body");
                LabelInstruction loop = LabelInstruction.getInstruction("new_arrayType_loop");
                LabelInstruction merge = LabelInstruction.getInstruction("new_arrayType_merge");

                Type reduce = ((ArrayType) type).reduce();
                List<Expression> reduceExpressions;
                if (expressions.size() <= 1) {
                  reduceExpressions = new ArrayList<>();
                }
                else {
                    reduceExpressions = expressions.subList(1, expressions.size());
                }
                NewExpression reduceExpression = NewExpression.getExpression(reduce, reduceExpressions);

                VirtualRegister i = Environment.registerTable.addTemporaryRegister();
                instructions.add(MoveInstruction.getInstruction(i, new ImmediateValue((0))));
                instructions.add(JumpInstruction.getInstruction(conditionLabel));

                instructions.add(conditionLabel);
                VirtualRegister tmp1 = Environment.registerTable.addTemporaryRegister();
                instructions.add(LessThanInstruction.getInstruction(tmp1, i, expressions.get(0).operand));
                instructions.add(BranchInstruction.getInstruction(tmp1, bodyLabel, merge));

                instructions.add(bodyLabel);
                reduceExpression.emit(instructions);
                VirtualRegister tmp2 = Environment.registerTable.addTemporaryRegister();
                instructions.add(MultiplicationInstruction.getInstruction(tmp2, i, new ImmediateValue(((ArrayType) type).baseType.size())));
                VirtualRegister tmp3 = Environment.registerTable.addTemporaryRegister();
                instructions.add(AdditionInstruction.getInstruction(tmp3, operand, tmp2));
                Address now = new Address(tmp3, ((ArrayType) type).baseType.size());
                instructions.add(StoreInstruction.getInstruction(reduceExpression.operand, now));
                instructions.add(JumpInstruction.getInstruction(loop));

                instructions.add(loop);
                instructions.add(AdditionInstruction.getInstruction(i, i, new ImmediateValue(1)));
                instructions.add(JumpInstruction.getInstruction(conditionLabel));
                instructions.add(JumpInstruction.getInstruction(merge));

                instructions.add(merge);
            }
        } else {
            throw new InternalError();
        }
    }
}
