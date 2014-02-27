package tarcollector;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.powerbot.event.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Player;
import org.powerbot.script.wrappers.Tile;
import org.powerbot.script.util.Random;
import org.powerbot.script.util.Timer;

import tarcollector.TarData.gItems;




@org.powerbot.script.Manifest(authors = { "Delta Scripter" }, name = "Delta Tar Collector", 
description = "Collects tar in the swamp of Lumbridge, for money",hidden = true
, version = 1, website = ""
)
public class TarBody extends PollingScript implements PaintListener{

	public TarBody(){
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
				//initiateGUI();
				//initialExp = ctx.skills.getExperience(25);
				runtime = new Timer(0);
				secondsA = new Timer(0);
				minutesA = new Timer(0);
				addNode(new hunt(ctx));
				addNode(new TarAntipattern(ctx));
				
			}
			
		});
	}

	public Tile pathToBank[] = {
			new Tile (2886,3480,0), new Tile(2892,3487,0),
			new Tile(2892,3502,0), new Tile(2898,3516,0),
			new Tile(2892,3530,0)
	};
	private final List<TarNode> nodeList = Collections.synchronizedList(new ArrayList<TarNode>());
	public static String state;
	private boolean harvest = false;
	public static boolean antiPattern;
	private Random rand = new Random();
	private boolean start = false;
	public static Timer waitClickMap = new Timer(0);
	public static Timer waitGrabTar = new Timer(0);//General use timer
	private Timer runtime;
	private Timer secondsA;
	private Timer minutesA;
	private int initialExp;
	TarMethod Method = new TarMethod(ctx);
	TarAntipattern anti = new TarAntipattern(ctx);
	
	
	@Override
	public int poll() {
		
		calcAntiPattern();
		while(Method.inventoryContains("Logs")){
			state = "Dropping logs";
			Method.interactInventory("Logs", "Drop", "Logs");
		}
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
		while(ctx.widgets.get(1186).isValid()){//after collecting limit of 10 chronicles..
			state = "Closing chronicle interface";
			Method.clickOnMap(ctx.players.local().getLocation().randomize(3, 5));
		}
		for(TarNode node: nodeList){
			if(node.activate()){
				node.execute();
			}
		}
		
		return 400;
	}
	
	   private void addNode(final TarNode...nodes) {
		   
	        for(TarNode node : nodes) {
	            if(!this.nodeList.contains(node)) {
	                this.nodeList.add(node);
	            }
	        }
	    }
	  
	   class hunt extends TarNode {

		public hunt(MethodContext ctx) {
			super(ctx);
		}

		@Override
		public boolean activate() {
			return true;
		}
		Tile baseTile = new Tile(3170,3159,0);
		@Override
		public void execute() {
			if(ctx.camera.getPitch()>Random.nextInt(60, 65)){
				ctx.camera.setPitch(50);
			}else if(ctx.camera.getPitch()<40){
				ctx.camera.setPitch(55);
			}
			Player local = ctx.players.local();
			while(!local.isIdle() && waitGrabTar.isRunning()){
				state = "Player is busy";
			}
			if(baseTile.distanceTo(local.getLocation())>30){
				state = "Too far away";
				ctx.movement.findPath(baseTile.randomize(2, 3)).traverse();
			}else if (Method.gItemIsNotNull(gItems.SWAMPTAR.getID())
					&& Method.getGItem(gItems.SWAMPTAR.getID()).getLocation().distanceTo(local.getLocation())<16){
				state  ="Grabbing the tar";
				if(!waitGrabTar.isRunning()){
				Method.interactG(gItems.SWAMPTAR.getID(), "Take", "Tar");
				}
			}else if(Method.gItemIsNotNull(gItems.SWAMPTAR.getID())){
				state = "Walking to tar";
				if(!waitClickMap.isRunning()){
				Method.clickOnMap(Method.getGItem(gItems.SWAMPTAR.getID()).getLocation().randomize(2, 4));
				waitClickMap = new Timer(Random.nextInt(1800, 2400));
				}
			}else state = "It's null, waiting.";
			
		}

		   
	   }
	   private boolean calcAntiPattern() {
			int number = rand.nextInt(0,8);
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
		if(Method.inventoryContains(gItems.SWAMPTAR.getID()))
		g.drawString("Money made: "+Method.inventoryStackSize(gItems.SWAMPTAR.getID())*303 + "GP", 20, 170);
		//g.drawString("Current pattern: " +set, 20, 170);
		
		
	}

	private void calcExpHr() {
		int current = ctx.skills.getExperience(25);
		int diff = current - initialExp;
		//expGained = diff;
		
	}

	
	

}
