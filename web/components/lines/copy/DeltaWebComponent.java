package web.components.lines.copy;

import org.powerbot.script.Tile;

import web.components.actions.copy.DeltaWebAction;



/**
 * Author: Tom
 * Date: 11/06/12
 * Time: 23:32
 */
public class DeltaWebComponent {

	private final Tile      tileA;
	private final Tile      tileB;
	private final DeltaWebAction actionA;
	private final DeltaWebAction actionB;

	public DeltaWebComponent(final Tile tileA, final Tile tileB, final DeltaWebAction actionA, final DeltaWebAction actionB) {
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

	public DeltaWebAction getActionA() {
		return actionA;
	}

	public DeltaWebAction getActionB() {
		return actionB;
	}
}
