package FrontEnd.AbstractSyntaxTree.Type.ClassType.Member;

import FrontEnd.AbstractSyntaxTree.Function;
import FrontEnd.AbstractSyntaxTree.Type.ClassType.ClassType;

public class MemberFunction extends Member {
    public Function function;

    public MemberFunction(ClassType classType, String name, Function function) {
        super(name);
        this.function = function;
    }
}
