package wtfcore.worldgen;

import java.util.Random;

import net.minecraft.world.World;

public class NullScanner  implements IWorldScanner{

	@Override
	public void generate(World world, Random rand, int chunkX, int chunkZ) {
		// do nothing- it's the null scanner
		
	}

}
