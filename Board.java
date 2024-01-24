import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

/**
 * Board Class. Represents a 10 x 10 board for playing 
 * battleship. 
 */

public class Board
{
   // Class Constant
   public static final int SIZE = 10;
   
   // Instance Variables
   private ArrayList<ArrayList<CellStatus>> layout = new ArrayList<ArrayList<CellStatus>>(SIZE);
   private ArrayList<CellStatus> rowLayout;
   private ArrayList<ArrayList<CellStatus>> startingLayout = new ArrayList<ArrayList<CellStatus>>();
   private Fleet fleet;
   
   /**
   *  Class Constructor accepts a String argument and 
   *  initializes the layout of the board. Gets information
   *  from file and add ships to the layout. Initializes Fleet.
   *  @param filename the filename of the board
   */
   public Board(String filename) throws IOException
   {
      // Initialize the file
      File file = new File(filename);
      
      // Create an object of the Scanner class
      Scanner inputFile = new Scanner(file);
    
      // Initialize the ArrayList
      ArrayList<CellStatus> r1 = new ArrayList<CellStatus>(SIZE);
      ArrayList<CellStatus> r2 = new ArrayList<CellStatus>(SIZE);
      ArrayList<CellStatus> r3 = new ArrayList<CellStatus>(SIZE);
      ArrayList<CellStatus> r4 = new ArrayList<CellStatus>(SIZE);
      ArrayList<CellStatus> r5 = new ArrayList<CellStatus>(SIZE);
      ArrayList<CellStatus> r6 = new ArrayList<CellStatus>(SIZE);
      ArrayList<CellStatus> r7 = new ArrayList<CellStatus>(SIZE);
      ArrayList<CellStatus> r8 = new ArrayList<CellStatus>(SIZE);
      ArrayList<CellStatus> r9 = new ArrayList<CellStatus>(SIZE);
      ArrayList<CellStatus> r10 = new ArrayList<CellStatus>(SIZE);
      layout.add(r1);
      layout.add(r2);
      layout.add(r3);
      layout.add(r4);
      layout.add(r5);
      layout.add(r6);
      layout.add(r7);
      layout.add(r8);
      layout.add(r9);
      layout.add(r10);
      
      // Set all cells to NOTHING   
      for (int i = 0; i < 10; i++)
      {
         for (int j = 0; j < 10; j++)
         {
            layout.get(i).add(j, CellStatus.NOTHING);
         }
      }

      // Place ships from file onto the board
      while (inputFile.hasNext())
      {
         char shipType = inputFile.next().charAt(0);
         CellStatus shipCells;
         if (shipType == 'A') {shipCells = CellStatus.AIRCRAFT_CARRIER;}
         else if (shipType == 'B') {shipCells = CellStatus.BATTLESHIP;}
         else if (shipType == 'C') {shipCells = CellStatus.CRUISER;}
         else if (shipType == 'S') {shipCells = CellStatus.SUB;}
         else {shipCells = CellStatus.DESTROYER;}
         String startPoint = inputFile.next();
         String endPoint = inputFile.next();
         if (endPoint.length() == 3)
         {
            if (startPoint.length() == endPoint.length())
            {
               int startValue = (int)startPoint.charAt(0) - 65;
               int endValue = (int)endPoint.charAt(0) - 65;
               for (int l = startValue; l <= endValue; l++)
               {
                  layout.get(l).remove((int)startPoint.charAt(1) - 49);
                  layout.get(l).add((int)startPoint.charAt(1) - 49, shipCells);
               }
            }
            else
            {
               int startNum = (int)startPoint.charAt(1);
               for (int k = startNum - 49; k < 10; k++)
               {
                  rowLayout = layout.get(((int)startPoint.charAt(0) - 65));
                  rowLayout.remove(k);
                  rowLayout.add(k, shipCells);
               }
            }
         }
         else if (startPoint.charAt(0) == endPoint.charAt(0))
         {
            int startNum = (int)startPoint.charAt(1);
            int endNum = (int)endPoint.charAt(1);
            for (int k = startNum - 49; k <= endNum - 49; k++)
            {
               rowLayout = layout.get(((int)startPoint.charAt(0) - 65));
               rowLayout.remove(k);
               rowLayout.add(k, shipCells);
            }
         }
         else
         {
            int startValue = (int)startPoint.charAt(0) - 65;
            int endValue = (int)endPoint.charAt(0) - 65;
            for (int l = startValue; l <= endValue; l++)
            {
               layout.get(l).remove((int)startPoint.charAt(1) - 49);
               layout.get(l).add((int)startPoint.charAt(1) - 49, shipCells);
            }
         }
         
         this.startingLayout = layout;
         
         // Initialize Fleet
         this.fleet = new Fleet();
      }
   }
   
   /*
   *  applyMoveToLayout method accepts a move as an 
   *  argument and applies the move to the board
   *  @param move the move
   */
   public void applyMoveToLayout(Move move)
   {
      CellStatus targetCell = layout.get(move.row()).get(move.col() - 48);
      if (targetCell == CellStatus.NOTHING)
      {
         layout.get(move.row()).remove(move.col() - 48);
         layout.get(move.row()).add(move.col() - 48, CellStatus.NOTHING_HIT);
      }
      else if (targetCell == CellStatus.BATTLESHIP)
      {
         layout.get(move.row()).remove(move.col() - 48);
         layout.get(move.row()).add(move.col() - 48, CellStatus.BATTLESHIP_HIT);
         fleet.updateFleet(fleet.battleship);
      }
      else if (targetCell == CellStatus.DESTROYER)
      {
         layout.get(move.row()).remove(move.col() - 48);
         layout.get(move.row()).add(move.col() - 48, CellStatus.DESTROYER_HIT);
         fleet.updateFleet(fleet.destroyer);
      }
      else if (targetCell == CellStatus.SUB)
      {
         layout.get(move.row()).remove(move.col() - 48);
         layout.get(move.row()).add(move.col() - 48, CellStatus.SUB_HIT);
         fleet.updateFleet(fleet.sub);
      }
      else if (targetCell == CellStatus.CRUISER)
      {
         layout.get(move.row()).remove(move.col() - 48);
         layout.get(move.row()).add(move.col() - 48, CellStatus.CRUISER_HIT);
         fleet.updateFleet(fleet.cruiser);
      }
      else if (targetCell == CellStatus.AIRCRAFT_CARRIER)
      {
         layout.get(move.row()).remove(move.col() - 48);
         layout.get(move.row()).add(move.col() - 48, CellStatus.AIRCRAFT_CARRIER_HIT);
         fleet.updateFleet(fleet.aircraftCarrier);
      }
   }
   
   /*
   *  moveAvailable method takes a move as a paramter and 
   *  determines if the spot is available. Returns true if
   *  the spot is available and false otherwise
   *  @param move the move
   */
   public boolean moveAvailable(Move move)
   {
      CellStatus checkCell = layout.get(move.row()).get(move.col() - 48);
      CellStatus originalCell = startingLayout.get(move.row()).get(move.col() - 48);
      return checkCell == originalCell;
   }
   
   /*
   *  getLayout method accessor for board Layout
   *  @return layout of the board
   */
   public ArrayList<ArrayList<CellStatus>> getLayout() {
      return layout;
   }
   
   /*
   *  getFleet method accessor for fleet
   *  @return the fleet
   */
   public Fleet getFleet() {
      return fleet;
   }
   
}