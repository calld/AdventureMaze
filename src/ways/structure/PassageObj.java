/* Passage class
*  By: Devon Call
*/

package ways.structure;

import java.util.List;

public class PassageObj extends ElementObj implements Passage{
   private Location destination;
   
   public static PassageObj getNewPassage(String name, String desc, Location dest){
      if(getElement(name) != null){return null;}
      return new PassageObj(name, desc, dest);
   }
   
   protected PassageObj(String name, String desc, Location dest){
      super(name, desc);
      destination = dest;
   }
   
   public Location getDestination(){
      return destination;
   }
   
   public void setDestination(Location l){
      destination = l;
   }
   
   public String getShortDesc(){
      return "to " + destination.getName();
   }
   
   //save/load functions
   
   public PassageObj(String name, String desc, List<String> notes, String dest) throws Exception{
      super(name, desc, notes);
      Element temp = getElement(dest);
      if(temp instanceof Location){
         destination = (Location) temp;
      }else{
         throw new Exception("destination not found, " + dest);
      }
   }
   
   protected StringBuilder getSaveFields(){
      StringBuilder sb = super.getSaveFields();
      sb.append("<destination>");
      sb.append(destination.getName());
      sb.append("</destination>\n");
      return sb;
   }
}