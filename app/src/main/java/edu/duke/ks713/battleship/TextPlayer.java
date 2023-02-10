package edu.duke.ks713.battleship;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Function;

import static java.lang.System.exit;
/**THis class handles the play round for one player*/
public class TextPlayer {
    final String name;
    final Board<Character> theBoard;
    final BufferedReader inputReader;
    final PrintStream out;
    final AbstractShipFactory<Character> shipFactory;
    final ArrayList<String> shipsToPlace;
    final HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns;
    /**Construct a player object for this battleship game
     * @param name the name of the player
     * @param theBoard  the borad belongs to the player
     * @param  inputReader the inputReader of the whole system
     * @param out The output stream
     * @param shipFactory the class help us to make ship.*/
    public TextPlayer(String name,Board<Character>theBoard,BufferedReader inputReader,PrintStream out,AbstractShipFactory<Character> shipFactory){
        this.name=name;
        this.theBoard=theBoard;
        this.inputReader=inputReader;
        this.out=out;
        this.shipFactory=shipFactory;
        this.shipsToPlace=new ArrayList<>();
        this.shipCreationFns=new HashMap<>();
        this.setupShipCreationMap();
        this.setupShipCreationList();
    }
    /**This method reads the coordinate from the user input
     * @param prompt the prompt to help user input
     * @return return the coordinate constructed from user input.
     * @throws EOFException when the reader meets EOF
     * @throws IOException when IO error happens*/
    public Coordinate readCoordinate(String prompt) throws IOException{
        prompt=prompt.replaceAll("\r","");
        out.println(prompt);
        String s=inputReader.readLine();
        if(s==null){
            throw new EOFException();
        }
        return new Coordinate(s);
    }
    /**This method reads the placement from the user input
     * @param prompt the prompt to help user input
     * @return return the placement constructed from user input.
     * @throws EOFException when the reader meets EOF
     * @throws IOException when IO error happens*/
    public Placement readPlacement(String prompt) throws IOException {
        prompt=prompt.replaceAll("\r","");
        out.println(prompt);
        String s = inputReader.readLine();
     //  System.out.println(s.charAt(s.length()-1)=='\u001a');
        if(s==null){
            throw new EOFException();
        }
        s=s.replaceAll("\r","");
        return new Placement(s);
    }
    /**This method do the interactive placement phase with the user*/
    public void doPlacementPhase() throws IOException{
        BoardTextView btv1=new BoardTextView(theBoard);
        out.println("Player "+name+"'s"+" turn");
        out.println(btv1.displayMyOwnBoard());
        String s="Player "+name+": you are going to place the following ships (which are all\n" +
                "rectangular). For each ship, type the coordinate of the upper left\n" +
                "side of the ship, followed by either H (for horizontal) or V (for\n" +
                "vertical).  For example M4H would place a ship horizontally starting\n" +
                "at M4 and going to the right.  You have\n" +
                "\n" +
                "2 \"Submarines\" ships that are 1x2 \n" +
                "3 \"Destroyers\" that are 1x3\n" +
                "3 \"Battleships\" that are 1x4\n" +
                "2 \"Carriers\" that are 1x6\n";
        out.println(s);
        for(int i=0;i<shipsToPlace.size();i++){
            String shipName= shipsToPlace.get(i);
            Function<Placement, Ship<Character>> func=shipCreationFns.get(shipName);
            this.doOnePlacement(shipName,func);
        }

    }
    /**One step of the placement plase*/
    public void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn) throws IOException {
        while(true){
            try{
                //Placement p = readPlacement("Player " + name + " where do you want to place a " + shipName + "?");
                while(true){
                    Placement p = readPlacement("Player " + name + " where do you want to place a " + shipName + "?");
                    Ship<Character> s = createFn.apply(p);
                    if(theBoard.tryAddShip(s)!=null){
                        out.println(theBoard.tryAddShip(s));
                    }else{
                        break;
                    }
                }
                BoardTextView view=new BoardTextView(theBoard);
                out.println("Current ocean:");
                out.print(view.displayMyOwnBoard());
                break;
            }catch(IllegalArgumentException e){
               out.println("The placement is invalid! It does not have a correct format!");
            }
        }
    }
    /**Set up ship creation functions*/
    protected void setupShipCreationMap(){
        shipCreationFns.put("Submarine", (p) -> shipFactory.makeSubmarine(p));
        shipCreationFns.put("Destroyer", (p) -> shipFactory.makeDestroyer(p));
        shipCreationFns.put("Battleship", (p) -> shipFactory.makeBattleship(p));
        shipCreationFns.put("Carrier", (p) -> shipFactory.makeCarrier(p));
    }
    /**Initialize ship list*/
    protected void setupShipCreationList(){
        shipsToPlace.addAll(Collections.nCopies(2, "Submarine"));
        shipsToPlace.addAll(Collections.nCopies(3, "Destroyer"));
        shipsToPlace.addAll(Collections.nCopies(2, "Battleship"));
        shipsToPlace.addAll(Collections.nCopies(3, "Carrier"));
    }
    /**Check if this player has lost
     * @return the boolean variable state whether the user has lost*/
    public boolean check_lost(){
        return theBoard.allSunk();
    }
    /**The method for the user to play one turn
     * @param enemy the enemy player
     * @throws IOException when calling other method possibly*/
    public void playOneTurn(TextPlayer enemy) throws IOException{
       BoardTextView myView=new BoardTextView(this.theBoard);
       BoardTextView enemyView=new BoardTextView(enemy.theBoard);
       out.println("Player "+name+"'s"+" turn");
       String myHeader="Your ocean";
       String enemyHeader="Player "+enemy.name+"'s"+"ocean";
       out.println(myView.displayMyBoardWithEnemyNextToIt(enemyView,myHeader,enemyHeader));
       //Coordinate prompt Check.
        while(true){
            try{
                Coordinate fireCor=readCoordinate("Please input the coordinate you want to fire at:");
                out.println(enemy.theBoard.fireAt(fireCor));
                break;
            }catch(IllegalArgumentException e){
                out.println("That coordinate is invalid: it does not have the correct format.");
            }
        }
    }
}
