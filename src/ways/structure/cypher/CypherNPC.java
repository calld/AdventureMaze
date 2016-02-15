/* Cypher NPC
*  By: Devon Call
*/

package ways.structure.cypher;

import ways.structure.Character;
import ways.structure.Faction;
import java.util.Set;
import java.util.HashSet;

public class CypherNPC extends Character{
   private int level;
   private int health;
   private int armor;
   private int damage;
   private Distance movement;
   private int difficulty;
   //private Set<String> homeTerrain;
   /*
   difficulty is suppose to serve as a rough estimate of how dangerous an NPC is to the group of PC's. 
   A standard difficulty for an NPC would be (level + health/3 + damage)/3 + armor.
   if an NPC is more dangerous for reasons other than level, health, damage, or armor, 
   */
   
   public static CypherNPC getNewCypherNPC(String name, String desc, Faction fac, int level, int health, int armor, int damage, Distance movement, int difficulty){
      if(getElement(name) != null){return null;}
      return new CypherNPC(name, desc, fac, level, health, armor, damage, movement, difficulty);
   }
   
   protected CypherNPC(String name, String desc, Faction fac, int level, int health, int armor, int damage, Distance movement, int difficulty){
      super(name, desc, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, fac);
      this.level = level;
      this.health = health;
      this.armor = armor;
      this.damage = damage;
      this.movement = movement;
      this.difficulty = difficulty;
      //homeTerrain = new HashSet();
   }
   
   /*
   public void addTerrain(String t){
      homeTerrain.add(t);
   }
   
   public Set<String> getTerrain(){
      return new HashSet(homeTerrain);
   }
   */
   public int getExp(){
      return 0;
   }
   
   public int getLvL(){
      return level;
   }
   public int getHealth(){
      return health;
   }
   public int getArmor(){
      return armor;
   }   
   public int getDamage(){
      return damage;
   }   
   public Distance getMovement(){
      return movement;
   }   
   public int getDifficulty(){
      return difficulty;
   }
   
   public String getLongDesc(){
      StringBuilder sb = new StringBuilder();
      sb.append(super.getLongDesc());
      sb.append("Level: ");
      sb.append(level);
      sb.append("\n");
      sb.append("Health: ");
      sb.append(health);
      sb.append("\n");
      sb.append("Damage: ");
      sb.append(damage);
      sb.append("\n");
      sb.append("Armor: ");
      sb.append(armor);
      sb.append("\n");
      if(movement != null){
         sb.append("Movement: ");
         sb.append(movement);
         sb.append("\n");
      }
      sb.append("Difficulty: ");
      sb.append(difficulty);
      sb.append("\n");
      
      return sb.toString();
   }
   
   private static int copycount = 0;
   
   public CypherNPC copy(){
      copycount++;
      return getNewCypherNPC(this.getName() + "(" + copycount + ")", this.getDesc(), this.getFaction(), level, health, armor, damage, movement, difficulty);
   }
}