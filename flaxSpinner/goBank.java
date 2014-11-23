package flaxSpinner;

import org.powerbot.script.rt6.ClientContext;

public class goBank extends FlaxNode{

	public goBank(ClientContext ctx) {
		super(ctx);
	}

	
	FlaxMethod m = new FlaxMethod(ctx);
	
	@Override
	public boolean activate() {
		return !m.inventoryContains(FlaxStuff.FLAX);
	}

	@Override
	public void execute() {
		
		if(ctx.bank.opened()){
			
			if(!m.bankContains(FlaxStuff.FLAX)){
				m.state("No more flax in bank!");
				System.out.println("Shutting down DeltaBowString");
				this.ctx.controller().stop();
			}else
			if(m.inventoryContains(FlaxStuff.BOWSTRING)){
				m.state("Depositing inventory in bank");
			   ctx.bank.depositInventory();
			}else {
				m.state("Withdrawing some flax from bank");
				ctx.bank.withdraw(FlaxStuff.FLAX, 28);
			}
		}else
		if(ctx.game.floor()==2){
			if(FlaxStuff.BANKTILE.matrix(ctx).reachable()){
				if(m.getToNearByTile(FlaxStuff.BANKTILE)){
					m.state("Clicking on bank booth");
					m.interactO(FlaxStuff.BANK, "", "Bank Booth");
				}
			}
		}else
		
		if(ctx.game.floor()==1){
			
			if(FlaxStuff.MIDDLESTAIRTILE.matrix(ctx).reachable()){
				if(m.getToNearByTile(FlaxStuff.MIDDLESTAIRTILE)){
					m.state("Clicking on middle stairs");
					m.interactO(FlaxStuff.STAIRMIDDLE, "Climb-up", "Middle Stairs");
				}
			}else m.state("Lost in a mad mad world");
		}
	}

}
