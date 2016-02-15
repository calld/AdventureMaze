/* Character abstract object
*  By: Devon Call
*/

package ways.structure;

public abstract class Character extends ContainerObj implements Allied{
   private Faction faction;
   private Group group;
   
   protected Character(String name, String desc, double sizelimit, double weightlimit, Faction faction){
      super(name, desc, sizelimit, weightlimit);
      this.faction = faction;
      this.group = null;
   }
   
   public abstract int getExp();
   
   public abstract int getLvL();
   
   public Faction getFaction(){
      return faction;
   }
   
   public void setFaction(Faction f){
      faction = f;
   }
   
   public Group getGroup(){
      return group;
   }
   
   public void setGroup(Group g){
      group = g;
   }
   
   public String getLongDesc(){
      StringBuilder sb = new StringBuilder();
      sb.append(super.getLongDesc());
      if(faction != null){
         sb.append("Member of: ");
         sb.append(faction.getName());
         sb.append("\n");
      }
      return sb.toString();
   }
   
   public Location getLocation(){
      if(group != null){
         return group.getLocation();
      }
      return null;
   }
} 