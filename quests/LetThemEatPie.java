package quests;


import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Player;

import quests.Vars.TeleportLode;
import quests.Vars.TeleportType;

public class LetThemEatPie extends Node{

public LetThemEatPie(ClientContext ctx) {
		super(ctx);
		
	}


public final Tile[] pathToNail = new Tile[] { 
			new Tile(2898, 3544, 0), new Tile(2896, 3539, 0), new Tile(2897, 3534, 0), 
			new Tile(2898, 3529, 0), new Tile(2898, 3524, 0), new Tile(2898, 3519, 0), 
			new Tile(2898, 3514, 0), new Tile(2897, 3509, 0), new Tile(2895, 3504, 0), 
			new Tile(2894, 3499, 0), new Tile(2892, 3494, 0), new Tile(2891, 3489, 0), 
			new Tile(2891, 3484, 0), new Tile(2891, 3479, 0), new Tile(2893, 3474, 0), 
			new Tile(2893, 3469, 0), new Tile(2893, 3464, 0), new Tile(2890, 3460, 0), 
			new Tile(2888, 3455, 0), new Tile(2886, 3450, 0), new Tile(2882, 3447, 0), 
			new Tile(2880, 3443, 0) };

public final Tile[] pathToMill = new Tile[] { 
		new Tile(2898, 3545, 0), new Tile(2899, 3540, 0), new Tile(2899, 3535, 0), 
		new Tile(2899, 3530, 0), new Tile(2899, 3525, 0), new Tile(2899, 3520, 0), 
		new Tile(2899, 3515, 0), new Tile(2897, 3510, 0), new Tile(2895, 3505, 0), 
		new Tile(2891, 3502, 0), new Tile(2890, 3497, 0), new Tile(2890, 3492, 0), 
		new Tile(2890, 3487, 0), new Tile(2891, 3482, 0), new Tile(2892, 3477, 0), 
		new Tile(2893, 3472, 0), new Tile(2893, 3467, 0), new Tile(2890, 3463, 0), 
		new Tile(2888, 3458, 0), new Tile(2887, 3453, 0), new Tile(2885, 3448, 0), 
		new Tile(2884, 3443, 0), new Tile(2883, 3438, 0), new Tile(2883, 3433, 0), 
		new Tile(2882, 3428, 0), new Tile(2887, 3426, 0), new Tile(2892, 3427, 0) };

public final Tile[] pathToAnimals = new Tile[] { 
		new Tile(2898, 3544, 0), new Tile(2898, 3539, 0), new Tile(2897, 3534, 0), 
		new Tile(2897, 3529, 0), new Tile(2898, 3524, 0), new Tile(2899, 3519, 0), 
		new Tile(2898, 3514, 0), new Tile(2897, 3509, 0), new Tile(2895, 3504, 0), 
		new Tile(2893, 3499, 0), new Tile(2892, 3494, 0), new Tile(2891, 3489, 0), 
		new Tile(2891, 3484, 0), new Tile(2889, 3479, 0), new Tile(2885, 3482, 0) };

public final Tile[] pathToPotatoHole = new Tile[] { 
		new Tile(2898, 3545, 0), new Tile(2899, 3540, 0), new Tile(2901, 3535, 0), 
		new Tile(2901, 3530, 0), new Tile(2901, 3525, 0), new Tile(2900, 3520, 0), 
		new Tile(2898, 3515, 0), new Tile(2897, 3510, 0), new Tile(2895, 3505, 0), 
		new Tile(2893, 3500, 0), new Tile(2893, 3495, 0), new Tile(2893, 3490, 0), 
		new Tile(2893, 3485, 0), new Tile(2892, 3480, 0), new Tile(2889, 3476, 0), 
		new Tile(2884, 3478, 0), new Tile(2880, 3481, 0), new Tile(2875, 3482, 0), 
		new Tile(2870, 3480, 0), new Tile(2868, 3477, 0) };

public final Tile[] pathToOven = new Tile[] { 
		new Tile(2896, 3544, 0), new Tile(2897, 3539, 0), new Tile(2898, 3534, 0), 
		new Tile(2899, 3529, 0), new Tile(2899, 3524, 0), new Tile(2899, 3519, 0), 
		new Tile(2899, 3514, 0), new Tile(2896, 3510, 0), new Tile(2894, 3505, 0), 
		new Tile(2892, 3500, 0), new Tile(2891, 3495, 0), new Tile(2891, 3490, 0), 
		new Tile(2891, 3485, 0), new Tile(2891, 3480, 0), new Tile(2892, 3475, 0), 
		new Tile(2894, 3470, 0), new Tile(2894, 3465, 0), new Tile(2895, 3460, 0), 
		new Tile(2891, 3457, 0), new Tile(2889, 3452, 0), new Tile(2887, 3447, 0), 
		new Tile(2888, 3442, 0), new Tile(2893, 3440, 0), new Tile(2894, 3442, 0) };

public final Tile[] pathToPierre = new Tile[] { 
		new Tile(2896, 3544, 0), new Tile(2897, 3539, 0), new Tile(2898, 3534, 0), 
		new Tile(2899, 3529, 0), new Tile(2899, 3524, 0), new Tile(2899, 3519, 0), 
		new Tile(2899, 3514, 0), new Tile(2896, 3510, 0), new Tile(2894, 3505, 0), 
		new Tile(2892, 3500, 0), new Tile(2891, 3495, 0), new Tile(2891, 3490, 0), 
		new Tile(2891, 3485, 0), new Tile(2891, 3480, 0), new Tile(2892, 3475, 0), 
		new Tile(2894, 3470, 0), new Tile(2894, 3465, 0), new Tile(2895, 3460, 0), 
		new Tile(2891, 3457, 0), new Tile(2888, 3451, 0), new Tile(2886, 3446, 0), 
		new Tile(2883, 3442, 0), new Tile(2882, 3437, 0), new Tile(2882, 3432, 0), 
		new Tile(2881, 3427, 0), new Tile(2881, 3425, 0) };

public boolean hasMFlour = false;
public boolean hasStinkyCrayfish =false;
public boolean hasStinkyPotato = false;
public boolean hasSeal = false;

public int bankItems[] = {23075,23079,23077,23074,23073,23071,23078,23072,1931,313,1947,1942,13435};
public int bankItemAmount[] ={1,1,1,1,1,1,1,1,1,1,1,1,1};

public int itemsArray[] = {0,0,0,0,0};//contains the states of items needing to be purchased.
public int itemDID[] = {1931,313,1947,1942,13435};//contains the ids of the items needing to be purchased.
public int itemDAmount[] = {1,1,1,1,1};
public int itemDPrice[] = {1000,1000,1000,3500,2000};//contains specific prices to use upon purchasing specific items.
public String itemDString[] = {"Empty pot","Fishing bait","Wheat","Raw potato","Raw crayfish"};//contains the names of the items needing to be purchased.

Method Method = new Method(ctx);
Vars Vars = new Vars();
boolean q = true;

	public boolean activate() {
		return DeltaQuester.scriptToStart==31;
	}

	
	public void execute() {
		Method.setGeneralCamera();//get the camera pitch for general use on quests
		Method.resetTeleporting();
		DeltaQuester.numSteps =13;
		Method.foodSupport();
		if(q){
			q = false;
		}
		
		if(DeltaQuester.checkedBank)
			Method.determineBank(bankItems);
		
			if(!DeltaQuester.checkedBank&& (ctx.varpbits.varpbit(2674)&0x3F)!=40){
			Method.checkBank();
		}else
	    if(Vars.useBank && (ctx.varpbits.varpbit(2674)&0x3F)!=40){
			Method.useBank(bankItems, bankItemAmount);
		}else
		if (DeltaQuester.GEFeature&&(ctx.varpbits.varpbit(2674)&0x3F)!=40) {
			Method.useGE(itemDString, itemDID, itemDPrice, itemDAmount);
		}else
		if((ctx.varpbits.varpbit(2674)&0x3F)==40){
			DeltaQuester.progress = 13;
			Method.state("The Let Them Eat Pie quest has been completed.");
			Method.sleep(2000);
			DeltaQuester.e = true;
		}else
		if((ctx.varpbits.varpbit(2674)&0x3F)==35){
			DeltaQuester.progress = 12;
			cs0();//Speak to Nails and finish the quest
		}else
		if((ctx.varpbits.varpbit(2674)&0x3F)==30){
			DeltaQuester.progress = 11;
			cs5();//Speak to Pierre with the letter
			
		}else
		if((ctx.varpbits.varpbit(2674)&0x3F)==25){
			DeltaQuester.progress = 10;
			cs6();//Take the seal and show it to Nails
			
		}else
		if((ctx.varpbits.varpbit(2674)&0x3F)==20){
			DeltaQuester.progress = 9;
			cs6();//Feed the man the pie
			
		}else
		if((ctx.varpbits.varpbit(2674)&0xF)==15){
			DeltaQuester.progress = 8;
		    cs5();//Pickpocket Pierre
			
		}else
		if((ctx.varpbits.varpbit(2674)&0xF)==13){
			DeltaQuester.progress = 7;
		    cs0();//Speak to Nails about the cooked pie, "Cook the uncooked pie"
			
		}else
		if((ctx.varpbits.varpbit(2674)&0xF)==12){
			DeltaQuester.progress = 6;
		    cs4();//Cook the uncooked pie
			
		}else
		if((ctx.varpbits.varpbit(2674)&0xF)==10){
			DeltaQuester.progress = 5;
		    cs3();//Gather and combine the potato with the dish
			
		}else
		if((ctx.varpbits.varpbit(2674)&0xF)==9){
			DeltaQuester.progress = 4;
		    cs2();//Mkae the stinky crayfish
			
			
		}else
		if((ctx.varpbits.varpbit(2674)&0x7)==6){
			DeltaQuester.progress = 3;
		    cs1();//Make the maggotry flour and speak to Nails again
			
		}else if((ctx.varpbits.varpbit(2674)&0x7)==5){
			DeltaQuester.progress = 2;
			cs0();//Speak to Nails again to gather instructions
			
		}else if((ctx.varpbits.varpbit(2674)&0x7)==0){
			DeltaQuester.progress = 1;
			cs0();//Speak to Nails Newton to begin the quest
		
		}
		
	}



	private void cs6() {
		Player local = ctx.players.local();
		
		final String opt[] = {"I have nerves","Crayfish"};
		while((ctx.varpbits.varpbit(1114)&0x1)==1){
			Method.skipPics();
			if(!Method.findOption(opt))
			Method.isChatting("Cutscene");
		}
		if(hasSeal){
			cs0();
		}else
		if(!Method.teleporting&& Method.inventoryContains(23079)){
			hasSeal = true;
		}else
		if(ctx.game.floor()==1){
             if((ctx.varpbits.varpbit(2674)&0x3F)==25){
            	 Method.interactO(67552, "Open", "Chest");
             }else
			if(!Method.isChatting("Roloe the Stout")){
				Method.speakTo(15081, "Roloe the Stout");
			}
		}else if(new Tile(2887,3443,0).distanceTo(local.tile())<8){
			Method.interactO(7104, "Climb", "Stairs");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToOven, "Walking to the Antagonist", false);
		}else if(TeleportLode.BURTHORPE.getTile().distanceTo(local.tile())<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());
		
	}


	private void cs5() {
		
		Player local = ctx.players.local();
		
		if(!Method.teleporting && Method.inventoryContains(23077)){
			Vars.DYNAMICV = false;
			Method.combineItems(23077, 23076);
		}else
		if(new Tile(2881, 3425, 0).distanceTo(local.tile())<8){
			Vars.DYNAMICV = false;
			if((ctx.varpbits.varpbit(2674)&0x3F)==30){
				if(!Method.isChatting("Pierre")){
					Method.speakTo(15086, "Pierre");
				}
			}else
			Method.npcInteract(15086, "Pick");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToPierre, "Walking to Pierre", false);
		}else if(TeleportLode.BURTHORPE.getTile().distanceTo(local.tile())<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());
		
	}


	private void cs4() {
		Player local = ctx.players.local();
		
		if( new Tile(2894, 3442, 0).distanceTo(local.tile())<6){
			Method.interactO(67060,"Cook", "Oven");
		}else if(Vars.DYNAMICV2){
			Method.walking(pathToOven, "Walking to an oven", false);
		}else if(TeleportLode.BURTHORPE.getTile().distanceTo(local.tile())<10){
			Vars.DYNAMICV2 = true;
		}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());
		
	}


	private void cs3() {
		Player local = ctx.players.local();
		
		if(hasStinkyPotato ){
			Method.combineItems(23073, 23072);
		}else
		if(!Method.teleporting && Method.inventoryContains(23074)){
			Vars.DYNAMICV2 = false;
			hasStinkyPotato = true;
		}else
		if( new Tile(2868, 3477, 0).distanceTo(local.tile())<8){
			Method.useItemOn(1942, 66474, "Hole");
		}else if(Vars.DYNAMICV2){
			Method.walking(pathToPotatoHole, "Walking to the kebbit area", false);
		}else if(TeleportLode.BURTHORPE.getTile().distanceTo(local.tile())<10){
			Vars.DYNAMICV2 = true;
		}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());
		
	}


	private void cs2() {
		Player local = ctx.players.local();
		
		while((ctx.varpbits.varpbit(1114)&0x1)==1){
			Method.skipPics();
			Method.isChatting("Cutscene");
		}
		if(hasStinkyCrayfish){
		cs0();
		}else
		if(!Method.teleporting && Method.inventoryContains(23073)){
			hasStinkyCrayfish = true;
		}else if(Vars.DYNAMICV2){
			Method.walking(pathToAnimals, "Walking to animal pen", false);
		}else if(TeleportLode.BURTHORPE.getTile().distanceTo(local.tile())<10){
			Vars.DYNAMICV2 = true;
		}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());
		
	}


	private void cs1() {
		Player local = ctx.players.local();
		
		if(hasMFlour){
			cs0();
		}else if(!Method.teleporting && (Method.inventoryContains(23071)||Method.inventoryContains(23072))){
			Vars.DYNAMICV = false;
			hasMFlour = true;
		}else
		if((ctx.varpbits.varpbit(2674)>>11&0x1)==1){
			if(new Tile(2890,3428,0).distanceTo(local.tile())<8 && ctx.game.floor()==0){
				if(new Tile(2892,3425,0).distanceTo(local.tile())<4){
					Method.interactO(67770, "Take", "Flour bin");
				}else Method.clickOnMap(new Tile(2892,3425,0));
			}else if(ctx.game.floor()==1){
				Method.interactO(66638, "Climb", "Stairs");
			}else if(Vars.DYNAMICV){
				Method.walking(pathToMill, "Walking to the water mill", false);
			}else if(TeleportLode.BURTHORPE.getTile().distanceTo(local.tile())<10){
				Vars.DYNAMICV = true;
			}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());
			
		}else if(new Tile(2892,3426,1).distanceTo(local.tile())<8 && ctx.game.floor()==1){
			if((ctx.varpbits.varpbit(2674)>>10&0x1)==1){
				Method.interactO(67775, "Operate", "Controls");
			}else{
			Method.skipPics();
			if(!Method.isChatting("Self"))
			Method.useItemOn(1947, 67774, "Wheat");
			}
		}else if(new Tile(2890,3428,0).distanceTo(local.tile())<7){
			Method.interactO(66637, "Climb", "Stairs");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToMill, "Walking to the water mill", false);
		}else if(TeleportLode.BURTHORPE.getTile().distanceTo(local.tile())<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());
		
	}


	private void cs0() {
		final String opt[] = {"What do you need me"};
		Player local = ctx.players.local();
		
		if(new Tile(2880, 3443, 0).distanceTo(local.tile())<8){
			//Method.skipPics();
			if(!Method.startQuestOpen())
			if(!Method.findOption(opt))Vars.DYNAMICV2 = false;
				if(!Method.isChatting("Nails Newton")){
					Method.speakTo(15085, "Nails Newton");
				}
		}else if(ctx.game.floor()==1 && new Tile(2893,3442,1).distanceTo(local.tile())<10){
			Vars.DYNAMICV = true;
			Method.interactO(7107, "Climb-down", "Stairs");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToNail, "Walking to Newton Nail", false);
		}else if(TeleportLode.BURTHORPE.getTile().distanceTo(local.tile())<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());
		
	}
}
