package astar.types;

import java.util.ArrayList;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

import astar.interfaces.Heuristic;







/**
 * Author: Tom
 * Date: 11/06/12
 * Time: 22:28
 */
public abstract class Graph extends ClientAccessor{

	private final ClientContext script;
	private final String       name;

	private boolean debugWeb = false;

	public Graph(ClientContext ctx, final ClientContext script, final String name) {
		super(ctx);
		this.script = script;
		this.name = name;
	}

	private final ArrayList<Node> nodes = new ArrayList<Node>();

	private Heuristic heuristic;

	public final Heuristic getHeuristic() {
		return heuristic;
	}

	public final boolean isDebugOn() {
		return debugWeb;
	}

	public final void setDebug(final boolean debugWeb) {
		this.debugWeb = debugWeb;
	}

	public final void debugMessage(final String string) {
		if (debugWeb) {
			StringBuilder message = new StringBuilder("[" + name + "] ");
			message.append(string);
			if (script != null) {
				System.out.println(message.toString());
			} else {
				System.out.println(message);
			}
		}
	}

	public final void addNode(final Node node) {
		nodes.add(node);
	}

	public final void setHeuristic(Heuristic h) {
		this.heuristic = h;
	}

	public final ArrayList<Node> getNodes() {
		return nodes;
	}

	public final void resetNodes(final Node target) {
		for (Node n : nodes) {
			n.reset(target);
		}
	}
}
