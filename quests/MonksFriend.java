package quests;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.Player;
import org.powerbot.script.wrappers.Tile;

import quests.Vars.TeleportLode;
import quests.Vars.TeleportType;

public class MonksFriend extends Node{

	public MonksFriend(MethodContext ctx) {
		super(ctx);
	}
	
	final Tile[] pathToMonastry = new Tile[] {
			new Tile(2632, 3348, 0), new Tile(2634, 3343, 0), new Tile(2635, 3338, 0), 
			new Tile(2637, 3333, 0), new Tile(2642, 3331, 0), new Tile(2647, 3329, 0), 
			new Tile(2648, 3324, 0), new Tile(2649, 3319, 0), new Tile(2652, 3315, 0), 
			new Tile(2654, 3310, 0), new Tile(2650, 3307, 0), new Tile(2645, 3307, 0), 
			new Tile(2641, 3304, 0), new Tile(2637, 3301, 0), new Tile(2633, 3298, 0), 
			new Tile(2628, 3298, 0), new Tile(2623, 3297, 0), new Tile(2618, 3297, 0), 
			new Tile(2613, 3297, 0), new Tile(2608, 3297, 0), new Tile(2606, 3292, 0), 
			new Tile(2606, 3287, 0), new Tile(2606, 3282, 0), new Tile(2606, 3277, 0), 
			new Tile(2602, 3274, 0), new Tile(2599, 3270, 0), new Tile(2597, 3265, 0), 
			new Tile(2597, 3260, 0), new Tile(2596, 3255, 0), new Tile(2592, 3252, 0), 
			new Tile(2595, 3248, 0), new Tile(2599, 3245, 0), new Tile(2602, 3241, 0), 
			new Tile(2602, 3236, 0), new Tile(2603, 3231, 0), new Tile(2605, 3226, 0), 
			new Tile(2607, 3221, 0), new Tile(2605, 3216, 0), new Tile(2605, 3215, 0) };

	final Tile[] pathToStoneCircle = new Tile[] {
			new Tile(2605, 3210, 0), new Tile(2605, 3215, 0), new Tile(2606, 3220, 0), 
			new Tile(2601, 3222, 0), new Tile(2596, 3222, 0), new Tile(2591, 3222, 0), 
			new Tile(2586, 3221, 0), new Tile(2581, 3221, 0), new Tile(2576, 3219, 0), 
			new Tile(2571, 3219, 0), new Tile(2566, 3221, 0), new Tile(2561, 3219, 0), 
			new Tile(2561,3221, 0) };
	
	final Tile[] pathToDrunkMan = new Tile[] { 
			new Tile(2605, 3212, 0), new Tile(2605, 3217, 0), new Tile(2605, 3222, 0), 
			new Tile(2604, 3227, 0), new Tile(2607, 3231, 0), new Tile(2611, 3234, 0), 
			new Tile(2612, 3239, 0), new Tile(2615, 3243, 0), new Tile(2617,3243,0) };
	
	public int bankItems[] = {90,1937,1511};
	public int bankItemAmount[] = {1,1,1};
	
	private Method Method = new Method(ctx);
	private Vars Vars = new Vars();
	
	public int itemsArray[] = {0,0};//contains the states of items needing to be purchased.
	public int itemDID[] = {1937,1511};//contains the ids of the items needing to be purchased.
	public int itemDPrice[] = {1000,1000};//contains specific prices to use upon purchasing specific items.
	public int itemDAmount[] = {1,1};
	public String itemDString[] = {"Jug of water", "Logs"};//contains the names of the items needing to be purchased.
	
	@Override
	public void execute() {
		Method.resetTeleporting();
		
		if(DeltaQuester.checkedBank)
			Method.determineBank(bankItems);
		
			if(!DeltaQuester.checkedBank && (ctx.settings.get(2370) & 0x7F) != 80){
			Method.checkBank();
		}else
	    if(Vars.useBank && (ctx.settings.get(2370) & 0x7F) != 80){
			Method.useBank(bankItems, bankItemAmount);
		}else if (DeltaQuester.GEFeature && (ctx.settings.get(2370) & 0x7F) != 80) {
			Method.useGE(itemDString, itemDID, itemDPrice, itemDAmount);
		}else
		if((ctx.settings.get(2370) & 0x7F) == 80){
			Method.state("The Monk's Friend quest has been completed");
			Method.sleep(2000);
			DeltaQuester.e = true;
		}else
		if((ctx.settings.get(2370) & 0x7F) == 70){
			cs0();//Return to brother Omad
		}else
		if((ctx.settings.get(2370) & 0x3F) == 60){
			cs2();//Give Brother Omad logs.
		}else
		if((ctx.settings.get(2370) & 0x3F) == 50){
			cs2();//Give Brother Omad a jug of water
		}else
		if((ctx.settings.get(2370) & 0x3F) == 40){
			cs2();//continue speaking to brother cedric
		}else
		if((ctx.settings.get(2370) & 0x1F) == 30){
			cs2();//Speak to Brother Cedric 
		}else
		if((ctx.settings.get(2370) & 0x1F) == 20){
			cs0();//Speak to brother Omad to find out if he needs anything else(he does)
		}else
		if((ctx.settings.get(2370) & 0xF) == 10){
			cs1();//Gather the blanket from the thieves and bring it to brother Omad
		}else cs0();//Start the quest by speaking to brother Omad
		
	}
	

	private void cs2() {//Speak to Brother Cedric
		Player local = ctx.players.local();
		final String opt[] = {"Yes, I'd be happy"};
		
		if(new Tile(2617,3248,0).distanceTo(local.getLocation())<8){
			Method.skipPics();
			if(!Method.findOption(opt))
				if(!Method.isChatting("Brother Cedric")){
					Vars.DYNAMICV=false;
					Method.speakTo(280, "Brother Cedric");
				}
		}else if(Vars.DYNAMICV2){
			if(new Tile(2606,3211,0).distanceTo(local.getLocation())<20){
			if(Method.objIsByTile(new Tile(2606, 3218, 0), 1530, 3)){//dor in monastry is in way
				if(new Tile(2606,3219,0).distanceTo(local.getLocation())<7){//door in monastry
					Method.interactO(1530, "Open", "Door");
				}else Method.findPath(new Tile(2606,3219,0), "Walking to door");
			}else Method.walking(pathToDrunkMan, "Walking to Cedric", false);
			}else Method.walking(pathToDrunkMan, "Walking to Cedric", false);
		}else cs0();
		
	}


	private void cs1() {//Gather the blanket and return it to the monk(Omad)
		Player local = ctx.players.local();
		
		if(Vars.hasBlanket){
			cs0();//return to brother omad
		}else
		if(!Method.teleporting && Method.inventoryContains(90)){//contains blanket
			Vars.hasBlanket = true;
		}else
		if(Method.objIsNotNull(31130)){//if inside a cave
			if(new Tile(2569,9604,0).distanceTo(local.getLocation())<5){//by the blanket you need
				if(Method.gItemIsNotNull(90))
				ctx.mouse.move(Method.getGroundItem(90).getLocation().getMatrix(ctx).getPoint(Random.nextDouble() * 0.6D - 0.0D,+0.60D,-400));
				ctx.mouse.click(true);
			}else
			if(!Method.objIsByTile(new Tile(2565,9613,0), 31808, 3)){//door in way
				Method.findPath(new Tile(2569,9604,0), "Walking to the blanket");
			}else if(new Tile(2565,9613,0).distanceTo(local.getLocation())<7){//door in way
				Method.interactO(31808, "Open", "Door");
			}else Method.findPath(new Tile(2565,9613,0), "Walking to door");
		
		}else//get the player into the cave
		if(new Tile(2561,3222,0).distanceTo(local.getLocation())<3){//ring of stones outside the cave
			Method.interactO(42, "Climb-down", "Ladder");
		}else if(Vars.DYNAMICV2){//if you've been at the monastry(path to stone circle starts there)
			if(new Tile(2606,3211,0).distanceTo(local.getLocation())<20){//monastry area
				if(Method.objIsByTile(new Tile(2606, 3218, 0), 1530, 3)){//dor in monastry is in way
					if(new Tile(2606,3219,0).distanceTo(local.getLocation())<7){//door in monastry
						Method.interactO(1530, "Open", "Door");
					}else Method.findPath(new Tile(2606,3219,0), "Walking to door");
				}else Method.walking(pathToStoneCircle, "Walking to the stone circle", false);
			}else Method.walking(pathToStoneCircle, "Walking to the stone circle", false);
		
		}else if(new Tile(2606,3211,0).distanceTo(local.getLocation())<20){//monastry area
			Vars.DYNAMICV2 = true;
		}else cs0();//get the player to the monastry
		
		
	}


	private void cs0() {//Speaks to brother Omad in the monastry to start the quest
		Player local = ctx.players.local();
		final String opt[] = {"Where should I look?","Who's Brother","Is there anything else","Can I help at all?","Why can't you sleep"};
		
		if(new Tile(2606,3211,0).distanceTo(local.getLocation())<20){//Monastry area
			Vars.DYNAMICV2 = true;//used to get to the other father(drunk one)
			if (Method.npcIsNotNull(279)) {//monk in monastry
				Vars.DYNAMICV = false;//we need to teleport later
				if (Method.getNPC(279).getLocation().distanceTo(local.getLocation()) < 4) {
					Method.skipPics();
					if(!Method.startQuestOpen())
					if(!Method.findOption(opt))
						if(!Method.isChatting("monk Omad")){
							Method.speakTo(279, "monk Omad");
						}
					
				}else if (!Method.objIsByTile(new Tile(2606, 3218, 0), 1530, 3)) {//if no door in the way
					Method.findPath(Method.getNPC(279).getLocation(), "Walking to monk Omad");
				}else if(new Tile(2606,3219,0).distanceTo(local.getLocation())<5){
					Method.interactO(1530, "Open", "Door");
				}else Method.findPath(new Tile(2606,3219,0), "Walking to door");
			}
		}else if(Vars.DYNAMICV){
			Method.walking(pathToMonastry, "Walking to the monastry", false);
		}else if(TeleportLode.ARDOUGNE.getTile().distanceTo(local.getLocation())<14){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.ARDOUGNE.getTeleport(),TeleportType.ARDOUGNE.getName());
		
	}


	public boolean activate() {
		return DeltaQuester.scriptToStart==37;
	}

}
