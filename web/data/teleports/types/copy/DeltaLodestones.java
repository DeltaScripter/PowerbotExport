package web.data.teleports.types.copy;

import org.powerbot.game.api.wrappers.Tile;
import pathfinding.web.components.actions.teleports.LodestoneAction;
import pathfinding.web.components.lines.WebTeleport;

/**
 * Author: Tom
 * Date: 12/06/12
 * Time: 16:00
 */
public class DeltaLodestones {

	public static DeltaWebTeleport LUMBRIDGE_LODESTONE = new DeltaWebTeleport(new Tile(3233, 3211, 0), new DeltaLodestoneAction("Lumbridge lodestone",new Tile(3233, 3211, 0), 47));
	public static DeltaWebTeleport FALADOR_LODESTONE = new DeltaWebTeleport(new Tile(2965,3403, 0), new DeltaLodestoneAction("Falador lodestone",new Tile(2965,3403, 0), 46));
	public static DeltaWebTeleport AL_KHARID_LODESTONE = new DeltaWebTeleport(new Tile(3297,3184, 0), new DeltaLodestoneAction("Al-Kharid lodestone",new Tile(3297,3184, 0), 40));
	public static DeltaWebTeleport DRAYNOR_LODESTONE = new DeltaWebTeleport(new Tile(3105,3298, 0), new DeltaLodestoneAction("Draynor lodestone",new Tile(3105,3298, 0), 44));
	public static DeltaWebTeleport VARROCK_LODESTONE = new DeltaWebTeleport(new Tile(3214,3376, 0), new DeltaLodestoneAction("Varrock lodestone",new Tile(3214,3376, 0), 51));

	public static DeltaWebTeleport[] ARRAY = {LUMBRIDGE_LODESTONE,FALADOR_LODESTONE,AL_KHARID_LODESTONE,DRAYNOR_LODESTONE,VARROCK_LODESTONE};

}
