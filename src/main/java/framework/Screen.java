package framework;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public abstract class Screen {
  public final void print() {
    JFrame frame = new JFrame();
    JOptionPane.showMessageDialog(frame, this.text());
    frame.dispose();
  }

  protected abstract String text();

}
