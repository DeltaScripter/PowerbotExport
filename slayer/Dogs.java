package slayer;

import org.powerbot.script.methods.MethodContext;
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

	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public void execute() {//2636,3312
	Tile local = ctx.players.local().getLocation();
		
		if(new Tile(2636,3312,0).distanceTo(local)<25){
			teleported = false;
			m.fightNPC(99, "Attack");//dogs
		}else if(teleported){
			m.walkTo(new Tile(2636,3312,0),"Dog area");//Dog area
		}else if(TeleportLode.ARDOUGNE.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.ARDOUGNE.getTeleport(), "Ardougne");
		
	}

}
