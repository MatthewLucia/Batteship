/**
 * Move class. Represents a move that
 * a player can make in battleship
 */

public class Move
{
   // Fields
   private int row, col;
   
   /*
   *  Constructor - accepts two integer arguments 
   *  representing the indicies in a two-dimensional array
   *  @param row the row
   *  @param col the column
   */
   public Move(int row, int col)
   {
      this.row = row;
      this.col = col;
   }
   
   /*
   *  Constructor - accepts a string consisting of a 
   *  letter and number and creates a move object
   *  @param str the move
   */
   public Move(String str)
   {
      try {
         this.row = str.toUpperCase().charAt(0) - 65;
         if (str.length() == 3) {
            this.col = 57;
            }
         else {
            this.col = (int)str.charAt(1) - 1;
            }
       }
       catch (Exception e)
       {
         System.out.println("Error: Invalid move. System Exiting");
         System.exit(0);
       }
   }
   
   /*
   *  row method accessor for row value
   *  @return the row
   */
   public int row()
   {
      return row;
   }
   
   /*
   *  col method accessor for column value
   *  @return the column
   */
   public int col()
   {
      return col;
   }
   
   @Override
   public String toString()
   {
      return (char)(row + 65) +  Integer.toString(col - 47);
   }
   
}