package kebbithunter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.powerbot.script.Filter;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.Script;
import org.powerbot.script.rt6.Action;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.Hud.Window;
import org.powerbot.script.rt6.MobileIdNameQuery;


@Script.Manifest(name = "Delta Kebbit Hunter", 
description = "Hunts Kebbits for money, 200k/hr", properties = "topic = 1134247")

public class KebBody extends PollingScript<ClientContext> implements org.powerbot.script.PaintListener{


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
	public double version = 0.10046;
	
	int dropSlot;
	boolean dropAction = false;
	int burySlot;
	boolean buryAction = false;
	
	public int gePrice;
	public boolean done = false;
	public static boolean antiPattern;
	private Random rand = new Random();
	private int initialExp;
	public long runTime;
	KebMethod Method = new KebMethod(ctx);
	KebAntipattern anti = new KebAntipattern(ctx);
	public int set = 0;
	public boolean hunt = true;
	
	public int huntAmount = 0;//amount of fur to hunt before banking
	public boolean allowDrop = false;//Determines when to drop items you don't need
	
	public int kebbitInvMonitor = 0;
	public int kebbitCount = 0;
	

	@Override
	public void poll() {
		
		onStart();
		Method.sleep(500);
		if(Method.inventoryGetCount(10117)!=kebbitInvMonitor){
			kebbitCount++;
			kebbitInvMonitor = Method.inventoryGetCount(10117);
		}
		while(ctx.widgets.component(669,1).visible()){//tut stuff
			state = "Closing interface #1";
			ctx.widgets.component(669,1).click();
		}
		//while(ctx.widgets.component(1477,54).visible()){
		//	state = "Closing interface #2";
		//	ctx.widgets.component(1477,54).component(2).click();
		//}
		while(ctx.widgets.component(1223,11).visible()){//task complete
			state = "Closing interface #3";
			ctx.widgets.component(1223,11).click();//close button
		}
		while(ctx.widgets.component(1401,31).visible()){//become a member!
			state = "Closing interface #4";
			ctx.widgets.component(1401,31).click();
		}
		while(ctx.widgets.component(1186,0).visible()){//after collecting limit of 10 chronicles..
			state = "Closing interface #5";
			ctx.movement.step(ctx.players.local().tile());
		}
		for(KebNode node: nodeList){
			if(node.activate()){
				node.execute();
			}
		}
		
	}
	
	   private void onStart() {
		   if(!done){
		   runTime = System.currentTimeMillis();
			   
			for(Action g: ctx.combatBar.actions()){
				if(g.id()==9986){//bear meati
					dropSlot = g.slot();
					dropAction = true;
					System.out.println("Setting to drop via actionbar, slot: " + dropSlot + ": " + dropAction);
				}
				if(g.id()==526){//bones
					burySlot = g.slot();
					buryAction = true;
					System.out.println("Setting to bury via actionbar, slot: " + burySlot + ": " + buryAction);
				}
			}
		   gePrice = 1700;
				   //GeItem.getPrice(10117);//kebbit fur
		    
			huntAmount = Random.nextInt(23,26);
			addNode(new hunt(ctx));
			addNode(new bank(ctx));
			addNode(new KebAntipattern(ctx));
			
			kebbitInvMonitor = Method.inventoryGetCount(10117);//for counting number of kebbit furs
			done = true;
		   }
	}

	private void addNode(final KebNode...nodes) {
		   
	        for(KebNode node : nodes) {
	            if(!this.nodeList.contains(node)) {
	                this.nodeList.add(node);
	            }
	        }
	    }
	   class bank extends KebNode{

		public bank(ClientContext ctx) {
			super(ctx);
		}

		@Override
		public boolean activate() {
			return !hunt;
		}
		public Tile bankTile = new Tile(2888,3536,0);
		@Override
		public void execute() {
			Tile local = ctx.players.local().tile();
			if(!Method.inventoryContains(10117)){//kebbit fur
				hunt = true;
				System.out.println("Setting hunt to true");
			}
			if(bankTile.distanceTo(local)<6){
				if(!ctx.bank.inViewport())
					ctx.camera.turnTo(ctx.bank.nearest());
				else
				if(ctx.bank.open()){
					ctx.bank.depositInventory();
				}else{
					Method.interactO(25688, "Bank", "Bank");
					Method.sleep(2500);
				}
			}else if(!ctx.players.local().inMotion() ||
					ctx.players.local().tile().distanceTo(ctx.movement.destination())<6){
				state = "Walking to bank";
				if(!ctx.movement.findPath(bankTile.derive(1, 2)).traverse()){
				ctx.movement.newTilePath(pathToBank).randomize(1, 2).traverse();
				}
				Method.sleep(2500);
			}
			
		}
		   
		   
	   }
	   class hunt extends KebNode {

		public hunt(ClientContext ctx) {
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
				
				if(ctx.hud.opened(Window.FRIENDS))
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
			if(ctx.camera.pitch()>70){
				ctx.camera.pitch(Random.nextInt(30, 65));
			}else if(ctx.camera.pitch()<30)
				ctx.camera.pitch(Random.nextInt(33, 68));
			
			int backPackItems;
			backPackItems = Method.inventoryGetCount(10117);
			if(backPackItems >= huntAmount){
				hunt = false;
				System.out.println("Setting hunt to false");
			}
			
			if (new Tile(2871,3481,0).distanceTo(ctx.players.local().tile())>25){//kebbit area
				ctx.bank.close();
				if(!ctx.players.local().inMotion() ||
						ctx.players.local().tile().distanceTo(ctx.movement.destination())<6){
				state = "Walking back to kebbit area";
				ctx.movement.newTilePath(pathToKebbit).randomize(1, 2).traverse();//goes to kebbit area
				}
			}else
			if(ctx.varpbits.varpbit(1218)==0){
				state = "Initialize the hunt";
				set = 0;//reset the variable
				catchKebbit(new Tile(2873,3488,0),66473,"Inspect");//initial hole
			}
			if((ctx.varpbits.varpbit(1218)>>27&0x3) ==2){
				catchKebbit(new Tile(2867,3483,0),66496, "Attack");//east snow pile
			}else if((ctx.varpbits.varpbit(1218)>>27&0x3) ==3){
				catchKebbit(new Tile(2876,3479,0),66496,"Attack");//west snow pile
			}else
			if(set==1){
				//do set
				if(((ctx.varpbits.varpbit(1218)>>6&0x1) ==1)){
					state = "Go to the lowest rock";
					checkRock(new Tile(2875,3482,0),66469);
				}else if(((ctx.varpbits.varpbit(1218)>>3&0x1) ==1)){
					state = "Go to the center rock";
					checkRock(new Tile(2871,3483,0),66468);
				}
			}else if(set==2){
				//do set
				if(((ctx.varpbits.varpbit(1218)>>6&0x1) ==1)){
					state = "Go to lowest rock";
					checkRock(new Tile(2875,3482,0),66469);
				}else
				if(((ctx.varpbits.varpbit(1218)>>5&0x1) ==1)){
					state = "Go to upper-right center rock";
					checkRock(new Tile(2869,3486,0),66467);
				}
			}else if(set==3){
				//do set
				if(((ctx.varpbits.varpbit(1218)>>16&0x1) ==1)){
					state = "Go to largest rock to the left";
					checkRock(new Tile(2873,3476,0),66472);
				}else
				if(((ctx.varpbits.varpbit(1218)>>13&0x1) ==1)){
					state = "Go to left-most rock by center";
					checkRock(new Tile(2872,3479,0),66471);
				}else
				if(((ctx.varpbits.varpbit(1218)>>10&0x1) ==1)){
					state = "Go to largest rock near top";
					checkRock(new Tile(2867,3481,0),66470);
				}
			}else if(set==4){
				//do set
				if(((ctx.varpbits.varpbit(1218)>>13&0x1) ==1)){
					state = "Go to left-most rock by center";
					checkRock(new Tile(2872,3479,0),66471);
				}else
				if(((ctx.varpbits.varpbit(1218)>>16&0x1) ==1)){
					state = "Go to largest rock to the left";
					checkRock(new Tile(2873,3476,0),66472);
				}else
				if(((ctx.varpbits.varpbit(1218)>>10&0x1) ==1)){
					state = "Go to largest rock near top";
					checkRock(new Tile(2867,3481,0),66470);
				}
			}else if(set==5){
				//do set
				if(((ctx.varpbits.varpbit(1218)>>16&0x1) ==1)){
					state = "Go to largest rock to the left";
					checkRock(new Tile(2873,3476,0),66472);
				}else
				if(((ctx.varpbits.varpbit(1218)>>9&0x1) ==1)){
					state = "Go to largest rock near top";
					checkRock(new Tile(2867,3481,0),66470);
				}else
				if(((ctx.varpbits.varpbit(1218)>>12&0x1) ==1)){
					state = "Go to left-most rock by center";
					checkRock(new Tile(2872,3479,0),66471);
				}
		    	}else if(set==6){
				//do set
				if(((ctx.varpbits.varpbit(1218)>>13&0x1) ==1)){
					state = "Go to left-most rock by center";
					checkRock(new Tile(2872,3479,0),66471);
				}else
				if(((ctx.varpbits.varpbit(1218)>>9&0x1) ==1)){
					state = "Go to largest rock near top";
					checkRock(new Tile(2867,3481,0),66470);
				}else
				if(((ctx.varpbits.varpbit(1218)>>15&0x1) ==1)){
					state = "Go to largest rock to the left";
					checkRock(new Tile(2873,3476,0),66472);
				}
			}
			
			if((ctx.varpbits.varpbit(1218)&0xF) ==12){
				set = 1;
			}else if((ctx.varpbits.varpbit(1218)&0x3F) ==33 ||
					(ctx.varpbits.varpbit(1218)&0x1) ==1){
				set = 2;
			}else if((ctx.varpbits.varpbit(1218)&0x7FF) ==1028||
					(ctx.varpbits.varpbit(1218)&0xFFF) ==2052){
				set = 3;
			}else if((ctx.varpbits.varpbit(1218)&0x7FF) ==1056||
					(ctx.varpbits.varpbit(1218)&0xFFF) ==2080){
				set = 4;
			}else if((ctx.varpbits.varpbit(1218)&0x1FFF) ==4288){
				set = 5;
			}else if((ctx.varpbits.varpbit(1218)&0xFFFF) ==32960){
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
			Tile local =ctx.players.local().tile();
			MobileIdNameQuery<GameObject> findPile =ctx.objects.select().select(new Filter<GameObject>() {
				public boolean accept(GameObject g) {
					return g.tile().distanceTo(snowTile)<2&& g.id()==id;
				}
		         });
		
				for (GameObject pile : findPile) {
					if (pile.tile().distanceTo(local) < 10 &&pile.inViewport()) {
						if (!ctx.players.local().inMotion()
								&&ctx.players.local().animation() == -1)
						if (pile.inViewport()&& pile.interact(action)) {
						
						} else
							ctx.camera.turnTo(pile.tile().derive(1, 3));

					} else if(!ctx.players.local().inMotion()){
						ctx.movement.step(pile.tile().derive(1, 3));
					}
				}
				
		}

		private void checkRock(final Tile rockTile, final int id) {
			Tile local =ctx.players.local().tile();
			
			MobileIdNameQuery<GameObject> findRock =ctx.objects.select().select(new Filter<GameObject>() {
				public boolean accept(GameObject g) {
				return g.tile().distanceTo(rockTile)<2 && g.id()==id;
					
				}
		         });
			
			for (GameObject rock : findRock) {
				if (rock.tile().distanceTo(local) < 10 || rock.inViewport()) {
					
					if(!ctx.players.local().inMotion()
							&&ctx.players.local().animation()==-1)
					if (rock.inViewport()&&rock.interact("Inspect")) {
							System.out.println("Clicking on rock: " + ctx.players.local().animation());
					} else{
						System.out.println("Turning camera rock");
						ctx.camera.turnTo(rock.tile().derive(1, 3));
					}
				} else {
					ctx.movement.step(rock.tile().derive(1, 4));
				}
			}
			
		}
		   
	   }
	   private boolean calcAntiPattern() {
			int number = rand.nextInt(0,23);
			if(number == 1){
				antiPattern = true;
				return true;
			}
			return false;
		}
		private Image getImage(String url) {
			
				return this.downloadImage(url);
			
		}
private Font myFont = new Font("Consolas",Font.BOLD,15);
private Font myStateFont = new Font("Arial Black",Font.BOLD,14);

private  Image paint = getImage("http://i1223.photobucket.com/albums/dd508/thriller645/Paint_Kebbit_Hunter2take5_zps67818849.png");

private int mouseX;
private int mouseY;

private void setMouse(Graphics g) {
	g.setColor(Color.MAGENTA);
	g.drawLine(mouseX, mouseY - 800, mouseX, mouseY + 800);
	g.drawLine(mouseX - 800, mouseY, mouseX + 800, mouseY);
}

	public void repaint(Graphics g) {
		
		String expHr = "";
		if(expHr.length()>3)
		expHr = expHr.substring(0, expHr.length() - 3);
		
	   g.drawImage(paint, 0,0,null);
		
		mouseX = (int) ctx.mouse.getLocation().getX();
		mouseY = (int) ctx.mouse.getLocation().getY();
		setMouse(g);
		g.setFont(myStateFont);
		//g.setColor(Color.magenta);
		//g.drawString("Current Version: " +version, 8,18);
		//g.setColor(Color.green);
		//g.drawString("State: "+state, 20, 130);
		g.setFont(myFont);
		//g.setColor(Color.CYAN);
		long time = runTime - System.currentTimeMillis();
		
		g.setColor(Color.black);
		g.drawString("" +version, 127,218);
		g.drawString(""+state, 63,39);
		//g.drawString("Runtime: " + Method.format(time), 20, 150);
		//g.drawString("Current pattern: " +set, 20, 170);
		//g.drawString("Gathered kebbit fur: " +kebbitCount, 20, 190);
		g.drawString("Runtime: " + Method.format(time), 85,55);
		g.drawString("" +set, 148,72);
		g.drawString(""+kebbitCount, 105, 87);
		
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
	
		g.drawString("" + moneyO + "GP" ,117,105);
		//g.drawString("Money gained: " +moneyO + " GP", 20, 210);
		g.setColor(Color.BLUE);
		//g.drawString((dropAction|| buryAction) ? "Using action bar " : "Not using action bar" , 20, 230);
		
		
	}

	private void calcExpHr() {
		int current = ctx.skills.experience(25);
		
	}

	
	

}
