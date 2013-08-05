/*package quests;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.Tile;



public class FishingContest extends Node{
	
	public final static Tile[] pathToDwarf = new Tile[] {
		new Tile(2898, 3544, 0), new Tile(2897, 3537, 0), new Tile(2896, 3530, 0), 
		new Tile(2896, 3523, 0), new Tile(2896, 3516, 0), new Tile(2895, 3509, 0), 
		new Tile(2895, 3502, 0), new Tile(2892, 3495, 0), new Tile(2891, 3488, 0), 
		new Tile(2890, 3481, 0), new Tile(2886, 3475, 0), new Tile(2881, 3469, 0), 
		new Tile(2878, 3464, 0) };
	
	public final static Tile[] pathToOverMountain = { 
		new Tile(2897, 3543, 0), new Tile(2898, 3534, 0), new Tile(2898, 3525, 0), 
		new Tile(2898, 3515, 0), new Tile(2897, 3506, 0), new Tile(2894, 3497, 0), 
		new Tile(2893, 3488, 0), new Tile(2893, 3479, 0), new Tile(2893, 3470, 0), 
		new Tile(2891, 3461, 0), new Tile(2887, 3452, 0), new Tile(2886, 3443, 0), 
		new Tile(2884, 3434, 0), new Tile(2882, 3425, 0), new Tile(2874, 3430, 0), 
		new Tile(2870, 3439, 0), new Tile(2864, 3446, 0), new Tile(2862,3458,0),
		new Tile(2858,3463,0), new Tile(2854,3472,0)};
	
	public final static Tile[] pathToGrandpaJack ={ 
		new Tile(2842, 3466, 0), new Tile(2844, 3460, 0), new Tile(2847, 3454, 0), 
		new Tile(2850, 3448, 0), new Tile(2854, 3443, 0), new Tile(2855, 3437, 0), 
		new Tile(2849, 3434, 0), new Tile(2843, 3434, 0), new Tile(2837, 3434, 0), 
		new Tile(2831, 3437, 0), new Tile(2825, 3437, 0), new Tile(2819, 3435, 0), 
		new Tile(2813, 3435, 0), new Tile(2807, 3435, 0), new Tile(2801, 3436, 0), 
		new Tile(2795, 3435, 0), new Tile(2789, 3434, 0), new Tile(2783, 3434, 0), 
		new Tile(2777, 3433, 0), new Tile(2771, 3431, 0), new Tile(2765, 3429, 0), 
		new Tile(2759, 3429, 0), new Tile(2753, 3429, 0), new Tile(2747, 3428, 0), 
		new Tile(2741, 3428, 0), new Tile(2735, 3427, 0), new Tile(2729, 3426, 0), 
		new Tile(2723, 3428, 0), new Tile(2718, 3432, 0), new Tile(2712, 3433, 0), 
		new Tile(2707, 3437, 0), new Tile(2701, 3441, 0), new Tile(2695, 3442, 0), 
		new Tile(2689, 3444, 0), new Tile(2683, 3446, 0), new Tile(2677, 3447, 0), 
		new Tile(2671, 3448, 0), new Tile(2665, 3447, 0), new Tile(2659, 3445, 0), 
		new Tile(2653, 3444, 0), new Tile(2649, 3445, 0) };
	
	public final static Tile[] pathToFenceWorm = new Tile[] { 
		new Tile(2648, 3445, 0), new Tile(2655, 3446, 0), new Tile(2660, 3451, 0), 
		new Tile(2666, 3456, 0), new Tile(2670, 3462, 0), new Tile(2674, 3468, 0), 
		new Tile(2676, 3475, 0), new Tile(2679, 3482, 0), new Tile(2680, 3489, 0), 
		new Tile(2682, 3496, 0), new Tile(2682, 3503, 0), new Tile(2677, 3508, 0), 
		new Tile(2670, 3510, 0), new Tile(2663, 3510, 0), new Tile(2657, 3506, 0), 
		new Tile(2659, 3500, 0) };
	
	public final Tile[] pathToWorms  = new Tile[] { 
			new Tile(2663, 3499, 0), new Tile(2665, 3495, 0), new Tile(2664, 3491, 0), 
			new Tile(2661, 3487, 0), new Tile(2657, 3485, 0), new Tile(2652, 3485, 0), 
			new Tile(2648, 3486, 0), new Tile(2643, 3487, 0), new Tile(2639, 3489, 0), 
			new Tile(2635, 3491, 0), new Tile(2632, 3492, 0), new Tile(2630, 3494, 0) };
	
	public boolean aWWM = false;
	public boolean overM = false;
	public boolean init = false;
	
	public int itemsArray[] = {0,0};//contains the states of items needing to be purchased.
	public int itemDID[] = {307,1550};//contains the ids of the items needing to be purchased.
	public int itemDAmount[] = {1,1};
	public int itemDPrice[] = {1500,1500};//contains specific prices to use upon purchasing specific items.
	public String itemDString[] = {"Fishing rod", "Garlic"};//contains the names of the items needing to be purchased.
	
	public int bankItems[] = {338,1550,25,27};
	public boolean activate() {
		return DeltaQuester.scriptToStart==24;
	}

	public void execute() {
		if(Vars.BURTHORPELODE.distanceTo()<10){
			Method.teleporting = false;
		}
		Method.foodSupport();
		if(!init){
			if(Skills.getLevel(Skills.FISHING)<10){
				Method.state("Fishing level of 10 is required, skipping quest");
				Task.sleep(2000);
				DeltaQuester.e  =true;
			}else init = true;
		}else if(Method.useBank &&(Settings.get(2331) & 0x7) !=5){
			Method.useBank(bankItems, 8,1,90);
		}else
		if (DeltaQuester.GEFeature &&(Settings.get(2331) & 0x7) !=5) {
			Method.useGE(itemDString, itemDID, itemDPrice, itemDAmount);
		}else{
			DeltaQuester.numSteps = 6;
			if((Settings.get(2331) & 0x7) ==5){
				DeltaQuester.progress = 6;
				DeltaQuester.state = "The Fishing Contest quest has been completed.";
				Task.sleep(2000);
				DeltaQuester.e = true;
			}else
			if((Settings.get(2331) & 0x7) ==4){
				DeltaQuester.progress = 5;
				cs0();
			}else
			if((Settings.get(2331) & 0x3) ==3){
				DeltaQuester.progress = 4;
				cs4();//Fish, then claim the trophy
			}else
			if((Settings.get(2331) & 0x3) ==2){
				DeltaQuester.progress = 3;
				cs3();//Stash garlic in the pipe 
			}else
			if((Settings.get(2331) & 0x1) ==1){
				DeltaQuester.progress = 2;
				cs2();//Gather the red worm and enter the competition
			}else
			if((Settings.get(2331) & 0x1) ==0){
				DeltaQuester.progress = 1;
				cs0();//Start the quest.
			}
		}		
	}

	private void cs4() {
		if (new Tile(2641, 3440, 0).distanceTo() < 13) {
			while (!Method.teleporting&&Inventory.getItem(338) == null) {
				if (Players.getLocal().isIdle()) {
					Quests.interactO(2759, "Bait");
				}
			}if(Widgets.get(1186).validate()){
				Quests.clickContinue();
			}else
			if(Quests.tActive()){
				if(Quests.chatting()){
					Quests.clickContinue();
				}else if(Quests.findOption("I have this")){
					Quests.clickOption(1188, Quests.value);
				}
			}else Quests.speakTo(225);//Speak to the fisherman judge
		}else cs2();
		
	}

	private void cs3() {
		if (new Tile(2641, 3440, 0).distanceTo() < 13) {
			while(Widgets.get(1189).validate()){
				Quests.clickContinue();
			}
			if(!Method.teleporting&&Inventory.getSelectedItem()!=null){
				if(new Tile(2639,3444,0).distanceTo()<4){
				Quests.interactO(48842, "Use");
				}else new Tile(2639,3444,0).clickOnMap();
			}else Quests.interactInventory(1550, "Use");
		}else cs2();
	}

	private void cs2() {
		if((!Method.teleporting&&Inventory.getItem(25)!=null) && Inventory.getItem(25).getStackSize() >=3 || new Tile(2641, 3440, 0).canReach()){
			if(new Tile(2649,3446,0).distanceTo()<15){
				if(NPCs.getNearest(227)!=null && NPCs.getNearest(227).isOnScreen()){
					if (Widgets.get(1189).validate()) {
						Quests.clickContinue();
					} else if (new Tile(2641, 3440, 0).canReach()) {
						
						if(Quests.tActive()){
							if(Quests.chatting()){
								Quests.clickContinue();
							}else if(Quests.findOption("I'll enter")){
								Quests.clickOption(1188, Quests.value);
							}
						}else Quests.speakTo(225);
					} else if (Quests.tActive()) {
						Quests.DYNAMICV = false;
						Quests.DYNAMICV2 = false;
						if (Quests.chatting()) {
							Quests.clickContinue();
						}
				}else Quests.interactO(47, "Open");
				}else NPCs.getNearest(227).getLocation().clickOnMap();
			}else if(new Tile(2662,3497,0).canReach()){
				if(new Tile(2663,3500,0).distanceTo()<7){
					Quests.interactO(51, "Squeeze");
				}else//Walk to the exit of the fenced area.
				Quests.findPath(new Tile(2663,3500,0));
			}else Quests.walking(pathToFenceWorm, "Walking back to the fishing contest", true);
			
		}else//Gather the red worms for bait.
		if(new Tile(2663,3497,0).canReach()){
			if(new Tile(2630,3493,0).distanceTo()<6){
			Quests.interactO(2992, "Check");
			}else {
				Quests.walking(pathToWorms, "Walking to the red worms", false);
				Task.sleep(500,600);
			}
		}else
		if(new Tile(2659, 3500, 0).distanceTo()<6){
			if(Players.getLocal().isIdle())
			Quests.interactO(51, "Squeeze");
		}else if(Quests.DYNAMICV2){
			Quests.walking(pathToFenceWorm, "Walking to the worm location", false);
		}else if(new Tile(2649, 3446, 0).distanceTo()<8){
			Quests.DYNAMICV2 = true;
		}else cs1();//get to Granpda Jacks general location.
		
	}

	private void cs1() {
	
		if(new Tile(2649, 3446, 0).distanceTo()<8){
			if(new Tile(2648,3450,0).canReach()){
				if(Quests.tActive()){
					if(Quests.chatting()){
						Quests.clickContinue();
					}else if(Quests.findOption("Are you entering the fishing")){
						Quests.clickOption(1188, Quests.value);
					}else if(Quests.findOption("I don't suppose you")){
						Quests.clickOption(1188, Quests.value);
					}
				}else Quests.speakTo(230);
			}else Quests.interactO(48961, "Open");
			overM = false;
		}else if(Quests.DYNAMICV){
			if(overM){
				Quests.walking(pathToGrandpaJack, "Walking to Hemenster", false);
			}else if((Settings.get(3) >> 3 & 1)==1){//if Catherby lodestone is active.
				if(new Tile(2831,3451,0).distanceTo()<20){
					overM = true;
				}else Method.teleportTo(43);
			}else if(aWWM){
				if(new Tile(2840,3465,0).distanceTo()<7){
					overM = true;
					aWWM = false;
				}else Quests.findPath(new Tile(2840,3465,0));
			}else if(new Tile(2854,3472,0).distanceTo()<6){
				Quests.state("Setting aWWM to true");
				aWWM = true;
			}else
			Quests.walking(pathToOverMountain, "Walking to Hemenster", false);
		}else if(Quests.BURTHORPELODE.distanceTo()<5 || Quests.CATHERBYLODE.distanceTo()<5){
			Quests.DYNAMICV = true;
		}else if((Settings.get(3) >> 3 & 1)==1){
			Method.teleportTo(43);//Catherby tele.
		}else Method.teleportTo(42);//Burthhorpe tele.
		
	}

	private void cs0() {//Start the quest.
		 
		if(new Tile(2878, 3463, 0).distanceTo()<10){//The dwarf area.
			while(Widgets.get(1189).validate()){
				Quests.clickContinue();
			}
			if(Quests.tActive()){
				Quests.DYNAMICV = false;
				if(Quests.chatting()){
					Quests.clickContinue();
				}else if(Quests.findOption("I was wondering what")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("Why not?")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("If you were my friend")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("Well, let's be")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("And how am I meant")){
					Quests.clickOption(1188, Quests.value);
				}
			}else Quests.speakTo(232);//Speak to the dwarf.
		}else if(Quests.DYNAMICV){
			Quests.walking(pathToDwarf, "Walking to the dwarf", false);
		}else if(Quests.BURTHORPELODE.distanceTo()<10){
			Quests.DYNAMICV = true;
		}else Method.teleportTo(42);
		
	}

}*/
