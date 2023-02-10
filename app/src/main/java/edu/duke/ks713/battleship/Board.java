package edu.duke.ks713.battleship;

import java.util.ArrayList;

public interface Board<T>{
    /**Get the width of the board
     * @return return the width of the board*/
    public int getWidth();
    /**
     * get the height of the BattleShipBoard
     * @return return the height of the BattleShipBoard
     */
    public int getHeight();
    /** get the display Info at specific location
     * @param where is the coordinate needs to be checked whether is in the board or not
     * @param self is a boolean variable indicate whether the board is mine or enemy's
     * @return T if the coordinate belongs to the board null otherwise*/
    public T whatIsAt(Coordinate where,boolean self);
    /** add the ship to the board if it follows the placement rules
     * @param toAdd is the ship which is about to be added to the board
     * @return null if add succeed string if not succeed with specific message*/
    public String tryAddShip(Ship<T> toAdd);
    /**Hit the ship in the board at the specific location
     * @param c is th hit location
     * @return return "You hit a ship's name" if hit succeed or"You missed" If hit fail.*/
    public String fireAt(Coordinate c);
    /**get the display info at specific position for self board
     * @param where is the coordinate to get display info
     * @return return the display info at this position*/
    public T whatIsAtForSelf(Coordinate where);
    /**get the display info at specific position for enemy's board
     * @param where is the coordinate to get display info
     * @return return the display info at this position*/
    public T whatIsAtForEnemy(Coordinate where);
    /**Check if all the ships that belongs to the board is sunk
     * @return a boolean variable state whether all the ships belong to the board is sunk.*/
    public boolean allSunk();
}
