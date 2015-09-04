package wtfcore.worldgen;

import java.util.Random;

import net.minecraft.world.World;
import wtfcore.WorldGenListener;
import wtfcore.utilities.BlockSets;

public class OverworldScanner implements IWorldScanner {
	
	public void generate(World world, Random rand, int chunkX, int chunkZ)
	{
		//WTFCore.log.info("Vanilla generator running");
		int lastY = 70;

		int surfaceaverage = 0;

		for (int xloop = 0; xloop < 16; xloop++)
		{
			int x = chunkX + xloop;
			for (int zloop = 0; zloop < 16; zloop++){
				int z = chunkZ + zloop;
				int y = scanForSurface(world, x, lastY, z);
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

	
	public int scanForSurface(World world, int x, int y, int z){
		//this makes sure that it's not given us a block below the surface level
		while (!world.canBlockSeeTheSky(x, y, z) && y<256){
			y+=10;
		}
		//initial scan to find the first non-air block
		while (world.isAirBlock(x,y,z) && y > 50)
		{
			y--;
		}
		while (!BlockSets.surfaceBlocks.contains(world.getBlock(x, y, z)) && y >50){
			y--;
		}
		return y;
	}
}
