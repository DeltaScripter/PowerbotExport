package quests;

import lodestoneActivator.Data.TeleportLode;
import lodestoneActivator.Data.TeleportType;

import org.powerbot.script.methods.Hud.Window;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.Tile;


public class GertrudesCat extends Node{

	public GertrudesCat(MethodContext ctx) {
		super(ctx);
	}
	public boolean activate(){
		return(DeltaQuester.scriptToStart==15);
	}
	//Walking paths.
	public final Tile[] pathToGertrudeFV = new Tile[] { 
			new Tile(3190,3377, 0),
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
	public String itemDString[] = {"Bucket of milk", "Raw sardine"};//contains the names of the items needing to be purchased.
	
	Method Method = new Method(ctx);
	Vars Vars = new Vars();
	//Other
	public boolean init = false;
	public int requiredItems[] = {0,0};
	public Tile[] crateLoc = new Tile[]{
			new Tile(3304,3498,0),new Tile(3311,3498,0),
			new Tile(3315,3514,0),new Tile(3307,3508,0),
			new Tile(3303,3507,0),new Tile(3298,3513,0)};
	
	public boolean check = false;
	public int bankItems[] = {13236,1552,1927,327,1573,1552};
	public int bankItemAmount[] = {1,1,1,1,1,1};
	
	public void execute() {
	
		
		// * ctx.settings used:
		// * 2175-main steps
		// * /*
		
		Method.setGeneralCamera();//get the camera pitch for general use on quests
			DeltaQuester.numSteps = 7;
			if(DeltaQuester.checkedBank)
				Method.determineBank(bankItems);
			
			if((ctx.settings.get(2175) & 0x7) ==6){
				DeltaQuester.progress = 7;
				DeltaQuester.state = "The Gertrudes Cat quest has been completed";
				ctx.game.sleep(2000);
				DeltaQuester.e = true;
			}else
			if(!DeltaQuester.checkedBank){//should be if false
				Method.checkBank();
			}else
		    if(Vars.useBank){
				Method.useBank(bankItems, bankItemAmount);
			}else
			if (DeltaQuester.GEFeature) {
				Method.useGE(itemDString, itemDID, itemDPrice, itemDAmount);
			}else
			if((ctx.settings.get(2175) & 0x7) ==5){
				DeltaQuester.progress = 6;
				cs0();//go back to Gertrude
			}else
			if((ctx.settings.get(2175) & 0x7) ==4){
				DeltaQuester.progress = 5;
				cs3();//find the kittens in the crates
			}else
			if((ctx.settings.get(2175) & 0x3) ==2 ||
					(ctx.settings.get(2175) & 0x7) ==3){
				DeltaQuester.progress = 3;
				cs2();//Go to the cat and feed it.
			}else
			if((ctx.settings.get(2175) & 0x1) ==1){
				cs1();//Bribe the punks.
			}else
			if((ctx.settings.get(2175) & 0x1) ==0){
				DeltaQuester.progress = 1;
				cs0();//start the quest.
			}
		
		
	}
	

	private void cs3() {
		 Tile local = ctx.players.local().getLocation();
		
			if(Method.inventoryContains(13236)){//kittens?
				if(new Tile(3309,3510,1).getMatrix(ctx).isReachable() && ctx.game.getPlane()==1){//tree house, cat loc
					Vars.DYNAMICV = false;
					if(!Method.isChatting("Cat..")){
						Method.useItemOnNpc(13236, 759, "Shizzle");//use kittens on cat
					}
					
				} else if (new Tile(3298, 3499, 0).getMatrix(ctx).isReachable()) {//pen area
					if (new Tile(3310, 3507, 0).distanceTo(local) < 4) {//ladder loc
						Method.interactO(24354, "Climb","Ladder");
					} else ctx.movement.findPath(new Tile(3310, 3507, 0)).traverse();
				}else cs1();//get to location
			} else if(!Method.isChatting("Self")) {

				if (new Tile(3301, 3505, 0).getMatrix(ctx).isReachable()) {//fence area
					for (int num = 0; num < 7;) {
						if(ctx.hud.isVisible(Window.WORN_EQUIPMENT))
							break;
						Method.state("Gathering kittens: " + num);
						if (ctx.widgets.get(1186,0).isVisible()) {
							if (ctx.widgets.get(1186,0).isVisible()) {
								Method.pressContinue();
								num = 7;
							} else num++;
						} else {
					if (crateLoc[num].distanceTo(local)<3){
						Method.npcInteract(7740, "Search");
						ctx.game.sleep(2300,2500);
						if(Method.playerText("You find nothing"))
							num++;
							
					}else ctx.movement.findPath(crateLoc[num]).traverse();
					}
				}
				
			}else if(ctx.game.getPlane()==1){
				Method.interactO(24355, "Climb-down", "Ladder");
			}else cs1();
				  
			}
		}
			
		
	
	Timer wait = new Timer(0);
	private void cs2() {//seems to go into the cat location
		 Tile local = ctx.players.local().getLocation();
		 
		if(requiredItems[0]==1){//Do we have a doogle leaf?
			if(requiredItems[1]==1){//Do we have the ....sandwich?
		if(new Tile(3310,3508,1).getMatrix(ctx).isReachable() && ctx.game.getPlane()==1){//tree house I assume
			while(ctx.widgets.get(1186,0).isVisible()){
				Method.pressContinue();
			}
			if((ctx.settings.get(2175) & 0x7)==3){//If we need to feed the cat.
				if(!wait.isRunning()){
				Method.useItemOnNpc(1552,759, "");//on the cat, sandwhich
				wait = new Timer(2300);
				}
			}else if(!wait.isRunning()){
				Method.useItemOnNpc(1927,759, "");//on the cat, milk
				wait = new Timer(2300);
			}
		} else if (new Tile(3298,3501, 0).getMatrix(ctx).isReachable()) {// inside pen area?
		if (new Tile(3310, 3507, 0).distanceTo(local) < 4) {// below  tree house, possibly
					Method.interactO(24354, "Climb", "Ladder");
			} else ctx.movement.findPath(new Tile(3310, 3507, 0)).traverse();

			} else if (new Tile(3294, 3498, 0).distanceTo(local) < 6) {// just  outside  the  pen
				Method.interactO(31149, "Squeeze", "Fence");
				} else if (Vars.DYNAMICV) {
					Method.walking(pathToLumber,"Walking to Secret Hide-out", false);
				} else if (TeleportLode.VARROCK.getTile().distanceTo(local) < 10) {
					Vars.DYNAMICV = true;
				} else Method.teleportTo(TeleportType.VARROCK.getTeleport(),"Varrock");// Teleport To Varrock.//Varrok
										

				} else if (Method.inventoryContains(1552)) {//I assume the sandwhich
					requiredItems[1] = 1;
				} else if (!Method.isChatting("Self")) {
					Method.skipPics();
					Method.combineItems(327, 1573);// combine to make a sandwhich

				}
			} else if (Method.inventoryContains(1573)|| Method.inventoryContains(1552)) {//below gets the leaf
				Vars.DYNAMICV = false;
				requiredItems[0] = 1;
			} else if (new Tile(3152, 3399, 0).distanceTo(local) < 15) {
				if(Method.gItemIsNotNull(1573)){//woodle leaf
					
					if(Method.getGroundItem(1573).getLocation().distanceTo(local)<6){
						Method.interactG(1573, "Take", "Woodle");
						ctx.game.sleep(2300);
					}else Method.clickOnMap(Method.getGroundItem(1573).getLocation().randomize(1, 2));
				}
				
			} else if (Vars.DYNAMICV) {
				Method.walking(pathToGertrudeFV, "Walking to Gertrude",false);
			} else if (TeleportLode.VARROCK.getTile().distanceTo(local)<10)
				Vars.DYNAMICV = true;
			else Method.teleportTo(TeleportType.VARROCK.getTeleport(),"Varrock");//Teleport To Varrock.
	}

	private void cs1() {
		 Tile local = ctx.players.local().getLocation();
		 String opt[] = {"What will make","Okay then,"};
		
		if(new Tile(3218, 3434,0).distanceTo(local)<6){//by kids
			Method.skipPics();
			if(!Method.startQuestOpen())
			if(!Method.findOption(opt)){
				if(!Method.isChatting("Person")){
					Vars.DYNAMICV = false;
					Method.npcInteract(781, "Talk-to");//kids
				}
			}
		}else if (Vars.DYNAMICV){
			Method.walking(pathToKidFV, "Walking to kids.",false);
		}else if(TeleportLode.VARROCK.getTile().distanceTo(local)<10)
			Vars.DYNAMICV = true;
		else Method.teleportTo(TeleportType.VARROCK.getTeleport(),"Varrock");
		
	}

	private void cs0() {//get to and speaks to Gertrude
		 Tile local = ctx.players.local().getLocation();
		 String opt[] = {"Well, I"};
		 
		if(new Tile(3151,3412,0).distanceTo(local)<6){//Gertrudes Area.
			Method.skipPics();
			if(!Method.startQuestOpen())
			if(!Method.findOption(opt)){
				if(!Method.isChatting("Person")){
					Vars.DYNAMICV = false;
					if(Method.npcIsNotNull(780) 
							&& Method.getNPC(780).getLocation().getMatrix(ctx).isReachable()){
					Method.npcInteract(780, "Talk-to");//Gertrude
					}else Method.interactO(24376, "Open", "Door");
				}
			}
		}else if (Vars.DYNAMICV){
		    Method.walking(pathToGertrudeFV, "Walking to Gertrude.",false);
		}else if(TeleportLode.VARROCK.getTile().distanceTo(local)<10)
			Vars.DYNAMICV = true;
		else Method.teleportTo(TeleportType.VARROCK.getTeleport(),"Varrock");//Teleport To Varrock.
	}


}
