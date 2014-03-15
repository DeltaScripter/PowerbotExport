package deltaflipper;

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



@org.powerbot.script.Manifest(name = "Delta Grand Exchange Flipper", 
description = "", 
topic =0)
public class FlipperBody extends PollingScript implements PaintListener{

	public FlipperBody(){
		getExecQueue(State.START).add(new Runnable() {
			@Override
			public void run() {
				 addNode(new BuyItem(ctx));
				 addNode(new SellItem(ctx));
				 addNode(new CollectItem(ctx));
				 addNode(new Monitor(ctx));
				   
			}
		});
	}
	
	private final List<FlipperNode> nodeList = Collections.synchronizedList(new ArrayList<FlipperNode>());
	public static String state;
	
	public int slot = 28;
	public int item1ID = 563;
	public int sellAmount = 297;
	public int buyAmount = 296;
	public int quantity = 5000;
	public String name = "Law rune";
	public boolean buyItem = false;
	
	FlipperExchange flip = new FlipperExchange(ctx);
	@Override
	public int poll() {
		
		for(FlipperNode node: nodeList){
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
	   private void addNode(final FlipperNode...nodes) {
		   
	        for(FlipperNode node : nodes) {
	            if(!this.nodeList.contains(node)) {
	                this.nodeList.add(node);
	            }
	        }
	    }
	   class Monitor extends FlipperNode{

		public Monitor(MethodContext ctx) {
			super(ctx);
		}

		@Override
		public boolean activate() {
			return true;
		}

		@Override
		public void execute() {
			if(ctx.widgets.get(105,slot).isVisible())
				if(!ctx.widgets.get(105,slot).getChild(4).isVisible()&&
						!flip.inventoryContains(item1ID)){
					System.out.println("Setting to buy more items");
					buyItem = true;
				}else {
					System.out.println("Setting buy to false");
					buyItem = false;
				}
			
		}
		   
	   }
	   class SellItem extends FlipperNode{

		public SellItem(MethodContext ctx) {
			super(ctx);
		}

		@Override
		public boolean activate() {
			return flip.inventoryContains(item1ID) && !buyItem;
		}

		@Override
		public void execute() {
			state = "Selling items";
			if(flip.OpenGeScreen()){
				if(ctx.widgets.get(105,slot).getChild(3).isVisible()){
					state = "Finished selling in slot "+ slot;
					
				}else flip.makeSellOffer(slot, name, quantity, sellAmount, item1ID);
			}
		}
		   
	   }
	   class CollectItem extends FlipperNode{

		public CollectItem(MethodContext ctx) {
			super(ctx);
		}

		@Override
		public boolean activate() {
			return !buyItem;
		}

		@Override
		public void execute() {
			
			if(flip.OpenGeScreen())
			if(ctx.widgets.get(105,slot).getChild(4).getTextColor()==4162078){
				
				state = "Collecting item in slot : "+ slot;
				flip.collectItem("Logs", slot);
			}else if(!flip.inventoryContains(item1ID)){
				System.out.println("Setting buy to true");
				buyItem = true;
			}
			
		}
		   
	   }
	   class BuyItem extends FlipperNode{

		public BuyItem(MethodContext ctx) {
			super(ctx);
		}

		@Override
		public boolean activate() {
			return !flip.inventoryContains(item1ID) &&buyItem;
		}

		@Override
		public void execute() {
			state = "Buying items";
			if(flip.OpenGeScreen()){
				if(ctx.widgets.get(105,slot).getChild(3).isVisible()||
						ctx.widgets.get(105,77).isVisible()){
					
					System.out.println("Finished buying in slot "+ slot);
					buyItem = false;
				}else flip.makeBuyOffer(28, name,quantity, buyAmount);
			}
			
		}
		   
	   }
private Font myFont = new Font("Consolas",Font.BOLD,14);
	@Override
	public void repaint(Graphics g) {
		g.setFont(myFont);
		g.setColor(Color.green);
		g.drawString("State: "+state, 20, 130);
		g.drawString("BuyItem: "+buyItem, 20, 150);
	}
}
