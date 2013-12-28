package divination;

import org.powerbot.script.wrappers.Tile;

public class DivineData {

	
	public enum wisps{
		
		PALEWISP(18150,"Pale wisp"),PALESPRING(18173,"Pale spring"),
		FLICKERINGWISP(18151,"Flickering wisp"),FLICKERINGSPRING(18174,"Flickering spring"),ENRICHEDFLICKERINGWISP(18152,""),
		BRIGHTWISP(9999,"Bright wisp"),BRIGHTSPRING(9999,"Bright spring"),
		GLOWINGWISP(9999,"Glowing wisp"),GLOWINGSPRING(9999,"Glowing spring"),
		SPARKLINGWISP(9999,"Sparkling wisp"),SPARKLINGSPRING(9999,"Sparkling spring"),
		GLEAMINGWISP(9999,"Gleaming wisp"),GLEAMINGSPRING(9999,"Gleaming spring"),
		VIBRANTWISP(9999,"Vibrant wisp"),VIBRANTSPRING(9999,"Vibrant spring"),
		LUSTROUSWISP(9999,"Lustrous wisp"),LUSTROUSSPRING(9999,"Lustrous spring"),
		BRILLIANTWISP(9999,"Brilliant wisp"),BRILLIANTSPRING(9999,"Brilliant spring"),
		RADIANTWISP(9999,"Radiant wisp"),RADIANTSPRING(9999,"Radiant spring"),
		LUMINOUSWISP(9999,"Luminous wisp"),LUMINOUSSPRING(9999,"Luminous spring"),
		INCANDESCENTWISP(9999,"Incandescent wisp"),INCANDESCENTSPRING(9999,"Incandescent spring");
		
		
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
		BRIGHTMEMORY(29386,"Bright memory"),BRIGHTENERGY(29315,"Bright energy"),
		GLOWINGMEMORY(9999,"Glowing memory"),GLOWINGENERGY(29316,"Glowing energy"),
		SPARKLINGMEMORY(9999,"Sparkling memory"),SPARKLINGENERGY(999,"Sparkling energy"),
		GLEAMINGMEMORY(9999,"Gleaming memory"),GLEAMINGENERGY(999,"Gleaming energy"),
		VIBRANTMEMORY(9999,"Vibrant memory"),VIBRANTENERGY(999,"Vibrant energy"),
		LUSTROUSMEMORY(9999,"Lustrous memory"),LUSTROUSENERGY(999,"Lustrous energy"),
		BRILLIANTMEMORY(9999,"Brilliant memory"),BRILLIANTENERGY(999,"Brilliant energy"),
		RADIANTMEMORY(9999,"Radiant memory"),RADIANTENERGY(999,"Radiant energy"),
		LUMINOUSMEMORY(9999,"Luminous memory"),LUMINOUSENERGY(999,"Luminous energy"),
		INCANDESCENTMEMORY(9999,"Incandescent memory"),INCANDESCENTENERGY(999,"Incandescent energy"),;
		
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
		
		DIVINEENERGY(29),DIVINITYEXP(26),BOTH(39);
		
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
