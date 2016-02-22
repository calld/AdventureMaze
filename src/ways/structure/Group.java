/* Group Class
*  By: Devon Call
*/

package ways.structure;
import java.util.List;
import java.util.ArrayList;

public class Group extends ElementObj implements Allied{
   private Faction fac;
   private List<ways.structure.Character> members;
   private Location position;
   
   public static Group getNewGroup(String name, String desc){
      if(getElement(name) != null){return null;}
      return new Group(name, desc);
   }
   
   protected Group(String name, String desc){
      super(name, desc);
      members = new ArrayList<ways.structure.Character>();
   }
   
   public Faction getFaction(){
      return fac;
   }
   
   public void setFaction(Faction f){
      fac = f;
   }
   
   public List<ways.structure.Character> getMembers(){
      return new ArrayList<ways.structure.Character>(members);
   }
   
   public Group addMember(ways.structure.Character ch){
      members.add(ch);
      ch.setGroup(this);
      return this;
   }
   
   public boolean removeMember(ways.structure.Character ch){
      ch.setGroup(null);
      return members.remove(ch);
   }
   
   public int getTotalExp(){
      int total = 0;
      for(ways.structure.Character ch: members){
         total += ch.getExp();
      }
      return total;
   }
   
   public Location getLocation(){
      return position;
   }
   
   protected void setLocation(Location l){
      position = l;
   }
   
   public String getLongDesc(){
      StringBuilder sb = new StringBuilder();
      sb.append(super.getLongDesc());
      if(position != null){
         sb.append("Location: ");
         sb.append(position.getName());
         sb.append("\n");
      }
      if(fac != null){
         sb.append("Employed By: ");
         sb.append(fac.getName());
         sb.append("\n");
      }
      sb.append("Members: ");
      for(Character c: members){
         sb.append(c.getName());
         sb.append(", ");
      }
      if(members.size() > 0){sb.delete(sb.length() - 2, sb.length());}
      sb.append("\n");
      return sb.toString();
   }
   
   //load and save
   
   private String _fac;
   private List<String> _chars;
   private String _loc;
   
   protected Group(String name, String desc, List<String> notes, String faction, List<String> characters, String loc) throws Exception{
	   super(name, desc, notes);
	   members = new ArrayList<ways.structure.Character>();
	   _fac = faction;
	   _chars = characters;
	   _loc = loc;
   }
   
   public void init(WorldState ws){
	   super.init(ws);
	   if(_fac != null && _chars != null && _loc != null){
		   if(ws.getElement(_fac) instanceof Faction){
			   fac = (Faction) ws.getElement(_fac);
		   }
		   for(String name: _chars){
			   if(ws.getElement(name) instanceof ways.structure.Character){
				   members.add((ways.structure.Character) ws.getElement(name));
			   }
		   }
		   if(ws.getElement(_loc) instanceof Location){
			   position = (Location) ws.getElement(_loc);
		   }
		   _fac = null;
		   _chars = null;
		   _loc = null;
	   }
   }
   
   protected StringBuilder getSaveFields(){
	   StringBuilder sb = super.getSaveFields();
	   sb.append("<faction>");
	   sb.append(fac.getName());
	   sb.append("</faction>\n");
	   sb.append("<members>");
	   for(ways.structure.Character mem: members){
		   sb.append("<member>");
		   sb.append(mem.getName());
		   sb.append("</member>\n");
	   }
	   sb.append("</members>\n");
	   sb.append("<location>");
	   sb.append(position.getName());
	   sb.append("</location>\n");
	   return sb;
   }
}