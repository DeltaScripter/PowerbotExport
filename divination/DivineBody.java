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
import org.powerbot.script.util.Timer;

import divination.DivineData.animation;
import divination.DivineData.convert;
import divination.DivineData.memories;
import divination.DivineData.rifts;
import divination.DivineData.wisps;



@org.powerbot.script.Manifest(authors = { "Delta Scripter" }, name = "DeltaDivinity", 
description = "Dances like a chicken everywhere!",hidden = true, 
website = "", version = 1)
public class DivineBody extends PollingScript implements PaintListener{

	public DivineBody(){
		getExecQueue(State.START).add(new Runnable() {
			@Override
			public void run() {
				initiateGUI();
				addNode(new harvestWisp(ctx));
				addNode(new convertMemories(ctx));
			}
		});
	}
	public final Tile[] pathToAubury = new Tile[]{new Tile(3253,3420,0),
			new Tile(3260,3417,0),new Tile(3258,3409,0),new Tile(3256,3402,0),
			new Tile(3253,3400,0)};
	private final List<DivineNode> nodeList = Collections.synchronizedList(new ArrayList<DivineNode>());
	public static String state;
	private boolean harvest= true;
	private int convertType;
	private int animationType;
	private Tile riftArea;
	private boolean start = false;
	//private Timer waiting = new Timer(0);
	
	DivineMethod Method = new DivineMethod(ctx);
	
	@Override
	public int poll() {
		
		if(start)
		for(DivineNode node: nodeList){
			if(node.activate()){
				node.execute();
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
			while(ctx.players.local().getAnimation()==animationType){
				state = "Converting memories..";
				ctx.game.sleep(8500,8700);
			}
			
			if(!Method.inventoryContains(memories.PALEMEMORY.getID())){
				harvest = true;
			}else
			if(closeToObj(riftArea,"Walking to Lummbridge rift")){
				if(ctx.widgets.get(131,convertType).isVisible()){
					ctx.widgets.get(131,convertType).click();
					ctx.game.sleep(3800,4500);
				}else Method.interactO(rifts.LUMMBRIDGE.getID(), "Convert memories", "Interacting with rift");
			}
			
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
				while(ctx.players.local().getAnimation()==animation.HARVESTINGSPRING2.getID()){
					state = "Harvesting spring";
					ctx.game.sleep(700,1500);
				}
				while(Method.backPackIsFull()){
					state = "backpack is full";
					harvest = false;
					break;
				}
				if(Method.npcIsNotNull(wisps.PALESPRING.getID())){
					if(closeToNpc(wisps.PALESPRING.getID(),"Walking to spring")){
						state = "Attempting to harvest spring";
						Method.npcInteract(wisps.PALESPRING.getID(), "Harvest");
					}
				}else
				if(Method.npcIsNotNull(wisps.PALEWISP.getID())){
					if(closeToNpc(wisps.PALEWISP.getID(),"Walking to wisp")){
						state = "Attempting to convert wisp to spring";
						Method.npcInteract(wisps.PALEWISP.getID(), "Harvest");
					}
				}else ctx.movement.findPath(riftArea).traverse();
				
			}
			
			private boolean closeToNpc(int id, String string) {
				if(Method.getNPC(id).getLocation().distanceTo(ctx.players.local().getLocation())<7){
					return true;
				}else {
					state = string;
					ctx.movement.findPath(Method.getNPC(id).getLocation()).traverse();
				}
				return false;
			}
			
		}
		private boolean closeToObj(Tile loc, String string) {
			if(loc.distanceTo(ctx.players.local().getLocation())<5){
				return true;
			}else {
				state = string;
				ctx.movement.findPath(loc).traverse();
			}
			return false;
		}
	  
private Font myFont = new Font("Consolas",Font.BOLD,14);
	@Override
	public void repaint(Graphics g) {
		g.setFont(myFont);
		g.setColor(Color.green);
		g.drawString("State: "+state, 20, 130);
		//g.drawString("Timer: " + waiting.getElapsed(), 20, 150);
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
			String riftChoice = riftLocation.getSelectedItem().toString();
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
			if(riftChoice=="Lummbridge"){
				riftArea = rifts.LUMMBRIDGE.getTile();
			}
			start = true;
			this.dispose();
		}

		private void initComponents() {
			label1 = new JLabel();
			convertList = new JComboBox<>();
			label2 = new JLabel();
			riftLocation = new JComboBox<>();
			label3 = new JLabel();
			wispType = new JComboBox<>();
			strtBtn = new JButton();

			//======== this ========
			setTitle("DeltaDivinity");
			Container contentPane = getContentPane();

			//---- label1 ----
			label1.setText("Convert memories into: ");

			//---- convertList ----
			convertList.setModel(new DefaultComboBoxModel<>(new String[] {
				"Divine Energy","Divinity Experience","Enhanced Experience"
			}));

			//---- label2 ----
			label2.setText("Location of rift: ");

			//---- riftLocation ----
			riftLocation.setModel(new DefaultComboBoxModel<>(new String[] {
				"Lummbridge"
			}));

			//---- label3 ----
			label3.setText("Wisp Type: ");

			//---- wispType ----
			wispType.setModel(new DefaultComboBoxModel<>(new String[] {
				"Pale wisp"
			}));

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
						.addContainerGap()
						.addGroup(contentPaneLayout.createParallelGroup()
							.addGroup(contentPaneLayout.createSequentialGroup()
								.addGroup(contentPaneLayout.createParallelGroup()
									.addComponent(label1)
									.addComponent(label2)
									.addComponent(label3))
								.addGap(19, 19, 19)
								.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
									.addComponent(convertList, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
									.addComponent(riftLocation, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
									.addComponent(wispType, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
								.addGap(0, 0, Short.MAX_VALUE))
							.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
								.addGap(0, 236, Short.MAX_VALUE)
								.addComponent(strtBtn)))
						.addContainerGap())
			);
			contentPaneLayout.setVerticalGroup(
				contentPaneLayout.createParallelGroup()
					.addGroup(contentPaneLayout.createSequentialGroup()
						.addGap(19, 19, 19)
						.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(convertList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label1))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(riftLocation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label2))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(wispType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label3))
						.addGap(30, 30, 30)
						.addComponent(strtBtn)
						.addContainerGap(10, Short.MAX_VALUE))
			);
			pack();
			setLocationRelativeTo(getOwner());
		}

		private JLabel label1;
		private JComboBox<String> convertList;
		private JLabel label2;
		private JComboBox<String> riftLocation;
		private JLabel label3;
		private JComboBox<String> wispType;
		private JButton strtBtn;
	}

}
