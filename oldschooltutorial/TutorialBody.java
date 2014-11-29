package oldschooltutorial;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Random;
import org.powerbot.script.Script;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Interactive;


@Script.Manifest(name = "Delta Tutorial Island", 
description = "In development", properties = "topic = 0")

public class TutorialBody extends PollingScript<ClientContext> implements PaintListener{



	
	private final List<TutorialNode> nodeList = Collections.synchronizedList(new ArrayList<TutorialNode>());
	
	
	public static String state;
	public double version = 0.1;
	

	
	
	public boolean done = false;
	public static boolean antiPattern;
	private Random rand = new Random();
	
	public long runTime;
	public int slot;
	TutorialMethod Method = new TutorialMethod(ctx);
	TutorialAntipattern anti = new TutorialAntipattern(ctx);
	public int set = 0;

	
	//object ids
	private int FIREID = 26185;
	private int GATEBYFISH = 9470;
	private int COOKDOORTOOUT = 9710;
	
	//npc ids
	private int RUNESCAPEGUIDE = 3308;
	private int SURVIVALEXPERT = 3306;
	private int FISHINGSPOT = 3317;
	private int COOK = 3305;
	private int QUESTGUIDE = 3312;
	private int MININGGUIDE = 3311;
	private int COMBATGUIDE = 3307;
	private int RAT = 3313;
	private int FINANCIALGUIDE = 3310;
	private int PRAYERGUIDE = 3319;
	private int MAGICGUIDE = 3309;
	private int CHICKEN = 3316;
	private int LUMMBRIDGEGUIDE = 306;
	

	@Override
	public void poll() {
		
		onStart();
		Method.sleep(500);
	
		
		for(TutorialNode node: nodeList){
			if(node.activate()){
				node.execute();
			}
		}
		
	}
	
	   private void onStart() {
		   if(!done){
		   runTime = System.currentTimeMillis();
			   
			
		   
			addNode(new main(ctx));
			addNode(new TutorialAntipattern(ctx));
			
			done = true;
		   }
	}

	private void addNode(final TutorialNode...nodes) {
		   
	        for(TutorialNode node : nodes) {
	            if(!this.nodeList.contains(node)) {
	                this.nodeList.add(node);
	            }
	        }
	    }

	   class main extends TutorialNode{

		public main(ClientContext ctx) {
			super(ctx);
		}

		@Override
		public boolean activate() {
			return true;
		}
		boolean selected = false;
		@Override
		public void execute() {//1504 - rs guide
			calcAntiPattern();
			//seeting 406 
			
			//'you retrieve bronze bar!' - dialogue..
			while(ctx.widgets.component(548,123).visible()){// a dialogue that interferes w/script
				ctx.widgets.component(548,124).click();//the continue button
			}
			//poll booth stuff
			while(ctx.widgets.component(204,1).visible()){// a dialogue that interferes w/script
				ctx.widgets.component(204,1).click();//the continue button
			}//also pole shit
			while(ctx.widgets.component(519,2).visible()){// a dialogue that interferes w/script
				ctx.widgets.component(519,2).click();//the continue button
			}
			
			
			if(Method.npcIsNotNull(LUMMBRIDGEGUIDE)){
				 System.out.println("step 65"); 
			state = "Finished tutorial";
			this.ctx.controller().stop();
			}else
			if(ctx.widgets.widget(372).component(2).text().contains("All you need to do now is move")){
				 System.out.println("step 64"); 
				if(ctx.widgets.widget(519).component(1).valid()){
					//odd tutroail chat screen
					ctx.widgets.widget(519).component(2).click();
				}else
				if(ctx.widgets.widget(228).component(1).valid()){
					state = "Answering question";
					//select yes to question
					ctx.widgets.widget(228).component(1).click();
				}else
				if(!Method.isChatiing()){
					  Method.npcInteract(MAGICGUIDE, "");//magic guide
				  }
				}else
			if(ctx.widgets.widget(421).component(3).text().contains("top left corner of the Magic")){
				 System.out.println("step 63"); 
				if(new Tile(3140,3090,0).distanceTo(ctx.players.local().tile())<4){
					//use a spell on a chicken through a cage
					//spell's widget is 192-1
					if(ctx.widgets.component(218, 2).borderThickness()==0){//means if spell not selected
						ctx.widgets.widget(218).component(2).hover();
						for(String n :ctx.menu.items()){
						if(n.contains("Cast"))
						ctx.widgets.widget(218).component(2).interact("Cast");
						//ctx.mouse.click(595,239,true);//click on magic icon
						}
					}else{
					Method.npcInteract(CHICKEN, "Cast");
					}
					}else ctx.movement.step(new Tile(3140,3090,0));
				
						}else
			if(ctx.widgets.widget(372).component(3).text().contains("Ask the mage about it.")){
				 System.out.println("step 62"); 
				if(ctx.widgets.widget(131).component(3).valid()){
					//the chat-menu equivelant to 1186 in EOC
					ctx.widgets.widget(131).component(3).click();
					
				}else
				if(!Method.isChatiing()){
					  Method.npcInteract(MAGICGUIDE, "Talk-to");//magic guide
				  }
						}else
			if(ctx.widgets.widget(372).component(2).text().contains("Open up the Magic menu by")){
				 System.out.println("step 61"); 
				 ctx.mouse.click(741,185,true);//click on magic icon
						}else
			 if(ctx.widgets.widget(372).component(2).text().contains("shown how to cast spells. Just talk")){
				 System.out.println("step 60"); 
				 if(new Tile(3141,3088,0).distanceTo(ctx.players.local().tile())<5){
					 if(!Method.isChatiing()){
						  Method.npcInteract(MAGICGUIDE, "Talk-to");//magic guide
					  }
					}else ctx.movement.step(new Tile(3141,3088,0));
							}else
			 if(ctx.widgets.widget(372).component(2).text().contains("You're almost finished on tutorial")){
				 System.out.println("step 59"); 
				 if(new Tile(3123,3103,0).distanceTo(ctx.players.local().tile())<4){
					 
					 final int[] bounds = {120, -16, -228, -16, 144, 112};
						GameObject churchDoor = ctx.objects.select().id(9723).each(Interactive.doSetBounds(bounds)).select(Interactive.areInViewport()).nearest().poll();
						churchDoor.interact("Open","");
					 
					}else ctx.movement.step(new Tile(3123,3103,0));
				
						}else
			 if(ctx.widgets.widget(372).component(2).text().contains("keeping track of when your friends")){
				 System.out.println("step 58"); 
				 if(!Method.isChatiing()){
					  Method.npcInteract(PRAYERGUIDE, "Talk-to");//prayer guide
				  }
						}else
			if(ctx.widgets.widget(372).component(2).text().contains("This will be explained by Brother")){
				 System.out.println("step 57"); 
				 ctx.mouse.click(608,485,true);//click on ignore icon
						}else
			 if(ctx.widgets.widget(372).component(1).text().contains("Friends list.")){
				 System.out.println("step 56"); 
				 ctx.mouse.click(574,483,true);//click on friends icon
						}else
			 if(ctx.widgets.widget(372).component(1).text().contains("Your Prayer menu.")){
				 System.out.println("step 55"); 
				 if(!Method.isChatiing()){
					  Method.npcInteract(PRAYERGUIDE, "Talk-to");//prayer guide
				  }
						}else
			 if(ctx.widgets.widget(372).component(2).text().contains("Click on the flashing icon to open the Prayer")){
				 System.out.println("step 54"); 
				 ctx.mouse.click(710,182,true);
				}else
			 if(ctx.widgets.widget(372).component(2).text().contains("Once inside talk to the monk. He'll tell you")){
				 System.out.println("step 53"); 
				 if(new Tile(3126,3107,0).distanceTo(ctx.players.local().tile())<5){
					 
					 if(!new Tile(3127,3107).matrix(ctx).reachable()){
						int CHURCHDOORENTER = 7111;
						Method.interactO(CHURCHDOORENTER, "", "Church door upon entering");
					 }else
					 if(!Method.isChatiing()){
						  Method.npcInteract(PRAYERGUIDE, "");//prayer guide
					  }
					}else ctx.movement.step(new Tile(3126,3107,0));
					}else
			  if(ctx.widgets.widget(372).component(2).text().contains("Continue through the next door.")){
				  System.out.println("step 52"); 
				  Method.interactO(9722, "Open", "Door");
					}else
			  if(ctx.widgets.widget(372).component(2).text().contains("The guide here will tell you all about")){
				  System.out.println("step 51"); 
				  if(!Method.isChatiing()){
					  Method.npcInteract(FINANCIALGUIDE, "Talk-to");//money guide
				  }
				 
					}else
					 if(ctx.widgets.widget(372).component(1).text().contains("Polls are run period")){
						 System.out.println("step 50.5"); 
						 if(ctx.widgets.component(245, 1).component(11).visible()){// a screen from the booth
							 ctx.widgets.component(245, 1).component(11).click();//close button
						 }else
						 if(ctx.widgets.widget(12).component(1).component(11).valid()){//bank screen
						
								ctx.widgets.widget(12).component(1).component(11).click();
						}else{
							 if(new Tile(3124,3124,0).distanceTo(ctx.players.local().tile())<5){
									Method.interactO(9721, "Open", "Door");
							}else ctx.movement.step(new Tile(3124,3124,0));
									
					}
				}else
			  if(ctx.widgets.widget(372).component(1).text().contains("You can store stuff here for safekeeping")){
				  System.out.println("step 50"); 
				  if(ctx.widgets.widget(12).component(1).component(11).valid()){//bank screen
						ctx.widgets.widget(12).component(1).component(11).click();//bank close button
				}else  Method.interactO(26801, "", "Poll booth");
					}else
			   if(ctx.widgets.widget(372).component(1).text().contains("Follow the path and you will come to the front")){
				   System.out.println("step 49"); 
				   if(ctx.widgets.widget(228).component(1).valid()){
						state = "Answering question";
						//select yes to question
						ctx.widgets.widget(228).component(1).click();
					}else
					if(!Method.isChatiing()){
					 if(new Tile(3121,3123,0).distanceTo(ctx.players.local().tile())<7){
							Method.interactO(10083, "Use", "Bank stall");
						}else ctx.movement.step(new Tile(3121,3123,0));
					}
					}else
	      if(ctx.widgets.widget(372).component(1).text().contains("You have completed the tasks here. To move on")){
				
	    	  System.out.println("step 48"); 
			 if(new Tile(3111,9525,0).distanceTo(ctx.players.local().tile())<7){
					Method.interactO(9727, "Climb-up", "Ladder");
				}else ctx.movement.step(new Tile(3111,9525,0));
				
			}else
			if((ctx.varpbits.varpbit(281))==480 ||//might be a setting for this specific step..?
			ctx.widgets.widget(372).component(1).text().contains("Now you have a bow and some")){
				System.out.println("step 47"); 
				
				if(Method.inventoryContains(882)||
						Method.inventoryContains(841)){
					state = "Equipping weapon ranged";
					Method.interactInventory(882, "Wield", "Bronze arrows");
					Method.interactInventory(841, "Wield", "Shortbow");
				}else if(new Tile(3111,9518,0).distanceTo(ctx.players.local().tile())<7){
					if(ctx.players.local().animation()==-1)
					Method.npcInteract(RAT, "Attack");//rat 
				}else ctx.movement.step(new Tile(3111,9518,0));
				
			}else
			if(ctx.widgets.widget(372).component(2).text().contains("Pass through the gate and")){
				System.out.println("step 46"); 
				if(new Tile(3111,9519,0).matrix(ctx).reachable()){
					if(new Tile(3107,9508,0).distanceTo(ctx.players.local().tile())<7){
					 if(ctx.widgets.component(230, 1).visible()){//an option to lcick during conversation
						 ctx.widgets.component(230, 1).click();
					 }else
						if(!Method.isChatiing()){
								Method.npcInteract(COMBATGUIDE, "Talk-to");//speaks to combat guide again
							}
					}else ctx.movement.step(new Tile(3107,9508,0));
				}else Method.interactO(9720, "Open", "Gate");
			}else
			if(ctx.widgets.widget(372).component(2).text().contains("To attack the rat, right click")||
					ctx.widgets.widget(372).component(1).text().contains("While you are fighting you will")){
				System.out.println("step 45"); 
			if(ctx.players.local().animation()==-1)
			Method.npcInteract(RAT, "Attack");//rat 
			}else
			if(ctx.widgets.widget(421).component(2).text().contains("From this interface you can select")){
				System.out.println("step 44"); 
				if(new Tile(3111,9518,0).distanceTo(ctx.players.local().tile())<7){
					 Method.interactO(9720, "Open", "Gate");
				}else ctx.movement.step(new Tile(3111,9518,0));
			}else
			if(ctx.widgets.widget(372).component(2).text().contains("Click on the flashing crossed swords")){
				System.out.println("step 43"); 
				ctx.mouse.click(546,188,true);
			}else
			if(ctx.widgets.widget(421).component(2).text().contains("In your worn inventory")){
				System.out.println("step 42"); 
				Method.interactInventory(1277, "Wield", "Long sword");
				Method.interactInventory(1171, "Wield", "Wooden shield");
			}else
			if(ctx.widgets.widget(421).component(2).text().contains("Clothes, armour, weapons")){
				System.out.println("step 41"); 
				 if(!Method.isChatiing()){
						Method.npcInteract(COMBATGUIDE, "Talk-to");//speaks to combat guide again
					}
			}else
			if(ctx.widgets.widget(372).component(1).text().contains("You can see what items you are wearing")){
				System.out.println("step 40"); 
				state = "Equipping dagger";
				if(ctx.widgets.widget(84).component(4).valid()){//the characters armour display screen
					System.out.println("Closing screen");
					ctx.widgets.widget(84).component(4).click();
				}else
				Method.interactInventory(1205, "Wield", "Bronze dagger");
			}else
			if(ctx.widgets.widget(372).component(1).text().contains("From here you can see what items you")){
				System.out.println("step 39"); 
				ctx.mouse.click(603,432,true);
			}else
			if(ctx.widgets.widget(372).component(1).text().contains("From here you can see what items you")){
				System.out.println("step 38"); 
				ctx.mouse.click(603,432,true);
			}else
			if(ctx.widgets.widget(372).component(2).text().contains("You now have access to a new inter")){
				System.out.println("step 37"); 
				ctx.mouse.click(670,181,true);
			}else
			if(ctx.widgets.widget(372).component(2).text().contains("In this area you will find out about combat")){
				System.out.println("step 36"); 
				if(new Tile(3106,9509,0).distanceTo(ctx.players.local().tile())<7){
					 if(!Method.isChatiing()){
							Method.npcInteract(COMBATGUIDE, "Talk-to");//speaks to combat guide 
						}
					
				}else ctx.movement.step(new Tile(3106,9509,0));
			}else
			if(ctx.widgets.widget(372).component(1).text().contains("So let's move on. Go through")){
				System.out.println("step 35"); 
				if(new Tile(3093,9502,0).distanceTo(ctx.players.local().tile())<7){
					int FIGHTGATE = 9717;//the gate in the fighitng area
					 final int[] bounds = {148, 116, -180, 56, 108, -152};
					 GameObject fightGate = ctx.objects.select().id(FIGHTGATE).each(Interactive.doSetBounds(bounds)).select(Interactive.areInViewport()).nearest().poll();
					   fightGate.interact("Open","");
				}else ctx.movement.step(new Tile(3093,9502,0));
			}else
			if(ctx.widgets.widget(372).component(1).text().contains("Now you have the Smithing menu")){
				System.out.println("step 34"); 
				if(new Tile(3082,9499,0).distanceTo(ctx.players.local().tile())<7){
				  if(ctx.widgets.widget(312).component(1).valid()){
					  ctx.mouse.click(43,58,true);
					  Method.sleep(2000);
				  }else if(ctx.inventory.selectedItem().valid()){
						Method.interactO(2097, "Use", "Anvil");
					}else Method.interactInventory(2349, "Use", "Bronze bar");
					
					
				}else ctx.movement.step(new Tile(3082,9499,0));
			}else
			if(ctx.widgets.widget(372).component(1).text().contains("To smith you'll need a hammer")){
				System.out.println("step 33"); 
				if(new Tile(3082,9499,0).distanceTo(ctx.players.local().tile())<4){
				   
					if(ctx.inventory.selectedItem().valid()){
						Method.interactO(2097, "Use", "Anvil");
					}else Method.interactInventory(2349, "Use", "Bronze bar");
					
				}else ctx.movement.step(new Tile(3082,9499,0));
			}else
			if(ctx.widgets.widget(372).component(2).text().contains("Speak to the Mining Instructor")){
				System.out.println("step 32"); 
				if(new Tile(3081,9506,0).distanceTo(ctx.players.local().tile())<7){
				  if(!Method.isChatiing()){
						Method.npcInteract(MININGGUIDE, "Talk-to");//speaks to mining guide again #2
					}
				}else ctx.movement.step(new Tile(3081,9506,0));
			}else
			if(ctx.widgets.widget(372).component(1).text().contains("You should now have both some copper and")){
				System.out.println("step 31"); 
				if(new Tile(3079,9498,0).distanceTo(ctx.players.local().tile())<4){
					if(ctx.inventory.selectedItem().valid()){
						Method.interactO(10082, "Use", "Furnace");
					}else Method.interactInventory(438, "Use", "Tin ore");
				}else ctx.movement.step(new Tile(3079,9498,0));
			}else
			if(ctx.widgets.widget(372).component(1).text().contains("Now you have some copper ore")){
				System.out.println("step 30"); 
				if(new Tile(3076,9502,0).distanceTo(ctx.players.local().tile())<7){
					Method.interactO(10080, "Mine", "Rock");
				}else ctx.movement.step(new Tile(3076,9502,0));
			}else
			if(ctx.widgets.widget(372).component(1).text().contains("It's quite simple really")){
				System.out.println("step 29"); 
					Method.interactO(10079, "Mine", "Rock");
			}else
			if(ctx.widgets.widget(372).component(1).text().contains("Talk to the Mining")){
				System.out.println("step 28"); 
				if(!Method.isChatiing()){
					Method.npcInteract(MININGGUIDE, "Talk-to");//speaks to mining guide again
				}
			}else
			if(ctx.widgets.widget(372).component(2).text().contains("So now you know there's tin")){
				System.out.println("step 27"); 
				if(new Tile(3086,9504,0).distanceTo(ctx.players.local().tile())<7){
					Method.interactO(10079, "Prospect", "Rock");
				}else ctx.movement.step(new Tile(3086,9504,0));
			}else
			if(ctx.widgets.widget(372).component(1).text().contains("To prospect a mineable")){
				System.out.println("step 26"); 
				Method.interactO(10080, "Prospect", "Rock");
			}else
			if(ctx.widgets.widget(372).component(1).text().contains("Next let's get you a")){
				System.out.println("step 25"); 
				if(new Tile(3082,9505,0).distanceTo(ctx.players.local().tile())<7){
					
					if(!Method.isChatiing()){
						Method.npcInteract(MININGGUIDE, "Talk-to");//speaks to mining guide
					}
				}else ctx.movement.step(new Tile(3082,9505,0));
			}else
			if(ctx.widgets.widget(372).component(2).text().contains("It's time to enter some caves")){
				System.out.println("step 24"); 
				Method.interactO(9726, "Climb-down", "Trapdoor");
			}else
			if(ctx.widgets.widget(372).component(2).text().contains("This is your Quest Journal")){
				System.out.println("step 23"); 
				if(!Method.isChatiing()){
					Method.npcInteract(QUESTGUIDE, "Talk-to");//speaks to the quest guide
				}
			}else
			if(ctx.widgets.widget(372).component(1).text().contains("Open the Quest Journal")){
				System.out.println("step 22"); 
				ctx.mouse.click(609,184,true);//click on quest icon
			}else
			if(ctx.widgets.widget(372).component(1).text().contains("Talk with the Quest Guide.")){
				System.out.println("step 21"); 
				
				if(!Method.isChatiing()){
					Method.npcInteract(QUESTGUIDE, "Talk-to");//speaks to the quest guide
				}
			}else
			if(ctx.widgets.widget(421).component(2).text().contains("Now that you have the run")){
				if(new Tile(3086,3126,0).distanceTo(ctx.players.local().tile())<7){
					System.out.println("step 20"); 
					Method.interactO(9716, "Open", "Door");
				}else ctx.movement.step(new Tile(3086,3126,0));
			}else
			if(ctx.widgets.widget(372).component(4).text().contains("click on the run button")){
				System.out.println("step 19"); 
				ctx.mouse.click(644,438,true);
			}else
			if(ctx.widgets.widget(372).component(2).text().contains("Why not try running")){
				System.out.println("step 18"); 
				ctx.mouse.click(672,478,true);
			}else
			if(ctx.widgets.widget(421).component(3).text().contains("For those situations where")){
				System.out.println("step 17"); 
			ctx.mouse.click(568,230,true);
			}else
			if(ctx.widgets.widget(372).component(2).text().contains("Now, how about showing")){
				System.out.println("step 16"); 
				ctx.mouse.click(712,480,true);
			}else
			if(ctx.widgets.widget(421).component(4).text().contains("examined this menu")){
				System.out.println("step 15"); 
				if(new Tile(3074,3090,0).distanceTo(ctx.players.local().tile())<7){
					final int[] bounds = {144, 104, -204, -32, 8, 136};
					GameObject cookDoor = ctx.objects.select().id(COOKDOORTOOUT).each(Interactive.doSetBounds(bounds)).select(Interactive.areInViewport()).nearest().poll();
					cookDoor.interact("Open","");
				}else ctx.movement.step(new Tile(3074,3090,0));
			}else
			if(ctx.widgets.widget(421).component(6).text().contains("the jukebox.")){
				System.out.println("step 14"); 
				ctx.mouse.click(743,481,true);//click jukebox icon
			}else
			if(ctx.widgets.widget(372).component(1).text().contains("Now you have made dough, you")){
				System.out.println("step 13"); 
				if(ctx.inventory.selectedItem().valid()){
					 Method.interactO(9736, "Use", "Cooking range");
					}else Method.interactInventory(2307, "Use", "Doe uncooked");
			
			}else
			if(ctx.widgets.widget(372).component(1).text().contains("This is the base for many of the meals")){
				System.out.println("step 12.5");  
				if(ctx.inventory.selectedItem().valid()){
						Method.interactInventory(2516, "Use", "Pot of flour");
					}else Method.interactInventory(1929, "Use", "Bucket of water");
			}else
			if(ctx.widgets.widget(372).component(1).text().contains("Talk to the chef indicated")){
				System.out.println("step 12"); 
				
				if(!Method.isChatiing()){
					Method.npcInteract(COOK, "Talk-to");//speaks to the chef init
				}
			}else
			if(ctx.widgets.widget(372).component(3).text().contains("Click on the gate shown and")||
					ctx.widgets.widget(372).component(1).text().contains("Follow the path until you get to")){
				System.out.println("step 11"); 
				cs8();//gets inside the chef area
			}else
			if(ctx.widgets.widget(372).component(1).text().contains("You have just burnt your first shrimp")){
				System.out.println("step 10"); 
				cs7();//catch shrimp again, and cook it properly this time
			  }else
			if(ctx.widgets.widget(372).component(1).text().contains("Now you have caught some")||
					ctx.widgets.widget(372).component(1).text().contains("Now right click on the shrimp")){
				System.out.println("step 9"); 
				cs6();//make a fire, cook shrimp(might get burnt?)
			  }else
			if(ctx.widgets.widget(372).component(1).text().contains("Click on the sparkling")){
				
				System.out.println("step 8"); 
				Method.npcInteract(FISHINGSPOT, "Net");
			  }else
			if(ctx.widgets.widget(421).component(2).text().contains("Here you will see how good")){
				System.out.println("step 7"); 
				cs2();//speak to survial expert again
			  }else
			if(ctx.widgets.widget(372).component(1).text().contains("You gained some experience.")){
				System.out.println("step 6"); 
				ctx.mouse.click(578,185,true);
			  }else
			if(ctx.widgets.widget(372).component(1).text().contains("Well done! You managed to cut")){
				  System.out.println("cs5"); 
				cs5();//use tinderbox on logs
			  }else
			if(ctx.widgets.widget(372).component(1).text().contains("You can click on the backpack icon at any")){
				  System.out.println("cs4"); 
				cs4();//Cut down a tree init
			  }else
			if(ctx.widgets.widget(372).component(1).text().contains("Click on the flashing backpack icon")){
				  System.out.println("cs3");
				cs3();//click on inventory icon
			  }else
			 if(ctx.widgets.widget(372).component(1).text().contains("Follow the path to")){
				  System.out.println("cs2");
				 cs2();//go and speak to survival guide initially
				 //setting 406 changed to 2 at this point
			  }else
			if(ctx.widgets.widget(421).component(2).text().contains("You can interact")){
				//door id 9398
				//open a door
				Method.interactO(9398, "Open", "Door");
			}else
			if(ctx.widgets.widget(242).component(2).text().contains("You will notice a flashing")){
					ctx.widgets.component(242, 4).click();//continue button of dialogue
			}else
		  if(ctx.varpbits.varpbit(1021)==12){
			  System.out.println("cs1");
			  cs1();//open up settings
		  }else {
			  System.out.println("cs0, talking to guide");
			  cs0();//begin by speaking to tutorial guide
		  }
			
			
		}

		private void cs8() {
			//9470 or 9708 = gate
			//9709 = door by chef
			
	
			if(new Tile(3088,3092,0).matrix(ctx).reachable()){
				
				if(new Tile(3079,3084,0).distanceTo(ctx.players.local().tile())<7){
					Method.interactO(9709, "Open", "Door");
				}else ctx.movement.step(new Tile(3079,3084,0));
				
			}else if(new Tile(3090,3092,0).distanceTo(ctx.players.local().tile())<7){
				final int[] bounds = {140, 108, -84, 144, -64, 136};
				GameObject gate = ctx.objects.select().id(GATEBYFISH).each(Interactive.doSetBounds(bounds)).select(Interactive.areInViewport()).nearest().poll();
				gate.interact("Open","");
			}else ctx.movement.step(new Tile(3090,3092,0));
			
			
		}

		private void cs7() {
			if(Method.inventoryContains(2514)){//shrimp
				cs6();
			}else if(ctx.inventory.selectedItem().valid()){
				state = "De-selecting shrimp";
				ctx.mouse.click(585,333,true);
			}else Method.npcInteract(FISHINGSPOT, "Net");
			
		}

		private void cs6() {//cook shrimmp on fire
			//2511 = logs
			//12718 = fire
			//2514 = shrimp
			
		if(Method.objIsNotNull(FIREID)){
			state = "Made fire";
			if(ctx.inventory.selectedItem().valid()){
				Method.interactO(FIREID, "Use", "Fire");
			}else Method.interactInventory(2514, "Use","Shrimp");
		}else
		 if(Method.inventoryContains(2511)){
			 System.out.println("Has logs in inventory");
			 if(ctx.inventory.selectedItem().valid()){
					Method.interactInventory(2511, "Use", "Logs");
				}else Method.interactInventory(590, "Use", "Tinder box");
				
		 }else Method.interactO(9730, "Chop down", "Tree");
			
			
		}

		private void cs5() {//use tinderbox on logs
			if(ctx.widgets.widget(519).component(1).text().contains("You get some")){
				ctx.widgets.widget(519).component(2).click();
			}
			if(ctx.inventory.selectedItem().valid()){
				Method.interactInventory(2511, "Use", "Logs");
			}else Method.interactInventory(590, "Use", "Tinder box");
			
		}

		private void cs4() {
			Method.interactO(9730, "Chop down", "Tree");
			
		}

		private void cs3() {
			ctx.mouse.click(640,185,true);
			
		}
	
		private void cs2() {
			if(new Tile(3103,3096,0).distanceTo(ctx.players.local().tile())<7){
				if(!Method.isChatiing()){
					Method.npcInteract(SURVIVALEXPERT, "Talk-to");
				}
			}else ctx.movement.step(new Tile(3103,3096,0));
			
		}

		private void cs1() {//click on the settings tab 
			ctx.mouse.click(675,481,true);
			
		}
		
		private void cs0() {//SPEAK TO THE RSGUIDE INITIALLY
			if(!Method.isChatiing()){
				Method.npcInteract(RUNESCAPEGUIDE, "Talk-to");
			}
			
		}
		   
		   
	   }
	  
	   private boolean calcAntiPattern() {
			int number = rand.nextInt(0,20);
			if(number == 1){
				antiPattern = true;
				return true;
			}
			return false;
		}
		private Image getImage(String url) {
			
				return this.downloadImage(url);
			
		}
private Font myFont = new Font("Consolas",Font.BOLD,15);
private Font myStateFont = new Font("Arial Black",Font.BOLD,14);


private int mouseX;
private int mouseY;

private void setMouse(Graphics g) {
	g.setColor(Color.MAGENTA);
	g.drawLine(mouseX, mouseY - 800, mouseX, mouseY + 800);
	g.drawLine(mouseX - 800, mouseY, mouseX + 800, mouseY);
}

	public void repaint(Graphics g) {
		
		String expHr = "";
		if(expHr.length()>3)
		expHr = expHr.substring(0, expHr.length() - 3);
		
		mouseX = (int) ctx.mouse.getLocation().getX();
		mouseY = (int) ctx.mouse.getLocation().getY();
		setMouse(g);
		g.setFont(myStateFont);
		g.setFont(myFont);
		long time = runTime - System.currentTimeMillis();
		
		g.setColor(Color.magenta);
		g.drawString("" +version, 127,218);
		g.drawString(""+state, 63,39);
		g.drawString("Runtime: " + Method.format(time), 85,55);
		g.drawString("" +set, 148,72);
		
		
	}

	private void calcExpHr() {
		int current = ctx.skills.experience(25);
		
	}

	
	

}
