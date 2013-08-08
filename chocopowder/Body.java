package chocopowder;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.powerbot.event.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.Hud.Window;
import org.powerbot.script.wrappers.Item;
import org.powerbot.script.wrappers.Widget;

import chocopowder.Data.choco;

import quests.Node;


@org.powerbot.script.Manifest(authors = { "Delta Scripter" }, name = "DeltaChocolate", 
description = "Converts chocolate bars into chocolate powder for profit!", website = "", version = .5)
public class Body extends PollingScript implements PaintListener{

	public Body(){
		getExecQueue(State.START).add(new Runnable() {
			@Override
			public void run() {
				   addNode(new Bank(ctx));
				   addNode(new Crush(ctx));
			}
		});
	}
	
	private final List<Node> nodeList = Collections.synchronizedList(new ArrayList<Node>());
	private String state;
	@Override
	public int poll() {
		
		for(Node node: nodeList){
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
	public void interactInventory(final int i, final String string, final String o) {
		ArrayList<String> actions = new ArrayList<String>();
		for(Item t : ctx.backpack.select().id(i).first()){
			//System.out.println(ctx.widgets.get(1477,122).getChild(0).getBoundingRect().getCenterY());
			if(ctx.hud.view(Window.BACKPACK) && ctx.widgets.get(1473,7).contains(
				t.getComponent().getCenterPoint())){
				System.out.println("Hovering");
				t.hover();
				ctx.game.sleep(1200);
				String[] menuItems = ctx.menu.getItems();
				for(String opt: menuItems){
					if(!actions.contains(opt)){
						actions.add(opt);
					}
				}
				for(String text: actions){
					if(text.contains(string)){
						if(t.interact(string)){
						System.out.println("Using " + string + " with item: " + o);
						ctx.game.sleep(2000);
						}
					}
				}
				 
			}else
			if(ctx.widgets.get(1473,7).getBoundingRect().getCenterY()>
			t.getComponent().getBoundingRect().getCenterY()){
				ctx.mouse.move(ctx.widgets.get(1473, 7).getAbsoluteLocation());
				ctx.mouse.scroll(false);
			}else {
				ctx.mouse.move(ctx.widgets.get(1473, 7).getAbsoluteLocation());
				ctx.mouse.scroll(true);
				}
			}
	}
	   private void addNode(final Node...nodes) {
		   
	        for(Node node : nodes) {
	            if(!this.nodeList.contains(node)) {
	                this.nodeList.add(node);
	            }
	        }
	    }
		public boolean inventoryContains(int i) {
			if(!ctx.hud.isVisible(Window.BACKPACK)){
				ctx.hud.view(Window.BACKPACK);
				sleep(2000);
			}
				while (!ctx.backpack.select().id(i).first().isEmpty()) {
					return true;
				}
			
			return false;
		}
	   
		class Crush extends Node{

			public Crush(MethodContext ctx) {
				super(ctx);
			}

			@Override
			public boolean activate() {
				return inventoryContains(choco.CHOCOLATEBAR.getID());
			}

			@Override
			public void execute() {
				state = "Crushing chocolate";
				if(ctx.bank.close() && ctx.players.local().getAnimation()!=1989){
					crushChocolateBars();
				}
				
			}

			private void crushChocolateBars() {
				Widget optionMenu = ctx.widgets.get(1370);
				
				if(optionMenu.getComponent(0).isVisible()){
					ctx.mouse.click(optionMenu.getComponent(38).getCenterPoint(),true);
					ctx.game.sleep(1000,1400);
				}else interactInventory(choco.CHOCOLATEBAR.getID(),"Powder","Chocolate Bar");
				
			}
			
		}
		
	   class Bank extends Node{

		public Bank(MethodContext ctx) {
			super(ctx);
		}

		@Override
		public boolean activate() {
			return ctx.players.local().getAnimation()!=1989&&!inventoryContains(choco.CHOCOLATEBAR.getID());
		}

		
		public void execute() {
				state = "Banking";
		     if(bankContains(choco.CHOCOLATEBAR.getID())){
		    	 ctx.bank.withdraw(choco.CHOCOLATEBAR.getID(), 28);
		     }else shutdown();
			
		}


		private boolean bankContains(int id) {
			ArrayList<Integer> bankItems = new ArrayList<Integer>();
			
			while(!ctx.bank.isOpen()){
				ctx.bank.open();
			}
			while(inventoryContains(choco.CHOCOLATEPOWDER.getID())){
				ctx.bank.deposit(choco.CHOCOLATEPOWDER.getID(), 28);
			}
			for(Item item : ctx.bank.select()){
				if(!bankItems.contains(item.getId())){
					bankItems.add(item.getId());
				}
			}
			return bankItems.contains(id);
		}
		   
		   
		   
	   }
private Font myFont = new Font("Consolas",Font.BOLD,14);
	@Override
	public void repaint(Graphics g) {
		g.setFont(myFont);
		g.setColor(Color.green);
		g.drawString("State: "+state, 20, 130);
	}
}
