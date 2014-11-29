package web.components.base;

import java.util.LinkedList;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.LocalPath;

import web.RSWeb;


/**
 * Author: Tom
 * Date: 12/06/12
 * Time: 14:59
 */
public class WebPath<T extends WebEdge> extends LinkedList<T> {

	public final Tile endTile;
	public String status = "";
	
	ClientContext ctx;
	
	public WebPath(final Tile endTile) {
		this.endTile = endTile;
		ctx = RSWeb.ctx;
	}

	public boolean traverse() {
		java.util.Iterator<T> a =  this.iterator();
		while (a.hasNext()) {
			T next = a.next();
			status = next.toString();
			LocalPath p = ctx.movement.findPath(endTile);
			if (p != null && p.valid()) {
				break;
			}
			if (!next.doAction()) {
				return false;
			}
		}
		return walkPath(endTile);
	}
	public void sleep(int millis){
		try {
			Thread.sleep(Math.max(5, (int) (millis * Random.nextDouble(0.85, 1.5))));
		} catch (InterruptedException ignored) {
		}
	}
	public double getCost() {
		double cur = 0;
		java.util.Iterator<T> a =  this.iterator();
		while (a.hasNext()) {
			cur += a.next().getCost();
		}
		return cur;
	}

	public boolean walkPath(Tile target) {
		
		LocalPath p = ctx.movement.findPath(target);
			if (!ctx.players.local().inMotion() || (ctx.movement.distance(ctx.movement.destination()) < 8 && ctx.movement.distance(ctx.movement.destination(),
			                                                                                                                      target) > 3)) {
				if (p == null) {
					System.out.println("Now walking VIA nodes!");
					ctx.movement.step(target);
					p = ctx.movement.findPath(target);
				} else {
					p.traverse();
				}
				sleep(Random.nextInt(1200, 2000));
			}
			if (ctx.movement.distance(target) < 5) {

				return true;
			}
		
		return false;
	}

	public String getStatus() {
		return status;
	}
}
