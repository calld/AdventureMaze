/* ContentGenerator Interface
*  By: Devon Call
*/

package ways.generator;

public interface ContentGenerator{
   boolean newContent(Object... attributes);//call to generate new name and description, accepts a variable number of arguments so that implementations may accept outside info when generating content, may throw UnsupportedOperationException if not given appropiate arguments.
   String getName();//get current name
   String getDesc();//get current description
}