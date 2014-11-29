package astar.copy;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import astar.types.copy.DeltaEdge;
import astar.types.copy.DeltaGraph;
import astar.types.copy.DeltaNode;
import astar.wrappers.copy.DeltaNodePath;



/**
 * Author: Tom
 * Date: 07/05/12
 * Time: 17:11
 */
public class DeltaAStar {

	public static DeltaNodePath<DeltaNode> findPath(DeltaGraph graph, DeltaNode start, DeltaNode end) {
		PriorityQueue<DeltaNode> open = new PriorityQueue<DeltaNode>();
		Set<DeltaNode> closed = new HashSet<DeltaNode>();
		graph.resetNodes(end);
		open.add(start);
		while (open.size() > 0) {
			DeltaNode current = open.poll();
			closed.add(current);
			
			//System.out.println("current : " + open.size() + " and end tile is : " + open.poll());
			
			if (current.equals(end)) {
				DeltaNodePath<DeltaNode> np = new DeltaNodePath<DeltaNode>();
				//System.out.println("Made a path!!!!!!!");
				while (current!=null){
					np.add(current);
					current=current.getParent();
				}
				return np;
			}
			for (DeltaEdge e :current.getEdges()) {
				if(!e.canUse()){
					continue;
				}
				double currentCost = current.getCurrentCost() + e.getCost();
				DeltaNode target = e.getTarget();
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
