package quests;
/*package quests;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class JunglePotion extends Node{

	public final Tile[] pathToSnakeWeed = new Tile[] { 
		new Tile(2807,3086,0), new Tile(2800,3083,0),
		new Tile(2797,3083, 0),	new Tile(2788,3080, 0), 
		new Tile(2775,3077, 0),new Tile(2767,3070, 0),
		new Tile(2766,3059, 0),	new Tile(2770,3047, 0), 
		new Tile(2776,3035, 0),new Tile(2777,3022, 0) ,
		new Tile(2765,3021, 0),	new Tile(2759,3016, 0)};
	
	public final Tile[] pathToArdrigal = new Tile[] 
			{new Tile(2808, 3084, 0), new Tile(2808, 3092, 0), new Tile(2813, 3099, 0), 
			new Tile(2820, 3103, 0), new Tile(2828, 3105, 0), new Tile(2836, 3107, 0), 
			new Tile(2844, 3108, 0), new Tile(2852, 3108, 0), new Tile(2860, 3108, 0), 
			new Tile(2867, 3112, 0), new Tile(2871, 3118, 0) };
	public final Tile[] pathToSitoFoil = new Tile[] { 
			new Tile(2809, 3085, 0), new Tile(2803, 3083, 0), new Tile(2806, 3080, 0), 
			new Tile(2810, 3077, 0), new Tile(2813, 3075, 0), new Tile(2817, 3072, 0), 
			new Tile(2819, 3069, 0), new Tile(2819, 3064, 0), new Tile(2818, 3060, 0), 
			new Tile(2815, 3057, 0), new Tile(2810, 3054, 0), new Tile(2807, 3051, 0), 
			new Tile(2801, 3050, 0), new Tile(2797, 3050, 0), new Tile(2792, 3048, 0), 
			new Tile(2790, 3048, 0) };
	
	public final Tile[] pathToVolenciaMoss = new Tile[] { 
			new Tile(2809, 3085, 0), new Tile(2811, 3079, 0), new Tile(2814, 3074, 0), 
			new Tile(2818, 3071, 0), new Tile(2821, 3067, 0), new Tile(2825, 3063, 0), 
			new Tile(2829, 3059, 0), new Tile(2833, 3056, 0), new Tile(2836, 3052, 0), 
			new Tile(2838, 3048, 0), new Tile(2840, 3044, 0), new Tile(2844, 3040, 0), 
			new Tile(2845, 3037, 0), new Tile(2850, 3033, 0) };
	
	public final Tile[] pathToCave = new Tile[] { 
			new Tile(2809, 3085, 0), new Tile(2809, 3092, 0), new Tile(2815, 3096, 0), 
			new Tile(2821, 3098, 0), new Tile(2829, 3098, 0), new Tile(2834, 3100, 0), 
			new Tile(2839, 3101, 0), new Tile(2844, 3100, 0), new Tile(2850, 3101, 0), 
			new Tile(2856, 3102, 0), new Tile(2859, 3106, 0), new Tile(2863, 3108, 0), 
			new Tile(2864, 3112, 0), new Tile(2861, 3116, 0), new Tile(2857, 3115, 0), 
			new Tile(2851, 3117, 0), new Tile(2846, 3120, 0), new Tile(2841, 3120, 0), 
			new Tile(2838, 3123, 0), new Tile(2835, 3127, 0), new Tile(2833, 3130, 0), 
			new Tile(2828, 3132, 0), new Tile(2824, 3131, 0), new Tile(2820, 3129, 0), 
			new Tile(2819, 3125, 0), new Tile(2820, 3122, 0), new Tile(2824, 3119, 0) };
	public boolean init = true;
	public int bankItems[] = {1533,1531,1529,1527,1528,1525};
	public void execute() {
		Quests.checkForGates();
		Quests.getSettings(2484);
		Method.foodSupport();
		Method.resetTeleporting();
		DeltaQuester.numSteps = 12;
		if(!init){
			if(Skills.getLevel(Skills.HERBLORE)<3){
				Method.state("Herblore level greater than 3 needed, skipping quest.");
				Task.sleep(2000);
				DeltaQuester.e  =true;
			}else init = true;
		}else if(Method.useBank && (Settings.get(2484)&0xF) !=12){
			Method.useBank(bankItems, 10,1,90);
		}else
		switch(Quests.stepX){
		
		case 0:
			DeltaQuester.progress = 1;
			CS0();//Start the quest.
			break;
		
		case 1:
			DeltaQuester.progress = 2;
			CS1();//Gather snake weed.
			break;
		
		case 2:
			DeltaQuester.progress = 3;
			CS2();//return to the man and gain next objective.
			break;
			
		case 3:
			DeltaQuester.progress = 4;
			CS3();//Gather some Ardrigal
			break;
			
		case 4:
			DeltaQuester.progress = 5;
			CS4();//return to the Ardrigal to theman.
			break;
			
		case 5:
			DeltaQuester.progress = 6;
			CS5();//gather the Sito foil
			break;
			
		case 6:
			DeltaQuester.progress = 7;
			CS6();//return the Sito foil to Trufitus.
			break;
			
		case 7:
			DeltaQuester.progress = 8;
			CS7();//Gather the volencia moss.
			break;
			
			
		case 8:
			DeltaQuester.progress = 9;
			CS8();//return the Volencia moss.
			break;
			
		case 9:
			DeltaQuester.progress = 10;
			CS9();//retrieve the cave moss.
			break;
			
		case 10:
			DeltaQuester.progress = 11;
			CS10();//return the cave moss.
			break;
			
		case 11:
			DeltaQuester.progress = 12;
			CS10();
			break;
			
		case 12:
			DeltaQuester.progress = 13;
			Quests.state("The Jungle Potion quest has been completed.");
			Task.sleep(2000);
			DeltaQuester.e = true;
			break;
			
		default:
			Quests.cannotFindSetting(2484);
			break;
			
		}
		
	}
	
	private void CS10() {
		
		while(Widgets.get(1186).validate()){
			Quests.clickContinue();
		}
		
		while(!Method.teleporting &&Inventory.getItem(1533)!=null){
			if (Inventory.getCount(1533) > 1) {
				Quests.interactInventory(1533, "Drop");
				Task.sleep(1300);
			} else {
			Task.sleep(400,600);
			Method.interactInventory(1533, "Clean","Grimy herb");
		}
		}
		
		if(SceneEntities.getNearest(2585)!=null){//if inside the cave.
			if(SceneEntities.getNearest(2585).getLocation().distanceTo()<6){
				if(Widgets.get(1186).validate()){
					Quests.clickContinue();
				}else
				Quests.interactO(2585, "Climb");
			}else SceneEntities.getNearest(2585).getLocation().clickOnMap();
		}else if(new Tile(2807,3086,0).distanceTo()<6){
		
			if(Quests.tActive()){
				Quests.DYNAMICV = false;
				Quests.DYNAMICV2=false;
			if(Quests.chatting()){
				Quests.clickContinue();
			}else if(Quests.findOption("Of course!")){
				Quests.clickOption(1188,Quests.value);
			}				
			}else Quests.speakTo(740);
		}else if(Quests.DYNAMICV2){
			Quests.walking(pathToCave, "Walking back to Trufitus.", true);
		}else if(new Tile(2823,3120,0).distanceTo()<9){
			Quests.walkingReset();
			Quests.DYNAMICV2 = true;
		}else CS0();
		
	}

	private void CS9() {
		NPC Jogre = NPCs.getNearest(113,3449,3450);
		if(SceneEntities.getNearest(31130)!=null){//if entered cave.
			
			if(SceneEntities.getNearest(32106)!=null){
			if(SceneEntities.getNearest(32106).getLocation().distanceTo()<3){
				Quests.DYNAMICV = false;
				Quests.DYNAMICV2 = false;
				while(Method.isInCombat() && Players.getLocal().getInteracting()!=null){
					Method.basicFightNPC(Jogre.getId());
				}
				if(Players.getLocal().isIdle())
				Method.interactO(32106, "Search","Wall moss");
			}else {
				Method.state("Attempting to walk to fungus");
				SceneEntities.getNearest(32106).getLocation().clickOnMap();
			}
			}else Quests.state("The moss is null");
		}else//if outside the cave.
	if(new Tile(2824, 3119, 0).distanceTo()<7){
		if(Widgets.get(1186).validate()){
			Quests.clickContinue();
		}else if(Quests.findOption("Yes")){
			Quests.clickOption(1188, Quests.value);
		}else Quests.interactO(2584, "Search");
		
	}else if(Quests.DYNAMICV2){
		Quests.walking(pathToCave, "Walking to the cave.", false);
	}else if(new Tile(2810,3086,0).distanceTo()<9){
		Quests.walkingReset();
		Quests.DYNAMICV2 = true;
	}else CS0();
		
	}

	private void CS8() {
		while(Inventory.getItem(1531)!=null){
			if (Inventory.getCount(1531) > 1) {
				Quests.interactInventory(1531, "Drop");
				Task.sleep(2300);
			} else {
				Task.sleep(400, 600);
				Quests.interactInventory(1531, "Clean");
			}
		}
		
		if(new Tile(2807,3086,0).distanceTo()<6){
			if(Quests.tActive()){
				Quests.DYNAMICV = false;
				Quests.DYNAMICV2=false;
			if(Quests.chatting()){
				Quests.clickContinue();
			}else if(Quests.findOption("Of course!")){
				Quests.clickOption(1188,Quests.value);
			}				
			}else Quests.speakTo(740);
		}else if(Quests.DYNAMICV2){
			Quests.walking(pathToVolenciaMoss, "Walking back to Trufitus.", true);
		}else if(new Tile(2850,3034,0).distanceTo()<9){
			Quests.walkingReset();
			Quests.DYNAMICV2 = true;
		}else CS0();
		
	}

	private void CS7() {
		
		if(	new Tile(2850, 3033, 0).distanceTo()<5){
			Quests.DYNAMICV = false;
			Quests.DYNAMICV2 =false;
			Task.sleep(500,600);
			if(Players.getLocal().isIdle())
				Quests.interactO(2581, "Search");
		}else if(Quests.DYNAMICV2){
			Quests.walking(pathToVolenciaMoss, "Walking to the Volencia moss", false);
		}else if(new Tile(2810,3086,0).distanceTo()<9){
			Quests.walkingReset();
			Quests.DYNAMICV2 = true;
		}else CS0();
	}

	private void CS6() {
		while(Inventory.getItem(1529)!=null){
			if (Inventory.getCount(1529) > 1) {
				Quests.interactInventory(1529, "Drop");
				Task.sleep(2300);
			} else {
				Task.sleep(500,600);
			Quests.interactInventory(1529, "Clean");
		}
		}
		if(new Tile(2807,3086,0).distanceTo()<6){
			if(Quests.tActive()){
				Quests.DYNAMICV = false;
				Quests.DYNAMICV2=false;
			if(Quests.chatting()){
				Quests.clickContinue();
			}else if(Quests.findOption("Of course!")){
				Quests.clickOption(1188,Quests.value);
			}				
			}else Quests.speakTo(740);
		}else if(Quests.DYNAMICV2){
			Quests.walking(pathToSitoFoil, "Walking back to Trufitus.", true);
		}else if(new Tile(2791,3049,0).distanceTo()<9){
			Quests.walkingReset();
			Quests.DYNAMICV2 = true;
		}else CS0();
		
	}

	private void CS5() {
		if(	new Tile(2790, 3048, 0).distanceTo()<5){
			Quests.DYNAMICV = false;
			Quests.DYNAMICV2 =false;
			Task.sleep(500,600);
			if(Players.getLocal().isIdle())
				Quests.interactO(2579, "Search");
		}else if(Quests.DYNAMICV2){
			Quests.walking(pathToSitoFoil, "Walking to the Sito Foil", false);
		}else if(new Tile(2810,3086,0).distanceTo()<9){
			Quests.walkingReset();
			Quests.DYNAMICV2 = true;
		}else CS0();
		
	}

	private void CS4() {
		while(Inventory.getItem(1527)!=null){
			if (Inventory.getCount(1527) > 1) {
				Quests.interactInventory(1527, "Drop");
				Task.sleep(2300);
			} else {
				Task.sleep(500);
			Quests.interactInventory(1527, "Clean");
			}
		}
		if(Inventory.getItem(1528)==null&&!Method.teleporting){
			CS3();//Gather the ardi
		}else
		if(new Tile(2807,3086,0).distanceTo()<6){
			if(Quests.tActive()){
				Quests.DYNAMICV = false;
				Quests.DYNAMICV2=false;
			if(Quests.chatting()){
				Quests.clickContinue();
			}else if(Quests.findOption("Of course!")){
				Quests.clickOption(1188,Quests.value);
			}				
			}else Quests.speakTo(740);
		}else if(Quests.DYNAMICV2){
			Quests.walking(pathToArdrigal, "Walking back to Trufitus.", true);
		}else if(new Tile(2870,3117,0).distanceTo()<9){
			Quests.walkingReset();
			Quests.DYNAMICV2 = true;
		}else CS0();
	}

	private void CS3() {
		if(new Tile(2870, 3116, 0).distanceTo()<5){
			Quests.DYNAMICV = false;
			Quests.DYNAMICV2 =false;
			Task.sleep(500,600);
			if(Players.getLocal().isIdle())
				Quests.interactO(2577, "Search");
		}else if(Quests.DYNAMICV2){
			Quests.walking(pathToArdrigal, "Walking to the Ardrigal", false);
		}else if(new Tile(2810,3086,0).distanceTo()<9){
			Quests.walkingReset();
			Quests.DYNAMICV2 = true;
		}else CS0();
	}

	private void CS2() {
		while (!Method.teleporting&&Inventory.getItem(1525) != null)
			Quests.interactInventory(1525, "Clean");//Clean the herb.
		
		if(new Tile(2807,3086,0).distanceTo()<6){
			if(Quests.tActive()){
				Quests.DYNAMICV = false;
				Quests.DYNAMICV2=false;
			if(Quests.chatting()){
				Quests.clickContinue();
			}else if(Quests.findOption("Of course!")){
				Quests.clickOption(1188,Quests.value);
			}				
			}else Quests.speakTo(740);
		}else if(Quests.DYNAMICV2){
			Quests.walking(pathToSnakeWeed, "Walking back to Trufitus.", true);
		}else if(new Tile(2760,3017,0).distanceTo()<9){
			Quests.walkingReset();
			Quests.DYNAMICV2 = true;
		}else CS0();
	}

	private void CS1() {
	
		if(new Tile(2762,3024,0).distanceTo()<12){
			if(Players.getLocal().isIdle()){
				Quests.DYNAMICV2 = false;
				Quests.interactO(2575, "Search");
			}
		}else if(Quests.DYNAMICV2){
			Quests.walking(pathToSnakeWeed, "Walking to snake weed location.",false);
		}else if(new Tile(2806,3085,0).distanceTo()<8){
			Quests.walkingReset();
			Quests.DYNAMICV2 = true;
		}else CS0();//Get the player to the initial start location.
		
	}

	private static void CS0() {//Starts quest.
		 
		while(Settings.get(1045)==5)
			Task.sleep(200,300);
		
		if(new Tile(2807,3086,0).distanceTo()<6){
			if(Quests.tActive()){
				Quests.DYNAMICV = false;
			if(Quests.chatting()){
				Quests.clickContinue();
			}else if(Quests.findOption("What does Bwana")){
				Quests.clickOption(1188,Quests.value);
			}else if(Quests.findOption("Yes, of course")){
				Quests.clickOption(1188,Quests.value);
			}else if(Quests.findOption("A proposition")){
				Quests.clickOption(1188,Quests.value);
			}else if(Quests.findOption("Me? How can I help?")){
				Quests.clickOption(1188,Quests.value);
			}else if(Quests.findOption("It sounds like just")){
				Quests.clickOption(1188,Quests.value);
			}					
			}else Quests.speakTo(740);
		}else if(new Tile(2815,3182,0).distanceTo()<7 && !new Tile(2814,3182,0).canReach()){//Fence at the location.
			Quests.interactO(24369, "Open");
		}else if(new Tile(2956,3143,1).canReach()){//on the ship.
			Quests.DYNAMICV = true;
			Quests.interactO(2082, "Cross");
		}else if(new Tile(3028,3217,0).distanceTo()<7){//by the port in Port Sarim.
			Quests.DYNAMICV =true;
			if(Quests.tActive()){
				if(Quests.chatting()){
					Quests.clickContinue();
				}else if(Quests.findOption("Yes,")){
					Quests.clickOption(1188, Quests.value);
				}
			}else Quests.speakTo(376);
		}else if(Quests.DYNAMICV){
			Quests.walking(Paths.pathToPortKaramja, "Walking to Trufitus.",false);
		}else if(Quests.LUMMBRIDGELODE.distanceTo()<6){
			Quests.DYNAMICV = true;
		}else Method.teleportTo(47);
		
	}

	public boolean activate() {
		return DeltaQuester.scriptToStart==22;
	}

}
*/