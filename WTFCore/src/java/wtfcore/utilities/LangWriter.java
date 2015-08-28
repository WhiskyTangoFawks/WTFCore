package wtfcore.utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

public class LangWriter {

	public static void genLangFile(HashSet<String> hashset, String fileName){
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

			Iterator<String> iterator = hashset.iterator();
			while (iterator.hasNext()){
				String string = (String) iterator.next();
				writer.write(string);
				writer.newLine();
			}
			writer.close();

		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}




}
