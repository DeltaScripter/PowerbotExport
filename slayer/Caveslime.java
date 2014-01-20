package slayer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Tile;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;

public class Caveslime extends SlayerNode{

	public Caveslime(MethodContext ctx) {
		super(ctx);
	}

	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public boolean activate() {
		return (ctx.settings.get(2091)>>slayerbody.push&0x1F)==21&&ctx.settings.get(183)!=0;
	}

	private int[] bankItems = {596};//unlit torch
	private int[] amountOfItem = {1};
	@Override
	public void execute() {
		Tile local = ctx.players.local().getLocation();
		
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
			m.walkTo(new Tile(3176,3161,0),"lumbridge swamp cave");//cave
		}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.LUMBRIDGE.getTeleport(), "Lumbridge");
		
	}

}
