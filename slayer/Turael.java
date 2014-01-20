package slayer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Tile;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;

public class Turael extends SlayerNode{

	public Turael(MethodContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return ctx.settings.get(183)==0;
	}

	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	private boolean emergency = false;
	@Override
	public void execute() {//8009
		final String opt[] = {"No thanks","No, that's okay"};
		Tile local = ctx.players.local().getLocation();
		
		if(m.getInteractingNPC()!=null&&new Tile(2909,3419,0).distanceTo(local)>30){
			System.out.println("Player is still being attacked, boolean now true");
			emergency = true;
		}
		if(emergency){
			
			if(new Tile(2963,3379,0).distanceTo(local)<30){//falador area
				emergency = false;
			}else m.interactInventory(8009,"Break", "Falador tablet");
			
		}else
		if(new Tile(2909,3419,0).distanceTo(local)<7){
			teleported = false;
			if(!m.findOption(opt))
				if(!m.isChatting("Turael")){
					m.npcInteract(8480, "Get-task");
				}
		}else if(teleported){
			m.walkTo(new Tile(2909,3419,0), "Turael");
		}else if(TeleportLode.TAVERLY.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.TAVERLY.getTeleport(), "Taverly");
		
	}

}
