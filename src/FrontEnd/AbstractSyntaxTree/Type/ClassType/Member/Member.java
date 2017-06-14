package FrontEnd.AbstractSyntaxTree.Type.ClassType.Member;

import Environment.Environment;
import FrontEnd.AbstractSyntaxTree.Type.ClassType.ClassType;

public abstract class Member {
    public String name;
    public ClassType origin;

    Member(String name) {
        this.name = name;
        this.origin = Environment.scopeTable.getClassScope();
    }
}
