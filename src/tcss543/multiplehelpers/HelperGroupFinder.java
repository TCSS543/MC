package tcss543.multiplehelpers;

import java.util.*;

public class HelperGroupFinder {
	private ArrayList<Helper> eligibleNeighbors;
	
	public HelperGroup findHelperGroup(Requestor requestor) {
		eligibleNeighbors = new ArrayList<Helper>();
		for (Helper h : requestor.neighbors) {
			if (!h.used)
				eligibleNeighbors.add(h);
		}
		
		return f(eligibleNeighbors.size(), requestor.unitsOfWork);
	}
	
	private HelperGroup f(int i, int j) {
		if (i == 0)
			return createEmptyGroup();
		
		if (j - eligibleNeighbors.get(i - 1).capacity < 0) {
			return better(
					f(i - 1, j),
					createSingleElementGroup(eligibleNeighbors.get(i - 1)),
					j);
		} else {
			return better(
					f(i - 1, j),
					createGroupWithExtraElement(
							f(i - 1, j - eligibleNeighbors.get(i - 1).capacity),
							eligibleNeighbors.get(i - 1)),
					j);
		}
	}
	
	private HelperGroup better(HelperGroup a, HelperGroup b, int R) {
		if (a.totalCapacity >= R && b.totalCapacity < R)
			return a;
		if (a.totalCapacity < R && b.totalCapacity >= R)
			return b;
		if (a.totalCapacity < R && b.totalCapacity < R)
			return max(a, b);
		
		return min(a, b);
	}
	
	private HelperGroup max(HelperGroup a, HelperGroup b) {
		if (a.totalCapacity > b.totalCapacity)
			return a;
		
		if (a.totalCapacity < b.totalCapacity)
			return b;
		
		if (a.helpers.size() < b.helpers.size())
			return a;
		
		return b;
	}
	
	private HelperGroup min(HelperGroup a, HelperGroup b) {
		if (a.totalCapacity < b.totalCapacity)
			return a;
		
		if (a.totalCapacity > b.totalCapacity)
			return b;
		
		if (a.helpers.size() < b.helpers.size())
			return a;
		
		return b;
	}
	
	private HelperGroup createEmptyGroup() {
		HelperGroup hg = new HelperGroup();
		hg.totalCapacity = 0;
		hg.helpers = new ArrayList<Helper>();
		return hg;
	}
	
	private HelperGroup createSingleElementGroup(Helper helper) {
		HelperGroup hg = new HelperGroup();
		hg.totalCapacity = helper.capacity;
		hg.helpers = new ArrayList<Helper>();
		hg.helpers.add(helper);
		return hg;
	}
	
	private HelperGroup createGroupWithExtraElement(HelperGroup group, Helper helper) {
		HelperGroup hg = new HelperGroup();
		hg.totalCapacity = group.totalCapacity + helper.capacity;
		hg.helpers = new ArrayList<Helper>(group.helpers);
		hg.helpers.add(helper);
		return hg;
	}
}
