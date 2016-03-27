/* SettlementType enum
*  By: Devon Call
*/

package structure;

public enum SettlementType{
	VILLAGE(2, 1, 1), TOWN(2, 2, 2), FORT(1, 1, 3), CITY(2, 2, 3);
	
	private int[] inits = new int[3];
	
	private SettlementType(int pop, int pros, int def){
		inits[0] = pop;
		inits[1] = pros;
		inits[2] = def;
	}
	
	public int[] getInits(){
		int[] temp = new int[]{inits[0], inits[1], inits[2]};		
		return temp;
	}
	
	public SettlementType update(int pop, int pros, int def){
		switch (this){
			case VILLAGE:
				break;
			default:
				return this;
				break;
		}
	}
}