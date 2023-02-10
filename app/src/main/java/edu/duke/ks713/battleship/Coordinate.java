package edu.duke.ks713.battleship;
/**This class constructs the corrdinate and also handles important related functions*/
public class Coordinate {
    private final int row;
    private final int column;
    /**
     * Constructs a BattleShipBoard with the specified width
     * and height
     * @param r is the row location of the point
     * @param c is the column location of the point
     */
    public Coordinate(int r,int c){
        this.row=r;
        this.column=c;
    }
    /**
     * Constructs a BattleShipBoard with the specified width
     * and height
     * @param descr is the string than represents the location;
     * @throws IllegalArgumentException if the descr string do not meet the requirement "A-Z"+"0-9"
     */
    public Coordinate(String descr){
        String check=descr.replaceAll(" ","");
        if(check.length()!=2){
            throw new IllegalArgumentException("illegal!");
        }
        char rowL=check.charAt(0);
        String rowLs=Character.toString(rowL).toUpperCase();
        rowL=rowLs.charAt(0);
        char columnL=check.charAt(1);
        boolean row_right=((rowL>='A'&&rowL<='Z')||(rowL>='a'&&rowL<='z'));
        boolean column_right=(columnL>='0'&&columnL<='9');
        if(row_right==false||column_right==false){
            throw new IllegalArgumentException("illegal!");
        }
        this.row=rowL-'A';
        this.column=columnL-'0';
    }
    /**
     * equals method to check if the two coordinates are the same
     * @param o the object which we check if it is equal with this Coordinate
     * @return whether this coordinate is equal with o.
     */
    @Override
    public boolean equals(Object o) {
        if (o.getClass().equals(getClass())) {
            Coordinate c = (Coordinate) o;
            return row == c.row && column == c.column;
        }
        return false;
    }
    /**
     * toString method overriden from Object class
     * @return string that represents the concatenation of different attributes
     */
    @Override
    public String toString() {
        return "("+row+", " + column+")";
    }
    /**
     * hashcode method overriden from Object class
     * @return the hashcode of this object.
     */
    @Override
    public int hashCode() {
        return toString().hashCode();
    }
    /**
     * getter method for row
     * @return the row of this coordinate
     */
    public int getRow(){
        return row;
    }
    /**
     * getter method for column
     * @return the column of this coordinate
     */
    public int getColumn(){
        return column;
    }

}
