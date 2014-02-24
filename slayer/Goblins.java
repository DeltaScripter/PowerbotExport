package slayer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.Npc;
import org.powerbot.script.wrappers.Tile;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;

public class Goblins extends SlayerNode{

	public Goblins(MethodContext ctx) {
		super(ctx);
	}

	Tile[] pathToGoblins = new Tile[] { 
			new Tile(3225, 3219, 0), new Tile(3231, 3220, 0), new Tile(3234, 3226, 0), 
			new Tile(3240, 3226, 0), new Tile(3246, 3225, 0), new Tile(3251, 3229, 0), 
			new Tile(3246, 3233, 0), new Tile(3244, 3235, 0) };
	@Override
	public boolean activate() {
		return slayerbody.currentTask=="goblins" && ctx.settings.get(183)!=0;
	}

	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public void execute() {
   Tile local = ctx.players.local().getLocation();
		
		if(new Tile(3248,3236,0).distanceTo(local)<20){
			teleported = false;
			for(Npc gob: ctx.npcs.select().id(11240,11253,12355,12356,12357).nearest().first()){
				if(!ctx.players.local().isInMotion()&& m.getInteractingNPC()==null){
					if(gob.getLocation().distanceTo(local)<7){
						if(!gob.interact("Attack")){
							ctx.camera.turnTo(gob.getLocation().randomize(1, 3));
						}else ctx.game.sleep(Random.nextInt(1000, 2000));
					}else m.clickOnMap(gob.getLocation());
				}else m.fightNPC(gob.getId(), "Attack");
			}
		}else if(teleported){
			m.simpleWalk(pathToGoblins, "Walking to goblins");
			//m.walkTo(new Tile(3248,3236,0),"lumbridge river");//river-side
		}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.LUMBRIDGE.getTeleport(), "Lumbridge");
		
	}

}
