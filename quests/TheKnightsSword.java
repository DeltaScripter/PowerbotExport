/*package quests;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class TheKnightsSword extends Node{

	public final Tile[] pathToSquire = new Tile[] { 
			new Tile(2965, 3405, 0), new Tile(2965, 3399, 0), new Tile(2964, 3393, 0), 
			new Tile(2964, 3387, 0), new Tile(2964, 3381, 0), new Tile(2962, 3375, 0), 
			new Tile(2963, 3369, 0), new Tile(2964, 3363, 0), new Tile(2964, 3357, 0), 
			new Tile(2963, 3351, 0), new Tile(2965, 3345, 0), new Tile(2971, 3341, 0), 
			new Tile(2977, 3339, 0), new Tile(2981,3341,0) };
	
	public final Tile[] pathToReldo = { 
		new Tile(3212, 3373, 0), new Tile(3211, 3379, 0), new Tile(3211, 3385, 0), 
		new Tile(3211, 3391, 0), new Tile(3211, 3397, 0), new Tile(3211, 3403, 0), 
		new Tile(3211, 3409, 0), new Tile(3211, 3415, 0), new Tile(3211, 3421, 0), 
		new Tile(3211, 3427, 0), new Tile(3211, 3433, 0), new Tile(3212, 3439, 0), 
		new Tile(3212, 3445, 0), new Tile(3213, 3451, 0), new Tile(3213, 3457, 0), 
		new Tile(3212, 3463, 0), new Tile(3211, 3469, 0), new Tile(3207, 3474, 0), 
		new Tile(3206, 3480, 0), new Tile(3206, 3486, 0), new Tile(3210, 3488, 0) };
	
	public final Tile[] pathToDwarfSmith = new Tile[] { 
			new Tile(2966, 3403, 0), new Tile(2965, 3397, 0), new Tile(2964, 3391, 0), 
			new Tile(2964, 3385, 0), new Tile(2964, 3379, 0), new Tile(2970, 3378, 0), 
			new Tile(2976, 3378, 0), new Tile(2982, 3376, 0), new Tile(2987, 3372, 0), 
			new Tile(2993, 3370, 0), new Tile(2998, 3366, 0), new Tile(3002, 3361, 0), 
			new Tile(3006, 3356, 0), new Tile(3006, 3350, 0), new Tile(3006, 3343, 0), 
			new Tile(3006, 3337, 0), new Tile(3006, 3331, 0), new Tile(3005, 3325, 0), 
			new Tile(3005, 3319, 0), new Tile(3005, 3313, 0), new Tile(3006, 3307, 0), 
			new Tile(3007, 3301, 0), new Tile(3007, 3295, 0), new Tile(3008, 3289, 0), 
			new Tile(3008, 3282, 0), new Tile(3008, 3273, 0), new Tile(3007, 3264, 0), 
			new Tile(3006, 3258, 0), new Tile(3005, 3252, 0), new Tile(3005, 3246, 0), 
			new Tile(3005, 3240, 0), new Tile(3005, 3234, 0), new Tile(3006, 3228, 0), 
			new Tile(3006, 3222, 0), new Tile(3005, 3216, 0), new Tile(3005, 3210, 0), 
			new Tile(3003, 3204, 0), new Tile(3003, 3198, 0), new Tile(3004, 3192, 0), 
			new Tile(3005, 3186, 0), new Tile(3006, 3180, 0), new Tile(3007, 3174, 0), 
			new Tile(3007, 3168, 0), new Tile(3002, 3164, 0), new Tile(3000, 3158, 0), 
			new Tile(2999, 3152, 0), new Tile(2998, 3146, 0), new Tile(2998, 3140, 0), 
			new Tile(2998, 3145, 0) };
	
	public boolean hasPortrait = false;
	public boolean hasSword = false;
	public boolean hasBlurite = false;
	public Tile init;
	
	public int itemsArray[] = {0,0,0};//contains the states of items needing to be purchased.
	public int itemDID[] = {2325,1265,2352};//contains the ids of the items needing to be purchased.
	public int itemDAmount[] = {1,1,2};
	public int itemDPrice[] = {2500,1800,2500};//contains specific prices to use upon purchasing specific items.
	public String itemDString[] = {"Redberry pie", "Bronze pickaxe", "Iron bar"};//contains the names of the items needing to be purchased.
	public boolean inits = false;
	
	public int bankItems[] = {2325,1265,2351,2351,666,667,668};
	public boolean activate() {
		return DeltaQuester.scriptToStart==25;
	}

	
	public void execute() {
		//Settings used, 2547 for main body.
		Method.foodSupport();
		Quests.getSettings(2547);
		DeltaQuester.numSteps = 8;
		Method.resetTeleporting();
		if(!inits){
			if(!Method.FaladorLodeIsActive() || !Method.VarrokLodeIsActive()){
				Method.state("You require Varrok and Falador lodestone, skipping quest");
				Task.sleep(2000);
				DeltaQuester.e = true;
			}else if(Skills.getLevel(Skills.MINING)<10){
				Method.state("Mining level of 10 is required, skipping quest");
				Task.sleep(2000);
				DeltaQuester.e = true;
			}else inits = true;
		}else if(Method.useBank && (Settings.get(2547) & 0x7) !=7){
			Method.useBank(bankItems, 20,1,90);
		}else
		if (DeltaQuester.GEFeature && (Settings.get(2547) & 0x7) !=7) {
			Method.useGE(itemDString, itemDID, itemDPrice, itemDAmount);
		}else if(Method.useBank && (Settings.get(2547) & 0x7) !=7){
			Method.useBank(bankItems, 20,90,2);
		}else
			if((Settings.get(2547) & 0x7) ==7){
				DeltaQuester.progress = 8;
				Quests.state("The Knight's Sword quest has been completed.");
				Task.sleep(2000);
				DeltaQuester.e = true;
			}else
			if((Settings.get(2547) & 0x7) ==6){
				DeltaQuester.progress = 7;
				cs4();//mine the blurite and then craft the sword + return it.
			}else
			if((Settings.get(2547) & 0x7) ==5){
				DeltaQuester.progress = 6;
				cs3();//Find a portrait and then give it to Thurgo.
			}else
			if((Settings.get(2547) & 0x7) ==4){
				DeltaQuester.progress = 5;
				cs0();//Ask the squire about what Thurgo said.
			}else
			if((Settings.get(2547) & 0x3) ==3){
				DeltaQuester.progress = 4;
				cs2();//Speak to Thurgo about the sword.
			}else
			if((Settings.get(2547) & 0x3) ==2){
				DeltaQuester.progress = 3;
				cs2();//Feed Thurgo the redberry pie.
			}else
			if((Settings.get(2547) & 0x1) ==1){
				DeltaQuester.progress = 2;
				cs1();//Speak to Reldo about the dwarves.
			}else
			if((Settings.get(2547) & 0x1) ==0){
				DeltaQuester.progress = 1;
				cs0();//Start the quest.
			}
		
	}




	private void cs4() {
		if(hasSword){
			cs0();
		}else if(!Method.teleporting&& Inventory.getItem(667)!=null ){
			Quests.DYNAMICV = false;
			hasSword = true;
		}else if(hasBlurite){
			cs2();
		}else
		if(!Method.teleporting && Inventory.getItem(668)!=null){
				hasBlurite = true;
		}else if(init!=null){
			Quests.state("" + (init.getX() - Players.getLocal().getLocation().getX()) + " " + ( init.getY() - Players.getLocal().getLocation().getY()));
			if(new Tile(init.getX()+39, init.getY()+18,0).distanceTo()<7){
				Quests.DYNAMICV = false;
				Quests.interactO(2561, "Mine");
			}else
			if(new Tile(init.getX()+23, init.getY()+30,0).distanceTo()<7){
				new Tile(init.getX()+39, init.getY()+18,0).clickOnMap();
			}else
			if(new Tile(init.getX()-2, init.getY()+29,0).distanceTo()<7){
				new Tile(init.getX()+23, init.getY()+30,0).clickOnMap();
			}else
			if(new Tile(init.getX()-14, init.getY()+25,0).distanceTo()<7){
				
				new Tile(init.getX()+0, init.getY()+30,0).clickOnMap();
			}else
			if(new Tile(init.getX()-15, init.getY()+11,0).distanceTo()<10){
			
				new Tile(init.getX()-14, init.getY()+25,0).clickOnMap();
			}else
			if(new Tile(init.getX()-13, init.getY()-1,0).distanceTo()<5){
				
				new Tile(init.getX()-15, init.getY()+11,0).clickOnMap();
		}else {
			if(Players.getLocal().isIdle())
			new Tile(init.getX()-13, init.getY()-1,0).clickOnMap();
		}
		
		}else if(SceneEntities.getNearest(31128)!=null){//Inside the cave.
			Task.sleep(1500);
			init = Players.getLocal().getLocation();
		}else if(new Tile(3007,3151,0).distanceTo()<14){
			if(new Tile(3007,3151,0).distanceTo()<6){
				Quests.interactO(9472, "Climb-down");
			}else Quests.findPath(new Tile(3007,3151,0));
		}else cs2();
		
	}


	private void cs3() {
		NPC King = NPCs.getNearest(605);
		SceneObject cabinet = SceneEntities.getNearest(2271);
		SceneObject opencabinet = SceneEntities.getNearest(2272);
			if(hasPortrait){
			cs2();
		}else if(!Method.teleporting && Inventory.getItem(666)!=null){
			hasPortrait = true;
		}else
		if(Game.getPlane()==2 && new Tile(2982,3335,2).canReach()){
			if(new Tile(2982,3335,2).distanceTo()<5){
				while(Widgets.get(1186).validate()){
					Quests.clickContinue();
				}
				if ((King != null && cabinet != null) || opencabinet!=null) {
					if ((cabinet!=null &&King.getLocation().distance(cabinet.getLocation()) > 3)|| opencabinet!=null) {
						Quests.DYNAMICV = false;
						Quests.state("HERE");
						Quests.interactO(2271, "Open");
						Quests.interactO(2272, "Search");
					} else
						Quests.state("Waiting for the king to move farther away.");
				}
			}else new Tile(2982,3335,2).clickOnMap();
		}else
		if(Game.getPlane()==2){
			Quests.interactO(11714, "Open");
		}else
		if(Game.getPlane()==1 && (new Tile(2989,3339,1).canReach())){
			if(new Tile(2982,3338,1).distanceTo()<5){
				Quests.interactO(11734, "Climb");
			}else new Tile(2982,3338,1).clickOnMap();
		}else if(Game.getPlane()==1 && (new Tile(2993,3341,1).canReach())){
			Quests.interactO(11714, "Open");
		}else
		if(new Tile(2993,3342,0).canReach()){
			if(new Tile(2993,3342,0).distanceTo()<4){
			Quests.interactO(11727, "Climb");
			}else new Tile(2993,3342,0).clickOnMap();
		}else
		if(new Tile(2987,3341,0).canReach()){
			Quests.interactO(11714, "Open");
		}else
		if(new Tile(2984,3341,0).canReach()){
			new Tile(2985,3341,0).clickOnMap();
			Quests.interactO(11714, "Open");
		}else if(new Tile(2981,3341,0).distanceTo()<9){
			Quests.interactO(11716, "Open");
		}else cs0();
		
	}


	private void cs2() {
		if(new Tile(2998, 3145, 0).distanceTo()<13){
			if(NPCs.getNearest(604).getLocation().distanceTo()<7){
				while(Widgets.get(1186).validate()){
					Quests.clickContinue();
				}
				if(Quests.tActive()){
					Quests.DYNAMICV = false;
					if(Quests.chatting()){
						Quests.clickContinue();
					}else if(Quests.findOption("Something else")){
						Quests.clickOption(1188, Quests.value);
					}else if(Quests.findOption("Would you like some red")){
						Quests.clickOption(1188, Quests.value);
					}
				}else Quests.speakTo(604);//Speak to Thurgo
			}else NPCs.getNearest(604).getLocation().clickOnMap();
		}else if(Quests.DYNAMICV){
			Quests.walking(pathToDwarfSmith, "Walking to Thurgo the dwarf", false);
		}else if(Quests.FALADORLODE.distanceTo()<5 || Quests.PORTSARIMLODE.distanceTo()<5){
			Quests.DYNAMICV = true;
		}else if((Settings.get(3) >> 8 & 1)==1){
			Method.teleportTo(48);//Port Sarim lodestone
		}else Method.teleportTo(46);
		
	}


	private void cs1() {
		if(new Tile(3210, 3488, 0).distanceTo()<7 || ((NPCs.getNearest(647)!=null) && NPCs.getNearest(647).getLocation().distanceTo()<5)){
			if(new Tile(3210,3492,0).canReach()){
				if(Quests.tActive()){
					Quests.DYNAMICV = false;
					if(Quests.chatting()){
						Quests.clickContinue();
					}else if(Quests.findOption("I'd like to talk")){
						Quests.clickOption(1188, Quests.value);
					}else if(Quests.findOption("What do you know")){
						Quests.clickOption(1188, Quests.value);
					}
				}else Quests.speakTo(647);//Reldo
			}else Quests.interactO(15536, "Open");
		}else if(Quests.DYNAMICV){
			Quests.walking(pathToReldo, "Walking to Reldo", false);
		}else if(Quests.varrokLode.distanceTo()<5){
			Quests.DYNAMICV = true;
		}else Method.teleportTo(51);//Varrok
		
	}


	private void cs0() {//Start the quest.
		 
		if(new Tile(2977,3339,0).distanceTo()<10){
			while(Widgets.get(1186).validate()){
				Quests.clickContinue();
			}
			if(Quests.tActive()){
				Quests.DYNAMICV = false;
				if(Quests.chatting()){
					Quests.clickContinue();
				}else if(Quests.findOption("Chat")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("And how is life")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("I can make a new")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("So would these dwarves")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("Ok, I'll give it a go.")){
					Quests.clickOption(1188, Quests.value);
				}
			}else Quests.speakTo(606);//Speak to the squire
		}else if(Quests.DYNAMICV){
			Quests.walking(pathToSquire, "Walking to the squire", false);
		}else if(Quests.FALADORLODE.distanceTo()<6){
			Quests.DYNAMICV = true;
		}else if((Settings.get(3) >> 7 & 1)==1){
			Method.teleportTo(46);//Falador tele
		}else {
			Quests.state("Falador lodestone is not activated.");
			Task.sleep(1500);
			DeltaQuester.e = true;
		}
		
	}

}
*/