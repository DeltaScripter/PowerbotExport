package deltaflipper;

import java.util.ArrayList;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import org.powerbot.script.methods.Hud.Window;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.Component;
import org.powerbot.script.wrappers.Item;
import org.powerbot.script.wrappers.Npc;


public class FlipperExchange extends MethodProvider{

	public FlipperExchange(MethodContext arg0) {
		super(arg0);
	}
	Timer wait = new Timer(0);
	private void sendText(String amount) {
		if(!wait.isRunning()){
			ctx.keyboard.sleep(1500);
			ctx.keyboard.send(amount);
			wait = new Timer(2000);
		}
		
	}
	private int quantity() {
		String num = ctx.widgets.get(105,49).getText().toString();
		num  = num.replace(",", "");
		return Integer.parseInt(num);
	}
	private void setPriceSell(int i) {
		System.out.println("Setting price");
		String price = ""+i;
		Component g = ctx.widgets.get(1469,2);//search box for price
		if(g.isVisible() && g.getText().contains("you wish to sell")){//if open
			if(ctx.widgets.get(1469,3).getText().equals(price)){
				ctx.keyboard.sendln("");
			}else if(ctx.widgets.get(1469,3).getText().isEmpty()){
				sendText(price);
			}else ctx.widgets.get(105,154).click();//re-open the search textbox
		}else ctx.widgets.get(105,154).click();
		
	}
	private void setPriceBuy(int i) {
		System.out.println("Setting price");
		String price = ""+i;
		Component g = ctx.widgets.get(1469,2);//search box for price
		if(g.isVisible() && g.getText().contains("you wish to buy")){//if open
			if(ctx.widgets.get(1469,3).getText().equals(price)){
				ctx.keyboard.sendln("");
			}else if(ctx.widgets.get(1469,3).getText().isEmpty()){
				sendText(price);
			}else ctx.widgets.get(105,154).click();//re-open the search textbox
		}else ctx.widgets.get(105,154).click();
		
	}
	private void setQuantity(int itemAmount) {
		System.out.println("Settinng quantity");
		String amount = ""+itemAmount;
		if(ctx.widgets.get(1469,2).isVisible()){//typing box for quantity
			if(ctx.widgets.get(1469,3).getText().equals(amount)){
			ctx.keyboard.sendln("");
			}else if(ctx.widgets.get(1469,3).getText().isEmpty()){
				sendText(amount);
			}else {
				ctx.widgets.get(105,131).click();//empty the search box (try again)
				ctx.game.sleep(1200);
			}
		
		}else ctx.widgets.get(105,131).click();//search quantity button
	
		
	}
	private int price() {
		String price = ctx.widgets.get(105,52).getText();
		price = price.replaceAll(" gp", "");
		price = price.replaceAll(",", "");
		return Integer.parseInt(price);
	}
	public void makeSellOffer(int slot, String nameOfItem, int quantity, int price, int ID){
		System.out.println("Making sell offer");
		if(OpenSellScreen(slot)){
			Component itemName = ctx.widgets.get(105,4);//text near top of screen
			
			if(itemName.getText().contains(nameOfItem)){
				//if(quantity()==(quantity)){
					if(price()==price){
						
						Component buy = ctx.widgets.get(105, 166);
						if(ctx.widgets.get(1469,0).isVisible()){//if search is still open..
							ctx.widgets.get(1469,2).click();
							setPriceSell(price);
						}else buy.click(true);
						
					}else setPriceSell(price);
				//}else setQuantity(quantity);
			}else interactInventory(ID, "Offer", "Item");//select it from inventory
		}
	}
	public void interactInventory(final int id, final String string, final String o) {
		System.out.println("Checking inventory/using");
		for(int t = 0; t<28;t++){
			System.out.println(ctx.widgets.get(107,4).getChild(t).getItemId());
			if(ctx.widgets.get(107,4).getChild(t).getItemId()==id){
				ctx.widgets.get(107,4).getChild(t).interact(string);
			}
		}
	}
	public void makeBuyOffer(int slot, String nameOfItem, int quantity, int price){
		System.out.println("Making buy offer");
		if(OpenBuyScreen(slot)){
			Component itemName = ctx.widgets.get(105,4);//text near top of screen
			Component searchIcon = ctx.widgets.get(389,11);//the magnifying glass
			Component confirmOffer = ctx.widgets.get(105,65);
			Component selectQuantity = ctx.widgets.get(105,58);
			Component selectAmount = ctx.widgets.get(105,63);
			Component searchResults = ctx.widgets.get(389,4);
			Component searchButton = ctx.widgets.get(105,11);//near top
			Component typingArea = ctx.widgets.get(389,7);//search typing area
			
			if(itemName.getText().contains(nameOfItem)){
				if(quantity()==(quantity)){
					if(price()==price){
						
						Component buy = ctx.widgets.get(105, 166);
						if(ctx.widgets.get(1469,0).isVisible()){//if search is still open..
							ctx.widgets.get(1469,2).click();
							setPriceBuy(price);
						}else buy.click(true);
						
					}else setPriceBuy(price);
				}else setQuantity(quantity);
			}else typeItem(nameOfItem);//search item and select it
		}
	}
	private void typeItem(String name) {
		System.out.println("Typing item");
		Component[] spot = null;
		Component results = ctx.widgets.get(389, 4);
		if(ctx.widgets.get(389,11).isVisible()){//magnifying glass button is visible
			if(ctx.widgets.get(389,7).getText().equals(name + "*")){
				spot = ctx.widgets.get(389,4).getChildren();//grabs all items in search result
				for (int s = 0; s < spot.length;) {
					System.out.println("Searching for item:(num results: " + spot.length);
					if(!ctx.widgets.get(105).isValid())//assume ge?
						break;
				
					if (ctx.widgets.get(389, 4).getChild(s).getText().equals(name)) {
						if(results.getBoundingRect().contains(ctx.widgets.get(389, 4).getChild(s).getAbsoluteLocation())){
							System.out.println("Clicking option");
							ctx.widgets.get(389, 4).getChild(s).click();
						}else
						if(results.getAbsoluteLocation().y>ctx.widgets.get(389, 4).getChild(s).getAbsoluteLocation().y){
							System.out.println("scrolling list down");
							ctx.mouse.move(results);
							ctx.mouse.scroll(false);
						}else {
							System.out.println("scrolling mouse");
							ctx.mouse.move(results);
							ctx.mouse.scroll(true);
						}
					}else s++;

				}
			}else if(ctx.widgets.get(389,7).getText().equals("*")){
				sendText(name);
			}else {
				ctx.widgets.get(105,11).click();//reset search box
				ctx.environment.sleep(1200);
			}
		}else {
			System.out.println("Opening the textbox");
			ctx.widgets.get(105,11).click();//open the textbox
			ctx.keyboard.sleep(800);
		}
		
	}
	public boolean OpenGeScreen(){
		if(!ctx.widgets.get(105,86).isVisible()){
			FlipperBody.state = "Opening GE screen";
			for(Npc clerk: ctx.npcs.select().id(2241,2240).nearest().first()){
				clerk.interact("Exchange");
			}
		}
		return ctx.widgets.get(105,86).isVisible();
	}
	public boolean inventoryContains(int ID) {
		if(!ctx.hud.isVisible(Window.BACKPACK)){
			ctx.hud.view(Window.BACKPACK);
			sleep(2000);
		}
			while (!ctx.backpack.select().id(ID).first().isEmpty()) {
				return true;
			}
		
		return false;
	}
	public void collectItem(String name, int slot) {
		int collect[] = {77,79};
		System.out.println("Collecting item");
		if(ctx.widgets.get(105,5).isVisible()){//collect area
			if(ctx.widgets.get(1469,1).isVisible()){//search bar still open(causes glitch)
				ctx.widgets.get(105,87).click();//the exit button in GE
			}
			for (int col : collect) {
				if (ctx.widgets.get(105, col).getItemId() != -1) {
					if(ctx.widgets.get(105, col).interact("Collect-items")){
						ctx.widgets.get(105, col).interact("Collect-items");
					}else{
						ctx.widgets.get(105, col).click();
					
					  }
					}
			}
		}else {
			ctx.widgets.get(105,slot).click();//open the collect area
		}
		
	}
	public boolean OpenSellScreen(int slot){
		System.out.println("Opening sell screen");
		if(ctx.widgets.get(105,8).isVisible()){//buy screen
			return true;
		}else if(ctx.widgets.get(105,27).isVisible()){//menu screen
			if(slot==28){
				ctx.widgets.get(105,176).click();
			}else if(slot==30){
				ctx.widgets.get(105,30).click();
			}else if(slot==33){
				ctx.widgets.get(105,33).click();
			}else if(slot==34){
				ctx.widgets.get(105,34).click();
			}else if(slot==36){
				ctx.widgets.get(105,36).click();
			}else if(slot==38){
				ctx.widgets.get(105,38).click();
			}
			//open slot buy screen
		}else if(ctx.widgets.get(105,5).isVisible()&&
				ctx.widgets.get(105,5).getText().contains("Sell")){//check if a sell screen is open
			ctx.widgets.get(105,42).click();//back button
		}
		
		return false;
	}
	public boolean OpenBuyScreen(int slot){
		System.out.println("Opening buy screen");
		if(ctx.widgets.get(105,8).isVisible()){//buy screen
			return true;
		}else if(ctx.widgets.get(105,27).isVisible()){//menu screen
			if(slot==28){
				ctx.widgets.get(105,171).click();
			}else if(slot==30){
				ctx.widgets.get(105,30).click();
			}else if(slot==33){
				ctx.widgets.get(105,33).click();
			}else if(slot==34){
				ctx.widgets.get(105,34).click();
			}else if(slot==36){
				ctx.widgets.get(105,36).click();
			}else if(slot==38){
				ctx.widgets.get(105,38).click();
			}
			//open slot buy screen
		}else if(ctx.widgets.get(105,5).isVisible()&&
				ctx.widgets.get(105,5).getText().contains("Sell")){//check if a sell screen is open
			ctx.widgets.get(105,42).click();//back button
		}
		
		return false;
	}
	
}
