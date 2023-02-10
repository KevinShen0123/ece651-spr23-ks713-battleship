package edu.duke.ks713.battleship;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/**This class is about testing the SimpleShipDisplayInfo class*/
class SimpleShipDisplayInfoTest {
    /**THis method is about testing getInfo function*/
@Test
public void first_test(){
    SimpleShipDisplayInfo<Integer> sisdi=new SimpleShipDisplayInfo<>(1,2);
    int result=sisdi.getInfo(null,true);
    assertEquals(result,2);
}
}