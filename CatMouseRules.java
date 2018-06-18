// See section 9 an 9.2 of the spec to complete this file
package thingsim;
public class CatMouseRules{
  public static Rule rules[] = {
    
    new Rule(10,"Cat move to Hole, not allowed, high in priority to avoid killing safe mice"){
      public boolean predicate(Thing x, String action, Thing y){
        return
          x instanceof Cat &&
          y instanceof Hole &&
          action.equals("move to");
      }
      public void apply(Thing x, Thing y){
        x.say("Pesky hole is in my way");
        // position of cat is unchanged 
      }
    },
    
    new Rule(9,"Mouse move to Mouse, not allowed"){
      public boolean predicate(Thing x, String action, Thing y){
        return
          x instanceof Mouse &&
          y instanceof Mouse &&
          action.equals("move to");
      }
      public void apply(Thing x, Thing y){
	//x doesn't change its position
	//y complains to x
	y.sayTo(x, "This is my spot, find your own!");
      }
    },
    
    new Rule(8,"Mouse move to Hole, becomes safe"){
      public boolean predicate(Thing x, String action, Thing y){
     //checks if a mouse and a hole involved and is x moving to y
        return
        	x instanceof Mouse &&
        	y instanceof Hole &&
        	action.equals("move to");
      }
      public void apply(Thing x, Thing y){
        x.position = y.position;
        x.status = Status.SAFE;
        x.say("Safe at last");
      }
    },
    
    new Rule(7,"Cat move to Mouse, Mouse dies"){
      public boolean predicate(Thing x, String action, Thing y){
        return 
          x instanceof Cat && 
          y instanceof Mouse &&
          action.equals("move to");
      }
      public void apply(Thing x, Thing y){
        Cat c = (Cat) x;
        Mouse m = (Mouse) y;
        if(m.status == Status.ACTIVE){
        //mouse's last goodbye
          m.say("Farewell cruel world");
        //the cat kills the mouse
	  m.status = Status.DEAD;
	  c.kills++;
	//cat takes mouse's spot
	  c.position = m.position;
        }    
      }
    },
    
    new Rule(0,"Default things can't occupy the same space"){
      public boolean predicate(Thing x, String action, Thing y){
        return 
          x instanceof Thing && 
          y instanceof Thing &&
          action.equals("move to");
      }
      public void apply(Thing x, Thing y){
        x.say("I can't move there");
        y.say("Ha ha");
      }
    }    
  };
}