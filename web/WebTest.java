package web;

import org.powerbot.bot.rt6.client.LinkedList;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Random;
import org.powerbot.script.Script;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import web.components.base.WebEdge;
import web.components.base.WebPath;



/**
 * Author: Tom
 * Date: 12/06/12
 * Time: 00:38
 */

public class WebTest extends PollingScript<ClientContext>{

	public static void main(String[] args) {
		RSWeb web = new RSWeb(null);
		web.setDebug(true);
		Tile start = new Tile(3295, 3177, 0);
		Tile end = new Tile(2949, 3439, 0);
		LinkedList edges = (LinkedList) web.getWebPath(start, end);
		if (edges != null) {
			web.debugMessage("With lodestones enabled");
			//for (WebEdge e : edges) {
			//	web.debugMessage(e.toString());
			//}
		}
		web.setLodestoneUsage(false);
		edges = (LinkedList) web.getWebPath(start, end);
		if (edges != null) {
			web.debugMessage("With lodestones disabled");
			//for (WebEdge e : edges) {
			//	web.debugMessage(e.toString());
			//}
		}

	}
  Tile dest = new Tile(-1,-1,-1);
	@Override
	public void poll() {/*
		RSWeb web = new RSWeb(null);
		web.setDebug(true);
		Tile start = new Tile(3142,3294,0);
		Tile end = new Tile(3115,3209,0);
		LinkedList edges = (LinkedList) web.getWebPath(start, end);
		if (edges != null) {
			web.debugMessage("With lodestones enabled");
			//for (WebEdge e : edges) {
			//	web.debugMessage(e.toString());
			//}
		}
		web.setLodestoneUsage(false);
		edges = (LinkedList) web.getWebPath(start, end);
		if (edges != null) {
			web.debugMessage("With lodestones disabled");
			//for (WebEdge e : edges) {
			//	web.debugMessage(e.toString());
			//}
		}
		
		*/
		
		//below method is for getting new tiles for the web!!
		///*
		if(ctx.movement.destination().matrix(ctx).reachable()&&
				dest!=ctx.movement.destination()&& !ctx.players.local().inMotion()){
		    Tile prev = dest;
			dest = ctx.movement.destination();
			//System.out.println("Dest is now : " + dest);  
		System.out.println("new WebLine(new Tile"+prev + ",new Tile" + ctx.movement.destination() + "),");
		sleep(700);
		}
		//*/
		
		
		
		//___________________
		RSWeb webs = new RSWeb(ctx);
	    //System.out.println("Nearest node to you"+ webs.getNearestNode(ctx.players.local().tile()));
	    
	    
		//TEST TILES
		//by birdge at wizard tower : new Tile(3115,3209,0)
		//Lumbridge  = new Tile(3235,3224,0)
		//new Tile(3212, 3431, 0) = varrock square
		//if(!webs.walkToTile(new Tile(3212, 3431, 0))){
			//System.out.println("Can't walk path for some reason");
		//}
	}
	public void sleep(int millis){
		try {
			Thread.sleep(Math.max(5, (int) (millis * Random.nextDouble(0.85, 1.5))));
		} catch (InterruptedException ignored) {
		}
	}
}
