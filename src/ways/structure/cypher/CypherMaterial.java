/* Material Item for Cypher System
*  By: Devon Call
*/

package ways.structure.cypher;
import ways.structure.BasicItem;
import java.util.List;


public class CypherMaterial extends BasicItem implements CypherElement{
   private int level;
   
   public static CypherMaterial getNewCypherMaterial(String name, String desc, int level){
      if(getElement(name) != null){return null;}
      return new CypherMaterial(name + "(" + Integer.toString(level) + ")", desc, level);
   }
   
   protected CypherMaterial(String name, String desc, int level){
      super(name, desc, 0.0, 0.0);
      this.level = level;
   }
   
   public int getLevel(){
      return level;
   }
   
   public String getLongDesc(){
      StringBuilder sb = new StringBuilder();
      sb.append("Level: ");
      sb.append(level);
      sb.append("\n");
      sb.append(super.getLongDesc());
      return sb.toString();
   }
   
   public String getName(){
      return super.getName();
   }
   
   //save and load function
   
   public CypherMaterial(String name, String desc, List<String> notes, String level) throws Exception{
      super(name, desc, notes, "0.0", "0.0");
      this.level = Integer.parseInt(level);
   }
   
   protected StringBuilder getSaveFields(){
      StringBuilder sb = super.getSaveFields();
      sb.append("<level>");
      sb.append(level);
      sb.append("</level>\n");
      return sb;
   }
   
   
}