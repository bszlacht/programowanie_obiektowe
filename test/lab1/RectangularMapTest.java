package lab1;
import org.junit.jupiter.api.Test;
import agh.cs.lab1.*;
import static org.junit.Assert.*;


import static org.junit.Assert.assertEquals;
public class RectangularMapTest {
    RectangularMap map;
    Animal cat;
    Animal dog;
    Animal mouse;

    public RectangularMapTest(){
        map = new RectangularMap(10,10);
        cat = new Animal(map, new Vector2d(2,2));
        dog = new Animal(map, new Vector2d(2, 4));
        mouse = new Animal(map, new Vector2d(8,4));
        map.place(cat);
        map.place(dog);
        map.place(mouse);
    }

    @Test
    public void canMoveTo() {
        assertFalse(map.canMoveTo(new Vector2d(11,4)));
        assertFalse(map.canMoveTo(new Vector2d(2,2)));
        assertTrue(map.canMoveTo(new Vector2d(0, 5)));
    }

    @Test
    public void place() {
        map.place(new Animal(map, new Vector2d(0,5)));
        assertTrue(map.isOccupied(new Vector2d(0,5)));
    }


    @Test
    public void isOccupied() {
        assertTrue(map.isOccupied(new Vector2d(2,2)));
        assertFalse(map.isOccupied(new Vector2d(4,1)));
        assertFalse(map.isOccupied(new Vector2d(-2,1)));
    }

    @Test
    public void objectAt() {
        assertEquals(cat, map.objectAt(new Vector2d(2,2)));
        assertNull(map.objectAt(new Vector2d(4,1)));
        assertNull(map.objectAt(new Vector2d(-1,1)));
    }
}