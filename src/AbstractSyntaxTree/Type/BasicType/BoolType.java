package AbstractSyntaxTree.Type.BasicType;

import AbstractSyntaxTree.Type.Type;

public class BoolType extends Type {
    private static BoolType instance = new BoolType();

    public static Type getType() {
        return instance;
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public boolean Compatible (Type other){
        return other instanceof BoolType;
    }

    @Override
    public boolean Castable (Type other) {
        return false;
    }

    @Override
    public String toString() {
        return "bool";
    }

}