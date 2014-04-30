package quests;


import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Player;

import quests.Vars.TeleportLode;
import quests.Vars.TeleportType;

public class CooksAssistant extends Node{

	
 public CooksAssistant(ClientContext ctx) {
		super(ctx);
	}
public final Tile[] pathToCook = new Tile[] { 
		 new Tile(3210,3233,0), new Tile(3217,3221,0), new Tile(3211,3214,0) };
 
 public final Tile[] pathToSpecialCow = new Tile[] { 
		 	new Tile(3210,3233,0), new Tile(3220,3227,0),new Tile(3234,3221,0),
		    new Tile(3232, 3222, 0), new Tile(3235, 3226, 0), new Tile(3240, 3226, 0), 
			new Tile(3245, 3226, 0), new Tile(3250, 3226, 0), new Tile(3255, 3228, 0), 
			new Tile(3257, 3233, 0), new Tile(3259, 3238, 0), new Tile(3259, 3243, 0), 
			new Tile(3256, 3247, 0), new Tile(3252, 3250, 0), new Tile(3249, 3254, 0), 
			new Tile(3248, 3259, 0), new Tile(3248, 3264, 0), new Tile(3253, 3266, 0), 
			new Tile(3258, 3268, 0), new Tile(3259, 3273, 0), new Tile(3261, 3279, 0) };
 
 public final Tile[] pathToSpecialEgg = new Tile[] {
		 new Tile(3218,3229,0), new Tile(3208,3238,0),
		 //up is path to old lode by weird minigame in lummy
		 
		 new Tile(3201,3235,0), new Tile(3200,3242,0), new Tile(3205,3248,0),
		 new Tile(3211,3253,0), new Tile(3216,3264,0),new Tile(3213,3273,0),
		 new Tile(3208,3278,0), new Tile(3207,3283,0)};
 
 public final  Tile[] pathToMill= new Tile[] {
		 new Tile(3218,3229,0), new Tile(3208,3238,0),
		 //up is path to old lode by weird minigame in lummy
		 
		 new Tile(3201,3235,0), new Tile(3200,3242,0), new Tile(3205,3248,0),
		 new Tile(3211,3253,0), new Tile(3216,3264,0),
			new Tile(3214, 3270, 0), 
			new Tile(3214, 3275, 0), new Tile(3210, 3279, 0), new Tile(3205, 3279, 0), 
			new Tile(3200, 3279, 0), new Tile(3195, 3279, 0), new Tile(3191, 3282, 0), 
			new Tile(3187, 3285, 0), new Tile(3182, 3286, 0), new Tile(3177, 3286, 0), 
			new Tile(3172, 3286, 0), new Tile(3168, 3289, 0), new Tile(3166, 3294, 0), 
			new Tile(3165, 3299, 0), new Tile(3166, 3304, 0), new Tile(3166, 3305, 0) };
 
 public final Area eggDoor = new Area(new Tile[] { 
		 new Tile(3184, 3282, 0), new Tile(3184, 3277, 0), new Tile(3191, 3277, 0), 
			new Tile(3191, 3282, 0) });
 
 public final Area cowDoor = new Area(new Tile[] {new Tile(3248, 3270, 0), new Tile(3248, 3262, 0), new Tile(3255, 3263, 0), 
			new Tile(3255, 3271, 0) });
 
 public final Area flourDoor = new Area(new Tile[] { 
		 new Tile(3163, 3305, 0), new Tile(3163, 3300, 0), new Tile(3168, 3300, 0), 
			new Tile(3168, 3305, 0) });
 
	public int requiredItems[] = { 0, 0, 0 };
	public boolean sFlour = false;
	public boolean opHopper = false;
	public int bankItems[] = { 15413, 15412, 15414, 1925,1947,1931 };
	public int bankItemAmount[] = {1,1,1,1,1,1};
	
	public int itemsArray[] = {0,0,0};//contains the states of items needing to be purchased.
	public int itemDID[] = {1931,1925,1947};//contains the ids of the items needing to be purchased.
	public int itemDPrice[] = {1000,1000,1000};//contains specific prices to use upon purchasing specific items.
	public int itemDAmount[] = {1,1,1};
	public String itemDString[] = {"Empty pot", "Bucket", "Wheat"};//contains the names of the items needing to be purchased.
	public Method Method = new Method(ctx);
	
	public Player player = ctx.players.local();
	public Vars Vars = new Vars();
	boolean q = true;//only runs once.
	public void execute() {
		Method.setGeneralCamera();//get the camera pitch for general use on quests
	  System.out.println("Then I'll let the darkness cover me..");
		Method.resetTeleporting();
		DeltaQuester.numSteps = 3;
		failsafe();
		if(DeltaQuester.checkedBank)
			Method.determineBank(bankItems);
		
		if(!DeltaQuester.checkedBank&& (ctx.varpbits.varpbit(2492)&0x3) !=2){
			System.out.println("HERE");
			Method.checkBank();
		}else
	    if(Vars.useBank && (ctx.varpbits.varpbit(2492)&0x3) !=2){
			Method.useBank(bankItems, bankItemAmount);
		}else
		if (DeltaQuester.GEFeature && (ctx.varpbits.varpbit(2492)&0x3) !=2) {
			Method.useGE(itemDString, itemDID, itemDPrice, itemDAmount);
		}else
		if((ctx.varpbits.varpbit(2492)&0x3) ==2){
			DeltaQuester.progress = 3;
			Method.state("The Cook's Assistant quest has been completed.");
			TaskListing.updateTaskRemove("Start quest by speaking to the cook","Speak to the cook and finish quest");
			TaskListing.removeTasks(TaskListing.taskRemove);
			DeltaQuester.e = true;
		}else
		if((ctx.varpbits.varpbit(2492)&0x1) ==1){
			DeltaQuester.progress = 2;
			cs1();//Gather/make the items and finish the quest
			TaskListing.updateTaskRemove("Start quest by speaking to the cook");
			TaskListing.removeTasks(TaskListing.taskRemove);
		}else
		if((ctx.varpbits.varpbit(2492)&0x1) ==0){
			DeltaQuester.progress = 1;
			cs0();//Speak to the cook and start the quest
		}
	}

	private void failsafe() {
		if(Method.objIsNotNull(78695)){//if player gets stuck in basement at lumbridge castle
			Method.interactO(29355, "Climb", "Ladder");
		}
		
	}

	private void cs1() {
		
		
		
		if (requiredItems[0] == 1) {// If has milk
			if (requiredItems[1] == 1) {// If has egg
				if (requiredItems[2] == 1) {//special flour
				cs0();//Speak to the cook again
			}else if(ctx.widgets.component(1188,1).valid() && ctx.widgets.component(1188,3).text().contains("Who are you")){
				Vars.DYNAMICV = false;
				requiredItems[2] = 1;
			}else if(Method.inventoryContains(15414)){//Flour
				Vars.DYNAMICV = false;
				requiredItems[2] = 1;
			}else if(new Tile(3166,3304,0).distanceTo(player.tile())<10 && ctx.game.floor()==0 || sFlour){
				if((ctx.varpbits.varpbit(3193)&0x1) ==1){//Whether or not there is flour already made
					opHopper = true;
				}
				if(!Method.objIsByTile(new Tile(3167,3302,0), 45966,5)){//mill door
					doMillStuff();
				}else Method.interactO(45966, "Open", "Door");//open the mill door
				
			}else if(Vars.DYNAMICV){
				Method.walking(pathToMill, "Walking to the mill", false);
			}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(player.tile())<10){
				Vars.DYNAMICV = true;
			}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
			
			}else if(Method.inventoryContains(15412) || Method.playerText("You've already given one of these eggs")){//Egg 45208
			    Vars.DYNAMICV = false;
				requiredItems[1] = 1;
			}else if(new Tile(3206,3283, 0).distanceTo(player.tile())<7){

				if(!Method.objIsByTile(new Tile(3206,3283,0),45208, 4)){//pen door
					Method.interactG(15412, "Take", "Special Egg");
				}else Method.interactO(45208, "Open","Door");
			
			}else if(Vars.DYNAMICV){
				Method.walking(pathToSpecialEgg, "Walking to the chicken pen", false);
			}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(player.tile())<10){
				Vars.DYNAMICV = true;
			}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
		}else if(Method.inventoryContains(15413)){//Milk
			requiredItems[0] = 1;
		}else if(ctx.widgets.component(1186,1).valid() && ctx.widgets.component(1186,1).text().contains("You've already")){
			requiredItems[0] = 1;
		}else
		if(new Tile(3261, 3279, 0).distanceTo(player.tile())<8){
			Vars.DYNAMICV = false;
			if(!player.inMotion())
			Method.interactO(47721, "Milk", "Prized dairy cow");
		}else if(Vars.DYNAMICV){
			if(new Tile(3254,3267,0).distanceTo(player.tile())<11 &&Method.objIsByTile(new Tile(3253,3267,0), 45212, 5)){
					Method.interactO(45212, "Open", "Door");
				
			}else Method.walking(pathToSpecialCow, "Walking to prized cow", false);
		}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(player.tile())<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
		
	}
	private void doMillStuff(){
		final String opt[] = {"I'm looking for"};
		if(opHopper){//Is true if the wheat was passed down the shout; after pulling the controls.
			if(ctx.game.floor()==2){
				Method.interactO(36797, "Climb-down", "Ladder");
			}else
			if(ctx.game.floor()==1){
				Method.interactO(36796, "Climb-down", "Ladder");
			}else
			if(ctx.game.floor()==0){
				Method.interactO(36880, "Take", "Flour bin");
			}
		}else//Below should put wheat in shout and use controls
		if(sFlour){//Checks if we are now making 'special flour'
			if(ctx.game.floor()==2){
				if(Method.playerText("You operate the")){
					opHopper = true;//becomes true after operating the controls (pushing the wheat down)
				}else if(Method.playerText("There is already") || Method.playerText("You put the wheat")){
					Method.interactO(2718, "Operate", "Controls");//if there is already wheat in hopper
				}else
				if(!Method.teleporting &&Method.inventoryContains(1947)){//wheat
					Method.useItemOn(1947, 70034, "Hopper");//if no wheat in hopper; put some in.
				}
			}else//below climbs up to the point
			if(ctx.game.floor()==1){
				Method.interactO(36796, "Climb-up", "Ladder");
			}else
			Method.interactO(36795, "Climb", "Ladder");
		}else if (Method.npcSays("Really? How marvellous!")){//Below tries to speak to miller about making special flour
			sFlour = true;
		}else 
			if(!Method.findOption(opt)){
			if(ctx.widgets.component(1184,1).valid() || ctx.widgets.component(1191,1).valid()){//NEEDED
				int widgets[] = {1184,1191};
				for(int wid: widgets){
					if(ctx.widgets.component(wid,1).valid())
						ctx.widgets.component(wid,14).click();
					Method.sleep(600);
					
				}
			}else Method.speakTo(3806, "Millie Miller");
			
			}
	}
	
	private void cs0() {
		final String opt[] = {"What's wrong"};
		if(new Tile(3206, 3211, 0).distanceTo(player.tile())<7){
			Method.skipPics();
			if(!Method.startQuestOpen())
			if(!Method.findOption(opt)){
				if(!Method.isChatting("Cook")){
					Method.speakTo(278, "Cook");
				}
			}
		}else if(Vars.DYNAMICV){
			Method.walking(pathToCook, "Walking to the cook",false);
		}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(player.tile())<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo( TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
		
	}
	public boolean activate() {
		return (DeltaQuester.scriptToStart == 4);
	}


}

