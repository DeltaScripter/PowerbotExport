package slayer;

import org.powerbot.script.Area;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Npc;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;
public class Ogre extends SlayerNode{

	public Ogre(ClientContext ctx) {
		super(ctx);
	}

	Tile[] toOgre = new Tile[] { new Tile(2525, 3095, 0), new Tile(2519, 3098, 0), new Tile(2513, 3100, 0), 
			new Tile(2507, 3101, 0), new Tile(2501, 3096, 0) };
	
	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public boolean activate() {
		return slayerbody.currentTask=="ogre" && ctx.varpbits.varpbit(183)!=0;
	}

	@Override
	public void execute() {
		Tile local = ctx.players.local().tile();
		
		if(new Tile(2498,3096,0).distanceTo(local)<20){
			teleported = false;
			for(Npc gob: ctx.npcs.select().id(3451,3419,115,8658).nearest().first()){
				if(gob.tile().matrix(ctx).reachable())
				if(!ctx.players.local().inMotion()&& m.getInteractingNPC()==null){
					if(gob.tile().distanceTo(local)<7){
						if(!gob.interact("Attack")){
							ctx.camera.turnTo(gob.tile().derive(1, 3));
						}else m.sleep(Random.nextInt(1000, 2000));
					}else m.clickOnMap(gob.tile());
				}else m.fightNPC(gob.id(), "Attack");
			}
		}else if(teleported){
			m.simpleWalk(toOgre, "Walking to ogres");
			//m.walkTo(new Tile(3248,3236,0),"lumbridge river");//river-side
		}else if(TeleportLode.YANILLE.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.YANILLE.getTeleport(), "Yanille");
		
	}

}
