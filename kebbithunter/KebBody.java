package kebbithunter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.powerbot.event.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.lang.BasicNamedQuery;
import org.powerbot.script.lang.Filter;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.Hud.Window;
import org.powerbot.script.wrappers.Action;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Tile;
import org.powerbot.script.util.GeItem;
import org.powerbot.script.util.Random;
import org.powerbot.script.util.Timer;




@org.powerbot.script.Manifest(authors = { "Delta Scripter" }, name = "Delta Kebbit Hunter", 
description = "Hunts polar kebbits for fur, banks them, repeat. 300K GP/hr",topic=1134247, version = 1
)
public class KebBody extends PollingScript implements PaintListener{

	public KebBody(){
		getExecQueue(State.STOP).add(new Runnable() {
			@Override
			public void run() {
				getController().stop();
			}
			});
		getExecQueue(State.START).add(new Runnable() {
			@Override
			public void run() {
				
				for(Action g: ctx.combatBar.getActions()){
					if(g.getId()==9986){//bear meat
						dropSlot = g.getSlot();
						dropAction = true;
						System.out.println("Setting to drop via actionbar, slot: " + dropSlot + ": " + dropAction);
					}
					if(g.getId()==526){//bones
						burySlot = g.getSlot();
						buryAction = true;
						System.out.println("Setting to bury via actionbar, slot: " + burySlot + ": " + buryAction);
					}
				}
				
			   gePrice = GeItem.getPrice(10117);//kebbit fur
			    
				huntAmount = Random.nextInt(23,26);
				runtime = new Timer(0);
				secondsA = new Timer(0);
				minutesA = new Timer(0);
				addNode(new hunt(ctx));
				addNode(new bank(ctx));
				addNode(new KebAntipattern(ctx));
				
				kebbitInvMonitor = Method.inventoryGetCount(10117);//for counting number of kebbit furs
				
			}
			
		});
	}
	public Tile pathToKebbit[] = {
			 new Tile(2880, 3543, 0), new Tile(2884, 3540, 0), new Tile(2888, 3537, 0), 
				new Tile(2891, 3533, 0), new Tile(2893, 3528, 0), new Tile(2896, 3524, 0), 
				new Tile(2898, 3519, 0), new Tile(2898, 3514, 0), new Tile(2897, 3509, 0), 
				new Tile(2895, 3504, 0), new Tile(2892, 3500, 0), new Tile(2891, 3495, 0), 
				new Tile(2891, 3490, 0), new Tile(2893, 3485, 0), new Tile(2892, 3480, 0), 
				new Tile(2887, 3479, 0), new Tile(2882, 3480, 0), new Tile(2877, 3479, 0), 
				new Tile(2872, 3480, 0), new Tile(2871, 3484, 0) };
	
	public Tile pathToBank[] = {
			new Tile(2885,3480,0), new Tile(2890,3492,0), new Tile(2892,3504,0),
			new Tile(2894,3517,0), new Tile(2894,3530,0), new Tile(2888,3535,0)};
	
	private final List<KebNode> nodeList = Collections.synchronizedList(new ArrayList<KebNode>());
	
	
	public static String state;
	public double version = 0.1002;
	
	int dropSlot;
	boolean dropAction = false;
	int burySlot;
	boolean buryAction = false;
	
	public int gePrice;
	public static boolean antiPattern;
	private Random rand = new Random();
	private boolean start = false;
	public static Timer waitClickMap = new Timer(0);
	public static Timer wait = new Timer(0);//General use timer
	private Timer runtime;
	private Timer secondsA;
	private Timer minutesA;
	private int initialExp;
	KebMethod Method = new KebMethod(ctx);
	KebAntipattern anti = new KebAntipattern(ctx);
	public int set = 0;
	public boolean hunt = true;
	
	public int huntAmount = 0;//amount of fur to hunt before banking
	public boolean allowDrop = false;//Determines when to drop items you don't need
	
	public int kebbitInvMonitor = 0;
	public int kebbitCount = 0;
	@Override
	public int poll() {
		
		//System.out.println("dropper: " +allowDrop);
		if(Method.inventoryGetCount(10117)!=kebbitInvMonitor){
			kebbitCount++;
			kebbitInvMonitor = Method.inventoryGetCount(10117);
		}
		while(ctx.widgets.get(1477,54).isVisible()){
			state = "Closing interface";
			ctx.widgets.get(1477,54).getChild(2).click();
		}
		while(ctx.widgets.get(1223,11).isVisible()){//task complete
			state = "Closing interface";
			ctx.widgets.get(1223,11).click();//close button
		}
		while(ctx.widgets.get(1401,35).isVisible()){//become a member!
			state = "Closing interface";
			ctx.widgets.get(1401,35).click();
		}
		while(ctx.widgets.get(1186,0).isVisible()){//after collecting limit of 10 chronicles..
			state = "Closing interface";
			Method.clickOnMap(ctx.players.local().getLocation().randomize(3, 5));
		}
		for(KebNode node: nodeList){
			if(node.activate()){
				node.execute();
			}
		}
		
		return 400;
	}
	
	   private void addNode(final KebNode...nodes) {
		   
	        for(KebNode node : nodes) {
	            if(!this.nodeList.contains(node)) {
	                this.nodeList.add(node);
	            }
	        }
	    }
	   class bank extends KebNode{

		public bank(MethodContext ctx) {
			super(ctx);
		}

		@Override
		public boolean activate() {
			return !hunt;
		}
		public Tile bankTile = new Tile(2888,3536,0);
		@Override
		public void execute() {
			Tile local = ctx.players.local().getLocation();
			if(!Method.inventoryContains(10117)){//kebbit fur
				hunt = true;
			}
			if(bankTile.distanceTo(local)<6){
				if(ctx.bank.isOpen()){
					ctx.bank.depositInventory();
				}else if(!wait.isRunning()){
					Method.interactO(25688, "Bank", "Bank");
					wait = new Timer(Random.nextInt(100, 2000));
				}
			}else if(!ctx.players.local().isInMotion() ||
					ctx.players.local().getLocation().distanceTo(ctx.movement.getDestination())<6){
				state = "Walking to bank";
				if(!ctx.movement.findPath(bankTile.randomize(1, 2)).traverse()){
				ctx.movement.newTilePath(pathToBank).randomize(1, 2).traverse();
				}//wait = new Timer(Random.nextInt(2300,2600));
			}
			
		}
		   
		   
	   }
	   class hunt extends KebNode {

		public hunt(MethodContext ctx) {
			super(ctx);
		}

		@Override
		public boolean activate() {
			return hunt;
		}
		
		@Override
		public void execute() {
			
		
			
			
		
			calcAntiPattern();
			calcDropTime();
			while((hasJunkItems() && allowDrop)||
					(hasJunkItems()&&Method.backPackFreeSlots()>24)){
				
				if(ctx.hud.isVisible(Window.FRIENDS))
					break;
				
				state  ="Dealing with items";
				if(dropAction){
					Method.useAction(dropSlot);
				}else Method.interactInventory(9986, "Drop", "Bear meat");
				
				if(buryAction){
					Method.useAction(burySlot);
				}else Method.interactInventory(526, "Bury", "Bones");
		
				
			}
			allowDrop = false;
			if(ctx.camera.getPitch()>70){
				ctx.camera.setPitch(Random.nextInt(30, 65));
			}else if(ctx.camera.getPitch()<30)
				ctx.camera.setPitch(Random.nextInt(33, 68));
			
			int backPackItems;
			backPackItems = Method.inventoryGetCount(10117);
			if(backPackItems >= huntAmount){
				hunt = false;
			}
			
			if (new Tile(2871,3481,0).distanceTo(ctx.players.local().getLocation())>25){//kebbit area
				ctx.bank.close();
				if(!ctx.players.local().isInMotion() ||
						ctx.players.local().getLocation().distanceTo(ctx.movement.getDestination())<6){
				state = "Walking back to kebbit area";
				ctx.movement.newTilePath(pathToKebbit).traverse();//goes to kebbit area
				}
			}else
			if(ctx.settings.get(1218)==0){
				state = "Initialize the hunt.";
				set = 0;//reset the variable
				catchKebbit(new Tile(2873,3488,0),66473,"Inspect");//initial hole
			}
			if((ctx.settings.get(1218)>>27&0x3) ==2){
				catchKebbit(new Tile(2867,3483,0),66496, "Attack");//east snow pile
			}else if((ctx.settings.get(1218)>>27&0x3) ==3){
				catchKebbit(new Tile(2876,3479,0),66496,"Attack");//west snow pile
			}else
			if(set==1){
				//do set
				if(((ctx.settings.get(1218)>>6&0x1) ==1)){
					state = "Go to the lowest rock";
					checkRock(new Tile(2875,3482,0),66469);
				}else if(((ctx.settings.get(1218)>>3&0x1) ==1)){
					state = "Go to the center rock";
					checkRock(new Tile(2871,3483,0),66468);
				}
			}else if(set==2){
				//do set
				if(((ctx.settings.get(1218)>>6&0x1) ==1)){
					state = "Go to lowest rock";
					checkRock(new Tile(2875,3482,0),66469);
				}else
				if(((ctx.settings.get(1218)>>5&0x1) ==1)){
					state = "Go to upper-right center rock";
					checkRock(new Tile(2869,3486,0),66467);
				}
			}else if(set==3){
				//do set
				if(((ctx.settings.get(1218)>>16&0x1) ==1)){
					state = "Go to largest rock to the left";
					checkRock(new Tile(2873,3476,0),66472);
				}else
				if(((ctx.settings.get(1218)>>13&0x1) ==1)){
					state = "Go to left-most rock by center";
					checkRock(new Tile(2872,3479,0),66471);
				}else
				if(((ctx.settings.get(1218)>>10&0x1) ==1)){
					state = "Go to largest rock near top";
					checkRock(new Tile(2867,3481,0),66470);
				}
			}else if(set==4){
				//do set
				if(((ctx.settings.get(1218)>>13&0x1) ==1)){
					state = "Go to left-most rock by center";
					checkRock(new Tile(2872,3479,0),66471);
				}else
				if(((ctx.settings.get(1218)>>16&0x1) ==1)){
					state = "Go to largest rock to the left";
					checkRock(new Tile(2873,3476,0),66472);
				}else
				if(((ctx.settings.get(1218)>>10&0x1) ==1)){
					state = "Go to largest rock near top";
					checkRock(new Tile(2867,3481,0),66470);
				}
			}else if(set==5){
				//do set
				if(((ctx.settings.get(1218)>>16&0x1) ==1)){
					state = "Go to largest rock to the left";
					checkRock(new Tile(2873,3476,0),66472);
				}else
				if(((ctx.settings.get(1218)>>9&0x1) ==1)){
					state = "Go to largest rock near top";
					checkRock(new Tile(2867,3481,0),66470);
				}else
				if(((ctx.settings.get(1218)>>12&0x1) ==1)){
					state = "Go to left-most rock by center";
					checkRock(new Tile(2872,3479,0),66471);
				}
		    	}else if(set==6){
				//do set
				if(((ctx.settings.get(1218)>>13&0x1) ==1)){
					state = "Go to left-most rock by center";
					checkRock(new Tile(2872,3479,0),66471);
				}else
				if(((ctx.settings.get(1218)>>9&0x1) ==1)){
					state = "Go to largest rock near top";
					checkRock(new Tile(2867,3481,0),66470);
				}else
				if(((ctx.settings.get(1218)>>15&0x1) ==1)){
					state = "Go to largest rock to the left";
					checkRock(new Tile(2873,3476,0),66472);
				}
			}
			
			if((ctx.settings.get(1218)&0xF) ==12){
				set = 1;
			}else if((ctx.settings.get(1218)&0x3F) ==33 ||
					(ctx.settings.get(1218)&0x1) ==1){
				set = 2;
			}else if((ctx.settings.get(1218)&0x7FF) ==1028||
					(ctx.settings.get(1218)&0xFFF) ==2052){
				set = 3;
			}else if((ctx.settings.get(1218)&0x7FF) ==1056||
					(ctx.settings.get(1218)&0xFFF) ==2080){
				set = 4;
			}else if((ctx.settings.get(1218)&0x1FFF) ==4288){
				set = 5;
			}else if((ctx.settings.get(1218)&0xFFFF) ==32960){
				set = 6;
			}
		}

	
		
		private boolean hasJunkItems() {
			if((Method.inventoryContains(9986)||//meat
					Method.inventoryContains(526))){//bones
				return true;
			}
			return false;
		}

		private void calcDropTime() {
		  int randomNum  =0;
		  randomNum = Random.nextInt(0, 24);
		  
		  if(randomNum==3){
			  allowDrop = true;
		  }
			
		}

		private void catchKebbit(final Tile snowTile, final int id, String action) {
			Tile local =ctx.players.local().getLocation();
			BasicNamedQuery<GameObject> findPile =ctx.objects.select().select(new Filter<GameObject>() {
				public boolean accept(GameObject g) {
					return g.getLocation().distanceTo(snowTile)<2&& g.getId()==id;
				}
		         });
			while (ctx.players.local().getAnimation()!=-1){
				state = "Inspecting snow pile";
				wait = new Timer(500);
			}
			if(!wait.isRunning())
				for(GameObject pile: findPile){
					if(pile.getLocation().distanceTo(local)<9){
						if(pile.isValid()&&pile.isInViewport()){
						if(!ctx.players.local().isInMotion()&&pile.interact(action)){
						wait = new Timer(Random.nextInt(1600, 3800));
						}else {
							ctx.camera.turnTo(pile.getLocation().randomize(2, 3));
						}
						}else ctx.camera.turnTo(pile);
					}else if(!waitClickMap.isRunning()){
						ctx.movement.stepTowards(pile.getLocation());
						waitClickMap = new Timer(Random.nextInt(1800, 2200));
					}
				}
		}

		private void checkRock(final Tile rockTile, final int id) {
			Tile local =ctx.players.local().getLocation();
			
			BasicNamedQuery<GameObject> findRock =ctx.objects.select().select(new Filter<GameObject>() {
				public boolean accept(GameObject g) {
				return g.getLocation().distanceTo(rockTile)<2 && g.getId()==id;
					
				}
		         });
			while (ctx.players.local().getAnimation()!=-1){
				state = "Inspecting rock";
				wait = new Timer(Random.nextInt(200, 400));
			}
			if(!wait.isRunning())
			for(GameObject rock: findRock){
				if(rock.getLocation().distanceTo(local)<15){
					if(rock.isValid()&&rock.isInViewport()){
					if(!ctx.players.local().isInMotion()&&rock.interact("Inspect")){
					wait = new Timer(Random.nextInt(1700, 3200));
					}else {
						ctx.camera.turnTo(rock.getLocation().randomize(1, 4));
					}
					}else ctx.camera.turnTo(rock.getLocation());
				}else if(!waitClickMap.isRunning()){
					ctx.movement.stepTowards(rock.getLocation().randomize(1,4));
					waitClickMap = new Timer(Random.nextInt(1800, 2800));
				}
			}
		}
		   
	   }
	   private boolean calcAntiPattern() {
			int number = rand.nextInt(0,5);
			if(number == 1){
				antiPattern = true;
				return true;
			}
			return false;
		}
	   
private Font myFont = new Font("Consolas",Font.BOLD,15);
private Font myStateFont = new Font("Arial Black",Font.BOLD,14);
//private final Image paint = getImage("http://img546.imageshack.us/img546/8859/i52e.png");
private int mouseX;
private int mouseY;

private void setMouse(Graphics g) {
	g.setColor(Color.MAGENTA);
	g.drawLine(mouseX, mouseY - 800, mouseX, mouseY + 800);
	g.drawLine(mouseX - 800, mouseY, mouseX + 800, mouseY);
}

	@Override
	public void repaint(Graphics g) {
		double runtimeHold;
		
		runtimeHold = runtime.getElapsed();
		runtimeHold = 3600000/runtimeHold;
		//expPerHr = (int)runtimeHold * expGained;
		String expHr = "";
		//expHr = ""+ expPerHr;
		if(expHr.length()>3)
		expHr = expHr.substring(0, expHr.length() - 3);
		
		
		mouseX = (int) ctx.mouse.getLocation().getX();
		mouseY = (int) ctx.mouse.getLocation().getY();
		setMouse(g);
		//g.drawImage(paint, mouseX-950,mouseY-600, null);
		int seconds = (int)(runtime.getElapsed()/1000);
		int minutes = (int)(seconds/60);
		int hours = (int)(minutes/60);
		int secHold = (int)(secondsA.getElapsed()/1000);
		int minHold = (int)(minutesA.getElapsed()/60000);
		
		if(secHold>=60)
			secondsA = new Timer(0);
		if(minHold>=60)
			minutesA = new Timer(0);
		
		
		g.setFont(myStateFont);
		g.setColor(Color.magenta);
		g.drawString("Current Version: " +version, 8,18);
		g.setColor(Color.green);
		g.drawString("State: "+state, 20, 130);
		g.setFont(myFont);
		g.setColor(Color.CYAN);
		g.drawString("Runtime: " +hours+":"+minHold +":" + secHold, 20, 150);
		g.drawString("Current pattern: " +set, 20, 170);
		g.drawString("Gathered kebbit fur: " +kebbitCount, 20, 190);
		String moneyNum = ""+(kebbitCount * gePrice);
		String moneyO = null;
		if(moneyNum.length()==4){
			DecimalFormat formatter = new DecimalFormat("#,###");
			moneyO = formatter.format((kebbitCount * gePrice));
		}else if(moneyNum.length()==5){
			DecimalFormat formatter = new DecimalFormat("##,###");
			moneyO = formatter.format((kebbitCount * gePrice));
		}else if(moneyNum.length()==6){
			DecimalFormat formatter = new DecimalFormat("###,###");
			moneyO = formatter.format((kebbitCount * gePrice));
		}else if(moneyNum.length()==7){
			DecimalFormat formatter = new DecimalFormat("#,###,###");
			moneyO = formatter.format((kebbitCount * gePrice));
		}
		
		g.drawString("Money gained: " +moneyO + " GP", 20, 210);
		g.setColor(Color.BLUE);
		g.drawString((dropAction|| buryAction) ? "Using action bar " : "Not using action bar" , 20, 230);
		
		
	}

	private void calcExpHr() {
		int current = ctx.skills.getExperience(25);
		int diff = current - initialExp;
		//expGained = diff;
		
	}

	
	

}
