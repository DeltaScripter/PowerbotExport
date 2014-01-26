package slayer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.Npc;
import org.powerbot.script.wrappers.Tile;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;

public class Ogre extends SlayerNode{

	public Ogre(MethodContext ctx) {
		super(ctx);
	}

	Tile[] toOgre = new Tile[] { new Tile(2525, 3095, 0), new Tile(2519, 3098, 0), new Tile(2513, 3100, 0), 
			new Tile(2507, 3101, 0), new Tile(2501, 3096, 0) };
	
	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public boolean activate() {
		return (ctx.settings.get(2091)>>slayerbody.push&0x1F)==28 && ctx.settings.get(183)!=0;
	}

	@Override
	public void execute() {
		Tile local = ctx.players.local().getLocation();
		
		if(new Tile(2498,3096,0).distanceTo(local)<20){
			teleported = false;
			for(Npc gob: ctx.npcs.select().id(3451,3419,115,8658).nearest().first()){
				if(gob.getLocation().getMatrix(ctx).isReachable())
				if(!ctx.players.local().isInMotion()&& m.getInteractingNPC()==null){
					if(gob.getLocation().distanceTo(local)<7){
						if(!gob.interact("Attack")){
							ctx.camera.turnTo(gob.getLocation().randomize(1, 3));
						}else ctx.game.sleep(Random.nextInt(1000, 2000));
					}else m.clickOnMap(gob.getLocation());
				}else m.fightNPC(gob.getId(), "Attack");
			}
		}else if(teleported){
			m.simpleWalk(toOgre, "Walking to ogres");
			//m.walkTo(new Tile(3248,3236,0),"lumbridge river");//river-side
		}else if(TeleportLode.YANILLE.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.YANILLE.getTeleport(), "Yanille");
		
	}

}
