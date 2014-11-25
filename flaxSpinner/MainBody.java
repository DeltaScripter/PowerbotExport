package flaxSpinner;

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






@Script.Manifest(name = "Delta Bow Strings", 
description = "Spins flax into bowstrings in Lummbridge, antipattern included!", properties = "topic = 1230497")

public class MainBody extends PollingScript<ClientContext> implements PaintListener{

	public static String state = "Welcome..";
	public static int stringCount = 0;
	
	private boolean start = true;//the boolean that allows the onStart method to run once
	
	public final List<FlaxNode> nodeList = Collections.synchronizedList(new ArrayList<FlaxNode>());
	Random rand = new Random();
	public static boolean antiPattern = false;
	

	private void onStart() {
		
		if(start){
		 //System.out.println("Adding node");
		 addNode(new FlaxStuff(ctx));
		 addNode(new goBank(ctx));
		 addNode(new flaxAntipattern(ctx));
		 start = false;
		}
	}
	@Override
	public void poll() {
		
		while(ctx.widgets.component(1188, 1).visible()){
			state = "Closing dialogue..";
			ctx.players.local().tile().matrix(ctx).click();
		}
		
		calcAntiPattern();
		
		onStart();
		if(!start){
		for(FlaxNode node: nodeList){
			if(node.activate()){
				node.execute();
				
				 
			}
		  }
		}
	}
	   private boolean calcAntiPattern() {
				int number = rand.nextInt(0,15);
				if(number == 1){
					antiPattern = true;
					return true;
				}
				return false;
			}
	  private void addNode(final FlaxNode...nodes) {
		   
	        for(FlaxNode node : nodes) {
	            if(!this.nodeList.contains(node)) {
	                this.nodeList.add(node);
	            }
	        }
	    }
	  
	  @Override
		public void repaint(Graphics g) {
		  g.setColor(Color.green);
			g.drawString(""+state, 9,91);
			//g.drawString("String in bank: "+stringCount, 9,96);
		}

}
