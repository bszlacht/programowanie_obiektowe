package main.classes;




import java.util.LinkedList;


public interface IWorldMap {
    boolean isOccupied(Vector2d position);
    int animalsAtCount(Vector2d position);
    Vector2d correctPosition(Vector2d requestedPosition);
    Vector2d getLowerLeft();
    Vector2d getUpperRight();
}