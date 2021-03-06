package slayer;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Npc;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;
public class Caveslime extends SlayerNode{

	public Caveslime(ClientContext ctx) {
		super(ctx);
	}

	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public boolean activate() {
		return slayerbody.currentTask=="cave slime" && ctx.varpbits.varpbit(183)!=0;
	}

	Tile[] myTiles = new Tile[] { new Tile(3228, 3220, 0), new Tile(3233, 3216, 0), new Tile(3234, 3210, 0), 
			new Tile(3235, 3204, 0), new Tile(3238, 3198, 0), new Tile(3242, 3193, 0), 
			new Tile(3244, 3187, 0), new Tile(3240, 3182, 0), new Tile(3240, 3176, 0), 
			new Tile(3239, 3170, 0), new Tile(3238, 3164, 0), new Tile(3233, 3160, 0), 
			new Tile(3228, 3156, 0), new Tile(3222, 3156, 0), new Tile(3216, 3157, 0), 
			new Tile(3210, 3157, 0), new Tile(3204, 3155, 0), new Tile(3198, 3152, 0), 
			new Tile(3192, 3151, 0), new Tile(3186, 3152, 0), new Tile(3181, 3156, 0), 
			new Tile(3179, 3162, 0), new Tile(3178, 3162, 0) };
	
	private int[] bankItems = {596};//unlit torch
	private int[] amountOfItem = {1};
	@Override
	public void execute() {
		Tile local = ctx.players.local().tile();
		
		if(slayerbody.goBank){
			m.bankItems(bankItems, amountOfItem);
		}else if(m.inventoryContains(596)){//unlit torch
			m.interactInventory(596, "Light", "Torch");
		}else
		if(!m.inventoryContains(594)){//lit torch
			slayerbody.goBank = true;
		}else 
		if(new Tile(3182,9548,0).distanceTo(local)<15){
			m.fightNPC(1831, "Attack");//slime
		}else if(m.objIsNotNull(78695)){//in cave?
			teleported = false;
			m.clickOnMap(new Tile(3182,9548,0));//slime loc
		}else if(new Tile(3176,3161,0).distanceTo(local)<7){
			m.interactO(5947, "Climb-down", "Cave");
		}else if(teleported){
			m.simpleWalk(myTiles, "Walking to cave");
		//	m.walkTo(new Tile(3176,3161,0),"lumbridge swamp cave");//cave
		}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.LUMBRIDGE.getTeleport(), "Lumbridge");
		
	}

}
