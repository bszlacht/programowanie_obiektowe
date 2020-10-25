package lab1;

import static java.lang.System.out;


public class World {
    public static void main(String[] args) {

    }
    public static void run(MoveDirection[] args){
        for(int i = 0; i < args.length; i++)
        {
            if(args[i] != null){
                switch(args[i]){
                    case FORWARD:
                        out.println("Do przodu");
                        break;
                    case BACKWARD:
                        out.println("Do tyłu");
                        break;
                    case LEFT:
                        out.println("W lewo");
                        break;
                    case RIGHT:
                        out.println("W prawo");
                        break;
                }
            }
        }
        System.out.print("\n");

    }
    public static MoveDirection[] strToDir(String[] str){
        MoveDirection[] arr;
        arr = new MoveDirection[str.length];

        int counter = 0;
        for(int i = 0; i < str.length; i++)
        {
            switch(str[i]){
                case "f":
                    arr[counter] = MoveDirection.FORWARD;
                    counter++;
                    break;
                case "b":
                    arr[counter] = MoveDirection.BACKWARD;
                    counter++;
                    break;
                case "l":
                    arr[counter] = MoveDirection.LEFT;
                    counter++;
                    break;
                case "r":
                    arr[counter] = MoveDirection.RIGHT;
                    counter++;
                    break;
            }
        }
        return arr;
    }
}

class Vector2d {
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
    public Vector2d add(Vector2d other){
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





