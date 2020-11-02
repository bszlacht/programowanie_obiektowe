package agh.cs.lab1;

public class Animal {
    private MapDirection direction;
    private Vector2d position;

    public Animal(){
        direction = MapDirection.NORTH;
        position = new Vector2d(2,2);
    }

    public String toString(){
        return "pozycja: " + this.position + ", orientacja: " + this.direction;
    }

    public void move(MoveDirection direction) throws IllegalArgumentException{
        Vector2d tmp = new Vector2d(0,0);   // nazwa tmp nic nie mówi
        switch (direction) {
            case RIGHT -> this.direction = this.direction.next();
            case LEFT -> this.direction = this.direction.previous();
            case FORWARD -> tmp = tmp.add(this.direction.toUnitVector());   // czemu add, a nie po prostu przypisanie?
            case BACKWARD -> tmp = tmp.subtract(this.direction.toUnitVector());
        }
        Vector2d newPosition = this.position.add(tmp);
        System.out.println(tmp.toString()); // proszę usuwać takie rzeczy po debuggowaniu
        if(newPosition.correctPosition()) {
            this.position = newPosition;
        }

    }
}
