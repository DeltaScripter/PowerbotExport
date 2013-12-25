/*package quests;

import java.awt.Graphics;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.util.Timer;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Equipment;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class RuneMysteries extends Node{

	public boolean hasMindSpike = false;
	public boolean equipStaff = false;
	public boolean init = true;
	public Tile marker;
	public final int fireID = 80244;
	public Tile distanceMarker;
	
	public final Area arianeArea= new Area(new Tile[] {//The first meeting spot
			new Tile(3097,3164,0), new Tile(3107,3164,0),
			new Tile(3107,3183,0), new Tile(3097,3185,0)});
	
	public final Area ariane2Area= new Area(new Tile[] {//Arianes spot on step 2.
			new Tile(3098,3183,0), new Tile(3107,3183,0),
			new Tile(3109,3210,0), new Tile(3098,3210,0)});
	
	
	public String[] inputKeys = {"B","B","B","B","B","B","B","B","B","B","A#","A#","A#","A#","A#","A#","A#","G#","G"};
	public int[] widgetKeys = {126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144};
	
	
	public final Tile[] pathToArianeWizardTower = new Tile[] { new Tile(3232, 3221, 0), new Tile(3233, 3226, 0), new Tile(3229, 3229, 0), 
			new Tile(3226, 3233, 0), new Tile(3223, 3237, 0), new Tile(3222, 3242, 0), 
			new Tile(3218, 3245, 0), new Tile(3213, 3247, 0), new Tile(3208, 3248, 0), 
			new Tile(3203, 3248, 0), new Tile(3198, 3247, 0), new Tile(3193, 3245, 0), 
			new Tile(3188, 3244, 0), new Tile(3183, 3245, 0), new Tile(3178, 3247, 0), 
			new Tile(3174, 3244, 0), new Tile(3170, 3241, 0), new Tile(3165, 3239, 0), 
			new Tile(3160, 3240, 0), new Tile(3157, 3236, 0), new Tile(3152, 3234, 0), 
			new Tile(3147, 3235, 0), new Tile(3143, 3232, 0), new Tile(3138, 3229, 0), 
			new Tile(3133, 3228, 0), new Tile(3129, 3225, 0), new Tile(3124, 3223, 0), 
			new Tile(3119, 3221, 0), new Tile(3114, 3222, 0), new Tile(3109, 3223, 0), 
			new Tile(3104, 3222, 0), new Tile(3102, 3217, 0), new Tile(3103, 3298, 0), 
			new Tile(3108, 3295, 0), new Tile(3108, 3290, 0), new Tile(3107, 3285, 0), 
			new Tile(3107, 3280, 0), new Tile(3104, 3276, 0), new Tile(3103, 3271, 0), 
			new Tile(3103, 3266, 0), new Tile(3103, 3261, 0), new Tile(3103, 3256, 0), 
			new Tile(3103, 3251, 0), new Tile(3104, 3246, 0), new Tile(3105, 3241, 0), 
			new Tile(3105, 3236, 0), new Tile(3105, 3231, 0), new Tile(3103, 3226, 0), 
			new Tile(3102, 3221, 0), new Tile(3101, 3216, 0), new Tile(3102, 3211, 0), 
			new Tile(3102, 3206, 0), new Tile(3102, 3201, 0), new Tile(3103, 3196, 0), 
			new Tile(3102, 3191, 0), new Tile(3102, 3186, 0), new Tile(3102, 3181, 0), 
			new Tile(3102, 3176, 0), new Tile(3102, 3171, 0), new Tile(3102, 3169, 0) };
	
	public boolean staffEquip = false;
	
	public int bankItems[] = {26113,26114,26115,26109};
	public void execute() {
		/*
		if((Settings.get(3294)&0x1F) == 10){
			
		cs1();	
		
		}else cs0();
		
		
		 * Settings used:
		 * 3295-Main steps for quest
		 * 0-Activation of auto-cast for a spell.
		 * 
		DeltaQuester.numSteps = 20;
		Quests.getSettings(3295);
		if(Method.useBank &&(Settings.get(3294) & 0x3FF) !=938){
			Method.useBank(bankItems, 1,1,90);
		}else
		if(init){
			init();
		}else
		if((Settings.get(3294) & 0x3FF) ==938){
			DeltaQuester.progress = 20;
			DeltaQuester.state = "The Rune Mysteries quest has been completed.";
			Task.sleep(2000);
			DeltaQuester.e = true;
		}else
		if((Settings.get(3294) & 0x3FF) ==928||(Settings.get(3294) & 0x3FF) ==929){
			DeltaQuester.progress = 19;
			CS6();//Speak to Ariane one last time to finish the quest.
		}else
		if((Settings.get(3294) & 0x3FF) ==919 || (Settings.get(3294) & 0x3FF) ==920){
			DeltaQuester.progress = 18;
			CS10();//Play the organ once 
		}else
		if((Settings.get(3294) & 0x3FF) ==918||(Settings.get(3294) & 0x3FF) ==908){
			DeltaQuester.progress = 17;
			CS10();//Return to the modern tower and annoy the wizards and speak to a wizard(not intentional)
		}else
		if((Settings.get(3294) & 0x3FF) ==898){
			DeltaQuester.progress = 16;
			CS9();//Play the orb game in the secret tower.
		}else
		if((Settings.get(3294) & 0x3FF) ==888){
			DeltaQuester.progress = 15;
			CS8();//Cross the fallen statue and enter the room at which you speak to Ariane again.
		}else
		if((Settings.get(3294) & 0x3FF) ==878){
			DeltaQuester.progress = 14;
			CS8();//Enter the door after golem and push down the statue.
		}else
		if((Settings.get(3294) & 0x7F) ==101){
			DeltaQuester.progress = 13;
			CS7();//Speak to the golem and answer its questions.
		}else
		if((Settings.get(3294) & 0x7F) ==100){
			DeltaQuester.progress = 12;
			CS7();//Speak to Ariane initially upon entering the tower.
		}else
		if((Settings.get(3294) & 0x7F) ==90){
			DeltaQuester.progress = 11;
			CS6();//Walk to and enter the secret-old tower.
		}else
		if((Settings.get(3294) & 0x7F) ==80){
			DeltaQuester.progress = 10;
			CS2();//Speak to Ariane again with the newly found key.
		}else
		if((Settings.get(3294) & 0x7F) ==70){
			DeltaQuester.progress = 9;
			CS5();//Walk to organ and solve puzzle
		}else
		if((Settings.get(3294) & 0x3F) ==60){
			DeltaQuester.progress = 8;
			CS4();//Search for the book Ellaron mentioned
		}else
		if((Settings.get(3294) & 0x7FFF) ==28722){
			DeltaQuester.progress = 7;
			CS3();//Speak to the wizard Ellaron
		}else
		if((Settings.get(3294) & 0x7FFF) ==28717){
			DeltaQuester.progress = 6;
			CS2();//Speak to Ariane regardin what to do next.
		}else
		if((Settings.get(3294) & 0x7FFF) ==28712){
			DeltaQuester.progress = 5;
			CS2();//Speak to Ariane regarding the issues.
		}else
		if((Settings.get(3294) & 0x3F) ==40){
			DeltaQuester.progress = 4;
			CS3();//Speak to the wizards in the tower
		}else
		if((Settings.get(3294) & 0x1F) ==30){
			DeltaQuester.progress = 3;
			CS2();//Speak to Ariane the wizards tower for the first time.
		}else
		if((Settings.get(3294) & 0xF) ==10){
			DeltaQuester.progress = 2;
			cs1();//Pick-up staff and complete the first puzzle(pull the orb in the circle)
		}else
		if((Settings.get(3294) & 0x1) ==0){
			DeltaQuester.progress = 1;
			CS0();//Start the quest by speaking to Ariane
		}
	}
	

public Timer wait = new Timer(1);
	private void cs1() {
		SceneObject staff = SceneEntities.getNearest(79901);
		NPC vortex = NPCs.getNearest(16141);
		
		if((Settings.get(3295)>>23&0x1)==1){
			if((Settings.get(3295)>>24&0x1)==1){
				if(staffEquip){
					if((Settings.get(715)>>6&0x3)==2){
						if(vortex!=null){
							Quests.DYNAMICV = false;
							if(Players.getLocal().getLocation().getY()!=vortex.getLocation().getY()-2|| vortex.getLocation().distanceTo()<3){
							Method.state("Adjusting position.");
									new Tile(vortex.getLocation().getX()+5,vortex.getLocation().getY()-3,0).clickOnMap();
							}else if(Players.getLocal().isIdle()){
								Method.state("Attacking vortex.");
								vortex.interact("Attack");
								Task.sleep(3000);
							}
						}
						
					}else setAutoCast();
				}else if(Equipment.getItem(26109)!=null){
					staffEquip  =true;
				}else if(Inventory.getItem(26109)!=null){
					Method.interactInventory(26109, "Wield", "Staff");
				}
			}else if(staff!=null){
				if (staff.getLocation().distanceTo() < 6) {// Gather mind spike
					Method.interactO(79901, "Take", "Mindspike");
				} else staff.getLocation().clickOnMap();
			}
			
		}else cs0();//Speak to Ariane
		
	}



	private void setAutoCast() {
		if(Tabs.ABILITY_BOOK.isOpen()){
			
			if((Settings.get(682)&0x7)==4){
				if(Widgets.get(275).getChild(19).getChild(0).visible()){
					Widgets.get(275).getChild(19).getChild(0).interact("Auto");
				}else Widgets.get(275).getChild(37).click(true);
			}else Widgets.get(275).getChild(41).click(true);
			
		}else Tabs.ABILITY_BOOK.open();
		
	}



	private void cs0() {//Speaks to Ariane in the Wizards Tower
		final String opt[] = {"What can I do","What's happening here"};
		
		Method.isChatting("People");
		if(new Tile(3102,3171,0).distanceTo()<5){//Ariane's initial location
			
			if(!Method.startQuestOpen())
			if(!Method.findOption(opt))
			if(!Method.isChatting("People")){
				Method.speakTo(16160, "Ariane");
			}
		}else if(Vars.DYNAMICV){
			Method.walking(pathToArianeWizardTower, "Walking to Ariane", false);
		}else if(Vars.DRAYNORLODE.distanceTo()<10 || Vars.LUMMBRIDGELODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else if(Method.DraynorLodeIsActive()){
			Method.teleportTo(Vars.DRAYNORTELEPORT);
		}else Method.teleportTo(Vars.LUMBRIDGETELEPORT);
		
	}



	private void CS10() {
		
		if(Settings.get(3294)==137130902 || Settings.get(3294)==141325208||Settings.get(3294)==141325206 || Settings.get(3294)==141325207){//We must distract the wizards.
			
			if(new Tile(3103,3141,0).distanceTo()<5 && new Tile(3103,3141,0).canReach()){
			Quests.DYNAMICV = false;
				if(Widgets.get(193).validate()){
					Widgets.get(193, 34).click(true);
					
				}else if(Quests.tActive()){
					if(Quests.chatting()){
						Quests.clickContinue();
					}
				}else Quests.interactO(79497, "Play");
				
			}else if(Quests.tActive()){
				if(Quests.chatting()){
				Quests.clickContinue();
				}else Players.getLocal().getLocation().click(true);
			}else if(  new Tile(3101,3158,0).distanceTo()<14 && new Tile(3101,3158,0).canReach()){
				Quests.walkingReset();
				Task.sleep(2700,2400);
				if(!Widgets.get(1184).validate())
				Quests.findPath(new Tile(3103,3141,0));
			}else if(Quests.DYNAMICV){
				Quests.walking(Paths.pathToWizard, "Walking to the Wizards Tower",false);
				//Quests.walkToNoDelay(Paths.pathToWizard, "Walking to the Wizard Tower");
			}else if(Quests.LUMMBRIDGELODE.distanceTo()<5){
				Quests.DYNAMICV = true;
			}else Method.teleportTo(47);
			
		}else//Just finished the game and must speak with Ariane again.
		if(Quests.tActive()){
		if(Quests.chatting()){
			Quests.clickContinue();
		}else if(Quests.findOption("do that now.")){
			Quests.clickOption(1188, Quests.value);
		}else if(Quests.findOption("Never mind the history")){
			Quests.clickOption(1188, Quests.value);
		}else if(Quests.findOption("Okay, so what")){
			Quests.clickOption(1188, Quests.value);
		}
		}else Quests.speakTo(16167);
	}



	private void CS9() {

		NPC greyOrb = NPCs.getNearest(16157);
		SceneObject vortex = SceneEntities.getNearest(79907);
		if(Camera.getPitch()<80)
			Camera.setPitch(80);
		if(greyOrb!=null && vortex!=null){
			double dist = greyOrb.getLocation().distance(vortex.getLocation());
			if(dist<2){
				Quests.DYNAMICV = false;
				Quests.state("Within acceptable range.");
			}else dragOrb();
		}else if(Quests.tActive()){
			if(Quests.chatting()){
				Quests.clickContinue();
			}
		}else Quests.speakTo(16167);
	}

	private void dragOrb() {
		Quests.state("Attempting");
		NPC greyOrb = NPCs.getNearest(16157);
		SceneObject vortex = SceneEntities.getNearest(79907);
		int x = greyOrb.getLocation().getX() - vortex.getLocation().getX();
		int y = greyOrb.getLocation().getY() - vortex.getLocation().getY();
		distanceMarker = new Tile(Players.getLocal().getLocation().getX() - x, Players.getLocal().getLocation().getY()-y, 0);
		if (greyOrb.getLocation().distanceTo() < 8) {
		
			if(greyOrb.getLocation().distanceTo()<3){
				distanceMarker.click(true);
			}
			
			Quests.state("x " + x + " y " + y);
			//^Cosmetics
			if(!Players.getLocal().isIdle()){
			Quests.npcInteract(16157, "Attack");//orb
			Task.sleep(2300,2500);
			}
			
			if(distanceMarker.distanceTo()<3 && !Players.getLocal().isMoving()){
			
			}else {
				new Tile(Players.getLocal().getLocation().getX() - x, Players.getLocal().getLocation().getY()-y, 0).clickOnMap();
				Task.sleep(2300,2500);
			}
		}else greyOrb.getLocation().clickOnMap(); 

	}



	private void CS8() {
		if(Settings.get(3294)==137130872){
			SceneObject door = SceneEntities.getNearest(79914);
			SceneObject something = SceneEntities.getNearest(80497);
			
			if(something!=null){
				if (Quests.tActive()) {
					if (Quests.chatting()) {
						Quests.clickContinue();
					} else if (Quests.findOption("I wish I could")) {
						Quests.clickOption(1188, Quests.value);
					}//Click on Ariane.
				}else if(NPCs.getNearest(16167)!=null && NPCs.getNearest(16167).getLocation().distanceTo()<7){
					Quests.speakTo(16167);
				}else NPCs.getNearest(16167).getLocation().clickOnMap();
			}else
			if(door!=null){
				if(door.getLocation().distanceTo()<10){//over the bridge.
					if(door.getLocation().distanceTo()<5){//make sure the door is on the screen.
						Quests.interactO(79914, "Climb");
					}else door.getLocation().clickOnMap();
				}else if(SceneEntities.getNearest(79938).getLocation().distanceTo()<8){//statue
					if(Players.getLocal().isIdle())
					Quests.interactO(79938, "Cross");
				}else SceneEntities.getNearest(79938).getLocation().clickOnMap();//click statue location.
			}else Quests.state("first door cannot be found.");
		}else
		if(SceneEntities.getNearest(79938)!=null){
			if(SceneEntities.getNearest(79938).getLocation().distanceTo()<8){
				if(Quests.tActive()){
					if(Quests.chatting()){
						Quests.clickContinue();
					}else if(Quests.findOption("I'm making us")){
						Quests.clickOption(1188, Quests.value);
					}else if(Quests.findOption("a better idea?")){
						Quests.clickOption(1188, Quests.value);
					}
				}else if (!Quests.tActive())Quests.interactO(79938, "Climb-behind");
			}else SceneEntities.getNearest(79938).getLocation().clickOnMap();
		}else Quests.interactO(80306, "Open");
		
	}



	private void CS7() {
	SceneObject fire = SceneEntities.getNearest(fireID);
	
	
	if(fire!=null){
		if(NPCs.getNearest(16171)!=null){
			if(Settings.get(3294)==137130085){
		if(NPCs.getNearest(16171).getLocation().distanceTo()<6){
			if(Quests.tActive()){
				if(Quests.chatting()){
					Quests.clickContinue();
				}else if(Quests.findOption("I'm ready")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("Cheat as much")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("Pretend to destroy")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("Appeal to the wizard's")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("Constantly challenge him,")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("Go ahead with the ritual")){
					Quests.clickOption(1188, Quests.value);
				}
			}else Quests.speakTo(16171);
			
		}else Quests.findPath(NPCs.getNearest(16171).getLocation());
		}else if(NPCs.getNearest(16165)!=null){
			if(NPCs.getNearest(16165).getLocation().distanceTo()<6){
				if(Quests.tActive()){
					if(Quests.chatting()){
					Quests.clickContinue();	
					}else if(Quests.findOption("Is there anything valuable")){
						Quests.clickOption(1188,Quests.value);
					}
				}else Quests.speakTo(16165);
			}else Quests.findPath(NPCs.getNearest(16165).getLocation());
		}else Quests.state("Cannot find Ariane");
		}else Quests.state("Cannot find rune guardian");
		
	}else if (new Tile(3133,3168,0).distanceTo()<9){//Outside the old tower.
		Quests.interactO(79903, "Open");
	}else if(Quests.DYNAMICV){
		Quests.walking(Paths.pathToRuins, "Walking to the ruins.",false);
	}else if(Quests.LUMMBRIDGELODE.distanceTo()<6){
		Quests.DYNAMICV = true;
	}else Method.teleportTo(47);//Lummbridge teleport.
		
	}



	private void CS6() {
		if(new Tile(3129,3169,9).distanceTo()<6){
			if(Settings.get(3294)==141325216 || Settings.get(3294)==141456289 || Settings.get(3294)==141521825){
				if(Quests.tActive()){
					if(Quests.chatting()){
						Quests.clickContinue();
					}else if(Quests.findOption("I'm happy to help!")){
						Quests.clickOption(1188, Quests.value);
					}else Quests.clickContinue();
				}else Quests.speakTo(16162);
			}else
			if(Widgets.get(1189).validate()){
				Quests.clickContinue();
			}else
			if(Inventory.getSelectedItem()!=null){
				if (Players.getLocal().isIdle()) {
					Quests.interactO(79903, "Use");
					Quests.state("Sleeping...");
					Task.sleep(2500,2600);
				}
			}else Quests.interactInventory(26113, "Use");
		}else if(Quests.DYNAMICV){
			Quests.walking(Paths.pathToRuins, "Walking to the ruins.",false);
		}else if(Quests.LUMMBRIDGELODE.distanceTo()<5){
			Quests.DYNAMICV=true;
		}else if(new Tile(3103,3141,0).distanceTo()<5){
			new Tile(3103,3150,0).clickOnMap();
		}else Method.teleportTo(47);
	}



	private void CS5() {
		if(new Tile(3103,3141,0).distanceTo()<5 && new Tile(3103,3141,0).canReach()){
			if(Settings.get(3294)==136605766){
				if(Widgets.get(193).validate()){
				
					for(int i= 0; i<=widgetKeys.length;){
						Quests.state(""+(i)+": and "+ inputKeys[i] );
						if(Widgets.get(193, 17).visible()){
							break;
						}else
						if(failsafe(i)){
							Quests.state("Made a mistake, restarting");
							Task.sleep(1200,1300);
							Widgets.get(193).getChild(145).click(true);
						}else
						if(Widgets.get(193).getChild(widgetKeys[i]).getText().contains(inputKeys[i])){
							if(i!=widgetKeys.length)
							i++;
							
						}else if(inputKeys[i]=="B"){
							Mouse.click(324,234,true);
							//Widgets.get(193).getChild(51).click(true);
							Task.sleep(200,300);
						}else if(inputKeys[i]=="A#"){
							Widgets.get(193).getChild(14).click(true);
							Task.sleep(200,300);
						}else if(inputKeys[i]=="G#"){
							Widgets.get(193).getChild(13).click(true);
							Task.sleep(1200,1300);
						}else if(inputKeys[i]=="G"){
							Widgets.get(193).getChild(7).click(true);
							Task.sleep(1200,1300);
						}
					}
					if(Widgets.get(193, 17).visible()){
						Quests.DYNAMICV=false;
						Widgets.get(193).getChild(17).interact("Take");
					}
				}else
				if(Widgets.get(1189).validate()){
					Quests.clickContinue();
				}else Quests.interactO(79497, "Play");
			}else
			if(Quests.tActive()){
				Quests.clickContinue();
			}else Quests.interactO(79497, "Play");
		}else if(new Tile(3101,3158,0).distanceTo()<14 && new Tile(3101,3158,0).canReach()){
			Quests.walkingReset();
			Quests.findPath(new Tile(3103,3141,0));
		}else if(Quests.DYNAMICV){
			Quests.walking(Paths.pathToWizard, "Walking to the Wizards Tower",false);
			//Quests.walkToNoDelay(Paths.pathToWizard, "Walking to the Wizard Tower");
		}else if(Quests.LUMMBRIDGELODE.distanceTo()<5){
			Quests.DYNAMICV = true;
		}else Method.teleportTo(47);
		
	}


	private boolean failsafe(int b) {
	
			if (!Widgets.get(193).getChild(127).getText().toString().equalsIgnoreCase("B") && b>2) {
				return true;
			}
		
		return false;

	}


	private void CS4() {
		if(new Tile(3114,3155,0).distanceTo()<4 &&new Tile(3114,3155,0).canReach()){
			if(Inventory.getItem(26114)!=null || Inventory.getItem(26115)!=null){
				Quests.state("Reading books.");
				Quests.interactInventory(26114, "Read");
				Task.sleep(2300,2400);
				Quests.interactInventory(26115, "Read");
				Task.sleep(2300,2400);
			}else if(Widgets.get(1186).validate()|| Widgets.get(1189).validate()){
				Quests.state("Clicking continue");
				Quests.clickContinue();
			}else
			Quests.interactO(79424, "Search");
		}else if(new Tile(3102,3153,0).distanceTo()<14 && new Tile(3102,3153,0).canReach()){
			Quests.findPath(new Tile(3114,3155,0));
			Quests.walkingReset();
		}else if(Quests.DYNAMICV){
			Quests.walking(Paths.pathToWizard, "Walking to book case",false);
			//Quests.walkToNoDelay(Paths.pathToWizard, "Walking to book case");
		}else if(Quests.LUMMBRIDGELODE.distanceTo()<5){
			Quests.DYNAMICV = true;
		}else Method.teleportTo(47);
		
	}



	private void CS3() {
		if(new Tile(3108,3158,2).canReach()){
			if(Settings.get(3294)==136327208){
				if(new Tile(3094,3159,2).distanceTo()<8){
					if(Quests.tActive()){
						Quests.DYNAMICV = false;
						if(Quests.chatting()){
							Quests.clickContinue();
						}else if(Quests.findOption("Have you seen")){
							Quests.clickOption(1188, Quests.value);
						}else if(Quests.findOption("In the power")){
							Quests.clickOption(1188, Quests.value);
						}else if(Quests.findOption("Where do these")){
							Quests.clickOption(1188, Quests.value);
						}
					}else Quests.speakTo(16179);
				}else new Tile(3094,3159,2).clickOnMap();
			}else
			if(Settings.get(3294)==136323112){
				if(new Tile(3100,3147,2).distanceTo()<5){
					if(Quests.tActive()){
						Quests.DYNAMICV = true;
						if(Quests.chatting()){
							Quests.clickContinue();
						}else if(Quests.findOption("Ariane says")){
							Quests.clickOption(1188, Quests.value);
						}else if(Quests.findOption("What are you")){
							Quests.clickOption(1188, Quests.value);
						}
					}else Quests.speakTo(16184);
				}else new Tile(3100,3147,2).clickOnMap();
			}else
			if(new Tile(3108,3158,2).distanceTo()<6){
			if(Quests.tActive()){
				Quests.DYNAMICV = false;
				if(Quests.chatting()){
					Quests.clickContinue();
				}else if(Quests.findOption("Ariane says")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("How can I get")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("You do know,")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("You mean the rumour")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("What do you mean,")){
					Quests.clickOption(1188, Quests.value);
				}
			}else Quests.speakTo(16181);
			}else new Tile(3108,3158,2).clickOnMap();
			
		}else if(new Tile(3103,3156,1).canReach()){
			Quests.interactO(79771, "Ascend");
			Task.sleep(4200,4300);
		}else if(new Tile(3103,3159,0).distanceTo()<6){
			Quests.walkingReset();
			Quests.interactO(79770, "Ascend");
			Task.sleep(4200,4300);
		}else if(Quests.DYNAMICV){
			Quests.walking(Paths.pathToWizard, "Walking to Wizards Tower",false);
			//Quests.walkToNoDelay(Paths.pathToWizard, "Walking to the Wizards Tower");
		}else if(Quests.LUMMBRIDGELODE.distanceTo()<5){
			Quests.DYNAMICV = true;
		}else Method.teleportTo(47);
		
	}

	private void CS2() {
		if(new Tile(3102,3186,0).distanceTo()<8){
			if(Quests.tActive()){
				Quests.walkingReset();
				if(Quests.chatting()){
					Quests.clickContinue();
				}else if(Quests.findOption("What do you")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("I'll get on")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("I've spoken to some")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("Wizard Traiborn said")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("Then let's")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("I'll get right")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("I've got the key to the")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("We should find out")){
					Quests.clickOption(1188, Quests.value);
				}
			}else Quests.speakTo(16161);//speak to Ariane
		}else if(Quests.DYNAMICV){
			if(ariane2Area.contains(Players.getLocal().getLocation())){
				Quests.state("Walking to final destination");
				Walking.findPath(new Tile(3103,3187,0)).traverse();
			}else
			Quests.walking(Paths.pathToWizard, "Walking to Ariane",false);
		}else if(Quests.LUMMBRIDGELODE.distanceTo()<5){
			Quests.DYNAMICV  =true;
		}else if(new Tile(3103,3141,0).distanceTo()<5){
			new Tile(3103,3150,0).clickOnMap();
		}else Method.teleportTo(47);//teleport to lummbridge.
		
	}

	private void init() {
		//Equipment.getItem(26109)!=null
			if(Equipment.getItem(26109)!=null){
				hasMindSpike = true;
				equipStaff = true;
				init = false;
			}else init = false;
		
		
	}

	private void CS0() {
		if(Settings.get(82)==1018){//cut scenes....
			Quests.clickContinue();
		}else
		if(new Tile(3102,3170,0).distanceTo()<5){//Ariane location.
			Quests.walkingReset();
			if(Quests.startQuestOpen()){
				Quests.clickAcceptQuest();
			}else
			if(Quests.tActive()){
				Quests.DYNAMICV = true;
				if(Quests.chatting()){
					Quests.clickContinue();
				}else if(Quests.findOption("What's happening")){
					Quests.clickOption(1188,Quests.value);
				}else if(Quests.findOption("What can")){
					Quests.clickOption(1188,Quests.value);
				}
			}else Quests.speakTo(16160);
		}else if(Quests.DYNAMICV){
			if(arianeArea.contains(Players.getLocal().getLocation())){//Walk closer to Ariane if in area.
				Walking.findPath(new Tile(3102,3171,0)).traverse();
			}else
			Quests.walking(Paths.pathToWizard, "Walking to Ariane",false);
		}else if(Quests.LUMMBRIDGELODE.distanceTo()<5){
			Quests.DYNAMICV = true;
		}else Method.teleportTo(47);
		
	}

	public boolean activate() {
		return (DeltaQuester.scriptToStart == 6);
	}
	
	public void onRepaint(Graphics g) {
		if (DeltaQuester.scriptToStart==6){
	 Quests.t.draw(g);
		}
		
	}

	
}
*/