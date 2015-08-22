package wtfcore.utilities;

import net.minecraft.block.Block;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;


public class UBCblocks {

	public static final float[] hardnessIgneousStone = {1.7f, 1.6f, 1.3f, 1.4f, 1.0f, 1.4f, 1.5f, 1.2f};
	public static final float[] resistanceIgneousStone = {1.42f, 1.39f, 1.26f, 1.31f, 1.0f, 1.31f, 1.35f, 1.2f};

	public static final float[] hardnessMetamorphicStone = {1.1f, 1.0f, 1.1f, 1.3f, 0.7f, 0.7f, 0.4f, 0.9f};
	public static final float[] resistanceMetamorphicStone = {1.11f, 1.0f, 1.11f, 1.26f, 0.54f, 0.54f, 0.2f, 0.86f};

	public static final float[] hardnessSedimentaryStone = {0.5f, 0.5f, 0.5f, 0.6f, 0.5f, 0.5f, 1.0f, 0.9f};
	public static final float[] resistanceSedimentaryStone = {0.29f, 0.29f, 0.29f, 0.4f, 0.29f, 0.29f, 1.0f, 0.86f};

	public static final String[] IgneousStoneList = new String[]{"redGranite", "blackGranite", "rhyolite", "andesite", "gabbro", "basalt", "komatiite", "dacite"};
	public static final String[] MetamorphicStoneList = new String[]{"gneiss", "eclogite", "marble", "quartzite", "blueschist", "greenschist", "soapstone", "migmatite"};
	public static final String[] SedimentaryStoneList = new String[]{"limestone", "chalk", "shale", "siltstone", "ligniteBlock", "dolomite", "greywacke", "chert"};

	public static final String[] IgneousCobblestoneList = new String[]{"redGraniteCobble", "blackGraniteCobble", "rhyoliteCobble", "andesiteCobble", "gabbroCobble", "basaltCobble", "komatiiteCobble", "daciteCobble"};
	public static final String[] MetamorphicCobblestoneList = new String[]{"gneissCobble", "eclogiteCobble", "marbleCobble", "quartziteCobble", "blueschistCobble", "greenschistCobble", "soapstoneCobble", "migmatiteCobble"};


	public static Block IgneousStone;
	public static Block IgneousCobblestone;
	public static Block MetamorphicStone;
	public static Block MetamorphicCobblestone;
	public static Block SedimentaryStone;

	public static void loadUBCStone(){

		IgneousStone = UndergroundBiomes.igneousStone;
		IgneousCobblestone = UndergroundBiomes.igneousCobblestone;
		MetamorphicStone = UndergroundBiomes.metamorphicStone;
		MetamorphicCobblestone = UndergroundBiomes.metamorphicCobblestone;
		SedimentaryStone = UndergroundBiomes.sedimentaryStone;
	}



}
