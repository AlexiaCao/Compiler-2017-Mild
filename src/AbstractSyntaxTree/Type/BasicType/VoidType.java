package AbstractSyntaxTree.Type.BasicType;

import AbstractSyntaxTree.Type.Type;

public class VoidType extends Type{
    private static VoidType instance = new VoidType();
    public static Type getType(){
        return instance;
    }

    @Override
    public boolean Compatible(Type other){
        return false;
    }

    @Override
    public boolean Castable(Type other){
        return false;
    }

    @Override
    public String toString(){
        return "void";
    }
}
