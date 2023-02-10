package edu.duke.ks713.battleship;
/**This class is about the handling of ship display information*/
public class SimpleShipDisplayInfo<T> implements ShipDisplayInfo<T>{
    T myData;
    T onHit;
    /**Constructor for SimpleShipDisplayInfo
     * @param myData the data to be displayed at the situation where this ship not hit at this position
     * @param onHit the data to be displayed at the situation where this ship hit at this position\*/
    public SimpleShipDisplayInfo(T myData, T onHit){
        this.myData=myData;
        this.onHit=onHit;
    }
    /**The method to get the display info at the location
     * @param where the coordinate that we need the display info
     * @param hit whether the ship is hit or not at this position
     * @return return the display info at this position*/
    @Override
    public T getInfo(Coordinate where, boolean hit) {
        return hit?onHit:myData;
    }
}
