/* implementation of ContentGenerator for Locations
*  by: Devon Call
*/

package ways.generator;

import ways.structure.Season;

public class SimpleLocationGenerator implements ContentGenerator{
   public final double MAX_ROOM_SIZE; //final values to be set on contruction, used to build desciptor
   public final double MAX_HOUSE_SIZE;
   private String name;//current temporary name and descriptor
   private String desc;
   private boolean valid; //current values of name and desc are valid
   private long roomcount;
   
   public SimpleLocationGenerator(double mrs, double mhs){
      MAX_ROOM_SIZE = mrs;
      MAX_HOUSE_SIZE = mhs;
      valid = false;
      roomcount = 0;
   }
   
   public boolean newContent(Object... arguments) throws UnsupportedOperationException{
      double len, wid;
      Season s;
      if((arguments[0] instanceof Double)&&(arguments[1] instanceof Double)&&(arguments[2] instanceof Season)){
         len = (Double) arguments[0];
         wid = (Double) arguments[1];
         s = (Season) arguments[2];
      }else{
         throw new UnsupportedOperationException("expected aruments for newContent is (double length, double width, Season season)");
      }
      roomcount++;
      name = "Room " + roomcount;
      if(MAX_ROOM_SIZE > len * wid){
         desc = "This place is the size of a room.";
      }else if(MAX_HOUSE_SIZE > len * wid){
         desc = "This place is the size of a house.";
      }else{
         desc = "This place is bigger than a house.";
      }
      
      switch (s){
         case SUMMER:
            desc = desc + " It is warm here.";
            break;
         case SPRING:
            desc = desc + " This place looks fresh.";
            break;
         case WINTER:
            desc = desc + " Brrr, it's cold.";
            break;
         case FALL:
            desc = desc + " All the leaves are colorful.";
            break;
         default:
            break;
      }
      valid = true;
      return valid;
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