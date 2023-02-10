package edu.duke.ks713.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
/**This class test the main functions in battleship board*/
public class BattleShipBoardTest {
    /**THis method show what is at board
     * @param b  the board
     * @param expect expect character distributions*/
    private void checkWhatIsAtBoard(Board<Character> b, char[][] expect){
        for(int i=0;i<expect.length;i++){
            for(int j=0;j<expect.length;j++){
                Coordinate cor=new Coordinate(i,j);
                if(b.whatIsAtForSelf(cor)!=null){
                    expect[i][j]=b.whatIsAtForSelf(cor);
                }
            }
        }
    }
    /**
     * test battleship board constructor
     */
    @Test
    public void test_width_and_height() {
        Board<Character> b1 = new BattleShipBoard<Character>(10, 20,'X');
        assertEquals(10, b1.getWidth());
        assertEquals(20, b1.getHeight());
    }
    /**
     * test battleship board constructor for the handling of incorrect dimensions
     */
    @Test
    public void test_invalid_dimensions() {
        assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(0, 1,'X'));
        assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(1, 0,'X'));
        assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(1, -5,'X'));
        assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(-5, 1,'X'));
    }
    /**Test the process of placing ships to the board*/
    @Test
    public void test_valid_place_ships(){
      Board<Character> battle=new BattleShipBoard<>(10,20,'X');
      Ship<Character> ship1=new RectangleShip<>("battleShip",new Coordinate(1,2),1,2,'S','*');
      Ship<Character> ship2=new RectangleShip<>("battleShip",new Coordinate(4,2),1,2,'S','*');
      Ship<Character> ship3=new RectangleShip<>("battleShip",new Coordinate(7,2),1,2,'S','*');
      assertNull(battle.tryAddShip(ship1));
      assertNull(battle.tryAddShip(ship2));
      assertNull(battle.tryAddShip(ship3));
      char info='S';
      char first_cor=battle.whatIsAt(new Coordinate(1,2),true);
      char second_cor=battle.whatIsAt(new Coordinate(2,2),true);
      char third_cor=battle.whatIsAt(new Coordinate(4,2),true);
      char fourth_cor=battle.whatIsAt(new Coordinate(5,2),true);
      char fifth_cor=battle.whatIsAt(new Coordinate(7,2),true);
      char sixth_cor=battle.whatIsAt(new Coordinate(8,2),true);
      assertEquals(info,first_cor);
      assertEquals(info,second_cor);
      assertEquals(info,third_cor);
      assertEquals(info,fourth_cor);
      assertEquals(info,fifth_cor);
      assertEquals(info,sixth_cor);
      assertNull(battle.whatIsAt(new Coordinate(1,2),false));
    }
    /**Test the handling of error cases when place ships to the board*/
    @Test
    public void test_invalid_place_ships(){
        Board<Character> battle=new BattleShipBoard<>(10,20,'X');
        Ship<Character> ship1=new RectangleShip<>("battleShip",new Coordinate(1,2),1,2,'S','*');
        Ship<Character> ship2=new RectangleShip<>("battleShip",new Coordinate(1,2),1,2,'S','*');
        Ship<Character> ship3=new RectangleShip<>("battleShip",new Coordinate(22,2),1,2,'S','*');
        Ship<Character> ship4=new RectangleShip<>("battleShip",new Coordinate(22,22),1,2,'S','*');
        Ship<Character> ship5=new RectangleShip<>("battleShip",new Coordinate(10,22),1,2,'S','*');
        assertNull(battle.tryAddShip(ship1));
        assertNotNull(battle.tryAddShip(ship2));
        System.out.println(battle.tryAddShip(ship2));
        assertNotNull(battle.tryAddShip(ship3));
        System.out.println(battle.tryAddShip(ship3));
        assertNotNull(battle.tryAddShip(ship4));
        System.out.println(battle.tryAddShip(ship4));
        assertNotNull(battle.tryAddShip(ship5));
        System.out.println(battle.tryAddShip(ship5));
    }
    /**Test fire at and all sunk function*/
    @Test
    public void test_fire_at_and_all_sunk(){
        Board<Character> battle=new BattleShipBoard<>(10,20,'X');
        Ship<Character> ship1=new RectangleShip<>("battleShip",new Coordinate(1,2),1,2,'S','*');
        Ship<Character> ship2=new RectangleShip<>("battleShip",new Coordinate(5,2),1,2,'S','*');
        //1,2,2,2,5,2,6,2
        battle.tryAddShip(ship1);
        battle.tryAddShip(ship2);
        Coordinate one=new Coordinate(1,2);
        Coordinate two=new Coordinate(2,2);
        Coordinate three=new Coordinate(5,2);
        Coordinate four=new Coordinate(6,2);
        Coordinate five=new Coordinate(9,2);
        String A=battle.fireAt(one);
        String B=battle.fireAt(two);
        String C=battle.fireAt(three);
        assertFalse(battle.allSunk());
        String D=battle.fireAt(four);
        String E=battle.fireAt(five);
        boolean a=A.contains("hit");
        boolean b=B.contains("hit");
        boolean c=C.contains("hit");
        boolean d=D.contains("hit");
        boolean e=E.contains("hit");
        boolean f=E.contains("miss");
        assertTrue(a);
        assertTrue(b);
        assertTrue(c);
        assertTrue(d);
        assertFalse(e);
        assertTrue(f);
        assertTrue(battle.allSunk());
    }
}

