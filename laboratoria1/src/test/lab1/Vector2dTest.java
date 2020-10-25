package lab1;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class Vector2dTest {
    private Vector2d upperLeft = new Vector2d(-1, 1);
    private Vector2d upperRight = new Vector2d(1, 1);
    private Vector2d lowerRight = new Vector2d(1, -1);
    private Vector2d lowerLeft = new Vector2d(-1, -1);
    @Test
    public void Precedes(){
        assertTrue(lowerLeft.precedes(upperRight));
        assertTrue(upperLeft.precedes(upperRight));
        assertTrue(lowerRight.precedes(upperRight));
        assertTrue(upperRight.precedes(upperRight));
        assertFalse(upperRight.precedes(lowerLeft));
        assertFalse(upperRight.precedes(upperLeft));
        assertFalse(upperRight.precedes(lowerRight));
    }

    @Test
    public void Follows(){
        assertTrue(lowerRight.follows(lowerLeft));
        assertTrue(lowerRight.follows(lowerLeft));
        assertTrue(upperRight.follows(lowerLeft));
        assertTrue(upperRight.follows(upperRight));
        assertFalse(upperLeft.follows(upperRight));
        assertFalse(lowerLeft.follows(upperRight));
        assertFalse(lowerRight.follows(upperRight));
    }

    @Test
    public void LowerLeft(){
        assertEquals(this.lowerLeft, lowerRight.lowerLeft(upperLeft));
        assertEquals(this.lowerLeft, lowerRight.lowerLeft(lowerLeft));
        assertEquals(this.lowerLeft, upperLeft.lowerLeft(lowerLeft));
    }

    @Test
    public void UpperRight(){
        assertEquals(this.upperRight, lowerRight.upperRight(upperLeft));
        assertEquals(this.upperRight, upperRight.upperRight(upperLeft));
        assertEquals(this.upperRight, upperRight.upperRight(lowerRight));
    }

    @Test
    public void Add(){
        assertEquals(new Vector2d(0,0), lowerLeft.add(upperRight));
        assertEquals(new Vector2d(2,0), upperRight.add(lowerRight));
    }

    @Test
    public void Subtract(){
        assertEquals(new Vector2d(-2,-2), lowerLeft.subtract(upperRight));
        assertEquals(new Vector2d(0,2), upperRight.subtract(lowerRight));
    }

    @Test
    public void Opposite(){
        assertEquals(lowerLeft, upperRight.opposite());
        assertEquals(upperLeft, lowerRight.opposite());
    }

    @Test
    public void ToString() {
        assertEquals("(1,1)", upperRight.toString());
        assertEquals("(-1,1)", upperLeft.toString());
    }
}
