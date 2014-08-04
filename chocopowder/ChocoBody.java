package chocopowder;

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
import org.powerbot.script.rt6.Component;
import org.powerbot.script.rt6.Hud.Window;
import org.powerbot.script.rt6.Item;

import chocopowder.ChocoData.choco;



@Script.Manifest(name = "DeltaChocolate", 
description = "Grinds chocolate bars into powder and banks, 250-300k/hour", properties = "topic = 1127240")

public class ChocoBody extends PollingScript<ClientContext> implements PaintListener{


	
	private final List<ChocoNode> nodeList = Collections.synchronizedList(new ArrayList<ChocoNode>());
	public static String state;
	public long runTime;
	public boolean start  =true;
	public int amount = 0;
	public boolean countTime = true;//for counting the choco powders made
	int bankAmount = 0;
	public static boolean antiPattern = false;
	
	ChocoMethod m = new ChocoMethod(ctx);
	@Override
	public void poll() {
		sleep(400);
		
		while(ctx.widgets.component(105,87).component(1).visible()){//if GE is open
			state = "Closing interface";
			ctx.widgets.component(105,87).component(1).click();
		}
		onStart();
		calcAntiPattern();
		for(ChocoNode node: nodeList){
			if(node.activate()){
				node.execute();
				
				 
			}
		}
		
	}
	private void onStart() {
	
		if(start){
			runTime = System.currentTimeMillis();
		 addNode(new Bank(ctx));
		  addNode(new Crush(ctx));
		  addNode(new ChocoAntipattern(ctx));
		  
		start = false;
		}
	}
	private void shutdown() {
		System.out.println("Now shutting down");
		stop();
	}
	  private boolean calcAntiPattern() {
			int number = Random.nextInt(0,20);
			if(number == 1){
				antiPattern = true;
				return true;
			}
			return false;
		}
		public void interactInventory(final int i, final String string, final String o) {
			ArrayList<String> actions = new ArrayList<String>();
			
			// if(!//timer.isRunning()){
			for(Item t : ctx.backpack.select().id(i).first()){
				//System.out.println(ctx.widgets.component(1477,122).component(0).boundingRect().getCenterY());
				if(ctx.hud.open(Window.BACKPACK) && ctx.widgets.component(1473,31).contains(
					t.component().centerPoint())){
					System.out.println("Hovering");
					t.hover();
					sleep(1200);
					String[] menuItems = ctx.menu.items();
					for(String opt: menuItems){
						if(!actions.contains(opt)){
							actions.add(opt);
						}
					}
					for(String text: actions){
						if(text.contains(string)){
							if(t.interact(string)){
							System.out.println("Using " + string + " with item: " + o);
							 sleep(Random.nextInt(2000, 2500));
							}
						}
					}
					 
				}else
				if(ctx.widgets.component(1473,31).boundingRect().getCenterY()>
				t.component().boundingRect().getCenterY()){
					ctx.mouse.move(ctx.widgets.component(1473, 31).centerPoint());
					ctx.mouse.scroll(false);
				}else {
					ctx.mouse.move(ctx.widgets.component(1473, 31).centerPoint());
					ctx.mouse.scroll(true);
					}
				}
			//}else System.out.println("//timer1 running");
		}
	   private void addNode(final ChocoNode...nodes) {
		   
	        for(ChocoNode node : nodes) {
	            if(!this.nodeList.contains(node)) {
	                this.nodeList.add(node);
	            }
	        }
	    }
		public boolean inventoryContains(int i) {
			if(!ctx.hud.opened(Window.BACKPACK)){
				ctx.hud.open(Window.BACKPACK);
				sleep(2000);
			}
				while (!ctx.backpack.select().id(i).first().isEmpty()) {
					return true;
				}
			
			return false;
		}
	   
		class Crush extends ChocoNode{

			public Crush(ClientContext ctx) {
				super(ctx);
			}

			@Override
			public boolean activate() {
				return inventoryContains(choco.CHOCOLATEBAR.getID());
			}

			@Override
			public void execute() {
				state = "Crushing chocolate";
				countTime = true;
				
				if(ctx.bank.close() && ctx.players.local().animation()!=1989){
					crushChocolateBars();
				}
				
			}

			private void crushChocolateBars() {
				Component optionMenu = ctx.widgets.component(1370,1);
				
				if(optionMenu.component().visible()){
					ctx.widgets.widget(1370).component(20).interact("");
					//ctx.mouse.click(ctx.widgets.widget(1370).component(20).centerPoint(),true);
					sleep(Random.nextInt(100,140));
				}else interactInventory(choco.CHOCOLATEBAR.getID(),"Powder","Chocolate Bar");
				
			}
			
		}
		public void sleep(int millis){
			try {
				Thread.sleep(Math.max(5, (int) (millis * Random.nextDouble(0.85, 1.5))));
			} catch (InterruptedException ignored) {
			}
		}
	   class Bank extends ChocoNode{

		public Bank(ClientContext ctx) {
			super(ctx);
		}

		@Override
		public boolean activate() {
			return ctx.players.local().animation()!=1989&&!inventoryContains(choco.CHOCOLATEBAR.getID());
		}

		
		public void execute() {
				state = "Banking";
		     if(bankContains(choco.CHOCOLATEBAR.getID())){
		    	 ctx.bank.withdraw(choco.CHOCOLATEBAR.getID(), 28);
		     }else shutdown();
			
		}

	
		private boolean bankContains(int id) {
			ArrayList<Integer> bankItems = new ArrayList<Integer>();
			
			while(!ctx.bank.opened()){
				ctx.bank.open();
			}
			while(inventoryContains(choco.CHOCOLATEPOWDER.getID())){
				for(Item i : ctx.bank.select()){
					if(i.id()==1973){
					bankAmount= i.stackSize();
					}
				}
				int i = m.inventoryGetCount(choco.CHOCOLATEPOWDER.getID());
				ctx.bank.depositInventory();
				
				if(countTime){
				amount+=i;
				countTime = false;
				}
			}
			for(Item item : ctx.bank.select()){
				if(!bankItems.contains(item.id())){
					bankItems.add(item.id());
				}
			}
			return bankItems.contains(id);
		}
		   
		   
		   
	   }
	   private void setMouse(Graphics g) {
			g.setColor(Color.MAGENTA);
			g.drawLine(mouseX, mouseY - 800, mouseX, mouseY + 800);
			g.drawLine(mouseX - 800, mouseY, mouseX + 800, mouseY);
		}

	   private int mouseX;
	   private int mouseY;
	   double phour;
	   double moneyphour;
	@Override
	public void repaint(Graphics g) {
		long time = runTime - System.currentTimeMillis();
		
		//phour is used for finding time left for some reason lolcats
		
		//for finding time left
	    phour = ((time/1000));
	    phour = phour/amount;
	    phour = phour*bankAmount;
	    phour = phour/60;

	    //for finding money per hour
	    moneyphour = (time/1000);
	    moneyphour = ((amount*66)/moneyphour);
	    moneyphour = moneyphour*3600;
		
		mouseX = (int) ctx.mouse.getLocation().getX();
		mouseY = (int) ctx.mouse.getLocation().getY();
		setMouse(g);
		g.setColor(Color.green);
		g.drawString("Runtime:"+m.format(time),20 ,130 );
		g.drawString("State: "+state, 20, 150);
		g.drawString("Chocolate powder made: "+amount, 20, 170);
		
		if(bankAmount>0)
		g.drawString("Time until finished: "+(int)phour*-1 + " Minutes", 20, 190);
		g.drawString("Bank amount: "+bankAmount, 20, 210);
	//	g.drawString("Money made: "+amount*66 + " GP", 20, 230);
		//g.drawString("Money per hour: "+ ((int)moneyphour*-1)+ " GP", 20, 250);
		
		
	}
	
}
