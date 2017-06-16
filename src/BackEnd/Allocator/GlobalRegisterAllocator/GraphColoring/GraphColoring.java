package BackEnd.Allocator.GlobalRegisterAllocator.GraphColoring;

import BackEnd.Allocator.GlobalRegisterAllocator.InterferenceGraph;
import BackEnd.Allocator.PhysicalRegister;
import BackEnd.ControlFlowGraph.Operand.VirtualRegister.VirtualRegister;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract class GraphColoring {
    protected InterferenceGraph graph;
    Map<VirtualRegister, PhysicalRegister> mapping;

    GraphColoring(InterferenceGraph graph) {
        this.graph = graph;
        this.mapping = new HashMap<>();
    }

    void color(VirtualRegister vertex) {
        Set<PhysicalRegister> used = new HashSet<PhysicalRegister>() {{
            for (VirtualRegister neighbor : graph.forbids.get(vertex)) {
                if (mapping.containsKey(neighbor)) {
                    add(mapping.get(neighbor));
                }
            }
        }};
        for (VirtualRegister neighbor : graph.recommends.get(vertex)) {
            if (mapping.containsKey(neighbor)) {
                PhysicalRegister color = mapping.get(neighbor);
                if (!mapping.containsKey(vertex) && !used.contains(color)) {
                    mapping.put(vertex, color);
                    break;
                }
            }
        }
        for (PhysicalRegister color : InterferenceGraph.colors) {
            if (!mapping.containsKey(vertex) && !used.contains(color)) {
                mapping.put(vertex, color);
                break;
            }
        }
    }
}
