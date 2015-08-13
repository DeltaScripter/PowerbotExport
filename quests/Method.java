package quests;


import java.util.ArrayList;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.Action;
import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.GroundItem;
import org.powerbot.script.rt6.Hud.Window;
import org.powerbot.script.rt6.Interactive;
import org.powerbot.script.rt6.Item;
import org.powerbot.script.rt6.ItemQuery;
import org.powerbot.script.rt6.MobileIdNameQuery;
import org.powerbot.script.rt6.Npc;
import org.powerbot.script.rt6.Player;

import quests.Vars.TeleportLode;
import quests.Vars.TeleportType;
import features.GrandExchange;


public class Method extends ClientAccessor{

		public Method(ClientContext ctx) {
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
		
		public static boolean depoBank = false;
		private Vars Vars = new Vars();
		GrandExchange Ge = new GrandExchange(ctx);
		public static boolean onlyItemsGE = false;
		
		//public static ArrayList<Tile> path = new ArrayList<Tile>();//path that is created(web walking)
		
		/*
		public void webWalk(Tile dest){
			
			boolean doItAgain = true;
			ArrayList<Tile> openList = new ArrayList<Tile>();//nodes to check
			ArrayList<Tile> closedList = new ArrayList<Tile>();//nodes to ignore
			
			ArrayList<Tile> adjacentNodes = new ArrayList<Tile>();//adjacent nodes to main point
			ArrayList<Double> adjacentNodesH = new ArrayList<Double>();//adjacent node distance to end (H)
			ArrayList<Double> adjacentNodesG = new ArrayList<Double>();//adjacent node cost (G)
			
	if(path.isEmpty()){
				
		while(doItAgain){	
			doItAgain = false;
			
			if(ctx.hud.opened(Window.SKILLS)){
				System.out.println("Breaking for equipment");
				break;
			}
			if(path.size()>1)
			System.out.println("Current end node to destination is : " + path.get(path.size()-1).distanceTo(dest));
			
			if(path.size()>1 &&
					path.get(path.size()-1).distanceTo(dest)<20){
				System.out.println("Breaking b/c end node is less than 20 " + path.get(path.size()-1));
				break;
			}else doItAgain = true;
			
			
			System.out.println("Path current size is : " + path.size());
			
			
			if(!path.contains(ctx.players.local().tile()))
			path.add(ctx.players.local().tile());
			
			for(Tile t: NodeTiles.nodes){//add all potential nodes to the open list
				if(!openList.contains(t)){
					openList.add(t);
				}
			}
			for(Tile g: openList){//calc adjacent nodes
				if(!adjacentNodes.contains(g)&&
						path.get(path.size()-1).distanceTo(g)<30){//if it is indeed an adjacent node
					adjacentNodes.add(g);
				}
			}
			//System.out.println("Adjacent node count: " + adjacentNodes.size());
			for(Tile adj: adjacentNodes){//calculate the H value of all adjacent tiles
				double dist = adj.distanceTo(dest);
				adjacentNodesH.add(dist);
			}
			for(Tile adj: adjacentNodes){//calc g value
				double cost = path.get(0).distanceTo(adj);
				adjacentNodesG.add(cost);
			}
			double nextNodeHValue=2000000;
			Tile TileToAdd = new Tile(0,0,0);
			for(int i = 0; i< adjacentNodes.size(); i++){
				double h = adjacentNodesH.get(i);
				double g = adjacentNodesG.get(i);
				double f = h+g;
				
				
				if(path.size()>1)
				System.out.println("Using base node of " + path.get(path.size()-1));
				//System.out.println("Node: " + adjacentNodes.get(i) + " has f value of " + f + " ______--------- " + nextNodeHValue);
				if(f<nextNodeHValue){
					nextNodeHValue = f;
					TileToAdd = adjacentNodes.get(i);
					System.out.println("Adding node to path : " + adjacentNodes.get(i) + " with f of " + f);
				}
				//System.out.println("Tile: " + adjacentNodes.get(i) + " has heuristic of " + adjacentNodesH.get(i) + " ::::: and cost of : "+adjacentNodesG.get(i)+" ::: F is " + f);
				
			}
			//System.out.println("Recommended tile is " + TileToAdd);
			if(!path.contains(TileToAdd)){
			path.add(TileToAdd);
			adjacentNodesH.clear();
			adjacentNodesG.clear();
			adjacentNodes.clear();
			doItAgain = true;
			}
			
			
		}
			
			System.out.println("_______________________------------------------path size" + path.size());
			for(Tile t:path){
				System.out.println("Path: " + t);
			}
			
			}else{
				System.out.println("THE PATH IS MADE : " + path.size());
			}
		}*/
		/*
	//uses the node map to walk to a location
		public void walkTo(ArrayList<Tile> tile , String pathName) {
		    state(pathName);
			
			while(tile.get(tile.size()-1).distanceTo(ctx.players.local().tile())>15){
				//System.out.println("Size up here is: " + tile.size());
				//System.out.println("0 is: "+ tile.get(0) +" our dist form it : "+tile.get(0).distanceTo(ctx.players.local().tile()));
			if(tile.get(0).distanceTo(ctx.players.local().tile())<15){
				tile.remove(0);
				System.out.println("Removing checkpoint, new size is: " + tile.size());
			}else{
				//System.out.println("Trying to walk towards : " + tile.get(0));
			try{
				if(nodeWalk(tile.get(0)).matrix(ctx).reachable()){//if we can reach the tile..
					clickOnMap(nodeWalk(tile.get(0)));//then walk towards it
				}
				
				
				/*
				if(!ctx.movement.findPath(nodeWalk(tile.get(0))).traverse()){
					//System.out.println("using click on map");
				clickOnMap(nodeWalk(tile.get(0)));
				}//else System.out.println("Using find path");
				*//*
			}catch(Exception e){System.out.println("Node path returning null");}
			}
			  //this is for breaking the loop
			   if(!ctx.hud.opened(Window.BACKPACK)){
			  	 break;
			  }
			}
			
		}*/
		
     public Npc getInteractingNPC()
	    {
	        final MobileIdNameQuery<Npc> npcs = ctx.npcs.select();
	       
	        if(npcs != null && npcs.size() > 0)
	            for(final Npc npc : npcs)
	                if(npc.interacting() != null && npc.interacting().equals(ctx.players.local()))
	                    return npc;
	        return null;
	    }
	 
		////timer waitClick = new //timer(0);
		public boolean goBank =true;
		public void fightNPC(int id) {
			
				
			if(getInteractingNPC()==null){//if not in combat
					for(Npc enemy: ctx.npcs.select().id(id).nearest().first()){
						if(enemy.tile().distanceTo(ctx.players.local().tile())<7){
							npcInteract(enemy.id(),"Attack");
						}else ctx.movement.step(enemy.tile());
					}
				}else
			for(int i = 0; i<9;){
				foodSupport();
				state("Fighting NPC");
				if(ctx.players.local().healthPercent()<60){
					System.out.println("Health percent less than 60, breaking for loop");
					break;
				}
				
				if(getInteractingNPC()==null){
					System.out.println("Breaking for loop, not in combat");
					break;
				}
				if(ctx.combatBar.actionAt(i).ready()){
				//	if(!waitClick.isRunning()){
					System.out.println("Clicking action: "+ ctx.combatBar.actionAt(i).id());
					ctx.combatBar.actionAt(i).component().click();
					 // waitClick = new //timer(750);
					//}
				}else i++;
				
			}	
				
				
			
		}
	public void useGE(String[] name, int[] itemID, int[] itemPrice, int[] itemAmount){
			
			
		if (new Tile(3178, 3479, 0).distanceTo(ctx.players.local().tile()) < 9) {
			while(!ctx.hud.opened(Window.BACKPACK)){
				state("Attempting to open backpack");
				if(Ge.geIsOpen())
				ctx.widgets.component(105,14).click();//close GE
				if(ctx.bank.opened())
					ctx.bank.close();//Close the bank
				else
				ctx.hud.open(Window.BACKPACK);
			}
			if (Ge.geIsOpen()) {
			for(int pos = 0; pos < itemID.length;){
				//state("Purchasing " + itemAmount[pos]+ " " + name[pos]+ " for "+itemPrice[pos]+ "GP" );
				if (Ge.geIsOpen()) {
					if(ctx.backpack.moneyPouchCount() < itemPrice[0]){
						System.out.println("not enough money in pouch");
						DeltaQuester.g = true;
						break;
					}else
					if(inventoryContains(itemID[pos])){
						if((pos+1)==itemID.length){
							while(ctx.widgets.component(105,87).visible()){
								state("Closing GE");
								ctx.widgets.component(105,87).click();
							}
							DeltaQuester.GEFeature=false;
							break;
						}else pos++;
					}else
					if(Ge.isSlotEmpty()){
						Ge.createBuyItem(name[pos], itemAmount[pos],itemPrice[pos]);
						////ctx.game.sleep(1000);
					}else {
						////ctx.game.sleep(1000);
						Ge.collectItem(name[pos]);
					}
				}else break;
			}
			
			}else if(ctx.bank.opened()){
				ctx.bank.close();
			}else npcInteract(2241, "Exchange");
		} else getToExchange();
		
		
	}
	public boolean getToNearByTile(Tile tile) {
		if(tile.distanceTo(ctx.players.local().tile())<4){
			return true;
		}else clickOnMap(tile);
		return false;
	}
	public int inventoryStackSize(int ID) {
		if(ctx.hud.open(Window.BACKPACK)){
		
		ItemQuery<Item> g;
		
		g = null;
		g = ctx.backpack.select().id(ID);
		for(Item t:g){
			if(t.id()==ID){
				return t.stackSize();
			}
		}
		//return g.count(true);
		}
		return 0;
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
	public boolean isChatting() {
		
		if(!startQuestOpen())
		if(ctx.widgets.widget(1184).valid()||
				ctx.widgets.component(1191,0).visible()||
				ctx.widgets.component(1187,0).visible()||
				ctx.widgets.component(1188,0).visible()||
				ctx.widgets.component(1189,0).visible()||
				ctx.widgets.component(1186,0).visible()){
			pressContinue();
			sleep(300);
			return true;
		}
		//System.out.println("Returning false");
		return false;
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
			sleep(300);
			return true;
		}
		//System.out.println("Returning false");
		return false;
	}
	public boolean isFaladorLodeAct(){
		if((ctx.varpbits.varpbit(3) >>6 &1)==1){
			return true;
		}
		return false;
	}

	public  boolean isPortSarimLodeAct() {
		if ((ctx.varpbits.varpbit(3) >> 8 & 1) == 1) {
			return true;
		}
		return false;
	}
	public  boolean YanilleLodeAct() {
		if ((ctx.varpbits.varpbit(3) >> 12 & 1) == 1) {
			return true;
		}
		return false;
	}
	public  boolean TaverlyLodeAct() {
		if ((ctx.varpbits.varpbit(3) >> 10 &0x1) == 1) {
			return true;
		}
		return false;
	}
	public  boolean findOption(String[] text) {
		Vars Vars = new Vars();
		if(!ctx.widgets.component(1188,2).visible()){
			System.out.println("Can't find the option text");
			return false;
		}
		for (String t:text) {
			////ctx.environment.sleep(20,50);
			for (int i :Vars.OPTIONVALUE) {
				System.out.println("Looking through options");
				if (ctx.widgets.component(1188,2).visible()&&ctx.widgets.component(1188, i).text().contains(t)) {
					state("Attempting to click option in this area");
					ctx.input.click(ctx.widgets.component(1188, i).centerPoint().x+10,ctx.widgets.component(1188, i).centerPoint().y+3 , true);
					return true; 
				}
			}
		}
		return false;
	}
	
	public void state(final String message) {
		DeltaQuester.state = message;
	}
	
	public void speakTo(final int id, final String p) {
		System.out.println("Moving camera fffto npc");
			for(Npc n: ctx.npcs.select().id(id).nearest().first()){
				System.out.println("Movretring camera to npc");
				if(n.inViewport()){
					state("Attempting to speak to: " + p);
					n.interact("Talk");
			        sleep(Random.nextInt(2000, 2500));
				}else {
					System.out.println("Moving camera to npc");
					ctx.movement.newTilePath(n.tile()).traverse();
					ctx.camera.turnTo(n);
				}
		}
	}
	public void foodSupport() {
		resetTeleporting();
		if(ctx.widgets.component(1430,83).valid()){//health bar
			double barHealth = ctx.widgets.component(1430,83).component(3).width();
			double maxHealth = ctx.widgets.component(1430,83).component(1).width() + 32;
			DeltaQuester.health = (barHealth/maxHealth)*100;
		}
		 
		 if(getInteractingNPC()!=null){//if fighting
				//combat//timer = new //timer(4000);
		}
		if(DeltaQuester.FOOD_FEATURE && ctx.players.local().healthPercent()<50){
			if(hasFood){
			if(inventoryContains(DeltaQuester.FOOD_ID)){
				state("Food support initiated");
				interactInventory(DeltaQuester.FOOD_ID, "Eat","Food");
				////ctx.environment.sleep(1700, 2000);
				}else hasFood = false;
			}
		}
	}
	public void interactInventory(final String name, final String string, final String o) {
		ArrayList<String> actions = new ArrayList<String>();
		
		// if(!//timer.isRunning()){
		for(Item t : ctx.backpack.select().name(name).first()){
			//System.out.println(ctx.widgets.component(1477,122).component(0).boundingRect().getCenterY());
			skipPics();
			if(ctx.hud.open(Window.BACKPACK) && ctx.widgets.component(1473,31).contains(
				t.component().centerPoint())){
				System.out.println("Hovering");
				t.hover();
				////ctx.game.sleep(1200);
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
						//ctx.game.sleep(2000);
						// //timer = new //timer(2500);
						}
					}
				}
				 
			}else
			if(ctx.widgets.component(1473,31).boundingRect().getCenterY()>//was 7
			t.component().boundingRect().getCenterY()){
				state("Scrolling through inventory");
				ctx.input.move(ctx.widgets.component(1473, 31).centerPoint());
				ctx.input.scroll(false);
			}else {
				state("Scrolling through inventory");
				ctx.input.move(ctx.widgets.component(1473, 31).centerPoint());
				ctx.input.scroll(true);
				}
			System.out.println("center y"+ctx.widgets.component(1473,31).boundingRect().getCenterY());
			System.out.println("center y object" + t.component().boundingRect().getCenterY());
			}
		//}else System.out.println("//timer1 running");
	}
	public void interactInventory(final int i, final String string, final String o) {
		ArrayList<String> actions = new ArrayList<String>();
		
		// if(!//timer.isRunning()){
		for(Item t : ctx.backpack.select().id(i).first()){
			//System.out.println(ctx.widgets.component(1477,122).component(0).boundingRect().getCenterY());
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
				ctx.input.move(ctx.widgets.component(1473, 31).centerPoint());
				ctx.input.scroll(false);
			}else {
				state("Scrolling through inventory");
				ctx.input.move(ctx.widgets.component(1473, 31).centerPoint());
				ctx.input.scroll(true);
				}
			}
		//}else System.out.println("//timer1 running");
	}
    public boolean isInCombat() {
        return ctx.players.local().interacting()!=null;
    }
    public void interactO(final String name, final String string, final String o) {
    
		for(GameObject y: ctx.objects.select().name(name).nearest().first()){
			//if(closeInterfaces())
			//if(y.inViewport()){
			
			if(!y.interact(name)){
				System.out.println("Can't");
			}else System.out.println("Can");
			//if (closeInterfaces() && y.inViewport()) {
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
		
			//ctx.game.sleep(1800);
		//	}else ctx.camera.turnTo(y);
				}
		
	}
    
	public boolean interactOReturn(final int i, final String action) {
	
		if(!objIsNotNull(i) && !ctx.players.local().inMotion()){
			System.out.println("Successfully click because object does not exist!");
			return true;
		}
		
		for(GameObject y: ctx.objects.select().id(i).nearest().first()){
			//if(closeInterfaces())
			System.out.println("dist" + y.tile().distanceTo(ctx.players.local().tile()));
			
			y.hover();
			y.hover();
			
			boolean menuExist = false;
			for(String h : ctx.menu.items()){
				if(!h.contains("Walk here") && !h.contains("Cancel") && !h.contains("Examine")){
					menuExist = true;
					System.out.println("On object with the menu of: "+h);
				}
			}
			if(y.inViewport() && y.interact(action)&&menuExist==true||
					 y.tile().distanceTo(ctx.players.local().tile())>6){
			state("Interacting: " + "");
			sleep(Random.nextInt(1000,2000));
			System.out.println("Successfully click an object!");
			if(!ctx.players.local().inMotion()){
				sleep(Random.nextInt(2000, 3000));
				return true;
			}
			}else {
				ctx.camera.turnTo(y.tile());
			}

				}
		return false;
	}
	public void interactO(final int i, final String string, final String o) {
	for(GameObject y: ctx.objects.select().id(i).nearest().first()){
			//if(closeInterfaces())
			if(y.inViewport() && y.interact(string)){
			state("Interacting: " + string);
			sleep(Random.nextInt(1800,2500));
			}else {
				ctx.camera.turnTo(y.tile());
			}
			/*
					if (closeInterfaces() && y.inViewport()) {
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
		
		while((ctx.widgets.component(1244,23).visible())){//completed quest screen
			state("Closing completed quest screen");
				ctx.widgets.component(1244,23).click(true);
		}
			while(ctx.bank.opened()){
				ctx.bank.close();
			}
		for(int index: widgetInterference){
			
			if(ctx.widgets.component(index,0).valid()&&
					ctx.widgets.component(index,0).visible()){
				System.out.println("Closing an interaface");
				if(index==1186){
					pressContinue();
				}else{
					state("Clicking on map to close dialogue - automatic");
				clickOnMap(ctx.players.local().tile());
				return false;
				}
			  }
			}
		return true;
	}
	public void npcInteract(String name, String string) {
		ArrayList<String> actions = new ArrayList<String>();
		//if(!SpeakTo//timer.isRunning()){
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
							state("Interacting with " + n.name() + " and using " + string);
							// SpeakTo//timer = new //timer(2400);
							 System.out.println("interacting");
							  n.interact(string);
							   sleep(2000);
							   break;
							
						}
					}
				} else ctx.camera.turnTo(n);
			}
	//	}
	}
	
	public boolean npcInteract(int i) {
		ArrayList<String> actions = new ArrayList<String>();
		//if(!SpeakTo//timer.isRunning()){
		for(Npc n : ctx.npcs.select().id(i).nearest().first()){
				if (n.inViewport()) {
					n.hover();
					String menuItems[] = ctx.menu.items();
					for(String opt: menuItems){
						if(!actions.contains(opt))
							actions.add(opt);
					}
					for(String text: actions){
						if(text.contains("")){
							// SpeakTo//timer = new //timer(2400);
							 System.out.println("interacting");
							  if(n.interact("")){
							   sleep(2000);
							   System.out.println("Returning true for clicking on npc");
							   return true;
							  }
							
						}
					}
				} else ctx.camera.turnTo(n);
			}
	//	}
		return false;
	}

	
	public boolean npcInteract(int i, String string) {
		ArrayList<String> actions = new ArrayList<String>();
		//if(!SpeakTo//timer.isRunning()){
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
							state("Interacting with " + n.name() + " and using " + string);
							// SpeakTo//timer = new //timer(2400);
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
	//	}
		return false;
	}


	public boolean VarrokLodeIsActive(){
		if((ctx.varpbits.varpbit(3) >> 11 & 1)==1){
			return true;
		}
		return false;
	}
	public boolean FaladorLodeIsActive(){
		if((ctx.varpbits.varpbit(3) >> 6 & 1)==1){
			return true;
		}
		return false;
	}
	
    public boolean DraynorLodeIsActive(){
    	  if((ctx.varpbits.varpbit(3) >>4 &1)==1){
    		  return true;
    	  }
    	
    	return false;
    }	
    public boolean CatherbyLodeIsActive(){
  	  if((ctx.varpbits.varpbit(3) >>3 &1)==1){
  		  return true;
  	  }
  	return false;
  }	
    public boolean ArdougneLodeIsActive(){
    	  if((ctx.varpbits.varpbit(3) >>1 &1)==1){
    		  return true;
    	  }
    	return false;
    }	
    public boolean SeersLodeIsActive(){
  	  if((ctx.varpbits.varpbit(3) >>9 &0x1)==1){
  		  return true;
  	  }
  	return false;
  }	
	public boolean startQuestOpen() {
		if(ctx.widgets.component(1500,0).visible()){
			state("Accepting quest offer");
			ctx.input.click(ctx.widgets.component(1500, 402).centerPoint(),true);
			return true;
		}
		return false;
	}
	public void interactSpecialObject(int[] bound, int ID, String interact){
		final int[] bounds = bound;
		final GameObject gobj = ctx.objects.select().id(ID).each(Interactive.doSetBounds(bounds)).select(Interactive.areInViewport()).nearest().poll();
		if(!gobj.interact(interact,"")){
			ctx.camera.turnTo(gobj);
		}
		
	}
	public void interactSpecialGroundItem(int[] bound, int ID, String interact){
		final int[] bounds = bound;
		final GroundItem gobj = ctx.groundItems.select().id(ID).each(Interactive.doSetBounds(bounds)).select(Interactive.areInViewport()).nearest().poll();
		if(!gobj.interact(interact,"")){
			ctx.camera.turnTo(gobj);
		}
		
	}
	public void walking(Tile[] t, String string, boolean dir){
		if(closeInterfaces())
		if(!dir ){
			state(string);
			ctx.movement.newTilePath(t).randomize(2, 1).traverse();
			sleep(1500);
			}else{
				state(string + ": Reverse");
				ctx.movement.newTilePath(t).reverse().traverse();
			    }
	}

	
	public void teleportTo(int loc, String teleName) {
		final int[] widgetsInterference = {1184,1189,1244,105,1191,149,1199,438,1242,1186,1188,1350,149,667};
		teleporting = true;
			//System.out.println("In teleport");
	   while(ctx.widgets.component(1477,47).component(2).visible()){//The task menu
			state("Closing task menu");
			ctx.widgets.component(1477,47).component(2).click(true);//The close button
		}
		while((ctx.widgets.component(1244,23).visible())){//completed quest screen
			state("Closing completed quest screen");
				ctx.widgets.component(1244,23).click(true);//continue option
		}
		while(ctx.widgets.component(438,22).visible()){//The annoying 'Recruit a friend' thing
			state("Closing advertisement");
			ctx.widgets.component(438,22).click(true);//The close button
		}
		while(ctx.widgets.component(1155,48).visible()){//The annoying 'Subscription advertisement' thing
			state("Closing advertisement");
			ctx.widgets.component(1155,48).click(true);//The close button
		}
		while(ctx.widgets.component(149,245).visible()){//The annoying 'Subscription advertisement'#2 thing
			state("Closing advertisement");
			ctx.widgets.component(149,245).click(true);//The close button
		}
			
		for(int i: widgetsInterference){
			if(ctx.widgets.component(i,0).visible()){
				state("Clicking on map to close dialogue for teleporting");
				//System.out.println("Clicking on map to close dialogue");
				clickOnMap(ctx.players.local().tile());
			}
		}
		//System.out.println("In area");
		if(ctx.bank.opened()){
			ctx.bank.close();
			//System.out.println("Bank is open");
		}else
		if(ctx.players.local().animation()==-1)
		if(ctx.widgets.component(1092,loc).visible()){//lodestone screen
			//System.out.println("Selecting the teleport");
			state("Selecting teleport: " + teleName);
			ctx.input.move(ctx.widgets.component(1092,loc).centerPoint());
			ctx.widgets.component(1092,loc).click(true);
			sleep(3000);
		}else {
			//System.out.println("Here now, opening tele");
			if (!ctx.players.local().inCombat())
				if (ctx.players.local().animation() == -1){
					System.out.println("Hovering mouse");
					ctx.widgets.component(1477,38).component(1).hover();//1477,59,1
					for(String t: ctx.menu.items()){
						if(t.contains("Teleport")){
							ctx.widgets.component(1477,38).component(1).click();//select lodestone button
						    sleep(Random.nextInt(2000, 2600));
						}
					}
				
				}
		}		
		
	}
	public void basicFightNPC(int npc){
		Method m = new Method(ctx);
		if(ctx.combatBar.expanded()){
			
			if(!m.isInCombat()){
				System.out.println("Clicking attack on enemy");
				m.npcInteract(npc, "Attack");
			}else
				for(Action ab: ctx.combatBar.actions()){
					if(ab.ready()){
						ab.select();
						sleep(Random.nextInt(900, 1400));
					}
				}
			}else ctx.combatBar.expanded(true);
				
			
		
		
	}
	
	public  void getToExchange() {
	    if (Vars.DYNAMICV){
	    	walking(Paths.pathToGE, "Walking to the Grand Exchange", false);
		}else if (TeleportLode.LUMMBRIDGE.getTile().distanceTo(ctx.players.local().tile())<5 || (TeleportLode.VARROCK.getTile().distanceTo(ctx.players.local().tile())<8)){	
			Vars.DYNAMICV = true;
			}else if(VarrokLodeIsActive()){
			teleportTo(TeleportType.VARROCK.getTeleport(), TeleportType.VARROCK.getName());
			}else teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
		
		
	}
	public boolean forceUseItemOnObj(int itemID, int objID){
		
		state("Using item on object");
		if(closeInterfaces())
		if(ctx.backpack.itemSelected()){
			if(interactOReturn(objID,"")){
				return true;
			}
		}else if(inventoryContains(itemID))
			interactInventory(itemID,"Use","Item");
		
		return false;
	}
	
	public void useItemOn(int item, int obj, String string) {
		state("Using item on object");
		if(closeInterfaces())
		if(ctx.backpack.itemSelected()){
			interactO(obj, "Use", string);
		}else if(inventoryContains(item))
			interactInventory(item,"Use","Item");
		
	}
	public void useItemOnNpc(int item, int npc, String string) {
		state("Using item on npc");
		if(ctx.backpack.itemSelected()){
			npcInteract(npc,"Use");
		}else if(inventoryContains(item))
			interactInventory(item,"Use","Item");
		
	}
	public void useItemOnG(int item, int obj, String string) {
		Method m = new Method(ctx);
		m.state("Using item on ground item");
		skipPics();
		if(!isChatting("Self"))
		if(ctx.backpack.itemSelected()){
			m.interactG(obj, "Use", string);
		}else if(m.inventoryContains(item))
			for(Item i: ctx.backpack.select().id(item).first()){
					i.interact("Use");
		}
		
	}
	public void skipPics() {
		if(ctx.widgets.component(1189,1).valid() || ctx.widgets.component(1186,1).valid()){
			//state("Skipping special chat");
			 int widgetID[] = {1189,1186};
			 
		
				while(ctx.widgets.component(1155,48).visible()){//The annoying 'Subscription advertisement' thing
					state("Closing advertisement");
					ctx.widgets.component(1155,48).click(true);//The close button
				}
				while(ctx.widgets.component(149,245).visible()){//The annoying 'Subscription advertisement'#2 thing
					state("Closing advertisement");
					ctx.widgets.component(149,245).click(true);//The close button
				}
				 
				for(int both : widgetID){
				    if(ctx.widgets.component(both,1).valid()){
				    	ctx.chat.clickContinue();
				    	ctx.widgets.component(both, 11).click();
				    	//ctx.widgets.component(both, 7).click();
				    	ctx.widgets.component(both, 6).click();
				    	
				    }
				}
		}
		
	}
	public boolean playerText(String string) {
		if (ctx.widgets.component(137,131).valid()) {
			//state("Checking: " + string);
			if (ctx.widgets.component(137,131).component(0).text()
					.contains(string)) {
				System.out.println("returning true for player text");
				return true;
			}
		}
		return false;
	}
	public void resetTeleporting() {
		if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(ctx.players.local().tile())<10 || TeleportLode.VARROCK.getTile().distanceTo(ctx.players.local().tile())<10 || TeleportLode.DRAYNOR.getTile().distanceTo(ctx.players.local().tile())<10||
				TeleportLode.PORTSARIM.getTile().distanceTo(ctx.players.local().tile())<10|| TeleportLode.ARDOUGNE.getTile().distanceTo(ctx.players.local().tile())<10|| TeleportLode.YANILLE.getTile().distanceTo(ctx.players.local().tile())<10||
				TeleportLode.BURTHORPE.getTile().distanceTo(ctx.players.local().tile())<10|| TeleportLode.CATHERBY.getTile().distanceTo(ctx.players.local().tile())<10|| TeleportLode.FALADOR.getTile().distanceTo(ctx.players.local().tile())<10
				){
			teleporting = false;
		}
	}
	public boolean interference() {
		while(ctx.widgets.component(1186,1).valid() && ctx.widgets.component(1186,2).text().contains("are now leaving the")){//18
			if(ctx.widgets.component(1186,3).valid()){
		    	ctx.widgets.component(1186, 3).click();
		    	return true;
		    }else break;
			
		}
		if(ctx.widgets.component(1188,0).visible() && ctx.widgets.component(1188,11).text().contains("Leave the st")){
			ctx.input.click(ctx.widgets.component(1188, 11).centerPoint().x+10,ctx.widgets.component(1188, 11).centerPoint().y+3 , true);
			
			return true;
		}
	 return false;
	}
	
	public void pressContinue(){
		
		
		 if(ctx.widgets.component(1186,10).visible()||
				 ctx.widgets.component(1186,7).visible()){
			 System.out.println("Pressing continue 1186 - from Method");
			 ctx.widgets.component(1186,10).click();//continue button
			 ctx.widgets.component(1186,7).click();// the continue button (another one)
			 sleep(Random.nextInt(2000, 2400));//the delayedment is necessary for What's Mine Is Yours quest (beginning of it)
		 }
		 //ctx.chat.clickContinue();
		 if(ctx.widgets.component(1189,6).visible()){
			 System.out.println("Pressing continue 1189, at widget component '6'- from Method at method 'pressContinue'");
			 ctx.widgets.component(1189,6).click();
		 }
		 if(ctx.widgets.widget(1184).component(10).visible()){
			 System.out.println("Pressing continue 1184- from Method");
			 ctx.widgets.widget(1184).component(10).click();
		 }
		 if (ctx.widgets.widget(1191).component(7).visible()){
			 System.out.println("Pressing continue 1191- from Method");
			 ctx.widgets.widget(1191).component(7).click();
		 }
		 if (ctx.widgets.widget(1187).component(7).visible()){//a adialogue that appears in Stolen Hearts
			 System.out.println("Pressing continue 1187- from Method");
			 ctx.widgets.widget(1187).component(7).click();
		 }
		
	}
	public void determineBank(int[] items){
		int invspace= 28;
		int itemsNeeded = 0;
		int foodspace;
		ArrayList<String> inv = new ArrayList<String>();//all item in inv
		ArrayList<String> itemCount = new ArrayList<String>();//counts the item in the above array that you need
		Item[] inventory = null;
		inventory = ctx.backpack.items();
		
		if(!quests.Vars.ranOnce){
			//state("Determining if you need to bank");
			for(Item i : inventory){
				//System.out.println("Checking inv items");
				if(i.id()!=-1)
					inv.add(i.name().toString());
			}
				for(Item item : inventory){
					//System.out.println("Someting else");
				   for(int i: items){
					   if(item.id()==i && !itemCount.contains(item.name())){
						   itemCount.add(item.name());
					   }
				   }
				}
				
				invspace = invspace - inv.size();
				itemsNeeded = items.length - itemCount.size();
				foodspace = invspace - 3;
				
				 if(invspace<itemsNeeded){
					 System.out.println("DetermineBank setting useBank to true, out of inventory space");
					quests.Vars.useBank = true;
					quests.Vars.ranOnce = true;
				}else {
					 System.out.println("DetermineBank, we have sufficient inventory space");
					quests.Vars.useBank = false;
					quests.Vars.ranOnce = true;
				}
				 for(int bankitem: items){
						if(quests.Vars.bankItems.contains(bankitem)){
							System.out.println("DetermineBank, Found item!");
							quests.Vars.useBank = true;
							quests.Vars.ranOnce = true;
						}else System.out.println("DetermineBank, can't find item in bank" + bankitem);
					}
				 if(DeltaQuester.FOOD_FEATURE){
						if(inventoryGetCount(DeltaQuester.FOOD_ID)<=foodspace && quests.Vars.bankItems.contains(DeltaQuester.FOOD_ID)){
							 System.out.println("DetermineBank, setting useBank to true - we need more food");
							quests.Vars.useBank = true;
							quests.Vars.ranOnce = true;
						}
					}
		}//else System.out.println("ranOnce: " + Vars.ranOnce);
	}
	public void useBank(int[] items, int[] amount) {
		final int destroyableItems[] = {19775,27156,26480,25131};
		boolean donesearch = false;
		state("Using the bank...");
		//if inventory is not open, open it.
		if(!ctx.hud.opened(Window.BACKPACK))
			ctx.hud.open(Window.BACKPACK);
		
		int freespace = 28 - items.length;//the freespace we have left over from the slots taken up by the items we need
		bankTile =new Tile(3180, 3482, 0);//the tile for our bank(G.E)
		int foodspace = freespace - 5;//how much food we are allowed to have taken out into inventory w/o causing problems during quest
		
		//make sure we're close to bank at G.E
		if (bankTile.distanceTo(ctx.players.local().tile()) < 8) {
			
			//Destroy unbankable items!
			for(int destroyItemID: destroyableItems){
				while(inventoryContains(destroyItemID)){
					
					if(ctx.widgets.component(1189,0).valid()&&
							ctx.widgets.component(1189,0).visible()){
						pressContinue();
					}
					if(ctx.bank.opened())//close bank if open
						ctx.bank.close();
					if(ctx.widgets.component(1183,16).visible()){//if the destroy screen is open
						ctx.widgets.component(1183,16).click();//click 'yes' to destroy item
						//ctx.game.sleep(1000);
					}else interactInventory(destroyItemID,"Destroy","Item");
				}
			}
			//Do the bank stuff below, once the bank is open
			if (ctx.bank.opened()) {
				
				
				//Determines depositing inventory.
					if(!depoBank){
						state("Deposit inventory initially");
						System.out.println("Inside dpeositing inventory");
						ctx.bank.depositInventory();
						//ctx.environment.sleep(2000);
						if(ctx.backpack.isEmpty())
						depoBank = true;
					}else{//after emptying our inventory we want to fill it w/stuff we need
					System.out.println("Finished w/depositing inventory");
				//below is just something to setup caching the items inside the bank
				ItemQuery<Item> bankstuff;
				bankstuff = null;
				bankstuff = ctx.bank.select();
				
				cacheBank();//stores ALL items in bank into an array in 'Vars' called 'bankItems' for use during questing
				
				//if we have enough room for food, we take some into our inventory.
				if(DeltaQuester.FOOD_FEATURE&&
						quests.Vars.bankItems.contains(DeltaQuester.FOOD_ID) &&
						inventoryGetCount(DeltaQuester.FOOD_ID)<foodspace){//needs to be 'less than', or else it'll keep taking out food
					
					    System.out.println("Taking " + foodspace + " of " + DeltaQuester.FOOD_ID + " out of the bank");
					    state("Taking out food...");
						ctx.bank.withdraw(DeltaQuester.FOOD_ID, foodspace);
				}else{//once the food is handled we move onto grabbing our items out of the bank
				 state("Taking out our items...");
				 while(bankHasAtleastOneOfItems(items, amount)){
					 state("Found some items in bank, taking them out..");
					for(Item i: bankstuff){
				
					for(int pos = 0; pos<items.length;){
						if(i.id()==items[pos]){
							state("Withdrawing: "+ items[pos]);
							ctx.bank.withdraw(items[pos], amount[pos]);
							
								pos++;
						}else {
							pos++;
						}
					}
				   }
				 }
				ctx.bank.close();
				System.out.println("Turning bank off: " + donesearch);
				quests.Vars.useBank = false;
					}
					  }
			} else {
				openBank();
				ctx.bank.open();
			}
			
			
		} else getToBank();
	}
	
	private boolean bankHasAtleastOneOfItems(int[] items, int[] amount) {
		ItemQuery<Item> bankstuff;
		bankstuff = null;
		bankstuff = ctx.bank.select();
		
		for(Item item: bankstuff){
			for(int i = 0;i<items.length;i++){
				if(ctx.bank.contains(item)&&item.id()==items[i] && !inventoryContains(items[i])||//if bank has item and inventory doesn't have it AT ALL
						ctx.bank.contains(item)&&item.id()==items[i] && inventoryGetCount(items[i])<amount[i]){//if bank has item and inventory has it but not enough of it
					System.out.println("Returning true for bank having atleast one item");
					return true;
				}
			}
			
		}
		System.out.println("Returning FALSE for bank having atleast one item");
		
		return false;
	}

	private void getToBank() {
		
		if(bankTile.tile().distanceTo(ctx.players.local().tile())>7){
			if(Vars.DYNAMICV){
				System.out.println("Inside getToBank");
				switch(DeltaQuester.number){
				case 1:
					walking(Paths.pathToBank2,"Walking to lummbridge bank", false);
					break;
				case 0:
					System.out.println("getToBank - case 0 walking area");
					walking(Paths.pathToGE,"Walking to Grand Exchange bank", false);
					break;
				
				}
			}else{
				System.out.println("getToBank - DYNAMICV is false");
				switch(DeltaQuester.number){
				
				case 1:
					System.out.println("Case 1");
					if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(ctx.players.local().tile())<10){
						Vars.DYNAMICV = true;
					}else teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
					break;
					
				case 0:
					System.out.println("Case 0");
					if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(ctx.players.local().tile())<10||
							TeleportLode.VARROCK.getTile().distanceTo(ctx.players.local().tile())<10){
						Vars.DYNAMICV = true;
					}else if(VarrokLodeIsActive()){
						System.out.println("Attempting to tele");
						teleportTo(TeleportType.VARROCK.getTeleport(),TeleportType.VARROCK.getName());
					}else {
						System.out.println("Teleporting to lumbridge b/c no varrock lode activated.");
						teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
					}
					break;
				}
			}
		}
		//System.out.println("Attempting" + bankTile+": "+rand.nextInt(3));
		
	}
public boolean forceInteractGItem(int ID){
	
	if(!ctx.groundItems.select().id(ID).first().isEmpty()){
		if(!ctx.widgets.component(1092,42).visible()){
			for(GroundItem item : ctx.groundItems.select().id(ID).nearest().first()){
				if (item.inViewport()) {
					
					ctx.input.move(item.centerPoint());
					
					boolean menuExist = false;
					for(String h : ctx.menu.items()){
						if(!h.contains("Walk here") && !h.contains("Cancel") && !h.contains("Examine")&&
								h.contains("Take")){
							menuExist = true;
							System.out.println("ground item with the menu of: "+h);
						}
					}
					
					
						if(item.interact("Take") && menuExist){
						//ctx.environment.sleep(500,600);
						return true;
						}else ctx.camera.turnTo(item.tile());
					
				} else ctx.camera.turnTo(item);
				break;
			}
			
		}else clickOnMap(ctx.players.local().tile());
	}
	
	return false;
}
	public void interactG(int i, String string, String string2) {
		if(!ctx.groundItems.select().id(i).first().isEmpty()){
			DeltaQuester.paintIndicator = i;
			if(!ctx.widgets.component(1092,42).visible()){
				for(GroundItem item : ctx.groundItems.select().id(i).nearest().first()){
					if (item.inViewport()) {
						state("Performing action on ground item: " + string2);
						ctx.input.move(item.centerPoint());
							if(item.interact(string)){
							//ctx.environment.sleep(500,600);
							break;
							}else ctx.camera.turnTo(item.tile());
						
					} else ctx.camera.turnTo(item);
					break;
				}
				
			}else clickOnMap(ctx.players.local().tile());
		}
		
	}
	public void combineItems(int item1, int item2) {
		state("Combining items");
		skipPics();
		if(!isChatting("Self")){
			if(ctx.backpack.itemSelected()){
				interactInventory(item2,"Use", "Item 2");
			}else interactInventory(item1,"Use","Item 1");
		}else System.out.println("Attempting to close dialogue so we can combine items");
		/*if(!inv.select().id(item1).first().isEmpty() && !inv.select().id(item2).first().isEmpty()){
			if(inv.itemSelected() && (inv.getSelectedItem().id()==item1 || inv.getSelectedItem().id()==item2)){
				interactInventory(item2, "Use", "Item");
			}else for(Item i : inv.getAllItems()){
				if(i.id() == item1){
					ctx.backpack.scroll(i);
					i.click();
				}
			}
		}
		*/
	}
	public void displayTileDifference(Tile tile) {
		state("X: " + (ctx.players.local().tile().x()-tile.x()) + "Y: "+ (ctx.players.local().tile().y()-tile.y()));
		
		
	}

	public boolean getToTile(Tile tile) {
		state("Walking to location: " + tile);
		Player local = ctx.players.local();
		if(tile.distanceTo(local.tile())<5){
			return true;
		}else clickOnMap(tile);
		return false;
	}

	
	public boolean EquipmentContains(int i) {
		if(ctx.bank.opened())
			ctx.bank.close();
		
		if(!ctx.hud.opened(Window.WORN_EQUIPMENT)){
			state("Opening equipment view");
			ctx.hud.open(Window.WORN_EQUIPMENT);
			sleep(2000);
		}
		if(!ctx.equipment.select().id(i).first().isEmpty()){
			return true;
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
	public boolean inventoryContains(String name) {
		if(!ctx.hud.opened(Window.BACKPACK)){
			while (ctx.bank.opened()){
				ctx.bank.close();
			}
			ctx.hud.open(Window.BACKPACK);
		}
			if(ctx.backpack.select().name(name).isEmpty()) {
				return false;
			}
		
		return true;
	}
public boolean forceWearItem(int ID){
	
	if(EquipmentContains(ID)){
		return true;
	}
	while(inventoryContains(ID)){
		System.out.println("Fiound tisobdddddddddu");
		interactInventory(ID, "", "");
		break;
	}
	return false;
	
}
	public void clickOnMap(Tile t) {
		try{
			if(t!=null)
		ctx.movement.step(ctx.movement.closestOnMap(t));
		}catch(Exception e){e.printStackTrace();}
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
		 
		int xTiles = ctx.widgets.component(1465, 12).getScrollWidth()/10;
		int yTiles = ctx.widgets.component(1465, 12).getScrollHeight()/10;
		int myX = ctx.players.local().tile().x();
		int myY = ctx.players.local().tile().y();
		int myPlane = ctx.players.local().tile().floor();
		 
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

	public boolean npcSays(String text) {
		if(ctx.widgets.component(1184,9).valid())
		if(ctx.widgets.component(1184,9).text().contains(text)){
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
			if(obj.tile().distanceTo(tile)<dist){
				return true;
			}
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
	public void checkBank() {
		System.out.println("Checking the bank initially - NOT 'useBank");
		//System.out.println("Now deciding bank.."+ DeltaQuester.DeltaQuester.number);
		if(!DeltaQuester.bankFound){
			System.out.println("Now deciding bank..");
			decideBank();
		}else
		if (bankTile.distanceTo(ctx.players.local().tile()) < 7) {
			System.out.println("Now3");
			Vars.DYNAMICV = false;
			if(ctx.bank.opened()){
				quests.Vars.bankItems.clear();
				for(Item i: ctx.bank.select()){
					if(!quests.Vars.bankItems.contains(i.id()))
						quests.Vars.bankItems.add(i.id());
				}
				System.out.println("Finished checking bank");
				DeltaQuester.checkedBank = true;
			}else openBank();
			
		} else getToBank();
		
	}
	private void openBank() {
		System.out.println("Attempting to open bank: "+ DeltaQuester.number);
		switch(DeltaQuester.number){
		case 0://G.E bank
			if(getNPC(2718).inViewport()){
		        npcInteract(2718,"Bank");
			}else ctx.camera.turnTo(getNPC(2718).tile());
			break;
		case 1://Lumbridge outside bank
			if(getObject(79036).inViewport()){
			  interactO(79036,"Use","Bank chest");
			}else ctx.camera.turnTo(getObject(79036).tile());
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
		
		if(ctx.bank.opened()){
		
			cacheBank();
			//ctx.environment.sleep(1200);
			if(inventoryContains(itemID) && inventoryGetCount(itemID)>=Amount){
				DeltaQuester.exchangeBank = true;
			}else if(inventoryContains(noteID)){
				state("Depositing note");
				ctx.bank.deposit(noteID, Amount);
				cacheBank();
				//ctx.environment.sleep(1200);
			}else if(!quests.Vars.bankItems.contains(itemID)){
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
		quests.Vars.bankItems.clear();
		for(Item i: ctx.bank.select()){
			if(!quests.Vars.bankItems.contains(i.id())){
				//System.out.println("Adding: " + i.id());
				quests.Vars.bankItems.add(i.id());
			}
		}
		
	}
	public void sleep(int millis){
		try {
			Thread.sleep(Math.max(5, (int) (millis * Random.nextDouble(0.85, 1.5))));
		} catch (InterruptedException ignored) {
		}
	}


	public boolean byCloseLoc(Tile loc, int dist) {
		Tile local = ctx.players.local().tile();
		
		if(local.distanceTo(loc)<dist){
			return true;
		}else {
			state("Walking to location");
			clickOnMap(loc);
		}
		return false;
	}
	public void setGeneralCamera() {
		if(ctx.camera.pitch()<50){
			state("Setting camera pitch");
			ctx.camera.pitch(60);
		}
		
	}/*
	public void bankItems(int[] bankItems, int[] amountOfItem) {
		ArrayList<Integer> itemsInBank= new ArrayList<Integer>();
		ArrayList<Integer> invSize= new ArrayList<Integer>();
		
		if(new Tile(3181,3481, 0).distanceTo(ctx.players.local().tile())<6){
		if(ctx.bank.opened()){
			boolean once = false;
			//find your inventory size
			ItemQuery<Item> y = ctx.backpack.select();
			for(Item j: y){
				if(!invSize.contains(j.id())){
					invSize.add(j.id());
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
				if(!itemsInBank.contains(bankItem.id())){
					itemsInBank.add(bankItem.id());
				}
			}
			for(int t = 0; t<bankItems.length;){
				if(!ctx.bank.opened())
					break;
				if(inventoryContains(bankItems[t])){
					for(Item item: ctx.backpack.select().id(bankItems[t])){
						if(item.stackSize()>2){
							if(item.stackSize()>=amountOfItem[t]){
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
				if(!ctx.bank.opened())
					break;
				System.out.println("Withdrawing food amount: " + foodAmount);
				//ctx.game.sleep(1200);
				ctx.bank.withdraw(DeltaQuester.FOOD_ID, foodAmount);
			}
			System.out.println("Setting goBank to false");
			Vars.DYNAMICV3 = false;
			goBank = false;
		}else if(!ctx.bank.open()){
			ctx.camera.turnTo(ctx.bank.nearest());
		}
	 }else if(Vars.DYNAMICV3){
			walking(Paths.pathToGE, "Walking to Varrock bank", false);
		}else if(TeleportLode.VARROCK.getTile().distanceTo(ctx.players.local().tile())<10){
			Vars.DYNAMICV3 = true;
		}else teleportTo(TeleportType.VARROCK.getTeleport(),"Varrock");
		
	}*/
	//public static Tile toTile(int hash){
		//return new Tile(Structure.TILE.getX(hash),Structure.TILE.getY(hash),Structure.TILE.getZ(hash));
	//}

	public boolean widgetOpenCloseIt(int parent, int child) {
		if(ctx.widgets.component(parent, child).visible()){
			state("Closing interupting interface..");
			ctx.widgets.component(parent, child).interact("");
		}
		return false;
	}

	public boolean dialogueClosed(int parent, int closebutton) {
		if(ctx.widgets.component(parent, closebutton).visible()){
			ctx.widgets.component(parent, closebutton).interact("");
			return false;
		}
		return true;
	}
	
	/*
	public void walkToLocation(Tile end) {
		Web web = new Web(ctx, ctx.players.local().tile(), end);
		if(!wait.isRunning())
		if(web.getPath() != null && web.getEnd().distanceTo(ctx.players.local()) > 4) {
			web.walk();
			wait = new //timer(Random.nextInt(2000,3000));
		}
		/*
		//state("Walking to location: " + end);
		//ctx.game.sleep(4000);
		Tile local = ctx.players.local().tile();
		TilePath path = pf.findPath(Structure.TILE.getHash(local.x(),local.y(),local.floor()),Structure.TILE.getHash(end.x(),end.y(), end.floor()) , 500l, false);
		
		if(path!=null)
		for(int i = 0;i< path.size();){
			local = ctx.players.local().tile();
			if(ctx.hud.opened(Window.FRIENDS)){
				System.out.println("Breaking because friend list open; debugging purposes");
				break;
			}
			PathNode p = path.get(i);
			System.out.println("End: "+local.distanceTo(end));
			
			
			if(!wait.isRunning())
			if(toTile(p.getHash()).distanceTo(local)>12||
					local.distanceTo(end)>12){
			clickOnMap(toTile(p.getHash()));
			wait = new //timer(4000);
			}else{
				//System.out.println("Incrementing: " + i);
				i++;
			}
		}
		
		
	}*/
	






}
