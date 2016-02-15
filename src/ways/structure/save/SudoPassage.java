/* Sudo Passage
*  By: Devon Call
*/

package ways.structure.save;

import ways.structure.Passage;
import ways.structure.Location;
import ways.structure.Element;

public class SudoPassage extends SudoElement implements Passage{

   public SudoPassage(String name){
      super(name);      
   }
   
   public Location getDestination() throws UnsupportedOperationException{
      Element temp = lookup();
      Passage p = null;
      if(temp instanceof Passage){
         p = (Passage) temp;
      }else{
         if(temp != null){throw new UnsupportedOperationException(this.name + " not an instance of Passage");}
      }
      return p.getDestination();
   }
   
   public void setDestination(Location l){
      Element temp = lookup();
      Passage p = null;
      if(temp instanceof Passage){
         p = (Passage) temp;
      }else{
         if(temp != null){throw new UnsupportedOperationException(this.name + " not an instance of Passage");}
      }
      p.setDestination(l);
   }
}