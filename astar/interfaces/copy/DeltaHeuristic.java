package astar.interfaces.copy;

import astar.types.copy.DeltaNode;



/**
 * Author: Tom
 * Date: 11/06/12
 * Time: 22:28
 */
public interface DeltaHeuristic<T extends DeltaNode> {

	public double getCost(T source,T target);
}