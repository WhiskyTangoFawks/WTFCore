package wtfcore.utilities;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.init.Blocks;
import wtfcore.tweaksmethods.FracMethods;
import wtfcore.tweaksmethods.FracMethods.IFracture;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameData;
import exterminatorJeff.undergroundBiomes.common.block.BlockUBOre;


public class BlockSets {

	private static HashMap<Block, Integer> fallingBlocks = new HashMap<Block, Integer>();
	private static HashMap<Block, IFracture> oreAndFractures = new HashMap<Block, IFracture>();
	private static HashMap<Block, Block> stoneAndCobble = new HashMap<Block, Block>();
	private static HashMap<Block, Integer> explosiveBlocks = new HashMap<Block, Integer>();
	public static HashMap<OreBlockInfo, Block> oreUbifier = new HashMap <OreBlockInfo, Block>();
	
	//explosive Backstop
	public static HashMap <Block,Float> explosiveModBlock = new HashMap<Block, Float>();
	
	public static HashSet<String> defaultFallingBlocks = new HashSet<String>();
	
	//WorldGenHashSets
	public static HashSet<Block> ReplaceHashset = new HashSet<Block>();
	public static HashSet<Block> surfaceBlocks = new HashSet<Block>();
	public static HashMap<BlockInfo, Block> blockTransformer = new HashMap<BlockInfo, Block>();
	public static HashSet<Block> meltBlocks = new HashSet<Block>();


	public enum Modifier {
		cobblestone, mossy_cobblestone, stoneMagmaCrust, MossyStone, waterDrippingStone, WaterRainStone, lavaDrippinStone, LavaRainStone 
	}


	public static void setHashSets(){

		//put config options controlling whether blocks get added to the hashsets

		//WTFTweaks HashSets
		addDefaultFallingBlock(Blocks.cobblestone, 1);
		addDefaultFallingBlock(Blocks.mossy_cobblestone, 1);
		addDefaultFallingBlock(Blocks.dirt, 2);
		addDefaultFallingBlock(Blocks.grass, 1);

		addOreBlock(Blocks.iron_ore, FracMethods.defaultfrac);
		addOreBlock(Blocks.diamond_ore, FracMethods.defaultfrac);
		addOreBlock(Blocks.lapis_ore, FracMethods.defaultfrac);
		addOreBlock(Blocks.gold_ore, FracMethods.defaultfrac);
		addOreBlock(Blocks.emerald_ore, FracMethods.defaultfrac);
		addOreBlock(Blocks.redstone_ore, FracMethods.defaultfrac);
		addOreBlock(Blocks.lit_redstone_ore, FracMethods.defaultfrac);
		addOreBlock(Blocks.coal_ore, FracMethods.defaultfrac);

		//addStoneBlock(Blocks.stone, Blocks.cobblestone, 1);

		explosiveModBlock.put(Blocks.iron_block, 0.8F);
		explosiveModBlock.put(Blocks.diamond_block, 0.5F);
		explosiveModBlock.put(Blocks.obsidian, 0.2F);

		addExplosiveBlock(Blocks.redstone_ore, 2);
		addExplosiveBlock(Blocks.lit_redstone_ore, 3);
		addExplosiveBlock(Blocks.redstone_torch, 1);

		if (Loader.isModLoaded("UndergroundBiomes")){
			addDefaultFallingBlock(UBCblocks.MetamorphicCobblestone, 1);
			addDefaultFallingBlock(UBCblocks.IgneousCobblestone, 1);

			//addStoneBlock(UBCblocks.IgneousStone, UBCblocks.IgneousCobblestone, 8);
			//addStoneBlock(UBCblocks.MetamorphicStone, UBCblocks.MetamorphicCobblestone, 8);
		}

		//CaveBiomesWorldGenerationHashSets		
		meltBlocks.add(Blocks.lava);
		meltBlocks.add(Blocks.flowing_lava);
		meltBlocks.add(Blocks.water);
		meltBlocks.add(Blocks.flowing_lava);
		meltBlocks.add(Blocks.torch);
		meltBlocks.add(Blocks.fire);

		ReplaceHashset.add(Blocks.stone);
		ReplaceHashset.add(Blocks.sandstone);
		ReplaceHashset.add(Blocks.dirt);
		ReplaceHashset.add(Blocks.gravel);
		ReplaceHashset.add(Blocks.sand);
		ReplaceHashset.add(Blocks.air);
		ReplaceHashset.add(Blocks.lava);
		ReplaceHashset.add(Blocks.cobblestone);
		ReplaceHashset.add(Blocks.flowing_lava);
		ReplaceHashset.add(Blocks.obsidian);
		ReplaceHashset.add(Blocks.water);
		ReplaceHashset.add(Blocks.flowing_water);

		surfaceBlocks.add(Blocks.dirt);
		surfaceBlocks.add(Blocks.sand);
		surfaceBlocks.add(Blocks.grass);
		surfaceBlocks.add(Blocks.stone);
		surfaceBlocks.add(Blocks.water);

		blockTransformer.put(new BlockInfo(Blocks.stone, 0, Modifier.cobblestone), Blocks.cobblestone);
		blockTransformer.put(new BlockInfo(Blocks.stone, 0, Modifier.mossy_cobblestone), Blocks.mossy_cobblestone);

		if (Loader.isModLoaded("UndergroundBiomes"))
		{
			surfaceBlocks.add(UBCblocks.IgneousStone);
			surfaceBlocks.add(UBCblocks.MetamorphicStone);
			surfaceBlocks.add(UBCblocks.SedimentaryStone);
			ReplaceHashset.add(UBCblocks.IgneousStone);
			ReplaceHashset.add(UBCblocks.MetamorphicStone);
			ReplaceHashset.add(UBCblocks.SedimentaryStone);
			blockTransformer.put(new BlockInfo(UBCblocks.IgneousStone, 0, Modifier.cobblestone), UBCblocks.IgneousCobblestone);
			blockTransformer.put(new BlockInfo(UBCblocks.MetamorphicStone, 0, Modifier.cobblestone), UBCblocks.MetamorphicCobblestone);
		}
		
		Iterator<BlockInfo> cobbleIterator = blockTransformer.keySet().iterator();
		while (cobbleIterator.hasNext()){
			BlockInfo blockinfo = cobbleIterator.next();
			if (blockinfo.addon == Modifier.cobblestone){
				stoneAndCobble.put(blockinfo.block, blockTransformer.get(blockinfo));
			}
		}
	}

	public static void addDefaultFallingBlock(Block block, int stability){
		defaultFallingBlocks.add(GameData.getBlockRegistry().getNameForObject(block)+"@"+stability+",");
	}
	
	public static void addGravity(Block block, int stability){
		fallingBlocks.put(block, stability);
	}

	public static void addExplosiveBlock(Block block, int size){
		explosiveBlocks.put(block, size);
	}
	
	public static boolean isExplosive(Block block){
		return explosiveBlocks.containsKey(block);
	}
	public static int getExplosionSize(Block block){
		return explosiveBlocks.get(block);
	}

	public static void addOreBlock(Block block, FracMethods.IFracture frac){
		oreAndFractures.put(block,frac);
	}
	public static boolean shouldFall(Block block){
		return fallingBlocks.containsKey(block);
	}
	public static int getStabilty(Block block){
		return fallingBlocks.get(block);
	}
	public static boolean isOre(Block block){
		if (oreAndFractures.containsKey(block)){
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
	public static IFracture getFrac(Block block){
		return oreAndFractures.get(block);
	}
	public static boolean hasCobblestone(Block block){
		return stoneAndCobble.containsKey(block);
	}
	public static Block getCobblestone(Block key){
		return stoneAndCobble.get(key);
	}
	public static boolean isCobblestone(Block block){
		return stoneAndCobble.containsValue(block);
	}
	


}
