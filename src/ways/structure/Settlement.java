/* Settlement object
*  By: Devon Call
*/

package structure;

import java.util.HashSet;

public class Settlement extends ElementObj{
	
	private SettlementType type;
	private HashSet<Stag> tags;
	
	public Settlement(String name, String desc, SettlementType type){
		super(name, desc);
		this.type = type;
		tags = new HashSet<Stag>();
	}
	
	public SettlementType getType(){
		return type;
	}
	
	public void setType(SettlementType type){
		this.type = type;
	}
}