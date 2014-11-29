package web.components.lines.copy;

import org.powerbot.game.api.wrappers.Tile;
import pathfinding.web.components.actions.entity.DoorAction;

/**
 * Author: Tom
 * Date: 11/06/12
 * Time: 23:52
 */
public class DeltaWebDoor extends DeltaWebComponent{
	public DeltaWebDoor(final String name,final Tile tileA, final Tile tileB,final int doorId) {
		super(tileA, tileB, new DeltaDoorAction(name,tileA,tileB,doorId),new DeltaDoorAction(name,tileB,tileA,doorId));
	}
}
