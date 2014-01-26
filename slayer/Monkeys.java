package slayer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Area;
import org.powerbot.script.wrappers.Tile;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;

public class Monkeys extends SlayerNode{

	public Monkeys(MethodContext ctx) {
		super(ctx);
	}

	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	
	Tile[] toDocks = new Tile[] { new Tile(3012, 3216, 0), new Tile(3018, 3217, 0), new Tile(3024, 3217, 0), 
			new Tile(3030, 3217, 0), new Tile(3028, 3217, 0) };
	
	Tile[] toMonkey = new Tile[] { new Tile(2953, 3146, 0), new Tile(2947, 3146, 0), new Tile(2941, 3146, 0), 
			new Tile(2935, 3149, 0), new Tile(2929, 3151, 0), new Tile(2923, 3151, 0), 
			new Tile(2917, 3152, 0), new Tile(2911, 3152, 0), new Tile(2906, 3156, 0), 
			new Tile(2900, 3156, 0), new Tile(2894, 3154, 0), new Tile(2888, 3155, 0), 
			new Tile(2882, 3157, 0), new Tile(2876, 3157, 0), new Tile(2874, 3154, 0) };
	
	Area island = new Area(new Tile[] { new Tile(2968, 3184, 0), new Tile(2968, 3128, 0), new Tile(2814, 3135, 0), 
			new Tile(2819, 3215, 0) });
	@Override
	public boolean activate() {
		return (ctx.settings.get(2091)>>slayerbody.push&0x1F)==20&& ctx.settings.get(183)!=0;
	}

	@Override
	public void execute() {
		Tile local = ctx.players.local().getLocation();
		final String opt[] = {"Yes"};
		while(ctx.settings.get(1113)!=0){
			m.state("Cutscene");
		}
		if(new Tile(2880,3161,0).distanceTo(local)<24){//money area
			teleported = false;
			m.fightNPC(132, "Attack");
		}else if(new Tile(2956,3143,1).getMatrix(ctx).isReachable()&&ctx.game.getPlane()==1){//on boat
			m.interactO(2082, "Cross", "Plank");
		}else
		if(island.contains(local)){
			m.simpleWalk(toMonkey, "Walking to monkey");
			//m.walkTo(new Tile(2880,3161,0), "monkey area");
		}else
		if(new Tile(3028,3215,0).distanceTo(local)<7){//portsarim doc
			if(!m.findOption(opt))
				if(!m.isChatting("Officer")){
					m.npcInteract(376, "Talk-to");
				}
		}else if(teleported){
			m.simpleWalk(toDocks, "Walking to Karamja docks");
			//m.walkTo(new Tile(3028,3215,0),"Port Sarim");//Port sarim
		}else if(TeleportLode.PORTSARIM.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.PORTSARIM.getTeleport(), "Port Sarim");
		
	}

}
