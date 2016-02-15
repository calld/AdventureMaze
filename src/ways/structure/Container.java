/* Item Container Interface
*  By: Devon Call
*/

package ways.structure;
import java.util.List;

public interface Container extends Element{
   boolean addItem(Item item);
   boolean addItem(Item item, int number);
   List<Item> getContents();
   boolean removeItem(Item item);
   double getSizeLimit();
   double getWeightLimit();
   double getContentsWeight();
   double getUsedSpace();
}