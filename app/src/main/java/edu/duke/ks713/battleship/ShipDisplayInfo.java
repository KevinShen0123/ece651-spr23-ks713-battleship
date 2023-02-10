package edu.duke.ks713.battleship;

public interface ShipDisplayInfo<T> {
    public T getInfo(Coordinate where, boolean hit);
}
