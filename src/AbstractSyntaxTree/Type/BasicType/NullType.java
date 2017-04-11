package AbstractSyntaxTree.Type.BasicType;

import AbstractSyntaxTree.Type.ArrayType;
import AbstractSyntaxTree.Type.Type;

public class NullType extends Type{
    private static NullType instance = new NullType();

    public static Type getType(){
        return instance;
    }

    @Override
    public boolean Compatible(Type other){
        return other instanceof NullType || other instanceof ArrayType || other instanceof  StringType;
    }

    @Override
    public boolean Castable(Type other){
        return false;
    }

    @Override
    public String toString(){
        return "null";
    }


}
