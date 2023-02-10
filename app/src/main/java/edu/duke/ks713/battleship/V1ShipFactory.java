package edu.duke.ks713.battleship;

public class V1ShipFactory implements AbstractShipFactory<Character>{
    protected Ship<Character> createShip(Placement where, int w, int h, char letter, String name){
        char orientation=where.getOrientation();
        int width=w;
        int height=h;
        if(orientation=='h'||orientation=='H'){
            width=h;
            height=w;
        }
        boolean valid=orientation=='h'||orientation=='H'||orientation=='v'||orientation=='V';
        if(!valid){
            throw new IllegalArgumentException();
        }
        RectangleShip<Character> rec=new RectangleShip<>(name,where.getWhere(),width,height,letter,'*');
        return rec;
    }
    @Override
    public Ship<Character> makeSubmarine(Placement where) {
        return createShip(where, 1, 2, 's', "Submarine");
    }

    @Override
    public Ship<Character> makeBattleship(Placement where) {
        return createShip(where, 1, 4, 'b', "Battleship");
    }

    @Override
    public Ship<Character> makeCarrier(Placement where) {
        return createShip(where, 1, 6, 'c', "Carrier");
    }

    @Override
    public Ship<Character> makeDestroyer(Placement where) {
        return createShip(where, 1, 3, 'd', "Destroyer");
    }
}
