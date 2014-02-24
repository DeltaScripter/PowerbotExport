package slayer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Tile;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;

public class Bears extends SlayerNode{

	public Bears(MethodContext ctx) {
		super(ctx);
	}

	Tile[] myTiles = new Tile[] { new Tile(2633, 3342, 0), new Tile(2634, 3348, 0), new Tile(2635, 3354, 0), 
			new Tile(2635, 3360, 0), new Tile(2635, 3366, 0), new Tile(2636, 3372, 0), 
			new Tile(2641, 3376, 0), new Tile(2645, 3381, 0), new Tile(2647, 3387, 0), 
			new Tile(2653, 3389, 0), new Tile(2659, 3389, 0), new Tile(2665, 3388, 0), 
			new Tile(2671, 3388, 0), new Tile(2677, 3385, 0), new Tile(2683, 3382, 0), 
			new Tile(2686, 3376, 0), new Tile(2687, 3370, 0), new Tile(2688, 3364, 0), 
			new Tile(2689, 3358, 0), new Tile(2691, 3352, 0), new Tile(2693, 3346, 0), 
			new Tile(2695, 3340, 0), new Tile(2698, 3335, 0) };
	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public boolean activate() {
		return slayerbody.currentTask=="bears" && ctx.settings.get(183)!=0;
	}

	@Override
	public void execute() {
		Tile local = ctx.players.local().getLocation();
		
		if(new Tile(2696,3344,0).distanceTo(local)<25){
			teleported = false;
			m.fightNPC(105, "Attack");//bears 
		}else if(teleported){
			m.simpleWalk(myTiles, "Walking to bears");
			//m.walkTo(new Tile(2696,3344,0),"bear area");//river-side
		}else if(TeleportLode.ARDOUGNE.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.ARDOUGNE.getTeleport(), "Ardougne");
	}

}
