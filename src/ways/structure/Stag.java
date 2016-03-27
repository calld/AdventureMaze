/* Stag object
*  By: Devon Call
*/

package structure;

public class Stag{
	
	private String type;
	private String desc;
	
	private int[] mods = new int[3];
	
	public Stag(String type, String desc, int pop, int pros, int def){
		this.type = type;
		this.desc = desc;
		mods[0] = pop;
		mods[1] = pros;
		mods[2] = def;
	}
	
	public String getType(){
		return type;
	}
	
	public String getDesc(){
		return desc;
	}
	
	public int[] getMods(){
		return new int[]{mods[0], mods[1], mods[2]};
	}
}