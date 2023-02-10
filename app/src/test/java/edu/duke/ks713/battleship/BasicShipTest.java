package edu.duke.ks713.battleship;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**The tests in this file aims to test the functions in BasicShip class, but because this class is abstract,when initialize with its subclasses*/
class BasicShipTest {
@Test
public void test_coordinate_occupy(){
    Coordinate upperLeft=new Coordinate(1,1);
    Ship<Character> mship=new RectangleShip<>("battleShip",upperLeft,1,3,'S','*');
    boolean b= mship.occupiesCoordinates(upperLeft);
    boolean c=mship.occupiesCoordinates(new Coordinate(2,1));
    boolean d=mship.occupiesCoordinates(new Coordinate(3,1));
    boolean e=mship.occupiesCoordinates(new Coordinate(10,1));
    assertTrue(b);
    assertTrue(c);
    assertTrue(d);
    assertFalse(e);
}
@Test
public void test_sunk(){
    Coordinate upperLeft=new Coordinate(1,1);
    Ship<Character> mship=new RectangleShip<>("battleShip",upperLeft,1,3,'S','*');
    Coordinate one=new Coordinate(2,1);
    Coordinate two=new Coordinate(3,1);
    mship.recordHitAt(upperLeft);
    boolean h=mship.isSunk();
    mship.recordHitAt(one);
    mship.recordHitAt(two);
    boolean a=mship.isSunk();
    assertTrue(a);
    assertFalse(h);
}
@Test
public void test_record_hit_at(){
    Coordinate upperLeft=new Coordinate(1,1);
    Ship<Character> mship=new RectangleShip<>("battleShip",upperLeft,1,3,'S','*');
    Coordinate one=new Coordinate(2,1);
    Coordinate two=new Coordinate(3,1);
    mship.recordHitAt(upperLeft);
    mship.recordHitAt(one);
    mship.recordHitAt(two);
    boolean a=mship.wasHitAt(upperLeft);
    boolean b=mship.wasHitAt(one);
    boolean c=mship.wasHitAt(two);
    assertTrue(a);
    assertTrue(b);
    assertTrue(c);
    assertThrows(IllegalArgumentException.class,()->mship.recordHitAt(new Coordinate(10,1)));
    assertThrows(IllegalArgumentException.class,()->mship.recordHitAt(new Coordinate(20,1)));
}
@Test
public void test_was_hit_at(){
    Coordinate upperLeft=new Coordinate(1,1);
    Ship<Character> mship=new RectangleShip<>("battleShip",upperLeft,1,3,'S','*');
    Coordinate one=new Coordinate(2,1);
    Coordinate two=new Coordinate(3,1);
    boolean a=mship.wasHitAt(upperLeft);
    boolean b=mship.wasHitAt(one);
    boolean c=mship.wasHitAt(two);
    assertFalse(a);
    assertFalse(b);
    assertFalse(c);
    assertThrows(IllegalArgumentException.class,()->mship.wasHitAt(new Coordinate(10,1)));
    assertThrows(IllegalArgumentException.class,()->mship.wasHitAt(new Coordinate(20,1)));
}
@Test
public void test_get_display_info_at(){
    Coordinate upperLeft=new Coordinate(1,1);
    Ship<Character> mship=new RectangleShip<>("battleShip",upperLeft,1,3,'S','*');
    Coordinate one=new Coordinate(2,1);
    Coordinate two=new Coordinate(3,1);
    mship.recordHitAt(upperLeft);
    mship.recordHitAt(one);
    assertEquals('S',mship.getDisplayInfoAt(two,true));
    assertEquals('*',mship.getDisplayInfoAt(one,true));
    assertEquals('*',mship.getDisplayInfoAt(upperLeft,true));
    assertEquals(null,mship.getDisplayInfoAt(two,false));
    assertEquals('S',mship.getDisplayInfoAt(one,false));
    assertEquals('S',mship.getDisplayInfoAt(upperLeft,false));
    assertThrows(IllegalArgumentException.class,()->mship.getDisplayInfoAt(new Coordinate(10,1),false));
    assertThrows(IllegalArgumentException.class,()->mship.getDisplayInfoAt(new Coordinate(20,1),true));
}
@Test
public void test_get_coordinate(){
    Coordinate upperLeft=new Coordinate(1,1);
    Ship<Character> mship=new RectangleShip<>("battleShip",upperLeft,1,3,'S','*');
    Coordinate one=new Coordinate(2,1);
    Coordinate two=new Coordinate(3,1);
    Iterable<Coordinate> cors=mship.getCoordinates();
    Iterator<Coordinate> iter= cors.iterator();
    while(iter.hasNext()){
        Coordinate thisCor=iter.next();
        boolean a=thisCor.equals(upperLeft);
        boolean b=thisCor.equals(one);
        boolean c=thisCor.equals(two);
        boolean d=a||b||c;
        assertTrue(d);
    }
}
}