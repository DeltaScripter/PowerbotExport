package astar.interfaces;

import astar.types.Node;


/**
 * Author: Tom
 * Date: 11/06/12
 * Time: 22:28
 */
public interface Heuristic<T extends Node> {

	public double getCost(T source,T target);
}