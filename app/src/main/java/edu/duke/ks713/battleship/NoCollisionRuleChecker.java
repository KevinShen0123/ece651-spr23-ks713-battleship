package edu.duke.ks713.battleship;

import java.util.Iterator;

public class NoCollisionRuleChecker<T> extends PlacementRuleChecker<T>{
    public NoCollisionRuleChecker(PlacementRuleChecker<T> next) {
        super(next);
    }
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
    @Override
    protected String checkMyRule(Ship<T> theShip, Board<T> theBoard) {
       if(check_rule(theShip, theBoard)!=null){
           return check_rule(theShip, theBoard);
       }
       return null;
    }
}
