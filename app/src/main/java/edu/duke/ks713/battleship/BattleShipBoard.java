package edu.duke.ks713.battleship;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
/**This class is the description of board*/
public class BattleShipBoard<T> implements Board<T> {
    private final int width;
    private final int height;
    final ArrayList<Ship<T>> myShips;
    private final PlacementRuleChecker<T> placementChecker;
    HashSet<Coordinate> enemyMisses;
    final T missInfo;
    /**
     * Constructs a BattleShipBoard with the specified width
     * and height and initialize all the corresponding parameters
     * @param new_width is the width of the newly constructed board.
     * @param new_height is the height of the newly constructed board.
     * @param missInfo the info to display when attacker miss an attack
     * @throws IllegalArgumentException if the width or height are less than or equal to zero.
     */
    public BattleShipBoard(int new_width, int new_height,T missInfo){
        if (new_width<= 0) {
            throw new IllegalArgumentException("BattleShipBoard's width must be positive but is " + new_width);
        }
        if (new_height<= 0) {
            throw new IllegalArgumentException("BattleShipBoard's height must be positive but is " + new_height);
        }
        this.width = new_width;
        this.height = new_height;
        this.myShips=new ArrayList<>();
        this.placementChecker=new NoCollisionRuleChecker<>(new InBoundsRuleChecker<T>(null));
        this.enemyMisses=new HashSet<>();
        this.missInfo=missInfo;
    }
    /**
     * get the width of the BattleShipBoard
     * @return  return the width of the BattleShipBoard
     */
    public int getWidth(){
        return width;
    }
    /**
     * get the height of the BattleShipBoard
     * @return return the height of the BattleShipBoard
     */
    public int getHeight(){
        return height;
    }
    /** add the ship to the board if it follows the placement rules
     * @param toAdd is the ship which is about to be added to the board
     * @return null if add succeed string if not succeed with specific message*/
    public String tryAddShip(Ship<T> toAdd){
        if(this.placementChecker.checkPlacement(toAdd,this)==null){
            myShips.add(toAdd);
            return null;
        }
        return this.placementChecker.checkPlacement(toAdd,this);
    }
    /** get the display Info at specific location
     * @param where is the coordinate needs to be checked whether is in the board or not
     * @param self is a boolean variable indicate whether the board is mine or enemy's
     * @return T if the coordinate belongs to the board null otherwise*/
    public T whatIsAt(Coordinate where,boolean self) {
        for (Ship<T> s: myShips) {
            if (s.occupiesCoordinates(where)){
                return s.getDisplayInfoAt(where,self);
            }
        }
        if(!self){
            if(enemyMisses.contains(where)){
                return missInfo;
            }
        }
        return null;
    }
    /**get the display info at specific position for self board
     * @param where is the coordinate to get display info
     * @return return the display info at this position*/
    public T whatIsAtForSelf(Coordinate where){
        return whatIsAt(where,true);
    }
    /**get the display info at specific position for enemy's board
     * @param where is the coordinate to get display info
     * @return return the display info at this position*/
    public T whatIsAtForEnemy(Coordinate where) {
        return whatIsAt(where, false);
    }
    /**Hit the ship in the board at the specific location
     * @param c is th hit location
     * @return return "You hit a ship's name" if hit succeed or"You missed" If hit fail.*/
    public String fireAt(Coordinate c){
        for(Ship<T> ship:myShips){
            if(ship.occupiesCoordinates(c)){
                ship.recordHitAt(c);
                return "You hit a "+ship.getName()+"!";
            }
        }
        enemyMisses.add(c);
        return "You missed!";
    }
    /**Check if all the ships that belongs to the board is sunk
     * @return allSunk is a boolean variable state whether all the ships belong to the board is sunk.*/
    public boolean allSunk(){
        boolean allSunk=true;
        for(Ship<T> ship: myShips){
            if(!ship.isSunk()){
                allSunk=false;
                break;
            }
        }
        return allSunk;
    }
}
