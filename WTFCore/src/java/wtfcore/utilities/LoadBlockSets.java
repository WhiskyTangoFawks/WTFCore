package wtfcore.utilities;

import java.util.Iterator;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameData;
import exterminatorJeff.undergroundBiomes.common.block.BlockUBOre;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.init.Blocks;
import wtfcore.api.BlockInfo;
import wtfcore.api.BlockSets;
import wtfcore.api.BlockSets.Modifier;


public class LoadBlockSets {


	public static void setHashSets(){
		//put config options controlling whether blocks get added to the hashsets

		//WTFTweaks HashSets
		//addDefaultFallingBlock(Blocks.cobblestone, 1);
		//addDefaultFallingBlock(Blocks.mossy_cobblestone, 1);
		addDefaultFallingBlock(Blocks.dirt, 2);
		addDefaultFallingBlock(Blocks.grass, 1);

		addOreBlock(Blocks.iron_ore);
		addOreBlock(Blocks.diamond_ore);
		addOreBlock(Blocks.lapis_ore);
		addOreBlock(Blocks.gold_ore);
		addOreBlock(Blocks.emerald_ore);
		addOreBlock(Blocks.redstone_ore);
		addOreBlock(Blocks.lit_redstone_ore);
		addOreBlock(Blocks.coal_ore);

		addExplosiveBlock(Blocks.redstone_ore, 2);
		addExplosiveBlock(Blocks.lit_redstone_ore, 3);
		addExplosiveBlock(Blocks.redstone_torch, 1);

		if (Loader.isModLoaded("UndergroundBiomes")){
			addDefaultFallingBlock(UBCblocks.MetamorphicCobblestone, 1);
			addDefaultFallingBlock(UBCblocks.IgneousCobblestone, 1);
		}

		//CaveBiomesWorldGenerationHashSets		

		BlockSets.meltBlocks.add(Blocks.lava);
		BlockSets.meltBlocks.add(Blocks.flowing_lava);
		BlockSets.meltBlocks.add(Blocks.water);
		BlockSets.meltBlocks.add(Blocks.flowing_lava);
		//BlockSets.meltBlocks.add(Blocks.torch);
		BlockSets.meltBlocks.add(Blocks.fire);

		BlockSets.ReplaceHashset.add(Blocks.stone);
		BlockSets.ReplaceHashset.add(Blocks.sandstone);
		BlockSets.ReplaceHashset.add(Blocks.dirt);
		BlockSets.ReplaceHashset.add(Blocks.gravel);
		BlockSets.ReplaceHashset.add(Blocks.sand);
		BlockSets.ReplaceHashset.add(Blocks.air);
		BlockSets.ReplaceHashset.add(Blocks.lava);
		BlockSets.ReplaceHashset.add(Blocks.cobblestone);
		BlockSets.ReplaceHashset.add(Blocks.flowing_lava);
		BlockSets.ReplaceHashset.add(Blocks.obsidian);
		BlockSets.ReplaceHashset.add(Blocks.water);
		BlockSets.ReplaceHashset.add(Blocks.flowing_water);

		BlockSets.surfaceBlocks.add(Blocks.dirt);
		BlockSets.surfaceBlocks.add(Blocks.sand);
		BlockSets.surfaceBlocks.add(Blocks.grass);
		BlockSets.surfaceBlocks.add(Blocks.stone);
		BlockSets.surfaceBlocks.add(Blocks.water);

		BlockSets.blockTransformer.put(new BlockInfo(Blocks.stone, 0, Modifier.cobblestone), Blocks.cobblestone);
		BlockSets.blockTransformer.put(new BlockInfo(Blocks.stone, 0, Modifier.mossy_cobblestone), Blocks.mossy_cobblestone);

		if (Loader.isModLoaded("UndergroundBiomes"))
		{
			BlockSets.blockTransformer.put(new BlockInfo(UBCblocks.IgneousStone, 0, Modifier.cobblestone), UBCblocks.IgneousCobblestone);
			BlockSets.blockTransformer.put(new BlockInfo(UBCblocks.MetamorphicStone, 0, Modifier.cobblestone), UBCblocks.MetamorphicCobblestone);
			BlockSets.surfaceBlocks.add(UBCblocks.IgneousStone);
			BlockSets.surfaceBlocks.add(UBCblocks.MetamorphicStone);
			BlockSets.surfaceBlocks.add(UBCblocks.SedimentaryStone);
		}

		Iterator<BlockInfo> cobbleIterator = BlockSets.blockTransformer.keySet().iterator();
		while (cobbleIterator.hasNext()){
			BlockInfo blockinfo = cobbleIterator.next();
			if (blockinfo.addon == Modifier.cobblestone){
				BlockSets.stoneAndCobble.put(blockinfo.block, BlockSets.blockTransformer.get(blockinfo));
			}
		}
	}

	public static void addDefaultFallingBlock(Block block, int stability){
		BlockSets.defaultFallingBlocks.add(GameData.getBlockRegistry().getNameForObject(block)+"@"+stability+",");
	}
	
	public static void addGravity(Block block, int stability){
		BlockSets.fallingBlocks.put(block, stability);
	}

	public static void addExplosiveBlock(Block block, int size){
		BlockSets.explosiveBlocks.put(block, size);
	}
	
	public static boolean isExplosive(Block block){
		return BlockSets.explosiveBlocks.containsKey(block);
	}
	public static int getExplosionSize(Block block){
		return BlockSets.explosiveBlocks.get(block);
	}

	public static void addOreBlock(Block block){
		BlockSets.oreAndFractures.add(block);
	}
	public static boolean shouldFall(Block block){
		return BlockSets.fallingBlocks.containsKey(block);
	}
	public static int getStabilty(Block block){
		return BlockSets.fallingBlocks.get(block);
	}
	public static boolean isOre(Block block){
		if (BlockSets.oreAndFractures.contains(block)){
			return true;
		}
		else if (Loader.isModLoaded("UndergroundBiomes") && block instanceof BlockUBOre){
			return true;
		}
		else if (block instanceof BlockOre){
			return true;
		}
		return false;
	}
	
	public static boolean hasCobblestone(Block block){
		return BlockSets.stoneAndCobble.containsKey(block);
	}
	public static Block getCobblestone(Block key){
		return BlockSets.stoneAndCobble.get(key);
	}
	public static boolean isCobblestone(Block block){
		return BlockSets.stoneAndCobble.containsValue(block);
	}

}
