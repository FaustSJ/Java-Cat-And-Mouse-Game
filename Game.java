// Abstraction of running a game
package thingsim;

import java.io.*;
public class Game {
  // For logging game events
  public PrintWriter gamelog;
  public Room room;
  public Rule rules[];

  // Do a single move on a thing
  public void doMove(Thing t){
    if(t.status != Status.ACTIVE){
      return;
    }
    this.gamelog.printf("%d %s's turn\n",t.id,t.name);
    Coords curr = t.position;
    Coords next = t.getMove(this.room);
    if(curr.distance(next) > 1){ // Ensure not moving too far, better in Rules?
      String msg = 
	String.format("I tried to move too far: %s to %s",
		      curr,next);
      t.say(msg);
    } else if(!t.inBounds(next,this.room)){ // Ensure doesn't leave board
      String msg = 
	String.format("%s is outside the room!",
		      next);
      t.say(msg);
    } else {   
      // Figure out what other things are at the desired position and
      // then what rules to apply
      Thing [] others = this.room.thingsAt(next);
      if(others.length == 0){ // Nothing else is there
	String msg = 
	  String.format("Going from %s to %s",curr,next);
	t.say(msg);
	t.position = next;
      } else {   // Something is there, apply rules
	Rule.apply(this.rules, t, "move to", others);
      }
    }
    this.gamelog.println(this.room.toString());
  }

  // Iterate through all things and do their moves
  public void doRound(){
    for(int i=0; i<this.room.thingCount(); i++){
      Thing t = this.room.getThingByIndex(i);
      this.doMove(t);
    }
  }

  // Play a game
  public void play(int maxRounds){
    int round = 1;
    this.gamelog.printf("Playing %d rounds\n\n",maxRounds);
    this.gamelog.printf("Initial\n\n%s\n\n",this.room);
    while(round <= maxRounds && this.room.anyActive()){
      this.gamelog.printf("======================\n"+
		     "Round %d\n"+
		     "======================\n",round);
      this.doRound();
      round++;
    }
    if(round > maxRounds){
      this.gamelog.printf("Max rounds reached\n");
    }
    else{
      this.gamelog.printf("All things at rest\n");
    }
    this.gamelog.flush();
  }

}