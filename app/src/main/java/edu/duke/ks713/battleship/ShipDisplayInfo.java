package edu.duke.ks713.battleship;
/**THis interface handles how to display the ship*/
public interface ShipDisplayInfo<T> {
    /**The method to get the display info at the location
     * @param where the coordinate that we need the display info
     * @param hit whether the ship is hit or not at this position
     * @return return the display info at this position*/
    public T getInfo(Coordinate where, boolean hit);
}
