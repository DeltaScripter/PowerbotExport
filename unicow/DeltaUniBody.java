package unicow;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.powerbot.event.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.methods.Hud.Window;
import org.powerbot.script.util.Timer;

import unicow.UniData.items;




@org.powerbot.script.Manifest(authors = { "Delta Scripter" }, name = "Delta Unicows", 
description = "Kills Unicows; gathers unicorn horns for 1M+ profit/hr!", 
website = "http://www.powerbot.org/community/topic/1146188-delta-unicow-kills-unicows-takes-unicorn-horns/", version = 1,hidden  =false, topic =1146188)
public class DeltaUniBody extends PollingScript implements PaintListener{
	
	public DeltaUniBody(){
		getExecQueue(State.START).add(new Runnable() {
			@Override
			public void run() {
				initiateGui();
				addNode(new UniMain(ctx));
				addNode(new UniBank(ctx));
				runtime = new Timer(0);
				secondsA = new Timer(0);
				minutesA = new Timer(0);
			}
		});
	}
	
	private final List<UniNode> nodeList = Collections.synchronizedList(new ArrayList<UniNode>());
	static String state;
    public static boolean bank = true;
    public boolean init = false;
	public Timer wait = new Timer(0);
	UniMethod Method = new UniMethod(ctx);
	public static boolean e = false;
	
	boolean start = false;
	static boolean foodSupport = false;
	static boolean foodAtBank = false;
	static int foodID = 0;
	static int hornCount = 0;
	static int capeID = 0;
	
	//summoning
	public static int pouchID;
	public static boolean Bob = false;//beast of burden
	
	//profit an hour
	int tempNum = 0;
	Timer hornCounter = new Timer(0);
	
	private Timer runtime;
	private Timer secondsA;
	private Timer minutesA;
	@Override
	public int poll() {
		
		
		if(start){
		while(ctx.widgets.get(1477,54).isVisible()){
			state = "Closing interface";
			ctx.widgets.get(1477,54).getChild(2).click();
		}
		if(e){
			shutdown();
		}
		if(!init){
			if(ctx.bank.close())
			if(ctx.hud.view(Window.WORN_EQUIPMENT)){
				if(Method.equipItemIsNotNull(items.RINGOFDUNGEONEERING.getID())){
					System.out.println("Found Dungeoneering ring");
					UniBank.dungeoneeringRing = true;
				}
				if(Method.equipItemIsNotNull(items.ARDOUGNECAPE1.getID())){
					System.out.println("Found Ardougne cloak");
					UniMain.ardougneCloak = true;
					capeID = 15345;
				}else if(Method.equipItemIsNotNull(15347)){
					System.out.println("Found Ardougne cloak");
					UniMain.ardougneCloak = true;
					capeID = 15347;
				}else  if(Method.equipItemIsNotNull(15349)){
					System.out.println("Found Ardougne cloak");
					UniMain.ardougneCloak = true;
					capeID = 15349;
				}
				init = true;
			}
		
		}
		if(!hornCounter.isRunning())
		//System.out.println("Counter: " + hornCounter.getElapsed());
		for(UniNode node: nodeList){
			if(node.activate()){
				node.execute();
			}
		}
		
		
		}
		
		return 400;
	}
	private void shutdown() {
		System.out.println("Now shutting down");
		getController().stop();
	}

	   private void addNode(final UniNode...nodes) {
		   
	        for(UniNode node : nodes) {
	            if(!this.nodeList.contains(node)) {
	                this.nodeList.add(node);
	            }
	        }
	    }
	   class UnicowGUI extends JFrame {
			public UnicowGUI() {
				initComponents();
			}

			private void strtBtnActionPerformed(ActionEvent e) {
				if(eatFoodBox.isSelected()){
					foodSupport = true;
				}
				String foodType = foodTypeCombo.getSelectedItem().toString();
			
				if(foodType.contains("Swordfish")){
					foodID = 373;
				}
				if(foodType.contains("Lobster")){
					foodID = 379;
				}
				String pouchType = beastCombo.getSelectedItem().toString();
				if(BobBox2.isSelected()){
					Bob = true;
				}
				if(pouchType.contains("Thorny snail")){
					pouchID = 12019;
				}
				if(pouchType.contains("Spirit kalphite")){
					pouchID = 12063;
				}
				if(pouchType.contains("Bull ant")){
					pouchID = 12087;
				}
				if(pouchType.contains("Spirit terrorbird")){
					pouchID = 12007;
				}
				if(pouchType.contains("War tortoise")){
					pouchID = 12031;
				}
				if(pouchType.contains("Pack yak")){
					pouchID = 12810;
				}
				start = true;
				this.dispose();
			}

			private void initComponents() {
				label1 = new JLabel();
				strtBtn = new JButton();
				eatFoodBox = new JCheckBox();
				foodTypeCombo = new JComboBox<String>();
				beastCombo = new JComboBox<String>();
				BobBox2 = new JCheckBox();

				//======== this ========
				Container contentPane = getContentPane();

				//---- label1 ----
				label1.setText("Delta Unicow");
				label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 6f));

				//---- strtBtn ----
				strtBtn.setText("START");
				strtBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						strtBtnActionPerformed(e);
					}
				});

				//---- eatFoodBox ----
				eatFoodBox.setText("Eat food?");

				//---- foodTypeCombo ----
				foodTypeCombo.setModel(new DefaultComboBoxModel<String>(new String[] {
					"Lobster",
					"Swordfish"
				}));

				//---- beastCombo ----
				beastCombo.setModel(new DefaultComboBoxModel<String>(new String[] {
					"Thorny snail",
					"Spirit kalphite",
					"Bull ant",
					"Spirit terrorbird",
					"War tortoise",
					"Pack yak"
				}));

				//---- BobBox2 ----
				BobBox2.setText("Beast of Burden?");

				GroupLayout contentPaneLayout = new GroupLayout(contentPane);
				contentPane.setLayout(contentPaneLayout);
				contentPaneLayout.setHorizontalGroup(
					contentPaneLayout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
							.addContainerGap(103, Short.MAX_VALUE)
							.addComponent(label1)
							.addGap(99, 99, 99))
						.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
							.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addGroup(contentPaneLayout.createSequentialGroup()
									.addContainerGap(232, Short.MAX_VALUE)
									.addComponent(strtBtn))
								.addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
									.addGap(20, 20, 20)
									.addGroup(contentPaneLayout.createParallelGroup()
										.addGroup(contentPaneLayout.createSequentialGroup()
											.addComponent(BobBox2)
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
											.addComponent(beastCombo, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
										.addGroup(contentPaneLayout.createSequentialGroup()
											.addComponent(eatFoodBox)
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
											.addComponent(foodTypeCombo, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)))))
							.addGap(24, 24, 24))
				);
				contentPaneLayout.setVerticalGroup(
					contentPaneLayout.createParallelGroup()
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(label1)
							.addGap(18, 18, 18)
							.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(eatFoodBox)
								.addComponent(foodTypeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
							.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(beastCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(BobBox2))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(strtBtn)
							.addContainerGap())
				);
				pack();
				setLocationRelativeTo(getOwner());
				// JFormDesigner - End of component initialization  //GEN-END:initComponents
			}

			// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
			// Generated using JFormDesigner Evaluation license - Christ Day
			private JLabel label1;
			private JButton strtBtn;
			private JCheckBox eatFoodBox;
			private JComboBox<String> foodTypeCombo;
			private JComboBox<String> beastCombo;
			private JCheckBox BobBox2;
			// JFormDesigner - End of variables declaration  //GEN-END:variables
		}


		public void initiateGui() {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					final UnicowGUI deltagui = new UnicowGUI();
					deltagui.setVisible(true);
					deltagui.setResizable(false);
				
				}
			});
		}
		
	   boolean once = false;
		double profit =0;
private Font myFont = new Font("Consolas",Font.BOLD,14);
	@Override
	public void repaint(Graphics g) {
		int seconds = (int)(runtime.getElapsed()/1000);
		int minutes = (int)(seconds/60);
		int hours = (int)(minutes/60);
		int secHold = (int)(secondsA.getElapsed()/1000);
		int minHold = (int)(minutesA.getElapsed()/60000);
		
		if(secHold>=60)
			secondsA = new Timer(0);
		if(minHold>=60)
			minutesA = new Timer(0);
	
	    double time;
	    if(!ctx.bank.isOpen())
	    	once = false;
	    if(ctx.bank.isOpen() && !once){
	    	int hornCount = Method.inventoryGetCount(items.HORN.getID()) + Method.inventoryStackSize(items.HORN.getID()+1);
	    	if(!hornCounter.isRunning()){
	    		    time = ((hornCounter.getElapsed())/1000);
	    		    time = 3600/time;
	    		    profit = time*(hornCount*4200);
	    		    System.out.println("Profit per hour: "+ profit);
	    		    hornCounter = new Timer(0);
	    		    once = true;
	    }
	 
	    }
		// (a > b) ? a : b;
		g.setFont(myFont);
		g.setColor(Color.GREEN);
		g.drawString("State: "+state, 20, 130);
		g.drawString((ctx.summoning.isFamiliarSummoned() ? "Familiar time left: "+
		(int)(ctx.summoning.getTimeLeft()/60) +" minutes": "No familiar summoned"), 20, 150);
		if(profit>0)
		g.drawString("Money per hour: "+(int)profit + "GP", 20, 170);
		else g.drawString("Money per hour: Waiting..", 20, 170);
		g.setColor(Color.CYAN);
		g.drawString("Runtime: " +hours+":"+minHold +":" + secHold, 20, 110);
		g.drawString("Food support for fighting? "+foodSupport, 20, 190);
		g.drawString("Money made(updates at bank): "+hornCount*4102, 20, 210);
	}
}
