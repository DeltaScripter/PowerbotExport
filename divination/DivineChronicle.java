package divination;



import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.Tile;

import divination.DivineData.TeleportLode;
import divination.DivineData.TeleportType;

public class DivineChronicle extends DivineNode{

	public DivineChronicle(MethodContext ctx) {
		super(ctx);
	
	}

	@Override
	public boolean activate() {
		return DivineBody.depositChronicles;
	}
	private Tile[] pathToMay = {
			new Tile(3107,3384,0), new Tile(3105,3267,0),new Tile(3105,3252,0),
			new Tile(3105,3241,0), new Tile(3116,3227,0), new Tile(3128,3228,0),
			new Tile(3135,3233,0)
	};
	
	private DivineMethod Method = new DivineMethod(ctx);
	private boolean teleported = false;
	public static boolean getBackToArea = false;
	@Override
	public void execute() {
		Tile local = ctx.players.local().getLocation();
		if(getBackToArea){//Goes back to the rift area
			DivineBody.state = "Now getting back to the rift";
			if(DivineBody.location == "Lummbridge"){
				getToLummbridgeRift();
			}
		}else if(!Method.inventoryContains("Chronicle fragment")){
			getBackToArea = true;
		}else
		if(new Tile(3134,3233,0).distanceTo(ctx.players.local().getLocation())<7){//may flower location
		DivineBody.state = "Ready";//get rid of chronicle fragments
		}else if(!ctx.movement.findPath(new Tile(3134,3233,0).randomize(3, 5)).traverse()){
			if(teleported){
				DivineBody.state = "Walking to May Stormbrewer";
				ctx.movement.newTilePath(pathToMay).traverse();
				ctx.game.sleep(Random.nextInt(500, 2000));
			}else if (TeleportLode.DRAYNOR.getTile().distanceTo(local)<10){
				teleported = true;
			}else Method.teleportTo(TeleportType.DRAYNOR.getTeleport(), "Draynor");
		}
		
	}

	private void getToLummbridgeRift() {
		Tile local = ctx.players.local().getLocation();
		
		if(Method.objIsNotNull("Energy Rift")){
			if(!ctx.movement.findPath(DivineBody.riftArea.randomize(3, 5)).traverse()){
				if(teleported){
					DivineBody.state = "Walking to Lummbridge rift";
					ctx.movement.newTilePath(pathToMay).traverse();//goes to same basic area
					ctx.game.sleep(Random.nextInt(500, 2000));
				}else if (TeleportLode.DRAYNOR.getTile().distanceTo(local)<10){
					teleported = true;
				}else Method.teleportTo(TeleportType.DRAYNOR.getTeleport(), "Draynor");
			}else if(DivineBody.riftArea.distanceTo(local)<20){
				getBackToArea = false;
				DivineBody.depositChronicles = false;
			}
		}
		
	}

}
