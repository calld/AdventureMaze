//Cypher Passage
// By: Devon Call

package ways.structure.cypher;

import ways.structure.PassageObj;
import ways.structure.Location;
import java.util.List;

public class CypherPassage extends PassageObj implements CypherElement{
   private int diff;
   
   protected CypherPassage(String name, String desc, Location loc, int diff){
      super(name, desc, loc);
      this.diff = diff;
   }
   
   public int getLevel(){
      return diff;
   }
   
   public void setDiff(int diff){
      this.diff = diff;
   }
   
   public String getShortDesc(){
      StringBuilder sb = new StringBuilder();
      sb.append(super.getShortDesc());
      sb.append(", diff: ");
      sb.append(diff);
      return sb.toString();
   }
   //save functions
   public CypherPassage(String name, String desc, List<String> notes, String dest, String diff) throws Exception{
      super(name, desc, notes, desc);
      this.diff = Integer.parseInt(diff);
   }
   
   protected StringBuilder getSaveFields(){
      StringBuilder sb = super.getSaveFields();
      sb.append("<diff>");
      sb.append(diff);
      sb.append("</diff>\n");
      return sb;
   }
}