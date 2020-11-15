package agh.cs.lab1;

public class Animal implements IMapElement{
    //zmienne:
    private MapDirection direction;
    private Vector2d position;
    private final IWorldMap map;
    //konstruktor:

    public Animal(IWorldMap map){
        this.map = map;
        this.position = new Vector2d(2,2);
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.direction = MapDirection.NORTH;
        this.position = initialPosition;
        this.map = map;
    }

    public String toString(){
        return this.direction.toStringShort();
    }

    public String animalTestToString(){
        return "pozycja: " + this.position +", orientacja: " + this.direction;
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
            this.position = newPosition;
        }

    }
    public Vector2d getPosition(){
        return this.position;
    }
}
