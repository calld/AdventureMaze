/* Cypher Distance Enum
*  By: Devon Call
*/

package ways.structure.cypher;

public class Distance{
   public final int range;
   public static final Distance IMMEDIATE = new Distance(10);
   public static final Distance SHORT = new Distance(50);
   public static final Distance LONG = new Distance(100);


   public Distance(int range){
      this.range = range;
   }
   
   public String toString(){
      if(range == 10){
         return "Immediate Distance";
      }
      if(range == 50){
         return "Short Distance";
      }
      if(range == 100){
         return "Long Distance";
      }
      
      return range + " feet";
      
   }

}