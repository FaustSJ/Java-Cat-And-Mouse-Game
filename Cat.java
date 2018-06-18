package thingsim;
public class Cat extends Thing{
  public int kills = 0;
  public Cat(){
    super("cat","C");
    this.status = Status.ACTIVE;
  }

}