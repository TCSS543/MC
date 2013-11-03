package tcss543;

import java.io.*;

import tcss543.multiplehelpers.*;

public class Program {
	public static void main(String args[]) throws IOException {
		
		GraphFileImporter importer =
				new GraphFileImporter(args[0]);
		
		DeviceGraph graph = importer.Load();
		
		Matcher matcher = new MultipleHelpersMatcherLargestFirst();
		
		int[][] matching = matcher.findMatching(graph);
		
		for (int i = 0; i < matching.length; i++) {
			System.out.print(i + 1);
			if (matching[i] != null) {
				System.out.print(":\t");
				
				for (int j = 0; j < matching[i].length; j++) {
					System.out.print(matching[i][j] + 1);
					System.out.print("\t");
				}			
			}
			
			System.out.println();
		}
	}
}
