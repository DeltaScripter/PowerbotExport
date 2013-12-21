package quests;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Player;
import org.powerbot.script.wrappers.Tile;

import quests.Vars.TeleportLode;
import quests.Vars.TeleportType;

public class VampyreSlayer extends Node{


	public VampyreSlayer(MethodContext ctx) {
		super(ctx);
	}

	public final Tile[] pathToMorgan = new Tile[] { 
			new Tile(3200,3237,0), new Tile(3201,3246,0), new Tile(3211,3251,0),
			new Tile(3217,3259,0), new Tile(3214,3272,0), new Tile(3206,3276,0),
			new Tile(3198,3280,0), new Tile(3190,3283,0), new Tile(3181,3285,0),
			new Tile(3174,3287,0), new Tile(3164,3288,0), new Tile(3155,3289,0),
			new Tile(3146,3293,0), new Tile(3135,3294,0), new Tile(3126,3296,0),
			new Tile(3117,3298,0), new Tile(3104,3299,0),//To Draynor
			new Tile(3109,3291,0), new Tile(3107,3283,0), new Tile(3103,3273,0),
			new Tile(3098,3271,0) };
	
	public final Tile[] pathToHallow = new Tile[] { 
			new Tile(3213, 3376, 0), new Tile(3211, 3381, 0), new Tile(3211, 3386, 0), 
			new Tile(3211, 3391, 0), new Tile(3214, 3395, 0) };
	
	public final Tile[] pathToMansion = new Tile[] {
			new Tile(3232, 3221, 0), new Tile(3235, 3225, 0), new Tile(3232, 3229, 0), 
			new Tile(3228, 3232, 0), new Tile(3225, 3236, 0), new Tile(3222, 3240, 0), 
			new Tile(3220, 3245, 0), new Tile(3215, 3246, 0), new Tile(3210, 3247, 0), 
			new Tile(3205, 3247, 0), new Tile(3200, 3247, 0), new Tile(3195, 3247, 0), 
			new Tile(3191, 3244, 0), new Tile(3186, 3244, 0), new Tile(3181, 3245, 0), 
			new Tile(3176, 3244, 0), new Tile(3173, 3240, 0), new Tile(3168, 3239, 0), 
			new Tile(3163, 3239, 0), new Tile(3158, 3237, 0), new Tile(3154, 3234, 0), 
			new Tile(3149, 3233, 0), new Tile(3144, 3234, 0), new Tile(3141, 3230, 0), 
			new Tile(3136, 3228, 0), new Tile(3131, 3227, 0), new Tile(3127, 3224, 0), 
			new Tile(3122, 3222, 0), new Tile(3117, 3221, 0), new Tile(3112, 3222, 0), 
			new Tile(3110, 3227, 0), new Tile(3107, 3231, 0), new Tile(3105, 3236, 0), 
			new Tile(3104, 3241, 0), new Tile(3103, 3246, 0), new Tile(3103, 3251, 0), 
			new Tile(3103, 3256, 0), new Tile(3103, 3261, 0), new Tile(3103, 3266, 0), 
			new Tile(3104, 3271, 0), new Tile(3105, 3276, 0), new Tile(3107, 3281, 0), 
			new Tile(3109, 3286, 0), new Tile(3109, 3291, 0), new Tile(3106, 3295, 0), 
			new Tile(3106, 3300, 0), new Tile(3110, 3303, 0), new Tile(3112, 3308, 0), 
			new Tile(3112, 3313, 0), new Tile(3111, 3318, 0), new Tile(3111, 3323, 0), 
			new Tile(3111, 3328, 0), new Tile(3109, 3333, 0), new Tile(3109, 3338, 0), 
			new Tile(3109, 3342, 0), new Tile(3108, 3347, 0), new Tile(3108, 3352, 0), 
			new Tile(3107, 3353, 0) };
	
	final Area MorganDoor = new Area(new Tile[] { 
			new Tile(3095, 3272, 0), new Tile(3095, 3269, 0), new Tile(3100, 3269, 0), 
			new Tile(3099, 3272, 0) });
	
	final Area BarBottomDoor = new Area(new Tile[] { 
			new Tile(3212, 3398, 0), new Tile(3212, 3392, 0), new Tile(3220, 3392, 0), 
			new Tile(3221, 3400, 0) });
	
	final Area ManorArea = new Area(new Tile[] { new Tile(3096, 3354, 0), new Tile(3125, 3354, 0), new Tile(3125, 3370, 0), 
			new Tile(3093, 3370, 0) });
	
	final Area ManorDoorToStairs = new Area(new Tile[] {
			new Tile(3110, 3362, 0), new Tile(3110, 3356, 0), new Tile(3114, 3356, 0), 
			new Tile(3114, 3363, 0) });
	
	public boolean hasEquip  =false;
	
	public int itemsArray[] = {0,0};//contains the states of items needing to be purchased.
	public int itemDID[] = {1550,1918};//contains the ids of the items needing to be purchased.
	public int itemDAmount[] = {1,3};
	public int itemDPrice[] = {1200,1500};//contains specific prices to use upon purchasing specific items.
	public String itemDString[] = {"Garlic","Beer"};//contains the names of the items needing to be purchased.
	
	public int bankItems[] ={1550,15417,1549,1917,1917,1917};
	public int bankItemAmount[] = {1,1,1,1,1,1};
	
	Method Method = new Method(ctx);
	Vars Vars  = new Vars();
	
	public void execute() {
		Method.foodSupport();
		Method.resetTeleporting();
		DeltaQuester.numSteps = 6;

		if(DeltaQuester.checkedBank)
			Method.determineBank(bankItems);
			if((DeltaQuester.checkedBank)&& (ctx.settings.get(2170)&0x7)!=7){
			Method.checkBank();
		}else
	    if(Vars.useBank && (ctx.settings.get(2170)&0x7)!=7){
			Method.useBank(bankItems, bankItemAmount);
		}else if (DeltaQuester.GEFeature && (ctx.settings.get(2170)&0x7)!=7) {
			Method.useGE(itemDString, itemDID, itemDPrice, itemDAmount);
		}else if(!DeltaQuester.exchangeBank){//should be if  false
			Method.exchangeBank(1918,1917,3);
		}else
		if((ctx.settings.get(2170)&0x7)==7){
			DeltaQuester.progress = 6;
			Method.state("The Vampire Slayer quest has been completed.");
			Method.sleep(2000);
			DeltaQuester.e = true;
		}else	
		if((ctx.settings.get(2170)>>3&0x1)==1){
			DeltaQuester.progress = 5;
			cs0();//finish quest
		}else
		if((ctx.settings.get(2170)&0x1F)==6){
			DeltaQuester.progress = 4;
			cs2();//Kill the vampyre
		}else
		if((ctx.settings.get(2170)&0x3)==2){
			DeltaQuester.progress = 3;
			cs1();//Speak to the vampyre slayer (buy beer?)
		}else
		if((ctx.settings.get(2170)&0x1)==1){
			DeltaQuester.progress = 2;
			cs1();//Speak to the vampyre slayer and get supplies
		}else {
			DeltaQuester.progress = 1;
			cs0();//Start the quest by speaking to Morgan
		}
	}

	private void cs2() {
		final String opt[] = {"There's a sign on"};
		Player local = ctx.players.local();
		//NPC vampyre = NPCs.getNearest(9357,9356);
		//SceneObject door = SceneEntities.getNearest(47512);
		
		if((ctx.settings.get(2170)>>6&0x1)==1){
			if(hasEquip){
				if(Method.objIsNotNull(31130)){
					if(Method.npcIsNotNull(9357) || Method.npcIsNotNull(9356)){//vampyres
						Vars.DYNAMICV = false;
						 
						if(Method.npcIsNotNull(9357)&&Method.getNPC(9357).getLocation().distanceTo(local.getLocation())<5||
								 Method.npcIsNotNull(9356)&& Method.getNPC(9356).getLocation().distanceTo(local.getLocation())<5){
							if(!Method.isChatting("Self"))
							if(local.getInteracting()!=null){
								Method.basicFightNPC(9357);
								Method.basicFightNPC(9356);
							}else {
								if(Method.npcIsNotNull(9357))
								Method.getNPC(9357).interact("Attack");
								if(Method.npcIsNotNull(9356))
								Method.getNPC(9356).interact("Attack");
							}
						}else {
							if(Method.npcIsNotNull(9357))
							Method.clickOnMap(Method.getNPC(9357).getLocation());
							if(Method.npcIsNotNull(9356))
							Method.clickOnMap(Method.getNPC(9356).getLocation());
						}
					}else 
					if(new Tile(3079,9785,0).distanceTo(local.getLocation())<5){
						if(!local.isInMotion())
						Method.interactO(158, "Open", "Coffin");
					}else Method.clickOnMap(new Tile(3079,9785,0));
				}else
				if(Method.objIsNotNull(47512) && !ManorDoorToStairs.contains(Method.getObject(47512).getLocation())&&ManorArea.contains(local.getLocation())||!Method.objIsNotNull(47512) && ManorArea.contains(local.getLocation())){
					if(new Tile(3117,3356,0).distanceTo(local.getLocation())<4){
						Method.interactO(47643, "Walk-down", "Stairs");
					}else Method.clickOnMap( new Tile(3117,3356,0));
				}else
				if(ManorArea.contains(local.getLocation())){
					if(new Tile(3112,3360,0).distanceTo(local.getLocation())<4){
						Method.interactO(47512, "Open", "Door");
;					}else Method.clickOnMap(new Tile(3112,3360,0));
				}else
				if(new Tile(3109,3352,0).distanceTo(local.getLocation())<5){
					
					while(Method.isChatting("Self")){
						Method.skipPics();
						Method.findOption(opt);
					}
					Method.interactO(47424, "Open", "Door");
					
				}else if(Vars.DYNAMICV){
					Method.walking(pathToMansion, "Walking to Draynor mansion", false);
				}else if(TeleportLode.DRAYNOR.getTile().distanceTo(local.getLocation())<10 || TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.getLocation())<10){
					Vars.DYNAMICV = true;
				}else if(Method.DraynorLodeIsActive()){
					Method.teleportTo(TeleportType.DRAYNOR.getTeleport(),TeleportType.DRAYNOR.getName());
				}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
				
				
			}else if(!Method.teleporting&&Method.inventoryContains(1549) && Method.inventoryContains(15417)){
				hasEquip = true;
			}else cs1();
		}else cs1();
		
	}

	private void cs1() {
		Player local = ctx.players.local();
	//	NPC dr = NPCs.getNearest(756);
		//SceneObject door = SceneEntities.getNearest(24376);
		final String opt[] = {"I've lost","Are you"};
		
		if(new Tile(3215,3395,0).distanceTo(local.getLocation())<4 || (Method.npcIsNotNull(756)&& Method.getNPC(756).getLocation().distanceTo(local.getLocation())<6)){
		
				if(Method.npcIsNotNull(756))
				if(Method.getNPC(756).getLocation().distanceTo(local.getLocation())<5){
					Method.skipPics();
					Vars.DYNAMICV = false;
					if(!Method.findOption(opt))
						if(!Method.isChatting("Morgan")){
							Method.speakTo(756, "Mogan");
						}
					
				}else if(!BarBottomDoor.contains(Method.getObject(24376).getLocation())||!Method.objIsNotNull(24376)){
					Method.clickOnMap(Method.getNPC(756).getLocation());
				}else Method.interactO(24376, "Open", "Door");
			
		}else if(Vars.DYNAMICV){
			Method.walking(pathToHallow, "Walking to Dr.Hallow", false);
		}else if(TeleportLode.VARROCK.getTile().distanceTo(local.getLocation())<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.VARROCK.getTeleport(),TeleportType.VARROCK.getName());
		
	}

	private void cs0() {
		Player local = ctx.players.local();
		final String opt[] = {"Yes","terrible"};
		//SceneObject door = SceneEntities.getNearest(1239);
		if(new Tile(3098,3271,0).distanceTo(local.getLocation())<2||new Tile(3100,3269,0).distanceTo(local.getLocation())<4){
			if(Method.objIsNotNull(1239) && !MorganDoor.contains(Method.getObject(1239))||!Method.objIsNotNull(1239)){
				if(new Tile(3099,3268,0).distanceTo(local.getLocation())<4){
				if(!Method.startQuestOpen())
				if(!Method.findOption(opt))
				if(!Method.isChatting("Morgan")){
					Vars.DYNAMICV = false;
					Method.speakTo(755, "Mogan");
				}
				}else Method.clickOnMap(new Tile(3099,3268,0));
			}else Method.interactO(1239, "Open", "Door");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToMorgan, "Walking to Morgan", false);
		}else if(TeleportLode.DRAYNOR.getTile().distanceTo(local.getLocation())<10 || TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.getLocation())<10){
			Vars.DYNAMICV = true;
		}else if(Method.DraynorLodeIsActive()){
			Method.teleportTo(TeleportType.DRAYNOR.getTeleport(),TeleportType.DRAYNOR.getName());
		}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
		
	}

	public boolean activate() {
		
		return DeltaQuester.scriptToStart==18;
	}


}
