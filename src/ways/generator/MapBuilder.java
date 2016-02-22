/* Map Builder
*  By: Devon Call
*/

package ways.generator;
import java.util.Random;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import ways.structure.cypher.*;
import ways.structure.*;

public class MapBuilder{

   public static WorldState build(int size, ContentGenerator lg, ContentGenerator pg, double max_size, double min_size){
      WorldState world = new CypherWorldState();
      Random ran = new Random();
      double len = dimGen(ran, max_size, min_size);
      double wid = dimGen(ran, max_size, min_size);
      Season s = Season.getSeason(ran.nextInt(4));
      lg.newContent(len, wid, s);
      for(int i = 0; i < size; i++){
         while(null == (world.add(world.makeNewLocation(lg.getName(), lg.getDesc(), len, wid, s)))){
            lg.newContent(len, wid, s);
         }
         len = dimGen(ran, max_size, min_size);
         wid = dimGen(ran, max_size, min_size);
         s = Season.getSeason(ran.nextInt(4));
         lg.newContent(len, wid, s);         
      }
      List<Direction> dirs;
      int j;
      for(int i = 0; i < size; i++){
         dirs = Direction.getCardinalDirections();
         j = (i + size - 1) % size;
         makePassage(world, pg, dirs, size, i, j, ran);
         
         j = (j + size - (randomStep(ran) % size)) % size;
         makePassage(world, pg, dirs, size, i, j, ran);
         
         j = (i + randomStep(ran)) % size;
         makePassage(world, pg, dirs, size, i, j, ran);
         
         j = (j + randomStep(ran)) % size;
         makePassage(world, pg, dirs, size, i, j, ran);
      }
      return world;
   }
   
   public static WorldState changePassages(WorldState world, Random ran){
      List<Direction> dirs;
      int j;
      int size = world.mapSize();
      for(int i = 0; i < size; i++){
         dirs = Direction.getCardinalDirections();
         j = (i + size - 1) % size;
         world.get(i).getPassage(dirs.remove(ran.nextInt(dirs.size()))).setDestination(world.get(j));
         
         j = (j + size - (randomStep(ran) % size)) % size;
         world.get(i).getPassage(dirs.remove(ran.nextInt(dirs.size()))).setDestination(world.get(j));
         
         j = (i + randomStep(ran)) % size;
         world.get(i).getPassage(dirs.remove(ran.nextInt(dirs.size()))).setDestination(world.get(j));
         
         j = (j + randomStep(ran)) % size;
         world.get(i).getPassage(dirs.remove(ran.nextInt(dirs.size()))).setDestination(world.get(j));
      }
      
      return world;
   }
   
   private static double dimGen(Random r, double max, double min){
      return r.nextDouble() * (max-min) + min;
   }
   
   public static int randomStep(Random ran){ //set up so that the function returns 1 one half of the time, returns 2 one quarter of the time, returns 3 one eighth, and so on.
      int i;
      try{
         i = (int) Math.ceil(Math.log(1.0/ran.nextDouble())/Math.log(2));
      }catch(Exception e){
         i = randomStep(ran);
      }
      return i;
   }
   
   private static void makePassage(WorldState world, ContentGenerator pg, List<Direction> dirs, int size, int i, int j, Random ran){
      pg.newContent(world.get(j));
      Passage p = world.makeNewPassage(pg.getName(), pg.getDesc(), world.get(j));
      Direction d = dirs.remove(ran.nextInt(dirs.size()));
      world.get(i).addPassage(d, p);
   }
   
   /*public static Location[] loadSave(String filename) throws FileNotFoundException, Exception{
      
      Location[] map = new Location[1];
      List<Location> m = new ArrayList<Location>();
      File f = new File(filename);
      Scanner scan = new Scanner(f);
      scan.useDelimiter("[<>]+");
      while(scan.hasNext()){
         String type = scan.next();
         switch(type.toLowerCase()){
            case "location":
               m.add(loadLocation(scan));
               break;
            case "passageobj":
               loadPassage(scan);
               break;
            case "cyphermaterial":
               loadMaterial(scan);
               break;
            default:
               break;
         }
      }
      scan.close();
      m.sort((l, r) -> Integer.parseInt(l.getName().split(":")[0]) - Integer.parseInt(r.getName().split(":")[0]));  
      map = m.toArray(map);
      return map;
   }
   
   private static Location loadLocation(Scanner scan) throws Exception{
      String name = null;
      String desc = null;
      List<String> notes = new ArrayList<String>();
      List<String> contents = new ArrayList<String>();
      String len = "0.0";
      String wid = "0.0";
      String season = null;
      List<String> direction = new ArrayList<String>();
      List<String> passage = new ArrayList<String>();
      boolean hold = true;
      boolean remaining = true;
      while(remaining){
         switch(scan.next().toLowerCase()){
            case "name":
               name = scan.next();
               scan.next();
               break;
            case "desc":
               desc = scan.next();
               scan.next();
               break;
            case "notes":
               hold = true;
               while(hold){
                  switch(scan.next().toLowerCase()){
                     case "note":
                        notes.add(scan.next());
                        scan.next();
                        break;
                     case "/notes":
                        hold = false;
                        break;
                     default:
                        break;
                  }
               }
               break;
            case "contents":
               hold = true;
               while(hold){
                  switch(scan.next().toLowerCase()){
                     case "item":
                        contents.add(scan.next());
                        scan.next();
                        break;
                     case "/contents":
                        hold = false;
                        break;
                     default:
                        break;
                  }
               }
               break;
            case "length":
               len = scan.next();
               scan.next();
               break;
            case "width":
               wid = scan.next();
               scan.next();
               break;
            case "season":
               season = scan.next();
               scan.next();
               break;
            case "passages":
               hold = true;
               while(hold){
                  switch(scan.next().toLowerCase()){
                     case "direction":
                        direction.add(scan.next());
                        scan.next();
                        break;
                     case "passage":
                        passage.add(scan.next());
                        scan.next();
                        break;
                     case "/passages":
                        hold = false;
                        break;
                     default:
                        break;
                  }
               }
               break;
            case "/location":
               remaining = false;
               break;
            default:
               break;
         }
      }
      return new LocationObj(name, desc, notes, contents, len, wid, season, direction, passage);
   }
   
   private static void loadPassage(Scanner scan) throws Exception{
      String name = null;
      String desc = null;
      List<String> notes = new ArrayList<String>();
      String destination = null;
      boolean hold = true;
      boolean remaining = true;
      while(remaining){
         switch(scan.next().toLowerCase()){
            case "name":
               name = scan.next();
               scan.next();
               break;
            case "desc":
               desc = scan.next();
               scan.next();
               break;
            case "notes":
               hold = true;
               while(hold){
                  switch(scan.next().toLowerCase()){
                     case "note":
                        notes.add(scan.next());
                        scan.next();
                        break;
                     case "/notes":
                        hold = false;
                        break;
                     default:
                        break;
                  }
               }
               break;
            case "destination":
               destination = scan.next();
               scan.next();
               break;
            case "/passageobj":
               remaining = false;
               break;
            default:
               break;
               
         }
      }
      new PassageObj(name, desc, notes, destination);
   }
   
   private static void loadMaterial(Scanner scan) throws Exception{
      String name = null;
      String desc = null;
      List<String> notes = new ArrayList<String>();
      String level = "0";
      boolean remaining = true;
      boolean hold = true;
      while(remaining){
         switch(scan.next().toLowerCase()){
            case "name":
               name = scan.next();
               scan.next();
               break;
            case "desc":
               desc = scan.next();
               scan.next();
               break;
            case "notes":
               hold = true;
               while(hold){
                  switch(scan.next().toLowerCase()){
                     case "note":
                        notes.add(scan.next());
                        scan.next();
                        break;
                     case "/notes":
                        hold = false;
                        break;
                     default:
                        break;
                  }
               }
               break;
            case "level":
               level = scan.next();
               scan.next();
               break;
            case "/cyphermaterial":
               remaining = false;
               break;
            default:
               break;
         }
      }
      new CypherMaterial(name, desc, notes, level);
   }*/
}