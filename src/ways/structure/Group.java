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
}