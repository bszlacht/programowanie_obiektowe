package main.classes;

import java.util.LinkedList;
import java.util.Random;

public class Animal implements IMapElement{
    private Vector2d position;
    private MapDirection direction; // {0,1,...,7}
    public long energy; // days till dead, energy = 0 -> died
    public final long startingEnergy;
    private final DNA dna;
    private final Map map;

    private final LinkedList<IPositionChangedObserver> observersList = new LinkedList<>();

    public Animal(Map map,Vector2d initialAnimalPosition, long energy, DNA dna){
        Random rand = new Random();
        this.direction = MapDirection.rand();
        this.position = initialAnimalPosition;
        this.map = map;
        this.startingEnergy = energy;
        this.energy = energy;
        this.dna = dna;
    }

    public DNA getGenes(){
        return this.dna;
    }

    public Vector2d getPosition(){
        return this.position;
    }

    public void addObserver(IPositionChangedObserver observer){
        this.observersList.add(observer);
    }
    public void removeObserver(IPositionChangedObserver observer) {
        this.observersList.remove(observer);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for(IPositionChangedObserver observer : this.observersList){
            observer.positionChanged(oldPosition, newPosition, this);
        }
    }
    public String toString(){
        return this.direction.toString();
    }




}
