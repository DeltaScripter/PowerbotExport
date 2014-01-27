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
description = "Kills Unicows; gathers unicorn horns for 400k-1M profit/hr depending on combat level", 
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
    public static boolean bank = false;
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
				start = true;
				this.dispose();
			}

			private void eatFoodBoxActionPerformed(ActionEvent e) {
				//if(eatFoodBox.isSelected()){
					//foodTypeCombo.setEnabled(true);
				//}else foodTypeCombo.setEnabled(false);
			}

			private void initComponents() {
				// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
				// Generated using JFormDesigner Evaluation license - Bob Bob
				label1 = new JLabel();
				strtBtn = new JButton();
				eatFoodBox = new JCheckBox();
				foodTypeCombo = new JComboBox<String>();

				//======== this ========
				setTitle("Delta Unicow");
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
				eatFoodBox.setText("Eat food while fighting?");
				eatFoodBox.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						eatFoodBoxActionPerformed(e);
					}
				});

				//---- foodTypeCombo ----
				foodTypeCombo.setModel(new DefaultComboBoxModel<String>(new String[] {
					"Lobster",
					"Swordfish"
				}));

				GroupLayout contentPaneLayout = new GroupLayout(contentPane);
				contentPane.setLayout(contentPaneLayout);
				contentPaneLayout.setHorizontalGroup(
					contentPaneLayout.createParallelGroup()
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGap(20, 20, 20)
							.addComponent(eatFoodBox)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(foodTypeCombo, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
							.addGap(24, 24, 24))
						.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(contentPaneLayout.createParallelGroup()
								.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
									.addComponent(label1)
									.addGap(99, 99, 99))
								.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
									.addComponent(strtBtn)
									.addContainerGap())))
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
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(strtBtn)
							.addContainerGap(4, Short.MAX_VALUE))
				);
				pack();
				setLocationRelativeTo(getOwner());
				// JFormDesigner - End of component initialization  //GEN-END:initComponents
			}

			// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
			// Generated using JFormDesigner Evaluation license - Bob Bob
			private JLabel label1;
			private JButton strtBtn;
			private JCheckBox eatFoodBox;
			private JComboBox<String> foodTypeCombo;
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
	    		    profit = time*(hornCount*2720);
	    		    System.out.println("Profit per hour: "+ profit);
	    		    hornCounter = new Timer(0);
	    		    once = true;
	    }
	 
	    }
		
		g.setFont(myFont);
		g.setColor(Color.GREEN);
		g.drawString("State: "+state, 20, 130);
		g.drawString("Health%: "+ctx.players.local().getHealthPercent(), 20, 150);
		if(profit>0)
		g.drawString("Money per hour: "+(int)profit + "GP", 20, 170);
		else g.drawString("Money per hour: Waiting..", 20, 170);
		g.setColor(Color.CYAN);
		g.drawString("Runtime: " +hours+":"+minHold +":" + secHold, 20, 110);
		g.drawString("Food support for fighting? "+foodSupport, 20, 190);
		g.drawString("Money made(updates at bank): "+hornCount*2729, 20, 210);
	}
}
