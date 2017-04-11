package AbstractSyntaxTree.Type.ClassType.Member;

import AbstractSyntaxTree.Function;
import AbstractSyntaxTree.Type.ClassType.ClassType;

public class MemberFunction extends Member {
    public Function function;

    public MemberFunction(ClassType classType, String name, Function function) {
        super(name);
        this.function = function;
    }
}
