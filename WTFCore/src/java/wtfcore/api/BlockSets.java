package wtfcore.api;

import java.util.HashMap;
import java.util.HashSet;
import net.minecraft.block.Block;
import net.minecraft.item.Item;


public class BlockSets {

	
	
	public enum Modifier {
		cobblestone, mossy_cobblestone, stoneMagmaCrust, MossyStone, waterDrippingStone, WaterRainStone, lavaDrippinStone, LavaRainStone 
	}
	
	
	public static HashMap<Block, Integer> fallingBlocks = new HashMap<Block, Integer>();
	public static HashSet<Block> oreAndFractures = new HashSet<Block>();
	public static HashMap<Block, Block> stoneAndCobble = new HashMap<Block, Block>();
	public static HashMap<Block, Integer> explosiveBlocks = new HashMap<Block, Integer>();
	public static HashMap<OreBlockInfo, Block> oreUbifier = new HashMap <OreBlockInfo, Block>();
	//public static HashMap <Block, Block> genReplace = new HashMap<Block, Block>();
	public static HashMap<String, IStoneRegister> stoneRegisters = new HashMap<String, IStoneRegister>();
	public static HashMap<Item, Item> itemReplacer = new HashMap<Item, Item>();
	
	//explosive Backstop
	public static HashMap <Block,Float> explosiveModBlock = new HashMap<Block, Float>();
	
	public static HashSet<String> defaultFallingBlocks = new HashSet<String>();
	
	//WorldGenHashSets
	public static HashSet<Block> ReplaceHashset = new HashSet<Block>();
	public static HashSet<Block> surfaceBlocks = new HashSet<Block>();
	public static HashMap<BlockInfo, Block> blockTransformer = new HashMap<BlockInfo, Block>();
	public static HashSet<Block> meltBlocks = new HashSet<Block>();	
	
	public static HashMap<Block, Replacer> genReplace = new HashMap <Block, Replacer>();	


}
