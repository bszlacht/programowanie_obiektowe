package lab1;

import org.junit.jupiter.api.Test;
import agh.cs.lab1.*;

import static org.junit.Assert.*;


public class AnimalTest{

    @Test
    public void moveTest1(){
        Animal cat = new Animal();
        cat.move(MoveDirection.RIGHT);
        cat.move(MoveDirection.FORWARD);
        cat.move(MoveDirection.FORWARD);
        cat.move(MoveDirection.FORWARD);
        Vector2d position = new Vector2d(4,2);
        assertEquals("pozycja: (4,2), orientacja: Wschód", cat.toString());
        // orientacja i pozycja się zgadza
    }

    @Test
    public void moveTest2(){
        Animal cat = new Animal();
        cat.move(MoveDirection.RIGHT);
        cat.move(MoveDirection.FORWARD);
        cat.move(MoveDirection.FORWARD);
        cat.move(MoveDirection.FORWARD);
        cat.move(MoveDirection.FORWARD);
        cat.move(MoveDirection.FORWARD);
        cat.move(MoveDirection.FORWARD);
        Vector2d position = new Vector2d(4,2);
        assertEquals("pozycja: (4,2), orientacja: Wschód", cat.toString());
        // nie wychodzi poza mapę
    }

    @Test
    public void moveTest3(){
        Animal cat = new Animal();
        cat.move(MoveDirection.LEFT);
        cat.move(MoveDirection.BACKWARD);
        cat.move(MoveDirection.LEFT);

        Vector2d position = new Vector2d(3,2);
        assertEquals("pozycja: (3,2), orientacja: Południe", cat.toString());
        // orientacja i pozycja się zgadza
    }

}
