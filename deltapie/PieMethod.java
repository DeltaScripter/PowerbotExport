package deltapie;
import java.util.ArrayList;

import org.powerbot.script.lang.ItemQuery;
import org.powerbot.script.methods.Hud.Window;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import org.powerbot.script.wrappers.Item;


public class PieMethod extends MethodProvider{
	public PieMethod(MethodContext arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	public void combineItems(int item1, int item2) {
			if(ctx.backpack.isItemSelected()){
				interactInventory(item2,"Use", "Item 2");
			}else interactInventory(item1,"Use","Item 1");
	}
	public int inventoryGetCount(int id) {
		if(!ctx.bank.isOpen())
			ctx.hud.view(Window.BACKPACK);
		ItemQuery<Item> g;
		g = null;
		g = ctx.backpack.select().id(id);
	
		return g.count(false);
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
	   
	public boolean inventoryContains(int i) {
		if(!ctx.hud.isVisible(Window.BACKPACK)){
			ctx.hud.view(Window.BACKPACK);
			sleep(100);
		}
			while (!ctx.backpack.select().id(i).first().isEmpty()) {
				return true;
			}
		
		return false;
	}
  
	public void interactInventory(final int i, final String string, final String o) {
		ArrayList<String> actions = new ArrayList<String>();
		for(Item t : ctx.backpack.select().id(i).first()){
			//System.out.println(ctx.widgets.get(1477,122).getChild(0).getBoundingRect().getCenterY());
			if(ctx.hud.view(Window.BACKPACK) && ctx.widgets.get(1473,7).contains(
				t.getComponent().getCenterPoint())){
				System.out.println("Hovering");
				t.hover();
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
}
