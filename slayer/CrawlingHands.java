package slayer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.Area;
import org.powerbot.script.wrappers.Npc;
import org.powerbot.script.wrappers.Tile;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;

public class CrawlingHands extends SlayerNode{

	public CrawlingHands(MethodContext ctx) {
		super(ctx);
	}

	private Tile baseTile;
	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public boolean activate() {
		return slayerbody.currentTask=="crawlinghands" && ctx.settings.get(183)!=0;
	}

	Area otherSide = new Area(new Tile[] { new Tile(3405, 3457, 0), new Tile(3407, 3531, 0), new Tile(3390, 3553, 0), 
			new Tile(3402, 3576, 0), new Tile(3456, 3575, 0), new Tile(3512, 3450, 0) });
	
	Tile[]toCrawlingHands = new Tile[] { 
			new Tile(3430, 3484, 0), new Tile(3436, 3486, 0), new Tile(3442, 3488, 0), 
			new Tile(3448, 3492, 0), new Tile(3444, 3497, 0), new Tile(3442, 3503, 0), 
			new Tile(3440, 3509, 0), new Tile(3435, 3513, 0), new Tile(3429, 3514, 0), 
			new Tile(3425, 3519, 0), new Tile(3422, 3525, 0), new Tile(3422, 3531, 0), 
			new Tile(3422, 3537, 0), new Tile(3419, 3543, 0), new Tile(3413, 3543, 0), 
			new Tile(3407, 3544, 0), new Tile(3404, 3546, 0), new Tile(3402, 3551, 0) };
	
	Tile[] toDungeon = new Tile[] { 
			new Tile(3212, 3379, 0), new Tile(3211, 3385, 0), new Tile(3211, 3391, 0), 
			new Tile(3210, 3397, 0), new Tile(3210, 3403, 0), new Tile(3209, 3409, 0), 
			new Tile(3209, 3415, 0), new Tile(3211, 3421, 0), new Tile(3216, 3425, 0), 
			new Tile(3222, 3427, 0), new Tile(3228, 3428, 0), new Tile(3234, 3430, 0), 
			new Tile(3240, 3430, 0), new Tile(3246, 3430, 0), new Tile(3252, 3430, 0), 
			new Tile(3258, 3430, 0), new Tile(3264, 3429, 0), new Tile(3270, 3429, 0), 
			new Tile(3276, 3429, 0), new Tile(3282, 3429, 0), new Tile(3287, 3433, 0), 
			new Tile(3287, 3439, 0), new Tile(3286, 3445, 0), new Tile(3287, 3451, 0), 
			new Tile(3289, 3457, 0), new Tile(3294, 3461, 0), new Tile(3300, 3462, 0), 
			new Tile(3306, 3462, 0), new Tile(3312, 3465, 0), new Tile(3318, 3468, 0), 
			new Tile(3324, 3469, 0), new Tile(3328, 3474, 0), new Tile(3333, 3478, 0), 
			new Tile(3339, 3480, 0), new Tile(3345, 3483, 0), new Tile(3350, 3487, 0), 
			new Tile(3356, 3489, 0), new Tile(3361, 3485, 0), new Tile(3367, 3484, 0), 
			new Tile(3373, 3486, 0), new Tile(3379, 3484, 0), new Tile(3385, 3483, 0), 
			new Tile(3391, 3485, 0), new Tile(3397, 3487, 0), new Tile(3403, 3488, 0) };
	@Override
	public void execute() {
		System.out.println("Crawling hands");
	Tile local = ctx.players.local().getLocation();
		
		if(new Tile(3406,3545,0).distanceTo(local)<15){
			teleported = false;
			baseTile = null;
			for(Npc gob: ctx.npcs.select().id(1651,1650,1655,1648,1657).nearest().first()){
				if(!ctx.players.local().isInMotion()&& m.getInteractingNPC()==null){
					if(gob.getLocation().distanceTo(local)<7){
						if(!gob.interact("Attack")){
							ctx.camera.turnTo(gob.getLocation().randomize(1, 3));
						}else ctx.game.sleep(Random.nextInt(1000, 2000));
					}else m.clickOnMap(gob.getLocation());
				}else m.fightNPC(gob.getId(), "Attack");
			}
		}else
		if(otherSide.contains(local)){//inside slayer tower(can reach?)
			m.simpleWalk(toCrawlingHands, "Walking to slayer tower");
			//m.walkTo(new Tile(3406,3545,0),"Slayer Tower");
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
				m.simpleWalk(toDungeon, "Walking to Slayer Tower");
		//	m.walkTo(new Tile(3400,3486, 0),"cave by church");//outside cave entrance by church
		
		}else if(TeleportLode.VARROCK.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.VARROCK.getTeleport(), "Varrock");
		
	}

}
