package AbstractSyntaxTree.Expression;

import Environment.Environment;
import AbstractSyntaxTree.Function;
import AbstractSyntaxTree.Type.ArrayType;
import AbstractSyntaxTree.Type.BasicType.IntType;
import AbstractSyntaxTree.Type.ClassType.ClassType;
import AbstractSyntaxTree.Type.Type;
import Utility.Error.CompilationError;
import Utility.Error.InternalError;
import Utility.Utility;

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

    public static Expression getExpression(Type type, List<Expression> dimensionExpressions) {
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
}
