package slayer;

import org.powerbot.script.Area;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Npc;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;
public class Icefiends extends SlayerNode{

	public Icefiends(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return slayerbody.currentTask=="icefiend"&&ctx.varpbits.varpbit(183)!=0;
	}

	Tile[] myTiles = new Tile[] { new Tile(2967, 3407, 0), new Tile(2970, 3413, 0), new Tile(2975, 3417, 0), 
			new Tile(2981, 3420, 0), new Tile(2987, 3424, 0), new Tile(2989, 3430, 0), 
			new Tile(2995, 3432, 0), new Tile(3001, 3432, 0), new Tile(3007, 3432, 0), 
			new Tile(3011, 3437, 0), new Tile(3015, 3442, 0), new Tile(3020, 3446, 0), 
			new Tile(3023, 3452, 0), new Tile(3026, 3458, 0), new Tile(3029, 3464, 0), 
			new Tile(3031, 3470, 0), new Tile(3025, 3473, 0), new Tile(3019, 3476, 0), 
			new Tile(3016, 3470, 0), new Tile(3010, 3472, 0), new Tile(3007, 3478, 0), 
			new Tile(3005, 3479, 0) };
	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public void execute() {
		Tile local = ctx.players.local().tile();
		
		if(new Tile(3005,3480,0).distanceTo(local)<20){//loc
			teleported = false;
			m.fightNPC(7716, "Attack");
		}else if(teleported){
			m.simpleWalk(myTiles, "Walking to icefiends");
			//m.walkTo(new Tile(3005,3480,0),"icefiend area");//river-side
		}else if(TeleportLode.FALADOR.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.FALADOR.getTeleport(), "Falador");
		
	}

}
