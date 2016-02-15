/* Item obj implementation
*  By: Devon Call
*/

package ways.structure;
import java.util.List;

public class BasicItem extends ElementObj implements Item{
   private double weight;
   private double size;
   
   public static BasicItem getNewBasicItem(String name, String desc, double weight, double size){
      if(getElement(name) != null){return null;}
      return new BasicItem(name, desc, weight, size);
   }
   
   protected BasicItem(String name, String desc, double weight, double size){
      super(name, desc);
      this.weight = weight;
      this.size = size;
   }
   
   public double getWeight(){
      return weight;
   }
   
   public double getSize(){
      return size;
   }
   
   //save and load functions
   
   public BasicItem(String name, String desc, List<String> notes, String weight, String size) throws Exception{
      super(name, desc, notes);
      this.weight = Double.parseDouble(weight);
      this.size = Double.parseDouble(size);
   }
   
   protected StringBuilder getSaveFields(){
      StringBuilder sb = super.getSaveFields();
      sb.append("<weight>");
      sb.append(weight);
      sb.append("</weight>\n");
      sb.append("<size>");
      sb.append(size);
      sb.append("</size>\n");
      return sb;
   }
}