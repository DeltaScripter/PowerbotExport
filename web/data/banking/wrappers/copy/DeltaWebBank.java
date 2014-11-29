package web.data.banking.wrappers.copy;

import org.powerbot.game.api.wrappers.Tile;

/**
 * Author: Tom
 * Date: 07/04/12
 * Time: 20:42
 */
public abstract class DeltaWebBank {

	private final String    name;
	private final Tile      tile;
	private final DeltaBANK_TYPE type;
	private final boolean   members;

	public DeltaWebBank(String name, final Tile tile, boolean members, DeltaBANK_TYPE type) {
		this.name = name;
		this.tile = tile;
		this.members = members;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public Tile getTile() {
		return tile;
	}

	public DeltaBANK_TYPE getType() {
		return type;
	}

	public boolean isMembers() {
		return members;
	}

	public abstract boolean canAccess();

	@Override
	public String toString() {
		return name;
	}

}