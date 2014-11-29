package web.components.lines;

import org.powerbot.script.Tile;

import web.components.actions.basic.WalkAction;

/**
 * Author: Tom
 * Date: 11/06/12
 * Time: 23:59
 */
public class WebLine extends WebComponent{
	public WebLine(final Tile tileA, final Tile tileB) {
		super(tileA, tileB,new WalkAction(tileA,tileB), new WalkAction(tileB,tileA));
	}
}
