package blackbox.v1.framework;

public class Start {

    private Screen s;

    public Start(Text text) {
        this.s = new Screen(text);
    }

    public void init() {
        this.s.print();
    }
}
