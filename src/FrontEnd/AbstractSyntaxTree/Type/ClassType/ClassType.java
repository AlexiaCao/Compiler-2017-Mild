package FrontEnd.AbstractSyntaxTree.Type.ClassType;

import Environment.Environment;
import Environment.ScopeTable.Scope;
import FrontEnd.AbstractSyntaxTree.Function;
import FrontEnd.AbstractSyntaxTree.Type.BasicType.NullType;
import FrontEnd.AbstractSyntaxTree.Type.ClassType.Member.Member;
import FrontEnd.AbstractSyntaxTree.Type.ClassType.Member.MemberFunction;
import FrontEnd.AbstractSyntaxTree.Type.ClassType.Member.MemberVariable;
import FrontEnd.AbstractSyntaxTree.Type.Type;
import Utility.Error.CompilationError;
import Utility.Utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassType extends Type implements Scope {
    public String name;
    public int allocateSize;
    public Map<String, MemberVariable> memberVariables;
    public Map<String, MemberFunction> memberFunctions;
    public Map<List<Type>, Function> constructors;
    public Function destructor;

    private ClassType(String name) {
        this.name = name;
        this.allocateSize = 0;
        this.memberVariables = new HashMap<>();
        this.memberFunctions = new HashMap<>();
        this.constructors = new HashMap<>();
        this.destructor = null;
    }

    public static Type getType(String name) {
        return new ClassType(name);
    }

    public void addMember(String name, Type type) {
        if (contains(name)) {
            throw new CompilationError("the class \"" + this.name + "\" cannot have two members named \"" + name + "\"");
        }
        if (type instanceof Function) {
            Function function = (Function)type;
            function.name = this.name + "." + function.name;
            MemberFunction member = new MemberFunction(this, name, function);
            memberFunctions.put(name, member);
        } else {
            MemberVariable member = new MemberVariable(this, name, type);
            memberVariables.put(name, member);
        }
    }

    public void addConstructor(Function function) {
        List<Type> parameterTypes = function.getParameterTypes();
        if (constructors.containsKey(parameterTypes)) {
            throw new CompilationError("different class constructors should have parameter list with different types");
        }
        function.name = name + ".constructor." + constructors.size();
        constructors.put(parameterTypes, function);
    }

    public void addDestructor(Function function) {
        if (destructor != null) {
            throw new CompilationError("the class \"" + name + "\" should have only one destructor");
        }
        function.name = name + ".destructor";
        destructor = function;
    }

    public Member getMember(String name) {
        Member member = null;
        if (memberVariables.containsKey(name)) {
            member = memberVariables.get(name);
        }
        if (memberFunctions.containsKey(name)) {
            member = memberFunctions.get(name);
        }
        if (member != null) {
            return member;
        }
        throw new CompilationError("the class \"" + this.name + "\" has no member named \"" + name + "\"");
    }

    public boolean contains(String name) {
        return memberVariables.containsKey(name) || memberFunctions.containsKey(name);
    }

    public void extendsFrom(ClassType baseClass) {
        //	copy the member variables
        Map<String, MemberVariable> memberBackups = memberVariables;
        memberVariables = new HashMap<>();
        allocateSize = baseClass.allocateSize;
        for (String name : baseClass.memberVariables.keySet()) {
            MemberVariable member = baseClass.memberVariables.get(name);
            memberVariables.put(name, member);
        }
        for (String name : memberBackups.keySet()) {
            MemberVariable member = memberBackups.get(name);
            if (!memberVariables.containsKey(name)) {
                member = new MemberVariable(this, member.name, member.type);
                memberVariables.put(name, member);
            }
        }
        //	copy the member functions
        for (String name : baseClass.memberFunctions.keySet()) {
            MemberFunction member = baseClass.memberFunctions.get(name);
            if (!memberFunctions.containsKey(name)) {
                memberFunctions.put(name, member);
            }
        }
    }

    @Override
    public boolean Compatible(Type other) {
        return other instanceof NullType || other == this;
    }

    @Override
    public boolean Castable(Type other) {
        if (!(other instanceof ClassType)) {
            return false;
        }
        ClassType otherClass = (ClassType)other;
        return Environment.classTable.isBaseOf(this, otherClass) || Environment.classTable.isBaseOf(otherClass, this);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String toString(int indents) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Utility.getIndent(indents)).append("[class: ").append(this).append(", size = ").append(allocateSize).append("]").append("\n");
        memberVariables.forEach((name, member) -> stringBuilder.append(member.toStringTree(indents + 1)));
        return stringBuilder.toString();
    }
}