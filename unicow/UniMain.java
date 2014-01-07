package unicow;

import lodestoneActivator.Data.TeleportLode;
import lodestoneActivator.Data.TeleportType;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Random;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.GroundItem;
import org.powerbot.script.wrappers.Tile;

import unicow.UniData.items;
import unicow.UniData.npcs;

public class UniMain extends UniNode{

	public UniMain(MethodContext ctx) {
		super(ctx);
	}
	final Tile[] pathToTower = {
			new Tile(2638,3335,0), new Tile(2649,3319),
			new Tile(2665,3302,0),new Tile(2644,3290,0),
			new Tile(2641,3268,0), new Tile(2630,3250),
			new Tile(2640,3231,0), new Tile(2648,3226,0)
	};
	
	boolean teleported = false;
	Timer onMap = new Timer(0);//for clicking on map
	Timer walk = new Timer(0);//walking
	Timer take = new Timer(0);//taking horn
	UniMethod Method = new UniMethod(ctx);
	final Tile altarLoc = new Tile(3019,4408,0);//5603 - unicow
	int foodID = 379;
	@Override
	public boolean activate() {
		return !DeltaUniBody.bank;
	}

	@Override
	public void execute() {
	
		//if(Method.getInteractingNPC()!=null){
		//	System.out.println("Yes");
		//}else System.out.println("No");
		
		//for a combat test
		//Method.fightNPC(npcs.GUARD.getID());
		while(ctx.players.local().getHealthPercent()<60){
			//eat food
			DeltaUniBody.state = "Eating food: "+ foodID;
			if(Method.inventoryGetCount(foodID)<3){
				System.out.println("Not enough food, breaking");
				break;
			}
			Method.interactInventory(foodID, "Eat", "lobster");
		}
		if(Method.inventoryGetCount(items.COWHIDE.getID())<1||
				Method.inventoryGetCount(foodID)<5){
			DeltaUniBody.bank  =true;
		}else
		if(altarLoc.getMatrix(ctx).isReachable()){
			
			if(Method.gItemIsNotNull(237)){
				if(Method.backPackIsFull()){
					Method.interactInventory(foodID, "Eat", "");
				}else
				for(GroundItem horn: ctx.groundItems.select().id(237).nearest()){
					if(!take.isRunning()){
						DeltaUniBody.state = "Taking unicorn horn";
						System.out.println("Taking: " + horn.getId());
					    horn.interact("Take");
						take = new Timer(Random.nextInt(200, 600));
					}
				}
			}else
			fightUnicow();
		}else getToTower();
		
		
	}

	private void getToTower() {
		Tile local = ctx.players.local().getLocation();
		
		if(new Tile(2649,3224,0).getMatrix(ctx).isReachable()){//inside tower
			if(new Tile(2648,3213,0).distanceTo(local)<7){//by trapdoor
				teleported = false;
				Method.interactO(21944, "Climb-down", "Trap-door");
				}else if(!onMap.isRunning()){
					Method.clickOnMap(new Tile(2648,3213,0).randomize(2, 3));
					onMap = new Timer(Random.nextInt(1300, 1700));
				}
		}else
		if(new Tile(2648,3226,0).distanceTo(local)<7){//outside tower
	        Method.interactO(21814, "Open", "Door");
		}else if(teleported){
			if(!walk.isRunning()){
				ctx.movement.newTilePath(pathToTower).randomize(1, 2).traverse();
				walk = new Timer(Random.nextInt(1700, 2300));
			}
		}else if(TeleportLode.ARDOUGNE.getTile().distanceTo(local)<10){
			teleported = true;
		}else {
			DeltaUniBody.state = "Teleporting to Ardougne";
			Method.teleportTo(TeleportType.ARDOUGNE.getTeleport(), "");
		}
		
	}

	private void fightUnicow() {
		Tile local = ctx.players.local().getLocation();
		if(altarLoc.distanceTo(local)<14){
		   if(Method.npcIsNotNull(npcs.UNICOW.getID())){
			   Method.fightNPC(npcs.UNICOW.getID());
		   }else if(ctx.widgets.get(1189,0).isVisible()){
			   ctx.widgets.get(1189,3).click();
			   ctx.game.sleep(600,900);
		   }else if(altarLoc.distanceTo(local)<7){
			   Method.interactO(21893, "Activate", "");
		   }else if(altarLoc.getMatrix(ctx).isOnScreen()){
			   altarLoc.getMatrix(ctx).click();
			  ctx.game.sleep(Random.nextInt(200, 600));
		   }else if(!onMap.isRunning()){
				Method.clickOnMap(altarLoc.randomize(1, 2));
				onMap = new Timer(Random.nextInt(1300, 1700));
			}
		}else if(!onMap.isRunning()){
			Method.clickOnMap(altarLoc.randomize(2, 3));
			onMap = new Timer(Random.nextInt(1300, 1700));
		}
		
	}

}
