/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.duke.ks713.battleship;
import org.w3c.dom.Text;

import java.io.*;

import static java.lang.System.exit;

public class App {
    final TextPlayer player1;
    final TextPlayer player2;
    /**THis method contsruct the app with two user*/
    public App(TextPlayer player1, TextPlayer player2) {
      this.player1=player1;
      this.player2=player2;
    }
    /**This method let two player do the attacking one by one*/
    public void doAttackPhase() throws IOException{
        while(true){
            player1.playOneTurn(player2);
            if(player2.check_lost()){
                System.out.println("Player "+player1.name+" won!");
                break;
            }
            player2.playOneTurn(player1);
            if(player1.check_lost()){
                System.out.println("Player "+player2.name+" won!");
                break;
            }
        }
    }
    /**This method starts the whole game*/
    public void start ()throws IOException{
        player1.doPlacementPhase();
        player2.doPlacementPhase();
        doAttackPhase();
    }
    /**This method served as the default entry point of the program*/
    public static void main(String[] args) throws IOException{
           Board<Character> b1 = new BattleShipBoard<Character>(10, 20,'X');
           Board<Character> b2 = new BattleShipBoard<Character>(10, 20,'X');
           BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
           V1ShipFactory factory = new V1ShipFactory();
           TextPlayer p1 = new TextPlayer("A", b1, input, System.out, factory);
           TextPlayer p2 = new TextPlayer("B", b2, input, System.out, factory);
           App test_app=new App(p1,p2);
           test_app.start();
    }
}
