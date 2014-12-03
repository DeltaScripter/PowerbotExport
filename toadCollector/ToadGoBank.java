package toadCollector;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;

public class ToadGoBank extends toadNode{

	
	public Tile[] pathToBank = {
			new Tile(2423, 3501, 0),
			new Tile(2423, 3493, 0),
			new Tile(2423, 3485, 0),
			new Tile(2424, 3477, 0),
			new Tile(2424, 3469, 0),
			new Tile(2425, 3461, 0),
			new Tile(2428, 3453, 0),
			new Tile(2433, 3446, 0),
			new Tile(2438, 3439, 0),
			new Tile(2443, 3432, 0)
	};
	
	
	public ToadGoBank(ClientContext ctx) {
		super(ctx);
	}

	
	toadMethod m = new toadMethod(ctx);
	
	@Override
	public boolean activate() {
		return m.backPackIsFull();
	}
	
	
	private int STAIRSBOTTOM = 69505;
	Tile TILEOUTSIDEBANK = new Tile(2444,3433,0);
	Tile TILEATBANK = new Tile(2445,3424,1);
	@Override
	public void execute() {
		
		if(toadStuff.BANKAREABYBANK.matrix(ctx).reachable() && ctx.game.floor()==1){
			
			if(m.getToNearByTile(TILEATBANK)){
				if(openUpBank()){
					ctx.bank.depositInventory();
					m.sleep(Random.nextInt(Random.nextInt(300, 700), Random.nextInt(1400, 2000)));
				}
			}
			m.state("Going to use bank now");
			
		}else if(TILEOUTSIDEBANK.distanceTo(ctx.players.local().tile())<7){
			m.state("Going to climb up stairs");
			m.interactO(STAIRSBOTTOM, "", "Bottom of stairs");
		}else {
			
			if(!ctx.movement.findPath(new Tile(2443, 3432, 0).derive(1, 2)).traverse()){
				ctx.movement.newTilePath(pathToBank).randomize(1, 2).traverse();
				}
			//m.sleep(Random.nextInt(Random.nextInt(1100, 1200), Random.nextInt(1300, 1400)));
			m.state("Need to walk to bank");
		}
	}
	private boolean openUpBank() {
		if(ctx.bank.opened()){
			return true;
		}else ctx.bank.open();
		return false;
	}

}
