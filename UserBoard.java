import java.util.ArrayList;
import java.util.Random;
import java.io.*;

/**
 * UserBoard class. Represents the user's
 * board in a game of battleship.
 */

public class UserBoard extends Board
{
   // Fields
   ArrayList<Move> moves; 
   Random rand;
   Move move;
   
   // Loop control variables
   boolean aSunk, bSunk, cSunk, dSunk, sSunk = false; 
   
   /**
   *  Class constructor accepts a String input of a File name  
   *  and passes it to the superclass. Initializes the Random
   *  object and the ArrayList of all possible moves
   *  @param String filename the filename of the user's board
   */
   public UserBoard(String filename) throws IOException
   {
      super(filename);
      this.moves = new ArrayList<Move>() {
         {
            for (int i = 97; i <= 107; i++)
            {
               char ch = (char)i;
               for (int k = 1; k <= 10; k++)
               {
                  add(new Move(ch + Integer.toString(k)));
               }
            }
         }
      };
      this.rand = new Random();
   }
   
   @Override
   public String toString()
   {
      String str = "\nUSER\n  1 2 3 4 5 6 7 8 9 10";
      int count = 1;
      for (ArrayList<CellStatus> subArray : super.getLayout())
      {
         char rowLetter = (char)(64 + count);
         str += "\n" + rowLetter;
         
         for (CellStatus item : subArray)
         {
            str += " " + item.toString().charAt(1);
         }
         
         count ++;
      } 
      
      return str += "\n";
   }
   
   /**
   *  makeComputerMove method
   *  @param move the move that is being made
   */
   public ArrayList<String> makeComputerMove()
   {
      boolean complete = false;
      while(!complete)
      {
         int index = rand.nextInt(99);
         move = moves.get(index);
         if (super.moveAvailable(move) == true)
         {
            super.applyMoveToLayout(move);
            complete = true;
         }
      }
      
      ArrayList<String> result = new ArrayList<String>();
      result.add(move.toString());

      if (super.getFleet().battleship.getSunk() && !bSunk)
      {
         result.add("\nOh no! The computer sunk your battleship!");
         for (int i = 0; i < 10; i++)
         {
            for (int k = 0; k < 10; k++)
            {
               if (super.getLayout().get(i).get(k) == CellStatus.BATTLESHIP_HIT)
               {
                   super.getLayout().get(i).remove(k);
                   super.getLayout().get(i).add(k, CellStatus.BATTLESHIP_SUNK);
               }
            }
         }
         this.bSunk = true; 
      }
      if (super.getFleet().aircraftCarrier.getSunk() && !aSunk)
      {
         result.add("\nOh no! the computer sunk your aircraft carrier!");
          for (int i = 0; i < 10; i++)
         {
            for (int k = 0; k < 10; k++)
            {
               if (super.getLayout().get(i).get(k) == CellStatus.AIRCRAFT_CARRIER_HIT)
               {
                   super.getLayout().get(i).remove(k);
                   super.getLayout().get(i).add(k, CellStatus.AIRCRAFT_CARRIER_SUNK);
               }
            }
         }
         this.aSunk = true;
      }

      if (super.getFleet().destroyer.getSunk() && !dSunk)
      {
         result.add("\nOh no! The computer sunk your destroyer!");
          for (int i = 0; i < 10; i++)
         {
            for (int k = 0; k < 10; k++)
            {
               if (super.getLayout().get(i).get(k) == CellStatus.DESTROYER_HIT)
               {
                   super.getLayout().get(i).remove(k);
                   super.getLayout().get(i).add(k, CellStatus.DESTROYER_SUNK);
               }
            }
         }
         this.dSunk = true;
      }

      if (super.getFleet().sub.getSunk() && !sSunk)
      {
         result.add("\nOh no! The computer sunk your sub!");
         for (ArrayList<CellStatus> item : super.getLayout())
          for (int i = 0; i < 10; i++)
         {
            for (int k = 0; k < 10; k++)
            {
               if (super.getLayout().get(i).get(k) == CellStatus.SUB_HIT)
               {
                   super.getLayout().get(i).remove(k);
                   super.getLayout().get(i).add(k, CellStatus.SUB_SUNK);
               }
            }
         }
         this.sSunk = true;
      }

      if (super.getFleet().cruiser.getSunk() && !cSunk)
      {
         result.add("\nOh no! The computer sunk your cruiser!");
         for (ArrayList<CellStatus> item : super.getLayout())
          for (int i = 0; i < 10; i++)
         {
            for (int k = 0; k < 10; k++)
            {
               if (super.getLayout().get(i).get(k) == CellStatus.CRUISER_HIT)
               {
                   super.getLayout().get(i).remove(k);
                   super.getLayout().get(i).add(k, CellStatus.CRUISER_SUNK);
               }
            }
         }
         this.cSunk = true;
      }
      return result;
   }
}