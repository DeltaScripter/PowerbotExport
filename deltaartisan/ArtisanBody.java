package deltaartisan;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.SwingUtilities;

import org.powerbot.event.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.Skills;
import org.powerbot.script.util.Random;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.Tile;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import deltaartisan.ArtisanData.itemIndex;
import deltaartisan.ArtisanData.itemNames;
import deltaartisan.ArtisanData.itemNamesIron;
import deltaartisan.ArtisanData.itemNamesSteel;
import deltaartisan.ArtisanData.locations;
import deltaartisan.ArtisanData.objectNames;



@org.powerbot.script.Manifest(authors = { "Delta Scripter" }, name = "Delta Artisan", 
description = "Gains smithing exp by making & depositing rail parts in the Artisan Workshop, start script in rail area.", 
website = "http://www.powerbot.org/community/topic/1130769-delta-artisan-workshop/", version = 0.13, hidden = false)
public class ArtisanBody extends PollingScript implements PaintListener{

	public ArtisanBody(){
		getExecQueue(State.START).add(new Runnable() {
			@Override
			public void run() {
				initialExp = ctx.skills.getExperience(Skills.SMITHING);
				runtime = new Timer(0);
				secondsA = new Timer(0);
				minutesA = new Timer(0);
				initiateGUI();
				if(new Tile(3064,9709,0).getMatrix(ctx).isReachable())//basement
					makeBurialArmour = false;
				else makeBurialArmour = true;
				
				   addNode(new burialArmor(ctx));
				   addNode(new SmithRail(ctx));
				   addNode(new Deposit(ctx));
				   addNode(new ArtisanAntipattern(ctx));
			}
		});
	}
	
	private final List<ArtisanNode> nodeList = Collections.synchronizedList(new ArrayList<ArtisanNode>());
	ArtisanMethod m = new ArtisanMethod(ctx);
	public static String state;
	public boolean start = false;
	public static boolean antiPattern;
	public int smithType;
	private Timer runtime;
	private Timer secondsA;
	private Timer minutesA;
	public static int initialExp;
	public static int expGained;
	public static int expPerHr;
	public static boolean makeBurialArmour = true;
	
	public static boolean goSmith = false;
	public static boolean deposit = true;
	
	public static String troughType;
	public static String ingotType;
	public static boolean isPolyItem;
	public static String singleItem;//item name, used for multi item and single item
	public static int singleItemIndex;
	//poly item stuff below
	public static String polyItem;//item name for end product of using 3 ingredients
	public static int polyItemIndex;
	public static String ingredientThree;//for making polyitem
	public static int ingredientThreeIndex;
	
	public static String ingredientOne;//for making multiitem
	public static int ingredientOneIndex;
	public static int ingredientTwoIndex;
	public static String ingredientTwo;
	public static String ingredientGTwo;//because the selecting item interaface needs to be general
	public static String ingredientGOne;
	public static String ingredientGThree;
	public static String singleGItem;
	public static String polyGItem;
	
	
	
	@Override
	public int poll() {
		if(ctx.camera.getPitch()<50)
			ctx.camera.setPitch(90);
		
		if(start)
		for(ArtisanNode node: nodeList){
			if(node.activate()){
				node.execute();
			}
		}
		
		return 400;
	}
	private void shutdown() {
		System.out.println("Now shutting down");
		getController().stop();
	}

	   private void addNode(final ArtisanNode...nodes) {
		   
	        for(ArtisanNode node : nodes) {
	            if(!this.nodeList.contains(node)) {
	                this.nodeList.add(node);
	            }
	        }
	    }
	
	   private void calcAntipattern() {
			int number = Random.nextInt(0, 2);
			if(number == 1){
				antiPattern = true;
			}
			
		}
	 		class Deposit extends ArtisanNode{

	 			
	 			public Deposit(MethodContext ctx) {
	 				super(ctx);
	 			}

	 			Timer wait = new Timer(0);
	 			@Override
	 			public boolean activate() {
	 				return !goSmith && deposit && !makeBurialArmour;
	 			}

	 			@Override
	 			public void execute() {
	 				deposit();
	 			}

				private void deposit() {
					while (ctx.widgets.get(1370,38).isValid() && ctx.widgets.get(1370,38).getText().contains("Smith")){//make items screen
						ctx.widgets.get(1370,31).click();//close button
					}
					if(m.inventoryContains(ingotType)){
						deposit = false;//stops this class from running
						goSmith = true;//Will start smithing the ingots
					}
					  
							if((m.inventoryContains(singleItem) || m.inventoryContains(polyItem))
									&& !isPolyItem){
						if(!wait.isRunning())
					if(locations.BYMINECARTS.getTile().distanceTo(ctx.players.local().getLocation())<6){
						state = "Depositing material in mine cart";
						m.interactO("Mine cart","Deposit-components", "Minecart");
						wait = new Timer(Random.nextInt(1400, 3000));
					}else {
						state = "Walking to mine carts";
						m.clickOnMap(locations.BYMINECARTS.getTile());
					}
					}else if(locations.BYTHREEANVILS.getTile().distanceTo(ctx.players.local().getLocation())<4){
						if(!wait.isRunning())
						if(ctx.widgets.get(1370,38).isValid()){//Take option for ingots in pile
							state = "Taking ingots";
							ctx.widgets.get(1370,38).click();//take option
							wait = new Timer(Random.nextInt(1800, 2500));
						}else {
							state = "Selecting ingot pile";
							m.interactO(troughType, "Take-ingots", "Ingot pile");
							wait = new Timer(Random.nextInt(1800, 2500));
						}
					}else{
						state = "Walking to ingot pile";
						m.clickOnMap(new Tile(3063, 9708, 0));// tile by aingot pile
					}
						
				}

	 		}
	 		
		class SmithRail extends ArtisanNode{

			
			public SmithRail(MethodContext ctx) {
				super(ctx);
			}

			Timer wait = new Timer(0);
			@Override
			public boolean activate() {
				return goSmith && !makeBurialArmour;
			}

			@Override
			public void execute() {
				m.calcExpHr();
				while (ctx.widgets.get(1370,38).isValid() && ctx.widgets.get(1370,38).getText().contains("Take")){//make items screen
					ctx.widgets.get(1370,31).click();//close button
				}
				switch(smithType){
				case 0:
					smithSingleItem();
					break;
				case 1:
					smithMultiItem();
					break;
				case 2:
					smithPolyItem();
					break;
				}
				
			}

			private void smithPolyItem() {
				if(!wait.isRunning())
				if(!m.inventoryContains(ingotType) && !m.inventoryContains(ingredientThree) &&
						m.inventoryContains(singleItem) || m.inventoryContains(polyItem)){//if it doesn't have the first ingrediant, it made the end product item.
					deposit = true;
					System.out.println("Turning smtih off");
					goSmith = false;
				}
				if(m.inventoryContains(polyItem)){//Track 60%
					System.out.println("Setting poly item to false");
					isPolyItem = false;
				}else isPolyItem = true;//will need to be changed to something indicating that were making a poly item in the GUI (a boolean variable?)
				if (ctx.players.local().getAnimation() != -1) {
					state = "Smithing..";
					wait = new Timer(5000);
				} else
				if(m.inventoryContains(singleItem)){//track 40%
					if(!wait.isRunning())
					if(m.inventoryContains(ingredientThree)){
						state = "Making final product: " + singleItem;
						smithSelect(polyGItem, polyItemIndex);
					}else {
						System.out.println("Making item: "+ingredientThree);
						if(!wait.isRunning())
						smithSelect(ingredientGThree, ingredientThreeIndex);
					}
				}else if(m.inventoryContains(ingredientTwo) && m.inventoryContains(ingredientOne)){
					System.out.println("Making multi-item product: "+singleItem);
						if(!wait.isRunning())
						smithSelect(singleItem, singleItemIndex);
				}else if (!wait.isRunning())
					if (!m.inventoryContains(ingredientOne)) {
						System.out.println("Making: "+ingredientOne);
					smithSelect(ingredientOne, ingredientOneIndex);
				} else{
					System.out.println("Making: "+ingredientTwo);
					smithSelect(ingredientTwo, ingredientTwoIndex);
				}
				
			}

			private void smithMultiItem() {
				calcAntipattern();
				//System.out.println("Ing 1: " + ingredientOne);
				//System.out.println("Ing 2: " + ingredientTwo);
				if(!m.inventoryContains(ingotType) && (!m.inventoryContains(ingredientOne) || 
						!m.inventoryContains(ingredientTwo))){//if it doesn't have the first ingrediant, it made the end product item.
					deposit = true;
					System.out.println("Turning smtih off");
					goSmith = false;
				}
			if (ctx.players.local().getAnimation() != -1) {
				state = "Smithing...";
				wait = new Timer(5000);
			} else if(m.inventoryContains(ingredientTwo) && m.inventoryContains(ingredientOne)){
				
					if(!wait.isRunning()){state = "Making end-product: "+singleGItem;
					smithSelect(singleGItem, singleItemIndex);}
			}else if (!wait.isRunning())
				if (!m.inventoryContains(ingredientOne)) {
					state = "Making " + ingredientOne;
				smithSelect(ingredientGOne, ingredientOneIndex);
			} else{
				state = "Making " + ingredientTwo;
				smithSelect(ingredientGTwo, ingredientTwoIndex);
			}
			}

			private void smithSelect(String string, int index) {
				if(ctx.widgets.get(1370,56).isValid()){
					if(ctx.widgets.get(1370,56).getText().contains(string)){
						ctx.widgets.get(1370,38).click();//Click the smith button.
						ctx.game.sleep(1200);
						wait = new Timer(Random.nextInt(2500, 3000));
					}else if(ctx.widgets.get(1371,44).isValid()){//type of smith screen
					ctx.widgets.get(1371,44).getChild(index).click();//item to make option
					wait = new Timer(Random.nextInt(500, 1000));
				}
				}else if(!wait.isRunning()){
					m.interactO("Anvil", "Smith", "Anvil");
					wait = new Timer(2000);
				}
				
			}

			private void smithSingleItem() {
				
				if(!m.inventoryContains(ingotType)){
					deposit = true;
					goSmith = false;
				}
				if (ctx.players.local().getAnimation() != -1) {
					state = "Smithing...";
					wait = new Timer(5000);
				}
				if(locations.BYTHREEANVILS.getTile().distanceTo(ctx.players.local().getLocation())<7){//by anvils
				//make the single item
					state = "Making " + singleGItem;
					if(!wait.isRunning())
					smithSelect(singleGItem, singleItemIndex);
			} else {
				state = "Walking to anvils";
				m.clickOnMap(new Tile(3063, 9708, 0));// tile by anvils
				ctx.game.sleep(Random.nextInt(100, 2000));
			}

		}

			
		}	
		class DeltaArtisanGUI extends JFrame {
			public DeltaArtisanGUI() {
				initComponents();
			}

			private void setMake(String troughTy, String ingotTy, String singleItemNam, int singleItemID) {
				troughType = troughTy;
				ingotType = ingotTy;
				singleItem = singleItemNam;
			    singleItemIndex = singleItemID;
			}
			private void setMake(String troughTy, String ingotTy, String singleItemNam, int singleItemID, String ingOne,
					int ingOneID,int ingTwoID, String ingTwo) {
				troughType = troughTy;
				ingotType = ingotTy;
				singleItem = singleItemNam;
			    singleItemIndex = singleItemID;
			    ingredientOne = ingOne;//for making multiitem
				ingredientOneIndex = ingOneID;
				ingredientTwoIndex = ingTwoID;
				ingredientTwo = ingTwo;
			}
			private void setBurialMake(int id, String string) {
				burialArmor.itemToMakeB = id;
				burialArmor.itemToMakeBName = string;
				
			}
			private void startButtonActionPerformed(ActionEvent e) {
				String chosenIngot = ingotTypeCombo.getSelectedItem().toString();
				String chosenItem = itemToMakeCombo.getSelectedItem().toString();
				String chosenIngotBurial =ingotTypeBurialCombo.getSelectedItem().toString();
				String chosenArmourBurial =burialArmourTypeCombo.getSelectedItem().toString();
				
				//Below is for the Burial armour!!!!
				if(chosenArmourBurial.equals("Helm")){
					if(chosenIngotBurial.contains("Steel"))
					setBurialMake(itemIndex.STEELHELM.getID(),"helm (steel)");
					if(chosenIngotBurial.contains("Iron"))
						setBurialMake(itemIndex.IRONHELM.getID(),"helm (iron)");
				}
				if(chosenArmourBurial.equals("Boots")){
					if(chosenIngotBurial.contains("Steel"))
					setBurialMake(itemIndex.STEELBOOTS.getID(),"boots (steel)");
					if(chosenIngotBurial.contains("Iron"))
						setBurialMake(itemIndex.IRONBOOTS.getID(),"boots (iron)");
				}
				if(chosenArmourBurial.equals("Gauntlets")){
					if(chosenIngotBurial.contains("Steel"))
					setBurialMake(itemIndex.STEELGAUNTLETS.getID(),"gauntlets (steel)");
					if(chosenIngotBurial.contains("Iron"))
						setBurialMake(itemIndex.IRONGAUNTLETS.getID(),"gauntlets (iron)");
				}
				if(chosenArmourBurial.equals("Chestplate")){
					if(chosenIngotBurial.contains("Steel"))
					setBurialMake(itemIndex.STEELCHESTPLATE.getID(),"chestplate (steel)");
					if(chosenIngotBurial.contains("Iron"))
						setBurialMake(itemIndex.IRONCHESTPLATE.getID(),"chestplate (iron)");
				}
				//(burial) directly below is for setting the ingot types
				if(chosenIngotBurial.equals("Iron ingot i")){
					burialArmor.typeOfIngot = "Iron ingot i";
					burialArmor.typeOfIngotIndex = itemIndex.IRONINGOT.getID();
				}
				if(chosenIngotBurial.equals("Iron ingot ii")){
					burialArmor.typeOfIngot = "Iron ingot ii";
					burialArmor.typeOfIngotIndex = itemIndex.IRONINGOT.getID();
				}
				if(chosenIngotBurial.equals("Iron ingot iii")){
					burialArmor.typeOfIngot = "Iron ingot iii";
					burialArmor.typeOfIngotIndex = itemIndex.IRONINGOT.getID();
				}
				if(chosenIngotBurial.equals("Steel ingot i")){
					burialArmor.typeOfIngot = "Steel ingot i";
					burialArmor.typeOfIngotIndex = itemIndex.STEELINGOT.getID();
				}
				if(chosenIngotBurial.equals("Steel ingot ii")){
					burialArmor.typeOfIngot = "Steel ingot ii";
					burialArmor.typeOfIngotIndex = itemIndex.STEELINGOT.getID();
				}
				if(chosenIngotBurial.equals("Steel ingot iii")){
					burialArmor.typeOfIngot = "Steel ingot iii";
					burialArmor.typeOfIngotIndex = itemIndex.STEELINGOT.getID();
				}
				
				
				//Below is for the rails!!!!
				if(chosenItem.equals("rails")){
					smithType = 0;
					  singleGItem = "rails";
					if(chosenIngot.equals("Bronze")){
						setMake(objectNames.BRONZETROUGH.getName(),  "Bronze ingot i",itemNames.BRONZERAILS.getName(),itemIndex.BRONZERAIL.getID());
					}else if(chosenIngot.equals("Iron")){
						setMake(objectNames.IRONTOUGH.getName(),  "Iron ingot i",itemNamesIron.BRONZERAILS.getName(),itemIndex.BRONZERAIL.getID());
					}else if(chosenIngot.equals("Steel")){
						setMake(objectNames.STEELTOUGH.getName(),"Steel ingot i",itemNamesSteel.BRONZERAILS.getName(), itemIndex.BRONZERAIL.getID());
					}
				}else if(chosenItem.equals("base plate")){
					smithType = 0;
					   singleGItem = "base plate";
					if(chosenIngot.equals("Bronze")){
						setMake(objectNames.BRONZETROUGH.getName(), "Bronze ingot i",itemNames.BRONZEBASEPLATE.getName(),itemIndex.BRONZEBASEPLATE.getID());
					 
						isPolyItem = false;
					}else if(chosenIngot.equals("Iron")){
						setMake(objectNames.IRONTOUGH.getName(),"Iron ingot i",itemNamesIron.BRONZEBASEPLATE.getName(),itemIndex.BRONZEBASEPLATE.getID());
						isPolyItem = false;
						
					}else if(chosenIngot.equals("Steel")){
						setMake(objectNames.STEELTOUGH.getName(),"Steel ingot i",itemNamesSteel.BRONZEBASEPLATE.getName(),itemIndex.BRONZEBASEPLATE.getID());
						isPolyItem = false;
					}
				}else if(chosenItem.equals("track 40%")){
						smithType = 1;
					   singleGItem = "track 40%";
					   ingredientGOne = "rails";
					   ingredientGTwo = "base plate";
					if(chosenIngot.equals("Bronze")){
						setMake(objectNames.BRONZETROUGH.getName(),"Bronze ingot i",itemNames.BRONZETRACK.getName(),itemIndex.BRONZETRACK.getID(),
								itemNames.BRONZERAILS.getName(),itemIndex.BRONZERAIL.getID(),itemIndex.BRONZEBASEPLATE.getID(),itemNames.BRONZEBASEPLATE.getName());
				
					}else if(chosenIngot.equals("Iron")){
						setMake(objectNames.IRONTOUGH.getName(),"Iron ingot i",itemNamesIron.BRONZETRACK.getName(),itemIndex.BRONZETRACK.getID(),
								itemNamesIron.BRONZERAILS.getName(),itemIndex.BRONZERAIL.getID(),itemIndex.BRONZEBASEPLATE.getID(),itemNamesIron.BRONZEBASEPLATE.getName());
					
					}else if(chosenIngot.equals("Steel")){
						setMake(objectNames.STEELTOUGH.getName(),"Steel ingot i",itemNamesSteel.BRONZETRACK.getName(),itemIndex.BRONZETRACK.getID(),
								itemNamesSteel.BRONZERAILS.getName(),itemIndex.BRONZERAIL.getID(),itemIndex.BRONZEBASEPLATE.getID(),itemNamesSteel.BRONZEBASEPLATE.getName());
					
					}
				}else if(chosenItem.equals("track 60%")){
						
					
				}else if(chosenItem.equals("joint")){
					smithType = 0;
					singleGItem = "joint";
					if(chosenIngot.equals("Bronze")){
						setMake(objectNames.BRONZETROUGH.getName(),  "Bronze ingot i",itemNames.BRONSEJOINT.getName(),itemIndex.BRONZEjOINT.getID());
						
						
					}else if(chosenIngot.equals("Iron")){
						setMake(objectNames.IRONTOUGH.getName(),  "Iron ingot i",itemNamesIron.BRONSEJOINT.getName(),itemIndex.BRONZEjOINT.getID());
						
					}else if(chosenIngot.equals("Steel")){
						setMake(objectNames.STEELTOUGH.getName(),  "Steel ingot i",itemNamesSteel.BRONSEJOINT.getName(),itemIndex.BRONZEjOINT.getID());
						
					}
				}else if(chosenItem.equals("tie")){
					smithType = 0;
					singleGItem = "tie";
					if(chosenIngot.equals("Bronze")){
						setMake(objectNames.BRONZETROUGH.getName(),  "Bronze ingot i",itemNames.BRONZETIE.getName(),itemIndex.BRONZETIE.getID());
						
					}else if(chosenIngot.equals("Iron")){
						setMake(objectNames.IRONTOUGH.getName(),  "Iron ingot i",itemNamesIron.BRONZETIE.getName(),itemIndex.BRONZETIE.getID());
						
					}else if(chosenIngot.equals("Steel")){
						setMake(objectNames.STEELTOUGH.getName(),  "Steel ingot i",itemNamesSteel.BRONZETIE.getName(),itemIndex.BRONZETIE.getID());
						
					}
				}else if(chosenItem.equals("spikes")){
					smithType = 0;
					singleGItem = "spikes";
					if(chosenIngot.equals("Bronze")){
						setMake(objectNames.BRONZETROUGH.getName(),  "Bronze ingot i",itemNames.BRONZESPIKES.getName(),itemIndex.BRONZESPIKES.getID());
						
					}else if(chosenIngot.equals("Iron")){
						setMake(objectNames.IRONTOUGH.getName(),  "Iron ingot i",itemNamesIron.BRONZESPIKES.getName(),itemIndex.BRONZESPIKES.getID());
						
					}else if(chosenIngot.equals("Steel")){
						setMake(objectNames.STEELTOUGH.getName(),  "Steel ingot i",itemNamesSteel.BRONZESPIKES.getName(),itemIndex.BRONZESPIKES.getID());
						
					}
				}
				start = true;
				this.dispose();
			}
			

			private void initComponents() {
				startButton = new JButton();
				tabs = new JTabbedPane();
				panel1 = new JPanel();
				IngotTypeLabel = new JLabel();
				ingotTypeCombo = new JComboBox<String>();
				itemToMakeCombo = new JComboBox<String>();
				itemToMakeLabel = new JLabel();
				panel2 = new JPanel();
				typeofingotburiallabel = new JLabel();
				ingotTypeBurialCombo = new JComboBox<String>();
				whatotmakelabel = new JLabel();
				burialArmourTypeCombo = new JComboBox<String>();
				instructionslabel = new JLabel();

				//======== this ========
				setTitle("Delta Artisan");
				Container contentPane = getContentPane();

				//---- startButton ----
				startButton.setText("START");
				startButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						startButtonActionPerformed(e);
					}
				});

				//======== tabs ========
				{

					//======== panel1 ========
					{

						panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});


						//---- IngotTypeLabel ----
						IngotTypeLabel.setText("Ingot Type:");

						//---- ingotTypeCombo ----
						ingotTypeCombo.setModel(new DefaultComboBoxModel<String>(new String[] {
							"Bronze",
							"Iron",
							"Steel"
						}));

						//---- itemToMakeCombo ----
						itemToMakeCombo.setModel(new DefaultComboBoxModel<String>(new String[] {
								"rails","base plate","joint","tie","spikes","track 40%"
						}));

						//---- itemToMakeLabel ----
						itemToMakeLabel.setText("Item to make:");

						GroupLayout panel1Layout = new GroupLayout(panel1);
						panel1.setLayout(panel1Layout);
						panel1Layout.setHorizontalGroup(
							panel1Layout.createParallelGroup()
								.addGroup(panel1Layout.createSequentialGroup()
									.addGap(25, 25, 25)
									.addGroup(panel1Layout.createParallelGroup()
										.addGroup(panel1Layout.createSequentialGroup()
											.addComponent(itemToMakeLabel)
											.addGap(18, 18, 18)
											.addComponent(itemToMakeCombo, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE))
										.addGroup(panel1Layout.createSequentialGroup()
											.addComponent(IngotTypeLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
											.addGap(18, 18, 18)
											.addComponent(ingotTypeCombo, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)))
									.addContainerGap(127, Short.MAX_VALUE))
						);
						panel1Layout.setVerticalGroup(
							panel1Layout.createParallelGroup()
								.addGroup(panel1Layout.createSequentialGroup()
									.addGap(20, 20, 20)
									.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(IngotTypeLabel)
										.addComponent(ingotTypeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(itemToMakeLabel)
										.addComponent(itemToMakeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addContainerGap(43, Short.MAX_VALUE))
						);
					}
					tabs.addTab("Rail tracks", panel1);

					//======== panel2 ========
					{

						//---- typeofingotburiallabel ----
						typeofingotburiallabel.setText("Type of ingot:");

						//---- ingotTypeBurialCombo ----
						ingotTypeBurialCombo.setModel(new DefaultComboBoxModel<String>(new String[] {
							"Iron ingot i","Iron ingot ii","Iron ingot iii",
							"Steel ingot i", "Steel ingot ii", "Steel ingot iii"
						}));

						//---- whatotmakelabel ----
						whatotmakelabel.setText("What to make:");

						//---- burialArmourTypeCombo ----
						burialArmourTypeCombo.setModel(new DefaultComboBoxModel<String>(new String[] {
							"Helm","Boots","Gauntlets","Chestplate"
						}));

						GroupLayout panel2Layout = new GroupLayout(panel2);
						panel2.setLayout(panel2Layout);
						panel2Layout.setHorizontalGroup(
							panel2Layout.createParallelGroup()
								.addGroup(panel2Layout.createSequentialGroup()
									.addContainerGap()
									.addGroup(panel2Layout.createParallelGroup()
										.addComponent(typeofingotburiallabel, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
										.addComponent(whatotmakelabel, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addComponent(ingotTypeBurialCombo, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
										.addComponent(burialArmourTypeCombo, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
									.addContainerGap(110, Short.MAX_VALUE))
						);
						panel2Layout.setVerticalGroup(
							panel2Layout.createParallelGroup()
								.addGroup(panel2Layout.createSequentialGroup()
									.addContainerGap()
									.addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(typeofingotburiallabel)
										.addComponent(ingotTypeBurialCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(whatotmakelabel)
										.addComponent(burialArmourTypeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addContainerGap(50, Short.MAX_VALUE))
						);
					}
					tabs.addTab("Burial Armour", panel2);
				}

				//---- instructionslabel ----
				instructionslabel.setText("Choose your settings below");

				GroupLayout contentPaneLayout = new GroupLayout(contentPane);
				contentPane.setLayout(contentPaneLayout);
				contentPaneLayout.setHorizontalGroup(
					contentPaneLayout.createParallelGroup()
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGroup(contentPaneLayout.createParallelGroup()
								.addGroup(contentPaneLayout.createSequentialGroup()
									.addGap(0, 0, Short.MAX_VALUE)
									.addComponent(startButton, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
								.addGroup(contentPaneLayout.createSequentialGroup()
									.addContainerGap()
									.addGroup(contentPaneLayout.createParallelGroup()
										.addGroup(contentPaneLayout.createSequentialGroup()
											.addComponent(instructionslabel, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
											.addGap(0, 0, Short.MAX_VALUE))
										.addComponent(tabs))))
							.addContainerGap())
				);
				contentPaneLayout.setVerticalGroup(
					contentPaneLayout.createParallelGroup()
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addContainerGap(16, Short.MAX_VALUE)
							.addComponent(instructionslabel)
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(tabs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(startButton)
							.addContainerGap())
				);
				pack();
				setLocationRelativeTo(getOwner());
			}

			private JButton startButton;
			private JTabbedPane tabs;
			private JPanel panel1;
			private JLabel IngotTypeLabel;
			private JComboBox<String> ingotTypeCombo;
			private JComboBox<String> itemToMakeCombo;
			private JLabel itemToMakeLabel;
			private JPanel panel2;
			private JLabel typeofingotburiallabel;
			private JComboBox<String> ingotTypeBurialCombo;
			private JLabel whatotmakelabel;
			private JComboBox<String> burialArmourTypeCombo;
			private JLabel instructionslabel;
			// JFormDesigner - End of variables declaration  //GEN-END:variables
		}

		public void initiateGUI() {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					
					final DeltaArtisanGUI deltagui = new DeltaArtisanGUI();
					deltagui.setVisible(true);
					deltagui.setResizable(false);
				
				}
			});
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
				String expHr = "";
				runtimeHold = runtime.getElapsed();
				runtimeHold = 3600000/runtimeHold;
				expPerHr = (int)runtimeHold * expGained;
				expHr = ""+ expPerHr;
				if(expHr.length()>3)
				expHr = expHr.substring(0, expHr.length() - 3);
				
				
				mouseX = (int) ctx.mouse.getLocation().getX();
				mouseY = (int) ctx.mouse.getLocation().getY();
				int level = ctx.skills.getLevel(Skills.SMITHING);
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
				if(!makeBurialArmour){
				if(polyGItem!=null)
				g.drawString("Making: " + ingotType + " " + polyGItem, 20, 170);
				else g.drawString("Making: " + ingotType + " " + singleGItem, 20, 170);
				}else g.drawString("Making: " + burialArmor.itemToMakeBName, 20, 170);
				g.drawString("Current level: " + level, 20, 190);
				g.drawString("XP Gained: " + expGained + " XP : P/Hr(" +expHr+"K)" , 20, 210);
				
				
			}


}
