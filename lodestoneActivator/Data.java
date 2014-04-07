package lodestoneActivator;

import org.powerbot.script.Tile;


public class Data {



	
	public enum TeleportLode{
		
		VARROCK(new Tile(3214,3378,0)),FALADOR(new Tile(2965,3402, 0)), LUMMBRIDGE(new Tile(3233,3221, 0)),
		BURTHORPE(new Tile(2899,3543, 0)),CATHERBY(new Tile(2831,3451,0)),PORTSARIM(new Tile(3011,3217,0)),
		DRAYNOR(new Tile(3107,3298,0)),ARDOUGNE(new Tile(2634,3348,0)), YANILLE(new Tile(2530,3095,0)),
		SEERS( new Tile(2689,3482,0)),TAVERLY(new Tile(2877,3441,0)),ALHARID(new Tile(3298,3184,0));
		Tile tile;
		TeleportLode(Tile tile){
			this.tile = tile;
		}
		public Tile getTile(){
			return tile;
		}
	}
	public enum TeleportType{
		ARDOUGNE(22,"Ardougne"),BURTHHORPE(23,"Burthorpe"),CATHERBY(24,"Catherby"),DRAYNOR(25,"Draynor"),
		FALADOR(27,"Falador"),LUMBRIDGE(28,"Lumbridge"),PORTSARIM(29,"Port Sarim"),
		SEERS(30,"Seers"),TAVERLY(31,"Taverly"),VARROCK(32,"Varrock"),YANILLE(33,"Yanille");
		int type;
		String name;
		TeleportType(int type, String name){
			this.type = type;
		}
		 public int getTeleport(){
			 return type;
		 }
	}
	
}
