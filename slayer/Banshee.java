package slayer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Random;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.Tile;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;

public class Banshee extends SlayerNode{

	public Banshee(MethodContext ctx) {
		super(ctx);
	}

	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	private Tile baseTile;
	@Override
	public boolean activate() {
		return (ctx.settings.get(2091)>>slayerbody.push&0x1F)==1&& ctx.settings.get(183)!=0;
	}

	@Override
	public void execute() {
		Tile local = ctx.players.local().getLocation();
		
		if(new Tile(3443,3548,0).distanceTo(local)<20){
			teleported = false;
			baseTile = null;
			if(m.getInteractingNPC()!=null && ctx.players.local().isInCombat()){
				m.fightNPC(1612,"Attack");//Banshee
			}else m.npcInteract(1612, "Attack");//Banshee
		}else
		if(m.canMakePath(new Tile(3443,3548,0))){//inside slayer tower(can reach?)
			m.walkTo(new Tile(3443,3548,0),"Slayer Tower");
		}else if(new Tile(3423,3532,0).distanceTo(local)<7){//outside slayer tower
			m.clickOnMap(new Tile(3443,3548,0));//banshee area in tower, gets in tower
		}else
		if(m.canMakePath(new Tile(3423,3532,0))){//outside slayer tower
			m.walkTo(new Tile(3423,3532,0),"Slayer Tower");
		}else
		if(m.objIsNotNull(11147)){//Checks if inside a cave
			
			if(baseTile!=null){
				m.displayTileDifference(baseTile);
				if(new Tile(baseTile.getX()+35,baseTile.getY()-19,0).distanceTo(local)<4){//exit loc
					m.interactO(3443, "Pass-through", "Exit");//exit
				}else
				if(new Tile(baseTile.getX()+29,baseTile.getY()-9,0).getMatrix(ctx).isReachable()){//second door
					m.clickOnMap(new Tile(baseTile.getX()+35,baseTile.getY()-19,0));//exit loc
				}else if(new Tile(baseTile.getX()+25,baseTile.getY()-9,0).distanceTo(local)<7){//second door loc
					m.interactO(3445, "Open", "Door");//last door
				}else
				if(new Tile(baseTile.getX(),baseTile.getY()-14,0).getMatrix(ctx).isReachable()){
					m.clickOnMap(new Tile(baseTile.getX()+25,baseTile.getY()-9,0));//walk to second door
				}else if(new Tile(baseTile.getX(),baseTile.getY()-14,0).distanceTo(local)<7){//first door loc
					m.interactO(3444, "Open", "Door");//first
				}else m.clickOnMap(new Tile(baseTile.getX(),baseTile.getY()-14,0));//first door loc
			}else{
				m.state("Determining location..");
				ctx.game.sleep(6500);
				baseTile = local;
			}
		}else
		if(new Tile(3405,3505, 0).distanceTo(local)<7){//outside cave entrance by church
			m.interactO(30571, "Open", "Trap door");//closed
			m.interactO(30572, "Climb-down", "Trap door");//opened
		}else if(new Tile(3405,3505, 0).distanceTo(local)<30){
			ctx.movement.findPath(new Tile(3405,3505, 0)).traverse();
		}else if(teleported){
			
			if(!m.handleDoor(new Tile(3319,3468,0),24370,"Open"))
			m.walkTo(new Tile(3400,3486, 0),"cave by church");//outside cave entrance by church
		
		}else if(TeleportLode.VARROCK.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.VARROCK.getTeleport(), "Varrock");
	}

}
