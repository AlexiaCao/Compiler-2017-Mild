package AbstractSyntaxTree.Type.BasicType;

import AbstractSyntaxTree.Type.Type;

public class StringType extends Type{

    private static StringType instance = new StringType();

    public static Type getType(){
        return instance;
    }

    @Override
    public boolean Compatible(Type other){
        return other instanceof StringType;
    }

    @Override
    public boolean Castable(Type other) {
        return false;
    }

    @Override
    public String toString() {
        return "string";
    }
}
