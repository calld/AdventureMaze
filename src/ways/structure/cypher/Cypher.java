/* Cypher system cypher item
*  By: Devon Call
*/

package ways.structure.cypher;

import ways.structure.BasicItem;

public class Cypher extends BasicItem{
   private int level;
   private CypherType type;
   
   public static Cypher getNewCypher(String name, String desc, int level, CypherType type){
      if(getElement(name) != null){return null;}
      return new Cypher(name, desc, level, type);
   }
   
   protected Cypher(String name, String desc, int level, CypherType type){
      super(name, desc, 0.0, 0.0);
      this.level = level;
      this.type = type;
   }
   
   public int getLevel(){
      return level;
   }
   
   public CypherType getType(){
      return type;
   }
}