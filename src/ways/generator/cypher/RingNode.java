/* ContentGenerator for Fantasy magic circle rooms
*  By: Devon Call
*/

package ways.generator.cypher;

import ways.generator.ContentGeneratorObj;
import java.util.Random;
import ways.generator.MapBuilder;
import ways.structure.Season;
import java.util.HashMap;
//import java.util.List;
//import java.util.ArrayList;

public class RingNode extends ContentGeneratorObj{

   private Random ran;
   private int count;
   private double safecount;
   private String[] noun = {"Forest", "Plain", "Archipelago", "Jungle", "Hills", "Mountain", "Swamp", "Desert"};
   private HashMap<Season, String[]> adjective = new HashMap<Season, String[]>(); 

   public RingNode(){
      super();
      ran = new Random();
      count = 0;
      safecount = 0.0;
      adjective.put(Season.SUMMER, new String[] {"Hot ", "Windy ", "Warm ", "Sunny "});
      adjective.put(Season.SPRING, new String[] {"Rainy ", "Warm ", "Stormy ", "Cool "});
      adjective.put(Season.WINTER, new String[] {"Frozen ", "Cool ", "Cold ", "Cloudy "});
      adjective.put(Season.FALL, new String[] {"Windy ", "Stormy ", "Cool ", "Warm "});
   }
   
   //extected double len, double wid, Season season
   public boolean newContent(Object... arguments) throws UnsupportedOperationException{
      Season sn;
      if(arguments[2] instanceof Season){
         sn = (Season) arguments[2];
      }else{
         valid = false;
         throw new UnsupportedOperationException("RingNode.newContent(double, len, double wid, Season season)");
      }
      
      count++;
      StringBuilder nme = new StringBuilder();
      StringBuilder dsc = new StringBuilder();
      nme.append(count);
      nme.append(": ");
      String adj = adjective.get(sn)[ran.nextInt(adjective.get(sn).length)];
      nme.append(adj);
      String nn = noun[ran.nextInt(noun.length)];
      nme.append(nn);
      
      dsc.append("This is a ");
      dsc.append(adj);
      dsc.append(nn);
      dsc.append(".");
      
      if(ran.nextDouble() < (safecount-1)/safecount){
         safecount = 0.0;
         dsc.append(terrainChallenge());
      }else{
         safecount += 1.0;
      }
      
      setName(nme.toString());
      setDesc(dsc.toString());
      
      valid = true;
      return valid;
   }
   
   private String terrainChallenge(){
      StringBuilder sb = new StringBuilder();
      sb.append("\nNode Terrain Challenge:\n\tdiff: ");
      sb.append(ran.nextInt(4)+2);
      sb.append(" type: ");
      int i = ran.nextInt(5);
      if(ran.nextBoolean()){
         sb.append("might skill: ");
         switch(i){
            case 0:
               sb.append("climbing\n");
               break;
            case 1:
               sb.append("jumping\n");
               break;
            case 2:
               sb.append("swimming\n");
               break;
            case 3:
               sb.append("carrying\n");
               break;
            case 4:
               sb.append("smashing\n");
               break;
            default:
               sb.append("</none>\n");
               break;
         }
      }else{
         sb.append("speed skill: ");
         switch(i){
            case 0:
               sb.append("climbing\n");
               break;
            case 1:
               sb.append("jumping\n");
               break;
            case 2:
               sb.append("swimming\n");
               break;
            case 3:
               sb.append("balancing\n");
               break;
            case 4:
               sb.append("running\n");
               break;
            default:
               sb.append("</none>\n");
               break;
         }
      }
      
      sb.append("\tdownside: take it slow (lose 1 supply/character), or risk it (");
      switch(ran.nextInt(18)){
         case 0:
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
            sb.append("take 3 dmg");
            break;
         case 8:
         case 9:
         case 10:
         case 11:
            sb.append("take 4 dmg");
            break;
         case 12:
         case 13:
            sb.append("take 5 dmg");
            break;
         case 14:
            sb.append("take 6 dmg");
            break;
         case 15:
         case 16:
         case 17:
            sb.append("lose an item");
            break;
         default:
            break;
      }
      sb.append(")");
      return sb.toString();
   }

}