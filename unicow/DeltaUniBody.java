package unicow;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.powerbot.event.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.methods.Hud.Window;
import org.powerbot.script.util.Timer;
import unicow.UniData.items;




@org.powerbot.script.Manifest(authors = { "Delta Scripter" }, name = "Delta Unicows", 
description = "In development", 
website = "", version = 1,hidden  =true)
public class DeltaUniBody extends PollingScript implements PaintListener{

	public DeltaUniBody(){
		getExecQueue(State.START).add(new Runnable() {
			@Override
			public void run() {
				addNode(new UniMain(ctx));
				addNode(new UniBank(ctx));
				runtime = new Timer(0);
				secondsA = new Timer(0);
				minutesA = new Timer(0);
			}
		});
	}
	
	private final List<UniNode> nodeList = Collections.synchronizedList(new ArrayList<UniNode>());
	static String state;
    public static boolean bank = false;
    public boolean init = false;
	public Timer wait = new Timer(0);
	UniMethod Method = new UniMethod(ctx);
	ArrayList<Integer> sheepID = new ArrayList<Integer>();
	public static boolean e = false;
	//profit an hour
	int tempNum = 0;
	Timer hornCounter = new Timer(0);
	
	private Timer runtime;
	private Timer secondsA;
	private Timer minutesA;
	@Override
	public int poll() {
		
		while(ctx.widgets.get(1477,54).isVisible()){
			state = "Closing interface";
			ctx.widgets.get(1477,54).getChild(2).click();
		}
		if(e){
			shutdown();
		}
		if(!init){
			if(ctx.bank.close())
			if(ctx.hud.view(Window.WORN_EQUIPMENT)){
				if(Method.equipItemIsNotNull(items.RINGOFDUNGEONEERING.getID())){
					System.out.println("Found Dungeoneering ring");
					UniBank.dungeoneeringRing = true;
				}
				if(Method.equipItemIsNotNull(items.ARDOUGNECAPE1.getID())){
					System.out.println("Found Ardougne cloak");
					UniMain.ardougneCloak = true;
				}
				init = true;
			}
		
		}
		if(!hornCounter.isRunning())
		//System.out.println("Counter: " + hornCounter.getElapsed());
		for(UniNode node: nodeList){
			if(node.activate()){
				node.execute();
			}
		}
		
		return 400;
	}
	private void shutdown() {
		System.out.println("Now shutting down");
		getController().stop();
	}

	   private void addNode(final UniNode...nodes) {
		   
	        for(UniNode node : nodes) {
	            if(!this.nodeList.contains(node)) {
	                this.nodeList.add(node);
	            }
	        }
	    }
	
		   
	   boolean once = false;
		double profit =0;
private Font myFont = new Font("Consolas",Font.BOLD,14);
	@Override
	public void repaint(Graphics g) {
		int seconds = (int)(runtime.getElapsed()/1000);
		int minutes = (int)(seconds/60);
		int hours = (int)(minutes/60);
		int secHold = (int)(secondsA.getElapsed()/1000);
		int minHold = (int)(minutesA.getElapsed()/60000);
		
		if(secHold>=60)
			secondsA = new Timer(0);
		if(minHold>=60)
			minutesA = new Timer(0);
	
	    double time;
	    if(!ctx.bank.isOpen())
	    	once = false;
	    if(ctx.bank.isOpen() && !once){
	    	int hornCount = Method.inventoryGetCount(items.HORN.getID()) + Method.inventoryStackSize(items.HORN.getID()+1);
	    	if(!hornCounter.isRunning()){
	    		    time = ((hornCounter.getElapsed())/1000);
	    		    time = 3600/time;
	    		    profit = time*(hornCount*2720);
	    		    System.out.println("Profit per hour: "+ profit);
	    		    hornCounter = new Timer(0);
	    		    once = true;
	    }
	 
	    }
		
		g.setFont(myFont);
		g.setColor(Color.GREEN);
		g.drawString("State: "+state, 20, 130);
		g.drawString("Health%: "+ctx.players.local().getHealthPercent(), 20, 150);
		if(profit>0)
		g.drawString("Money per hour: "+(int)profit + "GP", 20, 170);
		else g.drawString("Money per hour: Waiting..", 20, 170);
		g.setColor(Color.CYAN);
		g.drawString("Runtime: " +hours+":"+minHold +":" + secHold, 20, 110);
	}
}
