package edu.duke.ks713.battleship;

import java.util.Iterator;
/**This class is abot placement rule checking*/
public class InBoundsRuleChecker<T> extends PlacementRuleChecker<T> {
    /**Construct InBoundsRuleChecker as a part of rule checking chain
     * @param next next rule checker in the chain.*/
    public InBoundsRuleChecker(PlacementRuleChecker<T> next) {
        super(next);
    }
    /**check whether all the coordinates of the ship satisfy the inbounds rule
     * @param theShip that is about to be added to the board
     * @param theBoard that is about to add a theShip
     * @return If the add of this ship meets the requirements return null otherwise return specific reason*/
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
    /***Check whether the placement of the ship to the board meets the inbounds rule.
     * @param theShip the ship that is about to be added
     * @param theBoard  the board contains ships
     * @return if the placement of the ship to the board viloates this rule, return a string about specific reason otherwise
     * return null*/
    @Override
    protected String checkMyRule(Ship<T> theShip, Board<T> theBoard) {
       if(check_rule(theShip, theBoard)!=null){
           return check_rule(theShip, theBoard);
       }
       return null;
    }
}
