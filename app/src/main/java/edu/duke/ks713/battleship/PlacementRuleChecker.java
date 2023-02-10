package edu.duke.ks713.battleship;
/**The base class of placement rule checker*/
public abstract class PlacementRuleChecker<T> {
    private final PlacementRuleChecker<T> next;
    //more stuff
    /**constructor
     * @param next the rule checker in the whole chain*/
    public PlacementRuleChecker(PlacementRuleChecker<T> next) {
        this.next = next;
    }
    /**abstract rule checking method
     * @param theShip the ship need to be added\
     * @param theBoard the board need to add ship and check*/
    protected abstract String checkMyRule(Ship<T> theShip, Board<T> theBoard);
    /**Placement checking method for the whole chain
     * @param theShip the ship need to be added
     * @param theBoard the board need to add ship*/
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
