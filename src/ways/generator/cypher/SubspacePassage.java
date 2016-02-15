/* node passage cypher system
*  By: Devon Call
*/

package ways.generator.cypher;

import ways.generator.ContentGeneratorObj;
import ways.generator.MapBuilder;
import java.util.Random;
import ways.structure.Location;
import java.util.LinkedList;

public class SubspacePassage extends ContentGeneratorObj{
   private Random ran;
   private int count = 0;
   //private int diff;
   private LinkedList<Integer> diff;
   
   public SubspacePassage(){
      valid = false;
      ran = new Random();
      diff = new LinkedList<Integer>();
   }
   
   public boolean newContent(Object... arguments) throws UnsupportedOperationException{
      Location dest;
      if(arguments[0] instanceof Location){
         dest = (Location) arguments[0];
      }else{
         valid = false;
         throw new UnsupportedOperationException("need to pass a location as destination: newContent(Location)");
      }
      count += 1;
      if(diff.size() <= 0){
         int t = 0;
         diff.add(t);
         t += MapBuilder.randomStep(ran);
         diff.add(t);
         t += MapBuilder.randomStep(ran);
         diff.add(t);
         t += MapBuilder.randomStep(ran);
         diff.add(t);
      }
      
      setName("Passage " + count + " (diff: " + diff.remove(ran.nextInt(diff.size())) + ")");
      setDesc("goes to " + dest.getName());
      valid = true;
      return valid;
   }
}