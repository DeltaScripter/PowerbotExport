package unicow;

import org.powerbot.script.wrappers.Tile;

public class UniData {

	public enum npcs{
		
		UNICOW(5603),GUARD(5919);
		
		int id;
		npcs(int id){
			this.id = id;
		}
		public int getID(){
			return id;
		}
		
	}
	public enum anim{
		
		DUNGEONEERINGTELEPORT(13652);
		
		int id;
		anim(int id){
			this.id = id;
		}
		public int getID(){
			return id;
		}
		
	}
	public enum items{
		
		RINGOFDUNGEONEERING(15707),ARDOUGNECAPE1(15345),COWHIDE(1739),
		HORN(237);
		
		int id;
		items(int id){
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
		ARDOUGNE(22,"Ardougne"),BURTHHORPE(23,"Burthorpe"),CATHERBY(24,"Catherby"),DRAYNOR(25,"Draynor"),
		FALADOR(27,"Falador"),LUMBRIDGE(28,"Lumbridge"),PORTSARIM(29,"Port Sarim"),
		SEERS(30,"Seers"),TAVERLY(31,"Taverly"),VARROCK(32,"Varrock"),YANILLE(33,"Yanille");
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
	
	
}
