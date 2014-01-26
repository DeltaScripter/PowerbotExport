package slayer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.Npc;
import org.powerbot.script.wrappers.Tile;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;

public class EarthWarriors extends SlayerNode{

	public EarthWarriors(MethodContext ctx) {
		super(ctx);
	}

	Tile[] toCave = new Tile[] { new Tile(3066, 3504, 0), new Tile(3064, 3509, 0), new Tile(3060, 3512, 0), 
			new Tile(3059, 3517, 0), new Tile(3059, 3522, 0), new Tile(3059, 3527, 0), 
			new Tile(3058, 3532, 0), new Tile(3057, 3537, 0), new Tile(3056, 3542, 0), 
			new Tile(3056, 3547, 0), new Tile(3055, 3550, 0) };
	Tile[] toBarrier = new Tile[] { new Tile(3066, 3505, 0), new Tile(3063, 3509, 0), new Tile(3060, 3513, 0), 
			new Tile(3057, 3517, 0), new Tile(3061,3520, 0) };
	@Override
	public boolean activate() {
		return (ctx.settings.get(2091)>>slayerbody.push&slayerbody.mask)==27&&ctx.settings.get(183)!=0;
	}

	
	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public void execute() {//2636,3312
	Tile local = ctx.players.local().getLocation();
		
	   while(new Tile(3167,5458,0).getMatrix(ctx).isReachable()){//fail safe
		   m.state("Fail safe initiated");
			if(m.byCloseLoc(new Tile(3168,5456,0), 3))
				m.interactO(77745, "Enter", "Portal");
		}
		if(new Tile(3141,5513,0).getMatrix(ctx).isReachable()){//room with warriors
			teleported = false;
			m.fightNPC(124, "Attack");//warriors
		}else if(new Tile(3158,5524,0).getMatrix(ctx).isReachable()){//black demons
			if(m.byCloseLoc(new Tile(3156,5523,0), 3))
				m.interactO(77745, "Enter", "Portal");
			
		}else if(new Tile(3167,5515,0).getMatrix(ctx).isReachable()){//dust devil
			if(m.byCloseLoc(new Tile(3165,5515,0), 3))
				m.interactO(77745, "Enter", "Portal");
			
		}else if(new Tile(3158,5498,0).getMatrix(ctx).isReachable()){//dmonk of zammorak
			if(m.byCloseLoc(new Tile(3159,5501,0), 3))
				m.interactO(77745, "Enter", "Portal");
			
		}else if(new Tile(3143,5480,0).getMatrix(ctx).isReachable()){//Mummy
			if(m.byCloseLoc(new Tile(3141,5480,0), 3))
				m.interactO(77745, "Enter", "Portal");
			
		}else if(new Tile(3182,5469,0).getMatrix(ctx).isReachable()){//entrance/gargoyle
			if(m.byCloseLoc(new Tile(3171,5478,0), 3))
				m.interactO(77745, "Enter", "Portal");
		}else if(new Tile(3056,3549,0).distanceTo(local)<7){
			m.interactO(65200, "Enter", "Cave");
		} else if(teleported){
			if(new Tile(3062,3523,0).getMatrix(ctx).isReachable()){
				
				m.simpleWalk(toCave, "Walking to chaos tunnels");
			}else if(new Tile(3063,3520,0).distanceTo(local)<8){
				
				m.interactO(6926, "Cross", "Barrier");
			}else m.simpleWalk(toBarrier, "Walking to barrier");
			
			
		}else if(TeleportLode.EDGEVILLE.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.EDGEVILLE.getTeleport(), "Edgeville");
		
	}

}
