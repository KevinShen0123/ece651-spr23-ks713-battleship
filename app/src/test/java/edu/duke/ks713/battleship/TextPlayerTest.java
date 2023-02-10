package edu.duke.ks713.battleship;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.*;
/**This method is about testing the functions is text player test, however, the functions in apptest does all the work, so here
 * overlaps*/
class TextPlayerTest {
    /**This function tests the read placement function in TextPlayer class
     * @throws IOException because needed when calling methods*/
    @Test
    void test_read_placement() throws IOException {
        StringReader s=new StringReader("B2V\nC8H\na4v\n");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bytes, true);
        Board<Character> b = new BattleShipBoard<Character>(10, 20,'X');
        TextPlayer player = new TextPlayer("A", b, new BufferedReader(s), ps, new V1ShipFactory());
        String prompt = "Please enter a location for a ship:";
        Placement[] expected = new Placement[3];
        expected[0] = new Placement(new Coordinate(1, 2), 'V');
        expected[1] = new Placement(new Coordinate(2, 8), 'H');
        expected[2] = new Placement(new Coordinate(0, 4), 'V');
        for (int i = 0; i < expected.length; i++) {
            Placement p = player.readPlacement(prompt);
            String s1=bytes.toString().replaceAll("\r","");
            assertEquals(p, expected[i]); //did we get the right Placement back
            assertEquals(prompt+"\n" , s1); //should have printed prompt and newline
            bytes.reset(); //clear out bytes for next time around
        }
    }
}