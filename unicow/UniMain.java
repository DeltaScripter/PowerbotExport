package unicow;



import org.powerbot.script.lang.BasicNamedQuery;
import org.powerbot.script.lang.Filter;
import org.powerbot.script.methods.Menu;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Random;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.GroundItem;
import org.powerbot.script.wrappers.Npc;
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
	final Tile[] pathToTower = { new Tile(2637, 3339, 0), 
			new Tile(2640, 3334, 0), new Tile(2643, 3330, 0), new Tile(2645, 3325, 0), 
			new Tile(2648, 3321, 0), new Tile(2649, 3316, 0), new Tile(2652, 3312, 0), 
			new Tile(2651, 3307, 0), new Tile(2646, 3306, 0), new Tile(2641, 3306, 0), 
			new Tile(2636, 3304, 0), new Tile(2633, 3300, 0), new Tile(2628, 3298, 0), 
			new Tile(2623, 3297, 0), new Tile(2618, 3297, 0), new Tile(2613, 3297, 0), 
			new Tile(2608, 3296, 0), new Tile(2605, 3292, 0), new Tile(2609, 3289, 0), 
			new Tile(2611, 3284, 0), new Tile(2606, 3283, 0), new Tile(2601, 3285, 0), 
			new Tile(2597, 3282, 0), new Tile(2596, 3277, 0), new Tile(2598, 3272, 0), 
			new Tile(2598, 3267, 0), new Tile(2598, 3262, 0), new Tile(2597, 3257, 0), 
			new Tile(2594, 3253, 0), new Tile(2594, 3248, 0), new Tile(2597, 3244, 0), 
			new Tile(2602, 3242, 0), new Tile(2607, 3242, 0), new Tile(2612, 3242, 0), 
			new Tile(2617, 3241, 0), new Tile(2622, 3241, 0), new Tile(2626, 3239, 0), 
			new Tile(2631, 3238, 0), new Tile(2635, 3235, 0), new Tile(2640, 3233, 0), 
			new Tile(2644, 3230, 0), new Tile(2647, 3226, 0), new Tile(2649, 3228, 0) };
	
	final Tile[] pathToTowerFromMonastry = {
			new Tile(2614,3222,0), new Tile(2627,3222,0),
			new Tile(2638,3224,0),new Tile(2649, 3228, 0)};

	boolean teleported = false;
	boolean wearingPorter = false;
	
	Timer onMap = new Timer(0);//for clicking on map
	Timer walk = new Timer(0);//walking
	Timer take = new Timer(0);//taking horn
	UniMethod Method = new UniMethod(ctx);
	Bob summon = new Bob(ctx);
	final Tile altarLoc = new Tile(3019,4408,0);//5603 - unicow
	int foodID = 373;
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
		DeltaUniBody.task = "Gathering horns";
		if(decide == 0 && !ctx.widgets.get(1184,0).isVisible()){
			decide = Random.nextInt(1, 3);
		}
		while(Method.inventoryGetCount(UniBank.ringIDs[1])>1||
				Method.inventoryGetCount(UniBank.ringIDs[2])>1||
				Method.inventoryGetCount(UniBank.ringIDs[3])>1||
				Method.inventoryGetCount(UniBank.ringIDs[4])>1||
				Method.inventoryGetCount(UniBank.ringIDs[5])>1||
				Method.inventoryGetCount(UniBank.ringIDs[6])>1){
			Method.state("Wearing ring of duelling");
			for(int id: UniBank.ringIDs)
			Method.interactInventory(id,"Wear", "Ring");
		}
		
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
					
					if(DeltaUniBody.Bob&&ctx.summoning.isFamiliarSummoned() && 
							Method.inventoryGetCount(items.HORN.getID())>3&&
							Bob.familiarItem==-1){
					
						while(!summon.give(237, ctx.summoning.getNpc().getId())){
							Method.state("Giving familiar unicorn horns");
						}
					}else if(summon.SUMMONING_CLOSEBUTTON.isVisible()){
						Method.state("Closing interface");
						summon.SUMMONING_CLOSEBUTTON.click();
					}else if(DeltaUniBody.usePorter&&Method.inventoryGetCount(items.PORTER.getID())>=1){
						System.out.println("Attempting to wear porter");
						Method.interactInventory(items.PORTER.getID(), "Wear", "Porter");
					}else
					if(Method.inventoryGetCount(DeltaUniBody.foodID)>1){
					Method.interactInventory(foodID, "Eat", "");
					}else Method.interactInventory(1739, "Drop", "Cowhide");
				}else 
				for(GroundItem horn: ctx.groundItems.select().id(237,238).nearest()){
					if(summon.SUMMONING_CLOSEBUTTON.isVisible()){
						Method.state("Closing interface");
						summon.SUMMONING_CLOSEBUTTON.click();
					}else if(!buryBones())
					if(!take.isRunning()){
					if(Method.gItemIsNotNull(10859)&&(Method.getGroundItem(10859).getLocation().distanceTo(horn.getLocation())>1)||//tea
							Method.gItemIsNotNull(10878)&&(Method.getGroundItem(10878).getLocation().distanceTo(horn.getLocation())>1)){//bag
						System.out.println("Taking horn via mass-clicking 1");
						if(!horn.isOnScreen())
							ctx.camera.turnTo(horn);
						horn.interact("Take");
					}else if(!Method.gItemIsNotNull(10859)&&!Method.gItemIsNotNull(10878)){
						System.out.println("Taking horn via mass-clicking 2");
						if(!horn.isOnScreen())
							ctx.camera.turnTo(horn);
						horn.interact("Take");
					}else if(ctx.menu.isOpen()){
							String[] menuItems = ctx.menu.getItems();
							ctx.game.sleep(200);
							for(String items: menuItems){
								if(items.contains("Unicorn horn")){
									ctx.menu.click(Menu.filter("Take","Unicorn horn"));
									take = new Timer(Random.nextInt(800, 1600));
								}else if(!take.isRunning()){
									if(items == menuItems[menuItems.length-1])
									clickOffMenu(horn);
								}
								
							}
							
						}else {
							if(!horn.isOnScreen())
								ctx.camera.turnTo(horn);
							horn.click(false);
						}
					
					}
				}
			}else if(droppedSatchels())
				fightUnicow();
		}else getToTower();
		
		
	}
private boolean droppedSatchels() {
		if(Method.inventoryGetCount(10878)>=1){
			Method.interactInventory(10878, "Drop", "Satchel");
		}
		return true;
	}

private boolean buryBones() {
		if(Method.inventoryGetCount(526)>=1){
			Method.interactInventory(526, "Bury", "Bones");
		}
		return false;
	}

private void clickOffMenu(GroundItem horn) {
/*r1 & r0 are the random nums for the x, and y*/
	
	System.out.println("Clicking off menu");
	r1 =  Random.nextInt(390,20);
	r0 =  Random.nextInt(90,400);
	if(Method.gItemIsNotNull(horn.getId()))
	ctx.mouse.move(horn.getCenterPoint().x+r0, horn.getCenterPoint().y+r1);
		
	}
Timer waitInv = new Timer(0);
	private boolean bankClauses() {
		while(ctx.widgets.get(1184,0).isValid()&&
				ctx.widgets.get(1184,0).isVisible()){
			Method.state("Closing dialogue");
			if(decide==2){
			//System.out.println("Deciding to close via clicking continue");
			ctx.widgets.get(1184,11).click();
			decide = 0;
			}else{
				//System.out.println("Deciding to close via clicking altar again");
				if(!waitFight.isRunning())
				if(summon.SUMMONING_CLOSEBUTTON.isVisible()){
					Method.state("Closing interface");
					summon.SUMMONING_CLOSEBUTTON.click();
				}else{
				Method.state("Interface open, clicking altar again");
				Method.interactO(21893, "Activate", "Altar");
				}
				decide = 0;
			}
			
		}
		if(!waitInv.isRunning())
		if(Method.inventoryGetCount(items.COWHIDE.getID())==0||
				DeltaUniBody.foodSupport&&Method.inventoryGetCount(foodID)==0||
				DeltaUniBody.Bob&&(ctx.summoning.isFamiliarSummoned()&&ctx.summoning.getTimeLeft()<120)||
				(!Method.npcIsNotNull(npcs.UNICOW.getID())&&!ctx.widgets.get(1189,0).isVisible()&&
						Method.inventoryGetCount(items.HORN.getID())==0&&!Method.gItemIsNotNull(items.HORN.getID()))){
			Method.state("Setting to bank again");
			wearingPorter = false;
			DeltaUniBody.bank  =true;
			return true;
		}
		if(Method.getInteractingNPC()==null && !ctx.players.local().isIdle()){
			Method.state("Boosting timer");
			System.out.println("Boosting the timer for waiting combat");
			waitInv = new Timer(Random.nextInt(3300, 3600));
			return false;
		}
		return false;
	}

	private void getToTower() {
		Tile local = ctx.players.local().getLocation();
		if(new Tile(2649,3224,0).getMatrix(ctx).isReachable()){//inside tower
			if(new Tile(2648,3213,0).distanceTo(local)<6){//by trapdoor
				teleported = false;
				Method.interactO(21944, "Climb-down", "Trap-door");
				ctx.game.sleep(1000);
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
			}else Method.useEquipmentItem(DeltaUniBody.capeID, "Kandarin Monastery");
			
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
 static Timer waitFight = new Timer(0);
	private void fightUnicow() {
		Tile local = ctx.players.local().getLocation();
		
		if(altarLoc.distanceTo(local)<14){
			BasicNamedQuery<Npc> cow =ctx.npcs.select(new Filter<Npc>() {
				public boolean accept(Npc g) {
					return g.getId()==npcs.UNICOW.getID()&&
							(g.getInteracting()!=null&&g.getInteracting().equals(ctx.players.local())||
							g.getInteracting()==null);
				}
		         });
			
		   if(!cow.select().nearest().id(npcs.UNICOW.getID()).isEmpty() && unicowIsOurs(cow)){
			   if(Method.getInteractingNPC()==null || !ctx.players.local().isInCombat()){
				  for(Npc c: cow){
					  Method.state("Attacking unicow");
					 
					  if(c.getId()==npcs.UNICOW.getID()){
						  ctx.game.sleep(Random.nextInt(2000, 2500));
						  if(c.getInteracting()==null){
							  System.out.println("Attacking unicow that is not interacting with any player");
							  c.interact("Attack");
						  }else if(c.getInteracting().equals(ctx.players.local())){
							  System.out.println("Attacking unicow that is interacting with our player");
							  c.interact("Attack");
						  }
					  }
				  }
			   }else  Method.fightNPC(npcs.UNICOW.getID());
			   
			   
		   }else if(!waitFight.isRunning())
			   if(summon.SUMMONING_CLOSEBUTTON.isVisible()){
				Method.state("Closing interface");
				summon.SUMMONING_CLOSEBUTTON.click();
			}else if(altarLoc.distanceTo(local)<5){
				System.out.println("Clicking the altar from distance");
			   Method.state("Clicking altar");
			  Method.interactO(21893, "Activate", "Altar");
			  waitInv = new Timer(3000);
		   }else if(altarLoc.getMatrix(ctx).isInViewport()&&ctx.camera.setPitch(50)){
			   System.out.println("Clicking the altar from viewport");
			   Method.state("Clicking altar");
			   Method.interactO(21893, "Activate", "Altar");
			   waitInv = new Timer(3000);
		   }else if(!onMap.isRunning()){
				Method.clickOnMap(altarLoc.randomize(1, 2));
				onMap = new Timer(Random.nextInt(1300, 1700));
			}
		}else if(!onMap.isRunning()){
			Method.clickOnMap(altarLoc.randomize(2, 4));
			onMap = new Timer(Random.nextInt(1300, 1700));
		}
		
	}

	private boolean unicowIsOurs(BasicNamedQuery<Npc> cow) {
		 for(Npc c: cow){
			  if(c.getId()==npcs.UNICOW.getID()){
				  if(c.getInteracting()==null){
					  return true;
				  }else if(c.getInteracting().equals(ctx.players.local())){
					 return true;
				  }
				  
			  }
		  }
		return false;
	}

}
