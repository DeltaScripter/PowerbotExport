package features;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.Component;

import quests.DeltaQuester;
import quests.Method;

public class GrandExchange extends MethodProvider{

	Timer wait = new Timer(0);
	
	public GrandExchange(MethodContext ctx) {
		super(ctx);
	}

	public boolean geIsOpen(){
		if(ctx.widgets.get(105,5).isValid()){
			while(ctx.widgets.get(105,5).getText().contains("Sell Offer") && 
					ctx.widgets.get(105,5).isVisible()){
				DeltaQuester.state = "Closing sell screen";
				ctx.widgets.get(105,87).click();
			}
		}
		return (ctx.widgets.get(105,86).getChild(14).isVisible());
	}

	public boolean isSlotEmpty() {
		return ctx.widgets.get(105,28).getChild(1).getText().contains("Empty");
	}

	public void createBuyItem(String name, int itemAmount, int itemPrice) {
	
		if(ctx.widgets.get(105,149).isVisible()){//buy screen
		
			if(itemName().equals(name)){
				if(quantity()==(itemAmount)){
					if(price()==itemPrice){
						Component buy = ctx.widgets.get(105, 166);
						if(ctx.widgets.get(1469,0).isVisible()){
							ctx.widgets.get(1469,2).click();
							setPrice(itemPrice);
						}else buy.click(true);
					}else setPrice(itemPrice);
				
				}else setQuantity(itemAmount);
			}else typeItem(name);
			
			
		}else ctx.widgets.get(105,19).click();//click the slot buy button
		
	}

	private void setPrice(int i) {
		DeltaQuester.state = "setting price";
		String price = ""+i;
		Component g = ctx.widgets.get(1469,2);//search box for price
		if(g.isVisible() && g.getText().contains("you wish to buy for")){//if open
			if(ctx.widgets.get(1469,3).getText().equals(price)){
				ctx.keyboard.sendln("");
			}else if(ctx.widgets.get(1469,3).getText().isEmpty()){
				sendText(price);
			}else ctx.widgets.get(105,154).click();//re-open the search textbox
		}else ctx.widgets.get(105,154).click();
		
	}

	private int price() {
		String price = ctx.widgets.get(105,52).getText();
		price = price.replaceAll(" gp", "");
		price = price.replaceAll(",", "");
		return Integer.parseInt(price);
	}

	private void setQuantity(int itemAmount) {
		String amount = ""+itemAmount;
		DeltaQuester.state = "setting quantity";
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

	private void sendText(String amount) {
		if(!wait.isRunning()){
			ctx.keyboard.sleep(1500);
			ctx.keyboard.send(amount);
			wait = new Timer(2000);
		}
		
	}

	private int quantity() {
		return Integer.parseInt(ctx.widgets.get(105,49).getText().toString());
	}

	private void typeItem(String name) {
		DeltaQuester.state = "Inside typing item";
		Component[] spot = null;
		Component results = ctx.widgets.get(389, 4);
		Method Method = new Method(ctx);
		if(ctx.widgets.get(389,11).isVisible()){//magnifying glass button is visible
			if(ctx.widgets.get(389,7).getText().equals(name + "*")){
				spot = ctx.widgets.get(389,4).getChildren();//grabs all items in search result
				for (int s = 0; s < spot.length;) {
					System.out.println("Searching for item:(num results: " + spot.length);
					if(!ctx.widgets.get(105).isValid())//assume ge?
						break;
				
					Method.state("Searching for item");
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

	private String itemName() {
		return ctx.widgets.get(105,4).getText().toString();
	}

	public void collectItem(String name) {
		int collect[] = {77,79};
		if(ctx.widgets.get(105,5).isVisible()){//collect area
			if(ctx.widgets.get(1469,1).isVisible()){//search bar still open(causes glitch)
				ctx.widgets.get(105,87).click();//the exit button in GE
			}else if(!itemName().equals(name)){//if something else is being bought
				ctx.widgets.get(105,200).click();//cancel the existing order
			}
			for (int col : collect) {
				if (ctx.widgets.get(105, col).getItemId() != -1) {
					if(Method.onlyItemsGE && ctx.widgets.get(105, col).interact("Collect-items")){
						DeltaQuester.state = "Taking item out as an item from collection box";
						ctx.widgets.get(105, col).interact("Collect-items");
					}else{
						DeltaQuester.state = "Selecting item in collection box";
						ctx.widgets.get(105, col).click();
					
					  }
					}
			}
		}else {
			DeltaQuester.state = "Opening collection area";
			ctx.widgets.get(105,28).click();//open the collect area
		}
		
	}
	
}
