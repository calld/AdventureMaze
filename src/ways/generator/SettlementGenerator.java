/* Fantasy Settlement note generator
*  By: Devon Call
*/

package ways.generator;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import ways.structure.Location;
import ways.structure.WorldState;
import java.util.Random;
import java.util.Arrays;
import java.util.HashMap;

public class SettlementGenerator {

   private static class Settlement{
      private String name;
      private String desc;
      private Prosperity pros;
      private Population pop;
      private Defense defense;
      private List<List<String>> tag;
      //private Settlement oathbound;
      public final Stype type;
      
      public Settlement(String name, String desc, Stype type){
         this.name = name;
         this.desc = desc;
         this.type = type;
         tag = new LinkedList<List<String>>();
         //oathbound = null;
      }
      
      public String getName(){
         return name;
      }
      
      public String getDesc(){
         return desc;
      }
      
      public Stype getType(){
         return type;
      }
      
      public List<List<String>> getTags(){
         return new ArrayList<List<String>>(tag);
      }
      
      public void addTag(List<String> s){
         tag.add(new ArrayList<String>(s));
      }
      
      public Prosperity getProsperity(){
         return pros;
      }
      
      public void setProsperity(Prosperity p){
         pros = p;
      }
      
      public Population getPopulation(){
         return pop;
      }
      
      public void setPopulation(Population p){
         pop = p;
      }
      
      public Defense getDefense(){
         return defense;
      }
      
      public void setDefense(Defense d){
         defense = d;
      }
      
      public void fillin(Location l){
         l.addNote("Connects to " + name + " (" + type + "): " + desc);
         l.addNote("Prosperity: " + pros);
         l.addNote("Population: " + pop);
         l.addNote("Defense: " + defense);
         StringBuilder note;
         for(List<String> ls: tag){
            note = new StringBuilder();
            for(String st: ls){
               note.append(st);
               note.append(" ");
            }
            l.addNote(note.toString());
         }
      }
      
   }
   
   private static enum Stype{
      Village, Town, Fort, City;
   }
   
   private static enum Prosperity{
      None(0), Inexpensive(1), Moderate(2), Expensive(3), VeryExpensive(4), Exorbitant(5);
      
      public static final Prosperity[] ALL;
      private int pos;
      
      static{
         ALL = new Prosperity[6];
         ALL[0] = None;
         ALL[1] = Inexpensive;
         ALL[2] = Moderate;
         ALL[3] = Expensive;
         ALL[4] = VeryExpensive;
         ALL[5] = Exorbitant;
      }
      
      private Prosperity(int i){
         pos = i;
      }
      
      public Prosperity up(){
         try{
            return ALL[pos + 1];
         }catch(ArrayIndexOutOfBoundsException e){
            return ALL[pos];
         }
      }
      
      public Prosperity down(){
         try{
            return ALL[pos - 1];
         }catch(ArrayIndexOutOfBoundsException e){
            return ALL[pos];
         }
      }
   }
   
   private static enum Population{
      Exodus(0), Shrinking(1), Steady(2), Growing(3), Booming(4);
      
      public static final Population[] ALL;
      private final int pos;
      
      static{
         ALL = new Population[5];
         ALL[0] = Exodus;
         ALL[1] = Shrinking;
         ALL[2] = Steady;
         ALL[3] = Growing;
         ALL[4] = Booming;
      }
      
      private Population(int i){
         pos = i;
      }
      
      public Population up(){
         try{
            return ALL[pos + 1];
         }catch(ArrayIndexOutOfBoundsException e){
            return ALL[pos];
         }
      }
      
      public Population down(){
         try{
            return ALL[pos - 1];
         }catch(ArrayIndexOutOfBoundsException e){
            return ALL[pos];
         }
      }
      
      public boolean isGreater(Population other){
         return this.pos > other.pos;
      }
   }
   
   public static enum Defense{
      None(0), Militia(1), Watch(2), Guard(3), Garrison(4), Battalion(5), Legion(6);
   
      public static final Defense[] ALL;
      private final int pos;
      
      static{
         ALL = new Defense[7];
         ALL[0] = None;
         ALL[1] = Militia;
         ALL[2] = Watch;
         ALL[3] = Guard;
         ALL[4] = Garrison;
         ALL[5] = Battalion;
         ALL[6] = Legion;
      }
      
      private Defense(int i){
         pos = i;
      }
      
      public Defense up(){
         try{
            return ALL[pos + 1];
         }catch(ArrayIndexOutOfBoundsException e){
            return ALL[pos];
         }
      }
      
      public Defense down(){
         try{
            return ALL[pos - 1];
         }catch(ArrayIndexOutOfBoundsException e){
            return ALL[pos];
         }
      }
   }
   
   public static final String[] RESOURCE = new String[]{
      "Iron",
      "Copper",
      "Wood",
      "Cotton",
      "Wheat",
      "Salt",
      "Cattle",
      "Pig",
      "Sheep",
      "Goat",
      "Apple", 
      "Cabbage",
      "Oranges",
      "Barley",
      "Peaches",
      "Oat", 
      "Grape", 
      "Fish"
   };
   
   //private enum

   public static void fill(WorldState world){
      Settlement[] listofsettle = new Settlement[world.mapSize()];
      Random ran = new Random();
      for(int i = 0; i < world.mapSize(); i++){
         switch(MapBuilder.randomStep(ran)){
            case 1:
               listofsettle[i] = null;
               world.get(i).addNote("No world connection");
               break;
            case 2:
               listofsettle[i] = null;
               world.get(i).addNote("connects to unsettled land");
               break;
            case 3:
            case 4:
               listofsettle[i] = newVillage(ran);
               break;
            case 5:
            case 6:
               listofsettle[i] = newTown(ran);
               break;
            case 7:
               listofsettle[i] = newFort(ran);
               break;
            default:
               listofsettle[i] = newCity(ran);
               break;
         
         }
      }
      
      boolean[][] favorablerecord = new boolean[listofsettle.length][listofsettle.length];
      //boolean[][] traderecord = new boolean[listofsettle.length][listofsettle.length];
      
      for(int i = 0; i < listofsettle.length; i++){
         if(listofsettle[i] != null){
            switch (listofsettle[i].getType()){
               case Village:
                  establishOath(i, listofsettle, favorablerecord, 4, Stype.Town, Stype.City);
                  break;
               case Town:
                  establishTwoTrade(i, listofsettle, favorablerecord, 12);                  
                  break;
               case City:
                  establishOath(i, listofsettle, favorablerecord, 20, Stype.Fort);
                  establishOath(i, listofsettle, favorablerecord, 16, Stype.Town);
                  establishTwoTrade(i, listofsettle, favorablerecord, 24);
                  break;
               case Fort:
                  establishOath(i, listofsettle, favorablerecord, 12, Stype.Town, Stype.Village);
                  break;
               default:
                  break;
            }
         }
      }
      
      for(int i = 0; i < listofsettle.length; i++){
         if(listofsettle[i] != null){
            if(MapBuilder.randomStep(ran) > 2){
               establishEmnity(i, listofsettle, favorablerecord);
            }
         }
      }
      
      for(int i = 0; i < listofsettle.length; i++){
         if(listofsettle[i] != null){
            listofsettle[i].fillin(world.get(i));
         }
      }
   
   }
   
   private static void establishEmnity(int i, Settlement[] stlmnt, boolean[][] record){
      int indx;
      for(int j = 2; j < stlmnt.length; j++){
         indx = (i + j) % stlmnt.length;
         if(stlmnt[indx] != null && !record[i][indx]){
            stlmnt[i].addTag(Arrays.asList("Emnity(" + stlmnt[indx].getName() + " at " + (indx + 1) + ")"));
            stlmnt[indx].addTag(Arrays.asList("Emnity(" + stlmnt[i].getName() + " at " + (i + 1) + ")"));
            record[i][indx] = true;
            record[indx][i] = true;
            break;
         }
         indx = (i - j + stlmnt.length) % stlmnt.length;
         if(stlmnt[indx] != null && !record[i][indx]){
            stlmnt[i].addTag(Arrays.asList("Emnity(" + stlmnt[indx].getName() + " at " + (indx + 1) + ")"));
            stlmnt[indx].addTag(Arrays.asList("Emnity(" + stlmnt[i].getName() + " at " + (i + 1) + ")"));
            record[i][indx] = true;
            record[indx][i] = true;
            break;
         }
      }
   }
   
   private static void establishOath(int i, Settlement[] stlmnt, boolean[][] oathrecord, int distancelimit, Stype... types){
      int indx;
      List<String> temp;
      for(int j = 1; (j < stlmnt.length) && (j < distancelimit); j++){
         indx = (i + j) % stlmnt.length;
         if(stlmnt[indx] != null && !oathrecord[i][indx] && OR(stlmnt[indx].getType(), types)){
            temp = new LinkedList<String>(Arrays.asList("Oath(" + stlmnt[indx].getName() + " at " + (indx + 1) + ")"));
            if(stlmnt[i].getType() == Stype.Village && (stlmnt[indx].getType() == Stype.Fort)){
               temp.add("+Defense");
               stlmnt[i].setDefense(stlmnt[i].getDefense().up());
            }else if(stlmnt[indx].getType() == Stype.Village && (stlmnt[i].getType() == Stype.Fort)){
               temp.add("-Defense");
               stlmnt[i].setDefense(stlmnt[i].getDefense().down());
            }else if(stlmnt[i].getType() == Stype.Fort && stlmnt[indx].getType() == Stype.City){
               if(stlmnt[i].getPopulation().isGreater(Population.Shrinking)){
                  temp.add("+Population");
                  stlmnt[i].setPopulation(stlmnt[i].getPopulation().up());
               }else{
                  temp.add("+Defense");
                  stlmnt[i].setDefense(stlmnt[i].getDefense().up());
               }
            }else if(stlmnt[i].getType() == Stype.Village && stlmnt[indx].getType() == Stype.City){
				temp.add("+Prosperity");
				stlmnt[i].setProsperity(stlmnt[i].getProsperity().up());
			}
            stlmnt[i].addTag(temp);
            
            temp = new LinkedList<String>(Arrays.asList("Oath(" + stlmnt[i].getName() + " at " + (i + 1) + ")"));
            if(stlmnt[indx].getType() == Stype.Village && (stlmnt[i].getType() == Stype.Fort || stlmnt[i].getType() == Stype.City)){
               temp.add("+Defense");
               stlmnt[indx].setDefense(stlmnt[indx].getDefense().up());
            }else if(stlmnt[i].getType() == Stype.Village && (stlmnt[indx].getType() == Stype.Fort || stlmnt[indx].getType() == Stype.City)){
               temp.add("-Defense");
               stlmnt[indx].setDefense(stlmnt[indx].getDefense().down());
            }else if(stlmnt[i].getType() == Stype.City && stlmnt[indx].getType() == Stype.Fort){
               if(stlmnt[i].getPopulation().isGreater(Population.Shrinking)){
                  temp.add("+Population");
                  stlmnt[i].setPopulation(stlmnt[i].getPopulation().up());
               }else{
                  temp.add("+Defense");
                  stlmnt[i].setDefense(stlmnt[i].getDefense().up());
               }
            }else if(stlmnt[indx].getType() == Stype.Village && stlmnt[i].getType() == Stype.City){
				temp.add("+Prosperity");
				stlmnt[indx].setProsperity(stlmnt[indx].getProsperity().up());
			}
            stlmnt[indx].addTag(temp);
            
            oathrecord[i][indx] = true;
            oathrecord[indx][i] = true;
            break;
         }
         
         indx = (i - j + stlmnt.length) % stlmnt.length;
        if(stlmnt[indx] != null && !oathrecord[i][indx] && OR(stlmnt[indx].getType(), types)){
            temp = new LinkedList<String>(Arrays.asList("Oath(" + stlmnt[indx].getName() + " at " + (indx + 1) + ")"));
            if(stlmnt[i].getType() == Stype.Village && (stlmnt[indx].getType() == Stype.Fort || stlmnt[indx].getType() == Stype.City)){
               temp.add("+Defense");
               stlmnt[i].setDefense(stlmnt[i].getDefense().up());
            }else if(stlmnt[indx].getType() == Stype.Village && (stlmnt[i].getType() == Stype.Fort || stlmnt[i].getType() == Stype.City)){
               temp.add("-Defense");
               stlmnt[i].setDefense(stlmnt[i].getDefense().down());
            }else if(stlmnt[i].getType() == Stype.Fort && stlmnt[indx].getType() == Stype.City){
               if(stlmnt[i].getPopulation().isGreater(Population.Shrinking)){
                  temp.add("+Population");
                  stlmnt[i].setPopulation(stlmnt[i].getPopulation().up());
               }else{
                  temp.add("+Defense");
                  stlmnt[i].setDefense(stlmnt[i].getDefense().up());
               }
            }
            stlmnt[i].addTag(temp);
            
            temp = new LinkedList<String>(Arrays.asList("Oath(" + stlmnt[i].getName() + " at " + (i + 1) + ")"));
            if(stlmnt[indx].getType() == Stype.Village && (stlmnt[i].getType() == Stype.Fort || stlmnt[i].getType() == Stype.City)){
               temp.add("+Defense");
               stlmnt[indx].setDefense(stlmnt[indx].getDefense().up());
            }else if(stlmnt[i].getType() == Stype.Village && (stlmnt[indx].getType() == Stype.Fort || stlmnt[indx].getType() == Stype.City)){
               temp.add("-Defense");
               stlmnt[indx].setDefense(stlmnt[indx].getDefense().down());
            }else if(stlmnt[i].getType() == Stype.City && stlmnt[indx].getType() == Stype.Fort){
               if(stlmnt[i].getPopulation().isGreater(Population.Shrinking)){
                  temp.add("+Population");
                  stlmnt[i].setPopulation(stlmnt[i].getPopulation().up());
               }else{
                  temp.add("+Defense");
                  stlmnt[i].setDefense(stlmnt[i].getDefense().up());
               }
            }
            stlmnt[indx].addTag(temp);
            
            oathrecord[i][indx] = true;
            oathrecord[indx][i] = true;
            break;
         }
      }
   }
   
   private static boolean OR(Stype type, Stype... types){
      for(Stype t: types){
         if(t == type){
            return true;
         }
      }
      return false;
   }
   
   private static void establishTwoTrade(int i, Settlement[] stlmnt, boolean[][] traderecord, int distancelimit){
      int indx;
      boolean left, right;//left and right unfound
      left = true;
      right = true;
      List<String> temp;
      for(int j = 1; (j < stlmnt.length) && (left || right) && (j < distancelimit); j++){
         indx = (i - j + stlmnt.length) % stlmnt.length;
         if(left && !traderecord[i][indx] && (stlmnt[indx] != null)){
            stlmnt[i].addTag(Arrays.asList("Trade(" + stlmnt[indx].getName() + " at " + (indx + 1) + ")"));
            temp = new LinkedList<String>(Arrays.asList("Trade(" + stlmnt[i].getName() + " at " + (i + 1) + ")"));
            if(stlmnt[indx].getType() == Stype.Village){
               temp.add("+Prosperity");
               stlmnt[indx].setProsperity(stlmnt[indx].getProsperity().up());
            }
            stlmnt[indx].addTag(temp);
            traderecord[i][indx] = true;
            traderecord[indx][i] = true;
            left = false;
         }
         
         indx = (i + j) % stlmnt.length;
         if(right && !traderecord[i][indx] && (stlmnt[indx] != null)){
            stlmnt[i].addTag(Arrays.asList("Trade(" + stlmnt[indx].getName() + " at " + (indx + 1) + ")"));
            temp = new LinkedList<String>(Arrays.asList("Trade(" + stlmnt[i].getName() + " at " + (i + 1) + ")"));
            if(stlmnt[indx].getType() == Stype.Village){
               temp.add("+Prosperity");
               stlmnt[indx].setProsperity(stlmnt[indx].getProsperity().up());
            }
            stlmnt[indx].addTag(temp);
            traderecord[i][indx] = true;
            traderecord[indx][i] = true;
            right = false;
         }
      }
   }
   
   private static final String[] NAMEPREFIX = new String[]{
      "New",
      "Holm",
      "North",
      "East",
      "South", 
      "West",
      "Arch",
      "Summer",
      "Spring",
      "Winter",
      "Fall",
      "Ports", 
      "Ship", 
      "Path",
      "Red",
      "Orang",
      "Yell",
      "Yellow",
      "Gold",
      "Silver",
      "Iron",
      "Green",
      "Forrest", 
      "Blue",
      "Purple",
      "Brut",
      "Cal",
      "River",
      "Copper",
      "Cotten",
      "Wake",
      "Chester",
      "Staw",
      "Rose",
      "Don", 
      "King",
      "Mayor",
      "Wolf",
      "Deer",
      "Fort",
      "Huth", 
      "Shining",
      "Apple",
      "Snow",
      "Har",
      "Cherry",
      "Sal",
      "Salt"
   };
   
   private static final String[] NAMESUFFIX = new String[]{
      "ton",
      "bury",
      "holt",
      "mouth",
      "stay",
      "caster",
      "mell",
      "stow",
      "field",
      "gill",
      "fair",
      "flower",
      "pool",
      "wick",
      "skirk",
      "cheth",
      "vale",
      "fell",
      "ridge",
      "glass",
      "mere",
      "more", 
      "fon",
      "eve",
      "pine",
      "beck",
      "ham"
   };
   
   //private static boolean[][] checkbox = new boolean[NAMEPREFIX.length][NAMESUFFIX.length];
   private static HashMap<String, List<String>> nametree;
   
   private static List<String> availablePre;
   
   static{ initialize(); }
   
   private static void initialize(){
      nametree = new HashMap<String, List<String>>();
      availablePre = new LinkedList<String>(Arrays.asList(NAMEPREFIX));
      for(String pre: NAMEPREFIX){
         nametree.put(pre, new LinkedList<String>(Arrays.asList(NAMESUFFIX)));
      }
   }
   
   private static String newName(Random ran){
      String pre;
      String suf;
      List<String> temp;
      
      pre = availablePre.get(ran.nextInt(availablePre.size()));
      temp = nametree.get(pre);
      suf = temp.remove(ran.nextInt(temp.size()));
      if(temp.size() <= 0){
         availablePre.remove(pre);
      }
      
      if(availablePre.size() <= 0){
         initialize();
      }
      
      return pre+suf;
   }
   
   private static final String[] LANDMARKPRE = new String[]{
      "an abandoned ",
      "a dragon's ",
      "an old ",
      "a holy ",
      "an eroded ",
      "a haunted ",
      "a large ",
      "a cursed ",
      "a magical ",
      "a secret ",
      "an illusory ",
      "an otherworldly ",
      "a shadowy ",
      "a blessed ",
      "an infernal ",
      "a dark ",
      "an enchanting ",
   };
   
   private static final String[] LANDMARKSUF = new String[]{
      "castle.",
      "skeleton.",
      "forest.",
      "site.",
      "statue.",
      "glen.",
      "mountain.",
      "tower.",
      "lake.",
      "wall.",
      "stonehedge.",
      "cave.",
      "manor.",
      "villa.",
      "hospital.",
      "homestead.",
      "asylum.",
      "school.",
      "prison."
   };
   
   private static Settlement newVillage(Random ran){
      String name, desc;
      name = newName(ran);
      desc = "This settlement is near to " + LANDMARKPRE[ran.nextInt(LANDMARKPRE.length)] + LANDMARKSUF[ran.nextInt(LANDMARKSUF.length)];
      //generate name and desc
      Settlement s = new Settlement(name, desc, Stype.Village);
      s.setProsperity(Prosperity.Inexpensive);
      s.setPopulation(Population.Steady);
      s.setDefense(Defense.Militia);
      List<String> temp = new LinkedList<String>();
      temp.add("Resource(" + RESOURCE[ran.nextInt(RESOURCE.length)] + ")");
      s.addTag(temp);
      
      //unique
      temp = new LinkedList<String>();
      switch (ran.nextInt(7)){
         case 0:
            temp.add("Safe");
            break;
         case 1:
            temp.add("Halfling");
            temp.add("Resource(" + RESOURCE[ran.nextInt(RESOURCE.length)] + ")");
            temp.add("+Prosperity");
            s.setProsperity(s.getProsperity().up());
            break;
         case 2:
            switch (ran.nextInt(4)){
               case 0:
                  temp.add("Personage(General)");
                  temp.add("+Defense");
                  s.setDefense(s.getDefense().up());
                  break;
               case 1:
                  temp.add("Personage(Political)");
                  temp.add("+Prosperity");
                  s.setProsperity(s.getProsperity().up());
                  break;
               case 2:
                  temp.add("Personage(Religious)");
                  temp.add("+Population");
                  s.setPopulation(s.getPopulation().up());
                  break;
               case 3:
                  temp.add("Personage(Arcane)");
                  temp.add("Arcane");
				  break;
               default:
                  break;
            }         
            break;
         case 3:
            temp.add("Exotic(ingredient: Room material)");
            temp.add("+Prosperity");
            s.setProsperity(s.getProsperity().up());
            break;
         case 4:
            temp.add("Divine");
            switch (ran.nextInt(5)){
               case 0:
                  temp.add("History(battle)");
                  break;
               case 1:
                  temp.add("History(miracle)");
                  break;
               case 2:
                  temp.add("History(myth)");
                  break;
               case 3:
                  temp.add("History(romance)");
                  break;
               case 4:
                  temp.add("History(tragedy)");
                  break;
               default:
                  break;
            }
            temp.add("+Population");
            s.setPopulation(s.getPopulation().up());        
            break;
         case 5:
            temp.add("Insular");
            temp.add("+Defense");
            s.setDefense(s.getDefense().up());
            break;
         case 6:
            if(ran.nextBoolean()){
               temp.add("Elven");
               temp.add("Arcane");
            }else{
               temp.add("Dwarven");
               temp.add("Fortified");
            }
            break;
         default:
            break;
      }
      s.addTag(temp);
      
      //problem
      temp = new LinkedList<String>();
      switch(ran.nextInt(9)){
         case 0:
            temp.add("Blight(Bandits/Mafia)");
            temp.add("-Prosperity");
            temp.add("Lawless");
            s.setProsperity(s.getProsperity().down());
            break;
         case 1:
            temp.add("Blight(Arcane)");
            break;
         case 2:
         case 3:
            int i = ran.nextInt(RESOURCE.length);
            temp.add("Need(" + RESOURCE[i] + ")");
            switch(i){
               case 0:
               case 1:
                  temp.add("-Defense");
                  s.setDefense(s.getDefense().down());
                  break;
               case 2:
               case 3:
                  temp.add("-Prosperity");
                  s.setProsperity(s.getProsperity().down());
                  break;
               default:
                  temp.add("-Population");
                  s.setPopulation(s.getPopulation().down());
                  break;            
            }
            break;
         case 4:
            temp.add("Blight(Undead)");
            temp.add("-Defense");
            s.setDefense(s.getDefense().down());
            break;
         case 5:
            temp.add("Blight(Illness)");
            temp.add("-Population");
            s.setPopulation(s.getPopulation().down());
            break;
         case 6:
            temp.add("Need(Water)");
            temp.add("-Prosperity");
            s.setProsperity(s.getProsperity().down());
            break;
         default:
            break;
      }
      s.addTag(temp);
      
      return s;
   }
   
   private static Settlement newTown(Random ran){
      String name, desc;
      name = newName(ran);
      desc = "This settlement is near to " + LANDMARKPRE[ran.nextInt(LANDMARKPRE.length)] + LANDMARKSUF[ran.nextInt(LANDMARKSUF.length)];
      Settlement s = new Settlement(name, desc, Stype.Town);
      s.setProsperity(Prosperity.Moderate);
      s.setPopulation(Population.Steady);
      s.setDefense(Defense.Watch);
      List<String> temp = new LinkedList<String>();
      switch(ran.nextInt(8)){
         case 0:
            temp.add("Craft (with Resource)");
            temp.add("Resource(" + RESOURCE[ran.nextInt(RESOURCE.length)] + ")");
            break;
         case 1:
            temp.add("Market");
            temp.add("+Prosperity");
            s.setProsperity(s.getProsperity().up());
            break;
         case 2:
            switch (ran.nextInt(4)){
               case 0:
                  temp.add("Personage(General)");
                  temp.add("+Defense");
                  s.setDefense(s.getDefense().up());
                  break;
               case 1:
                  temp.add("Personage(Political)");
                  temp.add("+Prosperity");
                  s.setProsperity(s.getProsperity().up());
                  break;
               case 2:
                  temp.add("Personage(Religious)");
                  temp.add("+Population");
                  s.setPopulation(s.getPopulation().up());
                  break;
               case 3:
                  temp.add("Personage(Arcane)");
                  temp.add("Arcane");
				  break;
               default:
                  break;
            }         
            break;
         case 3:
            temp.add("Exotic(ingredient: Room material)");
            temp.add("+Prosperity");
            s.setProsperity(s.getProsperity().up());
            break;
         case 4:
            temp.add("Divine");
            switch (ran.nextInt(5)){
               case 0:
                  temp.add("History(battle)");
                  break;
               case 1:
                  temp.add("History(miracle)");
                  break;
               case 2:
                  temp.add("History(myth)");
                  break;
               case 3:
                  temp.add("History(romance)");
                  break;
               case 4:
                  temp.add("History(tragedy)");
                  break;
               default:
                  break;
            }
            temp.add("+Population");
            s.setPopulation(s.getPopulation().up());
            break;
         case 5:
            switch (ran.nextInt(4)){
               case 0:
                  temp.add("Guild(Theives)");
                  temp.add("Lawless");
                  break;
               case 1:
               case 2:
                  temp.add("Guild(Hunting)");
                  temp.add("Resource(Furs)");
                  break;
               case 3:
                  temp.add("Guild(Blacksmith)");
                  break;
               default:
                  break;
            }
            break;
         case 6:
            switch(ran.nextInt(3)){
               case 0:
                  temp.add("Elven");
                  temp.add("Arcane");
                  break;
               case 1:
                  temp.add("Dwarven");
                  temp.add("Fortified");
                  break;
               case 2:
                  temp.add("Halfling");
                  temp.add("Resource(" + RESOURCE[ran.nextInt(RESOURCE.length)] + ")");
                  temp.add("Resource(" + RESOURCE[ran.nextInt(RESOURCE.length)] + ")");
                  break;
               default:
                  break;
            }
            break;
         case 7:
            temp.add("Strategic Position");
            temp.add("+Defense");
            s.setDefense(s.getDefense().up());
            break;
         default:
            break;
      }
      s.addTag(temp);
      
      //problem
      temp = new LinkedList<String>();
      switch(ran.nextInt(8)){
         case 0:
            temp.add("Blight(Bandits/Mafia)");
            temp.add("-Prosperity");
            temp.add("Lawless");
            s.setProsperity(s.getProsperity().down());
            break;
         case 1:
            temp.add("Blight(Arcane)");
            break;
         case 2:
         case 3:
            int i = ran.nextInt(RESOURCE.length);
            temp.add("Need(" + RESOURCE[i] + ")");
            switch(i){
               case 0:
               case 1:
                  temp.add("-Defense");
                  s.setDefense(s.getDefense().down());
                  break;
               case 2:
               case 3:
                  temp.add("-Prosperity");
                  s.setProsperity(s.getProsperity().down());
                  break;
               default:
                  temp.add("-Population");
                  s.setPopulation(s.getPopulation().down());
                  break;            
            }
            break;
         case 4:
            temp.add("Blight(Undead)");
            temp.add("-Defense");
            s.setDefense(s.getDefense().down());
            break;
         case 5:
            temp.add("Blight(Illness)");
            temp.add("-Population");
            s.setPopulation(s.getPopulation().down());
            break;
         case 6:
            temp.add("Need(Water)");
            temp.add("-Prosperity");
            s.setProsperity(s.getProsperity().down());
            break;
         case 7:
            temp.add("Insecure");
            temp.add("-Prosperity");
            temp.add("+Defense");
            s.setDefense(s.getDefense().up());
            s.setProsperity(s.getProsperity().down());
            break;
         default:
            break;
      }
      s.addTag(temp);

      return s;
   }
   
   private static Settlement newFort(Random ran){
      String name, desc;
      name = newName(ran);
      desc = "This settlement is near to " + LANDMARKPRE[ran.nextInt(LANDMARKPRE.length)] + LANDMARKSUF[ran.nextInt(LANDMARKSUF.length)];
      Settlement s = new Settlement(name, desc, Stype.Fort);
      s.setProsperity(Prosperity.Inexpensive);
      s.setPopulation(Population.Shrinking);
      s.setDefense(Defense.Guard);
      List<String> temp = new LinkedList<String>();
      switch(ran.nextInt(4)){
         case 0:
         case 1:
         case 2:
            temp.add("Need(Supplies)");
            break;
         case 3:
            temp.add("Self-sustaining");
            temp.add("+Population");
            s.setPopulation(s.getPopulation().up());
            break;
         default:
            break;
      }
      s.addTag(temp);
      
      temp = new LinkedList<String>();
      switch(ran.nextInt(6)){
         case 0:
            temp.add("Personage(General)");
            temp.add("+2Defense");
            s.setDefense(s.getDefense().up().up());
            break;
         case 1:
            temp.add("Personage(Political)");
            temp.add("+Prosperity");
            s.setProsperity(s.getProsperity().up());
            temp.add("+Defense");
            s.setDefense(s.getDefense().up());
            break;
         case 2:
            temp.add("Personage(Arcane)");
            temp.add("Arcane");
            temp.add("+Defense");
            s.setDefense(s.getDefense().up());
            break;
         case 3:
            temp.add("Guild(trade)");
            temp.add("+Prosperity");
            s.setProsperity(s.getProsperity().up());
            break;
         case 4:
            temp.add("Personage(Religious)");
            temp.add("Divine");
            switch (ran.nextInt(5)){
               case 0:
                  temp.add("History(battle)");
                  break;
               case 1:
                  temp.add("History(miracle)");
                  break;
               case 2:
                  temp.add("History(myth)");
                  break;
               case 3:
                  temp.add("History(romance)");
                  break;
               case 4:
                  temp.add("History(tragedy)");
                  break;
               default:
                  break;
            }
            temp.add("+Population");
            s.setPopulation(s.getPopulation().up());
            break;
         case 5:
            if(ran.nextBoolean()){
               temp.add("Elven");
               temp.add("Arcane");
            }else{
               temp.add("Dwarven");
               temp.add("Fortified");
            }
            break;
         default:
            break;
      }
      s.addTag(temp);
      
      temp = new LinkedList<String>();
      switch(ran.nextInt(4)){
         case 0:
            temp.add("Safe");
            break;
         case 1:
            temp.add("Stockpile");
            temp.add("+Population");
            s.setPopulation(s.getPopulation().up());
            break;
         case 2:
            switch(ran.nextInt(3)){
               case 0:
                  temp.add("Treasure(Arcane)");
                  break;
               case 1:
                  temp.add("Treasure(Religious)");
                  break;
               case 2:
                  temp.add("Treasure(Historical)");
                  break;
               default:
                  break;
            }
            temp.add("+Defense");
            s.setDefense(s.getDefense().up());
            break;
         case 3:
            temp.add("Resource(" + RESOURCE[ran.nextInt(RESOURCE.length)] + ")");
            temp.add("+Prosperity");
            s.setProsperity(s.getProsperity().up());
            break;
         default:
            break;
      }
      s.addTag(temp);
       
      temp = new LinkedList<String>();
      switch(ran.nextInt(7)){
         case 0:
            temp.add("Blight(Bandits/Mafia)");
            temp.add("-Prosperity");
            temp.add("Lawless");
            s.setProsperity(s.getProsperity().down());
            break;
         case 1:
            temp.add("Blight(Arcane)");
            break;
         case 2:
         case 3:
            int i = ran.nextInt(RESOURCE.length);
            temp.add("Need(" + RESOURCE[i] + ")");
            switch(i){
               case 0:
               case 1:
                  temp.add("-Defense");
                  s.setDefense(s.getDefense().down());
                  break;
               case 2:
               case 3:
                  temp.add("-Prosperity");
                  s.setProsperity(s.getProsperity().down());
                  break;
               default:
                  temp.add("-Population");
                  s.setPopulation(s.getPopulation().down());
                  break;            
            }
            break;
         case 4:
            temp.add("Blight(Undead)");
            temp.add("-Defense");
            s.setDefense(s.getDefense().down());
            break;
         case 5:
            temp.add("Blight(Illness)");
            temp.add("-Population");
            s.setPopulation(s.getPopulation().down());
            break;
         case 6:
            temp.add("Need(Water)");
            temp.add("-Prosperity");
            s.setProsperity(s.getProsperity().down());
            break;
         default:
            break;
      }
      s.addTag(temp);
      
      return s;
   }
   
   private static Settlement newCity(Random ran){
      String name, desc;
      name = newName(ran);
      desc = "This settlement is near to " + LANDMARKPRE[ran.nextInt(LANDMARKPRE.length)] + LANDMARKSUF[ran.nextInt(LANDMARKSUF.length)];
      Settlement s = new Settlement(name, desc, Stype.City);
      s.setProsperity(Prosperity.Moderate);
      s.setPopulation(Population.Steady);
      s.setDefense(Defense.Guard);
      List<String> temp = new LinkedList<String>();
      temp.add("Market");
      switch(ran.nextInt(6)){
         case 0:
            temp.add("Guild(Blacksmith)");
            break;
         case 1:
            temp.add("Guild(Textile)");
            break;
         case 2:
            temp.add("Guild(Bakers)");
            break;
         case 3:
            temp.add("Guild(Masons)");
            break;
         case 4:
            temp.add("Guild(Apothecaries)");
            break;
         case 5:
            temp.add("Guild(Carpenters)");
            break;
         default:
            break;
      }
      s.addTag(temp);
      
      temp = new LinkedList<String>();
      //something unique
      switch(ran.nextInt(6)){
         case 0:
            temp.add("Personage(Arcane)");
            temp.add("Arcane");  
            temp.add("Power(Arcane School)");
            break;
         case 1:
            switch(ran.nextInt(3)){
               case 0:
                  temp.add("Dwarven");
                  temp.add("Fortified");
                  break;
               case 1:
                  temp.add("Elven");
                  temp.add("Arcane");
                  break;
               case 2:
                  temp.add("Halfling");
                  temp.add("Resource(" + RESOURCE[ran.nextInt(RESOURCE.length)] + ")");
                  temp.add("Resource(" + RESOURCE[ran.nextInt(RESOURCE.length)] + ")");
                  temp.add("Resource(" + RESOURCE[ran.nextInt(RESOURCE.length)] + ")");
                  break;
               //extra cases if mixed cities wanted
               case 3:
                  temp.add("Dwarven");
                  temp.add("Elven");
                  break;
               case 4:
                  temp.add("Dwarven");
                  temp.add("Halfling");
                  break;
               case 5:
                  temp.add("Elven");
                  temp.add("Halfling");
                  break;
               case 6:
                  temp.add("Dwarven");
                  temp.add("Elven");
                  temp.add("Halfling");
                  break;
               default:
                  break;
            }
            break;
         case 2:
            temp.add("Personage(Political)");
            temp.add("+Defense");
            temp.add("+Prosperity");
            s.setDefense(s.getDefense().up());
            s.setProsperity(s.getProsperity().up());
            break;
         case 3:
            temp.add("Personage(Merchant)");
            temp.add("Craft(with Resource)");
            temp.add("Resource(" + RESOURCE[ran.nextInt(RESOURCE.length)] + ")");
            temp.add("Exotic(ingredient: Room material)");
            temp.add("+2Prosperity");
            s.setProsperity(s.getProsperity().up().up());
            break;
         case 4:
            temp.add("Personage(Religious)");
            temp.add("Divine");
            switch (ran.nextInt(5)){
               case 0:
                  temp.add("History(battle)");
                  break;
               case 1:
                  temp.add("History(miracle)");
                  break;
               case 2:
                  temp.add("History(myth)");
                  break;
               case 3:
                  temp.add("History(romance)");
                  break;
               case 4:
                  temp.add("History(tragedy)");
                  break;
               default:
                  break;
            }
            temp.add("+Population");
            s.setPopulation(s.getPopulation().up());
            break;
         case 5:
            temp.add("Personage(General)");
            temp.add("Fortified");
            temp.add("+2Defense");
            s.setDefense(s.getDefense().up().up());
            break;
         case 6:
            temp.add("Democracy");
            temp.add("Personage(Politicion)");
            switch(ran.nextInt(3)){
               case 0:
                  temp.add("-Defense");
                  temp.add("+Population");
                  s.setDefense(s.getDefense().down());
                  s.setPopulation(s.getPopulation().up());
                  break;
               case 1:
                  temp.add("+Prosperity");
                  temp.add("-Population");
                  s.setProsperity(s.getProsperity().up());
                  s.setPopulation(s.getPopulation().down());
                  break;
               case 2:
                  temp.add("+Defense");
                  temp.add("-Prosperity");
                  s.setProsperity(s.getProsperity().down());
                  s.setDefense(s.getDefense().up());
				  break;
               default:
                  break;
            }
            break;
         default:
            break;
      }
      s.addTag(temp);
      
      //fame
      temp = new LinkedList<String>();
      switch(ran.nextInt(4)){
         case 0:
            temp.add("Safe");
            break;
         case 1:
            temp.add("Sprawl");
            temp.add("+Population");
            s.setPopulation(s.getPopulation().up());
            break;
         case 2:
            switch(ran.nextInt(3)){
               case 0:
                  temp.add("Treasure(Arcane)");
                  break;
               case 1:
                  temp.add("Treasure(Religious)");
                  break;
               case 2:
                  temp.add("Treasure(Historical)");
                  break;
               default:
                  break;
            }
            temp.add("+Defense");
            s.setDefense(s.getDefense().up());
            break;
         case 3:
            switch(ran.nextInt(3)){
               case 0:
                  temp.add("Art(Architecture)");
                  break;
               case 1:
                  temp.add("Art(Music)");
                  break;
               case 2:
                  temp.add("Art(Cuisine)");
				  break;
               default:
                  break;
            }
            temp.add("+Prosperity");
            s.setProsperity(s.getProsperity().up());
            break;
         default:
            break;
      }
      s.addTag(temp);     
      
      //problem
      temp = new LinkedList<String>();
      switch(ran.nextInt(8)){
         case 0:
            temp.add("Blight(Bandits/Mafia)");
            temp.add("-Prosperity");
            temp.add("Lawless");
            s.setProsperity(s.getProsperity().down());
            break;
         case 1:
            temp.add("Blight(Arcane)");
            temp.add("-Defense");
            s.setDefense(s.getDefense().down());
            break;
         case 2:
         case 3:
            int i = ran.nextInt(RESOURCE.length);
            temp.add("Need(" + RESOURCE[i] + ")");
            switch(i){
               case 0:
               case 1:
                  temp.add("-Defense");
                  s.setDefense(s.getDefense().down());
                  break;
               case 2:
               case 3:
                  temp.add("-Prosperity");
                  s.setProsperity(s.getProsperity().down());
                  break;
               default:
                  temp.add("-Population");
                  s.setPopulation(s.getPopulation().down());
                  break;            
            }
            break;
         case 4:
            temp.add("Blight(Undead)");
            temp.add("-Defense");
            s.setDefense(s.getDefense().down());
            break;
         case 5:
            temp.add("Blight(Illness)");
            temp.add("-Population");
            s.setPopulation(s.getPopulation().down());
            break;
         case 6:
            temp.add("Need(Water)");
            temp.add("-Population");
            s.setPopulation(s.getPopulation().down());
            break;
         case 7:
            temp.add("Totalitarian");
            temp.add("-Prosperity");
            s.setProsperity(s.getProsperity().down());
			break;
         default:
            break;
      }
      s.addTag(temp);
      
      return s;
   }

}