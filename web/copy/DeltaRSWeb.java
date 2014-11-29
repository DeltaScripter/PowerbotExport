package web.copy;

import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Path;

import web.components.base.copy.DeltaWebEdge;
import web.components.base.copy.DeltaWebNode;
import web.components.base.copy.DeltaWebPath;
import web.components.lines.copy.DeltaWebComponent;
import web.data.copy.DeltaFTPLines;
import web.interfaces.copy.DeltaTileHeuristic;
import astar.copy.DeltaAStar;
import astar.types.copy.DeltaGraph;
import astar.types.copy.DeltaNode;
import astar.wrappers.copy.DeltaNodePath;









/**
 * Author: Tom
 * Date: 11/06/12
 * Time: 23:10
 */
public class DeltaRSWeb extends DeltaGraph {

	public DeltaRSWeb(ClientContext script) {
		super(script, script, "RSWeb");
		//System.out.println("Building web");
		setHeuristic(new DeltaTileHeuristic());
		for (DeltaWebComponent wc : DeltaFTPLines.componentArray) {
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

	public final void addWebEdge(final DeltaWebComponent line) {
		DeltaWebNode a = getNode(line.getTileA());
		DeltaWebNode b = getNode(line.getTileB());
		a.addEdge(new DeltaWebEdge(ctx,this, a, b, line.getActionA()));
		b.addEdge(new DeltaWebEdge(ctx,this, b, a, line.getActionB()));
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

	private DeltaWebNode getNode(final Tile tile) {
		DeltaWebNode targ = new DeltaWebNode(this, tile);
		for (DeltaNode n : getNodes()) {
			DeltaWebNode temp = (DeltaWebNode) n;
			if (n.equals(targ)) {
				return temp;
			}
		}
		addNode(targ);
		return targ;
	}

	public final DeltaWebNode getNearestNode(final Tile tile) {
		DeltaWebNode nearest = null;
		double dist = Integer.MAX_VALUE;
		for (DeltaNode n : getNodes()) {
			DeltaWebNode node = (DeltaWebNode) n;
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

	private DeltaNodePath<DeltaNode> getPath(final Tile source,final Tile target) {
		DeltaWebNode start = getNearestNode(source);
		DeltaWebNode end = getNearestNode(target);
		if (start == null || end == null) {
			System.out.println("Failed to find start or end node");
			return null;
		}
		//System.out.println("Nearest node is : " + start);
		if( DeltaAStar.findPath(this, start, end)==null){
			System.out.println("AStar cannot make path");
		}
		
		return DeltaAStar.findPath(this, start, end);
	}

	private DeltaWebPath<DeltaWebEdge> buildWebPath(final DeltaNodePath<DeltaNode> nodes,final  Tile finalTile) {
		DeltaWebPath<DeltaWebEdge> edges = new DeltaWebPath<DeltaWebEdge>(finalTile);
		DeltaWebNode current = (DeltaWebNode) nodes.removeLast();
		while (nodes.size() > 0) {
			DeltaWebNode temp = (DeltaWebNode) nodes.removeLast();
			edges.add((DeltaWebEdge) current.getEdge(temp));
			current = temp;
		}
		return edges;
	}

	public final DeltaWebPath<DeltaWebEdge> getWebPath(final Tile source,final  Tile target) {
		DeltaNodePath<DeltaNode> nodes = getPath(source, target);
		
		return nodes == null ? null : buildWebPath(nodes, target);
	}

	public final DeltaWebPath<DeltaWebEdge> getWebPath(final Tile target) {
		//if(getWebPath(ctx.players.local().tile(), target)==null)
			//System.out.println("path is null");
		return getWebPath(ctx.players.local().tile(), target);
	}

	public final boolean walkToTile(final Tile target) {
		if (target.matrix(ctx).reachable()) {
			return walkTo(target);
		}
		DeltaWebPath path = getWebPath(target);
		return path != null && path.traverse();
	}

	private boolean walkTo(final Tile target) {
	    System.out.println("Walking via findPath NOT NODES");
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
