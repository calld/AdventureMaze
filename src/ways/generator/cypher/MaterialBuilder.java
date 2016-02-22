/* magic materials list constructor
*  By: Devon Call
*/

package ways.generator.cypher;
import java.util.List;
import java.util.ArrayList;
import ways.structure.cypher.CypherMaterial;
import ways.structure.cypher.CypherWorldState;
import ways.structure.Item;

public class MaterialBuilder{
   private MaterialBuilder(){}
   
   public static List<List<Item>> getMaterials(CypherWorldState world){
         List<List<Item>> materialTable = new ArrayList<List<Item>>();
         for(int i = 0; i < 8; i++){
            materialTable.add(new ArrayList<Item>());
         }
         
         List<Item> l;
         //lvl 3 items
         l = materialTable.get(0);
         l.add(world.makeNewCypherMaterial("Temp Stat Pool (might)", "This material is used to supply a temporary status pool for might.", 3));
         l.add(world.makeNewCypherMaterial("Temp Stat Pool (speed)", "This material is used to supply a temporary status pool for speed.", 3));
         l.add(world.makeNewCypherMaterial("Temp Stat Pool (int)", "This material is used to supply a temporary status pool for intelligence.", 3));
         l.add(world.makeNewCypherMaterial("General Antitoxin", "This material adds the property of nullifiing poisons", 3));
         l.add(world.makeNewCypherMaterial("General Anesthetic", "material puts you to sleep", 3));
         l.add(world.makeNewCypherMaterial("Poison", "material makes result toxic", 3));
         l.add(world.makeNewCypherMaterial("Magic Detection Copper", "Copper attracted to sources of magic", 3));
         l.add(world.makeNewCypherMaterial("Sealing Silver", "Silver that is very effective at sealing magic", 3));
         l.add(world.makeNewCypherMaterial("Fire Resistant Hide", "hide that is resistant to high tempurature", 3));
         l.add(world.makeNewCypherMaterial("Cold Resistant Hide", "hide that is resistant to cold tempurature", 3));
         l.add(world.makeNewCypherMaterial("Magically Responsive Ink", "Ink that works well with magic", 3));
         l.add(world.makeNewCypherMaterial("Long Lasting Parchmant", "A long lasting material that works well as a writing surface.", 3));
         l.add(world.makeNewCypherMaterial("Living Clay", "Clay that makes flexibile pottery", 3));
         l.add(world.makeNewCypherMaterial("Iron Wood", "Wood as hard as iron.", 3));
         //lvl 4 items
         l = materialTable.get(1);
         l.add(world.makeNewCypherMaterial("Temp Stat Edge (might)", "This material gives a temporary edge in might", 4));
         l.add(world.makeNewCypherMaterial("Temp Stat Edge (speed)", "This material gives a temporary edge in speed", 4));
         l.add(world.makeNewCypherMaterial("Temp Stat Edge (int)", "This material gives a temporary edge in intelligence", 4));
         l.add(world.makeNewCypherMaterial("Memory Alter", "This material effects memory", 4));
         l.add(world.makeNewCypherMaterial("Recover Might", "restores might pool", 4));
         l.add(world.makeNewCypherMaterial("Recover Speed", "restores speed pool", 4));
         l.add(world.makeNewCypherMaterial("Recover Int", "restores intelligence pool", 4));
         l.add(world.makeNewCypherMaterial("Steel Fiber", "super strong threads", 4));
         l.add(world.makeNewCypherMaterial("Cold Iron", "Iron that destroys magic", 4));
         l.add(world.makeNewCypherMaterial("Magnite", "metal with magical properties similar to magnetism. Magnite Forge: slag falls out", 4));
         l.add(world.makeNewCypherMaterial("Entangled Dust", "material that can be effected and effect at a distance.", 4));
         
         //lvl 5 items
         l = materialTable.get(2);
         l.add(world.makeNewCypherMaterial("Greater Poison", "very strong poison", 5));
         l.add(world.makeNewCypherMaterial("Elemental Material (fire)", "This material can be used to inbue with fire", 5));
         l.add(world.makeNewCypherMaterial("Elemental Material (water)", "This material can be used to inbue with water", 5));
         l.add(world.makeNewCypherMaterial("Elemental Material (earth)", "This material can be used to inbue with earth", 5));
         l.add(world.makeNewCypherMaterial("Elemental Material (air)", "This material can be used to inbue with air", 5));
         l.add(world.makeNewCypherMaterial("Scaled Hide", "Hide that provides extra Armor", 5));
         l.add(world.makeNewCypherMaterial("High Quality Meat", "This meat is a delicacy, Lots of people would trade anything for a taste.", 5));
         l.add(world.makeNewCypherMaterial("Floating Wood", "Wood lighter than air", 5));
         
         //lvl 6 items
         l = materialTable.get(3);
         l.add(world.makeNewCypherMaterial("Black Crystallized Emotion (apathy)", "solidified apathy", 6));
         l.add(world.makeNewCypherMaterial("Yellow Crystallized Emotion (fear)", "solidified fear", 6));
         l.add(world.makeNewCypherMaterial("Blue Crystalized Emotion (sadness)", "solidified sadness", 6));
         l.add(world.makeNewCypherMaterial("Golden Apple", "can be eaten as an action to make a free recovery roll", 6));
         l.add(world.makeNewCypherMaterial("Blood Iron", "Iron Ore formed from heoglobim", 6));
         
         //lvl 7 items
         l = materialTable.get(4);
         l.add(world.makeNewCypherMaterial("Orange Crystallized Emotion (lust)", "solidified lust", 7));
         l.add(world.makeNewCypherMaterial("Purple Crystallized Emotion (compassion)", "solidified compassion", 7));
         l.add(world.makeNewCypherMaterial("Pink Crystallized Emotion (happiness)", "solidified happiness", 7));
         l.add(world.makeNewCypherMaterial("Imperial Gold", "a golden metal with strength that is greater than iron and easily enchanted.", 7));
         
         //lvl 8 items
         l = materialTable.get(5);
         l.add(world.makeNewCypherMaterial("Red Crystallized Emotion (anger)", "solidified anger", 8));
         l.add(world.makeNewCypherMaterial("Green Crystallized Emotion (bravery)", "solidified bravery", 8));
         l.add(world.makeNewCypherMaterial("Mythril", "light metal with high strength; armor will encomber at -1.", 8));
         
         //lvl 9 items
         l = materialTable.get(6);
         l.add(world.makeNewCypherMaterial("Solid Fire", "Fire that is solid", 9));
         l.add(world.makeNewCypherMaterial("Cold Fire", "Fire that freezes rather than burns", 9));
         l.add(world.makeNewCypherMaterial("Liquid Air", "air in a liquid form at room tempurature, can be drunk instead of breathing.", 9));
         l.add(world.makeNewCypherMaterial("Solid Dust", "weightless earth", 9));
         l.add(world.makeNewCypherMaterial("Adamantine", "Extremely hard and resistant metal", 9));
         
         //lvl 10 items
         l = materialTable.get(7);
         l.add(world.makeNewCypherMaterial("Divine Nectar", "liquid consentrate of divinity", 10));
         
         
      return materialTable;
   }

}