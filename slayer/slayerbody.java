package slayer;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



import org.powerbot.event.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.Skills;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.Tile;



@org.powerbot.script.Manifest(authors = { "Delta Scripter" }, name = "Delta Slayer", 
description = "Completes slayer assignments, currently in development - please consult thread", version = 1, website = "", hidden = true)
public class slayerbody extends PollingScript implements PaintListener{

	



	public slayerbody(){
			getExecQueue(State.STOP).add(new Runnable() {
				@Override
				public void run() {
					getController().stop();
				}
				});
			getExecQueue(State.START).add(new Runnable() {
				@Override
				public void run() {
					gainedExp = ctx.skills.getExperience(Skills.SLAYER);
					addNode(new determineTask(ctx));
					addNode(new getRequiredItems(ctx));
					addNode(new Turael(ctx));
					addNode(new Vannaka(ctx));
					
					addNode(new Banshee(ctx));
					addNode(new Grotworms(ctx));
					addNode(new Spiders(ctx));
					addNode(new Minotaurs(ctx));
					addNode(new Dogs(ctx));
					addNode(new Monkeys(ctx));
					addNode(new Bats(ctx));
					addNode(new Ghosts(ctx));
					addNode(new Caveslime(ctx));
					addNode(new Icefiends(ctx));
					addNode(new Trolls(ctx));
					addNode(new DesertLizards(ctx));
					addNode(new Goblins(ctx));
					addNode(new Scorpians(ctx));
					addNode(new CrawlingHands(ctx));
					addNode(new Dwarves(ctx));
					addNode(new Birds(ctx));
					addNode(new Bears(ctx));
					addNode(new CaveBugs(ctx));
					addNode(new Zombies(ctx));
					addNode(new Skeletons(ctx));
					addNode(new Ogre(ctx));
					addNode(new Pyrefiend(ctx));
					addNode(new EarthWarriors(ctx));
					addNode(new Ghouls(ctx));
					addNode(new Harpieswarm(ctx));
				}
				
			});
		
	}
	public static final int FOODID = 373;
	public static final boolean FOOD_FEATURE = true;
	public static boolean goBank = false;
	private int gainedExp = 0;
	private Timer timeTask = new Timer(0);
	
	private String taskState = "Unknown";
	public static String currentTask = "Finished task, getting new one";
	public static int push = 16;//was 21
	//26 push for Vannaka tasks
	//21 push for Turael
	public static int mask = 0x3F;//was 1F
	public static String state = "Null";
	public static double health;
	public static int kills;//counts the amount of kills
	public static boolean onceDepositInventory = false;
	public static String master = "Turael";//"Turael""Vannaka"
	boolean doneFight = false;//for use in detection of kills
	private final List<SlayerNode> nodeList = Collections.synchronizedList(new ArrayList<SlayerNode>());
	
	
	
	@Override
	public void repaint(Graphics g) {
		
		if(ctx.settings.get(183)==0){
			taskState = "Completed";
		}else taskState = "Not complete";
		
		g.drawString("Task state: " + taskState, 57, 123);
		g.drawString("Type of task: " + currentTask, 57, 143);
		g.drawString("Amount left: " + ctx.settings.get(183), 57, 163);
		g.drawString("Gained slayer exp: " + (ctx.skills.getExperience(Skills.SLAYER)-gainedExp), 57, 183);
		g.drawString("Current state: " + state, 57, 213);
		g.drawString("Amount of kills: " + kills, 57, 233);
		g.drawString("Slayer master: " + master, 57, 253);
		
	}
	 private void addNode(final SlayerNode...nodes) {
		   
	        for(SlayerNode node : nodes) {
	            if(!this.nodeList.contains(node)) {
	                this.nodeList.add(node);
	            }
	        }
	    }
	SMethod m = new SMethod(ctx);
	String ts="";
	
	@Override
	public int poll() {
		m.setGeneralCamera();
		killCounter();
	
		if(ctx.players.local().getHealthPercent()<30){
			m.state("Emergency! Teleporting away!");
			m.interactInventory(8009,"Break", "Falador tablet");
		}
		if(!ctx.bank.isOpen())
			onceDepositInventory = false;
		m.foodSupport();
		
		while(ctx.widgets.get(1262,18).isVisible()){
			state = "Closing dialogue";
			ctx.widgets.get(1262,18).click();
		}
		while(ctx.widgets.get(1223,11).isVisible()){
			state = "Closing dialogue";
			ctx.widgets.get(1223,11).click();
		}
		while(ctx.widgets.get(676,15).isVisible()){
			state = "Closing dialogue";
			ctx.widgets.get(676,15).click();
		}
		while(ctx.widgets.get(565,13).isVisible()){
			state = "Closing dialogue";
			ctx.widgets.get(565,13).click();
		}
		for(SlayerNode node: nodeList){
			if(node.activate()){
			//	System.out.println("Executing: " + node.toString());
				node.execute();
			}
		}
	 
		return 500;
	}
	private void killCounter() {
		if(m.getInteractingNPC()!=null && ctx.players.local().isInCombat()){
			doneFight  = true;
		}else
		if(doneFight){
			System.out.println("Enemy health is zero");
			slayerbody.kills+=1;
			doneFight = false;
		}
		
	}
	class determineTask extends SlayerNode{

		public determineTask(MethodContext ctx) {
			super(ctx);
		}

		@Override
		public boolean activate() {
			return true;
		}
		
		@Override
		public void execute() {
			if(ctx.settings.get(183)==0){
				currentTask = "Finished task, getting new one";
				ts = "";
				Harpieswarm.hasLantern = false;
			}else if(currentTask == "Finished task, getting new one"){
				if(m.playerText("Your current")){
					ts = ctx.widgets.get(137,90).getChild(0).getText();
					System.out.println("ts:" + ts);
				}else m.interactInventory(4155, "Kills-left", "Enchanted gem");
			}
			if(ts.contains("harpie bug swarms")){//harpie bug swarms - unknown requirement
				   currentTask = "harpie bug swarms";
		    }else if(ts.contains("zombies")){//Zombies - no requirements
				currentTask = "zombies";
		    }else if(ts.contains("spider")){//Spiders - no requirements
				currentTask = "spider";
		    }else if(ts.contains("bats")){//Bats - no requirements
				currentTask = "bats";
		    }else if(ts.contains("dwarves")){//Dwarves - no requirements
				currentTask = "dwarves";
		    }else if(ts.contains("trolls")){//Trolls - no requirements
				currentTask = "trolls";
		    }else if(ts.contains("dogs")){//Trolls - no requirements
				currentTask = "dogs";
		    }else if(ts.contains("desert lizards")){//Desert lizards - waterskins(optional), 'ice coolers' for killing them
				currentTask = "desert lizards";
		    }else if(ts.contains("banshee")){////Banshees - need ear mufflers
				currentTask = "banshee";
		    }else if(ts.contains("bears")){////Bears - no requirements
				currentTask = "bears";
		    }if(ts.contains("grotworms")){//Grotworms - no requirements
				currentTask = "grotworms";
			}else if((ts.contains("birds"))){//Birds - no requirements
				currentTask = "birds";
		    }else if((ts.contains("cavebugs"))){//Cave bugs - lit torch
				currentTask = "cavebugs";
		    }else if((ts.contains("cave slime"))){//Cave slime - lit torch
				currentTask = "cave slime";
		    }else if((ts.contains("goblins"))){//Goblins - no requirements
				currentTask = "goblins";
		    }/*
			else if((ctx.settings.get(2091)>>push&mask)==2){//Crawling hands
				currentTask = "Crawling hands";
			}else if((ctx.settings.get(2091)>>push&mask)==3){//Skeletons
				currentTask = "Skeletons";
			}else if((ctx.settings.get(2091)>>push&mask)==4||
					(ctx.settings.get(2091)>>push&mask)==25){//Pyrefiends
				currentTask = "Pyrefiends";
			}else if((ctx.settings.get(2091)>>push&mask)==5){//Goblins
				currentTask = "Goblins";
		    }else if((ctx.settings.get(2091)>>push&mask)==6){//Dogs
		    	if(master=="Vannaka")
		    		currentTask = "Crocodiles";
		    	else
				currentTask = "Ghosts";
				currentTask = "Dogs";
		    }else if((ctx.settings.get(2091)>>push&mask)==7){//Ice fiends
		    	if(master=="Vannaka")
		    		currentTask = "Ogres";
		    	else
				currentTask = "Ghosts";
				currentTask = "Ice fiends";
		    }else if((ctx.settings.get(2091)>>push&mask)==8){//Minotaurs
				currentTask = "Minotaurs";
		    }else if((ctx.settings.get(2091)>>push&mask)==9){//Ghosts/moss giants
		    	if(master=="Vannaka")
		    		currentTask = "Moss giants";
		    	else
				currentTask = "Ghosts";
			}else if((ctx.settings.get(2091)>>push&mask)==10){//Bears
				currentTask = "Bears";
			}else if((ctx.settings.get(2091)>>push&mask)==11){//Bats
				currentTask = "Bats";
			}else*/
			/*
		    else if((ctx.settings.get(2091)>>push&mask)==13){//Scorpians
				currentTask = "Scorpians";
		    }else if((ctx.settings.get(2091)>>push&mask)==14){//Cave bugs
				currentTask = "Cave bugs";
		    }else if((ctx.settings.get(2091)>>push&mask)==15){//desert lizards - needs 'Ice cooler' + maybe waterskins
		    	if(master=="Vannaka")
		    		currentTask = "Ankous";
		    	else
				currentTask = "Ghosts";
		    	currentTask = "Desert lizards";
		    }else if((ctx.settings.get(2091)>>push&mask)==16){//Dwarves
		    	if(master=="Vannaka")
		    		currentTask = "Ice giants";
		    	else
				currentTask = "Dwarves";
		    }else if((ctx.settings.get(2091)>>push&mask)==17){//Spiders
		    	if(master=="Vannaka")
		    		currentTask = "Green dragons";
		    	else
				currentTask = "Spiders";
		    }else if((ctx.settings.get(2091)>>push&mask)==18){//Birds
				currentTask = "Birds";
		    }else if((ctx.settings.get(2091)>>push&mask)==19){
				currentTask = "Unknown: 19";
				System.out.println("Unknown found: 19");
				getController().stop();
		    }else if((ctx.settings.get(2091)>>push&mask)==20){//Monkeys
				currentTask = "Monkeys";
			}else if((ctx.settings.get(2091)>>push&mask)==21){//Cave slime, needs a torch, and antipoison
				currentTask = "Cave slime";
		    }else if((ctx.settings.get(2091)>>push&mask)==22){
				currentTask = "Unknown: 22";
				System.out.println("Unknown found: 22");
				getController().stop();
		    }else if((ctx.settings.get(2091)>>push&mask)==23){//Trolls
				currentTask = "Trolls";
		    }else */
		    /*
			else if((ctx.settings.get(2091)>>push&mask)==25){
				if(master=="Vannaka")
		    		currentTask = "Pyrefiends";
		    	else{
				currentTask = "Ghosts";
				currentTask = "Unknown: 25";
				System.out.println("Unknown found: 25");
				getController().stop();
		    	}
		    }else if((ctx.settings.get(2091)>>push&mask)==26){
				currentTask = "Unknown: 26";
				System.out.println("Unknown found: 26");
				getController().stop();
		    }else if((ctx.settings.get(2091)>>push&mask)==27){//Earth warriors
				currentTask = "Earth warriors";
		    }else if((ctx.settings.get(2091)>>push&mask)==28){//Ogres
				currentTask = "Ogres";
			}else if((ctx.settings.get(2091)>>push&mask)==29){
				currentTask = "Unknown: 29";
				System.out.println("Unknown found: 29");
				getController().stop();
		    }else if((ctx.settings.get(2091)>>push&mask)==30){//Ice warriors
		    	if(master=="Vannaka")
		    		currentTask = "Ice warriors";
		    	else
				currentTask = "Ice warriors";
		    }else if((ctx.settings.get(2091)>>push&mask)==31){
		    	if(master=="Vannaka")
		    		currentTask = "Ghouls";
		    	else{
				currentTask = "Ghosts";
				currentTask = "Unknown: 31";
				System.out.println("Unknown found: 31");
				getController().stop();
		    	}
		    }else if((ctx.settings.get(2091)>>push&mask)==32){///Harpie swarms
				currentTask = "Harpy bug swarms";
		    }else currentTask = "Missing a setting: " + (ctx.settings.get(2091)>>push&mask);
			
			/*
			if((ctx.settings.get(2091)>>push&mask)==1){//Banshees, need ear mufflers/some helmet?
				if(master=="Vannaka")
		    		currentTask = "Harpie bug swarms";
		    	else
				currentTask = "Banshees";
		    }else if((ctx.settings.get(2091)>>push&mask)==2){//Crawling hands
				currentTask = "Crawling hands";
			}else if((ctx.settings.get(2091)>>push&mask)==3){//Skeletons
				currentTask = "Skeletons";
			}else if((ctx.settings.get(2091)>>push&mask)==4||
					(ctx.settings.get(2091)>>push&mask)==25){//Pyrefiends
				currentTask = "Pyrefiends";
			}else if((ctx.settings.get(2091)>>push&mask)==5){//Goblins
				currentTask = "Goblins";
		    }else if((ctx.settings.get(2091)>>push&mask)==6){//Dogs
		    	if(master=="Vannaka")
		    		currentTask = "Crocodiles";
		    	else
				currentTask = "Ghosts";
				currentTask = "Dogs";
		    }else if((ctx.settings.get(2091)>>push&mask)==7){//Ice fiends
		    	if(master=="Vannaka")
		    		currentTask = "Ogres";
		    	else
				currentTask = "Ghosts";
				currentTask = "Ice fiends";
		    }else if((ctx.settings.get(2091)>>push&mask)==8){//Minotaurs
				currentTask = "Minotaurs";
		    }else if((ctx.settings.get(2091)>>push&mask)==9){//Ghosts/moss giants
		    	if(master=="Vannaka")
		    		currentTask = "Moss giants";
		    	else
				currentTask = "Ghosts";
			}else if((ctx.settings.get(2091)>>push&mask)==10){//Bears
				currentTask = "Bears";
			}else if((ctx.settings.get(2091)>>push&mask)==11){//Bats
				currentTask = "Bats";
			}else if((ctx.settings.get(2091)>>push&mask)==12){//Zombies
				currentTask = "Zombies";
		    }else if((ctx.settings.get(2091)>>push&mask)==13){//Scorpians
				currentTask = "Scorpians";
		    }else if((ctx.settings.get(2091)>>push&mask)==14){//Cave bugs
				currentTask = "Cave bugs";
		    }else if((ctx.settings.get(2091)>>push&mask)==15){//desert lizards - needs 'Ice cooler' + maybe waterskins
		    	if(master=="Vannaka")
		    		currentTask = "Ankous";
		    	else
				currentTask = "Ghosts";
		    	currentTask = "Desert lizards";
		    }else if((ctx.settings.get(2091)>>push&mask)==16){//Dwarves
		    	if(master=="Vannaka")
		    		currentTask = "Ice giants";
		    	else
				currentTask = "Dwarves";
		    }else if((ctx.settings.get(2091)>>push&mask)==17){//Spiders
		    	if(master=="Vannaka")
		    		currentTask = "Green dragons";
		    	else
				currentTask = "Spiders";
		    }else */
		    /*
		    else if((ctx.settings.get(2091)>>push&mask)==19){
				currentTask = "Unknown: 19";
				System.out.println("Unknown found: 19");
				getController().stop();
		    }else if((ctx.settings.get(2091)>>push&mask)==20){//Monkeys
				currentTask = "Monkeys";
			}else if((ctx.settings.get(2091)>>push&mask)==21){//Cave slime, needs a torch, and antipoison
				currentTask = "Cave slime";
		    }else if((ctx.settings.get(2091)>>push&mask)==22){
				currentTask = "Unknown: 22";
				System.out.println("Unknown found: 22");
				getController().stop();
		    }else if((ctx.settings.get(2091)>>push&mask)==23){//Trolls
				currentTask = "Trolls";
		    }else if((ctx.settings.get(2091)>>push&mask)==24||
		    		(ctx.settings.get(2091)>>push&mask)==35){//Grotworms
				currentTask = "Grotworms";
			}else if((ctx.settings.get(2091)>>push&mask)==25){
				if(master=="Vannaka")
		    		currentTask = "Pyrefiends";
		    	else{
				currentTask = "Ghosts";
				currentTask = "Unknown: 25";
				System.out.println("Unknown found: 25");
				getController().stop();
		    	}
		    }else if((ctx.settings.get(2091)>>push&mask)==26){
				currentTask = "Unknown: 26";
				System.out.println("Unknown found: 26");
				getController().stop();
		    }else if((ctx.settings.get(2091)>>push&mask)==27){//Earth warriors
				currentTask = "Earth warriors";
		    }else if((ctx.settings.get(2091)>>push&mask)==28){//Ogres
				currentTask = "Ogres";
			}else if((ctx.settings.get(2091)>>push&mask)==29){
				currentTask = "Unknown: 29";
				System.out.println("Unknown found: 29");
				getController().stop();
		    }else if((ctx.settings.get(2091)>>push&mask)==30){//Ice warriors
		    	if(master=="Vannaka")
		    		currentTask = "Ice warriors";
		    	else
				currentTask = "Ice warriors";
		    }else if((ctx.settings.get(2091)>>push&mask)==31){
		    	if(master=="Vannaka")
		    		currentTask = "Ghouls";
		    	else{
				currentTask = "Ghosts";
				currentTask = "Unknown: 31";
				System.out.println("Unknown found: 31");
				getController().stop();
		    	}
		    }else if((ctx.settings.get(2091)>>push&mask)==32){///Harpie swarms
				currentTask = "Harpy bug swarms";
		    }else currentTask = "Missing a setting: " + (ctx.settings.get(2091)>>push&mask);
			*/
		}
		
	}

}