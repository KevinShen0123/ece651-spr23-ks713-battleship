package edu.duke.ks713.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BoardTextViewTest {
    private void emptyTestHelper(int w, int h, String expectedHeader, String expectedBody,boolean ship_yes){
        Board<Character> b1 = new BattleShipBoard<Character>(w, h,'X');
        if(ship_yes){
            //Ship<Character> s=new BasicShip(new Coordinate(0,1));
            RectangleShip<Character> s=new RectangleShip<>("battleShip",new Coordinate(1,1),1,1,new SimpleShipDisplayInfo<Character>('s','*'),new SimpleShipDisplayInfo<Character>('*','s'));
            b1.tryAddShip(s);
        }
        BoardTextView view = new BoardTextView(b1);
        assertEquals(expectedHeader, view.makeHeader());
        String expected = expectedHeader + expectedBody+ expectedHeader;
        //assertEquals(expected, view.displayMyOwnBoard());
        assertEquals(expected, view.displayEnemyBoard());
    }
    /**
     * test the function of display boards in boardtextview in 2*2 dimension(width==height)
     */
    @Test
    public void test_display_empty_2by2() {
        Board<Character> b1 = new BattleShipBoard<Character>(2, 2,'X');
        BoardTextView view = new BoardTextView(b1);
        String expectedHeader= "  0|1\n";
        String expectedBody=
                        "A  |  A\n"+
                        "B  |  B\n";
        emptyTestHelper(2,2,expectedHeader,expectedBody,false);
    }
    /**
     * test the function of display boards for the handling of invalid input board size
     */
    @Test
    public void test_invalid_board_size() {
        Board<Character> wideBoard = new BattleShipBoard<Character>(11,20,'X');
        Board<Character> tallBoard = new BattleShipBoard<Character>(10,27,'X');
        //you should write two assertThrows here
        assertThrows(IllegalArgumentException.class, () -> new BoardTextView(wideBoard));
        assertThrows(IllegalArgumentException.class, () -> new BoardTextView(tallBoard));
    }
    /**
     * test the function of display boards in boardtextview in 3*2 dimension(width>height)
     */
    @Test
    public void test_display_empty_3by2(){
        Board<Character> b1 = new BattleShipBoard<Character>(3, 2,'X');
        BoardTextView view = new BoardTextView(b1);
        String expectedHeader= "  0|1|2\n";
        String expectedBody=
                        "A  | |  A\n"+
                        "B  | |  B\n";
        emptyTestHelper(3,2,expectedHeader,expectedBody,false);
    }
    /**
     * test the function of display boards in boardtextview in 3*5 dimension(width<height)
     */
    @Test
    public void test_display_empty_3by5(){
        Board<Character> b1 = new BattleShipBoard<Character>(3, 5,'X');
        BoardTextView view = new BoardTextView(b1);
        String expectedHeader= "  0|1|2\n";
        String expectedBody=
                        "A  | |  A\n"+
                        "B  | |  B\n"+
                        "C  | |  C\n"+
                        "D  | |  D\n"+
                        "E  | |  E\n";
        emptyTestHelper(3,5,expectedHeader,expectedBody,false);
    }
    @Test
    public void test_ship_display(){
       Board<Character> p=new BattleShipBoard<>(2,2,'X');
        String expectedHeader= "  0|1\n";
        String expectedBody=
                "A  |  A\n"+
                        "B  |  B\n";
        emptyTestHelper(2,2,expectedHeader,expectedBody,false);
    }
    @Test
    public void test_enemy_display(){
        Board<Character> p=new BattleShipBoard<>(2,2,'X');
        String expectedHeader= "  0|1\n";
        String expectedBody=
                "A  |  A\n"+
                        "B  |* B\n";
        emptyTestHelper(2,2,expectedHeader,expectedBody,true);
    }
    @Test
    public void test_display_together(){
        Board<Character> p=new BattleShipBoard<>(10,20,'X');
        BoardTextView view1=new BoardTextView(p);
        Board<Character> b1 = new BattleShipBoard<Character>(10, 20,'X');
        BoardTextView view2=new BoardTextView(b1);
        String disInfo=view1.displayMyBoardWithEnemyNextToIt(view2,"Your ocean","Enemy's ocean");
        System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");
        System.out.println(disInfo);
        System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
    }
}
