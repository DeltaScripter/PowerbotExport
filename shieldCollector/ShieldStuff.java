package shieldCollector;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;

public class ShieldStuff extends ShieldNode{

	public ShieldStuff(ClientContext ctx) {
		super(ctx);
	}


	@Override
	public boolean activate() {
		return !m.backPackIsFull();
	}
	ShieldMethod m = new ShieldMethod(ctx);
	private int STAIRSTOP = 36778;
	private int DOOR = 36844;
	
	private Tile STAIRSTOPTILE = new Tile(3206,3229,2);
	private Tile DUKETILE = new Tile(3210,3221,1);
	
	private int DUKE = 741;
	
	private int SHIELD = 1540;
	private String option[] = {"So, are you going to","Yes","Elvarg, the dragon","I seek a"};
	@Override
	public void execute() {
		
	if(ctx.game.floor()==1){//proper floor
		
		if(m.getPastDoor(new Tile(3206,3221,1),new Tile(3210,3222,1), DOOR))
		  if(DUKETILE.distanceTo(ctx.players.local().tile())<6){
			  m.state("Ready to take shields");
			  
			  
			 if(!m.findOption(option))
			  if(!m.isChatting("Duke")){
				  m.npcInteract(DUKE, "");
			  }
		   }else {
			   ctx.movement.step(DUKETILE);
			   m.sleep(Random.nextInt(Random.nextInt(200, 500), Random.nextInt(700, 900)));
			   
		   }//below will get our player to the duke area
		}else if(ctx.game.floor()==2){
		   if(closeBank())
			if(m.getToNearByTile(STAIRSTOPTILE)){
				m.interactO(STAIRSTOP, "Climb-down", "Top stairs");
			}
		}else {
			m.state("Please start the script in Lumbridge bank.");
		}
	}
	private boolean closeBank() {
		if(ctx.bank.opened()){
			ShieldMainBody.stringCount = m.bankAmount(SHIELD);
			m.sleep(Random.nextInt(Random.nextInt(200, 500), Random.nextInt(700, 900)));
			ctx.bank.close();
			m.sleep(Random.nextInt(Random.nextInt(200, 500), Random.nextInt(700, 900)));
			return false;
		}
		return true;
	}


}
