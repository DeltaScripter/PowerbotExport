package quests;


import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.Player;

import quests.Vars.TeleportLode;
import quests.Vars.TeleportType;



public class DemonSlayer extends DeltaNode {

	public DemonSlayer(ClientContext ctx) {
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
			new Tile(3248, 3473, 0), new Tile(3250,3478,0)};
	
	final Tile[] pathToTemple = new Tile[] { 
			new Tile(3212, 3373, 0), new Tile(3211, 3378, 0), new Tile(3211, 3383, 0), 
			new Tile(3211, 3388, 0), new Tile(3215, 3391, 0), new Tile(3220, 3391, 0), 
			new Tile(3225, 3391, 0), new Tile(3230, 3391, 0), new Tile(3235, 3390, 0), 
			new Tile(3240, 3390, 0), new Tile(3245, 3388, 0), new Tile(3250, 3388, 0),
			new Tile(3253,3388,0)};
	
	final DeltaArea TrapDoor = new DeltaArea(new Tile[] {
			new Tile(3254, 3487, 0), new Tile(3254, 3483, 0), new Tile(3260, 3483, 0), 
			new Tile(3260, 3488, 0) });
	
	final DeltaArea TempleDoor = new DeltaArea(new Tile[] {
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
		
		
		/*For interacting with the bounds of an object, accurate clicking
		final int[] bounds = {76, 376, -440, -60, -156, 108};
		final GameObject gobj = ctx.objects.select().id(24381).each(Interactive.doSetBounds(bounds)).select(Interactive.areInViewport()).nearest().poll();
		if(gobj.interact("Open","Door")){
			System.out.println("Interacting with door");
		}*/
		
		//the interface when we read grufeld's scroll <need to be closed for teleporting later
		while(ctx.widgets.widget(468).component(13).visible()){
			Method.state("Closing the scroll interface");
			ctx.widgets.widget(468).component(13).click();//the close button
		}
		
		Method.setGeneralCamera();//get the camera pitch for general use on quests
		if(q){
			q = false;
		}
		Method.foodSupport();
		
		DeltaQuester.numSteps = 14;
		
		if(DeltaQuester.checkedBank)
			Method.determineBank(bankItems);
		if(!DeltaQuester.checkedBank&& (ctx.varpbits.varpbit(3518) & 0x7F)!=121){
			Method.checkBank();
		}else
	    if(quests.Vars.useBank && (ctx.varpbits.varpbit(3518) & 0x7F)!=121){
			Method.useBank(bankItems, bankItemAmount);
		}else
		if(!inits){
			if(!Method.VarrokLodeIsActive()){
				Method.state("Varrok lodestone must be active, skipping quest.");
			
				DeltaQuester.e = true;
			}else inits = true;
		}else 
		if((ctx.varpbits.varpbit(3518) & 0x7F)==121){
			DeltaQuester.progress=14;
			Method.state("The Demon Slayer quest has been completed.");
			
			DeltaQuester.e = true;
		}else
		if((ctx.varpbits.varpbit(3518) & 0x7F)==120){
			DeltaQuester.progress=13;
			cs0();//Complete the quest
			
		}else
		if((ctx.varpbits.varpbit(3518) & 0x3FF)==1015){
			DeltaQuester.progress=12;
			cs9();//Fight delrith
			
		}else
		if((ctx.varpbits.varpbit(3518) & 0x1FF)==503){
			DeltaQuester.progress=11;
			cs8();//Fight wave 3 of enemies
		}else
		if((ctx.varpbits.varpbit(3518) & 0xFF)==247){
			DeltaQuester.progress=10;
			cs7();//Fight wave 2 of enemies
		}else
		if((ctx.varpbits.varpbit(3518) & 0x7F)==119){
			DeltaQuester.progress=9;
			cs6();//Fight wave 1 of enemies
			
		}else
		if((ctx.varpbits.varpbit(3518) & 0x7F)==118){
			DeltaQuester.progress=9;
			cs0();//Read scroll where Bach used to be... 
		
		}else
		if((ctx.varpbits.varpbit(3518) & 0x7F)==117){
			DeltaQuester.progress=8;
			cs5();//Take the sword from the pedestal
		
		}else
		if((ctx.varpbits.varpbit(3518) & 0x7F)==116){
			DeltaQuester.progress=7;
			cs1();//Speak to the ghosts after the trials; to release the sword.
		
		}else
		if((ctx.varpbits.varpbit(3518) & 0x7F)==83){
			DeltaQuester.progress=6;
			cs4();//Speak to the ghost of mind and take the test.
		
		}else
		if((ctx.varpbits.varpbit(3518) & 0x7F)==67){
			DeltaQuester.progress=5;
			cs3();//Speak to the ghost of body and then defeat the skeletons
		
		}else
		if((ctx.varpbits.varpbit(3518) & 0x3)==3){
			DeltaQuester.progress=4;
			cs2();//Speak to the ghost then complete the walking puzzle.
		
		}else
		if((ctx.varpbits.varpbit(3518) & 0x3)==2){
			DeltaQuester.progress=3;
			cs1();//Speak to the ghosts downstairs.
		}else
		if((ctx.varpbits.varpbit(3518) & 1)==1){
			DeltaQuester.progress=2;
			cs0();//Speak to bach initially
		}else
		if((ctx.varpbits.varpbit(3518) & 1)==0){
			DeltaQuester.progress=1;
			cs0();//Start the quest
		}
		
	}
	private int BACH = 19505;//Bach's ID when in combat
	private void cs9() {//Enter area and fight Delrith the demon
		Player local = ctx.players.local();
		//SceneObject door = SceneEntities.getNearest(24381);
		if(init!=null){
			if(!Method.isChatting("People"))
			if((ctx.varpbits.varpbit(82)&0x3FF)==1018){//once silver light is equip
				if(!Method.isChatting("Cutscene")){//16724
					
					if(Method.npcIsNotNull(BACH))
					if(Method.getNPC(BACH).tile().distanceTo(ctx.players.local().tile())<5){//for staying close to Bach
						//during the fight
					
					if(local.appearance().equals(18019)){
						Method.npcInteract(16724, "Attack");
					}else if(!Method.isInCombat()){
						Method.npcInteract(16724, "Attack");
					}else Method.fightNPC(16724);
					
					}else Method.clickOnMap(Method.getNPC(BACH).tile());
					
				}
			}else if(silverEquip){
				if(!Method.npcIsNotNull(16722) || !Method.npcIsNotNull(16723)){//Denath, not the demon
				Method.clickOnMap(new Tile(init.x()-25,init.y()+30,0));
				Method.sleep(5000);
				}
			}else if(!Method.EquipmentContains(2402)){
				Method.interactInventory(2402, "Wield", "Silverlight");
			}else silverEquip = true;
			
		}else { 
			cs6();//Get back into cave
	}
		
	}

	public void delta(Tile[] path, String s) {
		Player local = ctx.players.local();
		if(Vars.DYNAMICV){
			Method.walking(path, s, false);
		}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.tile())<5 || TeleportLode.VARROCK.getTile().distanceTo(local.tile())<5){
			Vars.DYNAMICV = true;
		}else if(Method.VarrokLodeIsActive()){
			Method.teleportTo(TeleportType.VARROCK.getTeleport(),TeleportType.VARROCK.getName());//Varrok tele
		}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());//Lumbridge tele
	}

	private void cs8() {//Go to last area to fight enemies in that cave
		Player local = ctx.players.local();
		
		if(init!=null){
			  if(new Tile(init.x()-27,init.y()+20,0).distanceTo(local.tile())<8){
				
				if(local.appearance().equals(18019)){
					Vars.DYNAMICV = false;
					Method.npcInteract(16723, "Attack");
				}else Method.fightNPC(16723);
				
			}else Method.clickOnMap(new Tile(init.x()-27,init.y()+20,0));
		}else
	{for(GameObject  o : ctx.objects.select().id(74990).nearest().first()){
			if(o.tile().distanceTo(local.tile())<20){
				Method.sleep(2000);
				init = ctx.players.local().tile();
			}
		}
		cs6();//get back into cave if out(cave w/enemies)
	}
		
	}


	private void cs7() {//fights the second wave of enemies in cave
	//	SceneObject door = SceneEntities.getNearest(24381);
		 Player local = ctx.players.local();
		
		if(init!=null){
			if(new Tile(init.x()-14,init.y()+13,0).distanceTo(local.tile())<8){
				
				if(local.appearance().equals(18019)){
					Method.npcInteract(16723, "Attack");
				}else Method.fightNPC(16723);
				
			}else Method.clickOnMap(new Tile(init.x()-14,init.y()+13,0));
		}else { 
			
			for(GameObject  o : ctx.objects.select().id(74990).nearest().first()){
			if(o.tile().distanceTo(local.tile())<20){
				Method.sleep(2000);
				init = ctx.players.local().tile();
		              	}
		                     }
		       cs6();//get back into cave with enemies
		
		
		}
	}


	private void cs6() {//Fights the first wave of enemies in cave
		//SceneObject door = SceneEntities.getNearest(24381);
		 Player local = ctx.players.local();
		
		if(init!=null){
			//System.out.println("The init tile is: "+ init);
			if(new Tile(init.x(),init.y()+11,0).distanceTo(local.tile())<8){
				
				if(local.appearance().equals(18019)){
					Method.npcInteract(16723, "Attack");
				}else Method.fightNPC(16723);
				
			}else {
				System.out.println("Clicking on map : "+new Tile(init.x(),init.y()+11,0));
				Method.clickOnMap(new Tile(init.x(),init.y()+11,0));
			}
		}else { 
			if(Method.objIsNotNull(71039)){
				Method.sleep(3000);
				init = ctx.players.local().tile();
			
			}
		if(new Tile(3256,3387,0).distanceTo(local.tile())<8){
				Method.interactO(82061, "Climb", "Trapdoor");
			
		}else delta(pathToTemple, "Walking to the temple");
	}
		
	}


	private void cs5() {
		 Player local = ctx.players.local();
		
		if(init!=null){
			if(new Tile(init.x(), init.y()+19,0).distanceTo(local.tile())<8){
				Vars.DYNAMICV = false;
				if(!local.inMotion()){
					Method.state("Waiting");
					Method.sleep(400);
				}
				if(!Method.isChatting("Self"))
				Method.interactO(82189, "Take", "Silverlight");
			}else {
				if(!local.inMotion())
				Method.clickOnMap(new Tile(init.x(), init.y()+19,0));
			}
		}else { for(GameObject  o : ctx.objects.select().id(74990).nearest().first()){
			if(o.tile().distanceTo(local.tile())<20){
				Method.sleep(2000);
				init = ctx.players.local().tile();
			}
		}
		if(new Tile(3258,3483,0).distanceTo(local.tile())<8){
			for(GameObject  door : ctx.objects.select().id(15536).nearest().first()){
				if(!TrapDoor.contains(door.tile())){
					Method.interactO(82059, "Climb", "Trapdoor");
				}else Method.interactO(15536, "Open", "Door");
			}
			if(ctx.objects.select().id(15536).nearest().first().isEmpty())
			Method.interactO(82059, "Climb", "Trapdoor");
		}else if(new Tile(3250,3478,0).distanceTo(local.tile())<20){
			Method.clickOnMap(new Tile(3250,3478,0));
			Method.sleep(1200);
		}else cs0();//Get into position
		}
		
	}


	private void cs4() {//Take spirit of mind test in first cave
		final String opt[] = {"Yes", "I seek", "A sword that", "A powerful demon","Delrith.","Gideon","Bach"};
		 Player local = ctx.players.local();
		
		//SceneObject door = SceneEntities.getNearest(15536);
		if(init!=null){
			if(new Tile(init.x()+13,init.y()+24,0).distanceTo(local.tile())<7){
				if(!Method.findOption(opt)){
					if(!Method.isChatting("Spirit of the Mind")){
						Method.speakTo(16718, "Spirit of the Mind");
					}
				}
			}else Method.clickOnMap(new Tile(init.x()+13,init.y()+24,0));
		}else {
			for(GameObject  o : ctx.objects.select().id(74990).nearest().first()){
			if(o.tile().distanceTo(local.tile())<20){
				Method.sleep(2000);
				init = ctx.players.local().tile();
			}
		}//Climb down the trapdoor near the position of Grufeld Bach
		if(new Tile(3250,3478,0).distanceTo(local.tile())<8){//tile by the trapdoor
					Method.interactO(82059, "Climb", "Trapdoor");
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
		}else if(ctx.widgets.component(1184,1).valid() && ctx.widgets.component(1184,1).component(13).text().contains("There are")
				|| local.inCombat()){
			Method.pressContinue();
			spokeToBody = true;
		}else
		if(new Tile(init.x()-12, init.y()+23,0).distanceTo(local.tile())<6){
			if(!Method.findOption(t)){
				if(!Method.isChatting("Spirit of Body")){
					Method.speakTo(16717, "Spirit of Body");
				}
			}
		}else {
			if(!local.inMotion())
			Method.clickOnMap(new Tile(init.x()-12, init.y()+23,0));
		}
		}else { for(GameObject  o : ctx.objects.select().id(74990).nearest().first()){
			if(o.tile().distanceTo(local.tile())<20){
				Method.sleep(2000);
				init = ctx.players.local().tile();
			}
		}
		if(new Tile(3258,3483,0).distanceTo(local.tile())<8){
			for(GameObject  door : ctx.objects.select().id(15536).nearest().first()){
				if(!TrapDoor.contains(door.tile())){
					Method.interactO(82059, "Climb", "Trapdoor");
				}else Method.interactO(15536, "Open", "Door");
			}
			if(ctx.objects.select().id(15536).nearest().first().isEmpty())
			Method.interactO(82059, "Climb", "Trapdoor");
		}else if(new Tile(3250,3478,0).distanceTo(local.tile())<20){
			Method.clickOnMap(new Tile(3250,3478,0));
			Method.sleep(1200);
		}else cs0();//Get into position
		}
	}


	private void cs2() {
		final String opt[] = {"Yes"};
		 Player local = ctx.players.local();
		
		//SceneObject door = SceneEntities.getNearest(15536);
		if(init!=null){
			
			if(spokeToFaith){
				
				ctx.camera.angle(40);
			 if(local.animation()==2311){
					for(int i = 0; i<=tileArray.length;){
						Method.state("Restarting");
						tileArray[i]=0;
						i++;
						Method.sleep(500);
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
				
			}else if(ctx.widgets.component(1184,1).valid() && ctx.widgets.component(1184,9).text().contains("Step out into the")){
				Method.pressContinue();
				spokeToFaith = true;
			}else
			if(new Tile(init.x(), init.y()+33,0).distanceTo(local.tile())<9){
				if(!Method.findOption(opt)){
					if(!Method.isChatting("Spirit of Faith")){
						Method.speakTo(16719, "Spirit of Faith");
					}
				}
			}else {
				if(!ctx.players.local().inMotion()){
					Method.clickOnMap(new Tile(init.x(), init.y()+33,0));
						Method.sleep(1300);
				}
			}
		}else {
			for(GameObject  o : ctx.objects.select().id(74990).nearest().first()){
			if(o.tile().distanceTo(local.tile())<20){
				Method.sleep(2000);
				init = ctx.players.local().tile();
			}
		}
		if(new Tile(3250,3477,0).distanceTo(local.tile())<8){
		
			Method.interactO(82059, "Climb", "Trapdoor");
			
		}else if(new Tile(3249,3480,0).distanceTo(local.tile())<20){
			Method.clickOnMap(new Tile(3250,3477,0));
			Method.sleep(1200);
		}else cs0();//Get into position
		}
		
	}


	private void moveToTile(int x, int y, int p) {
		 Player local = ctx.players.local();
		 
		if(new Tile(init.x()+x,init.y()+y,0).distanceTo(local.tile())==0){
			tileArray[p]=1;
		}else {
			Method.state("Attempting faith traversal");
			ctx.camera.angleTo(87);
			new Tile(init.x()+x,init.y()+y,0).matrix(ctx).click();
			Method.sleep(1600);
		}
		
	}


	private void cs1() {
		//SceneObject door = SceneEntities.getNearest(15536);
		 Player local = ctx.players.local();
		
		final String opt[] = {"I come seeking"};
		if(init!=null){
			if(new Tile(init.x(), init.y()+19,0).distanceTo(local.tile())<8){
				if(!Method.findOption(opt)){
					if(!Method.isChatting("Spirit of Faith")){
						Method.speakTo(16719, "Spirit of Faith");
					}
				}
			}else {
				if(!ctx.players.local().inMotion())
					Method.clickOnMap(new Tile(init.x(), init.y()+19,0));
			}
		}else{ for(GameObject  o : ctx.objects.select().id(74990).nearest().first()){
			if(o.tile().distanceTo(local.tile())<20){
				Method.sleep(2000);
				init = ctx.players.local().tile();
			}
		}
		if(new Tile(3250,3478,0).distanceTo(local.tile())<4){
			for(GameObject  door : ctx.objects.select().id(15536).nearest().first()){
				if(!TrapDoor.contains(door.tile())){
					Method.interactO(82059, "Climb", "Trapdoor");
				}else Method.interactO(15536, "Open", "Door");
			}
			if(ctx.objects.select().id(15536).nearest().first().isEmpty())
			Method.interactO(82059, "Climb", "Trapdoor");
		}else if(new Tile(3250,3478,0).distanceTo(local.tile())<20){
			Method.clickOnMap(new Tile(3250,3478,0));
			Method.sleep(1200);
			} else
				cs0();// Get into position

		}
	}


	private void cs0() {//Speak to Grufeld Bach first time and last
		 Player local = ctx.players.local();
		if (new Tile(3250, 3482, 0).distanceTo(local.tile()) < 5) {
			
			if((ctx.varpbits.varpbit(3518) & 0x7F)==118){//If you already got the silver light and must read the dropped scroll
				init = null;
				Vars.DYNAMICV = false;
				//the scroll acts like an npc
				Method.interactO(91692, "Read", "Grufeld's scroll");//the scroll on ground near Bach's old location
				
			}else//If first starting the quest
			if (!Method.startQuestOpen()) {
				Vars.DYNAMICV = false;
				init = null;
				if (!Method.isChatting("Grufeld Bach")) {
					Vars.DYNAMICV = false;
					Method.speakTo(16713, "Grufeld Bach");
					Method.speakTo(19501, "Grufeld Bach");
				}
			}
		} else if(!Method.isChatting("People"))
			delta(pathToBach, "Walking to Grufeld Bach");
	}
	  
	  
  }
