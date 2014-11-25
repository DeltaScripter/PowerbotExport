package toadCollector;

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
import org.powerbot.script.rt6.TileMatrix;






@Script.Manifest(name = "Delta Toad Grabber", 
description = "Gathers toads at the swamp in Tree Gnome Stronghold and banks", properties = "topic = 1230952")

public class ToadMainBody extends PollingScript<ClientContext> implements PaintListener{

	public static String state = "Welcome..";
	public static int stringCount = 0;
	
	private boolean start = true;//the boolean that allows the onStart method to run once
	
	public final List<toadNode> nodeList = Collections.synchronizedList(new ArrayList<toadNode>());
	Random rand = new Random();
	public static boolean antiPattern = false;
	toadMethod m = new toadMethod(ctx);

	private void onStart() {
		
		if(start){
		 //System.out.println("Adding node");
		 addNode(new toadStuff(ctx));
		 addNode(new ToadGoBank(ctx));
		 addNode(new toadAntipattern(ctx));
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
		for(toadNode node: nodeList){
			if(node.activate()){
				node.execute();
				
				 
			}
		  }
		}
	}
	   private boolean calcAntiPattern() {
				int number = rand.nextInt(0,9);
				if(number == 1){
					antiPattern = true;
					return true;
				}
				return false;
			}
	  private void addNode(final toadNode...nodes) {
		   
	        for(toadNode node : nodes) {
	            if(!this.nodeList.contains(node)) {
	                this.nodeList.add(node);
	            }
	        }
	    }
	  private int TOAD = 2150;
	  @Override
		public void repaint(Graphics g) {
		  g.setColor(Color.green);
			g.drawString(""+state, 9,91);
			//g.drawString("String in bank: "+stringCount, 9,96);
			
			if(m.gItemIsNotNull(TOAD) && m.getGroundItem(TOAD).tile().matrix(ctx).reachable()&&
					m.getGroundItem(TOAD).tile().matrix(ctx).inViewport()){
				TileMatrix player = ctx.players.local().tile().matrix(ctx);
				g.drawLine(player.centerPoint().x,player.centerPoint().y, m.getGroundItem(TOAD).centerPoint().x,m.getGroundItem(TOAD).centerPoint().y);
			}
			
		}

}
