package wtfcore.api;

import net.minecraft.block.Block;

public interface IStoneRegister {
	
	Block stone = null;
	Block cobblestone = null;
	String unlocalisedName = null;
	String[] stoneTextureNames = null;
	String[] cobbleTextureNames = null;
	String domain = null;
	
	/**
	 * generates a set of stalactites and stalagmites based on the stone block
	 */
	public boolean speleothems = true;
	
	/**
	 * generates a lava crust block based on the stone block
	 */
	public boolean lavacrust = true;
	
	/**
	 * generates a mossy versions of the stone
	 */
	public boolean mossyStone = true;
	
	/**
	 * generates a mossy versions of the cobblestone
	 */
	public boolean mossyCobble = true;
	
	/**
	 * generates a dripping water stone and rainstone
	 */
	public boolean drippingWater = true;
	
	/**
	 * generates dripping lava and raining lava stone
	 */
	public boolean drippingLava = true;
	
	/**
	 * adds cobblestone to the falling blocks list- requires WTFTweaks to be present, otherwise it gets ignored
	 */
	public boolean cobbleFall = true;
	
	/**
	 * allows the stone block to be replaced during world generation by cave biomes 
	 */
	public boolean genReplaceable = true;



	public void register();

		

	
}
