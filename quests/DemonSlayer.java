package quests;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Player;
import org.powerbot.script.wrappers.Tile;

import quests.Vars.TeleportLode;
import quests.Vars.TeleportType;



public class DemonSlayer extends Node {

	public DemonSlayer(MethodContext ctx) {
		super(ctx);
	}


	final Tile[] pathToBach = new Tile[] {// new Tile(3212, 3376, 0),
			new Tile(3211, 3381, 0), new Tile(3210, 3386, 0),
			new Tile(3210, 3391, 0), new Tile(3210, 3396, 0),
			new Tile(3210, 3401, 0), new Tile(3210, 3406, 0),
			new Tile(3211, 3411, 0), new Tile(3211, 3416, 0),
			new Tile(3212, 3421, 0), new Tile(3213, 3426, 0),
			new Tile(3211, 3431, 0), new Tile(3211, 3436, 0),
			new Tile(3211, 3441, 0), new Tile(3215, 3445, 0),
			new Tile(3219, 3448, 0), new Tile(3220, 3453, 0),
			new Tile(3223, 3457, 0), new Tile(3227, 3461, 0),
			new Tile(3231, 3464, 0), new Tile(3236, 3466, 0),
			new Tile(3241, 3466, 0), new Tile(3245, 3469, 0),
			new Tile(3248, 3473, 0), new Tile(3249, 3479, 0) };
	
	final Tile[] pathToTemple = new Tile[] { 
			new Tile(3212, 3373, 0), new Tile(3211, 3378, 0), new Tile(3211, 3383, 0), 
			new Tile(3211, 3388, 0), new Tile(3215, 3391, 0), new Tile(3220, 3391, 0), 
			new Tile(3225, 3391, 0), new Tile(3230, 3391, 0), new Tile(3235, 3390, 0), 
			new Tile(3240, 3390, 0), new Tile(3245, 3388, 0), new Tile(3250, 3388, 0),
			new Tile(3253,3388,0)};
	
	final Area TrapDoor = new Area(new Tile[] {
			new Tile(3254, 3487, 0), new Tile(3254, 3483, 0), new Tile(3260, 3483, 0), 
			new Tile(3260, 3488, 0) });
	
	final Area TempleDoor = new Area(new Tile[] {
			new Tile(3252, 3389, 0), new Tile(3253, 3386, 0), new Tile(3257, 3386, 0), 
			new Tile(3257, 3391, 0) });
	
	public Tile init;
	public boolean inits = false;
	public boolean spokeToFaith = false;
	public boolean spokeToBody = false;
	private boolean silverEquip = false;//for rquipping the silver light sword
	public int[] tileArray = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

	public int bankItems[] = {2402};
	public int bankItemAmount[] = {1};
	
	Method Method = new Method(ctx);	
	Vars Vars = new Vars();
	boolean q = true;
	public boolean activate() {
		return DeltaQuester.scriptToStart==26;
	}

	
	public void execute() {
		Method.setGeneralCamera();//get the camera pitch for general use on quests
		if(q){
			TaskListing.taskRemove.clear();
			TaskListing.taskListData.add("Start the quest");
			TaskListing.taskListData.add("Speak to the ghosts downstairs");
			TaskListing.taskListData.add("Complete walking puzzle");
			TaskListing.taskListData.add("Defeat the skeletons");
			TaskListing.taskListData.add("Complete the mind test");
			TaskListing.taskListData.add("Speak to the ghosts and recieve the sword");
			TaskListing.taskListData.add("Inform Grufeld Bach of your success");
			TaskListing.taskListData.add("Defeat the supporters of the demon");
			TaskListing.taskListData.add("Defeat the demon");
			TaskListing.taskListData.add("Complete the quest");
			TaskListing.updateTasks();
			q = false;
		}
		Method.foodSupport();
		
		DeltaQuester.numSteps = 14;
		
		if(DeltaQuester.checkedBank)
			Method.determineBank(bankItems);
			if(!DeltaQuester.checkedBank&& (ctx.settings.get(3518) & 0x7F)!=121){
			Method.checkBank();
		}else
	    if(Vars.useBank && (ctx.settings.get(3518) & 0x7F)!=121){
			Method.useBank(bankItems, bankItemAmount);
		}else
		if(!inits){
			if(!Method.VarrokLodeIsActive()){
				Method.state("Varrok lodestone must be active, skipping quest.");
				ctx.environment.sleep(2000);
				DeltaQuester.e  =true;
			}else inits = true;
		}else 
		if((ctx.settings.get(3518) & 0x7F)==121){
			DeltaQuester.progress=14;
			Method.state("The Demon Slayer quest has been completed.");
			updateTaskRemove("Start the quest","Speak to the ghosts downstairs","Complete walking puzzle","Defeat the skeletons","Complete the mind test","Speak to the ghosts and recieve the sword","Inform Grufeld Bach of your success","Defeat the supporters of the demon","Defeat the demon","Complete the quest");
			TaskListing.removeTasks(TaskListing.taskRemove);
			
			ctx.environment.sleep(2000);
			DeltaQuester.e = true;
		}else
		if((ctx.settings.get(3518) & 0x7F)==120){
			DeltaQuester.progress=13;
			cs0();//Complete the quest
			updateTaskRemove("Start the quest","Speak to the ghosts downstairs","Complete walking puzzle","Defeat the skeletons","Complete the mind test","Speak to the ghosts and recieve the sword","Inform Grufeld Bach of your success","Defeat the supporters of the demon","Defeat the demon");
			TaskListing.removeTasks(TaskListing.taskRemove);
			
		}else
		if((ctx.settings.get(3518) & 0x3FF)==1015){
			DeltaQuester.progress=12;
			cs9();//Fight delrith
			updateTaskRemove("Start the quest","Speak to the ghosts downstairs","Complete walking puzzle","Defeat the skeletons","Complete the mind test","Speak to the ghosts and recieve the sword","Inform Grufeld Bach of your success","Defeat the supporters of the demon");
			TaskListing.removeTasks(TaskListing.taskRemove);
			
		}else
		if((ctx.settings.get(3518) & 0x1FF)==503){
			DeltaQuester.progress=11;
			cs8();//Fight wave 3 of enemies
		}else
		if((ctx.settings.get(3518) & 0xFF)==247){
			DeltaQuester.progress=10;
			cs7();//Fight wave 2 of enemies
		}else
		if((ctx.settings.get(3518) & 0x7F)==119){
			DeltaQuester.progress=9;
			cs6();//Fight wave 1 of enemies
			updateTaskRemove("Start the quest","Speak to the ghosts downstairs","Complete walking puzzle","Defeat the skeletons","Complete the mind test","Speak to the ghosts and recieve the sword","Inform Grufeld Bach of your success");
			TaskListing.removeTasks(TaskListing.taskRemove);
			
		}else
		if((ctx.settings.get(3518) & 0x7F)==118){
			DeltaQuester.progress=9;
			cs0();//Inform Bach you have the silverlight sword
			updateTaskRemove("Start the quest","Speak to the ghosts downstairs","Complete walking puzzle","Defeat the skeletons","Complete the mind test","Speak to the ghosts and recieve the sword");
			TaskListing.removeTasks(TaskListing.taskRemove);
		
		}else
		if((ctx.settings.get(3518) & 0x7F)==117){
			DeltaQuester.progress=8;
			System.out.println("Here");
			cs5();//Take the sword from the pedestal
		
		}else
		if((ctx.settings.get(3518) & 0x7F)==116){
			DeltaQuester.progress=7;
			System.out.println("Here2");
			cs1();//Speak to the ghosts after the trials; to release the sword.
			updateTaskRemove("Start the quest","Speak to the ghosts downstairs","Complete walking puzzle","Defeat the skeletons","Complete the mind test");
			TaskListing.removeTasks(TaskListing.taskRemove);
		
		}else
		if((ctx.settings.get(3518) & 0x7F)==83){
			DeltaQuester.progress=6;
			System.out.println("Here3");
			cs4();//Speak to the ghost of mind and take the test.
			updateTaskRemove("Start the quest","Speak to the ghosts downstairs","Complete walking puzzle","Defeat the skeletons");
			TaskListing.removeTasks(TaskListing.taskRemove);
		
		}else
		if((ctx.settings.get(3518) & 0x7F)==67){
			DeltaQuester.progress=5;
			System.out.println("Here4");
			cs3();//Speak to the ghost of body and then defeat the skeletons
			updateTaskRemove("Start the quest","Speak to the ghosts downstairs","Complete walking puzzle");
			TaskListing.removeTasks(TaskListing.taskRemove);
		
		}else
		if((ctx.settings.get(3518) & 0x3)==3){
			DeltaQuester.progress=4;
			System.out.println("Here5");
			cs2();//Speak to the ghost then complete the walking puzzle.
			updateTaskRemove("Start the quest","Speak to the ghosts downstairs");
			TaskListing.removeTasks(TaskListing.taskRemove);
		
		}else
		if((ctx.settings.get(3518) & 0x3)==2){
			DeltaQuester.progress=3;
			System.out.println("Here6");
			cs1();//Speak to the ghosts downstairs.
		}else
		if((ctx.settings.get(3518) & 1)==1){
			DeltaQuester.progress=2;
			cs0();//Speak to bach initially
			updateTaskRemove("Start the quest");
			TaskListing.removeTasks(TaskListing.taskRemove);
		}else
		if((ctx.settings.get(3518) & 1)==0){
			DeltaQuester.progress=1;
			cs0();//Start the quest
		}
		
	}

	private void updateTaskRemove(String... tasks) {
		for(String t: tasks){
			if(!TaskListing.taskRemove.contains(t)){
				TaskListing.taskRemove.add(t);
			}
		}
		
	}
	private void cs9() {
		Player local = ctx.players.local();
		//SceneObject door = SceneEntities.getNearest(24381);
		if(init!=null){
			if(!Method.isChatting("People"))
			if((ctx.settings.get(82)&0x3FF)==1018){
				if(!Method.isChatting("Cutscene")){//16724
					
					if(local.getAppearance().equals(18019)){
						Method.npcInteract(16724, "Attack");
					}else if(!Method.isInCombat()){
						Method.npcInteract(16724, "Attack");
					}else Method.fightNPC(16724);
					
				}
			}else if(silverEquip){
				if(!Method.npcIsNotNull(16722) || !Method.npcIsNotNull(16723)){//Denath, not the demon
				Method.clickOnMap(new Tile(init.getX()+31,init.getY()-30,0));
				ctx.game.sleep(5000);
				}
			}else if(!Method.EquipmentContains(2402)){
				Method.interactInventory(2402, "Wield", "Silverlight");
			}else silverEquip = true;
			
		}else { 
			for(GameObject  o : ctx.objects.select().id(71036).nearest().first()){
			if(o.getLocation().distanceTo(local.getLocation())<20){
				ctx.environment.sleep(2000);
				init = ctx.players.local().getLocation();
			}
		}
		if(new Tile(3256,3387,0).distanceTo(local.getLocation())<4){
			if(!Method.objIsByTile(new Tile(3255,3388,0), 24381, 3)){
				Method.interactO(82061, "Climb", "Trapdoor");
			}else Method.interactO(24381, "Open", "Door");
			
		}else delta(pathToTemple, "Walking to the temple");
	}
		
	}

	public void delta(Tile[] path, String s) {
		Player local = ctx.players.local();
		
		if(Vars.DYNAMICV){
			Method.walking(path, s, false);
		}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.getLocation())<5 || TeleportLode.VARROCK.getTile().distanceTo(local.getLocation())<5){
			Vars.DYNAMICV = true;
		}else if(Method.VarrokLodeIsActive()){
			Method.teleportTo(TeleportType.VARROCK.getTeleport(),TeleportType.VARROCK.getName());//Varrok tele
		}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());//Lumbridge tele
	}

	private void cs8() {
		//SceneObject door = SceneEntities.getNearest(24381);
		Player local = ctx.players.local();
		
		if(init!=null){
			  if(new Tile(init.getX()+27,init.getY()-20,0).distanceTo(local.getLocation())<8){
				
				if(local.getAppearance().equals(18019)){
					Vars.DYNAMICV = false;
					Method.npcInteract(16723, "Attack");
				}else Method.fightNPC(16723);
				
			}else Method.clickOnMap(new Tile(init.getX()+27,init.getY()-20,0));
		}else
	{for(GameObject  o : ctx.objects.select().id(74990).nearest().first()){
			if(o.getLocation().distanceTo(local.getLocation())<20){
				ctx.environment.sleep(2000);
				init = ctx.players.local().getLocation();
			}
		}
		if(new Tile(3258,3483,0).distanceTo(local.getLocation())<8){
			for(GameObject  door : ctx.objects.select().id(15536).nearest().first()){
				if(!TrapDoor.contains(door.getLocation())){
					Method.interactO(82059, "Climb", "Trapdoor");
				}else Method.interactO(15536, "Open", "Door");
			}
			if(ctx.objects.select().id(15536).nearest().first().isEmpty())
			Method.interactO(82059, "Climb", "Trapdoor");
		}else if(new Tile(3249,3480,0).distanceTo(local.getLocation())<20){
			Method.clickOnMap(new Tile(3257,3483,0));
			ctx.environment.sleep(1200);
		}else delta(pathToTemple, "Walking to the temple");
	}
		
	}


	private void cs7() {
	//	SceneObject door = SceneEntities.getNearest(24381);
		 Player local = ctx.players.local();
		
		if(init!=null){
			if(new Tile(init.getX()+14,init.getY()-13,0).distanceTo(local.getLocation())<8){
				
				if(local.getAppearance().equals(18019)){
					Method.npcInteract(16723, "Attack");
				}else Method.fightNPC(16723);
				
			}else Method.clickOnMap(new Tile(init.getX()+14,init.getY()-13,0));
		}else { for(GameObject  o : ctx.objects.select().id(74990).nearest().first()){
			if(o.getLocation().distanceTo(local.getLocation())<20){
				ctx.environment.sleep(2000);
				init = ctx.players.local().getLocation();
			}
		}
		if(new Tile(3258,3483,0).distanceTo(local.getLocation())<8){
			for(GameObject  door : ctx.objects.select().id(15536).nearest().first()){
				if(!TrapDoor.contains(door.getLocation())){
					Method.interactO(82059, "Climb", "Trapdoor");
				}else Method.interactO(15536, "Open", "Door");
			}
			if(ctx.objects.select().id(15536).nearest().first().isEmpty())
			Method.interactO(82059, "Climb", "Trapdoor");
		}else if(new Tile(3249,3480,0).distanceTo(local.getLocation())<20){
			Method.clickOnMap(new Tile(3257,3483,0));
			ctx.environment.sleep(1200);
		}else delta(pathToTemple, "Walking to the temple");
		}
	}


	private void cs6() {
		//SceneObject door = SceneEntities.getNearest(24381);
		 Player local = ctx.players.local();
		
		if(init!=null){
			if(new Tile(init.getX(),init.getY()-11,0).distanceTo(local.getLocation())<8){
				
				if(local.getAppearance().equals(18019)){
					Method.npcInteract(16723, "Attack");
				}else Method.fightNPC(16723);
				
			}else Method.clickOnMap(new Tile(init.getX(),init.getY()-11,0));
		}else { 
			for(GameObject  o : ctx.objects.select().id(71036).nearest().first()){
			if(o.getLocation().distanceTo(local.getLocation())<20){
				ctx.environment.sleep(2000);
				init = ctx.players.local().getLocation();
			}
		}
		if(new Tile(3256,3387,0).distanceTo(local.getLocation())<8){
			if(!Method.objIsByTile(new Tile(3255,3388,0), 24381, 3)){
				Method.interactO(82061, "Climb", "Trapdoor");
			}else Method.interactO(24381, "Open", "Door");
			
				
		}else delta(pathToTemple, "Walking to the temple");
	}
		
	}


	private void cs5() {
		 Player local = ctx.players.local();
		
		if(init!=null){
			if(new Tile(init.getX(), init.getY()+19,0).distanceTo(local.getLocation())<8){
				Vars.DYNAMICV = false;
				if(!local.isInMotion()){
					Method.state("Waiting");
					ctx.environment.sleep(200,400);
				}
				if(!Method.isChatting("Self"))
				Method.interactO(82189, "Take", "Silverlight");
			}else {
				if(!local.isInMotion())
				Method.clickOnMap(new Tile(init.getX(), init.getY()+19,0));
			}
		}else { for(GameObject  o : ctx.objects.select().id(74990).nearest().first()){
			if(o.getLocation().distanceTo(local.getLocation())<20){
				ctx.environment.sleep(2000);
				init = ctx.players.local().getLocation();
			}
		}
		if(new Tile(3258,3483,0).distanceTo(local.getLocation())<8){
			for(GameObject  door : ctx.objects.select().id(15536).nearest().first()){
				if(!TrapDoor.contains(door.getLocation())){
					Method.interactO(82059, "Climb", "Trapdoor");
				}else Method.interactO(15536, "Open", "Door");
			}
			if(ctx.objects.select().id(15536).nearest().first().isEmpty())
			Method.interactO(82059, "Climb", "Trapdoor");
		}else if(new Tile(3249,3480,0).distanceTo(local.getLocation())<20){
			Method.clickOnMap(new Tile(3257,3483,0));
			ctx.environment.sleep(1200);
		}else cs0();//Get into position
		}
		
	}


	private void cs4() {
		final String opt[] = {"Yes", "I seek", "A sword that", "A powerful demon","Delrith.","Bach"};
		 Player local = ctx.players.local();
		
		//SceneObject door = SceneEntities.getNearest(15536);
		if(init!=null){
			if(new Tile(init.getX()+13,init.getY()+24,0).distanceTo(local.getLocation())<7){
				if(!Method.findOption(opt)){
					if(!Method.isChatting("Spirit of the Mind")){
						Method.speakTo(16718, "Spirit of the Mind");
					}
				}
			}else Method.clickOnMap(new Tile(init.getX()+13,init.getY()+24,0));
		}else {
			for(GameObject  o : ctx.objects.select().id(74990).nearest().first()){
			if(o.getLocation().distanceTo(local.getLocation())<20){
				ctx.environment.sleep(2000);
				init = ctx.players.local().getLocation();
			}
		}
		if(new Tile(3258,3483,0).distanceTo(local.getLocation())<8){
			for(GameObject  door : ctx.objects.select().id(15536).nearest().first()){
				if(!TrapDoor.contains(door.getLocation())){
					Method.interactO(82059, "Climb", "Trapdoor");
				}else Method.interactO(15536, "Open", "Door");
			}
			if(ctx.objects.select().id(15536).nearest().first().isEmpty())
			Method.interactO(82059, "Climb", "Trapdoor");
		}else if(new Tile(3249,3480,0).distanceTo(local.getLocation())<20){
			Method.clickOnMap(new Tile(3257,3483,0));
			ctx.environment.sleep(1200);
		}else cs0();//Get into position
	}
		
	}


	private void cs3() {
		//SceneObject door = SceneEntities.getNearest(15536);
		 Player local = ctx.players.local();
		final String[] t = {"Yes"};
		
		if(init!=null){
		if(spokeToBody){
			if(!Method.isInCombat()){
				Method.npcInteract(16721, "Attack");
			}else Method.fightNPC(16721);
		}else if(ctx.widgets.get(1184).isValid() && ctx.widgets.get(1184).getComponent(13).getText().contains("There are") || local.isInCombat()){
			Method.pressContinue();
			spokeToBody = true;
		}else
		if(new Tile(init.getX()-12, init.getY()+23,0).distanceTo(local.getLocation())<6){
			if(!Method.findOption(t)){
				if(!Method.isChatting("Spirit of Body")){
					Method.speakTo(16717, "Spirit of Body");
				}
			}
		}else {
			if(!local.isInMotion())
			Method.clickOnMap(new Tile(init.getX()-12, init.getY()+23,0));
		}
		}else {
		for(GameObject  o : ctx.objects.select().id(74990).nearest().first()){
			if(o.getLocation().distanceTo(local.getLocation())<20){
				ctx.environment.sleep(2000);
				init = ctx.players.local().getLocation();
			}
		}
		if(new Tile(3258,3483,0).distanceTo(local.getLocation())<6){
			for(GameObject  door : ctx.objects.select().id(15536).nearest().first()){
				if(!TrapDoor.contains(door.getLocation())){
					Method.interactO(82059, "Climb", "Trapdoor");
				}else Method.interactO(15536, "Open", "Door");
			}
			if(ctx.objects.select().id(15536).nearest().first().isEmpty())
			Method.interactO(82059, "Climb", "Trapdoor");
		}else if(new Tile(3249,3480,0).distanceTo(local.getLocation())<20){
			Method.clickOnMap(new Tile(3257,3483,0));
			ctx.environment.sleep(1200);
		}else cs0();//Get into position
		}
	}


	private void cs2() {
		final String opt[] = {"Yes"};
		 Player local = ctx.players.local();
		
		//SceneObject door = SceneEntities.getNearest(15536);
		if(init!=null){
			
			if(spokeToFaith){
				if(ctx.camera.getPitch()<40)
				ctx.camera.setPitch(80);
				ctx.camera.setAngle(40);
			 if(local.getAnimation()==2311){
					for(int i = 0; i<=tileArray.length;){
						Method.state("Restarting");
						tileArray[i]=0;
						i++;
						ctx.environment.sleep(500);
					}
				}else
				if(tileArray[0]==1){
					if(tileArray[1]==1){
						if(tileArray[2]==1){
							if(tileArray[3]==1){
								if(tileArray[4]==1){
									if(tileArray[5]==1){
										if(tileArray[6]==1){
											if(tileArray[7]==1){
												if(tileArray[8]==1){
													if(tileArray[9]==1){
														if(tileArray[10]==1){
															if(tileArray[11]==1){
																if(tileArray[12]==1){
																	if(tileArray[13]==1){
																		if(tileArray[14]==1){
																			if(tileArray[15]==1){
																				Method.state("Complete");
																			}else moveToTile(-2,42,15);
																		}else moveToTile(-2,41,14);	
																	}else moveToTile(-2,40,13);
																}else moveToTile(-2,39,12);
															}else moveToTile(-1,39,11);
														}else moveToTile(0,39,10);
													}else moveToTile(0,40,9);
												}else moveToTile(1,40,8);
											}else moveToTile(2,40,7);
										}else moveToTile(2,39,6);
									}else moveToTile(2,38,5);
								}else moveToTile(2,37,4);
							}else moveToTile(1,37,3);
						}else moveToTile(0,37,2);
					}else moveToTile(0,36,1);
				}else moveToTile(0,35,0);
				
			}else if(ctx.widgets.get(1184).isValid() && ctx.widgets.get(1184).getComponent(9).getText().contains("Step out into the")){
				Method.pressContinue();
				spokeToFaith = true;
			}else
			if(new Tile(init.getX(), init.getY()+33,0).distanceTo(local.getLocation())<9){
				if(!Method.findOption(opt)){
					if(!Method.isChatting("Spirit of Faith")){
						Method.speakTo(16719, "Spirit of Faith");
					}
				}
			}else {
				if(!ctx.players.local().isInMotion()){
					Method.clickOnMap(new Tile(init.getX(), init.getY()+33,0));
						ctx.environment.sleep(1200,1300);
				}
			}
		}else {for(GameObject  o : ctx.objects.select().id(74990).nearest().first()){
			if(o.getLocation().distanceTo(local.getLocation())<20){
				ctx.environment.sleep(2000);
				init = ctx.players.local().getLocation();
			}
		}
		if(new Tile(3258,3483,0).distanceTo(local.getLocation())<8){
			for(GameObject  door : ctx.objects.select().id(15536).nearest().first()){
				if(!TrapDoor.contains(door.getLocation())){
					Method.interactO(82059, "Climb", "Trapdoor");
				}else Method.interactO(15536, "Open", "Door");
			}
			if(ctx.objects.select().id(15536).nearest().first().isEmpty())
			Method.interactO(82059, "Climb", "Trapdoor");
		}else if(new Tile(3249,3480,0).distanceTo(local.getLocation())<20){
			Method.clickOnMap(new Tile(3257,3483,0));
			ctx.environment.sleep(1200);
		}else cs0();//Get into position
		}
		
	}


	private void moveToTile(int x, int y, int p) {
		 Player local = ctx.players.local();
		 
		if(new Tile(init.getX()+x,init.getY()+y,0).distanceTo(local.getLocation())==0){
			tileArray[p]=1;
		}else {
			Method.state("Attempting faith traversal");
			ctx.camera.getAngleTo(87);
			new Tile(init.getX()+x,init.getY()+y,0).getMatrix(ctx).click();
			ctx.environment.sleep(1500,1600);
		}
		
	}


	private void cs1() {
		//SceneObject door = SceneEntities.getNearest(15536);
		 Player local = ctx.players.local();
		
		final String opt[] = {"I come seeking"};
		if(init!=null){
			if(new Tile(init.getX(), init.getY()+19,0).distanceTo(local.getLocation())<8){
				if(!Method.findOption(opt)){
					if(!Method.isChatting("Spirit of Faith")){
						Method.speakTo(16719, "Spirit of Faith");
					}
				}
			}else {
				if(!ctx.players.local().isInMotion())
					Method.clickOnMap(new Tile(init.getX(), init.getY()+19,0));
			}
		}else{ for(GameObject  o : ctx.objects.select().id(74990).nearest().first()){
			if(o.getLocation().distanceTo(local.getLocation())<20){
				ctx.environment.sleep(2000);
				init = ctx.players.local().getLocation();
			}
		}
		if(new Tile(3258,3483,0).distanceTo(local.getLocation())<4){
			for(GameObject  door : ctx.objects.select().id(15536).nearest().first()){
				if(!TrapDoor.contains(door.getLocation())){
					Method.interactO(82059, "Climb", "Trapdoor");
				}else Method.interactO(15536, "Open", "Door");
			}
			if(ctx.objects.select().id(15536).nearest().first().isEmpty())
			Method.interactO(82059, "Climb", "Trapdoor");
		}else if(new Tile(3249,3480,0).distanceTo(local.getLocation())<20){
			Method.clickOnMap(new Tile(3257,3483,0));
			ctx.environment.sleep(1200);
			} else
				cs0();// Get into position

		}
	}


	private void cs0() {
		 Player local = ctx.players.local();
		if (new Tile(3249, 3479, 0).distanceTo(local.getLocation()) < 7) {
			if (!Method.startQuestOpen()) {
				Vars.DYNAMICV = false;
				init = null;
				if (!Method.isChatting("Grufeld Bach")) {
					Vars.DYNAMICV = false;
					Method.speakTo(16713, "Grufeld Bach");
				}
			}
		} else if(!Method.isChatting("People"))
			delta(pathToBach, "Walking to Grufeld Bach");
	}
	  
	  
  }
