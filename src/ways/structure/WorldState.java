/* Fantasy World State object
*  By: Devon Call
*/

package ways.structure;

public class WorldState{

	private HashMap<String, Element> allElements = new HashMap<String, Element>();
	
	public WorldState(){
	}
	
	public Element getElement(String name){
		return allElements.get(name);
	}
	
	public void dropElement(String name){
		allElements.put(name, null);
	}
	
	protected Element putElement(Element el){
		allElements.put(el.getName(), el);
		return el;
	}
	
	protected boolean check(String name){
		return null == allElements.get(name);
	}
	
	public Passage makeNewPassage(String name, String desc, Location dest){
		if(check(name)){
			return (Passage) putElement(new PassageObj(name, desc, dest));
		}
		return null;		
	}
	
	public Location makeNewLocation(String name, String desc, double length, double width, Season season){
		if(check(name)){
			return (Location) putElement(new LocationObj(name, desc, length, width, season));
		}
		return null;
	}
	
	public Item makeNewItem(String name, String desc, double weight, double size){
		if(check(name)){
			return (Item) putElement(new BasicItem(name, desc, weight, size));
		}
		return null;
	}
	
	public Box makeNewBox(String name, String desc, double sizelimit, double weightlimit, double size, double empty){
		if(check(name)){
			return (Box) putElement(new Box(name, desc, sizelimit, weightlimit, size, empty));
		}
		return null;
	}
	
	public Bag makeNewBag(String name, String desc, double sizelimit, double weightlimit, double empty_s, double empty_w){
		if(check(name)){
			return (Bag) putElement(new Bag(name, desc, sizelimit, weightlimit, empty_s, empty_w));
		}
		return null;
	}
	
	public Faction makeNewFaction(String name, String desc, Demeanor dem){
		if(check(name)){
			return (Faciton) putElement(new Faction(name, desc, dem));
		}
		return null;
	}
	
	public Group makeNewGroup(String name, String desc){
		if(check(name)){
			return (Group) putElement(new Group(name, desc));
		}
		return null;
	}
	
	
}