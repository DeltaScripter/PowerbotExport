package unicow;

import java.util.ArrayList;

import org.powerbot.script.lang.BasicNamedQuery;
import org.powerbot.script.lang.ItemQuery;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import org.powerbot.script.methods.Hud.Window;
import org.powerbot.script.util.Random;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.GroundItem;
import org.powerbot.script.wrappers.Item;
import org.powerbot.script.wrappers.Npc;
import org.powerbot.script.wrappers.Tile;
import org.powerbot.script.wrappers.TilePath;

public class UniMethod extends MethodProvider{

	public UniMethod(MethodContext arg0) {
		super(arg0);
	}
	public boolean walk(final Tile[] PATH, String dest) {
		DeltaUniBody.state = dest;
	     TilePath path = ctx.movement.newTilePath(PATH);
	     
	     return path.randomize(2, 3).traverse();
	}


	public void clickOnMap(Tile loc){
		System.out.println("Trying/doing stepTowards");
		if(!ctx.movement.stepTowards(loc)){
			loc.randomize(2, 3).getMatrix(ctx).click();
		}
		
		ctx.game.sleep(Random.nextInt(30, 60));
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
	public void npcInteract(int id, String string) {
		ArrayList<String> actions = new ArrayList<String>();
		for(Npc n : ctx.npcs.select().id(id).nearest().first()){
				if (n.isOnScreen()) {
					n.hover();
					n.interact(string);
				} else ctx.camera.turnTo(n.getLocation().randomize(2, 3));
			}
	}
	public GroundItem getGroundItem(int i) {
		for(GroundItem item : ctx.groundItems.select().id(i).nearest().first()){
			return item;
		}
		return null;
	}
	public Npc getInteractingNPC()
    {
        final BasicNamedQuery<Npc> npcs = ctx.npcs.select();
       
        if(npcs != null && npcs.size() > 0)
            for(final Npc npc : npcs)
                if(npc.getInteracting() != null && npc.getInteracting().equals(ctx.players.local()))
                    return npc;
        return null;
    }
 
        public boolean isInCombat()
    {
        return getInteractingNPC() == null&&ctx.players.local().getInteracting() == null;
    }
 
	
	
	Timer waitClick = new Timer(0);
	public void fightNPC(int id) {
		
		if(ctx.combatBar.isExpanded()){
			
			if(getInteractingNPC()==null && !waitClick.isRunning()){//if not in combat
				for(Npc enemy: ctx.npcs.select().id(id).nearest().first()){
					if(enemy.getLocation().distanceTo(ctx.players.local().getLocation())<7){
						DeltaUniBody.state = "Attacking npc";
						ctx.game.sleep(2000);
						npcInteract(enemy.getId(),"Attack");
					}else ctx.movement.stepTowards(enemy.getLocation());
				}
			}else
		for(int i = 0; i<9;){
			  UniMain.waitFight = new Timer(Random.nextInt(2300, 2700));//sleeps a tiny bit after a fight
			
			DeltaUniBody.state = "Fighting NPC";
			if(ctx.players.local().getHealthPercent()<40){
				System.out.println("Breaking; too low health");
				break;
			}
			
			if(getInteractingNPC()==null){
				System.out.println("Breaking for loop, not in combat");
				waitClick = new Timer(2000);
				break;
			}
			if(ctx.widgets.get(1184,0).isVisible())
				break;
			
			if(!ctx.players.local().isInCombat()){//in case we can't reach the cow + its attacking us
				for(Npc enemy: ctx.npcs.select().id(id).nearest().first()){
					if(enemy.getLocation().distanceTo(ctx.players.local().getLocation())<7&& !waitClick.isRunning()){
						DeltaUniBody.state = "Attacking npc";
						npcInteract(enemy.getId(),"Attack");
					}else ctx.movement.stepTowards(enemy.getLocation());
				}
			}else
			if(ctx.combatBar.getActionAt(i).isReady()){
				if(!waitClick.isRunning()){
				
							if (ctx.combatBar.getActionAt(i).select(true)) {
								i++;
								waitClick = new Timer(Random.nextInt(750, 1500));
								ctx.game.sleep(Random.nextInt(400, 200));
							} else {
								ctx.combatBar.getActionAt(i).getComponent().click();
								i++;
								waitClick = new Timer(Random.nextInt(750, 1500));
							}
						}
			}else i++;
			
			
		}	
			
			
		}else System.out.println("Please open the actionbar");
		
	}
	public boolean equipItemIsNotNull(int id) {
		if(!ctx.equipment.select().id(id).first().isEmpty()){
			return true;
		}
		return false;
	}
	public boolean gItemIsNotNull(int id) {
		if(!ctx.groundItems.select().id(id).nearest().first().isEmpty()){
			return true;
		}
		return false;
	}
	public boolean npcIsNotNull(int id) {
		if(!ctx.npcs.select().id(id).nearest().first().isEmpty()){
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
		if(!ctx.bank.isOpen())
			ctx.hud.view(Window.BACKPACK);
		ItemQuery<Item> g;
		g = null;
		g = ctx.backpack.select().id(id);
	
		return g.count(false);
	}
	public boolean bankContains(int ID) {
		
		if(!ctx.bank.isOpen())
			return false;
		
			while (!ctx.bank.select().id(ID).first().isEmpty()) {
				return true;
			}
		
		return false;
	}
	public void interactInventory(final int id, final String string, final String o) {
		ArrayList<String> actions = new ArrayList<String>();
		for(Item t : ctx.backpack.select().id(id).first()){
			if(ctx.hud.view(Window.BACKPACK) && ctx.widgets.get(1473,7).contains(
				t.getComponent().getCenterPoint())){
				t.hover();
				ctx.game.sleep(200);
				String[] menuItems = ctx.menu.getItems();
				for(String opt: menuItems){
					if(!actions.contains(opt)){
						actions.add(opt);
					}
				}
				for(String text: actions){
					if(text.contains(string)){
						if(t.interact(string)){
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
	public void useEquipmentItem(int id, String action){
		for(Item item : ctx.equipment.select().id(id).first()){
			if(ctx.hud.view(Window.WORN_EQUIPMENT)){
				
				if(!waitClick.isRunning()){
				item.interact(action);
				waitClick = new Timer(Random.nextInt(2500, 3400));
				}
			}
		}
	}
	Timer interactO = new Timer(0);
	public void interactO(final int id, final String string, final String o) {
		ArrayList<String> actions = new ArrayList<String>();
		for(GameObject y: ctx.objects.select().id(id).nearest().first()){
	
					if (y.isOnScreen()) {
						if(!interactO.isRunning()){
							if(y.interact(string)){
								interactO = new Timer(Random.nextInt(1700, 2300));
							}else {
								ctx.camera.setPitch(Random.nextInt(60, 80));
								ctx.camera.turnTo(y.getLocation().randomize(2, 3));
							}
						}
					} else ctx.camera.turnTo(y);
				
				}
		
	}
	Timer timer = new Timer(0);
	public void teleportTo(int loc, String teleName) {
		while(ctx.bank.isOpen())
			ctx.bank.close();
		
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

	public void wahlk(Tile[] path, String dest) {
		DeltaUniBody.state = dest;
		ctx.movement.newTilePath(path).randomize(1, 2).traverse();
		
	}

	public void state(String string) {
		DeltaUniBody.state = string;
		
	}
	public boolean equipmentContains(int id) {
		if(ctx.hud.view(Window.WORN_EQUIPMENT)){
			
		for(Item g : ctx.equipment.select().id(id).first()){
			if(g.getId()==id)
				return true;
		}
		}
		return false;
	}
	public int inventoryGetCount(int[] ringIDs) {
		for(int i: ringIDs){
			if(inventoryGetCount(i)>=1){
				return inventoryGetCount(i);
			}else if(i==ringIDs[ringIDs.length-1]){
				return 0;
			}
		}
		return 0;
	}
	public boolean bankContains(int[] ringIDs) {
		for(int i: ringIDs){
			if(bankContains(i)){
				return true;
			}else if(i==ringIDs[ringIDs.length-1]){
				return false;
			}
		}
		return false;
	}
}
