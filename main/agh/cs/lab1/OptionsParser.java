package agh.cs.lab1;

import java.util.Arrays;
import java.util.LinkedList;

public class OptionsParser {
    public static MoveDirection[] parse(String[] input) throws IllegalArgumentException{
        LinkedList<MoveDirection> resList = new LinkedList<>();
        for(String dir: input){
            switch (dir) {
                case "f", "forward" -> resList.add(MoveDirection.FORWARD);
                case "b", "backward" -> resList.add(MoveDirection.BACKWARD);
                case "r", "right" -> resList.add(MoveDirection.RIGHT);
                case "l", "left" -> resList.add(MoveDirection.LEFT);
                default -> throw new IllegalArgumentException(dir + " is not legal move specification");
            }
        }
        MoveDirection[] res = new MoveDirection[resList.size()];
        resList.toArray(res);
        return res;
    }
}