/* Location class
*  By: Devon Call
*/

package ways.structure;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class LocationObj extends ContainerObj implements Location {
   //private Group danger;
   private List<Group> travelers;
   private HashMap<Direction, Passage> passages;
   private double length;
   private double width;
   private Faction fac;
   private Season season;

   protected LocationObj(String name, String desc, double length, double width, Season season){
      super(name, desc, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
      //danger = null;
      travelers = new LinkedList<Group>();
      passages = new HashMap<Direction, Passage>();
      this.length = length;
      this.width = width;
      this.fac = null;
      this.season = season;
   }

   public List<Passage> getExits(){
      return new ArrayList<Passage>(passages.values());
   }

   public Passage addPassage(Direction dir, Passage pas){
      return passages.put(dir, pas);
   }

   public Passage getPassage(Direction dir){
      return passages.get(dir);
   }

   public List<Group> getParties(){
      return new ArrayList<Group>(travelers);
   }

   public void addParty(Group gr){
      gr.getLocation().removeParty(gr);
      gr.setLocation(this);
      travelers.add(gr);
   }

   public void removeParty(Group gr){
      travelers.remove(gr);
   }

   //public Group getDanger(){
      //return danger;
   //}

   /*public void setDanger(Group gr){
      if(danger != null){
         danger.setLocation(null);
      }
      gr.setLocation(this);
      danger = gr;
   }*/

   public Faction getFaction(){
      return fac;
   }

   public void setFaction(Faction f){
      fac = f;
   }

   public Season getSeason(){
      return season;
   }

   public void setSeason(Season s){
	   season = s;
   }

   public String getLongDesc(){
      StringBuilder sb = new StringBuilder();
      sb.append(super.getLongDesc());
      if(season != null){
         sb.append("Season: ");
         sb.append(season.toString());
         sb.append("\n");
      }
      sb.append("Length: ");
      sb.append(String.format("%.2f", length));
      sb.append(" ft Width: ");
      sb.append(String.format("%.2f", width));
      sb.append(" ft\n");
      if(fac != null){
         sb.append("Controlled By: ");
         sb.append(fac.getName());
         sb.append("\n");
      }
      /*if(danger != null){
         sb.append("Defended By: ");
         sb.append(danger.getName());
         sb.append("\n");
      }*/
      sb.append("Exits:\n\tDirection\tLocation\t\tPassage\n");
      Passage p;
      for(Direction d: Direction.getAllDirections()){
         p = passages.get(d);
         if(p != null){
            sb.append("\t");
            sb.append(d.toString());
            if(d.toString().length() < 8){
               sb.append("\t");
            }
            sb.append("\t");
            String temp = p.getShortDesc();
            sb.append(temp);
            if(temp.length() < 17){
               sb.append("\t");
            }
            sb.append("\t");
            sb.append(p.getName());
            sb.append("\n");
         }
      }
      sb.append("Current Groups: ");
      for(Group g: travelers){
         sb.append(g.getName());
         sb.append(", ");
      }
      if(travelers.size() > 0){
         sb.delete(sb.length() - 2, sb.length());
      }else{
         sb.append("</none>");
      }
      sb.append("\n");
      return sb.toString();
   }

   //save/load functions
   private List<String> _dir;
   private List<String> _pas;

   public LocationObj(String name, String desc, List<String> notes, List<String> contents, String len, String wid, String season, List<String> direction, List<String> passage){
      super(name, desc, notes, contents, Double.toString(Double.POSITIVE_INFINITY), Double.toString(Double.POSITIVE_INFINITY));
      //danger = null;
      travelers = new LinkedList<Group>();
      passages = new HashMap<Direction, Passage>();
	  _dir = direction;
	  _pas = passage;
      this.season = Season.valueOf(season.toUpperCase());
      length = Double.valueOf(len);
      width = Double.valueOf(wid);
      fac = null;
   }

   public void init(WorldState ws){
	   super.init(ws);
			String temp;
			Iterator<String> directs = _dir.iterator();
			for(String passage: _pas){
				temp = directs.next();
        //System.out.println(temp + ": " + passage);
				if(ws.getElement(passage) instanceof Passage){
					passages.put(Direction.valueOf(temp.toUpperCase()), (Passage) ws.getElement(passage));
				}
			}
   }

   protected StringBuilder getSaveFields(){
      StringBuilder sb = super.getSaveFields();
      sb.append("<length>");
      sb.append(length);
      sb.append("</length>\n");
      sb.append("<width>");
      sb.append(width);
      sb.append("</width>\n");
      sb.append("<season>");
      sb.append(season);
      sb.append("</season>\n");
      sb.append("<passages>");
      for(Direction direct: passages.keySet()){
         if(passages.get(direct) != null){
            sb.append("<direction>");
            sb.append(direct);
            sb.append("</direction>");
            sb.append("<passage>");
            sb.append(passages.get(direct).getName());
            sb.append("</passage>\n");
         }
      }
      sb.append("</passages>\n");
      return sb;
   }
}
