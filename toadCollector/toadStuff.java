package toadCollector;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;

public class toadStuff extends toadNode{

	public toadStuff(ClientContext ctx) {
		super(ctx);
	}

	public Tile[] pathToToad = {
			new Tile(2436,3453,0),
			new Tile(2428,3472,0),
			new Tile(2423,3478,0),
			new Tile(2426,3480,0),
			new Tile(2422,3489,0),
			new Tile(2422,3505,0),
			new Tile(2420,3510,0)
	};
	

	private int TOAD = 2150;//Also the ID for the 'groundItem' of the toad
	
	toadMethod m = new toadMethod(ctx);
	@Override
	public boolean activate() {
		return !m.backPackIsFull();
	}
	public static Tile TOADAREATILE = new Tile(2419,3512,0);
	public static Tile BANKAREABYSTAIRS = new Tile(2445,3433,1);
	public static Tile BANKAREABYBANK = new Tile(2445,3426,1);
	
	private int STAIRSTOP = 69404;
	@Override
	public void execute() {
		
		if(TOADAREATILE.distanceTo(ctx.players.local().tile())<15){
			
			if(m.gItemIsNotNull(TOAD)){
				if(m.getGroundItem(TOAD).tile().distanceTo(ctx.players.local().tile())<4){
					m.interactG(TOAD, "Take", "");
					m.sleep(Random.nextInt(Random.nextInt(200, 400), Random.nextInt(450, 500)));
				}else if(m.getGroundItem(TOAD).tile().distanceTo(ctx.players.local().tile())<6 &&
						!ctx.players.local().inMotion()){
					m.interactG(TOAD, "Take", "");
					m.sleep(Random.nextInt(300, Random.nextInt(400, 1000)));
				}else {
					
					ctx.movement.step(m.getGroundItem(TOAD).tile());
					m.sleep(Random.nextInt(1200, 1700));
					
				}
				
			}
			m.state("Now hunting toads");
			
			
		}else if (BANKAREABYBANK.matrix(ctx).reachable()&&ctx.game.floor()==1){//in bank area..
		   if(closeBank())
			if(m.getToNearByTile(BANKAREABYSTAIRS)){
				m.interactO(STAIRSTOP, "", "Stairs at bank level");
			}
			m.state("Need to get out of bank area");
		}else {
			
			if(!ctx.movement.findPath(new Tile(2420,3510,0).derive(1, 2)).traverse()){
				ctx.movement.newTilePath(pathToToad).randomize(1, 2).traverse();
				}
		//	m.sleep(Random.nextInt(Random.nextInt(1100, 1200), Random.nextInt(1300, 1400)));
			m.state("Need to walk to toads");
		}
	
		
	}
	private boolean closeBank() {
		if(ctx.bank.opened()){
			m.sleep(Random.nextInt(Random.nextInt(200, 500), Random.nextInt(700, 900)));
			ctx.bank.close();
			m.sleep(Random.nextInt(Random.nextInt(200, 500), Random.nextInt(700, 900)));
			return false;
		}
		return true;
	}


}
