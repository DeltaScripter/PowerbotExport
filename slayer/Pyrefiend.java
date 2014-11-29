package slayer;

import org.powerbot.script.Area;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Npc;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;
public class Pyrefiend extends SlayerNode{

	public Pyrefiend(ClientContext ctx) {
		super(ctx);
	}
	Tile[] toFiend = new Tile[] {
			new Tile(2794,9997, 0),	new Tile(2777,10003, 0), new Tile(2784,10017,0),
			new Tile(2788,10032,0), new Tile(2768,10033,0), new Tile(2760,10010,0)};
	Tile[] toCave = new Tile[] { new Tile(2710, 3674, 0), new Tile(2710, 3669, 0), new Tile(2709, 3664, 0), 
			new Tile(2706, 3660, 0), new Tile(2703, 3656, 0), new Tile(2699, 3653, 0), 
			new Tile(2695, 3650, 0), new Tile(2692, 3646, 0), new Tile(2687, 3645, 0), 
			new Tile(2682, 3643, 0), new Tile(2677, 3642, 0), new Tile(2672, 3641, 0), 
			new Tile(2669, 3637, 0), new Tile(2666, 3633, 0), new Tile(2666, 3628, 0), 
			new Tile(2670, 3625, 0), new Tile(2675, 3623, 0), new Tile(2680, 3621, 0), 
			new Tile(2685, 3620, 0), new Tile(2690, 3619, 0), new Tile(2695, 3618, 0), 
			new Tile(2700, 3618, 0), new Tile(2705, 3617, 0), new Tile(2710, 3615, 0), 
			new Tile(2715, 3614, 0), new Tile(2720, 3612, 0), new Tile(2725, 3610, 0), 
			new Tile(2730, 3609, 0), new Tile(2735, 3609, 0), new Tile(2740, 3609, 0), 
			new Tile(2745, 3609, 0), new Tile(2750, 3610, 0), new Tile(2755, 3610, 0), 
			new Tile(2760, 3612, 0), new Tile(2765, 3613, 0), new Tile(2770, 3615, 0), 
			new Tile(2775, 3615, 0), new Tile(2780, 3614, 0), new Tile(2785, 3614, 0), 
			new Tile(2790, 3613, 0), new Tile(2795, 3614, 0) };
	@Override
	public boolean activate() {
		return slayerbody.currentTask=="pyrefiend"&& ctx.varpbits.varpbit(183)!=0;
	}

	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public void execute() {
   Tile local = ctx.players.local().tile();
		if(new Tile(2757,10006,0).distanceTo(local)<20){
			teleported = false;
			for(Npc gob: ctx.npcs.select().id(1633,1634,1635,1636).nearest().first()){
				if(!ctx.players.local().inMotion()&& m.getInteractingNPC()==null){
					if(gob.tile().distanceTo(local)<7){
						if(!gob.interact("Attack")){
							ctx.camera.turnTo(gob.tile().derive(1, 3));
						}else m.sleep(Random.nextInt(1000, 2000));
					}else m.clickOnMap(gob.tile());
				}else m.fightNPC(gob.id(), "Attack");
			}
		}else
   if(new Tile(2808,10002,0).matrix(ctx).reachable()){
	   m.simpleWalk(toFiend, "Walking to pyrefiends");
   }else
		if(new Tile(2795,3615,0).distanceTo(local)<7){
			m.interactO(84, "Enter", "Cave");
		}else if(teleported){
			m.simpleWalk(toCave, "Walking to dungeon");
		}else if(TeleportLode.FPROVINCE.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.FREMNICKPRIVINCE.getTeleport(), "Fremnick Province");
		
	}

}
