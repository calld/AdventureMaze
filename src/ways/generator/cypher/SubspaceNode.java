/* Cypher System Modern/Scify Location Generator
*  By: Devon Call
*/

package ways.generator.cypher;

import ways.generator.ContentGeneratorObj;
import java.util.Random;

public class SubspaceNode extends ContentGeneratorObj{
   
   private Random ran;
   int[] counts = {0,0,0,0};
   private double safecount;
   private boolean earth;
   
   public SubspaceNode(){
      super();
      ran = new Random();
      safecount = 0.0;
      earth = true;
   }
   
   public boolean newContent(Object... arguments){
      if(earth){
         setName("Earth");
         setDesc("Connects to Earth");
         earth = false;
         valid = true;
         return valid;
      }
      StringBuilder sb = new StringBuilder();
      double randouble = ran.nextDouble();
      if(randouble > (1.0/3.0)){
      //gas giant
      counts[0]++;
      setName("G" + counts[0]);
      sb.append("This node connects to a gas giant.");
      }else if(randouble > (1.0/9.0)){
      //terrestrial
      counts[1]++;
      setName("T" + counts[1]);
      sb.append("This node connects to a rocky planet.");
      }else if(randouble > (1.0/27.0)){
      //habital zone
      counts[2]++;
      setName("H" + counts[2]);
      sb.append("This node connects to a planet with liquid water.");
      }else{
      //macro-life
      counts[3]++;
      setName("M" + counts[3]);
      sb.append("This node connects to a planet with complex life.");
      }
      
      randouble = ran.nextDouble();
      if(randouble < (safecount-1)/safecount){
         safecount = 0.0;
         sb.append(terrainChallenge());
      }else{
         safecount += 1;
      }
      
      setDesc(sb.toString());
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