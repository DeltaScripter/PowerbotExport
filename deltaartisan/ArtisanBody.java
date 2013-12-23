package deltaartisan;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.powerbot.event.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.Hud.Window;
import org.powerbot.script.util.Random;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.Item;
import org.powerbot.script.wrappers.Tile;
import org.powerbot.script.wrappers.Widget;

import chocopowder.ChocoData.choco;
import deltaartisan.ArtisanData.itemIndex;
import deltaartisan.ArtisanData.itemNames;
import deltaartisan.ArtisanData.locations;
import deltaartisan.ArtisanData.objects;



@org.powerbot.script.Manifest(authors = { "Delta Scripter" }, name = "Delta Artisan", 
description = "Currently in testing, smithing!", 
website = "", version = 0.1,topic =1127240, hidden = true)
public class ArtisanBody extends PollingScript implements PaintListener{

	public ArtisanBody(){
		getExecQueue(State.START).add(new Runnable() {
			@Override
			public void run() {
				   addNode(new SmithRail(ctx));
				   addNode(new Deposit(ctx));
			}
		});
	}
	
	private final List<ArtisanNode> nodeList = Collections.synchronizedList(new ArrayList<ArtisanNode>());
	ArtisanMethod m = new ArtisanMethod(ctx);
	private String state;
	public static boolean goSmith = true;
	public static boolean deposit = false;
	public static String singleItem = itemNames.BRONZESPIKES.getName();//item name
	public static int singleItemIndex = itemIndex.BRONZESPIKES.getID();
	public static String ingredientOne = itemNames.BRONZEBASEPLATE.getName();//for making multiitem
	public static int ingredientOneIndex = itemIndex.BRONZEBASEPLATE.getID();
	public static int ingredientTwoIndex = itemIndex.BRONZERAIL.getID();
	public static String ingredientTwo = itemNames.BRONZERAILS.getName();
	public static String ingotType = "Bronze ingot i";
	public static String depositItems[] = {"Bronze rails","Bronze base plate","Bronze track 40%"};
	@Override
	public int poll() {
		
		for(ArtisanNode node: nodeList){
			if(node.activate()){
				node.execute();
			}
		}
		
		return 400;
	}
	private void shutdown() {
		System.out.println("Now shutting down");
		getController().stop();
	}

	   private void addNode(final ArtisanNode...nodes) {
		   
	        for(ArtisanNode node : nodes) {
	            if(!this.nodeList.contains(node)) {
	                this.nodeList.add(node);
	            }
	        }
	    }
	
	   
	 		class Deposit extends ArtisanNode{

	 			
	 			public Deposit(MethodContext ctx) {
	 				super(ctx);
	 			}

	 			Timer wait = new Timer(0);
	 			@Override
	 			public boolean activate() {
	 				return !goSmith && deposit;
	 			}

	 			@Override
	 			public void execute() {
	 				deposit();
	 			}

				private void deposit() {
					while (ctx.widgets.get(1370,38).isValid() && ctx.widgets.get(1370,38).getText().contains("Smith")){//make items screen
						ctx.widgets.get(1370,31).click();//close button
					}
					if(m.inventoryContains("Bronze ingot i")){
						deposit = false;//stops this class from running
						goSmith = true;//Will start smithing the ingots
					}
					  
							if(m.inventoryContains(singleItem)){
						if(!wait.isRunning())
					if(locations.BYMINECARTS.getTile().distanceTo(ctx.players.local().getLocation())<6){
						System.out.println("Depositing rails");
						m.interactO("Mine cart","Deposit-components", "Minecart");
						wait = new Timer(Random.nextInt(1400, 3000));
					}else {
						System.out.println("Walking to minecarts");
						m.clickOnMap(locations.BYMINECARTS.getTile());
					}
					}else if(locations.BYTHREEANVILS.getTile().distanceTo(ctx.players.local().getLocation())<4){
						if(!wait.isRunning())
						if(ctx.widgets.get(1370,38).isValid()){//Take option for ingots in pile
							System.out.println("Clicking take option");
							ctx.widgets.get(1370,38).click();//take option
							wait = new Timer(Random.nextInt(1800, 2500));
						}else {
							System.out.println("Taking ingots");
							m.interactO("Bronze ingot trough", "Take-ingots", "Ingot pile");
							wait = new Timer(Random.nextInt(1800, 2500));
						}
					}else{
						System.out.println("Walking to ingot pile");
						m.clickOnMap(new Tile(3063, 9708, 0));// tile by aingot pile
					}
						
				}

	 		}
	 		
		class SmithRail extends ArtisanNode{

			
			public SmithRail(MethodContext ctx) {
				super(ctx);
			}

			Timer wait = new Timer(0);
			@Override
			public boolean activate() {
				return goSmith;
			}

			@Override
			public void execute() {
				//smithMultiItem();
				smithSingleItem();
				
			}

			private void smithMultiItem() {
				
				if(!m.inventoryContains("Bronze ingot i") && !m.inventoryContains(ingredientOne)){//if it doesn't have the first ingrediant, it made the end product item.
					deposit = true;
					System.out.println("Turning smtih off");
					goSmith = false;
				}
			if (ctx.players.local().getAnimation() != -1) {
				System.out.println("currently smithing");
				wait = new Timer(5000);
			} else if(m.inventoryContains(ingredientTwo) && m.inventoryContains(ingredientOne)){
				System.out.println("Making multi-item product: "+singleItem);
					if(!wait.isRunning())
					smithSelect(singleItem, singleItemIndex);
			}else if (!wait.isRunning())
				if (!m.inventoryContains(ingredientOne)) {
					System.out.println("Making: "+ingredientOne);
				smithSelect(ingredientOne, ingredientOneIndex);
			} else{
				System.out.println("Making: "+ingredientTwo);
				smithSelect(ingredientTwo, ingredientTwoIndex);
			}
			}

			private void smithSelect(String string, int index) {
				if(ctx.widgets.get(1370,56).isValid()){
					if(ctx.widgets.get(1370,56).getText().contains(string)){
						System.out.println("Clicking smith button");
						ctx.widgets.get(1370,38).click();//Click the smith button.
						ctx.game.sleep(1200);
						wait = new Timer(Random.nextInt(2500, 3000));
					}else if(ctx.widgets.get(1371,44).isValid()){//type of smith screen
					System.out.println("Clicking select option");
					ctx.widgets.get(1371,44).getChild(index).click();//item to make option
					wait = new Timer(2000);
				}
				}else if(!wait.isRunning()){
					System.out.println("clicking on anvil: " + wait.getRemaining());
					m.interactO("Anvil", "Smith", "Anvil");
					wait = new Timer(2000);
				}
				
			}

			private void smithSingleItem() {
				
				if(!m.inventoryContains(ingotType)){
					deposit = true;
					System.out.println("Turning smtih off");
					goSmith = false;
				}
				if (ctx.players.local().getAnimation() != -1) {
					System.out.println("currently smithing");
					wait = new Timer(5000);
				}
				if(locations.BYTHREEANVILS.getTile().distanceTo(ctx.players.local().getLocation())<7){//by anvils
				//make the single item
					if(!wait.isRunning())
					smithSelect(singleItem, singleItemIndex);
			} else {
				System.out.println("Walking to anvils");
				m.clickOnMap(new Tile(3063, 9708, 0));// tile by anvils
				ctx.game.sleep(Random.nextInt(100, 2000));
			}

		}

			
		}
		
		   
private Font myFont = new Font("Consolas",Font.BOLD,14);
	@Override
	public void repaint(Graphics g) {
		g.setFont(myFont);
		g.setColor(Color.green);
		g.drawString("State: "+state, 20, 130);
	}
}
