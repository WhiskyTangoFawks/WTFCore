package wtfcore.worldgen;

import java.util.Random;


import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import wtfcore.WorldGenListener;
import wtfcore.api.BlockSets;

public class OverworldScanner implements IWorldScanner {
	
	
	
	public void generate(World world, Random rand, int chunkX, int chunkZ)
	{
		
		
		int lastY = 70;
		Chunk chunk = world.getChunkFromBlockCoords(chunkX, chunkZ);
		int surfaceaverage = 0;

		for (int xloop = 0; xloop < 16; xloop++)
		{
			int x = chunkX + xloop;
			for (int zloop = 0; zloop < 16; zloop++){
				int z = chunkZ + zloop;
				int y = scanForSurface(chunk, x, lastY, z);
				lastY = y;
				surfaceaverage += y;
				
				
			}
		}
		surfaceaverage /= 256;
		if (surfaceaverage < 64){ surfaceaverage = 64;}
		
		if (WorldGenListener.generator != null){
			WorldGenListener.generator.generate(world, surfaceaverage, chunkX, chunkZ, rand);
		}
	}
	
	public int scanForSurface(Chunk chunk, int x, int y, int z){
		while (!chunk.canBlockSeeTheSky(x & 15, y, z & 15) && y<256){
			y+=10;
		}	
		//initial scan to find the first non-air block
		while (isAirAndCheck(chunk, x,y,z) && y > 40)
		{
			y--;
		}
		while (isSurfaceAndCheck(chunk, x, y, z) && y >40){
			y--;
		}
		return y;
	}
	
	public boolean isAirAndCheck(Chunk chunk, int x, int y, int z){
		Block block = chunk.getBlock(x & 15, y, z & 15);
		if (BlockSets.genReplace.containsKey(block)){
			
			//do replace magic here
			return false;
		}
		return block.isAir(chunk.worldObj, x, y, z);
	}
	
	public boolean isSurfaceAndCheck(Chunk chunk, int x, int y, int z){
		Block block = chunk.getBlock(x&15, y, z&15);
		if (BlockSets.genReplace.containsKey(block)){
			//do replace magic here
		}
		return BlockSets.surfaceBlocks.contains(block);
	}
	

	
}
