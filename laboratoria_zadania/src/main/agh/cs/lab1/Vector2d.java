package agh.cs.lab1;

public class Vector2d {
    final public int x;
    final public int y;

    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }
    public String toString(){
        return "(" + this.x + "," + this.y + ")";

    }
    public boolean precedes(Vector2d other){
        if(this.x <= other.x && this.y <= other.y){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean follows(Vector2d other){
        if(this.x >= other.x && this.y >= other.y){
            return true;
        }
        else{
            return false;
        }
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

}

