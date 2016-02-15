/* Location Interface
*  By: Devon Call
*/

package ways.structure;

import java.util.List;

public interface Location extends Container, Allied{
   
   List<Passage> getExits();
   Passage addPassage(Direction dir, Passage pas);
   Passage getPassage(Direction dir);
   List<Group> getParties();
   void addParty(Group gr);
   void removeParty(Group gr);
   Season getSeason();
   void setSeason(Season s);
}