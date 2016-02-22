/* Cypher World State
*  By: Devon Call
*/

package ways.structure.cypher;

import ways.structure.WorldState;
import java.util.Scanner;

public class CypherWorldState extends WorldState{
	
	public CypherWorldState(){}
	
	public CypherWorldState(Scanner data){
		data.setDelimiter("[<>]");
		while(data.hasNext()){
			loadElement(data.next(), data);
		}
		initElements();
	}
	
	protected void loadElement(String type, Scanner data){
		FieldRecord info;
		switch(type){
			case "CypherMaterial":
				break;
			case "Cypher":
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
			return (CypherNPC) putElement(new CypherNPC(name, desc, fac, level, health, armor, damage, movement, difficulty));
		}
		return null;
	}
}