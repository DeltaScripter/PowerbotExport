package web.components.actions.basic;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import web.components.actions.WebAction;



/**
 * Author: Tom
 * Date: 11/06/12
 * Time: 23:54
 */
public class WalkAction extends WebAction {

	public WalkAction(ClientContext ctx, final Tile source, final Tile target) {
		super(ctx,"Walk",source, target);
	}

	@Override
	public boolean canDoAction() {
		return true;
	}

	@Override
	public boolean doAction() {
		for (int i = 0; i < 5; i++) {
			if (walkToTile(target)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public double getCost() {
		return source.distanceTo(target);
	}


	
}
