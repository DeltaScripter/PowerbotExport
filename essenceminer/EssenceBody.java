package essenceminer;

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
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Item;
import org.powerbot.script.wrappers.Npc;
import org.powerbot.script.wrappers.Player;
import org.powerbot.script.wrappers.Tile;
import essenceminer.EssenceData.essence;



@org.powerbot.script.Manifest(authors = { "Delta Scripter" }, name = "DeltaEssence", 
description = "Mines rune essence at Varrock and banks, start at Varrock eastern bank.", 
website = "", version = 1, topic = 1128727)
public class EssenceBody extends PollingScript implements PaintListener{

	public EssenceBody(){
		getExecQueue(State.START).add(new Runnable() {
			@Override
			public void run() {
				  addNode(new MineEssence(ctx));
				  addNode(new Bank(ctx));
			}
		});
	}
	public final Tile[] pathToAubury = new Tile[]{new Tile(3253,3420,0),
			new Tile(3260,3417,0),new Tile(3258,3409,0),new Tile(3256,3402,0),
			new Tile(3253,3400,0)};
	private final List<EssenceNode> nodeList = Collections.synchronizedList(new ArrayList<EssenceNode>());
	private String state;
	ArrayList test = new ArrayList();
	@Override
	public int poll() {
		
		for(EssenceNode node: nodeList){
			if(node.activate()){
				node.execute();
			}
		}
		
		return 400;
	}
	public void interactInventory(final int i, final String string, final String o) {
		ArrayList<String> actions = new ArrayList<String>();
		for(Item t : ctx.backpack.select().id(i).first()){
			//System.out.println(ctx.widgets.get(1477,122).getChild(0).getBoundingRect().getCenterY());
			if(ctx.hud.view(Window.BACKPACK) && ctx.widgets.get(1473,7).contains(
				t.getComponent().getCenterPoint())){
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
	   private void addNode(final EssenceNode...nodes) {
		   
	        for(EssenceNode node : nodes) {
	            if(!this.nodeList.contains(node)) {
	                this.nodeList.add(node);
	            }
	        }
	    }
		public GameObject getObject(int i) {
			for(GameObject obj : ctx.objects.select().id(i).nearest().first()){
				return obj;
			}
			return null;
		}
		public void clickOnMap(Tile t) {
			Tile winner = null;
			 
			for(Tile tile : getSurroundingTiles()){
			if(winner == null || tile.distanceTo(t) < winner.distanceTo(t))
			winner = tile;
			}
			ctx.movement.stepTowards(winner.randomize(1, 1));
			}
			 
			public ArrayList<Tile> getSurroundingTiles(){
			ArrayList<Tile> l = new ArrayList<Tile>();
			 
			int xTiles = ctx.widgets.get(1465, 12).getScrollWidth()/10;
			int yTiles = ctx.widgets.get(1465, 12).getScrollHeight()/10;
			int myX = ctx.players.local().getLocation().getX();
			int myY = ctx.players.local().getLocation().getY();
			int myPlane = ctx.players.local().getLocation().getPlane();
			 
			for(int i = 0; i < xTiles; i++)
			for(int j = 0; j < yTiles; j++){
			if(new Tile(myX - i, myY - j, myPlane).getMatrix(ctx).isOnMap()){
			l.add(new Tile(myX - i, myY - j, myPlane));
			}
			if(new Tile(myX + i, myY - j, myPlane).getMatrix(ctx).isOnMap()){
			l.add(new Tile(myX + i, myY - j, myPlane));
			}
			if(new Tile(myX - i, myY + j, myPlane).getMatrix(ctx).isOnMap()){
			l.add(new Tile(myX - i, myY + j, myPlane));
			}
			if(new Tile(myX + i, myY + j, myPlane).getMatrix(ctx).isOnMap() ){
			l.add(new Tile(myX + i, myY + j, myPlane));
			}
			}
			return l;
		}
			public boolean objIsByTile(Tile tile, int object, int dist) {
				for(GameObject obj : ctx.objects.select().id(object).nearest(tile)){
					if(obj.getLocation().distanceTo(tile)<dist){
						return true;
					}
				}
				return false;
			}

			private void openDoor(Tile tile) {
				state = "Opening door";
				ArrayList<String> actions = new ArrayList<String>();
				if(tile.distanceTo(ctx.players.local())<5){
					for(GameObject door: ctx.objects.select().id(24381).nearest().first()){
						while(!door.isOnScreen())
							ctx.camera.turnTo(door);
						door.hover();
						for(String t: door.getActions()){
							if(!actions.contains(t)){
								actions.add(t);
							}
						}
						if(actions.contains("Open")){
							
							door.interact("Open");
						}
					}
				}else {
					clickOnMap(tile);
					ctx.game.sleep(3000,3500);
				}
				
			}
	   public boolean objIsNotNull(int id) {
			if(!ctx.objects.select().id(id).first().isEmpty()){
				return true;
			}
			return false;
		}
		private boolean backPackIsFull() {
			ArrayList<Integer> inventory = new ArrayList<Integer>();
			for(Item i : ctx.backpack.getAllItems()){
					if(i.getId()!=-1)
					inventory.add(i.getId());
			}
			return inventory.size()>=28;
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
	   
		class MineEssence extends EssenceNode{

			public MineEssence(MethodContext ctx) {
				super(ctx);
			}
				public Timer mine  =new Timer(0);
			@Override
			public boolean activate() {
				return !inventoryContains(essence.ESSENCE.getID())||!backPackIsFull();
			}

			@Override
			public void execute() {
				if(ctx.bank.close() && byRock()){
					mineRock();
				}else getToRock();
				
			}

			private void mineRock() {
				state = "Mining rock";
				ArrayList<String> actions = new ArrayList<String>();
				if(readyToMine()){
					for(GameObject rock: ctx.objects.select().id(essence.ESSENCEROCK.getID()).nearest().first()){
						while(!rock.isOnScreen())
							ctx.camera.turnTo(rock);
						rock.hover();
						for(String t: rock.getActions()){
							if(!actions.contains(t)){
								actions.add(t);
							}
						}
						if(actions.contains("Mine")){
							rock.interact("Mine");
							ctx.game.sleep(500,1000);
						}
					}
				}
				
			}

			private boolean readyToMine() {
				if(ctx.players.local().getAnimation()!=6753){
					ctx.game.sleep(1800,2100);
					if(ctx.players.local().getAnimation()!=6753)
						return true;
				}
				return false;
			}

			private void getToRock() {
				Player local = ctx.players.local();
				state = "Getting to essence area";
				if(new Tile(3253,3401,0).distanceTo(local.getLocation())<10){
					if(objIsByTile(new Tile(3253,3399,0), 24381,5)){
						openDoor(new Tile(3253,3399,0));
					}else if(new Tile(3253,3401,0).distanceTo(local.getLocation())<6){
						for(Npc teleMan : ctx.npcs.select().id(essence.AUBURYID.getID()).nearest().first()){
							teleMan.interact("Teleport");
							ctx.game.sleep(1200,2000);
						}
					}else {
						clickOnMap(new Tile(3253,3401,0));
						ctx.game.sleep(4000,5500);
					}
				}else {
					ctx.movement.newTilePath(pathToAubury).traverse();
					ctx.game.sleep(3300,4000);
				}
			}


			private boolean byRock() {
				Player local = ctx.players.local();
				
				if(objIsNotNull(essence.ESSENCEROCK.getID())){
					if(local.getLocation().distanceTo(getObject(essence.ESSENCEROCK.getID()).getLocation())<6){
						return true;
					}else {
						clickOnMap(getObject(essence.ESSENCEROCK.getID()).getLocation());
						ctx.game.sleep(3000,3500);
					}
				}
				return false;
			}

			
		}
		
	   class Bank extends EssenceNode{

		public Bank(MethodContext ctx) {
			super(ctx);
		}

		@Override
		public boolean activate() {
			return backPackIsFull();
		}

		

		public void execute() {
			state= "Banking";
			if(!objIsNotNull(essence.ESSENCEROCK.getID())){
				bank();
			}else exitPortal();
		}


		private void bank() {
			if(new Tile(3253,3420,0).distanceTo(ctx.players.local().getLocation())<5){
				if(ctx.bank.open()){
					while(inventoryContains(essence.ESSENCE.getID())){
						ctx.bank.deposit(essence.ESSENCE.getID(), 28);
					}
				}
			}else {
				if(new Tile(3253,3401,0).distanceTo(ctx.players.local().getLocation())<10){
					while(objIsByTile(new Tile(3253,3399,0), 24381,5)){
						openDoor(new Tile(3253,3399,0));
					}
					}
				ctx.movement.newTilePath(pathToAubury).reverse().traverse();
				ctx.game.sleep(2300,3000);
			}
			
		}

		private void exitPortal() {
			ArrayList<String> actions = new ArrayList<String>();
			if(byPortal()){
				for(GameObject portal: ctx.objects.select().id(essence.PORTAL.getID()).nearest().first()){
					portal.hover();
					String[] menuItems = ctx.menu.getItems();
					for(String t: menuItems){
						if(!actions.contains(t)){
							actions.add(t);
						}
					}
					for(String inter : actions){
						if(inter.contains("Enter")){
							ctx.mouse.click(portal.getCenterPoint(),true);
						}
					}
				}
			}
		}

		private boolean byPortal() {
				Player local = ctx.players.local();
				
				if(objIsNotNull(essence.PORTAL.getID())){
					if(local.getLocation().distanceTo(getObject(essence.PORTAL.getID()).getLocation())<3){
						return true;
					}else {
						clickOnMap(getObject(essence.PORTAL.getID()).getLocation());
						ctx.game.sleep(3000,3500);
					}
				}
			return false;
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
