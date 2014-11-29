package web.components.lines;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

import web.components.actions.WebAction;


/**
 * Author: Tom
 * Date: 11/06/12
 * Time: 23:32
 */
public class WebComponent extends ClientAccessor{

	private final Tile      tileA;
	private final Tile      tileB;
	private final WebAction actionA;
	private final WebAction actionB;

	public WebComponent(ClientContext ctx, final Tile tileA, final Tile tileB, final WebAction actionA, final WebAction actionB) {
		super(ctx);
		this.tileA = tileA;
		this.tileB = tileB;
		this.actionA = actionA;
		this.actionB = actionB;
	}

	public Tile getTileA() {
		return tileA;
	}

	public Tile getTileB() {
		return tileB;
	}

	public WebAction getActionA() {
		return actionA;
	}

	public WebAction getActionB() {
		return actionB;
	}
}
