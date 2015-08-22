package wtfcore.utilities;

import java.util.HashMap;
import java.util.HashSet;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import wtfcore.tweaksmethods.FracMethods;
import wtfcore.tweaksmethods.FracMethods.IFracture;
import cpw.mods.fml.common.Loader;


public class BlockSets {

	private static HashMap<Block, Integer> fallingBlocks = new HashMap<Block, Integer>();
	private static HashMap<Block, IFracture> oreBlocks = new HashMap<Block, IFracture>();
	private static HashMap<Block, Block> stoneBlocks = new HashMap<Block, Block>();
	private static HashMap<Block, Integer> explosiveBlocks = new HashMap<Block, Integer>();
	public static HashMap<OreBlockInfo, Block> oreUbifier = new HashMap <OreBlockInfo, Block>();
	public static HashMap <Block,Float> explosiveModBlock = new HashMap<Block, Float>();

	//WorldGenHashSets
	public static HashSet<Block> ReplaceHashset = new HashSet<Block>();
	public static HashSet<Block> surfaceBlocks = new HashSet<Block>();
	public static HashMap<BlockInfo, Block> floorBlock = new HashMap<BlockInfo, Block>();
	public static HashSet<Block> meltBlocks = new HashSet<Block>();


	public enum Modifier {
		cobblestone, mossy_cobblestone, stoneMagmaCrust, MossyStone, waterDrippingStone, WaterRainStone, lavaDrippinStone, LavaRainStone 
	}


	public static void setHashSets(){

		//put config options controlling whether blocks get added to the hashsets

		//WTFTweaks HashSets
		addFallingBlock(Blocks.cobblestone, 1, 1);
		addFallingBlock(Blocks.mossy_cobblestone, 1, 1);
		addFallingBlock(Blocks.dirt, 2, 1);
		addFallingBlock(Blocks.grass, 1, 1);

		addOreBlock(Blocks.iron_ore);
		addOreBlock(Blocks.diamond_ore);
		addOreBlock(Blocks.lapis_ore);
		addOreBlock(Blocks.gold_ore);
		addOreBlock(Blocks.emerald_ore);
		addOreBlock(Blocks.redstone_ore);
		addOreBlock(Blocks.lit_redstone_ore);
		addOreBlock(Blocks.coal_ore);

		addStoneBlock(Blocks.stone, Blocks.cobblestone, 1);

		explosiveModBlock.put(Blocks.iron_block, 0.8F);
		explosiveModBlock.put(Blocks.diamond_block, 0.5F);
		explosiveModBlock.put(Blocks.obsidian, 0.2F);


		addExplosiveBlock(Blocks.redstone_ore, 2);
		addExplosiveBlock(Blocks.lit_redstone_ore, 3);
		addExplosiveBlock(Blocks.redstone_torch, 1);

		if (Loader.isModLoaded("UndergroundBiomes")){
			addFallingBlock(UBCblocks.MetamorphicCobblestone, 1, 8);
			addFallingBlock(UBCblocks.IgneousCobblestone, 1, 8);

			addStoneBlock(UBCblocks.IgneousStone, UBCblocks.IgneousCobblestone, 8);
			addStoneBlock(UBCblocks.MetamorphicStone, UBCblocks.MetamorphicCobblestone, 8);
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


		if (Loader.isModLoaded("CaveBiomes")){
			floorBlock.put(new BlockInfo(Blocks.stone, 0, Modifier.cobblestone), Blocks.cobblestone);
			floorBlock.put(new BlockInfo(Blocks.stone, 0, Modifier.mossy_cobblestone), Blocks.mossy_cobblestone);

		}


		if (Loader.isModLoaded("UndergroundBiomes"))
		{
			surfaceBlocks.add(UBCblocks.IgneousStone);
			surfaceBlocks.add(UBCblocks.MetamorphicStone);
			surfaceBlocks.add(UBCblocks.SedimentaryStone);
			ReplaceHashset.add(UBCblocks.IgneousStone);
			ReplaceHashset.add(UBCblocks.MetamorphicStone);
			ReplaceHashset.add(UBCblocks.SedimentaryStone);
			floorBlock.put(new BlockInfo(UBCblocks.IgneousStone, 0, Modifier.cobblestone), UBCblocks.IgneousCobblestone);
			floorBlock.put(new BlockInfo(UBCblocks.MetamorphicStone, 0, Modifier.cobblestone), UBCblocks.MetamorphicCobblestone);
		}

	}

	public static void addExplosiveBlock(Block block, int size){
		explosiveBlocks.put(block, size);
	}

	public static void addFallingBlock(Block block, int stability){
		addFallingBlock(block, stability, 0);
	}

	public static void addFallingBlock(Block block, int stability, int metaloop){
		fallingBlocks.put(block, stability);


	}

	public static void addStoneBlock(Block key, Block value, int metaloop){
		stoneBlocks.put(key, value);
	}
	public static void addStoneBlock(Block key, Block value){
		addStoneBlock(key, value, 0);
	}

	public static boolean isExplosive(Block block){
		return explosiveBlocks.containsKey(block);
	}
	public static int getExplosionSize(Block block){
		return explosiveBlocks.get(block);
	}

	public static void addOreBlock(Block block){
		oreBlocks.put(block, new FracMethods.DefaultFrac());
	}
	public static boolean shouldFall(Block block){
		return fallingBlocks.containsKey(block);
	}
	public static int getStabilty(Block block){
		return fallingBlocks.get(block);
	}
	public static boolean isOre(Block block){
		return oreBlocks.containsKey(block);
	}
	public static IFracture getFrac(Block block){
		return oreBlocks.get(block);
	}
	public static boolean isStone(Block block){
		return stoneBlocks.containsKey(block);
	}
	public static Block getCobblestone(Block key){
		return stoneBlocks.get(key);
	}
	public static boolean isCobblestone(Block block){
		return stoneBlocks.containsValue(block);
	}
	


}
