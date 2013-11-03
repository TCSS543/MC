package tcss543.multiplehelpers;

import java.util.Comparator;

public class RequestorComparator implements Comparator<Requestor> {

	@Override
	public int compare(Requestor arg0, Requestor arg1) {
		return Integer.compare(arg0.unitsOfWork, arg1.unitsOfWork);
	}

}
