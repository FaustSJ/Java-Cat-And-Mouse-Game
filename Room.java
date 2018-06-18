package thingsim;
import java.util.ArrayList;

public class Room {
  private Thing [] things;
  public final int rows;
  public final int cols;

  // Not a deep copy, can't re-use Thing array
  public Room(int r, int c, Thing t[]){
    this.rows = r;
    this.cols = c;
    this.things = t;
  }
  
  // Is x inside the room?
  public boolean inside(Coords x){
    return
      x.r >= 0 && x.c >= 0
      && x.r < this.rows && x.c < this.cols;
  }

  // How many Things are in the room
  public int thingCount(){
    return this.things.length;
  }

  // Retrieve the thing at index i of the rooms collection
  public Thing getThingByIndex(int i){
    return this.things[i];
  }

  // Retrieve the Thing with unique ID id
  public Thing getThingById(int id){
    for(Thing t : this.things){
      if(t.id == id){
	return t;
      }
    }
    System.err.printf("Warning: could not find Thing with id %d\n",
		      id);
    return null;
  }

  // Retrieve all things that occupy coordinate c
  public Thing [] thingsAt(Coords c){
    ArrayList<Thing> there = new ArrayList<Thing>();
    for(Thing t : this.things){
      if(t.occupies(c)){
	there.add(t);
      }
    }
    Thing [] ans = there.toArray(new Thing[0]);
    return ans;
  }

  // true if there are any ACTIVE things in the room, false otherwise
  public boolean anyActive(){
    for(Thing t : things){
      if(t.status == Status.ACTIVE){
	return true;
      }
    }
    return false;
  }
  
  // Convert the room to a string representation for printing.
  public String toString(){
    String mat[][] = new String[this.rows][this.cols];
    for(int i=0; i<this.rows; i++){
      for(int j=0; j<this.cols; j++){
        // mat[i][j] = "---   ";
        mat[i][j] = "   ---";
      }
    }
    // May overwrite safe mice with holes which sucks, but oh well,
    // put mice after holes in the things list
    for(Thing t : this.things){
      // String s = String.format("%3s%-3d",t.toString(),t.id);
      String s = String.format("%3d%-3s",t.id,t.toString());
      mat[t.position.r][t.position.c] = s;
    }

    // StringBuilder would be better but oh well
    String s = "";
    for(int i=0; i<this.rows; i++){
      for(int j=0; j<this.cols; j++){
	s += mat[i][j];
      }
      s += "\n";
    }
    return s;
  }

}