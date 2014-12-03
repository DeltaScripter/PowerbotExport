package OldQuester.quests;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import web.RSWeb;
import OldQuester.DeltaOldQuester;
import OldQuester.OldMethod;
import OldQuester.OldNode;

public class OldWaterFall extends OldNode{

	public OldWaterFall(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return DeltaOldQuester.scriptSelect==4;
	}

	
	OldMethod Method = new OldMethod(ctx);
	private boolean init = true;
	
	@Override
	public void execute() {
		
		DeltaOldQuester.numSteps = 10;
		
		
		while(ctx.widgets.component(49, 0).visible()){
			Method.state("Closing the book!");
			Method.clickOnMap(ctx.players.local().tile());
		}
		
		
		
		
	    if(init){
			Method.state("Initializing..");
		 if(Method.equipmentOpened())
			if(Method.EquipmentContains(AMULET)){
				System.out.println("Detected amulet in equipment");
				hasAmulet = true;
				init = false;
			}else if(!Method.EquipmentContains(AMULET)){
				init = false;
			}
			
			
		}else
	
			if(ctx.varpbits.varpbit(65)==9||
					ctx.varpbits.varpbit(65)==10){
				DeltaOldQuester.progress=10;
				//COMPLETED QUEST!
				Method.state("COMPLETED QUEST!!");
				Method.sleep(2000);
				DeltaOldQuester.shutOff = true;
			}else
				
		if(ctx.varpbits.varpbit(65)==8){
			DeltaOldQuester.progress=9;
			//hasAmulet = true;
			if(new Tile(2602,9911,0).matrix(ctx).reachable()){
			  if(Method.getToNearByTile(new Tile(2602,9911,0)))
			    Method.useItemOn(URN, 2014, "");//click on this to finish quest! --- chalice = 2014
			}else {
				cs3();//get to chalice area?
			}
		}else
		if(ctx.varpbits.varpbit(65)==7){
			DeltaOldQuester.progress=8;
			cs3();//it didn't ever go to this setting - skipped from 6 to 8 after putting necklace on statue
		}else
		if(ctx.varpbits.varpbit(65)==6){
			DeltaOldQuester.progress=7;
			cs3();//do waterfall parts including runes
		}else
		if(ctx.varpbits.varpbit(65)==5){
			DeltaOldQuester.progress=6;
			cs3();//turns to this setting upon entering the waterfall(last area) - but still need to do cs3 anyways(cs3 does cs4)
		}else
		if(ctx.varpbits.varpbit(65)==4){
			DeltaOldQuester.progress=5;
			cs3();//setting changes to this after opening the chest in tomb - still need to do cs3 though.
		}else
		if(ctx.varpbits.varpbit(65)==3){
			DeltaOldQuester.progress=4;
			cs3();//get the stone from the gnome cave - head to tomb and grab needed items
		}else
		if(ctx.varpbits.varpbit(65)==2){
			DeltaOldQuester.progress=3;
			cs2();//Get out o the waterfall - head to the book house - go upstairs and get book then read it
		}else
		if(ctx.varpbits.varpbit(65)==1){
			DeltaOldQuester.progress=2;
			cs1();//get to the crash site and speak to Hudon to progress
		}else{
			DeltaOldQuester.progress=1;
			cs0();//Speak to Almera and start the quest
		}
	
	}
	private int TOPSTAIRS = 16673;
	private int LADDERMAZE = 5250;
	private int CRATE = 1990;
	private int KEY = 293;
	private int LOCKEDDOOR = 1991;
	
	private int GOLRIE = 4183;
	private int PEBBLE = 294;
	private int AMULET = 295;
	private int URN = 296;
	
	private int LADDEROUT = 17387;
	
	private int GLARIALTOMBSTONE  = 1992;
	private int LADDERTOMB = 17387;//ladder leading out in the tombstone of Glarial
	private int CHESTCLOSED = 1994;
	private int CHESTOPEN = 1995;
	private int GRAVE = 1993;
	

	
	private boolean hasPebble = false;
	private boolean hasAmulet = false;
	private boolean hasUrn = false;
	
	private void cs3() {//enters gnome cave - gets pebble,exits, later enters tomb cave - gets urn and amulet, then does cs4!
		final int[] bounds = {84, 32, -144, 0, 16, 132};//book door
		final int[] bounds2 = {0, 132, -228, 4, 92, 112};//lockeddoor
		final String opt[] = {""};
		
		if(Method.inventoryContains(PEBBLE)){hasPebble = true;}
		if(Method.inventoryContains(AMULET)){hasAmulet = true;}
		if(Method.inventoryContains(URN)){hasUrn = true;}
		
		if(ctx.game.floor()==1){
			Method.interactO(TOPSTAIRS, "", "Stairs in book house");
		}else if(!new Tile(2521,3430,0).matrix(ctx).reachable() && Method.tileDisctanceToPlayer(new Tile(2521,3430,0))<20){//book area
			Method.getPastDoor(new Tile(2518,3431,0),new Tile(2521,3430,0), BOOKDOOR, bounds);
		}else{//above gets us out of the book house
			
			if(hasPebble){
				System.out.println("have pebble");
				if(new Tile(2515,9573,0).matrix(ctx).reachable()){//area outside of door
					if(Method.getToNearByTile(new Tile(2535,9555,0))){
						Method.interactO(LADDEROUT, "", "Ladder leading out of cave");
					}
				}else
				if(new Tile(2515,9578,0).matrix(ctx).reachable()){//by the npc - close to door as well
					if(Method.getToNearByTile(new Tile(2515,9576,0))){//by door
						Method.preciseInteractO(LOCKEDDOOR, bounds2, "");//open door
					}
				}else{//if not in cave any longer..
					if(hasUrn && hasAmulet){
						
						System.out.println("have both items");
					if(new Tile(2557,9844,0).matrix(ctx).reachable()){//if still in the tomb..
						if(Method.getToNearByTile(new Tile(2557,9844,0))){//tile by exit of tomb
							Method.state("Exiting tomb");
							Method.interactO(LADDERTOMB, "", "");
						}
					}else{//if we have all the items from tomb and are outside of the tomb..
						System.out.println("Doing cs4");
						cs4();//do other sutff..
						
						
					
					}
					}else
					if(new Tile(2556,9845,0).matrix(ctx).reachable()){//tile inside the tomb w/necklace and urn
						
						if(hasAmulet){
							
								if(Method.getToNearByTile(new Tile(2542,9809,0))){//tile by grave
									Method.interactO(GRAVE, "", "");//get the urn from the grave in the tomb
								}
							
							
						}else{
						
						if(Method.getToNearByTile(new Tile(2531,9844,0))){//tile by chest
							Method.interactO(CHESTCLOSED, "", "");
							Method.interactO(CHESTOPEN, "", "");
						}
						
					}
					}else
					if(Method.tileDisctanceToPlayer(new Tile(2561,3445,0))<6){//tile by tomb stone on outside
						Method.useItemOn(PEBBLE, GLARIALTOMBSTONE, "");
					}else{
					
					Method.state("Walking to temple");
					RSWeb web = new RSWeb(ctx);
					 Tile dest = new Tile(2561,3445,0);
					 web.walkToTile(dest);
					 
					}
				
				}
				
			}else
			
			if(new Tile(2515,9580,0).matrix(ctx).reachable()){//area with trapped gnome
				//the is a delayedment with conversation - add sleep?
				Method.sleep(1000);
				Method.generalChatWithNPC(opt, GOLRIE);
				
			}else
			if(new Tile(2534,9555,0).matrix(ctx).reachable()){//if we are in the cave of the gnome maze..
				
				if(Method.inventoryContains(KEY)){
					if(Method.getToNearByTile(new Tile(2515,9575,0))){//tile by locked door
						Method.useItemOn(KEY, LOCKEDDOOR,  bounds2,"");//open locked door to auto enter it
					}
				}else
				if(Method.getToNearByTile(new Tile(2544, 9564, 0))){//tile by crate
					Method.interactO(CRATE, "", "The cave crate containing key");
				}
			}else
			
			if(Method.tileDisctanceToPlayer(new Tile(2535,3155, 0))<4){//tile by ladder leading to cave
				Method.interactO(LADDERMAZE, "", "");
			}else{
			Method.state("Walking to the Gnome Maze");
			 RSWeb web = new RSWeb(ctx);
			 Tile dest = new Tile(2533, 3155, 0);
			 web.walkToTile(dest);
		
			}
		}
	}

  private int LEDGEDOOR = 2010;//door in the waterfall going to last place!
  private int DOOREAST = 7111;//eastern door in waterfall
  private int DOORWEST = 7111;//western door in waterfall
  private int CRATEEAST = 1999;//crate in waterfall
  
  private int KEY2 = 298;
  private int FINALDOOR = 2002;
	private int PILLAR = 2004;
	private int STATUE = 2006;
	
	private int AIRRUNE = 556;
	private int EARTHRUNE = 557;
	private int WATERRUNE = 555;
  
  private boolean hasKey2 = false;
  
	private void cs4() {
		
		final int[] bounds = {-116, 140, -224, 44, 120, 88};//western door in river
		final int[] bounds2 = {-20, 124, -216, 56, 120, 100};// final door
		
	
		if(Method.inventoryContains(KEY2)){hasKey2 = true;}
		
		if(new Tile(2573,9866,0).matrix(ctx).reachable() || 
				new Tile(2584,9875,0).matrix(ctx).reachable()||
				new Tile(2566,9900,0).matrix(ctx).reachable()||
				new Tile(2565,9906,0).matrix(ctx).reachable())//inside the waterfall!
		{
			
			if(new Tile(2565,9906,0).matrix(ctx).reachable()){//if inside final area of quest!
				 
				
				if(Method.inventoryGetCount(AIRRUNE)==6||
						Method.inventoryGetCount(EARTHRUNE)==6||
						Method.inventoryGetCount(WATERRUNE)==6){
					useRunes(new Tile(2563,9910,0),6,6,6);
				}else
				if(Method.inventoryGetCount(AIRRUNE)==5||
						Method.inventoryGetCount(EARTHRUNE)==5||
						Method.inventoryGetCount(WATERRUNE)==5){
					useRunes(new Tile(2563,9912,0),5,5,5);
				}else if(Method.inventoryGetCount(AIRRUNE)==4||
						Method.inventoryGetCount(EARTHRUNE)==4||
						Method.inventoryGetCount(WATERRUNE)==4){
					useRunes(new Tile(2563,9914,0),4,4,4);
				}else if(Method.inventoryGetCount(AIRRUNE)==3||
						Method.inventoryGetCount(EARTHRUNE)==3||
						Method.inventoryGetCount(WATERRUNE)==3){
					useRunes(new Tile(2568,9910,0),3,3,3);
				}else if(Method.inventoryGetCount(AIRRUNE)==2||
						Method.inventoryGetCount(EARTHRUNE)==2||
						Method.inventoryGetCount(WATERRUNE)==2){
					useRunes(new Tile(2568,9912,0),2,2,2);
				}else if(Method.inventoryGetCount(AIRRUNE)==1||
						Method.inventoryGetCount(EARTHRUNE)==1||
						Method.inventoryGetCount(WATERRUNE)==1){
					useRunes(new Tile(2568,9914,0),1,1,1);
				}else{
					
					if(Method.inventoryContains(AMULET)){
						Method.useItemOn(AMULET, STATUE, "");
					}else if(!Method.Takeoff(AMULET))
						Method.Takeoff(AMULET);
					
				}
				
				
			}else//below gets the second key and uses it on doors to get to above area
			if(hasKey2){
				if(new Tile(2566,9900,0).matrix(ctx).reachable()){//last area of quest - hallways leading to it!
					if(Method.getToNearByTile(new Tile(2566,9901,0))){//door leading to final area
						Method.useItemOn(KEY2, FINALDOOR, bounds2,"");//get past door#3
					}
				}else
				if(Method.getPastDoor(new Tile(2564, 9881, 0),new Tile(2564, 9885, 0),DOORWEST, bounds)){//get past door#1
					if(Method.getToNearByTile(new Tile(2568,9893,0))){//get past door#2
						Method.useItemOn(KEY2, FINALDOOR, bounds,"");
					}
				}
			}else
			
			if(Method.getPastDoor(new Tile(2581,9875,0), new Tile(2584,9876,0), DOOREAST)){
				if(Method.getToNearByTile(new Tile(2588,9888,0))){//tile by crate
					Method.interactO(CRATEEAST, "", "Eastern crate in waterfall");
				}
			}
			
			//end of stuff we do it waterfall area
		}else	
		if(new Tile(2511,3463,0).matrix(ctx).reachable() &&
				Method.tileDisctanceToPlayer(new Tile(2511,3463,0))<10){//last waterfall area
			while(Method.inventoryContains(AMULET)){
				Method.state("Equiping the amulet");
				Method.interactInventory(AMULET, "", "");
			}
			
			Method.interactO(LEDGEDOOR, "", "Door at waterfall");
			
		}else
		if(new Tile(2513,3467,0).matrix(ctx).reachable() &&
				Method.tileDisctanceToPlayer(new Tile(2513,3467,0))<10){//second area
			
			if(!ctx.players.local().inMotion()){
				Method.useItemOn(ROPE, DEADTREE, "");
			}
			
		}else
		if(new Tile(2512,3480,0).matrix(ctx).reachable()&&
					Method.tileDisctanceToPlayer((new Tile(2512,3480,0)))<10){//initla crash site
			
			if(ctx.players.local().tile().equals(new Tile(2512,3476,0))){
				if(ctx.camera.pitch()>50)
				ctx.camera.pitch(40);
				Method.useItemOn(ROPE, FIRSTROCK,"");
			}else Method.clickOnMap(new Tile(2512,3476,0));
			
		}else cs1();//get to the crash site again..
	}


	private void useRunes(Tile tile, int i, int h, int j) {
		if(tile.equals(ctx.players.local().tile())){
			if(Method.inventoryGetCount(AIRRUNE)<i){
				if(Method.inventoryGetCount(EARTHRUNE)<h){
					if(Method.inventoryGetCount(WATERRUNE)<j){
						Method.state("Done pillar!");
					}else Method.useItemOn(WATERRUNE, PILLAR, "");
				}else Method.useItemOn(EARTHRUNE, PILLAR, "");
			}else Method.useItemOn(AIRRUNE, PILLAR, "");
		}else if(Method.tileDisctanceToPlayer(tile)<6){
			tile.matrix(ctx).click();
		}else Method.clickOnMap(tile);
	}


	private int FIRSTROCK  =1996;//by crash site
	private int ROPE = 954;
	private int DEADTREE = 2020;
	
	private int BOOKDOOR = 7143;
	private int STAIRBOOK = 16671;
	private int HEDLY = 4179;
	private int BOOKSHELF = 1989;
	private int BOOK = 292;
	
	private void cs2() {//Get out o the waterfall - head to the book house - go upstairs and get book then read it
		final int[] bounds = {84, 32, -144, 0, 16, 132};//book door
		
		final String[] opt = {"Is there treasure under","Where else is worth","Can you tell me what happened"};
		
		
		if(new Tile(2513,3467,0).matrix(ctx).reachable()){//second area
			if(!ctx.players.local().inMotion()){
				Method.useItemOn(ROPE, DEADTREE, "");
			}
		}else
		if(new Tile(2512,3480,0).matrix(ctx).reachable()){//initla crash site
			if(ctx.players.local().tile().equals(new Tile(2512,3476,0))){
				if(ctx.camera.pitch()>40)
				ctx.camera.pitch(30);
				Method.useItemOn(ROPE, FIRSTROCK,"");
			}else Method.clickOnMap(new Tile(2512,3476,0));
		}else {//above gets us out of the trapped crash site area
			
			if(Method.inventoryContains(BOOK)){
				Method.interactInventory(BOOK, "", "Book");
			}else
			  if(ctx.game.floor()==1){
				  Method.interactO(BOOKSHELF, "", "Book shelf");//get the book in the shelf
			  }else
			if(Method.tileDisctanceToPlayer(new Tile(2521,3430,0))<20){
				if(Method.getPastDoor(new Tile(2521,3430,0), new Tile(2518,3431,0), BOOKDOOR, bounds)){
					if(Method.getToNearByTile(new Tile(2518,3428,0))){//tile by he stairs in book house
						Method.interactO(STAIRBOOK, "", "Stairs in book house");
					}
					
				}
			}else{
			
			Method.state("Walking to house with book");
			 RSWeb web = new RSWeb(ctx);
			 web.walkToTile(new Tile(2523, 3431, 0));//Almera tile area
		
			}
			
		}
	}


	private int BACKGATEALMERA = 7160;
	private int LOGRAFT = 1987;
	
	private int HUDON = 4182;
	
	private void cs1() {//get t o crash site and talk to Hudon(kid)
		final String[] opt = {""};
		
		
		if(new Tile(2512,3480,0).matrix(ctx).reachable()){//crash site
			
			Method.generalChatWithNPC(opt, HUDON);//the kid
			
		}else
		
		//below gets us to raft crash site!
		if(!new Tile(2513,3494,0).matrix(ctx).reachable() &&//by raft tile
				Method.tileDisctanceToPlayer(new Tile(2521,3497,0))>6){//tile in house
			cs0();//get to area!
		}else
		if(Method.getPastDoor(new Tile(2514,3495,0), new Tile(2511,3493,0), BACKGATEALMERA)){
			if(Method.getToNearByTile(new Tile(2511,3493,0)))
			Method.interactO(LOGRAFT, "", "Log raft");
		}
	}


	private int ALMERAGATE = 7160;
	private int ALMERADOOR = 7129;
	
	private int ALMERA = 4181;
	
	private void cs0() {//Speak to Almera to start the quest
		
		final String[] opt = {"How can I help?"};
		final int[] bounds = {28, 20, -236, -8, 8, 144};//door
		
		if(Method.tileDisctanceToPlayer(new Tile(2531,3495,0))>20){
			Method.state("Walking to Almera");
			 RSWeb web = new RSWeb(ctx);
			 web.walkToTile(new Tile(2530,3500, 0));//Almera tile area
		}else
		if(Method.tileDisctanceToPlayer(new Tile(2521,3497,0))<6 && 
				new Tile(2521,3497,0).matrix(ctx).reachable()){//tile inside house
			Method.generalChatWithNPC(opt, ALMERA);
		}else
		if(Method.getPastDoor(new Tile(2529,3495,0), new Tile(2526,3496,0), ALMERAGATE)){
			if(Method.getPastDoor(new Tile(2526,3496,0), new Tile(2521,3495,0), ALMERADOOR,bounds)){
				Method.getToNearByTile(new Tile(2521,3497,0));//inside fo house
			}
		}
		
	}

}
