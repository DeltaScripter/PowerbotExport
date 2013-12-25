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
import org.powerbot.script.methods.Hud.Window;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.Component;
import org.powerbot.script.wrappers.Item;
import org.powerbot.script.wrappers.Tile;
import org.powerbot.script.wrappers.Widget;




@org.powerbot.script.Manifest(authors = { "Delta Scripter" }, name = "DeltaShearer", 
description = "In  development crafting exp", 
website = "", version = 1, hidden = true)
public class ShearBody extends PollingScript implements PaintListener{

	public ShearBody(){
		getExecQueue(State.START).add(new Runnable() {
			@Override
			public void run() {
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
	   
		class Shear extends ShearNode{

			public Shear(MethodContext ctx) {
				super(ctx);
			}
			Tile sheepArea = new Tile(3197,3291,0);
			int[] sheep = {1765,43,5160};
			Timer wait = new Timer(0);
			boolean teleported = false;
			@Override
			public boolean activate() {
				return !spinWool;
			}

			@Override
			public void execute() {
				Tile player = ctx.players.local().getLocation();
				if(m.backPackIsFull()){
					spinWool = true;
				}
				
				if(sheepArea.getMatrix(ctx).isReachable()){
				
					
					if(!wait.isRunning() && !ctx.players.local().isInMotion())
					for(int sheepID: sheep){
						state = "Shearing sheep";
						if(ctx.players.local().getAnimation()!=-1){
							System.out.println("Shearing sheep");
							wait = new Timer(2500);
						}else if(m.npcIsNotNull(sheepID) && m.getNPC(sheepID).getLocation().distanceTo(player)<6){
							System.out.println("Clicking sheep");
							m.npcInteract(sheepID, "Shear");
							wait = new Timer(1500);
						}else if(m.npcIsNotNull(sheepID))
							m.clickOnMap(m.getNPC(sheepID).getLocation());
					}
					
					
				}else if(new Tile(3199,3285,0).distanceTo(player)<8){
					state = "Climbing over stile";
					m.interactO("Stile", "Climb-over", "Stile");
				}else if(teleported){
					state = "Walking to sheep pen";
					System.out.println("Walking to the sheep pen");
					ctx.movement.newTilePath(pathToSheep).traverse();
				}else if(new Tile(3233,3221,0).distanceTo(player)<10){//lummbridge area
					teleported = true;
				}else {
					state = "Teleporting to Lummbridge";
					System.out.println("Teleporting");
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
				state = "Dropping ball of wool(s)";
				m.interactInventory("Ball of wool", "Drop", "Ball of wool");
			}else
			if(!wait.isRunning())
			if(ctx.game.getPlane()==1){
				if(wheelArea.distanceTo(player)<6){
					if(clickWhoolItem.isVisible()){
						if(currentItemSelected.getText().contains("Ball of wool")){
							spinButton.click();
							wait = new Timer(2000);
						}else clickWhoolItem.click();
					}else
					m.interactO("Spinning Wheel", "Spin", "Wheel");
				}else m.clickOnMap(wheelArea);
			}else if(new Tile(3206,3228,0).distanceTo(player)<8){
				System.out.println("Climbing stairs");
				state = "Climbing stairs";
				m.interactO("Staircase", "Climb", "Stairs");
			}else if(teleported){
				System.out.println("Walking to castle");
				state = "Walking to the castle";
				ctx.movement.newTilePath(pathToCastle).traverse();
			}else if(new Tile(3233,3221,0).distanceTo(player)<10){//lummbridge area
				teleported = true;
			}else {
				state  ="Teleporting to Lummbridge";
				System.out.println("Teleporting");
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
	}
}
