package shieldCollector;

import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Random;
import org.powerbot.script.Script;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GeItem;




@Script.Manifest(name = "Delta Shield Collector", 
description = "Talks to the duke in Lumbridge to get anti-dragon shields and banks them for money", properties = "topic = 1231125")

public class ShieldMainBody extends PollingScript<ClientContext> implements PaintListener{

	public static String state = "Welcome..";
	public static int stringCount = 0;
	
	private boolean start = true;//the boolean that allows the onStart method to run once
	
	public final List<ShieldNode> nodeList = Collections.synchronizedList(new ArrayList<ShieldNode>());
	Random rand = new Random();
	public static boolean antiPattern = false;
	ShieldMethod m = new ShieldMethod(ctx);
	
	
	public static long runTime;
	public static long timeCycle = 0;
	
	public static int amount = 0;
	public static boolean useKeyBoard = false;
	private int SHIELD = 1540;
	private int GEPrice = 900;
	
	
	private void onStart() {
		
		if(start){
			
			try{
				GEPrice = GeItem.price(SHIELD);
				System.out.println("Setting shield price to : " + GEPrice);
			}catch(Exception e){System.out.println("There was a problem getting the Grand Exchange price of shields");}
			
			if(Random.nextInt(1, 4)>1){
				useKeyBoard = true;
				System.out.println("Using keyboard");
			}
			
	     runTime = System.currentTimeMillis();
			 
		 //System.out.println("Adding node");
		 addNode(new ShieldStuff(ctx));
		 addNode(new ShieldGoBank(ctx));
		 addNode(new ShieldAntipattern(ctx));
		 start = false;
		}
	}
	@Override
	public void poll() {
		
		
		calcAntiPattern();
		
		onStart();
		if(ctx.game.floor()==0){
			m.state("Please start the script in Lumbridge bank");
			m.interactO(36776, "", "Stairs");
		}else
		if(!start){
		for(ShieldNode node: nodeList){
			if(node.activate()){
				node.execute();
				
				 
			}
		  }
		}
	}
	   private boolean calcAntiPattern() {
				int number = rand.nextInt(0,14);
				if(number == 1){
					System.out.println("Telling it to perform antipattern again: " + number);
					antiPattern = true;
					return true;
				}
				return false;
			}
	  private void addNode(final ShieldNode...nodes) {
		   
	        for(ShieldNode node : nodes) {
	            if(!this.nodeList.contains(node)) {
	                this.nodeList.add(node);
	            }
	        }
	    }
	   double phour;
	   double moneyphour;
	  @Override
		public void repaint(Graphics g) {
		  
			long time = runTime - System.currentTimeMillis();
			
		 // long perHour = 0;
		  
		  //for finding money per hour
		    //moneyphour = (time/1000);//convert to seconds
		    //moneyphour = ((amount*66)/moneyphour);
		   // moneyphour = moneyphour*800;
		  
		//  if(timeCycle>0){
		 // perHour = (((timeCycle/1000)/60))*28;
		//  perHour = perHour/60;
		//  }
			//below calcs the total profit in bank and formats it for presentation
			String moneyNum = ""+(stringCount * GEPrice);
			String moneyO = "unknown amount";
			if(moneyNum.length()==4){
				DecimalFormat formatter = new DecimalFormat("#,###");
				moneyO = formatter.format((stringCount * GEPrice));
			}else if(moneyNum.length()==5){
				DecimalFormat formatter = new DecimalFormat("##,###");
				moneyO = formatter.format((stringCount * GEPrice));
			}else if(moneyNum.length()==6){
				DecimalFormat formatter = new DecimalFormat("###,###");
				moneyO = formatter.format((stringCount * GEPrice));
			}else if(moneyNum.length()==7){
				DecimalFormat formatter = new DecimalFormat("#,###,###");
				moneyO = formatter.format((stringCount * GEPrice));
			}
		  g.setColor(Color.GREEN);
		  g.drawString("Runtime: " + m.format(time), 9,80);
			g.drawString(""+state, 9,100);
			
			g.drawString("Shields In Bank: "+stringCount + " total profit of: " + moneyO + " GP" , 9,120);
			//g.drawString("Time it took: " + moneyphour, 9, 140);
			
			
			
			String moneyNumHR = ""+(stringCount * GEPrice);
			String moneyOHR = "unknown amount";
			
			long perHour = ((timeCycle/1000)/60);//into minutes
			//System.out.println("Minutes are : " + perHour);
			if(perHour<0){perHour = perHour*-1;}
			
			if(perHour>0){
			perHour = 60/perHour;//how many trips per hour
			perHour = perHour*(28*GEPrice);
			}
			
			//System.out.println("perHour is : " + perHour);
			if(moneyNumHR.length()==4){
				DecimalFormat formatter = new DecimalFormat("#,###");
				moneyOHR = formatter.format((perHour));
			}else if(moneyNumHR.length()==5){
				DecimalFormat formatter = new DecimalFormat("##,###");
				moneyOHR = formatter.format((perHour));
			}else if(moneyNumHR.length()==6){
				DecimalFormat formatter = new DecimalFormat("###,###");
				moneyOHR = formatter.format((perHour));
			}else if(moneyNumHR.length()==7){
				DecimalFormat formatter = new DecimalFormat("#,###,###");
				moneyOHR = formatter.format((perHour));
			}
			System.out.println("After formatting it is : " + moneyOHR);
			if(stringCount>0 ){
				g.drawString("GP/HR: " + moneyOHR+ " GP", 9, 140);
			}else 	g.drawString("GP/HR: Must wait until we bank atleast once..", 9, 140);
			//g.drawString("Time it took: " + m.format(timeCycle), 9, 140);
			//g.drawString("Shields per hour: " + perHour, 9, 160);
			
			
		}

}
