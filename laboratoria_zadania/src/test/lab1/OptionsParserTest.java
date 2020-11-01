package lab1;

import org.junit.jupiter.api.Test;
import agh.cs.lab1.*;
import static org.junit.Assert.*;


public class OptionsParserTest {
    @Test
    public void parseTest1(){
        MoveDirection[] input = new MoveDirection[]{MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD};
        MoveDirection[] output = OptionsParser.parse(new String[]{"progra","right","mowanie","f","forward","obiektowe","f"});
        assertArrayEquals(input,output);
    }
    @Test
    public void parseTest2(){
        MoveDirection[] input = new MoveDirection[]{MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.FORWARD,MoveDirection.FORWARD,MoveDirection.FORWARD,MoveDirection.FORWARD,MoveDirection.FORWARD};
        MoveDirection[] output = OptionsParser.parse(new String[]{"progra","l","mowanie","b","left","obiektowe","f","f","f","f","f"});
        assertArrayEquals(input,output);
    }


    //dane wejściowe podane jako tablica łańcuchów znaków są poprawnie interpretowane

}
