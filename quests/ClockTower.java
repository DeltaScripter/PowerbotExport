/*package quests;

import org.powerbot.script.methods.ClientContext;
import org.powerbot.script.wrappers.Player;
import org.powerbot.script.Tile;

import quests.Vars.TeleportLode;
import quests.Vars.TeleportType;

public class ClockTower extends Node{

	public ClockTower(ClientContext ctx) {
		super(ctx);
	}
	
	final Tile[] pathToClockTower = new Tile[] {
			 new Tile(2634, 3343, 0), new Tile(2635, 3338, 0), 
			new Tile(2638, 3334, 0), new Tile(2642, 3331, 0), new Tile(2646, 3328, 0), 
			new Tile(2647, 3323, 0), new Tile(2650, 3319, 0), new Tile(2653, 3315, 0), 
			new Tile(2653, 3310, 0), new Tile(2650, 3306, 0), new Tile(2645, 3307, 0), 
			new Tile(2640, 3306, 0), new Tile(2638, 3301, 0), new Tile(2634, 3298, 0), 
			new Tile(2629, 3297, 0), new Tile(2624, 3297, 0), new Tile(2619, 3296, 0), 
			new Tile(2614, 3296, 0), new Tile(2609, 3297, 0), new Tile(2606, 3293, 0), 
			new Tile(2606, 3288, 0), new Tile(2607, 3283, 0), new Tile(2606, 3278, 0), 
			new Tile(2603, 3274, 0), new Tile(2599, 3271, 0), new Tile(2597, 3266, 0), 
			new Tile(2598, 3261, 0), new Tile(2596, 3256, 0), new Tile(2592, 3253, 0), 
			new Tile(2588, 3250, 0), new Tile(2583, 3249, 0), new Tile(2578, 3249, 0), 
			new Tile(2574, 3249, 0) };
	
	final Tile[] pathToLadder = new Tile[] {//the other cave entrance by the zoo
			new Tile(2633, 3348, 0), new Tile(2634, 3343, 0), new Tile(2635, 3338, 0), 
			new Tile(2637, 3335, 0), new Tile(2640, 3331, 0), new Tile(2645, 3329, 0), 
			new Tile(2649, 3325, 0), new Tile(2650, 3320, 0), new Tile(2653, 3316, 0), 
			new Tile(2653, 3311, 0), new Tile(2650, 3307, 0), new Tile(2645, 3306, 0), 
			new Tile(2640, 3305, 0), new Tile(2637, 3301, 0), new Tile(2632, 3299, 0), 
			new Tile(2627, 3297, 0), new Tile(2622, 3297, 0), new Tile(2617, 3297, 0), 
			new Tile(2612, 3297, 0), new Tile(2607, 3295, 0), new Tile(2606, 3290, 0), 
			new Tile(2607, 3285, 0), new Tile(2608, 3280, 0), new Tile(2606, 3275, 0), 
			new Tile(2602, 3272, 0), new Tile(2598, 3269, 0), new Tile(2598, 3264, 0), 
			new Tile(2598, 3259, 0), new Tile(2600, 3254, 0), new Tile(2605, 3252, 0), 
			new Tile(2610, 3252, 0), new Tile(2615, 3251, 0), new Tile(2620, 3253, 0), 
			new Tile(2621, 3258, 0), new Tile(2621, 3260, 0) };
	
	final Tile[] pathToWall = new Tile[] {new Tile(2616,6950,0), new Tile(2616,9635,0),
			new Tile(2608,9626,0), new Tile(2607,9614,0), new Tile(2596,9603,0),
			new Tile(2583,9601,0), new Tile(2580,9615,0), new Tile(2576,9631,0)};
	
 private Method Method = new Method(ctx);
 private Vars Vars = new Vars();
 
 public int itemsArray[] = {0};//contains the states of items needing to be purchased.
 public int itemDID[] = {1929};//contains the ids of the items needing to be purchased.
 public int itemDAmount[] = {1};
 public int itemDPrice[] = {1000};//contains specific prices to use upon purchasing specific items.
 public String itemDString[] = {"Bucket of water"};//contains the names of the items needing to be purchased.

 
 public int bankItems[] = {1929,20,21,22,23,24};
 public int bankItemAmount[] ={1,1,1,1,1,1};

	
	public void execute() {
		Method.setGeneralCamera();//get the camera pitch for general use on quests
		Method.resetTeleporting();
		Method.foodSupport();
		DeltaQuester.numSteps = 6;
		
		
		if(DeltaQuester.checkedBank && (ctx.varpbits.varpbit(2197)&0xF)!=8)
			Method.determineBank(bankItems);
		
			if(DeltaQuester.checkedBank&& (ctx.varpbits.varpbit(2197)&0xF)!=8){
			Method.checkBank();
		}else
	    if(Vars.useBank && (ctx.varpbits.varpbit(2197)&0xF)!=8){
			Method.useBank(bankItems, bankItemAmount);
		}else if (DeltaQuester.GEFeature&&(ctx.varpbits.varpbit(2197)&0xF)!=8) {
			Method.useGE(itemDString, itemDID, itemDPrice, itemDAmount);
		}else
		if((ctx.varpbits.varpbit(2197)&0xF)==8){
			DeltaQuester.progress = 6;
			DeltaQuester.state ="The Clock Tower quest has been completed";
			ctx.environment.sleep(2000);
			DeltaQuester.e  = true;
		}else
		if((ctx.varpbits.varpbit(2197)&0x7)==5){
			DeltaQuester.progress = 5;
			cs0();//Speak to the man to finish the quest
		}else
		if((ctx.varpbits.varpbit(2197)&0x7)==4){
			DeltaQuester.progress = 4;
			cs4();//Gather and place the white cog
		}else
		if((ctx.varpbits.varpbit(2197)&0x3)==3){
			DeltaQuester.progress = 3;
			cs3();//Gather and place the blue cog
		}else
		if((ctx.varpbits.varpbit(2197)&0x3)==2){
			DeltaQuester.progress = 2;
			cs2();//Gather and place the red cog
		}else
		if((ctx.varpbits.varpbit(2197)&0x1)==1){
			DeltaQuester.progress = 1;
			cs1();//Gather and place the black cog
		}else cs0();//Start the quest
		
	}
	


	private void cs4() {//Gather and place the white cog
		Player local = ctx.players.local();
		
		if(!Method.teleporting&& Method.inventoryContains(20)){//inv contains white cog
			if(ctx.game.floor()==2){
				Method.useItemOn(20, 31, "Use");//use white cog on white spindle
			}else
			if(ctx.game.floor()==1){
				Method.interactO(1739, "Climb-up", "Stairs");//go to third floor
			}else
			if(new Tile(2567,3242,0).distanceTo(local.getLocation())<4){//main floor
				Method.interactO(1738, "Climb", "Stairs");//get to upstairs
			}else
			if(Method.objIsNotNull(31130)){
				Method.interactO(32015, "Climb", "Ladder");
			}else cs1();//Get into position
			
		}else
		if(Method.objIsNotNull(31130)){
			if(new Tile(2577,9656,0).distanceTo(local.getLocation())<2){//if by the white cog area
				Method.interactG(20, "Take", "White cog");
			}else//get into the white cog area
			if(Vars.ratPoison){//rat poison
				if(Method.objIsByTile(new Tile(2591,9661,0),35, 3)){//if lever is pulled
					Method.skipPics();//skips dialogue
					if(!Method.inventoryContains(24)){//inv does not contain rat poison
						if(new Tile(2580,9656,0).distanceTo(local.getLocation())<4){//by last door(rat)
							Method.interactO(39, "Go-through","Weak door");//enter the rat door
						}else Method.clickOnMap(new Tile(2580,9656,0));//by last door
					}else if(new Tile(2587,9655,0).distanceTo(local.getLocation())<6){
							Method.useItemOn(24, 40, "Use");//use rat poison on trough
						}else if(Method.objIsByTile(new Tile(2576,9651,0),31808,2)){//first door by white tile
							if(new Tile(2576,9651,0).distanceTo(local.getLocation())<4){
								Method.interactO(31808, "Open", "Large Door");
							}else Method.findPath(new Tile(2576,9651,0), "Walking to the door by red tile");
						}else Method.findPath(new Tile(2587,9655,0),"Walking to the rat trough");
					
					
				}else if(new Tile(2591,9661,0).distanceTo(local.getLocation())<4){
					Method.state("Attemptin to pull lever");
					Method.interactO(33, "Pull", "Lever");
				}else Method.findPath(new Tile(2591,9661,0), "Walking to the lever");
			}else if(!Method.teleporting && Method.inventoryContains(24)){
				Vars.ratPoison = true;
			}else
			if(new Tile(2562,9662,0).distanceTo(local.getLocation())<7){//area by rat poison
				Method.interactG(24, "Take", "Rat poison");
			}else
			if(Method.objIsByTile(new Tile(2576,9651,0),31808,2)){//first door by white tile
				if(new Tile(2576,9651,0).distanceTo(local.getLocation())<4){
				Method.interactO(31808, "Open", "Large Door");
			}else Method.findPath(new Tile(2576,9651,0), "Walking to the door by red tile");
		}else Method.findPath(new Tile(2562,9662,0), "Walking to the rat poison");
			
		}else if(ctx.game.floor()==1){
			Method.interactO(1739, "Climb-down", "Stairs");//go downstairs if on the second floor
		}else cs1();
		
	}


	private void cs3() {//Gather and place the blue cog
		Player local = ctx.players.local();
	
		if(!Method.teleporting && Method.inventoryContains(22)){//if inv contains blue cog
			if(ctx.game.floor()==1){//upstairs
				Method.useItemOn(22, 32, "Use");//use the blue cog on the spindle
			}else
			if(new Tile(2567,3242,0).distanceTo(local.getLocation())<4){//main floor
				//ctx.camera.setYaw(50);
				Method.interactO(1738, "Climb", "Stairs");//get to upstairs
			}else
			if(Method.objIsNotNull(31130)){
				Method.interactO(32015, "Climb", "Ladder");
			}else cs1();//Get into position
		}else//Gather the blue cog
		 if(Method.objIsNotNull(31130)){//if in cave
			 if(new Tile(2573,9631,0).distanceTo(local.getLocation())<3){
				 Method.interactG(22, "Take", "Blue cog");
			 }else
			 if(new Tile(2576,9631,0).distanceTo(local.getLocation())<4){//area outside the cell
				 Method.interactO(1586, "Push", "Wall");
			 }else if(new Tile(2622,9662,0).distanceTo(local.getLocation())>35){
				 Method.findPath(new Tile(2576,9631,0),"Walking to the secret wall");
			 }else Method.findPath(new Tile(2607,9614,0),"Walking closer");
			 
		 }else if(new Tile(2621,3259,0).distanceTo(local.getLocation())<7){//outside the other cave entrance
				Method.interactO(1754, "Climb", "Ladder");//enter the cave
			}else if(Vars.DYNAMICV){//get to the ladder by cave
				Method.walking(pathToLadder, "Walking to the other entrance", false);
			}else if(TeleportLode.ARDOUGNE.getTile().distanceTo(local.getLocation())<10){
				Vars.DYNAMICV = true;
			}else Method.teleportTo(TeleportType.ARDOUGNE.getTeleport(),TeleportType.ARDOUGNE.getName());
		
	}


	private void cs2() {//Gather and place the red cog
		Player local = ctx.players.local();
		
		if(!Method.teleporting && Method.inventoryContains(23)){//if contains the red cog
			if(new Tile(2567,3243,0).distanceTo(local.getLocation())<3){//red spindle area
				Vars.DYNAMICV = false;
				Method.useItemOn(23, 29, "Use");//use the red cog on the spindle
			}else if(Method.objIsNotNull(31127)){//if still in the cave
				if(new Tile(2566,9641,0).distanceTo(local.getLocation())<5){//area by exit
					Method.interactO(32015, "Climb", "Ladder");
				}else
				if(Method.objIsByTile(new Tile(2580,9647,0),31808,3)){//first door by red tile
					if(new Tile(2580,9647,0).distanceTo(local.getLocation())<4){
					Method.interactO(31808, "Open", "Large Door");
				}else Method.findPath(new Tile(2582,9648,0), "Walking to the door by red tile");
			}else Method.findPath(new Tile(2566,9641,0), "Walking to the exit");
				
			}else cs1();//Get into the red spindle area(ladder to the cave area)
			
		}else//Gather the red cog
		if(Method.objIsNotNull(31127)){//if you're in a cave
			if(new Tile(2583,9612,0).distanceTo(local.getLocation())<5){
				Method.interactG(23, "Take", "Red cog");
			}else
			if(Method.objIsByTile(new Tile(2580,9647,0),31808,3)){//first door by red tile
					if(new Tile(2580,9647,0).distanceTo(local.getLocation())<4){
					Method.interactO(31808, "Open", "Large Door");
				}else Method.findPath(new Tile(2580,9647,0), "Walking to door #1 by red tile");
			}else Method.findPath(new Tile(2583,9612,0), "Walking to the red cog");
			
		}else cs1();//Get the player into the cavern area
		
	}


	private void cs1() {//Gather and place the black cog
		Player local = ctx.players.local();
		//black cog ID: 21
		//Water bucket ID: 1929
		while(!Method.teleporting && Method.inventoryGetCount(1929)>1){
			Method.interactInventory(1929, "Drop", "Bucket of water");
		}
		if(!Method.teleporting && Method.inventoryContains(21)){//contains black cog
			//The exit location
			if(new Tile(2571,9641,0).getLocation().distanceTo(local.getLocation())<4){
				Method.useItemOn(21, 30, "Use");
			}else//once we have the cog we must head to the area by the entrance
			if(Method.objIsByTile(new Tile(2602,9638,0), 31808, 3)){//second door
				if(new Tile(2602,9638,0).distanceTo(local.getLocation())<4){
					Method.interactO(31808, "Open", "Large Door");
				}else Method.findPath(new Tile(2602,9638,0), "Walking to door #2");
			}else if(Method.objIsByTile(new Tile(2582,9651,0), 31808, 2)){
				if(new Tile(2582,9651,0).distanceTo(local.getLocation())<4){
					Method.interactO(31808, "Open", "Large Door");
				}else Method.findPath(new Tile(2582,9651,0), "Walking to door #1");
			}else Method.findPath(new Tile(2571,9641,0),"Walking to the exit");
		}else//Gather the black cog
		if(Method.objIsNotNull(31127)){//If inside the cave
			if(new Tile(2612,9639,0).distanceTo(local.getLocation())<7){//area by first cog
				if(Method.inventoryContains("Bucket of water")){//A bucket of water
					System.out.println("Trying to interact item");
				Method.useItemOnG(1929, 21, "Use");//use the water bucket on the cog
				}else System.out.println("Trying to interact directly");Method.interactG(21, "Take", "Black cog");
			}else
			if(!Method.objIsByTile(new Tile(2601,9638,0),31808,3) && //second door loc
					!Method.objIsByTile(new Tile(2582,9651,0), 31808, 3)){
				Method.findPath(new Tile(2612,9639,0), "Walking to the black cog");
			}else
			if(new Tile(2601,9638,0).distanceTo(local.getLocation())<6){//second door
				Method.interactO(31808, "Open", "Large Door");
			}else if(!Method.objIsByTile(new Tile(2582,9651,0), 31808, 3)){
				Method.findPath(new Tile(2601,9638,0), "Walking to door #2");
			}else
			if(new Tile(2579,9650,0).distanceTo(local.getLocation())<6){//tile area
				if(new Tile(2581,9651,0).distanceTo(local.getLocation())<2){//closer to door
					Method.interactO(31808, "Open", "Large Door");
				}else if(new Tile(2581,9651,0).getMatrix(ctx).isOnScreen())
					new Tile(2581,9651,0).getLocation().getMatrix(ctx).click();
				else ctx.camera.turnTo(new Tile(2581,9651,0));
			}else Method.findPath(new Tile(2578,9648,0),"Walking to the main area");
		}else//If not in cave enter the cave
		if(new Tile(2566,3243,0).distanceTo(local.getLocation())<4){//distance to ladder
			Method.interactO(1754, "Climb", "Ladder");
		}else if(new Tile(2570,3249,0).distanceTo(local.getLocation())<16){
			if (!Method.objIsByTile(new Tile(2573, 3250, 0), 1530, 3)) {//Checks for the doors outside			
				if (!Method.objIsByTile(new Tile(2567, 3246, 0), 1530, 3)) {
						Method.clickOnMap(new Tile(2566,3243,0));											
				}else cs0();//Open the second door
			}else cs0();//Open the first door
		}else cs0();//get into the clock tower
		
	}

	private void cs0() {//Heads to the clock tower and starts the quest
		Player local = ctx.players.local();
		final String[] opt = {"OK old monk"};
		
		if(ctx.game.floor()==2){
			//ctx.camera.setYaw(70);
			Method.interactO(1740, "Climb-down","Stairs");
		}else if(ctx.game.floor()==1){
			Method.interactO(1739, "Climb-down","Stairs");
		}else
		if(new Tile(2570,3249,0).distanceTo(local.getLocation())<16){
			if(!Method.objIsByTile(new Tile(2573,3250,0), 1530, 3)){//first door
				
				if(!Method.objIsByTile(new Tile(2567,3246,0), 1530, 3)){//second door
					if(Method.npcIsNotNull(223)){//223 is the monk
						if (Method.getNPC(223).getLocation().distanceTo(local.getLocation()) < 7) {
							if (!Method.findOption(opt))
								if (!Method.isChatting("Brother Kojo")) {
									Method.speakTo(223, "Brother Kojo");
								}
						}else Method.clickOnMap(Method.getNPC(223).getLocation());
					}
				}else if(new Tile(2567,3246,0).distanceTo(local.getLocation())<6){//Inside house
						Method.interactO(1530, "Open", "Door");
					}else Method.clickOnMap(new Tile(2569,3250,0));
				
			}else if(new Tile(2574,3250,0).distanceTo(local.getLocation())<6){//outside the house
				Method.interactO(1530, "Open", "Door");
			}else Method.clickOnMap(new Tile(2574,3250,0));
		}else if(Vars.DYNAMICV){
			if(new Tile(2572,3230,0).distanceTo(local.getLocation())<5){//for cs3
				Method.findPath(new Tile(2574,3250,0),"Walking to the door");
			}else
			Method.walking(pathToClockTower, "Walking to the clock tower", false);
		}else if(TeleportLode.ARDOUGNE.getTile().distanceTo(local.getLocation())<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.ARDOUGNE.getTeleport(),TeleportType.ARDOUGNE.getName());
		
	}

	public boolean activate() {
		return DeltaQuester.scriptToStart==36;
	}

}*/
