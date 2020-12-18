package main.classes;

import java.util.Objects;
import java.util.Random;

public class Vector2d {
    final private int x;
    final private int y;

    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }
    public String toString(){
        return "(" + this.x + "," + this.y + ")";

    }
    public boolean precedes(Vector2d other){
        return this.x <= other.x && this.y <= other.y;
    }
    public boolean follows(Vector2d other){
        return this.x >= other.x && this.y >= other.y;
    }
    public Vector2d upperRight(Vector2d other){
        Vector2d res = new Vector2d(Math.max(this.x,other.x),Math.max(this.y,other.y));
        return res;
    }
    public Vector2d lowerLeft(Vector2d other){
        Vector2d res = new Vector2d(Math.min(this.x,other.x),Math.min(this.y,other.y));
        return res;
    }
    public Vector2d add(Vector2d other){ // TO NIE PODMIENIA TYLKO ZWRACA VEKTOR!!!! PAMIETAC O TYM NA PRZYSZLOSC!
        Vector2d res = new Vector2d(this.x + other.x,this.y + other.y);
        return res;
    }
    public Vector2d subtract(Vector2d other){
        Vector2d res = new Vector2d(this.x - other.x,this.y - other.y);
        return res;
    }

    public boolean equals(Object other){
        if(this == other) return true;
        return other instanceof Vector2d && this.x == ((Vector2d) other).x && this.y == ((Vector2d) other).y;
    }

    public Vector2d opposite(){
        return new Vector2d(-this.x, -this.y);
    }

    static public Vector2d randInRectangle(Vector2d lowerLeft, Vector2d upperRight){
        int xBoundaries = upperRight.getX() - lowerLeft.getX();
        int yBoundaries = upperRight.getY() - lowerLeft.getY();
        Random randX = new Random();
        Random randY = new Random();

        int rand_int1 = randX.nextInt(xBoundaries);
        int rand_int2 = randY.nextInt(yBoundaries);


        int x = lowerLeft.getX() + rand_int1;
        int y = lowerLeft.getY() + rand_int2;
        return new Vector2d(x, y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Vector2d wrapAround(int height, int width){
        return new Vector2d(Math.floorMod(this.x,width),Math.floorMod(this.y,height));
    }
    public Vector2d wrapAround(Map map){
        return this.wrapAround(map.getHeight(),map.getWidth());
    }

    public boolean inJungle(Map map, Vector2d position){
        Vector2d jungleLowerLeft = map.getJungleLoweLeft();
        Vector2d jungleUpperRight = map.getJungleUpperRight();
        if(position.precedes(jungleUpperRight) && position.follows(jungleLowerLeft)){
            return true;
        }
        return false;
    }



}

