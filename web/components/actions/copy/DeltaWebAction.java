package web.components.actions.copy;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Path;

/**
 * Author: Tom
 * Date: 11/06/12
 * Time: 23:25
 */
public abstract class DeltaWebAction extends PollingScript<ClientContext>{

	protected DeltaWebAction(final String name, final Tile source, final Tile target) {
		this.name = name;
		this.source = source;
		this.target = target;
	}

	protected final Tile   source;
	protected final Tile   target;
	protected final String name;

	public abstract boolean canDoAction();

	public abstract boolean doAction();

	public abstract double getCost();

	public Tile getSource() {
		return source;
	}

	public Tile getTarget() {
		return target;
	}
/*
	public boolean clickSceneEntity(SceneObject io, String action, String name) {
		Timer failSafe = new Timer(10000);
		ActiveScript script = Context.get().getActiveScript();
		if (script == null) {
			return false;
		}
		while (script.isRunning() && failSafe.isRunning()) {
			if (io.isOnScreen()) {
				if (io.interact(action, name)) {
					for (int i = 0; i < 10; i++) {
						if (Players.getLocal().getAnimation() == -1) {
							break;
						}
						Time.sleep(100, 300);
					}
					return true;
				}
			} else {
				if (Players.getLocal().isMoving()) {
					Time.sleep(100, 300);
				} else {
					Walking.walk(io);
					for (int i = 0; i < 10; i++) {
						if (Players.getLocal().isMoving()) {
							break;
						}
						Time.sleep(100, 300);
					}
				}
			}
		}
		return false;
	}*/
	public void sleep(int millis){
		try {
			Thread.sleep(Math.max(5, (int) (millis * Random.nextDouble(0.85, 1.5))));
		} catch (InterruptedException ignored) {
		}
	}

	public boolean walkToTile(Tile target) {
		//System.out.println("Walking towards target " + target);
		////if(!ctx.movement.findPath(target).traverse()){
		//ctx.movement.step(target);
		//}
		//sleep(Random.nextInt(200, 1200));
		
		//Timer failSafe = new Timer(30000);
		//PollingScript script = ctx.client(.get().getActiveScript();
		
		Path p = ctx.movement.findPath(target);
			if (!ctx.players.local().inMotion() || (ctx.movement.distance(ctx.movement.destination()) < 8 &&
					ctx.movement.distance(ctx.movement.destination(),
			                                                                                                                      target) > 5)) {
				if (p == null || !p.valid()) {
					ctx.movement.step(target);
					p = ctx.movement.findPath(target);
				} else {
					p.traverse();
				}
			}
			if (ctx.movement.distance(target) < 5) {
				return true;
			}
		return false;
	}

	@Override
	public String toString() {
		return name;
	}
}
