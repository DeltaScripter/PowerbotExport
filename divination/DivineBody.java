package divination;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.powerbot.event.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.Skills;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Tile;
import org.powerbot.script.util.GeItem;
import org.powerbot.script.util.Random;
import org.powerbot.script.util.Timer;

import divination.DivineData.animation;
import divination.DivineData.convert;
import divination.DivineData.memories;
import divination.DivineData.wisps;



@org.powerbot.script.Manifest(authors = { "Delta Scripter" }, name = "Delta Divinity", 
description = "Collects all types of energy; harvest and uses energy to your choosing, earn 200-300k/hr!",
topic = 1130348, version = 1.14
)
public class DivineBody extends PollingScript implements PaintListener{

	public DivineBody(){
		getExecQueue(State.STOP).add(new Runnable() {
			@Override
			public void run() {
				System.out.println("Shutting down.");
				getController().stop();
			}
			});
		getExecQueue(State.START).add(new Runnable() {
			@Override
			public void run() {
				initiateGUI();
				initilizeLocVariables();
				initialExp = ctx.skills.getExperience(25);
				runtime = new Timer(0);
				secondsA = new Timer(0);
				minutesA = new Timer(0);
				addNode(new harvestWisp(ctx));
				addNode(new convertMemories(ctx));
				addNode(new DivineAntipattern(ctx));
				addNode(new DivineChronicle(ctx));
				
			}
			
			private void initilizeLocVariables() {
				
				if(Method.npcIsNotNull(wisps.PALEWISP.getName())){
					price = 100;
					energyType = memories.PALEENERGY.getID();
					location = "Lummbridge";
					wispKind = wisps.PALEWISP.getName();
					wispSpring = wisps.PALESPRING.getName();
					memoryType = memories.PALEMEMORY.getName();
					System.out.println(""+wispKind);
				}
				if(Method.npcIsNotNull(wisps.FLICKERINGWISP.getName())){
					price = 140;
					energyType = memories.FLICKERINGENERGY.getID();
					location = "Falador";
					wispKind = wisps.FLICKERINGWISP.getName();
					wispSpring = wisps.FLICKERINGSPRING.getName();
					memoryType = memories.FLICKERINGMEMORY.getName();
				}
				if(Method.npcIsNotNull(wisps.BRIGHTWISP.getName())){
					price = 160;
					energyType = memories.BRIGHTENERGY.getID();
					location = "Varrock";
					wispKind = wisps.BRIGHTWISP.getName();
					wispSpring = wisps.BRIGHTSPRING.getName();
					memoryType = memories.BRIGHTMEMORY.getName();
				}
				if(Method.npcIsNotNull(wisps.GLOWINGWISP.getName())){
					price = 130;
					energyType = memories.GLOWINGENERGY.getID();
					location = "Seers' Village";
					wispKind = wisps.GLOWINGWISP.getName();
					wispSpring = wisps.GLOWINGSPRING.getName();
					memoryType = memories.GLOWINGMEMORY.getName();
				}
				if(Method.npcIsNotNull(wisps.SPARKLINGWISP.getName())){
					price = 140;
					energyType = memories.SPARKLINGENERGY.getID();
					location = "Golden Apple Tree";
					wispKind = wisps.SPARKLINGWISP.getName();
					wispSpring = wisps.SPARKLINGSPRING.getName();
					memoryType = memories.SPARKLINGMEMORY.getName();
				}
				if(Method.npcIsNotNull(wisps.GLEAMINGWISP.getName())){
					energyType = memories.GLEAMINGENERGY.getID();
					location = "Shilo Village";
					wispKind = wisps.GLEAMINGWISP.getName();
					wispSpring = wisps.GLEAMINGSPRING.getName();
					memoryType = memories.GLEAMINGMEMORY.getName();
				}
				if(Method.npcIsNotNull(wisps.VIBRANTWISP.getName())){
					price = 95;
					energyType = memories.VIBRANTENERGY.getID();
					location = "Mobilizing Armies";
					wispKind = wisps.VIBRANTWISP.getName();
					wispSpring = wisps.VIBRANTSPRING.getName();
					memoryType = memories.VIBRANTMEMORY.getName();
				}
				if(Method.npcIsNotNull(wisps.LUSTROUSWISP.getName())){
					price = 100;
					energyType = memories.LUMINOUSENERGY.getID();
					location = "Slayer Tower";
					wispKind = wisps.LUSTROUSWISP.getName();
					wispSpring = wisps.LUSTROUSSPRING.getName();
					memoryType = memories.LUSTROUSMEMORY.getName();
				}
				if(Method.npcIsNotNull(wisps.BRILLIANTWISP.getName())){
					price = 110;
					energyType = memories.BRILLIANTENERGY.getID();
					location = "Mage Training Arena";
					wispKind = wisps.BRILLIANTWISP.getName();
					wispSpring = wisps.BRILLIANTSPRING.getName();
					memoryType = memories.BRILLIANTMEMORY.getName();
				}
				if(Method.npcIsNotNull(wisps.RADIANTWISP.getName())){
					price = 170;
					energyType = memories.RADIANTENERGY.getID();
					location = "Dragontooth Island";
					wispKind = wisps.RADIANTWISP.getName();
					wispSpring = wisps.RADIANTSPRING.getName();
					memoryType = memories.RADIANTMEMORY.getName();
				}
				if(Method.npcIsNotNull(wisps.LUMINOUSWISP.getName())){
					price = 180;
					energyType = memories.LUMINOUSENERGY.getID();
					location = "Sophanem";
					wispKind = wisps.LUMINOUSWISP.getName();
					wispSpring = wisps.LUMINOUSSPRING.getName();
					memoryType = memories.LUMINOUSMEMORY.getName();
				}
				if(Method.npcIsNotNull(wisps.INCANDESCENTWISP.getName())){
					price = 160;
					energyType = memories.INCANDESCENTENERGY.getID();
					location = "Poison Waste";
					wispKind = wisps.INCANDESCENTWISP.getName();
					wispSpring = wisps.INCANDESCENTSPRING.getName();
					memoryType = memories.INCANDESCENTMEMORY.getName();
				}
				
			}
		});
	}
	
	private final List<DivineNode> nodeList = Collections.synchronizedList(new ArrayList<DivineNode>());
	public static String state;
	private boolean harvest = false;
	public static int convertType;
	private int animationType;
	public static String location;
	GeItem energy;
	private String wispKind;
	private String wispSpring;
	private String memoryType;
	private int energyType;
	private int price;
	public static Tile riftArea;
	public static boolean antiPattern;
	private Random rand = new Random();
	private boolean start = false;
	public static Timer waiting = new Timer(0);
	public static Timer wait = new Timer(0);//General use timer
	private Timer runtime;
	private Timer secondsA;
	private Timer minutesA;
	private int initialExp;
	private int expGained;
	private int expPerHr;
	static int level;
	static int xpToLevel;
	DivineMethod Method = new DivineMethod(ctx);
	DivineAntipattern anti = new DivineAntipattern(ctx);
	public static boolean prioritizeNearbyWisps;
	public static boolean catchChronicles = true;
	public static boolean depositChronicles = false;
	private boolean catchAndDepoChronicles = false;
	
	
	@Override
	public int poll() {
		
		while(Method.inventoryContains("Logs")){
			DivineBody.state = "Dropping logs";
			Method.interactInventory("Logs", "Drop", "Logs");
		}
		while(ctx.widgets.get(1477,54).isValid() &&ctx.widgets.get(1477,54).isVisible()){
			state = "Closing interface tasks";
			ctx.widgets.get(1477,54).getChild(2).click();
		}
		while(ctx.widgets.get(1223,11).isValid()&&ctx.widgets.get(1223,11).isVisible()){//task complete
			state = "Closing interface task complete";
			ctx.widgets.get(1223,11).click();//close button
		}
		while(ctx.widgets.get(1401,35).isValid()&&ctx.widgets.get(1401,35).isVisible()){//become a member!
			state = "Closing interface member advertisement";
			ctx.widgets.get(1401,35).click();
		}
		while(ctx.widgets.get(1186,1).isValid()&&ctx.widgets.get(1186,0).isVisible()){//after collecting limit of 10 chronicles..
			state = "Closing chronicle interface";
			//Method.clickOnMap(ctx.players.local().getLocation().randomize(3, 5));
			Method.pressContinue();
		}
		if(start){
		for(DivineNode node: nodeList){
			if(node.activate()){
				node.execute();
			}
		}
		}
		return 400;
	}
	
	   private void addNode(final DivineNode...nodes) {
		   
	        for(DivineNode node : nodes) {
	            if(!this.nodeList.contains(node)) {
	                this.nodeList.add(node);
	            }
	        }
	    }
	   class convertMemories extends DivineNode{

		public convertMemories(MethodContext ctx) {
			super(ctx);
		}
		
		@Override
		public boolean activate() {
			return !harvest && !depositChronicles;
		}

		@Override
		public void execute() {
			calcAntiPattern();
			anti.closeInteruptions();
			//Adjust camera
			if(ctx.camera.getPitch()<30){
				ctx.camera.setPitch(Random.nextInt(50, 70));
			}
			while(riftArea==null && Method.objIsNotNull("Energy Rift")){
				state = "Setting rift area";
				System.out.println("Setting rift area");
				riftArea = Method.getObject("Energy Rift").getLocation();
				riftArea = new Tile(riftArea.getX(), riftArea.getY()-2,riftArea.getPlane());
				break;
			}
			
			while(ctx.players.local().getAnimation()==animationType){
				waiting = new Timer(4500);
				updateCounts();
				calcAntiPattern();
				state = "Converting memories..";
			}
			if(!waiting.isRunning() || ctx.widgets.get(131,convertType).isValid()&&
					ctx.widgets.get(131,convertType).isVisible())
			if(!Method.inventoryContains(memoryType) && !Method.backPackIsFull()){//if you're ready to gather more memories..
				harvest = true;
			}else
			if(closeToObj(riftArea,"Walking to rift")){
				if(ctx.widgets.get(131,convertType).isValid()&&
						ctx.widgets.get(131,convertType).isVisible()){
					//if(convertType == convert.BOTH.getID()){
						//if(ctx.widgets.get(131,39).getTextureId()==13827){
							//animationType = animation.TODIVINEENERGY.getID();
							//convertType = convert.DIVINEENERGY.getID();
							//harvest = true;
						//}
					//}
					ctx.widgets.get(131,convertType).click();
					waiting = new Timer(Random.nextInt(700, 1200));
					ctx.game.sleep(Random.nextInt(200, 1000));
					
					
				}else if(ctx.widgets.get(1186,2).isVisible()){//garbage
					state = "Closing dialogue";
					System.out.println("Closing dialogue");
					if(!wait.isRunning()){
						Method.pressContinue();
						//Method.clickOnMap(ctx.players.local().getLocation().randomize(2, 3));
					wait = new Timer(Random.nextInt(500, 1200));
					}
				}else clickRift("Energy Rift", "Interacting with rift");
			}
			//if(waiting.isRunning())
				//System.out.println("Timer: " + waiting.getRemaining());
		}

		private void clickRift(String name, String o) {
			if(!ctx.players.local().isInMotion())
			for(GameObject y: ctx.objects.select().name(name).nearest().first()){
				DivineBody.state = o;
				if(ctx.menu.isOpen())
					for(String option: ctx.menu.getItems()){
						if(option.contains("Convert memories")){
							//System.out.println("Clicking the option: " + option);
						ctx.menu.click(ctx.menu.filter("Convert memories",""));
						waiting = new Timer(Random.nextInt(2200, 2800));
						}
					}//in case menu wasn't open
						if (y.isOnScreen()) {
							ctx.mouse.move(y.getLocation().getMatrix(ctx).getPoint(Random.nextDouble() * 0.2D - 0.1D,+0.10D,+100));
							ctx.mouse.click(true);
							waiting = new Timer(Random.nextInt(700, 1200));
						} else {
							ctx.camera.setPitch(40);
							ctx.camera.turnTo(y);
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
		class harvestWisp extends DivineNode{

			public harvestWisp(MethodContext ctx) {
				super(ctx);
			}

			@Override
			public boolean activate() {
				return harvest && !depositChronicles;
			}
			@Override
			public void execute() {
				calcAntiPattern();
				
				
				while(ctx.players.local().getAnimation()!=-1){
					ArrayList<String> itemList = new ArrayList<String>();
					state = "Harvesting spring";
					if(foundChronicle())
						break;
					if(Method.backPackIsFull()){
						state = "backpack is full";
						harvest = false;
						break;
					}
					if(ctx.widgets.get(1401,35).isVisible())//become a member
						break;
					
					int backPackItems;
					backPackItems = Method.inventoryGetCount(memoryType);
					if(backPackItems>=Random.nextInt(17, 20) && 
							Method.objIsNotNull("Energy Rift") &&Method.getObject("Energy Rift").isOnScreen()){
						state = "Right-clicking rift";
						if(!ctx.menu.isOpen()){
							System.out.println("menu isn't open: " + backPackItems);
							ctx.mouse.click(Method.getObject("Energy Rift").getCenterPoint(),false);
							ctx.game.sleep(Random.nextInt(1000, 1600));
						}else{
							String[] items = ctx.menu.getItems();
							itemList.add("nothing");//for preventing null errors
							for(String item: items){
								if(!itemList.contains(item)){
									if(item.contains("Convert memories")){
									itemList.add("Convert memories");
									}
								}
							}
							if(!itemList.contains("Convert memories")){//if we mis-cliked and got the wrong menu to pop up..
								//System.out.println("can't find menu option");
								ctx.mouse.click(Method.getObject("Energy Rift").getCenterPoint(),false);
							ctx.game.sleep(Random.nextInt(1800, 2600));
							}
						}
					}else
					if(Method.npcIsNotNull(wispKind)){
						if(Method.getNPC(wispKind).getLocation().distanceTo(ctx.players.local().getLocation())<7){
							if(ctx.menu.isOpen()){
								String[] items = ctx.menu.getItems();
								itemList.add("nothing");//for preventing null errors
								for(String item: items){
									if(!itemList.contains(item)){
										if(item.contains("Harvest")){
										itemList.add("Harvest");
										}
									}
								}
								if(!itemList.contains("Harvest"))//if we mis-cliked and got the wrong menu to pop up..
									if(Method.getNPC(wispKind).isOnScreen()){
										   ctx.mouse.click(Method.getNPC(wispKind).getCenterPoint(),false);//try click another wisp
										   //System.out.println("Not the correct menu, clicked again");
										}
								}else if(Method.npcIsNotNull(wispSpring) && Method.getNPC(wispSpring).isOnScreen()&&
										Method.getNPC(wispSpring).getLocation().distanceTo(ctx.players.local().getLocation())>1){
									ctx.game.sleep(Random.nextInt(1350, 2000));
									state = "Right-clicking spring";
									if(Method.npcIsNotNull(wispSpring))
									ctx.mouse.click(Method.getNPC(wispSpring).getCenterPoint(),false);
								}else if(Method.getNPC(wispKind).isOnScreen()){
									ctx.game.sleep(Random.nextInt(1350, 2000));
									state = "Right-clicking wisp";
									if(Method.npcIsNotNull(wispKind))
							     ctx.mouse.click(Method.getNPC(wispKind).getCenterPoint(),false);
							}
						}
					}
					updateCounts();
					
				}
				while(Method.backPackIsFull()){
					state = "backpack is full";
					harvest = false;
					break;
				}
				
				if((catchAndDepoChronicles ||catchChronicles) && Method.inventoryContains("Chronicle fragment"))
				while(Method.inventoryStackSize("Chronicle fragment")>9){
					state = "Destroying Chronicle fragments";
					if(ctx.widgets.get(1401,35).isVisible())//become a member
						break;
					if(ctx.widgets.get(1186,5).isVisible())//the screen that states you can't collect anymore -another method handles it
						break;
					
					if(catchAndDepoChronicles){//Sets for starting to deposit the chronicles.
						depositChronicles = true;
						break;
					}else
					if(ctx.widgets.get(1183,6).isVisible()){//are you sure?(destroy chronicles)
						ctx.widgets.get(1183,6).click();//yes button
					}else
					Method.interactInventory("Chronicle fragment", "Destroy", "Fragement");
				}
				if(ctx.widgets.get(131,1).isVisible()){
					state = "Closing interference";
					ctx.widgets.get(131,14).click();
					ctx.game.sleep(Random.nextInt(200, 500));
					//Method.clickOnMap(ctx.players.local().getLocation());
				//	Method.pressContinue();
				}else
				if(!foundChronicle())//catches chronicles if they appear
				if(!foundEnrichedSpring("Enriched sparkling spring"))
				if(!foundEnrichedSpring("Enriched glowing spring"))
					if(prioritizeNearbyWisps && Method.npcIsNotNull(wispSpring) && 
							Method.getNPC(wispSpring).getLocation().distanceTo(ctx.players.local().getLocation())<6){
						if(closeToNpc(wispSpring,"Walking to spring") && !waiting.isRunning()&&
								ctx.players.local().isIdle()){
							state = "Attempting to harvest spring";
							Method.npcInteract(wispSpring, "Harvest");
						}
					}else
					if(prioritizeNearbyWisps && Method.npcIsNotNull(wispKind) && 
							Method.getNPC(wispKind).getLocation().distanceTo(ctx.players.local().getLocation())<7){
						//Belows selects the menu item, if the menu is open
						if(ctx.menu.isOpen())
						for(String option: ctx.menu.getItems()){
							if(option.contains("Harvest")){
								//System.out.println("Clicking the option: " + option);
							ctx.menu.click(ctx.menu.filter("Harvest",""));
							ctx.game.sleep(Random.nextInt(2900, 3600));
							}
						}//in case menu wasn't open
						if(closeToNpc(wispKind,"Walking to wisp") && !waiting.isRunning()
								&& ctx.players.local().isIdle()){
							state = "Attempting to convert wisp to spring";
							Method.npcInteract(wispKind, "Harvest");
							
						}
					}else
				if(Method.npcIsNotNull(wispSpring) && 
						Method.getNPC(wispSpring).getLocation().distanceTo(ctx.players.local().getLocation())<12){
					//Belows selects the menu item, if the menu is open
					if(ctx.menu.isOpen())
						for(String option: ctx.menu.getItems()){
							if(option.contains("Harvest")){
								System.out.println("Clicking the option: " + option);
							ctx.menu.click(ctx.menu.filter("Harvest",""));
							ctx.game.sleep(Random.nextInt(3200, 3600));
							}
						}
					if(closeToNpc(wispSpring,"Walking to spring")){
						state = "Attempting to harvest spring";
						Method.npcInteract(wispSpring, "Harvest");
					}
				}else
				if(Method.npcIsNotNull(wispKind)){
					if(closeToNpc(wispKind,"Walking to wisp")){
						state = "Attempting to convert wisp to spring";
						Method.npcInteract(wispKind, "Harvest");
					}
				}else if(!ctx.movement.findPath(riftArea).traverse()){
					Method.clickOnMap(riftArea);
				}
				
			}
			
		

			private boolean foundChronicle() {
				Tile local = ctx.players.local().getLocation();
				if(catchChronicles){
					
					if(!wait.isRunning())
					if(Method.npcIsNotNull("Chronicle fragment")){
						
						if(Method.getNPC("Chronicle fragment").getLocation().distanceTo(local)<7){
							state = "Chronicle within range";
							Method.npcInteract("Chronicle fragment","Capture");
						}else return false;
					}else return false;
				}
				return false;
			}

			private boolean foundEnrichedSpring(String string) {
				if(Method.npcIsNotNull(string)){
					System.out.println("Found an enriched spring!");
					if(closeToNpc(string,"Walking to enriched spring")){
						state = "Attempting to harvest enriched spring";
						Method.npcInteract(string, "Harvest");
					}
				}
				return false;
			}

			private boolean closeToNpc(String name, String string) {
				if(Method.npcIsNotNull(name))
				if(Method.getNPC(name).getLocation().distanceTo(ctx.players.local().getLocation())<8){
					return true;
				}else {
					state = string;
					
					if(Method.npcIsNotNull(name))
					if(Method.getNPC(name).isOnScreen()){
						Method.getNPC(name).click();
					}else if(Method.npcIsNotNull(name)&&!ctx.movement.findPath(Method.getNPC(name).getLocation()).traverse()){
					Method.clickOnMap(Method.getNPC(name).getLocation().randomize(4, 7));
					}
					//ctx.movement.findPath(Method.getNPC(name).getLocation()).traverse();
					ctx.game.sleep(2000,2400);
				}
				return false;
			}
			
		}
		private void updateCounts() {
			 level = ctx.skills.getLevel(Skills.DIVINATION);
			Method.inventoryGetCount(memoryType);
			xpToLevel = ctx.skills.getExperienceAt(level+1) - ctx.skills.getExperience(Skills.DIVINATION);
			calcExpHr();
			
		}
		private boolean closeToObj(Tile loc, String string) {
			if(loc!=null)
			if(loc.distanceTo(ctx.players.local().getLocation())<18){
				return true;
			}else if(loc.distanceTo(ctx.players.local().getLocation())<8 && ctx.widgets.get(1186,1).isVisible()){
				System.out.println("Getting rid of dialogue");
				if(!wait.isRunning()){
				Method.pressContinue();
				//Method.clickOnMap(loc.randomize(1, 2));
				wait = new Timer(Random.nextInt(700, 1700));
				}
			}else {
				state = string;
				ctx.movement.stepTowards(loc.randomize(2,5));
				ctx.game.sleep(1800,2600);
				//ctx.movement.findPath(loc).traverse();
			}
			return false;
		}/*
		private Image getImage(String url) {
			try {
				return ImageIO.read(new URL(url));
			} catch (IOException e) {
				return null;
			}
		}*/
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
		
		if(start){
		runtimeHold = runtime.getElapsed();
		runtimeHold = 3600000/runtimeHold;
		expPerHr = (int)runtimeHold * expGained;
		String expHr = "";
		expHr = ""+ expPerHr;
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
		g.setColor(Color.green);
		g.drawString("State: "+state, 20, 130);
		g.setFont(myFont);
		g.setColor(Color.CYAN);
		g.drawString("Runtime: " +hours+":"+minHold +":" + secHold, 20, 150);
		g.drawString("XP left: "+xpToLevel + "XP", 20, 170);
		g.drawString("Current level: " + level, 20, 190);
		g.drawString("XP Gained: " + expGained + " XP : P/Hr(" +expHr+"K)" , 20, 210);
		g.drawString("Location: " + location, 20, 230);
		g.drawString("Prioritize nearby wisps: " + prioritizeNearbyWisps, 20, 250);
		g.drawString("Capture & destroy chronicles: " + catchChronicles, 20, 270);
		g.drawString("Capture & deposit chronicles: " + catchAndDepoChronicles, 20, 290);
		if(Method.inventoryContains(energyType))
		g.drawString("Money amount in energy: " + price * Method.inventoryStackSize(energyType) + "GP", 20, 310);
		//g.drawString("XP per hour: "+ expPerHr, 20, 230);
		}
		
	}

	private void calcExpHr() {
		int current = ctx.skills.getExperience(25);
		int diff = current - initialExp;
		expGained = diff;
		
	}

	public void initiateGUI() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				final DeltaDivinityGUI deltagui = new DeltaDivinityGUI();
				deltagui.setVisible(true);
				deltagui.setResizable(false);
			
			}
		});
	}
	class DeltaDivinityGUI extends JFrame {
		public DeltaDivinityGUI() {
			initComponents();
			priorCheckButton.setSelected(true);
		}

		private void strtBtnActionPerformed(ActionEvent e) {
			String convChoice = convertList.getSelectedItem().toString();
			
			if(chronicleCheckButton2.isSelected()){
				catchChronicles = true;
			}else catchChronicles = false;
			
			if(priorCheckButton.isSelected()){
				prioritizeNearbyWisps = true;
			}else prioritizeNearbyWisps = false;
			
			if(convChoice=="Divine Energy"){
				convertType = convert.DIVINEENERGY.getID();
				animationType = animation.TODIVINEENERGY.getID();
			}
			if(convChoice=="Divinity Experience"){
				convertType = convert.DIVINITYEXP.getID();
				animationType = animation.TODIVINEEXP.getID();
			}			 
			if(convChoice=="Enhanced Experience"){
				convertType = convert.BOTH.getID();
				animationType = animation.TODIVINEEXP.getID();
			}
			start = true;
			this.dispose();
		}

		private void initComponents() {
			// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
			// Generated using JFormDesigner Evaluation license - Christian Day
			label1 = new JLabel();
			convertmemorylabel = new JLabel();
			convertList = new JComboBox<String>();
			priorCheckButton = new JCheckBox();
			strtBtn = new JButton();
			chronicleCheckButton2 = new JCheckBox();

			//======== this ========
			Container contentPane = getContentPane();

			//---- label1 ----
			label1.setText("Delta Divinity");
			label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 6f));

			//---- convertmemorylabel ----
			convertmemorylabel.setText("Convert memories into: ");

			//---- convertList ----
			convertList.setModel(new DefaultComboBoxModel<String>(new String[] {
					"Divine Energy",
					"Divinity Experience",
					"Enhanced Experience"
			}));

			//---- priorCheckButton ----
			priorCheckButton.setText("Prioritize nearby wisps");

			//---- strtBtn ----
			strtBtn.setText("START");
			strtBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					strtBtnActionPerformed(e);
				}
			});

			//---- chronicleCheckButton2 ----
			chronicleCheckButton2.setText("Capture and destroy chronicles");

			GroupLayout contentPaneLayout = new GroupLayout(contentPane);
			contentPane.setLayout(contentPaneLayout);
			contentPaneLayout.setHorizontalGroup(
				contentPaneLayout.createParallelGroup()
					.addGroup(contentPaneLayout.createSequentialGroup()
						.addGroup(contentPaneLayout.createParallelGroup()
							.addGroup(contentPaneLayout.createSequentialGroup()
								.addGap(119, 119, 119)
								.addComponent(label1))
							.addGroup(contentPaneLayout.createSequentialGroup()
								.addGap(20, 20, 20)
								.addGroup(contentPaneLayout.createParallelGroup()
									.addGroup(contentPaneLayout.createSequentialGroup()
										.addComponent(convertmemorylabel)
										.addGap(27, 27, 27)
										.addComponent(convertList, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
									.addGroup(contentPaneLayout.createSequentialGroup()
										.addGroup(contentPaneLayout.createParallelGroup()
											.addComponent(priorCheckButton)
											.addComponent(chronicleCheckButton2))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
										.addComponent(strtBtn)))))
						.addContainerGap())
			);
			contentPaneLayout.setVerticalGroup(
				contentPaneLayout.createParallelGroup()
					.addGroup(contentPaneLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(label1)
						.addGap(18, 18, 18)
						.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(convertmemorylabel)
							.addComponent(convertList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(contentPaneLayout.createParallelGroup()
							.addGroup(contentPaneLayout.createSequentialGroup()
								.addComponent(priorCheckButton)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(chronicleCheckButton2)
								.addGap(0, 16, Short.MAX_VALUE))
							.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
								.addGap(0, 46, Short.MAX_VALUE)
								.addComponent(strtBtn)))
						.addContainerGap())
			);
			pack();
			setLocationRelativeTo(getOwner());
		}

		private JLabel label1;
		private JLabel convertmemorylabel;
		private JComboBox<String> convertList;
		private JCheckBox priorCheckButton;
		private JButton strtBtn;
		private JCheckBox chronicleCheckButton2;
	}


	
	

}
