package edu.duke.ks713.battleship;

import java.util.ArrayList;
import java.util.HashSet;

public class RectangleShip<T> extends BasicShip<T> {
    private final String name;
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
    public RectangleShip(String name,Coordinate upperLeft, int width, int height, T data, T onHit) {
        this(name,upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit),new SimpleShipDisplayInfo<T>(null,data));
    }
    public RectangleShip(Coordinate upperLeft, T data, T onHit) {
        this("testship",upperLeft, 1, 1, data, onHit);
    }

    public RectangleShip(String name,Coordinate upperLeft, int width, int height,ShipDisplayInfo<T> shipDisplayInfo,ShipDisplayInfo<T> enemyInfo) {
        super(makeCoords(upperLeft, width, height),shipDisplayInfo,enemyInfo);
        this.name=name;
    }


    @Override
    public String getName() {
        return name;
    }
}
