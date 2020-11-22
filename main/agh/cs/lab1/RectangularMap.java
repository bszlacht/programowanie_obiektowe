package agh.cs.lab1;
import java.util.LinkedList;

public class RectangularMap extends AbstractWorldMap { // implementuje interfejs IWorldMap
    private final Vector2d upperRight;
    private final Vector2d lowerLeft;

    public RectangularMap(int width, int height){ // konstruktor
        this.upperRight = new Vector2d(width - 1, height - 1);
        this.lowerLeft = new Vector2d(0,0);
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        // jesli sie miesci na mapie i pozycja jest pusta
        return super.canMoveTo(position) && position.follows(this.lowerLeft) && position.precedes(this.upperRight);
    }

    public Vector2d getLowerLeft(){
        return this.lowerLeft;
    }
    public Vector2d getUpperRight(){
        return this.upperRight;
    }

}

