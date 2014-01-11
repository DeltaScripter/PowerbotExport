package quests;

import java.awt.Graphics;

import lodestoneActivator.Data.TeleportLode;
import lodestoneActivator.Data.TeleportType;

import org.powerbot.script.methods.Hud.Window;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.Tile;

public class RuneMysteries extends Node{

	public RuneMysteries(MethodContext ctx) {
		super(ctx);
	}

	public boolean activate() {
		return (DeltaQuester.scriptToStart == 6);
	}
	
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
	Method Method = new Method(ctx);
	Vars Vars = new Vars();
	
	public int bankItems[] = {26113,26114,26115,26109};
	public void execute() {
		Method.setGeneralCamera();//get the camera pitch for general use on quests
		if(ctx.camera.getPitch()>60)
			ctx.camera.setPitch(50);
			else if(ctx.camera.getPitch()<40)
				ctx.camera.setPitch(50);
		
		DeltaQuester.numSteps = 20;
		if(init){
			if(Method.EquipmentContains(26109)){//staff
				hasMindSpike = true;
				equipStaff = true;
				staffEquip = true;
				init = false;
			}else init = false;
		}else
		if((ctx.settings.get(3294) & 0x3FF) ==938){
			DeltaQuester.progress = 20;
			DeltaQuester.state = "The Rune Mysteries quest has been completed.";
			ctx.game.sleep(2000);
			DeltaQuester.e = true;
		}else
		if((ctx.settings.get(3294) & 0x3FF) ==928||(ctx.settings.get(3294) & 0x3FF) ==929){
			DeltaQuester.progress = 19;
			CS6();//Speak to Ariane one last time to finish the quest.
		}else
		if((ctx.settings.get(3294) & 0x3FF) ==919 || (ctx.settings.get(3294) & 0x3FF) ==920){
			DeltaQuester.progress = 18;
			CS10();//Play the organ once 
		}else
		if((ctx.settings.get(3294) & 0x3FF) ==918||(ctx.settings.get(3294) & 0x3FF) ==908){
			DeltaQuester.progress = 17;
			CS10();//Return to the modern tower and annoy the wizards and speak to a wizard(not intentional)
		}else
		if((ctx.settings.get(3294) & 0x3FF) ==898){
			DeltaQuester.progress = 16;
			CS9();//Play the orb game in the secret tower.
		}else
		if((ctx.settings.get(3294) & 0x3FF) ==888){
			DeltaQuester.progress = 15;
			CS8();//Cross the fallen statue and enter the room at which you speak to Ariane again.
		}else
		if((ctx.settings.get(3294) & 0x3FF) ==878){
			DeltaQuester.progress = 14;
			CS8();//Enter the door after golem and push down the statue.
		}else
		if((ctx.settings.get(3294) & 0x7F) ==101){
			DeltaQuester.progress = 13;
			CS7();//Speak to the golem and answer its questions.
		}else
		if((ctx.settings.get(3294) & 0x7F) ==100){
			DeltaQuester.progress = 12;
			CS7();//Speak to Ariane initially upon entering the tower.
		}else
		if((ctx.settings.get(3294) & 0x7F) ==90){
			DeltaQuester.progress = 11;
			CS6();//Walk to and enter the secret-old tower.
		}else
		if((ctx.settings.get(3294) & 0x7F) ==80){
			DeltaQuester.progress = 10;
			CS2();//Speak to Ariane again with the newly found key.
		}else
		if((ctx.settings.get(3294) & 0x7F) ==70){
			DeltaQuester.progress = 9;
			CS5();//Walk to organ and solve puzzle
		}else
		if((ctx.settings.get(3294) & 0x3F) ==60){
			DeltaQuester.progress = 8;
			CS4();//Search for the book Ellaron mentioned
		}else
		if((ctx.settings.get(3294) & 0x7FFF) ==28722){
			DeltaQuester.progress = 7;
			CS3();//Speak to the wizard Ellaron
		}else
		if((ctx.settings.get(3294) & 0x7FFF) ==28717){
			DeltaQuester.progress = 6;
			CS2();//Speak to Ariane regardin what to do next.
		}else
		if((ctx.settings.get(3294) & 0x7FFF) ==28712){
			DeltaQuester.progress = 5;
			CS2();//Speak to Ariane regarding the issues.
		}else
		if((ctx.settings.get(3294) & 0x3F) ==40){
			DeltaQuester.progress = 4;
			CS3();//Speak to the wizards in the tower
		}else
		if((ctx.settings.get(3294) & 0x1F) ==30){
			DeltaQuester.progress = 3;
			Method.state("Here up to");
			//CS2();//Speak to Ariane the wizards tower for the first time.
		}else
		if((ctx.settings.get(3294) & 0xF) ==10){
			DeltaQuester.progress = 2;
			cs1();//Pick-up staff and complete the first puzzle(pull the orb in the circle)
		}else
		if((ctx.settings.get(3294) & 0x1) ==0){
			DeltaQuester.progress = 1;
			System.out.println("Here");
			cs0();//Start the quest by speaking to Ariane
		}
	}
	

/*

	private void setAutoCast() {
		if(ctx.hud.view(Window.MAGIC_ABILITIES)){
			
			if((ctx.settings.get(682)&0x7)==4){
				if(ctx.widgets.get(275,19).getChild(0).isVisible()){
					ctx.widgets.get(275,19).getChild(0).interact("Auto");
				}else ctx.widgets.get(275,37).click(true);
			}else ctx.widgets.get(275,41).click(true);
			
		}else Tabs.ABILITY_BOOK.open();
		
	}*/



	private void cslolcats() {//Speaks to Ariane in the Wizards Tower
	/*	final String opt[] = {"What can I do","What's happening here"};
		
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
		*/
	}



	private void CS10() {
		/*
		if(ctx.settings.get(3294)==137130902 || ctx.settings.get(3294)==141325208||ctx.settings.get(3294)==141325206 || ctx.settings.get(3294)==141325207){//We must distract the wizards.
			
			if(new Tile(3103,3141,0).distanceTo()<5 && new Tile(3103,3141,0).canReach()){
			Vars.DYNAMICV = false;
				if(ctx.widgets.get(193).validate()){
					ctx.widgets.get(193, 34).click(true);
					
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
				if(!ctx.widgets.get(1184).validate())
				Quests.findPath(new Tile(3103,3141,0));
			}else if(Vars.DYNAMICV){
				Quests.walking(Paths.pathToWizard, "Walking to the Wizards Tower",false);
				//Quests.walkToNoDelay(Paths.pathToWizard, "Walking to the Wizard Tower");
			}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(local)<10){
				Vars.DYNAMICV = true;
			}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport());
			
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
		}else Quests.speakTo(16167);*/
	}



	private void CS9() {
/*
		NPC greyOrb = NPCs.getNearest(16157);
		SceneObject vortex = SceneEntities.getNearest(79907);
		if(Camera.getPitch()<80)
			Camera.setPitch(80);
		if(greyOrb!=null && vortex!=null){
			double dist = greyOrb.getLocation().distance(vortex.getLocation());
			if(dist<2){
				Vars.DYNAMICV = false;
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
*/
	}



	private void CS8() {/*
		if(ctx.settings.get(3294)==137130872){
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
		*/
	}



	private void CS7() {/*
	SceneObject fire = SceneEntities.getNearest(fireID);
	
	
	if(fire!=null){
		if(NPCs.getNearest(16171)!=null){
			if(ctx.settings.get(3294)==137130085){
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
	}else if(Vars.DYNAMICV){
		Quests.walking(Paths.pathToRuins, "Walking to the ruins.",false);
	}else if(Quests.LUMMBRIDGELODE.distanceTo()<6){
		Vars.DYNAMICV = true;
	}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport());//Lummbridge teleport.
		*/
	}



	private void CS6() {/*
		if(new Tile(3129,3169,9).distanceTo()<6){
			if(ctx.settings.get(3294)==141325216 || ctx.settings.get(3294)==141456289 || ctx.settings.get(3294)==141521825){
				if(Quests.tActive()){
					if(Quests.chatting()){
						Quests.clickContinue();
					}else if(Quests.findOption("I'm happy to help!")){
						Quests.clickOption(1188, Quests.value);
					}else Quests.clickContinue();
				}else Quests.speakTo(16162);
			}else
			if(ctx.widgets.get(1189).validate()){
				Quests.clickContinue();
			}else
			if(Inventory.getSelectedItem()!=null){
				if (Players.getLocal().isIdle()) {
					Quests.interactO(79903, "Use");
					Quests.state("Sleeping...");
					Task.sleep(2500,2600);
				}
			}else Quests.interactInventory(26113, "Use");
		}else if(Vars.DYNAMICV){
			Quests.walking(Paths.pathToRuins, "Walking to the ruins.",false);
		}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(local)<10){
			Vars.DYNAMICV=true;
		}else if(new Tile(3103,3141,0).distanceTo()<5){
			new Tile(3103,3150,0).clickOnMap();
		}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport());*/
	}



	private void CS5() {/*
		if(new Tile(3103,3141,0).distanceTo()<5 && new Tile(3103,3141,0).canReach()){
			if(ctx.settings.get(3294)==136605766){
				if(ctx.widgets.get(193).validate()){
				
					for(int i= 0; i<=widgetKeys.length;){
						Quests.state(""+(i)+": and "+ inputKeys[i] );
						if(ctx.widgets.get(193, 17).visible()){
							break;
						}else
						if(failsafe(i)){
							Quests.state("Made a mistake, restarting");
							Task.sleep(1200,1300);
							ctx.widgets.get(193).getChild(145).click(true);
						}else
						if(ctx.widgets.get(193).getChild(widgetKeys[i]).getText().contains(inputKeys[i])){
							if(i!=widgetKeys.length)
							i++;
							
						}else if(inputKeys[i]=="B"){
							Mouse.click(324,234,true);
							//ctx.widgets.get(193).getChild(51).click(true);
							Task.sleep(200,300);
						}else if(inputKeys[i]=="A#"){
							ctx.widgets.get(193).getChild(14).click(true);
							Task.sleep(200,300);
						}else if(inputKeys[i]=="G#"){
							ctx.widgets.get(193).getChild(13).click(true);
							Task.sleep(1200,1300);
						}else if(inputKeys[i]=="G"){
							ctx.widgets.get(193).getChild(7).click(true);
							Task.sleep(1200,1300);
						}
					}
					if(ctx.widgets.get(193, 17).visible()){
						Vars.DYNAMICV=false;
						ctx.widgets.get(193).getChild(17).interact("Take");
					}
				}else
				if(ctx.widgets.get(1189).validate()){
					Quests.clickContinue();
				}else Quests.interactO(79497, "Play");
			}else
			if(Quests.tActive()){
				Quests.clickContinue();
			}else Quests.interactO(79497, "Play");
		}else if(new Tile(3101,3158,0).distanceTo()<14 && new Tile(3101,3158,0).canReach()){
			Quests.walkingReset();
			Quests.findPath(new Tile(3103,3141,0));
		}else if(Vars.DYNAMICV){
			Quests.walking(Paths.pathToWizard, "Walking to the Wizards Tower",false);
			//Quests.walkToNoDelay(Paths.pathToWizard, "Walking to the Wizard Tower");
		}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(local)<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport());
		*/
	}

/*
	private boolean failsafe(int b) {
	
			if (!ctx.widgets.get(193).getChild(127).getText().toString().equalsIgnoreCase("B") && b>2) {
				return true;
			}
		
		return false;

	}
*/

	private void CS4() {/*
		if(new Tile(3114,3155,0).distanceTo()<4 &&new Tile(3114,3155,0).canReach()){
			if(Inventory.getItem(26114)!=null || Inventory.getItem(26115)!=null){
				Quests.state("Reading books.");
				Quests.interactInventory(26114, "Read");
				Task.sleep(2300,2400);
				Quests.interactInventory(26115, "Read");
				Task.sleep(2300,2400);
			}else if(ctx.widgets.get(1186).validate()|| ctx.widgets.get(1189).validate()){
				Quests.state("Clicking continue");
				Quests.clickContinue();
			}else
			Quests.interactO(79424, "Search");
		}else if(new Tile(3102,3153,0).distanceTo()<14 && new Tile(3102,3153,0).canReach()){
			Quests.findPath(new Tile(3114,3155,0));
			Quests.walkingReset();
		}else if(Vars.DYNAMICV){
			Quests.walking(Paths.pathToWizard, "Walking to book case",false);
			//Quests.walkToNoDelay(Paths.pathToWizard, "Walking to book case");
		}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(local)<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport());
		*/
	}



	private void CS3() {/*
		if(new Tile(3108,3158,2).canReach()){
			if(ctx.settings.get(3294)==136327208){
				if(new Tile(3094,3159,2).distanceTo()<8){
					if(Quests.tActive()){
						Vars.DYNAMICV = false;
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
			if(ctx.settings.get(3294)==136323112){
				if(new Tile(3100,3147,2).distanceTo()<5){
					if(Quests.tActive()){
						Vars.DYNAMICV = true;
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
				Vars.DYNAMICV = false;
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
		}else if(Vars.DYNAMICV){
			Quests.walking(Paths.pathToWizard, "Walking to Wizards Tower",false);
			//Quests.walkToNoDelay(Paths.pathToWizard, "Walking to the Wizards Tower");
		}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(local)<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport());
		*/
	}

	private void CS2() {
		Tile local  =ctx.players.local().getLocation();
		String opt[] = {"We should find out","I've got the key to the","I'll get right","Then let's","Wizard Traiborn said",
				"I've spoken to some","I'll get on","What do you"};
		/*
		if(new Tile(3102,3186,0).distanceTo(local)<8){
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
		}else if(Vars.DYNAMICV){
			if(ariane2Area.contains(Players.getLocal().getLocation())){
				Quests.state("Walking to final destination");
				Walking.findPath(new Tile(3103,3187,0)).traverse();
			}else
			Quests.walking(Paths.pathToWizard, "Walking to Ariane",false);
		}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(local)<10){
			Vars.DYNAMICV  =true;
		}else if(new Tile(3103,3141,0).distanceTo()<5){
			new Tile(3103,3150,0).clickOnMap();
		}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport());//teleport to lummbridge.
		*/
	}

	public Timer wait = new Timer(1);
	private void cs1() {
		Tile local  =ctx.players.local().getLocation();
		
		while(Method.inventoryContains(26109)){
			Method.interactInventory(26109, "Wield", "Staff");
		}
		
		if((ctx.settings.get(3295)>>23&0x1)==1){
			if((ctx.settings.get(3295)>>24&0x1)==1){//if didn't pick up staff
				if(staffEquip){
				//	if((ctx.settings.get(715)>>6&0x3)==2){
						if(Method.npcIsNotNull(16141)){
							Tile vortex = Method.getNPC(16141).getLocation();
							Vars.DYNAMICV = false;
							if(Method.getInteractingNPC()==null && ctx.players.local().isIdle()){
								 if(!wait.isRunning()){
										System.out.println("Attacking vortex");
										Method.state("Attacking vortex");
										Method.npcInteract(16141, "Attack");
										wait = new Timer(1500);
									}else System.out.println("Players busy");
							}else
							if(vortex.distanceTo(local)<3){
								System.out.println("Adjustin g pos");
							  Method.state("Adjusting position; " +vortex.distanceTo(local));
							new Tile(local.getX()+3,local.getY()-3,0).getMatrix(ctx).click();
							ctx.game.sleep(1200);
							}else System.out.println("Waiting on orb..");
						}
						
					//}else setAutoCast();
				}else if(Method.EquipmentContains(26109)){//staff
					staffEquip = true;
				}
			}else if(Method.objIsNotNull(79901)){//staff
				if (Method.getObject(79901).getLocation().distanceTo(local) < 6) {// Gather mind spike
					Method.interactO(79901, "Take", "Mindspike");
				} else Method.clickOnMap(Method.getObject(79901).getLocation());//staff loc
			}
			
		}else cs0();//Speak to Ariane
		
	}
	private void cs0() {
		Tile local  =ctx.players.local().getLocation();
		String opt[] = {"What's happening","What can",
				"We should find out","I've got the key to the","I'll get right","Then let's","Wizard Traiborn said",
				"I've spoken to some","I'll get on","What do you"};
		if(ctx.settings.get(82)==1018){//cut scenes....
		Method.pressContinue();
		}else
		if(new Tile(3102,3170,0).distanceTo(local)<6){//Ariane location.
			if(!Method.startQuestOpen())
				if(!Method.findOption(opt)){
					Vars.DYNAMICV = false;
					if(!Method.isChatting("Self")){
						Method.npcInteract(16160, "Talk to");
					}
				}
		}else if(Vars.DYNAMICV){
			if(new Tile(3102,3171,0).distanceTo(local)<20){//Walk closer to Ariane if in area.
				ctx.movement.findPath(new Tile(3102,3171,0)).traverse();
			}else Method.walking(pathToArianeWizardTower, "Walking to Ariane",false);
		}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(local)<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),"Lumbridge");
		
	}
		
}
