package kebbithunter;

import java.util.ArrayList;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.Hud.Window;
import org.powerbot.script.rt6.Item;
import org.powerbot.script.rt6.ItemQuery;
import org.powerbot.script.rt6.Npc;



public class KebMethod extends ClientAccessor{

	
	
	public KebMethod(ClientContext ctx) {
		super(ctx);
	}
	public static String format(final long millis) {
		final StringBuilder sb = new StringBuilder();
		final long totalseconds = millis / 1000L;
		final long totalminutes = totalseconds / 60L;
		final long totalhours = totalminutes / 60L;
		final int seconds = (int) totalseconds % 60;
		final int minutes = (int) totalminutes % 60;
		final int hours = (int) totalhours % 24;
		final int days = (int) (totalhours / 24);
		if (days > 0) {
		if (days < 10) {
		sb.append(0);
		}
		sb.append(days);
		sb.append(":");
		}
		if (hours < 10) {
		sb.append(0);
		}
		sb.append(hours);
		sb.append(":");
		if (minutes < 10) {
		sb.append(0);
		}
		sb.append(minutes);
		sb.append(":");
		if (seconds < 10) {
		sb.append(0);
		}
		sb.append(seconds);
		return sb.toString();
		}
	public void sleep(int millis){
		try {
			Thread.sleep(Math.max(5, (int) (millis * Random.nextDouble(0.85, 1.5))));
		} catch (InterruptedException ignored) {
		}
	}
//Timer timer = new Timer(0);
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
	/*
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
		int myX = ctx.players.local().tile().x();
		int myY = ctx.players.local().tile().y();
		int myPlane = ctx.players.local().tile().z();
		 
		for(int i = 0; i < xTiles; i++)
		for(int j = 0; j < yTiles; j++){
		if(new Tile(myX - i, myY - j, myPlane).tile()..getMatrix(ctx).isOnMap()){
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
	}*/
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
					if (n.inViewport()) {
						n.hover();
						String menuItems[] = ctx.menu.items();
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
					if (n.inViewport()) {
						n.hover();
						String menuItems[] = ctx.menu.items();
						for(String opt: menuItems){
							if(!actions.contains(opt))
								actions.add(opt);
						}
						for(String text: actions){
							if(text.contains(string)){
								  n.interact(string);
								 //  sleep(2000);
								   break;
								
							}
						}
					} else ctx.camera.turnTo(n);
				}
		}
		public boolean objIsByTile(Tile tile, int object, int dist) {
			for(GameObject obj : ctx.objects.select().id(object).nearest(tile)){
				if(obj.tile().distanceTo(tile)<dist){
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
				for(Item i : ctx.backpack.select()){
						if(i.id()!=-1)
						inventory.add(i.id());
				}
				return inventory.size()>=28;
			}
			public int inventoryGetCount(int id) {
				if(!ctx.hud.opened(Window.BACKPACK) || !ctx.hud.opened(Window.BACKPACK))
					ctx.hud.open(Window.BACKPACK);
				
				ItemQuery<Item> g;
				g = null;
				g = ctx.backpack.select().id(id);
			
				return g.count(false);
			}
			public int inventoryGetCount(String name) {
				if(!ctx.hud.open(Window.BACKPACK) || !ctx.hud.opened(Window.BACKPACK))
					ctx.hud.open(Window.BACKPACK);
				
				ItemQuery<Item> g;
				g = null;
				g = ctx.backpack.select().name(name);
			
				return g.count(false);
			}
			public void interactO(final int id, final String string, final String o) {
				for(GameObject y: ctx.objects.select().id(id).nearest().first()){
					KebBody.state = o;
							if (y.inViewport()) {
								y.interact(string);
							} else ctx.camera.turnTo(y.tile().derive(1,7));
						
						}
				
			}
			public boolean inventoryContains(String name) {
				if(!ctx.hud.opened(Window.BACKPACK)){
					ctx.hud.open(Window.BACKPACK);
					//sleep(2000);
				}
					while (!ctx.backpack.select().name(name).first().isEmpty()) {
						return true;
					}
				
				return false;
			}
			public boolean inventoryContains(int ID) {
				if(!ctx.hud.opened(Window.BACKPACK)){
					ctx.hud.open(Window.BACKPACK);
					//sleep(2000);
				}
					while (!ctx.backpack.select().id(ID).first().isEmpty()) {
						return true;
					}
				
				return false;
			}
	public void interactInventory(final int id, final String string, final String o) {
		for(Item t : ctx.backpack.select().id(id).first()){
			if(ctx.hud.open(Window.BACKPACK) && ctx.widgets.component(1473,31).contains(
				t.component().centerPoint())){
				t.hover();
				t.interact(string);
				sleep(Random.nextInt(150,500));
				 
			}else
			if(ctx.widgets.component(1473,31).boundingRect().getCenterY()>
			t.component().boundingRect().getCenterY()){
				ctx.input.move(ctx.widgets.component(1473, 31).centerPoint());
				ctx.input.scroll(false);
			}else {
				ctx.input.move(ctx.widgets.component(1473, 31).centerPoint());
				ctx.input.scroll(true);
				}
			}
	}
	public void teleportTo(int loc, String teleName) {
		while(ctx.bank.open())
			ctx.bank.close();
		KebBody.state = "Teleporting to: " + teleName;
		/*if(!timer.isRunning()){
		if(ctx.widgets.get(1092,loc).opened()){//lodestone screen
			ctx.input.move(ctx.widgets.get(1092).getComponent(loc).getCenterPoint());
			ctx.widgets.get(1092).getComponent(loc).click(true);
			timer = new Timer(6000);
		}else {
				if (ctx.players.local().getAnimation() == -1){
					ctx.widgets.get(1465,10).hover();
					for(String t: ctx.menu.getItems()){
						if(t.contains("Teleport")){
							ctx.widgets.get(1465,10).click();//select lodestone button
							//timer = new Timer(1000);
						}
					}
				
				}
		}		
		}*/
	}
	public int inventoryStackSize(String name) {
		if(ctx.hud.open(Window.BACKPACK)){
		
		ItemQuery<Item> g;
		g = null;
		g = ctx.backpack.select().name(name);
		return g.count(true);
		}
		return 0;
	}
	public int inventoryStackSize(int ID) {
		if(ctx.hud.open(Window.BACKPACK)){
		
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
	public void useAction(int Slot) {
		if(ctx.combatBar.actionAt(Slot).ready()){
			if(ctx.combatBar.actionAt(Slot).select()){
				sleep(Random.nextInt(Random.nextInt(100, 200), Random.nextInt(500, 1000)));
			}
		}
		
	}
}
