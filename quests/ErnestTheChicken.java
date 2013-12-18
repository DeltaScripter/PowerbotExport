package quests;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.Player;
import org.powerbot.script.wrappers.Tile;

import quests.Vars.TeleportLode;
import quests.Vars.TeleportType;

public class ErnestTheChicken extends Node{

	public ErnestTheChicken(MethodContext ctx) {
		super(ctx);
	}


	public Tile t;
	public final String s = "Walking to Veronica";
	public int itemsArray[] = {0,0,0,0,0};
	
	public final static Tile[] pathToVeronica = new Tile[] { 
		new Tile(3232, 3221, 0), new Tile(3232, 3226, 0), new Tile(3232, 3231, 0), 
		new Tile(3228, 3234, 0), new Tile(3226, 3239, 0), new Tile(3223, 3243, 0), 
		new Tile(3219, 3246, 0), new Tile(3218, 3251, 0), new Tile(3218, 3256, 0), 
		new Tile(3215, 3261, 0), new Tile(3215, 3266, 0), new Tile(3213, 3271, 0), 
		new Tile(3210, 3275, 0), new Tile(3207, 3279, 0), new Tile(3202, 3279, 0), 
		new Tile(3197, 3279, 0), new Tile(3192, 3279, 0), new Tile(3189, 3283, 0), 
		new Tile(3184, 3285, 0), new Tile(3179, 3285, 0), new Tile(3174, 3286, 0), 
		new Tile(3169, 3288, 0), new Tile(3164, 3289, 0), new Tile(3159, 3289, 0), 
		new Tile(3154, 3291, 0), new Tile(3150, 3294, 0), new Tile(3145, 3294, 0), 
		new Tile(3140, 3295, 0), new Tile(3135, 3295, 0), new Tile(3130, 3296, 0), 
		new Tile(3125, 3296, 0), new Tile(3120, 3296, 0), new Tile(3115, 3297, 0), 
		new Tile(3110, 3298, 0), new Tile(3105, 3298, 0), new Tile(3101, 3301, 0), 
		new Tile(3103, 3306, 0), new Tile(3105, 3311, 0), new Tile(3107, 3316, 0), 
		new Tile(3109, 3321, 0), new Tile(3110, 3326, 0), new Tile(3110, 3331, 0), 
		new Tile(3110, 3336, 0), new Tile(3109, 3341, 0), new Tile(3109, 3346, 0), 
		new Tile(3108, 3351, 0) };
	
	public int bankItems[] = {952};
	private Timer timer = new Timer(0);
	private Method Method = new Method(ctx);
	private Vars Vars = new Vars();
	
	public void execute() {
		
		Method.resetTeleporting();
		Method.foodSupport();
		DeltaQuester.numSteps = 4;
		///ctx.camera.setYaw(70);
		

		if ((ctx.settings.get(2183) & 0x3) == 3) {
			DeltaQuester.progress = 4;
			Method.state("The Ernest The Chicken quest has been completed.");
			Method.sleep(2000);
			DeltaQuester.e = true;
		} else // if(Method.useBank){
				// Method.useBank(bankItems, 10,1,90);
		// }else
		if ((ctx.settings.get(2183) & 0x3) == 2) {
			DeltaQuester.progress = 3;
			cs3();
		} else if ((ctx.settings.get(2183) & 0x1) == 1) {
			DeltaQuester.progress = 2;
			cs2();
		} else if ((ctx.settings.get(2183) & 0x1) == 0) {
			DeltaQuester.progress = 1;
			cs1();//Start the quest by speaking with Veronica
		}
		
	}
		
		
	

	private void cs3() {
		Player local = ctx.players.local();
		if (!Method.teleporting) {
			if (Method.inventoryContains(271)) {//pressure Gauge.
				if (Method.inventoryContains(276)){
					if(Method.inventoryContains(277)){
						//finishQuest();
					}else{
						//gatherOilCan();
					}
				}else{
					//gatherRubberTube();
				}
			} else
				gatherPressureGauge();
		}else if(Method.DraynorLodeIsActive()){
			if(TeleportLode.DRAYNOR.getTile().distanceTo(local.getLocation())<10){
				Vars.DYNAMICV = true;
			}else Method.teleportTo(TeleportType.DRAYNOR.getTeleport(),TeleportType.DRAYNOR.getName());
		} else
			Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());

	}
/*
	private void finishQuest() {
		if (new Tile(3106,3365,2).getMatrix(ctx).isReachable() || new Tile(3110,3365,2).getMatrix(ctx).isReachable()){
			if(new Tile(3110,3365,2).getMatrix(ctx).isReachable()){
				if (Quests.tActive()){
					Quests.state("Speaking.");
					if (Quests.chat(1184, 13, "")){
						Quests.clickContinue();
					}else if(Quests.chat(1191, 17, "")){
						Quests.clickContinue();
					}else if(Quests.findOption("I'm looking for")){
						Quests.clickOption(1188, Quests.value);
					}else if(Quests.findOption("Change him back")){
						Quests.clickOption(1188, Quests.value);
					}
				}else Quests.speakTo(286);
			}else Quests.interactO(47512, "Open");
		}else if (new Tile(3105,3364,1).getMatrix(ctx).isReachable()){
			Quests.state("Reached 2nd floor.");
			Quests.interactO(47574, "Climb");
		}else if(new Tile(3108,3361,0).getMatrix(ctx).isReachable()){//Within first floor.
			
			if (new Tile(3108,3361,0).distanceTo(local.getLocation())<4){
			Quests.interactO(47364, "Climb");
			}else Walking.findPath(new Tile(3108,3361,0)).traverse();
			
		}else if(new Tile(3108,3353,0).distanceTo(local.getLocation())<5){
			Quests.walkingReset();
			Quests.interactO(47421, "Open");
		}else if(Vars.DYNAMICV){
			Quests.walking(pathToVeronica, s,false);
		}else if(Method.DraynorLodeIsActive()){
			if(Vars.DRAYNORLODE.distanceTo(local.getLocation())<10){
				Vars.DYNAMICV = true;
			}else Method.teleportTo(Vars.DRAYNORTELEPORT);
		}else if(Vars.LUMMBRIDGELODE.distanceTo(local.getLocation())<10){
			Vars.DYNAMICV=true;
		}else Method.teleportTo(47);
		
	}
*/
/*
	private void gatherOilCan() {
		SceneObject check = SceneEntities.getNearest(31130);
		if(check!=null){//can you rech teh basement floor bro??
			Vars.DYNAMICV = false;
			solvePuzzle();
		}else{
		
		if(new Tile(3095,3359,0).getMatrix(ctx).isReachable()){
		Quests.interactO(133, "Climb");
		}else{
		
		if(new Tile(3098,3360,0).getMatrix(ctx).isReachable()){//outside the secret room that leads to basement..
			if(new Tile(3098,3360,0).distanceTo(local.getLocation())<5){
				Quests.interactO(47711, "Search");
			}else Quests.findPath(new Tile(3098,3360,0));
		}else if(new Tile(3103,3365,0).getMatrix(ctx).isReachable()){//door outside of tube room.
			if (new Tile(3103,3365,0).distanceTo(local.getLocation())<5){//checks distance.
				Method.openDoor(new Tile(3103,3364,0));//open door leading to secret b shelf.
			}else Quests.findPath(new Tile(3103,3364,0));//walks to the door if too far.
		}else if(new Tile(3107,3368,0).getMatrix(ctx).isReachable()){
			Quests.interactO(47511, "Open");
		}else Quests.state("...I'm lost..");

			}
		}
	}*/
/*
	private void solvePuzzle() {
		
		if(ctx.settings.get(2184)==88){
			if(new Tile(3099,9755,0).getMatrix(ctx).isReachable()){
			GroundItem oil = GroundItems.getNearest(277);//oil ID
			if(oil!=null){
				if(oil.isOnScreen()){
					oil.interact("Take");
				}else ctx.camera.turnTo(oil);
			}
			}else if(new Tile(3101,9755,0).getMatrix(ctx).isReachable()){
				Quests.interactO(141, "Open");
				 Delay.sleep(1200,1500);
			}else if(new Tile(3102,9759,0).getMatrix(ctx).isReachable()){
				Quests.interactO(145, "Open");
				 Delay.sleep(1200,1500);
			}else if(new Tile(3102,9764,0).getMatrix(ctx).isReachable()){
				Quests.interactO(2374, "Open");
				 Delay.sleep(1200,1500);
			}else if(new Tile(3099,9765,0).getMatrix(ctx).isReachable()){
				Quests.interactO(138, "Open");
				 Delay.sleep(1200,1500);
			}
		}else{
		
		if(ctx.settings.get(2184)==120){
			if(new Tile(3099,9765,0).getMatrix(ctx).isReachable()){
				 Quests.interactO(150, "Pull");
				 Delay.sleep(1200,1500);
			}else if((new Tile(3101,9765,0).getMatrix(ctx).isReachable())){
				Quests.interactO(138, "Open");
				 Delay.sleep(1200,1500);
			}else if(new Tile(3106,9765,0).getMatrix(ctx).isReachable()){
				if(new Tile(3106,9765,0).distanceTo(local.getLocation())<5){
					Quests.interactO(137, "Open");
					 Delay.sleep(1200,1500);
				}else Quests.findPath(new Tile(3106,9765,0));
			}
		}else{
		
		if(ctx.settings.get(2184)==112){
			if(new Tile(3106,9765,0).getMatrix(ctx).isReachable()){
				if(new Tile(3112,9760,0).distanceTo(local.getLocation())<5){
					 Quests.interactO(148, "Pull");
					 Delay.sleep(1200,1500);
					 }else Quests.findPath(new Tile(3112,9760,0));
			}else if(new Tile(3104,9765,0).getMatrix(ctx).isReachable()){
				Quests.interactO(137, "Open");
				 Delay.sleep(1200,1500);
			}else if(new Tile(3099,9765,0).getMatrix(ctx).isReachable()){
				Quests.interactO(138, "Open");
				 Delay.sleep(1200,1500);
			}
		}else{
		
		if(new Tile(3102,9756,0).getMatrix(ctx).isReachable() && ((ctx.settings.get(2184)==22) || ctx.settings.get(2184)==20)|| ctx.settings.get(2184)==16 || ctx.settings.get(2184)==16|| ctx.settings.get(2184)==48){
			
			if(ctx.settings.get(2184)==48){
				Quests.interactO(151, "Pull");
				 Delay.sleep(1200,1500);
			}else{
			
			if(ctx.settings.get(2184)==16){//dominent..
				if(new Tile(3097,9764,0).getMatrix(ctx).isReachable()){
					if(ctx.settings.get(2184)==16){
						 Quests.interactO(150, "Pull");
						 Delay.sleep(1200,1500);
					}
				}else{
				if(new Tile(3099,9761,0).getMatrix(ctx).isReachable()){
					Quests.interactO(143, "Open");
					 Delay.sleep(1200,1500);
				}else if(new Tile(3102,9760,0).getMatrix(ctx).isReachable()){
					Quests.interactO(140, "Open");
					 Delay.sleep(1200,1500);
				}else{
					if(new Tile(3102,9757,0).distanceTo(local.getLocation())<5){
						 Quests.interactO(145, "Open");
						 Delay.sleep(1200,1500);
						} else
							Quests.findPath(new Tile(3102, 9757, 0));
					}
				}
			
			}else if(ctx.settings.get(2184)==22){
				if(new Tile(3108,9745,0).distanceTo(local.getLocation())<5){
					 Quests.interactO(146, "Pull");
					 Delay.sleep(1200,1500);
					 }else Quests.findPath(new Tile(3108,9745,0));
				}else if(ctx.settings.get(2184)==20){
					if(new Tile(3118,9752,0).distanceTo(local.getLocation())<5){
						 Quests.interactO(147, "Pull");
						 Delay.sleep(1200,1500);
						 }else Quests.findPath(new Tile(3118,9752,0));
				}
			}
		}else{
			
		if (new Tile(3108,9759,0).getMatrix(ctx).isReachable() || new Tile(3104,9760,0).getMatrix(ctx).isReachable()){
			if(ctx.settings.get(2184)==6){
			 if(new Tile(3108,9767,0).distanceTo(local.getLocation())<5){
				 Quests.interactO(149, "Pull");
				 Delay.sleep(1200,1500);
				 }else Quests.findPath(new Tile(3108,9767,0));
			}else{
				if(new Tile(3103,9762,0).getMatrix(ctx).isReachable()){
					Quests.interactO(145, "Open");
					 Delay.sleep(1200,1500);
				}else{
					 if(new Tile(3105,9761,0).distanceTo(local.getLocation())<5){
							Quests.interactO(2373, "Open");
							 Delay.sleep(1200,1500);
						 }else Quests.findPath(new Tile(3105,9761,0));
				}
			}
		}else{
			
		 if(ctx.settings.get(2184)==6){
			 Quests.state("Ready to open door.");
			 if(new Tile(3108,9757,0).distanceTo(local.getLocation())<5){
				Quests.interactO(144, "Open");
				 Delay.sleep(1200,1500);
			 }else Quests.findPath(new Tile(3108,9757,0));
		 }else if(ctx.settings.get(2184)==4){
			 
			 if(new Tile(3108,9746,0).distanceTo(local.getLocation())<5){
				 Quests.interactO(146, "Pull");
				 Delay.sleep(1200,1500);
			 }else Quests.findPath(new Tile(3108,9746,0));
			 //grab next switch//
		 }else{
			 if(new Tile(3118,9752,0).distanceTo(local.getLocation())<5){
				 Quests.interactO(147, "Pull");
				 Delay.sleep(1200,1500);
								} else
									Quests.findPath(new Tile(3118, 9752, 0));
							}

						}
					}
				}
			}
		}
	}
/*
	private void gatherRubberTube() {
		
		if(new Tile(3107,3366,0).getMatrix(ctx).isReachable()){
				GroundItem tube = GroundItems.getNearest(276);
				if (tube.isOnScreen()) {
					Quests.state("Grabbing our tube.");
					Mouse.click(Quests.getOnScreenPoint(tube), true);
				} else
					ctx.camera.turnTo(tube);

			
		}else{//if you can't reach room with the tube.
		
		if(new Tile(3107,3369,0).getMatrix(ctx).isReachable()){
			if(new Tile(3107,3369,0).distanceTo(local.getLocation())<5){
				Method.openDoor(new Tile(3107,3369,0));
			}else Quests.findPath(new Tile(3107,3369,0));
			
		}else{//if you can't reach the final room to dest.
		
		if (new Tile(3103, 3362, 0).getMatrix(ctx).isReachable()) {//if you can reach the second room to destination..
			if (new Tile(3103, 3362, 0).distanceTo(local.getLocation()) < 6) {//dist check..
				Method.openDoor(new Tile(3103, 3363, 0));//open door..
			} else
				Quests.findPath(new Tile(3103, 3362, 0));//walk a bit closer..
		} else {

		if(new Tile(3108,3360,0).getMatrix(ctx).isReachable()){
			if(new Tile(3105,3360,0).distanceTo(local.getLocation())<5){
				Method.openDoor(new Tile(3105,3360,0));
			}else Quests.findPath(new Tile(3105,3360,0));//walks to first door in mansion..
		}else{
		//if outside mansion..
			if (new Tile(3109, 3352, 0).getMatrix(ctx).isReachable()) {//can reach loc by mansion door.
				if (new Tile(3109, 3352, 0).distanceTo(local.getLocation()) < 5) {//tests distance..
					Quests.state("Opening mansion door.");
					Method.openDoor(new Tile(3109, 3353, 0));//opens mansion door.
				} else
					Quests.findPath(new Tile(3109, 3353, 0));//walks to mansion door.
				}
			}
				}
			}
		}
	}
	*/

	private void gatherPressureGauge() {
		Player local = ctx.players.local();
			if (itemsArray[0]==1){//Fish food check
				if (itemsArray[1]==1){//Poison
					if (itemsArray[2]==1){//Spade
						if(itemsArray[3]==1){//Grimy key.
							if (itemsArray[4]==1){
								if (Method.inventoryContains(274)){//The poison fish food...
									Method.state("Must update here");
							}else if(Method.inventoryContains(274)){
								itemsArray[4]=1;
							}else Method.useItemOn(272, 273, "Object");
						
						}else if(Method.inventoryContains(275)){
							itemsArray[3]=1;
						} else {
							if (new Tile(3086, 3361, 0).getMatrix(ctx).isReachable()) {
								if (new Tile(3086, 3361, 0).distanceTo(local.getLocation()) < 5) {
									Method.interactO(152, "Search","Compost");
								} else ctx.movement.findPath(new Tile(3086, 3361, 0)).traverse();
							} else if (new Tile(3123, 3363, 0).getMatrix(ctx).isReachable()) {
								if (new Tile(3123, 3363, 0).distanceTo(local.getLocation()) < 5) {
									Method.interactO(47512, "Open", "Door");
								} else ctx.movement.findPath(new Tile(3123, 3363, 0)).traverse();//finds path to grimy key loc.
							} else if (new Tile(3118, 3358, 0).getMatrix(ctx).isReachable()) {//checks if can outside spade room.
								if (new Tile(3118, 3358, 0).distanceTo(local.getLocation()) < 6) {//checks distance to door.
									Method.interactO(47512, "Open", "Door");
								} else ctx.movement.findPath(new Tile(3118, 3358, 0)).traverse();//find path tooor containing spade.
							}

						}
					}else if(Method.inventoryContains(952)){
						itemsArray[2]=1;
					}else{
						
						 if(new Tile(3121,3359,0).getMatrix(ctx).isReachable()){
								if (Method.gItemIsNotNull(952)){
									if (Method.getGroundItem(952).isOnScreen()){
										Method.interactG(952, "Take", "Spade");
									}else Method.clickOnMap(Method.getGroundItem(952).getLocation());
								}
							}else if(new Tile(3119,3357,0).getMatrix(ctx).isReachable()){
								if (new Tile(3119,3357,0).distanceTo(local.getLocation())<5){
								//	ctx.camera.setYaw(50);
									Method.interactO(47512, "Open", "Door");
								}else ctx.movement.findPath(new Tile(3119,3357,0)).traverse();
							}else if(new Tile(3100,3371,0).getMatrix(ctx).isReachable()){
								if (new Tile(3100,3371,0).distanceTo(local.getLocation())<4){
									Method.interactO(47512, "Open", "Door");
								}else ctx.movement.findPath(new Tile(3101,3371,0)).traverse();
							}else if(new Tile(3099,3366,0).getMatrix(ctx).isReachable()){
								Method.interactO(47512, "Open", "Door");
							}
						
					}
					
				}else{
					if (Method.inventoryContains(273)|| Method.inventoryContains(274)){
						itemsArray[1] =1;
					}else{
						if (new Tile(3099,3365,0).getMatrix(ctx).isReachable()){
							if (new Tile(3099,3365,0).distanceTo(local.getLocation())<3){
								if(!local.isInMotion()){
									Method.interactG(273, "Take", "Poison");
								}
							}else ctx.movement.findPath(new Tile (3099,3365,0)).traverse();
						}else{//if you can't reach the poison room..
							if(new Tile(3099,3369,0).getMatrix(ctx).isReachable()){
								if (new Tile(3099,3369,0).distanceTo(local.getLocation())<4){
									Method.interactO(47512, "Open", "Door");
								}else ctx.movement.findPath(new Tile(3099,3367,0)).traverse();
							}else if (new Tile(3103,3371,0).getMatrix(ctx).isReachable()){//if you can't reach the second room to poison..
								if (new Tile(3103,3371,0).distanceTo(local.getLocation())<4){
									Method.interactO(47512, "Open", "Door");
								}else ctx.movement.findPath(new Tile(3103,3371,0)).traverse();
							}else{
								
								if(new Tile(3103,3362,0).getMatrix(ctx).isReachable()){
									if(new Tile(3103,3362,0).distanceTo(local.getLocation())<5){
										Method.interactO(47512, "Open", "Door");
									}else ctx.movement.findPath(new Tile(3103,3362,0)).traverse();
								}else if(new Tile(3107,3360,0).getMatrix(ctx).isReachable()){
									Method.interactO(47512, "Open", "Door");
								}else{//now checking upstairs..
									if(new Tile(3108,3366,1).getMatrix(ctx).isReachable() &&  ctx.game.getPlane()==1){
										if (new Tile(3109,3367,1).distanceTo(local.getLocation())<5){
										Method.interactO(47657, "Climb","Stairs");//climbing down staircase.
										}else ctx.movement.findPath(new Tile(3109,3367,1)).traverse();
									}else if(new Tile(3112,3362,1).getMatrix(ctx).isReachable()){
										Method.interactO(47512, "Open", "Door");
									}else if(new Tile(3109,3358,1).getMatrix(ctx).isReachable()){
										Method.interactO(47512, "Open", "Door");
									}
								}
								
							}
						}
						
					}
				}
				}
			}else{
				if (Method.inventoryContains(272) || Method.inventoryContains(274)){//274 being the poison fish food.
					itemsArray[0] = 1;
				}else{//RETREIVES FISH FOOD.
				if ( !Method.objIsByTile(new Tile(3112, 3363, 1),47512,3)&&
						!Method.objIsByTile(new Tile(3111,3358, 1),47512,3)&&  ctx.game.getPlane()==1) {
					if (new Tile(3109, 3357, 1).distanceTo(local.getLocation()) < 4) {
						Method.interactG(272, "Take", "Item");
					} else {
						Method.state("Walking to fish food");
						ctx.movement.findPath(new Tile(3109, 3357, 1)).traverse();
					}
				} else if (new Tile(3112, 3358, 1).getMatrix(ctx).isReachable() && ctx.game.getPlane()==1) {// second room to fish food.
					if (new Tile(3112, 3358, 1).distanceTo(local.getLocation()) < 3) {
						Method.interactO(47512, "Open", "Door");
					}else {
						Method.state("Hello2");
						ctx.movement.findPath(new Tile(3112, 3358, 1)).traverse();
					}
					
			} else if (new Tile(3112, 3365, 1).getMatrix(ctx).isReachable() && ctx.game.getPlane()==1) {
				if (new Tile(3112,3363,1).distanceTo(local.getLocation())<4){
					Method.interactO(47512, "Open", "Door");
				}else {
					Method.state("Hello1");
					ctx.movement.findPath(new Tile(3112,3363,1)).traverse();
				}
			} else if (new Tile(3105, 3362, 2).getMatrix(ctx).isReachable()&& ctx.game.getPlane()==2) {// 3rd floor by ladder.
				if(!Method.objIsByTile(new Tile(3107,3364,2), 47512, 3)){
					Method.interactO(47575, "Climb","Ladder");
				}else Method.interactO(47512,"Open", "Door");
			} else if (new Tile(3109, 3365, 2).getMatrix(ctx).isReachable()&& ctx.game.getPlane()==2) {// in professors room.
				Method.interactO(47512, "Open","Door");
			} else if (new Tile(3105,3361, 2).getMatrix(ctx).isReachable()&& ctx.game.getPlane()==2) {// in professors room.
				Method.interactO(47511, "Open","Door");
			}else if (new Tile(3109, 3361, 0).getMatrix(ctx).isReachable()) {// bottom floor in house
				if (new Tile(3109, 3361, 0).distanceTo(local.getLocation()) < 4) {// near staircase.
					Method.interactO(47364, "Climb","Stairs");
				} else {
					Method.state("Hello");
					ctx.movement.findPath(new Tile(3109, 3361, 0)).traverse();
				}
			} else {
				if (new Tile(3108, 3352, 0).distanceTo(local.getLocation()) < 5) {
					Method.interactO(47421, "Open","Door");
				} else if (Vars.DYNAMICV) {
					Method.walking(pathToVeronica, "Walking to the mansion", false);
				}else cs2();//Get the player to the mansion
				}
			}
			}
		}


	private boolean pathToFish() {
		final Tile[] locations = {new Tile(3112,3363,1), new Tile(3111,3358,1)};
		for(Tile tile : locations){
			if(!Method.objIsByTile(tile, 47512, 3)){
				return true;
			}
		}
	return false;
}




	private void cs2() {//Enter the mansion, speak to the scientist in the attic
		Player local = ctx.players.local();
		final String[] opt = {"Change him back","I'm looking for"};
		
		if(new Tile(3106,3364,2).getMatrix(ctx).isReachable() && ctx.game.getPlane()==2){//third floor
			if(!Method.objIsByTile(new Tile(3107,3364,2), 47512, 3)){
				
				if(!Method.findOption(opt))
					if(!Method.isChatting("Scientist")){
						Method.speakTo(286, "Scientist");
					}
			}else Method.interactO(47512,"Open", "Door");
		}else
		if(new Tile(3108,3366,1).getMatrix(ctx).isReachable()){//second floor
			Method.interactO(47574, "Climb", "Ladder");
		}else
		if(new Tile(3108,3356,0).getMatrix(ctx).isReachable()){//mansion main hall
			if(new Tile (3109,3361,0).distanceTo(local.getLocation())<7){
				Method.interactO(47364, "Climb", "Stairs");
			}else ctx.movement.findPath(new Tile (3109,3361,0)).traverse();
		}else
	    if(new Tile (3108,3352,0).distanceTo(local.getLocation())<5){//outside mansion
	    	Method.skipPics();
	    	if(!Method.isChatting("Self"))
			Method.interactO(47421, "Open","Mansion door");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToVeronica, "Walking to the mansion", false);
		}else if(TeleportLode.DRAYNOR.getTile().distanceTo(local.getLocation())<10||
				TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.getLocation())<10){
			Vars.DYNAMICV = true;
		}else if(Method.DraynorLodeIsActive()){
			 Method.teleportTo(TeleportType.DRAYNOR.getTeleport(),TeleportType.DRAYNOR.getName());
		}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
		
	}

	private void cs1() {//Start the quest by speaking to Veronica
		Player local = ctx.players.local(); 
		
		if(new Tile(3111,3328,0).distanceTo(local.getLocation())<7){
			Vars.DYNAMICV = true;
			if(!Method.startQuestOpen())
				if(!Method.isChatting("Veronica")){
					Method.speakTo(285, "Veronica");
				}
		}else if(Vars.DYNAMICV){
			Method.walking(pathToVeronica, "Walking to Veronica", false);
		}else if(TeleportLode.DRAYNOR.getTile().distanceTo(local.getLocation())<10||
				TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.getLocation())<10){
			Vars.DYNAMICV = true;
		}else if(Method.DraynorLodeIsActive()){
			 Method.teleportTo(TeleportType.DRAYNOR.getTeleport(),TeleportType.DRAYNOR.getName());
		}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
		
	}

	public boolean activate() {
		return (DeltaQuester.scriptToStart == 11);
	}

}
