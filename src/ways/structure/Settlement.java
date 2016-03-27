/* Settlement object
*  By: Devon Call
*/

package structure;

import java.util.HashSet;

public class Settlement extends ElementObj{
	
	private SettlementType type;
	private HashSet<Stag> tags;
	
	private Population pop;
	private Prosperity pros;
	private Defense def;
	
	
	public Settlement(String name, String desc, SettlementType type){
		super(name, desc);
		this.type = type;
		tags = new HashSet<Stag>();
		int[] tmp = type.getInits();
		pop = Population.get(tmp[0]);
		pros = Prosperity.get(tmp[1]);
		def = Defense.get(tmp[2]);
	}
	
	public SettlementType getType(){
		return type;
	}
	
	public void setType(SettlementType type){
		this.type = type;
	}
	
	private int[] getNetMod(){
		int[] tmp;
		int[] res = new int[3];
		for(Stag t: tags){
			tmp = t.getMods();
			res[0] += tmp[0];
			res[1] += tmp[1];
			res[2] += tmp[2];
		}
		return res;
	}
	
	public boolean update(){
		
	}
	
	public void addTag(Stag tag){
		tags.add(tag);
	}
	
	protected static enum Prosperity{
		None(0), Inexpensive(1), Moderate(2), Expensive(3), VeryExpensive(4), Exorbitant(5);
      
		private static final Prosperity[] ALL;
		private int pos;
      
		static{
			ALL = new Prosperity[6];
			ALL[0] = None;
			ALL[1] = Inexpensive;
			ALL[2] = Moderate;
			ALL[3] = Expensive;
			ALL[4] = VeryExpensive;
			ALL[5] = Exorbitant;
		}
      
		private Prosperity(int i){
			pos = i;
		}

		public Prosperity up(){
			this.change(1);
		}

		public Prosperity down(){
			return this.change(-1);
		}
		
		public Prosperity change(int d){
			try{
				return ALL[pos + d];
			}catch(ArrayIndexOutOfBoundsException e){
				if (d < 0){
					return ALL[0];
				}else{
					return ALL[ALL.length - 1];
				}
			}
		}
		
		public static Prosperity get(int i){
			try{
				return ALL[i];
			}catch(ArrayIndexOutOfBoundsException e){
				if (i < 0){
					return ALL[0];
				}else{
					return ALL[ALL.length - 1];
				}
			}
		}
	}
	
	protected static enum Population{
		Exodus(0), Shrinking(1), Steady(2), Growing(3), Booming(4);

		private static final Population[] ALL;
		private final int pos;

		static{
			ALL = new Population[5];
			ALL[0] = Exodus;
			ALL[1] = Shrinking;
			ALL[2] = Steady;
			ALL[3] = Growing;
			ALL[4] = Booming;
		}

		private Population(int i){
			pos = i;
		}
		
		public Population up(){
			this.change(1);
		}

		public Population down(){
			return this.change(-1);
		}
		
		public Population change(int d){
			try{
				return ALL[pos + d];
			}catch(ArrayIndexOutOfBoundsException e){
				if (d < 0){
					return ALL[0];
				}else{
					return ALL[ALL.length - 1];
				}
			}
		}

		public boolean isGreater(Population other){
			return this.pos > other.pos;
		}
		
		public static Population get(int i){
			try{
				return ALL[i];
			}catch(ArrayIndexOutOfBoundsException e){
				if (i < 0){
					return ALL[0];
				}else{
					return ALL[ALL.length - 1];
				}
			}
		}
	}

	protected static enum Defense{
		None(0), Militia(1), Watch(2), Guard(3), Garrison(4), Battalion(5), Legion(6);

		private static final Defense[] ALL;
		private final int pos;

		static{
			ALL = new Defense[7];
			ALL[0] = None;
			ALL[1] = Militia;
			ALL[2] = Watch;
			ALL[3] = Guard;
			ALL[4] = Garrison;
			ALL[5] = Battalion;
			ALL[6] = Legion;
		}

		private Defense(int i){
			pos = i;
		}
		
		public Defense up(){
			this.change(1);
		}

		public Defense down(){
			return this.change(-1);
		}
		
		public Defense change(int d){
			try{
				return ALL[pos + d];
			}catch(ArrayIndexOutOfBoundsException e){
				if (d < 0){
					return ALL[0];
				}else{
					return ALL[ALL.length - 1];
				}
			}
		}
		
		public static Defense get(int i){
			try{
				return ALL[i];
			}catch(ArrayIndexOutOfBoundsException e){
				if (i < 0){
					return ALL[0];
				}else{
					return ALL[ALL.length - 1];
				}
			}
		}
	}
}