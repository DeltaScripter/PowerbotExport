package flaxSpinner;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;

public class FlaxStuff extends FlaxNode{

	public FlaxStuff(ClientContext ctx) {
		super(ctx);
	}

	//Items
	public static int FLAX = 1779;//wool ID is 1737(for testing purposes)
	public static int BOWSTRING = 1777;//ball of wool 1759(same as above)
	
	//Objects
	public static int STAIRMIDDLE = 33777;
	public static int STAIRTOP = 36775;
	public static int SPINNER = 36970;
	public static int BANK = 36786;
	
	//Tiles
	public static Tile SPINNERTILE = new Tile(3209,3213,1);
	public static Tile BANKTILE = new Tile(3208,3220,2);
	public static Tile TOPSTAIRTILE = new Tile(3205,3209,2);
	public static Tile MIDDLESTAIRTILE = new Tile(3205,3209,1);
	
	FlaxMethod m = new FlaxMethod(ctx);
	@Override
	public boolean activate() {
		return m.inventoryContains(FLAX);
	}

	@Override
	public void execute() {
		
		
		
	
		if(ctx.varpbits.varpbit(1176)!=0){
			m.state("Spinning flax...");
			m.sleep(Random.nextInt(200, Random.nextInt(100, 500)));
		}else
		if(ctx.bank.opened()){
			m.state("Closing the bank");
			ctx.bank.close();
		}else
		if(ctx.widgets.component(1371, 1).visible()){//the interface 
			
			if(ctx.widgets.component(1370, 56).text().contains("Bowstring")){//is the bowstring selected in the options menu?
				m.state("Selecting 'Spin'");
				ctx.widgets.component(1370, 38).interact("");//Select 'Spin' to begin spinnign the flax into bowstrings
				m.sleep(Random.nextInt(2000, 2500));
			}else ctx.widgets.component(1371, 44).component(6).interact("");//select flax in options menu
			//ctx.widgets.component(1371, 44).component(1).interact("");
		}else//Get to the spinning machine..
		if(m.tileDisctanceToPlayer(SPINNERTILE)<6 && ctx.game.floor()==1){
			m.state("Clicking on spinner");
			if(m.objIsNotNull(SPINNER)&&
					m.getObject(SPINNER).tile().distanceTo(ctx.players.local().tile())>6){
				ctx.movement.step(SPINNERTILE);
				m.sleep(Random.nextInt(1200, 1800));
			}else
			if(!ctx.players.local().inMotion()){
				m.interactO(SPINNER, "", "Spinner");//open up the spinning interface
			}
		}else if (SPINNERTILE.matrix(ctx).reachable() && ctx.game.floor()==1){
			m.state("Walking towards spinner");
			ctx.movement.step(SPINNERTILE);
		}else if(ctx.game.floor()==2){//top floor
			if(m.getToNearByTile(TOPSTAIRTILE)){
				m.state("Clicking on stairs");
				m.interactO(STAIRTOP, "", "Top Stairs");
			}
		}
		
		
		
	}

}
