package web;



import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Path;

import web.components.base.WebEdge;
import web.components.base.WebNode;
import web.components.base.WebPath;
import web.components.lines.WebComponent;
import web.data.FTPLines;
import web.interfaces.TileHeuristic;
import OldQuester.DeltaOldQuester;
import OldQuester.OldMethod;
import astar.AStar;
import astar.types.Graph;
import astar.types.Node;
import astar.wrappers.NodePath;







/**
 * Author: Tom
 * Date: 11/06/12
 * Time: 23:10
 */

public class RSWeb extends Graph {

	
	FTPLines FTPLines = new FTPLines(ctx);
	public RSWeb(ClientContext script) {
		super(script, script, "RSWeb");
		//System.out.println("Building web");
		setHeuristic(new TileHeuristic());
		for (WebComponent wc : FTPLines.componentArray) {
			this.addWebEdge(wc);
		}
		//for (WebComponent wc : Lumbridge.LUMMY_CASTLE) {
			//this.addWebEdge(wc);
	//	}
		//for (WebTeleport[] arr : Teleports.ALL_TELEPORTS) {
		//	for (WebTeleport wt : arr) {
		////		addWebTeleport(wt);
		//	}
		//}
		//System.out.println("Web built");
		ctx = script;
	}
	public static ClientContext ctx;

	public final void setLodestoneUsage(final boolean bool) {
		//LodestoneAction.canUse = bool;
		
	}

	public final void setLodestoneCost(final double cost) {
	//	LodestoneAction.lodestoneCost = cost;
	}

//	public final WebPath<WebEdge> getClosestBankPath() {
	//	WebPath<WebEdge> best = null;
		//WebBank bank = null;
	//	double cost = Integer.MAX_VALUE;
		//for (WebBank b : Banks.bankArray) {
			//WebPath<WebEdge> path = getWebPath(b.getTile());
			//double temp;
			//if (path != null && (temp = path.getCost()) < cost) {
			//	bank = b;
			//	best = path;
			//	cost = temp;
		//	}
		//}
	//	System.out.println(bank==null?"Unable to find bank":"Closest bank: " + bank.toString());
	//	return best;
	//}

//	public final WebPath<WebEdge> getPathToBank(final WebBank bank) {
	//	return getWebPath(bank.getTile());
	//}

	public final void addWebEdge(final WebComponent line) {
		WebNode a = getNode(line.getTileA());
		WebNode b = getNode(line.getTileB());
		a.addEdge(new WebEdge(ctx,this, a, b, line.getActionA()));
		b.addEdge(new WebEdge(ctx,this, b, a, line.getActionB()));
	}

	//public final void addWebTeleport(final WebTeleport line) {
	//	addWebTeleport(line,false);
	//}

	//public final void addWebTeleport(final WebTeleport line,final  boolean newNode) {
	//	Tile target = line.getTileA();
	//	WebNode nearest;
	//	if (newNode) {
	//		nearest = getNode(target);
	//	} else {
	//		nearest = getNearestNode(target);
	//	}
	//	for (Node n : getNodes()) {
	//		n.addEdge(new WebEdge(this, (WebNode) n, nearest, line.getActionA()));
	//	}
	//}

	private WebNode getNode(final Tile tile) {
		WebNode targ = new WebNode(this, tile);
		for (Node n : getNodes()) {
			WebNode temp = (WebNode) n;
			if (n.equals(targ)) {
				return temp;
			}
		}
		addNode(targ);
		return targ;
	}

	public final WebNode getNearestNode(final Tile tile) {
		WebNode nearest = null;
		double dist = Integer.MAX_VALUE;
		for (Node n : getNodes()) {
			WebNode node = (WebNode) n;
			if (node.getTile().floor() != tile.floor()) {
				continue;
			}
			double temp = node.getTile().distanceTo(tile);
			if (temp < dist) {
				nearest = node;
				dist = temp;
			}
		}
		return nearest;
	}

	private NodePath<Node> getPath(final Tile source,final Tile target) {
		WebNode start = getNearestNode(source);
		WebNode end = getNearestNode(target);
		if (start == null || end == null) {
			System.out.println("Failed to find start or end node");
			return null;
		}
		//System.out.println("Nearest node is : " + start);
		if( AStar.findPath(this, start, end)==null){
			System.out.println("AStar cannot make path");
		}
		
		return AStar.findPath(this, start, end);
	}

	private WebPath<WebEdge> buildWebPath(final NodePath<Node> nodes,final  Tile finalTile) {
		WebPath<WebEdge> edges = new WebPath<WebEdge>(finalTile);
		WebNode current = (WebNode) nodes.removeLast();
		while (nodes.size() > 0) {
			WebNode temp = (WebNode) nodes.removeLast();
			edges.add((WebEdge) current.getEdge(temp));
			current = temp;
		}
		return edges;
	}

	public final WebPath<WebEdge> getWebPath(final Tile source,final  Tile target) {
		NodePath<Node> nodes = getPath(source, target);
		
		return nodes == null ? null : buildWebPath(nodes, target);
	}

	public final WebPath<WebEdge> getWebPath(final Tile target) {
		//if(getWebPath(ctx.players.local().tile(), target)==null)
			//System.out.println("path is null");
		return getWebPath(ctx.players.local().tile(), target);
	}
	OldMethod Method = new OldMethod(ctx);
	final int[] bounds = {112, 136, -220, 32, -148, 116};
	
	public final boolean walkToTile(final Tile target) {
		try{//imideately below hanles the Burthorpe gate
		if(Method.tileDisctanceToPlayer(new Tile(2937,3451,0))<10 &&
				Method.objIsNotNull(7408)){//gate at burhorpe
		
			Method.preciseInteractO(7408, bounds, "");
		}else{//if Burthhorpe gate is not in our way..
		
		if (target.matrix(ctx).reachable()) {
			return walkTo(target);
		}

		WebPath path = getWebPath(target);
		return path != null && path.traverse();
		}
		
		}catch(Exception e){}
		
		 return false;
		
	}

	public final boolean walkCreatedPath(final WebPath thePath, String state) {
		DeltaOldQuester.state = state;
		System.out.println("First node is " + thePath.getFirst());
		return thePath != null && thePath.traverse();
	}
	
	private boolean walkTo(final Tile target) {
	   // System.out.println("Walking via findPath NOT NODES");
		Path p = ctx.movement.findPath(target);
			if (!ctx.players.local().inMotion() || (ctx.movement.distance(ctx.movement.destination()) < 8 && ctx.movement.distance(ctx.movement.destination(),
			                                                                                                                      target) > 5)) {
				if (p == null || !p.valid()) {
					ctx.movement.step(target);
					p = ctx.movement.findPath(target);
				} else {
					p.traverse();
				}
			}
			if (ctx.movement.distance(target) < 5) {
				return true;
			}
		
		return false;
	}

}
