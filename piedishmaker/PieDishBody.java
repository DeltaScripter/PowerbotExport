package piedishmaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Random;
import org.powerbot.script.Script;
import org.powerbot.script.rt6.Action;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Item;

import piedishmaker.PieDishData.items;


@Script.Manifest(name = "Delta Piedish Maker", 
description = "Eats pie for money, 500k/hr", properties = "topic = 1178018")

public class PieDishBody extends PollingScript<ClientContext> implements PaintListener{



	
	private final List<PieDishNode> nodeList = Collections.synchronizedList(new ArrayList<PieDishNode>());
	
	
	public static String state;
	public double version = 0.1;
	

	
	
	public boolean done = false;
	public static boolean antiPattern;
	private Random rand = new Random();
	
	public long runTime;
	public int slot;
	public int slot2;
	public int amount = 0;
	public int bankAmount = 0;
	public boolean okToAdd = false;
	PieDishMethod Method = new PieDishMethod(ctx);
	PieDishAntipattern anti = new PieDishAntipattern(ctx);
	public boolean halfApplePieSlot = false;
	public boolean applePieSlot = false;

	

	@Override
	public void poll() {
		
		onStart();
		Method.sleep(200);
	
		while(ctx.widgets.component(105,87).component(1).visible()){//if GE is open
			state = "Closing interface";
			ctx.widgets.component(105,87).component(1).click();
		}
		while(ctx.widgets.component(669,1).visible()){//tut stuff
			state = "Closing interface";
			ctx.widgets.component(669,1).click();
		}
		while(ctx.widgets.component(1477,54).visible()){
			state = "Closing interface";
			ctx.widgets.component(1477,54).component(2).click();
		}
		while(ctx.widgets.component(1223,11).visible()){//task complete
			state = "Closing interface";
			ctx.widgets.component(1223,11).click();//close button
		}
		while(ctx.widgets.component(1401,31).visible()){//become a member!
			state = "Closing interface";
			ctx.widgets.component(1401,31).click();
		}
		while(ctx.widgets.component(1186,0).visible()){//after collecting limit of 10 chronicles..
			state = "Closing interface";
			ctx.movement.step(ctx.players.local().tile());
		}
		for(PieDishNode node: nodeList){
			if(node.activate()){
				node.execute();
			}
		}
		
	}
	
	   private void onStart() {
		   if(!done){
		   runTime = System.currentTimeMillis();
			   
			for(Action g: ctx.combatBar.actions()){
				if(g.id()==items.APPLEPIE.getID()){//bear meati
					slot = g.slot();
					applePieSlot = true;
					//System.out.println("Setting to drop via actionbar, slot: " + slot);
				}else if(g.id()==items.HALFAPPLEPIE.getID()){
					slot2 = g.slot();
					halfApplePieSlot = true;
				}
			}
		   
			addNode(new main(ctx));
			addNode(new bank(ctx));
			addNode(new PieDishAntipattern(ctx));
			
			done = true;
		   }
	}

	private void addNode(final PieDishNode...nodes) {
		   
	        for(PieDishNode node : nodes) {
	            if(!this.nodeList.contains(node)) {
	                this.nodeList.add(node);
	            }
	        }
	    }
	   class bank extends PieDishNode{

		public bank(ClientContext ctx) {
			super(ctx);
		}

		@Override
		public boolean activate() {
			return !Method.inventoryContains(items.APPLEPIE.getID())&&
					!Method.inventoryContains(items.HALFAPPLEPIE.getID());
		}
		@Override
		public void execute() {
		  state = "Banking";
			if(ctx.bank.opened()){
				if(okToAdd){//boolean for adding only once
					amount += Method.inventoryGetCount(items.DISH.getID());//counts piedish's in inventory
					for(Item i : ctx.bank.select()){//counts the current amount of apple pie in bank
						if(i.id()==items.APPLEPIE.getID()){
						bankAmount= i.stackSize();
						break;
						}
					}
					okToAdd = false;
				}
				ctx.bank.depositInventory();
				ctx.bank.withdraw(items.APPLEPIE.getID(), 28);
			}else ctx.bank.open();
		
		}
		   
		   
	   }
	   class main extends PieDishNode{

		public main(ClientContext ctx) {
			super(ctx);
		}

		@Override
		public boolean activate() {
			return Method.inventoryContains(items.APPLEPIE.getID())||
					Method.inventoryContains(items.HALFAPPLEPIE.getID());
		}
		@Override
		public void execute() {
			okToAdd = true;
			calcAntiPattern();
		  ctx.bank.close();
				    state ="Eating pie";
				    if(Method.inventoryContains(items.APPLEPIE.getID())){
				    	eatPieType(applePieSlot, slot,items.APPLEPIE.getID());
				    }else eatPieType(halfApplePieSlot, slot2,items.HALFAPPLEPIE.getID());
			
		
		}

		private void eatPieType(boolean applePieSlo, int slo, int id) {
			  if(applePieSlo){
			         Method.useAction(slo);
				}else{
				   Method.interactInventory(id, "Eat", "Apple pie");
				  }
			
		}
		   
		   
	   }
	  
	   private boolean calcAntiPattern() {
			int number = rand.nextInt(0,24);
			if(number == 1){
				antiPattern = true;
				return true;
			}
			return false;
		}
		private Image getImage(String url) {
			
				return this.downloadImage(url);
			
		}
private Font myFont = new Font("Consolas",Font.BOLD,15);
private Font myStateFont = new Font("Arial Black",Font.BOLD,14);


private int mouseX;
private int mouseY;

private void setMouse(Graphics g) {
	g.setColor(Color.MAGENTA);
	g.drawLine(mouseX, mouseY - 800, mouseX, mouseY + 800);
	g.drawLine(mouseX - 800, mouseY, mouseX + 800, mouseY);
}

	double phour;
	double moneyphour;
	public void repaint(Graphics g) {
		long time = runTime - System.currentTimeMillis();
		//for finding time left
	    phour = ((time/1000));
	    phour = phour/amount;
	    phour = phour*bankAmount;
	    phour = phour/60;
		
		String expHr = "";
		if(expHr.length()>3)
		expHr = expHr.substring(0, expHr.length() - 3);
		
		mouseX = (int) ctx.mouse.getLocation().getX();
		mouseY = (int) ctx.mouse.getLocation().getY();
		setMouse(g);
		g.setFont(myStateFont);
		g.setFont(myFont);
		
		
		g.setColor(Color.GREEN);
		g.drawString("Runtime: " + Method.format(time), 30,100);
		g.drawString("Version: " +version, 30,120);
		g.drawString("State: "+state, 30,140);
		g.drawString("Time until finished: " +(int)phour*-1 + " Minutes", 30,160);
		g.drawString("Bank amount: "+bankAmount, 30, 180);
		g.drawString("Amount made: "+amount, 30, 200);
		
		
	}

	private void calcExpHr() {
		int current = ctx.skills.experience(25);
		
	}

	
	

}
