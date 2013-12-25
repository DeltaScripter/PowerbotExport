package bonebury;

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



@org.powerbot.script.Manifest(authors = { "Delta Scripter" }, name = "Delta BoneBury", 
description = "Buries bones from your bank! start script with the type of bones you want to use in your inventory.", 
website = "", version = 1)
public class BoneBody extends PollingScript implements PaintListener{

	public BoneBody(){
		getExecQueue(State.START).add(new Runnable() {
			@Override
			public void run() {
				   addNode(new Bank(ctx));
				   addNode(new Bury(ctx));
				   
				   if(inventoryContains("Bones")){
					   boneType = 526;
					   
				   }else
				   if(inventoryContains("Airut bones")){
					   boneType = 30209;
					   
				   }
				   if(inventoryContains("Babydragon bones")){
					   boneType = 534;
					   
				   }
				   if(inventoryContains("Bat bones")){
					   boneType = 530;
					   
				   }
				   if(inventoryContains("Big bones")){
					   boneType = 532;
					   
				   }
				   if(inventoryContains("Burnt bones")){
					   boneType = 528;
					   
				   }
				   if(inventoryContains("Fayrg bones")){
					   boneType = 4830;
					   
				   }
				   if(inventoryContains("Jogre bones")){
					   boneType = 3125;
					   
				   }
				   if(inventoryContains("Monkey bones")){
					   boneType = 3183;
					   
				   }
				   if(inventoryContains("Ourg bones")){
					   boneType = 14793;
					   
				   }
				   if(inventoryContains("Raurg bones")){
					   boneType = 4832;
					   
				   }
				   if(inventoryContains("Shaikahan bones")){
					   boneType = 3123;
					   
				   }
				   if(inventoryContains("Wolf bones")){
					   boneType = 2859;
					   
				   }
				   if(inventoryContains("Wyvern bones")){
					   boneType = 6812;
					   
				   }
				   if(inventoryContains("Zogre bones")){
					   boneType = 4812;
					   
				   }
				   for(Item t : ctx.backpack.select().id(boneType).first()){
					   boneName = t.getName();
				   }
			}
		});
	}
	
	private final List<BoneNode> nodeList = Collections.synchronizedList(new ArrayList<BoneNode>());
	private String state;
	public int boneType;
	public String boneName;
	
	@Override
	public int poll() {
		
		for(BoneNode node: nodeList){
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
	public boolean inventoryContains(String name) {
		if(!ctx.hud.isVisible(Window.BACKPACK)){
			ctx.hud.view(Window.BACKPACK);
			sleep(2000);
		}
			while (!ctx.backpack.select().name(name).first().isEmpty()) {
				return true;
			}
		
		return false;
	}
	public void interactInventory(final int i, final String string, final String o) {
		ArrayList<String> actions = new ArrayList<String>();
		for(Item t : ctx.backpack.select().id(i).first()){
			if(ctx.hud.view(Window.BACKPACK) && ctx.widgets.get(1473,7).contains(
				t.getComponent().getCenterPoint())){
				t.interact(string);
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
	   private void addNode(final BoneNode...nodes) {
		   
	        for(BoneNode node : nodes) {
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
	   
		class Bury extends BoneNode{

			public Bury(MethodContext ctx) {
				super(ctx);
			}

			@Override
			public boolean activate() {
				return inventoryContains(boneType);
			}

			@Override
			public void execute() {
				state = "Burying bones";
				if(ctx.bank.close() && ctx.players.local().getAnimation()==-1){
					state = "Burying bones";
					interactInventory(boneType,"Bury","Bones");
				}
				
			}

			
		}
		
	   class Bank extends BoneNode{

		public Bank(MethodContext ctx) {
			super(ctx);
		}

		@Override
		public boolean activate() {
			return !inventoryContains(boneType);
		}

		
		public void execute() {
				state = "Banking";
		     if(bankContains(boneType)){
		    	 ctx.bank.withdraw(boneType, 28);
		     }else shutdown();
			
		}


		private boolean bankContains(int id) {
			ArrayList<Integer> bankItems = new ArrayList<Integer>();
			
			while(!ctx.bank.isOpen()){
				ctx.bank.open();
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
		g.drawString("Bone type: "+boneName, 20, 150);
	}
}
