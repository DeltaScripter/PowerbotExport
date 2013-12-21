package quests;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Player;
import org.powerbot.script.wrappers.Tile;

import quests.Vars.TeleportLode;
import quests.Vars.TeleportType;



public class DruidicRitual extends Node{

	public DruidicRitual(MethodContext ctx) {
		super(ctx);
	}
	public final Tile[] pathToCircle = new Tile[] { 
			new Tile(2899,3537,0),new Tile(2901,3529,0),
			new Tile(2899,3517,0), new Tile(2900,3507,0),
			new Tile(2907,3498,0),new Tile(2911,3501,0)};
	
	public final Tile[] pathToSanfew = new Tile[] { 
			new Tile(2899,3537,0),new Tile(2901,3529,0),
			new Tile(2899,3517,0), new Tile(2900,3507,0),
			new Tile(2907,3498,0),new Tile(2911,3501,0), new Tile(2914,3495,0),
			new Tile(2917,3486,0), new Tile(2920,3474,0), new Tile(2922,3465,0),
			new Tile(2923,3451,0), new Tile(2921,3438,0), new Tile(2917,3438,0)};
	
	public final Tile[] pathToCave = new Tile[] { 
			new Tile(2901,3529, 0),	new Tile(2900,3515, 0), 
			new Tile(2903,3502, 0),new Tile(2908,3501,0), 
			new Tile(2917,3488, 0),new Tile(2920,3473,0), 
			new Tile(2922,3461, 0),new Tile(2922,3447,0), 
			new Tile(2916,3438, 0),new Tile(2921,3425, 0),
			new Tile(2930,3412, 0), new Tile(2927,3408, 0)};
	
	public final Tile[] pathToFishSpot = new Tile[] { 
			new Tile(2899,3531, 0),	new Tile(2899,3517, 0), 
			new Tile(2893,3504, 0),new Tile(2892,3492,0), 
			new Tile(2892,3478, 0),new Tile(2893,3464,0), 
			new Tile(2899,3452, 0),new Tile(2899,3437,0), 
			new Tile(2887,3429, 0),new Tile(2887,3415,0), 
			new Tile(2900,3415, 0),new Tile(2904,3403,0), 
			new Tile(2905,3398, 0)};
	
	public Tile initTile;
	public boolean spoke = false;
	public int tele = 0;
	public int[] wood = {15111,15110,15109,15108};

	public int itemsArray[] = {0,0,0};//contains the states of items needing to be purchased.
	public int itemDID[] = {307,1755,229};//contains the ids of the items needing to be purchased.
	public int itemDPrice[] = {1550,2000,800};//contains specific prices to use upon purchasing specific items.
	public int itemDAmount[] = {1,1,1};
	public String itemDString[] = {"Fishing rod", "Chisel", "Vial"};//contains the names of the items needing to be purchased.
	
	public int bankItems[] = {23096,23099,23097,23098,307,1755,229,23100};
	public int bankItemAmount[] = {1,1,1,1,1,1,1,1};
	
	
	Vars Vars = new Vars();
	Method Method = new Method(ctx);
	
	boolean q = true;
	public void execute() {
		if(q){
			TaskListing.taskRemove.clear();
			TaskListing.taskListData.add("Start quest by speaking to Sanfew");
			TaskListing.taskListData.add("Speak to Sanfew");
			TaskListing.taskListData.add("Gather special water, wandering wyrm wood and fish scales and make potion");
			TaskListing.taskListData.add("Speak to Sanfew again.");
			TaskListing.taskListData.add("Speak to Kaqemeex and finish quest");
			TaskListing.updateTasks();
			q = false;
		}
		
		DeltaQuester.numSteps = 6;
		Method.foodSupport();
		while (ctx.settings.get(1113)!=0){//If cutscene is occurring.
			Method.isChatting("Cutscene");
		}
		
		if(DeltaQuester.checkedBank)
			Method.determineBank(bankItems);
		if(!DeltaQuester.checkedBank && (ctx.settings.get(2694) & 0xFF)!=136){
			Method.checkBank();
		}else
		 if(Vars.useBank && (ctx.settings.get(2694) & 0xFF)!=136){
			Method.useBank(bankItems,bankItemAmount);
			}else
		if (DeltaQuester.GEFeature && (ctx.settings.get(2694) & 0xFF)!=136) {
			Method.useGE(itemDString, itemDID, itemDPrice, itemDAmount);
		}else{
		DeltaQuester.numSteps = 6;
		if(TeleportLode.BURTHORPE.getTile().distanceTo(ctx.players.local().getLocation())<10)
			Method.teleporting = false;
		
		if((ctx.settings.get(2694) & 0xFF)==136){
			DeltaQuester.progress=6;
			Method.state("The Druidic Ritual quest has been completed.");
			ctx.environment.sleep(2000);
			DeltaQuester.e = true;
		}else
		if((ctx.settings.get(2694) & 0x7F)==118){
			DeltaQuester.progress=5;
			cS4();//Speaks to Kaqemeex at the stone circle, finishing the quest, "Speak to Sanfew again."
			
			if(!TaskListing.taskRemove.contains("Start quest by speaking to Sanfew"))
				TaskListing.taskRemove.add("Start quest by speaking to Sanfew");
			if(!TaskListing.taskRemove.contains("Speak to Sanfew"))
				TaskListing.taskRemove.add("Speak to Sanfew");
			if(!TaskListing.taskRemove.contains("Gather special water, wandering wyrm wood and fish scales and make potion"))
				TaskListing.taskRemove.add("Gather special water, wandering wyrm wood and fish scales and make potion");
			if(!TaskListing.taskRemove.contains("Speak to Sanfew again."))
				TaskListing.taskRemove.add("Speak to Sanfew again.");
			    TaskListing.removeTasks(TaskListing.taskRemove);
		}else
		if((ctx.settings.get(2694) & 0x7F)==116){
			DeltaQuester.progress=4;
			cS3();//Speaks to Sanfew again.., "Gather special water, wandering wyrm wood and fish scales"
			
			if(!TaskListing.taskRemove.contains("Start quest by speaking to Sanfew"))
				TaskListing.taskRemove.add("Start quest by speaking to Sanfew");
			if(!TaskListing.taskRemove.contains("Speak to Sanfew"))
				TaskListing.taskRemove.add("Speak to Sanfew");
			if(!TaskListing.taskRemove.contains("Gather special water, wandering wyrm wood and fish scales and make potion"))
				TaskListing.taskRemove.add("Gather special water, wandering wyrm wood and fish scales and make potion");
			
			    TaskListing.removeTasks(TaskListing.taskRemove);
		}else
		if((ctx.settings.get(2694) & 0x7)==4){
			DeltaQuester.progress=3;
			cS2();//Gathers items & makes another item, "Speak to Sanfew"
			if(!TaskListing.taskRemove.contains("Start quest by speaking to Sanfew"))
				TaskListing.taskRemove.add("Start quest by speaking to Sanfew");
			if(!TaskListing.taskRemove.contains("Speak to Sanfew"))
				TaskListing.taskRemove.add("Speak to Sanfew");
			
			    TaskListing.removeTasks(TaskListing.taskRemove);
		}else
		if((ctx.settings.get(2694) & 0x3)==2){
			DeltaQuester.progress=2;
			cS1();//Speaks to Sanfew
			if(!TaskListing.taskRemove.contains("Start quest by speaking to Sanfew"))
				TaskListing.taskRemove.add("Start quest by speaking to Sanfew");
			    TaskListing.removeTasks(TaskListing.taskRemove);
			    
		}else if((ctx.settings.get(2694) & 0x1)==0){
			DeltaQuester.progress=1;
			cS0();//Speaks to Kaqemeex and starts the quest
		}
		}
		
	}
	
	private void cS4() {
		final String opt[] = {"I'm pretty sure","What do you need","Talk about Druidic"};
		Player local = ctx.players.local();
		while(ctx.settings.get(1113)==5){
			Method.pressContinue();
		}
		if(new Tile(2909,3501,0).distanceTo(local.getLocation())<6){
			if(!Method.findOption(opt))
				if(!Method.isChatting("Kaqemeex")){
					Method.speakTo(455,"Kaqemeex");
				}
		}else if(Vars.DYNAMICV){
			Method.walking(pathToCircle,"Walking to Kaqemeex",false);
		}else if(new Tile(2897,3545,0).distanceTo(local.getLocation())<8){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());
		
		
	}

	private void cS3() {
		Player local = ctx.players.local();
		if(ctx.widgets.get(1189).isValid()){
			Method.pressContinue();
		}
		if(new Tile(2916,3438,0).distanceTo(local.getLocation())<6){
			Method.skipPics();
			if(!Method.isChatting("Sanfew")){
				Vars.DYNAMICV=false;
				Method.speakTo(454,"Sanfew");
			}
		}else if(Vars.DYNAMICV){
			Method.walking(pathToSanfew,"Walking to Sanfew",false);
		}else if(new Tile(2897,3545,0).distanceTo(local.getLocation())<8){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());
		
	}

	private void cS2() {
		final String opt[] = {"make an ointment"};
		Player local = ctx.players.local();
		
		Method.skipPics();
		if(!Method.teleporting){
		if(Method.inventoryContains(23096)){
			if(Method.inventoryContains(23099)){
				if(Method.inventoryContains(23097)){
					
					if(new Tile(2916,3438,0).distanceTo(local.getLocation())<6){
						if(!Method.findOption(opt))
							if(!Method.isChatting("Sanfew")){
								Method.speakTo(454,"Sanfew");
							}
						
					}else if(Vars.DYNAMICV){
						Method.walking(pathToSanfew,"Walking to Sanfew",false);
					}else if(new Tile(2897,3545,0).distanceTo(local.getLocation())<8){
						Vars.DYNAMICV = true;
					}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());
					
				}else gatherWood();
			}else gatherFishScales();
		}else{
			gatherWater();
		}
		}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());
		
	}

	private void gatherWood() {
		Player local = ctx.players.local();
		
		if(new Tile(2912,3383,0).distanceTo(local.getLocation())<10){
			//ctx.camera.setYaw(50);
			
			for(int i: wood){
				Vars.DYNAMICV = false;
					Method.state("Taking leaf/Waiting for creatures to spawn");
				Method.npcInteract(i, "Take");
				
			}
			
		}else if(new Tile(2912,3383,0).distanceTo(local.getLocation())>20){
			gatherFishScales();
		}else Method.clickOnMap( new Tile(2912,3383,0));
		
	}

	private void gatherFishScales() {
		Player local = ctx.players.local();
		
		if(new Tile(2905,3398,0).distanceTo(local.getLocation())<5){
			if(Method.inventoryContains(23098)){
				if(ctx.widgets.get(1179).isValid()){
					ctx.widgets.get(1179,16).click(true);
				}else Method.interactInventory(23098, "Gather","Item");
			}else {
				if(local.getAnimation()==-1){
					Vars.DYNAMICV = false;
				Method.interactO(67686, "Bait","Bait");
				}
			}
		}else{
			if(Vars.DYNAMICV){
				Method.walking(pathToFishSpot,"Walking to fishing location",false);
			}else if(TeleportLode.BURTHORPE.getTile().distanceTo(local.getLocation())<8){
				Vars.DYNAMICV = true;
			}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());
		}
		
	}

	private void gatherWater() {
	//	SceneObject check = SceneEntities.getNearest(72822);
		
		Player local = ctx.players.local();
		final String opt[] = {"Let's go!","bravest"};
		if(Method.objIsNotNull(72822)){
			Vars.DYNAMICV=false;
				if(initTile !=null){
					//NPC woman = NPCs.getNearest(14952);
					if(Method.npcIsNotNull(14952)){
						if(spoke){
							Vars.DYNAMICV = false;
							collectWater();
						}else{
							Method.state("Hete");
							if(ctx.widgets.get(1184).isValid()&& ctx.widgets.get(1184,13).getText().contains("We need to find")){
								spoke = true;
							}
							if(!Method.findOption(opt))
								if(!Method.isChatting("Ariane")){
									Method.speakTo(14952,"Ariane");
									Method.speakTo(14953,"Ariane");
								}
						}
					}else collectWater();
					
				}else initTile = local.getLocation();
			
				
				
		}else if(new Tile(2927,3406,0).distanceTo(local.getLocation())<6){
			Method.state("Entering cave.");
			Method.interactO(67043, "Enter","Cave");
		}else {
			if(Vars.DYNAMICV){
			Method.walking(pathToCave ,"Walking to cave with special water",false);
			}else if(TeleportLode.BURTHORPE.getTile().distanceTo(local.getLocation())<8){
				Vars.DYNAMICV = true;
			}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());
		}
		
	}

	private void collectWater() {
		final String opt[] = {"make an ointment"};
		Player local = ctx.players.local();
		
		if(!Method.findOption(opt))
		if(Method.isChatting("Person")){
			Vars.DYNAMICV = false;
		}else{
		//SceneObject fountain = SceneEntities.getNearest(67045);
			if(Method.objIsNotNull(67045))
				if (Method.getObject(67045).getLocation().distanceTo(local.getLocation()) < 3) {
					Method.state("Collecting water.");
					Method.interactO(67045, "Collect","Substance");
					
				} else if(Method.objIsNotNull(67045)){
					Method.clickOnMap(Method.getObject(67045).getLocation());
				}else Method.state("Obj is null");
				
			
		}
	}

	private void cS1() {//Speaks to Sanfew
		final String opt[] = {"make an ointment"};
		Player local = ctx.players.local();
			
			Method.skipPics();
			if(new Tile(2916,3438,0).distanceTo(local.getLocation())<6){//sanfew area
					if(!Method.findOption(opt))
						if(!Method.isChatting("Sanfew")){
							Method.speakTo(454,"Sanfew");
						}
			}else if(Vars.DYNAMICV){
				Method.walking(pathToSanfew, "Walking to Sanfew",false);
			}else if(TeleportLode.BURTHORPE.getTile().distanceTo(local.getLocation())<8){
				Vars.DYNAMICV = true;
			}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());
		
	}

	private void cS0() {//Speaks to Kaqemeex and starts the quest
		 final String opt[] = {"I'm pretty sure","What do you need","Talk about Druidic"};
		 Player local = ctx.players.local();
		if(new Tile(2909,3501,0).distanceTo(local.getLocation())<6){//Stonecircle area
			if(!Method.startQuestOpen())
				if(!Method.findOption(opt))
					if(!Method.isChatting("Kaqemeex")){
						Method.speakTo(455,"Kaqemeex");
					}
		}else if(Vars.DYNAMICV){
			Method.walking(pathToCircle, "Walking to Kaqemeex",false);
		}else if(TeleportLode.BURTHORPE.getTile().distanceTo(local.getLocation())<8){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());
		
	}
	public boolean activate() {
		return (DeltaQuester.scriptToStart == 12);
	}

}
