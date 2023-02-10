package edu.duke.ks713.battleship;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.io.BufferedReader;
/**This class is responsible for testing the method in V1ShipFactory class*/
class V1ShipFactoryTest {
 /**This method is about testing the different make functions in V1ShipFactory class*/
@Test
public void first_test(){
    V1ShipFactory v1=new V1ShipFactory();
    assertEquals(v1.getClass().getName(),"edu.duke.ks713.battleship.V1ShipFactory");
    Placement placement=new Placement(new Coordinate(1,2),'V');
    Ship<Character> s=v1.makeSubmarine(placement);
    Ship<Character> b=v1.makeBattleship(placement);
    Ship<Character> c=v1.makeCarrier(placement);
    Ship<Character> d=v1.makeDestroyer(placement);
    assertEquals("Submarine",s.getName());
    assertTrue(s.occupiesCoordinates(new Coordinate(2,2)));
    assertEquals("Battleship",b.getName());
    assertTrue(b.occupiesCoordinates(new Coordinate(4,2)));
    assertEquals("Carrier",c.getName());
    assertTrue(c.occupiesCoordinates(new Coordinate(6,2)));
    assertEquals("Destroyer",d.getName());
    assertTrue(d.occupiesCoordinates(new Coordinate(3,2)));
}
/**This method is about testing make submarine methods*/
@Test
public void second_test(){
    V1ShipFactory v1=new V1ShipFactory();
    assertEquals(v1.getClass().getName(),"edu.duke.ks713.battleship.V1ShipFactory");
    Placement placement=new Placement(new Coordinate(1,2),'H');
    Ship<Character> s=v1.makeSubmarine(placement);
    assertTrue(s.occupiesCoordinates(new Coordinate(1,3)));
}
/**THis method is about testing the handling of invalid inputs*/
@Test
public void third_test(){
    V1ShipFactory v1=new V1ShipFactory();
    assertEquals(v1.getClass().getName(),"edu.duke.ks713.battleship.V1ShipFactory");
    Placement placement=new Placement(new Coordinate(1,2),'F');
    assertThrows(IllegalArgumentException.class,()->v1.makeSubmarine(placement));
}
}