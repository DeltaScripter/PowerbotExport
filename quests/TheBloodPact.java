package quests;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Player;
import org.powerbot.script.wrappers.Tile;

import divination.DivineBody;

import quests.Vars.TeleportLode;
import quests.Vars.TeleportType;

public class TheBloodPact extends Node{

	 public TheBloodPact(MethodContext ctx) {
			super(ctx);
		}
	
	public final Tile[] pathToStart = new Tile[] { 
			new Tile(3210,3233,0), new Tile(3220,3227,0),new Tile(3234,3221,0),
			new Tile(3235,3207, 0),	new Tile(3244,3198, 0)};
	
	public final Tile[] pathToXenia = new Tile[] {
			new Tile(3210,3233,0), new Tile(3220,3227,0),new Tile(3234,3221,0),
			new Tile(3232, 3222, 0), new Tile(3233, 3217, 0), new Tile(3234, 3212, 0), 
			new Tile(3235, 3207, 0), new Tile(3237, 3202, 0), new Tile(3241, 3198, 0), 
			new Tile(3243, 3197, 0) };
	
	public Tile initTile;
	public Tile initTile2;
	public boolean slingEquip = false;
	public int armour[] = {15597,19830};
	
	public int bankItems[] = {19830,15597};
	public int bankItemAmount[] = {1,1};
	
	public Method Method = new Method(ctx);
	public void execute() {
		DeltaQuester.numSteps = 8;
		Method.foodSupport();
	
		
		//if(DeltaQuester.checkedBank)
		//	Method.determineBank(bankItems);
			if(DeltaQuester.checkedBank&& (ctx.settings.get(2334)&0x3F)!=60){
			Method.checkBank();
		}else
	    if(Vars.useBank && (ctx.settings.get(2334)&0x3F)!=60){
			Method.useBank(bankItems, bankItemAmount);
		}else
		if((ctx.settings.get(2334)&0x3F)==60){
		DeltaQuester.progress = 8;
		Method.state("The Blood Pact quest has been completed");
		Method.sleep(2000);
		DeltaQuester.e = true;
		}else
		if((ctx.settings.get(2334)&0x3F)==55){
			DeltaQuester.progress =7;
		cs0();//Speak to Xenia at the end of quest
		}else
		if((ctx.settings.get(2334)&0x3F)==40||(ctx.settings.get(2334)&0x3F)==41||(ctx.settings.get(2334)&0x3F)==45||(ctx.settings.get(2334)&0x3F)==50){
			DeltaQuester.progress =6;
		cs4();//Fight Reese
		}else
		if((ctx.settings.get(2334)&0x3F)==35||(ctx.settings.get(2334)&0x3F)==36){
			DeltaQuester.progress =5;
		cs3();//Speak to the defeated Caitlin and finish off.
		}else
		if((ctx.settings.get(2334)&0x1F)==30){
			DeltaQuester.progress =4;
		cs2();//Walk to Caitlin and attack her.
		}else
		if((ctx.settings.get(2334)&0xF)==12|| (ctx.settings.get(2334)&0xF)==15|| (ctx.settings.get(2334)&0x1F)==25){
			DeltaQuester.progress =3;
		cs1();//Enter the catacombs and kill Kayle
		}else
		if((ctx.settings.get(2334)&0xF)==10){
			DeltaQuester.progress =2;
		cs1();//Enter the catacombs to trigger the cutscene
		}else
		if((ctx.settings.get(2334)&0xF)==0){
			DeltaQuester.progress =1;
		cs0();//Speak to Xenia to start the quest
		}
		
	}
	private void cs4() {
		final String opt[] = {"die","I'm your worst","Go downstairs"};
		if(initTile2!=null){
			 if(new Tile(initTile2.getX()+9, initTile2.getY()-6,initTile2.getPlane()).distanceTo(ctx.players.local().getLocation())<6){
				 if((ctx.settings.get(2334)&0x3F)==50){
					 if(!Method.findOption(opt))
						 if(!Method.isChatting("Ilona")){
							 Method.npcInteract(9630, "Untie");
						 }
				 }else
				 if((ctx.settings.get(2334)&0x3F)==45){//After defeating Reese finish him off
					 if(!Method.findOption(opt))
					 if(!Method.isChatting("Reese")){
						 Method.speakTo(9625, "Reese");
					 }
				 }else
				 if((ctx.settings.get(2334)&0x3F)==41){//Defeat Reese
					 if(ctx.players.local().isInCombat()){
						 Method.basicFightNPC(9624);
					 }else Method.npcInteract(9624, "Attack");
				 }else
				 if(!Method.findOption(opt))
				 if(!Method.isChatting("Reese"))
				 Method.interactO(48793, "Open", "Tomb door");
			}else {
				Method.state("Walking to Reese");
				ctx.movement.newTilePath(new Tile(initTile2.getX()+9, initTile2.getY()-6,initTile2.getPlane())).traverse();
			}
		}else 
		if(!ctx.objects.select().nearest().id(48677).first().isEmpty()){
			initTile2 = ctx.players.local().getLocation();
		}else if(initTile!=null){
			 if(new Tile(initTile.getX()-20, initTile.getY()+9,initTile.getPlane()).distanceTo(ctx.players.local().getLocation())<4){
				  if(!Method.findOption(opt))
				  if(!Method.isChatting("Xenia"))
				 Method.interactO(48678, "Climb", "Stairs");
				  
			}else {
				Method.state("Walking to stairs" + initTile);
				Method.clickOnMap(new Tile(initTile.getX()-20, initTile.getY()+9,initTile.getPlane()));
			}
		}else if(!ctx.objects.select().nearest().id(48742).first().isEmpty()){//if it does exist
			Method.sleep(1300);
			for (GameObject marker : ctx.objects.select().nearest().id(48742).first()){//and is on screen
				if(marker.isOnScreen())
				initTile = ctx.players.local().getLocation();//set initial location of player
			}
		}else
		if(new Tile(3246,3198,0).distanceTo(ctx.players.local().getLocation())<9){
			Method.interactO(48797, "Climb-down", "Catacomb");
		}else cs0();//get the player to the catacombs
		
	}
	private void cs3() {
		final String opt[] = {"Time for you to die!"};
		if(initTile!=null){
			
			 if((ctx.settings.get(1261)>>18&0x3) == 2){
				  if(new Tile(initTile.getX()-15, initTile.getY()+12,initTile.getPlane()).distanceTo(ctx.players.local().getLocation())<8){
					  if(!Method.findOption(opt))
					  if(!Method.isChatting("Caitlin")){
						  Method.speakTo(9627, "Caitlin");
					  }
				}else {
					Method.state("Walking to Caitlin");
					Method.clickOnMap(new Tile(initTile.getX()-15, initTile.getY()+12,initTile.getPlane()));
				}
			}else
			  if(new Tile(initTile.getX()-9, initTile.getY()+12,initTile.getPlane()).distanceTo(ctx.players.local().getLocation())<8){
				  Method.interactO(48791, "Operate", "Door Mechanism");
			}else {
				Method.state("Walking to Caitlin");
				ctx.movement.newTilePath(new Tile(initTile.getX()-9, initTile.getY()+12,initTile.getPlane())).traverse();
			}
			
		}else if(!ctx.objects.select().nearest().id(48742).first().isEmpty()){//if it does exist
			Method.sleep(1300);
			for (GameObject marker : ctx.objects.select().nearest().id(48742).first()){//and is on screen
				if(marker.isOnScreen())
				initTile = ctx.players.local().getLocation();//set initial location of player
			}
		}else
		if(new Tile(3246,3198,0).distanceTo(ctx.players.local().getLocation())<9){
			Method.interactO(48797, "Climb-down", "Catacomb");
		}else cs0();//get the player to the catacombs
		
	}
	
	private void cs2() {

		if(initTile!=null){
			
			  if(new Tile(initTile.getX()-9, initTile.getY()+12,initTile.getPlane()).distanceTo(ctx.players.local().getLocation())<8){
			   if(Method.isInCombat()){
				   Method.basicFightNPC(9626);
			   }else Method.npcInteract(9626,"Attack");
			}else {
				Method.state("Walking to Caitlin");
				ctx.movement.newTilePath(new Tile(initTile.getX()-9, initTile.getY()+12,initTile.getPlane())).traverse();
			}
			
		}else if(!ctx.objects.select().nearest().id(48742).first().isEmpty()){//if it does exist
			Method.sleep(1300);
			for (GameObject marker : ctx.objects.select().nearest().id(48742).first()){//and is on screen
				if(marker.isOnScreen())
				initTile = ctx.players.local().getLocation();//set initial location of player
			}
		}else
			Method.interactO(48797, "Climb-down", "Catacomb");
	
		//}else cs0();//get the player to the catacombs

	}

	private void cs1() {
		final String opt[]  ={"Yes"};
		Player local = ctx.players.local();
		
		while((ctx.settings.get(1114)&0x1)==1){
			Method.isChatting("Cutscene");
		}
		if(initTile!=null){
			final Tile loc1 = new Tile(initTile.x, initTile.y+15,initTile.getPlane());
			if(loc1.getLocation().distanceTo(ctx.players.local().getLocation())<8){
				if((ctx.settings.get(2334)&0x1F)==25){
					if(!Method.findOption(opt))
					if(!Method.isChatting("Kayle")){
						Method.speakTo(9629, "Kayle");
					}
				}else 
				if(Method.isInCombat()){
					Method.basicFightNPC(9628);
				}else Method.npcInteract(9628, "Attack");
			}else {
				Method.state("Distance: " + (int)loc1.distanceTo(local.getLocation()));
				Method.state("Walking to Kayle");
				ctx.movement.newTilePath(loc1).traverse();
			}
		}else if(Method.objIsNotNull(48742)){//if it does exist
			Method.sleep(1300);
			for (GameObject marker : ctx.objects.select().nearest().id(48742).first()){//and is on screen
				if(marker.isOnScreen())
				initTile = local.getLocation();//set initial location of player
				break;
			}
		}else
		if(new Tile(3246,3198,0).distanceTo(ctx.players.local().getLocation())<9){
			for(GameObject y: ctx.objects.select().name("Catacomb entrance").nearest().first()){//Enters the catacomb
						if (y.isOnScreen()) {
							ctx.mouse.move(y.getLocation().getMatrix(ctx).getPoint(Random.nextDouble() * 0.2D - 0.1D,+0.10D,+100));
							ctx.mouse.click(true);
							ctx.game.sleep(Random.nextInt(700, 1200));
						} else ctx.camera.turnTo(y);
					
					}
			Method.sleep(4000);
		}else cs0();//get the player to the catacombs
		
	}

	private void cs0() {
		final String opt[] = {"I think I'll","It was nothing","I'll help you","do you need"};
		if(	new Tile(3243, 3197, 0).distanceTo(ctx.players.local().getLocation())<8){
			Method.skipPics();
			if(!Method.startQuestOpen())
				if(!Method.findOption(opt))
				if(!Method.isChatting("Xenia")){
					Method.speakTo(9633, "Xenia");
				}
		}else if(DeltaQuester.getVars().DYNAMICV){
			Method.walking(pathToXenia, "Walking to Xenia", false);
		}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(ctx.players.local().getLocation())<10){
			DeltaQuester.getVars().DYNAMICV = true;
		}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
		
	}

	public boolean activate() {
		return (DeltaQuester.scriptToStart == 13);
	}

}
