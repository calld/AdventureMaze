/* Faction Interface
*  By: Devon Call
*/

package ways.structure;
import java.util.List;

public interface Faction extends Element{
   List<Faction> getEnemies();
   List<Faction> getAllies();
   Faction addEnemy(Faction f);
   Faction addAlly(Faction f);
   Faction removeEnemy(Faction f);
   Faction removeAlly(Faction f);
   boolean isEnemy(Faction f);
   boolean isAlly(Faction f);
   Demeanor getDemeanor();
}