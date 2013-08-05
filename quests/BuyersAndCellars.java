package quests;

import org.powerbot.script.methods.Hud.Window;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.Skills;
import org.powerbot.script.wrappers.Player;
import org.powerbot.script.wrappers.Tile;

import quests.Vars.TeleportLode;
import quests.Vars.TeleportType;

public class BuyersAndCellars extends Node {
	public BuyersAndCellars(MethodContext ctx) {
		super(ctx);
	}

	public boolean pickPocket = false;
	public int itemsArray[] = { 0, 0, 0 };
	public final int fArray[] = { 9, 2, 4, 12 };
	Tile t = new Tile(3211, 3150, 0);

	public final Tile[] pathToCellar = new Tile[] { new Tile(3227, 3232, 0),
			new Tile(3222, 3242, 0), new Tile(3218, 3252, 0),
			new Tile(3216, 3264, 0), new Tile(3222, 3269, 0) };

	public final Tile[] pathToRobin = new Tile[] { new Tile(3224, 3217, 0),
			new Tile(3218, 3210, 0), new Tile(3212, 3206, 0) };

	public final Tile[] pathToFather = new Tile[] { new Tile(3235, 3208, 0),
			new Tile(3240, 3195, 0), new Tile(3240, 3181, 0),
			new Tile(3236, 3169, 0), new Tile(3227, 3158, 0),
			new Tile(3216, 3156, 0), new Tile(3207, 3153, 0) };
	
	final Area FatherDoor = new Area(new Tile[] { new Tile(3203, 3154, 0), new Tile(3203, 3150, 0), new Tile(3209, 3150, 0), 
			new Tile(3209, 3156, 0) });

	public int itemsArrayGE[] = {0};//contains the states of items needing to be purchased.
	public int itemDID[] = {1511};//contains the ids of the items needing to be purchased.
	public int itemDAmount[] = {1};
	public int itemDPrice[] = {500};//contains specific prices to use upon purchasing specific items.
	public String itemDString[] = {"Logs"};//contains the names of the items needing to be purchased.
	
	public int bankItems[] = {1511,18648};
	private Vars Vars = new Vars();
	private Method Method = new Method(ctx);
	public void execute() {
		/*
		 * ctx.settings used:
		 * 2086-indication if distracted(crazy father)
		 * 2085-steps for quest
		 * 1297-More possibilities for starting quest and ending quest.
		 */
		if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(ctx.players.local().getLocation())<6){
			Method.teleporting = false;
		}
		DeltaQuester.numSteps =9;
	//	if(Method.useBank&&(ctx.settings.get(2085) & 0x7FF) != 1930){
		//	Method.useBank(bankItems, 1,1,90);
		//}else
		//if (DeltaQuester.getInstance().GEFeature &&(ctx.settings.get(2085) & 0x7FF) != 1930) {
		//	Method.useGE(itemDString, itemDID, itemDPrice, itemDAmount);
	//	}else
			if ((ctx.settings.get(2085) & 0x7FF) == 1930) {
			DeltaQuester.progress=9;
			Method.state("The Buyers and Cellars quest has been completed.");
			ctx.environment.sleep(2000);
			DeltaQuester.getInstance().e = true;
		}else if ((ctx.settings.get(2085) & 0x7FF) == 1605) {
			cS7();
			DeltaQuester.progress=8;
		}else if ((ctx.settings.get(2085) & 0x7FF) == 1413) {
			cS6();
			DeltaQuester.progress=7;
		}else if ((ctx.settings.get(2085) & 0x7FF) == 1285) {
			cS5();
			DeltaQuester.progress=6;
		}else if ((ctx.settings.get(2085) & 0x3FF) == 965) {
			cS2();//Speaks to the theif obin again
			DeltaQuester.progress=5;
		}else if ((ctx.settings.get(2085) & 0x3FF) == 645) {
			cS3();//Speaks to the old priest
			DeltaQuester.progress=4;
		}else if ((ctx.settings.get(2085) & 0x1FF) == 453) {
			cS2();//Walks and speaks to maste theif Robin
			DeltaQuester.progress=3;
		} else if ((ctx.settings.get(2085) & 0x7F) == 69) {
			cS1();//Pickpockets the dummy in the cellar
			DeltaQuester.progress=2;
		} else if (ctx.settings.get(2085) == 0) {
			DeltaQuester.progress=1;
			cS0();//Head into the cellar and start the quest
		}
	
	}

	private void cS7() {//Hand back the trophy
		//SceneObject check = SceneEntities.getNearest(52289);
		Player local = ctx.players.local();
		if (Method.objIsNotNull(52289)) {
			//SceneObject pole = SceneEntities.getNearest(52424);
			if (Method.objIsNotNull(52424) && Method.getObject(52424).getLocation().distanceTo(local.getLocation()) < 8) {
				if(ctx.widgets.get(1186).isValid()){
					Method.pressContinue();
				}
				if(!Method.isChatting("Head-thief")){
					Vars.DYNAMICV = false;
					if(ctx.hud.isOpen(Window.BACKPACK)){//If the inventory is open
					if (ctx.backpack.getSelectedItem() != null) {
						Method.npcInteract(11273, "Use");
					} else
						Method.interactInventory(18648, "Use","Trophy");
				}else ctx.hud.open(Window.BACKPACK);//open backpack
				}
			} else if(Method.objIsNotNull(52424)){
				Method.state("Moving closer.");
			    Method.clickOnMap(Method.getObject(52424).getLocation());
			}
		} else if (Vars.DYNAMICV) {
			if (new Tile(3221, 3269, 0).distanceTo(local.getLocation()) < 3) {
				Method.interactO(52309, "Enter","Trap door");
			} else
			Method.walking(pathToCellar, "Walking to the Cellar",false);
		} else if (TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.getLocation())<10) {
			Vars.DYNAMICV = true;
		} else
			Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());

	}

	private void cS6() {//Steal the metal from the case
		Method.state("Retrieved key to glass case");
		Vars.DYNAMICV = false;
		Method.interactO(51653, "Open","Glass case");

	}

	private void cS5() {//Sets the fire, and steals the metal
		final String opt[] = {"Fire","Nice"};
		Player local = ctx.players.local();
	//	SceneObject door = SceneEntities.getNearest(45539);
		if (new Tile(3207, 3153, 0).distanceTo(local.getLocation()) < 7) {
			if (fireValid()) {
				if (Method.objIsNotNull(45539) && !FatherDoor.contains(Method.getObject(45539).getLocation()) || !Method.objIsNotNull(45539)) {
					if (ctx.settings.get(2086) == 1) {
						Method.npcInteract(458, "Pick");
					} else {
						while(ctx.widgets.get(1186).isValid()){
							Method.pressContinue();
						}
						if(!Method.findOption(opt)){Vars.DYNAMICV = false;
							if(!Method.isChatting("Old priest")){
								Method.speakTo(458, "Priest");
							}
						}
					}
				} else
					Method.interactO(45539, "Open","Door");
			} else {
				Method.state("Attempting to start fire");
				//GroundItem log =GroundItems.getNearest(1511);
				if (local.getLocation().equals(new Tile(3211, 3150, 0))) {
					if (local.getAnimation() == -1){
						if(Method.inventoryContains(1511)){
						Method.interactInventory(1511, "Light","Logs");
						}else if(Method.gItemIsNotNull(1511)&&Method.getGroundItem(1511).isOnScreen()){
							Method.interactG(1511, "Light", "Log");
						} else{
							Vars.DYNAMICV = false;
							Vars.DYNAMICV2 = false;
							DeltaQuester.getInstance().GEFeature = true;
						}
					}
				} else {
					if (new Tile(3211, 3150, 0).getMatrix(ctx).isOnScreen()) {
						new Tile(3211, 3150, 0).getMatrix(ctx).click(true);
						ctx.environment.sleep(300, 600); 
					} else
						ctx.camera.turnTo(new Tile(3211, 3150, 0));

				}
			}
		} else if (Vars.DYNAMICV) {
			Method.walking(pathToFather, "Walking to priest",false);
		} else if (TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.getLocation())<10) {
			Vars.DYNAMICV = true;
		} else
			Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());

	}

	private boolean fireValid() {
		//SceneObject fire = SceneEntities.getNearest(70755);
		if (Method.objIsNotNull(70755)) {
			if (Method.getObject(70755).isOnScreen()) {
				return true;
			}
		}
		return false;
	}
/*
	private void cS4() {//Speaks to the theif robin again..
		Player local = ctx.players.local();
		if (new Tile(3212, 3207, 0).distanceTo(local.getLocation()) < 5) {
			final String opt[] = {"egre"};
			if(!Method.findOption(opt)){Vars.DYNAMICV = false;
				if(!Method.isChatting("Thief Robin")){
					Method.speakTo(11268, "Thief Robin");
				}
			}
		} else if (Vars.DYNAMICV) {
			Method.walking(pathToRobin, "Walking to thief Robin",false);
		} else if (TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.getLocation())<10) {
			Vars.DYNAMICV = true;
		} else
			Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
	}
*/
	private void cS3() {//Speaks to the old priest initially
		Player local = ctx.players.local();
		//SceneObject door = SceneEntities.getNearest(45539);
		if (new Tile(3207, 3153, 0).distanceTo(local.getLocation()) < 8) {//45539 is a door
			if (Method.objIsNotNull(45539) && !FatherDoor.contains(Method.getObject(45539).getLocation()) ||!Method.objIsNotNull(45539)) {
				final String opt[] ={"Bye","look at it?","Nice"};
				if(!Method.findOption(opt)){
					Vars.DYNAMICV = false;
					if(!Method.isChatting("Old Priest")){
						Method.speakTo(458, "Old Priest");
					}
				}
			} else Method.interactO(45539, "Open","Door");
		} else if (Vars.DYNAMICV) {
			Method.walking(pathToFather, "Walking to priest",false);
		} else if (TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.getLocation())<10) {
			Vars.DYNAMICV = true;
		} else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());

	}

	private void cS2() {//Walks and speaks to the master theif Robin
		Player local = ctx.players.local();
		final String opt[] = {"Go ahead."};
		if (new Tile(3212, 3207, 0).distanceTo(local.getLocation()) < 5) {
			
			if(!Method.findOption(opt)){Vars.DYNAMICV = false;
				if(!Method.isChatting("Thief Robin")){
					Method.speakTo(11268, "Thief Robin");
				}
			}
		} else if (Vars.DYNAMICV) {
			Method.walking(pathToRobin, "Walking to Thief Robin",false);
		} else if (TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.getLocation())<10) {
			Vars.DYNAMICV = true;
		} else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());

	}

	private void cS1() {//Pickpockets dummy in the cellar
		//SceneObject check = SceneEntities.getNearest(52289);
		Player local = ctx.players.local();
		final String opt[] = {"Alright.","No, I","Yes","Skip speech"};
		if (Method.objIsNotNull(52289)) {//Basement area
			//NPC dar = NPCs.getNearest(11273);
			Method.skipPics();
			if (Method.npcIsNotNull(11273) && Method.getNPC(11273).getLocation().distanceTo(local.getLocation()) < 5) {
				if (pickPocket) {
					if (ctx.skills.getLevel(Skills.THIEVING) >= 5) {
						if (new Tile(4661, 5903, 0).distanceTo(local.getLocation()) < 2) {
							pickPocket = false;
						} else
							new Tile(4661, 5903, 0).getMatrix(ctx).click(true);
					} else Method.interactO(52316, "Pickpocket","Dummy");
					
				} else if(ctx.widgets.get(1184).isValid() && ctx.widgets.get(1184,13).getText().contains("Just keep picking that")){
					pickPocket = true;
				}else{
					if (!Method.startQuestOpen()) {Vars.DYNAMICV = false;
						if (!Method.findOption(opt)) {
							if (!Method.isChatting("Head Thief")) {
								if ((!local.isInMotion())) {
									if (local.getLocation().equals(new Tile(4664, 5903, 0))) {
										if(local.getOrientation()!=0){
											Method.state("Temp-sleep");
											ctx.environment.sleep(2000);
											if(!ctx.widgets.get(1184).isValid())
												Method.speakTo(11273, "Head Thief");
										}
									} else Method.speakTo(11273, "Head Thief");
									
								}
							}
						}
					}
				}
			} else if(Method.npcIsNotNull(11273)){
				Method.state("Walking to Head Thief");
				Method.clickOnMap(Method.getNPC(11273).getLocation());
			}
		} else if (Vars.DYNAMICV) {
			if (new Tile(3221, 3269, 0).distanceTo(local.getLocation()) < 3) {
				Method.interactO(52309, "Enter","Trap door");
			} else Method.walking(pathToCellar, "Walking to Cellar.",false);
		} else if (TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.getLocation())<10) {
			Vars.DYNAMICV = true;
		} else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
	}

	private void cS0() {//Heads into the cellar and starts the quest by speaking to the theives
		 
		 final String opt[] = {"What are you","And what","Alirght.","Skip speech"};
		 Player local = ctx.players.local();
		//SceneObject check = SceneEntities.getNearest(52289);
		if (Method.objIsNotNull(52289)) {//basement area
			if (new Tile(4664, 5902, 0).distanceTo(local.getLocation()) < 5) {//people location
				if(!Method.startQuestOpen())
				if(!Method.findOption(opt))
					if(!Method.isChatting("Head Thief")){
						
						if ((!local.isInMotion())) {
							if (local.getLocation().equals(new Tile(4664, 5903, 0))) {
								if(local.getOrientation()!=0){
									Method.state("Temp-sleep");
									ctx.environment.sleep(2000);
									if(!ctx.widgets.get(1184).isValid())
										Method.speakTo(11273, "Head Thief");
								}
							} else Method.speakTo(11273, "Head Thief");
							
							}
						}
					
				
					
			} else Method.clickOnMap(new Tile(4664, 5902, 0));
		} else if (Vars.DYNAMICV) {
			if (new Tile(3221, 3269, 0).distanceTo(local.getLocation()) < 3) {
				Method.interactO(52309, "Enter","Trap door");
			} else Method.walking(pathToCellar,"Walking to Cellar.",false);
		} else if (TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.getLocation())<10) {
			Vars.DYNAMICV = true;
		} else
			Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());

	}
	public boolean activate() {
		return (DeltaQuester.scriptToStart == 10);
	}

}
