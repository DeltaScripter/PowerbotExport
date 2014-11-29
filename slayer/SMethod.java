package slayer;

import java.util.ArrayList;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.Hud.Window;
import org.powerbot.script.rt6.Item;
import org.powerbot.script.rt6.ItemQuery;
import org.powerbot.script.rt6.MobileIdNameQuery;
import org.powerbot.script.rt6.Npc;


public class SMethod extends ClientAccessor{

	public SMethod(ClientContext arg0) {
		super(arg0);
	}
	public enum TeleportLode{
		
		CANIFIS(new Tile(3517,3515,0)),VARROCK(new Tile(3214,3378,0)),FALADOR(new Tile(2965,3402, 0)), LUMMBRIDGE(new Tile(3233,3221, 0)),
		BURTHORPE(new Tile(2899,3543, 0)),CATHERBY(new Tile(2831,3451,0)),PORTSARIM(new Tile(3011,3217,0)),
		DRAYNOR(new Tile(3107,3298,0)),ARDOUGNE(new Tile(2634,3348,0)), YANILLE(new Tile(2530,3095,0)),
		SEERS( new Tile(2689,3482,0)),TAVERLY(new Tile(2877,3441,0)),EDGEVILLE(new Tile(3068,3505,0)),
		FPROVINCE( new Tile(2710,3679,0)),KARAMJA( new Tile(2761,3147,0));
		Tile tile;
		TeleportLode(Tile tile){
			this.tile = tile;
		}
		public Tile getTile(){
			return tile;
		}
	}
	public void sleep(int millis){
		try {
			Thread.sleep(Math.max(5, (int) (millis * Random.nextDouble(0.85, 1.5))));
		} catch (InterruptedException ignored) {
		}
	}
	public boolean playerText(String string) {
		if (ctx.widgets.component(137,90).valid()) {
			//state("Checking: " + string);
			if (ctx.widgets.component(137,90).component(0).text()
					.contains(string)) {
				System.out.println("returning true for player text");
				return true;
			}
		}
		return false;
	}
	public enum TeleportType{
		ARDOUGNE(22,"Ardougne"),BURTHHORPE(18,"Burthorpe"),CATHERBY(19,"Catherby"),DRAYNOR(20,"Draynor"),
		FALADOR(22,"Falador"),LUMBRIDGE(23,"Lumbridge"),PORTSARIM(24,"Port Sarim"),
		SEERS(25,"Seers"),TAVERLY(26,"Taverly"),VARROCK(27,"Varrock"),YANILLE(28,"Yanille"),
		CANIFIS(34,"Canifis"),
		FREMNICKPRIVINCE(36,"Fremnik Province"),KARAMJA(37,"Karamja"),EDGEVILLE(26,"Edgeville");
		
		
		
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
		for(Item i : ctx.backpack.items()){
				if(i.id()!=-1)
				inventory.add(i.id());
		}
		return inventory.size()>=28;
	}
	
	public boolean EquipmentContains(int i) {
		
		if(!ctx.hud.opened(Window.WORN_EQUIPMENT)){
			state("Opening equipment open");
			ctx.hud.open(Window.WORN_EQUIPMENT);
			sleep(2000);
		}
		if(!ctx.equipment.select().id(i).first().isEmpty()){
			return true;
		}
		return false;
	}
	private boolean teleBank = false;
	private boolean once = false;
	public void bankItems(int[] bankItems, int[] amountOfItem) {

		Tile[] pathToBank = new Tile[] { new Tile(2965, 3398, 0), new Tile(2964, 3392, 0), new Tile(2964, 3386, 0), 
				new Tile(2960, 3381, 0), new Tile(2954, 3380, 0), new Tile(2948, 3377, 0), 
				new Tile(2944, 3372, 0), new Tile(2947, 3370, 0) };
		
		ArrayList<Integer> itemsInBank= new ArrayList<Integer>();
		
		if(new Tile(2947,3368, 0).distanceTo(ctx.players.local().tile())<6){
		if(ctx.bank.opened()){
			
			//if backpack is full (of food?..) deposit everything
			if(backPackIsFull()){
				System.out.println("Inventory is full, depositing.");
				ctx.bank.deposit(slayerbody.FOODID, bankItems.length);
			}
			//below is for depositing our inventory only once
			while(!slayerbody.onceDepositInventory){
				if(!ctx.bank.opened()){
					System.out.println("Bank is closed, breaking");
					break;
				}
				if(ctx.bank.depositInventory()){
					System.out.println("Deposited inventory");
					state("Depositing");
					slayerbody.onceDepositInventory = true;
					sleep(500);
				}
			}
			
			//Find all items in bank and store them in an arraylist
			for(Item bankItem: ctx.bank.select()){
				if(!itemsInBank.contains(bankItem.id()))
					itemsInBank.add(bankItem.id());
			}
			//Grabs the items we need from the bank
			for(int i = 0; i<bankItems.length;){
				
				if(!ctx.bank.opened()){//precaution for breaking a loop
					System.out.println("Bank is not open, breaking for loop");
					break;
				}
				//amountOfItem = amount of the item you want in the inventory
				//bankItems = the items you want from the bank
				if(!itemsInBank.contains(bankItems[i])){
					System.out.println("Cannot find item with ID: " + bankItems[i]);
					i++;
				}else
				if(itemIsStackable(bankItems[i])>amountOfItem[i]){
					System.out.println("Item is stackable and we now have enough of it");
					i++;
				}else if(inventoryGetCount(bankItems[i])==amountOfItem[i]){
					System.out.println("Have enough of item in inventory, equal amount");
					i++;
				}else if(inventoryGetCount(bankItems[i])>amountOfItem[i]){
					state("Depositing item of overabundunce");
					ctx.bank.deposit(bankItems[i],(inventoryGetCount(bankItems[i])-amountOfItem[i]) );
				}else {
					System.out.println("Current spot in array, "+ i);
					System.out.println("Amount of item currently in inventory: "+inventoryGetCount(bankItems[i]));
					ctx.bank.withdraw(bankItems[i], (amountOfItem[i]-inventoryGetCount(bankItems[i])));
					sleep(2000);
				}
			}
			//Food stuff below
			while(inventoryGetCount(slayerbody.FOODID)<(15)){
				System.out.println("Withdrawing food");
				state("Attempting to withdraw food");
				if(!ctx.bank.opened()){
					System.out.println("Bank is closed, breaking");
					break;
				}
				if(!itemsInBank.contains(slayerbody.FOODID)){
					System.out.println("Out of food!, breaking out of loop");
					break;
				}
				//System.out.println("Taking out "+(25 - (bankItems.length+10) -inventoryGetCount(slayerbody.FOODID))+ " of food");
				ctx.bank.withdraw(slayerbody.FOODID, (15));
			}
			while(inventoryGetCount(slayerbody.FOODID)>(15)){
				System.out.println("Depositing food; too much in inventory - need 10");
				if(!ctx.bank.opened()){
					System.out.println("Bank is closed, breaking");
					break;
				}
				//System.out.println("Taking out "+(25 - (bankItems.length+10) -inventoryGetCount(slayerbody.FOODID))+ " of food");
				ctx.bank.deposit(slayerbody.FOODID, (inventoryGetCount(slayerbody.FOODID)-15));
			}
			System.out.println("Finished banking");
			once = false;
			hasFood = true;
			slayerbody.currentTask ="Finished task, getting new one";
			slayerbody.goBank = false;
			
		}else{
			state("Opening bank");
			ctx.bank.open();
		}
	 }else if(teleBank){
		   ctx.movement.newTilePath(pathToBank).traverse();
		}else if(TeleportLode.FALADOR.getTile().distanceTo(ctx.players.local().tile())<30){
			teleBank = true;
		}else teleportTo(TeleportType.FALADOR.getTeleport(),"Falador");
		
	}

	private int itemIsStackable(int id) {
		for(Item i:ctx.backpack.select().id(id)){
			return i.stackSize();
		}
		return 0;
	}

	public int inventoryGetCount(int i) {
		if(!ctx.hud.opened(Window.BACKPACK) || !ctx.hud.opened(Window.BACKPACK))
			ctx.hud.open(Window.BACKPACK);
		
		ItemQuery<Item> g;
		g = null;
		g = ctx.backpack.select().id(i);
		for(Item h:ctx.backpack.select().id(i)){
		 if(h.stackSize()>2){
			 return h.stackSize();
		 }
		}
	
		return g.count(false);
	}
	public boolean state (String txt){
		slayerbody.state = txt;
		return true;
	}
	public boolean byCloseLoc(Tile loc, int dist) {
		Tile local = ctx.players.local().tile();
		
		if(local.distanceTo(loc)<dist){
			return true;
		}else ctx.movement.step(loc);
		
		return false;
	}
	public boolean objIsByTile(Tile tile, int object, int dist) {
		for(GameObject obj : ctx.objects.select().id(object).nearest(tile)){
			if(obj.tile().distanceTo(tile)<dist){
				return true;
			}
		}
		return false;
	}

	static ArrayList<Tile> path = new ArrayList<Tile>();
	boolean oncew = false;
	boolean pass = false;
	public ArrayList<Tile> walkTo (Tile dest, String destName) {/*
		Tile local = ctx.players.local().tile();
		Tile[] nodes = new Tile[] { new Tile(3108, 3288, 0), new Tile(3108, 3282, 0), new Tile(3106, 3276, 0), 
				new Tile(3104, 3270, 0), new Tile(3103, 3264, 0), new Tile(3102, 3258, 0), 
				new Tile(3102, 3252, 0), new Tile(3104, 3246, 0), new Tile(3105, 3240, 0), 
				new Tile(3105, 3234, 0), new Tile(3107, 3228, 0), new Tile(3111, 3223, 0), 
				new Tile(3117, 3222, 0), new Tile(3123, 3222, 0), new Tile(3128, 3226, 0), 
				new Tile(3134, 3229, 0), new Tile(3140, 3229, 0), new Tile(3145, 3233, 0), 
				new Tile(3151, 3236, 0), new Tile(3157, 3239, 0), new Tile(3163, 3241, 0), 
				new Tile(3169, 3242, 0), new Tile(3175, 3244, 0), new Tile(3181, 3246, 0), 
				new Tile(3187, 3246, 0), new Tile(3193, 3246, 0), new Tile(3199, 3246, 0), 
				new Tile(3205, 3246, 0), new Tile(3211, 3246, 0), new Tile(3216, 3242, 0), 
				new Tile(3222, 3239, 0), new Tile(3227, 3235, 0), new Tile(3231, 3230, 0), 
				new Tile(3235, 3225, 0), new Tile(3237, 3219, 0), new Tile(3236, 3213, 0), 
				new Tile(3237, 3219, 0), new Tile(3237, 3225, 0), new Tile(3243, 3227, 0), 
				new Tile(3249, 3227, 0), new Tile(3255, 3227, 0), new Tile(3258, 3233, 0), 
				new Tile(3258, 3239, 0), new Tile(3257, 3245, 0), new Tile(3254, 3251, 0), 
				new Tile(3251, 3257, 0), new Tile(3250, 3263, 0), new Tile(3249, 3269, 0), 
				new Tile(3247, 3275, 0), new Tile(3243, 3280, 0), new Tile(3240, 3286, 0), 
				new Tile(3239, 3292, 0), new Tile(3237, 3298, 0), new Tile(3239, 3302, 0), 
				new Tile(3241, 3308, 0), new Tile(3244, 3314, 0), new Tile(3249, 3318, 0), 
				new Tile(3254, 3322, 0), new Tile(3260, 3325, 0), new Tile(3266, 3328, 0), 
				new Tile(3266, 3334, 0), new Tile(3261, 3338, 0), new Tile(3255, 3340, 0), 
				new Tile(3249, 3341, 0), new Tile(3243, 3342, 0), new Tile(3237, 3344, 0), 
				new Tile(3231, 3345, 0), new Tile(3225, 3348, 0), new Tile(3221, 3353, 0), 
				new Tile(3220, 3359, 0), new Tile(3219, 3360, 0), new Tile(3216, 3366, 0), 
				new Tile(3213, 3372, 0), new Tile(3213, 3377, 0) };
		
		ArrayList<Tile> openList = new ArrayList<Tile>();
		
		for (int i = 0;i < nodes.length-1;i++){
			
				if(!openList.contains(nodes[i+1]))
					openList.add(nodes[i+1]);
		}
		while(!pass){
			if(ctx.hud.opened(Window.FRIENDS)){System.out.println("Breaking, friends");break;}
			pass = true;
		for(int o = 0; o < openList.size()-1; o++){
			if(ctx.hud.opened(Window.FRIENDS)){System.out.println("Breaking, friends");break;}
			
			local = ctx.players.local().tile();
			//System.out.println("Our path tiles: " + openList.get(o) + " size: " + openList.size());
			
			int dist3 = (int)openList.get(o).distanceTo(dest);
			int dist4 = (int)openList.get(o+1).distanceTo(dest);
			
			
			
			if(dist4  < dist3){
				pass = false;
				System.out.println("Swapped: " + dist3 + " for " + dist4);
				Tile temp = openList.get(o+1);
				openList.set(o+1, openList.get(o));
				openList.set(o,temp);
			}
			
			
		}
		}
		for(Tile f: openList){
			System.out.println("Tile: " + f + " : dist: " + (int)f.distanceTo(dest));
		}
		System.out.println();
		
		Tile[] arr = {};
			arr = new Tile[openList.size()];
		
		for(int i = 0; i < arr.length; i++) {
			System.out.println(" size of our path: " + arr.length);
			arr[i] = openList.get(i);
		}
		ctx.movement.newTilePath(arr).traverse();
		
		
		
		/*
		  web = new Web(ctx, ctx.players.local().tile(), dest );
			state("Walking eto "+destName + " " + web.getPath().getNext() );
			if(web.getPath() != null && !wait.isRunning()) {
				state("Walking to "+destName + " " + web.getPath().getNext() );
				wait = new Timer(Random.nextInt(3000, 4000));
				web.walk();
				
			}*/
		
		return null;
	}
	public void interactO(final int i, final String string, final String o) {
		for(GameObject y: ctx.objects.select().id(i).nearest().first()){
			if(y.inViewport() && y.interact(string)){
			sleep(Random.nextInt(600, 1600));
			}else {
				ctx.camera.turnTo(y.tile().derive(2, 3));
			}
		}
		
	}
	public void clickOnMap (Tile loc){
		ctx.movement.step(ctx.movement.closestOnMap(loc.derive(1, 3)));
	}
	
	public void teleportTo(int loc, String teleName) {
		final int[] widgetsInterference = {1184,1189,1244,105,1191,149,1199,438,1242,1186,1188,1350,149,667};
		
		for(int i: widgetsInterference){
			if(ctx.widgets.component(i,0).valid()){
				state("Clicking on map to close dialogue");
				clickOnMap(ctx.players.local().tile());
			}
		}
		
		
		if(ctx.players.local().animation()==-1)
			if(ctx.bank.close() && ctx.widgets.component(1092,loc).visible()){//lodestone screen
				state("Selecting teleport: " + teleName);
				ctx.mouse.move(ctx.widgets.component(1092,loc).centerPoint());
				ctx.widgets.component(1092,loc).click(true);
				sleep(3000);
			}else {
				if (!ctx.players.local().inCombat()){
					if (ctx.players.local().animation() == -1){
						ctx.widgets.component(1477,38).component(1).hover();//1477,59,1
						ctx.widgets.component(1477,38).component(1).click();//select lodestone button
							
					}
				}else slayerbody.state = "Waiting for character to not be in combat";
			}		
				
		
	}
	
	
	public boolean objIsNotNull(int id) {
		if(!ctx.objects.select().id(id).first().isEmpty()){
			return true;
		}
		return false;
	}
	public void displayTileDifference(Tile tile) {
		state("X: " + (ctx.players.local().tile().x()-tile.x()) + "Y: "+ (ctx.players.local().tile().y()-tile.y()));
		
	}
	public boolean handleDoor(Tile tile, int id, String action) {
         Tile local = ctx.players.local().tile();
         
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
        final MobileIdNameQuery<Npc> npcs = ctx.npcs.select();
       
        if(npcs != null && npcs.size() > 0)
            for(final Npc npc : npcs)
                if(npc.interacting() != null && npc.interacting().equals(ctx.players.local())){
           
                	return npc.id();
                }
        return 0;
    }
	public Npc getInteractingNPC()
    {
        final MobileIdNameQuery<Npc> npcs = ctx.npcs.select();
       
        if(npcs != null && npcs.size() > 0)
            for(final Npc npc : npcs)
                if(npc.interacting() != null && npc.interacting().equals(ctx.players.local()))
                    return npc;
        return null;
    }
	public void pressContinue(){
		 int widgetID[] = {1191,1184,1187};
		 if(ctx.widgets.component(1186,3).valid()){
			 ctx.widgets.component(1186,3).click();
			 sleep(1200);
		 }else if(ctx.widgets.component(1189,4).valid()){
			 ctx.widgets.component(1189,4).click();
			sleep(1200);
		 }
		for(int both : widgetID){
		    if(ctx.widgets.component(both,14).valid()){
		    	ctx.widgets.component(both, 14).click();
		    	sleep(500);
		    }
		}
	}
	private boolean closeInterfaces() {
		int widgetInterference[] = {1188,1184,1092,1186,1189};
		System.out.println("Inside close interfaces");
		while((ctx.widgets.component(1244,23).valid())){//completed quest screen
			state("Closing completed quest screen");
				ctx.widgets.component(1244,23).click(true);
		}
			while(ctx.bank.opened()){
				ctx.bank.close();
			}
		for(int index: widgetInterference){
			
			if(ctx.widgets.component(index,0).valid()){
				System.out.println("Closing an interface");
				if(index==1186){
					pressContinue();
				}else{
					state("Clicking on map to close dialogue");
				clickOnMap(ctx.players.local().tile().derive(Random.nextInt(2, 4),Random.nextInt(1, 3)));
				return false;
				}
			  }
			}
		return true;
	}
	public void npcInteract(int i, String string) {
		for(Npc n : ctx.npcs.select().id(i).nearest().first()){
			if(ctx.players.local().inCombat()){
				System.out.println("Sleeping slightly from combat");
				sleep(Random.nextInt(300, 730));
			}
			if(n.inViewport()){
				if(!n.interact(string)){
					System.out.println("failed to interact");
					ctx.camera.turnTo(n.tile().derive(1, 3));
				}else sleep(Random.nextInt(400, 830));
			}else {
				ctx.camera.turnTo(n.tile().derive(1, 3));
			}
		}
		
	}
	public void fightNPC(int id, String action) {
		
		
		if (actionBarOpened())
			if (!inCombat()) {
				attackNPC(id, action);// /uses 'attack' on the npc we're fighting
				slayerbody.state = "Waiting";
			} else
				for (int i = 0; i < 9; i++) {
					//System.out.println("In for loop");
					if (ctx.players.local().healthPercent() < 40) {
						System.out.println("Breaking; too low health");
						break;
					}


					if (!inCombat()) {
						break;
					} else if (ctx.combatBar.actionAt(i).ready()) {
						slayerbody.state = "Fighting!";
						if (ctx.combatBar.actionAt(i).select(true)) {
							i++;
							sleep(Random.nextInt(50, 200));
						} else {
							ctx.combatBar.actionAt(i).component().click();
							i++;
						}

					} else i++;

				}
		
	}
	
	public boolean inCombat() {
		return ctx.players.local().interacting().valid();
	}
	private boolean actionBarOpened() {
		return ctx.combatBar.expanded();
	}
	private void attackNPC(int id, String action) {
		
		for(Npc enemy: ctx.npcs.select().id(id).nearest().first()){
			
			if(enemy.tile().distanceTo(ctx.players.local().tile())<6){

				if(!ctx.players.local().inMotion() && enemy.valid()&&
						enemy.healthPercent()>=20){
					System.out.println("Enemy stance:" + enemy.stance());
					System.out.println("Enemy healthpercent:" + enemy.healthPercent());
					System.out.println("Enemy in combat:" + enemy.inCombat());
				npcInteract(enemy.id(),action);
				sleep(Random.nextInt(200, 400));
				}
				
			}else if(!ctx.players.local().inMotion())
				ctx.movement.step(enemy.tile().derive(2, 3));
		}
		
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
	public GameObject getObject(int i) {
		for(GameObject obj : ctx.objects.select().id(i).nearest().first()){
			return obj;
		}
		return null;
	}
	
	
	
	public static boolean hasFood = true;
	
	public void foodSupport() {
		//System.out.println("Inside food support");
		if(ctx.widgets.component(1430,83).valid()){//health bar
			double barHealth = ctx.widgets.component(1430,83).component(3).width();
			double maxHealth = ctx.widgets.component(1430,83).component(1).width() + 32;
			slayerbody.health = (barHealth/maxHealth)*100;
		}
		 
		if(slayerbody.FOOD_FEATURE) {
			if (ctx.players.local().healthPercent()<55) {
			  if(hasFood)
				if(closeInterfaces() &&inventoryContains(slayerbody.FOODID)){
					System.out.println("Attempting to eat food");
					state("Food support initiated");
					interactInventory(slayerbody.FOODID, "Eat","Food");
					}else hasFood = false;
				
			}
		}
	}
	public boolean inventoryContains(String i) {
		//System.out.println("Inside inventorycontains(string)");
		if(!ctx.hud.opened(Window.BACKPACK)){
			while (ctx.bank.opened()){
				ctx.bank.close();
			}
			ctx.hud.open(Window.BACKPACK);
		}
			while (!ctx.backpack.select().name(i).isEmpty()) {
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
	public void interactInventory(final int id, final String string, final String o) {
		if(closeInterfaces())
		for(Item t : ctx.backpack.select().id(id).shuffle()){
			if(ctx.hud.open(Window.BACKPACK) && ctx.widgets.component(1473,7).contains(
				t.component().centerPoint())){
				t.hover();
				t.interact(string);
				sleep(Random.nextInt(200,570));
				 break;
			}else
			if(ctx.widgets.component(1473,7).boundingRect().getCenterY()>
			t.component().boundingRect().getCenterY()){
				ctx.mouse.move(ctx.widgets.component(1473, 7).centerPoint());
				ctx.mouse.scroll(false);
			}else {
				ctx.mouse.move(ctx.widgets.component(1473, 7).centerPoint());
				ctx.mouse.scroll(true);
				}
			}
	}

	public boolean isChatting(final String p) {
		int[] widgets = {1184,1191,1187};
		
		for(int w : widgets){
			if(ctx.widgets.component(w,1).valid()){
				state("Speaking to: " + p);
				pressContinue();
				sleep(300);
				return true;
			}
		}
		return false;
	}
	public  final int OPTIONVALUE[] = {11,12,18,19,23,24,28,29,33,34};
	public  boolean findOption(String[] text) {
		if(!ctx.widgets.component(1188,1).valid()){
			return false;
		}
		for (String t:text) {
			sleep(50);
			for (int i :OPTIONVALUE) {
				if (ctx.widgets.component(1188,1).valid()&&ctx.widgets.component(1188, i).text().contains(t)) {
					state("Attempting to click option");
					ctx.mouse.click(ctx.widgets.component(1188, i).centerPoint().x+10,ctx.widgets.component(1188, i).centerPoint().y+3 , true);
					return true; 
				}
			}
		}
		return false;
	}
	public void useItemOnNpc(int item, int npc, String string) {
		System.out.println("Inside useItemOnNPC");
		state("Using item on npc");
		if(closeInterfaces())
		if(ctx.backpack.itemSelected()){
			npcInteract(npc,"Use");
		}else if(inventoryContains(item))
			interactInventory(item,"Use","Item");
		
	}
	public void setGeneralCamera(int camAngle) {
		if(ctx.camera.pitch()<50){
			state("Setting camera pitch");
			ctx.camera.pitch(camAngle);
		}
		
	}
	public void simpleWalk(Tile[] path, String string) {
			state(string);
			ctx.movement.newTilePath(path).randomize(1, 2).traverse();
			sleep(3500);
		
	}
}
