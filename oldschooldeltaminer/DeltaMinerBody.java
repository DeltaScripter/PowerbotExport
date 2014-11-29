package oldschooldeltaminer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.SwingUtilities;
 

import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Random;
import org.powerbot.script.Script;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.ItemQuery;



@Script.Manifest(name = "Delta Miner", 
description = "In development", properties = "topic = 0")

public class DeltaMinerBody extends PollingScript<ClientContext> implements PaintListener{



	
	private final List<DeltaMinerNode> nodeList = Collections.synchronizedList(new ArrayList<DeltaMinerNode>());
	
	
	public static String state = "Welcome to Delta Miner, please choose your settings";
	public double version = 0.1;
	

	
	
	public boolean done = false;
	public static boolean antiPattern;
	private Random rand = new Random();
	
	public long runTime;
	public int slot;
	DeltaMinerMethod Method = new DeltaMinerMethod(ctx);
	DeltaMinerAntipattern anti = new DeltaMinerAntipattern(ctx);
	public static boolean start = false;
 
	public boolean oreTime = false;//when to interact with ores
	public int oreMined = 0;
	public ItemQuery<Item> invCount;
	
	
	//settings
	public Tile mineLocation;
	public static int[] rockType;

	@Override
	public void poll() {
		
		onStart();
		Method.sleep(500);
	  if(start){
		   
		   
		if(!antiRandom())
		for(DeltaMinerNode node: nodeList){
			if(node.activate()){
			   node.execute();
			}
		}
		
	   }
	}
	
	   private boolean antiRandom() {
		   //2528 the lamp the genie gives you(item)
		   //3062 = the mysterious box(item)
		   //5830 = The genie(npc)
			if(Method.inventoryContains(3062)){
				state = "Mystery box random!";
				stop();
				return true;
			}
			  if(Method.npcIsNotNull(5830) && !Method.inventoryContains(2528)){
				  state = "Genie random!";
				  if(!Method.isChatiing()){
					  Method.npcInteract(5830, "Talk-to");
				  }
				  return true;
			  }
		return false;
	}

	
		public void initiateGui() {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					final DeltaMiner deltagui = new DeltaMiner();
					deltagui.setVisible(true);
					deltagui.setResizable(false);
				
				}
			});
		}
		
	private void onStart() {
		   if(!done){
			initiateGui();
		    runTime = System.currentTimeMillis();
		  
			//settings
		    mineLocation = ctx.players.local().tile();
			
		   
			addNode(new main(ctx));
			addNode(new bank(ctx));
			addNode(new DeltaMinerAntipattern(ctx));
			
			done = true;
		   }
	}

	private void addNode(final DeltaMinerNode...nodes) {
		   
	        for(DeltaMinerNode node : nodes) {
	            if(!this.nodeList.contains(node)) {
	                this.nodeList.add(node);
	            }
	        }
	    }

	   class main extends DeltaMinerNode{

		public main(ClientContext ctx) {
			super(ctx);
		}

		@Override
		public boolean activate() {
			return !oreTime;
		}
		
		@Override
		public void execute() {//1504 - rs guide
			calcAntiPattern();
			
			//For when we must deal with the ore
			if(Method.backPackIsFull()){
				oreTime = true;
			}
			
			//Necessary for counting ores mined
			if(invCount==null)
			 invCount = ctx.inventory.select();
			
			//Mines the ore
			if(atLocation())
			if(ctx.players.local().animation()==-1 && 
					!ctx.players.local().inMotion()){
				for(int i : rockType){
					state = "Selecting new rock : " + i;
					if(Method.objIsNotNull(i)){
						selectRock(i);
					break;
					}
				}
			}
			//Updates status
			if(ctx.players.local().animation()==625)
				state = "Mining";
			
			int num  =ctx.inventory.select().size();
			//Counts ore mined
			if(invCount.size()< num){
				 System.out.println("invCount: "+ invCount.size() + "  actual amount " + num );
				oreMined++;
				invCount = ctx.inventory.select();
			
			}
		}

		private boolean atLocation() {
			if(ctx.players.local().tile().distanceTo(mineLocation)>10){
				ctx.movement.step(mineLocation);
			}
			return true;
		}

		private void selectRock(int id) {
			for(GameObject rock: ctx.objects.select().id(id).nearest().first()){
				rock.interact("Mine");
				Method.sleep(Random.nextInt(600,1200));
			}
			
		}

		   
	   }
	   class bank extends DeltaMinerNode{

			public bank(ClientContext ctx) {
				super(ctx);
			}

			@Override
			public boolean activate() {
				return oreTime;
			}
			
			@Override
			public void execute() {
				calcAntiPattern();
				state = "Dropping ores";
				if(!Method.inventoryContains(436))//copper ore
					oreTime = false;//go mine more
				
			   Method.interactInventory(436	,"Drop", "Copper ore");
			  
			}

			   
		   }
	  
	   private boolean calcAntiPattern() {
			int number = Random.nextInt(0,15);
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
		
	if(start){
		mouseX = (int) ctx.mouse.getLocation().getX();
		mouseY = (int) ctx.mouse.getLocation().getY();
		setMouse(g);
		g.setFont(myStateFont);
		g.setFont(myFont);
		long time = runTime - System.currentTimeMillis();
		
		g.setColor(Color.magenta);
		g.drawString("Runtime: " + Method.format(time), 63,55);
		g.drawString("" +version, 63,75);
		g.drawString(""+state, 63,95);
		g.drawString("Oretime? "+(oreTime ?"Interacting with ore": "Mining more ore"), 63,125);
		
		g.drawString("Ore mined: " + oreMined, 63,145);
	}
		
		
	}


	
	

}
