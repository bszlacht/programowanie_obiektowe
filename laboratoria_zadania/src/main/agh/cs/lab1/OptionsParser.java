package agh.cs.lab1;

import java.util.Arrays;
import java.util.LinkedList;

public class OptionsParser {
    public static MoveDirection[] parse(String[] input) throws IllegalArgumentException{
        LinkedList<MoveDirection> resList = new LinkedList<>();
        for(String dir: input){
            switch(dir){
                case "f":
                    case "forward": resList.add(MoveDirection.FORWARD); break;

                case "b":
                   case "backward": resList.add(MoveDirection.BACKWARD); break;

                case "r":
                   case "right": resList.add(MoveDirection.RIGHT); break;

                case "l":
                   case "left": resList.add(MoveDirection.LEFT); break;

                default:
                    //throw new IllegalArgumentException(dir + " is not a correct argument");
                    break; // <- doNothing
            }
        }
        MoveDirection[] res = new MoveDirection[resList.size()];
        resList.toArray(res);
        return res; // można return resList.toArray(new MoveDirection[0]);
    }
}