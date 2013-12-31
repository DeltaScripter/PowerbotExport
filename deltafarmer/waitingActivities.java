package deltafarmer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Random;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.Player;
import org.powerbot.script.wrappers.Tile;

public class waitingActivities extends FarmNode{

	public waitingActivities(MethodContext ctx) {
		super(ctx);
	}

	FarmMethod Method = new FarmMethod(ctx);
	Timer waitChop = new Timer(0);
	Timer backChop = new Timer(0);
	@Override
	public boolean activate() {
		return !FarmBody.harvest && !FarmBody.plantSeeds;
	}

	@Override
	public void execute() {
		chopLogsFalador();
		
	}

	private void chopLogsFalador() {
		Tile baseLocation  = new Tile(3034,3322,0);
		Tile bank = new Tile(3012,3355,0);
		Player local = ctx.players.local();
		
		while(local.getAnimation()!=-1){
			FarmBody.state = "Chopping tree";
			waitChop = new Timer(2000);
		}
		if(!waitChop.isRunning())
		if(Method.backPackIsFull()){
			if(bank.distanceTo(local.getLocation())<6){
				if(ctx.bank.isOpen()){
					ctx.bank.deposit(1511, 28);//logs
				}else ctx.bank.open();
			}else if(!ctx.movement.findPath(bank).traverse()){
				System.out.println("Too far away to walk");
			}
		}else if(ctx.bank.isOpen()){
			ctx.bank.close();
		}else
			if(baseLocation.distanceTo(local.getLocation())>20){
			FarmBody.state = "Getting back to chopping location";
			backChop = new Timer(6000);
			if(baseLocation.getMatrix(ctx).isReachable()){
				ctx.movement.findPath(baseLocation.randomize(3, 6)).traverse();
				ctx.game.sleep(Random.nextInt(2300, 3400));
			}
		}else if(!backChop.isRunning())
		if(Method.objIsNotNull("Tree")){
			if(Method.getObject("Tree").getLocation().distanceTo(local.getLocation())<4){
				Method.interactO("Tree","Chop down", "Tree");
				waitChop =new Timer(Random.nextInt(2800, 3500));
			}else{
				Method.clickOnMap(Method.getObject("Tree").getLocation().randomize(3, 6));
				ctx.game.sleep(Random.nextInt(700, 1500));
			}
		}
		
	}

}
