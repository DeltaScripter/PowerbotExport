package slayer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Tile;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;

public class Scorpians extends SlayerNode{

	public Scorpians(MethodContext ctx) {
		super(ctx);
	}

	Tile[] scorpians = new Tile[] {
			new Tile(3233, 3217, 0), new Tile(3235, 3223, 0), new Tile(3241, 3226, 0), 
			new Tile(3247, 3226, 0), new Tile(3253, 3226, 0), new Tile(3259, 3227, 0), 
			new Tile(3265, 3228, 0), new Tile(3271, 3231, 0), new Tile(3277, 3232, 0), 
			new Tile(3282, 3236, 0), new Tile(3288, 3237, 0), new Tile(3294, 3237, 0), 
			new Tile(3300, 3236, 0), new Tile(3305, 3232, 0), new Tile(3296, 3184, 0), 
			new Tile(3302, 3184, 0), new Tile(3308, 3183, 0), new Tile(3310, 3189, 0), 
			new Tile(3309, 3195, 0), new Tile(3307, 3201, 0), new Tile(3307, 3207, 0), 
			new Tile(3306, 3213, 0), new Tile(3305, 3219, 0), new Tile(3304, 3225, 0), 
			new Tile(3305, 3231, 0), new Tile(3306, 3237, 0), new Tile(3305, 3243, 0), 
			new Tile(3305, 3249, 0), new Tile(3304, 3255, 0), new Tile(3303, 3261, 0), 
			new Tile(3300, 3267, 0), new Tile(3299, 3273, 0), new Tile(3296, 3279, 0), 
			new Tile(3295, 3285, 0), new Tile(3295, 3291, 0), new Tile(3295, 3296, 0) };
	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public boolean activate() {
		return (ctx.settings.get(2091)>>slayerbody.push&0x1F)==13 && ctx.settings.get(183)!=0;
	}

	@Override
	public void execute() {
		Tile local = ctx.players.local().getLocation();
		
		if(new Tile(3297,3293,0).distanceTo(local)<30){//scorpian area
			if(ctx.players.local().isInCombat())
				teleported = false;
			m.fightNPC(107, "Attack");//scorpians 
		}else if(teleported){
			m.simpleWalk(scorpians, "Walking to scorpian area");
			//m.walkTo(new Tile(3297,3293,0),"scorpian area");//scorpian area
		}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.LUMBRIDGE.getTeleport(), "Lumbridge");
		
	}

}
