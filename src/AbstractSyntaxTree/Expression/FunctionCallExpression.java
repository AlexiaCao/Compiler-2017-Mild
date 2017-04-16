package AbstractSyntaxTree.Expression;

import AbstractSyntaxTree.Expression.VariableExpression.FieldExpression;
import AbstractSyntaxTree.Function;
import AbstractSyntaxTree.Type.BasicType.VoidType;
import AbstractSyntaxTree.Type.ClassType.ClassType;
import AbstractSyntaxTree.Type.Type;
import Utility.Error.CompilationError;
import Utility.Error.InternalError;
import Utility.Utility;

import java.util.ArrayList;
import java.util.List;

public class FunctionCallExpression extends Expression {
    public Function function;
    public List<Expression> parameters;

    private FunctionCallExpression(Type type, boolean isLeftValue, Function function, List<Expression> parameters) {
        super(type, isLeftValue);
        this.function = function;
        this.parameters = parameters;
    }

    public static Expression getExpression(Function function, List<Expression> parameters) {
        return new FunctionCallExpression(function.type, false, function, parameters);
    }

    public static Expression getExpression(Expression expression, List<Expression> parameters) {
        if (expression instanceof NewExpression) {
            //	class constructor
            NewExpression newExpression = (NewExpression)expression;
            if (newExpression.type instanceof ClassType) {
                ClassType classType = (ClassType)newExpression.type;

                List<Type> parameterTypes = new ArrayList<Type>() {{
                    add(classType);
                    parameters.forEach(parameter -> add(parameter.type));
                }};

                if (!classType.constructors.containsKey(parameterTypes)) {
                    throw new CompilationError("the class \"" + classType.name + "\" has no matching constructor");
                }
                newExpression.constructor = classType.constructors.get(parameterTypes);
                newExpression.parameters = parameters;
                return newExpression;
            }
            throw new CompilationError("the " + newExpression.type.toString() + "-type has no constructor");
        }
        if (expression.type instanceof Function) {
            Function function = (Function)expression.type;
            if (expression instanceof FieldExpression) {
                //	member function call : add "this" to the parameter list
                parameters.add(0, ((FieldExpression)expression).expression);
            }
            if (parameters.size() != function.parameters.size()) {
                throw new CompilationError("the number of parameters in the function-call expression is wrong");
            }
            for (int i = 0; i < parameters.size(); ++i) {
                if (i == 0 && expression instanceof FieldExpression) {
                    //	no need to compare the type of "this"
                    continue;
                }
                if (!function.parameters.get(i).type.Compatible(parameters.get(i).type)) {
                    throw new CompilationError("the type of parameters in the function-call expression is wrong");
                }
            }
            return new FunctionCallExpression(function.type, false, function, parameters);
        }
        throw new CompilationError("a function is expected in the function-call expression");
    }

    @Override
    public String toString() {
        return "[expression: function-call, name = " + function.name + "]";
    }

    @Override
    public String toString(int indents) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Utility.getIndent(indents)).append(this).append("\n");
        parameters.forEach(parameter -> stringBuilder.append(parameter.toString(indents + 1)));
        return stringBuilder.toString();
    }
}
