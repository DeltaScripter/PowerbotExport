package slayer;

import org.powerbot.script.Area;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Npc;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;
public class Turael extends SlayerNode{

	public Turael(ClientContext ctx) {
		super(ctx);
	}

	Tile[] pathToTurael = new Tile[] {
			new Tile(2876, 3443, 0), new Tile(2881, 3439, 0), new Tile(2882, 3433, 0), 
			new Tile(2881, 3427, 0), new Tile(2882, 3421, 0), new Tile(2887, 3417, 0), 
			new Tile(2893, 3414, 0), new Tile(2899, 3415, 0), new Tile(2905, 3415, 0), 
			new Tile(2909, 3420, 0), new Tile(2910, 3423, 0) };
	@Override
	public boolean activate() {
		return ctx.varpbits.varpbit(183)==0 && slayerbody.master=="Turael" && slayerbody.goBank==false;
	}

	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	private boolean emergency = false;
	@Override
	public void execute() {//8009
		final String opt[] = {"No thanks","No, that's okay"};
		Tile local = ctx.players.local().tile();
		
		if(m.getInteractingNPC()!=null&&ctx.players.local().inCombat()&&new Tile(2909,3419,0).distanceTo(local)>30){
			System.out.println("Player is still being attacked, boolean now true");
			emergency = true;
		}
		if(emergency){
			
			if(new Tile(2963,3379,0).distanceTo(local)<30){//falador area
				emergency = false;
			}else m.interactInventory(8009,"Break", "Falador tablet");
			
		}else
		if(new Tile(2909,3419,0).distanceTo(local)<6){
			teleported = false;
			if(!m.findOption(opt))
				if(!m.isChatting("Turael")){
					m.npcInteract(8480, "Get-task");
				}
		}else if(teleported){
			m.simpleWalk(pathToTurael, "Walking to Turael");
			//m.walkTo(new Tile(2909,3419,0), "Turael");
		}else if(TeleportLode.TAVERLY.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.TAVERLY.getTeleport(), "Taverly");
		
	}

}
