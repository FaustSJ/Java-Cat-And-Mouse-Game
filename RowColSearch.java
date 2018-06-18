package thingsim;
public class RowColSearch implements Behavior{
	public int targetId;
	public String targetName;
	
	//constructor
	public RowColSearch(String targetName){
		this.targetId = -1;
		this.targetName = targetName;
	}
	
	//gets the closest target
	private void selectTarget(Room room, Thing me){
		int aim = 1000;	//tracks the lowest distance
		int dist=-1;	//tracks distance
		
	//runs through every thing in room
		for(int i=0; i<room.thingCount(); i++){
	//grabs the thing at that index
			Thing tempo = room.getThingByIndex(i);
	//measures distance between ma and another thing
			dist = me.position.distance(tempo.position);		
	//checks if it's the lowest distance and if the names match
			if ((dist<aim)&&
			   (tempo.name.equals(this.targetName))){
				aim=dist;
				this.targetId = tempo.id;
			}
		}
	}
	
	//the thing gets its next move
	public Coords getMove(Room room, Thing me){
	//has a target been selected?
		if (this.targetId==-1){
			selectTarget(room, me);
			if (this.targetId==-1){
				me.say("I don't know where to go!");
				return me.position;
			}
		}
	//if yes, move toward the target
		Thing target = room.getThingById(this.targetId);
	
	//if their rows don't match, then it tries to match the rows
		if (me.position.r!=target.position.r){
	//thing me moves up or down a row towards the target
			if (me.position.r>target.position.r){
				Coords newp = new Coords(me.position.r-1, me.position.c);
				return newp;
			}
			if (me.position.r<target.position.r){
				Coords newp = new Coords(me.position.r+1, me.position.c);
				return newp;
			}
		}
	//if their rows do match, then it tries to match the columns
		if (me.position.r==target.position.r){
	//thing me moves left or right towards the target
			if (me.position.c>target.position.c){
				Coords newp = new Coords(me.position.r, me.position.c-1);
				return newp;
			}
			if (me.position.c<target.position.c){
				Coords newp = new Coords(me.position.r, me.position.c+1);
				return newp;
			}
		}
	//if nothing happens, thing me stays in place
		return me.position;
	}
}









