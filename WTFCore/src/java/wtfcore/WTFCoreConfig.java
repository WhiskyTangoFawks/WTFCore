package wtfcore;

import java.io.File;
import java.util.HashSet;

import net.minecraftforge.common.config.Configuration;


public class WTFCoreConfig {
	static Configuration config = new Configuration(new File("config/WTFCoreConfig.cfg"));

	
	static HashSet<Integer> overworlds = new HashSet<Integer>();
	static HashSet<Integer> nethers = new HashSet<Integer>();;
	
	public static void customConfig() {
		config.load();
		
		String overworldIDs = config.get("Dimension IDs", "List of Dimension IDs to run OverworldScanner on", "0").getString();
		String[] OverworldArray = overworldIDs.split(",");
		for (int loop = 0; loop < OverworldArray.length; loop++){
			overworlds.add(Integer.parseInt(OverworldArray[loop]));
		}
		
		String netherIDs = config.get("Dimension IDs", "List of Dimension IDs to run NetherScanner on", "-1").getString();
		String[] netherArray = netherIDs.split(",");
		for (int loop = 0; loop < netherArray.length; loop++){
			nethers.add(Integer.parseInt(netherArray[loop]));
		}
		
		config.save();
	}
	
	
	
}
