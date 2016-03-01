/* Terrain executable
*  By: Devon Call
*/

package terrain;

import java.util.Random;

public class MapGenerator{
	
	public static void main(String[] args){
		Terrain[][] map =  new Terrain[17][38];
		Terrain[] allTerrains = new Terrain[]{Terrain.MOUNTAIN, Terrain.BADLAND, Terrain.DESERT, 
										Terrain.PLAIN, Terrain.SWAMP, Terrain.LAKE, Terrain.FOREST};
		Random ran = new Random();
		//Initialize
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
		//fill
		for(int i = 1; i < map.length - 1; i++){
			for(int j = 2; j < map[i].length - 2; j++){
				map[i][j] = allTerrains[ran.nextInt(allTerrains.length)];
			}
		}
		
		//simulate
		for(int k = 0; k < 100; k++){
			for(int i = 1; i < map.length - 1; i++){
				for(int j = 2; j < map[i].length - 2; j++){
					map[i][j] = map[i][j].update(new Terrain[]{map[i - (j+1)%2][j-1],
													map[i - (j+1)%2][j+1],
													map[i][j-2],
													map[i][j+2],
													map[i + j%2][j-1],
													map[i + j%2][j+1]}, ran);
				}
			}
		}
		
		//print
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j += 2){
				sb.append(map[i][j].toLetter());
				sb.append("   ");
			}
			sb.append("\n  ");
			for(int j = 1; j < map[i].length; j += 2){
				sb.append(map[i][j].toLetter());
				sb.append("   ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}