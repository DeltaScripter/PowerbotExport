package quests;

import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Player;

import quests.Vars.TeleportLode;
import quests.Vars.TeleportType;

public class ElementalWorkshop1 extends DeltaNode{

	public ElementalWorkshop1(ClientContext ctx) {
		super(ctx);
	}


	public final Tile[] pathToBookHouse = new Tile[] {
			new Tile(2830, 3451, 0), new Tile(2828, 3456, 0), new Tile(2823, 3457, 0), 
			new Tile(2818, 3457, 0), new Tile(2813, 3457, 0), new Tile(2808, 3457, 0), 
			new Tile(2803, 3455, 0), new Tile(2801, 3450, 0), new Tile(2796, 3449, 0), 
			new Tile(2791, 3451, 0), new Tile(2786, 3449, 0), new Tile(2781, 3447, 0), 
			new Tile(2776, 3448, 0), new Tile(2773, 3452, 0), new Tile(2770, 3456, 0), 
			new Tile(2767, 3460, 0), new Tile(2763, 3463, 0), new Tile(2762, 3468, 0), 
			new Tile(2759, 3472, 0), new Tile(2756, 3476, 0), new Tile(2751, 3477, 0), 
			new Tile(2746, 3478, 0), new Tile(2741, 3480, 0), new Tile(2736, 3482, 0), 
			new Tile(2731, 3484, 0), new Tile(2726, 3484, 0), new Tile(2721, 3483, 0), 
			new Tile(2716, 3484, 0), new Tile(2712, 3483, 0), new Tile(2688, 3483, 0), 
			new Tile(2693, 3484, 0), new Tile(2698, 3485, 0), new Tile(2703, 3484, 0), 
			new Tile(2708, 3484, 0), new Tile(2711, 3483, 0) };
	
	final Area EntranceDoor = new Area(new Tile[] { new Tile(2707, 3498, 0), new Tile(2707, 3495, 0), new Tile(2712, 3495, 0), 
			new Tile(2713, 3500, 0) });
	
	final Area StartDoor = new Area(new Tile[] {
			new Tile(2710, 3484, 0), new Tile(2710, 3481, 0), new Tile(2718, 3481, 0), 
			new Tile(2718, 3485, 0) });
	
	
	public final int  bankItems[] = {453,453,453,453,2347,946,1741,1733,1265,1734,2887,2892,2888,9715};
	public int bankItemAmount[] = {1,1,1,1,1,1,1,1,1,1,1,1,1,1};
	
	
	public int itemsArray[] = {0,0,0,0,0,0,0};//contains the states of items needing to be purchased.
	public int itemDID[] = {454,2347,946,1741,1733,1265,1734};//contains the ids of the items needing to be purchased.
	public int itemDAmount[] = {4,1,1,1,1,1,1};
	public int itemDPrice[] = {1000,1000,1000,1500,1000,3500,1000};//contains specific prices to use upon purchasing specific items.
	public String itemDString[] = {"Coal","Hammer","Knife","Leather","Needle","Bronze pickaxe","Thread"};//contains the names of the items needing to be purchased.

	private Method Method = new Method(ctx);
    private Vars Vars = new Vars();
	public boolean hasKey = false;
	public boolean activate() {
		return DeltaQuester.scriptToStart==33;
	}


	public void execute() {
		
		Method.foodSupport();
		Method.setGeneralCamera();//get the camera pitch for general use on quests
		DeltaQuester.numSteps = 10;
		
		if(DeltaQuester.checkedBank)
			Method.determineBank(bankItems);
		
		
		if(!DeltaQuester.checkedBank){
			Method.checkBank();
		}else
		if (DeltaQuester.GEFeature && (ctx.varpbits.varpbit(2675)>>20&0x1)!=1) {
			Method.useGE(itemDString, itemDID, itemDPrice, itemDAmount);
		}else if(Vars.useBank){
			Method.useBank(bankItems, bankItemAmount);
		}
		else if((ctx.varpbits.varpbit(2675)>>20&0x1)==1){
			DeltaQuester.progress=10;
			Method.state("The Elemental Workshop I quest has been completed.");
			Method.sleep(2000);
			DeltaQuester.e = true;
		}else if((ctx.varpbits.varpbit(2675)&0x7)==6){
			
			if((ctx.varpbits.varpbit(2675)>>5&0x1)==1){
				cs2();//Do alot of things in the factory
			}else cs1();//Start the water machine(wheel)
			
		}else if((ctx.varpbits.varpbit(2675)&0x3)==2){
			if(Method.dialogueClosed(960,79)){
			Method.interactInventory(2886, "Cut", "Book");//Cuts the book to find key
			}
		}else cs0();//Search the book shelf and read the book
		
	}


	private void cs2() {
		//GroundItem ore = GroundItems.getNearest(2892);
		Player local = ctx.players.local();
		if(Method.objIsNotNull(18585)){
		if((ctx.varpbits.varpbit(2675)>>6&0x1)==1){//if bellows is fixed
			if((ctx.varpbits.varpbit(2675)>>7&0x1)==1){//If furnace is fixed
				if(Method.inventoryContains(2893)){
					DeltaQuester.progress = 9;
					if(new Tile(2718,9890,0).distanceTo(local.tile())<4){
						if(ctx.widgets.widget(1370).valid()){
							if(ctx.widgets.component(1370,56).text().contains("Elemental shield")){
								ctx.widgets.component(1370,38).click(true);
							}else ctx.widgets.component(1371,44).component(2).click(true);
						}else Method.interactO(3402, "Smith", "Anvil");
					}else Method.clickOnMap(new Tile(2718,9890,0));
				}else
				if(Method.inventoryContains(2892)){
					if((ctx.varpbits.varpbit(2675)>>9&0x1)==1){//Is the bellows turned on?
						DeltaQuester.progress = 8;
						if(new Tile(2723,9875,0).distanceTo(local.tile())<4){
							Method.useItemOn(2892, 3410, "Furnace");
						}else Method.clickOnMap(new Tile(2723,9875,0));
					}else{//Turns on bellows(air pump)
						DeltaQuester.progress = 7;
					if(new Tile(2734,9888,0).distanceTo(local.tile())<4){
						Method.interactO(3409, "Pull", "Lever");
					}else Method.clickOnMap(new Tile(2734,9888,0));
				}
				}else{
					DeltaQuester.progress = 6;
				if(Method.objIsNotNull(2892)){//ore
					if(Method.getObject(2892).tile().distanceTo(local.tile())<6){
						Method.interactG(2892, "Take", "Elemental ore");
					}else Method.clickOnMap(Method.getObject(2892).tile());
				}else
				if(new Tile(2704,9896,0).distanceTo(local.tile())<7){
					if(local.interacting()!=null){
						Method.basicFightNPC(4911);
					}else
					Method.npcInteract(4911, "Mine");
				}else Method.clickOnMap(new Tile(2704,9896,0));
			}
			}else{//Fix the furnace
				DeltaQuester.progress = 5;
			if(Method.inventoryContains(2889)){//bowl of lava
				if(new Tile(2724,9875,0).distanceTo(local.tile())<4){
					Method.useItemOn(2889, 3410, "Furnace");
				}else Method.clickOnMap(new Tile(2724,9875,0));
			}else
			if(Method.inventoryContains(2888)){//Stone bowl
				if(new Tile(2717,9872,0).distanceTo(local.tile())<4){
					Method.useItemOn(2888, 18520, "Lava");
				}else Method.clickOnMap(new Tile(2717,9872,0));
			}else
			if(new Tile(2723,9894,0).distanceTo(local.tile())<4){
				Method.interactO(3397, "Search", "Boxes");
			}else Method.clickOnMap(new Tile(2723,9894,0));
		}
		}else{//Fix the bellows{
			DeltaQuester.progress = 4;
		if(new Tile(2734,9888,0).distanceTo(local.tile())<4){
			Method.interactO(3407, "Fix", "Bellows");
		}else Method.clickOnMap(new Tile(2734,9888,0));
		}
		}else cs1();//get into the workshop
	}


	private void cs1() {//Start the waterwheel machine
		//SceneObject door2 = SceneEntities.getNearest(25819);
		Player local = ctx.players.local();
	if(Method.objIsNotNull(18585)){
		
		if((ctx.varpbits.varpbit(2675)>>4&0x1)==1){//right lever(water controls)
			if((ctx.varpbits.varpbit(2675)>>3&0x1)==1){//left lever(water controls)
				if(new Tile(2722,9905,0).distanceTo(local.tile())<4){
					Method.interactO(3406, "Pull", "Lever");
				}else Method.clickOnMap(new Tile(2722,9905,0));
			}else{
				DeltaQuester.progress = 3;
			if(new Tile(2713,9907,0).distanceTo(local.tile())<3){//the water controls to the right of the room
				Method.interactO(3404, "Turn", "Water controls");
			}else Method.clickOnMap(new Tile(2713,9907,0));
			}
		}else{
			DeltaQuester.progress = 2;
		if(new Tile(2726,9907,0).distanceTo(local.tile())<3){//the water controls to the right of the room
			Method.interactO(3403, "Turn", "Water controls");
		}else Method.clickOnMap( new Tile(2726,9907,0));
		}
	}else
		if(new Tile(2709,3493,0).distanceTo(local.tile())<4){
			if(EntranceDoor.contains(local.tile())){
				Method.interactO(25795, "Climb", "Stairs");
			}else Method.interactO(26114, "Open", "Wall");
		}else if(new Tile(2709,3493,0).distanceTo(local.tile())<30){
			if (Method.objIsNotNull(25819) && StartDoor.contains(Method.getObject(25819).tile())){
				Method.interactO(25819, "Open", "Door");
			}else Method.clickOnMap(new Tile(2709,3493,0));
		}else cs0();//get to the area
		
	}


	private void cs0() {//Start the quest by playing with some books
		//SceneObject door = SceneEntities.getNearest(25819);
		Player local = ctx.players.local();
		DeltaQuester.progress = 1;
		if(new Tile(2712,3483,0).distanceTo(local.tile())<4||new Tile(2715,3481,0).distanceTo(local.tile())<3){
			
			if(Method.objIsNotNull(25819) && !StartDoor.contains(Method.getObject(25819).tile()) || !Method.objIsNotNull(25819)){
				if(new Tile(2715,3481,0).distanceTo(local.tile())<2){
					if(Method.inventoryContains(2886)){//some book
					Method.interactInventory(2886, "Read", "Book");	
					}else{
					Method.skipPics();
					Method.interactO(26113, "Search", "Bookshelf");
					}
				}else Method.clickOnMap(new Tile(2715,3481,0));
			}else Method.interactO(25819, "Open", "Door");//open the door in our way
			
		}else if(Vars.DYNAMICV){
			Method.walking(pathToBookHouse, "Walking to the book shelf", false);
		}else if(TeleportLode.CATHERBY.getTile().distanceTo(local.tile())<10 || TeleportLode.SEERS.getTile().distanceTo(local.tile())<10){
			Vars.DYNAMICV = true;
		}else if(Method.SeersLodeIsActive()){
			Method.teleportTo(TeleportType.SEERS.getTeleport(),TeleportType.SEERS.getName());
		}else Method.teleportTo(TeleportType.CATHERBY.getTeleport(),TeleportType.CATHERBY.getName());
		
	}
	
}
