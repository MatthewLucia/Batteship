/**
 * Fleet class. Represents the group of ships
 * that a player has in a game of battleship.
 * contains all 5 ship types
 */

public class Fleet
{
   // Fields
   public Ship battleship, aircraftCarrier, cruiser, sub, destroyer;
   
   /**
   *  Class Constructor sets ship fields
   */
   public Fleet()
   {
      this.battleship = new Battleship();
      this.aircraftCarrier = new AircraftCarrier();
      this.cruiser = new Cruiser();
      this.sub = new Sub();
      this.destroyer = new Destroyer();
   }
   
   /*
   *  updateFleet method informs the appropriate ship that
   *  it has been hit and returns true if sank, false otherwise
   *  @param shipType the ship 
   *  @return boolean true if sunk, false otherwise
   */
   public boolean updateFleet(Ship shipType)
   {
      return shipType.hit();   
   }
   
   /*
   *  gameOver method returns true if all ships have been
   *  sunk, false otherwise
   *  @return boolean true if all ships sunk, false otherwise
   */
   public boolean gameOver()
   {
      return battleship.getSunk() && aircraftCarrier.getSunk() &&
               cruiser.getSunk() && destroyer.getSunk() && sub.getSunk();
   }
}