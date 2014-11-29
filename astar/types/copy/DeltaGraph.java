package astar.types.copy;

import java.util.ArrayList;

import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;

import astar.interfaces.copy.DeltaHeuristic;





/**
 * Author: Tom
 * Date: 11/06/12
 * Time: 22:28
 */
public abstract class DeltaGraph extends ClientAccessor{

	private final ClientContext script;
	private final String       name;

	private boolean debugWeb = false;

	public DeltaGraph(ClientContext ctx, final ClientContext script, final String name) {
		super(ctx);
		this.script = script;
		this.name = name;
	}

	private final ArrayList<DeltaNode> nodes = new ArrayList<DeltaNode>();

	private DeltaHeuristic heuristic;

	public final DeltaHeuristic getHeuristic() {
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

	public final void addNode(final DeltaNode node) {
		nodes.add(node);
	}

	public final void setHeuristic(DeltaHeuristic h) {
		this.heuristic = h;
	}

	public final ArrayList<DeltaNode> getNodes() {
		return nodes;
	}

	public final void resetNodes(final DeltaNode target) {
		for (DeltaNode n : nodes) {
			n.reset(target);
		}
	}
}
