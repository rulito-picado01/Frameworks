package blackbox.v1.usuario;

import blackbox.v1.framework.Text;

public class MyText implements Text {

    @Override
    public String text() {
        return "Imprimiendo en Swing con el BB framework";
    }
}
