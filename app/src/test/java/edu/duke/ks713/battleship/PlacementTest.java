package edu.duke.ks713.battleship;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/**This method test the functions in Placement class*/
class PlacementTest {
 /**This method test the valid case of placement constructor and equals*/
@Test
public void test_constructor_valid(){
Placement placement=new Placement("A1V");
assertEquals(0,placement.getWhere().getRow());
assertEquals(1,placement.getWhere().getColumn());
assertEquals('V',placement.getOrientation());
System.out.println(placement.toString());
System.out.println(placement.hashCode());
System.out.println(placement.equals(new Placement("B2H")));
System.out.println(placement.equals(new Placement("A1V")));
System.out.println(placement.equals(new Placement("A1H")));
boolean c=placement.equals(new String("1"));
assertFalse(c);
}
/**This method test the invalid cases happen when construct the placement object*/
@Test
public void test_constructor_invalid(){
assertThrows(IllegalArgumentException.class,()->new Placement("XXX"));
assertThrows(IllegalArgumentException.class,()->new Placement("XXV"));
assertThrows(IllegalArgumentException.class,()->new Placement("A11"));
assertThrows(IllegalArgumentException.class,()->new Placement("A1VH"));
assertThrows(IllegalArgumentException.class,()->new Placement(""));
assertThrows(IllegalArgumentException.class,()->new Placement("                   "));
}
}