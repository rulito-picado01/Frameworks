package ejercicioFrameworksFacundo.main;

import ejercicioFrameworksFacundo.framework.Framework;

public class Main {
    public static void main(String[] args) {
        String configFile = "acciones.txt";
        Framework fw = new Framework(configFile);
        fw.ejecutar();
    }
}
