package AbstractSyntaxTree.Type;

import AbstractSyntaxTree.Type.BasicType.NullType;
import AbstractSyntaxTree.Type.BasicType.VoidType;
import Utility.Error.CompilationError;
import Utility.Error.InternalError;

public class ArrayType extends Type{
    public Type baseType;
    public int dimension;

    private ArrayType(Type baseType, int dimension){
        this.baseType = baseType;
        this.dimension = dimension;
    }

    public static Type getType(Type baseType, int dimension) {
        if (baseType instanceof VoidType) {
            throw new CompilationError("The void ArrayType is not allowed!");
        }
        if (dimension == 0) {
            throw new InternalError();
        }
        return new ArrayType(baseType, dimension);
    }

    public static Type getType(Type baseType) {
        if (baseType instanceof VoidType) {
            throw new CompilationError("The void ArrayType is not allowed!");
        }
        if (baseType instanceof ArrayType) {
            ArrayType arrayType = (ArrayType)baseType;
            return new ArrayType(arrayType.baseType, arrayType.dimension + 1);
        }
        else {
            return new ArrayType(baseType, 1);
        }
    }

    public Type reduce() {
        if (dimension == 1) {
            return baseType;
        }
        else {
            return ArrayType.getType(baseType, dimension - 1);
        }
    }

    @Override
    public boolean Compatible(Type other) {
        if (other instanceof NullType) {
            return true;
        }
        else if (other instanceof ArrayType) {
                ArrayType arrayType = (ArrayType)other;
                return arrayType.baseType.Compatible(baseType) && arrayType.dimension == dimension;
        }
        return false;
    }

    @Override
    public boolean Castable(Type other){
        return false;
    }

    @Override
    public String toString() {
        return "[array: " + baseType + ", " + dimension + "]";
    }

}
