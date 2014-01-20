package slayer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.Hud.Window;

public class getRequiredItems extends SlayerNode{

	public getRequiredItems(MethodContext ctx) {
		super(ctx);
	}

	private int[] bankItems = {8009};//water skins & ice cooler
	private int[] amountOfItem = {20};
	@Override
	public boolean activate() {
		return (m.inventoryGetCount(8009)<1||
				m.inventoryGetCount(slayerbody.FOODID)<2);//fally tablet
	}
	SMethod m = new SMethod(ctx);
	@Override
	public void execute() {
		if((m.inventoryGetCount(8009)<1)){
			slayerbody.goBank = true;
		}
		while(slayerbody.goBank){
			if(ctx.hud.isVisible(Window.FRIENDS)){
				System.out.println("Breaking friends");
				break;
			}
			m.bankItems(bankItems, amountOfItem);
		}
	
		
	}

}
