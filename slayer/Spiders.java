package slayer;

import org.powerbot.script.Area;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Npc;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;
public class Spiders extends SlayerNode{

	public Spiders(ClientContext ctx) {
		super(ctx);
	}

	Tile[] pathToSpiders = new Tile[] { 
			new Tile(3225, 3219, 0), new Tile(3231, 3220, 0), new Tile(3234, 3226, 0), 
			new Tile(3240, 3226, 0), new Tile(3246, 3225, 0), new Tile(3251, 3229, 0), 
			new Tile(3246, 3233, 0), new Tile(3244, 3235, 0) };
	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public boolean activate() {
		return slayerbody.currentTask=="spider" && ctx.varpbits.varpbit(183)!=0;
	}

	@Override
	public void execute() {
		Tile local = ctx.players.local().tile();
		
		if(new Tile(3248,3236,0).distanceTo(local)<18){
			teleported = false;
			m.fightNPC(12352, "Attack" );//spiders 
		}else if(teleported){
			m.simpleWalk(pathToSpiders, "Walking to spiders");
			//m.walkTo(new Tile(3248,3236,0),"lumbridge river");//river-side
		}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.LUMBRIDGE.getTeleport(), "Lumbridge");
	}

}
