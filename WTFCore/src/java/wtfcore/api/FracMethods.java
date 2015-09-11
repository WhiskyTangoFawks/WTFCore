package wtfcore.api;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;


public class FracMethods {

	static Random random = new Random();

	public interface IFracture {
		void fracture(World world, int x, int y, int z);		
	}

	public static IFracture defaultfrac = new DefaultFrac();
	
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
	
	public static IFracture wtforesfrac = new DefaultFrac();
	public static class WTFOresFrac implements IFracture{

		@Override
		public void fracture(World world, int x, int y, int z) {
			HashSet<ChunkPosition> hashset = new HashSet<ChunkPosition>();

			for (int loop = 0; loop < 4; loop++){
				//first, it attempts to fracture an adjacent block
				int fracX = 0;
				int fracY = 0;
				int fracZ = 0;

				Block blockToFracture = Blocks.cobblestone;
				while (BlockSets.stoneAndCobble.containsValue(blockToFracture)){
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

				if  (BlockSets.stoneAndCobble.containsKey(blockToFracture)){
					hashset.add(new ChunkPosition(x+fracX, y+fracY, z+fracZ));
				}

				if  (BlockSets.stoneAndCobble.containsKey(blockToFracture)){
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
			fracStone(chunkposition.chunkPosX, chunkposition.chunkPosY, chunkposition.chunkPosZ, world);
		}
	}
	
	public static boolean fracStone(int x, int y, int z, World world){
		Block blockToSet = BlockSets.blockTransformer.get(new BlockInfo(world.getBlock(x, y, z), world.getBlockMetadata(x, y, z), BlockSets.Modifier.cobblestone));
		if (blockToSet != null) { 
			world.setBlock(x,  y,  z, blockToSet, world.getBlockMetadata(x, y, z), 3);
			return true;
		}
		return false;
	}
	
}
