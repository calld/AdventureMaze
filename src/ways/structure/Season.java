/* season enum
*  Devon Call
*/

package ways.structure;

public enum Season{
   SUMMER, WINTER, FALL, SPRING;
   
   public String toString(){
      switch(this){
         case SUMMER:
            return "Summer";
         case WINTER:
            return "Winter";
         case FALL:
            return "Fall";
         case SPRING:
            return "Spring";
         default:
            return "none";
      }
   }
   
   public static Season getSeason(int i){
      switch(i % 4){
         case 0:
            return SUMMER;
         case 1:
            return WINTER;
         case 2:
            return FALL;
         case 3:
            return SPRING;
         default:
            return null;
      }
   }
}