package edu.duke.ks713.battleship;

import java.util.HashMap;

public abstract class BasicShip<T>implements Ship<T>{
    protected ShipDisplayInfo<T> myDisplayInfo;
    protected ShipDisplayInfo<T> enemyDisplayInfo;
   protected HashMap<Coordinate, Boolean>  myPieces;
    /**
     * Constructs a BasicShip and initialize the corresponding information
     * @param where is the collection of all coordinates in the ship.
     * @param myDisplayInfo is the display info of my ships.
     * @param enemyDisplayInfo is the display info of enemy's ships.
     */
    public BasicShip(Iterable<Coordinate> where,ShipDisplayInfo<T> myDisplayInfo,ShipDisplayInfo<T> enemyDisplayInfo){
        myPieces=new HashMap<>();
        for(Coordinate cor:where){
            myPieces.put(cor,false);
        }
        this.myDisplayInfo=myDisplayInfo;
        this.enemyDisplayInfo=enemyDisplayInfo;
    }
    /**
     * Check whether the specified coordinate is in the ship or not.
     * @param where is a Coordinate for checking whether it is in the ship
     * @return return a boolean to state whether this coordinate is in the ship or not.
     */
    @Override
    public boolean occupiesCoordinates(Coordinate where) {
        return myPieces.size()!=0&&myPieces.get(where)!=null;
    }
    /**
     * Check whether this ship is sunk(all the coordinate has been hit).
     * @return return a boolean to state whether this ship is sunk(all the coordinate has been hit).
     */
    @Override
    public boolean isSunk() {
        boolean is_sunk=true;
        for(Coordinate cor: myPieces.keySet()){
            if(myPieces.get(cor)==false){
                is_sunk=false;
                break;
            }
        }
        return is_sunk;
    }
    /**
     * record this coordinate has been hitted.
     * @param where is the coordinate to hit at.
     */
    @Override
    public void recordHitAt(Coordinate where) {
         checkCoordinateInThisShip(where);
         myPieces.put(where,true);
    }
    /**
     * Check if the ship has been hit at this coordinate
     * @param where  is the coordinate in the ship for checking whether hit at or not.
     * @return return a boolean to state whether this coordinate in the ship was hit.
     */
    @Override
    public boolean wasHitAt(Coordinate where) {
        checkCoordinateInThisShip(where);
        return myPieces.get(where);
    }
    /**
     * get the display info at specific coordinate
     * @param where  is the coordinate in the ship to get display info
     * @param myShip is a boolean to state whether it is my ship or enemy's ship.
     * @return return the display info at specific location.
     */
    @Override
    public T getDisplayInfoAt(Coordinate where,boolean myShip) {
        if(myShip){
            return myDisplayInfo.getInfo(where,this.wasHitAt(where));
        }
        return enemyDisplayInfo.getInfo(where,this.wasHitAt(where));
    }
    /**
     * Check if the coordinate belongs to the ship
     * @param c  is the coordinate to check whether in the ship or not.
     * @throws IllegalArgumentException if the coordinate is not in the ship.
     */
    protected void checkCoordinateInThisShip(Coordinate c){
        boolean b=this.occupiesCoordinates(c);
        if(b==false){
            throw new IllegalArgumentException();
        }
    }
    /**
     * Get all the coordinates belong to the ship
     * @return return all coordinates belong to this ship.
     */
    public Iterable<Coordinate> getCoordinates(){
        return myPieces.keySet();
    }
}
