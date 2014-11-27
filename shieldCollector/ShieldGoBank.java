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
	private String option[] = {"I seek a"};
	ShieldMethod m = new ShieldMethod(ctx);
	private Tile BANKTILE = new Tile(3208,3220,2);
	private Tile MIDDLESTAIRSTILE = new Tile(3206,3229,1);
	private int DOOR = 36844;
	private int STAIRSMIDDLE = 36777;

	public long tempTime = 0;
	private int SHIELD = 1540;
	
	private boolean setTime = true;
	
	@Override
	public void execute() {
		
		 if(ctx.game.floor()==2){//if at bank level
			if(m.getToNearByTile(BANKTILE)){//get to bank tile
				if(ctx.bank.opened()){
					ShieldMainBody.amount = m.bankAmount(SHIELD);
					
					if(System.currentTimeMillis()!=tempTime && setTime){
						if((tempTime - System.currentTimeMillis())<10000){
						ShieldMainBody.timeCycle =  tempTime - System.currentTimeMillis();
						System.out.println("Set time to : " + tempTime);
						}else System.out.println("Got time, but not showing b/c unrealistic");
						tempTime = System.currentTimeMillis();
						setTime = false;
					}
					
					ctx.bank.depositInventory();
				}else {
					setTime = true;
					ctx.bank.open();
				}
			}
		}else if(!m.findOption(option))
			   if(!m.isChatting(""))
			if(m.getPastDoor(new Tile(3210,3222,1), new Tile(3206,3221,1), DOOR)){
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
