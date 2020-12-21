package classes.main.gui;

import classes.main.Animal;
import classes.main.Map;

import javax.swing.*;

public class EndStatisticsFrame extends JFrame {
    private Map map;
    public EndStatisticsFrame(Map map, Animal animal){
        this.map = map;
        this.add(new EndStatisticsPanel(this.map,animal));
        this.setTitle("End Stats Panel");
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
