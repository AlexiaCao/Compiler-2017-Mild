package AbstractSyntaxTree.Type.BasicType;

import AbstractSyntaxTree.Type.Type;

public class IntType extends Type {

    private static IntType instance = new IntType();

    public static Type getType() {
        return instance;
    }

    @Override
    public boolean Compatible (Type other){
        return other instanceof IntType;
    }

    @Override
    public boolean Castable (Type other) {
        return false;
    }

    @Override
    public String toString() {
        return "int";
    }

}