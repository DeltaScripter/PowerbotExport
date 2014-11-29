package dqquests;

import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Hud.Window;

import dqbody.DeltaQuester;
import dqbody.Method;
import dqbody.DeltaNode;
import dqbody.Vars;
import lodestoneActivator.Data.TeleportLode;
import lodestoneActivator.Data.TeleportType;



public class Biohazard extends DeltaNode{

	public Biohazard(ClientContext ctx) {
		super(ctx);
	}

	
	public boolean activate() {
		return DeltaQuester.scriptToStart==41;
	}
	
	public final Tile[] pathToElena = {
			new Tile(2638,3336,0), new Tile(2648,3322,0),
			new Tile(2654,3309,0), new Tile(2642,3305,0),
			new Tile(2630,3297,0), new Tile(2617,3297,0),
			new Tile(2606,3297,0), new Tile(2596,3297,0),
			new Tile(2589,3307,0), new Tile(2582,3319,0),
			new Tile(2584,3330,0), new Tile(2591,3339,0)
	};
	public final Tile[] pathToJerico = {
			new Tile(2632,3335,0), new Tile(2617,3336,0),
			new Tile(2607,3329,0), new Tile(2610,3324,0)
	};
	
	public final Tile[] pathToGate  = {
			new Tile(2636,3335,0),new Tile(2648,3322,0),
			new Tile(2643,3304,0), new Tile(2627,3297,0),
			new Tile(2613,3297,0),new Tile(2595,3296,0),
			new Tile(2587,3310,0), new Tile(2574,3313,0),
			new Tile(2565,3301,0), new Tile(2560,3300,0)
	};
	public final Tile[] pathToOmart  = {
			new Tile(2636,3335,0),new Tile(2648,3322,0),
			new Tile(2643,3304,0), new Tile(2627,3297,0),
			new Tile(2613,3297,0),new Tile(2595,3296,0),
			new Tile(2587,3310,0), new Tile(2574,3313,0),
			new Tile(2565,3301,0), new Tile(2560,3300,0),
			new Tile(2560,3285,0), new Tile(2559,3267,0)
	};
	public final Tile[] pathToRimmington = {
			new Tile(2965, 3393, 0),new Tile(2975, 3379, 0),
			new Tile(2985, 3373, 0),new Tile(2997, 3364, 0),
			new Tile(3005, 3353, 0),new Tile(3004, 3339, 0),
			new Tile(3006, 3339, 0),new Tile(3005, 3328, 0),
			new Tile(3006, 3316, 0),new Tile(3014, 3276, 0),
			new Tile(3006, 3297, 0),new Tile(2999, 3284, 0),
			new Tile(3002, 3268, 0),new Tile(2994, 3253, 0),
			new Tile(2979, 3249, 0),new Tile(2970, 3244, 0),
			new Tile(2960, 3234, 0),new Tile(2956, 3219, 0),
			new Tile(2938, 3216, 0),new Tile(2932, 3215, 0),

	};
	public final Tile[] pathToVarrockBank = {
			new Tile(3213,3395,0), new Tile(3210,3417,0),
			new Tile(3229,3431,0), new Tile(3248,3429,0),
			new Tile(3253,3420,0)
	};
	public final Tile[] pathToKing = {
			new Tile(2642, 3330, 0),new Tile(2642, 3332, 0),
			new Tile(2649, 3318, 0),new Tile(2643, 3307, 0),
			new Tile(2643, 3306, 0),new Tile(2633, 3298, 0),
			new Tile(2620, 3298, 0),new Tile(2607, 3297, 0),
			new Tile(2594, 3297, 0),new Tile(2584, 3297, 0),
	        new Tile(2580, 3297, 0),
	};
	
	public Tile local;
	//public Timer wait = new Timer(0);
	public boolean hasDoctorSuit = false;
	public boolean init = false;
	public boolean stuffInBank = false;
	public boolean checkBank = false;
	public boolean teleported = false;
	public boolean teleported2 = false;
	public boolean gaveStuff = false;
	public boolean retrievedVials = false;
	
	public int bankItems[] = { 426,428 };
	public int bankItemAmount[] = {1,1};
	
	public int itemsArray[] = {0,0};//contains the states of items needing to be purchased.
	public int itemDID[] = {426,426};//contains the ids of the items needing to be purchased.
	public int itemDPrice[] = {2000,1000};//contains specific prices to use upon purchasing specific items.
	public int itemDAmount[] = {1,1};
	public String itemDString[] = {"Priest gown", "Priest gown"};//contains the names of the items needing to be purchased.
	

	Method Method = new Method(ctx);
	Vars Vars = new Vars();
	
	public void execute() {
		Method.foodSupport();
		Method.setGeneralCamera();//get the camera pitch for general use on quests
		local = ctx.players.local().tile();
	
		if(DeltaQuester.checkedBank&&(ctx.varpbits.varpbit(2535)&0x1F)==0)//only whenequal to 0, can't tele during quest at times
			Method.determineBank(bankItems);
		
		if(!DeltaQuester.checkedBank&&(ctx.varpbits.varpbit(2535)&0x1F)==0){
			Method.checkBank();
		}else
	    if(Vars.useBank&&(ctx.varpbits.varpbit(2535)&0x1F)==0){
			Method.useBank(bankItems, bankItemAmount);
		}else
		if(!init){
			while(!checkBank&&(ctx.varpbits.varpbit(2535)&0x1F)!=12&&
					(ctx.varpbits.varpbit(2535)&0x1F)!=14){
				local = ctx.players.local().tile();
				if(ctx.hud.opened(Window.WORN_EQUIPMENT))
				break;
				goCheckBankForItems();
			}
			if(Method.EquipmentContains(430)){
				hasDoctorSuit = true;
			}
			init = true;
			
		}else if((ctx.varpbits.varpbit(2535)&0x1F)==16){
			Method.state("The Biohazard quest has been completed.");
			//ctx.environment.sleep(2000);
			DeltaQuester.e = true;
		}else if((ctx.varpbits.varpbit(2535)&0x1F)==15){
			cs8();//Speak to king & finish quest
		}else if((ctx.varpbits.varpbit(2535)&0x1F)==14){
			cs0();//Tells Elena about the results
		}else  if((ctx.varpbits.varpbit(2535)&0x1F)==12){
			cs7();//give the items to people, bank sutff - tele - take stuff out - take items - speak to Guidor in varrock
		}else if((ctx.varpbits.varpbit(2535)&0x1F)==10){
			cs6();//bank the items, take them out & speak to chemist
		}else if((ctx.varpbits.varpbit(2535)&0x1F)==7){
			cs0();//give Elena her device
		}else if((ctx.varpbits.varpbit(2535)&0x1F)==6){
			cs5();//grab the doctor cloth and find the device
		}else if((ctx.varpbits.varpbit(2535)&0x1F)==5){
			cs4();//grab the rotton apple and use it on the cauldron
		}else if((ctx.varpbits.varpbit(2535)&0x1F)==4){
			cs4();//Speak to MArt and get inside the city
		}else if((ctx.varpbits.varpbit(2535)&0x1F)==2||
				(ctx.varpbits.varpbit(2535)&0x1F)==3){
			cs2();//go to gate and bait + release birds
		}else if((ctx.varpbits.varpbit(2535)&0x1F)==1){
			cs1();//Speak to Jerico about for advice + gather the bird cage and bird food
		}else if((ctx.varpbits.varpbit(2535)&0x1F)==0){
			cs0();//Speak to Elena and start the quest
		}
	
	}


	private void cs8() {//speaks to the king, finishes quest
		final String opt[] = {"I don't"};
		
		if(ctx.game.floor()==1){
			if(new Tile(2574,3296,1).matrix(ctx).reachable()){//inside room king
				if(Method.byCloseLoc(new Tile(2574,3296,1), 6)){//inside room
					if(!Method.findOption(opt))
					if(!Method.isChatting("Da Vinci")){
						Method.npcInteract(364,"Talk-to");
					}
				}
			}else if(Method.byCloseLoc(new Tile(2572,3296,1), 6)){//by king door
				Method.interactO(34825, "Open", "Door");
			}
		}else
		if(new Tile(2572,3287,0).matrix(ctx).reachable()){//by stairs
			if(Method.byCloseLoc(new Tile(2572,3287,0), 3)){//stairs
				Method.interactO(34871, "Climb-up", "Stairs");
			}
		}else
		if(new Tile(2572,3292,0).matrix(ctx).reachable()){//firt door in king plce
			if(Method.byCloseLoc(new Tile(2572,3292,0), 6)){//outside first door
				Method.interactO(34807, "Open", "Door");
			}
		}else
		if(new Tile(2580,3297,0).distanceTo(local)<7){//ouside king house
			Method.interactO(2546, "Open", "Door");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToKing, "Walking to the king's castle",false);
		}else if(TeleportLode.ARDOUGNE.getTile().distanceTo(local)<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.ARDOUGNE.getTeleport(), "Ardougne");
		
		
	}


	private void cs7() {//ethena -415, honey-416, 417 - sulphur
		final String opt1[] = {"You give him the vial of sul"};
		final String opt2[] = {"You give him the vial of liq"};
		final String opt3[] = {"You give him the vial of eth"};
		final String opt[] = {"So what does that","I've been sent by","I've come to ask"};
		
		while(Method.inventoryContains(428)||
				Method.inventoryContains(426)){
			Method.interactInventory(428, "Wear", "Priest gown");
			Method.interactInventory(426, "Wear", "Priest gown");
		}
		
		if(!Method.inventoryContains(418)){//plague sample
			stuffInBank = true;
		}
		if(teleported2){
			if(stuffInBank){//both should be true
				if(new Tile(3253,3420,0).distanceTo(local)<7){
					if(ctx.bank.open()){
						if(Method.inventoryContains(418)){//plague sample
							stuffInBank = false;
							ctx.bank.close();
						}else ctx.bank.withdraw(418, 1);
					}
				}else Method.walking(pathToVarrockBank, "Walking to Varrock bank", false);
			}else if(new Tile(3275,3389,0).matrix(ctx).reachable()||retrievedVials){//the bar
				if(retrievedVials){
					if(new Tile(3283,3382,0).matrix(ctx).reachable()){//analysis guy
						Vars.DYNAMICV = false;
						if(!Method.findOption(opt))
							if(!Method.isChatting("Guidor")){
								Method.npcInteract(343, "Talk-to");
							}
					}else
					if(new Tile(3281,3383,0).matrix(ctx).reachable()){//second part of house
						if(Method.byCloseLoc(new Tile(3281,3383,0),3)){//outside the analysis guy
						   Method.interactO(2032, "Open","Door");
						}
					}else
					if(new Tile(3282,3386,0).matrix(ctx).reachable()){//first part of house
						Method.state("Opening last door");
						Method.interactO(15536, "Open", "Door");
					}else
					if(Method.byCloseLoc(new Tile(3283,3389,0),2)){//outside the house
						Method.interactO(15536, "Open", "Door");
					}
				}else
				if(Method.byCloseLoc(new Tile(3269,3390,0),7)){//outside the bar
					if(Method.inventoryContains(416)){//honey
						if(Method.inventoryContains(415)){//ethena
							if(Method.inventoryContains(417)){//ethena
								retrievedVials = true;
							}else
							if(!Method.isChatting("Hops")){
								Method.npcInteract(341,"Talk-to");
							}
						}else
						if(!Method.isChatting("Da Vinci")){
							Method.npcInteract(337,"Talk-to");
						}
					}else
					if(!Method.isChatting("Chancy")){
						Method.npcInteract(339,"Talk-to");
					}
				}
			}else if(new Tile(3275,3389,0).matrix(ctx).reachable()){//inside danger area
				if(Method.byCloseLoc(new Tile(3275,3389,0),7)){//outside the bar
					Method.interactO(24381,"Open", "Door");
				}
			}else if(new Tile(3262,3406,0).distanceTo(local)<7){//outside pen dnager plce
				if(!Method.isChatting("")){
					//if(!wait.isRunning()){
					Method.interactO(2050, "Open", "");
					//wait = new Timer(4000);
					//}else Method.state("Waiting for search");
				}
			}else if(!ctx.movement.findPath(new Tile(3262,3406,0)).traverse()){
				Method.clickOnMap(new Tile(3262,3406,0));
			}
		}else
		if(gaveStuff){
			
			if(stuffInBank){
				if(TeleportLode.VARROCK.getTile().distanceTo(local)<20){
					teleported2 = true;
				}else Method.teleportTo(TeleportType.VARROCK.getTeleport(), "Varrock");
			}else if(new Tile(2994,3285,0).distanceTo(local)<7){//bank
				if(ctx.bank.opened()){
					if(Method.bankContains(418)){//plague sampel
						stuffInBank = true;
					}else ctx.bank.deposit(418, 1);//plague sample
				}else Method.interactO(88835, "Use", "Bank chest");
			}else if(!ctx.movement.findPath(new Tile(2994,3285,0)).traverse()){
				Method.clickOnMap(new Tile(2994,3285,0));
			}
		}else if(!Method.inventoryContains(415)&&
				!Method.inventoryContains(416)&&
				!Method.inventoryContains(417)){
			gaveStuff = true;
		}else
		if(new Tile(2931,3221,0).distanceTo(local)<7){//by the three stooges
			
			
			if(Method.inventoryContains(416)){//honey
				if(!Method.findOption(opt2)){
					if(!Method.isChatting("Chancy")){
						Method.npcInteract(338,"Talk-to");
					}
				}
			}else if(Method.inventoryContains(415)){//ethena
				if(!Method.findOption(opt3)){
					if(!Method.isChatting("Da vinci")){
						Method.npcInteract(336,"Talk-to");
					}
				}
			}else if(Method.inventoryContains(417)){//sulphur
				if(!Method.findOption(opt1)){
					if(!Method.isChatting("Hops")){
						Method.npcInteract(340,"Talk-to");
					}
				}
			}else gaveStuff = true;
			
		}else Method.clickOnMap(new Tile(2931,3221,0));//area above
		
	}


	private void goCheckBankForItems() {
		Method.state("Initializing; checking bank for vials");
		if(Method.inventoryContains(418)){//plague sample
			stuffInBank = false;
			checkBank = true;
			ctx.bank.close();
		}
		if(new Tile(2946,3368,0).distanceTo(local)<6){//bank tile
			if(ctx.bank.open()){
				if(Method.bankContains(418)){//an item, plague
					stuffInBank = true;
					checkBank = true;
					Vars.DYNAMICV = false;
					ctx.bank.close();
				}else{
					stuffInBank = false;
					checkBank = true;
					Vars.DYNAMICV = false;
					ctx.bank.close();
				}
			}
		}else if(Vars.DYNAMICV){
			Method.clickOnMap(new Tile(2946,3368,0));
		}else if(TeleportLode.FALADOR.getTile().distanceTo(local)<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.FALADOR.getTeleport(), "Falador");
		
		
		
	}


	private void cs6() {
		final int[] vials = {416,415,417,418};
		final String opt[]  ={"I just need some touch","It's ok,","Ask about Bio"};
		System.out.println("4");
		if(teleported){
			if(stuffInBank){//once tele you must take out items
				if(new Tile(3011,3355,0).distanceTo(local)<6){//bank tile
					if(ctx.bank.open()){
						if(Method.inventoryContains(416)&&//the items
								Method.inventoryContains(415)&&
								Method.inventoryContains(417)&&
								Method.inventoryContains(418)){
							stuffInBank = false;
						}else for(int items: vials){
							ctx.bank.withdraw(items, 1);
						}
					}
				}else if(Vars.DYNAMICV){
					Method.clickOnMap(new Tile(3011,3355,0));
				}else if(TeleportLode.FALADOR.getTile().distanceTo(local)<10){
					Vars.DYNAMICV = true;
				}else Method.teleportTo(TeleportType.FALADOR.getTeleport(), "Falador");
				
			}else
			if(new Tile(2933,3212,0).matrix(ctx).reachable()){//inside house rinngotm
				if(Method.byCloseLoc(new Tile(2933, 3210,0),7)){//by chemist
					if(!Method.findOption(opt)){
						if(!Method.isChatting("Chemist")){
							Method.npcInteract(367,"Talk-to");
						}
					}
				}
			}else
			     if(new Tile(2932,3215,0).distanceTo(local)<6){//rimmingoton door
			    	 Method.interactO(72004, "Open", "");
			     }else if(Vars.DYNAMICV){
						Method.walking(pathToRimmington, "Walking to Rimmington", false);
					}else if(TeleportLode.FALADOR.getTile().distanceTo(local)<10){
						Vars.DYNAMICV = true;
					}else Method.teleportTo(TeleportType.FALADOR.getTeleport(), "Falador");
					
		}else
		if(stuffInBank){
			if(!teleported&& !Method.inventoryContains(418)){//in case you didn't teleport without the items yet
				if(TeleportLode.FALADOR.getTile().distanceTo(local)<10){
					teleported = true;
				}else Method.teleportTo(TeleportType.FALADOR.getTeleport(), "Falador");
			}
				
		}else if(!Method.inventoryContains(418) && !ctx.bank.opened()){//plague sample
			cs0();//go get the smaple
		}else if(new Tile(2616,3333,0).distanceTo(local)<7){//bank
			if(ctx.bank.open()){
				for(int items: vials){
				  if(!Method.bankContains(items)){
					ctx.bank.deposit(items, 1);
				  }
				}
				if(!Method.inventoryContains(416)&&//the items
						!Method.inventoryContains(415)&&
						!Method.inventoryContains(417)&&
						!Method.inventoryContains(418)){
					stuffInBank = true;
				}
			}
		}else if(!new Tile(2592,3338,0).matrix(ctx).reachable()){
			Method.clickOnMap(new Tile(2616,3333,0));//bank tile
		}else Method.interactO(2054, "Open", "Door");
		
		
	}


	private void cs5() {//grab the doctor cloth and find the device
		System.out.println("1");
		if(!hasDoctorSuit){
			System.out.println("5");
			if(Method.inventoryContains(430)){//doctor suit
				hasDoctorSuit = true;
			}else
			if(new Tile(2517,3274,0).matrix(ctx).reachable()){//inside house
				if(Method.byCloseLoc(new Tile(2517,3274,0), 7)){
					Method.interactO(2062, "Open", "");
					Method.interactO(2063, "Search", "");
				}
			}else
			if(new Tile(2519,3274,0).distanceTo(local)<7){//outside house
				Method.interactO(34807, "Open", "Door");
			}else
			if(new Tile(2538,3328,0).matrix(ctx).reachable()){//city
				Method.state("Walking to house");
				ctx.movement.findPath(new Tile(2519,3274,0)).traverse();
			}else
			if(new Tile(2545,3331,0).matrix(ctx).reachable()){//sick house pen
				if(Method.byCloseLoc(new Tile(2542,3331,0), 7)){//by exit
					Method.interactO(2068, "Squeeze", "Hole");
				}
			}
		}else if(ctx.game.floor()==1){
			System.out.println("7");
			if(new Tile(2553,3325,1).matrix(ctx).reachable()){//in cage room
				Method.interactO(2064,"Search", "Crate");
			}else
			if(new Tile(2549,3325,1).matrix(ctx).reachable()){//upstairs other side
				if(Method.inventoryContains(423)){//key
					Method.useItemOn(423, 2058, "Use");//use key on cage
				}else
				Method.fightNPC(370);
			}else Method.interactO(2034, "Open","Door");
		}else if(new Tile(2545,3325,0).matrix(ctx).reachable()){//by the stairs
			System.out.println("6");
			if(Method.byCloseLoc(new Tile(2545,3325,0), 6)){//by stairs
				Method.interactO(34548, "Climb-up", "Stairs");
			}
		}else if(new Tile(2550,3322,0).matrix(ctx).reachable()){//inside mourner house bottom flr
			System.out.println("8");
			Method.interactO(34811, "Open", "");
		}else
		if(new Tile(2544,3330,0).matrix(ctx).reachable()){//inside pen cauldrin
			System.out.println("9");
			if(Method.byCloseLoc(new Tile(2551,3328,0), 6)){//by door
				while(Method.inventoryContains(430)){//doctor suit
					Method.interactInventory(430, "Wear", "Doctor suit");
				}
				if(!Method.isChatting("")){
					Method.interactO(2036, "Open", "");
				}
			}
		}else if(new Tile(2535,3298,0).matrix(ctx).reachable()){//the city
			if(Method.byCloseLoc(new Tile(2540,3331,0), 7)){//outside pen
			Method.interactO(2068, "Squeeze", "hole");
		     }
		}else getInsideCity();
	}


	private void getInsideCity() {
		final String opt[] = {"Ok, let's"};
		
	    if(new Tile(2559,3267,0).distanceTo(local)<7){//omart loc
	    	if(!Method.findOption(opt))
	    		if(!Method.isChatting("Omart")){
	    			Method.npcInteract(350, "Talk-to");
	    		}
	    }else if(Vars.DYNAMICV){
			Method.walking(pathToOmart, "Walking to Omart", false);
		}else if(TeleportLode.ARDOUGNE.getTile().distanceTo(local)<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.ARDOUGNE.getTeleport(), "Ardougne");
		
		
		
	}


	private void cs4() {//speaks to Omart, gets in city and causes food poisoning
		final String opt[] = {"Ok, lets"};
		System.out.println("2");
		if((ctx.varpbits.varpbit(2535)&0x1F)==5){//occurrs once in city
			if(Method.inventoryContains(1984)){//rotton apple
				if(new Tile(2544,3330,0).matrix(ctx).reachable()){//inside pen cauldrin
					Method.useItemOn(1984, 2043, "Use");
				}else if(Method.byCloseLoc(new Tile(2540,3331,0), 7)){//outside pen
					Method.interactO(2068, "Squeeze", "hole");
				}
			}else
			if(Method.byCloseLoc(new Tile(2535,3332,0), 7)){//by rotton apple
				Method.interactG(1984, "Take","Beautiful apple");
			}
		}else
	    if(new Tile(2559,3267,0).distanceTo(local)<7){//omart loc
	    	if(!Method.findOption(opt))
	    		if(!Method.isChatting("Omart")){
	    			Method.npcInteract(350, "Talk-to");
	    		}
	    }else if(Vars.DYNAMICV){
			Method.walking(pathToOmart, "Walking to Omart", false);
		}else if(TeleportLode.ARDOUGNE.getTile().distanceTo(local)<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.ARDOUGNE.getTeleport(), "Ardougne");
		
		
	}


	private void cs3() {//releases stuff at the gate\
		System.out.println("3");
		if(new Tile(2560,3300,0).distanceTo(local)<7){//gate loc
				Vars.DYNAMICV = false;
				if((ctx.varpbits.varpbit(2535)&0x1F)==3){
					Method.interactInventory(424, "Open", "Cage");
				}else
				Method.useItemOn(422, 2067,"Use");
			
		}else if(Vars.DYNAMICV){
			Method.walking(pathToGate, "Walking to the gate", false);
		}else if(TeleportLode.ARDOUGNE.getTile().distanceTo(local)<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.ARDOUGNE.getTeleport(), "Ardougne");
		
	}


	private void cs2() {//Takes bird food and cage + speaks to Elena afterwards
		if((ctx.varpbits.varpbit(2535)&0x1F)==3){//we don't need the stuff below
			cs3();
		}else
		if(Method.inventoryContains(422)){//bird feed
		    if(Method.inventoryContains(424)){//pigeon cage
		    	cs3();//Now speak to O-man
		    }else if(new Tile(2619,3325,0).distanceTo(local)<7){//cage area
		    	//by cage
		    	Method.skipPics();
		    	Method.interactG(424, "Take", "Cage");
		    	//ctx.game.sleep(2300);
		    }else if(new Tile(2619,3325,0).distanceTo(local)<14){
		    	Method.byCloseLoc(new Tile(2619,3325,0),7);//cage area
		    }else cs1();//get to the location
		}else cs1();//get the bird food
		
	}


	private void cs1() {//Speaks to Jerico and takes bird food
		final String opt[] = {"Maybe you could"};
		
		if(new Tile(2612,3324,0).matrix(ctx).reachable()&&
				new Tile(2612,3324,0).distanceTo(local)<14){//Jerico's house
			
			if((ctx.varpbits.varpbit(2535)&0x1F)==2){//for grabbing bird food
				Method.skipPics();
				//if(!wait.isRunning()){
				Method.interactO(2056, "Open", "Storage");
				Method.interactO(2057, "Search", "Storage");
				//wait = new Timer(2300);
				//}
			}else
				if(!Method.findOption(opt)){
					Vars.DYNAMICV = false;
			if(!Method.isChatting("Jerico")){
				Method.npcInteract(366,"Talk-to");//Jerico
			}
				}
		}else
		if(new Tile(2610,3324,0).distanceTo(local)<7){//outisde of it
			Method.interactO(34811, "Open", "Door");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToJerico, "Walking to Jeerico", false);
		}else if(TeleportLode.ARDOUGNE.getTile().distanceTo(local)<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.ARDOUGNE.getTeleport(), "Ardougne");
		
		
	}


	private void cs0() {//Speaks to Elena and starts quest
		final String opt[] = {"I'm afraid","I'll try"};
		
		if(new Tile(2592,3337,0).matrix(ctx).reachable()&&
				new Tile(2592,3337,0).distanceTo(local)<10){//inside Elena's house
			Method.skipPics();
		   if(!Method.startQuestOpen());
			if(!Method.findOption(opt)){
				Vars.DYNAMICV = false;
				if(!Method.isChatting("Elena")){
					Method.npcInteract(335, "Talk-to");
				}
			}
		}else
		if(new Tile(2591,3339,0).distanceTo(local)<6){//outside Elena house
			Method.interactO(2054, "Open", "Door");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToElena, "Walking to Elena's location", false);
		}else if(TeleportLode.ARDOUGNE.getTile().distanceTo(local)<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.ARDOUGNE.getTeleport(), "Ardougne");
		
	}

}
