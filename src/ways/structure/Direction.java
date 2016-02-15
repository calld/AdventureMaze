/* Direction enum
*  By: Devon Call
*/

package ways.structure;

import java.util.List;
import java.util.ArrayList;

public enum Direction{
   NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST;
   
   public String toString(){
      switch(this){
         case NORTH:
            return "North";
         case NORTHEAST:
            return "Northeast";
         case EAST:
            return "East";
         case SOUTHEAST:
            return "Southeast";
         case SOUTH:
            return "South";
         case SOUTHWEST:
            return "Southwest";
         case WEST:
            return "West";
         case NORTHWEST:
            return "Northwest";
         default:
            return "None";
      }
   }
   
   public static List<Direction> getAllDirections(){
      List<Direction> tmp = new ArrayList<Direction>();
      tmp.add(NORTH);
      tmp.add(NORTHEAST);
      tmp.add(EAST);
      tmp.add(SOUTHEAST);
      tmp.add(SOUTH);
      tmp.add(SOUTHWEST);
      tmp.add(WEST);
      tmp.add(NORTHWEST);
      return tmp;
   }
   
   public static List<Direction> getCardinalDirections(){
      List<Direction> tmp = new ArrayList<Direction>();
      tmp.add(NORTH);
      tmp.add(EAST);
      tmp.add(SOUTH);
      tmp.add(WEST);
      return tmp;
   }
}