package slayer;

import org.powerbot.script.Area;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;


public class Banshee extends SlayerNode{

	public Banshee(ClientContext ctx) {
		super(ctx);
	}

	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	private Tile baseTile;
	

	Area otherSide = new Area(new Tile[] { new Tile(3405, 3457, 0), new Tile(3407, 3531, 0), new Tile(3390, 3553, 0), 
			new Tile(3402, 3576, 0), new Tile(3456, 3575, 0), new Tile(3512, 3450, 0) });
	
	Tile[]toBanshees = new Tile[] {
			new Tile(3517,3515,0), new Tile(3499,3519,0), new Tile(3479,3518),
			new Tile(3460,3515,0), new Tile(3443,3514,0), new Tile(3426,3512),
			new Tile(3422,3529,0), new Tile(3423,2525,0), new Tile(3432,3545,0),
			new Tile(3440,3545,0)};
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
	public boolean activate() {
		return slayerbody.currentTask=="banshee" && ctx.varpbits.varpbit(183)!=0;
	}

	@Override
	public void execute() {
		//System.out.println("Banshees");
		Tile local = ctx.players.local().tile();
		
		if(new Tile(3443,3548,0).distanceTo(local)<30){
			teleported = false;
			baseTile = null;
			m.fightNPC(1612,"Attack");//Banshee
		}else if(teleported){
			
			if(!m.handleDoor(new Tile(3319,3468,0),24370,"Open"))
				m.simpleWalk(toBanshees, "Walking to Slayer Tower");
		}else if(TeleportLode.CANIFIS.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.CANIFIS.getTeleport(), "Canifis");}

}
