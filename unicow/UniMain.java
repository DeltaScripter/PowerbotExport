package unicow;



import org.powerbot.script.lang.BasicNamedQuery;
import org.powerbot.script.lang.Filter;
import org.powerbot.script.methods.Menu;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Random;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.GroundItem;
import org.powerbot.script.wrappers.Tile;

import unicow.UniData.TeleportLode;
import unicow.UniData.TeleportType;
import unicow.UniData.items;
import unicow.UniData.npcs;

public class UniMain extends UniNode{

	public static boolean ardougneCloak = false;
	public UniMain(MethodContext ctx) {
		super(ctx);
	}
	final Tile[] pathToTower = {
			new Tile(2638,3335,0), new Tile(2649,3319),
			new Tile(2665,3302,0),new Tile(2644,3290,0),
			new Tile(2641,3268,0), new Tile(2630,3250),
			new Tile(2640,3231,0), new Tile(2648,3226,0)};
	
	final Tile[] pathToTowerFromMonastry = {
			new Tile(2614,3222,0), new Tile(2627,3222,0),
			new Tile(2638,3224,0),new Tile(2648,3225,0)};

	boolean teleported = false;
	Timer onMap = new Timer(0);//for clicking on map
	Timer walk = new Timer(0);//walking
	Timer take = new Timer(0);//taking horn
	UniMethod Method = new UniMethod(ctx);
	final Tile altarLoc = new Tile(3019,4408,0);//5603 - unicow
	int foodID = 379;
	public int r1 = 0;//for random nums when click
	public int r0 = 0;
	public int decide = 0;
	public Tile garbageLoc;
	@Override
	public boolean activate() {
		return !DeltaUniBody.bank;
	}

	@Override
	public void execute() {
		if(decide == 0 && !ctx.widgets.get(1184,0).isVisible()){
			decide = Random.nextInt(0, 2);
		}
		//if(Method.getInteractingNPC()!=null){
		//	System.out.println("Yes");
		//}else System.out.println("No");
		
		//for a combat test
		//Method.fightNPC(npcs.GUARD.getID());
		
		while(ctx.players.local().getHealthPercent()<50){
			//eat food
			DeltaUniBody.state = "Eating food: "+ foodID;
			if(Method.inventoryGetCount(foodID)<1){
				System.out.println("Not enough food, breaking");
				break;
			}
			Method.interactInventory(foodID, "Eat", "lobster");
		}
		if(!bankClauses())
		if(altarLoc.getMatrix(ctx).isReachable()){
			
			if(Method.gItemIsNotNull(237)||Method.gItemIsNotNull(238)){//horn + noted horn
				if(Method.backPackIsFull()){
					Method.interactInventory(foodID, "Eat", "");
				}else 
				for(GroundItem horn: ctx.groundItems.select().id(237,238).nearest()){
					if(!take.isRunning()){
					if(Method.gItemIsNotNull(10859)&&(Method.getGroundItem(10859).getLocation().distanceTo(horn.getLocation())>1)||//tea
							Method.gItemIsNotNull(10878)&&(Method.getGroundItem(10878).getLocation().distanceTo(horn.getLocation())>1)){//bag
						System.out.println("Taking horn via mass-clicking 1");
						horn.interact("Take");
					}else if(!Method.gItemIsNotNull(10859)&&!Method.gItemIsNotNull(10878)){
						System.out.println("Taking horn via mass-clicking 2");
						horn.interact("Take");
					}else if(ctx.menu.isOpen()){
							String[] menuItems = ctx.menu.getItems();
							ctx.game.sleep(400);
							for(String items: menuItems){
								if(items.contains("Unicorn horn")){
									ctx.menu.click(Menu.filter("Take","Unicorn horn"));
									take = new Timer(Random.nextInt(800, 1600));
								}else if(!take.isRunning()){
									clickOffMenu(horn);
								}
								
							}
							
						}else horn.click(false);
					
					}
				}
			}else fightUnicow();
		}else getToTower();
		
		
	}
private void clickOffMenu(GroundItem horn) {
/*r1 & r0 are the random nums for the x, and y*/
	
	
	r1 =  Random.nextInt(40,70);
	r0 =  Random.nextInt(30,60);
	ctx.mouse.move(horn.getCenterPoint().x+r0, horn.getCenterPoint().y+r1);
		
	}
Timer waitInv = new Timer(0);
	private boolean bankClauses() {
		
		while(ctx.widgets.get(1184,0).isVisible()){
			Method.state("Closing dialogue");
			if(decide==2){
			System.out.println("Deciding to close via clicking continue");
			ctx.widgets.get(1184,11).click();
			decide = 0;
			}else{
				System.out.println("Deciding to close via clicking altar again");
				Method.interactO(21893, "Activate", "Altar");
				decide = 0;
			}
			
		}
		if(!waitInv.isRunning())
		if(Method.inventoryGetCount(items.COWHIDE.getID())==0||
				Method.inventoryGetCount(foodID)==0||
				(Method.inventoryGetCount(items.HORN.getID())==0&&!Method.gItemIsNotNull(items.HORN.getID()))){
			System.out.println("Setting to bank");
			DeltaUniBody.bank  =true;
			return true;
		}
		if(Method.getInteractingNPC()==null && !ctx.players.local().isIdle()){
			waitInv = new Timer(Random.nextInt(2300, 2600));
			return false;
		}
		return false;
	}

	private void getToTower() {
		Tile local = ctx.players.local().getLocation();
		if(new Tile(2649,3224,0).getMatrix(ctx).isReachable()){//inside tower
			if(new Tile(2648,3213,0).distanceTo(local)<7){//by trapdoor
				teleported = false;
				Method.interactO(21944, "Climb-down", "Trap-door");
				ctx.game.sleep(3000);
				}else if(!onMap.isRunning()){
					Method.clickOnMap(new Tile(2648,3213,0).randomize(2, 3));
					onMap = new Timer(Random.nextInt(1300, 1700));
				}
		}else
		if(new Tile(2648,3226,0).distanceTo(local)<7){//outside tower
	        Method.interactO(21814, "Open", "Door");
		}else if(ardougneCloak){//has the ardougne cape for teleport
			if(teleported){
				if(!new Tile(2606,3220,0).getMatrix(ctx).isReachable()&&new Tile(2606,3214,0).distanceTo(local)<10){//outside monastry
					BasicNamedQuery<GameObject> findDoor =ctx.objects.select().select(new Filter<GameObject>() {
						public boolean accept(GameObject g) {
							return g.getLocation().distanceTo(new Tile(2606,3218,0))<2&& g.getId()==1530;
						}
				         });
					for(GameObject door: findDoor){
						Method.state("Opening monastry door");
						if(!door.interact("Open")){
							ctx.camera.turnTo(door.getLocation().randomize(2, 3));
							ctx.camera.setPitch(40);
						}
					}
				
				}else if(!ctx.movement.findPath(new Tile(2648,3226,0).randomize(1, 2)).traverse())
					Method.walk(pathToTowerFromMonastry,"Walking to the Tower of Life");
			}else
			if(new Tile(2606,3214,0).distanceTo(local)<10){//inside monastry
				teleported = true;
			}else Method.useEquipmentItem(items.ARDOUGNECAPE1.getID(), "Kandarin Monastery");
			
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
			   
		   }else if(altarLoc.distanceTo(local)<7){
			   Method.state("Clicking altar");
			  Method.interactO(21893, "Activate", "Altar");
		   }else if(altarLoc.getMatrix(ctx).isOnScreen()){
			   Method.state("Clicking altar");
			   Method.interactO(21893, "Activate", "Altar");
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
