package web.components.base;

import org.powerbot.script.rt4.ClientContext;

import web.components.actions.WebAction;
import astar.types.Edge;
import astar.types.Graph;





/**
 * Author: Tom
 * Date: 02/04/12
 * Time: 17:14
 */
public class WebEdge extends Edge {

	private final WebAction action;

	public WebEdge(ClientContext ctx, final Graph graph, final WebNode source, final WebNode target, WebAction action) {
		super(ctx,source,target);
		this.action = action;
	}

	public WebAction getAction() {
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
