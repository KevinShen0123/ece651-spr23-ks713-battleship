package edu.duke.ks713.battleship;

import java.util.ArrayList;
import java.util.HashSet;
/**This class is about the construction of rectangleship */
public class RectangleShip<T> extends BasicShip<T> {
    private final String name;
    /**Get all the coordinates belongs to the ship
     * @param upperLeft the coordinate in the upper left corner of the ship
     * @param width the width of the ship
     * @param height the height of the ship
     * @return  a set contains all the coordinates belong to the ship*/
    static HashSet<Coordinate> makeCoords(Coordinate upperLeft, int width, int height) {
        int up_leftx=upperLeft.getRow();
        int up_lefty=upperLeft.getColumn();
        ArrayList<Integer> x=new ArrayList<>();
        ArrayList<Integer> y=new ArrayList<>();
        HashSet<Coordinate> cor_set=new HashSet<>();
        x.add(up_leftx);
        int x_start=up_leftx;
        for(int i=1;i<=height-1;i++){
            x_start+=1;
            x.add(x_start);
        }
        y.add(up_lefty);
        int y_start=up_lefty;
        for(int j=1;j<=width-1;j++){
            y_start+=1;
            y.add(y_start);
        }
        for(int p=0;p<x.size();p++){
            for(int q=0;q<y.size();q++){
                Coordinate coordinate=new Coordinate(x.get(p),y.get(q));
                cor_set.add(coordinate);
            }
        }
       return cor_set;
    }
    /**Constructor for RectangleShip
     * @param name the name of the rectangleship differed by type
     * @param upperLeft the uppleft coordinate of the rectangleship
     * @param width   the width of the ship
     * @param height the height of the ship
     * @param data the data to display when this ship not hit
     * @param onHit the data to display when this ship hit.*/
    public RectangleShip(String name,Coordinate upperLeft, int width, int height, T data, T onHit) {
        this(name,upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit),new SimpleShipDisplayInfo<T>(null,data));
    }
    /**The constructor of a rectangelship
     * @param upperLeft the upperLeft coordinate of the ship
     * @param data the data to display when this ship not hit
     * @param onHit the data to display when ship hit at specific  coordinate*/
    public RectangleShip(Coordinate upperLeft, T data, T onHit) {
        this("testship",upperLeft, 1, 1, data, onHit);
    }
    /**The constructor of the rectangelship
     * @param name the name of the rectangeship differed by type.
     * @param upperLeft  the upperLeft coordinate of the ship
     * @param width  the width of the ship
     * @param height the height of the ship
     * @param shipDisplayInfo what to display at each location of my own ship
     * @param enemyInfo what to display at each location of my enemy's ship*/
    public RectangleShip(String name,Coordinate upperLeft, int width, int height,ShipDisplayInfo<T> shipDisplayInfo,ShipDisplayInfo<T> enemyInfo) {
        super(makeCoords(upperLeft, width, height),shipDisplayInfo,enemyInfo);
        this.name=name;
    }
   /**Get the name of this ship
    * @return the name of this ship*/

    @Override
    public String getName() {
        return name;
    }
}
