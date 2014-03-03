package kebbithunter;

import java.util.ArrayList;

import org.powerbot.script.lang.ItemQuery;
import org.powerbot.script.methods.Hud.Window;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import org.powerbot.script.util.Random;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Item;
import org.powerbot.script.wrappers.Npc;
import org.powerbot.script.wrappers.Tile;

public class KebMethod extends MethodProvider{

	
	
	public KebMethod(MethodContext ctx) {
		super(ctx);
	}
Timer timer = new Timer(0);
	public GameObject getObject(int i) {
		for(GameObject obj : ctx.objects.select().id(i).nearest().first()){
			return obj;
		}
		return null;
	}
	public GameObject getObject(String name) {
		for(GameObject obj : ctx.objects.select().name(name).nearest().first()){
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
		ctx.movement.stepTowards(winner);
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
		public boolean npcIsNotNull(int id) {
			if(!ctx.npcs.select().id(id).nearest().first().isEmpty()){
				return true;
			}
		return false;
	}
		public boolean npcIsNotNull(String name) {
			if(!ctx.npcs.select().name(name).nearest().first().isEmpty()){
				return true;
			}
		return false;
	}
		public Npc getNPC(int i) {
			for(Npc npc : ctx.npcs.select().id(i).nearest().first()){
				return npc;
			}
			return null;
		}
		public Npc getNPC(String name) {
			for(Npc npc : ctx.npcs.select().name(name).nearest().first()){
				return npc;
			}
			return null;
		}
		public void npcInteract(String name, String string) {
			ArrayList<String> actions = new ArrayList<String>();
			for(Npc n : ctx.npcs.select().name(name).nearest().first()){
					if (n.isOnScreen()) {
						n.hover();
						String menuItems[] = ctx.menu.getItems();
						for(String opt: menuItems){
							if(!actions.contains(opt))
								actions.add(opt);
						}
						for(String text: actions){
							if(text.contains(string)){
								  n.interact(string);
								 // KebBody.waiting = new Timer(Random.nextInt(1500, 2154));
								   break;
								
							}
						}
					} else ctx.camera.turnTo(n);
				}
		}
		public void npcInteract(int i, String string) {
			ArrayList<String> actions = new ArrayList<String>();
			for(Npc n : ctx.npcs.select().id(i).nearest().first()){
					if (n.isOnScreen()) {
						n.hover();
						String menuItems[] = ctx.menu.getItems();
						for(String opt: menuItems){
							if(!actions.contains(opt))
								actions.add(opt);
						}
						for(String text: actions){
							if(text.contains(string)){
								  n.interact(string);
								   sleep(2000);
								   break;
								
							}
						}
					} else ctx.camera.turnTo(n);
				}
		}
		public boolean objIsByTile(Tile tile, int object, int dist) {
			for(GameObject obj : ctx.objects.select().id(object).nearest(tile)){
				if(obj.getLocation().distanceTo(tile)<dist){
					return true;
				}
			}
			return false;
		}
		 public boolean objIsNotNull(int id) {
				if(!ctx.objects.select().id(id).first().isEmpty()){
					return true;
				}
				return false;
			}
		 public boolean objIsNotNull(String name) {
				if(!ctx.objects.select().name(name).first().isEmpty()){
					return true;
				}
				return false;
			}
			public boolean backPackIsFull() {
				ArrayList<Integer> inventory = new ArrayList<Integer>();
				for(Item i : ctx.backpack.getAllItems()){
						if(i.getId()!=-1)
						inventory.add(i.getId());
				}
				return inventory.size()>=28;
			}
			public int inventoryGetCount(int id) {
				if(!ctx.hud.isOpen(Window.BACKPACK) || !ctx.hud.isVisible(Window.BACKPACK))
					ctx.hud.view(Window.BACKPACK);
				
				ItemQuery<Item> g;
				g = null;
				g = ctx.backpack.select().id(id);
			
				return g.count(false);
			}
			public int inventoryGetCount(String name) {
				if(!ctx.hud.isOpen(Window.BACKPACK) || !ctx.hud.isVisible(Window.BACKPACK))
					ctx.hud.view(Window.BACKPACK);
				
				ItemQuery<Item> g;
				g = null;
				g = ctx.backpack.select().name(name);
			
				return g.count(false);
			}
			public void interactO(final int id, final String string, final String o) {
				for(GameObject y: ctx.objects.select().id(id).nearest().first()){
					KebBody.state = o;
							if (y.isInViewport()) {
								y.interact(string);
							} else ctx.camera.turnTo(y.getLocation().randomize(3, 5));
						
						}
				
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
			public boolean inventoryContains(int ID) {
				if(!ctx.hud.isVisible(Window.BACKPACK)){
					ctx.hud.view(Window.BACKPACK);
					sleep(2000);
				}
					while (!ctx.backpack.select().id(ID).first().isEmpty()) {
						return true;
					}
				
				return false;
			}
	public void interactInventory(final int id, final String string, final String o) {
		for(Item t : ctx.backpack.select().id(id).first()){
			if(ctx.hud.view(Window.BACKPACK) && ctx.widgets.get(1473,7).contains(
				t.getComponent().getCenterPoint())){
				t.hover();
				t.interact(string);
				ctx.game.sleep(150,500);
				 
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
	public void teleportTo(int loc, String teleName) {
		while(ctx.bank.isOpen())
			ctx.bank.close();
		KebBody.state = "Teleporting to: " + teleName;
		if(!timer.isRunning()){
		if(ctx.widgets.get(1092,loc).isVisible()){//lodestone screen
			ctx.mouse.move(ctx.widgets.get(1092).getComponent(loc).getCenterPoint());
			ctx.widgets.get(1092).getComponent(loc).click(true);
			timer = new Timer(6000);
		}else {
				if (ctx.players.local().getAnimation() == -1){
					ctx.widgets.get(1465,10).hover();
					for(String t: ctx.menu.getItems()){
						if(t.contains("Teleport")){
							ctx.widgets.get(1465,10).click();//select lodestone button
							timer = new Timer(1000);
						}
					}
				
				}
		}		
		}
	}
	public int inventoryStackSize(String name) {
		if(ctx.hud.view(Window.BACKPACK)){
		
		ItemQuery<Item> g;
		g = null;
		g = ctx.backpack.select().name(name);
		return g.count(true);
		}
		return 0;
	}
	public int inventoryStackSize(int ID) {
		if(ctx.hud.view(Window.BACKPACK)){
		
		ItemQuery<Item> g;
		g = null;
		g = ctx.backpack.select().id(ID);
		return g.count(true);
		}
		return 0;
	}
	public int backPackFreeSlots() {
		int freeSlots = 0;
		for(Item g : ctx.backpack.select()){
			freeSlots+=1;
		}
		//System.out.println("Amount: " + freeSlots);
		return freeSlots;
	}
}
