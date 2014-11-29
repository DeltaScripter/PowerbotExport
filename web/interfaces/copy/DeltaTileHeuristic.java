package web.interfaces.copy;

import web.components.base.copy.DeltaWebNode;
import astar.interfaces.copy.DeltaHeuristic;




/**
 * Author: Tom
 * Date: 12/06/12
 * Time: 00:09
 */
public class DeltaTileHeuristic implements DeltaHeuristic<DeltaWebNode>{
	@Override
	public double getCost(final DeltaWebNode source, final DeltaWebNode target) {
		return source.getTile().distanceTo(target.getTile());
	}
}
