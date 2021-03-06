package slayer;

import org.powerbot.script.Area;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Npc;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;
public class Trolls extends SlayerNode{

	public Trolls(ClientContext ctx) {
		super(ctx);
	}

	Tile[] trolls = new Tile[] { 
			new Tile(2898, 3542, 0), new Tile(2893, 3546, 0), new Tile(2887, 3547, 0), 
			new Tile(2882, 3551, 0), new Tile(2877, 3555, 0), new Tile(2875, 3561, 0), 
			new Tile(2875, 3567, 0), new Tile(2876, 3572, 0) };
	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public boolean activate() {
		return slayerbody.currentTask=="trolls" && ctx.varpbits.varpbit(183)!=0;
	}

	@Override
	public void execute() {
		Tile local = ctx.players.local().tile();
		
		if(new Tile(2206,4397,0).distanceTo(local)<17){//troll area
			m.fightNPC(14981, "Attack");
		}else
		if(new Tile(2208,4364,0).matrix(ctx).reachable()){//cave area
			teleported = false;
			m.clickOnMap(new Tile(2206,4397,0));//troll area
		}else if(new Tile(2879,3573,0).distanceTo(local)<7){//outside cave area
			m.interactO(66877, "Enter", "Cave");
		}else if(teleported){
			m.simpleWalk(trolls, "Walking to troll cave");
			//m.walkTo(new Tile(2879,3573,0),"cave entrance");//cave entrance
		}else if(TeleportLode.BURTHORPE.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.BURTHHORPE.getTeleport(), "Burthorpe");
		
	}

}
