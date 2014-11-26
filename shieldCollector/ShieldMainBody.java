package shieldCollector;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Random;
import org.powerbot.script.Script;
import org.powerbot.script.rt6.ClientContext;




@Script.Manifest(name = "Delta Dragon Shield Collector", 
description = "Talks to the duke in Lumbridge to get anti-dragon fire shields and banks them", properties = "topic = 1231125")

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
	
	
	private void onStart() {
		
		if(start){
			
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
	  @Override
		public void repaint(Graphics g) {
		  
			long time = runTime - System.currentTimeMillis();
			
		  long perHour = 0;
		  
		  if(timeCycle>0){
		  perHour = (((timeCycle/1000)/60))*28;
		  perHour = perHour/60;
		  }
		  g.setColor(Color.GREEN);
		  g.drawString("Runtime: " + m.format(time), 9,80);
			g.drawString(""+state, 9,100);
			g.drawString("Shields In Bank: "+stringCount, 9,120);
			//g.drawString("Time it took: " + m.format(timeCycle), 9, 140);
			//g.drawString("Shields per hour: " + perHour, 9, 160);
			
			
		}

}
