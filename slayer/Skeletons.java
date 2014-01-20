package slayer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Npc;
import org.powerbot.script.wrappers.Tile;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;

public class Skeletons extends SlayerNode{

	public Skeletons(MethodContext ctx) {
		super(ctx);
		
	}

	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public boolean activate() {
		return (ctx.settings.get(2091)>>slayerbody.push&0x1F)==3&&ctx.settings.get(183)!=0;
	}

	@Override
	public void execute() {
		Tile local = ctx.players.local().getLocation();
		
		if(new Tile(2884,9816,0).distanceTo(local)<30){//ghost area
			teleported = false;
				if(!ctx.players.local().isInMotion()){
				  for(Npc ghost: ctx.npcs.select().nearest().id(90,5333,5334,5332).first()){
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
			
		}else if(m.objIsNotNull(74990)){//in cave
			m.clickOnMap(new Tile(2884,9816,0));
		}else if(new Tile(2887,3394,0).distanceTo(local)<7){//cave entrance
			m.interactO(66991, "Climb-down", "Cave");
		}else if(teleported){
			m.walkTo(new Tile(2888,3394,0),"cave entrance");//cave entrance
		}else if(TeleportLode.TAVERLY.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.TAVERLY.getTeleport(), "Taverly");
		
	}

}
