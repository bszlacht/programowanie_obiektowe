package lab1;

import org.junit.jupiter.api.Test;
import agh.cs.lab1.*;

import static org.junit.Assert.*;


public class AnimalTest{

    @Test
    public void moveTest1(){
        IWorldMap map = new RectangularMap(5, 5);
        Vector2d initPos = new Vector2d(2,2);
        Animal cat = new Animal(map,initPos);
        cat.move(MoveDirection.RIGHT);
        cat.move(MoveDirection.FORWARD);
        cat.move(MoveDirection.FORWARD);
        cat.move(MoveDirection.FORWARD);;
        assertEquals(MapDirection.EAST,cat.getDirection());
        assertEquals(new Vector2d(4,2), cat.getPosition());

        Animal cat2 = new Animal(map,initPos);
        cat2.move(MoveDirection.RIGHT);
        cat2.move(MoveDirection.FORWARD);
        cat2.move(MoveDirection.FORWARD);
        cat2.move(MoveDirection.FORWARD);
        cat2.move(MoveDirection.FORWARD);
        cat2.move(MoveDirection.FORWARD);
        cat2.move(MoveDirection.FORWARD);
        assertEquals(MapDirection.EAST,cat2.getDirection());
        assertEquals(new Vector2d(4,2), cat2.getPosition());
        // nie wychodzi poza mapę

        Animal cat3 = new Animal(map,initPos);
        cat3.move(MoveDirection.LEFT);
        cat3.move(MoveDirection.BACKWARD);
        cat3.move(MoveDirection.LEFT);
        assertEquals(MapDirection.SOUTH,cat3.getDirection());
        assertEquals(new Vector2d(3,2), cat3.getPosition());
        // orientacja i pozycja się zgadza
    }



}
