package deltapie;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.powerbot.event.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.methods.MethodContext;




@org.powerbot.script.Manifest(authors = { "Delta Scripter" }, name = "DeltaPie", 
description = "In development", 
website = "", version = 1,topic =0)
public class DeltaPie extends PollingScript implements PaintListener{

	public DeltaPie(){
		getExecQueue(State.START).add(new Runnable() {
			@Override
			public void run() {
				   addNode(new Bank(ctx));
				   addNode(new Make(ctx));
			}
		});
	}
	
	private final List<PieNode> nodeList = Collections.synchronizedList(new ArrayList<PieNode>());
	private String state;
	private int countIngredient1 = 0;
	PieMethod m = new PieMethod(ctx);
	public boolean bank = false;
	
	 public int pieDish = 2313;//2315
	   public int berry = 1953;//1951
	
	@Override
	public int poll() {
		if(ctx.bank.isOpen()){
			
		}
		for(PieNode node: nodeList){
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
	
	   private void addNode(final PieNode...nodes) {
		   
	        for(PieNode node : nodes) {
	            if(!this.nodeList.contains(node)) {
	                this.nodeList.add(node);
	            }
	        }
	    }

class Make extends PieNode{

	public Make(MethodContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return !bank;
	}

	@Override
	public void execute() {
		if(ctx.bank.close()){
			if(!m.inventoryContains(berry)){
				bank = true;
			}
			
			if(ctx.widgets.get(1370,20).isVisible()){
				ctx.widgets.get(1370,20).click();
			}else if(!ctx.widgets.get(1251,13).isVisible()){
				state = "Combining items";
				m.combineItems(pieDish, berry);
			}else state = "Making";
		}
		
	}
	
	
}
		   class Bank extends PieNode{
			  
			public Bank(MethodContext ctx) {
				super(ctx);
			}

			@Override
			public boolean activate() {
				return bank;
			}

			@Override
			public void execute() {
				if(ctx.bank.open()){
					if(m.inventoryContains(2315)){
						ctx.bank.depositInventory();
					}else
					
					if(m.inventoryGetCount(pieDish)==14){
						if(m.inventoryGetCount(berry)==14){
							bank = false;
						}else takeOutQuantity(berry,14);
					}else takeOutQuantity(pieDish,14);
				}
				
			}

			private void takeOutQuantity(int item, int amount) {
				state = "Taking out items";
				if(m.inventoryGetCount(item)>amount){
					System.out.println("deposit");
					ctx.bank.deposit(item, amount-m.inventoryGetCount(item));
				}else {
					System.out.println("withdraw: "+(amount-m.inventoryGetCount(item)));
					ctx.bank.withdraw(item, (amount-m.inventoryGetCount(item)));
				}
				
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
