/* generator testor
*  By: Devon Call
*/

package ways.generator;

import ways.structure.*;
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
      Location[] map = initiate(scan);
      scan.useDelimiter("[\r\n]+");
      String input;
      String[] token;
      Element current = map[0];
      Element temp;
      int i;
      System.out.println("Map Size: " + map.length);
      System.out.println(current);
      //System.out.print("cmd: ");
      while(scan.hasNext()){
         input = scan.next();
         token = input.split("\\p{Space}", 2);
         switch(token[0].toLowerCase()){
            case "f":
            case "find":
               temp = ElementObj.getElement(token[1]);
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
                  current = map[i - 1];
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
                  save(token[1]);
               }else{
                  save(currentsavename);
               }
               break;
            //change passages case
            case "change":
               MapBuilder.changePassages(map, new Random());
               break;
            case "tofile":
               if(token.length >= 2){
                  makeReadable(map, token[1]);
               }else{
                  makeReadable(map, null);
               }
               break;
            default:
               break;
         }
         System.out.println(current);
         //System.out.print("cmd: ");
      }
      save(currentsavename);
   }
   
   private static void save(String filename){
      try{
         Path newSave = Paths.get(filename);
         Files.deleteIfExists(newSave);
         Files.write(newSave, ElementObj.completeSave(), Charset.forName("UTF-8"));
         currentsavename = filename;
      }catch(Exception e){
         e.printStackTrace(System.err);
         System.out.println("unable to save");
      }
   }
   
   private static Location[] initiate(Scanner in){
      Location[] map = null;
      String[] tokens;
      in.reset();
      while(map == null && in.hasNext()){
         switch (in.next().toLowerCase()){
            case "load":
               //System.out.print("Save File: ");
               try{
                  map = loadSave(in.next());
               }catch(Exception e){
                  System.err.println(e);
                  map = null;
               }
               break;
            case "new":
               //System.out.print("Size: ");
               try{
                  map = newMap(Integer.parseInt(in.next()));
               }catch(Exception e){
                  System.err.println(e.getStackTrace());
                  map = null;
               }
               break;
            default:
               break;
         }
      }
      if(map == null){System.exit(0);}
      return map;
   }
   
   private static Location[] loadSave(String filename) throws FileNotFoundException, Exception{
      currentsavename = filename;
      return MapBuilder.loadSave(filename);
   }
   
   private static Location[] newMap(int size){
      Location[] map;     
      //map = MapBuilder.build(20, new SimpleLocationGenerator(900.0, 8100.0), new SimplePassageGenerator(), 250.0, 20.0);
      map = MapBuilder.build(size, new RingNode(), new SubspacePassage(), 200.0, 200.0);
      LocationMaterialFiller.fill(map, MaterialBuilder.getMaterials());
      SettlementGenerator.fill(map);
      currentsavename = "temp.ams";
      return map;
   }
   
   private static void makeReadable(Location[] map, String filename){
      List<String> ls = new ArrayList<String>();
      ls.add("file: " + currentsavename + "\n");
      for(Location l: map){
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