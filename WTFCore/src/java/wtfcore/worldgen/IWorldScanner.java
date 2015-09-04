package wtfcore.worldgen;

import java.util.Random;

import net.minecraft.world.World;

public interface IWorldScanner {
	public void generate(World world, Random rand, int chunkX, int chunkZ);
}
