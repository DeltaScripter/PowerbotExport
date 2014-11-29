package astar;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import astar.types.Edge;
import astar.types.Graph;
import astar.types.Node;
import astar.wrappers.NodePath;



/**
 * Author: Tom
 * Date: 07/05/12
 * Time: 17:11
 */
public class AStar {

	public static NodePath<Node> findPath(Graph graph, Node start, Node end) {
		PriorityQueue<Node> open = new PriorityQueue<Node>();
		Set<Node> closed = new HashSet<Node>();
		graph.resetNodes(end);
		open.add(start);
		while (open.size() > 0) {
			Node current = open.poll();
			closed.add(current);
			
			//System.out.println("current : " + open.size() + " and end tile is : " + open.poll());
			
			if (current.equals(end)) {
				NodePath<Node> np = new NodePath<Node>();
				//System.out.println("Made a path!!!!!!!");
				while (current!=null){
					np.add(current);
					current=current.getParent();
				}
				return np;
			}
			for (Edge e :current.getEdges()) {
				if(!e.canUse()){
					continue;
				}
				double currentCost = current.getCurrentCost() + e.getCost();
				Node target = e.getTarget();
				if(closed.contains(target)){
					continue;
				}
				if (target.getParent() == null||target.getCost()>currentCost) {
					target.setParent(e);
					open.add(target);
				}
			}
		}
		return null;
	}


}
