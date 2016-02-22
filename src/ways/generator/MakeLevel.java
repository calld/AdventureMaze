/* generator testor
*  By: Devon Call
*/

package ways.generator;

import ways.structure.*;
import ways.structure.cypher.*;
import ways.generator.cypher.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class MakeLevel{
   private static String currentsavename;

   public static void main(String[] args){
      Scanner scan = new Scanner(System.in);
      WorldState world = initiate(scan);
      scan.useDelimiter("[\r\n]+");
      String input;
      String[] token;
      Element current = world.get(0);
      Element temp;
      int i;
      System.out.println("Map Size: " + world.mapSize());
      System.out.println(current);
      //System.out.print("cmd: ");
      while(scan.hasNext()){
         input = scan.next();
         token = input.split("\\p{Space}", 2);
         switch(token[0].toLowerCase()){
            case "f":
            case "find":
               temp = world.getElement(token[1]);
               if(temp != null){
                  current = temp;
               }else{
                  System.out.println("Element not found");
               }               
               break;
            case "j":
            case "jump":
               try{
                  i = Integer.parseInt(token[1]);
                  current = world.get(i - 1);
               }catch(Exception e){
                  System.out.println("invalid number");
               }
               break;
            case "n":
            case "note":
               current.addNote(token[1]);
               break;
            case "r":
            case "remove":
               try{
                  i = Integer.parseInt(token[1]);
                  current.removeNote(i);
               }catch(Exception e){
                  //System.out.print(e);
                  System.out.println("invalid number");
               }
               break;
               
            //save case
            case "s":
            case "save":
               if(token.length >= 2){
                  save(token[1], world);
               }else{
                  save(currentsavename, world);
               }
               break;
            //change passages case
            case "change":
               MapBuilder.changePassages(world, new Random());
               break;
            case "tofile":
               if(token.length >= 2){
                  makeReadable(world, token[1]);
               }else{
                  makeReadable(world, null);
               }
               break;
            default:
               break;
         }
         System.out.println(current);
         //System.out.print("cmd: ");
      }
      save(currentsavename, world);
   }
   
   private static void save(String filename, WorldState world){
      try{
         Path newSave = Paths.get(filename);
         Files.deleteIfExists(newSave);
         Files.write(newSave, ElementObj.completeSave(world), Charset.forName("UTF-8"));
         currentsavename = filename;
      }catch(Exception e){
         e.printStackTrace(System.err);
         System.out.println("unable to save");
      }
   }
   
   private static WorldState initiate(Scanner in){
      WorldState world = null;
      String[] tokens;
      in.reset();
      while(world == null && in.hasNext()){
         switch (in.next().toLowerCase()){
            case "load":
               //System.out.print("Save File: ");
               try{
                  world = loadSave(in.next());
               }catch(Exception e){
                  System.err.println(e);
                  world = null;
               }
               break;
            case "new":
               //System.out.print("Size: ");
               try{
                  world = newWorld(Integer.parseInt(in.next()));
               }catch(Exception e){
                  System.err.println(e.getStackTrace());
                  world = null;
               }
               break;
            default:
               break;
         }
      }
      if(world == null){System.exit(0);}
      return world;
   }
   
   private static WorldState loadSave(String filename) throws FileNotFoundException, Exception{
	  return new WorldState(new Scanner(new File(filename)));
   }
   
   /*private static Location[] newMap(int size){
      Location[] map;     
      //map = MapBuilder.build(20, new SimpleLocationGenerator(900.0, 8100.0), new SimplePassageGenerator(), 250.0, 20.0);
      map = MapBuilder.build(size, new RingNode(), new SubspacePassage(), 200.0, 200.0);
      LocationMaterialFiller.fill(map, MaterialBuilder.getMaterials());
      SettlementGenerator.fill(map);
      currentsavename = "temp.ams";
      return map;
   }*/
   
   private static WorldState newWorld(int size){
	   WorldState world = MapBuilder.build(size, new RingNode(), new SubspacePassage(), 200.0, 200.0);
	   LocationMaterialFiller.fill(world, MaterialBuilder.getMaterials((CypherWorldState) world));
	   SettlementGenerator.fill(world);
	   currentsavename = "temp.ams";
	   return world;
   }
   
   private static void makeReadable(WorldState world, String filename){
      List<String> ls = new ArrayList<String>();
      ls.add("file: " + currentsavename + "\n");
      for(Location l: world.getMap()){
         ls.add(l.toString());
      }
      if(filename == null){
         filename = "read" + currentsavename;
      }
      try{
         Path newSave = Paths.get(filename);
         Files.deleteIfExists(newSave);
         Files.write(newSave, ls, Charset.forName("UTF-8"));
      }catch(Exception e){
         e.printStackTrace(System.err);
         System.out.println("unable to write to file");
      }
   }
}