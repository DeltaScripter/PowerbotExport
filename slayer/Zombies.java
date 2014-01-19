package slayer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Npc;
import org.powerbot.script.wrappers.Tile;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;

public class Zombies extends SlayerNode{

	public Zombies(MethodContext ctx) {
		super(ctx);
		
	}

	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public boolean activate() {
		return (ctx.settings.get(2091)>>slayerbody.push&0x1F)==12&&ctx.settings.get(183)!=0;
	}

	@Override
	public void execute() {
		Tile local = ctx.players.local().getLocation();
		
		if(new Tile(3091,9673,0).getMatrix(ctx).isReachable()){//zombies area
			teleported = false;
				if(!ctx.players.local().isInMotion()){
				  for(Npc ghost: ctx.npcs.select().nearest().id(5320,5322,5305).first()){
					 if(m.getInteractingNPC()==null){
						 if(ghost.getLocation().distanceTo(local)<7){
						   if(!ghost.interact("Attack")){
						  	 ctx.camera.turnTo(ghost.getLocation().randomize(2, 3));
						   }
						 }else m.clickOnMap(ghost.getLocation());
					 }else
					  m.fightNPC(ghost.getId(), "Attack");
				  }
				}
			
		}else if(new Tile(3085,3272,0).distanceTo(local)<7){//outside dungeon
			m.interactO(6434, "Open", "Cave");
			m.interactO(6435, "Climb-down", "Cave");
		}else if(teleported){
			m.walkTo(new Tile(3085,3272,0),"cave entrance");//cave entrance
		}else if(TeleportLode.DRAYNOR.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.DRAYNOR.getTeleport(), "Draynor");
		
	}

}
