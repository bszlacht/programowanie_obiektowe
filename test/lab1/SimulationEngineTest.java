package lab1;

import org.junit.jupiter.api.Test;
import agh.cs.lab1.*;
import static org.junit.Assert.*;


public class SimulationEngineTest {
    IWorldMap map = new RectangularMap(10, 5);

    @Test
    public void canMoveToTest(){
        Animal cat = new Animal(map, new Vector2d(2, 2));
        Animal dog = new Animal(map, new Vector2d(4, 4));
        MoveDirection[] catMoves={MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.BACKWARD};
        MoveDirection[] dogMoves={MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.LEFT};

        for (int i=0; i<3; i++) {
            cat.move(catMoves[i]);
            dog.move(dogMoves[i]);
        }
        assertEquals(new Vector2d(2, 3),cat.getPosition());
        assertEquals(new Vector2d(4, 2),dog.getPosition());
    }



    @Test
    public void placeTest(){
        Animal cat = new Animal(map,new Vector2d(3,3));
        Animal dog = new Animal(map,new Vector2d(1,2));
        map.place(cat);
        map.place(dog);
        assertTrue(map.isOccupied(new Vector2d(3, 3)));
        assertTrue(map.isOccupied(new Vector2d(1, 2)));
    }

    @Test
    public void isOccupiedTest(){
        assertFalse(map.isOccupied(new Vector2d(0, 0)));
        assertFalse(map.isOccupied(new Vector2d(10, 5))); //pozaMapa
        assertFalse(map.isOccupied(new Vector2d(4, 3)));
        assertFalse(map.isOccupied(new Vector2d(1, 5)));
    }

    @Test
    public void objectAtTest(){
        MoveDirection[] directions = new OptionsParser().parse(new String[] {"f","b","r","l","f","f","r","r","f","f","f","f","f","f","f","f"});
        IWorldMap map2 = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map2, positions);
        engine.run();
        assertTrue(map2.isOccupied(new Vector2d(2,0)));
        assertTrue(map2.isOccupied(new Vector2d(3,4)));
    }

    @Test
    public void runTestMapDir(){
        MoveDirection[] directions = new OptionsParser().parse(new String[] {"f","b","r","l","f","f","r","r","f","f","f","f","f","f","f","f"});
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        Object cat = map.objectAt(new Vector2d(2, 0));
        Object dog = map.objectAt(new Vector2d(3, 4));
        assertEquals(MapDirection.SOUTH.toStringShort(), cat.toString());
        assertEquals(MapDirection.NORTH.toStringShort(), dog.toString());
    }
}
