package edu.duke.ks713.battleship;
/**This class is about the factory of making ships*/
public class V1ShipFactory implements AbstractShipFactory<Character>{
    /**The method of creating ships in different size
     * @param where  the position to place the ship
     * @param w  the width of the ship
     * @param h the height of the ship
     * @param letter the leeter to bedisplayed at each coordinates of the ship
     * @param name the name of the ship
     * @return return the created ship*/
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
    /**The method of making submarine
     * @param where the position to place the ship
     * @return the created ship*/
    @Override
    public Ship<Character> makeSubmarine(Placement where) {
        return createShip(where, 1, 2, 's', "Submarine");
    }
    /**The method of making battleship
     * @param where the position to place the ship
     * @return the created ship*/
    @Override
    public Ship<Character> makeBattleship(Placement where) {
        return createShip(where, 1, 4, 'b', "Battleship");
    }
    /**The method of making carrier
     * @param where the position to place the ship
     * @return the created ship*/
    @Override
    public Ship<Character> makeCarrier(Placement where) {
        return createShip(where, 1, 6, 'c', "Carrier");
    }
    /**The method of making Destroyer
     * @param where the position to place the ship
     * @return the created ship*/
    @Override
    public Ship<Character> makeDestroyer(Placement where) {
        return createShip(where, 1, 3, 'd', "Destroyer");
    }
}
