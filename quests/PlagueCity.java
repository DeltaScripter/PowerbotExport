package quests;
/*package quests;

import org.powerbot.script.methods.ClientContext;
import org.powerbot.script.methods.Hud.Window;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.Item;
import org.powerbot.script.wrappers.Player;
import org.powerbot.script.Tile;

import quests.Vars.TeleportLode;
import quests.Vars.TeleportType;

public class PlagueCity extends Node{

	public PlagueCity(ClientContext ctx) {
		super(ctx);
	}
final Tile[] pathToEdmond = new Tile[] {
		 new Tile(2635, 3343, 0), new Tile(2635, 3338, 0), 
		new Tile(2638, 3334, 0), new Tile(2642, 3331, 0), new Tile(2646, 3328, 0), 
		new Tile(2648, 3323, 0), new Tile(2650, 3318, 0), new Tile(2654, 3314, 0), 
		new Tile(2654, 3309, 0), new Tile(2649, 3307, 0), new Tile(2644, 3306, 0), 
		new Tile(2639, 3304, 0), new Tile(2636, 3300, 0), new Tile(2631, 3298, 0), 
		new Tile(2626, 3296, 0), new Tile(2621, 3297, 0), new Tile(2616, 3297, 0), 
		new Tile(2611, 3297, 0), new Tile(2606, 3297, 0), new Tile(2601, 3296, 0), 
		new Tile(2596, 3296, 0), new Tile(2592, 3299, 0), new Tile(2590, 3304, 0), 
		new Tile(2587, 3309, 0), new Tile(2584, 3313, 0), new Tile(2583, 3318, 0), 
		new Tile(2581, 3323, 0), new Tile(2580, 3328, 0), new Tile(2579, 3333, 0), 
		new Tile(2575, 3336, 0), new Tile(2570, 3337, 0), new Tile(2568, 3334, 0) };
	
public int itemsArray[] = {0,0,0,0,0,0,0};//contains the states of items needing to be purchased.
public int itemDID[] = {2126,954,1929,1975,231,1927,952};//contains the ids of the items needing to be purchased.
public int itemDPrice[] = {2000,1000,1000,1000,2000,1000,1000};//contains specific prices to use upon purchasing specific items.
public int itemDAmount[] = {1,1,4,1,1,1,1};
public String itemDString[] = {"Dwellberries", "Rope", "Bucket of water",
		"Chocolate dust","Snape grass","Bucket of milk","Spade"};//contains the names of the items needing to be purchased.

public int bankItems[] = {2126,954,1929,1975,231,1927,952};
public int bankItemAmount[] = {1,1,4,1,1,1,1};

	boolean hasPaint = false;
	boolean init = false;

	private Method Method = new Method(ctx);
	private Vars Vars = new Vars();
	@Override
	public void execute() {
		
		Method.setGeneralCamera();//get the camera pitch for general use on quests
		DeltaQuester.numSteps = 12;
		if(!init){
			Method.state("Initializing");
			while(Method.inventoryContains(1506)){
				Method.interactInventory(1506, "Wear", "Mask");//gas mask
			}
			init = true;
		}else
		if(DeltaQuester.checkedBank)
			Method.determineBank(bankItems);
		
		if(!DeltaQuester.checkedBank){
			Method.checkBank();
		}else
	    if(Vars.useBank){
			Method.useBank(bankItems, bankItemAmount);
		}else
		if (DeltaQuester.GEFeature) {
			Method.onlyItemsGE = true;
			Method.useGE(itemDString, itemDID, itemDPrice, itemDAmount);
		}else if((ctx.varpbits.varpbit(2386)&0x1F)==29){
			DeltaQuester.progress  =12;
			Method.state("The Plague City quest has been completed.");
			ctx.environment.sleep(2000);
			DeltaQuester.e = true;
		}else if((ctx.varpbits.varpbit(2386)&0x1F)==28){
			DeltaQuester.progress  =11;
			cs10();
		}else if((ctx.varpbits.varpbit(2386)&0x1F)==27){
			DeltaQuester.progress  =10;
			cs9();//go and rescue the daughter
		}else if((ctx.varpbits.varpbit(2386)&0x1F)==26||
				(ctx.varpbits.varpbit(2386)&0x1F)==27){
			DeltaQuester.progress  =9;
			cs8();//makes drunk-cure and feed
		}else if((ctx.varpbits.varpbit(2386)&0x1F)==24||
				(ctx.varpbits.varpbit(2386)&0x1F)==25){
			DeltaQuester.progress  =8;
			cs7();//go to priest and speak to drunk
		}else if((ctx.varpbits.varpbit(2386)&0x1F)==22||
				(ctx.varpbits.varpbit(2386)&0x1F)==23){
			DeltaQuester.progress  =7;
			cs6();//Head to the odd house and try the door
		}else if((ctx.varpbits.varpbit(2386)&0x1F)==20||
				(ctx.varpbits.varpbit(2386)&0x1F)==21){
		DeltaQuester.progress = 6;
		    cs5();//Grab the book + give it and speak to people in house
		  }else
		 if((ctx.varpbits.varpbit(2386)&0xF)==10){
		    DeltaQuester.progress = 5;
		    cs4();//Speak to Jesthick in plague city about the missing daughter
	    }else
		if((ctx.varpbits.varpbit(2386)&0xF)==8||
				(ctx.varpbits.varpbit(2386)&0xF)==9){
			DeltaQuester.progress = 4;
			cs3();//Goe into the sewer and pulls of the grill
		}else
		if((ctx.varpbits.varpbit(2386)&0x3)==2||//various settings of using the buckets
				(ctx.varpbits.varpbit(2386)&0x3)==3||
				(ctx.varpbits.varpbit(2386)&0x7)==4||
				(ctx.varpbits.varpbit(2386)&0x7)==5||
				(ctx.varpbits.varpbit(2386)&0x7)==6){
			DeltaQuester.progress = 3;
			
			cs2();//Put water on the hole + dig it
		}else
		if((ctx.varpbits.varpbit(2386)&0x1)==1){
			DeltaQuester.progress = 2;
			cs1();//Speak to Alrena in house and get the mask
		}else {
			DeltaQuester.progress  =1;
			cs0();//Speak to edmond to start the quest
		}
		
	}
	
	private void cs10() {
	if(new Tile(2517,9758,0).getMatrix(ctx).isReachable()){//serwer
		if(Method.byCloseLoc(new Tile(2516,9755,0),7)){//by edmond in sewer
			Method.skipPics();
			if(!Method.isChatting("Edmond")){
				Method.speakTo(3214, "Edmond");//edmond in sewer
			 }
			}
	}else cs2();//get to sewer
		
	}

	private void cs9() {//resuce perosn
	
		if(Method.inventoryContains(1503)){//warrant
			if(new Tile(2541,9671,0).getMatrix(ctx).isReachable()){//basement
				Vars.DYNAMICV = false;
				Vars.DYNAMICV2 = false;
				if(!Method.isChatting("Person")){
					Method.npcInteract(715, "Talk-to");
				}
			}else
			if(new Tile(2538,9670,0).getMatrix(ctx).isReachable()){//basement
				Method.skipPics();
				if(!ctx.widgets.get(1186,0).isVisible())
				Method.interactO(2526, "Open", "Door");
			}else
			if(new Tile(2539,3271,0).getMatrix(ctx).isReachable()){//in odd house
				if(Method.inventoryContains(1507)){//key
					Method.interactO(2522, "Walk-down", "Stairs");
				}else Method.interactO(2530, "Search","Barrel");
			}else cs6();
		} else cs8();//gos and gets the permmision slip
	}

	private void cs8() {//makes drunk cure and feeeds it
		String opt[] = {"They won't"};
		
		
		if(ctx.game.floor()==1){
		if(new Tile(2535,3314,0).getMatrix(ctx).isReachable()){
			if((ctx.varpbits.varpbit(2386)&0x1F)==27){//after feeding
				Method.skipPics();
				if(!Method.findOption(opt)){
					if(!Method.isChatting("Drunk")){
						Method.npcInteract(711, "Talk-to");
					}
				}
			}else
		if(Method.inventoryContains(1504)){//hang-over bucket
			Method.skipPics();
			if(!Method.isChatting("Person"))
			Method.useItemOnNpc(1504, 711, "Use");//use drink on drunk
		}else
		if(Method.inventoryContains(1977)){//choco milk
			Method.combineItems(231, 1977);//snape grass + chocomilk
		}else Method.combineItems(1927, 1975);//choco + milk
		
	}else Method.interactO(2528, "Open", "Door");
	}else if(new Tile(2526,3313,0).getMatrix(ctx).isReachable()){//mansion
			if(Method.byCloseLoc(new Tile(2527, 3317,0),7))
			Method.interactO(34498, "Climb-up", "Stairs");
		}else cs7();
	}

	private void cs7() {//speaks to priest + the drunk
		String opt[] = {"Do you know","This is really","This is urgent","I need"};
		if((ctx.varpbits.varpbit(2386)&0x1F)==25){
			if(ctx.game.floor()==1){
				if(new Tile(2535,3314,0).getMatrix(ctx).isReachable()){//drunks room
					
					Method.skipPics();
					if(Method.byCloseLoc(new Tile(2535,3315,1),6))//by drunk
						if(!Method.findOption(opt))
							if(!Method.isChatting("King")){
								   Method.npcInteract(711, "Talk-to");//king
							   }
				}else if(Method.byCloseLoc(new Tile(2531,3316,1), 5))//by door to drunk room
					Method.interactO(2528, "Open", "Door");
				
			}else if(new Tile(2526,3313,0).getMatrix(ctx).isReachable()){//mansion
				if(Method.byCloseLoc(new Tile(2527, 3317,0),7))
				Method.interactO(34498, "Climb-up", "Stairs");
			}else cs4();
		}else
		if(new Tile(2526,3313,0).getMatrix(ctx).isReachable()){//inside the mansion bottom flr
			if(Method.byCloseLoc(new Tile(2527,3319,0), 7)){//by priest
				if(!Method.findOption(opt))
				if(!Method.isChatting("Priest")){
					   Method.npcInteract(713, "Talk-to");//priest
				   }
			}
		}else
		if(new Tile(2531,3327,0).getMatrix(ctx).isReachable()){//the city
			if(Method.byCloseLoc(new Tile(2525,3309,0), 7)){//outside the mansion
				Method.interactO(2546, "Open", "Door");
			}
		}
		
	}

	private void cs6() {//Heads to the odd house
		String opt[] = {"I want to check","But I think"};
		if(ctx.game.floor()==1){
			if(new Tile(2532,3315,0).getMatrix(ctx).isReachable()){//outside drunk room
				Method.interactO(34499, "Climb-down", "Stairs");
			}else Method.interactO(2528, "Open", "Door");
		}else if(!new Tile(2525,3308,0).getMatrix(ctx).isReachable()&&//the city
				new Tile(2526,3311,0).getMatrix(ctx).isReachable()){//the mansion
			System.out.println("HEERER3");
			if(Method.byCloseLoc(new Tile(2526, 3311,0),7)){
				System.out.println("HEERER4");
				Method.interactO(2548, "Open", "Door");
			}
		}
		if((ctx.varpbits.varpbit(2386)&0x1F)==23||//after speaking to louis up[stairs
				(ctx.varpbits.varpbit(2386)&0x1F)==27){
			System.out.println("HEERER5");
			if(new Tile(2538,3308,0).getMatrix(ctx).isReachable() && ctx.game.floor()==0){//in city
				System.out.println("HEERER6");
				if((ctx.varpbits.varpbit(2386)&0x1F)==27){
					if(Method.byCloseLoc(new Tile(2538,3274), 5)){//slightly diff loc
					
						 Method.skipPics();
					  if(!Method.findOption(opt))
					   if(!Method.isChatting("Guard")){
						if(Method.inventoryContains(1503)){//if you have warrant
						   Method.useItemOnNpc(1503, 3216, "Use");//use on guard
					     }
					   }
					}
				}else
				if(Method.byCloseLoc(new Tile(2533,3273), 8)){///odd house
				  Method.skipPics();
				  if(!Method.findOption(opt))
				   if(!Method.isChatting("Guard")){
					   if(!ctx.widgets.get(1184,0).isVisible())
					   Method.interactO(35991, "Open", "Door");
				   }
			   }
			}else if(ctx.game.floor()==1){
				if(Method.byCloseLoc(new Tile(2527,3332,1),4))//by ladder
				Method.interactO(34396,"Climb-down", "Ladder");
			}else if(new Tile(2529,3332,0).getMatrix(ctx).isReachable()){//inside houe bottom floor
				Method.interactO(2537, "Open", "Door");
			}else cs4();//get to city
		}else
		if((ctx.varpbits.varpbit(2386)&0x1F)==22){//now go speak to louis
			if(ctx.game.floor()==1){
				if(!Method.isChatting("Person")){
					Method.npcInteract(724, "Talk-to");
				}
			}else if(new Tile(2531,3330,0).getMatrix(ctx).isReachable()){//inside house bottom floor in city
				Method.interactO(34394, "Climb-up", "Ladder");
			}else cs5();
		}
		
	}

	private void cs5() {//speak to people in city house + grab & give book
		String opt[] = {"Yes, I'll","Hi, I'm look"};
		
		if(new Tile(2531,3330,0).getMatrix(ctx).isReachable()){//house in the city
			if(!Method.isChatting("Person")){
				Method.npcInteract(722, "Talk-to");
			}
		}else
	if(new Tile(2531,3305,0).getMatrix(ctx).isReachable()){//the city itself
		
		if(Method.inventoryContains(1509)){
		if(Method.byCloseLoc(new Tile(2531,3328,0), 7)){//by the house in plague city
			if(!Method.isChatting("Person")){
				Method.interactO(2537, "Open", "Door");
			}
		}//else get th book
		}else if(Method.byCloseLoc(new Tile(2539,3306,0), 7)){//by jethick
			
			Method.skipPics();
	    	if(!Method.findOption(opt))
	    	if(!Method.isChatting("Jesthick")){
				Method.speakTo(725, "Jesthick");//Jesthick by entrance
			}
	    }
	}else cs4();//get to the city
		
	}

	private void cs4() {//Speak to Jesthick in plague city
		String opt[] = {"Yes, I'll","Hi, I'm look"};
		
		if(new Tile(2531,3305,0).getMatrix(ctx).isReachable()){//plague city
			 Vars.DYNAMICV = false;
			if(Method.byCloseLoc(new Tile(2539,3306,0), 7)){//by jethick
				
				Method.skipPics();
		    	if(!Method.findOption(opt))
		    	if(!Method.isChatting("Jesthick")){
					Method.speakTo(725, "Jesthick");//Jesthick by entrance
				}
		    }
		}else
		if(new Tile(2517,9758,0).getMatrix(ctx).isReachable()){//sewer system
			System.out.println("Here6");
			Vars.DYNAMICV = false;
			if(Method.byCloseLoc(new Tile(2514,9739,0), 7)){//by pipe
				Method.interactO(2542, "Climb-up", "Pipe");
			}
		
		}else cs2();//Get to the sewer
		
	}

	private void cs3() {//pulls the sewer grill off
		
		while(ctx.varpbits.varpbit(1113)!=0){
			Method.state("Cutscene");
			Method.pressContinue();
		}
		
		if(new Tile(2517,9758,0).getMatrix(ctx).isReachable()){//sewer system
			Vars.DYNAMICV  =false;
			if((ctx.varpbits.varpbit(2387)&0x1F)==29){//After putting rope on grill
				if(Method.byCloseLoc(new Tile(2516,9755,0),7)){//by edmond in sewer
				Method.skipPics();
				if(!Method.isChatting("Edmond")){
					Method.speakTo(3214, "Edmond");//edmond in sewer
				 }
				}
			
			}else
			if((ctx.varpbits.varpbit(2387)&0xF)==13){//After initially attempting to open the grill
				if(Method.byCloseLoc(new Tile(2514,9739,0),7))
				Method.useItemOn(954, 11422, "Use");//use rope on grill
			}else//try to open grill initially
			if(Method.byCloseLoc(new Tile(2514,9739,0),7)){//by sewer pipe
				Method.skipPics();
				if(!ctx.widgets.get(1186,0).isVisible())
				Method.interactO(11422, "Open", "Grill");
			}
			
			
		}else cs2();//get to sewer
		
	}

	private void cs2() {//gets into the sewer system + digs hole
		Tile local = ctx.players.local().getLocation();
		while(Method.inventoryContains(1506)){//gas mask
			Method.interactInventory(1506, "Wear", "Mask");//gas mask
		}
		
		if((ctx.varpbits.varpbit(2386)&0x1F)==3||//keep using water buckets until done
				(ctx.varpbits.varpbit(2386)&0x1F)==4||
				(ctx.varpbits.varpbit(2386)&0x1F)==5||
				(ctx.varpbits.varpbit(2386)&0x1F)==6){//after knowing what to do
			if(new Tile(2568,3333,0).distanceTo(local)<7){
				Method.useItemOn(1929, 2532, "Use");//water on mud
			}else cs0();//get to the garden
		}else
		if((ctx.varpbits.varpbit(2386)&0x1F)==2){//need to speak to Edmond
			cs0();//Speaks to Edmond
		}else{
			if(new Tile(2568,3333,0).distanceTo(local)<7){
				  if(new Tile(2566,3332,0).equals(local)){//hole in ground
					//dig up the hole
					  Vars.DYNAMICV = false;
					  Method.skipPics();
					 Method.state("Ready to dig hole");
					Method.interactInventory(952, "Dig", "Spade");
					Method.interactO(2532, "Climb-down", "Hole");
				  }else {
					  new Tile(2566,3332,0).getMatrix(ctx).click();//hole spot
				  }
				}else cs0();//get to the garden
		}
		
		
	}

	private void cs1() {//Speaks to Alrena in house
		
		
		if(hasPaint){
			if(new Tile(2572,3333,0).distanceTo(ctx.players.local().getLocation())<2){
				Method.skipPics();
					if(!Method.isChatting("Person")){
						Method.speakTo(710, "Person");//woman in house by garden area
					}
				
			}else if(Method.objIsByTile(new Tile(2573,3333,0), 34807, 3)){//door 2
				openDoor(new Tile(2573,3333,0), 34807);
			}else if(Method.objIsByTile(new Tile(2570,3333,0), 34811, 3)){//door 1
				openDoor(new Tile(2570,3333,0), 34811);
			}else Method.clickOnMap(new Tile(2572,3333,0));
			
		}else if(Method.inventoryContains(1510)){
			hasPaint = true;
		}else
		if(new Tile(2575,3333,0).distanceTo(ctx.players.local().getLocation())<2){
			if(Method.gItemIsNotNull(1510))
			ctx.mouse.move(Method.getGroundItem(1510).getLocation().getMatrix(ctx).getPoint(Random.nextDouble() * 0.6D + 0.2D,+0.6D,-400));
			ctx.mouse.click(true);
		}else if(Vars.DYNAMICV2){
			if(Method.objIsByTile(new Tile(2570,3333,0), 34811, 3)){//door 1
			openDoor(new Tile(2570,3333,0), 34811);
		}else if(Method.objIsByTile(new Tile(2573,3333,0), 34807, 3)){//door 2
			openDoor(new Tile(2573,3333,0), 34807);
		}else Method.clickOnMap(new Tile(2575,3333,0));
		
		}else cs0();//get to the loc
		
	}

	private void openDoor(Tile tile, int id) {
		Player local = ctx.players.local();
		
		if(tile.distanceTo(local.getLocation())<5){
			Method.interactO(id, "Open", "Door");
		}else Method.findPath(tile, "Walking to door");
		
	}

	private void cs0() {//Speaks to edmond to start the quest
		Player local = ctx.players.local();
		final String opt[] = {"Can I help find","What's happened"};
		
		if(new Tile(2568,3333,0).distanceTo(local.getLocation())<6){
			Vars.DYNAMICV2  =true;
				if(!Method.startQuestOpen())
					if(!Method.findOption(opt))
						if(!Method.isChatting("Edmond")){
							Method.speakTo(3213, "Edmond");
						}
		}else if(Vars.DYNAMICV){
		Method.walking(pathToEdmond, "Walking to Edmond", false);
		}else if(TeleportLode.ARDOUGNE.getTile().distanceTo(local.getLocation())<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.ARDOUGNE.getTeleport(),TeleportType.ARDOUGNE.getName());
	}

	@Override
	public boolean activate() {
		return DeltaQuester.scriptToStart==38;
	}

}
*/