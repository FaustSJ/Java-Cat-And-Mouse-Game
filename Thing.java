package thingsim;
import java.io.*; //for PrintWriter and its Exceptions
public class Thing{

	
	public String name;		//full name
	public String shortName;	//abbrev. name
	public final int id;		//thing's id
	public Coords position;		//current position of thing
	public Behavior behavior;	//determines where thing moves
	public Status status;		//active, dead, inert, or safe
	public static int totalThings;	//tracks number of thing objects
	public static PrintWriter log;	//used by say and tosay to post messages
	
	//constructor for subclasses
	public Thing(String name, String shortName){
		this();	// calls other constructor
		this.name=name;
		this.shortName=shortName;
	}
	
	//constructor for this class
	//gives thing a unique id and increases totalThings
	private Thing(){
		this.id = totalThings;
		totalThings++;
	}
	
	//assigns static log to p so thing can start posting
	public static void startLogging(PrintWriter p){
		log = p;
	}
	
	//sets p to null, stops messages from printing
	public static void stopLogging(){
		log = null;
	}
	
	//uses behavior to determine things moves
	public Coords getMove(Room r){
		return behavior.getMove(r, this);
	}
	
	//thing says/prints messages from CatMouseRules
	public void say(String msg){
		log.println(this.id+" "+this.name+": "+msg);
	}
	
	//thing says message to other
	public void sayTo(Thing other, String msg){
		log.println(this.id+" "+this.name+" to "+other.id+" "+
			other.name+": "+msg);
	}
	
	//checks if thing's position matches c
	public boolean occupies(Coords c){
		return c.equals(this.position);
	}
	
	//checks if thing is inside room bounds
	public boolean inBounds(Coords c, Room room){
		return room.inside(c);
	}
	
	//returns message based off on thing's status
	public String toString(){
		//if thing is dead
		if (this.status.equals(Status.DEAD)){ return "R.I.P."; }
		//if thing is safe
		if (this.status.equals(Status.SAFE)){ return this.shortName+" ok"; }
		//if thing has any other status
		return this.shortName;
	}
}