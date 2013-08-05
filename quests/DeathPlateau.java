package quests;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Player;
import org.powerbot.script.wrappers.Tile;

import quests.Vars.TeleportLode;
import quests.Vars.TeleportType;

public class DeathPlateau extends Node{

	public DeathPlateau(MethodContext ctx) {
		super(ctx);
	}
	public final static Tile[] pathToDwarf = new Tile[] {
		new Tile(2911,3531, 0), new Tile(2901,3540, 0),
		new Tile(2894,3545, 0), new Tile(2884,3549, 0),
		new Tile(2874,3554, 0), new Tile(2863,3565, 0),
		new Tile(2861,3570,0),new Tile(2858,3577, 0),
		new Tile(2847,3578,0), new Tile(2837,3585,0),
		new Tile(2829,3578,0), new Tile(2832,3563,0), 
		new Tile(2827,3555,0)};
	
	public final static Tile[] pathToSmith = new Tile[] {
		new Tile(2907,3547, 0), new Tile(2918,3548, 0),
		new Tile(2923,3558, 0), new Tile(2922,3569, 0),
		new Tile(2919,3574, 0)};
	
	public final Area dwarf1Area= new Area(new Tile[] {//Area outside the first dwarf's cave.
			new Tile(2853,3570,0), new Tile(2862,3570,0),
			new Tile(2864,3576,0), new Tile(2852,3578,0)});
	
	public final Area DwarfDoor1 = new Area(new Tile[] { 
			new Tile(2823, 3558, 0), new Tile(2823, 3551, 0), new Tile(2827, 3550, 0), 
			new Tile(2828, 3559, 0) });
	
	public final Area DwarfDoor2 = new Area(new Tile[] {
			new Tile(2820, 3557, 0), new Tile(2820, 3553, 0), new Tile(2824, 3552, 0), 
			new Tile(2823, 3557, 0) });
	
	public int bankItems[] = {23084};
	public int bankItemAmount[] = {1};
	public Vars Vars = new Vars();
	public Method Method = new Method(ctx);

	
	public void execute() {
		DeltaQuester.numSteps = 12;//2337 is setting
		Method.resetTeleporting();
		while(ctx.settings.get(1113)==5){
			Method.state("Cinematic");
			Method.pressContinue();
		}
		Method.foodSupport();
		
		
		if(DeltaQuester.checkedBank && (ctx.settings.get(2337) & 0x1FF) !=449)
			Method.determineBank(bankItems);
		
		if(DeltaQuester.checkedBank && (ctx.settings.get(2337) & 0x1FF) !=449){
			Method.checkBank();
		}else
	    if(Vars.useBank && (ctx.settings.get(2337) & 0x1FF) !=449){
			Method.useBank(bankItems, bankItemAmount);
		}else
		if((ctx.settings.get(2337) & 0x1FF) ==449){
			DeltaQuester.progress=12;
			Method.state("The Death Plateau quest has been completed");
			ctx.environment.sleep(2000);
			DeltaQuester.e = true;
		}else 
		if((ctx.settings.get(2337) & 0x1FF) ==444){
			DeltaQuester.progress=11;
			cs0();//Finish the quest.
		}else
		if((ctx.settings.get(2337) & 0x1FF) ==439){
			DeltaQuester.progress=10;
			cs6();//Attack and destroy the hostile troll
		}else
		if((ctx.settings.get(2337) & 0x1FF) ==434){
			DeltaQuester.progress=9;
			cs6();//Speak with the troll for a moment
		}else
		if((ctx.settings.get(2337) & 0x1FF) ==429){
			DeltaQuester.progress=8;
			cs5();//Find safe passage through the cavern
		}else
		if((ctx.settings.get(2337) & 0xFF) ==168){
			DeltaQuester.progress=7;
			cs1();//Must mine the wall near dwarf # 1
		}else
		if((ctx.settings.get(2337) & 0x3F) ==35){
			DeltaQuester.progress=6;
			cs4();//Simply read the survey.
		}else
		if((ctx.settings.get(2337) & 0x1F) ==30){
			DeltaQuester.progress=5;
			cs2();//We must give the boots back to the other dwarf.
		}else
		if((ctx.settings.get(2337) & 0xF) ==15){
			DeltaQuester.progress=4;
			cs3();//Must speak to the smith.
		}else
		if((ctx.settings.get(2337) & 0xF) ==10){
			DeltaQuester.progress=3;
			cs2();//Must speak with dwarf wife.
		}else
		if((ctx.settings.get(2337) & 0x7) ==5){
			DeltaQuester.progress=2;
			cs1();//WE must speak to the dwarf; first time.
		}else
		if((ctx.settings.get(2337) & 0x1) ==0){
			DeltaQuester.progress=1;
			cs0();//Start the quest.
		}

	}
	
	private void cs6() {
		Player local = ctx.players.local();
		final String opt[] = {"Prepare to die"};
		
		if(Method.npcIsNotNull(15101)){
		if(local.isInCombat()){
			Vars.DYNAMICV = false;
			Method.basicFightNPC(15101);//hostile troll
		}else Method.npcInteract(15101, "Attack");
		}else if (Method.npcIsNotNull(15100)){
			Vars.DYNAMICV = false;
			if(!Method.findOption(opt)) 
			if(!Method.isChatting("Troll")){
				Method.speakTo(15100, "Troll");
			}
		}else cs5();//Get the player back to the troll area
	}

	private void cs5(){
		Player local = ctx.players.local();
		
		if(!failsafe())
		if(new Tile(3432,4241,2).getMatrix(ctx).isReachable()){//last obs area
			if(new Tile(3435,4240,2).distanceTo(local.getLocation())<7){//last obs
				Method.interactO(67569, "Exit", "Cliffside hole");
			}else Method.clickOnMap(new Tile(3435,4240,2));//last obs
		}else
		if(new Tile(3420,4239,1).getMatrix(ctx).isReachable()){//fith obs area
			if(new Tile(3420,4239,1).distanceTo(local.getLocation())<7){//fith obs
				Method.interactO(17030, "Climb", "Cliffside");
			}else Method.clickOnMap(new Tile(3420,4239,1));//fith obs
		}else		
		if(new Tile(3414,4260,1).getMatrix(ctx).isReachable()){//fith obs area
			if(new Tile(3417,4254,1).distanceTo(local.getLocation())<5){//fith obs
				if(local.getAnimation()==-1)
				Method.interactO(67676, "Squeeze", "Gap");
			}else Method.clickOnMap(new Tile(3417,4254,1));//fith obs
		}else	
		if(new Tile(3428,4261,1).getMatrix(ctx).isReachable()){//fourth obs area
			if(new Tile(3423,4260,1).distanceTo(local.getLocation())<7){//fourth obs
				Method.interactO(67679, "Jump", "Stepping stones");
			}else Method.clickOnMap(new Tile(3423,4260,1));//fourth obs
		}else
		if(new Tile(3434,4274,1).getMatrix(ctx).isReachable()){//third obs area
			if(new Tile(3435,4261,1).distanceTo(local.getLocation())<7){//third obs
				Method.interactO(67752, "Swing-on", "Rope swing");
			}else Method.clickOnMap(new Tile(3435,4261,1));//third obs
		}else
		if(new Tile(3428,4279,1).getMatrix(ctx).isReachable()){//second obs area
			if(new Tile(3434,4278,1).distanceTo(local.getLocation())<7){//second obs
				if(local.getAnimation()==-1)
				Method.interactO(67678, "Jump", "Crevice");
			}else Method.clickOnMap(new Tile(3434,4278,1));//second obs
		}else
		if(new Tile(3419,4280,1).distanceTo(local.getLocation())<7){//first obs
			if(local.getAnimation()==-1)
			Method.interactO(67667, "Squeeze", "Gap");
		}else if(new Tile(3406,4278,1).getMatrix(ctx).isReachable()){
			if(!local.isInMotion())
			Method.clickOnMap(new Tile(3419,4280,1));//get to the first obs
		}else
		if(new Tile(3405,4283,2).distanceTo(local.getLocation())<7){//Cave initial entrance
			Method.interactO(67674, "Jump-down", "Cliff");
		}else
		if(new Tile(2267,4759,0).distanceTo(local.getLocation())<15){//Inside dwarf room(cave)
			if(new Tile(2267,4759,0).distanceTo(local.getLocation())<5){//area by cave entrance
			if(!Method.isChatting("Dwarf"))
				Method.interactO(67562, "Enter", "Cave");
			}else Method.clickOnMap(new Tile(2267,4759,0));//Area by door
		}else cs1();//Get the player to the cave
	}

	private boolean failsafe() {
		if(new Tile(3432,4258,0).getMatrix(ctx).isReachable()){
			Method.interactO(7103,"Climb", "Rock ladder");
			return true;
		}else
		if(new Tile(3432,4276,0).getMatrix(ctx).isReachable()){
			Method.interactO(7103,"Climb", "Rock ladder");
			return true;
		}else if(new Tile(3419,4259,0).getMatrix(ctx).isReachable()){
			Method.interactO(7103,"Climb", "Rock ladder");
			return true;
		}
		return false;
	}

	private void cs4() {//Reads a scroll given to us
		Method Method = new Method(ctx);
		Method.skipPics();
		if(!Method.isChatting("Dwarf"))
		if(ctx.widgets.get(1242).isValid()){
			ctx.environment.sleep(2300,2700);
			ctx.movement.stepTowards(ctx.players.local().getLocation());
		}else Method.interactInventory(23084, "Read","Scroll");
	}

	private void cs3() {//Speaks to the smith to have something studded
		Method Method = new Method(ctx);
		final String opt[] = {"Can you put some"};
		
		if(new Tile(2919,3574,0).distanceTo(ctx.players.local().getLocation())<6){
			Vars.DYNAMICV = false;
			if(!Method.findOption(opt))
				if(!Method.isChatting("Smith")){
					Method.speakTo(1082, "Smith");
				}
		}else if(Vars.DYNAMICV){
			Method.walking(pathToSmith, "Walking to the smith.", false);
		}else if(TeleportLode.BURTHORPE.getTile().distanceTo(ctx.players.local().getLocation())<6){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());
	}

	private void cs2() {//Walks to and speaks with the other dwarf to get the plans
		Method Method = new Method(ctx);
		Player local = ctx.players.local();
		if(Method.objIsNotNull(67568)){//Below exits the cave you're no supposed to be in
			if(Method.getObject(67568).getLocation().distanceTo(local.getLocation())<6){
				Vars.DYNAMICV = true;
				Method.interactO(67568, "Exit", "Cave exit");
			}else Method.clickOnMap(Method.getObject(67568).getLocation());//cave area
		}else
		
		if(new Tile(2824,3555,0).distanceTo(ctx.players.local().getLocation())<8){//location by other dwarf
			if(Method.objIsNotNull(3725) && DwarfDoor1.contains(Method.getObject(3725).getLocation())){
				if(DwarfDoor1.contains(Method.getObject(3725).getLocation())){
						Method.interactO(3725, "Open","Door");
				}else Method.interactO(67638, "Open","Door");
			}else
			if(Method.objIsNotNull(67638) && DwarfDoor2.contains(Method.getObject(67638).getLocation())){
				Method.interactO(67638, "Open", "Door");
			}else{
				Vars.DYNAMICV = false;
				if(!Method.isChatting("Dwarf")){
					Method.speakTo(15099,"Dwarf");
				}
			}
			
		}else if(Vars.DYNAMICV){
			Method.walking(pathToDwarf, "Walking to the other dwarf.",false);
		}else if(TeleportLode.BURTHORPE.getTile().distanceTo(ctx.players.local().getLocation())<6){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());//burthorpe.
	}

	private void cs1() {//Enters the dwarf's cave and speaks to him; then mines the wall
		Method Method = new Method(ctx);
		final String opt[]  ={"Fine!","Well I'm not moving","I've been sent"};
		if(Method.npcIsNotNull(15095)){//Dwarf
			if((ctx.settings.get(2337) & 0xFF) ==168){//Once you need to mine the wall
				Method.interactO(67562, "Mine","Rock");
			}else if(!Method.findOption(opt))//Speak to the dwarf before mining
					if(!Method.isChatting("Dwarf")){
						Method.speakTo(15095, "Dwarf");
					}
		}else if(new Tile(2859,3577,0).distanceTo(ctx.players.local().getLocation())<6){
			Method.interactO(34395, "Enter","Cave");//enter the cave.
		}else if(Vars.DYNAMICV){
			if(new Tile(2858,3577,0).distanceTo(ctx.players.local().getLocation())<20){
				ctx.movement.newTilePath(new Tile(2858,3576,0)).traverse();
			}else{
			Method.walking(pathToDwarf, "Walking to the dwarf.",false);//walk
			}
		}else if(TeleportLode.BURTHORPE.getTile().distanceTo(ctx.players.local().getLocation())<6){
			Vars.DYNAMICV= true;
		}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());
		
	}

	private void cs0() {//Speaks to the captain to begin the quest
		 final String opt[] = {"Do you have any"};
		if(new Tile(2910,3534,0).distanceTo(ctx.players.local().getLocation())<6){
			if(!Method.startQuestOpen())
				if(!Method.findOption(opt))
					if(!Method.isChatting("Captain")){
						Method.speakTo(1060, "Captain");
					}
		}else if(Vars.DYNAMICV){
			ctx.movement.newTilePath(new Tile(2910,3534,0)).traverse();//walk to captain.
		}else if(TeleportLode.BURTHORPE.getTile().distanceTo(ctx.players.local().getLocation())<6){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());
		
	}

	public boolean activate() {
		return DeltaQuester.scriptToStart==21;
	}

	

}
