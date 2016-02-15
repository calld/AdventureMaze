/* abstract sudo element for loading saves
*  By: Devon Call
*/

package ways.structure.save;

import ways.structure.ElementObj;
import ways.structure.Element;
import java.util.List;

public abstract class SudoElement extends ElementObj{
   protected final String name;
   private ElementObj actual;
   
   public SudoElement(String name){
      super(null, null);
      dropElement(null);
      this.name = name;
      actual = null;
   }
   
   protected Element lookup(){
      if(actual == null){
         Element temp = getElement(name);
         if(temp instanceof ElementObj){
            actual = (ElementObj) getElement(name);
         }
      }
      return actual;
   }
   
   public String getName(){
      lookup();
      return actual.getName();
   }
   
   /*public String getDescription(){
      lookup();
      return actual.getDescription();
   }*/
   
   public String getDesc(){
      lookup();
      return actual.getDesc();
   }
   
   public String getLongDesc(){
      lookup();
      return actual.getLongDesc();
   }
   
   public Element addNote(String note){
      lookup();
      return actual.addNote(note);
   }
   
   public Element removeNote(int id){
      lookup();
      return actual.removeNote(id);
   }
   
   public List<String> getNotes(){
      lookup();
      return actual.getNotes();
   }
   
   public String toString(){
      lookup();
      return actual.toString();
   }
   
   public boolean equals(ElementObj other){
      lookup();
      return actual.equals(other);
   }

}