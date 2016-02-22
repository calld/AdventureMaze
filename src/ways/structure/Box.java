/* Container Item, Box object
*  By: Devon Call
*/

package ways.structure;
import java.util.List;

public class Box extends ContainerObj implements Item{
   private double size;
   private double empty_weight;
   
   public static Box getNewBox(String name, String desc, double sizelimit, double weightlimit, double size, double empty_weight){
      if(getElement(name) != null){return null;}
      return new Box(name, desc, sizelimit, weightlimit, size, empty_weight);
   }
   
   protected Box(String name, String desc, double sizelimit, double weightlimit, double size, double empty_weight){
      super(name, desc, sizelimit, weightlimit);
      this.size = size;
      this.empty_weight = empty_weight;
   }
   
   public double getWeight(){
      return getContentsWeight() + empty_weight;
   }
   
   public double getSize(){
      return size;
   }
   
   //load and save
   
   protected Box(String name, String desc, List<String> notes, List<String> contents, String sizel, String weightl, String size, String e_w) throws Exception{
	   super(name, desc, notes, contents, sizel, weightl);
	   this.size = Double.parseDouble(size);
	   this.empty_weight = Double.parseDouble(e_w);
   }
   
   protected StringBuilder getSaveFields(){
	   StringBuilder sb = super.getSaveFields();
	   sb.append("<size>");
	   sb.append(size);
	   sb.append("</size>\n");
	   sb.append("<empty_weight>");
	   sb.append(empty_weight);
	   sb.append("</empty_weight>\n");
	   return sb;
   }
}