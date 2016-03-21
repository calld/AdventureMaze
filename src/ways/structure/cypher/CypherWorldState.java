/* Cypher World State
*  By: Devon Call
*/

package ways.structure.cypher;

import ways.structure.WorldState;
import ways.structure.Faction;
import ways.structure.Location;
import java.util.Scanner;

public class CypherWorldState extends WorldState{

	public CypherWorldState(){}

	public CypherWorldState(Scanner data) throws Exception{
		data.useDelimiter("[<>]+");
		while(data.hasNext()){
			loadElement(data.next(), data);
		}
		initElements();
	}

	protected void loadElement(String type, Scanner data) throws Exception{
		FieldRecord info;
		switch(type){
			case "CypherMaterial":
				info = readElement(data);
				readBasicItem(data, info);
				readCypherMaterial(data, info);
				putElement(new CypherMaterial(info.name, info.desc, info.notes, info.field[0]));
				break;
			case "Cypher":
				info = readElement(data);
				readBasicItem(data, info);
				readCypher(data, info);
				putElement(new Cypher(info.name, info.desc, info.notes, info.field[0]));
				break;
			case "CypherPassage":
				break;
			case "CypherNPC":
				break;
			default:
				super.loadElement(type, data);
				break;
		}
	}
	
	protected void readCypher(Scanner data, FieldRecord fr){
		fr.b = true;
		while(data.hasNext() && fr.b){
			switch(data.next().toLowerCase()){
				case "level":
					fr.field[0] = data.next();
					data.next();
					fr.b = false;
					break;
				default:
					break;
			}
		}
	}

	protected void readCypherMaterial(Scanner data, FieldRecord fr){
		fr.b = true;
		while(data.hasNext() && fr.b){
			switch(data.next().toLowerCase()){
				case "level":
					fr.field[0] = data.next();
					data.next();
					fr.b = false;
					break;
				default:
					break;
			}
		}
	}

	public Cypher makeNewCypher(String name, String desc, int level){
		if(check(name)){
			return (Cypher) putElement(new Cypher(name, desc, level));
		}
		return null;
	}

	public CypherMaterial makeNewCypherMaterial(String name, String desc, int level){
		if(check(name)){
			return (CypherMaterial) putElement(new CypherMaterial(name, desc, level));
		}
		return null;
	}

	public CypherPassage makeNewCypherPassage(String name, String desc, Location dest, int level){
		if(check(name)){
			return (CypherPassage) putElement(new CypherPassage(name, desc, dest, level));
		}
		return null;
	}

	public CypherNPC makeNewCypherNPC(String name, String desc, Faction fac, int level, int health, int armor, int damage, Distance movement, int difficulty){
		if(check(name)){
			return (CypherNPC) putElement(new CypherNPC(name, desc, level, health, armor, damage, movement, difficulty));
		}
		return null;
	}
}
