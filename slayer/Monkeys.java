package slayer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Tile;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;

public class Monkeys extends SlayerNode{

	public Monkeys(MethodContext ctx) {
		super(ctx);
	}

	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
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
		}else
		if(m.canMakePath(new Tile(2880,3161,0))){
			m.walkTo(new Tile(2880,3161,0), "monkey area");
		}else
		if(new Tile(2956,3143,1).getMatrix(ctx).isReachable()&&ctx.game.getPlane()==1){//on boat
			m.interactO(2082, "Cross", "Plank");
		}else
		if(new Tile(3028,3215,0).distanceTo(local)<7){//portsarim doc
			if(!m.findOption(opt))
				if(!m.isChatting("Officer")){
					m.npcInteract(376, "Talk-to");
				}
		}else if(teleported){
			m.walkTo(new Tile(3028,3215,0),"Port Sarim");//Port sarim
		}else if(TeleportLode.PORTSARIM.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.PORTSARIM.getTeleport(), "Port Sarim");
		
	}

}
