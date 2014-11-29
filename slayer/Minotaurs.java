package slayer;

import org.powerbot.script.Area;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Npc;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;
public class Minotaurs extends SlayerNode{

	public Minotaurs(ClientContext ctx) {
		super(ctx);
	}

	Tile[] pathToBarbarianVillage = new Tile[] { 
			new Tile(2965, 3402, 0), new Tile(2966, 3408, 0), new Tile(2970, 3413, 0), 
			new Tile(2976, 3416, 0), new Tile(2982, 3417, 0), new Tile(2988, 3421, 0), 
			new Tile(2990, 3427, 0), new Tile(2994, 3432, 0), new Tile(3000, 3433, 0), 
			new Tile(3006, 3432, 0), new Tile(3012, 3434, 0), new Tile(3015, 3428, 0), 
			new Tile(3020, 3424, 0), new Tile(3026, 3425, 0), new Tile(3032, 3428, 0), 
			new Tile(3036, 3423, 0), new Tile(3037, 3417, 0), new Tile(3041, 3412, 0), 
			new Tile(3047, 3410, 0), new Tile(3053, 3410, 0), new Tile(3058, 3415, 0), 
			new Tile(3064, 3417, 0), new Tile(3070, 3417, 0), new Tile(3076, 3418, 0), 
			new Tile(3079, 3419, 0) };
	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public boolean activate() {
		return slayerbody.currentTask=="minotaur" && ctx.varpbits.varpbit(183)!=0;
	}

	@Override
	public void execute() {
		Tile local = ctx.players.local().tile();
		
		if(new Tile(1870,5226,0).matrix(ctx).reachable()){//minotaur loc
			m.fightNPC(4404, "Attack");//Minotaur
		}else
		if(new Tile(1867,5226,0).matrix(ctx).reachable()){//after first 2 doors
			if(m.byCloseLoc(new Tile(1867,5226,0), 2))//fourth door loc
				m.interactO(16124, "Open", "Door");//fourth door
		}else
		if(new Tile(1859,5234,0).matrix(ctx).reachable()){//after first 2 doors
			if(m.byCloseLoc(new Tile(1864,5226,0), 2))//third door loc
				m.interactO(16124, "Open", "Door");//third door
		}else
		if(new Tile(1858,5238,0).matrix(ctx).reachable()){//in first doors
			if(m.byCloseLoc(new Tile(1859,5236,0), 1))
				m.interactO(16124, "Open", "Door");//second door
		}else
		if(new Tile(1859,5243,0).matrix(ctx).reachable()){//entrance inside cave
			teleported = false;
			if(m.byCloseLoc(new Tile(1858,5239,0), 5))
				m.interactO(16124, "Open", "Door");//first door
		}else if(new Tile(3081,3421,0).distanceTo(local)<7){//outside cave entrance
			m.interactO(16154, "Climb-down", "Hole");
		}else if(teleported){
			m.simpleWalk(pathToBarbarianVillage, "Walking to Barbarian Village");
			//m.walkTo(new Tile(3081,3421,0),"Barbarian Village");//barbarian village
		}else if(TeleportLode.FALADOR.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.FALADOR.getTeleport(), "Falador");
	}

}
