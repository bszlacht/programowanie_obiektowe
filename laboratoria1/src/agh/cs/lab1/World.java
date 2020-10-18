package agh.cs.lab1;
import static java.lang.System.out;



public class World {
    public static void main(String[] args) {
        System.out.print("start \n");

        Direction [] res = strToDir(args);

        run(res);


        System.out.println("stop \n");
    }
    public static void run(Direction[] args){
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
    public static Direction [] strToDir(String[] str){
        Direction [] arr;
        arr = new Direction[str.length];

        int counter = 0;
        for(int i = 0; i < str.length; i++)
        {
            switch(str[i]){
                case "f":
                    arr[counter] = Direction.FORWARD;
                    counter++;
                    break;
                case "b":
                    arr[counter] = Direction.BACKWARD;
                    counter++;
                    break;
                case "l":
                    arr[counter] = Direction.LEFT;
                    counter++;
                    break;
                case "r":
                    arr[counter] = Direction.RIGHT;
                    counter++;
                    break;
            }
        }
        return arr;
    }
}







