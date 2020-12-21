package classes.main.gui;

import classes.main.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class MapSimulationPanel extends JPanel implements ActionListener, KeyListener, MouseListener {
    public final GameParameters parameters;
    public Map map;
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 800;
    public final int MAP_WIDTH;
    public final int MAP_HEIGHT;
    public final int ELEMENT_WIDTH;
    public final int ELEMENT_HEIGHT;
    public final int ELEMENT_SIZE;
    public final int ELEMENTS;
    public final int DELAY;
    public boolean running = false;
    public Timer timer;
    Random random;
    public static final Color grassColor =  new Color(45, 100, 45);
    public static final Color animalColor = new Color(230, 25, 5);

    public int day = 0;

    public MapSimulationPanel(GameParameters parameters){
        this.parameters = parameters;
        this.map = new Map(parameters.width,parameters.height,parameters.jungleRatio,parameters.initialEnergy,parameters.grassEnergy,parameters.moveEnergy,parameters.startingAnimals);
        this.MAP_WIDTH = map.width;
        this.MAP_HEIGHT = map.height;
        this.ELEMENT_WIDTH = SCREEN_WIDTH/MAP_WIDTH;
        this.ELEMENT_HEIGHT = SCREEN_HEIGHT/MAP_HEIGHT;
        this.ELEMENT_SIZE = ELEMENT_HEIGHT*ELEMENT_WIDTH;
        this.ELEMENTS = MAP_WIDTH*MAP_HEIGHT;
        this.DELAY = parameters.delay;
        this.setPreferredSize(getPreferredSize());
        this.setFocusable(true);
        this.addKeyListener(this);
        this.setLayout(null);
        this.addMouseListener(this);

        this.startGame();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
    };

    public void startGame(){
        this.running = true;
        this.timer = new Timer(DELAY,this);
        this.timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(new Color(170, 224, 103));
        g2D.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        g2D.setColor(new Color(200, 160, 7));
        g2D.fillRect(map.jungleLoweLeft.getX() * ELEMENT_WIDTH,
                map.jungleLoweLeft.getY() * ELEMENT_HEIGHT,
                map.jungleWidth * ELEMENT_WIDTH,
                map.jungleHeight * ELEMENT_HEIGHT);
        for (int i = 0; i <= MAP_WIDTH; i++) {
            for (int j = 0; j <= MAP_HEIGHT; j++) {
                if (this.map.objectIn(new Vector2d(i, j))) {
                    g2D.setPaint(colorAtPosition(new Vector2d(i, j)));
                    g2D.fillRect(i * ELEMENT_WIDTH, j * ELEMENT_HEIGHT, ELEMENT_WIDTH, ELEMENT_HEIGHT);
                }
            }
        }
        if(!this.running){
            g2D.setColor(new Color(0,0,255));
            for (int i = 0; i <= MAP_WIDTH; i++) {
                for (int j = 0; j <= MAP_HEIGHT; j++) {
                    if (this.map.objectIn(new Vector2d(i, j))) {
                        Vector2d position = new Vector2d(i,j);
                        LinkedList<Animal> animalsList = this.map.AnimalsAt(position);
                        if(animalsList != null){
                            for(Animal animal : animalsList){
                                if(this.map.dominatingDNA().toString().equals(animal.getGenes().toString())){
                                    g2D.fillRect(i * ELEMENT_WIDTH, j * ELEMENT_HEIGHT, ELEMENT_WIDTH, ELEMENT_HEIGHT);
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    public void actionPerformed(ActionEvent e){
        this.map.newDay();
        repaint();
        day++;
        if(day>=this.parameters.days){
            this.timer.stop();
            this.running = false;
            try {
                this.map.savaAvarageInTxt();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private Color colorAtPosition(Vector2d position){
        Grass grass  = this.map.GrassAt(position);
        LinkedList<Animal> animals = this.map.AnimalsAt(position);
        if(animals != null){
            return this.animalColor;
        }
        return this.grassColor;
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == 83){
            this.timer.stop();
            this.running = false;
        }
        if(key == 65){
            this.timer.start();
            this.running = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX()/ELEMENT_WIDTH;
        int y = e.getY()/ELEMENT_HEIGHT;
        if(!this.running){
            Vector2d position = new Vector2d(x,y);
            if(this.map.animalsAtCount(position) > 0){
                Animal animal = this.map.getStrongestOne(this.map.AnimalsAt(position));
                this.repaint();
                new EndStatisticsFrame(this.map,animal);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
