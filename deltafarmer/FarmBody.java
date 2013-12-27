package deltafarmer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.powerbot.event.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.Skills;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Tile;
import org.powerbot.script.util.Random;
import org.powerbot.script.util.Timer;

import deltafarmer.FarmData.TeleportLode;
import deltafarmer.FarmData.TeleportType;




@org.powerbot.script.Manifest(authors = { "Delta Scripter" }, name = "Delta Farmer", 
description = "Dances in a circle! YAY!", version = 1, website = "", hidden = true)
public class FarmBody extends PollingScript implements PaintListener{

	public FarmBody(){
		getExecQueue(State.STOP).add(new Runnable() {
			@Override
			public void run() {
				System.out.println("Shutting down.");
				getController().stop();
			}
			});
		getExecQueue(State.START).add(new Runnable() {
			@Override
			public void run() {
				initialExp = ctx.skills.getExperience(Skills.FARMING);
				runtime = new Timer(0);
				secondsA = new Timer(0);
				minutesA = new Timer(0);
				addNode(new watcher(ctx));
				addNode(new harvest(ctx));
				addNode(new plantSeed(ctx));
				
			}

		});
	}
	public final Tile[] pathToCatherbyGarden = {new Tile(2823,3463,0),new Tile(2808,3462,0)};
	public final Tile[] pathToFaladorGarden = {
			new Tile(2968,3388,0),new Tile(2981,3377,0),
			new Tile(2996,3366,0), new Tile(3004,3356,0),
			new Tile(3006,3338,0), new Tile(3007,3323,0),
			new Tile(3023,3318,0), new Tile(3036,3317,0),
			new Tile(3048,3306,0), new Tile(3054,3309,0)};
	
	private final List<FarmNode> nodeList = Collections.synchronizedList(new ArrayList<FarmNode>());
	public static String state;
	public static boolean antiPattern;
	private Random rand = new Random();
	private boolean start = true;
	private Timer waiting = new Timer(0);
	public static Timer wait = new Timer(0);//General use timer
	private Timer runtime;
	private Timer secondsA;
	private Timer minutesA;
	private int initialExp;
	private int expGained;
	private int expPerHr;
	static int level;
	static int xpToLevel;
	FarmMethod Method = new FarmMethod(ctx);
	FarmAntipattern anti = new FarmAntipattern(ctx);
	
	//---
	public Timer waitHarvest = new Timer(0);
	public boolean harvest;
	public boolean plantSeeds;
	public int seedTypeID = 5318;
	public String seedTypeName = "Potato seed";
	
	public boolean teleported = false;
	public int cropTypeID[] = {8552, 8550,8551};//potato..
	
	
	@Override
	public int poll() {
		System.out.println("plantseeds: "+plantSeeds);
		while(ctx.widgets.get(1401,35).isVisible()){//become a member!
			state = "Closing interface";
			ctx.widgets.get(1401,35).click();
		}
		if(start){
		for(FarmNode node: nodeList){
			if(node.activate()){
				node.execute();
			}
		}
		}
		return 400;
	}
	
	   private void addNode(final FarmNode...nodes) {
		   
	        for(FarmNode node : nodes) {
	            if(!this.nodeList.contains(node)) {
	                this.nodeList.add(node);
	            }
	        }
	    }
	   class plantSeed extends FarmNode{

			public plantSeed(MethodContext ctx) {
				super(ctx);
			}

			@Override
			public boolean activate() {
				return plantSeeds;
			}

			@Override
			public void execute() {

				//figure out which allotment is empty..
				if((ctx.settings.get(12)>>24 & 0xF) ==3){//Catherby south allotment empty
					System.out.println("Attempting to plant seed in catherby south");
					///plantSeed();
				}else if((ctx.settings.get(12)>>16 & 0xF) <=3){//Catherby north allotment empty
					System.out.println("Attempting to plant seed in catherby south");
					
				}else if((ctx.settings.get(12) & 0xF) <=3){//Falador west allotment empty
					System.out.println("PLHERE1");
					plantSeedsFalador(new Tile(3049,3311,0),7517,(ctx.settings.get(12) & 0xF) <3);
					
				}else if((ctx.settings.get(12)>>8 & 0xF) <=3){///Falador east allotment empty
					System.out.println("Attempting to plant seed in fally south");
					plantSeedsFalador(new Tile(3056,3306,0),7517,((ctx.settings.get(12)>>8 & 0xF) <3));
					
				}
				
			}

			private void plantSeedsFalador(Tile alotLoc, int patchID,boolean condition) {
				Tile local = ctx.players.local().getLocation();
				System.out.println("Now trying to plant seeds Falador allotment");
				
				while(ctx.players.local().getAnimation()!=-1){
					state = "Planting..";
					waitHarvest = new Timer(5000);
				}
				if(!waitHarvest.isRunning())
					
				if(alotLoc.distanceTo(local)<3){
					if(condition){//weeds
						Method.interactO(8550,"Rake", "Weeds");
						waitHarvest = new Timer(1200);
					}else{
					teleported = false;
					state = "Planting " + seedTypeID;
						Method.useItemOn(seedTypeName, patchID, "Plant");
					waitHarvest = new Timer(1200);
					}
				}else if(alotLoc.distanceTo(local)<15){
					ctx.movement.stepTowards(alotLoc);
				}else if(teleported){
					state = "Walking to Falador garden";
					ctx.movement.newTilePath(pathToFaladorGarden).traverse();
				}else if(TeleportLode.FALADOR.getTile().distanceTo(local)<10){
					teleported = true;
				}else Method.teleportTo(TeleportType.FALADOR.getTeleport(), "Falador");
				
				
				
			}

			   
		   }
	   class harvest extends FarmNode{

		public harvest(MethodContext ctx) {
			super(ctx);
		}

		@Override
		public boolean activate() {
			return harvest && !plantSeeds;
		}

		@Override
		public void execute() {
			if((ctx.settings.get(12)>>24 & 0xF) >=9){//Catherby south allotment done
				harvestCatherby(new Tile(2810,3461,0),cropTypeID,(ctx.settings.get(12)>>24 & 0xF) ==9);
			}else if((ctx.settings.get(12)>>16 & 0xF) >=9){//Catherby north allotment done
				harvestCatherby(new Tile(2807,3466,0),cropTypeID,(ctx.settings.get(12)>>16 & 0xF) ==9);
			}else if((ctx.settings.get(12) & 0xF) >=9){//Falador west allotment done
				harvestFalador(new Tile(3052,3310,0),cropTypeID,(ctx.settings.get(12) & 0xF) ==9);
			}else if((ctx.settings.get(12)>>8 & 0xF) >=9){//Falador east allotment done
				harvestFalador(new Tile(3057,3305,0),cropTypeID,(ctx.settings.get(12)>>8 & 0xF) ==9);
			}
			
		}

		private void harvestFalador(Tile alotLoc, int[] cropTypeID,boolean condition) {
			Tile local = ctx.players.local().getLocation();
			System.out.println("Now trying to harvest Falador allotment");
			
			while(ctx.players.local().getAnimation()!=-1){
				state = "Harvesting..";
				waitHarvest = new Timer(5000);
			}
			if(!waitHarvest.isRunning())
			if(alotLoc.distanceTo(local)<3){
			if(condition){
				state = "Clearing dead crops";
					Method.interactO(8550, "Clear", "Potato");
					waitHarvest = new Timer(Random.nextInt(1900, 2500));
				
			}else{
				teleported = false;
				state = "Harvesting " + cropTypeID;
				for(int i: cropTypeID)
					Method.interactO(i, "Harvest", "Potato");
				waitHarvest = new Timer(1200);
			}
			}else if(alotLoc.distanceTo(local)<15){
				ctx.movement.stepTowards(alotLoc);
			}else if(teleported){
				state = "Walking to Falador garden";
				ctx.movement.newTilePath(pathToFaladorGarden).traverse();
			}else if(TeleportLode.FALADOR.getTile().distanceTo(local)<10){
				teleported = true;
			}else Method.teleportTo(TeleportType.FALADOR.getTeleport(), "Falador");
			
			
		}

		private void harvestCatherby(Tile alotLoc, int[] cropTypeID, boolean condition) {
			Tile local = ctx.players.local().getLocation();
			System.out.println("Now trying to harvest catherby allot");
			
			while(ctx.players.local().getAnimation()!=-1){
				state = "Harvesting..";
				waitHarvest = new Timer(5000);
			}
			if(!waitHarvest.isRunning())
			if(alotLoc.distanceTo(local)<3){
				if(condition){
					state = "Clearing dead crops";
					for(int i: cropTypeID)
						Method.interactO(i, "Clear", "Potato");
				}else{
				teleported = false;
				state = "Harvesting " + cropTypeID;
				for(int i: cropTypeID)
				Method.interactO(i, "Harvest", "Potato");
				waitHarvest = new Timer(1200);
				}
			}else if(alotLoc.distanceTo(local)<15){
				ctx.movement.stepTowards(alotLoc);
			}else if(teleported){
				state = "Walking to Catherby garden";
				ctx.movement.newTilePath(pathToCatherbyGarden).traverse();
			}else if(TeleportLode.CATHERBY.getTile().distanceTo(local)<10){
				teleported = true;
			}else Method.teleportTo(TeleportType.CATHERBY.getTeleport(), "Catherby");
			
		}
		   
	   }
	   class watcher extends FarmNode{

		public watcher(MethodContext ctx) {
			super(ctx);
		}
		
		@Override
		public boolean activate() {
			return true;
		}

		@Override
		public void execute() {
			
			//If there is a vacant allotment..
			if((ctx.settings.get(12)>>24 & 0xF) <=3){//Catherby south allotment empty
				plantSeeds = true;
			}else if((ctx.settings.get(12)>>16 & 0xF) <=3){//Catherby north allotment empty
				plantSeeds = true;
			}else if((ctx.settings.get(12) & 0xF) <=3){//Falador west allotment empty
				plantSeeds = true;
			}else if((ctx.settings.get(12)>>8 & 0xF) <=3){///Falador east allotment empty
				plantSeeds = true;
			}else plantSeeds = false;
			
			//If done growing..
			if((ctx.settings.get(12)>>24 & 0xF) >=9){//Catherby south allotment done
				System.out.println("setting harvest to true");
				harvest = true;
			}else if((ctx.settings.get(12)>>16 & 0xF) >=9){//Catherby north allotment done
				harvest = true;
			}else if((ctx.settings.get(12) & 0xF) >=9){//Falador west allotment done
				harvest = true;
			}else if((ctx.settings.get(12)>>8 & 0xF) >=9){//Falador east allotment done
				harvest = true;
			}else harvest = false;
		updateCounts();
		}


	   }
	   private void calcAntiPattern() {
			int number = rand.nextInt(0, 3);
			if(number == 1){
				antiPattern = true;
			}
			
		}
		private void updateCounts() {
			 level = ctx.skills.getLevel(Skills.FARMING);
			xpToLevel = ctx.skills.getExperienceAt(level+1) - ctx.skills.getExperience(Skills.FARMING);
			calcExpHr();
			
		}
		
		
private Font myFont = new Font("Consolas",Font.BOLD,15);
private Font myStateFont = new Font("Arial Black",Font.BOLD,14);
//private final Image paint = getImage("http://img546.imageshack.us/img546/8859/i52e.png");
private int mouseX;
private int mouseY;

private void setMouse(Graphics g) {
	g.setColor(Color.MAGENTA);
	g.drawLine(mouseX, mouseY - 800, mouseX, mouseY + 800);
	g.drawLine(mouseX - 800, mouseY, mouseX + 800, mouseY);
}

	@Override
	public void repaint(Graphics g) {
		double runtimeHold;
		String expHr = "";
		if(start){
		runtimeHold = runtime.getElapsed();
		runtimeHold = 3600000/runtimeHold;
		expPerHr = (int)runtimeHold * expGained;
		expHr = ""+ expPerHr;
		if(expHr.length()>3)
		expHr = expHr.substring(0, expHr.length() - 3);
		
		
		mouseX = (int) ctx.mouse.getLocation().getX();
		mouseY = (int) ctx.mouse.getLocation().getY();
		setMouse(g);
		//g.drawImage(paint, mouseX-950,mouseY-600, null);
		int seconds = (int)(runtime.getElapsed()/1000);
		int minutes = (int)(seconds/60);
		int hours = (int)(minutes/60);
		int secHold = (int)(secondsA.getElapsed()/1000);
		int minHold = (int)(minutesA.getElapsed()/60000);
		
		if(secHold>=60)
			secondsA = new Timer(0);
		if(minHold>=60)
			minutesA = new Timer(0);
		
		
		g.setFont(myStateFont);
		g.setColor(Color.green);
		g.drawString("State: "+state, 20, 130);
		g.setFont(myFont);
		g.setColor(Color.CYAN);
		g.drawString("Runtime: " +hours+":"+minHold +":" + secHold, 20, 150);
		g.drawString("XP left: "+xpToLevel + "XP", 20, 170);
		g.drawString("Current level: " + level, 20, 190);
		g.drawString("XP Gained: " + expGained + " XP : P/Hr(" +expHr+"K)" , 20, 210);
		//g.drawString("Location: " + location, 20, 230);
	
		//catherby south
		if((ctx.settings.get(12)>>24 & 0xF) >=10){//patch done growing
			g.drawString("Catherby south patch state: Ready to harvest", 20, 250);
		}else if((ctx.settings.get(12)>>24 & 0xF) <=3){
			g.drawString("Catherby south patch state: Ready for planting", 20, 250);
		}else if((ctx.settings.get(12)>>24 & 0xF) ==9){
			g.drawString("Catherby south patch state: Dead plants", 20, 250);
		}else g.drawString("Catherby south patch state: Still growing...", 20, 250);
		
		if((ctx.settings.get(24)>>11 & 0x1) ==1)//farmer - south garden
		g.drawString("Catherby farmer SA: Yes", 20, 270);
		else g.drawString("Catherby farmer SA: No", 20, 270);
		
		//catherby north
		if((ctx.settings.get(12)>>16 & 0xF) >=10){//patch done growing
			g.drawString("Catherby north patch state: Ready to harvest", 20, 290);
		}else if((ctx.settings.get(12)>>16 & 0xF) <=3){
			g.drawString("Catherby north patch state: Ready for planting", 20, 290);
		}else if((ctx.settings.get(12)>>16 & 0xF) ==9){
			g.drawString("Catherby north patch state: Dead plants", 20, 290);
		}else g.drawString("Catherby north patch state: Still growing...", 20, 290);
		
		if((ctx.settings.get(24)>>10 & 0x1) ==1)//farmer - north garden
			g.drawString("Catherby farmer NA: Yes", 20, 310);
			else g.drawString("Catherby farmer NA: No", 20, 310);
		
		//Falador west allotment
		g.setColor(Color.GREEN);
		if((ctx.settings.get(12) & 0xF) >=10){//patch done growing
			g.drawString("Falador west patch state: Ready to harvest", 20, 330);
		}else if((ctx.settings.get(12) & 0xF) <=3){
			g.drawString("Falador west patch state: Ready for planting", 20, 330);
		}else if((ctx.settings.get(12) & 0xF) ==9){
			g.drawString("Falador west patch state: Dead plants", 20, 330);
		}else g.drawString("Falador west patch state: Still growing...", 20, 330);
		
		if((ctx.settings.get(24)>>8 & 0x1) ==1)//farmer falador allotment WEST
		g.drawString("Falador farmer WA: Yes", 20, 350);
		else g.drawString("Falador farmer WA: No", 20, 350);
		
		//Falador east allotment
		if((ctx.settings.get(12)>>8 & 0xF) >=10){//patch done growing
			g.drawString("Falador east patch state: Ready to harvest", 20, 370);
		}else if((ctx.settings.get(12)>>8 & 0xF) <=3){
			g.drawString("Falador east patch state: Ready for planting", 20, 370);
		}else if((ctx.settings.get(12)>>8 & 0xF) ==9){
			g.drawString("Falador east patch state: Dead plants", 20, 370);
		}else g.drawString("Falador east patch state: Still growing...", 20, 370);
		
		if((ctx.settings.get(24)>>9 & 0x1) ==1)//farmer falador allotment EAST
			g.drawString("Falador farmer EA: Yes", 20, 390);
			else g.drawString("Falador farmer EA: No", 20, 390);
		//g.drawString("XP per hour: "+ expPerHr, 20, 230);
		}
		
	}

	private void calcExpHr() {
		int current = ctx.skills.getExperience(Skills.FARMING);
		int diff = current - initialExp;
		expGained = diff;
		
	}

}
