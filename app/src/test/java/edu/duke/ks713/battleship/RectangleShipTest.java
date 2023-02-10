package edu.duke.ks713.battleship;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Iterator;

class RectangleShipTest {
  @Test
  public void first_test(){
      Coordinate uLeft=new Coordinate(1,2);
      HashSet<Coordinate> expect=new HashSet<>();
      expect.add(new Coordinate(1,2));
      expect.add(new Coordinate(2,2));
      expect.add(new Coordinate(3,2));
      HashSet<Coordinate> real=RectangleShip.makeCoords(uLeft,1,3);
      Iterator<Coordinate> iter=real.iterator();
      Iterator<Coordinate> iter2= expect.iterator();
      while(iter.hasNext()&&iter2.hasNext()){
          Coordinate x1=iter.next();
          Coordinate x2=iter2.next();
          boolean b=x1.equals(x2);
          assertTrue(b);
      }
  }
    @Test
    public void second_test(){
     Coordinate start=new Coordinate(1,2);
     RectangleShip<Integer> r1=new RectangleShip<Integer>("testship",start,1,3,1,3);
     boolean b=r1.occupiesCoordinates(new Coordinate(1,2));
     boolean c=r1.occupiesCoordinates(new Coordinate(2,2));
     boolean d=r1.occupiesCoordinates(new Coordinate(3,2));
     String name=r1.getName();
     boolean e=name.equals("testship");
     assertTrue(b);
     assertTrue(c);
     assertTrue(d);
    }
    @Test
    public void third_test(){
      Coordinate uLeft=new Coordinate(1,2);
      RectangleShip<Character> rec=new RectangleShip<Character>(uLeft,'S','*');
      assertEquals("testship",rec.getName());
    }
    @Test
    public void fourth_test(){
        Coordinate start=new Coordinate(1,2);
        RectangleShip<Integer> r1=new RectangleShip<Integer>("testship",start,2,3,1,3);
        boolean b=r1.occupiesCoordinates(new Coordinate(1,3));
        boolean c=r1.occupiesCoordinates(new Coordinate(2,3));
        boolean d=r1.occupiesCoordinates(new Coordinate(3,3));
        assertTrue(b);
        assertTrue(c);
        assertTrue(d);
    }

}