package quests;
/*package quests;

import lodestoneActivator.Data.TeleportLode;
import lodestoneActivator.Data.TeleportType;

import org.powerbot.script.methods.ClientContext;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.Tile;

public class DeathOfChivalry extends Node{

	public DeathOfChivalry(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return DeltaQuester.scriptToStart==39;
	}
	
	public Tile[] pathToSirOwen = {
		new Tile(2976, 3413, 0),new Tile (2986, 3419, 0),
		new Tile(2995, 3430, 0), new Tile(3008, 3431, 0),
		new Tile(3025, 3436, 0), new Tile(3037, 3448, 0),
		new Tile(3044, 3458, 0),new Tile(3044, 3457, 0),
		new Tile(3050, 3467, 0), new Tile(3051, 3480, 0),
	    new Tile(3051, 3492, 0),new Tile(3052, 3500, 0)
	};
	
	public boolean pickedArmour = false;
	
	Vars Vars = new Vars();
	Method Method = new Method(ctx);
	@Override
	public void execute() {
		if((ctx.varpbits.varpbit(3820)&0xFF)==10||
				(ctx.varpbits.varpbit(3820)&0xFF)==20){
			cs3();//
		}else
		if((ctx.varpbits.varpbit(3820)&0xFF)==5){
			cs2();//pick-up he armour & speak to the false-god
		}else cs1();//Start the quest
		
	}

	private void cs3() {
		Tile local = ctx.players.local().getLocation();
		String[] opt = {"By whose orders?","What do you want, maggot?","Are you suggesting I","No, I have something","He is my prisoner.","Yes, I have returned."};
		
		Method.skipPics();
		if(!Method.findOption(opt))
			if(!Method.isChatting("Guard")){
				
		if(ctx.game.floor()==1){
			Method.state("Now on floor 1");
		}else if(new Tile(3004,3575,0).distanceTo(local)<7){
			Method.interactO(86701, "Climb-up", "Stairs");
		}else if(!ctx.movement.findPath(new Tile(3004,3575,0)).traverse()){
			Method.interactO(86700, "Open", "Door");
		}
			}
		
	}

	private void cs2() {
		Tile local = ctx.players.local().getLocation();
		String[] opt = {"Yes, teleport us to the Black"};
		
		while(ctx.varpbits.varpbit(1113)!=0){
			Method.state("Cutscene occurring");
			Method.pressContinue();
		}
		//puts on all amrour..
			while(Method.inventoryContains("Black Knight captain's gauntlets")||
					Method.inventoryContains("Black Knight captain's boots")||
					Method.inventoryContains("Black Knight captain's helm")||
					Method.inventoryContains("Black Knight captain's cuirass")||
					Method.inventoryContains("Black Knight captain's gown")){
				System.out.println("Wearing armour");
				pickedArmour = true;
				Method.interactInventory("Black Knight captain's gauntlets","Wear", "Item");
				Method.interactInventory("Black Knight captain's boots", "Wear", "Item");
				Method.interactInventory("Black Knight captain's helm", "Wear", "Item");
				Method.interactInventory("Black Knight captain's cuirass", "Wear", "Item");
				Method.interactInventory("Black Knight captain's gown", "Wear", "Item");
			}
			
			if((ctx.varpbits.varpbit(3820)>>16&0xFF)!=2){//if picked up armour
				if(!Method.findOption(opt))
					if(!Method.isChatting("Blue man")){
						Method.npcInteract("Saradomin", "Talk-to");
					}
			}else
			if(new Tile(3047, 3500, 0).distanceTo(local)<6){//suit of armour loc
				
				Method.interactO(86699, "Pick up", "Suit of armour");
				
			}else if(!ctx.movement.findPath(new Tile(3047, 3500, 0)).traverse()){//suit of armour loc
				Method.state("Getting to suit of armour");
				cs1();
			}
		
		
	}

	private void cs1() {
		Tile local = ctx.players.local().getLocation();
		String[] opt = {"How can I help?","You are no god of","Saradomin must have had",
				"I guess I have one","Actually, it was a","The raven caws"};
		while(ctx.varpbits.varpbit(1113)!=0){
			Method.state("Cutscene occurring");
			Method.pressContinue();
		}
		Method.skipPics();
		if(!Method.startQuestOpen())
		if(new Tile(3052, 3500, 0).distanceTo(local)<7){//By Sir Owen
			if(!Method.findOption(opt))
				if(!Method.isChatting("Sir Owen")){
					Method.npcInteract("Sir Owen", "Talk-to");
				}
		}else if(Vars.DYNAMICV){
			Method.walking(pathToSirOwen, "Walking to Sir Owen", false);
		}else if(TeleportLode.FALADOR.getTile().distanceTo(local)<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.FALADOR.getTeleport(), "Falador");
		
	}

}
*/