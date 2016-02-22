/* Container Item, Bag object
*  By: Devon Call
*/

package ways.structure;
import java.util.List;

public class Bag extends ContainerObj implements Item{
   private double empty_size;
   private double empty_weight;
   
   protected Bag(String name, String desc, double sizelimit, double weightlimit, double empty_size, double empty_weight){
      super(name, desc, sizelimit, weightlimit);
      this.empty_size = empty_size;
      this.empty_weight = empty_weight;
   }
   
   public double getWeight(){
      return getContentsWeight() + empty_weight;
   }
   
   public double getSize(){
      return getUsedSpace() + empty_size;
   }
   
   //save and load
   
   protected Bag(String name, String desc, List<String> notes, List<String> contents, String s_l, String w_l, String e_s, String e_w) throws Exception{
	   super(name, desc, notes, contents, s_l, w_l);
	   this.empty_size = Double.parseDouble(e_s);
	   this.empty_weight = Double.parseDouble(e_w);
   }
   
   protected StringBuilder getSaveFields(){
	   StringBuilder sb = super.getSaveFields();
	   sb.append("<empty_size>");
	   sb.append(empty_size);
	   sb.append("</empty_size>\n");
	   sb.append("<empty_weight>");
	   sb.append(empty_weight);
	   sb.append("</empty_weight>\n");
	   return sb;
   }
}