package dqquests;

import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.Player;

import dqbody.Area;
import dqbody.DeltaQuester;
import dqbody.Method;
import dqbody.Node;
import dqbody.Vars;
import dqbody.Vars.TeleportLode;
import dqbody.Vars.TeleportType;

public class PiratesTreasure extends Node{

	
	public PiratesTreasure(ClientContext ctx) {
		super(ctx);
	}

	public final static Tile[] pathToFrank = new Tile[] {
			new Tile(3018,3218,0),new Tile(3022,3218,0),new Tile(3027,3225,0),
			new Tile(3027,3234,0),new Tile(3035,3235,0),new Tile(3041,3244,0),
			new Tile(3047,3247,0),new Tile(3053,3247,0),//from port sarim
			
			new Tile(3218,3229,0), new Tile(3208,3238,0),
			new Tile(3200,3238,0),new Tile(3210,3254,0),new Tile(3216,3268,0),
			new Tile(3209,3274,0),new Tile(3197,3278,0),new Tile(3188,3285,0),
			new Tile(3177,3285,0),new Tile(3167,3288,0),new Tile(3154,3291,0),
			new Tile(3143,3293,0),new Tile(3131,3295,0),new Tile(3118,3296,0),
			new Tile(3109,3297,0),new Tile(3102,3295,0),new Tile(3093,3292,0),
			new Tile(3086,3289,0),new Tile(3078,3283,0),new Tile(3072,3276,0),
			new Tile(3064,3269,0),new Tile(3062,3261,0),new Tile(3056,3252,0),
			new Tile(3052,3247,0)
	};
	
	public final Tile[] pathToHallow = new Tile[] { 
			new Tile(3218,3229,0), new Tile(3208,3238,0),
			new Tile(3232, 3221, 0), new Tile(3237, 3223, 0), new Tile(3242, 3225, 0), 
			new Tile(3247, 3225, 0), new Tile(3252, 3226, 0), new Tile(3256, 3229, 0), 
			new Tile(3258, 3234, 0), new Tile(3258, 3239, 0), new Tile(3258, 3244, 0), 
			new Tile(3254, 3247, 0), new Tile(3251, 3251, 0), new Tile(3249, 3256, 0), 
			new Tile(3249, 3261, 0), new Tile(3250, 3266, 0), new Tile(3247, 3270, 0), 
			new Tile(3243, 3273, 0), new Tile(3240, 3278, 0), new Tile(3238, 3283, 0), 
			new Tile(3237, 3288, 0), new Tile(3238, 3293, 0), new Tile(3238, 3298, 0), 
			new Tile(3237, 3303, 0), new Tile(3237, 3308, 0), new Tile(3237, 3313, 0), 
			new Tile(3239, 3318, 0), new Tile(3240, 3323, 0), new Tile(3240, 3328, 0), 
			new Tile(3240, 3333, 0), new Tile(3236, 3336, 0), new Tile(3231, 3336, 0), 
			new Tile(3226, 3337, 0), new Tile(3225, 3342, 0), new Tile(3225, 3347, 0), 
			new Tile(3225, 3352, 0), new Tile(3223, 3357, 0), new Tile(3219, 3360, 0), 
			new Tile(3216, 3365, 0), new Tile(3214, 3370, 0), new Tile(3213, 3375, 0), 
			new Tile(3212, 3377, 0), new Tile(3211, 3382, 0), new Tile(3211, 3387, 0), 
			new Tile(3211, 3392, 0), new Tile(3214, 3396, 0) };
	
	public final static Tile[] pathToPatch = new Tile[] {
		new Tile(2966,3390, 0), new Tile(2967,3380, 0),
		new Tile(2979,3378, 0), new Tile(2989,3379, 0),
		new Tile(2999,3384, 0)};
	
	final Area Karamja = new Area(new Tile[] { 
			new Tile(2897, 3159, 0), new Tile(2896, 3137, 0), new Tile(2967, 3133, 0), 
			new Tile(2967, 3164, 0) });
	
	final Area DoorByCrateOfWine = new Area(new Tile[] {new Tile(3012, 3210, 0), new Tile(3012, 3202, 0), new Tile(3007, 3202, 0), 
			new Tile(3007, 3211, 0) });
	
	final Area FoodDoor = new Area(new Tile[] { 
			new Tile(3014, 3207, 0), new Tile(3014, 3200, 0), new Tile(3019, 3201, 0), 
			new Tile(3019, 3209, 0) });
	
	final Area PortSarim = new Area(new Tile[] { 
			new Tile(3012, 3193, 0), new Tile(3012, 3223, 0), new Tile(3058, 3267, 0), 
			new Tile(3058, 3190, 0) });
	
	final Area BarBottomDoor = new Area(new Tile[] { 
			new Tile(3212, 3398, 0), new Tile(3212, 3392, 0), new Tile(3220, 3392, 0), 
			new Tile(3221, 3400, 0) });
	
	final Area BarUpDoor = new Area(new Tile[] { 
			new Tile(3226, 3393, 1), new Tile(3226,3399, 1), new Tile(3221,3398, 1), 
			new Tile(3221,3390, 1) });
	
	
	public int itemsArray[] = {0,0};//contains the states of items needing to be purchased.
	public int itemDID[] = {1005,952};//contains the ids of the items needing to be purchased.
	public int itemDPrice[] = {1500,500};//contains specific prices to use upon purchasing specific items.
	public int itemDAmount[] = {1,1};
	public String itemDString[] = {"White apron","Spade"};//contains the names of the items needing to be purchased.
	public boolean init = false;
	
	
	private Vars Vars = new Vars();
	private Method Method = new Method(ctx);
	
	public int bankItems[] = {952,1005,433,431,1963,1963,1963,1963,1963,1963,1963,1963,1963,1963,1963,1963,1963};
	public int bankItemAmount[] = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
	boolean q = true;
	public void execute() {
		Method.setGeneralCamera();//get the camera pitch for general use on quests
	
		Method.resetTeleporting();
		Method.foodSupport();
		
	
		if(ctx.camera.pitch()<50){
			ctx.camera.pitch(90);
		}
		DeltaQuester.numSteps = 5;
		if(DeltaQuester.checkedBank)
			Method.determineBank(bankItems);
		
			if(!DeltaQuester.checkedBank && (ctx.varpbits.varpbit(2227) & 0x7) !=4){
			Method.checkBank();
		}else
	    if(Vars.useBank && (ctx.varpbits.varpbit(2227) & 0x7) !=4){
	    	Method.useBank(bankItems, bankItemAmount);
		}else
		if (DeltaQuester.GEFeature && (ctx.varpbits.varpbit(2227) & 0x7) !=4) {
			Method.useGE(itemDString, itemDID, itemDPrice, itemDAmount);
		}else
		if(!Method.interference())
		if(init){
			if(!Method.FaladorLodeIsActive()){
				Method.state("Falador lodestone must be active, skipping quest.");
				Method.sleep(2000);
				DeltaQuester.e  =true;
			}else init = true;
		}else
			if((ctx.varpbits.varpbit(2227) & 0x7) ==4){
				DeltaQuester.progress = 5;
				Method.state("The Pirates Treasure quest has been completed.");
				
				Method.sleep(2000);
				DeltaQuester.e = true;
			}else if(!Vars.r){
				Method.state("Checking apron");
					if(Method.EquipmentContains(1005)){
						Vars.r = true;
					}else if(Method.inventoryContains(1005)){
						Method.interactInventory(1005, "Wear", "Apron");
					}
			}else{
			if((ctx.varpbits.varpbit(2227) & 0x3) ==3){
				DeltaQuester.progress = 4;
				cs5();//Find the treasure!!
				
			}else
			if((ctx.varpbits.varpbit(2227) & 0x3) ==2){
				DeltaQuester.progress = 3;
				cs4();//Find the chest!!
			}else
			if((ctx.varpbits.varpbit(2227) & 0x1) ==1){
				DeltaQuester.progress = 2;
				try {
					cs1();//Heads to karamja and finishes the job(gets the wine stored)
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else
			if((ctx.varpbits.varpbit(2227) & 0x1) ==0){
				DeltaQuester.progress = 1;
				cs0();//Speak to the pirate and begin the quest
			}
			}
		if(ctx.widgets.widget(1186).component(3).visible()){
			Method.state("Closing crate dialogue");
			ctx.widgets.widget(1186).component(3).click();
		}
	}
	
	private void cs5() {
		Player local = ctx.players.local();
		if(ctx.widgets.component(468,15).visible()){//After readin ght emap
			ctx.widgets.component(468,15).click();
		}else
		if(Method.npcIsNotNull(3914)){
			if(Method.getInteractingNPC()==null){
				Method.npcInteract(3914, "Attack");
			}else if(ctx.players.local().inCombat()){
				Method.fightNPC(3914);//farmer
			}
		}else{
		
		if(new Tile(2999,3383,0).distanceTo(local.tile())<4){
			if(new Tile(2999,3383,0).distanceTo(local.tile())<1){
				if(!local.inMotion())
				Method.interactInventory(952, "Dig","Spade");
			}else new Tile(2999,3383,0).matrix(ctx).click(true);
		}else if(Vars.DYNAMICV){
			Method.walking(pathToPatch, "Walking to Falador garden.",false);
		}else if(TeleportLode.FALADOR.getTile().distanceTo(local.tile())<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.FALADOR.getTeleport(),TeleportType.FALADOR.getName());//Falador tele
		
		}
	}

	private void cs4() {
		//SceneObject door1 = SceneEntities.getNearest(24376);
		Player local = ctx.players.local();
		
		if(Method.inventoryContains(433)){
			Vars.DYNAMICV = false;
			Method.interactInventory(433, "Read","Map");
		}else{
				if(Method.objIsNotNull(24376) && !BarUpDoor.contains(Method.getObject(24376).tile())&&ctx.game.floor()==1 || 
						!Method.objIsNotNull(24376) && ctx.game.floor()==1){
					if(new Tile(3221,3395,1).distanceTo(local.tile())<5){
					Method.interactO(2079, "Open","Chest");
					}else Method.clickOnMap(new Tile(3221,3395,1));
				}else
			if(ctx.game.floor()==1){
			Method.interactO(24376, "Open","Door");
		}else {
			if(new Tile(3226,3394,0).distanceTo(local.tile())<30){
			if(Method.objIsNotNull(24376) && !Method.objIsByTile(new Tile(3215,3395,0), 24376, 5) ||
					!Method.objIsNotNull(24376) && !Method.objIsByTile(new Tile(3215,3395,0), 24376, 5)){
				
				if(new Tile(3226,3394,0).distanceTo(local.tile())<5){
					Method.interactO(24356, "Climb","Stairs");
					}else Method.clickOnMap(new Tile(3226,3394,0));
				
			}else if (new Tile(3215, 3395, 0).distanceTo(local.tile()) < 5) {
					Method.interactO(24376, "Open", "Door");
			}else Method.clickOnMap(new Tile(3215, 3395, 0));
			}else if (Vars.DYNAMICV) {

					Method.walking(pathToHallow,"Walking to the Blue Moon Inn", false);
				} else if (TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.tile()) < 10 || 
						TeleportLode.VARROCK.getTile().distanceTo(local.tile()) < 10) {
					Vars.DYNAMICV = true;
				} else if (Method.VarrokLodeIsActive()) {
					Method.teleportTo(TeleportType.VARROCK.getTeleport(),TeleportType.VARROCK.getName());
				} else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
			}
		}
		
	}
	private void cs3() {
		//SceneObject door = SceneEntities.getNearest(40108);
		Player local = ctx.players.local();
		if (PortSarim.contains(local.tile())) {
			for(GameObject door : ctx.objects.select().id(40108).nearest().first()){
				if(FoodDoor.contains(door.tile()) && door.tile().distanceTo(local.tile())<8){
					Method.interactO(40108, "Open","Door");
					break;
				}else break;
			}
			if (new Tile(3052, 3247, 0).distanceTo(local.tile()) < 5) {
				if(!Method.isChatting("Person")){
					Vars.DYNAMICV = false;
					Method.speakTo(375,"Person");
				}
			} else Method.clickOnMap(new Tile(3052, 3247, 0));
		} else {
			if (DoorByCrateOfWine.contains(local.tile())) {
				Method.interactO(2069, "Open","Door");
			}
		}
	
	}
	private void cs2() {
		final String opt[] = {"Well, can","Ok.","Search","journey"};
		Player local = ctx.players.local();
		
		while (ctx.game.floor()==1) {
			Method.interactO(2084, "Cross","Plank");
		}
		if (Method.inventoryContains(431)) {
			cs3();
		} else {
			if (DoorByCrateOfWine.contains(local.tile())) {
				Method.interactO(2071, "Search","Object");
			} else {
				if (ctx.game.floor()==0 && new Tile(3017, 3207, 0).distanceTo(local.tile()) < 30) {
					if (new Tile(3017, 3207, 0).distanceTo(local.tile()) < 7) {
						
						for(GameObject door : ctx.objects.select().id(40108).nearest().first()){
							if(!FoodDoor.contains(door.tile()) || !Method.objIsNotNull(40108)){
								if(!Method.findOption(opt))
									if(!Method.isChatting("Person")){
										Method.interactO(2069, "Open","Door");
									}
							}else Method.interactO(40108, "Open","Door");
						}
						
					} else Method.clickOnMap(new Tile(3017, 3207, 0));//Walks the player to the food store
				} else {
						if (new Tile(2955, 3146, 0).distanceTo(local.tile()) < 5) {
							if(!Method.findOption(opt))
								if(!Method.isChatting("Person")){
									Vars.DYNAMICV = false;
									Method.speakTo(380,"Person");
								}
						} else Method.clickOnMap(new Tile(2955, 3146, 0));
					
				}
			}
		}
	}
	
	//Heads to Karamja, takes the job, stores the rum
	private void cs1() throws Exception {
		final String opt[] = {"Could you","Yes"};
		Player local = ctx.players.local();
		int[] bananaIds = {2073,2074,2075,2076};
		while (ctx.game.floor()==1 && !Vars.finishedJ){//To get off the boat
			Method.interactO(2082, "Cross", "Gangplank");
		}
		
		if(Method.playerText("There is already some rum in")||
				Method.playerText("The crate is already")){//if you've already stored the wine
			if(Method.inventoryContains(431)){
				Method.interactInventory(431, "Drop", "Wine");
			}else{
			Vars.finishedJ = true;
			Vars.DYNAMICV = false;
			}
		}
		if(Method.playerText("There's already some")){
			if(Method.inventoryContains(431)){
				Method.interactInventory(431, "Drop", "Wine");
				Vars.hasB = false;
			}
		}
		if(Vars.finishedJ){
			cs2();
		}else
		if(Vars.startJob){//After you've started Luthas's job
			if(Vars.boughtWine){//and you have had the wine at least once
			  if(Vars.hasB){//and has enough bananas at some point
				  if(Vars.doneTasks){//If finished storing the rum and bananas, speak to Luthas for the last time
					  
					  if(new Tile(2937,3152,0).distanceTo(local.tile())<8){//by Luthas
							if(ctx.widgets.component(1184,11).visible()){
								while(Method.npcSays("Well done, here's your")||
										Method.npcSays("Hello I'm Luthas")){
									Method.state("Finishing job");
									Vars.finishedJ = true;
									Method.pressContinue();
								}
							}else if(!Method.isChatting("Luthas"))
								Method.speakTo(379, "Luthas");
								
						}else if (new Tile(2937,3152,0).distanceTo(local.tile())<20){//if on the island
							Method.clickOnMap(new Tile(2937,3152,0));//Luthas's location
						}
					  
				  }else
				  if(new Tile(2942,3151,0).distanceTo(local.tile())<8){//crate location
					  if(Method.inventoryContains(431)){//if still contains the wine
						  Method.useItemOn(431, 2072, "Use");//use the wine on the crate
					  }else if(Method.inventoryGetCount(1963)>=13){
						  Method.interactO(2072, "Fill", "Crate");//fill it with bananas
					  }else{
						Vars.doneTasks = true;
						Vars.DYNAMICV = false;
						}
					  
				  }else Method.clickOnMap(new Tile(2942,3151,0));//crate location
			}else
			if(Method.inventoryGetCount(1963)>13){//contains 8 or more bananas
				Vars.hasB = true;
			}else if(new Tile(2917,3160,0).distanceTo(local.tile())<14){//banana area
				for(int i: bananaIds){
					if(!local.inMotion() && local.animation()==-1)
				Method.interactO(i, "Pick", "Banana tree");
				}
			}else Method.clickOnMap(new Tile(2917,3160,0));//Banana area
		   }else if(Method.inventoryContains(431)){//If inventory contains the wine
			   Vars.boughtWine = true;
		   }else if(new Tile(2926,3146,0).distanceTo(local.tile())<8){//Store location
			   //Buys the wine
			   if(ctx.widgets.component(1265,25).valid()){
					ctx.widgets.component(1265,25).component(1).interact("Buy 1");
					Method.sleep(2000);
				}else Method.npcInteract(568, "Trade");
			   
		   }else Method.clickOnMap(new Tile(2926,3146,0));//store location
		}else if(new Tile(2937,3152,0).distanceTo(local.tile())<8){//by Luthas
			if(Method.npcSays("Have you completed your task")){
				Vars.startJob = true;
			}else
			if(!Method.findOption(opt))//Speak to Luthas and start the job
				Method.skipPics();
				if(!Method.isChatting("Luthas")){
					Method.speakTo(379, "Luthas");
				}
		}else if (new Tile(2937,3152,0).distanceTo(local.tile())<20){//if on the island
			Method.clickOnMap(new Tile(2937,3152,0));//Luthas's location
		}else
		if(new Tile(3028,3217,0).distanceTo(local.tile())<8){//By the boat
			
			if(!Method.findOption(opt))
				if(!Method.isChatting("Seaman Thresnor")){
					Method.speakTo(378, "Seaman Thresnor");
				}
			
		}else if(Vars.DYNAMICV2){
			Method.clickOnMap(new Tile(3028,3217,0));//by the boat
		}else if(Vars.DYNAMICV){
			if (new Tile(3028,3217,0).distanceTo(local.tile())<20){
			Method.clickOnMap(new Tile(3028,3217,0));//by the boat
			}else Method.walking(pathToFrank, "Walking to the boat", false);
		}else if(TeleportLode.PORTSARIM.getTile().distanceTo(local.tile())<10||
				TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.tile())<10){
			Vars.DYNAMICV = true;
		}else if(Method.isPortSarimLodeAct()){
			Method.teleportTo(TeleportType.PORTSARIM.getTeleport(),TeleportType.PORTSARIM.getName());
		}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
	}
	private void cs0() {//Speak to Redbeard Frank and starts the quest
		
		Player local = ctx.players.local();
		final String opt[] = {"Ok, I","I'm in search"}; 
		if(new Tile(3052,3247,0).distanceTo(local.tile())<28){
			if(new Tile(3052,3247,0).distanceTo(local.tile())<7){
				if(!Method.findOption(opt))
					if(!Method.isChatting("Pirate")){
						Vars.DYNAMICV = false;
						Vars.DYNAMICV2 = true;
						Method.speakTo(375,"Pirate");
					}
			}else Method.clickOnMap(new Tile(3052,3247,0));
		}else if(Vars.DYNAMICV){
			Method.walking(pathToFrank, "Walking to RedBeard Frank.",false);
		}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.tile())<10 || 
				TeleportLode.PORTSARIM.getTile().distanceTo(local.tile())<10){
			Vars.DYNAMICV = true;
		}else if(Method.isPortSarimLodeAct()){
			Method.teleportTo(TeleportType.PORTSARIM.getTeleport(),TeleportType.PORTSARIM.getName());
		}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
		
	}
	
	public boolean activate() {
		return DeltaQuester.scriptToStart==19;
	}

}
