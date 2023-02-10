package edu.duke.ks713.battleship;

public class Placement {
    private final Coordinate where;
    private final char orientation;
    /**
     * Constructs a Placement with coordinate and location
     * @param where is the coordinate of the placemet
     * @param orientation is the direction of the placement
     */
    public Placement(Coordinate where,char orientation){
        this.where=where;
        this.orientation=orientation;
    }
    /**
     * Constructs a Placement with location string
     * @param descr is the location string, the combination of coordinates and the direction
     * @throws IllegalArgumentException if descr not meet the requirement
     */
    public Placement(String descr){
        String descr2=descr.replaceAll(" ","");
        if(descr2.length()!=3){
            throw new IllegalArgumentException();
        }
        String first_part=descr2.substring(0,descr2.length()-1);
        Coordinate x=new Coordinate(first_part);
        this.where=x;
        char or=descr2.charAt(descr2.length()-1);
        boolean direction_judge=(or=='V'||or=='H')||(or=='v'||or=='h');
        if(direction_judge==false){
            throw new IllegalArgumentException();
        }
        this.orientation=or;
    }
    /**The getter method of the coordinate where*/
    public Coordinate getWhere(){
        return where;
    }
    /**The getter method of the orientation*/
    public char getOrientation(){
        return orientation;
    }
    /**The toString method of Placement class, similar to the toString method of Coordinate class*/
    @Override
    public String toString() {
        return "("+where.toString()+", " + orientation+")";
    }
    /**The hashcode method of the Placement class*/
    @Override
    public int hashCode() {
        return toString().hashCode();
    }
    /**The equals method of the Placement class, similar to Coordinate class*/
    @Override
    public boolean equals(Object o) {
        if (o.getClass().equals(getClass())) {
            Placement c = (Placement) o;
            String thisOri=Character.toString(this.orientation);
            String cOri=Character.toString(c.orientation);
            return c.where.equals(((Placement) this).where)&&(thisOri.equalsIgnoreCase(cOri));
        }
        return false;
    }

}
