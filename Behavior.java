package thingsim;
public interface Behavior {
  public Coords getMove(Room room, Thing me);
}