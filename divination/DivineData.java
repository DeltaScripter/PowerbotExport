package divination;

import org.powerbot.script.wrappers.Tile;

public class DivineData {

	
	public enum wisps{
		
		PALEWISP(18150,"Pale wisp"),PALESPRING(18173,"Pale spring"),
		FLICKERINGWISP(18151,"Flickering wisp"),FLICKERINGSPRING(18174,"Flickering spring"),ENRICHEDFLICKERINGWISP(18152,""),
		BRIGHTWISP(9999,"Bright wisp"),BRIGHTSPRING(9999,"Bright spring");
		
		int id;
		String name;
		wisps(int id, String name){
			this.id = id;
			this.name = name;
			
		}
		public int getID(){
			return id;
		}
		public String getName(){
			return name;
		}
		
	}
	public enum rifts{
		
		LUMMBRIDGE(87306, new Tile(3120,3218,0)),FALADOR(87306, new Tile(3004,3402,0));
		
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
		
		PALEMEMORY(29384,"Pale memory"),PALEENERGY(29313, "Pale energy"),
		FLICKERINGMEMORY(29385,"Flickering memory"),FLICKERINGENERGY(29314,"Flickering energy"),ENRICHEDFLICKERINGMEMORY(29396,"don't know"),
		BRIGHTMEMORY(29386,"Bright memory"),BRIGHTENERGY(29315,"Bright energy");
		
		int id;
		String name;
		memories(int id, String name){
			this.id = id;
			this.name = name;
			
		}
		public int getID(){
			return id;
		}
		public String getName(){
			return name;
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
