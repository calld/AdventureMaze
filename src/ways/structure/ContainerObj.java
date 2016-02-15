/* Container implmentation
*  By: Devon Call
*/

package ways.structure;
import java.util.List;
import java.util.LinkedList;

public abstract class ContainerObj extends ElementObj implements Container{
   private List<Item> contents;
   private final double sizelimit;
   private final double weightlimit;
   
   protected ContainerObj(String name, String desc, double sizelimit, double weightlimit){
      super(name, desc);
      this.sizelimit = sizelimit;
      this.weightlimit = weightlimit;
      this.contents = new LinkedList<Item>();
   }
   
   private boolean room_for(Item item, int num){
      return (getUsedSpace() + item.getSize() * num <= sizelimit)
           && (getContentsWeight() + item.getWeight() * num<= weightlimit);
   }
   
   public boolean addItem(Item item, int number){
      if(room_for(item, number)){
         for(int i = 0; i < number; i++){
            contents.add(item);
         }           
         return true;
      }
      return false;      
   }
   
   public boolean addItem(Item item){
      return addItem(item, 1);
   }
   
   public double getSizeLimit(){
      return sizelimit;
   }
   
   public double getWeightLimit(){
      return weightlimit;
   }
   
   public List<Item> getContents(){
      return new LinkedList<Item>(contents);
   }
   
   public boolean removeItem(Item item){
      return contents.remove(item);
   }
   
   public double getContentsWeight(){
      double total = 0.0;
      
      for(Item i: contents){
         total += i.getWeight();
      }
      
      return total;
   }
   
   public double getUsedSpace(){
      double total = 0.0;
      
      for(Item i: contents){
         total += i.getSize();
      }
      
      return total;
   }
   
   public String getLongDesc(){
      StringBuilder sb = new StringBuilder();
      sb.append(super.getLongDesc());
      //sb.append(this.getName());
      sb.append("Contains: ");
      for(Item i: contents){
         sb.append(i.getName());
         sb.append(", ");
      }
      sb.delete(sb.length() - 2, sb.length());
      sb.append("\n");
      return sb.toString();
   }
   
   //save/load functions
   
   public ContainerObj(String name, String desc, List<String> notes, List<String> contents, String sizel, String weightl) throws Exception{
      super(name, desc, notes);
      Element temp;
      this.contents = new LinkedList<Item>();
      for(String s: contents){
         temp = getElement(s);
         if(temp instanceof Item){
            this.contents.add((Item) temp);
         }else{
            throw new Exception("Item not found, " + s);
         }
      }
      this.sizelimit = Double.parseDouble(sizel);
      this.weightlimit = Double.parseDouble(weightl);
   }
   
   protected StringBuilder getSaveFields(){
      StringBuilder sb = super.getSaveFields();
      sb.append("<contents>");
      for(Item i: contents){
         sb.append("<item>");
         sb.append(i.getName());
         sb.append("</item>");
      }
      sb.append("</contents>\n");
      sb.append("<sizelimit>");
      sb.append(sizelimit);
      sb.append("</sizelimit>");
      sb.append("<weightlimit>");
      sb.append(weightlimit);
      sb.append("</weightlimit>\n");
      return sb;
   }
}