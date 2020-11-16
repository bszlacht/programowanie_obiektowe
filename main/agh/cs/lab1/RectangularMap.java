package agh.cs.lab1;
import java.util.LinkedList;

public class RectangularMap extends AbstractWorldMap { // implementuje interfejs IWorldMap
    private final Vector2d maxUpperRight;   // czemu max?
    private final Vector2d maxLowerLeft;

    public RectangularMap(int width, int height){ // konstruktor
        this.maxUpperRight = new Vector2d(width - 1, height - 1);
        this.maxLowerLeft = new Vector2d(0,0);
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        // jesli sie miesci na mapie i pozycja jest pusta
        return super.canMoveTo(position) && position.follows(maxLowerLeft) && position.precedes(maxUpperRight);
    }

    @Override
    public String toString(){
        return super.toString(maxLowerLeft,maxUpperRight);
    }

    @Override
    public boolean place(Animal animal){
        return super.place(animal);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        return super.objectAt(position);
    }
}

