package blackbox.v2.usuario;

import blackbox.v2.framework.Text;

public class MiTexto implements Text {

    @Override
    public String text() {
        return "Hola desde la implementacion de la interfaz Text instanciada por el framework usando reflection";
    }
}
