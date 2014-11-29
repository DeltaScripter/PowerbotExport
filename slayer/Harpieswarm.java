package slayer;

import org.powerbot.script.Area;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Npc;

import slayer.SMethod.TeleportLode;
import slayer.SMethod.TeleportType;
public class Harpieswarm extends SlayerNode{

	public Harpieswarm(ClientContext ctx) {
		super(ctx);
	}

	Tile[] toSwarm = new Tile[] { new Tile(2763, 3145, 0), new Tile(2767, 3142, 0), new Tile(2772, 3140, 0), 
			new Tile(2776, 3137, 0), new Tile(2780, 3134, 0), new Tile(2783, 3130, 0), 
			new Tile(2787, 3127, 0), new Tile(2791, 3124, 0), new Tile(2796, 3122, 0), 
			new Tile(2800, 3119, 0), new Tile(2805, 3117, 0), new Tile(2810, 3116, 0), 
			new Tile(2815, 3114, 0), new Tile(2820, 3112, 0), new Tile(2825, 3111, 0), 
			new Tile(2830, 3110, 0), new Tile(2835, 3110, 0), new Tile(2840, 3110, 0), 
			new Tile(2845, 3109, 0), new Tile(2850, 3109, 0), new Tile(2855, 3109, 0), 
			new Tile(2860, 3109, 0), new Tile(2865, 3110, 0), new Tile(2868, 3110, 0) };
	@Override
	public boolean activate() {
		return (slayerbody.currentTask=="harpie bug swarms" && ctx.varpbits.varpbit(183)!=0);
	}
	static boolean hasLantern = false;
	
	private int[] bankItems = {7051};//lantern bug
	private int[] amountOfItem = {1};
	
	SMethod m = new SMethod(ctx);
	private boolean teleported = false;
	@Override
	public void execute() {
   Tile local = ctx.players.local().tile();
	System.out.println("Here");
   while(!hasLantern && m.inventoryGetCount(7051)==1){
	   m.state("Equipping bug lantern");
	   m.interactInventory(7051, "Wield", "Lantern");
   }
   if(!hasLantern)
	if(!m.EquipmentContains(7051)){
		slayerbody.goBank = true;
	}else hasLantern = true;
   
	if(slayerbody.goBank){
		if(m.getInteractingNPC()!=null){
			m.interactInventory(8009,"Break", "Falador tablet");
		}else
		m.bankItems(bankItems, amountOfItem);
	}else 
		if(new Tile(2868, 3110, 0).distanceTo(local)<18){
			teleported = false;
			for(Npc gob: ctx.npcs.select().id(3153).nearest().first()){
				if(!ctx.players.local().inMotion()&& m.getInteractingNPC()==null){
					if(gob.tile().distanceTo(local)<7){
						if(!gob.interact("Attack")){
							ctx.camera.turnTo(gob.tile().derive(1, 3));
						}else m.sleep(Random.nextInt(1000, 2000));
					}else m.clickOnMap(gob.tile());
				}else m.fightNPC(gob.id(), "Attack");
			}
		}else if(teleported){
			m.simpleWalk(toSwarm, "Walking to goblins");
			//m.walkTo(new Tile(3248,3236,0),"lumbridge river");//river-side
		}else if(TeleportLode.KARAMJA.getTile().distanceTo(local)<10){
			teleported = true;
		}else m.teleportTo(TeleportType.KARAMJA.getTeleport(), "Karamja");
		
	}

}
