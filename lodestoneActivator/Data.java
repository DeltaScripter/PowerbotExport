package lodestoneActivator;

import org.powerbot.script.wrappers.Tile;

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
		ARDOUGNE(41),BURTHHORPE(42),CATHERBY(43),DRAYNOR(44),FALADOR(46),LUMBRIDGE(47),PORTSARIM(48),
		SEERS(49),TAVERLY(50),VARROCK(51),YANILLE(52);
		int type;
		TeleportType(int type){
			this.type = type;
		}
		 public int getTeleport(){
			 return type;
		 }
	}
	
}
