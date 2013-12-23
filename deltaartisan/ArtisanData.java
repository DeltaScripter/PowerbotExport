package deltaartisan;

import org.powerbot.script.wrappers.Tile;

public class ArtisanData {

	
	public enum objects{
		
		CMINECRAFT(24824),ANVIL(24820);
		
		int id;
		objects(int id){
			this.id = id;
			
		}
		public int getID(){
			return id;
		}
		
	}
	public enum itemIndex{
		
		BRONZEBASEPLATE(6),BRONZERAIL(2), BRONZETRACK(10),
		BRONZESPIKES(14),BRONZETRACK60(18),BRONZEjOINT(22),
		BRONZETRACK80(26),BRONZETIE(30),BRONZETRACK100(34);
		
		int id;
		itemIndex(int id){
			this.id = id;
			
		}
		public int getID(){
			return id;
		}
	}
public enum itemNames{
		
		BRONZEBASEPLATE("Bronze base plate"),BRONZERAILS("Bronze rails"),BRONZETRACK("Bronze track 40%"),
		BRONZESPIKES("Bronze spikes"), BRONZETRACK60("Bronze track 60%"), BRONSEJOINT("Bronze joint"),
		BRONZETRACK80("Bronze track 80%"), BRONZETIE("Bronze tie"), BRONZETRACK100("Bronze track 100%");
		
		String id;
		itemNames(String id){
			this.id = id;
			
		}
		public String getName(){
			return id;
		}
	}
	public enum locations{
		
		BYTHREEANVILS(new Tile(3063,9708,0)),BYMINECARTS(new Tile(3061,9714,0));
		Tile tile;
		locations(Tile tile){
			this.tile = tile;
		}
		public Tile getTile(){
			return tile;
		}
	}
	
}
