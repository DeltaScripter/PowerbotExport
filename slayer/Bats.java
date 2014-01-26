package slayer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Tile;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;

public class Bats extends SlayerNode{

	public Bats(MethodContext ctx) {
		super(ctx);
	}

	Tile[] bats = new Tile[] {
			new Tile(2830, 3453, 0), new Tile(2828, 3447, 0), new Tile(2827, 3441, 0), 
			new Tile(2822, 3437, 0), new Tile(2816, 3436, 0), new Tile(2810, 3435, 0), 
			new Tile(2804, 3434, 0), new Tile(2798, 3433, 0), new Tile(2792, 3432, 0), 
			new Tile(2786, 3431, 0), new Tile(2780, 3431, 0), new Tile(2774, 3429, 0), 
			new Tile(2768, 3427, 0), new Tile(2762, 3424, 0), new Tile(2758, 3419, 0), 
			new Tile(2756, 3413, 0), new Tile(2756, 3407, 0), new Tile(2758, 3401, 0) };
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
			m.simpleWalk(bats, "Walking to bats");
			//m.walkTo(new Tile(2755,3406,0),"bat area");//bat area
		}else if(TeleportLode.CATHERBY.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.CATHERBY.getTeleport(), "Catherby");
	}

}
