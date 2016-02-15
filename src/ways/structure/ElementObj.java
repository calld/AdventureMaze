/* Element abstract Object
*  By: Devon Call
*/

package ways.structure;
import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.ArrayList;

public abstract class ElementObj implements Element{
   private final String desc;
   private final String name;
   private List<String> notes;
   private static HashMap<String, Element> allElements = new HashMap<String, Element>();
   
   //basic contructor template: returns null if name is taken
   /*public static ElementObj getNewElement(String name, String desc){
      if(getElement(name) != null){return null;}
      return new ElementObj(name, desc);
   }*/
   
   public static Element getElement(String name){
      return allElements.get(name);
   }
   
   public static void dropElement(String name){
      allElements.put(name, null);
   }
   
   //game elements have an unchanging name and description set upon construction
   protected ElementObj(String name, String desc){
      this.name = name;
      this.desc = desc;
      this.notes = new LinkedList<String>();
      allElements.put(name, this);
   }
   
   public String getName(){
      return name;
   }
   
   /*public String getDescription(){
      return getLongDesc();
   }*/
   
   public String getDesc(){
      return desc;
   }
   
   public String getLongDesc(){
      StringBuilder sb = new StringBuilder();
      sb.append(desc);
      sb.append("\nNotes:");
      if(notes.size() > 0){
         sb.append("\n");
         for(int i = 1; i <= notes.size(); i++){
            sb.append(i);
            sb.append(": ");
            sb.append(notes.get(i-1));
            sb.append("\n");
         }
      }else{
         sb.append("</none>\n");
      }
      return sb.toString();
   }
   
   public String getShortDesc(){
      return desc;
   }
   
   public Element addNote(String note){
      notes.add(note);
      return this;
   }
   
   public Element removeNote(int id){
      notes.remove(id - 1);
      return this;
   }
   
   public List<String> getNotes(){
      return new LinkedList<String>();
   }
   
   public String toString(){
      return getName() + ":\n" + getLongDesc();
   }
   
   public boolean equals(ElementObj other){
      if(other != null){
         return name.equals(other.name);
      }
      return false;
   }
   
   //save and load constructors and methods
   
   public ElementObj(String name, String desc, List<String> notes) throws Exception{
      this.name = name;
      this.desc = desc;
      this.notes = new LinkedList<String>(notes);
      if(allElements.get(name) == null){
         allElements.put(name, this);
      }else{
         throw new Exception("Save File corrupted, more than one copy of " + name + ".");
      } 
   }
   
   public String getSaveString(){
      StringBuilder sb = getSaveFields();
      sb.insert(0, "<" + this.getClass().getSimpleName() + ">\n");
      sb.append("</" + this.getClass().getSimpleName() + ">");
      return sb.toString();
   }
   
   protected StringBuilder getSaveFields(){
      StringBuilder sb = new StringBuilder();
      sb.append("<name>");
      sb.append(this.getName());
      sb.append("</name>\n");
      sb.append("<desc>");
      sb.append(this.getDesc());
      sb.append("</desc>\n");
      sb.append("<notes>");
      for(String n: notes){
         sb.append("<note>");
         sb.append(n);
         sb.append("</note>\n");
      }
      sb.append("</notes>\n");
      return sb;
   }
   
   //beta version, once all sudo classes implemented allow for indifferent ordering
   public static List<String> completeSave(){
      List<Element> everything = new ArrayList<Element>(allElements.values());
      List<String> sl = new ArrayList<String>();
      //get all items
      for(Element e: everything){
         if(e instanceof Item){
            sl.add(e.getSaveString());
         }
      }
      //get all Locations
      for(Element e: everything){
         if(e instanceof Location){
            sl.add(e.getSaveString());
         }
      }
      //get all Passages
      for(Element e: everything){
         if(e instanceof Passage){
            sl.add(e.getSaveString());
         }
      }
      
      return sl;
   }
}