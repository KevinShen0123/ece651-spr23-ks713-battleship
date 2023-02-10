package edu.duke.ks713.battleship;

import java.util.Iterator;

public class InBoundsRuleChecker<T> extends PlacementRuleChecker<T> {
    public InBoundsRuleChecker(PlacementRuleChecker<T> next) {
        super(next);
    }
    protected String check_rule(Ship<T> theShip, Board<T> theBoard){
        int width= theBoard.getWidth();
        int height= theBoard.getHeight();
        Iterable<Coordinate> cors=theShip.getCoordinates();
        Iterator<Coordinate> iter= cors.iterator();
        while(iter.hasNext()){
            Coordinate point=iter.next();
            int r=point.getRow();
            int c= point.getColumn();
             boolean a=r>=0;
             boolean b=r<height;
             boolean f=c>=0;
             boolean g=c<width;
             if(!a){
               return "That placement is invalid: the ship goes off the bottom of the board.";
             }
             if(!b){
                return "That placement is invalid: the ship goes off the top of the board.";
             }
             if(!f){
                return "That placement is invalid: the ship goes off the left of the board.";
             }
             if(!g){
               return "That placement is invalid: the ship goes off the right of the board.";
             }
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
