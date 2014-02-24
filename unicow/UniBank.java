package unicow;

import org.powerbot.script.methods.Hud.Window;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Random;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.Item;
import org.powerbot.script.wrappers.Tile;

import unicow.UniData.anim;
import unicow.UniData.items;

public class UniBank extends UniNode{

	public UniBank(MethodContext ctx) {
		super(ctx);
	}

	public static boolean dungeoneeringRing = false;
	public boolean checkPorter = false;
	public static boolean hasDuellingRing = false;
	Timer map = new Timer(0);
	UniMethod Method = new UniMethod(ctx);
	Bob summon = new Bob(ctx);
	@Override
	public boolean activate() {
		return DeltaUniBody.bank;
	}

	boolean once = false;
	@Override
	public void execute() {
		//if(dungeoneeringRing)
		if(new Tile(3449,3719,0).getMatrix(ctx).isReachable()||//daemonheim
				new Tile(2446,3085,0).getMatrix(ctx).isReachable()){//castle wars
			
			
			if(new Tile(3448,3719,0).distanceTo(ctx.players.local().getLocation())<7||//dungeon
					new Tile(2446,3085,0).distanceTo(ctx.players.local().getLocation())<4){//bank tile
				
				if(DeltaUniBody.usePorter&&!checkPorter){
					if(Method.equipmentContains(items.PORTER.getID())){
						System.out.println("Removing porter");
						for(Item g:ctx.equipment.select().id(items.PORTER.getID()).first()){
							g.interact("Remove");
						}
					}else checkPorter = true;//done with the thing
				}else
				if(ctx.summoning.isFamiliarSummoned()&&
						Bob.familiarItem!=-1){
					Method.state("Depositing familiar's horns");
						if(ctx.bank.open()){
							if(ctx.bank.depositFamiliar()){
								System.out.println("Setting familiar items to zero, -1");
								Bob.familiarItem=-1;
							}
						}
					
				}else
				if(DeltaUniBody.Bob &&ctx.summoning.getSummoningPoints()<5){
					Method.state("Restoring summoning points");
					if(invenContains(summon.summoningPotion)){
						if(ctx.bank.close()){
							System.out.println("Interacting with potion");
							for(int i: summon.summoningPotion){
								Method.interactInventory(i, "Drink", "Potion");
							}
						}
					}else if(ctx.bank.open()){
						System.out.println("Withdrawing pouch");
						if(Method.backPackIsFull()){
							ctx.bank.depositInventory();
						}else
						for(int g: summon.summoningPotion){
								if (ctx.bank.withdraw(g, 1)) {
									break;
								}
							}
					}
				}else if(DeltaUniBody.Bob&&ctx.summoning.isFamiliarSummoned() && ctx.summoning.getTimeLeft()<600){
					Method.state("Dismissing familiar");
					if(ctx.bank.close()&&ctx.hud.view(Window.FAMILIAR)){
						System.out.println("Now trying to press dismiss button");
						if(ctx.widgets.get(1188,12).isVisible()){
							ctx.widgets.get(1188,12).click();
						}else
						ctx.widgets.get(662,52).click();//dismiss familiar button
					}
				}else
				if(DeltaUniBody.Bob&&!ctx.summoning.isFamiliarSummoned()){
					Method.state("Summoning familiar");
					if(Method.inventoryGetCount(DeltaUniBody.pouchID)>=1){
						if(ctx.bank.close()){
							if(new Tile(2447,3083,0).equals(ctx.players.local().getLocation())){
								ctx.camera.turnTo(new Tile(2444,3084,0));
								new Tile(2444,3084,0).getMatrix(ctx).click();
							}else
							Method.interactInventory(DeltaUniBody.pouchID, "Summon", "Pouch");
						}
					}else if(ctx.bank.open()){
						System.out.println("Withdrawing pouch");
						if(Method.backPackIsFull()){
							ctx.bank.depositInventory();
						}else
						ctx.bank.withdraw(DeltaUniBody.pouchID, 1);
					}
				}else if(DeltaUniBody.bankLocation.contains("Castle Wars")&&!hasDuellingRing){
					for (int ring : UniBank.ringIDs) {
						while(Method.inventoryGetCount(ring)>=1) {
							System.out.println("Equipping ring");
							Method.interactInventory(ring, "Wear", "Ring");
						}
					}
					if(ctx.hud.view(Window.WORN_EQUIPMENT)){
						
						for (int ring : UniBank.ringIDs) {
							if (Method.equipmentContains(ring)) {
								hasDuellingRing = true;
								System.out.println("Found duelling ring");
							}
						}
					}
					if(!hasDuellingRing)
					ctx.bank.open();
					
					while(ctx.bank.isOpen()){
						System.out.println("Taking duelling ring from bank");
						
						if(Method.inventoryGetCount(UniBank.ringIDs)>=1){
							System.out.println("Inventory now has the ring");
							if(ctx.bank.close()){
								ctx.game.sleep(2000);
							}
							
						}else
						if(Method.bankContains(UniBank.ringIDs)){
							 System.out.println("Taking ring out of bank");
							for(int i: ringIDs){
								if(Method.bankContains(i)){
								ctx.bank.withdraw(i, 1);
								continue;
								}
							}
								
						}else {
							System.out.println("Out of duelling rings");
							DeltaUniBody.e = true;
							break;
						}
					}
					
				}else
				if(ctx.bank.open()){
					//Count horns
					if(ctx.bank.isOpen()){
						DeltaUniBody.hornCount = DeltaUniBody.hornCount+Method.inventoryGetCount(items.HORN.getID());
					}
					
					while(!once){
						DeltaUniBody.state = "Depositing inventory";
						ctx.bank.depositInventory();
						ctx.game.sleep(1400);
						ctx.bank.depositInventory();
						once = true;
					}
					//Ending script clauses
					if(!Method.bankContains(items.COWHIDE.getID())||
							(!Method.bankContains(items.HORN.getID())&&
									Method.inventoryGetCount(items.HORN.getID())<1)||
									(DeltaUniBody.Bob&&!Method.bankContains(DeltaUniBody.pouchID))||
							DeltaUniBody.foodSupport&&!Method.bankContains(DeltaUniBody.foodID)){
						System.out.println("Out of some item, shuttong down, :" );
						DeltaUniBody.e  =true;
					}
					
					while(Method.inventoryGetCount(238)>=1){
						DeltaUniBody.state = "Depositing noted horns";
						ctx.bank.deposit(238, 28);//noted horns
					}
					while(DeltaUniBody.usePorter&&Method.inventoryGetCount(items.PORTER.getID())<2){
						System.out.println("No porters, withdrawing");
						if(Method.bankContains(items.PORTER.getID())){
						ctx.bank.withdraw(items.PORTER.getID(), 2);
						}else {
							System.out.println("Out of porters");
							DeltaUniBody.e = true;
							break;
						}
					}
					
					if(Method.inventoryGetCount(items.HORN.getID())==1){
						if(Method.inventoryGetCount(items.COWHIDE.getID())==15&&DeltaUniBody.foodSupport||
								Method.inventoryGetCount(items.COWHIDE.getID())==24&&!DeltaUniBody.foodSupport){
							
							if(Method.inventoryGetCount(DeltaUniBody.foodID)>=8 || !DeltaUniBody.foodSupport){
								ctx.bank.close();
								System.out.println("Done");
								once = false;
								checkPorter = false;
								hasDuellingRing = false;
								DeltaUniBody.bank = false;
							}else ctx.bank.withdraw(DeltaUniBody.foodID, 10);
							
						}else{
						if(DeltaUniBody.foodSupport){
							   if(Method.inventoryGetCount(items.COWHIDE.getID())>15){
								   ctx.bank.deposit(items.COWHIDE.getID(), 27);
								}else ctx.bank.withdraw(items.COWHIDE.getID(), 15-Method.inventoryGetCount(items.COWHIDE.getID()));
							}else if(Method.inventoryGetCount(items.COWHIDE.getID())>24){
								   ctx.bank.deposit(items.COWHIDE.getID(), 28);
								}else ctx.bank.withdraw(items.COWHIDE.getID(), 24-Method.inventoryGetCount(items.COWHIDE.getID()));
						}
					}else
					if(Method.inventoryGetCount(items.HORN.getID())>1){
						int num = Method.inventoryGetCount(items.HORN.getID());
						ctx.bank.deposit(items.HORN.getID(), num-1);
					}else ctx.bank.withdraw(items.HORN.getID(), 1);
						
					
				}
			}else if(!map.isRunning()){
				if(DeltaUniBody.bankLocation.contains("Castle Wars")){
					Method.clickOnMap(new Tile(2446,3085,0).randomize(2, 3));
				}else if(DeltaUniBody.bankLocation.contains("Dungeoneering")){
					Method.clickOnMap(new Tile(3448,3719,0).randomize(2, 3));
				}
				
				
				map = new Timer(Random.nextInt(1200, 1600));
			}
		}else if(ctx.players.local().getAnimation()!=anim.DUNGEONEERINGTELEPORT.getID()||
				ctx.players.local().getAnimation()!=anim.CASTLEWARS.getID()){
			if(DeltaUniBody.bankLocation.contains("Castle Wars")){
			    teleportToCastleWars();
			}else if(DeltaUniBody.bankLocation.contains("Dungeoneering")){
				Method.useEquipmentItem(items.RINGOFDUNGEONEERING.getID(), "Teleport to Daemonheim");
			}
		}
	}


	/*
	 * 2552 - 2554 - 2556 - 2558 - 2560 - 2562  -2564 -2566
	 * */
	static int[] ringIDs = {2552,2554,2556,2558,2560,2562,2564,2566};
	Timer sleep = new Timer(0);
	private void teleportToCastleWars() {
		Method.state("Teleporting to Castle Wars");
		
		if(summon.SUMMONING_CLOSEBUTTON.isVisible()){
			Method.state("Closing interface");
			summon.SUMMONING_CLOSEBUTTON.click();
		}else
		if(!sleep.isRunning())
		if(ctx.widgets.get(1188,4).isVisible()){
			System.out.println("Selecting option");
			ctx.widgets.get(1188,4).click();
		}else {
			if(ctx.hud.view(Window.WORN_EQUIPMENT)){
				for(int ringID: ringIDs)
				for(Item ring : ctx.equipment.select().id(ringID).first()){
					if(ring.interact("Castle Wars")){
						sleep = new Timer(6000);
					}
				}
			}
		}
		
	}
	private boolean invenContains(int[] summoningPotion) {
		for(int i: summoningPotion){
			if(Method.inventoryGetCount(i)>=1){
				return true;
			}
		}
		return false;
	}

}
