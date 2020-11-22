package agh.cs.lab1;
import java.util.LinkedList;
public class Animal implements IMapElement{
    //zmienne:
    private MapDirection direction;
    private Vector2d position;
    private final IWorldMap map;
    LinkedList<IPositionChangeObserver> observersList = new LinkedList<>();
    //konstruktor:

    public Animal(IWorldMap map){
        this(map,new Vector2d(2,2));
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.direction = MapDirection.NORTH;
        this.position = initialPosition;
        this.map = map;

    }

    public String toString(){
        return this.direction.toStringShort();
    }





    public void move(MoveDirection direction) throws IllegalArgumentException{
        Vector2d tworzacyPozycje = new Vector2d(0,0);
        switch (direction) {
            case RIGHT -> this.direction = this.direction.next();
            case LEFT -> this.direction = this.direction.previous();
            case FORWARD -> tworzacyPozycje = tworzacyPozycje.add(this.direction.toUnitVector());
            case BACKWARD -> tworzacyPozycje = tworzacyPozycje.subtract(this.direction.toUnitVector());
        }
        Vector2d newPosition = this.position.add(tworzacyPozycje);
        if(this.map.canMoveTo(newPosition)) {
            this.positionChanged(this.getPosition(),newPosition);
            this.position = newPosition;
        }

    }
    public Vector2d getPosition(){
        return this.position;
    }
    public MapDirection getDirection(){return this.direction;}

    public void addObserver(IPositionChangeObserver observer){
        this.observersList.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer) {
        this.observersList.remove(observer);
    }
    void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for(IPositionChangeObserver observer : this.observersList){
            observer.positionChanged(oldPosition, newPosition);
        }
    }
}
