package slayer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Tile;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;

public class Icefiends extends SlayerNode{

	public Icefiends(MethodContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return (ctx.settings.get(2091)>>slayerbody.push&0x1F)==7&&ctx.settings.get(183)!=0;
	}

	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public void execute() {
		Tile local = ctx.players.local().getLocation();
		
		if(new Tile(3005,3480,0).distanceTo(local)<20){//loc
			teleported = false;
			m.fightNPC(7716, "Attack");
		}else if(teleported){
			m.walkTo(new Tile(3005,3480,0),"icefiend area");//river-side
		}else if(TeleportLode.FALADOR.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.FALADOR.getTeleport(), "Falador");
		
	}

}
