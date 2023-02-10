package edu.duke.ks713.battleship;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class PlacementRuleCheckerTest {
    @Test
    public void inbound_collision_test() {
        Board<Character> battle = new BattleShipBoard<>(10, 20, 'X');
        Ship<Character> ship1 = new RectangleShip<>("battleShip", new Coordinate(1, 2), 1, 2, 'S', '*');
        Ship<Character> ship2 = new RectangleShip<>("battleShip", new Coordinate(1, 2), 1, 2, 'S', '*');
        Ship<Character> ship3 = new RectangleShip<>("battleShip", new Coordinate(20, 2), 1, 2, 'S', '*');
        Ship<Character> ship4 = new RectangleShip<>("battleShip", new Coordinate(1, 22), 1, 2, 'S', '*');
        Ship<Character> ship5 = new RectangleShip<>("battleShip", new Coordinate(-1, 2), 1, 2, 'S', '*');
        Ship<Character> ship6 = new RectangleShip<>("battleShip", new Coordinate(1, -2), 1, 2, 'S', '*');
        assertNull(battle.tryAddShip(ship1));
        assertNotNull(battle.tryAddShip(ship2));
        assertNotNull(battle.tryAddShip(ship3));
        assertNotNull(battle.tryAddShip(ship4));
        assertNotNull(battle.tryAddShip(ship5));
        assertNotNull(battle.tryAddShip(ship6));
        System.out.println(battle.tryAddShip(ship2));
        System.out.println(battle.tryAddShip(ship3));
        System.out.println(battle.tryAddShip(ship4));
        System.out.println(battle.tryAddShip(ship5));
        System.out.println(battle.tryAddShip(ship6));
    }
}