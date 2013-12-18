/*package quests;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.GroundItem;

public class GertrudesCat extends Node{

	//Walking paths.
	public final Tile[] pathToGertrudeFV = new Tile[] { 
			new Tile(3203,3375, 0),new Tile(3190,3377, 0),
			new Tile(3176,3382, 0), new Tile(3164,3391, 0),
			new Tile(3161,3407, 0), new Tile(3151,3412, 0) };
	
	public final Tile[] pathToKidFV = new Tile[] { 
			new Tile(3211,3388, 0),new Tile(3211,3402, 0),
			new Tile(3210,3416, 0), new Tile(3215,3429, 0),
			new Tile(3219,3434, 0) };
	
	public final Tile[] pathToLumber = new Tile[] { 
			new Tile(3213,3390, 0),new Tile(3211,3402, 0),
			new Tile(3211,3418, 0), new Tile(3221,3428, 0),
			new Tile(3236,3429, 0),new Tile(3252,3428, 0),
			new Tile(3260,3428,0),	new Tile(3271,3428, 0),
			new Tile(3282,3429, 0),
			new Tile(3284,3441, 0) ,new Tile(3285,3453, 0),
			new Tile(3289,3465, 0), new Tile(3289,3479, 0),
			new Tile(3294,3492, 0), new Tile(3294,3497,0)};
	
	
	//G.E
	public int itemsArray[] = {0,0};//contains the states of items needing to be purchased.
	public int itemDID[] = {1927,327};//contains the ids of the items needing to be purchased.
	public int itemDAmount[] = {1,1};
	public int itemDPrice[] = {700,1825};//contains specific prices to use upon purchasing specific items.
	public String itemDString[] = {"Bucket of Milk", "Raw Sardine"};//contains the names of the items needing to be purchased.
	
	
	//Other
	public boolean init = false;
	public int requiredItems[] = {0,0};
	public Tile[] crateLoc = new Tile[]{
			new Tile(3304,3498,0),new Tile(3311,3498,0),
			new Tile(3315,3514,0),new Tile(3307,3508,0),
			new Tile(3303,3507,0),new Tile(3298,3513,0)};
	
	public boolean check = false;
	public int bankItems[] = {13236,1552,1927,327,1573,1552};
	public void execute() {
		/*
		 * Settings used:
		 * 2175-main steps
		 * 
		if(!init){
			if(!Method.VarrokLodeIsActive()){
				Method.state("Varrok lodestone must be active, skipping quest");
				Task.sleep(2000);
				DeltaQuester.e  =true;
			}else init = true;
		}else if(Method.useBank && (Settings.get(2175) & 0x7) !=6){
			Method.useBank(bankItems, 1,1,90);
		}else
		if (DeltaQuester.GEFeature && (Settings.get(2175) & 0x7) !=6) {
			Method.useGE(itemDString, itemDID, itemDPrice, itemDAmount);
		}else{
			Quests.checkForGates();
			Quests.getSettings(2175);
			if(Quests.varrokLode.distanceTo()<6)
				Method.teleporting = false;
			DeltaQuester.numSteps = 7;
			
			if((Settings.get(2175) & 0x7) ==6){
				DeltaQuester.progress = 7;
				DeltaQuester.state = "The Gertrudes Cat quest has been completed";
				Task.sleep(2000);
				DeltaQuester.e = true;
			}else
			if((Settings.get(2175) & 0x7) ==5){
				DeltaQuester.progress = 6;
				cS0();
			}else
			if((Settings.get(2175) & 0x7) ==4){
				DeltaQuester.progress = 5;
				cS3();
			}else
			if((Settings.get(2175) & 0x3) ==3){
				DeltaQuester.progress = 4;
				cS2();
			}else
			if((Settings.get(2175) & 0x3) ==2){
				DeltaQuester.progress = 3;
				cS2();
			}else
			if((Settings.get(2175) & 0x1) ==1){
				cS1();//Bribe the punks.
			}else
			if((Settings.get(2175) & 0x1) ==0){
				DeltaQuester.progress = 1;
				cS0();//start the quest.
			}
		}
		
	}
	

	private void cS3() {
			if(Inventory.getItem(13236)!=null){
				if(new Tile(3309,3510,1).canReach()){
					Quests.DYNAMICV = false;
					if(Quests.tActive()){
						//Test.
					}else{
					if(Inventory.getSelectedItem()!=null){
						Quests.npcInteract(759, "Use");
					}else Quests.interactInventory(13236, "Use");
					}
					
				} else if (new Tile(3298, 3499, 0).canReach()) {
					if (new Tile(3310, 3507, 0).distanceTo() < 4) {
						Quests.interactO(24354, "Climb");
					} else
						Quests.findPath(new Tile(3310, 3507, 0));
				} else if (new Tile(3294, 3498, 0).distanceTo() < 6) {
					Quests.interactO(31149, "Squeeze");
				} else if (Quests.DYNAMICV) {
					Quests.walking(pathToLumber, "Walking to Secret Hide-out",false);
				} else if (Quests.varrokLode.distanceTo() < 5) {
					Quests.DYNAMICV = true;
				} else
					Method.teleportTo(51);// Varrok teleport..
			} else if (Quests.tActive()) {
				Quests.clickContinue();
			} else {

				if (new Tile(3301, 3505, 0).canReach()) {
					for (int num = 0; num < 7;) {
						Quests.state("Gathering kittens");
						if (Quests.tActive()) {
							if (Widgets.get(1186).validate()) {
								Quests.clickContinue();
								num = 7;
							} else
								num++;
						} else {
					if (crateLoc[num].distanceTo()<3){
						if(!Players.getLocal().isIdle() && !Players.getLocal().isMoving()){
								num++;
							}else if(Players.getLocal().isIdle()){
								Quests.npcInteract(7740, "Search");
							}
							
					}else Quests.findPath(crateLoc[num]);
					}
				}
				
			}else if(new Tile(3294,3498,0).distanceTo()<6){
				Quests.interactO(31149, "Squeeze");
			}else if (new Tile(3309,3510,1).canReach()){
				Quests.interactO(24355, "Climb");
			}else if(Quests.DYNAMICV){
				Quests.walking(pathToLumber, "Walking to Secret Hide-out",false);
			}else if(Quests.varrokLode.distanceTo()<5){
				Quests.DYNAMICV = true;
			}else Method.teleportTo(51);//Varrok teleport..
			}
				}
			
		
	

	private void cS2() {
		if(!Method.teleporting){
		if(requiredItems[0]==1){//Do we have a doogle leaf?
			if(requiredItems[1]==1){//Do we have the ....sandwich?
		if(new Tile(3310,3508,1).canReach()){
			if(Settings.get(2175)==3){//If we need to feed the cat.
			if (check){
				if(Quests.chat(1186, 1, "Then she mews")){
					check = false;
				}else{
				if(Quests.tActive()){
				if(Quests.chat(1186, 1, "hungry")){
					Quests.clickContinue();
				}else if(Quests.chat(1186, 1, "")){
					Quests.clickContinue();
				}else if(Quests.chat(1184, 13, "") || Quests.chat(1191, 17, "")){
					Quests.clickContinue();
				}
				}else if(Inventory.getItem(1552)!=null){
					if(Inventory.getSelectedItem()!=null){
						Quests.npcInteract(759, "Use");
						Task.sleep(1200,1300);
					}else Quests.interactInventory(1552, "Use");
				}else Quests.state("Can't find food.");
				}
			}else{
				if(Quests.tActive()){
					if(Quests.chat(1186, 1, "hungry")){
						check = true;
					}else Quests.clickContinue();
				}else {
					Quests.state("Picking up cat..");
					Quests.npcInteract(759, "Pick-up");
					Task.sleep(2000,2300);
				}
			}
			}else{//We need to feed the cat fluid..
				if (check){
					if(Quests.tActive()){
					if(Quests.chat(1186, 1, "thirsty")){
						Quests.clickContinue();
					}else if(Quests.chat(1186, 1, "again")){
						check = false;
					}else if(Quests.chat(1186, 1, "")){
						Quests.clickContinue();
					}else if(Quests.chat(1184, 13, "") || Quests.chat(1191, 17, "")){
						Quests.clickContinue();
					}
					}else if(Inventory.getItem(1927)!=null){
						if(Inventory.getSelectedItem()!=null){
							Quests.npcInteract(759, "Use");
						}else Quests.interactInventory(1927, "Use");
					}else Quests.state("Can't find bucket of milk...");
				}else{
					if(Quests.tActive()){
						if(Quests.chat(1186, 1, "thirsty")){
							check = true;
						}
					}else {
						Quests.state("Picking up cat..");
						Quests.npcInteract(759, "Pick-up");
					}
				}
				
			}
			
		}else{
		if(new Tile(3298,3499,0).canReach()){
			if(new Tile(3310,3507,0).distanceTo()<4){
				Quests.interactO(24354, "Climb");
			}else Quests.findPath(new Tile(3310,3507,0));
		}else if(new Tile(3294,3498,0).distanceTo()<6){
			Quests.interactO(31149, "Squeeze");
		}else if(Quests.DYNAMICV){
			Quests.walking(pathToLumber, "Walking to Secret Hide-out",false);
		}else if(Quests.varrokLode.distanceTo()<5){
			Quests.DYNAMICV = true;
		}else Method.teleportTo(51);//Varrok teleport..
		}
				} else if (Inventory.getItem(1552) != null) {
					requiredItems[1] = 1;
				} else if (Quests.tActive()) {
					Quests.clickContinue();
				} else if (Inventory.getSelectedItem() != null) {
					Quests.interactInventory(327, "Use");
				} else
					Quests.interactInventory(1573, "Use");
			} else if (Inventory.getItem(1573) != null|| Inventory.getItem(1552) != null) {
				Quests.DYNAMICV = false;
				requiredItems[0] = 1;
			} else if (new Tile(3152, 3399, 0).distanceTo() < 15) {
				GroundItem woodle = GroundItems.getNearest(1573);
				if (woodle.isOnScreen() && !Players.getLocal().isMoving()) {
					Mouse.click(Quests.getOnScreenPoint(woodle), true);
				} else woodle.getLocation().clickOnMap();
			} else if (Quests.DYNAMICV) {
				Quests.walking(pathToGertrudeFV, "Walking to Gertrude",false);
			} else if (Quests.varrokLode.distanceTo() < 6)
				Quests.DYNAMICV = true;
			else
				Method.teleportTo(51);
		} else
			Method.teleportTo(51);
	}

	private void cS1() {
		if(new Tile(3218, 3434,0).distanceTo()<6){
			if(Quests.tActive()){
				Quests.DYNAMICV = false;
				if(Quests.chat(1184, 13, "") || Quests.chat(1191, 17, "")){
					Quests.clickContinue();
				}else if(Quests.findOption("What will make")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("Okay then,")){
					Quests.clickOption(1188, Quests.value);
				}
			}else if(NPCs.getNearest(781).isOnScreen())
				Quests.speakTo(781);
			else NPCs.getNearest(781).getLocation().clickOnMap();
		}else if (Quests.DYNAMICV){
			Quests.walking(pathToKidFV, "Walking to kids.",false);
		}else if(Quests.varrokLode.distanceTo()<6)
			Quests.DYNAMICV = true;
		else Method.teleportTo(51);
		
	}

	private void cS0() {
		 
		if(Widgets.get(1186).validate()){
			Quests.clickContinue();
		}
		if(new Tile(3151,3412,0).distanceTo()<6){//Gertrudes Area.
			if(Quests.tActive()){
				Quests.DYNAMICV = false;
				if (Quests.chat(1191, 17, "") || Quests.chat(1184, 13, "")) {
					Quests.clickContinue();
				} else if (Quests.findOption("Well, I")) {
					Quests.clickOption(1188, Quests.value);
				} else if (Widgets.get(1186).validate()) {
					Quests.clickContinue();
				}
			} else if (NPCs.getNearest(780).isOnScreen()){
				if(NPCs.getNearest(780).getLocation().canReach())
				Quests.speakTo(780);
				else Quests.interactO(24376, "Open");
			}else NPCs.getNearest(780).getLocation().clickOnMap();
		}else if (Quests.DYNAMICV){
			Quests.walking(pathToGertrudeFV, "Walking to Gertrude.",false);
		}else if(Quests.varrokLode.distanceTo()<6)
			Quests.DYNAMICV = true;
		else Method.teleportTo(51);//Teleport To Varrock.
	}


	public boolean activate(){
		return(DeltaQuester.scriptToStart==15);
	}

}*/
