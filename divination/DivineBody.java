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
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Tile;
import org.powerbot.script.util.Random;
import org.powerbot.script.util.Timer;

import divination.DivineData.animation;
import divination.DivineData.convert;
import divination.DivineData.memories;
import divination.DivineData.wisps;



@org.powerbot.script.Manifest(authors = { "Delta Scripter" }, name = "Delta Divinity", 
description = "Trains the Divination Skill; harvests and converts energy to your choosing",
topic = 1130348, version = 1.1, website = "http://www.powerbot.org/community/topic/1130348-delta-divinity/"
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
				
			}

			private void initilizeLocVariables() {
				
				if(Method.npcIsNotNull(wisps.PALEWISP.getName())){
					location = "Lummbridge";
					wispKind = wisps.PALEWISP.getName();
					wispSpring = wisps.PALESPRING.getName();
					memoryType = memories.PALEMEMORY.getName();
					System.out.println(""+wispKind);
				}
				if(Method.npcIsNotNull(wisps.FLICKERINGWISP.getName())){
					location = "Falador";
					wispKind = wisps.FLICKERINGWISP.getName();
					wispSpring = wisps.FLICKERINGSPRING.getName();
					memoryType = memories.FLICKERINGMEMORY.getName();
				}
				if(Method.npcIsNotNull(wisps.BRIGHTWISP.getName())){
					location = "Varrock";
					wispKind = wisps.BRIGHTWISP.getName();
					wispSpring = wisps.BRIGHTSPRING.getName();
					memoryType = memories.BRIGHTMEMORY.getName();
				}
				if(Method.npcIsNotNull(wisps.GLOWINGWISP.getName())){
					location = "Seers' Village";
					wispKind = wisps.GLOWINGWISP.getName();
					wispSpring = wisps.GLOWINGSPRING.getName();
					memoryType = memories.GLOWINGMEMORY.getName();
				}
				if(Method.npcIsNotNull(wisps.SPARKLINGWISP.getName())){
					location = "Golden Apple Tree";
					wispKind = wisps.SPARKLINGWISP.getName();
					wispSpring = wisps.SPARKLINGSPRING.getName();
					memoryType = memories.SPARKLINGMEMORY.getName();
				}
				if(Method.npcIsNotNull(wisps.GLEAMINGWISP.getName())){
					location = "Shilo Village";
					wispKind = wisps.GLEAMINGWISP.getName();
					wispSpring = wisps.GLEAMINGSPRING.getName();
					memoryType = memories.GLEAMINGMEMORY.getName();
				}
				if(Method.npcIsNotNull(wisps.VIBRANTWISP.getName())){
					location = "Mobilizing Armies";
					wispKind = wisps.VIBRANTWISP.getName();
					wispSpring = wisps.VIBRANTSPRING.getName();
					memoryType = memories.VIBRANTMEMORY.getName();
				}
				if(Method.npcIsNotNull(wisps.LUSTROUSWISP.getName())){
					location = "Slayer Tower";
					wispKind = wisps.LUSTROUSWISP.getName();
					wispSpring = wisps.LUSTROUSSPRING.getName();
					memoryType = memories.LUSTROUSMEMORY.getName();
				}
				if(Method.npcIsNotNull(wisps.BRILLIANTWISP.getName())){
					location = "Mage Training Arena";
					wispKind = wisps.BRILLIANTWISP.getName();
					wispSpring = wisps.BRILLIANTSPRING.getName();
					memoryType = memories.BRILLIANTMEMORY.getName();
				}
				if(Method.npcIsNotNull(wisps.RADIANTWISP.getName())){
					location = "Dragontooth Island";
					wispKind = wisps.RADIANTWISP.getName();
					wispSpring = wisps.RADIANTSPRING.getName();
					memoryType = memories.RADIANTMEMORY.getName();
				}
				if(Method.npcIsNotNull(wisps.LUMINOUSWISP.getName())){
					location = "Sophanem";
					wispKind = wisps.LUMINOUSWISP.getName();
					wispSpring = wisps.LUMINOUSSPRING.getName();
					memoryType = memories.LUMINOUSMEMORY.getName();
				}
				if(Method.npcIsNotNull(wisps.INCANDESCENTWISP.getName())){
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
	private int convertType;
	private int animationType;
	private String location;
	private String wispKind;
	private String wispSpring;
	private String memoryType;
	private Tile riftArea;
	private int paleECount;
	public static boolean antiPattern;
	private Random rand = new Random();
	private boolean start = false;
	private Timer waiting = new Timer(0);
	public static Timer wait = new Timer(0);//General use timer
	private Timer runtime;
	private Timer secondsA;
	private Timer minutesA;
	private int initialExp;
	private int expGained;
	private int expPerHr;
	
	DivineMethod Method = new DivineMethod(ctx);
	DivineAntipattern anti = new DivineAntipattern(ctx);
	public static boolean prioritizeNearbyWisps;
	
	
	@Override
	public int poll() {
		while(Method.inventoryContains("Logs")){
			DivineBody.state = "Dropping logs";
			Method.interactInventory("Logs", "Drop", "Logs");
		}
		while(ctx.widgets.get(1477,54).isVisible()){
			state = "Closing interface";
			ctx.widgets.get(1477,54).getChild(2).click();
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
			return !harvest;
		}

		@Override
		public void execute() {
			calcAntiPattern();
			anti.closeInteruptions();
			//Adjust camera
			if(ctx.camera.getPitch()<50){
				ctx.camera.setPitch(90);
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
			
			if(!Method.inventoryContains(memoryType) && !Method.backPackIsFull()){//if you're ready to gather more memories..
				harvest = true;
			}else
			if(closeToObj(riftArea,"Walking to rift")){
				if(ctx.widgets.get(131,convertType).isValid()&&
						ctx.widgets.get(131,convertType).isVisible()){
					System.out.println("Here" + 	ctx.widgets.get(131,convertType));
					ctx.widgets.get(131,convertType).click();
					ctx.game.sleep(Random.nextInt(600, 1200));
					
				}else if(ctx.widgets.get(1186,2).isVisible()){
					state = "Closing dialogue";
					System.out.println("Closing dialogue");
					if(!wait.isRunning()){
					Method.clickOnMap(ctx.players.local().getLocation());
					wait = new Timer(Random.nextInt(500, 1200));
					}
				}else clickRift("Energy Rift", "Interacting with rift");
			}
			//if(waiting.isRunning())
				//System.out.println("Timer: " + waiting.getRemaining());
		}

		private void clickRift(String name, String o) {
			for(GameObject y: ctx.objects.select().name(name).nearest().first()){
				DivineBody.state = o;
						if (y.isOnScreen()) {
							ctx.mouse.move(y.getLocation().getMatrix(ctx).getPoint(Random.nextDouble() * 0.2D - 0.1D,+0.10D,+100));
							ctx.mouse.click(true);
							ctx.game.sleep(Random.nextInt(700, 1200));
						} else ctx.camera.turnTo(y);
					
					}
			
		}

	   }
	   private void calcAntiPattern() {
			int number = rand.nextInt(0, 3);
			if(number == 1){
				antiPattern = true;
			}
			
		}
		class harvestWisp extends DivineNode{

			public harvestWisp(MethodContext ctx) {
				super(ctx);
			}

			@Override
			public boolean activate() {
				return harvest;
			}
			@Override
			public void execute() {
				calcAntiPattern();
				while(ctx.players.local().getAnimation()==animation.HARVESTINGSPRING2.getID()){
					state = "Harvesting spring";
					calcAntiPattern();
					updateCounts();
				}
				while(Method.backPackIsFull()){
					state = "backpack is full";
					harvest = false;
					break;
				}
				if(ctx.widgets.get(131,1).isVisible()){
					state = "Closing interference";
					Method.clickOnMap(ctx.players.local().getLocation());
				}else
				if(!foundEnrichedSpring("Enriched sparkling spring"))
				if(!foundEnrichedSpring("Enriched glowing spring"))
					
					if(prioritizeNearbyWisps && Method.npcIsNotNull(wispSpring) && 
							Method.getNPC(wispSpring).getLocation().distanceTo(ctx.players.local().getLocation())<6){
						if(closeToNpc(wispSpring,"Walking to spring")){
							state = "Attempting to harvest spring";
							Method.npcInteract(wispSpring, "Harvest");
						}
					}else
					if(prioritizeNearbyWisps && Method.npcIsNotNull(wispKind) && 
							Method.getNPC(wispKind).getLocation().distanceTo(ctx.players.local().getLocation())<6){
						if(closeToNpc(wispKind,"Walking to wisp")){
							state = "Attempting to convert wisp to spring";
							Method.npcInteract(wispKind, "Harvest");
						}
					}else
				if(Method.npcIsNotNull(wispSpring) && 
						Method.getNPC(wispSpring).getLocation().distanceTo(ctx.players.local().getLocation())<12){
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
				}else ctx.movement.findPath(riftArea).traverse();
				
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
				if(Method.getNPC(name).getLocation().distanceTo(ctx.players.local().getLocation())<7){
					return true;
				}else {
					state = string;
					if(Method.getNPC(name).isOnScreen()){
						System.out.println("Clicking on npc to get closer");
						Method.getNPC(name).click();
					}else
					ctx.movement.stepTowards(Method.getNPC(name).getLocation());
					//ctx.movement.findPath(Method.getNPC(name).getLocation()).traverse();
					ctx.game.sleep(2000,2400);
				}
				return false;
			}
			
		}
		private void updateCounts() {
			paleECount = Method.inventoryGetCount(memoryType);
			calcExpHr();
			
		}
		private boolean closeToObj(Tile loc, String string) {
			if(loc!=null)
			if(loc.distanceTo(ctx.players.local().getLocation())<5){
				return true;
			}else if(loc.distanceTo(ctx.players.local().getLocation())<8 && ctx.widgets.get(1186,1).isVisible()){
				System.out.println("Cliking on map");
				if(!wait.isRunning()){
				Method.clickOnMap(loc);
				wait = new Timer(Random.nextInt(700, 1700));
				}
			}else{
				state = string;
				ctx.movement.stepTowards(loc);
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
		String expHr = "";
		if(start){
		runtimeHold = runtime.getElapsed();
		runtimeHold = 3600000/runtimeHold;
		expPerHr = (int)runtimeHold * expGained;
		expHr = ""+ expPerHr;
		if(expHr.length()>3)
		expHr = expHr.substring(0, expHr.length() - 3);
		
		
		mouseX = (int) ctx.mouse.getLocation().getX();
		mouseY = (int) ctx.mouse.getLocation().getY();
		int level = ctx.skills.getLevel(25);
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
		g.drawString("Energy in inventory: " + paleECount, 20, 170);
		g.drawString("Current level: " + level, 20, 190);
		g.drawString("XP Gained: " + expGained + " XP : P/Hr(" +expHr+"K)" , 20, 210);
		g.drawString("Location: " + location, 20, 230);
		g.drawString("Prioritize nearby wisps: " + prioritizeNearbyWisps, 20, 250);
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
			label1 = new JLabel();
			convertmemorylabel = new JLabel();
			convertList = new JComboBox<String>();
			priorCheckButton = new JCheckBox();
			strtBtn = new JButton();

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
										.addComponent(priorCheckButton)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
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
								.addGap(0, 21, Short.MAX_VALUE))
							.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
								.addGap(0, 21, Short.MAX_VALUE)
								.addComponent(strtBtn)))
						.addContainerGap())
			);
			pack();
			setLocationRelativeTo(getOwner());
			// JFormDesigner - End of component initialization  //GEN-END:initComponents
		}

		private JLabel label1;
		private JLabel convertmemorylabel;
		private JComboBox<String> convertList;
		private JCheckBox priorCheckButton;
		private JButton strtBtn;
	}


	
	

}
