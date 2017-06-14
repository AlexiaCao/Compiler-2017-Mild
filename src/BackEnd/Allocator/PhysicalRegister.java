package BackEnd.Allocator;

public abstract class PhysicalRegister {
    public int identity;
    public String name;
    public boolean callPreserved;

    protected PhysicalRegister(int identity, String name, boolean callPreserved) {
        this.identity = identity;
        this.name = name;
        this.callPreserved = callPreserved;
    }


    @Override
    public String toString() {
        return name;
    }
}
