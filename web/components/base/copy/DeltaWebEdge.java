package web.components.base.copy;

import org.powerbot.script.rt6.ClientContext;

import web.components.actions.copy.DeltaWebAction;
import astar.types.copy.DeltaEdge;
import astar.types.copy.DeltaGraph;






/**
 * Author: Tom
 * Date: 02/04/12
 * Time: 17:14
 */
public class DeltaWebEdge extends DeltaEdge {

	private final DeltaWebAction action;

	public DeltaWebEdge(ClientContext ctx, final DeltaGraph graph, final DeltaWebNode source, final DeltaWebNode target, DeltaWebAction action) {
		super(ctx,source,target);
		this.action = action;
	}

	public DeltaWebAction getAction() {
		return action;
	}

	@Override
	public boolean canUse() {
		return action.canDoAction();
	}

	@Override
	public double getCost() {
		return action.getCost();
	}

	public boolean doAction() {
		return action.doAction();
	}

	@Override
	public String toString(){
		if(getAction()!=null){
			return getAction()+" : "+ getSource().toString()+" > " + getTarget().toString();
		}
		return "Default"+" : "+ getSource().toString()+" > " + getTarget().toString();
	}



}
