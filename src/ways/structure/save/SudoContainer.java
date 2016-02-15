/* SudoContainer used as a place holder for loading save files
*  By: Devon Call
*/

package ways.structure.save;

import ways.structure.Item;
import ways.structure.Container;
import ways.structure.Element;
import java.util.List;

public abstract class SudoContainer extends SudoElement implements Container{
   
   public SudoContainer(String name){
      super(name);
   }
   
   private Container getActual() throws UnsupportedOperationException{
      Element temp = lookup();
      Container c = null;
      if(temp instanceof Container){
         c = (Container) temp;
      }else{
         if(temp != null){throw new UnsupportedOperationException(this.name + " is not an instance of a Container");}
      }
      return c;
   }
   
   public boolean addItem(Item item) throws UnsupportedOperationException{
      return getActual().addItem(item);
   }
   
   public boolean addItem(Item item, int number) throws UnsupportedOperationException{
      return getActual().addItem(item, number);
   }
   
   public List<Item> getContents() throws UnsupportedOperationException{
      return getActual().getContents();
   }
   
   public boolean removeItem(Item item) throws UnsupportedOperationException{
      return getActual().removeItem(item);
   }
   
   public double getSizeLimit() throws UnsupportedOperationException{
      return getActual().getSizeLimit();
   }
   
   public double getWeightLimit() throws UnsupportedOperationException{
      return getActual().getWeightLimit();
   }
   
   public double getContentsWeight() throws UnsupportedOperationException{
      return getActual().getContentsWeight();
   }
   
   public double getUsedSpace() throws UnsupportedOperationException{
      return getActual().getUsedSpace();
   }
}