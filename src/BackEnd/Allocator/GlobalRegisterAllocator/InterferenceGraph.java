package BackEnd.Allocator.GlobalRegisterAllocator;

import BackEnd.Allocator.PhysicalRegister;
import BackEnd.ControlFlowGraph.Operand.VirtualRegister.VariableRegister.TemporaryRegister;
import BackEnd.ControlFlowGraph.Operand.VirtualRegister.VirtualRegister;
import BackEnd.Translator.NASM.NASMRegister;

import java.util.*;

public class InterferenceGraph {
    public static List<PhysicalRegister> colors = new ArrayList<PhysicalRegister>() {{
        add(NASMRegister.r12);
        add(NASMRegister.r13);
        add(NASMRegister.r14);
        add(NASMRegister.r15);
        add(NASMRegister.rbx);
        add(NASMRegister.rsi);
        add(NASMRegister.rdi);
        add(NASMRegister.r8);
        add(NASMRegister.r9);
    }};

    public Set<VirtualRegister> vertices;
    public Map<VirtualRegister, Set<VirtualRegister>> forbids;
    public Map<VirtualRegister, Set<VirtualRegister>> recommends;

    public InterferenceGraph() {
        vertices = new HashSet<>();
        forbids = new HashMap<>();
        recommends = new HashMap<>();
    }

    void add(VirtualRegister x) {
        vertices.add(x);
        forbids.put(x, new HashSet<>());
        recommends.put(x, new HashSet<>());
    }

    void forbid(VirtualRegister x, VirtualRegister y) {
        if (x == y) {
            return;
        }
        if (x instanceof TemporaryRegister && y instanceof TemporaryRegister) {
            forbids.get(x).add(y);
            forbids.get(y).add(x);
        }
    }

    void recommend(VirtualRegister x, VirtualRegister y) {
        if (x == y) {
            return;
        }
        if (x instanceof TemporaryRegister && y instanceof TemporaryRegister) {
            recommends.get(x).add(y);
            recommends.get(y).add(x);
        }
    }
}