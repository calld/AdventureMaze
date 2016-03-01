/* Terrain Enum
*  By: Devon Call
*/

package terrain;

import java.util.Random;

public enum Terrain{
	MOUNTAIN(new int[]{8, 2, 1, 0, 0, 1, 2}), 
	BADLAND(new int[]{2, 8, 2, 1, 0, 0, 1}), 
	DESERT(new int[]{1, 2, 8, 2, 1, 0, 0}),
	PLAIN(new int[]{0, 1, 2, 8, 2, 1, 0}), 
	SWAMP(new int[]{0, 0, 1, 2, 8, 2, 1}), 
	LAKE(new int[]{1, 0, 0, 1, 2, 8, 2}), 
	FOREST(new int[]{2, 1, 0, 0, 1, 2, 8}), 
	EDGE(new int[]{2, 2, 2, 2, 2, 2, 2});
	
	private int[] adjacency;
	
	private Terrain(int[] adjacency){
		this.adjacency = adjacency;
	}
	
	public String toLetter(){
		switch(this){
			case MOUNTAIN:
				return "M";
			case BADLAND:
				return "B";
			case DESERT:
				return "D";
			case PLAIN:
				return "P";
			case SWAMP:
				return "S";
			case LAKE:
				return "L";
			case FOREST:
				return "F";
			default:
				return "E";
		}
	}
	
	public Terrain update(Terrain[] adj, Random ran){
		if(Terrain.EDGE == this){
			return Terrain.EDGE;
		}
		int[] updateTable = new int[]{0, 0, 0, 0, 0, 0, 0};
		addArray(updateTable, this.adjacency);
		addArray(updateTable, this.adjacency);
		addArray(updateTable, this.adjacency);
		for(Terrain temp: adj){
			addArray(updateTable, temp.adjacency);
		}
		for(int i = 1; i < 7; i++){
			updateTable[i] += updateTable[i-1];
		}
		int i = ran.nextInt(updateTable[6]);
		if(i < updateTable[0]){
			return Terrain.MOUNTAIN;
		}else if(i < updateTable[1]){
			return Terrain.BADLAND;
		}else if(i < updateTable[2]){
			return Terrain.DESERT;
		}else if(i < updateTable[3]){
			return Terrain.PLAIN;
		}else if(i < updateTable[4]){
			return Terrain.SWAMP;
		}else if(i < updateTable[5]){
			return Terrain.LAKE;
		}else{
			return Terrain.FOREST;
		}
	}
	
	private void addArray(int[] current, int[] update){
		for(int i = 0; i < 7; i++){
			current[i] += update[i];
		}
	}
}