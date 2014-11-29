package dqquests;
/*package quests;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Equipment;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.Tile;

public class TheBlackKnightsFortress extends Node{


	public boolean cT = true;//check teleport.
	public boolean cE = false;//check equipment.
	public boolean checkEquip  =true;
	public int armour[] = {1139,1101};

	public final Tile[] pathToCastle = new Tile[] {
			new Tile(2965,3393, 0), new Tile(2963,3379, 0),
			new Tile(2965,3366, 0), new Tile(2964,3354, 0),
			new Tile(2966,3343, 0), new Tile(2966,3339, 0)};
	
	public final Tile[] pathToBlackCastle = new Tile[] {
			new Tile(2967, 3403, 0), new Tile(2969, 3408, 0),
			new Tile(2974, 3409, 0), new Tile(2978, 3413, 0),
			new Tile(2982, 3417, 0), new Tile(2986, 3421, 0),
			new Tile(2988, 3426, 0), new Tile(2992, 3431, 0),
			new Tile(2997, 3433, 0), new Tile(2998, 3433, 0),
			new Tile(3004, 3433, 0), new Tile(3010, 3433, 0),
			new Tile(3015, 3434, 0), new Tile(3015, 3440, 0), 
			new Tile(3014, 3445, 0), new Tile(3015, 3451, 0),
			new Tile(3019, 3455, 0), new Tile(3023, 3459, 0),
			new Tile(3025, 3464, 0), new Tile(3030, 3467, 0),
			new Tile(3031, 3472, 0), new Tile(3030, 3477, 0),
			new Tile(3025, 3479, 0), new Tile(3023, 3484, 0),
			new Tile(3025, 3489, 0), new Tile(3024, 3494, 0),
			new Tile(3026, 3499, 0), new Tile(3031, 3502, 0),
			new Tile(3033, 3507, 0), new Tile(3029, 3503, 0),
			new Tile(3024, 3505, 0), new Tile(3020, 3509, 0),
			new Tile(3016, 3514, 0), new Tile(3016, 3514, 0)};

	public boolean init  = false;
	public int itemsArray[] = {0,0,0};//contains the states of items needing to be purchased.
	public int itemDID[] = {1101,1965,1139};//contains the ids of the items needing to be purchased.
	public int itemDAmount[] = {1,1,1};
	public int itemDPrice[] = {2500,1500,1400};//contains specific prices to use upon purchasing specific items.
	public String itemDString[] = {"Iron chainbody", "Cabbage", "Bronze helm"};//contains the names of the items needing to be purchased.
	
	public int bankItems[]  ={1101, 1139, 1965,1139};
	public void execute() {
		Method.foodSupport();
		
		if(!init){
			if(!Method.FaladorLodeIsActive()){
				Method.state("Falador lodestone must be active, skipping quest.");
				Task.sleep(2000);
				DeltaQuester.e  =true;
			}else if(Skills.getLevel(Skills.DEFENSE)<10){
				Method.state("Defense level of 10 is needed, skipping quest.");
				Task.sleep(2000);
				DeltaQuester.e  =true;
			}else init = true;
		}else if(Method.useBank){
			Method.useBank(bankItems, 15,1,90);
		}else
		 if (DeltaQuester.GEFeature) {
				Method.useGE(itemDString, itemDID, itemDPrice, itemDAmount);
			}if(Method.useBank){
				Method.useBank(bankItems, 15,1,90);
			}else if(cE){
				DeltaQuester.numSteps = 5;
				Quests.getSettings(2552);
				switch(Quests.stepX){
				
				case 0:
					DeltaQuester.progress = 1;
					cS0();//Start quest.
					break;
				case 1:
					DeltaQuester.progress = 2;
					cS1();//Listen at grill.
					break;
					
				case 2:
					DeltaQuester.progress = 3;
					cS2();
					break;
				case 3:
					DeltaQuester.progress = 4;
					cS0();
					break;
					
				case 4:
					DeltaQuester.progress = 5;
					DeltaQuester.state = "The Black Knights Fortress quest has been completed.";
					Task.sleep(2000);
					DeltaQuester.e = true;
					break;
					
					default:
						Quests.state("Cannot find setting: " + Settings.get(2552));
						break;
				}
				
				
			}else if(!checkEquip){
				if(Inventory.getItem(1101, 1139)!=null){
					Quests.interactInventory(1101, "Wear");
					Quests.interactInventory(1139, "Wear");
					checkEquip = true;
				}else checkEquip = true;
			}else if(Equipment.appearanceContainsAll(armour)){
				cE=true;
			}else checkEquip = false;
		}

	
	
	private void cS2() {
		if(Settings.get(1114)==1){
			if(Quests.tActive()){
				Quests.DYNAMICV = false;
				Quests.clickContinue();
			}
		}else
		if(new Tile(3029,3507,1).canReach()){
			if(Inventory.getSelectedItem()!=null){
				Quests.interactO(2336, "Use");
			}else Quests.interactInventory(1965, "Use");
		}else
		if(new Tile(3024,3517,1).isOnMap() && SceneEntities.getNearest(2336)!=null){
			if(new Tile(3030,3511,1).distanceTo()<8){
				Quests.DYNAMICV = true;
				Quests.interactO(2341, "Push");
			}else new Tile(3030,3511,1).clickOnMap();
		}else
		if(new Tile(3023,3516,0).canReach()){
			Quests.interactO(17159, "Climb-up");
		}else
		if(new Tile(3012,3516,0).canReach()){
			if(Quests.tActive()){
				if(Quests.chatting()){
					Quests.clickContinue();
				}else if(Quests.findOption("I don't care")){
					Quests.clickOption(1188, Quests.value);
				}
			}else Quests.interactO(2338, "Open");
		}else
	if(new Tile(3016,3514,0).distanceTo()<5){
		 if(Quests.tActive()){
			if(Quests.chatting()){
				Quests.clickContinue();
			}else if(Quests.findOption("but I work")){
				Quests.clickOption(1188, Quests.value);
			}
		}else Quests.interactO(2337, "Open");
	}else if(Quests.DYNAMICV){
		Quests.walking(pathToBlackCastle, "Walking to the Black Knights castle",false);
	}else if(Quests.FALADORLODE.distanceTo()<5){
		Quests.DYNAMICV = true;
	}else Method.teleportTo(46);
		
	}



	private void cS1() {
		if(new Tile(3026,3507,0).canReach()){
			if(Quests.tActive()){
				Quests.DYNAMICV = false;
				Quests.clickContinue();
			}else {Quests.interactO(2342, "Listen");
				Task.sleep(1300,1400);
			}
		}else
		if(new Tile(3024,3511,1).canReach()){
			Quests.interactO(17149, "Climb-down");
		}else
		if(new Tile(3025,3514,1).canReach()){
			Quests.interactO(2339, "Open");
		}else
		if(new Tile(3022,3513,2).canReach()){
			if(new Tile(3026,3513,2).distanceTo()<2){
				Quests.interactO(17149,"Climb-down");
			}else Quests.findPath(new Tile(3026,3513,2));
		}else
		if(new Tile(3023,3514,1).canReach()){
			if(new Tile(3023,3514,1).distanceTo()<3){
				Quests.interactO(17148, "Climb-up");
			}else Quests.findPath(new Tile(3023,3514,1));
		}else
		if(new Tile(3016,3516,1).canReach()){
			Quests.interactO(14749, "Open");
		}else
		if(new Tile(3016,3518,2).canReach()){
			if(new Tile(3018,3516,2).distanceTo()<2){
			Quests.interactO(17149, "Climb-down");
			}else Quests.findPath(new Tile(3018,3515,2));
		}else
		if(new Tile(3015,3518,1).canReach()){
			Quests.interactO(17148, "Climb-up");
		}else
		if(new Tile(3015,3518,0).canReach()){
			Quests.interactO(17148, "Climb-up");
		}else
		if(new Tile(3012,3516,0).canReach()){
			Quests.interactO(2341, "Push");
		}else
	if(new Tile(3016,3514,0).distanceTo()<5){
		 if(Quests.tActive()){
			if(Quests.chatting()){
				Quests.clickContinue();
			}else if(Quests.findOption("but I work")){
				Quests.clickOption(1188, Quests.value);
			}
		}else Quests.interactO(2337, "Open");
	}else if(Quests.DYNAMICV){
		Quests.walking(pathToBlackCastle, "Walking to the Black Knights castle",false);
	}else if(Quests.FALADORLODE.distanceTo()<5){
		Quests.DYNAMICV = true;
	}else Method.teleportTo(46);
		
	}



	private void cS0() {
		 
		if(new Tile(2956,3337,2).canReach()){//3rd floor by sir amik
			if(Quests.tActive()){
				Quests.DYNAMICV = false;
				if(Widgets.get(1186).validate()){
					Quests.clickContinue();
				}else
				if(Quests.chatting()){
					Quests.clickContinue();
				}else if(Quests.findOption("I seek a")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("I laugh in")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("Ok, I'll")){
					Quests.clickOption(1188, Quests.value);
				}
			}else Quests.speakTo(608);
		}else if(new Tile(2960,3340,1).canReach()){
			Quests.interactO(11729, "Climb-up");
		}else if(new Tile(2957,3338,0).canReach()){
			if(new Tile(2957,3338,0).distanceTo()<5){
				Quests.interactO(11729, "Climb-up");
			}else Quests.findPath(new Tile(2957,3338,0));
		}else if(new Tile(2966,3339,0).distanceTo()<6){
			Quests.interactO(11721, "Open");
		}else if(Quests.DYNAMICV){
			Quests.walking(pathToCastle, "Walking to Sir Amik Varze",false);
		}else if(Quests.FALADORLODE.distanceTo()<5){
			Quests.DYNAMICV = true;
		}else Method.teleportTo(46);
		
	}
	
	public boolean activate() {
		return DeltaQuester.scriptToStart==20;
	}

}
*/