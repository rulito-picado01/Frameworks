package blackbox.v2.framework;


public class Start {

    private Screen s;

    public Start() {
        this.s = new Screen();
    }

    public Start(String pathConfig) {
        this.s = new Screen(pathConfig);
    }

    public void init() {
        this.s.print();
    }
}
