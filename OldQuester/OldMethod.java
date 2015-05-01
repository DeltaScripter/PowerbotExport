package OldQuester;


import java.util.ArrayList;

import org.powerbot.bot.rt4.client.Npc;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.GroundItem;
import org.powerbot.script.rt4.Interactive;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.ItemQuery;
import org.powerbot.script.rt4.Player;


public class OldMethod extends ClientAccessor{

		public OldMethod(ClientContext ctx) {
		super(ctx);
	}
		static boolean closedInterface = false;
	    public boolean teleporting = false;
	    static Tile bankTile;
		static int life = 500;//Used for food support
		static int baseLife;
		public static boolean useBank =true;
		public static int bankDecide = 5;
		
		public static int slot = 0;
		public static boolean depoBank = false;
		public static boolean onlyItemsGE = false;
		
		private int NPCTALKWIDGET = 241;
		private int NPCTALKWIDGET2 = 242;//with two lines of text
		private int NPCTALKWIDGET3 = 243;//with three lines of text
		private int NPCTALKWIDGET4 = 244;//with 4 lines of text
		private int OPTIONMENUWIDGET3 = 232;//with only three options to select
		private int OPTIONMENUWIDGET = 230;//with only three options to select
		private int OPTIONMENUWIDGET2 = 228;//with only two options to select
		private int PLAYERTALKWIDGET = 64;
		private int PLAYERTALKWIDGET2 = 65;
		private int PLAYERTALKWIDGET3 = 66;//three lines fo text
		private int GIVEYOUSTUFFWIDGET = 204;
		
		


		public boolean goBank =true;
	

	public int inventoryStackSize(int ID) {
		state("Don't know how to count stacksize");
		
		return 0;
	}
	public boolean bankContains(int id) {
		ArrayList<Integer> bankItems = new ArrayList<Integer>();
		
		//while(!ctx.bank.opened()){
		//	state("Don't know how to interact w/inventory");
			
		//}
		for(Item item : ctx.bank.select()){
			if(!bankItems.contains(item.id())){
				bankItems.add(item.id());
			}
		}
		return bankItems.contains(id);
	}
	   
	public boolean isChatting(final String p) {
		
		if(ctx.widgets.component(NPCTALKWIDGET, 1).visible()||
				ctx.widgets.component(NPCTALKWIDGET2, 1).visible()||
				ctx.widgets.component(NPCTALKWIDGET3, 1).visible()||
				ctx.widgets.component(NPCTALKWIDGET4, 1).visible()||
				ctx.widgets.component(OPTIONMENUWIDGET, 1).visible()||
				ctx.widgets.component(OPTIONMENUWIDGET3, 1).visible()||
				ctx.widgets.component(OPTIONMENUWIDGET2, 1).visible()||
				ctx.widgets.component(PLAYERTALKWIDGET, 1).visible()||
				ctx.widgets.component(PLAYERTALKWIDGET2, 1).visible()||
				ctx.widgets.component(PLAYERTALKWIDGET3, 1).visible()||
				ctx.widgets.component(GIVEYOUSTUFFWIDGET, 1).visible()){
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
		OldVars Vars = new OldVars();
		if(!ctx.widgets.component(OPTIONMENUWIDGET,1).visible()&&
				!ctx.widgets.component(OPTIONMENUWIDGET2,1).visible()&&
				   !ctx.widgets.component(OPTIONMENUWIDGET3,1).visible()){
			return false;
		}
		//deals with option menu 1(3 possible choices)
		for (String t:text) {
			for (int i :Vars.OPTIONVALUE) {
				if (ctx.widgets.component(OPTIONMENUWIDGET,1).visible()&&ctx.widgets.component(OPTIONMENUWIDGET, i).text().contains(t)) {
					ctx.input.click(ctx.widgets.component(OPTIONMENUWIDGET, i).centerPoint().x+10,ctx.widgets.component(OPTIONMENUWIDGET, i).centerPoint().y+3 , true);
					sleep(Random.nextInt(1000,1600));
					return true; 
				}
			}
		}
		//deals with option menu 2 (two possibl choices)
		for (String t:text) {
			for (int i :Vars.OPTIONVALUE) {
				if (ctx.widgets.component(OPTIONMENUWIDGET2,1).visible()&&ctx.widgets.component(OPTIONMENUWIDGET2, i).text().contains(t)) {
					System.out.println("Clicking option in second type menu");
					ctx.input.click(ctx.widgets.component(OPTIONMENUWIDGET2, i).centerPoint().x+10,ctx.widgets.component(OPTIONMENUWIDGET2, i).centerPoint().y+3 , true);
					sleep(Random.nextInt(1000,1600));
					return true; 
				}
			}
		}
		//deals with option menu 2 (two possibl choices)
				for (String t:text) {
					for (int i :Vars.OPTIONVALUE) {
						if (ctx.widgets.component(OPTIONMENUWIDGET3,1).visible()&&ctx.widgets.component(OPTIONMENUWIDGET3, i).text().contains(t)) {
							System.out.println("Clicking option in second type menu");
							ctx.input.click(ctx.widgets.component(OPTIONMENUWIDGET3, i).centerPoint().x+10,ctx.widgets.component(OPTIONMENUWIDGET3, i).centerPoint().y+3 , true);
							sleep(Random.nextInt(1000,1600));
							return true; 
						}
					}
				}
		return false;
	}
	
	public void state(final String message) {
		DeltaOldQuester.state = message;
	}
	
	

	public void interactInventory(final int i, final String string, final String o) {
		//state("Don't know how to interact w/inventory");
	ArrayList<String> actions = new ArrayList<String>();
		
		// if(!//timer.isRunning()){
		for(Item t : ctx.inventory.select().id(i).first()){
			//System.out.println(ctx.widgets.component(1477,122).component(0).boundingRect().getCenterY());
			if(inventoryOpened()){
				System.out.println("Hovering");
				t.hover();
				sleep(200);
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
						 //sleep(Random.nextInt(2000, 2500));
						break;
						}
					}
				}
			}else openInventory();
			}
			
		
	}
    private void openInventory() {
    	ctx.widgets.component(548, 51).interact("");//inventory button 
	}

	private boolean inventoryOpened() {
		if(ctx.widgets.component(548, 51).textureId()!=-1){
			return true;
		}
		return false;
	}

	public boolean isInCombat() {
        return ctx.players.local().interacting()!=null;
    }
    public void interactO(final String name, final String string, final String o) {
    	//ArrayList<String> actions = new ArrayList<String>();
    	
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
	public void interactO(final int i, final String string, final String o) {
		//ArrayList<String> actions = new ArrayList<String>();
		for(GameObject y: ctx.objects.select().id(i).nearest().first()){
			//if(closeInterfaces())
			if(y.inViewport() && y.interact(string)){
			//state("Interacting with: " + o);
			sleep(Random.nextInt(1100,1400));
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
	
	public boolean npcInteract(int i, String string) {
		ArrayList<String> actions = new ArrayList<String>();
		//if(!SpeakTo//timer.isRunning()){
		for(org.powerbot.script.rt4.Npc n : ctx.npcs.select().id(i).nearest().first()){
					n.hover();
					String menuItems[] = ctx.menu.items();
					for(String opt: menuItems){
						if(!actions.contains(opt))
							actions.add(opt);
					}
					for(String text: actions){
						if(text.contains(string)){
							state("Interacting with " + i + " and using " + string);
							// SpeakTo//timer = new //timer(2400);
							 System.out.println("interacting");
							  n.interact(string);
							   sleep(2000);
							   break;
							
						}
					}
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

	private int SPELLBOOKWIDGET = 218;
	private int LUMBRIDGESPELL = 1;
	public void teleportTo(int loc) {
		final int[] widgetsInterference = {};
		teleporting = true;
		
			
		for(int i: widgetsInterference){
			if(ctx.widgets.component(i,0).visible()){
				state("Clicking on map to close dialogue for teleporting");
				clickOnMap(ctx.players.local().tile());
			}
		}
		if(ctx.bank.opened()){
			ctx.bank.close();
		}else{//below will select the lumbridge teleport in the spell book
			if(ctx.players.local().animation()==-1)
			if(spellBookOpen()){
				ctx.widgets.component(SPELLBOOKWIDGET, LUMBRIDGESPELL).interact("");
				sleep(Random.nextInt(3000, 3500));
			}else openSpellBook();
		}	
		
	}

private int SPELLBOOKICONWIDGET = 54;
private int SPELLBOOKICONWIDGETPARENT = 548;
	private void openSpellBook() {
		state("Opening spell book");
		ctx.widgets.component(SPELLBOOKICONWIDGETPARENT, SPELLBOOKICONWIDGET).interact("");
	}

	private boolean spellBookOpen() {
		if(ctx.widgets.component(SPELLBOOKWIDGET,0).visible()){
			return true;}
		return false;
	}
	public void useItemOn(int item, int obj, int[] bounds, String string) {
		
		  if(!inventoryOpened())
				openInventory();
		
		interactInventory(item,"Use","Item");
		sleep(Random.nextInt(600, 1000));
		GameObject objs = ctx.objects.select().id(obj).each(Interactive.doSetBounds(bounds)).select(Interactive.areInViewport()).nearest().poll();
		objs.interact(string,"");
			sleep(2000);
		
		
	}
	public void useItemOn(int item, int obj, String string) {
		//state("Using item on object: " + ctx.inventory.selectedItem().);
		//System.out.println("Selected item: " + ctx.inventory.selectedItem().valid());
		//System.out.println("Selected type: " + ctx.inventory.selectionType());
		//System.out.println("Selected item index: " + ctx.inventory.selectedItemIndex());
	   if(!inventoryOpened())
		openInventory();
		for(Item g: ctx.inventory.select().id(item)){
			if(g.interact("Use")){
				
				for(GameObject y: ctx.objects.select().id(obj).nearest().first()){
					//if(closeInterfaces())
					if(y.inViewport() && y.interact("Use")){
						sleep(Random.nextInt(1000,2000));
						break;
					}else {
						ctx.camera.turnTo(y.tile());
					}
				
						}
				 break;
			}
		}
		/*
		//if(ctx.inventory.selectedItem().id()!=-1){
			interactO(obj, "", string);
			sleep(Random.nextInt(600, 1000));
		//}else 
			//if(inventoryContains(item))
			interactInventory(item,"Use","Item");
		
		*/
	}
	public void useItemOnNpc(int item, int npc, String string) {
		state("Don't know how to use item on npcs");
		
	}
	public void useItemOnG(int item, int obj, String string) {
		state("Don't know how to use item on ground item");
		
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
				    	ctx.widgets.component(both, 11).click();
				    	ctx.widgets.component(both, 7).click();
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
		 if(ctx.widgets.component(NPCTALKWIDGET,1).visible()){
			 ctx.widgets.component(NPCTALKWIDGET,3).click();//continue button
		 }
		 if(ctx.widgets.component(NPCTALKWIDGET2,1).visible()){
			 ctx.widgets.component(NPCTALKWIDGET2,4).click();//continue button
		 }
		 if(ctx.widgets.component(NPCTALKWIDGET3,1).visible()){
			 ctx.widgets.component(NPCTALKWIDGET3,5).click();//continue button
		 }
		 if(ctx.widgets.component(NPCTALKWIDGET4,1).visible()){
			 ctx.widgets.component(NPCTALKWIDGET4,6).click();//continue button
		 }
		 if(ctx.widgets.component(PLAYERTALKWIDGET,1).visible()){
			 ctx.widgets.component(PLAYERTALKWIDGET,3).click();//continue button
		 }
		 if(ctx.widgets.component(PLAYERTALKWIDGET2,4).visible()){
			 ctx.widgets.component(PLAYERTALKWIDGET2,4).click();//continue button
		 }
		 if(ctx.widgets.component(PLAYERTALKWIDGET3,5).visible()){
			 ctx.widgets.component(PLAYERTALKWIDGET3,5).click();//continue button
		 }
		 if(ctx.widgets.component(GIVEYOUSTUFFWIDGET,1).visible()){
			 ctx.widgets.component(GIVEYOUSTUFFWIDGET,1).click();//continue button
		 }
	}
/*
	public void useBank(int[] items, int[] amount) {
		final int destroyableItems[] = {19775,27156,26480,25131};
		boolean donesearch = false;
		state("Using the bank...");
		//if inventory is not open, open it.
		//if(!ctx.hud.opened(Window.BACKPACK))
			//ctx.hud.open(Window.BACKPACK);
		
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
						ctx.bank.depositInventory();
						//ctx.environment.sleep(2000);
						if(ctx.backpack.isEmpty())
						depoBank = true;
					}else{//after emptying our inventory we want to fill it w/stuff we need
					
				//below is just something to setup caching the items inside the bank
				ItemQuery<Item> bankstuff;
				bankstuff = null;
				bankstuff = ctx.bank.select();
				
				cacheBank();//stores ALL items in bank into an array in 'Vars' called 'bankItems' for use during questing
				
				//if we have enough room for food, we take some into our inventory.
				if(DeltaOldQuester.FOOD_FEATURE&&
						OldVars.bankItems.contains(DeltaOldQuester.FOOD_ID) &&
						inventoryGetCount(DeltaOldQuester.FOOD_ID)<foodspace){//needs to be 'less than', or else it'll keep taking out food
					
					    System.out.println("Taking " + foodspace + " of " + DeltaOldQuester.FOOD_ID + " out of the bank");
					    state("Taking out food...");
						ctx.bank.withdraw(DeltaOldQuester.FOOD_ID, foodspace);
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
				OldVars.useBank = false;
					}
					  }
			} else {
				ctx.bank.open();
			}
			
			
		} else getToBank();
	}
	*/
	public boolean bankHasAtleastOneOfItems(int[] items, int[] amount) {
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

	public void getToBank() {
		
		state("Need to make ability to get to a bank");
	}

	public void interactG(int i, String string, String string2) {
		if(!ctx.groundItems.select().id(i).first().isEmpty()){
			
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
		state("Combining items - NOPE duns knowhow to brah");
	
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
	
	public boolean getToTile(Tile tile, int dist) {
		state("Walking to location: " + tile);
		Player local = ctx.players.local();
		if(tile.distanceTo(local.tile())<dist){
			return true;
		}else clickOnMap(tile);
		return false;
	}

	
	public boolean EquipmentContains(int i) {
		//state("Don't know how to check equipment");
	  if(equipmentOpened()){
		  sleep(1000);
		  if(ctx.widgets.component(387, 8).component(1).itemId()==i
				  &&ctx.widgets.component(387, 8).component(1).visible()){//neck slot
			  return true;
		  }
		  if(ctx.widgets.component(387, 7).component(1).itemId()==i
				  &&ctx.widgets.component(387, 8).component(1).visible()){//cape slot
			  return true;
		  }
		  if(ctx.widgets.component(387, 6).component(1).itemId()==i
				  &&ctx.widgets.component(387, 8).component(1).visible()){//helmet slot
			  return true;
		  }
		  if(ctx.widgets.component(387, 9).component(1).itemId()==i
				  &&ctx.widgets.component(387, 8).component(1).visible()){//sword slot
			  return true;
		  }
		  if(ctx.widgets.component(387, 10).component(1).itemId()==i
				  &&ctx.widgets.component(387, 8).component(1).visible()){//armour slot
			  return true;
		  }
		  if(ctx.widgets.component(387, 11).component(1).itemId()==i
				  &&ctx.widgets.component(387, 8).component(1).visible()){//shield slot
			  return true;
		  }
		  if(ctx.widgets.component(387, 12).component(1).itemId()==i
				  &&ctx.widgets.component(387, 8).component(1).visible()){//leggings slot
			  return true;
		  }//All I care to add..
		  
	  }
		  
		//if(!ctx.equipment.select().id(i).first().isEmpty()){//if we have item in equipment
		//	return true;
		//}
		return false;
	}
public boolean equipmentOpened() {
		if(ctx.widgets.component(548, 52).textureId()==-1){
			state("Opening equipment..");
			ctx.widgets.component(548, 52).interact("");
			return false;
		}
		//System.out.println("Equipment now open");
		return true;
	}
private int INVETORYIDWIDGET = 149;
	public boolean inventoryContains(int i) {
		//state("Checking inventory..");
		int[] ids = (ctx.widgets.component(INVETORYIDWIDGET, 0).itemIds());
		for(int mito: ids){
			if(mito==i){
				return true;
			}
		}
		
		return false;
	}


	public void clickOnMap(Tile t) {
		try{
			
		ctx.movement.step(ctx.movement.closestOnMap(t));
		sleep(Random.nextInt(50, 200));
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
		
		if(inventoryOpened()){
		
		ItemQuery<Item> g;
		g = null;
		g = ctx.inventory.select().id(i);
		return g.count(true);
		
		}else openInventory();
		return 0;
	}

	public GameObject getObject(int i) {
		for(GameObject obj : ctx.objects.select().id(i).nearest().first()){
			return obj;
		}
		return null;
	}

	public Npc getNPC(int i) {
		state("Don't know how to get npc's");
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
		state("Don't know how to check  if inventory is full");
		
		return false;
	}


	public void cacheBank() {
		System.out.println("Clearing cache");
		OldVars.bankItems.clear();
		for(Item i: ctx.bank.select()){
			if(!OldVars.bankItems.contains(i.id())){
				//System.out.println("Adding: " + i.id());
				OldVars.bankItems.add(i.id());
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
		
	}
	

	public boolean widgetOpenCloseIt(int parent, int child) {
		if(ctx.widgets.component(parent, child).visible()){
			state("Closing interupting interface..");
			ctx.widgets.component(parent, child).interact("");
		}
		return false;
	}

	public void preciseInteractO(int i, int[] bounds, String string) {
			GameObject obj = ctx.objects.select().id(i).each(Interactive.doSetBounds(bounds)).select(Interactive.areInViewport()).nearest().poll();
			obj.interact(string,"");
	}

	public double tileDisctanceToPlayer(Tile tile) {
		return tile.distanceTo(ctx.players.local().tile());
		}

	public boolean getToNearByTile(Tile tile) {
		if(tile.matrix(ctx).reachable()){
		if(tile.distanceTo(ctx.players.local().tile())<4){
			return true;
		}else {
			//System.out.println("Clicking on tile : " + tile);
			if(!ctx.movement.findPath(tile).traverse())
			clickOnMap(tile);
			sleep(Random.nextInt(1000, 1400));
		}
		}else System.out.println("Can't get to tile we can't reach!");
		return false;
	}

	public boolean getPastDoor(Tile tileCanReach, Tile tilePastDoor, int doorID) {
		if(tilePastDoor.matrix(ctx).reachable()){
			return true;
		}else
		if(tileCanReach.matrix(ctx).reachable()){
			if(getToNearByTile(tileCanReach))
			interactO(doorID,"","The door");
		}
		return false;
	}

	public boolean getPastDoor(Tile tileCanReach, Tile tilePastDoor, int doorID, int[] bounds) {
		if(tilePastDoor.matrix(ctx).reachable()){
			return true;
		}else
		if(tileCanReach.matrix(ctx).reachable()&&
				getToNearByTile(tileCanReach)){
				preciseInteractO(doorID, bounds, "");
		}
		return false;
	}

	public boolean getToTileAndClickObject(Tile tile, int ID, String action) {
		if(tile.distanceTo(ctx.players.local().tile())<4){
			interactO(ID, action,"");
			return true;
		}else {
			clickOnMap(tile);
			sleep(Random.nextInt(1000, 1400));
		}
		return false;
	}

	public void speakToNPC(int npc, String[] opt) {
		if(!findOption(opt)){
			if(!isChatting("NPC")){
				npcInteract(npc,"Talk-to");
			}
		}
		
	}

	public void generalChatWithNPC(String[] opt, int npc) {
		if(!findOption(opt)){
			if(!isChatting("NPC")){
				npcInteract(npc,"Talk-to");
			}
		}
	}
	public boolean Takeoff(int i) {
		if(equipmentOpened()){
			 if(ctx.widgets.component(387, 8).component(1).itemId()==i){//neck slot
				 ctx.widgets.component(387, 8).component(1).click();
			  }
			  if(ctx.widgets.component(387, 7).component(1).itemId()==i){//cape slot
				  ctx.widgets.component(387, 8).component(1).click();
			  }
			  if(ctx.widgets.component(387, 6).component(1).itemId()==i){//helmet slot
				  ctx.widgets.component(387, 8).component(1).click();
			  }
			  if(ctx.widgets.component(387, 9).component(1).itemId()==i){//sword slot
				  ctx.widgets.component(387, 8).component(1).click();
			  }
			  if(ctx.widgets.component(387, 10).component(1).itemId()==i){//armour slot
				  ctx.widgets.component(387, 8).component(1).click();
			  }
			  if(ctx.widgets.component(387, 11).component(1).itemId()==i){//shield slot
				  ctx.widgets.component(387, 8).component(1).click();
			  }
			  if(ctx.widgets.component(387, 12).component(1).itemId()==i){//leggings slot
				  ctx.widgets.component(387, 8).component(1).click();
			  }
		}else openEquipment();
		
		return false;
	}
	private void openEquipment() {
	 	ctx.widgets.component(548, 52).interact("");//equipment button 
		
	}
	private boolean hasFood = true;
	public void foodSupport() {
		String g = ""+ ctx.widgets.component(548, 77).text().toString();
		int currentHealth = Integer.parseInt(g);
		
		if(hasFood && (currentHealth<=9)){
			
			state("Eating some food - low on health");
			interactInventory(DeltaOldQuester.foodID, "", "Food");
			
			if(!inventoryContains(DeltaOldQuester.foodID)){
				System.out.println("hasFood is false b/c no food in inventory");
				hasFood=false;
			}
		}
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
