package OldQuester;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import OldQuester.quests.OldCooksAssistant;
import OldQuester.quests.OldErnestTheChicken;
import OldQuester.quests.OldRestlessGhost;
import OldQuester.quests.OldRuneMysteries;
import OldQuester.quests.OldWaterFall;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;



@Script.Manifest(name = "O7 Quester", 
description = "Automatically completes quests you want done.",properties = "client=4; topic = 1231910")
public class DeltaOldQuester extends PollingScript<ClientContext> implements PaintListener{

	
	public static boolean FOOD_FEATURE = false;
	public static boolean shutOff = false;//for shutting down the script when necessary
	
	public static String state = "Welcome...";
	public static int scriptSelect = 0;
	public static ArrayList<Tile> walkingTile = new ArrayList<Tile>();
	public String questName = "Choose a quest..";
	
	//for paint
		public static int progress = 0;
		public static int numSteps = 0;
	
	public static int foodID = 0;
	
    private boolean start = false;//for using onStart() only once
    private boolean goOn = false;
	private final List<OldNode> nodesList = Collections.synchronizedList(new ArrayList<OldNode>());

	@Override
	public void poll() {
		
		onStart();
		if(shutOff || !ctx.game.loggedIn()){
			System.out.println("Shutting down..");
			this.ctx.controller.stop();
			stop();
			this.stop();
		}
		if(goOn){
			for(OldNode node : nodesList) {
	            if(node.activate()) {
	            	//ystem.out.println("In here `2: " + node);
	                node.execute();
	           }
	       }
			
		}
		
	}
	/*INSTRUCTIONS ON NODE WALKING
	 * -Any new nodes added must be connected to an existing node
	 * I believe each node can have multiple branches to other paths
	 * -We used code inside the Restless ghost quest script to generate a new path conveniently
	 * */
	
	
	private void onStart() {
		if(!start){
			initiateGui();
			addNode(new OldRuneMysteries(ctx));
			addNode(new OldRestlessGhost(ctx));
			addNode(new OldCooksAssistant(ctx));
			addNode(new OldWaterFall(ctx));
			addNode(new OldErnestTheChicken(ctx));
			///System.out.println("Added nodes!");
			start = true;
		}
		
	}
	   private void addNode(final OldNode...nodes) {
		   
	        for(OldNode node : nodes) {
	            if(!this.nodesList.contains(node)) {
	                this.nodesList.add(node);
	            }
	        }
	    }
	   
		BufferedImage paint = downloadImage("http://i1223.photobucket.com/albums/dd508/thriller645/OldQuesterPaint2_zps1196bf64.png");
		
	   public static int mouseX;
	   public static int mouseY;
	@Override
	public void repaint(Graphics g) {
		

    	
		g.drawImage(paint, -5, -45, null);
		
		mouseX = (int) ctx.input.getLocation().getX();
		mouseY = (int) ctx.input.getLocation().getY();
		
		setMouse(g);
		   
		g.setColor(Color.BLACK);
		g.drawRect(6,330, 500, 7);
		g.setColor(Color.green);
		if(numSteps!=0)
		g.fillRect(6,330, progress*500/numSteps, 7);
		
		g.setColor(Color.BLACK);
		g.drawString("State: " + state, 20,370);
		g.drawString("Quest name: " + questName, 20,390);
		
		String h = ""+ ctx.widgets.component(548, 77).text().toString();
		int currentHealth = Integer.parseInt(h);
		try{
			
		g.drawString("Player health: " + currentHealth + " HP - eating at < 9 HP", 20,430);
		}catch(Exception e){}
		
		g.drawString("Food ID: " +foodID, 20,450);
		
	}
	private void setMouse(Graphics g) {
		g.setColor(Color.ORANGE);
		g.drawLine(mouseX, mouseY - 800, mouseX, mouseY + 800);
		g.drawLine(mouseX - 800, mouseY, mouseX + 800, mouseY);
		
	}
	boolean bootGUI = false;
	public void initiateGui() {
		if(bootGUI==false){
			System.out.println("In here `3");
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				final DeltaOldQuesterGUI deltagui = new DeltaOldQuesterGUI();
				deltagui.setVisible(true);
				deltagui.setResizable(false);
			
			}
		});
		bootGUI = true;
		}
	}
	class DeltaOldQuesterGUI extends JFrame {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public DeltaOldQuesterGUI() {
			initComponents();
		}

		private void okButtonActionPerformed(ActionEvent e) {
			
			setQuestToComplete();
			setFood();
			this.setVisible(false);
			goOn = true;//for starting normal script activity - unpausing the script
			this.dispose();
		}
		private void setFood() {
			if(foodCombo.getSelectedItem().toString().contains("Shrimp")){
				foodID = 315;
			}
			
			if(foodCombo.getSelectedItem().toString().contains("Trout")){
				foodID = 333;
			}
			
			if(foodCombo.getSelectedItem().toString().contains("Salmon")){
				foodID = 329;
			}
			
			if(foodCombo.getSelectedItem().toString().contains("")){
				
			}
			
			if(foodCombo.getSelectedItem().toString().contains("")){
				
			}
			
		}

		private void setQuestToComplete() {
			if(questComboBox.getSelectedItem().toString().contains("Restless Ghost")){
				questName = "Restless Ghost";
				scriptSelect = 1;
			}
			if(questComboBox.getSelectedItem().toString().contains("Rune Mysteries")){
				questName = "Rune Mysteries";
				scriptSelect = 2;
			}
			if(questComboBox.getSelectedItem().toString().contains("Cook's Assistant")){
				questName = "Cook's Assistant";
				scriptSelect = 3;
			}
			if(questComboBox.getSelectedItem().toString().contains("Waterfall")){
				questName = "Waterfall";
				scriptSelect = 4;
			}
			if(questComboBox.getSelectedItem().toString().contains("Ernest The Chicken")){
				questName = "Ernest The Chicken";
				scriptSelect = 5;
			}
		}

		private void questComboBoxActionPerformed(ActionEvent e) {
			System.out.println("Changed quest selection");
			if(questComboBox.getSelectedItem().toString().contains("Restless Ghost")){
				textArea1.setText("Start this script near Lumbridge or Varrock");
			}
			if(questComboBox.getSelectedItem().toString().contains("Rune Mysteries")){
				textArea1.setText("Start this script near Lumbridge or Varrock\nNo requirements");
			}
			if(questComboBox.getSelectedItem().toString().contains("Cook's Assistant")){
				textArea1.setText("Start this quest in Lumbridge\n\nYou will need:\n- Empty pot\n- Empty bucket");
			}
			if(questComboBox.getSelectedItem().toString().contains("Waterfall")){
				textArea1.setText("You will need\n- Air runes X 6\n- Earth runes X 6\n- Water runes X 6\n- Rope X 1\n\nMake sure you have only the amounts stated above\nand no more!\n\nStart the script in any of the quest areas(or just outside Catherby bank.)");
			}
			if(questComboBox.getSelectedItem().toString().contains("Ernest The Chicken")){
				textArea1.setText("In development");
			}
		}

		private void initComponents() {
			// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
			// Generated using JFormDesigner Evaluation license - chris gofih
			okButton = new JButton();
			scrollPane1 = new JScrollPane();
			textArea1 = new JTextArea();
			questComboBox = new JComboBox<String>();
			foodCombo = new JComboBox<String>();
			label1 = new JLabel();

			//======== this ========
			setTitle("Delta 07 Quester");
			Container contentPane = getContentPane();
			contentPane.setLayout(null);

			//---- okButton ----
			okButton.setText("START");
			okButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					okButtonActionPerformed(e);
				}
			});
			contentPane.add(okButton);
			okButton.setBounds(333, 15, 104, 34);

			//======== scrollPane1 ========
			{

				//---- textArea1 ----
				textArea1.setText("Please select a quest.");
				textArea1.setLineWrap(true);
				textArea1.setEditable(false);
				scrollPane1.setViewportView(textArea1);
			}
			contentPane.add(scrollPane1);
			scrollPane1.setBounds(3, 54, 437, 173);

			//---- questComboBox ----
			questComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {
					"The Restless Ghost",
					"Rune Mysteries",
					"Cook's Assistant",
					"Ernest The Chicken",
					"Waterfall"
				}));
			contentPane.add(questComboBox);
			questComboBox.setBounds(12, 19, 308, 30);
			questComboBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					questComboBoxActionPerformed(e);
				}
			});
			//---- foodCombo ----
			foodCombo.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Shrimp"
			}));
			contentPane.add(foodCombo);
			foodCombo.setBounds(103, 239, 150, 28);

			//---- label1 ----
			label1.setText("Type of food:");
			contentPane.add(label1);
			label1.setBounds(10, 236, 127, 35);

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < contentPane.getComponentCount(); i++) {
					Rectangle bounds = contentPane.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = contentPane.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				contentPane.setMinimumSize(preferredSize);
				contentPane.setPreferredSize(preferredSize);
			}
			pack();
			setLocationRelativeTo(getOwner());
			// JFormDesigner - End of component initialization  //GEN-END:initComponents
		}

		// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
		// Generated using JFormDesigner Evaluation license - chris gofih
		private JButton okButton;
		private JScrollPane scrollPane1;
		private JTextArea textArea1;
		private JComboBox<String> questComboBox;
		private JComboBox<String> foodCombo;
		private JLabel label1;
		// JFormDesigner - End of variables declaration  //GEN-END:variables
	}
/*
	class DeltaOldQuesterGUI extends JFrame {
		public DeltaOldQuesterGUI() {
			initComponents();
		}

		private void okButtonActionPerformed(ActionEvent e) {
			System.out.println("Pressed ok");
			
			setQuestToComplete();
			
			this.setVisible(false);
			goOn = true;//for starting normal script activity - unpausing the script
			this.dispose();
		}
		private void setQuestToComplete() {
			if(questComboBox.getSelectedItem().toString().contains("Restless Ghost")){
				questName = "Restless Ghost";
				scriptSelect = 1;
			}
			if(questComboBox.getSelectedItem().toString().contains("Rune Mysteries")){
				questName = "Rune Mysteries";
				scriptSelect = 2;
			}
			if(questComboBox.getSelectedItem().toString().contains("Cook's Assistant")){
				questName = "Cook's Assistant";
				scriptSelect = 3;
			}
			if(questComboBox.getSelectedItem().toString().contains("Waterfall")){
				questName = "Waterfall";
				scriptSelect = 4;
			}
		}

		private void questComboBoxActionPerformed(ActionEvent e) {
			System.out.println("Changed quest selection");
			if(questComboBox.getSelectedItem().toString().contains("Restless Ghost")){
				textArea1.setText("Start this script near Lumbridge or Varrock");
			}
			if(questComboBox.getSelectedItem().toString().contains("Rune Mysteries")){
				textArea1.setText("Start this script near Lumbridge or Varrock\nNo requirements");
			}
			if(questComboBox.getSelectedItem().toString().contains("Cook's Assistant")){
				textArea1.setText("Start this quest in Lumbridge\n\nYou will need:\n- Empty pot\n- Empty bucket");
			}
			if(questComboBox.getSelectedItem().toString().contains("Waterfall")){
				textArea1.setText("In development");
			}
		}

		private void initComponents() {
			okButton = new JButton();
			scrollPane1 = new JScrollPane();
			textArea1 = new JTextArea();
			questComboBox = new JComboBox();

			//======== this ========
			setTitle("Delta 07 Quester");
			Container contentPane = getContentPane();
			contentPane.setLayout(null);

			//---- okButton ----
			okButton.setText("START");
			okButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					okButtonActionPerformed(e);
				}
			});
			contentPane.add(okButton);
			okButton.setBounds(333, 15, 104, 34);

			//======== scrollPane1 ========
			{

				//---- textArea1 ----
				textArea1.setText("Please select a quest from above.\n\nMake sure you have only items you need in your\ninventory");
				textArea1.setLineWrap(true);
				textArea1.setEditable(false);
				scrollPane1.setViewportView(textArea1);
			}
			contentPane.add(scrollPane1);
			scrollPane1.setBounds(3, 54, 437, 173);

			//---- questComboBox ----
			questComboBox.setModel(new DefaultComboBoxModel(new String[] {
				"The Restless Ghost",
				"Rune Mysteries",
				"Cook's Assistant",
				"Waterfall"
			}));
			questComboBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					questComboBoxActionPerformed(e);
				}
			});
			contentPane.add(questComboBox);
			questComboBox.setBounds(12, 19, 308, 30);

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < contentPane.getComponentCount(); i++) {
					Rectangle bounds = contentPane.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = contentPane.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				contentPane.setMinimumSize(preferredSize);
				contentPane.setPreferredSize(preferredSize);
			}
			pack();
			setLocationRelativeTo(getOwner());
			// JFormDesigner - End of component initialization  //GEN-END:initComponents
		}

		// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
		// Generated using JFormDesigner Evaluation license - chris gofih
		private JButton okButton;
		private JScrollPane scrollPane1;
		private JTextArea textArea1;
		private JComboBox<String> questComboBox;
		// JFormDesigner - End of variables declaration  //GEN-END:variables
	}


*/

}
