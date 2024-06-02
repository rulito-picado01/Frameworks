package blackbox.v2.usuario;

import blackbox.v2.framework.Start;

public class Main {

    public static void main(String args[]) {
        Start m = new Start("/config.properties");
//        Start m = new Start();
        m.init();
    }
}
