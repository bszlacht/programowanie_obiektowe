package classes.main.gui;

import classes.main.GameParameters;

import javax.swing.*;
import java.awt.*;


public class MapSimulationFrame extends JFrame{
    public final GameParameters parameters;
    public MapSimulationFrame(GameParameters parameters){
        this.parameters = parameters;
        this.setLayout(new GridLayout(1,2));
        MapSimulationPanel MsPanel = new MapSimulationPanel(this.parameters);
        this.add(MsPanel);
        StatisticsPanel StPanel = new StatisticsPanel(MsPanel.map,parameters.delay);
        this.add(StPanel);
        this.setTitle("Visualizer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setResizable(true);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }


}
