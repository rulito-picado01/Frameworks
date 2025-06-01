package workflow.framework;

import workflow.framework.nodos.Node;

public interface CodeNode extends Node {
    @Override
    default String type() {
        return "CUSTOM CODE NODE";
    }
}
