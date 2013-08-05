package quests;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.Hud.Window;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.Item;
import org.powerbot.script.wrappers.Player;
import org.powerbot.script.wrappers.Tile;

import quests.Vars.TeleportLode;
import quests.Vars.TeleportType;

public class PlagueCity extends Node{

	public PlagueCity(MethodContext ctx) {
		super(ctx);
	}
final Tile[] pathToEdmond = new Tile[] {
		new Tile(2633, 3348, 0), new Tile(2635, 3343, 0), new Tile(2635, 3338, 0), 
		new Tile(2638, 3334, 0), new Tile(2642, 3331, 0), new Tile(2646, 3328, 0), 
		new Tile(2648, 3323, 0), new Tile(2650, 3318, 0), new Tile(2654, 3314, 0), 
		new Tile(2654, 3309, 0), new Tile(2649, 3307, 0), new Tile(2644, 3306, 0), 
		new Tile(2639, 3304, 0), new Tile(2636, 3300, 0), new Tile(2631, 3298, 0), 
		new Tile(2626, 3296, 0), new Tile(2621, 3297, 0), new Tile(2616, 3297, 0), 
		new Tile(2611, 3297, 0), new Tile(2606, 3297, 0), new Tile(2601, 3296, 0), 
		new Tile(2596, 3296, 0), new Tile(2592, 3299, 0), new Tile(2590, 3304, 0), 
		new Tile(2587, 3309, 0), new Tile(2584, 3313, 0), new Tile(2583, 3318, 0), 
		new Tile(2581, 3323, 0), new Tile(2580, 3328, 0), new Tile(2579, 3333, 0), 
		new Tile(2575, 3336, 0), new Tile(2570, 3337, 0), new Tile(2568, 3334, 0) };
	
	private Method Method = new Method(ctx);
	private Vars Vars = new Vars();
	@Override
	public void execute() {
		if(ctx.hud.isVisible(Window.BACKPACK)){
			if(Method.inventoryContains(1005)){
		
				for(Item i: ctx.backpack.select().id(1005).first()){
					if(!ctx.backpack.isItemSelected()){
						Method.state("Selecting item");
						i.interact("Use");
						}else Method.state("selected");
				}
				
			}else
			Method.state("not there");
		}else {
			Method.state("Backpack is not visible");
			ctx.hud.view(Window.BACKPACK);
		}
		/*
		Method.resetTeleporting();
		DeltaQuester.numSteps = 2;
		
		if((ctx.settings.get(2386)&0x1)==1){
			DeltaQuester.progress = 2;
			cs1();
		}else {
			DeltaQuester.progress  =1;
			cs0();//Speak to edmond to start the quest
		}
		*/
	}
	
	private void cs1() {
		if(Vars.hasPaint){
			if(new Tile(2572,3333,0).distanceTo(ctx.players.local().getLocation())<2){
				Method.state("Now ready");
			}else if(Method.objIsByTile(new Tile(2573,3333,0), 34807, 3)){//door 2
				openDoor(new Tile(2573,3333,0), 34807);
			}else if(Method.objIsByTile(new Tile(2570,3333,0), 34811, 3)){//door 1
				openDoor(new Tile(2570,3333,0), 34811);
			}else Method.clickOnMap(new Tile(2572,3333,0));
			
		}else if(!Method.teleporting && Method.inventoryContains(1510)){
			Vars.hasPaint = true;
		}else
		if(new Tile(2575,3333,0).distanceTo(ctx.players.local().getLocation())<2){
			if(Method.gItemIsNotNull(1510))
			ctx.mouse.move(Method.getGroundItem(1510).getLocation().getMatrix(ctx).getPoint(Random.nextDouble() * 0.6D + 0.2D,+0.6D,-400));
			ctx.mouse.click(true);
		}else if(Vars.DYNAMICV2){
			if(Method.objIsByTile(new Tile(2570,3333,0), 34811, 3)){//door 1
			openDoor(new Tile(2570,3333,0), 34811);
		}else if(Method.objIsByTile(new Tile(2573,3333,0), 34807, 3)){//door 2
			openDoor(new Tile(2573,3333,0), 34807);
		}else Method.clickOnMap(new Tile(2575,3333,0));
		
		}else cs0();
		
	}

	private void openDoor(Tile tile, int id) {
		Player local = ctx.players.local();
		
		if(tile.distanceTo(local.getLocation())<5){
			Method.interactO(id, "Open", "Door");
		}else Method.findPath(tile, "Walking to door");
		
	}

	private void cs0() {//Speaks to edmond to start the quest
		Player local = ctx.players.local();
		final String opt[] = {"Can I help find","What's happened"};
		
		if(new Tile(2568,3333,0).distanceTo(local.getLocation())<6){
			Vars.DYNAMICV2  =true;
				if(!Method.startQuestOpen())
					if(!Method.findOption(opt))
						if(!Method.isChatting("Edmond")){
							Method.speakTo(3213, "Edmond");
						}
		}else if(Vars.DYNAMICV){
		Method.walking(pathToEdmond, "Walking to Edmond", false);
		}else if(TeleportLode.ARDOUGNE.getTile().distanceTo(local.getLocation())<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.ARDOUGNE.getTeleport(),TeleportType.ARDOUGNE.getName());
	}

	@Override
	public boolean activate() {
		return DeltaQuester.scriptToStart==38;
	}

}
