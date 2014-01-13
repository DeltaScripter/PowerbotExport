package kebbithunter;

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
import org.powerbot.script.lang.BasicNamedQuery;
import org.powerbot.script.lang.Filter;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.Skills;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Tile;
import org.powerbot.script.util.GeItem;
import org.powerbot.script.util.Random;
import org.powerbot.script.util.Timer;




@org.powerbot.script.Manifest(authors = { "Delta Scripter" }, name = "Delta Kebbit Hunter", 
description = "Hunts polar kebbits for fur, banks them, repeat. 200K GP/hr"
, version = 1, website = ""
)
public class KebBody extends PollingScript implements PaintListener{

	public KebBody(){
		getExecQueue(State.STOP).add(new Runnable() {
			@Override
			public void run() {
				getController().stop();
			}
			});
		getExecQueue(State.START).add(new Runnable() {
			@Override
			public void run() {
				//initiateGUI();
				//initialExp = ctx.skills.getExperience(25);
				runtime = new Timer(0);
				secondsA = new Timer(0);
				minutesA = new Timer(0);
				addNode(new hunt(ctx));
				addNode(new bank(ctx));
				addNode(new KebAntipattern(ctx));
				
			}
			
		});
	}
	public Tile pathToKebbit[] = {
			new Tile (2899,3517,0), new Tile(2893,3504,0),
			new Tile(2894,3489,0), new Tile(2884,3481,0),
			new Tile(2871,3481,0)
	};
	public Tile pathToBank[] = {
			new Tile (2886,3480,0), new Tile(2892,3487,0),
			new Tile(2892,3502,0), new Tile(2898,3516,0),
			new Tile(2892,3530,0)
	};
	private final List<KebNode> nodeList = Collections.synchronizedList(new ArrayList<KebNode>());
	public static String state;
	private boolean harvest = false;
	public static boolean antiPattern;
	private Random rand = new Random();
	private boolean start = false;
	public static Timer waitClickMap = new Timer(0);
	public static Timer wait = new Timer(0);//General use timer
	private Timer runtime;
	private Timer secondsA;
	private Timer minutesA;
	private int initialExp;
	KebMethod Method = new KebMethod(ctx);
	KebAntipattern anti = new KebAntipattern(ctx);
	public int set = 0;
	public boolean hunt = true;
	
	
	@Override
	public int poll() {
		
		//System.out.println("Harvest: " +harvest);
		
		while(ctx.widgets.get(1477,54).isVisible()){
			state = "Closing interface";
			ctx.widgets.get(1477,54).getChild(2).click();
		}
		while(ctx.widgets.get(1223,11).isVisible()){//task complete
			state = "Closing interface";
			ctx.widgets.get(1223,11).click();//close button
		}
		while(ctx.widgets.get(1401,35).isVisible()){//become a member!
			state = "Closing interface";
			ctx.widgets.get(1401,35).click();
		}
		while(ctx.widgets.get(1186,0).isVisible()){//after collecting limit of 10 chronicles..
			state = "Closing interface";
			Method.clickOnMap(ctx.players.local().getLocation().randomize(3, 5));
		}
		for(KebNode node: nodeList){
			if(node.activate()){
				node.execute();
			}
		}
		
		return 400;
	}
	
	   private void addNode(final KebNode...nodes) {
		   
	        for(KebNode node : nodes) {
	            if(!this.nodeList.contains(node)) {
	                this.nodeList.add(node);
	            }
	        }
	    }
	   class bank extends KebNode{

		public bank(MethodContext ctx) {
			super(ctx);
		}

		@Override
		public boolean activate() {
			return !hunt;
		}
		public Tile bankTile = new Tile(2892,3529,0);
		@Override
		public void execute() {
			Tile local = ctx.players.local().getLocation();
			if(!Method.inventoryContains(10117)){//kebbit fur
				hunt = true;
			}
			if(bankTile.distanceTo(local)<7){
				if(ctx.bank.isOpen()){
					ctx.bank.depositInventory();
				}else ctx.bank.open();
			}else if(!wait.isRunning()){
				state = "Walking to bank";
				if(!ctx.movement.findPath(bankTile.randomize(1, 2)).traverse()){
				ctx.movement.newTilePath(pathToBank).randomize(1, 2).traverse();
				}wait = new Timer(Random.nextInt(2300,2600));
			}
			
		}
		   
		   
	   }
	   class hunt extends KebNode {

		public hunt(MethodContext ctx) {
			super(ctx);
		}

		@Override
		public boolean activate() {
			return hunt;
		}
		@Override
		public void execute() {
			int dropItemIDs[] = {9986,526};
			
			calcAntiPattern();
			while(Method.inventoryContains(9986)||
					Method.inventoryContains(526)){
				state  ="Dropping items";
				for(int dropItems: dropItemIDs)
				Method.interactInventory(dropItems, "Drop", "Item");
			}
			
			if(ctx.camera.getPitch()>60){
				ctx.camera.setPitch(Random.nextInt(45, 50));
			}else if(ctx.camera.getPitch()<30)
				ctx.camera.setPitch(50);
			
			int backPackItems;
			backPackItems = Method.inventoryGetCount(10117);
			if(backPackItems >= Random.nextInt(22, 23)){
				hunt = false;
			}
			
			if (new Tile(2871,3481,0).distanceTo(ctx.players.local().getLocation())>25){//kebbit area
				ctx.bank.close();
				if(!wait.isRunning()){
				state = "Walking back to kebbit area";
				ctx.movement.newTilePath(pathToKebbit).traverse();//goes to kebbit area
				
				wait = new Timer(Random.nextInt(2300,2600));
				}
			}else
			if(ctx.settings.get(1218)==0){
				state = "Initialize the hunt.";
				set = 0;//reset the variable
				catchKebbit(new Tile(2872,3488,0),66473,"Inspect");//initial hole
			}
			if((ctx.settings.get(1218)>>27&0x3) ==2){
				catchKebbit(new Tile(2867,3483,0),66496, "Attack");//east snow pile
			}else if((ctx.settings.get(1218)>>27&0x3) ==3){
				catchKebbit(new Tile(2876,3479,0),66496,"Attack");//west snow pile
			}else
			if(set==1){
				//do set
				if(((ctx.settings.get(1218)>>6&0x1) ==1)){
					state = "Go to the lowest rock";
					checkRock(new Tile(2875,3482,0),66469);
				}else if(((ctx.settings.get(1218)>>3&0x1) ==1)){
					state = "Go to the center rock";
					checkRock(new Tile(2871,3483,0),66468);
				}
			}else if(set==2){
				//do set
				if(((ctx.settings.get(1218)>>6&0x1) ==1)){
					state = "Go to lowest rock";
					checkRock(new Tile(2875,3482,0),66469);
				}else
				if(((ctx.settings.get(1218)>>5&0x1) ==1)){
					state = "Go to upper-right center rock";
					checkRock(new Tile(2869,3486,0),66467);
				}
			}else if(set==3){
				//do set
				if(((ctx.settings.get(1218)>>16&0x1) ==1)){
					state = "Go to largest rock to the left";
					checkRock(new Tile(2873,3476,0),66472);
				}else
				if(((ctx.settings.get(1218)>>13&0x1) ==1)){
					state = "Go to left-most rock by center";
					checkRock(new Tile(2872,3479,0),66471);
				}else
				if(((ctx.settings.get(1218)>>10&0x1) ==1)){
					state = "Go to largest rock near top";
					checkRock(new Tile(2867,3481,0),66470);
				}
			}else if(set==4){
				//do set
				if(((ctx.settings.get(1218)>>13&0x1) ==1)){
					state = "Go to left-most rock by center";
					checkRock(new Tile(2872,3479,0),66471);
				}else
				if(((ctx.settings.get(1218)>>16&0x1) ==1)){
					state = "Go to largest rock to the left";
					checkRock(new Tile(2873,3476,0),66472);
				}else
				if(((ctx.settings.get(1218)>>10&0x1) ==1)){
					state = "Go to largest rock near top";
					checkRock(new Tile(2867,3481,0),66470);
				}
			}else if(set==5){
				//do set
				if(((ctx.settings.get(1218)>>16&0x1) ==1)){
					state = "Go to largest rock to the left";
					checkRock(new Tile(2873,3476,0),66472);
				}else
				if(((ctx.settings.get(1218)>>9&0x1) ==1)){
					state = "Go to largest rock near top";
					checkRock(new Tile(2867,3481,0),66470);
				}else
				if(((ctx.settings.get(1218)>>12&0x1) ==1)){
					state = "Go to left-most rock by center";
					checkRock(new Tile(2872,3479,0),66471);
				}
		    	}else if(set==6){
				//do set
				if(((ctx.settings.get(1218)>>13&0x1) ==1)){
					state = "Go to left-most rock by center";
					checkRock(new Tile(2872,3479,0),66471);
				}else
				if(((ctx.settings.get(1218)>>9&0x1) ==1)){
					state = "Go to largest rock near top";
					checkRock(new Tile(2867,3481,0),66470);
				}else
				if(((ctx.settings.get(1218)>>15&0x1) ==1)){
					state = "Go to largest rock to the left";
					checkRock(new Tile(2873,3476,0),66472);
				}
			}
			
			if((ctx.settings.get(1218)&0xF) ==12){
				set = 1;
			}else if((ctx.settings.get(1218)&0x3F) ==33 ||
					(ctx.settings.get(1218)&0x1) ==1){
				set = 2;
			}else if((ctx.settings.get(1218)&0x7FF) ==1028||
					(ctx.settings.get(1218)&0xFFF) ==2052){
				set = 3;
			}else if((ctx.settings.get(1218)&0x7FF) ==1056||
					(ctx.settings.get(1218)&0xFFF) ==2080){
				set = 4;
			}else if((ctx.settings.get(1218)&0x1FFF) ==4288){
				set = 5;
			}else if((ctx.settings.get(1218)&0xFFFF) ==32960){
				set = 6;
			}
		}

	
		
		private void catchKebbit(final Tile snowTile, final int id, String action) {
			Tile local =ctx.players.local().getLocation();
			
			BasicNamedQuery<GameObject> findPile =ctx.objects.select().select(new Filter<GameObject>() {
				public boolean accept(GameObject g) {
					return g.getLocation().distanceTo(snowTile)<2&& g.getId()==id;
				}
		         });
			while (ctx.players.local().getAnimation()!=-1){
				state = "Inspecting snow pile";
				wait = new Timer(500);
			}
			if(!wait.isRunning())
				for(GameObject pile: findPile){
					if(pile.getLocation().distanceTo(local)<9){
						if(pile.isOnScreen()){
						if(pile.interact(action)){
						wait = new Timer(Random.nextInt(1600, 1800));
						}else {
							ctx.camera.turnTo(pile.getLocation().randomize(2, 3));
						}
						}else ctx.camera.turnTo(pile);
					}else if(!waitClickMap.isRunning()){
						ctx.movement.stepTowards(pile.getLocation());
						waitClickMap = new Timer(Random.nextInt(1800, 2200));
					}
				}
		}

		private void checkRock(final Tile rockTile, final int id) {
			Tile local =ctx.players.local().getLocation();
			
			BasicNamedQuery<GameObject> findRock =ctx.objects.select().select(new Filter<GameObject>() {
				public boolean accept(GameObject g) {
				return g.getLocation().distanceTo(rockTile)<2 && g.getId()==id;
					
				}
		         });
			while (ctx.players.local().getAnimation()!=-1){
				state = "Inspecting rock";
				wait = new Timer(Random.nextInt(200, 400));
			}
			if(!wait.isRunning())
			for(GameObject rock: findRock){
				if(rock.getLocation().distanceTo(local)<15){
					if(rock.isOnScreen()){
					if(rock.interact("Inspect")){
					wait = new Timer(Random.nextInt(1700, 2500));
					}else {
						ctx.camera.turnTo(rock.getLocation().randomize(2, 3));
					}
					}else ctx.camera.turnTo(rock);
				}else if(!waitClickMap.isRunning()){
					ctx.movement.stepTowards(rock.getLocation().randomize(2,3));
					waitClickMap = new Timer(Random.nextInt(1800, 2800));
				}
			}
		}
		   
	   }
	   private boolean calcAntiPattern() {
			int number = rand.nextInt(0,5);
			if(number == 1){
				antiPattern = true;
				return true;
			}
			return false;
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
		
		runtimeHold = runtime.getElapsed();
		runtimeHold = 3600000/runtimeHold;
		//expPerHr = (int)runtimeHold * expGained;
		String expHr = "";
		//expHr = ""+ expPerHr;
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
		//if(Method.inventoryContains(10117))
		//g.drawString("Money made: "+Method.inventoryGetCount(10117)*1900 + "GP", 20, 170);
		g.drawString("Current pattern: " +set, 20, 170);
		
		
	}

	private void calcExpHr() {
		int current = ctx.skills.getExperience(25);
		int diff = current - initialExp;
		//expGained = diff;
		
	}

	
	

}
