package slayer;

import org.powerbot.script.Area;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Npc;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;
public class Goblins extends SlayerNode{

	public Goblins(ClientContext ctx) {
		super(ctx);
	}

	Tile[] pathToGoblins = new Tile[] { 
			new Tile(3225, 3219, 0), new Tile(3231, 3220, 0), new Tile(3234, 3226, 0), 
			new Tile(3240, 3226, 0), new Tile(3246, 3225, 0), new Tile(3251, 3229, 0), 
			new Tile(3246, 3233, 0), new Tile(3244, 3235, 0) };
	@Override
	public boolean activate() {
		return slayerbody.currentTask=="goblins" && ctx.varpbits.varpbit(183)!=0;
	}

	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public void execute() {
   Tile local = ctx.players.local().tile();
   
		if(new Tile(3248,3236,0).distanceTo(local)<20){
			teleported = false;
			for(Npc gob: ctx.npcs.select().id(11240,11253,12355,12356,12357,11238).nearest().first()){
				if(gob.tile().matrix(ctx).reachable())
				if(!ctx.players.local().inMotion()&& m.getInteractingNPC()==null){
					if(gob.tile().distanceTo(local)<7 ){
						System.out.println("Reachable tile for goblin? "+gob.tile().matrix(ctx).reachable());
						if(!gob.interact("Attack")){
							ctx.camera.turnTo(gob.tile().derive(1, 3));
						}else m.sleep(Random.nextInt(1000, 2000));
					}else m.clickOnMap(gob.tile());
				}else m.fightNPC(gob.id(), "Attack");
			}
		}else if(teleported){
			m.simpleWalk(pathToGoblins, "Walking to goblins");
			//m.walkTo(new Tile(3248,3236,0),"lumbridge river");//river-side
		}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.LUMBRIDGE.getTeleport(), "Lumbridge");
		
	}

}
