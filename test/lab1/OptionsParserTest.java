package lab1;

import org.junit.jupiter.api.Test;
import agh.cs.lab1.*;
import static org.junit.Assert.*;


public class OptionsParserTest {
    @Test
    public void parseTest(){
        MoveDirection[] input = new MoveDirection[]{MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD};
        MoveDirection[] output = OptionsParser.parse(new String[]{"progra","right","mowanie","f","forward","obiektowe","f"});
        assertArrayEquals(input,output);

        MoveDirection[] input2 = new MoveDirection[]{MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.FORWARD,MoveDirection.FORWARD,MoveDirection.FORWARD,MoveDirection.FORWARD,MoveDirection.FORWARD};
        MoveDirection[] output2 = OptionsParser.parse(new String[]{"progra","l","mowanie","b","left","obiektowe","f","f","f","f","f"});
        assertArrayEquals(input2,output2);
    }

    //dane wejściowe podane jako tablica łańcuchów znaków są poprawnie interpretowane

}
