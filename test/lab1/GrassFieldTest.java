package lab1;

import org.junit.jupiter.api.Test;
import agh.cs.lab1.*;
import static org.junit.Assert.*;

public class GrassFieldTest {
    private final GrassField map;
    // testujemy grassField na pieskach

    public GrassFieldTest(){
        map = new GrassField(0);
        Animal[] dogs = new Animal[3];
        dogs[0] = new Animal(map, new Vector2d(2,1));
        dogs[1] = new Animal(map, new Vector2d(10, 5));
        dogs[2] = new Animal(map, new Vector2d(2, 3));
        map.place(dogs[0]);
        map.place(dogs[1]);
        map.place(dogs[2]);
    }
    @Test
    public void canMoveTo() {
        assertFalse(map.canMoveTo(new Vector2d(2,3)));
        assertTrue(map.canMoveTo(new Vector2d(9,3)));
    }


    @Test
    public void isOccupied() {
        assertTrue(map.isOccupied(new Vector2d(2,1)));
        assertFalse(map.isOccupied(new Vector2d(4,1)));

    }

    @Test
    public void objectAt() {
        assertNull(map.objectAt(new Vector2d(4,1)));
        assertTrue(map.objectAt(new Vector2d(2,3)) instanceof Animal);

    }
}

