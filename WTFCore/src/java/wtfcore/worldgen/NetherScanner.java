package wtfcore.worldgen;

import java.util.Random;

import net.minecraft.world.World;
import wtfcore.WorldGenListener;

public class NetherScanner implements IWorldScanner{

	@Override
	public void generate(World world, Random rand, int chunkX, int chunkZ) {
		if (WorldGenListener.generator != null){
			WorldGenListener.generator.generate(world, world.getActualHeight(), chunkX, chunkZ, rand);
		}
		
	}


	

}
