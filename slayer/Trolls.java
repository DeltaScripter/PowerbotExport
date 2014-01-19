package slayer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Tile;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;

public class Trolls extends SlayerNode{

	public Trolls(MethodContext ctx) {
		super(ctx);
	}

	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public boolean activate() {
		return (ctx.settings.get(2091)>>slayerbody.push&0x1F)==23&&ctx.settings.get(183)!=0;
	}

	@Override
	public void execute() {
		Tile local = ctx.players.local().getLocation();
		
		if(new Tile(2206,4397,0).distanceTo(local)<17){//troll area
			m.fightNPC(14981, "Attack");
		}else
		if(new Tile(2208,4364,0).getMatrix(ctx).isReachable()){//cave area
			teleported = false;
			m.clickOnMap(new Tile(2206,4397,0));//troll area
		}else if(new Tile(2879,3573,0).distanceTo(local)<7){//outside cave area
			m.interactO(66877, "Enter", "Cave");
		}else if(teleported){
			m.walkTo(new Tile(2879,3573,0),"cave entrance");//cave entrance
		}else if(TeleportLode.BURTHORPE.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.BURTHHORPE.getTeleport(), "Burthorpe");
		
	}

}
