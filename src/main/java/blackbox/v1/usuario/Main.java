package blackbox.v1.usuario;


import blackbox.v1.framework.Start;

public class Main {
    public static void main(String args[]) {
        Start m = new Start(new MyText());
        m.init();
    }
}
