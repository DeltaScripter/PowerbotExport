package slayer;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Npc;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;

public class Birds extends SlayerNode{

	public Birds(ClientContext ctx) {
		super(ctx);
	}

	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	Tile[] myTiles = new Tile[] { new Tile(3013, 3215, 0), new Tile(3019, 3218, 0), new Tile(3025, 3218, 0), 
			new Tile(3027, 3224, 0), new Tile(3027, 3230, 0), new Tile(3028, 3235, 0) };
	@Override
	public boolean activate() {
		return (slayerbody.currentTask=="birds" && ctx.varpbits.varpbit(183)!=0);
	}

	@Override
	public void execute() {
	Tile local = ctx.players.local().tile();
		
		if(new Tile(3029,3236,0).distanceTo(local)<18){//bird loc
			teleported = false;
			for(Npc gob: ctx.npcs.select().id(6115,6116).nearest().first()){
				if(!ctx.players.local().inMotion()&& m.getInteractingNPC()==null){
					if(gob.tile().distanceTo(local)<7){
						if(!gob.interact("Attack")){
							ctx.camera.turnTo(gob.tile().derive(1, 3));
						}else m.sleep(Random.nextInt(1000, 2000));
					}else m.clickOnMap(gob.tile());
				}else m.fightNPC(gob.id(), "Attack");
			}
		}else if(teleported){
			m.simpleWalk(myTiles, "Walking to the birds");
			//m.walkTo(new Tile(3029,3236,0),"bird area");//port sarim docks
		}else if(TeleportLode.PORTSARIM.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.PORTSARIM.getTeleport(), "Port Sarim");
		
	}

}
