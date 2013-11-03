package tcss543;

import java.io.*;
import java.util.*;

public class GraphFileImporter {
	private BufferedReader reader;
	
	public GraphFileImporter(String fileName) throws FileNotFoundException {
		reader = new BufferedReader(new FileReader(fileName));
	}
	
	public DeviceGraph Load() throws IOException {
		int[] requestors = getNodes();
		int[] helpers = getNodes();
		int[][] neighbors = getNeighbors(requestors.length);
		
		return new DeviceGraph(requestors, helpers, neighbors);
	}
	
	private int[] getNodes() throws NumberFormatException, IOException {
		String line;
		ArrayList<Integer> nodes;
		
		nodes = new ArrayList<Integer>();
		while ((line = reader.readLine()).length() != 0) {
			nodes.add(Integer.parseInt(line));
		}
		
		int[] nodeArray = new int[nodes.size()];
		
		for (int i = 0; i < nodes.size(); i++) {
			nodeArray[i] = nodes.get(i);
		}
		
		return nodeArray;
	}
	
	private int[][] getNeighbors(int requestorCount) throws IOException {
		ArrayList<ArrayList<Integer>> reqNeighbors = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < requestorCount; i++) {
			reqNeighbors.add(new ArrayList<Integer>());
		}
		
		String line;
		
		while ((line = reader.readLine()) != null) {
			String[] rh = line.split(",");
			int r = Integer.parseInt(rh[0]);
			int h = Integer.parseInt(rh[1]);
			
			reqNeighbors.get(r - 1).add(h - 1);
		}
		
		int[][] neighbors = new int[requestorCount][];
		for (int i = 0; i < requestorCount; i++) {
			neighbors[i] = new int[reqNeighbors.get(i).size()];
			
			for (int j = 0; j < neighbors[i].length; j++) {
				neighbors[i][j] = reqNeighbors.get(i).get(j);
			}
		}
		
		return neighbors;
	}
}
