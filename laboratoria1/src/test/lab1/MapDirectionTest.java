package lab1;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class MapDirectionTest {
    @Test
    public void next(){
        assertEquals(MapDirection.SOUTH.next(), MapDirection.WEST);
        assertEquals(MapDirection.NORTH.next(), MapDirection.EAST);
        assertEquals(MapDirection.WEST.next(), MapDirection.NORTH);
        assertEquals(MapDirection.EAST.next(), MapDirection.SOUTH);
    }
    @Test
    public void previous(){
        assertEquals(MapDirection.SOUTH.previous(), MapDirection.EAST);
        assertEquals(MapDirection.NORTH.previous(), MapDirection.WEST);
        assertEquals(MapDirection.WEST.previous(), MapDirection.SOUTH);
        assertEquals(MapDirection.EAST.previous(), MapDirection.NORTH);
    }
    @Test
    public void ToUnitVector(){
        assertEquals(new Vector2d(0,1), MapDirection.NORTH.toUnitVector());
        assertEquals(new Vector2d(0,-1), MapDirection.SOUTH.toUnitVector());
        assertEquals(new Vector2d(1,0), MapDirection.EAST.toUnitVector());
        assertEquals(new Vector2d(-1,0), MapDirection.WEST.toUnitVector());
    }
    @Test
    public void ToString(){
        assertEquals("Południe", MapDirection.SOUTH.toString());
        assertEquals("Zachód", MapDirection.WEST.toString());
        assertEquals("Północ", MapDirection.NORTH.toString());
        assertEquals("Wschód", MapDirection.EAST.toString());
    }
}
