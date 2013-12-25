package deltashearer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.powerbot.event.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.Skills;
import org.powerbot.script.methods.Hud.Window;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.Component;
import org.powerbot.script.wrappers.Item;
import org.powerbot.script.wrappers.Npc;
import org.powerbot.script.wrappers.Tile;
import org.powerbot.script.wrappers.Widget;




@org.powerbot.script.Manifest(authors = { "Delta Scripter" }, name = "Delta Sheep", 
description = "Shears sheep, makes balls of wool, banks the wool. For crafting xp; start with shears", 
website = "", version = 1, hidden = false)
public class ShearBody extends PollingScript implements PaintListener{

	public ShearBody(){
		getExecQueue(State.START).add(new Runnable() {
			@Override
			public void run() {
				sheepID.add(1765);
				sheepID.add(43);
				sheepID.add(5157);
				sheepID.add(5160);
				sheepID.add(5161);
				   addNode(new spinWools(ctx));
				   addNode(new Shear(ctx));
			}
		});
	}
	public Tile[] pathToCastle = {new Tile(3220,3219,0),new Tile(3214,3226,0),
			new Tile(3208,3225,0)};
	public Tile[] pathToSheep = {new Tile(3228,3233,0),new Tile(3221,3244,0),
			new Tile(3218,3262,0), new Tile(3203,3277), new Tile(3199,3285,0)};
	
	private final List<ShearNode> nodeList = Collections.synchronizedList(new ArrayList<ShearNode>());
	private String state;
	public boolean spinWool = false;
	public Timer wait = new Timer(0);
	ArrayList<Integer> sheepID = new ArrayList<Integer>();
	public ShearMethod m = new ShearMethod(ctx);
	@Override
	public int poll() {
		
		for(ShearNode node: nodeList){
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
	   private void addNode(final ShearNode...nodes) {
		   
	        for(ShearNode node : nodes) {
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
		public void npcInteract(int i, String string) {
			ArrayList<String> actions = new ArrayList<String>();
			for(Npc n : ctx.npcs.select().id(i).nearest().first()){
					if (n.isOnScreen()) {
						n.interact(string);
					} else ctx.camera.turnTo(n);
				}
		}
		class Shear extends ShearNode{

			public Shear(MethodContext ctx) {
				super(ctx);
			}
			Tile sheepArea = new Tile(3197,3291,0);
		
			Timer wait = new Timer(0);
			boolean teleported = false;
			@Override
			public boolean activate() {
				return !spinWool;
			}

			@Override
			public void execute() {
				//npcInteract(sheepID.get(1),"");
				
				Tile player = ctx.players.local().getLocation();
				if(m.backPackIsFull()){
					spinWool = true;
				}
				if(sheepArea.getMatrix(ctx).isReachable()){
					
					
					for(int index = 0; index < sheepID.size();){
						 player = ctx.players.local().getLocation();
						if(ctx.widgets.get(1092,0).isVisible())
							break;
						if(m.backPackIsFull())
							break;
						
						
						//System.out.println("Sheep is null?: " + !m.npcIsNotNull(sheepID.get(index)));
						//if(m.npcIsNotNull(sheepID.get(index)))
						//System.out.println("How far is sheep?: " + m.getNPC(sheepID.get(index)).getLocation().distanceTo(player));
						
						state = "Shearing sheep";
						if(!wait.isRunning())
						if(ctx.players.local().getAnimation()!=-1){
							System.out.println("Shearing sheep");
							wait = new Timer(2500);
						}else if(m.npcIsNotNull(sheepID.get(index)) && m.getNPC(sheepID.get(index)).getLocation().distanceTo(player)<6){
							System.out.println("Clicking sheep: "+ sheepID.get(index));
							teleported = false;
							npcInteract(sheepID.get(index), "Shear");
							wait = new Timer(1500);
						}else if(m.npcIsNotNull(sheepID.get(index))&& m.getNPC(sheepID.get(index)).getLocation().distanceTo(player)<10){
							System.out.println("Getting to sheep: " + sheepID.get(index) + " at the location: " +m.getNPC(sheepID.get(index)).getLocation());
							ctx.movement.stepTowards(m.getNPC(sheepID.get(index)).getLocation());
							wait = new Timer(3000);
						}else index++;
					}
					
					
				}else if(new Tile(3199,3285,0).distanceTo(player)<8){
					state = "Climbing over stile";
					m.interactO("Stile", "Climb-over", "Stile");
				}else if(teleported){
					state = "Walking to sheep pen";
					ctx.movement.newTilePath(pathToSheep).traverse();
				}else if(new Tile(3233,3221,0).distanceTo(player)<10){//lummbridge area
					teleported = true;
				}else {
					state = "Teleporting to Lummbridge";
					m.teleportTo(47, "");//tele to lummbridge
				}
			}

			
		}
		
	   class spinWools extends ShearNode{

		public spinWools(MethodContext ctx) {
			super(ctx);
		}

		@Override
		public boolean activate() {
			return spinWool;
		}
		
		Tile wheelArea = new Tile(3209,3213,1);
		Component clickWhoolItem = ctx.widgets.get(1371,44).getChild(2);
		Component currentItemSelected = ctx.widgets.get(1370,56);
		Component spinButton = ctx.widgets.get(1370,38);
		boolean teleported = false;
		public void execute() {
			Tile player = ctx.players.local().getLocation();
			if(ctx.players.local().getAnimation()!=-1){
				state = "Spinning wool";
				wait = new Timer(4000);
			}
			
			if(!m.inventoryContains("Wool") && !m.inventoryContains("Ball of wool")){
				spinWool = false;
			}else
			if(!m.inventoryContains("Wool") && m.inventoryContains("Ball of wool")){
				state = "Going to bank balls of wool";
				if(ctx.game.getPlane()==2){
					if(new Tile(3209,3220,2).distanceTo(player)<6){
						if(ctx.bank.isOpen()){
							ctx.bank.deposit(1759, 27);//ball of wool
						}else ctx.bank.open();
					}else ctx.movement.stepTowards((new Tile(3209,3220,2)));
				}else if(ctx.game.getPlane()==1){
					if(new Tile(3205,3209,1).distanceTo(player)<6){//stairs to bank
						teleported = false;
						m.interactO("Staircase", "Climb-up", "Stairs");
					}else ctx.movement.stepTowards(new Tile(3205,3209,1));//stairs
				}else getTo1Floor();
			}else
			if(!wait.isRunning())
			if(ctx.game.getPlane()==1){
				if(wheelArea.distanceTo(player)<6){
					if(clickWhoolItem.isVisible()){
						if(currentItemSelected.getText().contains("Ball of wool")){
							teleported = false;
							spinButton.click();
							wait = new Timer(2000);
						}else clickWhoolItem.click();
					}else
					m.interactO("Spinning Wheel", "Spin", "Wheel");
				}else m.clickOnMap(wheelArea);
			}else getTo1Floor();
		}

		private void getTo1Floor() {
			Tile player = ctx.players.local().getLocation();
			 if(new Tile(3206,3228,0).distanceTo(player)<8){
				System.out.println("Climbing stairs");
				state = "Climbing stairs";
				m.interactO("Staircase", "Climb", "Stairs");
			}else if(teleported){
				state = "Walking to the castle";
				ctx.movement.newTilePath(pathToCastle).traverse();
			}else if(new Tile(3233,3221,0).distanceTo(player)<10){//lummbridge area
				teleported = true;
			}else {
				state  ="Teleporting to Lummbridge";
				m.teleportTo(47, "");//tele to lummbridge
			}
			
		}
		   
		   
	   }
private Font myFont = new Font("Consolas",Font.BOLD,14);
	@Override
	public void repaint(Graphics g) {
		g.setFont(myFont);
		g.setColor(Color.green);
		g.drawString("State: "+state, 20, 130);
		g.drawString("Crafting level: " + ctx.skills.getLevel(Skills.CRAFTING), 20, 150);
	}
}
