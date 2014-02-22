package unicow;

import org.powerbot.script.methods.Hud.Window;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Random;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.Tile;

import unicow.UniData.anim;
import unicow.UniData.items;

public class UniBank extends UniNode{

	public UniBank(MethodContext ctx) {
		super(ctx);
	}

	public static boolean dungeoneeringRing = false;
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
		if(dungeoneeringRing)
		if(new Tile(3449,3719,0).getMatrix(ctx).isReachable()){//daemonheim
			
			
			if(new Tile(3448,3719,0).distanceTo(ctx.players.local().getLocation())<7){//bank tile
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
					if(ctx.bank.close()&&ctx.hud.isVisible(Window.FAMILIAR)){
						if(ctx.widgets.get(1188,12).isVisible()){
							ctx.widgets.get(1188,12).click();
						}else
						ctx.widgets.get(662,52).click();//dismiss familiar button
					}else ctx.hud.view(Window.FAMILIAR);
				}else
				if(DeltaUniBody.Bob&&!ctx.summoning.isFamiliarSummoned()){
					Method.state("Summoning familiar");
					if(Method.inventoryGetCount(DeltaUniBody.pouchID)>=1){
						if(ctx.bank.close()){
							Method.interactInventory(DeltaUniBody.pouchID, "Summon", "Pouch");
						}
					}else if(ctx.bank.open()){
						System.out.println("Withdrawing pouch");
						if(Method.backPackIsFull()){
							ctx.bank.depositInventory();
						}else
						ctx.bank.withdraw(DeltaUniBody.pouchID, 1);
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
							DeltaUniBody.foodSupport&&!Method.bankContains(DeltaUniBody.foodID)){
						System.out.println("Out of some item, shuttong down");
						DeltaUniBody.e  =true;
					}
					
					while(Method.inventoryGetCount(238)>=1){
						DeltaUniBody.state = "Depositing noted horns";
						ctx.bank.deposit(238, 28);//noted horns
					}
					
					if(Method.inventoryGetCount(items.HORN.getID())==1){
						if(Method.inventoryGetCount(items.COWHIDE.getID())==15&&DeltaUniBody.foodSupport||
								Method.inventoryGetCount(items.COWHIDE.getID())==27&&!DeltaUniBody.foodSupport){
							
							if(Method.inventoryGetCount(DeltaUniBody.foodID)>=10 || !DeltaUniBody.foodSupport){
								ctx.bank.close();
								System.out.println("Done");
								once = false;
								DeltaUniBody.bank = false;
							}else ctx.bank.withdraw(DeltaUniBody.foodID, 10);
							
						}else{
						if(DeltaUniBody.foodSupport){
							   if(Method.inventoryGetCount(items.COWHIDE.getID())>15){
								   ctx.bank.deposit(items.COWHIDE.getID(), 27);
								}else ctx.bank.withdraw(items.COWHIDE.getID(), 15-Method.inventoryGetCount(items.COWHIDE.getID()));
							}else if(Method.inventoryGetCount(items.COWHIDE.getID())>27){
								   ctx.bank.deposit(items.COWHIDE.getID(), 28);
								}else ctx.bank.withdraw(items.COWHIDE.getID(), 27-Method.inventoryGetCount(items.COWHIDE.getID()));
						}
					}else
					if(Method.inventoryGetCount(items.HORN.getID())>1){
						int num = Method.inventoryGetCount(items.HORN.getID());
						ctx.bank.deposit(items.HORN.getID(), num-1);
					}else ctx.bank.withdraw(items.HORN.getID(), 1);
						
					
				}
			}else if(!map.isRunning()){
				Method.clickOnMap(new Tile(3448,3719,0).randomize(2, 3));
				map = new Timer(Random.nextInt(1200, 1600));
			}
		}else if(ctx.players.local().getAnimation()!=anim.DUNGEONEERINGTELEPORT.getID())
			Method.useEquipmentItem(items.RINGOFDUNGEONEERING.getID(), "Teleport to Daemonheim");
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
