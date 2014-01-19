package features;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import org.powerbot.script.wrappers.Tile;
import org.powerbot.script.wrappers.TilePath;

import src.pathfinder.core.util.Structure;
import src.pathfinder.impl.Pathfinder;



public class Web extends MethodProvider {
	
	private final Pathfinder pathfinder;
	private final TilePath path;
	
	public Web(final MethodContext ctx, final Tile start, final Tile end) {
		super(ctx);
		pathfinder = new Pathfinder();
		path = toPath(pathfinder.findPath(Structure.TILE.getHash(start.x, start.y, start.plane), Structure.TILE.getHash(end.x, end.y, end.plane), 1000, false));
	}
	
	public TilePath getPath() {
		return path;
	}
	
	public Tile getEnd() {
		return path.getEnd();
	}
	
	public boolean walk() {
		return path.traverse();
	}
	
	private Tile toTile(int hash) {
		return new Tile(Structure.TILE.getX(hash), Structure.TILE.getY(hash), Structure.TILE.getZ(hash));
	}
	
	private TilePath toPath(final src.pathfinder.core.wrapper.TilePath tp) {
		Tile[] arr = {};
		if(tp!=null)
			arr = new Tile[tp.size()];
		
		for(int i = 0; i < arr.length; i++) {
			arr[i] = toTile(tp.get(i).getHash());
		}
		return ctx.movement.newTilePath(arr);
	}

}