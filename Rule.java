// An abstract class representing game logic.  Rules override the
// predicate and apply methods
package thingsim;
import java.util.ArrayList;
public abstract class Rule {
	public final String description; // A documentation string for the rule
	public final int priority;    // High priority rules override others
  // Simple public constructor
  	public Rule(int p, String s){ 
  		this.description = s; 
  		this.priority = p;
  	}
  // Override this method to indicate when the rule applies to
  // interactions
  	abstract public boolean predicate(Thing x, String action, Thing y);
  // Override this rule to show how the rule takes effect
  	abstract public void apply(Thing x, Thing y);
  
  // Apply rules by priority: if x performs action x which can affect
  // others, find the highest priority rules and apply them, ignore
  // others
  public static void apply(Rule [] rules, Thing x, String a, Thing others[]){
 	 	 int bestPriority = Integer.MIN_VALUE;
    // Iterate through all other-thing and rule pairs
    for(Thing y : others){
      for(Rule r : rules){
        if(x != y &&
           r.predicate(x,a,y) && 
           r.priority > bestPriority){
          // New best rule
          bestPriority = r.priority;
        }
      }
    }
    // Again, but apply proper rules this time
    for(Thing y : others){
      for(Rule r : rules){
        if(x != y &&
           r.predicate(x,a,y) && 
           r.priority == bestPriority){
          r.apply(x,y);
        }
      }
    }
  }
  
}