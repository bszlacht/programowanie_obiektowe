package agh.cs.lab1;
import static java.lang.System.out;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

//
public class World {
    public static void main(String[] args) {
        try{
            MoveDirection[] directions = new OptionsParser().parse(args);
            GrassField map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
            System.out.print(map.toString());
        }catch (IllegalArgumentException | IllegalStateException | NullPointerException e){
            e.printStackTrace();
        }

    }

        // ZADANIE 10:
        // Moje pomysły to:
            // 1: talbica 2D która przechowuje nam czy jest jakies zwierze w danym miejscu, np. True jak jest i False jak nie ma;   (dobre dal małych talic ,ale dla dużej to będzie masakra)
            // 2: lista do przechowywania samych współrzędnych i liniowy skan kiedy wynokujemy animal.move();

}




