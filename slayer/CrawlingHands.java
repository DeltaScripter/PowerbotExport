package slayer;

import org.powerbot.script.Area;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Npc;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;
public class CrawlingHands extends SlayerNode{

	public CrawlingHands(ClientContext ctx) {
		super(ctx);
	}

	private Tile baseTile;
	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public boolean activate() {
		return slayerbody.currentTask.contains("crawling hand") && ctx.varpbits.varpbit(183)!=0;
	}

	Area otherSide = new Area(new Tile[] { new Tile(3405, 3457, 0), new Tile(3407, 3531, 0), new Tile(3390, 3553, 0), 
			new Tile(3402, 3576, 0), new Tile(3456, 3575, 0), new Tile(3512, 3450, 0) });
	
	Tile[]toCrawlingHands = new Tile[] {
			new Tile(3517,3515,0), new Tile(3499,3519,0), new Tile(3479,3518),
			new Tile(3460,3515,0), new Tile(3443,3514,0), new Tile(3426,3512),
			new Tile(3422,3529,0), new Tile(3411,3542,0)
	};
	
@Override
	public void execute() {
		//System.out.println("Crawling hands");
	Tile local = ctx.players.local().tile();
		
		if(new Tile(3404,3557,0).distanceTo(local)<35){
			teleported = false;
			baseTile = null;
			for(Npc gob: ctx.npcs.select().id(1651,1650,1655,1648,1657).nearest().first()){
			    m.fightNPC(gob.id(), "Attack");
			}
		}else if(teleported){
			
			if(!m.handleDoor(new Tile(3319,3468,0),24370,"Open"))
				m.simpleWalk(toCrawlingHands, "Walking to Slayer Tower");
		}else if(TeleportLode.CANIFIS.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.CANIFIS.getTeleport(), "Canifis");
		
	}

}
