package workflow.framework.nodos;

public abstract class AbstractNode implements Node {
    private String name;
    private String type;

    public AbstractNode(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String type() {
        return this.type;
    }
}
