import java.io.*;
import java.util.ArrayList;

/**
 * ComputerBoard class. Extends Board class. 
 * represents the computer's board in a 
 * game of battleship
 */

public class ComputerBoard extends Board
{
   // Loop control Variables
   boolean bSunk, aSunk, cSunk, dSunk, sSunk = false;
   
   /**
   *  Class constructor accepts a string of a file name and sets
   *  the computer's hand to what is in the file
   *  @param filename the filename for the computer's board
   */
   public ComputerBoard(String filename) throws IOException
   {
      super(filename);
   }
   
   /**
   *  makePlayerMove method
   *  @param move the move that is being made
   */
   public String makePlayerMove(Move move)
   {
      String result = "";
      if (super.moveAvailable(move) == true)
      {
         CellStatus cell = super.getLayout().get(move.row()).get(move.col() - 48);
         super.applyMoveToLayout(move);
      }
      else
      {
         System.out.println("Invalid Move. Turn Lost.");
      }
      if (super.getFleet().battleship.getSunk() && !bSunk)
      {
         result += "\nThe computer says: You sunk my battleship!";
         for (int i = 0; i < 10; i++)
         {
            for (int j = 0; j < 10; j++)
            {
               if (super.getLayout().get(i).get(j) == CellStatus.BATTLESHIP_HIT)
                  {
                     super.getLayout().get(i).remove(j);
                     super.getLayout().get(i).add(j, CellStatus.BATTLESHIP_SUNK);
                  }
            }
         }
         this.bSunk = true;
      }
      else if (super.getFleet().aircraftCarrier.getSunk() && !aSunk)
      {
         result += "\nThe computer says: You sunk my aircraft carrier!";
         for (int i = 0; i < 10; i++)
         {
            for (int j = 0; j < 10; j++)
            {
               if (super.getLayout().get(i).get(j) == CellStatus.AIRCRAFT_CARRIER_HIT)
                  {
                     super.getLayout().get(i).remove(j);
                     super.getLayout().get(i).add(j, CellStatus.AIRCRAFT_CARRIER_SUNK);
                  }
            }
         }
         this.aSunk = true;
      }
      else if (super.getFleet().destroyer.getSunk() && !dSunk)
      {
         result += "\nThe computer says: You sunk my destroyer!";
         for (int i = 0; i < 10; i++)
         {
            for (int j = 0; j < 10; j++)
            {
               if (super.getLayout().get(i).get(j) == CellStatus.DESTROYER_HIT)
                  {
                     super.getLayout().get(i).remove(j);
                     super.getLayout().get(i).add(j, CellStatus.DESTROYER_SUNK);
                  }
            }
         }
         this.dSunk = true;
      }
      else if (super.getFleet().sub.getSunk() && !sSunk)
      {
         result += "\nThe computer says: You sunk my sub!";
         for (int i = 0; i < 10; i++)
         {
            for (int j = 0; j < 10; j++)
            {
               if (super.getLayout().get(i).get(j) == CellStatus.SUB_HIT)
                  {
                     super.getLayout().get(i).remove(j);
                     super.getLayout().get(i).add(j, CellStatus.SUB_SUNK);
                  }
            }
         }
         this.sSunk = true;
      }
      else if (super.getFleet().cruiser.getSunk() && !cSunk)
      {
         result += "\nThe computer says: You sunk my cruiser!";
         for (int i = 0; i < 10; i++)
         {
            for (int j = 0; j < 10; j++)
            {
               if (super.getLayout().get(i).get(j) == CellStatus.CRUISER_HIT)
                  {
                     super.getLayout().get(i).remove(j);
                     super.getLayout().get(i).add(j, CellStatus.CRUISER_SUNK);
                  }
            }
         }
         this.cSunk = true;
      }
      return result;
   }
   
   @Override
   public String toString()
   {
      String str = "\nCOMPUTER\n  1 2 3 4 5 6 7 8 9 10";
      int count = 1;
      for (ArrayList<CellStatus> subArray : super.getLayout())
      {
         char rowLetter = (char)(64 + count);
         str += "\n" + rowLetter;
         
         for (CellStatus item : subArray)
         {
            str += " " + item.toString().charAt(0);
         }
         
         count ++;
      } 
      
      return str += "\n";
   }
}