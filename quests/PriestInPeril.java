package quests;
/*package quests;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class PriestInPeril extends Node{

	public Tile[] pathToKing = new Tile[] { 
			new Tile(3213, 3376, 0), new Tile(3211, 3381, 0), new Tile(3211, 3386, 0), 
			new Tile(3211, 3391, 0), new Tile(3211, 3396, 0), new Tile(3210, 3401, 0), 
			new Tile(3210, 3406, 0), new Tile(3209, 3411, 0), new Tile(3211, 3416, 0), 
			new Tile(3212, 3421, 0), new Tile(3215, 3426, 0), new Tile(3216, 3431, 0), 
			new Tile(3215, 3436, 0), new Tile(3212, 3440, 0), new Tile(3212, 3445, 0), 
			new Tile(3211, 3450, 0), new Tile(3211, 3455, 0), new Tile(3212, 3460, 0), 
			new Tile(3215, 3464, 0), new Tile(3214, 3467, 0), new Tile(3211, 3471, 0), 
			new Tile(3216, 3473, 0) };
	
 public final Tile[] pathToTemple = new Tile[] {
			new Tile(3213, 3376, 0), new Tile(3211, 3381, 0), new Tile(3210, 3386, 0), 
			new Tile(3211, 3391, 0), new Tile(3211, 3396, 0), new Tile(3212, 3401, 0), 
			new Tile(3212, 3406, 0), new Tile(3211, 3411, 0), new Tile(3211, 3416, 0), 
			new Tile(3211, 3421, 0), new Tile(3214, 3425, 0), new Tile(3219, 3427, 0), 
			new Tile(3224, 3429, 0), new Tile(3229, 3430, 0), new Tile(3234, 3430, 0), 
			new Tile(3239, 3430, 0), new Tile(3244, 3429, 0), new Tile(3249, 3429, 0), 
			new Tile(3254, 3429, 0), new Tile(3259, 3429, 0), new Tile(3264, 3429, 0), 
			new Tile(3269, 3429, 0), new Tile(3274, 3428, 0), new Tile(3279, 3428, 0), 
			new Tile(3283, 3431, 0), new Tile(3284, 3434, 0), new Tile(3286, 3439, 0), 
			new Tile(3284, 3444, 0), new Tile(3285, 3449, 0), new Tile(3287, 3454, 0), 
			new Tile(3290, 3458, 0), new Tile(3294, 3461, 0), new Tile(3299, 3462, 0), 
			new Tile(3304, 3462, 0), new Tile(3309, 3464, 0), new Tile(3314, 3466, 0), 
			new Tile(3319, 3467, 0), new Tile(3324, 3468, 0), new Tile(3329, 3470, 0), 
			new Tile(3331, 3475, 0), new Tile(3335, 3478, 0), new Tile(3340, 3480, 0), 
			new Tile(3344, 3483, 0), new Tile(3347, 3487, 0), new Tile(3352, 3489, 0), 
			new Tile(3357, 3487, 0), new Tile(3361, 3484, 0), new Tile(3366, 3483, 0), 
			new Tile(3371, 3485, 0), new Tile(3376, 3484, 0), new Tile(3381, 3483, 0), 
			new Tile(3386, 3484, 0), new Tile(3391, 3484, 0), new Tile(3396, 3486, 0), 
			new Tile(3401, 3487, 0), new Tile(3405, 3488, 0) };
	
 public boolean hasKey  = false;
 public Tile initTile;
 public int numStatue = 0;
 public boolean found = false; 
 public int statues[]  ={3493,3499,3494,3497,3495,3498,3496};
 
 public final int bankItems[] = {1925};
 
 
    public int itemsArrayGE[] = {0};//contains the states of items needing to be purchased.
	public int itemDID[] = {1925};//contains the ids of the items needing to be purchased.
	public int itemDAmount[] = {1};
	public int itemDPrice[] = {1000};//contains specific prices to use upon purchasing specific items.
	public String itemDString[] = {"Bucket"};//contains the names of the items needing to be purchased.
	
	public boolean activate() {
		return DeltaQuester.scriptToStart==35;
	}

	
	public void execute() {
		//Upon starting the quest these are the setting changes:
		//2171: 0 > 1
		//2172: 0 > 128
		DeltaQuester.numSteps =7;
		Method.foodSupport();
		Method.resetTeleporting();
		
		if(Method.useBank){
			if((Settings.get(2171) & 0x7) <= 6){//the time when we still need the bucket
			Method.useBank(bankItems, 1,1,90);
			}
		}else
		if (DeltaQuester.GEFeature) {
			Method.useGE(itemDString, itemDID, itemDPrice, itemDAmount);
		}else
		if((Settings.get(2171) & 0x7) == 6){
			cs6();
		}else
		if ((Settings.get(2171) & 0x7) == 5) {
			if ((Settings.get(2172) >> 7 & 0xF) == 9) {
				DeltaQuester.progress = 7;
				cs5();//Get to the top floor of the temple, unlock the cell door.
			} else {
				DeltaQuester.progress = 6;
				 cs4();//Enter the cave, investigate the statues, swap yellow
				// key for iron
			}
		}else
		if((Settings.get(2171)&0x7)==4){
			DeltaQuester.progress = 5;
			cs3();//Gather the yellow key and speak to the priest in his cell.
		}else
		if((Settings.get(2171)&0x3)==3){
			DeltaQuester.progress = 4;
			cs0();//Speak to the king about your accomplishments
		}else
		if((Settings.get(2171)&0x3)==2){
			DeltaQuester.progress = 3;
			cs2();//Enter the cave and kill the dog
		}else
		if((Settings.get(2171)&0x1)==1){
			DeltaQuester.progress = 2;
			cs1();//Speak to the people inside the temple through the door.
		}else {
			DeltaQuester.progress = 1;
			cs0();//Speak to the king and start the quest
		}
		
	}


	private void cs6() {
		NPC priest = NPCs.getNearest(1047);
		String opt[] = {""};
		Method.state("We must pour blessed water on a coffin");
		
	}


	private void cs5() {
		if(Game.floor()==2){
			if(new Tile(3413,3487,2).distanceTo()<6){
				Method.useItemOn(2945, 3463, "Use");
			}else new Tile(3413,3487,2).clickOnMap();
		}else
		   if (Game.floor() == 1) {
			if (new Tile(3409, 3485, 1).distanceTo() < 4) {
				Method.interactO(30489, "Climb", "Ladder");// Climb ladder on
															// second floor
			} else new Tile(3409, 3485, 1).clickOnMap();// Move closer to the
														// ladder
		}else if(new Tile(3409,3489,0).canReach()){
			if(new Tile(3415,3485,0).distanceTo()<6){//Walks to the staircase at the bottom floor.
					Method.interactO(30722, "Climb", "Staircase");
				}else Method.findPath(new Tile(3415,3485,0));
			
		} else if (new Tile(3405, 3488, 0).distanceTo() < 6) {
			Method.interactO(30707, "Open", "Door");
		} else cs1();// Get to the cell location in the building
		
		
	}


	private void cs4() {//Enter cave, investigate statues, swap keys
		SceneObject cavemarker = SceneEntities.getNearest(11147);
		if(cavemarker!=null){
			
			if(initTile!=null){
				Method.displayTileDifference(initTile);
				if(new Tile(initTile.x(),initTile.getY() -14,0).canReach() ||
						new Tile(initTile.x()+12,initTile.getY() -17,0).canReach()){
				
					if(found){
						Vars.DYNAMICV = false;
						Vars.DYNAMICV2 = false;
						if(Widgets.get(272).validate()){
							Widgets.get(272,18).click(true);
						}else Method.useItemOn(2944, statues[numStatue], "Use");
					}else switch(numStatue){
					
					case 0:
						if(new Tile(initTile.x()+12,initTile.getY() -17,0).distanceTo()<2){
							invest();
						}else new Tile(initTile.x()+12,initTile.getY() -17,0).clickOnMap();
						break;
						
					case 1:
						if(new Tile(initTile.x()+14,initTile.getY() -13,0).distanceTo()<2){
							invest();
						}else new Tile(initTile.x()+14,initTile.getY() -13,0).clickOnMap();
						break;
					
					case 2:
						if(new Tile(initTile.x()+19,initTile.getY() -12,0).distanceTo()<2){
							invest();
						}else new Tile(initTile.x()+19,initTile.getY() -12,0).clickOnMap();
						break;
						
					case 3:
						if(new Tile(initTile.x()+22,initTile.getY() -13,0).distanceTo()<2){
							invest();
						}else new Tile(initTile.x()+22,initTile.getY() -13,0).clickOnMap();
						break;
						
					case 4:
						if(new Tile(initTile.x()+22,initTile.getY() -16,0).distanceTo()<2){
							invest();
						}else new Tile(initTile.x()+22,initTile.getY() -16,0).clickOnMap();
						break;
						
					case 5:
						if(new Tile(initTile.x()+22,initTile.getY() -19,0).distanceTo()<2){
							invest();
						}else new Tile(initTile.x()+22,initTile.getY() -19,0).clickOnMap();
						break;
						
					case 6:
						if(new Tile(initTile.x()+18,initTile.getY() -20,0).distanceTo()<2){
							invest();
						}else new Tile(initTile.x()+18,initTile.getY() -20,0).clickOnMap();
						break;
					}
					
					
				}else
				if(new Tile(initTile.x(),initTile.getY() -11,0).distanceTo()<5){
					Method.interactO(3444, "Open", "Gate");
				}else new Tile(initTile.x(),initTile.getY() -11,0).clickOnMap();
				
			}else initTile = Players.getLocal().getLocation();
		}else cs2();//Get the player to the cave
		
	}


	private void invest() {//Investigates a statue, determining if it has the iron key
		if(Widgets.get(272).validate()){
			if(Widgets.get(272,17).getText().contains("key that unlocks")){
				found = true;
			}else numStatue++;
			}else Method.interactO(statues[numStatue], "Study", "Statue: " + numStatue);
		
	}


	private void cs3() {//Gather the yellow key and speak to the priest in his cell.
		NPC monk = NPCs.getNearest(1046);
		NPC priest = NPCs.getNearest(1047);
		GroundItem key  = GroundItems.getNearest(2944);
		String opt[]  ={"Yes","You're right,"};
		
		if(hasKey){
			Vars.DYNAMICV2 = false;
			if(Game.floor()==2){
				if(new Tile(3413,3487,2).distanceTo()<6){
					if(!Method.findOption(opt))
						if(!Method.isChatting("Drezel")){
							if(priest!=null && priest.getLocation().distanceTo()<6){
							Method.speakTo(1047, "Drezel");
							}else Method.state("Waiting for Drezel to come closer");
						}
				}else new Tile(3413,3487,2).clickOnMap();
			}else
			if(Game.floor()==1){
				if(new Tile(3409,3485,1).distanceTo()<4){
					Method.interactO(30489, "Climb", "Ladder");//Climb ladder on second floor
				}else new Tile(3409,3485,1).clickOnMap();//Move closer to the ladder
			}else
			if(!new Tile(3415,3485,0).canReach()){
				if(!Method.teleporting && Inventory.getItem(2944)==null){
					hasKey = false;
				}
				Method.interactInventory(2944, "Drop", "Key");
			}else
			if(new Tile(3415,3485,0).distanceTo()<6){//Walks to the staircase at the bottom floor.
				Method.interactO(30722, "Climb", "Staircase");
			}else Method.findPath(new Tile(3415,3485,0));
			
		}else if(!Method.teleporting && (Inventory.getItem(2944)!=null)){
			hasKey  =true;
		}else
		if(new Tile(3409,3489,0).canReach()){
			if(key!=null && key.getLocation().canReach()){
				if(key.getLocation().distanceTo()<6){
					Method.interactG(2944, "Take", "Key");
				}else key.getLocation().clickOnMap();
			}else
			if(Method.isInCombat()){
				Method.basicFightNPC(1046);
			}else if(monk!=null){
				if(monk.getLocation().distanceTo()<6){
					Method.npcInteract(monk.getId(), "Attack");
				}else monk.getLocation().clickOnMap();
			}
		}else
		if(new Tile(3405, 3488, 0).distanceTo()<5){
			Method.interactO(30708, "Open", "Door");
		}else cs1();
		
	}


	private void cs2() {//Enters the cave and kills the guard dog
		SceneObject cellar = SceneEntities.getNearest(30571);
		SceneObject cavemarker = SceneEntities.getNearest(11147);//whether or not we're in a cave.
		
		if(cavemarker!=null){
			Vars.DYNAMICV = false;
			if(Method.isInCombat()){
				Method.basicFightNPC(15255);
			}else Method.npcInteract(15255, "Attack");
		}else
		if(new Tile(3405,3504,0).distanceTo()<5){
			if(cellar!=null){
				Method.interactO(30571, "Open", "Trap door");
			}else Method.interactO(30572, "Climb", "Cave");
		}else if(Vars.DYNAMICV2){
			Method.findPath(new Tile(3405,3504,0));//outside cave
			
		}else if(new Tile(3409,3489,0).canReach() && Game.floor()==0){//first floor
			if(Method.getToTile(new Tile(3407,3489,0))){
				Method.interactO(30707, "Open", "Door");
			}
		}else if(new Tile(3410,3484,1).canReach() && Game.floor()==1){//inside temp[le second floor
			if(Method.getToTile(new Tile(3414,3486,1))){
				Method.interactO(30723, "Climb", "Staircase");
			}
		}else if(Game.floor()==2){//inside temple top floor
			if(Method.getToTile(new Tile(3408,3485,2))){
				Method.interactO(30733, "Climb", "Staircase");
			}
		} else cs1();//if you're not in the temple/outside of it
		
	}


	private void cs1() {//Got to the temple and speak to the people inside through the door.
		SceneObject gate = SceneEntities.getNearest(24370);
		SceneObject cavemarker = SceneEntities.getNearest(11147);//whether or not we're in a cave.
		final String opt[] = {"Sure","Roald sent me to"};
		
		if(new Tile(3405, 3488, 0) .distanceTo()<6){
			Vars.DYNAMICV2 = true;//For the next step
			Method.skipPics();
			if(!Method.findOption(opt))
			if(!Method.isChatting("People")){
				if(Players.getLocal().getAnimation()!=9105)
				Method.interactO(30707, "Knock","Door");
			}
		}else if(cavemarker!=null && (Settings.get(2171) & 0x7) != 5){
			Vars.DYNAMICV2  =true;
			Method.interactO(30575, "Climb", "Ladder");
		}else if(Vars.DYNAMICV2){
			Method.findPath(new Tile(3405, 3488, 0));
		}else if(Vars.DYNAMICV){
			if(new Tile(3318,3468,0).distanceTo()<6 && gate!=null){
				Method.interactO(24370, "Open", "Gate");
			}else
			Method.walking(pathToTemple, "Walking to the temple", false);
		}else if(Vars.VARROKLODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(Vars.VARROKTELEPORT);
		
	}


	private void cs0() {//Speak to the king
		NPC king = NPCs.getNearest(648);
		final String opt[]  ={"Sure."};
		
		if(	new Tile(3216, 3473, 0).distanceTo()<6 || (king!=null && king.getLocation().distanceTo()<6)){
			if(king!=null){
				if(king.getLocation().canReach()){
					Vars.DYNAMICV = false;
					Vars.DYNAMICV2 = false;
					if(!Method.findOption(opt))
					if(!Method.isChatting("king Roald")){
						Method.speakTo(648, "king Roald");
					}
				}else Method.interactO(15536, "Open", "Door");
			}else Method.state("King is null");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToKing, "Walking to king Roald", false);
		}else if(Vars.VARROKLODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(Vars.VARROKTELEPORT);
		
	}

}*/
