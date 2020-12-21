package classes.main.gui;

import classes.main.GameParameters;

import com.google.gson.Gson;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JButton;

public class SettingsPanel extends JPanel implements ActionListener{
    static final int SCREEN_WIDTH = 420;
    static final int SCREEN_HEIGHT = 720;
    static final int STRIPE_COUNT = 10; // 10.ty na przycisk
    static final int STRIPE_SIZE = SCREEN_HEIGHT/STRIPE_COUNT;
    JTextField width = new JTextField("width");
    JTextField height = new JTextField("height");
    JTextField jungleRatio = new JTextField("jungleRatio");
    JTextField initialEnergy = new JTextField("initialEnergy");
    JTextField moveEnergy = new JTextField("moveEnergy");
    JTextField grassEnergy = new JTextField("grassEnergy");
    JTextField startingAnimals = new JTextField("startingAnimals");
    JTextField days = new JTextField("days");
    JTextField delay = new JTextField("delay");


    JButton button = new JButton("Start Simulation");
    JButton button2 = new JButton("Read from JSON");
    public GameParameters parameters = new GameParameters(200,200,0.2,100,1,40,40,10000,1); // randomowe wpisalem

    public SettingsPanel() throws FileNotFoundException {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setFocusable(true);

        // textfields:
        width.setPreferredSize(new Dimension(SCREEN_WIDTH,STRIPE_SIZE));
        this.add(width);
        height.setPreferredSize(new Dimension(SCREEN_WIDTH,STRIPE_SIZE));
        this.add(height);
        jungleRatio.setPreferredSize(new Dimension(SCREEN_WIDTH,STRIPE_SIZE));
        this.add(jungleRatio);
        initialEnergy.setPreferredSize(new Dimension(SCREEN_WIDTH,STRIPE_SIZE));
        this.add(initialEnergy);
        moveEnergy.setPreferredSize(new Dimension(SCREEN_WIDTH,STRIPE_SIZE));
        this.add(moveEnergy);
        grassEnergy.setPreferredSize(new Dimension(SCREEN_WIDTH,STRIPE_SIZE));
        this.add(grassEnergy);
        startingAnimals.setPreferredSize(new Dimension(SCREEN_WIDTH,STRIPE_SIZE));
        this.add(startingAnimals);
        days.setPreferredSize(new Dimension(SCREEN_WIDTH,STRIPE_SIZE));
        this.add(days);
        delay.setPreferredSize(new Dimension(SCREEN_WIDTH,STRIPE_SIZE));
        this.add(delay);
        // starting button:
        this.button.setPreferredSize(new Dimension(SCREEN_WIDTH,STRIPE_SIZE));
        this.add(button);
        this.button2.setPreferredSize(new Dimension(SCREEN_WIDTH,STRIPE_SIZE));
        this.add(button2);

        this.button.addActionListener(this);
        this.button2.addActionListener(this);

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT + 150);
    };

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == button){
            this.parameters = new GameParameters(Integer.parseInt(width.getText()),
                    Integer.parseInt(height.getText()),
                    Double.parseDouble(jungleRatio.getText()),
                    Integer.parseInt(initialEnergy.getText()),
                    Integer.parseInt(moveEnergy.getText()),
                    Integer.parseInt(grassEnergy.getText()),
                    Integer.parseInt(startingAnimals.getText()),
                    Integer.parseInt(days.getText()),
                    Integer.parseInt(days.getText()));

            new MapSimulationFrame(this.parameters);
        }if(e.getSource() == button2){
            try {
                this.parameters.readFromJson();
                if(this.parameters.jungleRatio > 1){
                    throw new IllegalArgumentException("zly jungle ratio!! musi byc <= 1");
                }
                new MapSimulationFrame(this.parameters);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }
    }

}
