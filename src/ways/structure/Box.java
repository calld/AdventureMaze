/* Container Item, Box object
*  By: Devon Call
*/

package ways.structure;

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
}