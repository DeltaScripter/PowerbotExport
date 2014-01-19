package slayer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Npc;
import org.powerbot.script.wrappers.Tile;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;

public class DesertLizards extends SlayerNode{

	public DesertLizards(MethodContext ctx) {
		super(ctx);
	}

	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public boolean activate() {
		return (ctx.settings.get(2091)>>slayerbody.push&0x1F)==15 && ctx.settings.get(183)!=0;
	}

	@Override
	public void execute() {
		Tile local = ctx.players.local().getLocation();
		
		if(new Tile(3413,3034,0).distanceTo(local)<20){//liz area
			for(Npc liz: ctx.npcs.select().id(2806,2808).nearest().first()){
				if(m.getInteractingNPC()==null){
					if(liz.getLocation().distanceTo(local)<7){
						if(!liz.interact("Attack")){
							ctx.camera.turnTo(liz.getLocation().randomize(2, 3));
						}
					}else m.clickOnMap(liz.getLocation());
				}else if(liz.getHealthPercent()<2){
					m.state("Using Ice cooler on lizard");
					m.useItemOnNpc(6696, liz.getId(), "Use");
				}else m.fightNPC(liz.getId(), "Attack", true);
			}
		}else if(teleported){
			if(m.canMakePath(new Tile(3413,3034,0))){//liz area
				m.walkTo(new Tile(3413,3034,0),"lizard area");//lizard area
			}else if(new Tile(3304,3120,0).distanceTo(local)<12){//area before netering desert
				m.clickOnMap(new Tile(3305,3112,0));//just to get out in the desert
			}else m.walkTo(new Tile(3304,3120,0), "shantay pass");
			
		}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.LUMBRIDGE.getTeleport(), "Lumbridge");
		
	}

}
