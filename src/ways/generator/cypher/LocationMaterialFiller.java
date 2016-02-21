/* Map Material Filler
*  By: Devon Call
*/

package ways.generator.cypher;
import ways.structure.Item;
import static ways.generator.MapBuilder.randomStep;
import ways.structure.Location;
import java.util.List;
import java.util.Random;

public class LocationMaterialFiller{

   public static Location[] fill(Location[] map, List<List<Item>> materialTable){
      Random ran = new Random();
      int i;
      for(Location place: map){
         f(place, 0, materialTable, ran);
      }
      return map;
   }
   
   private static void f(Location place, int mod, List<List<Item>> materialTable, Random ran){
      switch(randomStep(ran) + mod){
         case 1://lvl 3
            f(place, mod + 1, materialTable, ran);
			place.addItem(materialTable.get(0).get(ran.nextInt(materialTable.get(0).size())));
			break;
         case 2:
            place.addItem(materialTable.get(0).get(ran.nextInt(materialTable.get(0).size())));
            break;
         case 3: //lvl 4
            place.addItem(materialTable.get(1).get(ran.nextInt(materialTable.get(1).size())));
            break;
         case 4: //lvl 5
            place.addItem(materialTable.get(2).get(ran.nextInt(materialTable.get(2).size())));
            break;
         case 5:
         case 6: //lvl 6
            place.addItem(materialTable.get(3).get(ran.nextInt(materialTable.get(3).size())));
            break;
         case 7:
         case 8: //lvl 7
            place.addItem(materialTable.get(4).get(ran.nextInt(materialTable.get(4).size())));
            break;
         case 9:
         case 10:
         case 11: // lvl 8
            place.addItem(materialTable.get(5).get(ran.nextInt(materialTable.get(5).size())));
            break;
         case 12:
         case 13: // lvl 9
            place.addItem(materialTable.get(6).get(ran.nextInt(materialTable.get(6).size())));
            break;
         default: // lvl 10
            place.addItem(materialTable.get(7).get(ran.nextInt(materialTable.get(7).size())));
            break;
      }
   }
}