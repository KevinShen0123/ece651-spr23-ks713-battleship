package edu.duke.ks713.battleship;

public abstract class PlacementRuleChecker<T> {
    private final PlacementRuleChecker<T> next;
    //more stuff
    public PlacementRuleChecker(PlacementRuleChecker<T> next) {
        this.next = next;
    }
    protected abstract String checkMyRule(Ship<T> theShip, Board<T> theBoard);
    public String checkPlacement (Ship<T> theShip, Board<T> theBoard) {
        //if we fail our own rule: stop the placement is not legal
        //System.out.println(this.getClass());
        if (checkMyRule(theShip, theBoard)!=null) {
            return checkMyRule(theShip, theBoard);
        }
        //otherwise, ask the rest of the chain.
        if (next != null) {
            return next.checkPlacement(theShip, theBoard);
        }
        //if there are no more rules, then the placement is legal
        return null;
    }

}
