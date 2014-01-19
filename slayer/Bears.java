package slayer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Tile;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;

public class Bears extends SlayerNode{

	public Bears(MethodContext ctx) {
		super(ctx);
	}

	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public boolean activate() {
		return (ctx.settings.get(2091)>>slayerbody.push&0x1F)==10 && ctx.settings.get(183)!=0;
	}

	@Override
	public void execute() {
		Tile local = ctx.players.local().getLocation();
		
		if(new Tile(2696,3344,0).distanceTo(local)<25){
			teleported = false;
			m.fightNPC(105, "Attack");//bears 
		}else if(teleported){
			m.walkTo(new Tile(2696,3344,0),"bear area");//river-side
		}else if(TeleportLode.ARDOUGNE.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.ARDOUGNE.getTeleport(), "Ardougne");
	}

}
