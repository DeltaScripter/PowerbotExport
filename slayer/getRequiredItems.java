package slayer;

import org.powerbot.script.rt6.ClientContext;
public class getRequiredItems extends SlayerNode{

	public getRequiredItems(ClientContext ctx) {
		super(ctx);
	}

	
	private int[] bankItems = {8009,4155};//
	private int[] amountOfItem = {10,1};
	@Override
	public boolean activate() {
		return (m.inventoryGetCount(8009)<1||//fally tablet
				m.inventoryGetCount(slayerbody.FOODID)<1||
				m.inventoryGetCount(4155)<1);//fally tablet
	}
	SMethod m = new SMethod(ctx);
	@Override
	public void execute() {
		System.out.println("Changing currentTask");
		slayerbody.currentTask  ="Getting required items!";
		if((m.inventoryGetCount(8009)<1)||
				(m.inventoryGetCount(4155)<1)|| m.inventoryGetCount(slayerbody.FOODID)<1){
			slayerbody.goBank = true;
		}
		if(m.getInteractingNPC()!=null){
			System.out.println("Breaking tablet");
			 m.interactInventory(8009,"Break", "Falador tablet");
		}else if(slayerbody.goBank){
			System.out.println("Trying to bank - getrequired items");
			
			m.bankItems(bankItems, amountOfItem);
		}
	
		
	}

}
