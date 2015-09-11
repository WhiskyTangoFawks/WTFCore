package wtfcore;

import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import wtfcore.worldgen.IWTFGenerator;
import wtfcore.worldgen.IWorldScanner;
import java.util.HashMap;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class WorldGenListener {
	
	public static IWTFGenerator generator;
	
	public static HashMap<Integer, IWorldScanner> GetScanner = new HashMap<Integer, IWorldScanner>();
	
	
	//This listens for chunk population, then tries to pull a generator from the hashmap based on the dimension ID
	//if there is a generator associated with the dimension ID, it runs world scanning and generation.
	@SubscribeEvent(priority=EventPriority.LOWEST)
	public void populate(PopulateChunkEvent.Post event){

		IWorldScanner scanner = GetScanner.get(event.world.provider.dimensionId);
		if (scanner != null){
			
			scanner.generate(event.world, event.rand, event.chunkX<< 4, event.chunkZ<< 4);
			
		}
		else {
			WTFCore.log.info("No scanner found for dimension " + event.world.provider.dimensionId);
		}

	}

}
