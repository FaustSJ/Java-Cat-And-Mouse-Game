// Always is a kind of Behavior which always moves a Thing in the same direction like "up"
package thingsim;
public class Always implements Behavior{
	public String direction;
	
	//constructor
	public Always(String direction){
		this.direction = direction;
	}
	
	//gets coordinate of me, uses direction to get next coordinate move
	public Coords getMove(Room room, Thing me){
	//	Coords temp = new Coords(3,3);
	//	return temp;
	//if the thing always moves up
		if (this.direction.equals("up")){
			Coords temp = new Coords(me.position.r-1, me.position.c);
	//is its desired position within the room?
			if (room.inside(temp)==true){return temp;}
		}
			
	//if the thing always moves down
		if (this.direction.equals("down")){
			Coords temp = new Coords(me.position.r+1, me.position.c);
	//is its desired position within the room?
			if (room.inside(temp)==true){return temp;}
		}
			
	//if the thing always moves to the right
		if (this.direction.equals("right")){
			Coords temp = new Coords(me.position.r, me.position.c+1);
	//is its desired position within the room?
			if (room.inside(temp)==true){return temp;}
		}
			
	//if the thing always moves to the left
		if (this.direction.equals("left")){
			Coords temp = new Coords(me.position.r, me.position.c-1);
	//is its desired position within the room?
			if (room.inside(temp)==true){return temp;}
		}
			
		return me.position;
	}
}