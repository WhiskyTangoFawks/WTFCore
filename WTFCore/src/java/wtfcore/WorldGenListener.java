package wtfcore;

import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import wtfcore.worldgen.IWTFGenerator;
import wtfcore.worldgen.WorldScanner;
import java.util.HashMap;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class WorldGenListener {
	
	public static IWTFGenerator generator;
	
	public static HashMap<Integer, WorldScanner> GetScanner = new HashMap<Integer, WorldScanner>();
	
	
	//This listens for chunk population, then tries to pull a generator from the hashmap based on the dimension ID
	//if there is a generator associated with the dimension ID, it runs world scanning and generation.
	@SubscribeEvent(priority=EventPriority.LOWEST)
	public void populate(PopulateChunkEvent.Post event){

		WorldScanner scanner = GetScanner.get(event.world.provider.dimensionId);
		if (scanner != null){
			scanner.generateSurface(event.world, event.rand, event.chunkX<< 4, event.chunkZ<< 4);
		}

	}

}
