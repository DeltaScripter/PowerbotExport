package shieldCollector;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;

public class ShieldGoBank extends ShieldNode{


	
	
	public ShieldGoBank(ClientContext ctx) {
		super(ctx);
	}

	

	@Override
	public boolean activate() {
		return m.backPackIsFull();
	}
	
	ShieldMethod m = new ShieldMethod(ctx);
	private Tile BANKTILE = new Tile(3208,3220,2);
	private Tile MIDDLESTAIRSTILE = new Tile(3206,3229,1);
	private int DOOR = 36844;
	private int STAIRSMIDDLE = 36777;

	public long tempTime = 0;
	
	@Override
	public void execute() {
		
		 if(ctx.game.floor()==2){//if at bank level
			if(m.getToNearByTile(BANKTILE)){//get to bank tile
				if(ctx.bank.opened()){
					if(System.currentTimeMillis()!=tempTime){
						ShieldMainBody.timeCycle =  tempTime - System.currentTimeMillis();
						System.out.println("Set time initially : " + tempTime);
						tempTime = System.currentTimeMillis();
						
					}
					
					ctx.bank.depositInventory();
				}else ctx.bank.open();
			}
		}else if(m.getPastDoor(new Tile(3210,3222,1), new Tile(3206,3221,1), DOOR)){
		  if(MIDDLESTAIRSTILE.distanceTo(ctx.players.local().tile())<6){
			   m.interactO(STAIRSMIDDLE, "Climb-up","Middle stairs");
		  }else {
			  ctx.movement.step(MIDDLESTAIRSTILE);
			  m.sleep(Random.nextInt(1400, Random.nextInt(1600, 2000)));
		  }
			
		}
	
	}
	private boolean openUpBank() {
		if(ctx.bank.opened()){
			return true;
		}else ctx.bank.open();
		return false;
	}

}
