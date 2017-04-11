package AbstractSyntaxTree.Type.ClassType.Member;

import Environment.Environment;
import AbstractSyntaxTree.Type.ClassType.ClassType;
import Utility.Error.InternalError;

public abstract class Member {
    public String name;
    public ClassType origin;

    Member(String name) {
        this.name = name;
        this.origin = Environment.scopeTable.getClassScope();
    }
}
