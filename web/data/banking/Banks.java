package pathfinding.web.data.banking;

import org.powerbot.game.api.wrappers.Tile;
import pathfinding.web.data.banking.wrappers.BANK_TYPE;
import pathfinding.web.data.banking.wrappers.WebBank;

/**
 * Author: Tom
 * Date: 07/04/12
 * Time: 20:47
 */
public class Banks {

	public static DeltaWebBank VARROCK_EAST = new DeltaWebBank("Varrock East", new Tile(3253, 3419, 0), false, DeltaBANK_TYPE.NPC) {
		@Override
		public boolean canAccess() {
			return true;
		}
	};

	public static DeltaWebBank VARROCK_WEST = new DeltaWebBank("Varrock West", new Tile(3187, 3438, 0), false, DeltaBANK_TYPE.NPC) {
		@Override
		public boolean canAccess() {
			return true;
		}
	};

	public static DeltaWebBank EDGEVILLE    = new DeltaWebBank("Edgeville", new Tile(3093, 3492, 0), false, DeltaBANK_TYPE.NPC) {
		@Override
		public boolean canAccess() {
			return true;
		}
	};

	public static DeltaWebBank DRAYNOR      = new DeltaWebBank("Draynor", new Tile(3093, 3245, 0), false, DeltaBANK_TYPE.NPC) {
		@Override
		public boolean canAccess() {
			return true;
		}
	};
	public static DeltaWebBank FALADOR_EAST = new DeltaWebBank("Falador East", new Tile(3014, 3355, 0), false, DeltaBANK_TYPE.NPC) {
		@Override
		public boolean canAccess() {
			return true;
		}
	};

	public static DeltaWebBank FALADOR_WEST = new DeltaWebBank("Falador West", new Tile(2945, 3368, 0), false, DeltaBANK_TYPE.NPC) {
		@Override
		public boolean canAccess() {
			return true;
		}
	};

	public static DeltaWebBank GRAND_EXCHANGE = new DeltaWebBank("Grand Exchange", new Tile(3160, 3490, 0), false, DeltaBANK_TYPE.NPC) {
		@Override
		public boolean canAccess() {
			return true;
		}
	};

	public static DeltaWebBank AL_KHARID = new DeltaWebBank("Al Kharid", new Tile(3269, 3167, 0), false, DeltaBANK_TYPE.NPC) {
		@Override
		public boolean canAccess() {
			return true;
		}
	};

	public static DeltaWebBank[] bankArray =
			{VARROCK_EAST, VARROCK_WEST, EDGEVILLE, DRAYNOR, FALADOR_EAST,
			 FALADOR_WEST, GRAND_EXCHANGE,AL_KHARID};

}
