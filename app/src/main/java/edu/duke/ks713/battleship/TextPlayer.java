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

public class TextPlayer {
    final String name;
    final Board<Character> theBoard;
    final BufferedReader inputReader;
    final PrintStream out;
    final AbstractShipFactory<Character> shipFactory;
    final ArrayList<String> shipsToPlace;
    final HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns;

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
    public Coordinate readCoordinate(String prompt) throws IOException{
        prompt=prompt.replaceAll("\r","");
        out.println(prompt);
        String s=inputReader.readLine();
        if(s==null){
            throw new EOFException();
        }
        return new Coordinate(s);
    }
    public Placement readPlacement(String prompt) throws IOException {
        prompt=prompt.replaceAll("\r","");
        out.println(prompt);
        String s = inputReader.readLine();
     //  System.out.println(s.charAt(s.length()-1)=='\u001a');
        if(s==null){
            throw new EOFException();
        }
        s=s.replaceAll("\r","");
        System.out.println(s);
        System.out.println(s.length());
        return new Placement(s);
    }
    public void doPlacementPhase() throws IOException{
        BoardTextView btv1=new BoardTextView(theBoard);
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
                out.print(view.displayMyOwnBoard());
                break;
            }catch(Exception e){
                //out.println(e.printStackTrace(););
                out.println("The placement is invalid! It does not have a correct format!");
            }
        }
    }
    protected void setupShipCreationMap(){
        shipCreationFns.put("Submarine", (p) -> shipFactory.makeSubmarine(p));
        shipCreationFns.put("Destroyer", (p) -> shipFactory.makeDestroyer(p));
        shipCreationFns.put("Battleship", (p) -> shipFactory.makeBattleship(p));
        shipCreationFns.put("Carrier", (p) -> shipFactory.makeCarrier(p));
    }

    protected void setupShipCreationList(){
        shipsToPlace.addAll(Collections.nCopies(2, "Submarine"));
        shipsToPlace.addAll(Collections.nCopies(3, "Destroyer"));
        shipsToPlace.addAll(Collections.nCopies(2, "Battleship"));
        shipsToPlace.addAll(Collections.nCopies(3, "Carrier"));
    }
    public boolean check_lost(){
        return theBoard.allSunk();
    }
    public void playOneTurn(TextPlayer enemy) throws IOException{
       BoardTextView myView=new BoardTextView(this.theBoard);
       BoardTextView enemyView=new BoardTextView(enemy.theBoard);
       out.println("Player "+name+"'s"+"turn");
       String myHeader="Your ocean";
       String enemyHeader="Player "+enemy.name+"'s"+"ocean";
       myView.displayMyBoardWithEnemyNextToIt(enemyView,myHeader,enemyHeader);
       //Coordinate prompt Check.
        while(true){
            try{
                while(true){
                    Coordinate fireCor=readCoordinate("Please input the coordinate you want to fire at:");
                    if(enemy.theBoard.fireAt(fireCor)!=null){
                        out.println(enemy.theBoard.fireAt(fireCor));
                    }else{
                        break;
                    }
                }
                break;
            }catch(Exception e){
                out.println("That coordinate is invalid: it does not have the correct format.");
            }
        }
    }
}
