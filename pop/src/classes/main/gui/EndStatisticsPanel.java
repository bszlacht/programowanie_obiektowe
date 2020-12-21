package classes.main.gui;

import classes.main.Animal;
import classes.main.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EndStatisticsPanel extends JPanel implements ActionListener {
    private Map map;
    public JTextField era;
    public JButton submit;
    public JLabel animalGenome;
    public JLabel animalKidsCount;
    public JLabel animalDescendantCount;
    public JLabel deathDay;
    Timer timer;
    static final int SCREEN_WIDTH = 550;
    static final int SCREEN_HEIGHT = 500;
    public Animal animal;

    private int eraNow;
    private int eraWanted;
    private int kidCountNow;
    private int kidCountAfter;
    private int descentCountNow;
    private int descentCountAfter;
    private int death;
    public EndStatisticsPanel(Map map, Animal animal){
        this.map = map;
        this.animal = animal;
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.era = new JTextField();
        era.setPreferredSize(new Dimension(550,45));
        this.add(era);
        this.submit = new JButton("Submit");
        submit.setPreferredSize(new Dimension(550,45));
        this.add(submit);
        this.submit.addActionListener(this);

        this.timer = new Timer(1,this);
        this.animalGenome = new JLabel();
        this.animalKidsCount = new JLabel();
        this.animalDescendantCount = new JLabel();
        this.deathDay = new JLabel();

        this.animalGenome.setText("DNA:" + "   " + "WAITING");
        this.animalKidsCount.setText("babies born until n:" + "   " + "WAITING");
        this.animalDescendantCount.setText("descent until n" + "   " + "WAITING");
        this.deathDay.setText("dead in:" + "   " + "WAITING");
        this.add(animalGenome);
        this.add(animalKidsCount);
        this.add(animalDescendantCount);
        this.add(deathDay);

        this.kidCountNow = this.animal.kidCount;
        this.kidCountAfter = this.animal.kidCount;
        this.descentCountNow = this.animal.getDescentCount();
        this.descentCountAfter = this.animal.getDescentCount();
        this.death = this.animal.getDeathDay();
        this.timer.start();
    }
    public Dimension getPreferredSize() {
        return new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.submit){
            this.eraWanted = Integer.parseInt(this.era.getText());
            this.eraNow = this.map.day;
            this.kidCountNow = this.animal.kidCount;
            this.descentCountNow = this.animal.getDescentCount();
            this.death = this.animal.getDeathDay();
        }
        if(this.eraWanted == this.map.day - this.eraNow){
            this.kidCountAfter = this.animal.kidCount;
            this.descentCountAfter = this.animal.getDescentCount();
            this.death = this.animal.getDeathDay();
            this.actualize();
        }
    }

    public void actualize(){
        this.animalGenome.setText("DNA:" + "   " + this.animal.getGenes());
        this.animalKidsCount.setText("babies born until n:" + "   " + (kidCountAfter - kidCountNow) + "///");
        this.animalDescendantCount.setText("descent until n" + "   "+ (descentCountAfter - descentCountNow)+"///");
        if(this.animal.getDeathDay() == -1){
            this.deathDay.setText("ALIVE!!!");
        }
        else {
            this.deathDay.setText("dead in:" + "   " + (this.animal.getDeathDay()));
        }
    }
}
