package astar.types.copy;

import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;




/**
 * Author: Tom
 * Date: 11/06/12
 * Time: 22:26
 */
public abstract class DeltaEdge extends ClientAccessor{

	private final DeltaNode source;
	private final DeltaNode target;

	public DeltaEdge(ClientContext ctx,final DeltaNode source, final DeltaNode target) {
		super(ctx);
		this.source = source;
		this.target = target;
	}

	public abstract boolean canUse();
	public abstract double getCost();

	public final DeltaNode getSource() {
		return source;
	}

	public final DeltaNode getTarget() {
		return target;
	}

}
