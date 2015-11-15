package wtfcore.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.world.World;
import wtfcore.utilities.CavePosition;

public interface IWTFGenerator {
	
	public void generate(World world, int surface, int x, int z, Random random, ArrayList<CavePosition> cavepositions);
	
}
