package wtfcore.api;


import exterminatorJeff.undergroundBiomes.api.BlockCodes;
import exterminatorJeff.undergroundBiomes.api.UBAPIHook;
import exterminatorJeff.undergroundBiomes.api.UBStrataColumn;
import exterminatorJeff.undergroundBiomes.api.UBStrataColumnProvider;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import wtfcore.utilities.UBCblocks;

public abstract class Replacer {

	public abstract void doReplace(Chunk chunk, int x, int y, int z, Block oldBlock);
	

	protected static BlockInfo getUBCStone(World world, int x, int y, int z){
		UBStrataColumnProvider columnProvider = UBAPIHook.ubAPIHook.dimensionalStrataColumnProvider.ubStrataColumnProvider(0);
		UBStrataColumn column = columnProvider.strataColumn(x, z);
		BlockCodes stoneCode = column.stone(y);
		return new BlockInfo(stoneCode.block, stoneCode.metadata);
	}
	/**
	 **Use instead of world.setBlock, when you don't want it to update adjacent blocks.  non-fluid blocks placed during world generation should use this method.
	 **/
	public static boolean setBlockWithoutNotify(World world, int x, int y, int z, Block block, int metadata){
		
		int flags = 0;
		Chunk chunk = world.getChunkFromChunkCoords(x >> 4, z >> 4);
		net.minecraftforge.common.util.BlockSnapshot blockSnapshot = null;
		if ((flags & 1) != 0){
		}
		if (world.captureBlockSnapshots && !world.isRemote)	{
			blockSnapshot = net.minecraftforge.common.util.BlockSnapshot.getBlockSnapshot(world, x, y, z, flags);
			world.capturedBlockSnapshots.add(blockSnapshot);
		}
		boolean flag = chunk.func_150807_a(x & 15, y, z & 15, block, metadata);
		if (!flag && blockSnapshot != null)	{
			world.capturedBlockSnapshots.remove(blockSnapshot);
			blockSnapshot = null;
		}
		//Disabled light updating.  I think.
		//world.theProfiler.startSection("checkLight");
		//world.func_147451_t(x, y, z);
		//world.theProfiler.endSection();
		return flag;
	}

}
