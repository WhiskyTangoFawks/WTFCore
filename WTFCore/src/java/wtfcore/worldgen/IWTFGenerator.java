package wtfcore.worldgen;

import java.util.Random;

import net.minecraft.world.World;

public interface IWTFGenerator {
	
	public void generate(World world, int surface, int x, int z, Random random);
	
}
