package deltaartisan;

import java.util.ArrayList;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import org.powerbot.script.util.Random;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.Component;
import org.powerbot.script.wrappers.Item;
import org.powerbot.script.wrappers.Widget;

import deltaartisan.ArtisanData.itemIndex;

public class burialArmor extends ArtisanNode{

	public burialArmor(MethodContext ctx) {
		super(ctx);
	}
	//Constants
	Widget SMITHING_INTERFACE = ctx.widgets.get(1371);
	Component SMITH_BUTTON = ctx.widgets.get(1370,20);
	Component CLOSE_BUTTON = ctx.widgets.get(1371,30);
	Component SELECTEDITEM_INMENU = ctx.widgets.get(1370,56);
	
	Timer clickTimer = new Timer (0);
	
	//Controls what item you're going to make
	public static int itemToMakeB;//what burial armour to make
	public static String itemToMakeBName ="";;//What the name of the item is, used for matching when selecting the item in the interface
	//public static String itemToMakeBFullName = "Miner chestplate (iron)";//Used when checking inventory items
	public static String typeOfIngot = "";//The type of ingot you are using to smelt (used in inventory checking)
	public static int typeOfIngotIndex;//used for selecting the ingot when withdrawing.
	
	//Other stuff
	ArtisanMethod m = new ArtisanMethod(ctx);
	String text = "";
	public boolean prepare = false;//the variable that controls smelting & gathering the ingots/depositing
	@Override
	public boolean activate() {
		return ArtisanBody.makeBurialArmour;
	}

	@Override
	public void execute() {
		m.calcExpHr();
		
		if(ctx.players.local().getAnimation()!=-1){
			clickTimer = new Timer(6000);
			ArtisanBody.state = "Smithing...";
		}
		if(!m.inventoryContains(typeOfIngot))
			prepare = true;//Go gather more ingot/deposit the items
		else prepare = false;
		
		if(!clickTimer.isRunning() && prepare){
			if(hasOtherItemsInInventory()){
				//deposit the item..
				ArtisanBody.state = "Depositing items";
				m.interactO("Chute", "Deposit-armour", "Chute");
				clickTimer = new Timer(Random.nextInt(1000, 2300));
			}else if(SMITHING_INTERFACE.isValid()){
				//select/withdraw the ingot type you want
				ArtisanBody.state = "Selecting type of ingot";
				selectOption(typeOfIngotIndex, typeOfIngot);
			}else{
				ArtisanBody.state = "Selecting smelter";
				m.interactO("Smelter", "Withdraw-ingots", "Smelter");
				clickTimer = new Timer(Random.nextInt(1000, 2300));
			} 
		}
		
		if(!clickTimer.isRunning() && !prepare)
		if(SMITHING_INTERFACE.isValid()){
			ArtisanBody.state = "Selecting armour";
			selectOption(itemToMakeB, itemToMakeBName);
		}else{
			ArtisanBody.state = "Using anvil";
			m.interactO("Anvil", "Smith", "Anvil");
			clickTimer = new Timer(Random.nextInt(1000, 2300));
		}
		
	}

	private boolean hasOtherItemsInInventory() {
		for(Item i : ctx.backpack.getAllItems()){
			if(i.getName()!="")
				if(i.getName()!=typeOfIngot){
					System.out.println("Unknown item: " + i.getName());
					return true;
				}
		}
		return false;
	}

	private void selectOption(int itemToMakeB2, String itemToMakeBName2) {
		if(SELECTEDITEM_INMENU.getText().contains(itemToMakeBName2)){
			//Then we're making the right item.
			SMITH_BUTTON.click();
			clickTimer = new Timer(Random.nextInt(900, 1700));
		}else {//select the item from the menu
			ctx.widgets.get(1371,44).getChild(itemToMakeB2).click();
			clickTimer = new Timer(Random.nextInt(1300, 2300));
		}
		
	}

}
