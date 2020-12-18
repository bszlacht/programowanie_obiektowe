package main.classes;
import java.util.Random;

public enum MapDirection {
    NORTH(new Vector2d(0,1), "^"),
    NORTH_EAST(new Vector2d(1,1), "/"),
    EAST(new Vector2d(1,0), ">"),
    SOUTH_EAST(new Vector2d(1,-1), "\\"),
    SOUTH(new Vector2d(0, -1), "v"),
    SOUTH_WEST(new Vector2d(-1, -1), "/"),
    WEST(new Vector2d(-1, 0), "<"),
    NORTH_WEST(new Vector2d(-1, 1), ")");

    private final Vector2d unitVector;
    private final String symbol;

    MapDirection(Vector2d unitVector, String symbol){
        this.unitVector = unitVector;
        this.symbol = symbol;
    }

    public MapDirection rotateBy(int factor){
        return  MapDirection.values()[(this.ordinal() + factor) % 8];
    }

    public MapDirection next(){
        return this.rotateBy(1);
    }

    public MapDirection previous(){
        return this.rotateBy(7);
    }

    public Vector2d toUnitVector(){
        return this.unitVector;
    }

    public static MapDirection rand(){
        return MapDirection.values()[(new Random()).nextInt(8)];
    }

    @Override
    public String toString(){
        return this.symbol;
    }
}
