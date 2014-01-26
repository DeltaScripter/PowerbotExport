package slayer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.Npc;
import org.powerbot.script.wrappers.Tile;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;

public class Dogs extends SlayerNode{

	public Dogs(MethodContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return (ctx.settings.get(2091)>>slayerbody.push&0x1F)==6&&ctx.settings.get(183)!=0;
	}

	Tile[] myTiles = new Tile[] { new Tile(2633, 3349, 0), new Tile(2633, 3343, 0), new Tile(2635, 3337, 0), 
			new Tile(2639, 3332, 0), new Tile(2645, 3330, 0), new Tile(2648, 3324, 0), 
			new Tile(2650, 3318, 0), new Tile(2652, 3312, 0), new Tile(2651, 3306, 0), 
			new Tile(2645, 3306, 0), new Tile(2639, 3306, 0), new Tile(2635, 3311, 0), 
			new Tile(2635, 3313, 0) };
	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public void execute() {//2636,3312
	Tile local = ctx.players.local().getLocation();
		
		if(new Tile(2636,3312,0).distanceTo(local)<35){
			teleported = false;
			for(Npc gob: ctx.npcs.select().id(99).nearest().first()){
				if(!ctx.players.local().isInMotion()&& m.getInteractingNPC()==null){
					if(gob.getLocation().distanceTo(local)<15){
						if(!gob.interact("Attack")){
							ctx.camera.turnTo(gob.getLocation().randomize(1, 3));
						}else ctx.game.sleep(Random.nextInt(1000, 2000));
					}else m.clickOnMap(gob.getLocation());
				}else m.fightNPC(gob.getId(), "Attack");
			}
		}else if(teleported){
			m.simpleWalk(myTiles, "Walking to dogs");
			//m.walkTo(new Tile(2636,3312,0),"Dog area");//Dog area
		}else if(TeleportLode.ARDOUGNE.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.ARDOUGNE.getTeleport(), "Ardougne");
		
	}

}
