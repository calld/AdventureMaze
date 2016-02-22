/* Passage class
*  By: Devon Call
*/

package ways.structure;

import java.util.List;

public class PassageObj extends ElementObj implements Passage{
   private Location destination;
   
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
   
   private String _dest;
   
   public PassageObj(String name, String desc, List<String> notes, String dest) throws Exception{
      super(name, desc, notes);
	  _dest = dest;
   }
   
   public void init(WorldState ws){
	   super.init(ws);
	   if(_dest != null){
		   if(ws.getElement(_dest) instanceof Location){
			   destination = (Location) ws.getElement(_dest);
		   }
		   _dest = null;
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