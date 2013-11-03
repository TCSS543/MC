package tcss543.multiplehelpers;

import java.util.*;
import tcss543.*;

public class ObjectGraphBuilder {
	public HashSet<Requestor> buildObjectGraph(DeviceGraph graph) {
		Helper[] helpers = new Helper[graph.getH().length];
		
		for (int i = 0; i < graph.getH().length; i++) {
			Helper h = new Helper();
			h.j = i;
			h.capacity = graph.getH()[i];
			h.used = false;
			helpers[i] = h;
		}
		
		HashSet<Requestor> requestors = new HashSet<Requestor>();
		
		for (int i = 0; i < graph.getR().length; i++) {
			Requestor r = new Requestor();
			r.i = i;
			r.unitsOfWork = graph.getR()[i];
			r.neighbors = new ArrayList<Helper>();
			
			for (int j = 0; j < graph.getNeighbors()[i].length; j++) {
				r.neighbors.add(helpers[graph.getNeighbors()[i][j]]);
			}
			
			requestors.add(r);
		}
		
		return requestors;
	}
}
