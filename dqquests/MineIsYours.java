package dqquests;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Component;
import org.powerbot.script.rt6.Player;
import org.powerbot.script.rt6.Skills;

import dqbody.Area;
import dqbody.DeltaQuester;
import dqbody.Method;
import dqbody.DeltaNode;
import dqbody.Vars;
import dqbody.Vars.TeleportLode;
import dqbody.Vars.TeleportType;

public class MineIsYours extends DeltaNode{

	public MineIsYours(ClientContext ctx) {
		super(ctx);
	}

	public final Tile[] pathToDoric = new Tile[] {
			new Tile(2966, 3401, 0), new Tile(2966, 3406, 0), new Tile(2966, 3411, 0), 
			new Tile(2962, 3414, 0), new Tile(2958, 3417, 0), new Tile(2954, 3420, 0), 
			new Tile(2951, 3424, 0), new Tile(2950, 3429, 0), new Tile(2951, 3434, 0), 
			new Tile(2954, 3438, 0), new Tile(2958, 3439, 0) };
	
	public final Tile[] pathToMine1 = new Tile[] { //Faladors
			new Tile(2965, 3403, 0), new Tile(2967, 3408, 0), new Tile(2969, 3413, 0), 
			new Tile(2974, 3414, 0), new Tile(2978, 3417, 0), new Tile(2983, 3420, 0), 
			new Tile(2987, 3424, 0), new Tile(2990, 3428, 0), new Tile(2995, 3429, 0), 
			new Tile(3000, 3430, 0), new Tile(3004, 3434, 0), new Tile(3009, 3436, 0), 
			new Tile(3014, 3437, 0), new Tile(3014, 3442, 0), new Tile(3014, 3447, 0), 
			new Tile(3018, 3448, 0) };
	
	public final Tile[] pathToMine2 = new Tile[] { //Varrok west
			new Tile(3214, 3376, 0), new Tile(3219, 3375, 0), new Tile(3224, 3374, 0), 
			new Tile(3230, 3373, 0), new Tile(3235, 3373, 0), new Tile(3240, 3372, 0), 
			new Tile(3245, 3371, 0), new Tile(3250, 3371, 0), new Tile(3255, 3370, 0), 
			new Tile(3260, 3368, 0), new Tile(3265, 3368, 0), new Tile(3270, 3368, 0), 
			new Tile(3275, 3369, 0), new Tile(3279, 3372, 0), new Tile(3284, 3373, 0), 
			new Tile(3289, 3372, 0), new Tile(3290, 3367, 0), new Tile(3289, 3370, 0) };
	
	public final Tile[] pathToMine3 = new Tile[] { //East varrok
			new Tile(3214, 3376, 0), new Tile(3209, 3376, 0), new Tile(3204, 3376, 0), 
			new Tile(3200, 3373, 0), new Tile(3195, 3371, 0), new Tile(3190, 3371, 0), 
			new Tile(3185, 3370, 0), new Tile(3180, 3368, 0), new Tile(3175, 3368, 0) };
	
	public final Tile[] pathToMine4 = new Tile[] {//Rimmington mine
			new Tile(2965, 3402, 0), new Tile(2964, 3397, 0), new Tile(2965, 3392, 0), 
			new Tile(2966, 3387, 0), new Tile(2967, 3382, 0), new Tile(2972, 3379, 0), 
			new Tile(2977, 3377, 0), new Tile(2982, 3376, 0), new Tile(2986, 3373, 0), 
			new Tile(2990, 3369, 0), new Tile(2995, 3367, 0), new Tile(2999, 3363, 0), 
			new Tile(3002, 3359, 0), new Tile(3005, 3355, 0), new Tile(3005, 3350, 0), 
			new Tile(3007, 3345, 0), new Tile(3007, 3340, 0), new Tile(3007, 3335, 0), 
			new Tile(3006, 3330, 0), new Tile(3006, 3325, 0), new Tile(3005, 3320, 0), 
			new Tile(3004, 3315, 0), new Tile(3004, 3310, 0), new Tile(3004, 3305, 0), 
			new Tile(3006, 3299, 0), new Tile(3006, 3294, 0), new Tile(3006, 3289, 0), 
			new Tile(3007, 3284, 0), new Tile(3007, 3279, 0), new Tile(3007, 3274, 0), 
			new Tile(3007, 3268, 0), new Tile(3007, 3263, 0), new Tile(3006, 3258, 0), 
			new Tile(3006, 3253, 0), new Tile(3006, 3248, 0), new Tile(3006, 3243, 0), 
			new Tile(3007, 3237, 0), new Tile(3007, 3232, 0), new Tile(3007, 3227, 0), 
			new Tile(3007, 3222, 0), new Tile(3007, 3217, 0), new Tile(3010, 3213, 0), 
			new Tile(3007, 3217, 0), new Tile(3003, 3220, 0), new Tile(3003, 3225, 0), 
			new Tile(3002, 3230, 0), new Tile(2999, 3234, 0), new Tile(2996, 3239, 0), 
			new Tile(2993, 3243, 0), new Tile(2988, 3243, 0), new Tile(2982, 3242, 0), 
			new Tile(2978, 3239, 0), new Tile(2974, 3236, 0), new Tile(2970, 3233, 0), 
			new Tile(2968, 3234, 0) };
	
	public final Tile[] pathToWorkshop = new Tile[] {
       //  new Tile(2965, 3399, 0),
         new Tile(2964, 3394, 0), 
			new Tile(2963, 3389, 0), new Tile(2965, 3384, 0), new Tile(2968, 3380, 0), 
			new Tile(2973, 3378, 0), new Tile(2978, 3377, 0), new Tile(2982, 3374, 0), 
			new Tile(2987, 3372, 0), new Tile(2991, 3369, 0), new Tile(2996, 3367, 0), 
			new Tile(3000, 3364, 0), new Tile(3005, 3364, 0), new Tile(3010, 3364, 0), 
			new Tile(3015, 3363, 0), new Tile(3020, 3360, 0), new Tile(3025, 3359, 0), 
			new Tile(3029, 3356, 0), new Tile(3032, 3352, 0), new Tile(3032, 3347, 0), 
			new Tile(3032, 3342, 0), new Tile(3035, 3337, 0), new Tile(3039, 3340, 0), 
			new Tile(3037, 3340, 0) };
	
	public final Tile[] pathToWorkshopbasement = new Tile[] { 
			new Tile(2966, 3405, 0), new Tile(2965, 3400, 0), new Tile(2965, 3395, 0), 
			new Tile(2965, 3390, 0), new Tile(2967, 3385, 0), new Tile(2969, 3380, 0), 
			new Tile(2973, 3377, 0), new Tile(2978, 3376, 0), new Tile(2983, 3375, 0), 
			new Tile(2988, 3374, 0), new Tile(2992, 3371, 0), new Tile(2997, 3369, 0), 
			new Tile(3001, 3366, 0), new Tile(3006, 3366, 0), new Tile(3011, 3366, 0), 
			new Tile(3016, 3364, 0), new Tile(3021, 3362, 0), new Tile(3026, 3360, 0), 
			new Tile(3031, 3357, 0), new Tile(3033, 3352, 0), new Tile(3033, 3347, 0), 
			new Tile(3033, 3342, 0), new Tile(3038, 3340, 0), new Tile(3043, 3339, 0), 
			new Tile(3048, 3338, 0), new Tile(3053, 3336, 0), new Tile(3058, 3336, 0) };
	
	public final Tile[] pathToSquire = new Tile[] { 
			new Tile(2966, 3402, 0), new Tile(2966, 3397, 0), new Tile(2965, 3392, 0), 
			new Tile(2965, 3387, 0), new Tile(2965, 3382, 0), new Tile(2965, 3377, 0), 
			new Tile(2965, 3372, 0), new Tile(2965, 3367, 0), new Tile(2963, 3362, 0), 
			new Tile(2964, 3357, 0), new Tile(2965, 3352, 0), new Tile(2965, 3347, 0), 
			new Tile(2965, 3342, 0), new Tile(2966, 3338, 0) };
	
	final Area CastleDoor = new Area(new Tile[] {new Tile(2962, 3343, 0), new Tile(2962, 3335, 0), new Tile(2967, 3335, 0), 
			new Tile(2967, 3343, 0) });
	
	public final int Doric = 284;
	public Tile initTile;
	public boolean init = false;
	public Method Method = new Method(ctx);
	public Vars Vars = new Vars();
	
	public int bankItems[] = {25334,25335,25336,25328,25329,25330,25331,25327,25324};
	public int bankItemAmount[] = {1,1,1,1,1,1,1,1,1};
	public boolean activate() {
		return DeltaQuester.scriptToStart==27;
	}

	boolean q = true;
	public void execute() {
		Method.setGeneralCamera();//get the camera pitch for general use on quests
		
		DeltaQuester.numSteps = 17;
		Method.foodSupport();
		while(ctx.varpbits.varpbit(1114)==1 && (ctx.varpbits.varpbit(2231)&0x3F) !=35){//When the cinimatic plays
			if(!Method.isChatting("Listening")){
			}
		}
		while(!Method.teleporting && (Method.inventoryContains(438) || (Method.inventoryContains(436)))){
			int ores[] = {438,436};
			for(int i : ores){
				Method.interactInventory(i, "Drop", "Ore");
			}
		}
		Method.skipPics();
		if(DeltaQuester.checkedBank)
			Method.determineBank(bankItems);
			if(!DeltaQuester.checkedBank&& (ctx.varpbits.varpbit(2231)&0x3F) !=55){
			Method.checkBank();
		}else
	    if(Vars.useBank && (ctx.varpbits.varpbit(2231)&0x3F) !=55){
			Method.useBank(bankItems, bankItemAmount);
		}else
		if(!init){
			if(ctx.skills.level(Skills.SMITHING)<5){
				Method.state("Smithing level of 5 is required, skipping quest");
				Method.sleep(4000);
				DeltaQuester.e  = true;
			}else init = true;
		}else
		if((ctx.varpbits.varpbit(2231)&0x3F) ==55){
			DeltaQuester.progress = 17;
			DeltaQuester.state ="The What's Mine Is Yours quest has been completed.";
		
			Method.sleep(2000);
			DeltaQuester.e  = true;
		}else
		if((ctx.varpbits.varpbit(2231)>>17& 0x1) ==1 ||((ctx.varpbits.varpbit(2231)&0x3F) ==50)){
			System.out.println("cs1 last step");
			DeltaQuester.progress = 16;
			cs1();//Speak to Doric for the last time
		
		}else
		if((ctx.varpbits.varpbit(2231)>>14& 0x7) ==7){
			System.out.println("cs11 fifteenth step");
			DeltaQuester.progress = 15;
			cs11();//Mine the rocks in basement
		
		}else
		if(((ctx.varpbits.varpbit(2231)&0x3F) ==45)){
			System.out.println("cs10 fourteenth step");
			DeltaQuester.progress = 14;
			cs10();//Make the ornamental armour
		
		}else
		if(((ctx.varpbits.varpbit(2231)&0x3F) ==40)){
			System.out.println("cs1 thirteenth step");
			DeltaQuester.progress = 13;
			cs1();//Speak to Doric again.
		
		}else
		if(((ctx.varpbits.varpbit(2231)&0x1F) ==30)||(ctx.varpbits.varpbit(2231)&0x3F) ==35){
			System.out.println("cs1 twelveth step");
			DeltaQuester.progress = 12;
			cs1();//Resolve the family conflict
		
		}else
		if(((ctx.varpbits.varpbit(2231)&0x1F) ==20)||(ctx.varpbits.varpbit(2231)&0x1F) ==25){
			System.out.println("cs9 eleventh step");
			DeltaQuester.progress = 11;
			cs9();//Speak to the squire
		
		}else
		if(((ctx.varpbits.varpbit(2231)&0x1F) ==19)){
			System.out.println("cs7 tenth step");
			DeltaQuester.progress = 10;
			cs7();//Give aksel the report we just recieved.\
		
		}else
		if(((ctx.varpbits.varpbit(2231)&0x1F) ==18)){
			System.out.println("cs8 ninth step");
			DeltaQuester.progress = 9;
			cs8();//Get report from Sten in the workshop
		
		}else
		if(((ctx.varpbits.varpbit(2231)&0xF) ==15)){
			System.out.println("cs7 eighth step");
			DeltaQuester.progress = 8;
			cs7();//Speak to Aksel in the workshop
		
		}else
		if(((ctx.varpbits.varpbit(2231)&0xF) ==10)){
			System.out.println("cs1 seventh step");
			DeltaQuester.progress = 7;
			cs1();//Make some weapons at Dorics house.
		
		}else
		if((ctx.varpbits.varpbit(2231)>>9 & 0x1F) ==31){
			System.out.println("cs1 sixth step");
			DeltaQuester.progress = 6;
			cs1();//Speak to Doric again
		
		}else
		if((ctx.varpbits.varpbit(2231)>>10 & 0x1) ==1){
			System.out.println("cs5 fifth step");
			DeltaQuester.progress = 5;
			cs5();//Gather more copper ore at the rimmington mine
		
		}else
		if((ctx.varpbits.varpbit(2231)>>11 & 0x1) ==1){
			System.out.println("cs4 fourth step");
			DeltaQuester.progress = 4;
			cs4();//Gather the high-quality copper ore(Varrok western mine)
		
		}else
		if((ctx.varpbits.varpbit(2231)>>9 &0x1)==1){
			System.out.println("cs3 third step");
			DeltaQuester.progress = 3;
			cs3();//Gather the high-quality tin ore(Varrok eastern mine)
		}else
		if((ctx.varpbits.varpbit(2231)&0x7)==6){
			System.out.println("cs2 second step");
			DeltaQuester.progress = 2;
			cs2();//Gather the high-quality copper ore(Faldor dwarven mine)
		}else
		if((ctx.varpbits.varpbit(2231)&0x7)==0 || (ctx.varpbits.varpbit(2231)&0x7)==1||
				(ctx.varpbits.varpbit(2231)&0x7)==4||(ctx.varpbits.varpbit(2231)&0x7)==5){
			System.out.println("cs1 first step");
			DeltaQuester.progress = 1;
		    cs1();//Start the quest
		}
		
	}


	private void cs11() {
		if(!ctx.objects.select().nearest().id(72884).first().isEmpty()){//Basement check
			if(!ctx.players.local().inMotion())
			Method.interactO(72791, "Mine", "Rocks");
		}else if(new Tile(2959,3442,0).distanceTo(ctx.players.local().tile())<10){
			Method.interactO(78070, "Climb", "Stairs");
		}else cs1();//Get the player into the house
	}


	private void cs10() {
		Component chainbody = ctx.widgets.component(1371,44).component(6);
		Component platebody = ctx.widgets.component(1371,44).component(2);
		Component shopsign = ctx.widgets.component(1371,44).component(10);
		Component startSmithBtn = ctx.widgets.component(1370,12);
		final String[] opt  ={"Yes"};
		while(ctx.players.local().animation()==3243 ||ctx.players.local().animation()==898){
			Method.state("Smelting");
			Method.sleep(30);
		}
		if(Method.inventoryContains(25334)){//Ornamental platebody
			if(Method.inventoryContains(25335)){//Ornamental chainbody
				if(Method.inventoryContains(25336)){//Shop sign
					if(!Method.findOption(opt)){
						if(!Method.isChatting("Doric")){
							Method.speakTo(Doric, "Doric");
						}
					}
				}else if(Method.inventoryContains(25329)){//High-quality bronze bar
					if(ctx.widgets.component(1370,0).visible()){
						 if(ctx.widgets.component(1370,56).text().contains("Shop")){
							 Method.state("Starting smith");
							 startSmithBtn.click(true);
						 }else shopsign.click(true);
				} else Method.interactO(78040, "Smith", "Anvil");
			}else getMoreBars();
			}else if(!ctx.backpack.select().id(25329).first().isEmpty()){//High-quality bronze bar
				if(ctx.widgets.component(1370,0).visible()){
					 if(ctx.widgets.component(1370,56).text().contains("chainbody")){
						 Method.state("Starting smith");
						 startSmithBtn.click(true);
					 }else chainbody.click(true);
			} else Method.interactO(78040, "Smith", "Anvil");
		}else getMoreBars();
		}else
		if(!ctx.backpack.select().id(25329).first().isEmpty()){//High-quality bronze bar
			if(ctx.widgets.component(1370,0).visible()){
					 if(ctx.widgets.component(1370,56).text().contains("platebody")){
						 Method.state("Starting smith");
						 startSmithBtn.click(true);
					 }else platebody.click(true);
			} else Method.interactO(78040, "Smith", "Anvil");
		}else getMoreBars();

	}


	private void getMoreBars() {
		final String[] opt  = {"Yes"};
		
		if(!Method.findOption(opt)){
			if(!Method.isChatting("Boric")){
				Method.speakTo(4231, "Boric");
			}
		}
		
	}


	private void cs9() {
		final String opt[] = {"ghrer"};
		Player local = ctx.players.local();
		//SceneObject door = SceneEntities.getNearest(11721);
		while(ctx.varpbits.varpbit(1113)==1){//Cutscene
			if(!Method.isChatting("Cutscene")){
				Method.sleep(30);
			}
		}
		if( new Tile(2966, 3338, 0).distanceTo(local.tile())<10){
		 if(Method.objIsNotNull(11721) && !CastleDoor.contains(Method.getObject(11721).tile())|| !Method.objIsNotNull(11721)){
			 if(Method.npcIsNotNull(6512)){
				 if(Method.getNPC(6512).tile().distanceTo(local.tile())<8){
					 if(!Method.findOption(opt)){
						 if(!Method.isChatting("Squire Cerlyn")){
							 Method.speakTo(6512, "Squire Cerlyn");
						 }
					 }
				 }else Method.clickOnMap(Method.getNPC(6512).tile());
			 }
		 }else Method.interactO(11721, "Open", "Door");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToSquire, "Walking to the squire", false);
		}else if(TeleportLode.FALADOR.getTile().distanceTo(ctx.players.local().tile())<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.FALADOR.getTeleport(),TeleportType.FALADOR.getName());
		
	}


	private void cs8() {
		final String opt[] = {"I better get","Aksel wants"};
		Player local = ctx.players.local();
		if(Method.objIsNotNull(31130)){//basement area
			if(Method.npcIsNotNull(69)){
				if(Method.getNPC(69).tile().distanceTo(local.tile())<7){
					if(!Method.findOption(opt)){
						Vars.DYNAMICV2 = true;
						if(!Method.isChatting("Sten")){
							Method.speakTo(69, "Sten");
						}
					}
				}else Method.clickOnMap(Method.getNPC(69).tile());
			}
		}else
		if(new Tile(3058, 3336, 0).distanceTo(local.tile())<5){
			Method.interactO(29386, "Climb", "Stairs");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToWorkshopbasement, "Walking to the basement door", false);
		}else if(TeleportLode.FALADOR.getTile().distanceTo(local.tile())<10 ||
				new Tile(3037, 3340, 0).distanceTo(local.tile())<7){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.FALADOR.getTeleport(),TeleportType.FALADOR.getName());
		
	}


	private void cs7() {
		final String opt[] = {"Yes, I have the repo","Doric sent me here"};
		Player local = ctx.players.local();
		//SceneObject exit = SceneEntities.getNearest(4618);
		if(Method.objIsNotNull(31130)){
			if(Method.objIsNotNull(4618)){
				if(Method.getObject(4618).tile().distanceTo(local.tile())<4){
			ctx.mouse.move(Method.getObject(4618).tile().matrix(ctx).point(Random.nextDouble() * .3 - 0.6D, .6D, 300));
			ctx.mouse.click(true);
				}else Method.clickOnMap(Method.getObject(4618).tile());
			}
		}else
		if(new Tile(3037, 3340, 0).distanceTo(local.tile())<7){
			if (!Method.findOption(opt)) {
				Vars.DYNAMICV2 = false;
				Vars.DYNAMICV = false;
				if (!Method.isChatting("Aksel")) {
					Method.speakTo(6663, "Aksel");
				}
			}
		}else if(Vars.DYNAMICV2){
			Method.walking(pathToWorkshopbasement, "Walking back to Askel", true);
		}else if(Vars.DYNAMICV){
			Method.walking(pathToWorkshop, "Walking to Artisans Workshop", false);
		}else if(TeleportLode.FALADOR.getTile().distanceTo(local.tile())<9){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.FALADOR.getTeleport(),TeleportType.FALADOR.getName());
		
	}


	private void cs6() {//make the weapons
	   System.out.println("Doing cs6 - making weapons");
		Component dagger = ctx.widgets.component(1371,2);
		Component sword = ctx.widgets.component(1371,6);
		Player local = ctx.players.local();
		Component startSmithBtn = ctx.widgets.component(1370,12);
		while(local.animation()==3243 ||local.animation()==898){
			Method.state("Smelting");
			Method.sleep(30);
		}
		
		if(Method.inventoryContains(25329)){//High-quality bronze bar
			if(ctx.widgets.component(1370,0).visible()){//the weapon smith screen after clicking an anvil
				
			 if(!Method.inventoryContains(25330)){//If you don't have a dagger yet
				 //make the dagger
				 if(ctx.widgets.component(1370,56).text().contains("dagger")){
					 Method.state("Starting smith");
					 startSmithBtn.click(true);
				 }else dagger.click(true);
				 
			 }else if(!Method.inventoryContains(25331)){//If you don't have a sword yet
				 //make the sword
				 if(ctx.widgets.component(1370,56).text().contains("sword")){
					 Method.state("Starting smith");
					 startSmithBtn.click(true);
				 }else sword.click(true);
				 
			 }
			}else {
				System.out.println("Trying to click on anvil");
				Method.interactO(78040, "Smith", "Anvil");
			}
		}else
		if(Method.inventoryContains(25328) || Method.inventoryContains(25327)){//High-quality tin and copper ore
			if(ctx.widgets.component(1370,12).visible()){
				ctx.widgets.component(1370,12).click(true);
				Method.sleep(3000);
			}else if(ctx.players.local().animation()==-1)
				Method.interactO(78111, "Smelt", "Furance");
		}else
		if(Method.inventoryContains(25324)){
			System.out.println("Trying to take ore out of bag");
		Method.interactInventory(25324, "Withdraw", "Ore bag");
		}
		
	}


	private void cs5() {
		Player local = ctx.players.local();
	//	NPC tin = NPCs.getNearest(6514);
		//SceneObject copperRock =SceneEntities.getNearest(72099);
		if(new Tile(2968, 3234, 0).distanceTo(local.tile())<8){

			Vars.DYNAMICV = false;
			if(Method.gItemIsNotNull(25327)){
				if(Method.getGroundItem(25327).inViewport()){
					//Method.getGroundItem(25327).interact("Take");
					Method.interactG(25327, "Take", "Item");
				}else Method.clickOnMap(Method.getGroundItem(25327).tile());
			}else 
			if(!Method.isChatting("Yourself")){
				if(Method.npcIsNotNull(6514)){
					if(Method.isInCombat()){
						Method.basicFightNPC(6514);
					}else Method.npcInteract(6514, "Attack");
				}else Method.interactO(72099,"Mine", "Copper Rock");
			}
			
		}else if(Vars.DYNAMICV){
			Method.walking(pathToMine4, "Walking to the fourth mine", false);
		}else if(TeleportLode.FALADOR.getTile().distanceTo(local.tile())<6 ||TeleportLode.PORTSARIM.getTile().distanceTo(local.tile())<6){
			Vars.DYNAMICV = true;
		}else if(Method.isPortSarimLodeAct()){
			Method.teleportTo(TeleportType.PORTSARIM.getTeleport(),TeleportType.PORTSARIM.getName());
		}else Method.teleportTo(TeleportType.FALADOR.getTeleport(),TeleportType.FALADOR.getName());
	}


	private void cs4() {
		Player local = ctx.players.local();
		//NPC tin = NPCs.getNearest(6514);
		//SceneObject tinRock =SceneEntities.getNearest(11957);
		if(new Tile(3175, 3368, 0).distanceTo(local.tile())<6){
			Vars.DYNAMICV = false;
			if(Method.gItemIsNotNull(25328)){
				if(Method.getGroundItem(25328).inViewport()){
				//GroundItems.getNearest(25328).interact("Take");
				Method.interactG(25328, "Take", "Item");
				}else Method.clickOnMap(Method.getGroundItem(25328).tile());
			}else 
			if(!Method.isChatting("Yourself")){
				if(Method.npcIsNotNull(6514)){
					if(Method.isInCombat()){
						Method.basicFightNPC(6514);
					}else Method.npcInteract(6514, "Attack");
				}else Method.interactO(11957,"Mine", "Tin Rock");
			}
		}else if(Vars.DYNAMICV){
			Method.walking(pathToMine3, "Walking to third mine", false);
		}else if(TeleportLode.VARROCK.getTile().distanceTo(local.tile())<6){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.VARROCK.getTeleport(),TeleportType.VARROCK.getName());
		
	}


	private void cs3() {
		Player local = ctx.players.local();
		//NPC tin = NPCs.getNearest(6514);
		//SceneObject tinRock =SceneEntities.getNearest(11959);
		
		if (new Tile(3289, 3370, 0).distanceTo(local.tile()) < 12) {
			Vars.DYNAMICV = false;
			if(Method.gItemIsNotNull(25327)){
				Method.interactG(25327, "Take", "Item");
				Method.interactG(25327, "Take", "Item");
				//GroundItems.getNearest(25327).interact("Take");
			}else
			if(!Method.isChatting("Yourself")){
				if(Method.npcIsNotNull(6514)){
					if(Method.isInCombat()){
						Method.basicFightNPC(6514);
					}else Method.npcInteract(6514, "Attack");
				}else Method.interactO(11959,"Mine", "Tin Rock");
			}
		}else if(Vars.DYNAMICV){
			Method.walking(pathToMine2, "Walking to the second mine", false);
		}else if(TeleportLode.VARROCK.getTile().distanceTo(local.tile())<6){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.VARROCK.getTeleport(),TeleportType.VARROCK.getName());

	}


	private void cs2() {
		//NPC rock = NPCs.getNearest(6514);
		Player local = ctx.players.local();
		if(initTile!=null){
			if(new Tile(initTile.x()+12,initTile.y()-23,0).distanceTo(local.tile())<6){Vars.DYNAMICV = false;
				if(!Method.teleporting&& !Method.isInCombat()&&Method.inventoryContains(25328)){
					
				}else if(Method.gItemIsNotNull(25328)){//High-quality copper ore
					//GroundItems.getNearest(25328).interact("Take");
					Method.interactG(25328, "Take", "Item");
				}else
				if(Method.npcIsNotNull(6514)){
					if(Method.isInCombat()){
						Method.basicFightNPC(6514);
					}else Method.npcInteract(6514, "Attack");
				}else
				if(!local.inMotion()){
					Method.interactO(5779, "Mine", "Copper Rock");
				}
				//Get the player to the cave, ands then to the rock
			}else Method.clickOnMap(new Tile(initTile.x()+12,initTile.y()-23,0));
		}else if(Method.objIsNotNull(33495)){
			Method.sleep(1500);
			initTile = local.tile();
		}else
		if(new Tile(3018, 3448, 0).distanceTo(local.tile())<7){Vars.DYNAMICV = true;
			if(!Method.isChatting("Dwarf")){
			Method.interactO(30942, "Climb", "Ladder");
			}
		}else if(Vars.DYNAMICV){
			Method.walking(pathToMine1, "Walking to mine", false);
		}else if(TeleportLode.FALADOR.getTile().distanceTo(local.tile())<6){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.FALADOR.getTeleport(),TeleportType.FALADOR.getName());
		
	}


	private void cs1() {//Speak to Doric and start quest
		final String[] opt = {"Yes","I'd love to","What did you need me to","Don't worry,"};
		final String[] opt2 = {"what his favourite toy was","tell Doric where you've been staying","Boric about your past","Boric why you sent him to","thought when Doric sent you away",
				"thought when Boric was born","why you wanted to stay","what you want Boric's future",
				"what you thought of your","about your graduation"};
		Player local = ctx.players.local();
		while(ctx.widgets.component(1186,0).visible() ||ctx.widgets.component(1189,0).visible()){
			Method.pressContinue();
		}
		while(ctx.varpbits.varpbit(1113)!=0){// A cutscene is going/on
			//for some odd reason putting 'Method.pressContinue' caused problems during testing
			if(ctx.widgets.component(1186,0).visible()){//it's the dialogue that appears during family talking session
				ctx.widgets.component(1186,7).click();//the continue button
			}else
			if (!Method.findOption(opt2)) {
				if (!Method.isChatting("Cutscene")) {
					Method.sleep(20);
				}
			}
		}
			
		if (new Tile(2958, 3439, 0).distanceTo(local.tile()) < 7) {//Doric's House
			
			if((ctx.varpbits.varpbit(2231)&0xF) ==10){
				
				Vars.DYNAMICV = false;
				if(Method.inventoryContains(25330) && Method.inventoryContains(25331)){
						if(!Method.isChatting("Doric")){
							Method.speakTo(Doric, "Doric");
						}
					
				}else cs6();
				
			}else//this is the part where we start the quest initially
			if(!Method.startQuestOpen()){
				Vars.DYNAMICV = false;
				if(!Method.findOption(opt)){
					Method.skipPics();
					if(ctx.widgets.component(1186,0).visible()){//might be the dialogue that appears that states it s a voiced quest
						ctx.widgets.component(1186,7).click();//the continue button
					}else
					if(!Method.isChatting("Doric")){
						Method.speakTo(Doric, "Doric");
					}
				}
			}
		}else if(Method.objIsNotNull(72884)){//Checks if you're in the basement
			Method.interactO(78088, "Climb", "Stairs");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToDoric, "Walking to Doric", false);
		}else if(TeleportLode.FALADOR.getTile().distanceTo(local.tile())<5){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.FALADOR.getTeleport(),TeleportType.FALADOR.getName());
		
	}
	
}
