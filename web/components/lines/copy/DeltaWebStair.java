package web.components.lines.copy;

import org.powerbot.game.api.wrappers.Tile;
import pathfinding.web.components.actions.entity.StairAction;

/**
 * Author: Tom
 * Date: 14/06/12
 * Time: 19:14
 */
public class DeltaWebStair extends DeltaWebComponent{
	public DeltaWebStair(final Tile tileA, final Tile tileB,final String nameA,final String nameB,final String actionA,final String actionB,final int stairIdA, final int stairIdb) {
		super(tileA, tileB, new DeltaStairAction(nameA,tileA,tileB,actionA,stairIdA), new DeltaStairAction(nameB,tileB,tileA,actionB,stairIdb));
	}
}
