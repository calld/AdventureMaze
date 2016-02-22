/* Element interface
*  By Devon Call
*/
package ways.structure;
import java.util.List;

public interface Element{
   String getName();
   String getDesc();
   String getLongDesc();//Desc + notes
   String getShortDesc();
   Element addNote(String note);
   List<String> getNotes();
   Element removeNote(int id);
   String getSaveString();
   void init(WorldState ws);
}