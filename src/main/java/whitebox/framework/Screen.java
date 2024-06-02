package whitebox.framework;

import javax.swing.*;

public abstract class Screen {
    public final void print() {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, this.text());
        frame.dispose();
    }

    protected abstract String text();

}
