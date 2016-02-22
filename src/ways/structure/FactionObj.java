/* Faction implementation
*  By: Devon Call
*/

package ways.structure;
import java.util.List;
import java.util.LinkedList;

public class FactionObj extends ElementObj implements Faction{
   private List<Faction> enemies;
   private List<Faction> allies;
   private Demeanor dem;
   
   public static FactionObj getNewFaction(String name, String desc, Demeanor dem){
      if(getElement(name) != null){return null;}
      return new FactionObj(name, desc, dem);
   }
   
   protected FactionObj(String name, String desc, Demeanor dem){
      super(name, desc);
      this.dem = dem;
      enemies = new LinkedList<Faction>();
      allies = new LinkedList<Faction>();
   }
   
   protected FactionObj(String name, String desc, Demeanor dem, List<Faction> enemies, List<Faction> allies) throws Exception{
      super(name, desc);
      for(Faction f: enemies){
         if (allies.contains(f)){
            throw new Exception("Faction cannot be the enemy and ally of the same faction");
         }
      }
      this.dem = dem;     
      this.enemies = new LinkedList<Faction>(enemies);
      this.allies = new LinkedList<Faction>(allies);
   }
   
   public List<Faction> getEnemies(){
      return new LinkedList<Faction>(enemies);
   }
   
   public List<Faction> getAllies(){
      return new LinkedList<Faction>(allies);
   }
   
   public Faction addEnemy(Faction f){
      allies.remove(f);
      enemies.add(f);
      return this;
   }
   
   public Faction addAlly(Faction f){
      enemies.remove(f);
      allies.add(f);
      return this;
   }
   
   public Faction removeEnemy(Faction f){
      enemies.remove(f);
      return this;
   }
   
   public Faction removeAlly(Faction f){
      allies.remove(f);
      return this;
   }
   
   public boolean isEnemy(Faction f){
      return enemies.contains(f);
   }
   
   public boolean isAlly(Faction f){
      return allies.contains(f);
   }
   
   public Demeanor getDemeanor(){
      return dem;
   }
   
   //load and save
   List<String> _allies;
   List<String> _enemies;
   
   protected FactionObj(String name, String desc, List<String> notes, String demen, List<String> enemies, List<String> allies) throws Exception{
	   super(name, desc, notes);
	   this.dem = Demeanor.valueOf(demen);
	   this.enemies = new LinkedList<Faction>();
       this.allies = new LinkedList<Faction>();
	   _allies = allies;
	   _enemies = enemies;
   }
   
   public void init(WorldState ws){
	   super.init(ws);
	   if(_allies != null && _enemies != null){
		   for(String ally: _allies){
			   if(ws.getElement(ally) instanceof Faction){
				   allies.add((Faction) ws.getElement(ally));
			   }
		   }
		   for(String enemy: _enemies){
			   if(ws.getElement(enemy) instanceof Faction){
				   enemies.add((Faction) ws.getElement(enemy));
			   }
		   }
		   _allies = null;
		   _enemies = null;
	   }
   }
   
   protected StringBuilder getSaveFields(){
	   StringBuilder sb = super.getSaveFields();
	   sb.append("<demeanor>");
	   sb.append(dem.toString());
	   sb.append("</demeanor>\n");
	   sb.append("<allies>");
	   for(Faction ally: allies){
		   sb.append("<ally>");
		   sb.append(ally.getName());
		   sb.append("</ally>\n");
	   }
	   sb.append("</allies>\n");
	   sb.append("<enemies>");
	   for(Faction enemy: enemies){
		   sb.append("<enemy>");
		   sb.append(enemy.getName());
		   sb.append("</enemy>\n");
	   }
	   sb.append("</enemies>\n"); 
	   return sb;
   }
}