package slayer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Tile;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;

public class Spiders extends SlayerNode{

	public Spiders(MethodContext ctx) {
		super(ctx);
	}

	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public boolean activate() {
		return (ctx.settings.get(2091)>>slayerbody.push&0x1F)==17 && ctx.settings.get(183)!=0;
	}

	@Override
	public void execute() {
		Tile local = ctx.players.local().getLocation();
		
		if(new Tile(3248,3236,0).distanceTo(local)<18){
			teleported = false;
			m.fightNPC(12352, "Attack");//spiders 
		}else if(teleported){
			m.walkTo(new Tile(3248,3236,0),"lumbridge river");//river-side
		}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.LUMBRIDGE.getTeleport(), "Lumbridge");
	}

}
