/* ContentGenerator for passages
*  By: Devon Call
*/

package ways.generator;

import ways.structure.Location;

public class SimplePassageGenerator implements ContentGenerator{
   private long count;
   private boolean valid;
   private String name;
   private String desc;
   
   
   public SimplePassageGenerator(){
      count = 0;
      valid = false;
   }
   
   public boolean newContent(Object... args) throws UnsupportedOperationException{
      Location loc;
      if(args[0] instanceof Location){
         loc = (Location) args[0];
      }else{
         valid = false;
         throw new UnsupportedOperationException("Passage Generator requires Location as the parameter");
      }
      
      count++;
      name = "Passage " + count;
      desc = "Leads to " + loc.getName();
      valid = true;
      return valid;  
   }
   
   public String getName() throws UnsupportedOperationException{
      if (valid){
         return name;
      }else{
         throw new UnsupportedOperationException("no Content available, .newContent() either has not been called or returned false");
      }
   }
   
   public String getDesc() throws UnsupportedOperationException{
      if (valid){
         return desc;
      }else{
         throw new UnsupportedOperationException("no Content available, .newContent() either has not been called or returned false");
      }
   }
}