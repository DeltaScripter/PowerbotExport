package slayer;

import java.util.ArrayList;

import org.powerbot.script.lang.BasicNamedQuery;
import org.powerbot.script.lang.ItemQuery;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import org.powerbot.script.methods.Hud.Window;
import org.powerbot.script.util.Random;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Item;
import org.powerbot.script.wrappers.Npc;
import org.powerbot.script.wrappers.Tile;

import quests.DeltaQuester;
import quests.Paths;
import quests.Vars;
import quests.Vars.TeleportLode;
import quests.Vars.TeleportType;

public class SMethod extends MethodProvider{

	public SMethod(MethodContext arg0) {
		super(arg0);
	}
	public enum TeleportLode{
		
		VARROCK(new Tile(3214,3378,0)),FALADOR(new Tile(2965,3402, 0)), LUMMBRIDGE(new Tile(3233,3221, 0)),
		BURTHORPE(new Tile(2899,3543, 0)),CATHERBY(new Tile(2831,3451,0)),PORTSARIM(new Tile(3011,3217,0)),
		DRAYNOR(new Tile(3107,3298,0)),ARDOUGNE(new Tile(2634,3348,0)), YANILLE(new Tile(2530,3095,0)),
		SEERS( new Tile(2689,3482,0)),TAVERLY(new Tile(2877,3441,0));
		Tile tile;
		TeleportLode(Tile tile){
			this.tile = tile;
		}
		public Tile getTile(){
			return tile;
		}
	}
	public enum TeleportType{
		ARDOUGNE(41,"Ardougne"),BURTHHORPE(42,"Burthorpe"),CATHERBY(43,"Catherby"),DRAYNOR(44,"Draynor"),
		FALADOR(46,"Falador"),LUMBRIDGE(47,"Lumbridge"),PORTSARIM(48,"Port Sarim"),
		SEERS(49,"Seers"),TAVERLY(50,"Taverly"),VARROCK(51,"Varrock"),YANILLE(52,"Yanille");
		int type;
		String name;
		int numMatch[] = {40,41,42,43,44,45,46,47,48,49,50,51,52,53};
		
		TeleportType(int type, String name){
			this.type = type;
			this.name = name;
		}
		 public int getTeleport(){
			 return type;
		 }
		 public String getName(){
			 return name;
		 }
		
	}
	public boolean backPackIsFull() {
		ArrayList<Integer> inventory = new ArrayList<Integer>();
		for(Item i : ctx.backpack.getAllItems()){
				if(i.getId()!=-1)
				inventory.add(i.getId());
		}
		return inventory.size()>=28;
	}
	private boolean teleBank = false;
	private boolean once = false;
	public void bankItems(int[] bankItems, int[] amountOfItem) {
		ArrayList<Integer> itemsInBank= new ArrayList<Integer>();
		
		if(new Tile(2947,3368, 0).distanceTo(ctx.players.local().getLocation())<6){
		if(ctx.bank.isOpen()){
			
			if(backPackIsFull()){
				System.out.println("Inventory is full, depositing.");
				ctx.bank.deposit(slayerbody.FOODID, bankItems.length);
			}
			/*while(!once){
				if(!ctx.bank.isOpen()){
					System.out.println("Bank is closed, breaking");
					break;
				}
				if(ctx.bank.depositInventory()){
					state("Depositing");
					once = true;
				}
			}*/
			
			//Find all items in bank and store them
			for(Item bankItem: ctx.bank.select()){
				if(!itemsInBank.contains(bankItem.getId()))
					itemsInBank.add(bankItem.getId());
			}
			//Grabs the items we need from the bank
			for(int i = 0; i<bankItems.length;){
				
				if(!ctx.bank.isOpen())
					break;
				
				if(!itemsInBank.contains(bankItems[i])){
					System.out.println("Cannot find item with ID: " + bankItems[i]);
					i++;
				}else
				if(itemIsStackable(bankItems[i])>amountOfItem[i]){
					i++;
				}else if(inventoryGetCount(bankItems[i])==amountOfItem[i]){
					i++;
				}else if(inventoryGetCount(bankItems[i])>amountOfItem[i]){
					state("Depositing item of overabundunce");
					ctx.bank.deposit(bankItems[i],(inventoryGetCount(bankItems[i])-amountOfItem[i]) );
				}else {
					System.out.println("Amount of item currently in inventory: "+inventoryGetCount(bankItems[i]));
					ctx.bank.withdraw(bankItems[i], (amountOfItem[i]-inventoryGetCount(bankItems[i])));
					ctx.game.sleep(2000);
				}
			}
			while(inventoryGetCount(slayerbody.FOODID)<(28 - (bankItems.length+5))){
				state("Attempting to withdraw food");
				if(!ctx.bank.isOpen()){
					System.out.println("Bank is closed, breaking");
					break;
				}
				if(!itemsInBank.contains(slayerbody.FOODID)){
					System.out.println("Out of food!, breaking out of loop");
					break;
				}
				System.out.println("Taking out "+(28 - (bankItems.length) -inventoryGetCount(slayerbody.FOODID))+ " of food");
				ctx.bank.withdraw(slayerbody.FOODID, ((28 - (bankItems.length+5)) -inventoryGetCount(slayerbody.FOODID)));
			}
			once = false;
			slayerbody.goBank = false;
			
		}else{
			state("Opening bank");
			ctx.bank.open();
		}
	 }else if(teleBank){
		   walkTo(new Tile(2947,3368, 0),"Falador west bank");
		}else if(TeleportLode.FALADOR.getTile().distanceTo(ctx.players.local().getLocation())<10){
			teleBank = true;
		}else teleportTo(TeleportType.FALADOR.getTeleport(),"Falador");
		
	}

	private int itemIsStackable(int id) {
		for(Item i:ctx.backpack.select().id(id)){
			return i.getStackSize();
		}
		return 0;
	}

	public int inventoryGetCount(int i) {
		if(!ctx.hud.isOpen(Window.BACKPACK) || !ctx.hud.isVisible(Window.BACKPACK))
			ctx.hud.open(Window.BACKPACK);
		
		ItemQuery<Item> g;
		g = null;
		g = ctx.backpack.select().id(i);
		for(Item h:ctx.backpack.select().id(i)){
		 if(h.getStackSize()>2){
			 return h.getStackSize();
		 }
		}
	
		return g.count(false);
	}
	private Web web;
	Timer wait = new Timer(0);
	public void state (String txt){
		slayerbody.state = txt;
	}
	public boolean byCloseLoc(Tile loc, int dist) {
		Tile local = ctx.players.local().getLocation();
		
		if(local.distanceTo(loc)<dist){
			return true;
		}else clickOnMap(loc);
		
		return false;
	}
	public boolean objIsByTile(Tile tile, int object, int dist) {
		for(GameObject obj : ctx.objects.select().id(object).nearest(tile)){
			if(obj.getLocation().distanceTo(tile)<dist){
				return true;
			}
		}
		return false;
	}
	public boolean canMakePath(Tile dest) {
		 web = new Web(ctx, ctx.players.local().getLocation(), dest );
			
			if(web.getPath()!=null && web.walk()){
				//System.out.println("path is not null");
				return true;
			}
		return false;
	}
	static ArrayList<Tile> path = new ArrayList<Tile>();
	public ArrayList<Tile> walkTo (Tile dest, String destName) {
		
		  web = new Web(ctx, ctx.players.local().getLocation(), dest );
			
			if(web.getPath() != null && !wait.isRunning()) {
				state("Walking to "+destName);
				wait = new Timer(Random.nextInt(3000, 4000));
				web.walk();
				
			}
		return null;
	}
	public void interactO(final int i, final String string, final String o) {
		for(GameObject y: ctx.objects.select().id(i).nearest().first()){
			if(y.isOnScreen() && y.interact(string)){
			ctx.game.sleep(Random.nextInt(1200, 1600));
			}else {
				ctx.camera.turnTo(y.getLocation().randomize(2, 3));
			}
		}
		
	}
	private Timer clickOnMap = new Timer(0);
	public void clickOnMap (Tile loc){
		if(!clickOnMap.isRunning()){
		ctx.movement.stepTowards(ctx.movement.getClosestOnMap(loc));
		clickOnMap = new Timer(Random.nextInt(1200, 2500));
		}
		
	}
	
	private Timer timer = new Timer(0);
	public void teleportTo(int loc, String teleName) {
		final int[] widgetsInterference = {1184,1189,1244,105,1191,149,1199,438,1242,1186,1188,1350,149,667};
		if(!timer.isRunning()){
	
			
		for(int i: widgetsInterference){
			if(ctx.widgets.get(i,0).isVisible()){
				state("Clicking on map to close dialogue");
				clickOnMap(ctx.players.local().getLocation());
				timer = new Timer(2000);
			}
		}
		
		if(ctx.bank.close() && ctx.widgets.get(1092,loc).isVisible()){//lodestone screen
			state("Selecting teleport: " + teleName);
			ctx.mouse.move(ctx.widgets.get(1092).getComponent(loc).getCenterPoint());
			ctx.widgets.get(1092).getComponent(loc).click(true);
			timer = new Timer(6000);
		}else {
			if (!ctx.players.local().isInCombat())
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
	
	
	public boolean objIsNotNull(int id) {
		if(!ctx.objects.select().id(id).first().isEmpty()){
			return true;
		}
		return false;
	}
	public void displayTileDifference(Tile tile) {
		state("X: " + (ctx.players.local().getLocation().getX()-tile.getX()) + "Y: "+ (ctx.players.local().getLocation().getY()-tile.getY()));
		
	}
	public boolean handleDoor(Tile tile, int id, String action) {
         Tile local = ctx.players.local().getLocation();
         
         if(tile.distanceTo(local)<20 && objIsByTile(tile,id,8)){
        	 state("Handling door");
        	 if(byCloseLoc(tile,8)){
        		 interactO(id,action,"Door");
        		 return true;
        	 }
        	 return true;
         }
		return false;
	}
	public int getInteractingNPCID()
    {
        final BasicNamedQuery<Npc> npcs = ctx.npcs.select();
       
        if(npcs != null && npcs.size() > 0)
            for(final Npc npc : npcs)
                if(npc.getInteracting() != null && npc.getInteracting().equals(ctx.players.local())){
           
                	return npc.getId();
                }
        return 0;
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
	public void pressContinue(){
		 int widgetID[] = {1191,1184,1187};
		 if(ctx.widgets.get(1186,3).isVisible()){
			 ctx.widgets.get(1186,3).click();
			 ctx.environment.sleep(1200);
		 }else if(ctx.widgets.get(1189,4).isVisible()){
			 ctx.widgets.get(1189,4).click();
			 ctx.environment.sleep(1200);
		 }
		for(int both : widgetID){
		    if(ctx.widgets.get(both,14).isVisible()){
		    	ctx.widgets.get(both, 14).click();
		    	ctx.environment.sleep(500);
		    }
		}
	}
	private boolean closeInterfaces() {
		int widgetInterference[] = {1188,1092,1186,1189};
		
		while((ctx.widgets.get(1244,23).isVisible())){//completed quest screen
			state("Closing completed quest screen");
				ctx.widgets.get(1244,23).click(true);
		}
			while(ctx.bank.isOpen()){
				ctx.bank.close();
			}
		for(int index: widgetInterference){
			
			if(ctx.widgets.get(index,0).isVisible()){
				System.out.println("Closing an interaface");
				if(index==1186){
					pressContinue();
				}else{
					state("Clicking on map to close dialogue");
				clickOnMap(ctx.players.local().getLocation());
				return false;
				}
			  }
			}
		return true;
	}
	Timer SpeakTotimer = new Timer(0);
	public void npcInteract(int i, String string) {
		
		for(Npc n : ctx.npcs.select().id(i).nearest().first()){
				if(n.isOnScreen()&&!n.interact(string)){
					ctx.camera.turnTo(n.getLocation().randomize(1, 3));
				}else ctx.camera.turnTo(n.getLocation().randomize(1, 3));
			}
		
	}

	Timer waitClick = new Timer(0);
	public void fightNPC(int id, String action) {
		if(ctx.combatBar.isExpanded()){
			//System.out.println("ID: "+getInteractingNPCID());
			if(!ctx.players.local().isInCombat() ||
					getInteractingNPCID()!=id&&!ctx.players.local().isInCombat()){//if not in combat
				
				for(Npc enemy: ctx.npcs.select().id(id).nearest().first()){
					if(enemy.getLocation().distanceTo(ctx.players.local().getLocation())<7){
						npcInteract(enemy.getId(),action);
					}else ctx.movement.stepTowards(enemy.getLocation());
				}
			}else if(getInteractingNPC()!=null&&getInteractingNPCID()==id&&
					ctx.players.local().isInCombat() )
		for(int i = 0; i<9;){
			foodSupport();
			state("Fighting NPC");
			if(ctx.players.local().getHealthPercent()<60){
				System.out.println("Health percent less than 60, breaking for loop");
				break;
			}
			
			if(getInteractingNPC()==null){
				System.out.println("Breaking for loop, not in combat");
				break;
			}
			if(ctx.combatBar.getActionAt(i).isReady()){
				if(!waitClick.isRunning()){
				//System.out.println("Clicking action: "+ ctx.combatBar.getActionAt(i).getId());
					if(!ctx.combatBar.getActionAt(i).select()){
					ctx.combatBar.getActionAt(i).getComponent().click();
				    waitClick = new Timer(Random.nextInt(980, 1300));
					}else  waitClick = new Timer(Random.nextInt(980, 1300));
				}
			}else i++;
			
		}	
		}else state("Please open the action bar to continue");
		
	}
	public void fightNPC(int id, String action, boolean specialFinish) {
		if(ctx.combatBar.isExpanded()){
			//System.out.println("ID: "+getInteractingNPCID());
			if(!ctx.players.local().isInCombat() ||
					getInteractingNPCID()!=id&&!ctx.players.local().isInCombat()){//if not in combat
				for(Npc enemy: ctx.npcs.select().id(id).nearest().first()){
					if(enemy.getLocation().distanceTo(ctx.players.local().getLocation())<7){
						npcInteract(enemy.getId(),action);
					}else ctx.movement.stepTowards(enemy.getLocation());
				}
			}else if(getInteractingNPC()!=null&&getInteractingNPCID()==id&&
					ctx.players.local().isInCombat() )
		for(int i = 0; i<9;){
			foodSupport();
			state("Fighting NPC");
			if(ctx.players.local().getHealthPercent()<60){
				System.out.println("Health percent less than 60, breaking for loop");
				break;
			}
			
			if(specialFinish && getInteractingNPC()!=null
					&& getInteractingNPC().getHealthPercent()<2){
				System.out.println("Breaking for special move");
				break;
			}
			
			if(getInteractingNPC()==null){
				System.out.println("Breaking for loop, not in combat");
				break;
			}
			if(ctx.combatBar.getActionAt(i).isReady()){
				if(!waitClick.isRunning()){
				//System.out.println("Clicking action: "+ ctx.combatBar.getActionAt(i).getId());
				ctx.combatBar.getActionAt(i).getComponent().click();
				  waitClick = new Timer(750);
				}
			}else i++;
			
		}	
		}else state("Please open the action bar to continue");
		
	}
	private boolean hasFood = true;
	Timer combatTimer = new Timer(0);
	public void foodSupport() {
		if(ctx.widgets.get(1430,83).isVisible()){//health bar
			double barHealth = ctx.widgets.get(1430,83).getChild(3).getWidth();
			double maxHealth = ctx.widgets.get(1430,83).getChild(1).getWidth() + 32;
			slayerbody.health = (barHealth/maxHealth)*100;
		}
		 
		 if(getInteractingNPC()!=null){//if fighting
				combatTimer = new Timer(4000);
		}
		if(slayerbody.FOOD_FEATURE) {
			if (slayerbody.health<55) {
				if(hasFood){
				if(inventoryContains(slayerbody.FOODID)){
					state("Food support initiated");
					interactInventory(slayerbody.FOODID, "Eat","Food");
					ctx.environment.sleep(1700, 2000);
					}else hasFood = false;
				}
			}
		}
	}
	public boolean inventoryContains(String i) {
		if(!ctx.hud.isVisible(Window.BACKPACK)){
			while (ctx.bank.isOpen()){
				ctx.bank.close();
			}
			ctx.hud.view(Window.BACKPACK);
		}
			while (!ctx.backpack.select().name(i).isEmpty()) {
				return true;
			}
		
		return false;
	}
	public boolean inventoryContains(int i) {
		if(!ctx.hud.isVisible(Window.BACKPACK)){
			while (ctx.bank.isOpen()){
				ctx.bank.close();
			}
			ctx.hud.view(Window.BACKPACK);
		}
			while (!ctx.backpack.select().id(i).isEmpty()) {
				return true;
			}
		
		return false;
	}
	public void interactInventory(final int i, final String string, final String o) {
		ArrayList<String> actions = new ArrayList<String>();
		
		 if(!timer.isRunning()){
		for(Item t : ctx.backpack.select().id(i).first()){
			//System.out.println(ctx.widgets.get(1477,122).getChild(0).getBoundingRect().getCenterY());
			if(ctx.hud.view(Window.BACKPACK) && closeInterfaces() && ctx.widgets.get(1473,7).contains(
				t.getComponent().getCenterPoint())){
				System.out.println("Hovering");
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
						if(closeInterfaces())
						if(t.interact(string)){
						System.out.println("Using " + string + " with item: " + o);
						 timer = new Timer(5500);
						}
					}
				}
				 
			}else
			if(ctx.widgets.get(1473,7).getBoundingRect().getCenterY()>
			t.getComponent().getBoundingRect().getCenterY()){
				state("Scrolling through inventory");
				ctx.mouse.move(ctx.widgets.get(1473, 7).getAbsoluteLocation());
				ctx.mouse.scroll(false);
			}else {
				state("Scrolling through inventory");
				ctx.mouse.move(ctx.widgets.get(1473, 7).getAbsoluteLocation());
				ctx.mouse.scroll(true);
				}
			}
		}else System.out.println("timer1 running");
	}
	public boolean isChatting(final String p) {
		int[] widgets = {1184,1191,1187};
		
		for(int w : widgets){
			if(ctx.widgets.get(w).isValid() && ctx.widgets.get(w,11).isVisible()){
				state("Speaking to: " + p);
				SpeakTotimer = new Timer(5000);
				pressContinue();
				ctx.environment.sleep(900,1300);
				return true;
			}
		}
		ctx.environment.sleep(1200,1300);
		return false;
	}
	public  boolean findOption(String[] text) {
		Vars Vars = new Vars();
		if(!ctx.widgets.get(1188).isValid()){
			return false;
		}
		for (String t:text) {
			ctx.environment.sleep(20,50);
			for (int i :Vars.OPTIONVALUE) {
				if (ctx.widgets.get(1188).isValid()&&ctx.widgets.get(1188, i).getText().contains(t)) {
					state("Attempting to click option");
					ctx.mouse.click(ctx.widgets.get(1188, i).getAbsoluteLocation().x+10,ctx.widgets.get(1188, i).getAbsoluteLocation().y+3 , true);
					return true; 
				}
			}
		}
		return false;
	}
	public void useItemOnNpc(int item, int npc, String string) {
		state("Using item on npc");
		if(closeInterfaces())
		if(ctx.backpack.isItemSelected()){
			npcInteract(npc,"Use");
		}else if(inventoryContains(item))
			interactInventory(item,"Use","Item");
		
	}
	public void setGeneralCamera() {
		if(ctx.camera.getPitch()<50){
			state("Setting camera pitch");
			ctx.camera.setPitch(60);
		}
		
	}
}
