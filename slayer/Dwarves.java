package slayer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.Npc;
import org.powerbot.script.wrappers.Tile;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;

public class Dwarves extends SlayerNode{

	public Dwarves(MethodContext ctx) {
		super(ctx);
	}

	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public boolean activate() {
		return (ctx.settings.get(2091)>>slayerbody.push&0x1F)==16 && ctx.settings.get(183)!=0;
	}

	@Override
	public void execute() {
		Tile local = ctx.players.local().getLocation();
		
		if(new Tile(3015,3456,0).distanceTo(local)<35){//loc of dwarf
			teleported = false;
			for(Npc gob: ctx.npcs.select().id(3275,3268,3274,118).nearest().first()){
				if(!ctx.players.local().isInMotion()&& m.getInteractingNPC()==null){
					if(gob.getLocation().distanceTo(local)<7){
						if(!gob.interact("Attack")){
							ctx.camera.turnTo(gob.getLocation().randomize(1, 3));
						}else ctx.game.sleep(Random.nextInt(1000, 2000));
					}else m.clickOnMap(gob.getLocation());
				}else m.fightNPC(gob.getId(), "Attack");
			}
		}else if(teleported){
			m.walkTo(new Tile(3015,3456,0),"dwarf area");//dwarf loc
		}else if(TeleportLode.FALADOR.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.FALADOR.getTeleport(), "Falador");
		
	}

}
