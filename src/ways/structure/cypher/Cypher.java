/* Cypher system cypher item
*  By: Devon Call
*/

package ways.structure.cypher;

import ways.structure.BasicItem;

public class Cypher extends BasicItem implements CypherElement{
   private int level;
   //private CypherType type;
   
   protected Cypher(String name, String desc, int level){
      super(name, desc, 0.0, 0.0);
      this.level = level;
      //this.type = type;
   }
   
   public int getLevel(){
      return level;
   }
}