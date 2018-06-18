// Create some standard kinds of cats/mice/bricks/holes
package thingsim;
public class Standard {
  public static Hole hole(int row, int col){
    Hole c = new Hole();
    c.position = new Coords(row,col);
    return c;
  }
  public static Brick brick(int row, int col){
    Brick c = new Brick();
    c.position = new Coords(row,col);
    return c;
  }
  // Looks for a mouse
  public static Cat cat(int row, int col){
    Cat c = new Cat();
    c.position = new Coords(row,col);
    c.behavior = new RowColSearch("mouse");
    return c;
  }
  // Looks for a hole
  public static Mouse mouse(int row, int col){
    Mouse m = new Mouse();
    m.position = new Coords(row,col);
    m.behavior = new RowColSearch("hole");
    return m;
  }
  // Doesn't move
  public static Mouse stillMouse(int row, int col){
    Mouse m = new Mouse();
    m.position = new Coords(row,col);
    m.behavior = new StayStill();
    return m;
  }
  // Always in one direction
  public static Mouse alwaysMouse(int row, int col,
				  String dir){
    Mouse m = new Mouse();
    m.position = new Coords(row,col);
    m.behavior = new Always(dir);
    return m;
  }
  // Always in one direction
  public static Cat alwaysCat(int row, int col,
			      String dir){
    Cat c = new Cat();
    c.position = new Coords(row,col);
    c.behavior = new Always(dir);
    return c;
  }
}