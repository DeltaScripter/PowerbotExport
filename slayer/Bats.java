package slayer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Tile;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;

public class Bats extends SlayerNode{

	public Bats(MethodContext ctx) {
		super(ctx);
	}

	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public boolean activate() {
		return (ctx.settings.get(2091)>>slayerbody.push&0x1F)==11 && ctx.settings.get(183)!=0;
	}

	@Override
	public void execute() {//2581,3481
		Tile local = ctx.players.local().getLocation();
		
		if(new Tile(2755,3406,0).distanceTo(local)<25){//bat area
			m.fightNPC(78, "Attack");//bats
		}else if(teleported){
			m.walkTo(new Tile(2755,3406,0),"bat area");//bat area
		}else if(TeleportLode.CATHERBY.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.CATHERBY.getTeleport(), "Catherby");
	}

}
