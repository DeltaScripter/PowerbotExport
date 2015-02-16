package features;

import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Component;

import quests.DeltaQuester;
import quests.Method;


public class GrandExchange extends ClientAccessor{


	public GrandExchange(ClientContext ctx) {
		super(ctx);
	}

	public boolean geIsOpen(){
		if(ctx.widgets.component(105,5).valid()){
			while(ctx.widgets.component(105,5).text().contains("Sell Offer") && 
					ctx.widgets.component(105,5).visible()){
				DeltaQuester.state = "Closing sell screen";
				ctx.widgets.component(105,87).click();
			}
		}
		return (ctx.widgets.component(105,86).component(14).visible());
	}

	public boolean isSlotEmpty() {
		return ctx.widgets.component(105,28).component(1).text().contains("Empty");
	}

	public void createBuyItem(String name, int itemAmount, int itemPrice) {
	
		if(ctx.widgets.component(105,149).visible()){//buy screen
			
		 sleep(Random.nextInt(2000, 2500));
			
		  if(itemName().equals(name)){
				System.out.println("item name is now in the screen");
				if(quantity()==(itemAmount)){
					if(price()==itemPrice){
						Component buy = ctx.widgets.component(105, 166);
						if(ctx.widgets.component(1469,0).visible()){
							ctx.widgets.component(1469,2).click();
							setPrice(itemPrice);
						}else buy.click(true);
					}else setPrice(itemPrice);
				
				}else setQuantity(itemAmount);
			}else typeItem(name);
			
			
		}else {
			System.out.println("Interacting w/buy button on initial screen");
			
			ctx.input.move(ctx.widgets.component(105, 19).centerPoint());
			
			sleep(Random.nextInt(1500, 2000));
			ctx.widgets.component(105,19).interact("");//click the slot buy button
		}
		
	}

	private void setPrice(int i) {
		DeltaQuester.state = "setting price";
		String price = ""+i;
		Component g = ctx.widgets.component(1469,2);//search box for price
		if(g.visible() && g.text().contains("you wish to buy for")){//if open
			if(ctx.widgets.component(1469,3).text().equals(price)){
				
				ctx.input.sendln("");
				
			}else if(ctx.widgets.component(1469,3).text().isEmpty()){
				sendText(price);
			}else ctx.widgets.component(105,154).click();//re-open the search textbox
		}else ctx.widgets.component(105,154).click();
		
	}

	private int price() {
		String price = ctx.widgets.component(105,52).text();
		price = price.replaceAll(" gp", "");
		price = price.replaceAll(",", "");
		return Integer.parseInt(price);
	}
	public void sleep(int millis){
		try {
			Thread.sleep(Math.max(5, (int) (millis * Random.nextDouble(0.85, 1.5))));
		} catch (InterruptedException ignored) {
		}
	}
	private void setQuantity(int itemAmount) {
		String amount = ""+itemAmount;
		DeltaQuester.state = "setting quantity";
		if(ctx.widgets.component(1469,2).visible()){//typing box for quantity
			if(ctx.widgets.component(1469,3).text().equals(amount)){
			
				ctx.input.sendln("");
				
			}else if(ctx.widgets.component(1469,3).text().isEmpty()){
				sendText(amount);
			}else {
				ctx.widgets.component(105,131).click();//empty the search box (try again)
				sleep(1200);
			}
		
		}else ctx.widgets.component(105,131).click();//search quantity button
	
		
	}

	private void sendText(String amount) {
			sleep(1500);
			
			ctx.input.send(amount);
		    sleep(1000);
	}

	private int quantity() {
		return Integer.parseInt(ctx.widgets.component(105,49).text().toString());
	}

	private void typeItem(String name) {
		DeltaQuester.state = "Typing item";
		Component[] spot = null;
		Component results = ctx.widgets.component(389, 4);
		Method Method = new Method(ctx);
		if(ctx.widgets.component(389,11).visible()){//magnifying glass button is visible
			if(ctx.widgets.component(389,7).text().equals(name + "*")){
				spot = ctx.widgets.component(389,4).components();//grabs all items in search result
				for (int s = 0; s < spot.length;) {
					System.out.println("Searching for item:(num results: " + spot.length);
					if(!ctx.widgets.component(105,0).valid())//assume ge?
						break;
				
					Method.state("Searching for item");
					if (ctx.widgets.component(389, 4).component(s).text().equals(name)) {
						if(results.boundingRect().contains(ctx.widgets.component(389, 4).component(s).centerPoint())){
							System.out.println("Clicking option");
							ctx.widgets.component(389, 4).component(s).click();
						}else
						if(results.centerPoint().y>ctx.widgets.component(389, 4).component(s).centerPoint().y){
							System.out.println("scrolling list down");
							
							
							ctx.input.move(results.centerPoint());
							ctx.input.scroll(false);
							
						}else {
							System.out.println("scrolling mouse");
							ctx.input.move(results.centerPoint());
							ctx.input.scroll(true);
						}
					}else s++;

				}
			}else if(ctx.widgets.component(389,7).text().equals("*")){
				sendText(name);
			}else {
				ctx.widgets.component(105,11).click();//reset search box
				sleep(1200);
			}
		}else {
			System.out.println("Opening the textbox");
			//sleep(1000);
			ctx.widgets.component(105,11).click();//open the textbox
			//sleep(800);
		}
		
	}

	private String itemName() {
		return ctx.widgets.component(105,4).text().toString();
	}

	public void collectItem(String name) {
		int collect[] = {77,79};
		if(ctx.widgets.component(105,5).visible()){//collect area
			if(ctx.widgets.component(1469,1).visible()){//search bar still open(causes glitch)
				ctx.widgets.component(105,87).click();//the exit button in GE
			}else if(!itemName().equals(name)){//if something else is being bought
				ctx.widgets.component(105,200).click();//cancel the existing order
			}
			for (int col : collect) {
				if (ctx.widgets.component(105, col).itemId() != -1) {
					if(Method.onlyItemsGE && ctx.widgets.component(105, col).interact("Collect-items")){
						DeltaQuester.state = "Taking item out as an item from collection box";
						ctx.widgets.component(105, col).interact("Collect-items");
					}else{
						DeltaQuester.state = "Selecting item in collection box";
						ctx.widgets.component(105, col).click();
					
					  }
					}
			}
		}else {
			DeltaQuester.state = "Opening collection area";
			ctx.widgets.component(105,28).click();//open the collect area
		}
		
	}
	
}
