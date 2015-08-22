package wtfcore.utilities;

import net.minecraft.world.ChunkPosition;

public class DungeonBlockPosition extends ChunkPosition{
	 public final boolean floating;

	public DungeonBlockPosition(int p_i45363_1_, int p_i45363_2_, int p_i45363_3_, boolean p_i45363_4_) {
		super(p_i45363_1_, p_i45363_2_, p_i45363_3_);
		this.floating = p_i45363_4_;
	}

}
