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
import org.powerbot.script.wrappers.Tile;
import org.powerbot.script.util.Random;
import org.powerbot.script.util.Timer;

import divination.DivineData.animation;
import divination.DivineData.convert;
import divination.DivineData.memories;
import divination.DivineData.wisps;



@org.powerbot.script.Manifest(authors = { "Delta Scripter" }, name = "Delta Divinity", 
description = "Trains the Divination skill, harvests and converts energy to your choosing.",
website = "http://www.powerbot.org/community/topic/1082019-delta-divinity/", version = 1.07)
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
	private int initialExp;
	private int expGained;
	private int expPerHr;
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
	private Timer runtime;
	private Timer secondsA;
	private Timer minutesA;
	
	DivineMethod Method = new DivineMethod(ctx);
	
	
	@Override
	public int poll() {
		
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
			while(riftArea==null && Method.objIsNotNull("Energy Rift")){
				state = "Setting rift area";
				System.out.println("Setting rift area");
				riftArea = Method.getObject("Energy Rift").getLocation();
				riftArea = new Tile(riftArea.getX(), riftArea.getY()-2,riftArea.getPlane());
				break;
			}
			
			while(ctx.players.local().getAnimation()==animationType){
				updateCounts();
				calcAntiPattern();
				state = "Converting memories..";
				waiting = new Timer(3700);
			}
			if(!waiting.isRunning())
			if(!Method.inventoryContains(memoryType) && !Method.backPackIsFull()){
				harvest = true;
			}else
			if(closeToObj(riftArea,"Walking to rift")){
				if(ctx.widgets.get(131,convertType).isVisible()){
					if(convertType == 7 && ctx.widgets.get(131,39).getTextureId()==13827){
						ctx.widgets.get(131,6).click();
					}else ctx.widgets.get(131,convertType).click();
					ctx.game.sleep(2000,2800);
				}else if(ctx.widgets.get(1186,2).isVisible()){
					state = "Closing dialogue";
					System.out.println("Closing dialogue");
					Method.clickOnMap(ctx.players.local().getLocation());
				}else Method.interactO("Energy Rift", "Convert memories", "Interacting with rift");
			}
			
		}

	   }
	   private void calcAntiPattern() {
			int number = rand.nextInt(0, 2);
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
				Method.clickOnMap(loc);
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
		}

		private void strtBtnActionPerformed(ActionEvent e) {
			String convChoice = convertList.getSelectedItem().toString();
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
			convertList = new JComboBox<>();
			strtBtn = new JButton();
			label3 = new JLabel();

			//======== this ========
			setTitle("");
			setFont(new Font("Dialog", Font.BOLD, 15));
			Container contentPane = getContentPane();

			//---- label1 ----
			label1.setText("Convert memories into: ");

			//---- convertList ----
			convertList.setModel(new DefaultComboBoxModel<>(new String[] {
					"Divine Energy","Divinity Experience","Enhanced Experience"
			}));

			//---- strtBtn ----
			strtBtn.setText("START");
			strtBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					strtBtnActionPerformed(e);
				}
			});

			//---- label3 ----
			label3.setText("Delta Divinity");
			label3.setFont(label3.getFont().deriveFont(label3.getFont().getStyle() | Font.BOLD, label3.getFont().getSize() + 5f));

			GroupLayout contentPaneLayout = new GroupLayout(contentPane);
			contentPane.setLayout(contentPaneLayout);
			contentPaneLayout.setHorizontalGroup(
				contentPaneLayout.createParallelGroup()
					.addGroup(contentPaneLayout.createSequentialGroup()
						.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
							.addGroup(contentPaneLayout.createSequentialGroup()
								.addGap(0, 0, Short.MAX_VALUE)
								.addComponent(strtBtn))
							.addGroup(contentPaneLayout.createSequentialGroup()
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label3)
								.addGap(95, 95, 95))
							.addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(label1)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
								.addComponent(convertList, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(14, Short.MAX_VALUE))
			);
			contentPaneLayout.setVerticalGroup(
				contentPaneLayout.createParallelGroup()
					.addGroup(contentPaneLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(label3)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(convertList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label1))
						.addGap(18, 18, 18)
						.addComponent(strtBtn)
						.addContainerGap(6, Short.MAX_VALUE))
			);
			setSize(340, 155);
			setLocationRelativeTo(getOwner());
		}

		private JLabel label1;
		private JComboBox<String> convertList;
		private JButton strtBtn;
		private JLabel label3;
	}

	
	

}
