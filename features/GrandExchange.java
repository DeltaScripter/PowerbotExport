package features;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.Component;

import quests.Method;

public class GrandExchange extends MethodProvider{

	Timer wait = new Timer(0);
	
	public GrandExchange(MethodContext ctx) {
		super(ctx);
	}

	public boolean geIsOpen(){
		return (ctx.widgets.get(105,2).isVisible());
	}

	public boolean isSlotEmpty() {
		return ctx.widgets.get(105,19).getChild(10).getText().contains("Empty");
	}

	public void createBuyItem(String name, int itemAmount, int itemPrice) {
	
		if(ctx.widgets.get(105,149).isVisible()){//buy screen
		
			if(itemName().equals(name)){
				if(quantity()==(itemAmount)){
					if(price()==itemPrice){
						Component buy = ctx.widgets.get(105, 187);
						if(ctx.widgets.get(1469,0).isVisible()){
							ctx.widgets.get(1469,2).click();
							setPrice(itemPrice);
						}else buy.click(true);
					}else setPrice(itemPrice);
				
				}else setQuantity(itemAmount);
			}else typeItem(name);
			
			
		}else ctx.widgets.get(105,30).click();//click the slot buy button
		
	}

	private void setPrice(int i) {
		String price = ""+i;
		Component g = ctx.widgets.get(1469,2);//search box for price
		if(g.isVisible() && g.getText().contains("you wish to buy for")){//if open
			if(ctx.widgets.get(1469,3).getText().equals(price)){
				ctx.keyboard.sendln("");
			}else if(ctx.widgets.get(1469,3).getText().isEmpty()){
				sendText(price);
			}else ctx.widgets.get(105,177).click();//re-open the search textbox
		}else ctx.widgets.get(105,177).click();
		
	}

	private int price() {
		String price = ctx.widgets.get(105,153).getText();
		price = price.replaceAll(" gp", "");
		price = price.replaceAll(",", "");
		return Integer.parseInt(price);
	}

	private void setQuantity(int itemAmount) {
		String amount = ""+itemAmount;
		
		if(ctx.widgets.get(1469,2).isVisible()){//typing box for quantity
			if(ctx.widgets.get(1469,3).getText().equals(amount)){
			ctx.keyboard.sendln("");
			}else if(ctx.widgets.get(1469,3).getText().isEmpty()){
				sendText(amount);
			}else {
				ctx.widgets.get(105,168).click();//empty the search box (try again)
				ctx.environment.sleep(1200);
			}
		
		}else ctx.widgets.get(105,168).click();//search quantity button
	
		
	}

	private void sendText(String amount) {
		if(!wait.isRunning()){
			ctx.keyboard.sleep(1500);
			ctx.keyboard.send(amount);
			wait = new Timer(2000);
		}
		
	}

	private int quantity() {
		return Integer.parseInt(ctx.widgets.get(105,148).getText().toString());
	}

	private void typeItem(String name) {
		final int spot[] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		Method Method = new Method(ctx);
		if(ctx.widgets.get(389,11).isVisible()){//magnifying glass button is visible
			if(ctx.widgets.get(389,7).getText().equals(name + "*")){
				for (int s = 0; s < spot.length;) {
					if(!ctx.widgets.get(105).isValid())
						break;
					
					Method.state("Searching for item");
					if (ctx.widgets.get(389, 4).getChild(s).getText().equals(name)) {
						ctx.widgets.get(389, 4).getChild(s).click();
						break;
					}else s++;

				}
			}else if(ctx.widgets.get(389,7).getText().equals("*")){
				sendText(name);
			}else {
				ctx.widgets.get(105,139).click();//reset search box
				ctx.environment.sleep(1200);
			}
		}else {
			ctx.widgets.get(105,139).click();//open the textbox
			ctx.keyboard.sleep(800);
		}
		
	}

	private String itemName() {
		return ctx.widgets.get(105,142).getText().toString();
	}

	public void collectItem(String name) {
		int collect[] = {206,208};
		if(ctx.widgets.get(105,208).isVisible()){//collect area
			if(ctx.widgets.get(1469,1).isVisible()){//search bar still open(causes glitch)
				ctx.widgets.get(105,14).click();//the exit button in GE
			}else if(!itemName().equals(name)){//if something else is being bought
				ctx.widgets.get(105,200).click();//cancel the existing order
			}
			for (int col : collect) {
				if (ctx.widgets.get(105, col).getItemId() != -1) {
					ctx.widgets.get(105, col).click();
				}
			}
		}else ctx.widgets.get(105,33).click();//open the collect area
		
	}
	
}
