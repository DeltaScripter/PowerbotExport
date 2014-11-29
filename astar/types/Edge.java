package astar.types;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;



/**
 * Author: Tom
 * Date: 11/06/12
 * Time: 22:26
 */
public abstract class Edge extends ClientAccessor{

	private final Node source;
	private final Node target;

	public Edge(ClientContext ctx,final Node source, final Node target) {
		super(ctx);
		this.source = source;
		this.target = target;
	}

	public abstract boolean canUse();
	public abstract double getCost();

	public final Node getSource() {
		return source;
	}

	public final Node getTarget() {
		return target;
	}

}
