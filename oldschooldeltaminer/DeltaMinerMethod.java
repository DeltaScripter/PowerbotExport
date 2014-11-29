package oldschooldeltaminer;

import java.util.ArrayList;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.Npc;



public class DeltaMinerMethod extends ClientAccessor{

	
	
	public DeltaMinerMethod(ClientContext ctx) {
		super(ctx);
	}
	public boolean isChatiing(){
		if(ctx.widgets.widget(242).component(1).valid()||
				ctx.widgets.widget(243).component(1).valid()||
				ctx.widgets.widget(244).component(6).valid()||
				ctx.widgets.widget(241).component(3).valid()||
				ctx.widgets.widget(65).component(4).valid()||
				ctx.widgets.widget(64).component(3).valid()){
			
			ctx.widgets.widget(64).component(3).click();
			ctx.widgets.widget(242).component(4).click();
			ctx.widgets.widget(243).component(5).click();
			ctx.widgets.widget(244).component(6).click();
			ctx.widgets.widget(241).component(3).click();
			
			ctx.widgets.widget(65).component(4).click();//player talk
			sleep(Random.nextInt(1200, 1700));
			
			return true;
		}
		return false;
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
				for(Item i : ctx.inventory.items()){
						if(i.id()!=-1)
						inventory.add(i.id());
				}
				return inventory.size()>=28;
			}
			public int inventoryGetCount(int id) {
			
				return 0;
			}
		
			public void interactO(final int id, final String string, final String o) {
				for(GameObject y: ctx.objects.select().id(id).nearest().first()){
					
					if (y.inViewport()) {
								y.interact(string);
								sleep(Random.nextInt(1200, 1800));
							} else ctx.camera.turnTo(y.tile().derive(1,7));
						
						}
				
			}
			public boolean inventoryContains(String name) {
			
				return false;
			}
			public boolean inventoryContains(int ID) {
				return !ctx.inventory.select().id(ID).isEmpty();
			}
	public void interactInventory(final int id, final String string, final String o) {

	for(Item g: ctx.inventory.items()){
			if(g.id()==id){
				g.interact(string);
				 sleep(Random.nextInt(Random.nextInt(10, 200),Random.nextInt(40, 300)));
				break;
			}
		}
		
	}
	public void teleportTo(int loc, String teleName) {
	
	}
	public int inventoryStackSize(String name) {
		
		return 0;
	}
	public int inventoryStackSize(int ID) {
	
		return 0;
	}
	public int backPackFreeSlots() {
	
		return 0;
	}
	public void useAction(int Slot) {
	
	}
}
