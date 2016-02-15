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
      return new LinkedList(enemies);
   }
   
   public List<Faction> getAllies(){
      return new LinkedList(allies);
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
}