package slayer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.Npc;
import org.powerbot.script.wrappers.Tile;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;

public class Vannaka extends SlayerNode{

	public Vannaka(MethodContext ctx) {
		super(ctx);
	}
	Tile[] toCave = new Tile[] { new Tile(3066, 3506, 0), new Tile(3072, 3503, 0), new Tile(3078, 3500, 0), 
			new Tile(3084, 3500, 0), new Tile(3087, 3494, 0), new Tile(3087, 3488, 0), 
			new Tile(3092, 3484, 0), new Tile(3093, 3478, 0), new Tile(3094, 3472, 0), 
			new Tile(3095, 3466, 0), new Tile(3096, 3467, 0) };
	
	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public boolean activate() {
		return ctx.settings.get(183)==0 && slayerbody.master=="Vannaka";
	}
	boolean emergency  =false;
	@Override
	public void execute() {
		Tile local = ctx.players.local().getLocation();
		final String opt[] = {"No thanks","No, that's okay"};
		
		if(m.getInteractingNPC()!=null&&ctx.players.local().isInCombat()&&new Tile(2909,3419,0).distanceTo(local)>30){
			System.out.println("Player is still being attacked, boolean now true");
			emergency = true;
		}
		if(emergency){
			
			if(new Tile(2963,3379,0).distanceTo(local)<30){//falador area
				emergency = false;
			}else m.interactInventory(8009,"Break", "Falador tablet");
			
		}else
		if(new Tile(3105,9910,0).getMatrix(ctx).isReachable()){
			if(new Tile(3140,9914,0).distanceTo(local)<10){
				teleported = false;
				if(!m.findOption(opt))
					if(!m.isChatting("Vannaka")){
						m.npcInteract(1597, "Get-task");
					}
			}else m.clickOnMap(new Tile(3140,9914,0));
		}else if(new Tile(3102,9909,0).getMatrix(ctx).isReachable()){
			if(new Tile(3102,9909,0).distanceTo(local)<7){
				m.interactO(29315, "Open", "Gate");
			}else {
				m.state("Walking to closed gate");
				ctx.movement.findPath(new Tile(3102,9909,0)).traverse();
			}
		}else
		if(new Tile(3095,3469,0).distanceTo(local)<7){
			m.interactO(26933, "Open", "Trapdoor");
			m.interactO(26934, "Climb-down", "Trapdoor");
		}else if(teleported){
			m.simpleWalk(toCave, "Walking to dungeon");
		}else if(TeleportLode.EDGEVILLE.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.EDGEVILLE.getTeleport(), "Edgeville");
	}

}
