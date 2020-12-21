package classes.main.gui;

import classes.main.DNA;
import classes.main.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class StatisticsPanel extends JPanel implements ActionListener , KeyListener {
    private LinkedList<DNA>dominatingDNAList = new LinkedList<DNA>();
    public Map map;
    public int animalCount;
    public int grassCount;
    public DNA dominatingDNA;
    public int averageEnergy;
    public int averageLifeLength; // for dead
    public int averageKidsCount;
    //---
    static final int SCREEN_WIDTH = 100;
    static final int SCREEN_HEIGHT = 100;
    public boolean running = false;
    Timer timer;
    int delay;
    //--
    public JLabel animalCountLabel;
    public JLabel grassCountLabel;
    public JLabel dominatingDNALabel;
    public JLabel averageEnergyLabel;
    public JLabel averageLifeLengthLabel;
    public JLabel averageKidsCountLabel;
    public JLabel day;
    public JLabel stopTutorial;
    public JLabel startTutorial;
    //public JLabel end;
    //public JLabel end1;
    public StatisticsPanel(Map map, int delay){
        this.delay = delay;
        //---
        this.map = map;
        this.animalCount = 0;
        this.grassCount = 0;
        this.dominatingDNA = null;
        this.averageEnergy = this.map.initialEnergy;
        this.averageLifeLength = 0;
        this.averageKidsCount = 0;
        //---
        this.setPreferredSize(getPreferredSize());
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.animalCountLabel = new JLabel();
        animalCountLabel.setText("animalCount" + "  " + Integer.toString(this.animalCount));
        this.add(animalCountLabel);

        this.grassCountLabel = new JLabel();
        grassCountLabel.setText("grassCount"+ "   " + Integer.toString(this.grassCount));
        this.add(grassCountLabel);

        this.dominatingDNALabel = new JLabel();
        dominatingDNALabel.setText("dominatingDNA"+ "   "+ this.dominatingDNA);
        this.add(dominatingDNALabel);

        this.averageEnergyLabel = new JLabel();
        averageEnergyLabel.setText("averegeEnergy" + "   " + Integer.toString(this.averageEnergy));
        this.add(averageEnergyLabel);

        this.averageLifeLengthLabel = new JLabel();
        averageLifeLengthLabel.setText("averageLifeLength" + "   " + Integer.toString(this.averageLifeLength));
        this.add(averageLifeLengthLabel);

        this.averageKidsCountLabel = new JLabel();
        averageKidsCountLabel.setText("averegeKidsCount" + "   " + Integer.toString(this.averageKidsCount));
        this.add(averageKidsCountLabel);

        this.day = new JLabel();
        day.setText("day:" + "   " + Integer.toString(this.map.day));
        this.add(day);

        this.stopTutorial = new JLabel();
        stopTutorial.setText("Press s to stop!");
        stopTutorial.setFont(new Font("Serif", Font.PLAIN, 120));

        this.startTutorial = new JLabel();
        startTutorial.setText("Press a to start!");
        startTutorial.setFont(new Font("Serif", Font.PLAIN, 120));

        this.add(stopTutorial);
        this.add(startTutorial);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.startActualizing();
    }
    public Dimension getPreferredSize() {
        return new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
    };

    public void actualize(){
        this.animalCount = this.map.AnimalsCount();
        this.animalCountLabel.setText("animalCount" + "  " + Integer.toString(this.animalCount));
        this.grassCount = this.map.GrassCount();
        this.grassCountLabel.setText("grassCount"+ "   " + Integer.toString(this.grassCount));
        this.dominatingDNA = this.map.dominatingDNA();
        this.dominatingDNALabel.setText("dominatingDNA"+ "   "+ this.dominatingDNA);
        dominatingDNAList.push(this.map.dominatingDNA());
        this.averageEnergy = this.map.averageEnergy();
        this.averageEnergyLabel.setText("averegeEnergy" + "   " + Integer.toString(this.averageEnergy));
        this.averageLifeLength = this.map.averageLifeLength();
        this.averageLifeLengthLabel.setText("averageLifeLength" + "   " + Integer.toString(this.averageLifeLength));
        this.averageKidsCount = this.map.averageKidsCount();
        this.averageKidsCountLabel.setText("averegeKidsCount" + "   " + Integer.toString(this.averageKidsCount));
        this.day.setText("day:" + "   " + Integer.toString(this.map.day));

    }

    public void startActualizing(){
        this.running = true;
        this.timer = new Timer(this.delay,this);
        this.timer.start();
    }

    public void actionPerformed(ActionEvent e){
        this.actualize();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.print("eee");
        int key2 = e.getKeyCode();
        if(key2 == 83){
            this.timer.stop();
            this.running = false;
        }
        if(key2 == 65){
            this.timer.start();
            this.running = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
