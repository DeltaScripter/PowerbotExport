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
import OldQuester.quests.OldRestlessGhost;
import OldQuester.quests.OldRuneMysteries;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;



@Script.Manifest(name = "O7 Quester", 
description = "Completes 07 quests! want more? suggest some on thread!",properties = "client=4; topic = 1231910")
public class DeltaOldQuester extends PollingScript<ClientContext> implements PaintListener{

	
	public static int FOOD_ID = 0;
	public static boolean FOOD_FEATURE = false;
	public static boolean shutOff = false;//for shutting down the script when necessary
	
	public static String state = "Welcome...";
	public static int scriptSelect = 0;
	public static ArrayList<Tile> walkingTile = new ArrayList<Tile>();
	public String questName = "Choose a quest..";
	
    private boolean start = false;//for using onStart() only once
    private boolean goOn = false;
	private final List<OldNode> nodesList = Collections.synchronizedList(new ArrayList<OldNode>());

	@Override
	public void poll() {
		
		onStart();
		if(shutOff || !ctx.game.loggedIn()){
			System.out.println("Shutting down..");
			this.ctx.controller().stop();
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
	private void onStart() {
		if(!start){
			initiateGui();
			addNode(new OldRuneMysteries(ctx));
			addNode(new OldRestlessGhost(ctx));
			addNode(new OldCooksAssistant(ctx));
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
		
		mouseX = (int) ctx.mouse.getLocation().getX();
		mouseY = (int) ctx.mouse.getLocation().getY();
		
		setMouse(g);
		   
		   
		//color tile
		if(walkingTile.size()>1){
			for(Tile tile: walkingTile){
		g.drawLine(ctx.players.local().tile().matrix(ctx).getBounds().getBoundingBox().x, ctx.players.local().tile().matrix(ctx).getBounds().getBoundingBox().y,tile.matrix(ctx).getBounds().getBoundingBox().x, tile.matrix(ctx).getBounds().getBoundingBox().y);
		//g.drawString("Hello",ctx.players.local().tile().matrix(ctx).getBounds().getBoundingBox().x, ctx.players.local().tile().matrix(ctx).getBounds().getBoundingBox().y);
		  }
		}
		g.setColor(Color.BLACK);
		g.drawString("State: " + state, 20,370);
		g.drawString("State: " + questName, 20,390);
		
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
		}

		private void questComboBoxActionPerformed(ActionEvent e) {
			System.out.println("Changed quest selection");
			if(questComboBox.getSelectedItem().toString().contains("Restless Ghost")){
				textArea1.setText("Start this script near Lumbridge or Varrock");
			}
			if(questComboBox.getSelectedItem().toString().contains("Rune Mysteries")){
				textArea1.setText("Start this script near Lumbridge or Varrock\nNo requirements except some free inventory space");
			}
			if(questComboBox.getSelectedItem().toString().contains("Cook's Assistant")){
				textArea1.setText("Quest currently under development");
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
				"Cook's Assistant"
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




}
