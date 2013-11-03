package tcss543.multiplehelpers;

import java.util.*;

import tcss543.*;

public class MultipleHelpersMatcherLeastWasteFirst implements Matcher {

	@Override
	public int[][] findMatching(DeviceGraph graph) {
		ObjectGraphBuilder ogb = new ObjectGraphBuilder();
		HashSet<Requestor> requestors = ogb.buildObjectGraph(graph);
		int requestorCount = requestors.size();
		HelperGroupFinder hgf = new HelperGroupFinder();
		ArrayList<Requestor> matchedRequestors = new ArrayList<Requestor>();
		
		int minDiff;
		Requestor bestCandidate;
		HelperGroup candidateHG;
		
		
		do {
			minDiff = Integer.MAX_VALUE;
			bestCandidate = null;
			candidateHG = null;
			
			for (Requestor r : requestors) {
				HelperGroup hg = hgf.findHelperGroup(r);
				if (hg.totalCapacity >= r.unitsOfWork
						&& (hg.totalCapacity - r.unitsOfWork) < minDiff) {
					minDiff = hg.totalCapacity - r.unitsOfWork;
					bestCandidate = r;
					candidateHG = hg;
				}
			}
			
			if (bestCandidate != null) {
				bestCandidate.selectedHelperGroup = candidateHG;
				
				for (Helper h : candidateHG.helpers) {
					h.used = true;
				}
				
				matchedRequestors.add(bestCandidate);
				requestors.remove(bestCandidate);
			}
		} while (bestCandidate != null);
		
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
