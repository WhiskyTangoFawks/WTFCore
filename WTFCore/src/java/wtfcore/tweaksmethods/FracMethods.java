package wtfcore.tweaksmethods;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import wtfcore.utilities.BlockSets;

public class FracMethods {

	static Random random = new Random();

	public interface IFracture {
		void fracture(World world, int x, int y, int z);		
	}

	public static class DefaultFrac implements IFracture{

		@Override
		public void fracture(World world, int x, int y, int z) {
			HashSet<ChunkPosition> hashset = new HashSet<ChunkPosition>();
			hashset.add(new ChunkPosition(x+1, y, z));
			hashset.add(new ChunkPosition(x-1, y, z));
			hashset.add(new ChunkPosition(x, y+1, z));
			hashset.add(new ChunkPosition(x, y-1, z));
			hashset.add(new ChunkPosition(x, y, z+1));
			hashset.add(new ChunkPosition(x, y, z-1));
			FracIterator(world, hashset);
		}

	}

	public static class WTFOresFrac implements IFracture{

		@Override
		public void fracture(World world, int x, int y, int z) {
			HashSet<ChunkPosition> hashset = new HashSet<ChunkPosition>();

			//modify the WTFOres hashmap to include ore level, required for breaking, and to influence fracturing
			//event.getPlayer().getHeldItem().get

			for (int loop = 0; loop < 4; loop++){
				//first, it attempts to fracture an adjacent block
				int fracX = 0;
				int fracY = 0;
				int fracZ = 0;

				Block blockToFracture = Blocks.cobblestone;
				while (BlockSets.isCobblestone(blockToFracture)){
					int frac = random.nextInt(6);
					switch (frac){
					case 0 :
						fracX -= 1;
						break;
					case 1 :
						fracX += 1;
						break;
					case 2 :
						fracY -= 1;
						break;
					case 3 :
						fracY += 1;
						break;
					case 4 :
						fracZ -= 1;
						break;
					case 5 :
						fracZ += 1;
						break;
					}
					blockToFracture = world.getBlock(x+fracX, y+fracY, z+fracZ);
				}

				if  (BlockSets.isStone(blockToFracture)){
					hashset.add(new ChunkPosition(x+fracX, y+fracY, z+fracZ));
				}

				if  (BlockSets.isStone(blockToFracture)){
					hashset.add(new ChunkPosition(x+(fracX), y+(fracY), z+(fracZ)));
				}


			}
			FracIterator(world, hashset);
		}

	}


	public static void FracIterator(World world, HashSet<ChunkPosition> hashset){
		ChunkPosition chunkposition;
		Iterator<ChunkPosition> iterator = hashset.iterator();
		while (iterator.hasNext()){
			chunkposition = (ChunkPosition)iterator.next();
			WTFmethods.Fracture(chunkposition.chunkPosX, chunkposition.chunkPosY, chunkposition.chunkPosZ, world);
		}

	}
}
