// Behavior where current positionis always returned
package thingsim;
public class StayStill implements Behavior {
  public StayStill(){ }
  public Coords getMove(Room room, Thing me){
    Coords curr = me.position;
    me.say("I'm staying here");
    return curr;
  }
}