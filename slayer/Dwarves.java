package slayer;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Npc;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;
public class Dwarves extends SlayerNode{

	public Dwarves(ClientContext ctx) {
		super(ctx);
	}

	Tile[] myTiles = new Tile[] { new Tile(2965, 3401, 0), new Tile(2969, 3406, 0), new Tile(2974, 3410, 0), 
			new Tile(2979, 3414, 0), new Tile(2985, 3417, 0), new Tile(2989, 3422, 0), 
			new Tile(2990, 3428, 0), new Tile(2995, 3432, 0), new Tile(3001, 3432, 0), 
			new Tile(3007, 3431, 0), new Tile(3013, 3433, 0), new Tile(3014, 3439, 0), 
			new Tile(3015, 3445, 0), new Tile(3014, 3451, 0) };
	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public boolean activate() {
		return slayerbody.currentTask=="dwarves" && ctx.varpbits.varpbit(183)!=0;
	}

	@Override
	public void execute() {
		Tile local = ctx.players.local().tile();
		
		if(new Tile(3015,3456,0).distanceTo(local)<40){//loc of dwarf
			teleported = false;
			for(Npc gob: ctx.npcs.select().id(3275,3268,3274,118).nearest().first()){
				if(!ctx.players.local().inMotion()&& m.getInteractingNPC()==null){
					if(gob.tile().distanceTo(local)<7){
						if(!gob.tile().matrix(ctx).reachable()){
							m.interactO(1530, "Open","Door");
						}else
						if(!gob.interact("Attack")){
							ctx.camera.turnTo(gob.tile().derive(1, 3));
						}else m.sleep(Random.nextInt(1000, 2000));
					}else m.clickOnMap(gob.tile());
				}else m.fightNPC(gob.id(), "Attack");
			}
		}else if(teleported){
			m.simpleWalk(myTiles, "Walking to dwarves");
		//m.walkTo(new Tile(3015,3456,0),"dwarf area");//dwarf loc
		}else if(TeleportLode.FALADOR.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.FALADOR.getTeleport(), "Falador");
		
	}

}
