package agh.cs.lab1;
import java.util.LinkedList;

public class RectangularMap implements IWorldMap { // implementuje interfejs IWorldMap
    private final Vector2d maxUpperRight;
    private final Vector2d maxLowerLeft;
    private final LinkedList<Animal> animalsList = new LinkedList<>();

    public RectangularMap(int width, int height){ // konstruktor
        this.maxUpperRight = new Vector2d(width - 1, height - 1);
        this.maxLowerLeft = new Vector2d(0,0);
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        // jesli sie miesci na mapie i pozycja jest pusta
        return !this.isOccupied(position) && position.follows(this.maxLowerLeft) && position.precedes(this.maxUpperRight);
    }

    @Override
    public String toString(){
        MapVisualizer mapToPrint = new MapVisualizer(this);
        return mapToPrint.draw(maxLowerLeft,maxUpperRight);
    }

    @Override
    public boolean place(Animal animal){
        if(this.canMoveTo(animal.getPosition())){
            this.animalsList.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for(int i = 0; i < this.animalsList.size();i++){
            Animal test = animalsList.get(i);
            if(test.getPosition().equals(position)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        Animal res = null;
        for(int i = 0; i < this.animalsList.size();i++){
            Animal test = animalsList.get(i);
            if(test.getPosition().equals(position)){
                res = test;
            }
        }
        return res;
    }
}
