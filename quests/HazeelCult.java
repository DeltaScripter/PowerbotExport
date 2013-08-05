/*package quests;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Delay;
import org.powerbot.script.wrappers.Player;
import org.powerbot.script.wrappers.Tile;

import quests.Vars.TeleportLode;
import quests.Vars.TeleportType;

public class HazeelCult extends Node{

	public HazeelCult(MethodContext ctx) {
		super(ctx);
	}

	public final Tile[] pathToMansion = new Tile[] { 
			new Tile(2633, 3348, 0), new Tile(2634, 3343, 0), new Tile(2636, 3338, 0), 
			new Tile(2639, 3334, 0), new Tile(2643, 3331, 0), new Tile(2647, 3328, 0), 
			new Tile(2648, 3323, 0), new Tile(2650, 3318, 0), new Tile(2653, 3314, 0), 
			new Tile(2653, 3309, 0), new Tile(2648, 3307, 0), new Tile(2643, 3307, 0), 
			new Tile(2640, 3303, 0), new Tile(2636, 3300, 0), new Tile(2631, 3298, 0), 
			new Tile(2626, 3296, 0), new Tile(2621, 3296, 0), new Tile(2616, 3296, 0), 
			new Tile(2611, 3297, 0), new Tile(2606, 3297, 0), new Tile(2601, 3297, 0), 
			new Tile(2596, 3296, 0), new Tile(2591, 3294, 0), new Tile(2589, 3289, 0), 
			new Tile(2588, 3284, 0), new Tile(2584, 3281, 0), new Tile(2579, 3279, 0), 
			new Tile(2574, 3280, 0), new Tile(2569, 3279, 0), new Tile(2568, 3276, 0) };
	
	public final Tile[] pathToCave = new Tile[] {//Theives hide-out 
			new Tile(2633, 3348, 0), new Tile(2634, 3343, 0), new Tile(2635, 3338, 0), 
			new Tile(2638, 3334, 0), new Tile(2643, 3332, 0), new Tile(2646, 3328, 0), 
			new Tile(2647, 3323, 0), new Tile(2650, 3319, 0), new Tile(2653, 3315, 0), 
			new Tile(2653, 3310, 0), new Tile(2650, 3306, 0), new Tile(2645, 3305, 0), 
			new Tile(2640, 3305, 0), new Tile(2637, 3301, 0), new Tile(2633, 3298, 0), 
			new Tile(2628, 3298, 0), new Tile(2623, 3297, 0), new Tile(2618, 3296, 0), 
			new Tile(2613, 3296, 0), new Tile(2608, 3296, 0), new Tile(2606, 3293, 0), 
			new Tile(2606, 3288, 0), new Tile(2607, 3283, 0), new Tile(2606, 3278, 0), 
			new Tile(2603, 3274, 0), new Tile(2599, 3271, 0), new Tile(2598, 3266, 0), 
			new Tile(2599, 3261, 0), new Tile(2596, 3257, 0), new Tile(2593, 3253, 0), 
			new Tile(2596, 3249, 0), new Tile(2599, 3244, 0), new Tile(2599, 3239, 0), 
			new Tile(2594, 3237, 0), new Tile(2591, 3235, 0), new Tile(2585, 3234, 0) };
	
	public final Tile[] pathToSwitch1 = new Tile[] { 
			new Tile(2633, 3348, 0), new Tile(2634, 3343, 0), new Tile(2635, 3338, 0), 
			new Tile(2638, 3334, 0), new Tile(2643, 3332, 0), new Tile(2646, 3328, 0), 
			new Tile(2647, 3323, 0), new Tile(2650, 3319, 0), new Tile(2653, 3315, 0), 
			new Tile(2653, 3310, 0), new Tile(2650, 3306, 0), new Tile(2645, 3305, 0), 
			new Tile(2640, 3305, 0), new Tile(2637, 3301, 0), new Tile(2633, 3298, 0), 
			new Tile(2628, 3298, 0), new Tile(2623, 3297, 0), new Tile(2618, 3296, 0), 
			new Tile(2613, 3296, 0), new Tile(2608, 3296, 0), new Tile(2606, 3293, 0), 
			new Tile(2606, 3288, 0), new Tile(2607, 3283, 0), new Tile(2606, 3278, 0), 
			new Tile(2603, 3274, 0), new Tile(2599, 3271, 0), new Tile(2598, 3266, 0), 
			new Tile(2599, 3261, 0), new Tile(2596, 3257, 0), new Tile(2593, 3253, 0), 
			new Tile(2592, 3251, 0), new Tile(2587, 3250, 0), new Tile(2582, 3249, 0), 
			new Tile(2577, 3251, 0), new Tile(2574, 3255, 0), new Tile(2569, 3257, 0), 
			new Tile(2564, 3255, 0), new Tile(2563, 3250, 0), new Tile(2562, 3248, 0) };
	
	
	public final int[] bankItems = {273,2404,2406};
	
	public boolean hasMark = false;
	public boolean hasKey = false;
	private Method Method = new Method(ctx);
	private Vars Vars = new Vars();
	
	public boolean activate() {
		return DeltaQuester.scriptToStart==34;
	}

	@Override
	public void execute() {
		DeltaQuester.numSteps = 8;
		Method.resetTeleporting();//Make sure to use banking that pulls out the mark!
		
		if((ctx.settings.get(2592)&0xF)==9){
			DeltaQuester.progress = 8;
			Method.state("The Hazeel Cult quest has been completed.");
			Delay.sleep(2000);
			DeltaQuester.getInstance().e = true;
		}else// if(Method.useBank){
			//Method.useBank(bankItems, 1, 90, 90);
		//}else
		if((ctx.settings.get(2592)&0x7)==7){
			DeltaQuester.progress = 7;
			while((ctx.settings.get(1114)&0x1)==1){
				Method.skipPics();
				Method.isChatting("Cutscene");
			}
			cs3();//Give the scroll to the 'other' member and summon the demon
		}else 
		if((ctx.settings.get(2592)&0x7)==6){
			DeltaQuester.progress = 6;
			cs4();//Finds key in basement then takes the scroll in the hidden chest using the key
		}else 
		if((ctx.settings.get(2592)&0x7)==5){
			DeltaQuester.progress = 5;
			cs3();//Turn the valves and enter the secret hide-out as well with speak to the member
		}else 
		if((ctx.settings.get(2592)&0x7)==4){
			DeltaQuester.progress = 4;
			cs2();//Enter ceril's basement and use poison on the range
		}else 
		if((ctx.settings.get(2592)&0x3)==3){
			DeltaQuester.progress = 3;
			cs1();//Speak to the cultist to learn the truth about the situation(and join him)
		}else 
		if((ctx.settings.get(2592)&0x3)==2){
			DeltaQuester.progress = 2;
			cs1();//Speak to the cult leader in the cave
		}else {
			DeltaQuester.progress = 1;
			cs0();//Speak to Ceril and start the quest
		}
		
	}

	private void cs4() {
		Player local = ctx.players.getLocal();
		if(hasKey){
			if(new Tile(2566,3275,2).canReach()){
				Vars.DYNAMICV = false;
				Method.interactO(2856, "Open", "Chest");
			}else
			if(new Tile(2566,3275,1).canReach()){
				Method.interactO(34785, "Climb", "Ladder");
			}else
			if(new Tile(2566,3272,1).canReach()){
				Method.interactO(26940, "Knock", "Wall");
			}else if(new Tile(2569,3269,1).canReach()){
				if(new Tile(2568,3272,1).distanceTo(local.getLocation())<2){
				Method.interactO(34807, "Open", "Door");
				}else Method.clickOnMap(new Tile(2568,3272,1));
			}else
			if(new Tile(2569,3268,0).distanceTo(local.getLocation())<7){
				Method.interactO(73418, "Climb", "Stairs");
			}else if(new Tile(2569,9668,0).canReach()){
				Method.interactO(34829, "Climb", "Ladder");
			}else cs2();//Enter the mansion
		}else if(!Method.teleporting && Method.inventoryContains(2404)){
			hasKey  =true;
		}else
		if(new Tile(2569,9668,0).canReach() && ctx.game.getPlane()==0){
			Method.interactO(2858, "Search", "Crate");
		}else cs2();//Enter the basement
		
	}

	private void cs3() {
		Player local = ctx.players.getLocal();
		if(hasMark){
			if((ctx.settings.get(2594)>>1&0x1)==1){
				if((ctx.settings.get(2594)>>2&0x1)==1){
					if((ctx.settings.get(2594)>>3&0x1)==1){
						if((ctx.settings.get(2594)>>4&0x1)==1){
							if((ctx.settings.get(2594)>>5&0x1)==1){
								if(new Tile(2606,9692,0).canReach()){
									if(new Tile(2608,9670,0).distanceTo(local.getLocation())<5){
												Vars.DYNAMICV = false;
												Method.skipPics();
											  if(!Method.isChatting("Alomone")){
												  Method.speakTo(891, "Alomone");
											  }
										
									}else Method.clickOnMap(new Tile(2608,9670,0));
								}else
								if(new Tile(2570,9682,0).canReach()){
									Method.interactO(2849, "Board", "Raft");
								}else cs1();
							}else
							if(new Tile(2611,3241,0).distanceTo(local.getLocation())<30){
								
								if(new Tile(2611,3241,0).distanceTo(local.getLocation())<4){
									Vars.DYNAMICV = false;
									Method.interactO(2848, "Turn-right", "Sewer valve");
								}else Method.findPath(new Tile(2611,3241,0),"Walking to sewer valve");
								
							}else if(Vars.DYNAMICV){
								Method.walking(pathToSwitch1, "Walking to lever", false);
							}else if(TeleportLode.ARDOUGNE.getTile().distanceTo(local.getLocation())<10){
								Vars.DYNAMICV = true;
							}else Method.teleportTo(TeleportType.ARDOUGNE.getTeleport());
						}else
						if(new Tile(2570,3264,0).distanceTo(local.getLocation())<40){
							
							if(new Tile(2597,3261,0).distanceTo(local.getLocation())<4){
								Vars.DYNAMICV = false;
								Method.interactO(2847, "Turn-right", "Sewer valve");
							}else Method.findPath(new Tile(2597,3261,0),"Walking to sewer valve");
							
						}else if(Vars.DYNAMICV){
							Method.walking(pathToSwitch1, "Walking to lever", false);
						}else if(TeleportLode.ARDOUGNE.getTile().distanceTo(local.getLocation())<10){
							Vars.DYNAMICV = true;
						}else Method.teleportTo(TeleportType.ARDOUGNE.getTeleport());
					}else
					if(new Tile(2570,3264,0).distanceTo(local.getLocation())<40){
						
						if(new Tile(2585,3245,0).distanceTo(local.getLocation())<4){
							Vars.DYNAMICV = false;
							Method.interactO(2846, "Turn-left", "Sewer valve");
						}else Method.findPath(new Tile(2585,3246,0),"Walking to sewer valve");
						
					}else if(Vars.DYNAMICV){
						Method.walking(pathToSwitch1, "Walking to lever", false);
					}else if(TeleportLode.ARDOUGNE.getTile().distanceTo(local.getLocation())<10){
						Vars.DYNAMICV = true;
					}else Method.teleportTo(TeleportType.ARDOUGNE.getTeleport());
				}else
				if(new Tile(2570,3264,0).distanceTo(local.getLocation())<30){
					
					if(new Tile(2570,3264,0).distanceTo(local.getLocation())<4){
						Vars.DYNAMICV = false;
						Method.interactO(2845, "Turn-right", "Sewer valve");
					}else Method.findPath(new Tile(2570,3264,0),"Walking to sewer valve");
					
				}else if(Vars.DYNAMICV){
					Method.walking(pathToSwitch1, "Walking to lever", false);
				}else if(TeleportLode.ARDOUGNE.getTile().distanceTo(local.getLocation())<10){
					Vars.DYNAMICV = true;
				}else Method.teleportTo(TeleportType.ARDOUGNE.getTeleport());
				
			}else
			if(new Tile(2562, 3248, 0).distanceTo(local.getLocation())<7){
				Vars.DYNAMICV = false;
				Method.interactO(2844, "Turn-right", "Sewer valve");
			}else if(Vars.DYNAMICV){
				Method.walking(pathToSwitch1, "Walking to lever", false);
			}else if(TeleportLode.ARDOUGNE.getTile().distanceTo(local.getLocation())<10){
				Vars.DYNAMICV = true;
			}else Method.teleportTo(TeleportType.ARDOUGNE.getTeleport());
			
		}else if(!Method.teleporting && !Method.isChatting("People")&& !local.isInMotion()&& Method.EquipmentContains(2406)){
			hasMark = true;
		}else if(!Method.teleporting && !Method.isChatting("People")&& !local.isInMotion() && (Method.inventoryContains(2406))){
			Method.interactInventory(2406, "Wear", "Hazeal's mark");
		}else cs1();
		
	}

	private void cs2() {//Enter ceril's basement and use poison on the range
		Player local = ctx.players.getLocal();
		if(new Tile(2569,9668,0).canReach()){
			Vars.DYNAMICV = false;
			Method.skipPics();
			if(!local.isInMotion())
			Method.useItemOn(273, 2859, "Range");
		}else
		if(new Tile(2569,3270,0).distanceTo(local.getLocation())<3){
		Method.interactO(36703, "Climb", "Ladder");
		}else cs0();
		
	}

	private void cs1() {//Speaks to the culstist(first)
		Player local = ctx.players.getLocal();
		final String opt[] = {"Okay, count","So, what would I","What do you mean?"};
		
		if(new Tile(2570,9682,0).canReach()){
			Vars.DYNAMICV = false;
			if(!Method.findOption(opt))
				  if(!Method.isChatting("Clivet")){
					  Method.speakTo(893, "Clivet");
				  }
		}else
		if(new Tile(2585, 3234, 0).distanceTo(local.getLocation())<7){
			if(!local.isInMotion())
			Method.interactO(2852, "Enter", "Cave");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToCave, "Walking to hide-out", false);
		}else if(TeleportLode.ARDOUGNE.getTile().distanceTo(local.getLocation())<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.ARDOUGNE.getTeleport());
		
	}

	private void cs0() {//Speak to the ceril
		Player local = ctx.players.getLocal();
		//NPC ceril = NPCs.getNearest(885);
		final String opt[] = {"Yes, of course","What's wrong"};
		
		if(new Tile(2569,3270,0).distanceTo(local.getLocation())<3 || (Method.npcIsNotNull(885) && Method.getNPC(885).getLocation().canReach() && Method.getNPC(885).getLocation().distanceTo(local.getLocation())<4)){
			if(Method.npcIsNotNull(885)){//ceril
				if(Method.getNPC(885).getLocation().canReach() ||Method.getNPC(885).getLocation().distanceTo(local.getLocation())<2){
					if(Method.getNPC(885).getLocation().distanceTo(local.getLocation())<6){
						Vars.DYNAMICV = false;
						if(!Method.startQuestOpen())
					  if(!Method.findOption(opt))
						  if(!Method.isChatting("Ceril Camillean")){
							  Method.speakTo(885, "Ceril Camillean");
						  }
					}else Method.clickOnMap(Method.getNPC(885).getLocation());
				}else Method.interactO(34807, "Open", "Door");
			}
		}else if(new Tile(2569,3275,0).distanceTo(local.getLocation())<6){
			if(new Tile(2569,3270,0).canReach()){
				Method.clickOnMap(new Tile(2569,3270,0));
			}else Method.interactO(34807, "Open", "Door");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToMansion, "Walking to Ceril's mansion", false);
		}else if(TeleportLode.ARDOUGNE.getTile().distanceTo(local.getLocation())<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.ARDOUGNE.getTeleport());
		
	}

}*/
