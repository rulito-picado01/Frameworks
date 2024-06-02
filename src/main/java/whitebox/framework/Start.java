package whitebox.framework;

public class Start {

    private Screen s;

    public Start(Screen s) {
        this.s = s;
    }

    public void init() {
        this.s.print();
    }
}
