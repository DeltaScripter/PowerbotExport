package shieldCollector;

import java.util.ArrayList;

import org.powerbot.script.Filter;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ChatOption;
import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.GroundItem;
import org.powerbot.script.rt6.Item;
import org.powerbot.script.rt6.ItemQuery;
import org.powerbot.script.rt6.Menu.Command;
import org.powerbot.script.rt6.Npc;
import org.powerbot.script.rt6.Hud.Window;



public class ShieldMethod extends ClientAccessor	{

	public ShieldMethod(ClientContext ctx) {
		super(ctx);
	}

	public void sleep(int millis){
		try {
			Thread.sleep(Math.max(5, (int) (millis * Random.nextDouble(0.85, 1.5))));
		} catch (InterruptedException ignored) {
		}
	}
	public void state(String state){ShieldMainBody.state = state;}
	
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
	public boolean npcInteract(int i, String string) {
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
							state("Interacting with " + n.name());
							 //System.out.println("interacting");
							  if(n.interact(string)){
							   sleep(Random.nextInt(Random.nextInt(200, 400), Random.nextInt(400, 800)));
							   return true;
							  }
							
						}
					}
				} else ctx.camera.turnTo(n);
			}
		return false;
	}
	public  final int OPTIONVALUE[] = {11,12,18,19,23,24,28,29,33,34};
	public  boolean findOption(String[] text) {
		if(!ctx.widgets.component(1188,1).visible()){
			return false;
		}
		for (String t:text) {
			////ctx.environment.sleep(20,50);
			for (int i :OPTIONVALUE) {
				if (ctx.widgets.component(1188,1).visible()&&ctx.widgets.component(1188, i).text().contains(t)) {
					if(ShieldMainBody.useKeyBoard){
						state("Attempting to use keyboard");
						if(i==12){
						ctx.input.send("{VK_1}");
						}
						if(i==18){
							ctx.input.send("{VK_2}");
							}
						sleep(Random.nextInt(300, 700));
					}else{
					state("Attempting to click option");
					ctx.mouse.click(ctx.widgets.component(1188, i).centerPoint().x+10,ctx.widgets.component(1188, i).centerPoint().y+3 , true);
					}
					return true; 
				}
			}
		
		}
		System.out.println("Inside npcInteract - we couldn't find proper option - clicking an npc to close wrong menu");
		npcInteract(741,"");
		return false;
	}
	public void pressContinue(){
		
		
		 if(ctx.widgets.component(1186,10).visible()||
				 ctx.widgets.component(1186,7).visible()){
			// System.out.println("Pressing continue 1186 - from Method");
			 ctx.widgets.component(1186,10).click();//continue button
			 ctx.widgets.component(1186,7).click();// the continue button (another one)
			 sleep(Random.nextInt(2000, 2400));//the delayedment is necessary for What's Mine Is Yours quest (beginning of it)
		 }
		 if(ctx.widgets.component(1189,10).visible()){
			// System.out.println("Pressing continue 1189- from Method");
			 ctx.widgets.component(1189,10).click();
		 }
		 if(ctx.widgets.widget(1184).component(11).visible()){
			// System.out.println("Pressing continue 1184- from Method");
			 ctx.widgets.widget(1184).component(11).click();
		 }
		 if (ctx.widgets.widget(1191).component(7).visible()){
			 //System.out.println("Pressing continue 1191- from Method");
			 ctx.widgets.widget(1191).component(7).click();
		 }
		 if (ctx.widgets.widget(1187).component(7).visible()){//a adialogue that appears in Stolen Hearts
			 //System.out.println("Pressing continue 1187- from Method");
			 ctx.widgets.widget(1187).component(7).click();
		 }
		 sleep(Random.nextInt(200, 500));
		
	}
	public boolean isChatting(final String p) {
		
		if(ctx.widgets.widget(1184).valid()||
				ctx.widgets.component(1191,0).visible()||
				ctx.widgets.component(1187,0).visible()||
				ctx.widgets.component(1188,0).visible()||
				ctx.widgets.component(1189,0).visible()||
				ctx.widgets.component(1186,0).visible()){
			state("Speaking to: " + p);
			pressContinue();
			return true;
		}
		//System.out.println("Returning false");
		return false;
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
			sleep(Random.nextInt(100,400));
			}else {
				ctx.camera.turnTo(y.tile().derive(2, 3));
			} 
				}
	}
	public boolean getPastDoor(Tile tileCanReach, Tile tilePastDoor, int doorID) {
		if(tilePastDoor.matrix(ctx).reachable()){
			return true;
		}else
		if(tileCanReach.matrix(ctx).reachable()&&
				getToNearByTile(tileCanReach)){
			interactO(doorID,"","The door");
		}
		return false;
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
	public int bankAmount(int items) {
		ItemQuery<Item> bankstuff;
		bankstuff = null;
		bankstuff = ctx.bank.select();
		
		for(Item item: bankstuff){
				if(ctx.bank.contains(item)&&item.id()==items){//if bank has item and inventory has it but not enough of it
					return item.stackSize();
				}
			
			
		}
		System.out.println("Returning FALSE for bank having atleast one item");
		
		return 0;
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
