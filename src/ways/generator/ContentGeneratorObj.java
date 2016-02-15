/* Abstract Content Generator
*  By: Devon Call
*/

package ways.generator;

public abstract class ContentGeneratorObj implements ContentGenerator{
   private String name;
   private String desc;
   protected boolean valid;
   
   public ContentGeneratorObj(){
      valid = false;
   }
   
   abstract public boolean newContent(Object... arguments);//should set valid to true when name and desc have correct values
   
   protected void setName(String name){
      this.name = name;
   }
   
   protected void setDesc(String desc){
      this.desc = desc;
   }
   
   public String getName() throws UnsupportedOperationException{
      if (!valid){
         throw new UnsupportedOperationException("no content available at time of call, likely due to newContent not yet been called or newContent returned false");
      }
      return name;
   }
   
   public String getDesc() throws UnsupportedOperationException{
      if (!valid){
         throw new UnsupportedOperationException("no content available at time of call, likely due to newContent not yet been called or newContent returned false");
      }   
      return desc;
   }
}