package blackbox.v1.framework;

import javax.swing.*;

public final class Screen {

    private Text text;

    public Screen(Text text) {
        this.text = text;
    }

    public final void print() {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, text.text());
        frame.dispose();
    }
}
