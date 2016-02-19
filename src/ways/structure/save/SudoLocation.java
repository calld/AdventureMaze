/* Sudo Location
* By: Devon Call
*/

package ways.structure.save;

import ways.structure.Element;
import ways.structure.Location;
import ways.structure.Passage;
import ways.structure.Group;
import ways.structure.Direction;
import ways.structure.Season;
import ways.structure.Faction;
import java.util.List;

public class SudoLocation extends SudoContainer implements Location{

  public SudoLocation(String name){
    super(name);
  }

  private Location getActual() throws UnsupportedOperationException{
     Element temp = lookup();
     Location c = null;
     if(temp instanceof Location){
        c = (Location) temp;
     }else{
        if(temp != null){throw new UnsupportedOperationException(this.name + " is not an instance of a Location");}
     }
     return c;
  }

  public List<Passage> getExits(){
    return getActual().getExits();
  }
  public Passage addPassage(Direction dir, Passage pas){
    return getActual().addPassage(dir, pas);
  }
  public Passage getPassage(Direction dir){
    return getActual().getPassage(dir);
  }
  public List<Group> getParties(){
    return getActual().getParties();
  }
  public void addParty(Group gr){
    getActual().addParty(gr);
  }
  public void removeParty(Group gr){
    getActual().removeParty(gr);
  }
  public Season getSeason(){
    return getActual().getSeason();
  }
  public void setSeason(Season s){
    getActual().setSeason(s);
  }
  public Faction getFaction(){
    return getActual().getFaction();
  }
  public void setFaction(Faction f){
    getActual().setFaction(f);
  }
}
