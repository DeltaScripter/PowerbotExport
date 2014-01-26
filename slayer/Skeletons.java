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

	Tile[] ghost = new Tile[] {
			new Tile(2877, 3443, 0), new Tile(2883, 3439, 0), new Tile(2883, 3433, 0), 
			new Tile(2882, 3427, 0), new Tile(2884, 3421, 0), new Tile(2889, 3417, 0), 
			new Tile(2895, 3414, 0), new Tile(2901, 3413, 0), new Tile(2901, 3407, 0), 
			new Tile(2897, 3402, 0), new Tile(2891, 3400, 0), new Tile(2886, 3396, 0), 
			new Tile(2884, 3395, 0) };
	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public boolean activate() {
		return (ctx.settings.get(2091)>>slayerbody.push&slayerbody.mask)==3&&ctx.settings.get(183)!=0;
	}

	@Override
	public void execute() {
		Tile local = ctx.players.local().getLocation();
		
		if(new Tile(2884,9816,0).distanceTo(local)<30){//ghost area
			teleported = false;
				if(!ctx.players.local().isInMotion()){
				  for(Npc ghost: ctx.npcs.select().nearest().id(90,5333,5334,5332).first()){
					 if(m.getInteractingNPC()==null){
						 System.out.println("getinteracting npc is null");
						 if(ghost.getLocation().distanceTo(local)<7){
						   if(m.state("Attempting to attack: " + ghost.getName())&&
								   !ghost.interact("Attack")){
						  	 ctx.camera.turnTo(ghost.getLocation().randomize(2, 3));
						   }
						 }else m.clickOnMap(ghost.getLocation());
					 }else m.fightNPC(ghost.getId(), "Attack");
				  }
				}
			
		}else if(m.objIsNotNull(74990)){//in cave
			m.clickOnMap(new Tile(2884,9816,0));
		}else if(new Tile(2887,3394,0).distanceTo(local)<7){//cave entrance
			m.interactO(66991, "Climb-down", "Cave");
		}else if(teleported){
			m.simpleWalk(ghost, "Walking to Taverly cave");
			//m.walkTo(new Tile(2888,3394,0),"cave entrance");//cave entrance
		}else if(TeleportLode.TAVERLY.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.TAVERLY.getTeleport(), "Taverly");
		
	}

}
