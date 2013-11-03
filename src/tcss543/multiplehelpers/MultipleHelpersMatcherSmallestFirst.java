package tcss543.multiplehelpers;

import java.util.*;

import tcss543.*;

public class MultipleHelpersMatcherSmallestFirst implements Matcher {

	@Override
	public int[][] findMatching(DeviceGraph graph) {
		ObjectGraphBuilder ogb = new ObjectGraphBuilder();
		HashSet<Requestor> unsortedRequestors = ogb.buildObjectGraph(graph);
		int requestorCount = unsortedRequestors.size();
		TreeSet<Requestor> requestors = new TreeSet<Requestor>(new RequestorComparator());
		requestors.addAll(unsortedRequestors);
		HelperGroupFinder hgf = new HelperGroupFinder();
		ArrayList<Requestor> matchedRequestors = new ArrayList<Requestor>();
		
		Requestor current = null;
		while ((current = requestors.pollFirst()) != null) {
			HelperGroup hg = hgf.findHelperGroup(current);
			if (hg.totalCapacity >= current.unitsOfWork) {
				current.selectedHelperGroup = hg;
				
				for (Helper h : hg.helpers) {
					h.used = true;
				}
				
				matchedRequestors.add(current);
			}
		}
		
		int[][] requestorsAndGroups = new int[requestorCount][];
		
		for (Requestor r : matchedRequestors) {
			
			requestorsAndGroups[r.i] =
					new int[r.selectedHelperGroup.helpers.size()];
			for (int j = 0; j < requestorsAndGroups[r.i].length; j++) {
				requestorsAndGroups[r.i][j] = r.selectedHelperGroup.helpers.get(j).j;
			}
		}
		
		return requestorsAndGroups;
	}

}
