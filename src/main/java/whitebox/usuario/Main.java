package whitebox.usuario;

import whitebox.framework.Start;

public class Main {
    public static void main(String[] args) {
        Start s = new Start(new MyApplication());
        s.init();
    }
}