/*package divination;

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
public enum TeleportLode{
		
		VARROCK(new Tile(3214,3378,0)),FALADOR(new Tile(2965,3402, 0)), LUMMBRIDGE(new Tile(3233,3221, 0)),
		BURTHORPE(new Tile(2899,3543, 0)),CATHERBY(new Tile(2831,3451,0)),PORTSARIM(new Tile(3011,3217,0)),
		DRAYNOR(new Tile(3107,3298,0)),ARDOUGNE(new Tile(2634,3348,0)), YANILLE(new Tile(2530,3095,0)),
		SEERS( new Tile(2689,3482,0)),TAVERLY(new Tile(2877,3441,0));
		Tile tile;
		TeleportLode(Tile tile){
			this.tile = tile;
		}
		public Tile getTile(){
			return tile;
		}
	}
	public enum TeleportType{
		ARDOUGNE(41,"Ardougne"),BURTHHORPE(42,"Burthorpe"),CATHERBY(43,"Catherby"),DRAYNOR(44,"Draynor"),
		FALADOR(46,"Falador"),LUMBRIDGE(47,"Lumbridge"),PORTSARIM(48,"Port Sarim"),
		SEERS(49,"Seers"),TAVERLY(50,"Taverly"),VARROCK(51,"Varrock"),YANILLE(52,"Yanille");
		int type;
		String name;
		int numMatch[] = {40,41,42,43,44,45,46,47,48,49,50,51,52,53};
		
		TeleportType(int type, String name){
			this.type = type;
			this.name = name;
		}
		 public int getTeleport(){
			 return type;
		 }
		 public String getName(){
			 return name;
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
*/