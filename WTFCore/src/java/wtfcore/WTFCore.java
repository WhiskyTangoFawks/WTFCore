package wtfcore;

import net.minecraftforge.common.MinecraftForge;
import wtfcore.api.BlockSets;
import wtfcore.proxy.CommonProxy;
import wtfcore.utilities.LoadBlockSets;
import wtfcore.utilities.UBCblocks;
import wtfcore.worldgen.NetherScanner;
import wtfcore.worldgen.OverworldScanner;
import wtfcore.worldgen.WorldGenListener;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import java.util.Iterator;

import org.apache.logging.log4j.Logger;

@Mod(modid = WTFCore.modid, name = "WTFCore", version = "1.63",  dependencies = "after:UndergroundBiomes")

public class WTFCore {

	public static  final String modid = "WTFCore";
	public static  final String CaveBiomes = "CaveBiomes";
	public static  final String WTFOres = "WTFOres";
	public static  final String WTFTweaks = "WTFTweaks";

	public static Logger log;

	@SidedProxy(clientSide="wtfcore.proxy.ClientProxy", serverSide="wtfcore.proxy.CommonProxy")
	public static CommonProxy proxy;


	@EventHandler
	public void PreInit(FMLPreInitializationEvent preEvent)
	{
		log = preEvent.getModLog();
		
		//Load a default scanner into the hashmap for the overworld
		
		WTFCoreConfig.customConfig();
		
		Iterator<Integer> iterator = WTFCoreConfig.overworlds.iterator();
		while (iterator.hasNext()){
			int dimensionID = iterator.next();
			WorldGenListener.GetScanner.put(dimensionID, new OverworldScanner());
			WTFCore.log.info("Adding default overworld scanner for dimension " + dimensionID);
		}
		iterator = WTFCoreConfig.nethers.iterator();
		while (iterator.hasNext()){
			int dimensionID = iterator.next();
			WorldGenListener.GetScanner.put(dimensionID, new NetherScanner());	
			WTFCore.log.info("Adding default nether scanner for dimension " + dimensionID);
		}

		if (Loader.isModLoaded("UndergroundBiomes")){
			UBCblocks.loadUBCStone();
		}
	}
	@EventHandler public void load(FMLInitializationEvent event)
	{
		proxy.clientRegister();
		MinecraftForge.EVENT_BUS.register(new WorldGenListener());

	}
	@EventHandler
	public void PostInit(FMLPostInitializationEvent postEvent){

		LoadBlockSets.setHashSets();



	}



}
