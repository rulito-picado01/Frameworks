package blackbox.v2.framework;

import javax.swing.*;
import java.io.InputStream;
import java.util.Properties;

public class Screen {
    private static final String CLASS_NAME_PROPERTY = "clase";
    private static final String CONFIG_FILE_NAME_DEFAULT = "/config.properties";
    Text textAImprimir;

    public Screen() {
        this(CONFIG_FILE_NAME_DEFAULT);
    }

    public Screen(String path) {
        Properties prop = new Properties();
        try (InputStream configFile = getClass().getResourceAsStream(path);) {
            prop.load(configFile);
            String clase = prop.getProperty(CLASS_NAME_PROPERTY);
            Class c = Class.forName(clase);
            this.textAImprimir = (Text) c.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(
                    "No pude crear la instancia de TextoAImprimir... ", e);
        }
    }

    public void print() {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, textAImprimir.text());
        frame.dispose();
    }
}
