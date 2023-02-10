package edu.duke.ks713.battleship;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * This class handles textual display of
 * a Board (i.e., converting it to a string to show
 * to the user).
 * It supports two ways to display the Board:
 * one for the player's own board, and one for the
 * enemy's board.
 */
public class BoardTextView {
    /**
     * The Board to display
     */
    private final Board<Character> the_board_to_display;
    /**
     * Constructs a BoardView, given the board it will display.
     * @param toDisplay is the Board to display
     * @throws IllegalArgumentException if the width are greater than 10 or width are greater than 26
     */
    public BoardTextView(Board<Character> toDisplay) {
        this.the_board_to_display= toDisplay;
        if (toDisplay.getWidth() > 10 || toDisplay.getHeight() > 26) {
            throw new IllegalArgumentException(
                    "Board must be no larger than 10x26, but is " + toDisplay.getWidth() + "x" + toDisplay.getHeight());
        }

    }
    /**Display the information on targeted board
     * @param getSquareFn the function to display board
     * @return the information to display*/
    protected String displayAnyBoard(Function<Coordinate, Character> getSquareFn){
        String header=this.makeHeader();
        StringBuilder seps=new StringBuilder("");
        for(int i=0;i<the_board_to_display.getWidth()-1;i++){
            seps.append("|");
            if(i< the_board_to_display.getWidth()-2){
                seps.append(" ");
            }
        }
        String seprators=seps.toString();
        StringBuilder alpha=new StringBuilder("");
        char first_letter='A';
        for(int j=0;j< the_board_to_display.getHeight();j++){
            alpha.append(Character.toString(first_letter));
            first_letter+=1;
        }
        String alphas= alpha.toString();
        StringBuilder middle=new StringBuilder();
        for(int k=0;k<alphas.length();k++){
            String sub=alphas.substring(k,k+1);
            if(sub.length()>0){
                sub+=" ";
                // sub+=seprators;
                String[]ship_letters=new String[the_board_to_display.getWidth()];
                for(int p=0;p<ship_letters.length;p++){
                    Coordinate cor=new Coordinate(k,p);
                    if(getSquareFn.apply(cor)!=null){
                        ship_letters[p]=Character.toString(getSquareFn.apply(cor));
                    }
                }
                String[]sep_arr=seprators.split(" ");
                for(int y=0;y<ship_letters.length;y++){
                    if(ship_letters[y]!=null){
                        sub+=ship_letters[y];
                    }else{
                        sub+=" ";
                    }
                    if(y< sep_arr.length){
                        sub+=sep_arr[y];
                    }
                }
                sub+=" ";
                sub+=alphas.substring(k,k+1);
                sub+="\n";
                middle.append(sub);
            }
        }
        String middlepart=middle.toString();
        header+=middlepart;
        header+=this.makeHeader();
//        String[]ar1=header.split("\n");
//        System.out.println(ar1.length);
        return header; //this is a placeholder for the moment
    }
    /**
     * display the whole information on my battleship board
     * @return the information to display
     */
    public String displayMyOwnBoard() {
        return displayAnyBoard((c)->the_board_to_display.whatIsAtForSelf(c));
    }
    /**
     * display the whole information on enemy's battleship board
     * @return the information to display
     */
    public String displayEnemyBoard(){
        return displayAnyBoard((c)->the_board_to_display.whatIsAtForEnemy(c));
    }
    /**
     * This makes the header line, e.g. 0|1|2|3|4\n
     *
     * @return the String that is the header line for the given board
     */
    String makeHeader() {
        StringBuilder ans = new StringBuilder("  "); // README shows two spaces at
        String sep=""; //start with nothing to separate, then switch to | to separate
        for (int i = 0; i < the_board_to_display.getWidth(); i++) {
            ans.append(sep);
            ans.append(i);
            sep = "|";
        }
        ans.append("\n");
        return ans.toString();
    }
    /**Display the information on my board and enemy's board together
     * @param enemyView the BoardTextView of my enemy
     * @param myHeader  the header of my part
     * @param enemyHeader the header of enemy's part
     * @return the info to display*/
    public String displayMyBoardWithEnemyNextToIt(BoardTextView enemyView, String myHeader, String enemyHeader){
        String myViewDisplay=this.displayMyOwnBoard();
        String enemyViewDisplay=enemyView.displayEnemyBoard();
        String []myViewDisplayLines=myViewDisplay.split("\n");
        String[]enemyViewDisplayLines=enemyViewDisplay.split("\n");
        ArrayList<String> lines=new ArrayList<>();
        String myH="     "+myHeader;
        int start_value=5+myHeader.length();
        while(start_value< 2*the_board_to_display.getWidth()+21){
            start_value+=1;
            myH+=" ";
        }
        myH+=enemyHeader;
        lines.add(myH);
        //System.out.println("PPPPPPPPPPPPPPPPPPPPPP:"+myH);
        String numberH=myViewDisplayLines[0];
        int number_value=2+numberH.length();
        while(number_value<=2* the_board_to_display.getWidth()+20){
            number_value+=1;
            numberH+=" ";
        }
        //System.out.println("PPPPPPPPPPPPPPPPPPPPPP:"+myH);
        numberH+=enemyViewDisplayLines[0];
        lines.add(numberH);
        for(int i=0;i<myViewDisplayLines.length;i++){
            if(i>0&&i<myViewDisplayLines.length-1){
                String left=myViewDisplayLines[i];
                int start_v=2* the_board_to_display.getWidth()+3;
                while(start_v<2*the_board_to_display.getWidth()+19){
                    start_v+=1;
                    left+=" ";
                }
                left+=enemyViewDisplayLines[i];
                lines.add(left);
            }
        }
        lines.add(numberH);
        String finalDisplayInfo="";
        for(int j=0;j<lines.size();j++){
            if(j<lines.size()-1){
                finalDisplayInfo+=lines.get(j)+"\n";
            }else{
                finalDisplayInfo+=lines.get(j);
            }
        }
        return finalDisplayInfo;
    }
}
