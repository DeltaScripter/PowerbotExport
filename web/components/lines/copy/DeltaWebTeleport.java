package web.components.lines.copy;

import org.powerbot.game.api.wrappers.Tile;
import pathfinding.web.components.actions.WebAction;

/**
 * Author: Tom
 * Date: 12/06/12
 * Time: 14:53
 */
public class DeltaWebTeleport extends DeltaWebComponent{

	public DeltaWebTeleport (final Tile tileA, final DeltaWebAction actionA) {
		super(tileA, null, actionA, null);
	}
}
