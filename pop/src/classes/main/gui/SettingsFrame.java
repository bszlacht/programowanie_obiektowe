package classes.main.gui;

import javax.swing.*;
import java.io.FileNotFoundException;

public class SettingsFrame extends JFrame {

    SettingsFrame() throws FileNotFoundException {
        this.add(new SettingsPanel());
        this.setTitle("Evolution Game Settings");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
