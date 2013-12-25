package quests;


import java.awt.*;

import quests.Node;
import quests.TaskListing.TaskListForm;

import javax.imageio.ImageIO;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;

import org.powerbot.event.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.util.Timer;


@org.powerbot.script.Manifest(authors = { "Delta Scripter" }, name = "Delta Quester", 
description = "Completes quests! See thread for supported quests", hidden = false,
website = "https://www.powerbot.org/community/topic/777386-delta-quester/",topic =816007, version = 1.6007)
public class DeltaQuester extends PollingScript implements PaintListener{


	public static int scriptToStart = 0;
	public static String state = "Welcome, please choose your settings";
	public static boolean GEFeature = false;
	public boolean useBank;
	public static boolean ranOnce = false;
	public static boolean checkedBank = false;//if cache bank initially
	public boolean GEWO = false;
	public static boolean FOOD_FEATURE = false;
	public static int number;
	public static boolean bankFound = false;
	public static boolean e = false;//The intermediate between quests, resets variables
	
	//for paint
	public static int progress = 0;
	public static int numSteps = 0;
	
	public static int FOOD_ID = 0;
	public static double health;
	private static int mouseX = 0;
	private static int mouseY = 0;
	private static DeltaQuester instance = null;
    private static Vars variable = new Vars();
	
	
	//Settings
	public static ArrayList<String> qList = new ArrayList<String>();

	public static int paintIndicator= 0;
	public static boolean exchangeBank = false;
	public static boolean g = false;
    public Timer time;
    public Timer timeSec;//seconds
	public int minute;
	public double second;
	private boolean ready = false;
   // private JobContainer container = null;
	private final List<Node> nodesList = Collections.synchronizedList(new ArrayList<Node>());

	
   
	public DeltaQuester() {
		
		getExecQueue(State.START).add(new Runnable() {
			@Override
			public void run() {
				timeSec = new Timer(0);
				
				initiateGui();
				//qList.add("The Restless Ghost");
				     addNode(new TheBloodPact(ctx));
					 addNode(new CooksAssistant(ctx));
					 addNode(new RestlessG(ctx));
					 addNode(new DeathPlateau(ctx));
					 addNode(new ImpCatcher(ctx));
					 addNode(new StolenHearts(ctx));
					 addNode(new WolfWhistle(ctx));
					 addNode(new DemonSlayer(ctx));
					 addNode(new LetThemEatPie(ctx));
					 addNode(new PiratesTreasure(ctx));
					 addNode(new SweptAway(ctx));
					 addNode(new DruidicRitual(ctx));
					 addNode(new VampyreSlayer(ctx));
					 addNode(new ElementalWorkshop1(ctx));
					 addNode(new MineIsYours(ctx));
					 addNode(new GoblinDiplomacy(ctx));
					 addNode(new GunnarsGround(ctx));
					 addNode(new LostCity(ctx));
					 addNode(new BuyersAndCellars(ctx));
					 addNode(new ClockTower(ctx));
					 addNode(new MonksFriend(ctx));
					 addNode(new PlagueCity(ctx));
			}
		});
		getExecQueue(State.STOP).add(new Runnable() {
			@Override
			public void run() {
				log.info("Now shutting down.");
				TaskListing.taskRemove.clear();//Clears the task list
				TaskListing.updateTasks();
				qList.clear();
				GEWO = false;
				GEFeature = false;
				scriptToStart = 100;
				Vars.useBank = false;
				Vars.ranOnce = false;
				checkedBank = false;
				Method.depoBank = false;
				Method.hasFood = true;
				ready  =false;
			}
		});
	}
	   private void addNode(final Node...nodes) {
		   
	        for(Node node : nodes) {
	            if(!this.nodesList.contains(node)) {
	                this.nodesList.add(node);
	            }
	        }
	    }


	public int poll() {
		
		if(g){
			log.info("shutting down");
			TaskListing.taskRemove.clear();//Clears the task list
			TaskListing.updateTasks();
			getController().stop();
		}
		while(ctx.widgets.get(1223,1).isVisible()){//Task completed dialogue
			System.out.println("Closing TASK COMPLETE");
			state = "Closing task complete dialogue";
			ctx.widgets.get(1223,11).getChild(1).click();//close button
		}
		if(ready){
		for(Node node : nodesList) {
            if(node.activate()) {
                node.execute();
           }
       }
		 if(e){
			 //Resets progress bar
				progress = 0;
				numSteps = 0;
			 //Resets variables then reinitiates the queue
				TaskListing.taskRemove.clear();//Clears the task list
				log.info("Finished quest: " + qList.get(0));
			    DeltaQuester.qList.remove(0);
				DeltaQuester.scriptToStart=100;
				Method.depoBank = false;
				DeltaQuester.exchangeBank = false;
				Vars.useBank = false;
				Vars.ranOnce = false;
				
				e = false;
		 }
		updateQuests();
		}
		return 100;
	}
	
	//public DeltaQuester getInstance(){
		
	//	if(instance==null){
		//	instance = new DeltaQuester();
	//	}
	//	return instance;
//	}
	
	public static Vars getVars(){
		
		if(variable==null){
			variable = new Vars();
		}
		return variable;
		
	}


	private void updateQuests() {
		Method Method = new Method(ctx);
		if ((qList.size() > 0)) {
			if (qList.get(0) == "The Restless Ghost") {
				scriptToStart=1;
			}else if (qList.get(0) == "Wolf Whistle") {
				scriptToStart=2;
			}else if (qList.get(0) == "The Black Knights Fortress") {
				scriptToStart=20;
			}else if (qList.get(0) == "Dwarf Cannon") {
				scriptToStart=3;
			}else if (qList.get(0) == "Cook's Assistant"&& scriptToStart!=4) {
				scriptToStart=4;
				if(GEWO) GEFeature = true;
			}else if (qList.get(0) == "Swept Away") {
				scriptToStart=5;
			}else if (qList.get(0) == "Rune Mysteries") {
				scriptToStart=6;
			}else if (qList.get(0) == "Gunnar's Ground"&& scriptToStart!=9) {
				scriptToStart=9;
				if(GEWO) GEFeature = true;
			}else if (qList.get(0) == "Buyers and Cellars"&& scriptToStart!=10) {
				scriptToStart=10;
				if(GEWO) GEFeature = true;
			}else if (qList.get(0) == "Ernest The Chicken") {
				scriptToStart=11;
			}else if (qList.get(0) == "Druidic Ritual"&& scriptToStart!=12) {
				scriptToStart=12;
				if(GEWO) GEFeature = true;
			}else if (qList.get(0) == "The Blood Pact") {
				scriptToStart=13;
			}else if (qList.get(0) == "The Dig Site"&& scriptToStart!=14) {
				scriptToStart=14;
				if(GEWO) GEFeature = true;
			}else if (qList.get(0) == "Gertrude's Cat"&& scriptToStart!=15) {
				scriptToStart=15;
				if(GEWO) GEFeature = true;
			}else if (qList.get(0) =="Stolen Hearts") {
				scriptToStart=8;
			}else if (qList.get(0) =="Goblin Diplomacy"&& scriptToStart!=16) {
				scriptToStart=16;
				if(GEWO) GEFeature = true;
			}else if (qList.get(0) =="Imp Catcher"&& scriptToStart!=17) {
				scriptToStart=17;
				if(GEWO) GEFeature = true;
			}else if (qList.get(0) =="Vampyre Slayer"&& scriptToStart!=18) {
				scriptToStart=18;
				if(GEWO) GEFeature = true;
			}else if (qList.get(0) =="Pirate's Treasure"&& scriptToStart!=19) {
				scriptToStart=19;
				if(GEWO) GEFeature = true;
			}else if (qList.get(0) =="Death Plateau") {
				scriptToStart=21;
			}else if (qList.get(0) =="Jungle Potion"&& scriptToStart!=22) {
				scriptToStart=22;
				if(GEWO) GEFeature = true;
			}else if (qList.get(0) =="Shilo Village"&& scriptToStart!=23) {
				scriptToStart=23;
				if(GEWO) GEFeature = true;
			}else if (qList.get(0) =="Fishing Contest"&& scriptToStart!=24) {//The extra part is to make sure we don't use the G.E twice.
				scriptToStart=24;
				if(GEWO) GEFeature = true;
			}else if (qList.get(0) =="The Knight's Sword"&& scriptToStart!=25) {//The extra part is to make sure we don't use the G.E twice.
				scriptToStart=25;
				if(GEWO) GEFeature = true;
			}else if (qList.get(0) =="Demon Slayer"&& scriptToStart!=26) {//The extra part is to make sure we don't use the G.E twice.
				scriptToStart=26;
				if(GEWO) GEFeature = true;
			}else if (qList.get(0) =="What's Mine Is Yours") {
				scriptToStart=27;
			}else if (qList.get(0) =="Lost City") {
				scriptToStart=28;
			}else if (qList.get(0) =="One Small Favour"&& scriptToStart!=29) {
				scriptToStart=29;
				if(GEWO) GEFeature = true;
			}else if (qList.get(0) =="Myths Of The White Lands"&& scriptToStart!=30) {
				scriptToStart=30;
				if(GEWO) GEFeature = true;
			}else if (qList.get(0) =="Let Them Eat Pie"&& scriptToStart!=31) {
				if(GEWO) GEFeature = true;
				    scriptToStart=31;
			}else if (qList.get(0) =="Dragon Slayer"&& scriptToStart!=32) {
				scriptToStart=32;
				if(GEWO) GEFeature = true;
			}else if (qList.get(0) =="Elemental Workshop 1"&& scriptToStart!=33) {
				scriptToStart=33;
				if(GEWO) GEFeature = true;
			}else if (qList.get(0) =="Hazeel Cult"&& scriptToStart!=34) {
				scriptToStart=34;
				if(GEWO) GEFeature = true;
			}else if (qList.get(0) =="Priest In The Peril"&& scriptToStart!=35) {
				scriptToStart=35;
				if(GEWO) GEFeature = true;
			}else if (qList.get(0) =="Clock Tower"&& scriptToStart!=36) {
				scriptToStart=36;
				if(GEWO) GEFeature = true;
			}else if (qList.get(0) =="Monk's Friend"&& scriptToStart!=37) {
				scriptToStart=37;
				if(GEWO) GEFeature = true;
			}else if (qList.get(0) =="Plague City"&& scriptToStart!=38) {
				scriptToStart=38;
				if(GEWO) GEFeature = true;
			}
			
		}else if(ready && qList.isEmpty()){
			Method.state("There are no quests in queue; shutting down.");
			getController().stop();
		}
		
	}
	class DeltaQuesterGUI extends JFrame {
		
		public DefaultListModel<String> queueListModel = new DefaultListModel<String>();
		public DefaultListModel<?> questListModel = new DefaultListModel<Object>();
		public DefaultListModel<String> rewardItemModel = new DefaultListModel<String>();
		public DefaultListModel<String> rewardExpModel = new DefaultListModel<String>();
		 Color color=new Color(255,0,0);
		public DeltaQuesterGUI() {
			rewardItemModel.addElement("");
			initComponents();
			setCompletedQuests();
		}

		private void setCompletedQuests() {
		questList.setCellRenderer(new MyListRenderer());
			
			questList.setModel(new AbstractListModel<String>() {
				String[] values = {
						"Buyers and Cellars","Cook's Assistant","Clock Tower","Death Plateau","Demon Slayer","Druidic Ritual",
						"Gunnar's Ground",
						"Imp Catcher","Let Them Eat Pie","Monk's Friend","Pirate's Treasure","Stolen Hearts","Swept Away",
						"The Restless Ghost","What's Mine Is Yours","Wolf Whistle","Vampyre Slayer"
				};
				
				@Override
				public int getSize() { return values.length; }
				@Override
				public String getElementAt(int i) { return values[i]; }
			});
		}

		private void addQuestActionPerformed(ActionEvent e) {
			String selected = questList.getSelectedValue();
			 
			 if(!queueListModel.contains(selected)){
			 queueListModel.addElement(selected);
			 queueList.setModel(queueListModel);
			 }
			 setRequirements();
			 setRewards();
		}

		private void removeQuestActionPerformed(ActionEvent e) {
			String selected = queueList.getSelectedValue();
			 System.out.println(selected);
			 queueListModel.removeElement(selected);
			 queueList.setModel(queueListModel);
			 setRewards();
			 setRequirements();
		}

		private void clearAllActionPerformed(ActionEvent e) {
			queueListModel.clear();
			queueList.setModel(queueListModel);
			 setRewards();
			setRequirements();
		}

		private void startButtonActionPerformed(ActionEvent e) {
			
			for(int index = 0; index <queueListModel.size();){
				String quest = queueListModel.get(index).toString();
			if(!qList.contains(quest)){
				System.out.println("Added quest: "+quest);
				qList.add(quest);
			}else index++;
			}
			if(foodSupportCheckBox.isSelected())
				FOOD_FEATURE = true;
			if(GESupportCheckBox.isSelected()){
				GEWO = true;
				GEFeature = true;
			}

			
			setFood();
			this.setVisible(false);
			ready = true;
			this.dispose();
		}

		private void setFood() {
			String foodType = typeOfFoodComboBox.getSelectedItem().toString();
			if(foodType.equals("Cooked meat")){
				FOOD_ID = Vars.foodtype.MEAT.getFood();
			}
			if(foodType.equals("Cooked lobster")){
				FOOD_ID = Vars.foodtype.LOBSTER.getFood();
			}
			if(foodType.equals("Swordfish")){
				FOOD_ID = Vars.foodtype.SWORDFISH.getFood();
			}
			if(foodType.equals("Bread")){
				FOOD_ID = Vars.foodtype.BREAD.getFood();
			}
			if(foodType.equals("Cooked Chicken")){
				FOOD_ID = Vars.foodtype.CHICKEN.getFood();
			}
			if(foodType.equals("Banana")){
				FOOD_ID = Vars.foodtype.BANANA.getFood();
			}
			if(foodType.equals("Rabbit sandwich")){
				FOOD_ID = Vars.foodtype.RABBITSANDWICH.getFood();
			}
			
			
		}
		private void setRewards() {
			
			rewardItemModel.clear();
			rewardExpModel.clear();
			for(int index = 0; index< queueListModel.size();){
				String quest = queueListModel.get(index).toString();
				System.out.println(queueListModel.get(index).toString());
				
			
				if(quest == "Druidic Ritual"){
					rewardExpModel.addElement("Herblore XP: 250");
					rewardItemModel.addElement("Grimy Guam x 15");
					rewardItemModel.addElement("Eye of Newt x 15");
					index++;
				}
				if(quest == "Cook's Assistant"){
					rewardExpModel.addElement("Cooking XP: 300");
					rewardItemModel.addElement("Sardines x 20");
					rewardItemModel.addElement("Coins x 500");
					index++;
				}else if(quest == "Clock Tower"){
					rewardItemModel.addElement("Coins x 500");
					index++;
				}else if(quest == "Death Plateau"){
					rewardItemModel.addElement("100xp Reward lamps x 3");
					index++;
				}else if(quest == "Demon Slayer"){
					rewardItemModel.addElement("Silverlight x 1");
					rewardItemModel.addElement("100xp Combat lamp x 3");
					index++;
				}else if(quest == "Imp Catcher"){
					rewardExpModel.addElement("Magic XP: 875");
					rewardItemModel.addElement("Amulet of Accuracy x 1");
					index++;
				}else if(quest == "Let Them Eat Pie"){
					rewardExpModel.addElement("Cooking XP: 100");
					rewardExpModel.addElement("Thieving XP: 150");
					rewardItemModel.addElement("Spice pouch x 1");
					rewardItemModel.addElement("Meat Pie x 5");
					index++;
				}else if(quest == "Pirate's Treasure"){
					rewardItemModel.addElement("One-Eyed Hector's Treasure x 1");
					index++;
				}else if(quest == "Swept Away"){
					rewardItemModel.addElement("A broom x 1");
					index++;
				}else if(quest == "The Blood Pact"){
					rewardExpModel.addElement("Attack XP: 100");
					rewardExpModel.addElement("Strength XP: 100");
					rewardExpModel.addElement("Defence XP: 100");
					rewardExpModel.addElement("Magic XP: 100");
					index++;
				}else if(quest == "The Restless Ghost"){
					rewardExpModel.addElement("Prayer XP: 125");
					rewardItemModel.addElement("Ancient bones x 5");
					index++;
				}else if(quest == "What's Mine Is Yours"){
					rewardItemModel.addElement("Gofannon amulet x 1");
					rewardItemModel.addElement("Coins x 180");
					index++;
				}else if(quest == "Wolf Whistle"){
					rewardExpModel.addElement("Summoning XP: 276");
					rewardItemModel.addElement("Gold charms x 275");
					index++;
				}else if(quest == "Vampyre Slayer"){
					rewardExpModel.addElement("Attack XP: 4825");
					index++;
				}else if(quest == "Monk's Friend"){
					rewardItemModel.addElement("Law Runes x 8");
					rewardExpModel.addElement("Woodcutting XP: 2000");
					index++;
				}else if(quest == "Stolen Hearts"){
					rewardItemModel.addElement("Coins x 2500");
					rewardItemModel.addElement("250XP Combat lamp x 1");
					rewardItemModel.addElement("250XP Agility lamp x 1");
					rewardItemModel.addElement("250XP Thieving lamp x 1");
					rewardExpModel.addElement("Constitution XP: 250");
					index++;
				}else if(quest == "Gunnar's Ground"){
					rewardItemModel.addElement("Antique lamp x 1");
					rewardItemModel.addElement("Swanky boots x 1");
					rewardExpModel.addElement("Crating XP: 300");
					index++;
				}else index++;
			}
			rewardItemList.setModel(rewardItemModel);
			rewardExpList.setModel(rewardExpModel);
			
		}
		private void setRequirements() {
			requirementsList.setText("");
			for(int index = 0; index < queueListModel.size();){
				String quest = queueListModel.get(index).toString();
				
				if(quest == "Druidic Ritual"){
					requirementsList.setText(requirementsList.getText() + quest + ":\n-5gp\n-Vial x 1\n-Fishing rod x 1\n-Chisel x 1\n\n");
					index++;
				}else if(quest == "Cook's Assistant"){
					requirementsList.setText(requirementsList.getText() + quest + ":\n-Wheat x 1\n-Empty pot x1\n-Bucket x 1\nIt is recommended not to already have flour made at the Lumbridge mill\n\n");
					index++;
				}else if(quest == "Clock Tower"){
					requirementsList.setText(requirementsList.getText() + quest + ":\n-Bucket of water x 1\nArdougne lodestone activated\nDo not start script in a cave\n\n");
					index++;
				}else if(quest == "Death Plateau"){
					requirementsList.setText(requirementsList.getText() + quest + ":\nThere are no requirements for this quest.\nYou will face a lvl 4 enemy\n\n");
					index++;
				}else if(quest == "Demon Slayer"){
					requirementsList.setText(requirementsList.getText() + quest + ":\nYou need the Varrok lodestone activated.\nAbility to defeat about ten lvl 2 enemies and one level 15.\nFood support recommended for low-levels.\nDo not start the script in a cave\n\n");
					index++;
				}else if(quest == "Imp Catcher"){
					requirementsList.setText(requirementsList.getText() + quest + ":\n-Black bead x 1\n-Red bead x 1\n-Yellow bread x 1\n-White bead x 1\n\n");
					index++;
				}else if(quest == "Let Them Eat Pie"){
					requirementsList.setText(requirementsList.getText() + quest + ":\n-Empty pot x 1\n-Fishing bait x 1\n-Wheat x 1\n-Raw potato x 1\n-Raw crayfish x 1\n\n");
					index++;
				}else if(quest == "Pirate's Treasure"){
					requirementsList.setText(requirementsList.getText() + quest + ":\n-500gp\n-White apron x 1\n-Spade x 1\nAtleast 10 free inventory spaces\nAbility to defeat a lvl 3 farmer\nFalador Lodestone teleport activated\n\n");
					index++;
				}else if(quest == "Swept Away"){
					requirementsList.setText(requirementsList.getText() + quest + ":\nYou may encounter a level 6 enemy\n\n");
					index++;
				}else if(quest == "The Blood Pact"){
					requirementsList.setText(requirementsList.getText() + quest + ":\nDo not start this quest underground\nYou will need a ranged weapon equipped\nAbility to defeat 3 level 3 enemies\n\n");
					index++;
				}else if(quest == "The Restless Ghost"){
					requirementsList.setText(requirementsList.getText() + quest + ":\nThere are no requirements for this quest\n\n");
					index++;
				}else if(quest == "What's Mine Is Yours"){
					requirementsList.setText(requirementsList.getText() + quest + ":\nFalador lodestone activated\nLevel 5 smithing\n\n");
					index++;
				}else if(quest == "Wolf Whistle"){
					requirementsList.setText(requirementsList.getText() + quest + ":\nThere are no requirements for this quest\n\n");
					index++;
				}else if(quest == "Vampyre Slayer"){
					requirementsList.setText(requirementsList.getText() + quest + ":\n-Garilic x 1(optional)\n-Beer x 3\nVarrock lodestone must be activated\nAbility to defeat a level 37 Vampyre\n\n");
					index++;
				}else if(quest == "Monk's Friend"){
					requirementsList.setText(requirementsList.getText() + quest + ":\n-Jug of water x 1\n-Logs x 1\nArdougne lodestone activated\nYou will come into contact with two level 30 thieves\n\n");
					index++;
				}else if(quest == "Stolen Hearts"){
					requirementsList.setText(requirementsList.getText() + quest + ":\nYou must defeat three level 2 enemies\n\n");
					index++;
				}else if(quest == "Gunnar's Ground"){
					requirementsList.setText(requirementsList.getText() + quest + ":\nLevel 5 Crafting\n\n");
					index++;
				}else index++;
			}
		}
		private void initComponents() {
			panel1 = new JPanel();
			tabbedPane1 = new JTabbedPane();
			panel2 = new JPanel();
			scrollPane1 = new JScrollPane();
			questList = new JList<String>();
			scrollPane2 = new JScrollPane();
			queueList = new JList<String>();
			questListLabel = new JLabel();
			questQueueLabel = new JLabel();
			addQuest = new JButton();
			removeQuest = new JButton();
			clearAll = new JButton();
			panel5 = new JPanel();
			scrollPane6 = new JScrollPane();
			requirementsList = new JTextArea();
			requirementsLabel = new JLabel();
			panel3 = new JPanel();
			scrollPane3 = new JScrollPane();
			rewardItemList = new JList<String>();
			itemLabel = new JLabel();
			scrollPane4 = new JScrollPane();
			rewardExpList = new JList<String>();
			expLabel = new JLabel();
			panel4 = new JPanel();
			foodSupportCheckBox = new JCheckBox();
			GESupportCheckBox = new JCheckBox();
			scrollPane7 = new JScrollPane();
			foodSupportInfoText = new JTextArea();
			typeOfFoodLabel = new JLabel();
			typeOfFoodComboBox = new JComboBox<String>();
			scrollPane8 = new JScrollPane();
			GESupportInfoText = new JTextArea();
			startButton = new JButton();

			//======== this ========
			setTitle("Delta Quester");
			Container contentPane = getContentPane();
			contentPane.setLayout(null);

			//======== panel1 ========
			{

			

				//======== tabbedPane1 ========
				{

					//======== panel2 ========
					{

						//======== scrollPane1 ========
						{

							
							questList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							
							scrollPane1.setViewportView(questList);
						}

						//======== scrollPane2 ========
						{

							//---- queueList ----
							queueList.setModel(new AbstractListModel<String>() {
								String[] values = {
									"Please add a quest"
								};
								@Override
								public int getSize() { return values.length; }
								@Override
								public String getElementAt(int i) { return values[i]; }
							});
							queueList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							
							scrollPane2.setViewportView(queueList);
						}

						//---- questListLabel ----
						questListLabel.setText("Quest List");

						//---- questQueueLabel ----
						questQueueLabel.setText("Quest Queue (To-Do List)");

						//---- addQuest ----
						addQuest.setText("-->");
						addQuest.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								addQuestActionPerformed(e);
							}
						});

						//---- removeQuest ----
						removeQuest.setText("<--");
						removeQuest.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								removeQuestActionPerformed(e);
							}
						});

						//---- clearAll ----
						clearAll.setText("Clear All");
						clearAll.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								clearAllActionPerformed(e);
							}
						});

						GroupLayout panel2Layout = new GroupLayout(panel2);
						panel2.setLayout(panel2Layout);
						panel2Layout.setHorizontalGroup(
							panel2Layout.createParallelGroup()
								.addGroup(panel2Layout.createSequentialGroup()
									.addContainerGap()
									.addGroup(panel2Layout.createParallelGroup()
										.addGroup(panel2Layout.createSequentialGroup()
											.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
											.addGroup(panel2Layout.createParallelGroup()
												.addGroup(panel2Layout.createSequentialGroup()
													.addGap(12, 12, 12)
													.addComponent(addQuest, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
												.addGroup(panel2Layout.createSequentialGroup()
													.addGap(13, 13, 13)
													.addComponent(removeQuest, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
												.addGroup(panel2Layout.createSequentialGroup()
													.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
													.addComponent(clearAll, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)))
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 12, GroupLayout.PREFERRED_SIZE))
										.addGroup(panel2Layout.createSequentialGroup()
											.addComponent(questListLabel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 248, Short.MAX_VALUE)))
									.addGroup(panel2Layout.createParallelGroup()
										.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
										.addComponent(questQueueLabel, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
									.addContainerGap())
						);
						panel2Layout.setVerticalGroup(
							panel2Layout.createParallelGroup()
								.addGroup(panel2Layout.createSequentialGroup()
									.addGroup(panel2Layout.createParallelGroup()
										.addGroup(panel2Layout.createSequentialGroup()
											.addContainerGap(52, Short.MAX_VALUE)
											.addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(questQueueLabel)
												.addComponent(questListLabel))
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
											.addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
												.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
												.addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)))
										.addGroup(panel2Layout.createSequentialGroup()
											.addGap(58, 58, 58)
											.addComponent(addQuest)
											.addGap(18, 18, 18)
											.addComponent(removeQuest)
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
											.addComponent(clearAll)))
									.addContainerGap())
						);
					}
					tabbedPane1.addTab("Quest Selection", panel2);

					//======== panel5 ========
					{

						//======== scrollPane6 ========
						{

							//---- requirementsList ----
							requirementsList.setFocusable(false);
							requirementsList.setLineWrap(true);
							requirementsList.setEditable(false);
							scrollPane6.setViewportView(requirementsList);
						}

						//---- requirementsLabel ----
						requirementsLabel.setText("Below is a collection of requirements based on the quests you chose.");

						GroupLayout panel5Layout = new GroupLayout(panel5);
						panel5.setLayout(panel5Layout);
						panel5Layout.setHorizontalGroup(
							panel5Layout.createParallelGroup()
								.addGroup(panel5Layout.createSequentialGroup()
									.addContainerGap()
									.addGroup(panel5Layout.createParallelGroup()
										.addComponent(scrollPane6)
										.addGroup(panel5Layout.createSequentialGroup()
											.addComponent(requirementsLabel, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE)
											.addGap(0, 0, Short.MAX_VALUE)))
									.addContainerGap())
						);
						panel5Layout.setVerticalGroup(
							panel5Layout.createParallelGroup()
								.addGroup(GroupLayout.Alignment.TRAILING, panel5Layout.createSequentialGroup()
									.addContainerGap()
									.addComponent(requirementsLabel)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
									.addComponent(scrollPane6, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
						);
					}
					tabbedPane1.addTab("Requirements", panel5);

					//======== panel3 ========
					{

						//======== scrollPane3 ========
						{

							//---- rewardItemList ----
							rewardItemList.setModel(new AbstractListModel<String>() {
								String[] values = {
									""
								};
								@Override
								public int getSize() { return values.length; }
								@Override
								public String getElementAt(int i) { return values[i]; }
							});
							rewardItemList.setFocusable(false);
							rewardItemList.setEnabled(false);
							rewardItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							scrollPane3.setViewportView(rewardItemList);
						}

						//---- itemLabel ----
						itemLabel.setText("Items:");

						//======== scrollPane4 ========
						{

							//---- rewardExpList ----
							rewardExpList.setModel(new AbstractListModel<String>() {
								String[] values = {
									""
								};
								@Override
								public int getSize() { return values.length; }
								@Override
								public String getElementAt(int i) { return values[i]; }
							});
							rewardExpList.setEnabled(false);
							rewardExpList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							scrollPane4.setViewportView(rewardExpList);
						}

						//---- expLabel ----
						expLabel.setText("Experience:");

						GroupLayout panel3Layout = new GroupLayout(panel3);
						panel3.setLayout(panel3Layout);
						panel3Layout.setHorizontalGroup(
							panel3Layout.createParallelGroup()
								.addGroup(panel3Layout.createSequentialGroup()
									.addContainerGap()
									.addGroup(panel3Layout.createParallelGroup()
										.addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
										.addComponent(itemLabel, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE))
									.addGap(18, 18, 18)
									.addGroup(panel3Layout.createParallelGroup()
										.addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
										.addComponent(expLabel, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
									.addContainerGap())
						);
						panel3Layout.setVerticalGroup(
							panel3Layout.createParallelGroup()
								.addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
									.addContainerGap()
									.addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(itemLabel)
										.addComponent(expLabel))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(panel3Layout.createParallelGroup()
										.addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
										.addComponent(scrollPane4, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))
									.addContainerGap())
						);
					}
					tabbedPane1.addTab("Rewards", panel3);

					//======== panel4 ========
					{

						//---- foodSupportCheckBox ----
						foodSupportCheckBox.setText("Food Support");

						//---- GESupportCheckBox ----
						GESupportCheckBox.setText("Grand Exchange Support");

						//======== scrollPane7 ========
						{

							//---- foodSupportInfoText ----
							foodSupportInfoText.setLineWrap(true);
							foodSupportInfoText.setText("You must have the type of food you wish to use in your bank or inventory");
							foodSupportInfoText.setEditable(false);
							foodSupportInfoText.setFocusable(false);
							scrollPane7.setViewportView(foodSupportInfoText);
						}

						//---- typeOfFoodLabel ----
						typeOfFoodLabel.setText("Type of Food:");

						//---- typeOfFoodComboBox ----
						typeOfFoodComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {
								"Banana","Cooked Chicken","Cooked lobster","Cooked meat","Rabbit sandwich",
								"Swordfish"
						}));

						//======== scrollPane8 ========
						{

							//---- GESupportInfoText ----
							GESupportInfoText.setText("You will need the first slot in the G.E free and at least 10-20k money in your money pouch");
							GESupportInfoText.setLineWrap(true);
							GESupportInfoText.setEditable(false);
							GESupportInfoText.setFocusable(false);
							scrollPane8.setViewportView(GESupportInfoText);
						}

						GroupLayout panel4Layout = new GroupLayout(panel4);
						panel4.setLayout(panel4Layout);
						panel4Layout.setHorizontalGroup(
							panel4Layout.createParallelGroup()
								.addGroup(panel4Layout.createSequentialGroup()
									.addGap(46, 46, 46)
									.addGroup(panel4Layout.createParallelGroup()
										.addGroup(panel4Layout.createSequentialGroup()
											.addGroup(panel4Layout.createParallelGroup()
												.addGroup(panel4Layout.createSequentialGroup()
													.addComponent(scrollPane7, GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE)
													.addGap(18, 18, 18)
													.addComponent(typeOfFoodComboBox, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
												.addComponent(GESupportCheckBox))
											.addContainerGap())
										.addGroup(panel4Layout.createSequentialGroup()
											.addComponent(foodSupportCheckBox, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 203, Short.MAX_VALUE)
											.addComponent(typeOfFoodLabel)
											.addGap(91, 91, 91))
										.addGroup(panel4Layout.createSequentialGroup()
											.addComponent(scrollPane8, GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE)
											.addContainerGap(183, Short.MAX_VALUE))))
						);
						panel4Layout.setVerticalGroup(
							panel4Layout.createParallelGroup()
								.addGroup(panel4Layout.createSequentialGroup()
									.addGap(25, 25, 25)
									.addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(foodSupportCheckBox)
										.addComponent(typeOfFoodLabel))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(panel4Layout.createParallelGroup()
										.addGroup(panel4Layout.createSequentialGroup()
											.addComponent(scrollPane7, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
											.addComponent(GESupportCheckBox))
										.addGroup(panel4Layout.createSequentialGroup()
											.addComponent(typeOfFoodComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(scrollPane8, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
						);
					}
					tabbedPane1.addTab("Settings", panel4);
				}

				//---- startButton ----
				startButton.setText("Begin");
				startButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						startButtonActionPerformed(e);
					}
				});

				GroupLayout panel1Layout = new GroupLayout(panel1);
				panel1.setLayout(panel1Layout);
				panel1Layout.setHorizontalGroup(
					panel1Layout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
							.addContainerGap()
							.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(tabbedPane1, GroupLayout.Alignment.LEADING)
								.addGroup(panel1Layout.createSequentialGroup()
									.addGap(0, 0, Short.MAX_VALUE)
									.addComponent(startButton, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap())
				);
				panel1Layout.setVerticalGroup(
					panel1Layout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
							.addContainerGap()
							.addComponent(tabbedPane1)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(startButton))
				);
			}
			contentPane.add(panel1);
			panel1.setBounds(-5, 2, 582, 405);

			contentPane.setPreferredSize(new Dimension(575, 410));
			pack();
			setLocationRelativeTo(getOwner());
			// JFormDesigner - End of component initialization  //GEN-END:initComponents
		}

		// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
		// Generated using JFormDesigner Evaluation license - Christian Day
		private JPanel panel1;
		private JTabbedPane tabbedPane1;
		private JPanel panel2;
		private JScrollPane scrollPane1;
		private JList<String> questList;
		private JScrollPane scrollPane2;
		private JList<String> queueList;
		private JLabel questListLabel;
		private JLabel questQueueLabel;
		private JButton addQuest;
		private JButton removeQuest;
		private JButton clearAll;
		private JPanel panel5;
		private JScrollPane scrollPane6;
		private JTextArea requirementsList;
		private JLabel requirementsLabel;
		private JPanel panel3;
		private JScrollPane scrollPane3;
		private JList<String> rewardItemList;
		private JLabel itemLabel;
		private JScrollPane scrollPane4;
		private JList<String> rewardExpList;
		private JLabel expLabel;
		private JPanel panel4;
		private JCheckBox foodSupportCheckBox;
		private JCheckBox GESupportCheckBox;
		private JScrollPane scrollPane7;
		private JTextArea foodSupportInfoText;
		private JLabel typeOfFoodLabel;
		private JComboBox<String> typeOfFoodComboBox;
		private JScrollPane scrollPane8;
		private JTextArea GESupportInfoText;
		private JButton startButton;
		// JFormDesigner - End of variables declaration  //GEN-END:variables
		 private class MyListRenderer extends DefaultListCellRenderer  
		    {  
		        private HashMap<Object, String> incompleteQuests = new HashMap<Object, String>();  
		   
		        public Component getListCellRendererComponent( JList<?> list,  
		                Object value, int index, boolean isSelected,  
		                boolean cellHasFocus)  
		        {  
		            super.getListCellRendererComponent( list, value, index,  
		                    isSelected, cellHasFocus);  
		           
		            
		           for(int i = 0; i < list.getModel().getSize();i++){
		        	   String name = list.getModel().getElementAt(i).toString();
		        	//   "Swept Away","The Blood Pact",
					//	"The Restless Ghost","What's Mine Is Yours","Wolf Whistle","Vampyre Slayer"
		            	if(name.equals("Buyers and Cellars")){
		            		if((ctx.settings.get(2085) & 0x7FF) == 1930){
		            			System.out.println("Done buyers and cellars");
		            			incompleteQuests.put( value, "complete" );  
		            			if(index == i)
				            		 if( incompleteQuests.containsKey( value ) )  
				            		 {setForeground( Color.green );} 
		            		}
		            		//setForeground( Color.red );  
		            	}else if(name.equals("Cook's Assistant")){
		            		if((ctx.settings.get(2492)&0x3) ==2){
		            			incompleteQuests.put( value, "complete" );  
	            			if(index == i)
			            		 if( incompleteQuests.containsKey( value ) )  {
			            		 {setForeground( Color.green );} 
			            		 }else setForeground( Color.red ); 
	            		}
		            		 
		            	}else if(name.equals("Clock Tower")){
		            		if((ctx.settings.get(2197)&0xF)==8){
		            			incompleteQuests.put( value, "complete" );  
	            			if(index == i)
			            		 if( incompleteQuests.containsKey( value ) )  
			            		 {setForeground( Color.green );} 
	            		}
		            	}else if(name.equals("Death Plateau")){
		            		if((ctx.settings.get(2337) & 0x1FF) ==449){
		            			incompleteQuests.put( value, "complete" );  
	            			if(index == i)
	            				 if( incompleteQuests.containsKey( value ) )  {
				            		 {setForeground( Color.green );} 
				            		 }else setForeground( Color.red ); 
	            		}
		            	}else if(name.equals("Gunnar's Ground")){
		            		if((ctx.settings.get(2111) & 0x7F) == 100){
		            			incompleteQuests.put( value, "complete" );  
	            			if(index == i)
	            				 if( incompleteQuests.containsKey( value ) )  {
				            		 {setForeground( Color.green );} 
				            		 }else setForeground( Color.red ); 
	            		}
		            	}
		            	else if(name.equals("Demon Slayer")){
		            		if((ctx.settings.get(3518) & 0x7F)==121){
		            			incompleteQuests.put( value, "complete" );  
	            			if(index == i)
	            				 if( incompleteQuests.containsKey( value ) )  {
				            		 {setForeground( Color.green );} 
				            		 }else setForeground( Color.red ); 
	            		}
		            	}
		            	else if(name.equals("Stolen Hearts")){
		            		if((ctx.settings.get(2449)&0x7F)==105){
		            			incompleteQuests.put( value, "complete" );  
	            			if(index == i)
	            				 if( incompleteQuests.containsKey( value ) )  {
				            		 {setForeground( Color.green );} 
				            		 }else setForeground( Color.red ); 
	            		}
		            	}
		            	else if(name.equals("Druidic Ritual")){
		            		if((ctx.settings.get(2694) & 0xFF)==136){
		            			incompleteQuests.put( value, "complete" );  
	            			if(index == i)
	            				 if( incompleteQuests.containsKey( value ) )  {
				            		 {setForeground( Color.green );} 
				            		 }else setForeground( Color.red ); 
	            		}
		            	}else if(name.equals("Elemental Workshop 1")){
		            		if((ctx.settings.get(2675)>>20&0x1)==1){
		            			incompleteQuests.put( value, "complete" );  
	            			if(index == i)
	            				 if( incompleteQuests.containsKey( value ) )  {
				            		 {setForeground( Color.green );} 
				            		 }else setForeground( Color.red ); 
	            		}
		            	}else if(name.equals("Ernest The Chicken")){
		            		if((ctx.settings.get(2183) & 0x3) == 3){
		            			incompleteQuests.put( value, "complete" );  
	            			if(index == i)
	            				 if( incompleteQuests.containsKey( value ) )  {
				            		 {setForeground( Color.green );} 
				            		 }else setForeground( Color.red ); 
	            		}
		            	}else if(name.equals("Goblin Diplomacy")){
		            		if(ctx.settings.get(2137)==6){
		            			incompleteQuests.put( value, "complete" );  
	            			if(index == i)
	            				 if( incompleteQuests.containsKey( value ) )  {
				            		 {setForeground( Color.green );} 
				            		 }else setForeground( Color.red ); 
	            		}
		            	}else if(name.equals("Gunnar's Ground")){
		            		if((ctx.settings.get(2111) & 0x7F) == 100){
		            			incompleteQuests.put( value, "complete" );  
	            			if(index == i)
	            				 if( incompleteQuests.containsKey( value ) )  {
				            		 {setForeground( Color.green );} 
				            		 }else setForeground( Color.red ); 
	            		}
		            	}else if(name.equals("Imp Catcher")){
		            		if((ctx.settings.get(2669)&0x3)==2){
		            			incompleteQuests.put( value, "complete" );  
	            			if(index == i)
	            				 if( incompleteQuests.containsKey( value ) )  {
				            		 {setForeground( Color.green );} 
				            		 }else setForeground( Color.red ); 
	            		}
		            	}else if(name.equals("Let Them Eat Pie")){
		            		if((ctx.settings.get(2674)&0x3F)==40){
		            			incompleteQuests.put( value, "complete" );  
	            			if(index == i)
	            				 if( incompleteQuests.containsKey( value ) )  {
				            		 {setForeground( Color.green );} 
				            		 }else setForeground( Color.red ); 
	            		}
		            	}else if(name.equals("Lost City")){
		            		if((ctx.settings.get(2551)&0x7) == 6){
		            			incompleteQuests.put( value, "complete" );  
	            			if(index == i)
	            				 if( incompleteQuests.containsKey( value ) )  {
				            		 {setForeground( Color.green );} 
				            		 }else setForeground( Color.red ); 
	            		}
		            	}else if(name.equals("What's Mine Is Yours")){
		            		if((ctx.settings.get(2231)&0x3F) ==55){
		            			incompleteQuests.put( value, "complete" );  
	            			if(index == i)
	            				 if( incompleteQuests.containsKey( value ) )  {
				            		 {setForeground( Color.green );} 
				            		 }else setForeground( Color.red ); 
	            		}
		            	}else if(name.equals("Monk's Friend")){
		            		if((ctx.settings.get(2370) & 0x7F) == 80){
		            			incompleteQuests.put( value, "complete" );  
	            			if(index == i)
	            				 if( incompleteQuests.containsKey( value ) )  {
				            		 {setForeground( Color.green );} 
				            		 }else setForeground( Color.red ); 
	            		}
		            	}else if(name.equals("Pirate's Treasure")){
		            		if((ctx.settings.get(2227) & 0x7) ==4){
		            			incompleteQuests.put( value, "complete" );  
	            			if(index == i)
	            				 if( incompleteQuests.containsKey( value ) )  {
				            		 {setForeground( Color.green );} 
				            		 }else setForeground( Color.red ); 
	            		}
		            	}else if(name.equals("The Restless Ghost")){
		            		if((ctx.settings.get(2324)&0x7) ==5){
		            			incompleteQuests.put( value, "complete" );  
	            			if(index == i)
	            				 if( incompleteQuests.containsKey( value ) )  {
				            		 {setForeground( Color.green );} 
				            		 }else setForeground( Color.red ); 
	            		}
		            	}else if(name.equals("Stolen Hearts")){
		            		if((ctx.settings.get(2449)&0x7F)==105){
		            			incompleteQuests.put( value, "complete" );  
	            			if(index == i)
	            				 if( incompleteQuests.containsKey( value ) )  {
				            		 {setForeground( Color.green );} 
				            		 }else setForeground( Color.red ); 
	            		}
		            	}else if(name.equals("Swept Away")){
		            		if((ctx.settings.get(2198) & 0x1F) ==18){
		            			incompleteQuests.put( value, "complete" );  
	            			if(index == i)
	            				 if( incompleteQuests.containsKey( value ) )  {
				            		 {setForeground( Color.green );} 
				            		 }else setForeground( Color.red ); 
	            		}
		            	}else if(name.equals("The Blood Pact")){
		            		if((ctx.settings.get(2334)&0x3F)==60){
		            			incompleteQuests.put( value, "complete" );  
	            			if(index == i)
	            				 if( incompleteQuests.containsKey( value ) )  {
				            		 {setForeground( Color.green );} 
				            		 }else setForeground( Color.red ); 
	            		}
		            	}else if(name.equals("Vampyre Slayer")){
		            		if((ctx.settings.get(2170)&0x7)==7){
		            			incompleteQuests.put( value, "complete" );  
	            			if(index == i)
	            				 if( incompleteQuests.containsKey( value ) )  {
				            		 {setForeground( Color.green );} 
				            		 }else setForeground( Color.red ); 
	            		}
		            	}else if(name.equals("Wolf Whistle")){
		            		if((ctx.settings.get(2506)&0x3F)==35){
		            			incompleteQuests.put( value, "complete" );  
	            			if(index == i)
	            				 if( incompleteQuests.containsKey( value ) )  {
				            		 {setForeground( Color.green );} 
				            		 }else setForeground( Color.red ); 
	            		}
		            	}
	            		
		            	
		        	  // System.out.println("lolly"+list.getModel().getElementAt(i));
		            }
		           
		           
		             
		   
		            return( this );  
		        }  
		    }  
		   
	}



	public void initiateGui() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				TaskListForm taskgui= new TaskListForm();
				taskgui.setVisible(true);
				final DeltaQuesterGUI deltagui = new DeltaQuesterGUI();
				deltagui.setVisible(true);
				deltagui.setResizable(false);
			
			}
		});
	}
	

	private Image getImage(String url) {
		try {
			return ImageIO.read(new URL(url));
		} catch (IOException e) {
			return null;
		}
	}
	private final Font font1 = new Font("Serif", 0, 15);
   // private final Image img1 = getImage("http://i1223.photobucket.com/albums/dd508/thriller645/newPaint_zps9e1ed368.png");
	private final Image paint = getImage("http://img841.imageshack.us/img841/5984/kg3.png");
	
	
    
    public  void repaint(Graphics g) {
		g.drawImage(paint, +5, -15, null);
		//mouseX = (int) ctx.mouse.getLocation().getX();
		//mouseY = (int) ctx.mouse.getLocation().getY();
		//setMouse(g);
		g.setColor(Color.DARK_GRAY);
		g.drawRect(20,334, 185, 7);
		g.setColor(Color.green);
		if(numSteps!=0)
		g.fillRect(20,335, progress*185/numSteps, 7);
		g.setFont(font1);
		g.setColor(Color.pink);
		g.drawString("State: " + state, 18, 106);
		if(!qList.isEmpty())
		g.drawString("Quest: "+ qList.get(0).toString(), 18, 370);
		if(qList.size()>=2)
		g.drawString("Next quest: " + qList.get(1).toString(), 18, 390);
		g.drawString("" + (int)((double)progress/numSteps*100) +"%", 215, 340);
		g.drawString("GEFeature: " + GEWO, 18, 410);
		g.drawString("Time: "+(int)(timeSec.getElapsed()/1000), 68,57);
		g.drawString("Food Support: " + FOOD_FEATURE, 18, 430);
		g.drawString("useBank: " + Vars.useBank, 18, 450);
		g.drawString("Health: " + health+ "%", 18, 470);
		//g.drawString("ranOnce: " + Vars.ranOnce,20, 332);
		//g.drawString("e: " + e, 20, 354);
		
		

	
	}

	private void setMouse(Graphics2D g) {
		g.setColor(Color.MAGENTA);
		g.drawLine(mouseX, mouseY - 800, mouseX, mouseY + 800);
		g.drawLine(mouseX - 800, mouseY, mouseX + 800, mouseY);
		
	}
	public void mouseClicked(MouseEvent e) {

	}

	//public boolean isReady() {
	//	return getInstance().ready;
	//}
	//public void setReady(boolean ready) {
	//	getInstance().ready = ready;
	//}

	

}
