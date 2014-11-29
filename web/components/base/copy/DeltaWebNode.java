package web.components.base.copy;

import org.powerbot.script.Tile;

import astar.types.copy.DeltaGraph;
import astar.types.copy.DeltaNode;



/**
 * Author: Tom
 * Date: 11/06/12
 * Time: 23:14
 */
public class DeltaWebNode extends DeltaNode {


	private final Tile tile;

	public DeltaWebNode(final DeltaGraph container, Tile a) {
		super(container);
		this.tile = a;
	}

	public Tile getTile() {
		return tile;
	}

	@Override
	public boolean equals(final Object o) {
		if(o instanceof DeltaWebNode){
			DeltaWebNode wb = (DeltaWebNode) o;
			return wb.getTile().equals(tile);
		}
		return false;
	}

	@Override
	public String toString(){
		return "("+tile.x()+","+tile.y()+","+tile.floor()+")";
	}
}
