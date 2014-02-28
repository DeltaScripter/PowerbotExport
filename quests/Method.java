package quests;


import features.GrandExchange;
import features.Web;

import java.util.ArrayList;
import java.util.LinkedList;


import org.powerbot.script.lang.BasicNamedQuery;
import org.powerbot.script.lang.ItemQuery;
import org.powerbot.script.methods.Backpack;
import org.powerbot.script.methods.Hud.Window;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import org.powerbot.script.util.Condition;
import org.powerbot.script.util.Random;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.Action;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.GroundItem;
import org.powerbot.script.wrappers.Item;
import org.powerbot.script.wrappers.Npc;
import org.powerbot.script.wrappers.Player;
import org.powerbot.script.wrappers.Tile;


import quests.Vars.TeleportLode;
import quests.Vars.TeleportType;
import src.pathfinder.core.util.Structure;
import src.pathfinder.core.wrapper.PathNode;
import src.pathfinder.core.wrapper.TilePath;
import src.pathfinder.impl.Pathfinder;

public class Method extends MethodProvider{

		public Method(MethodContext ctx) {
		super(ctx);
	}
		static boolean closedInterface = false;
	    public boolean teleporting = false;
	    static Tile bankTile;
		static int life = 500;//Used for food support
		static int baseLife;
		static boolean hasFood = true;
		public static boolean useBank =true;
		public static int bankDecide = 5;
		
		static Timer timer = new Timer(0);
		private Timer interactO = new Timer(0);
		static Timer SpeakTotimer = new Timer(0);
		static Timer abilityTimer = new Timer(1);
		static Timer combatTimer = new Timer(1);
		public static int slot = 0;
		public static boolean depoBank = false;
		private Vars Vars = new Vars();
		GrandExchange Ge = new GrandExchange(ctx);
		public static boolean onlyItemsGE = false;
		
	
	
	
		public Npc getInteractingNPC()
	    {
	        final BasicNamedQuery<Npc> npcs = ctx.npcs.select();
	       
	        if(npcs != null && npcs.size() > 0)
	            for(final Npc npc : npcs)
	                if(npc.getInteracting() != null && npc.getInteracting().equals(ctx.players.local()))
	                    return npc;
	        return null;
	    }
	 
		Timer waitClick = new Timer(0);
		public boolean goBank =true;
		public void fightNPC(int id) {
			if(ctx.combatBar.isExpanded()){
				
				if(getInteractingNPC()==null){//if not in combat
					for(Npc enemy: ctx.npcs.select().id(id).nearest().first()){
						if(enemy.getLocation().distanceTo(ctx.players.local().getLocation())<7){
							npcInteract(enemy.getId(),"Attack");
						}else ctx.movement.stepTowards(enemy.getLocation());
					}
				}else
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
					System.out.println("Clicking action: "+ ctx.combatBar.getActionAt(i).getId());
					ctx.combatBar.getActionAt(i).getComponent().click();
					  waitClick = new Timer(750);
					}
				}else i++;
				
			}	
				
				
			}else state("Please open the action bar to continue");
			
		}
	public void useGE(String[] name, int[] itemID, int[] itemPrice, int[] itemAmount){
			
			
		if (new Tile(3178, 3479, 0).distanceTo(ctx.players.local().getLocation()) < 9) {
			while(!ctx.hud.isVisible(Window.BACKPACK)){
				state("Attempting to open backpack");
				if(Ge.geIsOpen())
				ctx.widgets.get(105,14).click();//close GE
				if(ctx.bank.isOpen())
					ctx.bank.close();//Close the bank
				else
				ctx.hud.view(Window.BACKPACK);
			}
			if (Ge.geIsOpen()) {
			for(int pos = 0; pos < itemID.length;){
				//state("Purchasing " + itemAmount[pos]+ " " + name[pos]+ " for "+itemPrice[pos]+ "GP" );
				if (Ge.geIsOpen()) {
					if(ctx.backpack.getMoneyPouch() < itemPrice[0]){
						System.out.println("not enough money in pouch");
						DeltaQuester.g = true;
						break;
					}else
					if(inventoryContains(itemID[pos])){
						if((pos+1)==itemID.length){
							while(ctx.widgets.get(105,87).isVisible()){
								state("Closing GE");
								ctx.widgets.get(105,87).click();
							}
							DeltaQuester.GEFeature=false;
							break;
						}else pos++;
					}else
					if(Ge.isSlotEmpty()){
						Ge.createBuyItem(name[pos], itemAmount[pos],itemPrice[pos]);
						ctx.game.sleep(1000);
					}else {
						ctx.game.sleep(1000);
						Ge.collectItem(name[pos]);
					}
				}else break;
			}
			
			}else if(ctx.bank.isOpen()){
				ctx.bank.close();
			}else npcInteract(2241, "Exchange");
		} else getToExchange();
	}

	public int inventoryStackSize(int ID) {
		if(ctx.hud.view(Window.BACKPACK)){
		
		ItemQuery<Item> g;
		
		g = null;
		g = ctx.backpack.select().id(ID);
		for(Item t:g){
			if(t.getId()==ID){
				return t.getStackSize();
			}
		}
		//return g.count(true);
		}
		return 0;
	}
	public boolean bankContains(int id) {
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
	public boolean isFaladorLodeAct(){
		if((ctx.settings.get(3) >>6 &1)==1){
			return true;
		}
		return false;
	}

	public  boolean isPortSarimLodeAct() {
		if ((ctx.settings.get(3) >> 8 & 1) == 1) {
			return true;
		}
		return false;
	}
	public  boolean YanilleLodeAct() {
		if ((ctx.settings.get(3) >> 12 & 1) == 1) {
			return true;
		}
		return false;
	}
	public  boolean TaverlyLodeAct() {
		if ((ctx.settings.get(3) >> 10 &0x1) == 1) {
			return true;
		}
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
	
	public void state(final String message) {
		DeltaQuester.state = message;
	}
	
	@SuppressWarnings("deprecation")
	public void speakTo(final int id, final String p) {
		if(!SpeakTotimer.isRunning()){
		
			for(Npc n: ctx.npcs.select().id(id).nearest().first()){
				
				if(closeInterfaces() && n.isOnScreen()){
					state("Attempting to speak to: " + p);
					n.interact("Talk");
					SpeakTotimer = new Timer(6500);
				}else {
					ctx.movement.newTilePath(n.getLocation()).traverse();
					ctx.camera.turnTo(n);
				}
		}
	}
	}
	public void foodSupport() {
		resetTeleporting();
		if(ctx.widgets.get(1430,83).isValid()){//health bar
			double barHealth = ctx.widgets.get(1430,83).getChild(3).getWidth();
			double maxHealth = ctx.widgets.get(1430,83).getChild(1).getWidth() + 32;
			DeltaQuester.health = (barHealth/maxHealth)*100;
		}
		 
		 if(getInteractingNPC()!=null){//if fighting
				combatTimer = new Timer(4000);
		}
		if(DeltaQuester.FOOD_FEATURE && ctx.players.local().getHealthPercent()<50){
			if(hasFood){
			if(inventoryContains(DeltaQuester.FOOD_ID)){
				state("Food support initiated");
				interactInventory(DeltaQuester.FOOD_ID, "Eat","Food");
				ctx.environment.sleep(1700, 2000);
				}else hasFood = false;
			}
		}
	}
	public void interactInventory(final String name, final String string, final String o) {
		ArrayList<String> actions = new ArrayList<String>();
		
		 if(!timer.isRunning()){
		for(Item t : ctx.backpack.select().name(name).first()){
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
						 timer = new Timer(2500);
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
	public void interactInventory(final int i, final String string, final String o) {
		ArrayList<String> actions = new ArrayList<String>();
		
		 if(!timer.isRunning()){
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
						 timer = new Timer(2500);
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
    public boolean isInCombat() {
        return ctx.players.local().getInteracting()!=null;
    }
    public void interactO(final String name, final String string, final String o) {
    	ArrayList<String> actions = new ArrayList<String>();
    	
		for(GameObject y: ctx.objects.select().name(name).nearest().first()){
			//if(closeInterfaces())
			//if(y.isOnScreen()){
			
			if(!y.interact(name)){
				System.out.println("Can't");
			}else System.out.println("Can");
			//if (closeInterfaces() && y.isOnScreen()) {
				/*y.hover();
				String menuItems[] = ctx.menu.getItems();
				for(String opt: menuItems){
					if(!actions.contains(opt))
						actions.add(opt);
					state("Investigating options..");
				}
				for(String text: actions){
					if(text.contains(string)){
						state("Interacting: " + string);
						   y.interact(string);
						  // sleep(2000);
					}
				}*/
		//	} else ctx.camera.turnTo(y);
		
			ctx.game.sleep(1800);
		//	}else ctx.camera.turnTo(y);
				}
		
	}
	public void interactO(final int i, final String string, final String o) {
		ArrayList<String> actions = new ArrayList<String>();
		for(GameObject y: ctx.objects.select().id(i).nearest().first()){
			//if(closeInterfaces())
			if(y.isOnScreen() && y.interact(string)){
			state("Interacting: " + string);
			ctx.game.sleep(1800);
			}else {
				ctx.camera.turnTo(y.getLocation().randomize(2, 3));
			}
			/*
					if (closeInterfaces() && y.isOnScreen()) {
						y.hover();
						String menuItems[] = ctx.menu.getItems();
						for(String opt: menuItems){
							if(!actions.contains(opt))
								actions.add(opt);
							state("Investigating options..");
						}
						for(String text: actions){
							if(text.contains(string)){
								   y.interact(string);
								  // sleep(2000);
								state("Using " + string + " on: " + o);
							}
						}
					} else ctx.camera.turnTo(y);
				*/
				}
		
	}
	private boolean closeInterfaces() {
		int widgetInterference[] = {1188,1092,1191,1184,1186,1189};
		
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
					state("Clicking on map to close dialogue - automatic");
				clickOnMap(ctx.players.local().getLocation());
				return false;
				}
			  }
			}
		return true;
	}
	public void npcInteract(String name, String string) {
		ArrayList<String> actions = new ArrayList<String>();
		if(!SpeakTotimer.isRunning()){
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
							state("Interacting with " + n.getName() + " and using " + string);
							 SpeakTotimer = new Timer(2400);
							 System.out.println("interacting");
							  n.interact(string);
							   sleep(2000);
							   break;
							
						}
					}
				} else ctx.camera.turnTo(n);
			}
		}
	}
	public boolean npcInteract(int i, String string) {
		ArrayList<String> actions = new ArrayList<String>();
		if(!SpeakTotimer.isRunning()){
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
							state("Interacting with " + n.getName() + " and using " + string);
							 SpeakTotimer = new Timer(2400);
							 System.out.println("interacting");
							  if(n.interact(string)){
							   sleep(2000);
							   System.out.println("Returning true for clicking on npc");
							   return true;
							  }
							
						}
					}
				} else ctx.camera.turnTo(n);
			}
		}
		return false;
	}


	public boolean VarrokLodeIsActive(){
		if((ctx.settings.get(3) >> 11 & 1)==1){
			return true;
		}
		return false;
	}
	public boolean FaladorLodeIsActive(){
		if((ctx.settings.get(3) >> 6 & 1)==1){
			return true;
		}
		return false;
	}
	
    public boolean DraynorLodeIsActive(){
    	  if((ctx.settings.get(3) >>4 &1)==1){
    		  return true;
    	  }
    	
    	return false;
    }	
    public boolean CatherbyLodeIsActive(){
  	  if((ctx.settings.get(3) >>3 &1)==1){
  		  return true;
  	  }
  	return false;
  }	
    public boolean ArdougneLodeIsActive(){
    	  if((ctx.settings.get(3) >>1 &1)==1){
    		  return true;
    	  }
    	return false;
    }	
    public boolean SeersLodeIsActive(){
  	  if((ctx.settings.get(3) >>9 &0x1)==1){
  		  return true;
  	  }
  	return false;
  }	
	public boolean startQuestOpen() {
		if(ctx.widgets.get(1500,0).isVisible()){
			state("Accepting quest offer");
			ctx.mouse.click(ctx.widgets.get(1500, 402).getCenterPoint(),true);
			return true;
		}
		return false;
	}
	
	public void walking(Tile[] t, String string, boolean dir){

		if(closeInterfaces())
		if(!dir && !SpeakTotimer.isRunning()){
			state(string);
			
			ctx.movement.newTilePath(t).randomize(2, 1).traverse();
			SpeakTotimer = new Timer(4800);
			}else if(!SpeakTotimer.isRunning()){
				state(string + ": Reverse");
				ctx.movement.newTilePath(t).reverse().traverse();
				SpeakTotimer = new Timer(3800);
			}
	}

	
	public void teleportTo(int loc, String teleName) {
		final int[] widgetsInterference = {1184,1189,1244,105,1191,149,1199,438,1242,1186,1188,1350,149,667};
		teleporting = true;
		if(!timer.isRunning()){
			
	   while(ctx.widgets.get(1477,47).getChild(2).isVisible()){//The task menu
			state("Closing task menu");
			ctx.widgets.get(1477,47).getChild(2).click(true);//The close button
		}
		while((ctx.widgets.get(1244,23).isVisible())){//completed quest screen
			state("Closing completed quest screen");
				ctx.widgets.get(1244,23).click(true);//continue option
		}
		while(ctx.widgets.get(438,22).isVisible()){//The annoying 'Recruit a friend' thing
			state("Closing advertisement");
			ctx.widgets.get(438,22).click(true);//The close button
		}
		while(ctx.widgets.get(1155,48).isVisible()){//The annoying 'Subscription advertisement' thing
			state("Closing advertisement");
			ctx.widgets.get(1155,48).click(true);//The close button
		}
		while(ctx.widgets.get(149,245).isVisible()){//The annoying 'Subscription advertisement'#2 thing
			state("Closing advertisement");
			ctx.widgets.get(149,245).click(true);//The close button
		}
			
		for(int i: widgetsInterference){
			if(ctx.widgets.get(i,0).isVisible()){
				state("Clicking on map to close dialogue for teleporting");
				clickOnMap(ctx.players.local().getLocation());
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
	
	public void basicFightNPC(int npc){
		Method m = new Method(ctx);
		if(ctx.combatBar.isExpanded()){
			
			if(!m.isInCombat()){
				System.out.println("Clicking attack on enemy");
				m.npcInteract(npc, "Attack");
			}else
				for(Action ab: ctx.combatBar.getActions()){
					if(ab.isReady() && !combatTimer.isRunning()){
						//state("Using ability: " + ab);
						//ab.select();
						combatTimer = new Timer(700);
						
					}
				}
			
		}else {state("Attempting to open");
			ctx.combatBar.setExpanded(true);
			combatTimer = new Timer(7000);
		}
		
	}
	
	public  void getToExchange() {
	    if (Vars.DYNAMICV){
	    	walking(Paths.pathToGE, "Walking to the Grand Exchange", false);
		}else if (TeleportLode.LUMMBRIDGE.getTile().distanceTo(ctx.players.local().getLocation())<5 || (TeleportLode.VARROCK.getTile().distanceTo(ctx.players.local().getLocation())<8)){	
			Vars.DYNAMICV = true;
			}else if(VarrokLodeIsActive()){
			teleportTo(TeleportType.VARROCK.getTeleport(), TeleportType.VARROCK.getName());
			}else teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
		
		
	}
	public void useItemOn(int item, int obj, String string) {
		state("Using item on object");
		if(closeInterfaces())
		if(ctx.backpack.isItemSelected()){
			interactO(obj, "Use", string);
		}else if(inventoryContains(item))
			interactInventory(item,"Use","Item");
		
	}
	public void useItemOnNpc(int item, int npc, String string) {
		state("Using item on npc");
		if(ctx.backpack.isItemSelected()){
			npcInteract(npc,"Use");
		}else if(inventoryContains(item))
			interactInventory(item,"Use","Item");
		
	}
	public void useItemOnG(int item, int obj, String string) {
		Method m = new Method(ctx);
		m.state("Using item on ground item");
		skipPics();
		if(!isChatting("Self"))
		if(ctx.backpack.isItemSelected()){
			m.interactG(obj, "Use", string);
		}else if(m.inventoryContains(item))
			for(Item i: ctx.backpack.select().id(item).first()){
					i.interact("Use");
		}
		
	}
	public void skipPics() {
		if(ctx.widgets.get(1189).isValid() || ctx.widgets.get(1186).isValid()){
			//state("Skipping special chat");
			 int widgetID[] = {1189,1186};
			 
		
				while(ctx.widgets.get(1155,48).isVisible()){//The annoying 'Subscription advertisement' thing
					state("Closing advertisement");
					ctx.widgets.get(1155,48).click(true);//The close button
				}
				while(ctx.widgets.get(149,245).isVisible()){//The annoying 'Subscription advertisement'#2 thing
					state("Closing advertisement");
					ctx.widgets.get(149,245).click(true);//The close button
				}
				 
				for(int both : widgetID){
				    if(ctx.widgets.get(both).isValid()){
				    	ctx.widgets.get(both, 11).click();
				    	ctx.widgets.get(both, 7).click();
				    }
				}
		}
		
	}
	public boolean playerText(String string) {
		if (ctx.widgets.get(137,89).isValid()) {
			//state("Checking: " + string);
			if (ctx.widgets.get(137,89).getChild(0).getText()
					.contains(string)) {
				System.out.println("returning true for player text");
				return true;
			}
		}
		return false;
	}
	public void resetTeleporting() {
		if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(ctx.players.local().getLocation())<10 || TeleportLode.VARROCK.getTile().distanceTo(ctx.players.local().getLocation())<10 || TeleportLode.DRAYNOR.getTile().distanceTo(ctx.players.local().getLocation())<10||
				TeleportLode.PORTSARIM.getTile().distanceTo(ctx.players.local().getLocation())<10|| TeleportLode.ARDOUGNE.getTile().distanceTo(ctx.players.local().getLocation())<10|| TeleportLode.YANILLE.getTile().distanceTo(ctx.players.local().getLocation())<10||
				TeleportLode.BURTHORPE.getTile().distanceTo(ctx.players.local().getLocation())<10|| TeleportLode.CATHERBY.getTile().distanceTo(ctx.players.local().getLocation())<10|| TeleportLode.FALADOR.getTile().distanceTo(ctx.players.local().getLocation())<10
				){
			teleporting = false;
		}
	}
	public boolean interference() {
		while(ctx.widgets.get(1186).isValid() && ctx.widgets.get(1186,2).getText().contains("are now leaving the")){//18
			if(ctx.widgets.get(1186,3).isValid()){
		    	ctx.widgets.get(1186, 3).click();
		    	return true;
		    }else break;
			
		}
		if(ctx.widgets.get(1188,0).isVisible() && ctx.widgets.get(1188,11).getText().contains("Leave the st")){
			ctx.mouse.click(ctx.widgets.get(1188, 11).getAbsoluteLocation().x+10,ctx.widgets.get(1188, 11).getAbsoluteLocation().y+3 , true);
			
			return true;
		}
	 return false;
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
		    if(ctx.widgets.get(both,14).isValid()&&
		    		ctx.widgets.get(both,14).isVisible()){
		    	ctx.widgets.get(both, 14).click();
		    	ctx.environment.sleep(500);
		    }
		}
	}
	public void determineBank(int[] items){
		int invspace= 28;
		int itemsNeeded = 0;
		int foodspace;
		ArrayList<String> inv = new ArrayList<String>();//all item in inv
		ArrayList<String> itemCount = new ArrayList<String>();//counts the item in the above array that you need
		Item[] inventory = null;
		inventory = ctx.backpack.getAllItems();
		
		if(!Vars.ranOnce){
			//state("Determining if you need to bank");
			for(Item i : inventory){
				//System.out.println("Checking inv items");
				if(i.getId()!=-1)
					inv.add(i.getName().toString());
			}
				for(Item item : inventory){
					//System.out.println("Someting else");
				   for(int i: items){
					   if(item.getId()==i && !itemCount.contains(item.getName())){
						   itemCount.add(item.getName());
					   }
				   }
				}
				
				invspace = invspace - inv.size();
				itemsNeeded = items.length - itemCount.size();
				foodspace = invspace - 3;
				
				 if(invspace<itemsNeeded){
					Vars.useBank = true;
					Vars.ranOnce = true;
				}else {
					Vars.useBank = false;
					Vars.ranOnce = true;
				}
				 for(int bankitem: items){
						if(Vars.bankItems.contains(bankitem)){
							System.out.println("Found item!");
							Vars.useBank = true;
							Vars.ranOnce = true;
						}else System.out.println("can't find item in bank" + bankitem);
					}
				 if(DeltaQuester.FOOD_FEATURE){
						if(inventoryGetCount(DeltaQuester.FOOD_ID)<=foodspace && Vars.bankItems.contains(DeltaQuester.FOOD_ID)){
							Vars.useBank = true;
							Vars.ranOnce = true;
						}
					}
		}//else System.out.println("ranOnce: " + Vars.ranOnce);
	}
	public void useBank(int[] items, int[] amount) {
		final int destroyableItems[] = {19775,27156,26480,25131};
		boolean donesearch = false;
		
		if(!ctx.hud.isVisible(Window.BACKPACK))
			ctx.hud.view(Window.BACKPACK);
		
		int freespace = 28 - items.length;
		bankTile =new Tile(3180, 3482, 0);
		int foodspace = freespace - 5;
		System.out.println("bankTile: "+ bankTile + "DeltaQuester.number is : " + DeltaQuester.number);
		if (bankTile.distanceTo(ctx.players.local().getLocation()) < 8) {
			
			//Destroy unbankable items!
			for(int destroyItemID: destroyableItems){
				while(inventoryContains(destroyItemID)){
					
					if(ctx.widgets.get(1189,0).isValid()&&
							ctx.widgets.get(1189,0).isVisible()){
						pressContinue();
					}
					if(ctx.bank.isOpen())//close bank if open
						ctx.bank.close();
					if(ctx.widgets.get(1183,16).isVisible()){//if the destroy screen is open
						ctx.widgets.get(1183,16).click();//click 'yes' to destroy item
						ctx.game.sleep(1000);
					}else interactInventory(destroyItemID,"Destroy","Item");
				}
			}
			
			if (ctx.bank.isOpen()) {
				
				
				//Determines depositing inventory.
					if(!depoBank){
						state("Attempting to deposit inventory");
						ctx.bank.depositInventory();
						ctx.environment.sleep(2000);
						if(ctx.backpack.isEmpty())
						depoBank = true;
					}else{
					
				ItemQuery<Item> bankstuff;
				bankstuff = null;
				bankstuff = ctx.bank.select();
				
				cacheBank();
				if(DeltaQuester.FOOD_FEATURE&&
						Vars.bankItems.contains(DeltaQuester.FOOD_ID) && inventoryGetCount(DeltaQuester.FOOD_ID)<foodspace){
					System.out.println("amount of food space: " + foodspace+" # of food in inv: "+inventoryGetCount(DeltaQuester.FOOD_ID));
						state("Taking out food");
						ctx.bank.withdraw(DeltaQuester.FOOD_ID, foodspace);
						ctx.environment.sleep(1400);
				}else{
				for(int checks = 0; checks<items.length;){
					state("Searching through bank..."  + checks);
				for(Item i: bankstuff){
				
					for(int pos = 0; pos<items.length;){
						if(i.getId()==items[pos]){
							state("Withdrawing: "+ items[pos]);
							ctx.bank.withdraw(items[pos], amount[pos]);
							
							if(!timer.isRunning()){
								pos++;
								timer = new Timer(1700);
							}
						}else {
							pos++;
							timer = new Timer(1200);
						}
					}
				}
				checks++;
				}
				ctx.bank.close();
				System.out.println("Turning bank off: " + donesearch);
				Vars.useBank = false;
					}
					  }
			} else openBank();
			
			
		} else getToBank();
	}
	
	private void getToBank() {
		if(bankTile.getLocation().distanceTo(ctx.players.local().getLocation())>7){
			if(Vars.DYNAMICV){
				switch(DeltaQuester.number){
				case 1:
					walking(Paths.pathToBank2,"Walking to lummbridge bank", false);
					break;
				case 0:
					walking(Paths.pathToGE,"Walking to Grand Exchange bank", false);
					break;
				
				}
			}else{
				switch(DeltaQuester.number){
				
				case 1:
					if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(ctx.players.local().getLocation())<10){
						Vars.DYNAMICV = true;
					}else teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
					break;
					
				case 0:
					if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(ctx.players.local().getLocation())<10||
							TeleportLode.VARROCK.getTile().distanceTo(ctx.players.local().getLocation())<10){
						Vars.DYNAMICV = true;
					}else if(VarrokLodeIsActive()){
						teleportTo(TeleportType.VARROCK.getTeleport(),TeleportType.VARROCK.getName());
					}else teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
					break;
				}
			}
		}
		//System.out.println("Attempting" + bankTile+": "+rand.nextInt(3));
		
	}

	public void interactG(int i, String string, String string2) {
		if(!ctx.groundItems.select().id(i).first().isEmpty()){
			DeltaQuester.paintIndicator = i;
			if(!ctx.widgets.get(1092,42).isVisible()){
				for(GroundItem item : ctx.groundItems.select().id(i).nearest().first()){
					if (item.isOnScreen()) {
						state("Performing action on ground item: " + string2);
						ctx.mouse.move(item.getCenterPoint());
							if(item.interact(string)){
							ctx.environment.sleep(500,600);
							break;
							}else ctx.camera.turnTo(item.getLocation().randomize(2, 3));
						
					} else ctx.camera.turnTo(item);
					break;
				}
				
			}else clickOnMap(ctx.players.local().getLocation());
		}
		
	}
	public void combineItems(int item1, int item2) {
		state("Combining items");
		Backpack inv = ctx.backpack;
		skipPics();
		if(!isChatting("Self"))
			if(ctx.backpack.isItemSelected()){
				interactInventory(item2,"Use", "Item 2");
			}else interactInventory(item1,"Use","Item 1");
		/*if(!inv.select().id(item1).first().isEmpty() && !inv.select().id(item2).first().isEmpty()){
			if(inv.isItemSelected() && (inv.getSelectedItem().getId()==item1 || inv.getSelectedItem().getId()==item2)){
				interactInventory(item2, "Use", "Item");
			}else for(Item i : inv.getAllItems()){
				if(i.getId() == item1){
					ctx.backpack.scroll(i);
					i.click();
				}
			}
		}
		*/
	}
	public void displayTileDifference(Tile tile) {
		state("X: " + (ctx.players.local().getLocation().getX()-tile.getX()) + "Y: "+ (ctx.players.local().getLocation().getY()-tile.getY()));
		
		
	}

	public boolean getToTile(Tile tile) {
		state("Walking to location: " + tile);
		Player local = ctx.players.local();
		if(tile.distanceTo(local.getLocation())<5){
			return true;
		}else clickOnMap(tile);
		return false;
	}

	
	public boolean EquipmentContains(int i) {
		if(ctx.bank.isOpen())
			ctx.bank.close();
		
		if(!ctx.hud.isVisible(Window.WORN_EQUIPMENT)){
			state("Opening equipment view");
			ctx.hud.view(Window.WORN_EQUIPMENT);
			sleep(2000);
		}
		if(!ctx.equipment.select().id(i).first().isEmpty()){
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
	public boolean inventoryContains(String name) {
		if(!ctx.hud.isVisible(Window.BACKPACK)){
			while (ctx.bank.isOpen()){
				ctx.bank.close();
			}
			ctx.hud.view(Window.BACKPACK);
		}
			if(ctx.backpack.select().name(name).isEmpty()) {
				return false;
			}
		
		return true;
	}

	public void clickOnMap(Tile t) {
		
		ctx.movement.stepTowards(ctx.movement.getClosestOnMap(t));
		/*
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
		return l;*/
	}

	public boolean npcIsNotNull(int id) {
			if(!ctx.npcs.select().id(id).nearest().first().isEmpty()){
				return true;
			}
		return false;
	}

	public boolean objIsNotNull(int id) {
		if(!ctx.objects.select().id(id).first().isEmpty()){
			return true;
		}
		return false;
	}

	public int inventoryGetCount(int i) {
		if(!ctx.hud.isOpen(Window.BACKPACK) || !ctx.hud.isVisible(Window.BACKPACK))
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

	public boolean npcSays(String text) {
		if(ctx.widgets.get(1184,9).isValid())
		if(ctx.widgets.get(1184,9).getText().contains(text)){
			return true;
		}
		return false;
	}
	public void findPath(final Tile tile,final String text){
		state(text);
		ctx.movement.findPath(tile).traverse();
	}
	
	public boolean objIsByTile(Tile tile, int object, int dist) {
		for(GameObject obj : ctx.objects.select().id(object).nearest(tile)){
			if(obj.getLocation().distanceTo(tile)<dist){
				return true;
			}
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
	public void checkBank() {
		//System.out.println("Now deciding bank.."+ DeltaQuester.DeltaQuester.number);
		if(!DeltaQuester.bankFound){
			System.out.println("Now deciding bank..");
			decideBank();
		}else
		if (bankTile.distanceTo(ctx.players.local().getLocation()) < 7) {
			Vars.DYNAMICV = false;
			if(ctx.bank.isOpen()){
				Vars.bankItems.clear();
				for(Item i: ctx.bank.select()){
					if(!Vars.bankItems.contains(i.getId()))
						Vars.bankItems.add(i.getId());
				}
				DeltaQuester.checkedBank = true;
			}else openBank();
			
		} else getToBank();
	}
	private void openBank() {
		System.out.println("Attempting to open bank: "+ DeltaQuester.number);
		switch(DeltaQuester.number){
		case 0://G.E bank
			if(getNPC(2718).isOnScreen()){
		   npcInteract(2718,"Bank");
			}else ctx.camera.turnTo(getNPC(2718).getLocation());
			break;
		case 1://Lumbridge outside bank
			if(getObject(79036).isOnScreen()){
			  interactO(79036,"Use","Bank chest");
			}else ctx.camera.turnTo(getObject(79036).getLocation());
			break;
		
		}
		
	}
	
	private void decideBank() {
		System.out.println("Inside decide bank");
		if(DeltaQuester.number!=0 && DeltaQuester.number!=1){
		System.out.println("Creating random numer!");
		DeltaQuester.number = Random.nextInt(0,2);
		}
		switch(DeltaQuester.number){
		
		case 0://Grand Exchange bank
			bankTile = new Tile(3179, 3481, 0);
			DeltaQuester.bankFound = true;
			break;
			
		case 1://Lumbridge outside bank
			bankTile = new Tile(3214,3257, 0);
			DeltaQuester.bankFound = true;
			break;
			
		
		};
		
	}

	public void exchangeBank(int noteID, int itemID, int Amount) {
		
		if(ctx.bank.isOpen()){
		
			cacheBank();
			ctx.environment.sleep(1200);
			if(inventoryContains(itemID) && inventoryGetCount(itemID)>=Amount){
				DeltaQuester.exchangeBank = true;
			}else if(inventoryContains(noteID)){
				state("Depositing note");
				ctx.bank.deposit(noteID, Amount);
				cacheBank();
				ctx.environment.sleep(1200);
			}else if(!Vars.bankItems.contains(itemID)){
				System.out.println("Could not find item in bank");
				DeltaQuester.exchangeBank = true;
			}else {
				state("Withdrawing item");
				ctx.bank.withdraw(itemID, Amount);
			}
			
		}else ctx.bank.open();
		
		
	}

	private void cacheBank() {
		System.out.println("Clearing cache");
		Vars.bankItems.clear();
		for(Item i: ctx.bank.select()){
			if(!Vars.bankItems.contains(i.getId())){
				System.out.println("Adding: " + i.getId());
				Vars.bankItems.add(i.getId());
			}
		}
		
	}
	public void sleep(int amount){
		ctx.environment.sleep(amount);
	}


	public boolean byCloseLoc(Tile loc, int dist) {
		Tile local = ctx.players.local().getLocation();
		
		if(local.distanceTo(loc)<dist){
			return true;
		}else {
			state("Walking to location");
			clickOnMap(loc);
		}
		return false;
	}
	public void setGeneralCamera() {
		if(ctx.camera.getPitch()<50){
			state("Setting camera pitch");
			ctx.camera.setPitch(60);
		}
		
	}
	public void bankItems(int[] bankItems, int[] amountOfItem) {
		ArrayList<Integer> itemsInBank= new ArrayList<Integer>();
		ArrayList<Integer> invSize= new ArrayList<Integer>();
		
		if(new Tile(3181,3481, 0).distanceTo(ctx.players.local().getLocation())<6){
		if(ctx.bank.isOpen()){
			boolean once = false;
			//find your inventory size
			ItemQuery<Item> y = ctx.backpack.select();
			for(Item j: y){
				if(!invSize.contains(j.getId())){
					invSize.add(j.getId());
				}
			}
			//deposits inventory is too full
			if(invSize.size() > bankItems.length){
				state("Depositing inventory, size is: " + invSize.size());
				ctx.bank.depositInventory();
			}
			while(!once){
				if(ctx.bank.depositInventory()){
					state("Depositing");
					once = true;
				}
			}
			//Add all items in bank to an array
			ItemQuery<Item> i = ctx.bank.select();
			for(Item bankItem: i){
				if(!itemsInBank.contains(bankItem.getId())){
					itemsInBank.add(bankItem.getId());
				}
			}
			for(int t = 0; t<bankItems.length;){
				if(!ctx.bank.isOpen())
					break;
				if(inventoryContains(bankItems[t])){
					for(Item item: ctx.backpack.select().id(bankItems[t])){
						if(item.getStackSize()>2){
							if(item.getStackSize()>=amountOfItem[t]){
								t++;
							}
						}else if(inventoryGetCount(bankItems[t])>=amountOfItem[t]){
							t++;
						}
					}
				}else if(itemsInBank.contains(bankItems[t])){
					System.out.println("Taking " + amountOfItem[t]+" of " + bankItems[t]);
					ctx.bank.withdraw(bankItems[t], amountOfItem[t]);
				}else {
					System.out.println("Can't find item in bank: " + bankItems[t]);
					t++;
				}
			}
			
			int foodAmount = 0;
			if(itemsInBank.contains(DeltaQuester.FOOD_ID)){//if food in bank
			  ItemQuery<Item> foodInBank = ctx.bank.select().id(DeltaQuester.FOOD_ID);
			  System.out.println("Checking food;");
			  foodAmount = foodInBank.count();
				if(foodAmount<(bankItems.length-5)){
					System.out.println("Short on food");
					foodAmount = foodInBank.count();
				}else {
					System.out.println("Setting food amount to: " + (23-bankItems.length));
					foodAmount = (23-bankItems.length);
				}
			}
			while(DeltaQuester.FOOD_FEATURE && inventoryGetCount(DeltaQuester.FOOD_ID)<foodAmount){
				if(!ctx.bank.isOpen())
					break;
				System.out.println("Withdrawing food amount: " + foodAmount);
				ctx.game.sleep(1200);
				ctx.bank.withdraw(DeltaQuester.FOOD_ID, foodAmount);
			}
			System.out.println("Setting goBank to false");
			Vars.DYNAMICV3 = false;
			goBank = false;
		}else{
			state("Opening bank");
			ctx.bank.open();
		}
	 }else if(Vars.DYNAMICV3){
			walking(Paths.pathToGE, "Walking to Varrock bank", false);
		}else if(TeleportLode.VARROCK.getTile().distanceTo(ctx.players.local().getLocation())<10){
			Vars.DYNAMICV3 = true;
		}else teleportTo(TeleportType.VARROCK.getTeleport(),"Varrock");
		
	}
	public static Tile toTile(int hash){
		return new Tile(Structure.TILE.getX(hash),Structure.TILE.getY(hash),Structure.TILE.getZ(hash));
	}
	
	/*
	public void walkToLocation(Tile end) {
		Web web = new Web(ctx, ctx.players.local().getLocation(), end);
		if(!wait.isRunning())
		if(web.getPath() != null && web.getEnd().distanceTo(ctx.players.local()) > 4) {
			web.walk();
			wait = new Timer(Random.nextInt(2000,3000));
		}
		/*
		//state("Walking to location: " + end);
		ctx.game.sleep(4000);
		Tile local = ctx.players.local().getLocation();
		TilePath path = pf.findPath(Structure.TILE.getHash(local.getX(),local.getY(),local.getPlane()),Structure.TILE.getHash(end.getX(),end.getY(), end.getPlane()) , 500l, false);
		
		if(path!=null)
		for(int i = 0;i< path.size();){
			local = ctx.players.local().getLocation();
			if(ctx.hud.isVisible(Window.FRIENDS)){
				System.out.println("Breaking because friend list open; debugging purposes");
				break;
			}
			PathNode p = path.get(i);
			System.out.println("End: "+local.distanceTo(end));
			
			
			if(!wait.isRunning())
			if(toTile(p.getHash()).distanceTo(local)>12||
					local.distanceTo(end)>12){
			clickOnMap(toTile(p.getHash()));
			wait = new Timer(4000);
			}else{
				//System.out.println("Incrementing: " + i);
				i++;
			}
		}
		
		
	}*/
	






}
