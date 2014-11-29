package slayer;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Npc;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;
public class DesertLizards extends SlayerNode{

	public DesertLizards(ClientContext ctx) {
		super(ctx);
	}
	Tile[] pathToLizards = new Tile[] {
			new Tile(3231, 3221, 0), new Tile(3236, 3225, 0), new Tile(3242, 3226, 0), 
			new Tile(3248, 3226, 0), new Tile(3254, 3226, 0), new Tile(3260, 3226, 0), 
			new Tile(3266, 3227, 0), new Tile(3272, 3229, 0), new Tile(3275, 3223, 0), 
			new Tile(3278, 3217, 0), new Tile(3279, 3211, 0), new Tile(3279, 3205, 0), 
			new Tile(3280, 3199, 0), new Tile(3285, 3195, 0), new Tile(3289, 3190, 0), 
			new Tile(3293, 3185, 0), new Tile(3298, 3181, 0), new Tile(3304, 3180, 0), 
			new Tile(3310, 3179, 0), new Tile(3316, 3177, 0), new Tile(3321, 3173, 0), 
			new Tile(3324, 3167, 0), new Tile(3324, 3161, 0), new Tile(3324, 3155, 0), 
			new Tile(3322, 3149, 0), new Tile(3320, 3143, 0), new Tile(3314, 3140, 0), 
			new Tile(3308, 3137, 0), new Tile(3305, 3131, 0), new Tile(3303, 3125, 0), 
			new Tile(3302, 3119, 0), new Tile(3303, 3113, 0), new Tile(3304, 3108, 0), 
			new Tile(3306, 3102, 0), new Tile(3309, 3096, 0), new Tile(3312, 3090, 0), 
			new Tile(3316, 3085, 0), new Tile(3321, 3081, 0), new Tile(3327, 3078, 0), 
			new Tile(3333, 3078, 0), new Tile(3339, 3078, 0), new Tile(3345, 3078, 0), 
			new Tile(3351, 3080, 0), new Tile(3357, 3082, 0), new Tile(3363, 3083, 0), 
			new Tile(3369, 3083, 0), new Tile(3375, 3083, 0), new Tile(3380, 3079, 0), 
			new Tile(3384, 3074, 0), new Tile(3389, 3070, 0), new Tile(3393, 3065, 0), 
			new Tile(3397, 3060, 0), new Tile(3401, 3055, 0), new Tile(3405, 3050, 0), 
			new Tile(3409, 3045, 0), new Tile(3413, 3040, 0), new Tile(3417, 3035, 0), 
			new Tile(3421, 3032, 0) };
	
	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public boolean activate() {
		return slayerbody.currentTask=="desert lizards" && ctx.varpbits.varpbit(183)!=0;
	}

	private int[] bankItems = {1823,6696};//water skins & ice cooler
	private int[] amountOfItem = {2,70};
	@Override
	public void execute() {
		Tile local = ctx.players.local().tile();
	
		if(m.inventoryGetCount(6696)<1){
			slayerbody.goBank = true;
		}
		if(slayerbody.goBank){
			if(m.getInteractingNPC()!=null){
				m.interactInventory(8009,"Break", "Falador tablet");
			}else
			m.bankItems(bankItems, amountOfItem);
		}else 
		if(new Tile(3413,3034,0).distanceTo(local)<30){//liz area
			for(Npc liz: ctx.npcs.select().id(2806,2808).nearest().first()){
				if(m.getInteractingNPC()==null){
					if(liz.tile().distanceTo(local)<7){
						if(!liz.interact("Attack")){
							ctx.camera.turnTo(liz.tile().derive(2, 3));
						}
					}else if(liz.tile().distanceTo(new Tile(3413,3034,0))<30){
						m.clickOnMap(liz.tile());
					}
				}else if(liz.healthPercent()<2){
					m.state("Using Ice cooler on lizard");
					m.useItemOnNpc(6696, liz.id(), "Use");
				}else m.fightNPC(liz.id(), "Attack");
			}
		}else if(teleported){
			m.simpleWalk(pathToLizards,"Walking to lizard area");
		}else if(!m.inventoryContains(1823)&&!m.inventoryContains(1831)||!m.inventoryContains(6696)){//water skins, ice cooler
			System.out.println("Inventory doesn't have desert equipment, setting banking");
			slayerbody.goBank = true;
		}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.LUMBRIDGE.getTeleport(), "Lumbridge");
		
	}

}
