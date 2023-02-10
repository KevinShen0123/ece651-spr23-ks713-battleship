package edu.duke.ks713.battleship;

import java.util.Iterator;
/**This class is about placement rule checking*/
public class NoCollisionRuleChecker<T> extends PlacementRuleChecker<T>{
    /**Construct NoCollisionRuleChecker  as a part of rule checking chain
     * @param next next rule checker in the chain.*/
    public NoCollisionRuleChecker(PlacementRuleChecker<T> next) {
        super(next);
    }
    /**Check whether add the ship to the board have no overlaps with othership
     * @param theShip that is about to be added to the board
     * @param theBoard who is about to add a ship/
     * @return null if adding the ship satisfy the requirements otherwise return specific reason*/
    protected String check_rule(Ship<T> theShip, Board<T> theBoard){
        Iterable<Coordinate> cor_iter=theShip.getCoordinates();
        boolean taken=false;
        for(Coordinate mycor:cor_iter){
            if(theBoard.whatIsAtForSelf(mycor)!=null){
                taken=true;
                break;
            }
        }
        if(taken){
            return "That placement is invalid: the ship overlaps another ship.";
        }
        return null;
    }
    /**Check whether add the ship to the board have no overlaps with othership
     * @param theShip that is about to be added to the board
     * @param theBoard who is about to add a ship/
     * @return null if adding the ship satisfy the requirements otherwise return specific reason*/
    @Override
    protected String checkMyRule(Ship<T> theShip, Board<T> theBoard) {
       if(check_rule(theShip, theBoard)!=null){
           return check_rule(theShip, theBoard);
       }
       return null;
    }
}
