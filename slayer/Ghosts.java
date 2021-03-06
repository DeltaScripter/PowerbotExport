package slayer;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Npc;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;
public class Ghosts extends SlayerNode{

	public Ghosts(ClientContext ctx) {
		super(ctx);
		
	}
	Tile[] ghost = new Tile[] {
			new Tile(2877, 3443, 0), new Tile(2883, 3439, 0), new Tile(2883, 3433, 0), 
			new Tile(2882, 3427, 0), new Tile(2884, 3421, 0), new Tile(2889, 3417, 0), 
			new Tile(2895, 3414, 0), new Tile(2901, 3413, 0), new Tile(2901, 3407, 0), 
			new Tile(2897, 3402, 0), new Tile(2891, 3400, 0), new Tile(2886, 3396, 0), 
			new Tile(2884, 3395, 0) };

	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public boolean activate() {
		return slayerbody.currentTask=="ghosts" && ctx.varpbits.varpbit(183)!=0;
	}

	@Override
	public void execute() {
		Tile local = ctx.players.local().tile();
		
		if(new Tile(2895,9848,0).distanceTo(local)<30){//ghost area
			teleported = false;
				  for(Npc ghost: ctx.npcs.select().nearest().id(5342,5343,5344,5345,5346,5347).first()){
					 m.fightNPC(ghost.id(), "Attack");
				  }
		}else if(m.objIsNotNull(74990)){//in cave
			m.clickOnMap(new Tile(2895,9848,0));
		}else if(new Tile(2887,3394,0).distanceTo(local)<7){//cave entrance
			m.interactO(66991, "Climb-down", "Cave");
		}else if(teleported){
			m.simpleWalk(ghost, "Walking to Taverly dungeon");
			//m.walkTo(new Tile(2888,3394,0),"cave entrance");//cave entrance
		}else if(TeleportLode.TAVERLY.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.TAVERLY.getTeleport(), "Taverly");
		
	}

}
