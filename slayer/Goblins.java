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

	@Override
	public boolean activate() {
		return (ctx.settings.get(2091)>>slayerbody.push&0x1F)==5 && ctx.settings.get(183)!=0;
	}

	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public void execute() {
   Tile local = ctx.players.local().getLocation();
		
		if(new Tile(3248,3236,0).distanceTo(local)<18){
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
			m.walkTo(new Tile(3248,3236,0),"lumbridge river");//river-side
		}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.LUMBRIDGE.getTeleport(), "Lumbridge");
		
	}

}
