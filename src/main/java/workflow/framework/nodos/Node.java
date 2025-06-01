package workflow.framework.nodos;

import workflow.framework.Params;

public interface Node {
    String type();

    String name();

    void execute(Params params);
}
