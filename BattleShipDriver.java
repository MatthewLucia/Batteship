import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * BattleshipDriver class. Runs a game of 
 * battleship between the user and the computer
 */

public class BattleShipDriver
{
   public static void main(String [] args) throws IOException
   {
      // Create the game object
      Game game = new Game();
      
      // Create an object for the Scanner class
      Scanner keyboard = new Scanner(System.in);
      
      // Flip a coin for who goes first
      Random rand = new Random();
      int result = rand.nextInt(2);
      if (result == 0)
      {
         System.out.println("You won the coin toss and get to go first!");
         while (!game.userDefeated() && !game.computerDefeated())
         {
            System.out.println(game.toString());
            
            System.out.println("Your turn: ");
            String move = keyboard.next();
            System.out.println(game.makePlayerMove(move));
            System.out.println(game.toString());
            
            System.out.println("Computer's turn. Press enter to continue...");
            try {
               System.in.read(); }
            catch (Exception e) {
               System.out.println("Error: System exiting.");
               System.exit(0);
            }
            
            System.out.print("Computer chose: ");
            for (String s : game.makeComputerMove()) {
               System.out.print(s + "\n"); }
         }
         if (game.userDefeated() == true)
         {
            System.out.println("Game Over. Computer Wins!");
         }
         else
         {
            System.out.println("Game Over. User Wins!");
         }
      }
      else
      {
         System.out.println("The computer won the coin toss and gets to go first.");
         while (!game.userDefeated() && !game.computerDefeated())
         {
            System.out.println("Computer's turn. Press enter to continue...");
            try {
               System.in.read(); }
            catch (Exception e) {
               System.out.println("Error: System exiting.");
               System.exit(0);
            }
            
            System.out.print("Computer Chose: ");
            for (String s : game.makeComputerMove()) {
               System.out.print(s + "\n"); }
            System.out.println(game.toString());
            
            System.out.println("Your turn: ");
            String move = keyboard.next();
            System.out.println(game.makePlayerMove(move)); 
            System.out.println(game.toString());
         }
         if (game.userDefeated() == true)
         {
            System.out.println("Game Over. Computer Wins!");
         }
         else
         {
            System.out.println("Game Over. User Wins!");
         }
      }
   }
}