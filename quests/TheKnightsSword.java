package quests;




import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;

import quests.Vars.TeleportLode;
import quests.Vars.TeleportType;

public class TheKnightsSword extends DeltaNode{

	public TheKnightsSword(ClientContext ctx) {
		super(ctx);
	}


	public final Tile[] pathToSquire = new Tile[] { 
			new Tile(2965, 3399, 0), new Tile(2964, 3393, 0), 
			new Tile(2964, 3387, 0), new Tile(2964, 3381, 0), new Tile(2962, 3375, 0), 
			new Tile(2963, 3369, 0), new Tile(2964, 3363, 0), new Tile(2964, 3357, 0), 
			new Tile(2963, 3351, 0), new Tile(2965, 3345, 0), new Tile(2971, 3341, 0), 
			new Tile(2977, 3339, 0), new Tile(2981,3341,0) };
	
	public final Tile[] pathToReldo = { 
			new Tile(3211, 3379, 0), new Tile(3211, 3385, 0), 
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
	
	public int itemsArray[] = {0,0};//contains the states of items needing to be purchased.
	public int itemDID[] = {2325,2352};//contains the ids of the items needing to be purchased.
	public int itemDAmount[] = {1,2};
	public int itemDPrice[] = {3500,2500};//contains specific prices to use upon purchasing specific items.
	public String itemDString[] = {"Redberry pie", "Iron bar"};//contains the names of the items needing to be purchased.
	public boolean inits = false;
	
	public int bankItems[] = {2325,1265,2351,2351,666,667,668};
	public int bankItemAmount[] = {1,1,2,1,1,1,1,1};
	public boolean activate() {
		return DeltaQuester.scriptToStart==25;
	}

	Method Method = new Method(ctx);
	Vars Vars = new Vars();
	boolean q = true;
	
	
	public void execute() {
		
	
		Method.setGeneralCamera();//get the camera pitch for general use on quests
	
		//Settings used, 2547 for main body.
		Method.foodSupport();
		DeltaQuester.numSteps = 8;
		System.out.println("useBank : "+quests.Vars.useBank);
		
		if(!inits){
			if(!Method.FaladorLodeIsActive() || !Method.VarrokLodeIsActive()){
				Method.state("You require Varrok and Falador lodestone, skipping quest");
				//ctx.game.sleep(2000);
				DeltaQuester.e = true;
			}else if(ctx.skills.level(14)<10){//14 is mining index constant
				Method.state("Mining level of 10 is required, skipping quest");
				//ctx.game.sleep(2000);
				DeltaQuester.e = true;
			}else inits = true;
		}
		
		if(DeltaQuester.checkedBank)
			Method.determineBank(bankItems);
		
		if(!DeltaQuester.checkedBank && (ctx.varpbits.varpbit(2547) & 0x7) !=7){
			Method.checkBank();
		}else 
		if (DeltaQuester.GEFeature && (ctx.varpbits.varpbit(2547) & 0x7) !=7) {
			Method.useGE(itemDString, itemDID, itemDPrice, itemDAmount);
		}else if(quests.Vars.useBank){
			System.out.println("Inside useBank call");
			Method.useBank(bankItems,bankItemAmount);
			}else 
			if((ctx.varpbits.varpbit(2547) & 0x7) ==7){
				DeltaQuester.progress = 8;
				Method.state("The Knight's Sword quest has been completed.");
				DeltaQuester.e = true;
			}else
			if((ctx.varpbits.varpbit(2547) & 0x7) ==6){
				DeltaQuester.progress = 7;
				cs4();//mine the blurite and then craft the sword + return it.
				
			}else
			if((ctx.varpbits.varpbit(2547) & 0x7) ==5){
				DeltaQuester.progress = 6;
				cs3();//Find a portrait and then give it to Thurgo.
				
			}else
			if((ctx.varpbits.varpbit(2547) & 0x7) ==4){
				DeltaQuester.progress = 5;
				cs0();//Ask the squire about what Thurgo said.
				
			}else
			if((ctx.varpbits.varpbit(2547) & 0x3) ==3){
				DeltaQuester.progress = 4;
				cs2();//Speak to Thurgo about the sword.
				
			}else
			if((ctx.varpbits.varpbit(2547) & 0x3) ==2){
				DeltaQuester.progress = 3;
				cs2();//Feed Thurgo the redberry pie.
				
			}else
			if((ctx.varpbits.varpbit(2547) & 0x1) ==1){
				DeltaQuester.progress = 2;
				cs1();//Speak to Reldo about the dwarves.
			}else
			if((ctx.varpbits.varpbit(2547) & 0x1) ==0){
				DeltaQuester.progress = 1;
				cs0();//Start the quest.
				
			}
		
	}




	private void cs4() {
		Tile player = ctx.players.local().tile();
		if(hasSword){
			cs0();
		}else if(!Method.teleporting&& Method.inventoryContains(667)){
			Vars.DYNAMICV = false;
			hasSword = true;
		}else if(hasBlurite){
			cs2();
		}else
		if(!Method.teleporting && Method.inventoryContains(668)){
				hasBlurite = true;
		}else if(init!=null){
			
			Method.state("" + (init.x() - player.x()) + " " + ( init.y() - player.y()));
			
			if(new Tile(init.x()-39, init.y()+18,0).distanceTo(player)<7){//ore area
				Vars.DYNAMICV = false;
				Method.interactO(2561, "Mine","Ore");
			}else
			if(new Tile(init.x()+23, init.y()+30,0).distanceTo(player)<7){
				Method.clickOnMap(new Tile(init.x()-39, init.y()+18,0));
			}else
			if(new Tile(init.x()-2, init.y()+29,0).distanceTo(player)<7){
				Method.clickOnMap(new Tile(init.x()+23, init.y()+30,0));
			}else
			if(new Tile(init.x()-14, init.y()+25,0).distanceTo(player)<7){//by more skeeltons
				
				Method.clickOnMap(new Tile(init.x()-0, init.y()+30,0));
			}else
			if(new Tile(init.x()-15, init.y()+11,0).distanceTo(player)<10){//secon place by skeletons
			
				Method.clickOnMap(new Tile(init.x()-14, init.y()+25,0));
			}else
			if(new Tile(init.x()-13, init.y()+1,0).distanceTo(player)<5){//the tile by muggers
				
				Method.clickOnMap(new Tile(init.x()-15, init.y()+11,0));
		}else {
			if(ctx.players.local().idle())
				Method.clickOnMap(new Tile(init.x()-13, init.y()+1,0));//click to first tile place by muggers
		}
		
		}else if(Method.objIsNotNull(31128)){//Inside the cave.
			//ctx.game.sleep(1500);
			init = ctx.players.local().tile();
		}else if(new Tile(3007,3151,0).distanceTo(player)<14){
			if(new Tile(3007,3151,0).distanceTo(player)<6){
				Method.interactO(9472, "Climb-down","Ladder");
			}else Method.findPath(new Tile(3007,3151,0),"Finding a path");
		}else cs2();
		
	}


	private void cs3() {//get into the castle and steal the portrait
		Tile player = ctx.players.local().tile();
		
		
		if(hasPortrait){
			cs2();
		}else if(!Method.teleporting && Method.inventoryContains(666)){
			hasPortrait = true;
		}else
		if(ctx.game.floor()==2 && new Tile(2982,3335,2).matrix(ctx).reachable()){//the area on second flr with the cabinet and portrait
			if(new Tile(2982,3335,2).distanceTo(player)<5){//the cabinet tile
				/*When the king is far away enough we must steal the portrait*/
				
				while(ctx.widgets.component(1186,0).visible()){
					Method.pressContinue();
				}
				if ((Method.npcIsNotNull(605) && Method.objIsNotNull(2271)) || Method.objIsNotNull(2272)) {
					if ((Method.objIsNotNull(2271) &&Method.getNPC(605).tile().distanceTo(Method.getObject(2271).tile()) > 3)|| Method.objIsNotNull(2272)) {
						Vars.DYNAMICV = false;
						Method.state("HERE");
						Method.interactO(2271, "Open","Cabinet");
						Method.interactO(2272, "Search","Cabinet");
					} else
						Method.state("Waiting for the king to move farther away.");
				}
			}else Method.clickOnMap(new Tile(2982,3335,2));//walk ot the cabinet
		}else
		if(ctx.game.floor()==2){
			Method.interactO(11714, "Open","");
		}else
		if(ctx.game.floor()==1 && (new Tile(2989,3339,1).matrix(ctx).reachable())){
			if(new Tile(2982,3338,1).distanceTo(player)<5){
				Method.interactO(11734, "Climb","");
			}else Method.clickOnMap(new Tile(2982,3338,1));
		}else if(ctx.game.floor()==1 && (new Tile(2993,3341,1).matrix(ctx).reachable())){
			Method.interactO(11714, "Open","");
		}else
		if(new Tile(2993,3342,0).matrix(ctx).reachable()){
			if(new Tile(2993,3342,0).distanceTo(player)<4){
			Method.interactO(11727, "Climb","");
			}else Method.clickOnMap(new Tile(2993,3342,0));//tile by the ladder
		}else
		if(new Tile(2987,3341,0).matrix(ctx).reachable()){//Just outside the last door leading to a ladder
		   if(Method.getToNearByTile(new Tile(2987,3341,0)))
			Method.interactO(11714, "Open","");
		}else
		if(new Tile(2984,3341,0).matrix(ctx).reachable()){//tile just inside the firs door leading into castle
		   if(Method.getToNearByTile(new Tile(2985,3341,0)))
			Method.interactO(11714, "Open","");
		}else if(new Tile(2981,3341,0).distanceTo(player)<9){//just outside the castle door
			if(Method.getToNearByTile(new Tile(2981,3341,0)))//tile as above
				Method.interactO(11716, "Open","");
		}else cs0();//get to the squire area
		
	}


	private void cs2() {//Speak to the dwarf named Thurgo
		final String opt[] = {"Something else","Would you like some red"}; 
		Tile player = ctx.players.local().tile();
		
		if(new Tile(2998, 3145, 0).distanceTo(player)<13){
			if(Method.npcIsNotNull(604))//Thurgo
			if(Method.getNPC(604).tile().distanceTo(player)<7){
				while(ctx.widgets.component(1186,0).visible()){
					Method.pressContinue();
				}
				if(!Method.findOption(opt))
					if(!Method.isChatting("Thurgo")){
						Vars.DYNAMICV = false;
						Method.speakTo(604, "Thurgo");
					}
			
			}else Method.clickOnMap(Method.getNPC(604).tile());
		}else if(Vars.DYNAMICV){
			Method.walking(pathToDwarfSmith, "Walking to Thurgo the dwarf", false);
		}else if(TeleportLode.FALADOR.getTile().distanceTo(player)<5 || TeleportLode.PORTSARIM.getTile().distanceTo(player)<5){
			Vars.DYNAMICV = true;
		}else if((ctx.varpbits.varpbit(3) >> 8 & 1)==1){
			Method.teleportTo(TeleportType.PORTSARIM.getTeleport(),"Port Sarim");//Port Sarim lodestone
		}else Method.teleportTo(TeleportType.FALADOR.getTeleport(),"Falador");
		
	}


	private void cs1() {//Speak to someone named Reldo
		final String opt[] = {"I'd like to talk","What do you know"}; 
		Tile player = ctx.players.local().tile();
		
		if(new Tile(3210, 3488, 0).distanceTo(player)<7 || ((Method.npcIsNotNull(647)) && Method.getNPC(647).tile().distanceTo(player)<5)){
			if(new Tile(3210,3492,0).matrix(ctx).reachable()){
				if(!Method.findOption(opt))
					if(!Method.isChatting("Reldo")){
						Vars.DYNAMICV = false;
						Method.speakTo(647, "Reldo");
					}
			}else Method.interactO(15536, "Open","Door");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToReldo, "Walking to Reldo", false);
		}else if(TeleportLode.VARROCK.getTile().distanceTo(player)<5){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.VARROCK.getTeleport(),"Varrock");//Varrok
		
	}


	private void cs0() {//Start the quest by speaking to the squire
		final String opt[] = {"Ok, I'll give it a go.","So would these dwarves","I can make a new","And how is life","Chat",}; 
		Tile player = ctx.players.local().tile(); 
		
		if(new Tile(2977,3339,0).distanceTo(player)<10){
			while(ctx.widgets.component(1186,0).visible()){
				Method.pressContinue();
			}
			if(!Method.findOption(opt))
				if(!Method.isChatting("Squire")){
					Vars.DYNAMICV = false;
					Method.speakTo(606, "Squire");
					System.out.println("Clicking squire");
				}
			
		}else if(Vars.DYNAMICV){
			Method.walking(pathToSquire, "Walking to the squire", false);
		}else if(TeleportLode.FALADOR.getTile().distanceTo(player)<6){
			Vars.DYNAMICV = true;
		}else if((ctx.varpbits.varpbit(3) >> 7 & 1)==1){
			Method.teleportTo(TeleportType.FALADOR.getTeleport(),"Falador");//Falador tele
		}else {
			Method.state("Falador lodestone is not activated.");
			//ctx.game.sleep(1500);
			DeltaQuester.e = true;
		}
		
	}

}
