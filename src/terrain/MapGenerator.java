/* Terrain executable
*  By: Devon Call
*/

package terrain;

import java.util.Random;

public class MapGenerator{
	
	public static void main(String[] args){
		Terrain[][][] map = new Terrain[10000][17][38];
		//Terrain[][] next_map = new Terrain[17][38]; //same as map
		Terrain[] allTerrains = new Terrain[]{Terrain.MOUNTAIN, Terrain.BADLAND, Terrain.DESERT, 
										Terrain.PLAIN, Terrain.SWAMP, Terrain.LAKE, Terrain.FOREST};
		Random ran = new Random();
		//Initialize
		init(map[0]);
		//init(next_map);
		//fill
		for(int i = 1; i < map[0].length - 1; i++){
			for(int j = 2; j < map[0][i].length - 2; j++){
				map[0][i][j] = allTerrains[ran.nextInt(allTerrains.length)];
			}
		}
		
		//simulate
		for(int k = 0; k < map.length-1; k++){
			init(map[k+1]);
			for(int i = 1; i < map[k].length - 1; i++){
				for(int j = 2; j < map[k][i].length - 2; j++){
					map[k+1][i][j] = map[k][i][j].update(new Terrain[]{map[k][i - (j+1)%2][j-1],
													map[k][i - (j+1)%2][j+1],
													map[k][i][j-2],
													map[k][i][j+2],
													map[k][i + j%2][j-1],
													map[k][i + j%2][j+1]}, ran);
				}
			}
		}
		
		//print
		StringBuilder sb = new StringBuilder();
		for(int k = 0; k < map.length; k++){
			sb.append("map at time: " + k + "\n");
			for(int i = 0; i < map[k].length; i++){
				for(int j = 0; j < map[k][i].length; j += 2){
					sb.append(map[k][i][j].toLetter());
					sb.append("   ");
				}
				sb.append("\n  ");
				for(int j = 1; j < map[k][i].length; j += 2){
					sb.append(map[k][i][j].toLetter());
					sb.append("   ");
				}
				sb.append("\n");
			}
			sb.append("\n\n\n\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static Terrain[][] init(Terrain[][] map){
		//Top Edge
		for(int i = 0; i < map[0].length; i += 2 ){
			map[0][i] = Terrain.EDGE;
		}
		for(int i = 1; i < map[0].length; i += 2){
			map[0][i] = Terrain.EDGE;
		}
		//Bottom Edge
		for(int i = 1; i < map[map.length - 1].length; i += 2){
			map[map.length - 1][i] = Terrain.EDGE;
		}
		for(int i = 0; i < map[map.length - 1].length; i += 2){
			map[map.length - 1][i] = Terrain.EDGE;
		}
		//Sides
		for(int i = 0; i < map.length; i++){
			map[i][0] = Terrain.EDGE;
			map[i][1] = Terrain.EDGE;
			map[i][map[i].length - 1] = Terrain.EDGE;
			map[i][map[i].length - 2] = Terrain.EDGE;
		}
		return map;
	}
}