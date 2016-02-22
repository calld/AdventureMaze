/* Fantasy World State object
*  By: Devon Call
*/

package ways.structure;
import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class WorldState{

	protected class FieldRecord{
		public String name;
		public String desc;
		public List<String> notes;
		public List<String> contents;
		public String sizel;
		public String weightl;
		public List<String> passages;
		public List<String> directions;
		public String[] field = new String[6];
		public boolean b;

		public FieldRecord(){}
	}

	private HashMap<String, Element> allElements = new HashMap<String, Element>();

	private List<Location> map = new ArrayList<Location>();

	public Location add(Location l){
		map.add(l);
		return l;
	}

	public Location get(int i){
		return map.get(i);
	}

	public int mapSize(){
		return map.size();
	}

	public List<Location> getMap(){
		return new ArrayList<Location>(map);
	}
	public WorldState(){}

	public WorldState(Scanner data) throws Exception{
		data.useDelimiter("[<>]+");
		while(data.hasNext()){
			loadElement(data.next(), data);
		}
		initElements();
	}

	protected void initElements(){
		for(Element e: allElements.values()){
			//System.out.println(e.getName() + " init");
			e.init(this);
		}
		map.sort((e1, e2) -> {
			int a = Integer.parseInt(e1.getName().split(":", 2)[0]);
			int b = Integer.parseInt(e2.getName().split(":", 2)[0]);
			return a - b;
		});
	}

	protected void loadElement(String type, Scanner data) throws Exception{
		FieldRecord info;
		switch(type){
			case "LocationObj":
				info = readElement(data);
				readContainer(data, info);
				readLocation(data, info);
				putElement(new LocationObj(info.name, info.desc, info.notes, info.contents, info.field[0], info.field[1], info.field[2], info.directions, info.passages));
				map.add((Location) getElement(info.name));
				break;
			case "PassageObj":
				info = readElement(data);
				readPassage(data, info);
				putElement(new PassageObj(info.name, info.desc, info.notes, info.field[0]));
				break;
			case "BasicItem":
				info = readElement(data);
				readBasicItem(data, info);
				putElement(new BasicItem(info.name, info.desc, info.notes, info.weightl, info.sizel));
				break;
			case "Box":
				info = readElement(data);
				readContainer(data, info);
				readBox(data, info);
				putElement(new Box(info.name, info.desc, info.notes, info.contents, info.sizel, info.weightl, info.field[0], info.field[1]));
				break;
			case "Bag":
				info = readElement(data);
				readContainer(data, info);
				readBag(data, info);
				putElement(new Bag(info.name, info.desc, info.notes, info.contents, info.sizel, info.weightl, info.field[0], info.field[1]));
				break;
			case "FactionObj":
				info = readElement(data);
				readFaction(data, info);
				putElement(new FactionObj(info.name, info.desc, info.notes, info.field[0], info.passages, info.directions));
				break;
			case "Group":
				info = readElement(data);
				readGroup(data, info);
				putElement(new Group(info.name, info.desc, info.notes, info.field[0], info.contents, info.field[1]));
				break;
			default:
				break;
		}
	}

	protected void readGroup(Scanner data, FieldRecord fr){
		fr.b = true;
		while(data.hasNext() && fr.b){
			switch(data.next().toLowerCase()){
				case "faction":
					fr.field[0] = data.next();
					data.next();
					break;
				case "members":
					fr.contents = new ArrayList<String>();
					while(data.hasNext() && fr.b){
						switch(data.next().toLowerCase()){
							case "member":
								fr.contents.add(data.next());
								data.next();
								break;
							case "/members":
								fr.b = false;
								break;
							default:
								break;
						}
					}
					fr.b = true;
					break;
				case "location":
					fr.field[1] = data.next();
					data.next();
					fr.b = false;
					break;
				default:
					break;
			}
		}
	}

	protected void readFaction(Scanner data, FieldRecord fr){
		fr.b = true;
		while(data.hasNext() && fr.b){
			switch(data.next().toLowerCase()){
				case "demeanor":
					fr.field[0] = data.next();
					data.next();
					break;
				case "allies":
					fr.directions = new ArrayList<String>();
					while(data.hasNext() && fr.b){
						switch(data.next().toLowerCase()){
							case "ally":
								fr.directions.add(data.next());
								data.next();
								break;
							case "/allies":
								fr.b = false;
								break;
							default:
								break;
						}
					}
					fr.b = true;
					break;
				case "enemies":
					fr.passages = new ArrayList<String>();
					while(data.hasNext() && fr.b){
						switch(data.next().toLowerCase()){
							case "enemy":
								fr.passages.add(data.next());
								data.next();
								break;
							case "/enemies":
								fr.b = false;
								break;
							default:
								break;
						}
					}
					break;
				default:
					break;
			}
		}
	}

	protected void readBag(Scanner data, FieldRecord fr){
		fr.b = true;
		while(data.hasNext() && fr.b){
			switch(data.next().toLowerCase()){
				case "empty_size":
					fr.field[0] = data.next();
					data.next();
					break;
				case "empty_weight":
					fr.field[1] = data.next();
					data.next();
					fr.b = false;
					break;
				default:
					break;
			}
		}
	}

	protected void readBox(Scanner data, FieldRecord fr){
		fr.b = true;
		while(data.hasNext() && fr.b){
			switch(data.next().toLowerCase()){
				case "size":
					fr.field[0] = data.next();
					data.next();
					break;
				case "empty_weight":
					fr.field[1] = data.next();
					data.next();
					fr.b = false;
					break;
				default:
					break;
			}
		}
	}

	protected void readBasicItem(Scanner data, FieldRecord fr){
		fr.b = true;
		while(data.hasNext() && fr.b){
			switch(data.next().toLowerCase()){
				case "weight":
					fr.weightl = data.next();
					data.next();
					break;
				case "size":
					fr.sizel = data.next();
					data.next();
					fr.b = false;
					break;
				default:
					break;
			}
		}
	}

	protected void readPassage(Scanner data, FieldRecord fr){
		fr.b = true;
		while(data.hasNext() && fr.b){
			switch(data.next().toLowerCase()){
				case "destination":
					fr.field[0] = data.next();
					data.next();
					fr.b = false;
					break;
				default:
					break;
			}
		}
	}

	protected void readLocation(Scanner data, FieldRecord fr){
		fr.b = true;
		while(data.hasNext() && fr.b){
			switch(data.next().toLowerCase()){
				case "length":
					fr.field[0] = data.next();
					data.next();
					break;
				case "width":
					fr.field[1] = data.next();
					data.next();
					break;
				case "season":
					fr.field[2] = data.next();
					data.next();
					break;
				case "passages":
					fr.passages = new ArrayList<String>();
					fr.directions = new ArrayList<String>();
					while(data.hasNext() && fr.b){
						switch(data.next().toLowerCase()){
							case "direction":
								fr.directions.add(data.next());
								data.next();
								break;
							case "passage":
								fr.passages.add(data.next());
								data.next();
								break;
							case "/passages":
								fr.b = false;
								break;
							default:
								break;
						}
					}
					break;
				default:
					break;
			}
		}
	}

	protected void readContainer(Scanner data, FieldRecord fr){
		fr.b = true;
		while(data.hasNext() && fr.b){
			switch(data.next().toLowerCase()){
				case "contents":
					fr.contents = new ArrayList<String>();
					while(data.hasNext() && fr.b){
						switch(data.next().toLowerCase()){
							case "item":
								fr.contents.add(data.next());
								data.next();
								break;
							case "/contents":
								fr.b = false;
								break;
							default:
								break;
						}
					}
					fr.b = true;
					break;
				case "sizelimit":
					fr.sizel = data.next();
					data.next();
					break;
				case "weightlimit":
					fr.weightl = data.next();
					data.next();
					fr.b = false;
					break;
				default:
					break;
			}
		}
	}

	protected FieldRecord readElement(Scanner data){
		FieldRecord fr = new FieldRecord();
		fr.b = true;
		String temp;
		while(data.hasNext() && fr.b){
			temp = data.next();
			switch(temp.toLowerCase()){
				case "name":
					fr.name = data.next();
					data.next();
					break;
				case "desc":
					fr.desc = data.next();
					data.next();
					break;
				case "notes":
					fr.notes = new ArrayList<String>();
					while(data.hasNext() && fr.b){
						switch(data.next().toLowerCase()){
							case "note":
								fr.notes.add(data.next());
								data.next();
								break;
							case "/notes":
								fr.b = false;
								break;
							default:
								break;
						}
					}
					break;
				default:
					break;
			}
		}
		return fr;
	}

	public List<String> completeSave(){
		List<String> sl = new ArrayList<String>();
		for(Element e: allElements.values()){
			sl.add(e.getSaveString());
		}
		return sl;
	}

	public Element getElement(String name){
		return allElements.get(name);
	}

	public List<Element> getAllElements(){
		return new ArrayList<Element>(allElements.values());
	}

	public void dropElement(String name){
		allElements.put(name, null);
	}

	protected Element putElement(Element el){
		allElements.put(el.getName(), el);
		//System.out.println(el.getName() + " created");
		return el;
	}

	protected boolean check(String name){
		return null == allElements.get(name);
	}

	/*public String completeSave(){
		StringBuilder sb = new StringBuilder();
		for(Element e: allElements.values()){
			sb.append(e.getSaveString());
		}
		return sb.toString();
	}*/

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
			return (Faction) putElement(new FactionObj(name, desc, dem));
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
