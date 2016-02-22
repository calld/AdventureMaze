/* Character abstract object
*  By: Devon Call
*/

package ways.structure;

public abstract class Character extends ContainerObj implements Allied{
   private Faction faction;
   private Group group;
   
   protected Character(String name, String desc, double sizelimit, double weightlimit){
      super(name, desc, sizelimit, weightlimit);
	  this.faction = null;
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
   
   //load and save
   
   private String _fac;
   private String _grp;
   
   protected Character(String name, String desc, List<String> notes, List<String> contents, String sizel, String weightl, String faction, String group){
	   super(name, desc, notes, contents, sizel, weightl);
	   _fac = faction;
	   _grp = group;
   }
   
   public void init(WorldState ws){
	   super.init(ws);
	   if(_fac != null && _grp != null){
		   if(ws.getElement(_fac) instanceof Faction){
			   faction = (Faction) ws.getElement(_fac);
		   }
		   if(ws.getElement(_grp) instanceof Group){
			   group = (Group) ws.getElement(_grp);
		   }
		   _fac = null;
		   _grp = null;
	   }
   }
   
   protected StringBuilder getSaveFields(){
	   StringBuilder sb = super.getSaveFields();
	   sb.append("<faction>");
	   sb.append(faction.getName());
	   sb.append("</faction>\n");
	   sb.append("<group>");
	   sb.append(group);
	   sb.append("</group>\n");
	   return sb;
   }
} 