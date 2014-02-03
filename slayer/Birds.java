package slayer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.Npc;
import org.powerbot.script.wrappers.Tile;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;

public class Birds extends SlayerNode{

	public Birds(MethodContext ctx) {
		super(ctx);
	}

	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	Tile[] myTiles = new Tile[] { new Tile(3013, 3215, 0), new Tile(3019, 3218, 0), new Tile(3025, 3218, 0), 
			new Tile(3027, 3224, 0), new Tile(3027, 3230, 0), new Tile(3028, 3235, 0) };
	@Override
	public boolean activate() {
		return (slayerbody.currentTask=="birds" && ctx.settings.get(183)!=0);
	}

	@Override
	public void execute() {
	Tile local = ctx.players.local().getLocation();
		
		if(new Tile(3029,3236,0).distanceTo(local)<18){//bird loc
			teleported = false;
			for(Npc gob: ctx.npcs.select().id(6115,6116).nearest().first()){
				if(!ctx.players.local().isInMotion()&& m.getInteractingNPC()==null){
					if(gob.getLocation().distanceTo(local)<7){
						if(!gob.interact("Attack")){
							ctx.camera.turnTo(gob.getLocation().randomize(1, 3));
						}else ctx.game.sleep(Random.nextInt(1000, 2000));
					}else m.clickOnMap(gob.getLocation());
				}else m.fightNPC(gob.getId(), "Attack");
			}
		}else if(teleported){
			m.simpleWalk(myTiles, "Walking to the birds");
			//m.walkTo(new Tile(3029,3236,0),"bird area");//port sarim docks
		}else if(TeleportLode.PORTSARIM.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.PORTSARIM.getTeleport(), "Port Sarim");
		
	}

}
