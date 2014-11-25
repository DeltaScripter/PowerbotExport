package toadCollector;

import java.util.ArrayList;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.GroundItem;
import org.powerbot.script.rt6.Item;
import org.powerbot.script.rt6.ItemQuery;
import org.powerbot.script.rt6.Npc;
import org.powerbot.script.rt6.Hud.Window;


public class toadMethod extends ClientAccessor	{

	public toadMethod(ClientContext ctx) {
		super(ctx);
	}

	public void sleep(int millis){
		try {
			Thread.sleep(Math.max(5, (int) (millis * Random.nextDouble(0.85, 1.5))));
		} catch (InterruptedException ignored) {
		}
	}
	public void state(String state){MainBody.state = state;}
	
	public double tileDisctanceToPlayer(Tile tile) {return tile.distanceTo(ctx.players.local().tile());}
	
	public void interactG(int i, String string, String string2) {
		if(!ctx.groundItems.select().id(i).first().isEmpty()){
				for(GroundItem item : ctx.groundItems.select().id(i).nearest().first()){
					if (item.inViewport()) {
						ctx.mouse.move(item.centerPoint());
							if(item.interact(string)){
								
							break;
							}else ctx.camera.turnTo(item.tile());
						
					} else ctx.camera.turnTo(item.tile().derive(3, 6));
					break;
				}
				
		}
		
	}
	public boolean getToNearByTile(Tile tile) {
		if(tile.distanceTo(ctx.players.local().tile())<4){
			return true;
		}else {
			sleep(Random.nextInt(Random.nextInt(800, 1500), Random.nextInt(1700, 1900)));
			ctx.movement.step(tile);
		}
		return false;
	}
	public boolean bankContains(int id) {
		ArrayList<Integer> bankItems = new ArrayList<Integer>();
		
		while(!ctx.bank.opened()){
			ctx.bank.open();
		}
		for(Item item : ctx.bank.select()){
			if(!bankItems.contains(item.id())){
				bankItems.add(item.id());
			}
		}
		return bankItems.contains(id);
	}
	public boolean objIsNotNull(int id) {
		if(!ctx.objects.select().id(id).first().isEmpty()){
			return true;
		}
		return false;
	}

	public int inventoryGetCount(int i) {
		if(!ctx.hud.opened(Window.BACKPACK) || !ctx.hud.opened(Window.BACKPACK))
			ctx.hud.open(Window.BACKPACK);
		
		ItemQuery<Item> g;
		g = null;
		g = ctx.backpack.select().id(i);
	
		return g.count(false);
	}

	public GameObject getObject(int i) {
		for(GameObject obj : ctx.objects.select().id(i).nearest().first()){
			return obj;
		}
		return null;
	}

	public Npc getNPC(int i) {
		for(Npc npc : ctx.npcs.select().id(i).nearest().first()){
			return npc;
		}
		return null;
	}

	public boolean gItemIsNotNull(int id) {
		if(!ctx.groundItems.select().id(id).first().isEmpty()){
			return true;
		}
		return false;
	}

	public GroundItem getGroundItem(int i) {
		for(GroundItem item : ctx.groundItems.select().id(i).nearest().first()){
			return item;
		}
		return null;
	}
	public void interactInventory(final int i, final String string, final String o) {
		ArrayList<String> actions = new ArrayList<String>();
		
		for(Item t : ctx.backpack.select().id(i).first()){
			if(ctx.hud.open(Window.BACKPACK) && ctx.widgets.component(1473,31).contains(
				t.component().centerPoint())){
				System.out.println("Hovering");
				t.hover();
				sleep(1200);
				String[] menuItems = ctx.menu.items();
				for(String opt: menuItems){
					if(!actions.contains(opt)){
						actions.add(opt);
					}
				}
				for(String text: actions){
					if(text.contains(string)){
						if(t.interact(string)){
						System.out.println("Using " + string + " with item: " + o);
						 sleep(Random.nextInt(2000, 2500));
						}
					}
				}
				 
			}else
			if(ctx.widgets.component(1473,31).boundingRect().getCenterY()>
			t.component().boundingRect().getCenterY()){
				state("Scrolling through inventory");
				ctx.mouse.move(ctx.widgets.component(1473, 31).centerPoint());
				ctx.mouse.scroll(false);
			}else {
				state("Scrolling through inventory");
				ctx.mouse.move(ctx.widgets.component(1473, 31).centerPoint());
				ctx.mouse.scroll(true);
				}
			}
		//}else System.out.println("//timer1 running");
	}
	public void interactO(final int i, final String string, final String o) {
		for(GameObject y: ctx.objects.select().id(i).nearest().first()){
			//if(closeInterfaces())
			if(y.inViewport() && y.interact(string)){
			//state("Interacting: " + string);
			sleep(Random.nextInt(400,900));
			}else {
				ctx.camera.turnTo(y.tile().derive(2, 3));
			} 
				}
	}
	public boolean inventoryContains(int i) {
		if(!ctx.hud.opened(Window.BACKPACK)){
			while (ctx.bank.opened()){
				ctx.bank.close();
			}
			ctx.hud.open(Window.BACKPACK);
		}
			while (!ctx.backpack.select().id(i).isEmpty()) {
				return true;
			}
		
		return false;
	}
	public boolean backPackIsFull() {
		ArrayList<Integer> inventory = new ArrayList<Integer>();
		for(Item i : ctx.backpack.items()){
				if(i.id()!=-1)
				inventory.add(i.id());
		}
		return inventory.size()>=28;
	}
	
}
