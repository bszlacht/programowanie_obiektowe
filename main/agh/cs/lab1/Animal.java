package agh.cs.lab1;

public class Animal {
    //zmienne:
    private MapDirection direction;
    private Vector2d position;
    private IWorldMap map;  // to może być finalne
    //konstruktor:

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.direction = MapDirection.NORTH;
        this.position = initialPosition;
        this.map = map;
    }
    //a drugi konstruktor?



    public String toString(){
        return this.direction.toStringShort();
    }





    public void move(MoveDirection direction) throws IllegalArgumentException{
        Vector2d tmp = new Vector2d(0,0);   // nazwa tmp nic nie mówi
        switch (direction) {
            case RIGHT -> this.direction = this.direction.next();
            case LEFT -> this.direction = this.direction.previous();
            case FORWARD -> tmp = tmp.add(this.direction.toUnitVector());
            case BACKWARD -> tmp = tmp.subtract(this.direction.toUnitVector());
        }
        Vector2d newPosition = this.position.add(tmp);
        if(this.map.canMoveTo(newPosition)) {
            this.position = newPosition;
        }

    }
    public Vector2d getPosition(){
        return this.position;
    }
}
