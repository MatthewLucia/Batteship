import java.util.ArrayList;
import java.io.*;

/**
 * Game class. Contains all necessary
 * methods to operate a game of battleship
 */

public class Game
{
   // Instance Variables
   ComputerBoard compBoard;
   UserBoard userBoard;
   
   /*
   *  Constructor - creates the boards for the user
   *  and computer
   */
   public Game() throws IOException 
   {
      this.compBoard = new ComputerBoard("compFleet.txt");
      this.userBoard = new UserBoard("userFleet.txt");
   }
   
   public ArrayList<String> makeComputerMove()
   {
      return userBoard.makeComputerMove();
   }
   
   public String makePlayerMove(String s)
   {
      return compBoard.makePlayerMove(new Move(s));
   }
   
   public boolean computerDefeated()
   {
      return compBoard.getFleet().gameOver();
   }
   
   public boolean userDefeated()
   {
      return userBoard.getFleet().gameOver();
   }
   
   @Override
   public String toString()
   {
      return userBoard.toString() + compBoard.toString();
   }
   
}