package web.interfaces;

import web.components.base.WebNode;
import astar.interfaces.Heuristic;



/**
 * Author: Tom
 * Date: 12/06/12
 * Time: 00:09
 */
public class TileHeuristic implements Heuristic<WebNode>{
	@Override
	public double getCost(final WebNode source, final WebNode target) {
		return source.getTile().distanceTo(target.getTile());
	}
}
