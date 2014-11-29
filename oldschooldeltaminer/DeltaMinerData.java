package oldschooldeltaminer;

import org.powerbot.script.Tile;



public class DeltaMinerData {

	public static int copperRocks[] = {23130,23128};

public enum rocks{
	COPPER(23128,23130);
	int id;
	
	rocks(int id,int id2){
		this.id = id;
	}
	public int getID(){
		return id;
	}
	

}
	
}
