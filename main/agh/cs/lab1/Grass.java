package agh.cs.lab1;

public class Grass implements IMapElement{
    private Vector2d position;  // to może być finalne
    public Grass(Vector2d initialPosition){
        this.position = initialPosition;
    }

    public String toString(){
        return "*";
    }

    public Vector2d getPosition() {
        return this.position;
    }
    static Grass randGrassInSquare(int from, int to){
        return new Grass(Vector2d.randInSquare(from, to));
    }
}
