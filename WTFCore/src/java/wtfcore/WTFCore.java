package wtfcore;

import net.minecraftforge.common.MinecraftForge;
import wtfcore.proxy.CommonProxy;
import wtfcore.utilities.BlockSets;
import wtfcore.utilities.UBCblocks;
import wtfcore.worldgen.WorldScanner;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Logger;

@Mod(modid = WTFCore.modid, name = "WTFCore", version = "1.51",  dependencies = "after:UndergroundBiomes")

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
		WorldGenListener.GetScanner.put(0, new WorldScanner());
		
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

		BlockSets.setHashSets();



	}



}
