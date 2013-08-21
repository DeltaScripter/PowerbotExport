package divination;

import org.powerbot.script.wrappers.Tile;

public class DivineData {

	
	public enum wisps{
		
		PALEWISP(18150),PALESPRING(18173);
		
		int id;
		wisps(int id){
			this.id = id;
			
		}
		public int getID(){
			return id;
		}
		
	}
	public enum rifts{
		
		LUMMBRIDGE(87306, new Tile(3120,3218,0));
		
		int id;
		Tile loc;
		rifts(int id, Tile loc){
			this.id = id;
			this.loc = loc;
			
		}
		public int getID(){
			return id;
		}
		public Tile getTile(){
			return loc;
		}
		
	}
	public enum memories{
		
		PALEMEMORY(29384);
		
		int id;
		memories(int id){
			this.id = id;
			
		}
		public int getID(){
			return id;
		}
		
	}
	public enum convert{
		
		DIVINEENERGY(1),DIVINITYEXP(6),BOTH(7);
		
		int id;
		convert(int id){
			this.id = id;
			
		}
		public int getID(){
			return id;
		}
		
	}
	public enum animation{
		
		HARVESTINGSPRING(21231),HARVESTINGSPRING2(21228),
		TODIVINEENERGY(21232),TODIVINEEXP(21234);//'both' uses 21234
		
		int id;
		animation(int id){
			this.id = id;
			
		}
		public int getID(){
			return id;
		}
		
	}
	
}
