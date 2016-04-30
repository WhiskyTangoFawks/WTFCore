package wtfcore.replacers;

import net.minecraft.block.Block;
import net.minecraft.world.chunk.Chunk;
import wtfcore.api.BlockInfo;
import wtfcore.api.Replacer;

public class StoneReplacer extends Replacer{

	public void doReplace(Chunk chunk, int x, int y, int z, Block oldBlock){
		BlockInfo ubcblock = getUBCStone(chunk.worldObj,x,y,z);
		setBlockWithoutNotify(chunk.worldObj, x, y, z, ubcblock.block, ubcblock.meta);
	}
	
}
