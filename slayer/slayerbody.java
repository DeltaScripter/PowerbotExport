package slayer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Random;
import org.powerbot.script.Script;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Skills;






@Script.Manifest(name = "Delta Slayer", 
description = "Currently under development", properties = "topic = 0")

public class slayerbody extends PollingScript<ClientContext> implements PaintListener{

	//CHANGE FILTER FOR WHAT NPC TO ATTACK IN FIGHTING
/*Requirements for Turael
 * - Unlit torch
 * - Falador tablets
 * - Enchanted gem
 * - Food source
 * -Need to have a rope tied to the lumbridge cave*/


	public slayerbody(){
			getExecQueue(State.STOP).add(new Runnable() {
				@Override
				public void run() {
					stop();
				}
				});
			getExecQueue(State.START).add(new Runnable() {
				@Override
				public void run() {
					//randoms
					camAngle = Random.nextInt(60, 70);
					
					runTime = System.currentTimeMillis();
					gainedExp = ctx.skills.experience(Skills.SLAYER);
					addNode(new SlayerAntipattern(ctx));
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
	
	private int camAngle;
	
	public static final int FOODID = 373;//379 = lobster, 373=swordfish
	public static final boolean FOOD_FEATURE = true;
	public static boolean goBank = false;
	private int gainedExp = 0;
	public long runTime;
	public static boolean antiPattern =false;
	
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
	public void poll() {
		m.setGeneralCamera(camAngle);
		killCounter();
	    calcAntiPattern();
	    
	    //if low on health and fighting then use tablet to teleport away
		if(ctx.players.local().healthPercent()<30&&
				ctx.players.local().interacting().valid()){
			m.state("Emergency! Teleporting away!");
			m.interactInventory(8009,"Break", "Falador tablet");
		}
		if(!ctx.bank.opened())
			onceDepositInventory = false;
		
		    m.foodSupport();
		//close interfaces
		while(ctx.widgets.component(1262,18).inViewport()){
			state = "Closing dialogue";
			ctx.widgets.component(1262,18).click();
		}
		while(ctx.widgets.component(1223,11).inViewport()){
			state = "Closing dialogue";
			ctx.widgets.component(1223,11).click();
		}
		while(ctx.widgets.component(676,15).inViewport()){
			state = "Closing dialogue";
			ctx.widgets.component(676,15).click();
		}
		while(ctx.widgets.component(565,13).inViewport()){
			state = "Closing dialogue";
			ctx.widgets.component(565,13).click();
		}
		for(SlayerNode node: nodeList){
			if(node.activate()){
			  System.out.println("Executing: " + node.toString());
				node.execute();
			}
		}
	 
	}
	private void killCounter() {
		if(m.getInteractingNPC()!=null && ctx.players.local().inCombat()){
			doneFight  = true;
		}else
		if(doneFight){
			System.out.println("Enemy health is zero");
			slayerbody.kills+=1;
			doneFight = false;
		}
		
	}
	class determineTask extends SlayerNode{

		public determineTask(ClientContext ctx) {
			super(ctx);
		}

		@Override
		public boolean activate() {
			return true;
		}
		
		@Override
		public void execute() {
			if(ctx.varpbits.varpbit(183)==0){
				currentTask = "Finished task, getting new one";
				ts = "";
				Harpieswarm.hasLantern = false;
			}else if(currentTask == "Finished task, getting new one"){
				if(m.playerText("Your current")){
					ts = ctx.widgets.component(137,90).component(0).text();
					System.out.println("ts:" + ts);
				}else {
					m.interactInventory(4155, "Kills-left", "Enchanted gem");
					m.sleep(Random.nextInt(1500, 2000));
				}
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
		    }else if((ts.contains("scorpions"))){//Scorpions - no requirements
				currentTask = "scorpions";
		    }else if((ts.contains("ghosts"))){//Ghosts - no requirements
				currentTask = "ghosts";
		    }else if((ts.contains("crawling hand"))){//Crawling Hands - no requirements
				currentTask = "crawling hand";
		    }else if((ts.contains("skeleton"))){//Skeletons - no requirements
				currentTask = "skeleton";
		    }else if((ts.contains("pyrefiend"))){//Skeletons - no requirements
				currentTask = "pyrefiend";
		    }else if((ts.contains("minotaur"))){//Skeletons - no requirements
				currentTask = "minotaur";
		    }/*
			else if((ctx.varpbits.varpbit(2091)>>push&mask)==2){//Crawling hands
				currentTask = "Crawling hands";
			}else if((ctx.varpbits.varpbit(2091)>>push&mask)==3){//Skeletons
				currentTask = "Skeletons";
			}else if((ctx.varpbits.varpbit(2091)>>push&mask)==4||
					(ctx.varpbits.varpbit(2091)>>push&mask)==25){//Pyrefiends
				currentTask = "Pyrefiends";
			}else if((ctx.varpbits.varpbit(2091)>>push&mask)==5){//Goblins
				currentTask = "Goblins";
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==6){//Dogs
		    	if(master=="Vannaka")
		    		currentTask = "Crocodiles";
		    	else
				currentTask = "Ghosts";
				currentTask = "Dogs";
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==7){//Ice fiends
		    	if(master=="Vannaka")
		    		currentTask = "Ogres";
		    	else
				currentTask = "Ghosts";
				currentTask = "Ice fiends";
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==8){//Minotaurs
				currentTask = "Minotaurs";
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==9){//Ghosts/moss giants
		    	if(master=="Vannaka")
		    		currentTask = "Moss giants";
		    	else
				currentTask = "Ghosts";
			}else if((ctx.varpbits.varpbit(2091)>>push&mask)==10){//Bears
				currentTask = "Bears";
			}else if((ctx.varpbits.varpbit(2091)>>push&mask)==11){//Bats
				currentTask = "Bats";
			}else*/
			/*
		    else if((ctx.varpbits.varpbit(2091)>>push&mask)==13){//Scorpians
				currentTask = "Scorpians";
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==14){//Cave bugs
				currentTask = "Cave bugs";
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==15){//desert lizards - needs 'Ice cooler' + maybe waterskins
		    	if(master=="Vannaka")
		    		currentTask = "Ankous";
		    	else
				currentTask = "Ghosts";
		    	currentTask = "Desert lizards";
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==16){//Dwarves
		    	if(master=="Vannaka")
		    		currentTask = "Ice giants";
		    	else
				currentTask = "Dwarves";
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==17){//Spiders
		    	if(master=="Vannaka")
		    		currentTask = "Green dragons";
		    	else
				currentTask = "Spiders";
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==18){//Birds
				currentTask = "Birds";
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==19){
				currentTask = "Unknown: 19";
				System.out.println("Unknown found: 19");
				getController().stop();
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==20){//Monkeys
				currentTask = "Monkeys";
			}else if((ctx.varpbits.varpbit(2091)>>push&mask)==21){//Cave slime, needs a torch, and antipoison
				currentTask = "Cave slime";
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==22){
				currentTask = "Unknown: 22";
				System.out.println("Unknown found: 22");
				getController().stop();
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==23){//Trolls
				currentTask = "Trolls";
		    }else */
		    /*
			else if((ctx.varpbits.varpbit(2091)>>push&mask)==25){
				if(master=="Vannaka")
		    		currentTask = "Pyrefiends";
		    	else{
				currentTask = "Ghosts";
				currentTask = "Unknown: 25";
				System.out.println("Unknown found: 25");
				getController().stop();
		    	}
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==26){
				currentTask = "Unknown: 26";
				System.out.println("Unknown found: 26");
				getController().stop();
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==27){//Earth warriors
				currentTask = "Earth warriors";
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==28){//Ogres
				currentTask = "Ogres";
			}else if((ctx.varpbits.varpbit(2091)>>push&mask)==29){
				currentTask = "Unknown: 29";
				System.out.println("Unknown found: 29");
				getController().stop();
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==30){//Ice warriors
		    	if(master=="Vannaka")
		    		currentTask = "Ice warriors";
		    	else
				currentTask = "Ice warriors";
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==31){
		    	if(master=="Vannaka")
		    		currentTask = "Ghouls";
		    	else{
				currentTask = "Ghosts";
				currentTask = "Unknown: 31";
				System.out.println("Unknown found: 31");
				getController().stop();
		    	}
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==32){///Harpie swarms
				currentTask = "Harpy bug swarms";
		    }else currentTask = "Missing a setting: " + (ctx.varpbits.varpbit(2091)>>push&mask);
			
			/*
			if((ctx.varpbits.varpbit(2091)>>push&mask)==1){//Banshees, need ear mufflers/some helmet?
				if(master=="Vannaka")
		    		currentTask = "Harpie bug swarms";
		    	else
				currentTask = "Banshees";
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==2){//Crawling hands
				currentTask = "Crawling hands";
			}else if((ctx.varpbits.varpbit(2091)>>push&mask)==3){//Skeletons
				currentTask = "Skeletons";
			}else if((ctx.varpbits.varpbit(2091)>>push&mask)==4||
					(ctx.varpbits.varpbit(2091)>>push&mask)==25){//Pyrefiends
				currentTask = "Pyrefiends";
			}else if((ctx.varpbits.varpbit(2091)>>push&mask)==5){//Goblins
				currentTask = "Goblins";
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==6){//Dogs
		    	if(master=="Vannaka")
		    		currentTask = "Crocodiles";
		    	else
				currentTask = "Ghosts";
				currentTask = "Dogs";
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==7){//Ice fiends
		    	if(master=="Vannaka")
		    		currentTask = "Ogres";
		    	else
				currentTask = "Ghosts";
				currentTask = "Ice fiends";
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==8){//Minotaurs
				currentTask = "Minotaurs";
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==9){//Ghosts/moss giants
		    	if(master=="Vannaka")
		    		currentTask = "Moss giants";
		    	else
				currentTask = "Ghosts";
			}else if((ctx.varpbits.varpbit(2091)>>push&mask)==10){//Bears
				currentTask = "Bears";
			}else if((ctx.varpbits.varpbit(2091)>>push&mask)==11){//Bats
				currentTask = "Bats";
			}else if((ctx.varpbits.varpbit(2091)>>push&mask)==12){//Zombies
				currentTask = "Zombies";
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==13){//Scorpians
				currentTask = "Scorpians";
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==14){//Cave bugs
				currentTask = "Cave bugs";
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==15){//desert lizards - needs 'Ice cooler' + maybe waterskins
		    	if(master=="Vannaka")
		    		currentTask = "Ankous";
		    	else
				currentTask = "Ghosts";
		    	currentTask = "Desert lizards";
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==16){//Dwarves
		    	if(master=="Vannaka")
		    		currentTask = "Ice giants";
		    	else
				currentTask = "Dwarves";
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==17){//Spiders
		    	if(master=="Vannaka")
		    		currentTask = "Green dragons";
		    	else
				currentTask = "Spiders";
		    }else */
		    /*
		    else if((ctx.varpbits.varpbit(2091)>>push&mask)==19){
				currentTask = "Unknown: 19";
				System.out.println("Unknown found: 19");
				getController().stop();
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==20){//Monkeys
				currentTask = "Monkeys";
			}else if((ctx.varpbits.varpbit(2091)>>push&mask)==21){//Cave slime, needs a torch, and antipoison
				currentTask = "Cave slime";
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==22){
				currentTask = "Unknown: 22";
				System.out.println("Unknown found: 22");
				getController().stop();
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==23){//Trolls
				currentTask = "Trolls";
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==24||
		    		(ctx.varpbits.varpbit(2091)>>push&mask)==35){//Grotworms
				currentTask = "Grotworms";
			}else if((ctx.varpbits.varpbit(2091)>>push&mask)==25){
				if(master=="Vannaka")
		    		currentTask = "Pyrefiends";
		    	else{
				currentTask = "Ghosts";
				currentTask = "Unknown: 25";
				System.out.println("Unknown found: 25");
				getController().stop();
		    	}
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==26){
				currentTask = "Unknown: 26";
				System.out.println("Unknown found: 26");
				getController().stop();
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==27){//Earth warriors
				currentTask = "Earth warriors";
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==28){//Ogres
				currentTask = "Ogres";
			}else if((ctx.varpbits.varpbit(2091)>>push&mask)==29){
				currentTask = "Unknown: 29";
				System.out.println("Unknown found: 29");
				getController().stop();
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==30){//Ice warriors
		    	if(master=="Vannaka")
		    		currentTask = "Ice warriors";
		    	else
				currentTask = "Ice warriors";
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==31){
		    	if(master=="Vannaka")
		    		currentTask = "Ghouls";
		    	else{
				currentTask = "Ghosts";
				currentTask = "Unknown: 31";
				System.out.println("Unknown found: 31");
				getController().stop();
		    	}
		    }else if((ctx.varpbits.varpbit(2091)>>push&mask)==32){///Harpie swarms
				currentTask = "Harpy bug swarms";
		    }else currentTask = "Missing a setting: " + (ctx.varpbits.varpbit(2091)>>push&mask);
			*/
		}
		
	}
	  private boolean calcAntiPattern() {
			int number = Random.nextInt(0,14);
			if(number == 1){
				antiPattern = true;
				return true;
			}
			return false;
		}
	 private void setMouse(Graphics g) {
			g.setColor(Color.MAGENTA);
			g.drawLine(mouseX, mouseY - 800, mouseX, mouseY + 800);
			g.drawLine(mouseX - 800, mouseY, mouseX + 800, mouseY);
		}
	   private int mouseX;
	   private int mouseY;
	
	@Override
	public void repaint(Graphics g) {
		long time = runTime - System.currentTimeMillis();
		
		setMouse(g);
		mouseX = (int) ctx.mouse.getLocation().getX();
		mouseY = (int) ctx.mouse.getLocation().getY();
		
		g.drawString("Runtime: " + m.format(time), 30, 100);
		
		if(ctx.varpbits.varpbit(183)==0){
			taskState = "Completed";
		}else taskState = "Not complete";
		g.setColor(Color.GREEN);
		g.drawString("Task state: " + taskState, 30, 120);
		g.drawString("Type of task: " + currentTask, 30, 140);
		g.drawString("Amount left: " + ctx.varpbits.varpbit(183), 30, 160);
		g.drawString("Gained slayer exp: " + (ctx.skills.experience(Skills.SLAYER)-gainedExp) + " XP", 30,180);
		g.drawString("Current state: " + state, 30,200);
		g.drawString("Amount of kills: " + kills, 30,220);
		g.drawString("Slayer master: " + master, 30,240);
		g.drawString("FOOD_FEATURE:  " + FOOD_FEATURE, 30,260);
		g.drawString("Player interacting:  " + ctx.players.local().interacting().valid(), 30,280);
		g.drawString("hasFood:  " + m.hasFood, 30,300);
		
		
	}

}
