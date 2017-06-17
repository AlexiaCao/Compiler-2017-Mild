package BackEnd.Allocator.RegisterAllocator;

import BackEnd.Allocator.PhysicalRegister;
import BackEnd.ControlFlowGraph.Operand.VirtualRegister.VariableRegister.TemporaryRegister;
import BackEnd.ControlFlowGraph.Operand.VirtualRegister.VirtualRegister;
import Utility.Error.InternalError;

import java.util.*;

public class ChaitinGraphColoring extends GraphColoring {
    private Set<VirtualRegister> vertices;
    private Map<VirtualRegister, Integer> degree;

    public ChaitinGraphColoring(InterferenceGraph graph) {
        super(graph);
        vertices = new HashSet<>();
        degree = new HashMap<>();
    }

    public Map<VirtualRegister, PhysicalRegister> analysis() throws Exception {
        for (VirtualRegister vertex : graph.vertices) {
            vertices.add(vertex);
            degree.put(vertex, graph.forbids.get(vertex).size());
        }
        Stack<VirtualRegister> stack = new Stack<>();
        while (stack.size() < graph.vertices.size()) {
            boolean modify = false;
            for (VirtualRegister vertex : vertices) {
                if (degree.get(vertex) < InterferenceGraph.colors.size()) {
                    stack.add(vertex);
                    remove(vertex);
                    modify = true;
                    break;
                }
            }
            if (!modify){
                int MAXdegree = -1;
                VirtualRegister register = null;
                for (VirtualRegister vertex : vertices) {
                    if (degree.get(vertex) > MAXdegree) {
                        MAXdegree = degree.get(vertex);
                        register = vertex;
                    }
                }
                if (register != null) {
                    stack.add(register);
                    remove(register);
                } else {
                    throw new InternalError("Internal Error!!");
                }
            }
        }
        while (!stack.empty()) {
            color(stack.pop());
        }

        Map<VirtualRegister, PhysicalRegister> old = mapping;
        mapping = new HashMap<>();
        for (VirtualRegister register : old.keySet()) {
            if (register instanceof TemporaryRegister) {
                mapping.put(register, old.get(register));
            }
        }

        return mapping;
    }

    private void remove(VirtualRegister vertex) {
        vertices.remove(vertex);
        for (VirtualRegister neighbor : graph.forbids.get(vertex)) {
            degree.put(neighbor, degree.get(neighbor) - 1);
        }
    }
}
