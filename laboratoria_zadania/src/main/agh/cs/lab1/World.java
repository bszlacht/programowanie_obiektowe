package agh.cs.lab1;
import static java.lang.System.out;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

//
public class World {
    public static void main(String[] args) {

        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        System.out.print(map.toString());


        /*Animal cat = new Animal();
        // wywołanie testujące
        //cat.move(MoveDirection.RIGHT);
        //cat.move(MoveDirection.FORWARD);
        //cat.move(MoveDirection.FORWARD);
        //cat.move(MoveDirection.FORWARD);
        //System.out.print(cat.toString());
        //optionparse:


        MoveDirection[] moves = OptionsParser.parse(args);
        //System.out.print(Arrays.toString(moves));
        for(MoveDirection move: moves){
            switch(move){
                case FORWARD:
                    cat.move(MoveDirection.FORWARD);
                    break;
                case BACKWARD:
                    cat.move(MoveDirection.BACKWARD);
                    break;
                case RIGHT:
                    cat.move(MoveDirection.RIGHT);
                    break;
                case LEFT:
                    cat.move(MoveDirection.LEFT);
                default:
                    break;
            }
        }
        System.out.print(cat.toString());*/
    }

        // ZADANIE 10:
        // Moje pomysły to:
            // 1: talbica 2D która przechowuje nam czy jest jakies zwierze w danym miejscu, np. True jak jest i False jak nie ma;   (dobre dal małych talic ,ale dla dużej to będzie masakra)
            // 2: lista do przechowywania samych współrzędnych i liniowy skan kiedy wynokujemy animal.move();



//TODO: ODKOMENTOWAC TEST JESLI TRZEBA












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
    public static MoveDirection [] strToDir(String[] str){
        MoveDirection [] arr;
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




