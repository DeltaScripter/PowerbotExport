package slayer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Tile;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;

public class Grotworms extends SlayerNode{

	public Grotworms(MethodContext ctx) {
		super(ctx);
	}

	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	private Tile baseTile = null;
	
	Tile[] myTiles = new Tile[] { new Tile(3011, 3215, 0), new Tile(3005, 3217, 0), new Tile(3001, 3223, 0), 
			new Tile(2996, 3228, 0), new Tile(2991, 3232, 0), new Tile(2989, 3237, 0) };
	@Override
	public boolean activate() {
		return (slayerbody.currentTask=="grotworms"&& ctx.settings.get(183)!=0);
	}

	@Override
	public void execute() {
		Tile local = ctx.players.local().getLocation();
		
		if(m.objIsNotNull(71039)){
			if(baseTile!=null){
				//m.displayTileDifference(baseTile);
				if(new Tile(baseTile.getX()-33,baseTile.getY()-1,0).distanceTo(local)<25){//grotworm area
					teleported = false;
						m.fightNPC(15461,"Attack");//Grotworms
				}else m.clickOnMap(new Tile(baseTile.getX()-33,baseTile.getY()-1,0));//grotworm area
			}else {
				ctx.game.sleep(3000);
				baseTile = local;
			}
		}else
		if(new Tile(2990,3237,0).distanceTo(local)<7){//outside grotworm cave
			baseTile = null;
			m.interactO(70792, "Enter", "Cave");
		}else if(teleported){
			m.simpleWalk(myTiles, "Walking to grotworms");
			//m.walkTo(new Tile(2990,3237,0),"grotworm cave");//outside cave entrance
		}else if(TeleportLode.PORTSARIM.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.PORTSARIM.getTeleport(), "Port Sarim");
		
	}

}
