package unicow;

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
	@Override
	public boolean activate() {
		return DeltaUniBody.bank;
	}

	@Override
	public void execute() {
		if(dungeoneeringRing)
		if(new Tile(3449,3719,0).getMatrix(ctx).isReachable()){//daemonheim
			while(Method.inventoryGetCount(10878)>=1){
				Method.interactInventory(10878, "Drop", "Bag");
			}
			while(Method.inventoryGetCount(526)>=1){
				Method.interactInventory(526, "Drop", "bones");
			}
			while(Method.inventoryGetCount(10859)>=1){
				Method.interactInventory(10859, "Drop", "tea");
			}
			if(new Tile(3448,3719,0).distanceTo(ctx.players.local().getLocation())<7){//bank tile
				if(ctx.bank.open()){
					
					if(!Method.bankContains(items.COWHIDE.getID())||
							!Method.bankContains(items.HORN.getID())){
						DeltaUniBody.e  =true;
					}
					
					if(Method.inventoryGetCount(items.HORN.getID())==1){
						if(Method.inventoryGetCount(items.COWHIDE.getID())==11){
							if(Method.inventoryGetCount(379)>=15){
								ctx.bank.close();
								DeltaUniBody.bank = false;
							}else ctx.bank.withdraw(379, 15);
							
						}else if(Method.inventoryGetCount(items.COWHIDE.getID())>11){
							ctx.bank.deposit(items.COWHIDE.getID(), 28);
						}else ctx.bank.withdraw(items.COWHIDE.getID(), 11-Method.inventoryGetCount(items.COWHIDE.getID()));
							
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

}
