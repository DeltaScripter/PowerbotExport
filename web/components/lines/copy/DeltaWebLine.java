package web.components.lines.copy;

import org.powerbot.script.Tile;

import web.components.actions.basic.copy.DeltaWalkAction;


/**
 * Author: Tom
 * Date: 11/06/12
 * Time: 23:59
 */
public class DeltaWebLine extends DeltaWebComponent{
	public DeltaWebLine(final Tile tileA, final Tile tileB) {
		super(tileA, tileB,new DeltaWalkAction(tileA,tileB), new DeltaWalkAction(tileB,tileA));
	}
}
