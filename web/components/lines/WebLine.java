package web.components.lines;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import web.components.actions.basic.WalkAction;

/**
 * Author: Tom
 * Date: 11/06/12
 * Time: 23:59
 */
public class WebLine extends WebComponent{
	public WebLine(ClientContext ctx,final Tile tileA, final Tile tileB) {
		super(ctx,tileA, tileB,new WalkAction(ctx,tileA,tileB), new WalkAction(ctx,tileB,tileA));
	}
}
