package slayer;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



import org.powerbot.event.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script.State;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.Skills;
import org.powerbot.script.methods.Hud.Window;
import org.powerbot.script.util.Random;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.Tile;
import org.powerbot.script.wrappers.TilePath;



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
				}
				
			});
		
	}
	public static final int FOODID = 373;
	public static final boolean FOOD_FEATURE = true;
	public static boolean goBank = false;
	private int gainedExp = 0;
	
	private String taskState = "Unknown";
	public static String currentTask = "Unknown";
	public static int push = 21;
	public static String state = "Null";
	public static double health;
	private final List<SlayerNode> nodeList = Collections.synchronizedList(new ArrayList<SlayerNode>());
	
	
	
	@Override
	public void repaint(Graphics g) {
		
		if(ctx.settings.get(183)==0){
			taskState = "Completed";
		}else taskState = "Not complete";
		
		for(Tile i: SMethod.path){
			g.drawRect(i.getX(), i.getY(), 20, 20);
		}
		
		g.drawString("Task state: " + taskState, 57, 123);
		g.drawString("Type of task: " + currentTask, 57, 143);
		g.drawString("Amount left: " + ctx.settings.get(183), 57, 163);
		g.drawString("Gained slayer exp: " + (ctx.skills.getExperience(Skills.SLAYER)-gainedExp), 57, 183);
		g.drawString("Current state: " + state, 57, 213);
		
	}
	 private void addNode(final SlayerNode...nodes) {
		   
	        for(SlayerNode node : nodes) {
	            if(!this.nodeList.contains(node)) {
	                this.nodeList.add(node);
	            }
	        }
	    }
	 ArrayList<Tile> pather = new ArrayList<Tile>();
	SMethod m = new SMethod(ctx);
	boolean fixed = false;
	boolean once = false;
	Tile[] arr;
	@Override
	public int poll() {
		m.setGeneralCamera();
		m.walkTo(new Tile(3229, 3218, 0),"");
		
		
		/*
		m.foodSupport();
		
		while(ctx.widgets.get(1262,18).isVisible()){
			state = "Closing dialogue";
			ctx.widgets.get(1262,18).click();
		}
		while(ctx.widgets.get(1223,11).isVisible()){
			state = "Closing dialogue";
			ctx.widgets.get(1223,11).click();
		}
		while(ctx.widgets.get(565,13).isVisible()){
			state = "Closing dialogue";
			ctx.widgets.get(565,13).click();
		}
		for(SlayerNode node: nodeList){
			if(node.activate()){
				node.execute();
			}
		}
	 */
		return 100;
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
		    if((ctx.settings.get(2091)>>push&0x1F)==1){//Banshees, need ear mufflers/some helmet?
				currentTask = "Banshees";
		    }else if((ctx.settings.get(2091)>>push&0x1F)==2){//Crawling hands
				currentTask = "Crawling hands";
			}else if((ctx.settings.get(2091)>>push&0x1F)==3){//Skeletons
				currentTask = "Skeletons";
			}else if((ctx.settings.get(2091)>>push&0x1F)==4){
				currentTask = "Unknown: 4";
				System.out.println("Unknown found: 4");
				getController().stop();
			}else if((ctx.settings.get(2091)>>push&0x1F)==5){//Goblins
				currentTask = "Goblins";
		    }else if((ctx.settings.get(2091)>>push&0x1F)==6){//Dogs
				currentTask = "Dogs";
		    }else if((ctx.settings.get(2091)>>push&0x1F)==7){//Ice fiends
				currentTask = "Ice fiends";
		    }else if((ctx.settings.get(2091)>>push&0x1F)==8){//Minotaurs
				currentTask = "Minotaurs";
		    }else if((ctx.settings.get(2091)>>push&0x1F)==9){//Ghosts
				currentTask = "Ghosts";
			}else if((ctx.settings.get(2091)>>push&0x1F)==10){//Bears
				currentTask = "Bears";
			}else if((ctx.settings.get(2091)>>push&0x1F)==11){//Bats
				currentTask = "Bats";
			}else if((ctx.settings.get(2091)>>push&0x1F)==12){//Zombies
				currentTask = "Zombies";
		    }else if((ctx.settings.get(2091)>>push&0x1F)==13){//Scorpians
				currentTask = "Scorpians";
		    }else if((ctx.settings.get(2091)>>push&0x1F)==14){//Cave bugs
				currentTask = "Cave bugs";
		    }else if((ctx.settings.get(2091)>>push&0x1F)==15){//desert lizards - needs 'Ice cooler' + maybe waterskins
				currentTask = "Desert lizards";
		    }else if((ctx.settings.get(2091)>>push&0x1F)==16){//Dwarves
				currentTask = "Dwarves";
		    }else if((ctx.settings.get(2091)>>push&0x1F)==17){//Spiders
				currentTask = "Spiders";
		    }else if((ctx.settings.get(2091)>>push&0x1F)==18){//Birds
				currentTask = "Birds";
		    }else if((ctx.settings.get(2091)>>push&0x1F)==19){
				currentTask = "Unknown: 19";
				System.out.println("Unknown found: 19");
				getController().stop();
		    }else if((ctx.settings.get(2091)>>push&0x1F)==20){//Monkeys
				currentTask = "Monkeys";
			}else if((ctx.settings.get(2091)>>push&0x1F)==21){//Cave slime, needs a torch, and antipoison
				currentTask = "Cave slime";
		    }else if((ctx.settings.get(2091)>>push&0x1F)==22){
				currentTask = "Unknown: 22";
				System.out.println("Unknown found: 22");
				getController().stop();
		    }else if((ctx.settings.get(2091)>>push&0x1F)==23){//Trolls
				currentTask = "Trolls";
		    }else if((ctx.settings.get(2091)>>push&0x1F)==24){//Grotworms
				currentTask = "Grotworms";
			}else currentTask = "Missing a setting";
			
		}
		
	}

}
