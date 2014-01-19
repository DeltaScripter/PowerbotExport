package slayer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Tile;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;

public class Minotaurs extends SlayerNode{

	public Minotaurs(MethodContext ctx) {
		super(ctx);
	}

	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public boolean activate() {
		return (ctx.settings.get(2091)>>slayerbody.push&0x1F)==8 && ctx.settings.get(183)!=0;
	}

	@Override
	public void execute() {
		Tile local = ctx.players.local().getLocation();
		
		if(new Tile(1870,5226,0).getMatrix(ctx).isReachable()){//minotaur loc
			m.fightNPC(4404, "Attack");//Minotaur
		}else
		if(new Tile(1867,5226,0).getMatrix(ctx).isReachable()){//after first 2 doors
			if(m.byCloseLoc(new Tile(1867,5226,0), 1))//fourth door loc
				m.interactO(16124, "Open", "Door");//fourth door
		}else
		if(new Tile(1859,5234,0).getMatrix(ctx).isReachable()){//after first 2 doors
			if(m.byCloseLoc(new Tile(1864,5226,0), 1))//third door loc
				m.interactO(16124, "Open", "Door");//third door
		}else
		if(new Tile(1859,5237,0).getMatrix(ctx).isReachable()){//in first doors
			if(m.byCloseLoc(new Tile(1859,5236,0), 1))
				m.interactO(16124, "Open", "Door");//second door
		}else
		if(new Tile(1859,5243,0).getMatrix(ctx).isReachable()){//entrance inside cave
			teleported = false;
			if(m.byCloseLoc(new Tile(1858,5239,0), 5))
				m.interactO(16124, "Open", "Door");//first door
		}else if(new Tile(3081,3421,0).distanceTo(local)<7){//outside cave entrance
			m.interactO(16154, "Climb-down", "Hole");
		}else if(teleported){
			m.walkTo(new Tile(3081,3421,0),"Barbarian Village");//barbarian village
		}else if(TeleportLode.FALADOR.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.FALADOR.getTeleport(), "Falador");
	}

}
