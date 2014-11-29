package astar.types.copy;

import java.util.LinkedList;

/**
 * Author: Tom
 * Date: 11/06/12
 * Time: 22:25
 */
public abstract class DeltaNode implements Comparable<DeltaNode> {

	private final DeltaGraph container;
	private final LinkedList<DeltaEdge> edges = new LinkedList<DeltaEdge>();

	private DeltaNode parent;

	private double heuristicCost;

	public DeltaNode(final DeltaGraph container) {
		this.container = container;
	}

	public final DeltaGraph getContainer() {
		return container;
	}

	public final LinkedList<DeltaEdge> getEdges() {
		return edges;
	}

	public final DeltaEdge getEdge(final DeltaNode node) {
		for (DeltaEdge e : edges) {
			if (e.getTarget().equals(node)) {
				return e;
			}
		}
		return null;
	}

	public final void addEdge(final DeltaEdge e) {
		edges.add(e);
	}

	public final void reset(final DeltaNode target) {
		heuristicCost = container.getHeuristic().getCost(this, target);
		parent = null;
	}

	public final double getCurrentCost() {
		return parent == null ? 0 : parent.getCurrentCost();
	}

	public final double getHeuristicCost() {
		return heuristicCost;
	}

	public final double getCost() {
		return getHeuristicCost() + getCurrentCost();
	}

	public final DeltaNode getParent() {
		return parent;
	}

	public final void setParent(final DeltaEdge edge) {
		this.parent = edge.getSource();
	}

	@Override
	public final int compareTo(final DeltaNode o) {
		double dist = getCost() - o.getCost();
		return dist == 0 ? 0 : dist < 0 ? -1 : 1;
	}

	@Override
	public abstract boolean equals(final Object o);


}
