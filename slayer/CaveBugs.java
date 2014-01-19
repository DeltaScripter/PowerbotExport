package slayer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.Npc;
import org.powerbot.script.wrappers.Tile;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;

public class CaveBugs extends SlayerNode{

	public CaveBugs(MethodContext ctx) {
		super(ctx);
	}

	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public boolean activate() {
		return (ctx.settings.get(2091)>>slayerbody.push&0x1F)==14&&ctx.settings.get(183)!=0;
	}

	@Override
	public void execute() {//1831-1832
		Tile local = ctx.players.local().getLocation();
		
		if(new Tile(3182,9548,0).distanceTo(local)<25){
			for(Npc gob: ctx.npcs.select().id(1832).nearest().first()){
				if(!ctx.players.local().isInMotion()&& m.getInteractingNPC()==null){
					if(gob.getLocation().distanceTo(local)<7){
						if(!gob.interact("Attack")){
							ctx.camera.turnTo(gob.getLocation().randomize(1, 3));
						}else ctx.game.sleep(Random.nextInt(1000, 2000));
					}else m.clickOnMap(gob.getLocation());
				}else m.fightNPC(gob.getId(), "Attack");
			}
		}else if(m.objIsNotNull(78695)){//in cave?
			teleported = false;
			m.clickOnMap(new Tile(3182,9548,0));//Bugs loc
		}else if(new Tile(3176,3161,0).distanceTo(local)<7){
			m.interactO(5947, "Climb-down", "Cave");
		}else if(teleported){
			m.walkTo(new Tile(3176,3161,0),"lumbridge swamp cave");//cave
		}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.LUMBRIDGE.getTeleport(), "Lumbridge");
		
	}

}
