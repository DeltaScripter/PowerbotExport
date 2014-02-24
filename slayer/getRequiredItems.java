package slayer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.Hud.Window;
import org.powerbot.script.wrappers.Tile;

public class getRequiredItems extends SlayerNode{

	public getRequiredItems(MethodContext ctx) {
		super(ctx);
	}

	
	private int[] bankItems = {8009,4155};//
	private int[] amountOfItem = {20,1};
	@Override
	public boolean activate() {
		return (m.inventoryGetCount(8009)<1||
				m.inventoryGetCount(slayerbody.FOODID)<1||
				m.inventoryGetCount(4155)<1);//fally tablet
	}
	SMethod m = new SMethod(ctx);
	@Override
	public void execute() {
		if((m.inventoryGetCount(8009)<1)||
				(m.inventoryGetCount(4155)<1)|| m.inventoryGetCount(slayerbody.FOODID)<1){
			slayerbody.goBank = true;
		}
		if(m.getInteractingNPC()!=null){
			 m.interactInventory(8009,"Break", "Falador tablet");
		}else
		while(slayerbody.goBank){
			System.out.println("Trying to bank - getrequired items");
			if(ctx.hud.isVisible(Window.FRIENDS)){
				System.out.println("Breaking friends");
				break;
			}
			m.bankItems(bankItems, amountOfItem);
		}
	
		
	}

}
