/* Container Item, Bag object
*  By: Devon Call
*/

package ways.structure;

public class Bag extends ContainerObj implements Item{
   private double empty_size;
   private double empty_weight;
   
   public static Bag getNewBag(String name, String desc, double sizelimit, double weightlimit, double empty_size, double empty_weight){
      if(getElement(name) != null){return null;}
      return new Bag(name, desc, sizelimit, weightlimit, empty_size, empty_weight);
   }
   
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
}